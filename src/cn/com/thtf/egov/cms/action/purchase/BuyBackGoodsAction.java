/**
 * ClassName  BuyBackGoodsAction
 *
 * History
 * Create User: liuqg
 * Create Date: 2010-6-11
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.action.purchase;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.NewBaseAction;
import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.constant.Constants;
import cn.com.thtf.egov.cms.dto.BuyBackGoodsAssessDto;
import cn.com.thtf.egov.cms.dto.BuyBackGoodsListDto;
import cn.com.thtf.egov.cms.dto.BuyBackGoodsSearchDto;
import cn.com.thtf.egov.cms.dto.CreateBuyBackGoodsInfoDto;
import cn.com.thtf.egov.cms.dto.ModifyBuyBackGoodsInfoDto;
import cn.com.thtf.egov.cms.dto.WorkReceiverDto;
import cn.com.thtf.egov.cms.entity.BuyBackGoodsDetailEntity;
import cn.com.thtf.egov.cms.entity.BuyBackGoodsEntity;
import cn.com.thtf.egov.cms.entity.ProductTypeEntity;
import cn.com.thtf.egov.cms.entity.StockroomEntity;
import cn.com.thtf.egov.cms.entity.SupplierAddressEntity;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.entity.WorkEntity;
import cn.com.thtf.egov.cms.iservice.common.ICommonService;
import cn.com.thtf.egov.cms.iservice.purchase.IBuyBackGoodsService;
import cn.com.thtf.egov.cms.util.Util;

/**
 * 采购返厂
 * 
 * @author liuqg
 */

public class BuyBackGoodsAction extends NewBaseAction {

    private static Logger log = LoggerFactory.getLogger(BuyBackGoodsAction.class);
    private IBuyBackGoodsService buyBackGoodsService = null;
    private ICommonService commonService = null;

    /**
     * 显示返厂评审
     * 
     * @author 蒋名星
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward getShowBuyBackAssess(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        log.debug("进入显示返厂评审");
        
        String buyBackGoodsId = (String) (request.getParameter("buyBackGoodsId") == null ? request
				.getAttribute("buyBackGoodsId")
				: request.getParameter("buyBackGoodsId"));

        buyBackGoodsService = (IBuyBackGoodsService) this.getApplicationContext()
                .getBean("buyBackGoodsServiceImpl");

        BuyBackGoodsAssessDto assessDto = buyBackGoodsService
                .getBuyBackGoodsById(buyBackGoodsId.trim());
        if (assessDto == null) {
            request.setAttribute("msg", "返厂评审显示失败!");
            return mapping.findForward("failure");
        }
        
        Util u = new Util();
        // 产品总监评审意见
        Integer[] ProMajIdea = null;
        // 评审意见拆分
        if (StringUtils.isEmpty(assessDto.getProMajIdea())) {
            log.debug("评审意见为空！");
        } else {
        	ProMajIdea = u.splitIdea(assessDto.getProMajIdea());
        }

        request.setAttribute("assessDto", assessDto);      //第一个Dto
        request.setAttribute("ProMajIdea",ProMajIdea);
        
        /* 返厂单信息 */
        ModifyBuyBackGoodsInfoDto modifyBuyBackGoodsInfoDto = buyBackGoodsService
                .getModifyBuyBackGoodsInfo(buyBackGoodsId);
        
        request.setAttribute("modifyBuyBackGoodsInfoDto", modifyBuyBackGoodsInfoDto);   //第二个Dto
        
      
        /* 入库产品信息 */
        List<CreateBuyBackGoodsInfoDto> list = buyBackGoodsService
                .getModifyBuyBackGoodsDetail(modifyBuyBackGoodsInfoDto);
       
        request.setAttribute("backGoogsCreateInfoList", list);            //第三个Dto
        
        request.getAttribute("buyBackGoodsAssessDto");
        
        /* 防止重复提交 */
        saveToken(request);

        /* 获得区分信息 view 跳转到查看页面  ，showAssess 跳转到显示评审页面   */
        String type = request.getParameter("type");
        if("showAssess".equals(type)){
        	return mapping.findForward("showAssess");
        }else if("view".equals(type)){
        	return mapping.findForward("view");
        }else{
        	return mapping.findForward("failure");
        }
    }

    /**
     * 返厂评审
     * 
     * @author 蒋名星
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward getBuyBackAssess(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        log.debug("正在评审");
        // 防止重复提交 
        if (isTokenValid(request)) {
            resetToken(request);
        } else {
            request.setAttribute("msg", "请勿重复评审!");
            request.setAttribute("buyBackGoodsId",request.getParameter("buyBackAssess.id"));
            return mapping.findForward("failureToList");
        }

        // 获得用户id
        UserEntity user = (UserEntity) request.getSession().getAttribute(
                Constants.USERLOGIN);

        String assessResult = request.getParameter("assessResult");
        
        String buyBackGoodsId = request.getParameter("buyBackAssess.id");
		request.setAttribute("buyBackGoodsId",buyBackGoodsId);

        DynaActionForm dform = (DynaActionForm) form;
        BuyBackGoodsAssessDto buyBackGoodsAssessDto = (BuyBackGoodsAssessDto) dform
                .get("buyBackAssess");
        
        StringBuffer sb = new StringBuffer();
        sb.append(buyBackGoodsAssessDto.getProMajIdea());
        sb.append(buyBackGoodsAssessDto.getProMajIdea1());
        buyBackGoodsAssessDto.setProMajIdea(sb.toString());

        buyBackGoodsService = (IBuyBackGoodsService) this.getApplicationContext()
                .getBean("buyBackGoodsServiceImpl");
        
        
        String userName = user.getName();
            
        int status = 0;
        boolean isSuccess = false;

        String roleId = String.valueOf(user.getRoleId());
        buyBackGoodsAssessDto.setRoleId(roleId);
        if ("10".equals(roleId)) {
            // 产品总监意见
            //防止重复评审
            commonService = (ICommonService) getBean("commonServiceImpl");
            
            String statusBefore = commonService.getStatusById("buy_back_goods", buyBackGoodsAssessDto.getId());
            
            if("3".equals(statusBefore)||"4".equals(statusBefore)){
                request.setAttribute("msg", "请勿重复评审");
                return mapping.findForward("failureToList");
            }
            
            if (StringUtils.equals("pass", assessResult)) {                
                status = 4;
            } else {                
                status = 3;
            }

            buyBackGoodsAssessDto.setStatus(status);
            buyBackGoodsAssessDto.setProMajName(userName);

            // 如果同意 修改状态并且给采购主管发待办事务
            if (status == 4) {
                // 给采购主管发待办事务
                WorkEntity work = new WorkEntity();
                work.setCount(1);
                work.setUserId(buyBackGoodsAssessDto.getBuyManId());
                work.setWorkId(21);

                // 把产品总监的待办事务减一
                WorkEntity workself = new WorkEntity();
                workself.setCount(-1);
                workself.setUserId(user.getId());
                workself.setWorkId(21);
                isSuccess = buyBackGoodsService.updateBuyBackGoodsAssess(
                        buyBackGoodsAssessDto, work, workself);

                if (isSuccess) {
                    log.info("User:{},{}产品总监返厂评审通过成功", user.getId(), user.getName());
                } else {
                    log.info("User:{},{}产品总监返厂评审通过失败", user.getId(), user.getName());
                }

            } else {
                WorkEntity workself = new WorkEntity();
                workself.setCount(-1);
                workself.setUserId(user.getId());
                workself.setWorkId(21);
                // 不同意只修改状态
                isSuccess = buyBackGoodsService.updateAssessNoWork(buyBackGoodsAssessDto,
                        workself);
                if (isSuccess) {
                    log.info("User:{},{} 产品总监返厂评审未通过成功", user.getId(), user.getName());
                } else {
                    log.info("User:{},{} 产品总监返厂评审未通过失败", user.getId(), user.getName());
                }
            }
        } else if ("11".equals(roleId)) {

            // 采购主管意见
            //防止重复评审
            commonService = (ICommonService) getBean("commonServiceImpl");
            
            String statusBefore = commonService.getStatusById("buy_back_goods", buyBackGoodsAssessDto.getId());
            
            if("5".equals(statusBefore)||"6".equals(statusBefore)){
                request.setAttribute("msg", "请勿重复评审");
                return mapping.findForward("failureToList");
            }
            if (StringUtils.equals("pass", assessResult)) {
                status = 6;
            } else {
                status = 5;
            }

            buyBackGoodsAssessDto.setStatus(status);
            buyBackGoodsAssessDto.setBuyManName(userName);

            // 如果同意 修改状态并且给运营总监发待办事务
            if (status == 6) {
                // 给运营总监发待办事务
                WorkEntity work = new WorkEntity();
                work.setCount(1);
                work.setUserId(buyBackGoodsAssessDto.getOpeMajId());
                work.setWorkId(21);

                // 把采购主管的待办事务减一
                WorkEntity workself = new WorkEntity();
                workself.setCount(-1);
                workself.setUserId(user.getId());
                workself.setWorkId(21);

                isSuccess = buyBackGoodsService.updateBuyBackGoodsAssess(
                        buyBackGoodsAssessDto, work, workself);
                if (isSuccess) {
                    log.info("User:{},{} 采购主管返厂评审通过成功", user.getId(), user.getName());
                } else {
                    log.info("User:{},{} 采购主管返厂评审通过失败", user.getId(), user.getName());
                }

            } else {
                WorkEntity workself = new WorkEntity();
                workself.setCount(-1);
                workself.setUserId(user.getId());
                workself.setWorkId(21);
                // 不同意只修改状态
                isSuccess = buyBackGoodsService.updateAssessNoWork(buyBackGoodsAssessDto,
                        workself);
                if (isSuccess) {
                    log.info("User:{},{} 采购主管返厂评审未通过成功", user.getId(), user.getName());
                } else {
                    log.info("User:{},{} 采购主管返厂评审未通过失败", user.getId(), user.getName());

                }
            }

        } else if ("17".equals(roleId)) {

            //运营总监意见
            //防止重复评审
            commonService = (ICommonService) getBean("commonServiceImpl");
            
            String statusBefore = commonService.getStatusById("buy_back_goods", buyBackGoodsAssessDto.getId());
            
            if("7".equals(statusBefore)||"8".equals(statusBefore)){
                request.setAttribute("msg", "请勿重复评审");
                return mapping.findForward("failureToList");
            }
            
            String logmsg = "";
            if (StringUtils.equals("pass", assessResult)) {
                status = 8;
                logmsg = "通过";
            } else{
                status = 7;
                logmsg = "未通过";
            }

            buyBackGoodsAssessDto.setStatus(status);
            buyBackGoodsAssessDto.setOpeMajName(userName);

            // 如果同意 修改状态并且给库房管理员发待办事务
            if (status == 8) {

                // 把运营总监的待办事务减一
                WorkEntity workself = new WorkEntity();
                workself.setCount(-1);
                workself.setUserId(user.getId());
                workself.setWorkId(21);

                isSuccess = buyBackGoodsService.updateAssessNoWork(
                        buyBackGoodsAssessDto,workself);
                if (isSuccess) {
                    log.info("User:{},{} 运营总监返厂评审通过" + logmsg + "成功", user.getId(), user
                            .getName());
                } else {
                    log.info("User:{},{} 运营总监返厂评审通过" + logmsg + "失败", user.getId(), user
                            .getName());
                }

            } else {
                WorkEntity workself = new WorkEntity();
                workself.setCount(-1);
                workself.setUserId(user.getId());
                workself.setWorkId(21);
                // 不同意只修改状态
                isSuccess = buyBackGoodsService.updateAssessNoWork(buyBackGoodsAssessDto,
                        workself);
                if (isSuccess) {
                    log.info("User:{},{} 运营总监返厂评审未通过" + logmsg + "成功", user.getId(), user
                            .getName());
                } else {
                    log.info("User:{},{} 运营总监返厂评审未通过" + logmsg + "失败", user.getId(), user
                            .getName());

                }
            }

        }
        request.setAttribute("buyBackGoodsAssessDto", buyBackGoodsAssessDto);
        if (!isSuccess) {
            request.setAttribute("msg", "评审失败");
            return mapping.findForward("failure");
        }
        return mapping.findForward("success");

    }


    /**
     * 返厂管理列表初始化
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @author zhangzx
     */
    public ActionForward getBuyBackGoodsList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("进入返厂管理初始列表");

        buyBackGoodsService = (IBuyBackGoodsService) getBean("buyBackGoodsServiceImpl");

        // 获取用户ID
        UserEntity user = (UserEntity) request.getSession().getAttribute(
                Constants.USERLOGIN);
        // 分页
        NewPage newPage = getNewPage(request);
        newPage.setUrl("getBuyBackGoodsList.do");

        List<BuyBackGoodsListDto> buyBackGoodsListDtos = null;
        List<ProductTypeEntity> productEntities = null;
        List<StockroomEntity> stockroomEntities = null;

        try {
            buyBackGoodsListDtos = buyBackGoodsService.getBuyBackGoodsList(newPage, user);
            productEntities = buyBackGoodsService.getProductType();
            stockroomEntities = buyBackGoodsService.getStockroom();
        } catch (Exception e) {
            log.error("获取返厂单一览错误", e);
        }
        // 将取得的退票list放在request中
        request.setAttribute("buyBackGoodsList", buyBackGoodsListDtos);
        // 产品分类
        request.setAttribute("productEntitiesList", productEntities);
        // 库房
        request.setAttribute("stockroomEntitiesList", stockroomEntities);
        // 分页
        request.setAttribute("NewPage", newPage);

        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 根据查询条件检索
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @author zhangzx
     */
    public ActionForward searchBuyBackGoods(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("检索返厂单一览");

        // 检索条件放到request中,保存画面检索条件
        DynaActionForm dform = (DynaActionForm) form;

        BuyBackGoodsSearchDto buyBackGoodsSearchDto = (BuyBackGoodsSearchDto) dform
                .get("searchBuyBackGoods");

        // 获取用户ID
        UserEntity user = (UserEntity) request.getSession().getAttribute(
                Constants.USERLOGIN);
        // 将当前登录用户的ID和权限放在检索用DTO中
        buyBackGoodsSearchDto.setLoginId(user.getId());
        buyBackGoodsSearchDto.setRoleId(String.valueOf(user.getRoleId()));

        request.setAttribute("buyBackGoodsSearchDto", buyBackGoodsSearchDto);

        // 分页
        NewPage newPage = getNewPage(request);
        newPage.setUrl("searchBuyBackGoods.do");

        // 页面检索条件保存
        setNewPagePara(newPage, buyBackGoodsSearchDto);
        // 实现类
        buyBackGoodsService = (IBuyBackGoodsService) getBean("buyBackGoodsServiceImpl");

        List<BuyBackGoodsListDto> buyBackGoodsListDtos = null;
        List<ProductTypeEntity> productEntities = null;
        List<StockroomEntity> stockroomEntities = null;

        // 获取数据
        try {
            buyBackGoodsListDtos = buyBackGoodsService.getBuyBackGoodsListByCondition(
                    newPage, buyBackGoodsSearchDto);
            productEntities = buyBackGoodsService.getProductType();
            stockroomEntities = buyBackGoodsService.getStockroom();
        } catch (Exception e) {
            request.setAttribute("msg", "检索错误!");
            log.error("检索返厂一览信息错误：", e);
        }
        // 将取得的list放在request中
        request.setAttribute("buyBackGoodsList", buyBackGoodsListDtos);
        // 产品分类
        request.setAttribute("productEntitiesList", productEntities);
        // 库房
        request.setAttribute("stockroomEntitiesList", stockroomEntities);
        request.setAttribute("NewPage", newPage);

        return mapping.findForward("success");
    }

    /**
     * 分页条件设置
     * 
     * @author zzx
     * @param newPage
     * @param sellBackSearchDto
     * @return {@link Void}
     */

    private void setNewPagePara(NewPage newPage,
            BuyBackGoodsSearchDto buyBackGoodsSearchDto) {
        // 返厂单号
        if (StringUtils.isNotEmpty(buyBackGoodsSearchDto.getId())) {
            newPage.setQuery("searchBuyBackGoods.id", buyBackGoodsSearchDto.getId());
        }
        // 库房编号
        if (StringUtils.isNotEmpty(buyBackGoodsSearchDto.getStockroomId())) {
            newPage.setQuery("searchBuyBackGoods.stockroomId", buyBackGoodsSearchDto
                    .getStockroomId());
        }
        // 产品分类编号
        if (StringUtils.isNotEmpty(buyBackGoodsSearchDto.getProductTypeId())) {
            newPage.setQuery("searchBuyBackGoods.productTypeId", buyBackGoodsSearchDto
                    .getProductTypeId());
        }
        // 产品合同号
        if (StringUtils.isNotEmpty(buyBackGoodsSearchDto.getProductContractCode())) {
            newPage.setQuery("searchBuyBackGoods.productContractCode",
                    buyBackGoodsSearchDto.getProductContractCode());
        }
        // 公司合同号
        if (StringUtils.isNotEmpty(buyBackGoodsSearchDto.getCompanyContractCode())) {
            newPage.setQuery("searchBuyBackGoods.companyContractCode",
                    buyBackGoodsSearchDto.getCompanyContractCode());
        }
        // 供货商名称
        if (StringUtils.isNotEmpty(buyBackGoodsSearchDto.getSupplierName())) {
            newPage.setQuery("searchBuyBackGoods.supplierName", buyBackGoodsSearchDto
                    .getSupplierName());
        }
        // 人员名称
        if (StringUtils.isNotEmpty(buyBackGoodsSearchDto.getUserName())) {
            newPage.setQuery("searchBuyBackGoods.userName", buyBackGoodsSearchDto
                    .getUserName());
        }
        // 返厂单状态
        if (StringUtils.isNotEmpty(buyBackGoodsSearchDto.getStatus())) {
            newPage.setQuery("searchBuyBackGoods.status", buyBackGoodsSearchDto
                    .getStatus());
        }

    }

    /**
     * 修改采购返厂显示信息
     * 
     * @author zhangzx
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getBuyBackGoodsModify(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("采购返厂修改显示信息");
        String buyBackGoodsId = (String) (request.getParameter("buyBackGoodsId") !=null?request.getParameter("buyBackGoodsId"):request.getAttribute("buyBackGoodsId"));
        buyBackGoodsService = (IBuyBackGoodsService) getBean("buyBackGoodsServiceImpl");
        /* 返厂单信息 */
        ModifyBuyBackGoodsInfoDto modifyBuyBackGoodsInfoDto = buyBackGoodsService
                .getModifyBuyBackGoodsInfo(buyBackGoodsId);

        /* 库房 */
        List<StockroomEntity> stockroomEntities = buyBackGoodsService.selectStockroomEntity();
        request.setAttribute("stockroomEntities", stockroomEntities);
        /* 入库产品信息 */
        List<CreateBuyBackGoodsInfoDto> createBuyBackGoodsInfoDtoList = buyBackGoodsService
                .getModifyBuyBackGoodsDetail(modifyBuyBackGoodsInfoDto);
        request.setAttribute("backGoogsCreateInfoList", createBuyBackGoodsInfoDtoList);       
        /* 评审信息 */
        // 产品总监
        if (StringUtils.isEmpty(modifyBuyBackGoodsInfoDto.getProMajIdea())) {
            modifyBuyBackGoodsInfoDto.setProMajFlag("-1");// -1代表没有意见
        } else {
            String proMajIdea = modifyBuyBackGoodsInfoDto.getProMajIdea();
            modifyBuyBackGoodsInfoDto.setProMajIdea1(proMajIdea.substring(0, 1));
            modifyBuyBackGoodsInfoDto.setProMajIdea2(proMajIdea.substring(1, 2));
        }     
        
        /* 防止重复提交 */
        saveToken(request);
        
        request.setAttribute("modifyBuyBackGoodsInfoDto", modifyBuyBackGoodsInfoDto);

        return mapping.findForward(Constants.SUCCESS);

    }    

    /**
     * 修改返厂单 保存、提交
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @author zhangzx
     */
    public ActionForward modifyBuyBackGoods(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("采购返厂修改");

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

        buyBackGoodsService = (IBuyBackGoodsService) getBean("buyBackGoodsServiceImpl");

        UserEntity user = (UserEntity) request.getSession().getAttribute(
                Constants.USERLOGIN);

        DynaActionForm actionForm = (DynaActionForm) form;

        BuyBackGoodsEntity modifyBuyBackGoodsDto = (BuyBackGoodsEntity) actionForm
                .get("modifyBuyBackGoodsDto");

        String buyBackGoodsId = modifyBuyBackGoodsDto.getId();
        commonService = (ICommonService) getBean("commonServiceImpl");
     // 产品总监
        WorkReceiverDto workReceiverDto = commonService.getUserIdByCondition(
        		modifyBuyBackGoodsDto.getProductTypeId(), null, null,
                Constants.ROLE_PRODUCT_DIRECTOR);
        String proMajId = workReceiverDto
                .getUserIdByRoleId(Constants.ROLE_PRODUCT_DIRECTOR);
        modifyBuyBackGoodsDto.setProMajId(proMajId);
        // 采购主管登录名
        workReceiverDto = commonService.getUserIdByCondition(modifyBuyBackGoodsDto
                .getProductTypeId(), null, null, Constants.ROLE_PROCUREMENT_OFFICER);
        String buyManId = workReceiverDto
                .getUserIdByRoleId(Constants.ROLE_PROCUREMENT_OFFICER);
        modifyBuyBackGoodsDto.setBuyManId(buyManId);
        // 运营总监登录名
        workReceiverDto = commonService.getUserIdByCondition(null, null, null,
                Constants.ROLE_DIRECTOR_OF_OPERATIONS);
        String opeMajId = workReceiverDto
                .getUserIdByRoleId(Constants.ROLE_DIRECTOR_OF_OPERATIONS);
        modifyBuyBackGoodsDto.setOpeMajId(opeMajId);
        // 库房管理员登录名
        workReceiverDto = commonService.getUserIdByCondition(modifyBuyBackGoodsDto
                .getProductTypeId(), null, modifyBuyBackGoodsDto.getStockroomId()
                );
        String stkAdmId = workReceiverDto.getUserId();
        modifyBuyBackGoodsDto.setStkAdmId(stkAdmId);
        
        /* 返厂明细添加 */
        List<BuyBackGoodsDetailEntity> list = new ArrayList<BuyBackGoodsDetailEntity>();
        /* 产品编码 */
        String[] productIds = request.getParameterValues("productIds");
        /* 返厂数 */
        String[] counts = request.getParameterValues("counts");
        /* 单价 */
        String[] prices = request.getParameterValues("prices");
        
        BigDecimal totalMoney = new BigDecimal("0");

        for (int i = 0; i < productIds.length; i++) {
            BuyBackGoodsDetailEntity backGoodsDetailEntity = new BuyBackGoodsDetailEntity();
            backGoodsDetailEntity.setBuyBackGoodsId(buyBackGoodsId);
            backGoodsDetailEntity.setCount(Integer.valueOf(counts[i]));
            backGoodsDetailEntity.setProductId(Integer.valueOf(productIds[i]));
            /* 返厂金额（合计） */
            totalMoney = totalMoney.add(new BigDecimal(counts[i]).multiply(new BigDecimal(prices[i])));
            list.add(backGoodsDetailEntity);
        }
        
        modifyBuyBackGoodsDto.setMoney(totalMoney);

        if ("save".equals(method)) {
            // 修改返厂保存
            modifyBuyBackGoodsDto.setStatus(1);

            if (buyBackGoodsService.modifyBuyBackGoodsSave(modifyBuyBackGoodsDto, list)) {
                log.info("User:{},{} 修改返厂保存成功", user.getId(), user.getName());
                return mapping.findForward("success");
            } else {
                log.info("User:{},{} 修改返厂保存失败", user.getId(), user.getName());
                request.setAttribute("msg", "修改失败");
                request.setAttribute("buyBackGoodsId", buyBackGoodsId);
                return mapping.findForward("failure");
            }

        } else if ("submit".equals(method)) {
            // 状态
            modifyBuyBackGoodsDto.setStatus(2);
            /* 提交 给产品总监发待办事务 */
            WorkEntity work = new WorkEntity();
            work.setUserId(modifyBuyBackGoodsDto.getProMajId());
            work.setWorkId(21);// 返厂单待评审
            work.setCount(1);

            if (buyBackGoodsService.modifyBuyBackGoodsSubmit(modifyBuyBackGoodsDto, list,
                    work)) {
                log.info("User:{},{} 修改返厂提交成功", user.getId(), user.getName());
                return mapping.findForward("success");
            } else {
                log.info("User:{},{} 修改返厂提交失败", user.getId(), user.getName());
                request.setAttribute("msg", "提交失败");
                request.setAttribute("buyBackGoodsId", buyBackGoodsId);
                return mapping.findForward("failure");
            }

        } else {
            request.setAttribute("msg", "非法提交");
            request.setAttribute("buyBackGoodsId", buyBackGoodsId);
            return mapping.findForward("failure");
        }
    }

    /**
     * 新建采购返厂显示信息
     * 
     * @author liuqg
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getBuyBackGoogsCreate(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("新建采购返厂显示信息");
        String id = request.getParameter("id");
        buyBackGoodsService = (IBuyBackGoodsService) getBean("buyBackGoodsServiceImpl");
        /* 入库信息 */
        CreateBuyBackGoodsInfoDto backGoodsInfo = buyBackGoodsService
                .selectInStockInfo(id);
        request.setAttribute("backGoodsInfoDto", backGoodsInfo);
        /* 库房 */
        List<StockroomEntity> stockroomEntities = buyBackGoodsService
                .selectStockroomEntity();
        request.setAttribute("stockroomEntities", stockroomEntities);

        /*
         * 入库产品信息 List<CreateBuyBackGoodsInfoDto> list = buyBackGoodsService
         * .selectCreateBuyBackGoodsInfo(id);
         * 
         * request.setAttribute("backGoogsCreateInfoList", list);
         */
        /* 防止重复提交 */
        saveToken(request);
        return mapping.findForward("success");

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
    public ActionForward getSendAddressSelect(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("进入发货地址选择页面");
        String supplierId = request.getParameter("supplierId");
        buyBackGoodsService = (IBuyBackGoodsService) getBean("buyBackGoodsServiceImpl");

        // 分页
        NewPage newPage = getNewPage(request);
        newPage.setUrl("getSendAddressSelect.do");
        newPage.setQuery("supplierId", supplierId);

        List<SupplierAddressEntity> addressEntities = buyBackGoodsService
                .selecSupplierAddress(supplierId, newPage);

        request.setAttribute("addressEntities", addressEntities);
        request.setAttribute("NewPage", newPage);
        return mapping.findForward("success");

    }

    /**
     * 新建返厂
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addBuyBackGoogs(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        log.debug("新建采购返厂");
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

        commonService = (ICommonService) getBean("commonServiceImpl");
        DynaActionForm actionForm = (DynaActionForm) form;
        BuyBackGoodsEntity backGoodsEntity = (BuyBackGoodsEntity) actionForm
                .get("addBuyBackGoogsDto");
        /* 返厂ID生成 */
        String id = commonService.getSerialNumber("TRK", "buy_back_goods");
        backGoodsEntity.setId(id);
        // 获取用户ID
        UserEntity user = (UserEntity) request.getSession().getAttribute(
                Constants.USERLOGIN);
        backGoodsEntity.setUserId(user.getId());
        backGoodsEntity.setUserName(user.getName());
        
        /* 返厂明细添加 */
        List<BuyBackGoodsDetailEntity> list = new ArrayList<BuyBackGoodsDetailEntity>();
        /* 产品编码 */
        String[] productIds = request.getParameterValues("productIds");
        /* 返厂数 */
        String[] counts = request.getParameterValues("counts");
        /* 单价 */
        String[] prices = request.getParameterValues("prices");
        
        BigDecimal totalMoney = new BigDecimal("0");
        for (int i = 0; i < productIds.length; i++) {
            BuyBackGoodsDetailEntity backGoodsDetailEntity = new BuyBackGoodsDetailEntity();
            backGoodsDetailEntity.setBuyBackGoodsId(id);
            backGoodsDetailEntity.setCount(Integer.valueOf(counts[i]));
            backGoodsDetailEntity.setProductId(Integer.valueOf(productIds[i]));
            list.add(backGoodsDetailEntity);
            /* 返厂金额（合计） */
            totalMoney = totalMoney.add(new BigDecimal(counts[i]).multiply(new BigDecimal(prices[i])));
        }
        backGoodsEntity.setMoney(totalMoney);
        // 产品总监
        WorkReceiverDto workReceiverDto = commonService.getUserIdByCondition(
                backGoodsEntity.getProductTypeId(), null, null,
                Constants.ROLE_PRODUCT_DIRECTOR);
        String proMajId = workReceiverDto
                .getUserIdByRoleId(Constants.ROLE_PRODUCT_DIRECTOR);
        backGoodsEntity.setProMajId(proMajId);
        // 采购主管登录名
        workReceiverDto = commonService.getUserIdByCondition(backGoodsEntity
                .getProductTypeId(), null, null, Constants.ROLE_PROCUREMENT_OFFICER);
        String buyManId = workReceiverDto
                .getUserIdByRoleId(Constants.ROLE_PROCUREMENT_OFFICER);
        backGoodsEntity.setBuyManId(buyManId);
        // 运营总监登录名
        workReceiverDto = commonService.getUserIdByCondition(null, null, null,
                Constants.ROLE_DIRECTOR_OF_OPERATIONS);
        String opeMajId = workReceiverDto
                .getUserIdByRoleId(Constants.ROLE_DIRECTOR_OF_OPERATIONS);
        backGoodsEntity.setOpeMajId(opeMajId);
        // 库房管理员登录名
        workReceiverDto = commonService.getUserIdByCondition(backGoodsEntity
                .getProductTypeId(), null, backGoodsEntity.getStockroomId()
                );
        String stkAdmId = workReceiverDto.getUserId();
        backGoodsEntity.setStkAdmId(stkAdmId);
        if ("save".equals(subFlag)) {
            /* 保存 */
            backGoodsEntity.setStatus(1);
            if (buyBackGoodsService.addBugBackGoodSave(backGoodsEntity, list)) {
                log.info("User:{},{} 新建返厂保存成功", user.getId(), user.getName());
                return mapping.findForward("success");
            } else {
                log.info("User:{},{}新建返厂保存失败", user.getId(), user.getName());
                request.setAttribute("msg", "保存失败");
                return mapping.findForward("failure");
            }

        } else if ("submit".equals(subFlag)) {
            /* 提交 给产品总监发待办事务 */
            backGoodsEntity.setStatus(2);
            WorkEntity work = new WorkEntity();
            work.setUserId(proMajId);
            work.setWorkId(21);// 返厂单待评审

            work.setCount(1);

            if (buyBackGoodsService.addBugBackGoodSubmit(backGoodsEntity, work, list)) {
                log.info("User:{},{} 新建返厂提交成功", user.getId(), user.getName());
                return mapping.findForward("success");
            } else {
                log.info("User:{},{}新建返厂提交失败", user.getId(), user.getName());
                request.setAttribute("msg", "提交失败");
                return mapping.findForward("failure");
            }

        } else {
            request.setAttribute("msg", "非法提交");
            return mapping.findForward("failure");
        }

    }

    /**
     * 入库明细选择小画面显示
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getStockroomSelect(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        log.debug("入库明细选择");
        BuyBackGoodsEntity backGoodsEntity = new BuyBackGoodsEntity();

        String inStockId = request.getParameter("inStockId");
        backGoodsEntity.setInStockId(inStockId);
        String stockroomId = request.getParameter("stockroomId");
        buyBackGoodsService = (IBuyBackGoodsService) getBean("buyBackGoodsServiceImpl");

        NewPage newPage = getNewPage(request);
        newPage.setUrl("getStockroomSelect.do");
        newPage.setQuery("inStockId", inStockId);
        newPage.setQuery("stockroomId", stockroomId);
        backGoodsEntity.setStockroomId(Integer.valueOf(stockroomId));
        /* 入库产品信息 */
        List<CreateBuyBackGoodsInfoDto> list = buyBackGoodsService
                .selectCreateBuyBackGoodsInfo(backGoodsEntity,newPage);
        request.setAttribute("backGoogsCreateInfoList", list);
        request.setAttribute("NewPage", newPage);
        return mapping.findForward("success");
    }
    
    /**
     * 删除返厂
     * @author liuqg
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteBuyBackGoods(ActionMapping mapping, ActionForm form,
    		HttpServletRequest request, HttpServletResponse response)
    		throws Exception {
    	log.debug("删除返厂");
    	String id = request.getParameter("buyBackGoodsId");
    	// 获取用户ID
        UserEntity user = (UserEntity) request.getSession().getAttribute(
                Constants.USERLOGIN);
        
    	buyBackGoodsService = (IBuyBackGoodsService) getBean("buyBackGoodsServiceImpl");
    	if (buyBackGoodsService.deleteBuyBackGoods(id)){
    		  log.info("User:{},{} 返厂删除成功", user.getId(), user.getName());
              return mapping.findForward("success");
    	}else{
    		  log.info("User:{},{} 返厂删除失败", user.getId(), user.getName());
              return mapping.findForward("success");
    	}
    	 
    }
}
