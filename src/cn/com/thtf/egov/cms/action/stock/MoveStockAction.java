/**
 * ClassName  MoveStockAction
 *
 * History
 * Create User: liuqg
 * Create Date: 2010-6-28
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.action.stock;

import java.math.BigDecimal;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.NewBaseAction;
import cn.com.thtf.egov.cms.dto.MoveStockAssessDto;
import cn.com.thtf.egov.cms.iservice.common.ICommonService;
import cn.com.thtf.egov.cms.iservice.sell.ISalesContractManagementService;
import cn.com.thtf.egov.cms.iservice.stock.IMoveStockService;
import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.constant.Constants;
import cn.com.thtf.egov.cms.dto.ErrorMsgDto;
import cn.com.thtf.egov.cms.dto.MoveOutStockDto;
import cn.com.thtf.egov.cms.dto.MoveStockListDto;
import cn.com.thtf.egov.cms.dto.MoveStockProductDto;
import cn.com.thtf.egov.cms.dto.MoveStockSearchDto;
import cn.com.thtf.egov.cms.dto.SalesConProductDto;
import cn.com.thtf.egov.cms.dto.WorkReceiverDto;
import cn.com.thtf.egov.cms.entity.MoveDetailEntity;
import cn.com.thtf.egov.cms.entity.MoveEntity;
import cn.com.thtf.egov.cms.entity.ProductTypeEntity;
import cn.com.thtf.egov.cms.entity.StockEntity;
import cn.com.thtf.egov.cms.entity.StockroomAddressEntity;
import cn.com.thtf.egov.cms.entity.StockroomEntity;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.entity.WorkEntity;

/**
 * 移库
 * 
 * @author liuqg
 */

public class MoveStockAction extends NewBaseAction {
    private static Logger log = LoggerFactory.getLogger(MoveStockAction.class);
    private IMoveStockService moveStockService = null;
    private ICommonService commonService = null;

    /**
     * 移库评审显示
     * 
     * @author 蒋名星
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward getShowMoveStockAssess(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        log.debug("进入显示移库评审");

        String moveStockId = (String) (request.getParameter("moveStockId") != null ? request
                .getParameter("moveStockId") : request.getAttribute("moveStockId"));

        moveStockService = (IMoveStockService) this.getApplicationContext().getBean(
                "moveStockServiceImpl");

        // 根据id获得移库评审信息
        MoveStockAssessDto assessDto = moveStockService.getMoveStockById(moveStockId
                .trim());
        if (assessDto == null) {
            request.setAttribute("msg", "移库评审显示失败!");
            return mapping.findForward("failure");
        }

        request.setAttribute("assessDto", assessDto); // 第一个dto

        List<MoveOutStockDto> list = moveStockService.getMoveOutStockInfoView(assessDto);

        /* 产品放在SESSION中 */
        HttpSession session = request.getSession();
        session.setAttribute("moveStockProductlist", list);

        request.setAttribute("list", list);

        /* 防止重复提交 */
        saveToken(request);

        /* 获得区分信息 view 跳转到查看页面 ，showAssess 跳转到显示评审页面 */
        String type = request.getParameter("type");
        if ("showAssess".equals(type)) {
            return mapping.findForward("showAssess");
        } else if ("view".equals(type)) {
            return mapping.findForward("view");
        } else {
            return mapping.findForward("failure");
        }
    }

    /**
     * 移库评审
     * 
     * @author 蒋名星
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public ActionForward getMoveStockAssess(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        log.debug("正在评审");
        ErrorMsgDto errorMsgDto = new ErrorMsgDto();
        /* 防止重复提交 */
        if (isTokenValid(request)) {
            resetToken(request);
        } else {
            request.setAttribute("msg", "请勿重复评审!");
            request.setAttribute("moveStockId", request.getParameter("moveStockId"));
            return mapping.findForward("failureToList");
        }

        // 获得用户id
        UserEntity user = (UserEntity) request.getSession().getAttribute(
                Constants.USERLOGIN);

        String userName = user.getName();

        String assessResult = request.getParameter("assessResult");

        DynaActionForm dform = (DynaActionForm) form;
        MoveStockAssessDto moveStockAssessDto = (MoveStockAssessDto) dform
                .get("moveStockAssess");

        request.setAttribute("moveStockId", moveStockAssessDto.getId());

        moveStockService = (IMoveStockService) this.getApplicationContext().getBean(
                "moveStockServiceImpl");

        /* 产品编码 */
        String[] productIds = request.getParameterValues("productIds");
        /* 移库数 */
        String[] counts = request.getParameterValues("counts");

        /* 产品冻结数 */
        List<StockEntity> stockEntities = new ArrayList<StockEntity>();

        List<MoveOutStockDto> dtos = (List<MoveOutStockDto>) request.getSession()
                .getAttribute("moveStockProductlist");

        for (int i = 0; i < productIds.length; i++) {

            StockEntity stockEntity = new StockEntity();
            stockEntity.setSendLock(Integer.valueOf(counts[i]));// 本次要减少的冻结数
            stockEntity.setProductId(Integer.valueOf(productIds[i]));
            stockEntity.setStockroomId(moveStockAssessDto.getOutStockroomId());
            stockEntity.setTimeStamp(getTimestampById(productIds[i], dtos));
            stockEntities.add(stockEntity);

        }

        int status = 0;
        boolean isSuccess = false;

        String roleId = String.valueOf(user.getRoleId());
        moveStockAssessDto.setRoleId(roleId);

        if ("11".equals(roleId)) {
            // 采购主管意见
            // 防止重复评审
            commonService = (ICommonService) getBean("commonServiceImpl");

            String statusBefore = commonService.getStatusById("move",
                    moveStockAssessDto.getId());

            if ("3".equals(statusBefore) || "4".equals(statusBefore)) {
                request.setAttribute("msg", "请勿重复评审");
                return mapping.findForward("failureToList");
            }
            if (StringUtils.equals("pass", assessResult)) {
                status = 4;
            } else {
                status = 3;
            }

            moveStockAssessDto.setStatus(status);
            moveStockAssessDto.setBuyManName(userName);

            // 如果同意 修改状态
            if (status == 4) {
                // 把采购主管的待办事务减一
                WorkEntity workself = new WorkEntity();
                workself.setCount(-1);
                workself.setUserId(user.getId());
                workself.setWorkId(22);

                isSuccess = moveStockService.updateMoveStockAssess(moveStockAssessDto,
                        workself);
                if (isSuccess) {
                    log.info("User:{},{} 采购主管移库评审通过成功", user.getId(), user.getName());
                } else {
                    log.info("User:{},{} 采购主管移库评审通过失败", user.getId(), user.getName());
                }

            } else {
                WorkEntity workself = new WorkEntity();
                workself.setCount(-1);
                workself.setUserId(user.getId());
                workself.setWorkId(22);
                // 不同意只修改状态
                isSuccess = moveStockService.updateAssessNoWork(moveStockAssessDto,
                        workself, stockEntities, errorMsgDto);
                if (isSuccess) {
                    request.getSession().setAttribute("moveStockProductlist", null);
                    log.info("User:{},{} 采购主管移库评审未通过成功", user.getId(), user.getName());
                } else {
                    request.getSession().setAttribute("moveStockProductlist", null);
                    request.setAttribute("msg", errorMsgDto.getErrmsg());
                    log.info("User:{},{} 采购主管移库评审未通过失败", user.getId(), user.getName());
                    return mapping.findForward("failure");
                }
            }

        }
        if (!isSuccess) {
            request.setAttribute("msg", "评审失败");
            return mapping.findForward("failure");
        }
        return mapping.findForward("success");
    }

    /**
     * 移库管理初始化
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @author zhangzx
     */
    public ActionForward getMoveStockList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("进入移库管理初始列表");

        moveStockService = (IMoveStockService) getBean("moveStockServiceImpl");

        // 获取用户ID
        UserEntity user = (UserEntity) request.getSession().getAttribute(
                Constants.USERLOGIN);
        // 分页
        NewPage newPage = getNewPage(request);
        newPage.setUrl("getMoveStockList.do");

        List<MoveStockListDto> moveStockListDtos = null;
        List<ProductTypeEntity> productTypeEntities = null;
        List<StockroomEntity> stockroomEntities = null;

        try {
            moveStockListDtos = moveStockService.getMoveStockList(newPage, user);
            productTypeEntities = moveStockService.getProductType();
            stockroomEntities = moveStockService.getStockroom();
        } catch (Exception e) {
            log.error("获取移库单一览错误", e);
        }
        // 移库列表
        request.setAttribute("moveStockList", moveStockListDtos);
        // 产品分类
        request.setAttribute("productTypeEntitiesList", productTypeEntities);
        // 库房
        request.setAttribute("stockroomEntitiesList", stockroomEntities);
        // 分页
        request.setAttribute("NewPage", newPage);

        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 根据条件检索
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @author zhangzx
     */

    public ActionForward searchMoveStock(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        log.debug("检索移库单一览");

        // 检索条件放到request中,保存画面检索条件
        DynaActionForm dform = (DynaActionForm) form;

        MoveStockSearchDto moveStockSearchDto = (MoveStockSearchDto) dform
                .get("searchMoveStockDto");

        // 获取用户ID
        UserEntity user = (UserEntity) request.getSession().getAttribute(
                Constants.USERLOGIN);

        // 将当前登录用户的ID和权限放在检索用DTO中
        moveStockSearchDto.setLoginId(user.getId());
        moveStockSearchDto.setRoleId(String.valueOf(user.getRoleId()));

        request.setAttribute("moveStockSearchDto", moveStockSearchDto);

        // 分页
        NewPage newPage = getNewPage(request);
        newPage.setUrl("searchMoveStock.do");
        // 页面检索条件保存
        setNewPagePara(newPage, moveStockSearchDto);

        moveStockService = (IMoveStockService) getBean("moveStockServiceImpl");

        List<MoveStockListDto> moveStockListDtos = null;
        List<ProductTypeEntity> productTypeEntities = null;
        List<StockroomEntity> stockroomEntities = null;

        try {
            moveStockListDtos = moveStockService.getMoveStockListByCondition(newPage,
                    moveStockSearchDto);
            productTypeEntities = moveStockService.getProductType();
            stockroomEntities = moveStockService.getStockroom();
        } catch (Exception e) {
            log.error("获取移库单一览错误", e);
        }
        // 移库列表
        request.setAttribute("moveStockList", moveStockListDtos);
        // 产品分类
        request.setAttribute("productTypeEntitiesList", productTypeEntities);
        // 库房
        request.setAttribute("stockroomEntitiesList", stockroomEntities);
        // 分页
        request.setAttribute("NewPage", newPage);

        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 分页条件设置
     * 
     * @author zzx
     * @param newPage
     * @param moveStockSearchDto
     * @return {@link Void}
     */

    private void setNewPagePara(NewPage newPage, MoveStockSearchDto moveStockSearchDto) {
        // 返厂单号
        if (StringUtils.isNotEmpty(moveStockSearchDto.getId())) {
            newPage.setQuery("searchMoveStockDto.id", moveStockSearchDto.getId());
        }

        // 产品分类编号
        if (StringUtils.isNotEmpty(moveStockSearchDto.getProductTypeId())) {
            newPage.setQuery("searchMoveStockDto.productTypeId",
                    moveStockSearchDto.getProductTypeId());
        }
        // 移出库房名称
        if (StringUtils.isNotEmpty(moveStockSearchDto.getOutStockroomId())) {
            newPage.setQuery("searchMoveStockDto.outStockroomId",
                    moveStockSearchDto.getOutStockroomId());
        }
        // 移入库房名称
        if (StringUtils.isNotEmpty(moveStockSearchDto.getInStockroomId())) {
            newPage.setQuery("searchMoveStockDto.inStockroomId",
                    moveStockSearchDto.getInStockroomId());
        }
        // 货物接收单位名称
        if (StringUtils.isNotEmpty(moveStockSearchDto.getStockroomAddressName())) {
            newPage.setQuery("searchMoveStockDto.stockroomAddressName",
                    moveStockSearchDto.getStockroomAddressName());
        }
        // 货运方式
        if (StringUtils.isNotEmpty(moveStockSearchDto.getTransportWay())) {
            newPage.setQuery("searchMoveStockDto.transportWay",
                    moveStockSearchDto.getTransportWay());
        }
        // 申请起始日期
        if (StringUtils.isNotEmpty(moveStockSearchDto.getDateStart())) {
            newPage.setQuery("searchMoveStockDto.dateStart",
                    moveStockSearchDto.getDateStart());
        }
        // 申请终止日期
        if (StringUtils.isNotEmpty(moveStockSearchDto.getDateEnd())) {
            newPage.setQuery("searchMoveStockDto.dateEnd",
                    moveStockSearchDto.getDateEnd());
        }
        // 要求发货起始日期
        if (StringUtils.isNotEmpty(moveStockSearchDto.getRequestDateStart())) {
            newPage.setQuery("searchMoveStockDto.requestDateStart",
                    moveStockSearchDto.getRequestDateStart());
        }
        // 要求发货终止日期
        if (StringUtils.isNotEmpty(moveStockSearchDto.getRequestDateEnd())) {
            newPage.setQuery("searchMoveStockDto.requestDateEnd",
                    moveStockSearchDto.getRequestDateEnd());
        }
        // 发货起始日期
        if (StringUtils.isNotEmpty(moveStockSearchDto.getSendDateStart())) {
            newPage.setQuery("searchMoveStockDto.sendDateStart",
                    moveStockSearchDto.getSendDateStart());
        }
        // 发货终止日期
        if (StringUtils.isNotEmpty(moveStockSearchDto.getSendDateEnd())) {
            newPage.setQuery("searchMoveStockDto.sendDateEnd",
                    moveStockSearchDto.getSendDateEnd());
        }
        // 入库起始日期
        if (StringUtils.isNotEmpty(moveStockSearchDto.getInAdmDateStart())) {
            newPage.setQuery("searchMoveStockDto.inAdmDateStart",
                    moveStockSearchDto.getInAdmDateStart());
        }
        // 入库终止日期
        if (StringUtils.isNotEmpty(moveStockSearchDto.getInAdmDateEnd())) {
            newPage.setQuery("searchMoveStockDto.inAdmDateEnd",
                    moveStockSearchDto.getInAdmDateEnd());
        }
        // 人员名称
        if (StringUtils.isNotEmpty(moveStockSearchDto.getUserName())) {
            newPage.setQuery("searchMoveStockDto.userName",
                    moveStockSearchDto.getUserName());
        }
        // 返厂单状态
        if (StringUtils.isNotEmpty(moveStockSearchDto.getStatus())) {
            newPage.setQuery("searchMoveStockDto.status", moveStockSearchDto.getStatus());
        }

    }

    /**
     * 发货地址选择(供应商)
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getSendGoodsAddressByStockroomId(ActionMapping mapping,
            ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        log.debug("进入发货地址选择页面");
        String stockroomId = request.getParameter("stockroomId");
        moveStockService = (IMoveStockService) getBean("moveStockServiceImpl");

        // 分页
        NewPage newPage = getNewPage(request);
        newPage.setUrl("getSendGoodsAddressByStockroomId.do");
        newPage.setQuery("stockroomId", stockroomId);

        List<StockroomAddressEntity> stockroomAddressEntities = moveStockService
                .selectSendGoodsAddress(newPage, stockroomId);

        request.setAttribute("stockroomAddressEntities", stockroomAddressEntities);
        request.setAttribute("NewPage", newPage);
        return mapping.findForward(Constants.SUCCESS);

    }

    /**
     * 移库修改显示信息
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @author zhangzx
     */
    public ActionForward getMoveStockModifyInfo(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("进入修改移库页面");

        String moveStockId = request.getParameter("moveStockId");
        if (moveStockId == null) {
            moveStockId = (String) request.getAttribute("moveStockId");
        }
        // 获得spring的bean容器
        moveStockService = (IMoveStockService) getBean("moveStockServiceImpl");

        // 根据id获得移库单信息
        MoveStockAssessDto assessDto = moveStockService.getMoveStockById(moveStockId);

        request.setAttribute("assessDto", assessDto);

        List<ProductTypeEntity> productTypeEntities = null;
        List<StockroomEntity> stockroomEntities = null;

        // 获取用户登陆Id
        UserEntity user = (UserEntity) request.getSession().getAttribute(
                Constants.USERLOGIN);
        /* 产品分类 */
        commonService = (ICommonService) getBean("commonServiceImpl");

        try {
            productTypeEntities = commonService.getAllProductTypes(null, user.getId(),
                    user.getRoleId() + "");
            stockroomEntities = moveStockService.getStockroom();
        } catch (Exception e) {
            log.error("获取移库单修改信息错误", e);
        }

        // 产品列表
        List<MoveOutStockDto> list = moveStockService.getMoveOutStockInfo(assessDto);

        request.setAttribute("list", list);

        request.setAttribute("productTypeEntities", productTypeEntities);

        // 产品分类
        request.setAttribute("productTypeEntitiesList", productTypeEntities);
        // 库房
        request.setAttribute("stockroomEntitiesList", stockroomEntities);

        /* 防止重复提交 */
        saveToken(request);

        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 修改移库单 保存、提交
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @author zhangzx
     */
    public ActionForward modifyMoveStock(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        log.debug("移库单修改");
        ErrorMsgDto errorMsgDto = new ErrorMsgDto();
        String method = request.getParameter("method");

        /* 防止重复提交 */
        if (isTokenValid(request)) {
            resetToken(request);
        } else {
            if ("save".equals(method)) {
                request.setAttribute("msg", "请勿重复保存 !");
            } else {
                request.setAttribute("msg", "请勿重复提交!");
            }
            return mapping.findForward(Constants.SUCCESS);
        }

        // 获得spring的bean容器
        moveStockService = (IMoveStockService) getBean("moveStockServiceImpl");
        commonService = (ICommonService) getBean("commonServiceImpl");
        // 获取用户登陆Id
        UserEntity user = (UserEntity) request.getSession().getAttribute(
                Constants.USERLOGIN);

        DynaActionForm actionForm = (DynaActionForm) form;

        MoveEntity modifyMoveStockDto = (MoveEntity) actionForm.get("modifyMoveStockDto");
        String moveStockId = modifyMoveStockDto.getId();
        request.setAttribute("moveStockId", moveStockId);
        // 采购主管登录名
        WorkReceiverDto workReceiverDto = commonService.getUserIdByCondition(
                modifyMoveStockDto.getProductTypeId(), null, null,
                Constants.ROLE_PROCUREMENT_OFFICER);
        if (workReceiverDto == null) {
            request.setAttribute("err", "获取采购主管登录名错误");
            return mapping.findForward("failure");
        }
        String buyManId = workReceiverDto
                .getUserIdByRoleId(Constants.ROLE_PROCUREMENT_OFFICER);
        modifyMoveStockDto.setBuyManId(buyManId);

        // 移出库房管理员登录名
        workReceiverDto = commonService.getUserIdByCondition(
                modifyMoveStockDto.getProductTypeId(), null,
                modifyMoveStockDto.getOutStockroomId());
        if (workReceiverDto == null) {
            request.setAttribute("err", "获取移出库房管理员错误");
            return mapping.findForward("failure");
        }
        String stkAdmId = workReceiverDto.getUserId();
        modifyMoveStockDto.setStkAdmId(stkAdmId);

        // 移入库房管理员登录名
        workReceiverDto = commonService.getUserIdByCondition(
                modifyMoveStockDto.getProductTypeId(), null,
                modifyMoveStockDto.getInStockroomId());
        if (workReceiverDto == null) {
            request.setAttribute("err", "获取移入库房管理员错误");
            return mapping.findForward("failure");
        }
        String inAdmId = workReceiverDto.getUserId();
        modifyMoveStockDto.setInAdmId(inAdmId);

        /* 移库明细添加 */
        List<MoveDetailEntity> list = new ArrayList<MoveDetailEntity>();
        /* 产品编码 */
        String[] productIds = request.getParameterValues("productIds");
        /* 返厂数 */
        String[] counts = request.getParameterValues("counts");
        /* 时间戳 */
        String[] timeStamps = request.getParameterValues("timeStamps");
        String price = "0.00";
        for (int i = 0; i < productIds.length; i++) {
            MoveDetailEntity moveDetailEntity = new MoveDetailEntity();
            moveDetailEntity.setMoveId(moveStockId);
            moveDetailEntity.setCount(Integer.valueOf(counts[i]));
            moveDetailEntity.setProductId(Integer.valueOf(productIds[i]));
            /* 库存平均单价 */
            price = commonService.getProdAvePrice(productIds[i], "0");
            if (price == null) {
                price = "0";
            }
            moveDetailEntity.setPrice(new BigDecimal(price));

            list.add(moveDetailEntity);
        }

        if ("save".equals(method)) {
            // 修改返厂保存
            modifyMoveStockDto.setStatus(1);

            if (moveStockService.modifyMoveStockSave(modifyMoveStockDto, list)) {
                log.info("User:{},{} 修改移库保存成功", user.getId(), user.getName());
                return mapping.findForward("success");
            } else {
                log.info("User:{},{} 修改移库保存失败", user.getId(), user.getName());
                request.setAttribute("err", "修改失败");
                return mapping.findForward("failure");
            }

        } else if ("submit".equals(method)) {

            /* 产品冻结数 */
            List<StockEntity> stockEntities = new ArrayList<StockEntity>();

            for (int i = 0; i < productIds.length; i++) {

                StockEntity stockEntity = new StockEntity();
                stockEntity.setSendLock(Integer.valueOf(counts[i]));// 本次要增加的冻结数
                stockEntity.setProductId(Integer.valueOf(productIds[i]));
                stockEntity.setStockroomId(modifyMoveStockDto.getOutStockroomId());
                // 后台再次判断移库数是否正确 add 20110106 start
                MoveStockProductDto moveStockProductDto = new MoveStockProductDto();
                moveStockProductDto.setProTypeId(productIds[i]);
                moveStockProductDto.setOutStockroomId(modifyMoveStockDto.getOutStockroomId().toString());
                MoveOutStockDto moveOutStockDto = moveStockService
                        .getStockByStockRoomAndProduct(moveStockProductDto);
                if (moveOutStockDto==null){
                    request.setAttribute("err", "获取产品库存信息失败");
                    return mapping.findForward("failure");
                }
                if (stockEntity.getSendLock() > moveOutStockDto.getUseCount()) {
                    request.setAttribute("err", "移库数量不能大于移库可用数");
                    return mapping.findForward("failure");
                }
                stockEntity.setTimeStamp(moveOutStockDto.getTimeStamp());
                // 后台再次判断移库数是否正确 add 20110106 end
                //stockEntity.setTimeStamp(Timestamp.valueOf(timeStamps[i]));
                stockEntities.add(stockEntity);
            }

            /* 采购专员 */
            if ("8".equals(String.valueOf(user.getRoleId()))) {
                // 状态
                modifyMoveStockDto.setStatus(2);
                /* 提交 给产品总监发待办事务 */
                WorkEntity work = new WorkEntity();
                work.setUserId(modifyMoveStockDto.getBuyManId());
                work.setWorkId(22);// 返厂单待评审
                work.setCount(1);

                if (moveStockService.modifyMoveStockSubmitWork(modifyMoveStockDto, list,
                        work, stockEntities, errorMsgDto)) {
                    log.info("User:{},{} 修改移库提交成功", user.getId(), user.getName());
                    return mapping.findForward("success");
                } else {
                    log.info("User:{},{} 修改移库提交失败", user.getId(), user.getName());
                    request.setAttribute("err", errorMsgDto.getErrmsg());
                    return mapping.findForward("failure");
                }
            } else if ("10".equals(String.valueOf(user.getRoleId()))) {
                /* 产品总监 */
                // 状态
                modifyMoveStockDto.setStatus(4);
                if (moveStockService.modifyMoveStockSubmitNoWork(modifyMoveStockDto,
                        list, stockEntities, errorMsgDto)) {
                    log.info("User:{},{} 修改移库保存成功", user.getId(), user.getName());
                    return mapping.findForward("success");
                } else {
                    log.info("User:{},{} 修改移库保存失败", user.getId(), user.getName());
                    request.setAttribute("err", errorMsgDto.getErrmsg());
                    return mapping.findForward("failure");
                }
            } else {
                request.setAttribute("err", "非法提交");
                return mapping.findForward("failure");
            }
        } else {
            request.setAttribute("err", "非法提交");
            return mapping.findForward("failure");
        }

    }

    /**
     * 新建移库显示
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward createMoveStockInfo(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        moveStockService = (IMoveStockService) getBean("moveStockServiceImpl");

        commonService = (ICommonService) getBean("commonServiceImpl");

        /* 库房不包含虚拟库 */
        List<StockroomEntity> stockroomEntities = moveStockService.getStockroom();
        // 获取用户登陆Id
        UserEntity user = (UserEntity) request.getSession().getAttribute(
                Constants.USERLOGIN);

        request.setAttribute("stockroomEntities", stockroomEntities);

        /* 产品分类 */

        List<ProductTypeEntity> productTypeEntities = commonService.getAllProductTypes(
                null, user.getId(), user.getRoleId() + "");
        request.setAttribute("productTypeEntities", productTypeEntities);

        /* 防止重复提交 */
        saveToken(request);
        return mapping.findForward("success");

    }

    /**
     * 产品选择检索
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public ActionForward getMoveProductSelect(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        log.debug("进入移库产品选择画面");

        // 参数获取
        MoveStockProductDto moveStockProductDto = new MoveStockProductDto();

        String productTypeId = request.getParameter("productTypeId");
        String outStockroomId = request.getParameter("outStockroomId");
        moveStockProductDto.setProTypeId(productTypeId);
        moveStockProductDto.setOutStockroomId(outStockroomId);
        String productTypeName = request.getParameter("productTypeName");

        request.setAttribute("productTypeId", productTypeId);
        request.setAttribute("outStockroomId", outStockroomId);
        request.setAttribute("productTypeName", productTypeName);

        // 根据产品分类获得产品系列
        ISalesContractManagementService salesContractService = (ISalesContractManagementService) this
                .getBean("salesContractManagementServiceImp");
        List<SalesConProductDto> serieList = salesContractService
                .getProSerieByProTypeId(productTypeId);
        ;
        request.setAttribute("serieList", serieList);

        // 分頁
        NewPage newPage = getNewPage(request);
        newPage.setUrl("getMoveProductSelect.do");

        String productName = request.getParameter("productName");
        String serieId = request.getParameter("proSerieId");
        String proCode = request.getParameter("productCode");
        String productType = request.getParameter("productType");

        moveStockProductDto.setProductName(productName);
        moveStockProductDto.setProSerieId(serieId);
        moveStockProductDto.setProductCode(proCode);
        moveStockProductDto.setProductType(productType);

        // 分页条件判断
        if (StringUtils.isNotEmpty(productTypeName)) {
            newPage.setQuery("productTypeName", productTypeName);
        }
        if (StringUtils.isNotEmpty(productName)) {
            newPage.setQuery("productName", productName);
        }
        if (StringUtils.isNotEmpty(productTypeId)) {
            newPage.setQuery("productTypeId", productTypeId);
        }
        if (StringUtils.isNotEmpty(serieId)) {
            newPage.setQuery("proSerieId", serieId);
        }
        if (StringUtils.isNotEmpty(proCode)) {
            newPage.setQuery("productCode", proCode);
        }
        if (StringUtils.isNotEmpty(productType)) {
            newPage.setQuery("productType", productType);
        }
        if (StringUtils.isNotEmpty(outStockroomId)) {
            newPage.setQuery("outStockroomId", outStockroomId);
        }

        // 根据条件查看产品信息
        moveStockService = (IMoveStockService) getBean("moveStockServiceImpl");
        List<MoveOutStockDto> productlist = moveStockService.selectProductList(
                moveStockProductDto, newPage);
        /* 产品放在SESSION中 */
        HttpSession session = request.getSession();
        session.setAttribute("moveStockProductlist", productlist);

        request.setAttribute("productlist", productlist);

        request.setAttribute("NewPage", newPage);

        request.setAttribute("moveStockProductDto", moveStockProductDto);

        return mapping.findForward("success");

    }

    /**
     * 新建移库单
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public ActionForward createMoveStock(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        log.debug("新建移库单");
        ErrorMsgDto errorMsgDto = new ErrorMsgDto();
        /* 重复提交 */
        String subFlag = request.getParameter("subFlag");

        /* 防止重复提交 */
        if (isTokenValid(request)) {
            resetToken(request);
        } else {
            if ("save".equals(subFlag)) {
                request.setAttribute("msg", "请勿重复保存 !");
            } else {
                request.setAttribute("msg", "请勿重复提交!");
            }
            return mapping.findForward("success");
        }

        DynaActionForm actionForm = (DynaActionForm) form;
        MoveStockAssessDto adddto = (MoveStockAssessDto) actionForm
                .get("createMoveStockDto");

        // 获取用户ID
        UserEntity user = (UserEntity) request.getSession().getAttribute(
                Constants.USERLOGIN);
        adddto.setUserId(user.getId());
        adddto.setUserName(user.getName());
        /* 移库单 ID生成 */
        commonService = (ICommonService) getBean("commonServiceImpl");
        moveStockService = (IMoveStockService) getBean("moveStockServiceImpl");
        String moveId = commonService.getSerialNumber("YK", "move");
        adddto.setId(moveId);

        /* 移库明细添加 */
        List<MoveDetailEntity> moveDetailEntities = new ArrayList<MoveDetailEntity>();

        /* 产品编码 */
        String[] productIds = request.getParameterValues("productIds");
        /* 移库数 */
        String[] counts = request.getParameterValues("counts");

        /* 产品冻结数 */
        List<StockEntity> stockEntities = new ArrayList<StockEntity>();

        List<MoveOutStockDto> dtos = (List<MoveOutStockDto>) request.getSession()
                .getAttribute("moveStockProductlist");
        String price = "0";
        for (int i = 0; i < productIds.length; i++) {

            MoveDetailEntity detailEntity = new MoveDetailEntity();
            detailEntity.setMoveId(moveId);
            detailEntity.setCount(Integer.valueOf(counts[i]));
            detailEntity.setProductId(Integer.valueOf(productIds[i]));
            /* 库存平均单价 */
            price = commonService.getProdAvePrice(productIds[i], "0");
            if (price == null) {
                price = "0";
            }
            detailEntity.setPrice(new BigDecimal(price));
            moveDetailEntities.add(detailEntity);

            StockEntity stockEntity = new StockEntity();
            stockEntity.setSendLock(Integer.valueOf(counts[i]));// 本次要增加的冻结数
            stockEntity.setProductId(Integer.valueOf(productIds[i]));
            stockEntity.setStockroomId(adddto.getOutStockroomId());
            // 后台再次判断移库数是否正确 add 20110106 start
            MoveStockProductDto moveStockProductDto = new MoveStockProductDto();
            moveStockProductDto.setProTypeId(productIds[i]);
            moveStockProductDto.setOutStockroomId(adddto.getOutStockroomId().toString());
            MoveOutStockDto moveOutStockDto = moveStockService
                    .getStockByStockRoomAndProduct(moveStockProductDto);
            if (moveOutStockDto==null){
                request.setAttribute("err", "获取产品库存信息失败");
                return mapping.findForward("failure");
            }
            if (stockEntity.getSendLock() > moveOutStockDto.getUseCount()) {
                request.setAttribute("err", "移库数量不能大于移库可用数");
                return mapping.findForward("failure");
            }
            stockEntity.setTimeStamp(moveOutStockDto.getTimeStamp());
            // stockEntity.setTimeStamp(getTimestampById(productIds[i], dtos));
            // 后台再次判断移库数是否正确 add 20110106 end
            stockEntities.add(stockEntity);

        }

        // 采购主管登录名
        WorkReceiverDto workReceiverDto = commonService
                .getUserIdByCondition(adddto.getProductTypeId(), null, null,
                        Constants.ROLE_PROCUREMENT_OFFICER);
        if (workReceiverDto == null) {
            request.setAttribute("err", "获取采购主管错误");
            return mapping.findForward("failure");
        }
        String buyManId = workReceiverDto
                .getUserIdByRoleId(Constants.ROLE_PROCUREMENT_OFFICER);
        adddto.setBuyManId(buyManId);

        // 移出库房管理员登录名
        workReceiverDto = commonService.getUserIdByCondition(adddto.getProductTypeId(),
                null, adddto.getOutStockroomId());
        if (workReceiverDto == null) {
            request.setAttribute("err", "获取移出库房管理员错误");
            return mapping.findForward("failure");
        }
        String stkAdmId = workReceiverDto.getUserId();
        adddto.setStkAdmId(stkAdmId);

        // 移入库房管理员登录名
        workReceiverDto = commonService.getUserIdByCondition(adddto.getProductTypeId(),
                null, adddto.getInStockroomId());
        if (workReceiverDto == null) {
            request.setAttribute("err", "获取移入库房管理员错误");
            return mapping.findForward("failure");
        }
        String inAdmId = workReceiverDto.getUserId();
        adddto.setInAdmId(inAdmId);

        
        if ("save".equals(subFlag)) {
            /* 保存 */
            adddto.setStatus(1);
            if (user.getRoleId().equals(Constants.ROLE_PROCUREMENT_COMMISSIONER)) {
                adddto.setType("1");
            }/** 产品总监 */
            else if (user.getRoleId().equals(Constants.ROLE_PRODUCT_DIRECTOR)) {
                adddto.setType("0");
            }
            if (moveStockService.addMoveStockSave(adddto, moveDetailEntities)) {
                log.info("User:{},{} 新建移库保存成功 moveId: "+moveId, user.getId(), user.getName());
                request.getSession().setAttribute("moveStockProductlist", null);
                return mapping.findForward("success");
            } else {
                log.info("User:{},{}新建移库保存失败", user.getId(), user.getName());
                request.setAttribute("err", "移库单创建失败");
                request.getSession().setAttribute("moveStockProductlist", null);
                return mapping.findForward("failure");
            }

        } else if ("submit".equals(subFlag)) {

            /** 采购专员 */
            WorkEntity work = new WorkEntity();
            if (user.getRoleId().equals(Constants.ROLE_PROCUREMENT_COMMISSIONER)) {
                /* 提交 采购主管发待办事务 */
                adddto.setStatus(2);
                adddto.setType("1");
                work.setUserId(buyManId);
                work.setWorkId(22);// 移库单待评审
                work.setCount(1);

            }/** 产品总监 */
            else if (user.getRoleId().equals(Constants.ROLE_PRODUCT_DIRECTOR)) {

                adddto.setStatus(4);
                adddto.setType("0");
            }

            if (moveStockService.addMoveStockSubmit(adddto, work, moveDetailEntities,
                    stockEntities, errorMsgDto)) {
                log.info("User:{},{} 新建移库提交成功 moveId: "+moveId, user.getId(), user.getName());
                /* 释放session中的产品信息 */
                request.getSession().setAttribute("moveStockProductlist", null);
                return mapping.findForward("success");
            } else {
                log.info("User:{},{}新建移库提交失败", user.getId(), user.getName());
                request.setAttribute("err", errorMsgDto.getErrmsg());
                request.getSession().setAttribute("moveStockProductlist", null);
                return mapping.findForward("failure");
            }
        } else {
            request.setAttribute("err", "非法提交");
            return mapping.findForward("failure");
        }

    }

    /**
     * 从session中获得时间戳
     * 
     * @param id
     * @param list
     * @return
     */

    public Timestamp getTimestampById(String id, List<MoveOutStockDto> list) {

        for (int i = 0; i < list.size(); i++) {
            MoveOutStockDto dto = (MoveOutStockDto) list.get(i);
            if (id.equals(dto.getId())) {
                return dto.getTimeStamp();
            }
        }
        return null;
    }

    /**
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteMoveStock(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("删除移库单");
        String moveStockId = request.getParameter("moveStockId");
        moveStockService = (IMoveStockService) getBean("moveStockServiceImpl");
        // 获取用户ID
        UserEntity user = (UserEntity) request.getSession().getAttribute(
                Constants.USERLOGIN);

        if (moveStockService.deleteMOveStock(moveStockId)) {
            log.info("User:{},{}删除移库单成功", user.getId(), user.getName());
            return mapping.findForward("success");
        } else {
            log.info("User:{},{}}删除移库单交失败", user.getId(), user.getName());
            request.setAttribute("msg", "}删除移库单失败");
            return mapping.findForward("success");
        }

    }
}
