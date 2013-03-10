package cn.com.thtf.egov.cms.action.sell;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.NewBaseAction;
import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.constant.Constants;
import cn.com.thtf.egov.cms.dto.NewReturnsProductDto;
import cn.com.thtf.egov.cms.dto.SaleReturnGoodsViewSelfDto;
import cn.com.thtf.egov.cms.dto.StockroomAndAddressDto;
import cn.com.thtf.egov.cms.entity.SellBackGoodsEntity;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.form.ReturnGoodsListForm;
import cn.com.thtf.egov.cms.form.SaleReturnsAddressSelectForm;
import cn.com.thtf.egov.cms.iservice.common.ICommonService;
import cn.com.thtf.egov.cms.iservice.inventory.IStockroomService;
import cn.com.thtf.egov.cms.iservice.sell.ISaleReturnsService;
import cn.com.thtf.egov.cms.iservice.sell.ISendGoodsService;
import cn.com.thtf.egov.cms.util.Container;
import cn.com.thtf.egov.cms.util.Util;

/**
 * 销售退货管理
 * 
 * @author LuPing
 */
public class SaleReturnsAction extends NewBaseAction {
    /* log */
    private static Logger log = LoggerFactory.getLogger(SaleReturnsAction.class);
    /* ISaleReturnsService */
    private ISaleReturnsService saleReturnsService;
    /* ISendGoodsService */
    private ISendGoodsService sendGoodsService;
    /* ICommonService */
    private ICommonService commonService;

    /**
     * 销售退货列表Action
     * 
     * @author LuPing
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public ActionForward getSaleReturnsList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("退货列表页");
        /* 获取form */
        ReturnGoodsListForm returnGoodsForm = (ReturnGoodsListForm) form;
        /* 获取初始化条件 */
        String init = request.getParameter("returnGoodsQuery.init");
        returnGoodsForm.getReturnGoodsQuery().setInit(init);
        /* 翻页 */
        NewPage newPage = getNewPage(request);
        /* 翻页url */
        newPage.setUrl("saleReturns.do");
        /* 翻页传参 */
        pageParam(newPage, returnGoodsForm);
        /* 获取bean容器 */
        saleReturnsService = (ISaleReturnsService) Container
                .getBean("saleReturnsServiceImpl");
        sendGoodsService = (ISendGoodsService) Container.getBean("sendGoodsServiceImpl");
        IStockroomService stockroomService = (IStockroomService) Container
                .getBean("stockroomServiceImp");
        /* 获取当前登陆用户 */
        UserEntity user = (UserEntity) request.getSession().getAttribute(
                Constants.USERLOGIN);
        returnGoodsForm.getReturnGoodsQuery().setNowUserId(user.getId());
        returnGoodsForm.getReturnGoodsQuery().setRoleId(user.getRoleId());
        returnGoodsForm.getReturnGoodsQuery().setNowUserAreaId(user.getUserAreaId());
        /* 获取所有退货单 */
        List list = saleReturnsService.getReturnGoodsList(newPage,
                returnGoodsForm.getReturnGoodsQuery());
        /* 获取所有库房 */
        List stockList = stockroomService.getAllStockRoom();
        /* 获取所有产品 */
        List productList = sendGoodsService.getAllProductType();

        request.setAttribute("roleId", user.getRoleId());
        request.setAttribute("returnGoodsListForm", returnGoodsForm);
        request.setAttribute("productList", productList);
        request.setAttribute("stockList", stockList);
        request.setAttribute("NewPage", newPage);
        request.setAttribute("list", list);
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 新建退货单初始化
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addSaleReturnsInit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("新建退货单初始化");
        String sendid = request.getParameter("id");
        /* 获取BEAN */
        saleReturnsService = (ISaleReturnsService) this.getBean("saleReturnsServiceImpl");
        IStockroomService stockroomService = (IStockroomService) Container
                .getBean("stockroomServiceImp");
        /* 查询产品分类名称和客户名称 * */
        SaleReturnGoodsViewSelfDto srgvs = saleReturnsService
                .getReturnGoodsProductAndCustomer(sendid);
        /* 查询退货单库房名称 * */
        List<StockroomAndAddressDto> stockRoomList = stockroomService
                .quertConditionStockRoom1(srgvs.getStockroomId());
        request.setAttribute("sendGoodsId", sendid);
        request.setAttribute("srgvs", srgvs);
        request.setAttribute("stockRoomList", stockRoomList);
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 新建退货单
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addSaleReturns(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserEntity userInfo = getLoginUserInfo(request);
        log.info("User:{},新建退货单", userInfo.getId());
        /* 创建form * */
        SaleReturnsAddressSelectForm srasf = (SaleReturnsAddressSelectForm) form;
        Integer[] thcount = srasf.getThcount();
        Integer productId[] = srasf.getProductId();
        SellBackGoodsEntity sbge = new SellBackGoodsEntity();
        commonService = (ICommonService) getBean("commonServiceImpl");
        String id = commonService.getSerialNumber("TFH", "SELL_BACK_GOODS");
        // 退货单ID
        sbge.setId(id);
        // 发货单ID
        sbge.setSendGoodsId(srasf.getSendGoodsId());
        // 产品分类ID
        sbge.setProductTypeId(Integer.parseInt(srasf.getProductTypeId()));
        // 客户ID
        sbge.setCustomerId(Integer.parseInt(srasf.getCustomeId()));
        // 客户NAME
        sbge.setCustomerName(srasf.getCustomeName());

        if (srasf.getPd() == 0) {
            log.debug("保存退货单!");
            // 申请日期
            sbge.setDate("");
            // 退货单状态
            sbge.setStatus(1);
        } else {
            log.debug("新建退货单!");
            // 申请日期
            sbge.setDate(Util.getDate());
            // 退货单状态
            sbge.setStatus(2);
        }
        // 库房编号
        sbge.setStockroomId(Integer.parseInt(srasf.getStockroomId()));
        // 库房收货地址编号
        sbge.setStockroomAddressId(Integer.parseInt(srasf.getStockRoomAddressId()));
        // 特殊说明
        sbge.setText(srasf.getTextarea());
        /* 获取当前登陆用户 */
        UserEntity user = getLoginUserInfo(request);
        // 用户ID
        sbge.setUserId(user.getId());
        // 用户名
        sbge.setUserName(user.getName());
        // 用户区域ID
        sbge.setUserAreaId(user.getUserAreaId());
        // 新建日期
        sbge.setDateTime(Util.getDate());

        /* 获取BEAN * */
        saleReturnsService = (ISaleReturnsService) this.getBean("saleReturnsServiceImpl");
        boolean result = saleReturnsService.addReturnsGoods(sbge, thcount, productId);
        response.getWriter().print(result);
        if (result == false) {
            log.info("User:{},新建退货单 失败", userInfo.getId());
        } else {
            log.info("User:{},新建退货单 成功", userInfo.getId());
        }
        return null;
    }

    /**
     * 销售退货单查看
     * 
     * @author LuPing
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward viewSaleReturns(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("退货单查看");
        String returnGoodsId = request.getParameter("returnGoodsId");
        String sendGoodsId = request.getParameter("sendGoodsId");
        log.debug("退货单号：" + returnGoodsId);
        /* 获取bean容器 */
        saleReturnsService = (ISaleReturnsService) this.getBean("saleReturnsServiceImpl");
        /* 获取产品信息 */
        // List list = saleReturnsService.newSaleReturnsInit(sendGoodsId,
        // returnGoodsId);
        List<NewReturnsProductDto> list = saleReturnsService.modifySaleReturnsProduct(
                sendGoodsId, returnGoodsId);

        request.setAttribute("list", list);
        request.setAttribute("returnGoods",
                saleReturnsService.getReturnGoodsById(returnGoodsId));
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 销售退货评审初始化
     * 
     * @author LuPing
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward auditSaleReturnsInit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("退货单评审初始化");
        String returnGoodsId = request.getParameter("returnGoodsId");
        String sendGoodsId = request.getParameter("sendGoodsId");
        log.debug("退货单号：" + returnGoodsId);
        /* 获取bean容器 */
        saleReturnsService = (ISaleReturnsService) this.getBean("saleReturnsServiceImpl");
        /* 获取产品信息 */
        // List<NewReturnsProductDto> list =
        // saleReturnsService.newSaleReturnsInit(
        // sendGoodsId, returnGoodsId);
        List<NewReturnsProductDto> list = saleReturnsService.modifySaleReturnsProduct(
                sendGoodsId, returnGoodsId);
        /* 获取当前登陆用户 */
        UserEntity user = (UserEntity) request.getSession().getAttribute(
                Constants.USERLOGIN);

        request.setAttribute("roleId", user.getRoleId());
        request.setAttribute("list", list);
        request.setAttribute("returnGoods",
                saleReturnsService.getReturnGoodsById(returnGoodsId));
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 退货单评审
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward auditSaleReturns(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("退货单评审");
        // 获取前台点击评审按钮
        String clickResult = request.getParameter("auditResult");
        /* 评审意见结果 */
        Boolean auditResult = false;
        Boolean result = false;
        log.debug(clickResult);
        /* 获取当前登陆用户 */
        UserEntity user = (UserEntity) request.getSession().getAttribute(
                Constants.USERLOGIN);
        /* 创建销售退货单 */
        SellBackGoodsEntity sellBackGoodsE = new SellBackGoodsEntity();
        sellBackGoodsE.setId(request.getParameter("id"));
        log.info("User:{},退货单评审:{}", user.getId(), sellBackGoodsE.getId());
        /* 获取bean容器 */
        saleReturnsService = (ISaleReturnsService) this.getBean("saleReturnsServiceImpl");

        // 退货单状态的判断
        ICommonService commonService = (ICommonService) getBean("commonServiceImpl");
        String status = commonService.getStatusById("sell_back_goods",
                sellBackGoodsE.getId());
        if ((StringUtils.equals("2", status) && user.getRoleId() == 5)
                || (StringUtils.equals("4", status) && user.getRoleId() == 17)) {

            /* 判断权限 */
            if (user.getRoleId() == Constants.ROLE_SALES_DIRECTOR) {
                sellBackGoodsE.setSellMajDate(Util.getDate());
                if (StringUtils.isNotEmpty(request.getParameter("sellMajIdea0"))) {
                    sellBackGoodsE.setSellMajIdea(request.getParameter("sellMajIdea0"));
                }
                if (StringUtils.isNotEmpty(request.getParameter("sellMajIdea1"))) {
                    sellBackGoodsE.setSellMajIdea(request.getParameter("sellMajIdea1"));
                }
                /* 判断评审结果 */
                if (StringUtils.equals(sellBackGoodsE.getSellMajIdea(), "1")) {
                    auditResult = true;
                } else if (StringUtils.equals(sellBackGoodsE.getSellMajIdea(), "0")) {
                    auditResult = false;
                }
                sellBackGoodsE.setSellMajId(user.getId());
                sellBackGoodsE.setSellMajName(user.getName());
                sellBackGoodsE.setSellMajText(request.getParameter("sellMajText"));
            } else if (user.getRoleId() == Constants.ROLE_DIRECTOR_OF_OPERATIONS) {
                sellBackGoodsE.setOpeMajDate(Util.getDate());
                if (StringUtils.isNotEmpty(request.getParameter("opeMajIdea0"))) {
                    sellBackGoodsE.setSellMajIdea(request.getParameter("opeMajIdea0"));
                }
                if (StringUtils.isNotEmpty(request.getParameter("opeMajIdea1"))) {
                    sellBackGoodsE.setOpeMajIdea(request.getParameter("opeMajIdea1"));
                }
                /* 判断评审结果 */
                if (StringUtils.equals(sellBackGoodsE.getOpeMajIdea(), "1")) {
                    auditResult = true;
                } else if (StringUtils.equals(sellBackGoodsE.getOpeMajIdea(), "0")) {
                    auditResult = false;
                }
                sellBackGoodsE.setOpeMajId(user.getId());
                sellBackGoodsE.setOpeMajName(user.getName());
                sellBackGoodsE.setOpeMajText(request.getParameter("opeMajText"));
            }
            /* 判断评审结果 */
            if (StringUtils.equals(clickResult, "1") && auditResult == true) {
                /* 根据权限和评审结果添加状态 */
                if (user.getRoleId() == Constants.ROLE_SALES_DIRECTOR) {
                    sellBackGoodsE.setStatus(4);
                } else if (user.getRoleId() == Constants.ROLE_DIRECTOR_OF_OPERATIONS) {
                    sellBackGoodsE.setStatus(6);
                }
            } else if (StringUtils.equals(clickResult, "1") && auditResult != true) {
                response.getWriter().print("validate");
                return null;
            } else {
                /* 根据权限和评审结果添加状态 */
                if (user.getRoleId() == 5) {
                    sellBackGoodsE.setStatus(3);
                } else if (user.getRoleId() == 17) {
                    sellBackGoodsE.setStatus(5);
                }
            }
            result = saleReturnsService.modifySaleReturnGoodsAudit(sellBackGoodsE, user);
            if (result == false) {
                log.info("User:{},退货单评审 失败", user.getId());
            } else {
                log.info("User:{},退货单评审 成功", user.getId());
            }
            response.getWriter().print(result);
        } else {
            log.info("User:{},退货单评审 失败{}", user.getId(), status);
            response.getWriter().print(false);
        }

        return null;
    }

    /**
     * 抽象翻页传参
     * 
     * @author LuPing
     * @param newPage
     * @param r
     */
    private void pageParam(NewPage newPage, ReturnGoodsListForm r) {
        newPage.setQuery("returnGoodsQuery.id", r.getReturnGoodsQuery().getId());
        newPage.setQuery("returnGoodsQuery.sendGoodsId", r.getReturnGoodsQuery()
                .getSendGoodsId());
        newPage.setQuery("returnGoodsQuery.stockRoomId", r.getReturnGoodsQuery()
                .getStockRoomId());
        newPage.setQuery("returnGoodsQuery.productContractCode", r.getReturnGoodsQuery()
                .getProductContractCode());
        newPage.setQuery("returnGoodsQuery.companyContarctCode", r.getReturnGoodsQuery()
                .getCompanyContarctCode());
        newPage.setQuery("returnGoodsQuery.productTypeId", r.getReturnGoodsQuery()
                .getProductTypeId());
        newPage.setQuery("returnGoodsQuery.customerName", r.getReturnGoodsQuery()
                .getCustomerName());
        newPage.setQuery("returnGoodsQuery.userName", r.getReturnGoodsQuery()
                .getUserName());
        newPage.setQuery("returnGoodsQuery.startDate", r.getReturnGoodsQuery()
                .getStartDate());
        newPage.setQuery("returnGoodsQuery.endDate", r.getReturnGoodsQuery().getEndDate());
        newPage.setQuery("returnGoodsQuery.startSBCDate", r.getReturnGoodsQuery()
                .getStartSBCDate());
        newPage.setQuery("returnGoodsQuery.endSBCDate", r.getReturnGoodsQuery()
                .getEndSBCDate());
        newPage.setQuery("returnGoodsQuery.status", r.getReturnGoodsQuery().getStatus());
        newPage.setQuery("returnGoodsQuery.init", r.getReturnGoodsQuery().getInit());
    }

    /**
     * 退货单收货地址选择
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getReceiveAddressSelect(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("收货地址选择");
        // SaleReturnsAddressSelectForm addressForm =
        // (SaleReturnsAddressSelectForm) form;
        String sookRoomId = request.getParameter("stockroomId");
        IStockroomService stockroomService = (IStockroomService) Container
                .getBean("stockroomServiceImp");
        // /* 翻页 */
        // NewPage newPage = getNewPage(request);
        // /* 翻页url */
        // newPage.setUrl("getAddressSelect.do");
        // List list = saleReturnsService.selectAddressSelect(newPage);

        saleReturnsService = (ISaleReturnsService) Container
                .getBean("saleReturnsServiceImpl");
        /* 查询当前库房的收货地址 * */
        List<StockroomAndAddressDto> stockRoomList = stockroomService
                .quertConditionStockRoom(sookRoomId);
        String stockRoomName = stockRoomList.get(0).getStockRoomName();
        request.setAttribute("stockRoomName", stockRoomName);
        request.setAttribute("stockRoomList", stockRoomList);
        // request.setAttribute("NewPage", newPage);
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 退货单修改初始化
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward modifyReturnGoodsSaleInit(ActionMapping mapping,
            ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        log.debug("销售退货单修改初始化");
        String returnGoodsId = request.getParameter("returnGoodsId");
        String sendGoodsId = request.getParameter("sendGoodsId");
        log.debug("退货单号：" + returnGoodsId);
        /* 获取bean容器 */
        saleReturnsService = (ISaleReturnsService) this.getBean("saleReturnsServiceImpl");
        SaleReturnGoodsViewSelfDto returnGoods = saleReturnsService
                .getReturnGoodsById(returnGoodsId);
        /* 获取产品信息 */
        // List list = saleReturnsService.newSaleReturnsInit(sendGoodsId,
        // returnGoodsId);
        List<NewReturnsProductDto> list = saleReturnsService.modifySaleReturnsProduct(
                sendGoodsId, returnGoodsId);
        /* 查询退货单库房名称 */
        IStockroomService stockroomService = (IStockroomService) Container
                .getBean("stockroomServiceImp");
        /* 查询退货单库房名称 * */
        List<StockroomAndAddressDto> stockRoomList = stockroomService
                .quertConditionStockRoom1(returnGoods.getStockroomId());
        request.setAttribute("stockroomList", stockRoomList);
        request.setAttribute("list", list);
        request.setAttribute("sendGoodsId", sendGoodsId);
        request.setAttribute("returnGoods", returnGoods);
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 退货单修改
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward modifyReturnsGoods(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("退货单修改action");
        /* 创建form * */
        SaleReturnsAddressSelectForm srasf = (SaleReturnsAddressSelectForm) form;
        UserEntity userInfo = getLoginUserInfo(request);
        log.info("User:{},退货单修改:{}", userInfo.getId(), srasf.getBackId());
        /* 防止重复提交 */
        this.saveToken(request);
        Integer[] thcount = srasf.getThcount();
        Integer productId[] = srasf.getProductId();
        for (Integer i : productId) {
            log.debug("产品编号" + i);
        }
        SellBackGoodsEntity sbge = new SellBackGoodsEntity();
        // 退货单ID
        sbge.setId(srasf.getBackId());
        // 发货单ID
        sbge.setSendGoodsId(srasf.getSendGoodsId());
        // 客户ID
        sbge.setCustomerId(Integer.parseInt(srasf.getCustomeId()));
        // 客户NAME
        sbge.setCustomerName(srasf.getCustomeName());
        // 产品分类
        sbge.setProductTypeId(Integer.parseInt(srasf.getProductTypeId()));
        log.debug("产品分类ID：" + sbge.getProductTypeId());
        if (srasf.getPd() == 0) {
            log.debug("保存退货单!");
            // 申请日期
            sbge.setDate("");
            // 退货单状态
            sbge.setStatus(1);
        } else {
            log.debug("修改退货单!");
            // 申请日期
            sbge.setDate(Util.getDate());
            // 退货单状态
            sbge.setStatus(2);
        }
        log.debug("库房编号:" + srasf.getStockroomId() + "库房收货地址"
                + srasf.getStockRoomAddressId());
        // 库房编号
        sbge.setStockroomId(Integer.parseInt(srasf.getStockroomId()));
        // 库房收货地址编号
        sbge.setStockroomAddressId(Integer.parseInt(srasf.getStockRoomAddressId()));
        // 特殊说明
        sbge.setText(srasf.getTextarea());

        /* 获取BEAN * */
        saleReturnsService = (ISaleReturnsService) this.getBean("saleReturnsServiceImpl");
        boolean result = saleReturnsService.modifyReturnsGoods(sbge, thcount, productId);
        response.getWriter().print(result);
        if (result == false) {
            log.info("User:{},退货单修改 失败", userInfo.getId());
        } else {
            log.info("User:{},退货单修改 成功", userInfo.getId());
        }
        return null;
    }

    /**
     * 退货单删除
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteReturnGoodsSale(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("returnGoodsId");
        UserEntity userInfo = getLoginUserInfo(request);
        log.info("User:{},退货单删除:{}", userInfo.getId(), id);
        /* 获取bean容器 */
        saleReturnsService = (ISaleReturnsService) this.getBean("saleReturnsServiceImpl");
        boolean result = saleReturnsService.deleteReturnGoodsSale(id);
        if (result == false) {
            log.info("User:{},退货单删除 失败", userInfo.getId());
            request.setAttribute("err", "删除退货单失败！");
        }
        log.info("User:{},退货单删除 成功", userInfo.getId());
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * @author lxs
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getReturnGoodsProductSelect(ActionMapping mapping,
            ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        log.debug("新建退货单发货明细选择");
        String sendGoodsId = request.getParameter("id");
        log.debug("发货单ID：" + sendGoodsId);
        /* 翻页 */
        NewPage newPage = getNewPage(request);
        /* 翻页url */
        newPage.setUrl("getReturnGoodsProductSelect.do");
        /* 翻页传参 */
        newPage.setQuery("id", sendGoodsId);
        saleReturnsService = (ISaleReturnsService) this.getBean("saleReturnsServiceImpl");
        List<NewReturnsProductDto> list = saleReturnsService.newSaleReturnsInit(newPage,
                sendGoodsId, null);
        request.setAttribute("list", list);
        request.setAttribute("NewPage", newPage);
        return mapping.findForward(Constants.SUCCESS);
    }
}