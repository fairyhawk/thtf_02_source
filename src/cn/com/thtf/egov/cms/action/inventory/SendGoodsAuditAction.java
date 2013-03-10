/**
 * ClassName  SendGoodsAuditAction
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-6-7
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.action.inventory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.NewBaseAction;
import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.dto.BuyBackGoodsAssessDto;
import cn.com.thtf.egov.cms.dto.CompanyCustomerSupplierAddressDto;
import cn.com.thtf.egov.cms.dto.CreateBuyBackGoodsInfoDto;
import cn.com.thtf.egov.cms.dto.ModifyBuyBackGoodsInfoDto;
import cn.com.thtf.egov.cms.dto.MoveOutStockDto;
import cn.com.thtf.egov.cms.dto.MoveStockAssessDto;
import cn.com.thtf.egov.cms.dto.ProductTypeInfoDto;
import cn.com.thtf.egov.cms.dto.SampleOutAssessDto;
import cn.com.thtf.egov.cms.dto.SampleOutProductInfoDto;
import cn.com.thtf.egov.cms.dto.SendGoodInfoDto;
import cn.com.thtf.egov.cms.dto.SendGoodsDto;
import cn.com.thtf.egov.cms.dto.SendgoodViewSelectProductDto;
import cn.com.thtf.egov.cms.dto.StockSendGoodsDto;
import cn.com.thtf.egov.cms.entity.BuyBackGoodsEntity;
import cn.com.thtf.egov.cms.entity.MoveEntity;
import cn.com.thtf.egov.cms.entity.SampleOutEntity;
import cn.com.thtf.egov.cms.entity.StockroomEntity;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.form.OrderForm;
import cn.com.thtf.egov.cms.iservice.IProductTypeService;
import cn.com.thtf.egov.cms.iservice.inventory.IStockService;
import cn.com.thtf.egov.cms.iservice.inventory.IStockroomService;
import cn.com.thtf.egov.cms.iservice.purchase.IBuyBackGoodsService;
import cn.com.thtf.egov.cms.iservice.sell.IBackInvoiceService;
import cn.com.thtf.egov.cms.iservice.sell.ISendGoodsService;
import cn.com.thtf.egov.cms.iservice.stock.IMoveStockService;
import cn.com.thtf.egov.cms.iservice.stock.ISampleOutService;
import cn.com.thtf.egov.cms.util.Util;

/**
 * SendGoodsAuditAction
 * 
 * @author Lubo
 */
public class SendGoodsAuditAction extends NewBaseAction {

    /** log */
    private static Logger log = LoggerFactory.getLogger(SendGoodsAuditAction.class);
    /** IStockroomService */
    private IStockroomService stockroomService;
    /** IProductTypeService */
    private IProductTypeService productTypeService;
    /** IStockService */
    private IStockService stockService;
    /** IStockService */
    private ISendGoodsService sendGoodsService;

    /**
     * 发货管理
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public ActionForward orderList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("库存管理");

        OrderForm orderForm = (OrderForm) form;
        StockSendGoodsDto queryPara = orderForm.getQueryPara();

        UserEntity userInfo = getLoginUserInfo(request);
        queryPara.setUser(userInfo);
        queryPara.setRoleId(userInfo.getRoleId());

        /* 检索所有库房 */
        stockroomService = (IStockroomService) getBean("stockroomServiceImp");
        List<StockroomEntity> stockroomList = stockroomService.getAllStockRoom();

        /* 产品分类 */
        productTypeService = (IProductTypeService) getBean("productTypeServiceImp");
        List<ProductTypeInfoDto> productTypeList = productTypeService
                .fingProductTypeAll();

        /* 封装翻页参数 */
        NewPage newPage = getNewPage(request);
        newPage.setUrl("stockOrder.do");
        newPage.setQuery("queryPara.orderId", queryPara.getOrderId());
        newPage.setQuery("queryPara.stockroomId", queryPara.getStockroomId());
        newPage.setQuery("queryPara.productTypeId", queryPara.getProductTypeId());
        newPage.setQuery("queryPara.customerName", queryPara.getCustomerName());
        newPage.setQuery("queryPara.customerAddressName",
                queryPara.getCustomerAddressName());

        newPage.setQuery("queryPara.transportWay", queryPara.getTransportWay());
        newPage.setQuery("queryPara.starttime", queryPara.getStarttime());
        newPage.setQuery("queryPara.endtime", queryPara.getEndtime());
        newPage.setQuery("queryPara.userName", queryPara.getUserName());
        newPage.setQuery("queryPara.changeStatus", queryPara.getChangeStatus());
        newPage.setQuery("queryPara.orderType", queryPara.getOrderType());

        newPage.setQuery("queryPara.init", queryPara.getInit());

        if (StringUtils.isNotBlank(queryPara.getOrderType())) {
            String[] changeStatusArr = queryPara.getOrderType().split(",");
            queryPara.setOrderTypeArr(changeStatusArr);
        }

        stockService = (IStockService) getBean("stockServiceImp");
        List<StockSendGoodsDto> orderList = stockService.getOrderList(queryPara, newPage);

        request.setAttribute("orderList", orderList);
        request.setAttribute("roleId", userInfo.getRoleId());
        request.setAttribute("NewPage", newPage);
        request.setAttribute("queryPara", queryPara);
        request.setAttribute("stockroomList", stockroomList);
        request.setAttribute("productTypeList", productTypeList);
        return mapping.findForward("orderList");
    }

    /**
     * 发货单查看
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getSendGoodsView(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        String sendid = request.getParameter("id");
        if (StringUtils.isEmpty(sendid)) {
            request.setAttribute("errorMsg", "发货单不存在！");
            return mapping.findForward("list");
        }

        log.debug("发货单查看页面,发货单ID:" + sendid);

        // 获取spring的service
        sendGoodsService = (ISendGoodsService) this.getBean("sendGoodsServiceImpl");

        Util util = new Util();
        SendGoodInfoDto sge = sendGoodsService.getSendGoodsView(sendid);
        List<SendgoodViewSelectProductDto> list = sendGoodsService
                .sendGoodsViewProduct(sge);
        Integer[] str = null;
        if (StringUtils.isEmpty(sge.getSendStkAdmIdea())) {
            log.debug("评审意见为空！");
        } else {
            str = util.splitIdea(sge.getSendStkAdmIdea());
        }

        UserEntity userInfo = getLoginUserInfo(request);
        request.setAttribute("roleId", userInfo.getRoleId());

        request.setAttribute("svsplist", list);
        request.setAttribute("ideastr", str);
        request.setAttribute("sge", sge);

        if (StringUtils.isNotBlank(request.getParameter("audit"))) {
            /* 防止重复提交 */
            this.saveToken(request);
            return mapping.findForward("audit");
        } else {
            return mapping.findForward("view");
        }
    }

    /**
     * 发货单评审
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward sendgoodsAudit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("发货单评审");

        /* 防止重复提交 */
        if (isTokenValid(request)) {
            resetToken(request);
        } else {
            log.warn("二次提交错误!");
            request.setAttribute("errorMsg", "二次提交错误！");
            return mapping.findForward("createFail");
        }

        /* 页面参数 */
        String sendGoodsId = request.getParameter("orderId");
        String stkMajIdea = request.getParameter("stkMajIdea");
        String stkMajText = request.getParameter("stkMajText");

        log.debug("sendGoodsId:" + sendGoodsId);
        log.debug("stkMajIdea:" + stkMajIdea);
        log.debug("stkMajText:" + stkMajText);

        stockService = (IStockService) getBean("stockServiceImp");

        UserEntity userInfo = getLoginUserInfo(request);

        SendGoodsDto auditPara = new SendGoodsDto();
        auditPara.setId(sendGoodsId);
        auditPara.setStatus(4);
        auditPara.setStkAdmIdea(stkMajIdea);
        auditPara.setStkAdmText(stkMajText);

        auditPara.setStkAdmId(userInfo.getId());
        auditPara.setStkAdmName(userInfo.getName());
        auditPara.setStkAdmDate(Util.getDate());

        auditPara.setUserName(userInfo.getName());

        log.info("User:{},发货单评审:{}", userInfo.getId(), auditPara.getId());
        log.info("发货单评审:{},评审意见:{}", auditPara.getId(), auditPara.getStkAdmIdea());

        boolean result = stockService.auditSendGoods(auditPara);
        if (!result) {
            log.info("User:{},发货单评审,评审失败{}", userInfo.getId(), auditPara.getId());
            request.setAttribute("errorMsg", "评审失败！");
            return mapping.findForward("createFail");
        } else {
            log.info("User:{},发货单评审,评审失败:{}", userInfo.getId(), auditPara.getId());
            return mapping.findForward("createSuccess");
        }
    }

    /**
     * 返厂单查看
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getStockBuyBackGoodsView(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        String sendid = request.getParameter("id");

        if (StringUtils.isEmpty(sendid)) {
            request.setAttribute("errorMsg", "返厂单不存在！");
            return mapping.findForward("list");
        }

        log.debug("返厂单查看页面,发货单ID:" + sendid);
        // 获得spring的bean容器
        IBackInvoiceService backInvoiceJudgeService = (IBackInvoiceService) this
                .getApplicationContext().getBean("backInvoiceServiceImpl");
        IBuyBackGoodsService buyBackGoodsService = (IBuyBackGoodsService) this
                .getApplicationContext().getBean("buyBackGoodsServiceImpl");
        BuyBackGoodsAssessDto assessDto = buyBackGoodsService.getBuyBackGoodsById(sendid);
        if (assessDto == null) {
            request.setAttribute("errorMsg", "返厂评审显示失败!");
            return mapping.findForward("list");
        }

        Util u = new Util();
        // 产品总监评审意见
        Integer[] proMajIdea = null;
        // 评审意见拆分
        if (StringUtils.isEmpty(assessDto.getProMajIdea())) {
            log.debug("评审意见为空！");
        } else {
            proMajIdea = u.splitIdea(assessDto.getProMajIdea());
        }

        // 产品总监姓名
        String proMajName = backInvoiceJudgeService.selectUserName(assessDto
                .getProMajId());
        // 采购主管姓名
        String buyManName = backInvoiceJudgeService.selectUserName(assessDto
                .getBuyManId());
        // 运营总监姓名
        String opeMajName = backInvoiceJudgeService.selectUserName(assessDto
                .getOpeMajId());

        assessDto.setProMajName(proMajName);
        assessDto.setBuyManName(buyManName);
        assessDto.setOpeMajName(opeMajName);

        request.setAttribute("assessDto", assessDto); // 第一个Dto
        request.setAttribute("ProMajIdea", proMajIdea);

        /* 返厂单信息 */
        ModifyBuyBackGoodsInfoDto modifyBuyBackGoodsInfoDto = buyBackGoodsService
                .getModifyBuyBackGoodsInfo(sendid);

        request.setAttribute("modifyBuyBackGoodsInfoDto", modifyBuyBackGoodsInfoDto); // 第二个Dto

        /* 入库产品信息 */
        List<CreateBuyBackGoodsInfoDto> list = buyBackGoodsService
                .getModifyBuyBackGoodsDetail(modifyBuyBackGoodsInfoDto);

        request.setAttribute("backGoogsCreateInfoList", list); // 第三个Dto

        UserEntity userInfo = getLoginUserInfo(request);
        request.setAttribute("roleId", userInfo.getRoleId());

        request.setAttribute("id", sendid);

        if (StringUtils.isNotBlank(request.getParameter("audit"))) {
            /* 防止重复提交 */
            this.saveToken(request);
            return mapping.findForward("audit");
        } else {
            return mapping.findForward("view");
        }
    }

    /**
     * 返厂单评审
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getStockBuyBackGoodsAudit(ActionMapping mapping,
            ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        log.debug("返场单评审");
        /* 防止重复提交 */
        if (isTokenValid(request)) {
            resetToken(request);
        } else {
            log.warn("二次提交错误!");
            request.setAttribute("errorMsg", "二次提交错误！");
            return mapping.findForward("createFail");
        }

        /* 页面参数 */
        String sendGoodsId = request.getParameter("orderId");
        String stkMajIdea = request.getParameter("stkMajIdea");
        String stkMajText = request.getParameter("stkMajText");

        log.debug("sendGoodsId:" + sendGoodsId);
        log.debug("stkMajIdea:" + stkMajIdea);
        log.debug("stkMajText:" + stkMajText);

        stockService = (IStockService) getBean("stockServiceImp");

        UserEntity userInfo = getLoginUserInfo(request);

        BuyBackGoodsEntity auditPara = new BuyBackGoodsEntity();
        auditPara.setId(sendGoodsId);
        auditPara.setStatus(10);
        auditPara.setStkAdmIdea(stkMajIdea);
        auditPara.setStkAdmText(stkMajText);

        auditPara.setStkAdmId(userInfo.getId());
        auditPara.setStkAdmName(userInfo.getName());
        auditPara.setStkAdmDate(Util.getDate());

        auditPara.setUserName(userInfo.getName());

        log.info("User:{},返场单评审:{}", userInfo.getId(), auditPara.getId());
        log.info("返场单评审:{},评审意见:{}", auditPara.getId(), auditPara.getStkAdmIdea());

        boolean result = stockService.auditBuyBackGoods(auditPara);
        if (!result) {
            log.info("User:{},返场单评审,评审失败{}", userInfo.getId(), auditPara.getId());
            request.setAttribute("errorMsg", "评审失败！");
            return mapping.findForward("createFail");
        } else {
            log.info("User:{},返场单评审,评审失败:{}", userInfo.getId(), auditPara.getId());
            return mapping.findForward("createSuccess");
        }
    }

    /**
     * 移库单查看
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getStockMoveView(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        String sendid = request.getParameter("id");

        if (StringUtils.isEmpty(sendid)) {
            request.setAttribute("errorMsg", "移库单不存在！");
            return mapping.findForward("list");
        }

        // 获得spring的bean容器
        IBackInvoiceService backInvoiceJudgeService = (IBackInvoiceService) this
                .getApplicationContext().getBean("backInvoiceServiceImpl");

        IMoveStockService moveStockService = (IMoveStockService) this
                .getApplicationContext().getBean("moveStockServiceImpl");

        // 根据id获得移库评审信息
        MoveStockAssessDto assessDto = moveStockService.getMoveStockById(sendid.trim());
        if (assessDto == null) {
            request.setAttribute("errorMsg", "移库评审显示失败!");
            return mapping.findForward("list");
        }

        // 采购主管姓名

        String buyManName = backInvoiceJudgeService.selectUserName(assessDto
                .getBuyManId());

        assessDto.setBuyManName(buyManName);

        request.setAttribute("assessDto", assessDto); // 第一个dto

        List<MoveOutStockDto> list = moveStockService.getMoveOutStockInfo(assessDto);

        /* 产品放在SESSION中 */
        HttpSession session = request.getSession();
        session.setAttribute("moveStockProductlist", list);

        request.setAttribute("list", list);

        UserEntity userInfo = getLoginUserInfo(request);
        request.setAttribute("roleId", userInfo.getRoleId());

        request.setAttribute("id", sendid);

        if (StringUtils.isNotBlank(request.getParameter("audit"))) {
            /* 防止重复提交 */
            this.saveToken(request);
            return mapping.findForward("audit");
        } else {
            return mapping.findForward("view");
        }
    }

    /**
     * 移库单评审
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getStockMoveAudit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("移库单评审");
        /* 防止重复提交 */
        if (isTokenValid(request)) {
            resetToken(request);
        } else {
            log.warn("二次提交错误!");
            request.setAttribute("errorMsg", "二次提交错误！");
            return mapping.findForward("createFail");
        }

        /* 页面参数 */
        String sendGoodsId = request.getParameter("orderId");
        String stkMajIdea = request.getParameter("stkMajIdea");
        String stkMajText = request.getParameter("stkMajText");

        log.debug("sendGoodsId:" + sendGoodsId);
        log.debug("stkMajIdea:" + stkMajIdea);
        log.debug("stkMajText:" + stkMajText);

        stockService = (IStockService) getBean("stockServiceImp");

        UserEntity userInfo = getLoginUserInfo(request);

        MoveEntity auditPara = new MoveEntity();
        auditPara.setId(sendGoodsId);
        auditPara.setStatus(6);
        auditPara.setStkAdmIdea(stkMajIdea);
        auditPara.setStkAdmText(stkMajText);

        auditPara.setStkAdmId(userInfo.getId());
        auditPara.setStkAdmName(userInfo.getName());
        auditPara.setStkAdmDate(Util.getDate());

        auditPara.setUserName(userInfo.getName());

        log.info("User:{},移库单评审:{}", userInfo.getId(), auditPara.getId());
        log.info("移库单评审:{},评审意见:{}", auditPara.getId(), auditPara.getStkAdmIdea());

        boolean result = stockService.auditMove(auditPara);
        if (!result) {
            log.info("User:{},移库单评审,评审失败{}", userInfo.getId(), auditPara.getId());
            request.setAttribute("errorMsg", "评审失败！");
            return mapping.findForward("createFail");
        } else {
            log.info("User:{},移库单评审,评审失败:{}", userInfo.getId(), auditPara.getId());
            return mapping.findForward("createSuccess");
        }
    }

    /**
     * 借出单查看
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getStockSampleView(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        String sendid = request.getParameter("id");

        if (StringUtils.isEmpty(sendid)) {
            request.setAttribute("errorMsg", "移库单不存在！");
            return mapping.findForward("list");
        }

        // 获得spring的bean容器
        IBackInvoiceService backInvoiceJudgeService = (IBackInvoiceService) this
                .getApplicationContext().getBean("backInvoiceServiceImpl");

        ISampleOutService sampleOutService = (ISampleOutService) this
                .getApplicationContext().getBean("sampleOutServiceImpl");

        // 根据id获得样品借出评审信息
        SampleOutAssessDto assessDto = sampleOutService.getSampleOutById(sendid.trim());
        if (assessDto == null) {
            request.setAttribute("msg", "样品借出评审显示失败!");
            return mapping.findForward("failure");
        }
        String userId = assessDto.getUserId();
        String tempRoleId = sampleOutService.selectUserRoleId(userId);

        request.setAttribute("tempRoleId", tempRoleId);

        // 采购主管姓名
        String buyManName = backInvoiceJudgeService.selectUserName(assessDto
                .getBuyManId());
        // 销售总监姓名
        String sellMajName = backInvoiceJudgeService.selectUserName(assessDto
                .getSellMajId());

        assessDto.setBuyManName(buyManName);
        assessDto.setSellMajName(sellMajName);
        request.setAttribute("assessDto", assessDto); // 第一个dto

        String companyType = String.valueOf(assessDto.getCompanyType());
        // 获得发货地址
        Map<String, String> map = new HashMap<String, String>();
        map.put("companyType", companyType);
        map.put("addressId", assessDto.getAddressId().toString());

        CompanyCustomerSupplierAddressDto addressDto = sampleOutService
                .getAddressInfo(map);

        request.setAttribute("addressDto", addressDto); // 第二个dto

        List<SampleOutProductInfoDto> list = sampleOutService.getProductInfo(assessDto);

        // 产品放在SESSION中
        HttpSession session = request.getSession();
        session.setAttribute("sampleOutProductlist", list);

        request.setAttribute("list", list);

        /* 防止重复提交 */
        saveToken(request);

        UserEntity userInfo = getLoginUserInfo(request);
        request.setAttribute("roleId", userInfo.getRoleId());

        request.setAttribute("id", sendid);

        if (StringUtils.isNotBlank(request.getParameter("audit"))) {
            /* 防止重复提交 */
            this.saveToken(request);
            return mapping.findForward("audit");
        } else {
            return mapping.findForward("view");
        }
    }

    /**
     * 借出单评审
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getStockSampleAudit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("借出单评审");
        /* 防止重复提交 */
        if (isTokenValid(request)) {
            resetToken(request);
        } else {
            log.warn("二次提交错误!");
            request.setAttribute("errorMsg", "二次提交错误！");
            return mapping.findForward("createFail");
        }

        /* 页面参数 */
        String sendGoodsId = request.getParameter("orderId");
        String stkMajIdea = request.getParameter("stkMajIdea");
        String stkMajText = request.getParameter("stkMajText");

        log.debug("sendGoodsId:" + sendGoodsId);
        log.debug("stkMajIdea:" + stkMajIdea);
        log.debug("stkMajText:" + stkMajText);

        stockService = (IStockService) getBean("stockServiceImp");

        UserEntity userInfo = getLoginUserInfo(request);

        SampleOutEntity auditPara = new SampleOutEntity();
        auditPara.setId(sendGoodsId);
        auditPara.setStatus(8);
        auditPara.setStkAdmIdea(stkMajIdea);
        auditPara.setStkAdmText(stkMajText);

        auditPara.setStkAdmId(userInfo.getId());
        auditPara.setStkAdmName(userInfo.getName());
        auditPara.setStkAdmDate(Util.getDate());

        auditPara.setUserName(userInfo.getName());

        log.info("User:{},借出单评审:{}", userInfo.getId(), auditPara.getId());
        log.info("借出单评审:{},评审意见:{}", auditPara.getId(), auditPara.getStkAdmIdea());

        boolean result = stockService.auditSampleOut(auditPara);
        if (!result) {
            log.info("User:{},借出单评审,评审失败{}", userInfo.getId(), auditPara.getId());
            request.setAttribute("errorMsg", "评审失败！");
            return mapping.findForward("createFail");
        } else {
            log.info("User:{},借出单评审,评审失败:{}", userInfo.getId(), auditPara.getId());
            return mapping.findForward("createSuccess");
        }
    }

    /**
     * 发货打印
     * 
     * @author lxs
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getStockSendGoodsPrint(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        String sendid = request.getParameter("id");

        log.debug("发货单查看页面,发货单ID:" + sendid);

        // 获取spring的service
        sendGoodsService = (ISendGoodsService) this.getBean("sendGoodsServiceImpl");

        Util util = new Util();
        SendGoodInfoDto sge = sendGoodsService.getSendGoodsView(sendid);
        List<SendgoodViewSelectProductDto> list = sendGoodsService
                .sendGoodsViewProduct(sge);
        Integer[] str = null;
        if (StringUtils.isEmpty(sge.getSendStkAdmIdea())) {
            log.debug("评审意见为空！");
        } else {
            str = util.splitIdea(sge.getSendStkAdmIdea());
        }
        UserEntity userInfo = getLoginUserInfo(request);
        request.setAttribute("roleId", userInfo.getRoleId());
        request.setAttribute("svsplist", list);
        request.setAttribute("ideastr", str);
        request.setAttribute("sge", sge);
        return mapping.findForward("sendgoodprint");
    }

    /**
     * 借出单打印
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getStockSamplePrint(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        String sendid = request.getParameter("id");

        // 获得spring的bean容器
        IBackInvoiceService backInvoiceJudgeService = (IBackInvoiceService) this
                .getApplicationContext().getBean("backInvoiceServiceImpl");

        ISampleOutService sampleOutService = (ISampleOutService) this
                .getApplicationContext().getBean("sampleOutServiceImpl");

        // 根据id获得样品借出评审信息
        SampleOutAssessDto assessDto = sampleOutService.getSampleOutById(sendid.trim());
        if (assessDto == null) {
            request.setAttribute("msg", "样品借出评审显示失败!");
            return mapping.findForward("failure");
        }

        String userId = assessDto.getUserId();
        String tempRoleId = sampleOutService.selectUserRoleId(userId);

        request.setAttribute("tempRoleId", tempRoleId);

        // 采购主管姓名
        String buyManName = backInvoiceJudgeService.selectUserName(assessDto
                .getBuyManId());
        // 销售总监姓名
        String sellMajName = backInvoiceJudgeService.selectUserName(assessDto
                .getSellMajId());

        assessDto.setBuyManName(buyManName);
        assessDto.setSellMajName(sellMajName);
        request.setAttribute("assessDto", assessDto); // 第一个dto

        String companyType = String.valueOf(assessDto.getCompanyType());
        // 获得发货地址
        Map<String, String> map = new HashMap<String, String>();
        map.put("companyType", companyType);
        map.put("addressId", assessDto.getAddressId().toString());

        CompanyCustomerSupplierAddressDto addressDto = sampleOutService
                .getAddressInfo(map);

        request.setAttribute("addressDto", addressDto); // 第二个dto

        List<SampleOutProductInfoDto> list = sampleOutService.getProductInfo(assessDto);

        // 产品放在SESSION中
        HttpSession session = request.getSession();
        session.setAttribute("sampleOutProductlist", list);

        request.setAttribute("list", list);

        UserEntity userInfo = getLoginUserInfo(request);
        request.setAttribute("roleId", userInfo.getRoleId());

        return mapping.findForward("stocksampleprint");
    }

    /**
     * 移库单打印
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getStockMovePrint(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        String sendid = request.getParameter("id");

        if (StringUtils.isEmpty(sendid)) {
            request.setAttribute("errorMsg", "移库单不存在！");
            return mapping.findForward("list");
        }

        // 获得spring的bean容器
        IBackInvoiceService backInvoiceJudgeService = (IBackInvoiceService) this
                .getApplicationContext().getBean("backInvoiceServiceImpl");

        IMoveStockService moveStockService = (IMoveStockService) this
                .getApplicationContext().getBean("moveStockServiceImpl");

        // 根据id获得移库评审信息
        MoveStockAssessDto assessDto = moveStockService.getMoveStockById(sendid.trim());
        if (assessDto == null) {
            request.setAttribute("errorMsg", "移库评审显示失败!");
            return mapping.findForward("list");
        }

        // 采购主管姓名

        String buyManName = backInvoiceJudgeService.selectUserName(assessDto
                .getBuyManId());

        assessDto.setBuyManName(buyManName);

        request.setAttribute("assessDto", assessDto); // 第一个dto

        List<MoveOutStockDto> list = moveStockService.getMoveOutStockInfo(assessDto);

        /* 产品放在SESSION中 */
        HttpSession session = request.getSession();
        session.setAttribute("moveStockProductlist", list);

        request.setAttribute("list", list);

        UserEntity userInfo = getLoginUserInfo(request);
        request.setAttribute("roleId", userInfo.getRoleId());

        return mapping.findForward("stockmoveprint");
    }

    /**
     * 返厂单查看
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getStockBuyBackGoodsPrint(ActionMapping mapping,
            ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String sendid = request.getParameter("id");

        if (StringUtils.isEmpty(sendid)) {
            request.setAttribute("errorMsg", "返厂单不存在！");
            return mapping.findForward("list");
        }

        log.debug("返厂单查看页面,发货单ID:" + sendid);
        // 获得spring的bean容器
        IBackInvoiceService backInvoiceJudgeService = (IBackInvoiceService) this
                .getApplicationContext().getBean("backInvoiceServiceImpl");
        IBuyBackGoodsService buyBackGoodsService = (IBuyBackGoodsService) this
                .getApplicationContext().getBean("buyBackGoodsServiceImpl");
        BuyBackGoodsAssessDto assessDto = buyBackGoodsService.getBuyBackGoodsById(sendid);
        if (assessDto == null) {
            request.setAttribute("errorMsg", "返厂评审显示失败!");
            return mapping.findForward("list");
        }

        Util u = new Util();
        // 产品总监评审意见
        Integer[] proMajIdea = null;
        // 评审意见拆分
        if (StringUtils.isEmpty(assessDto.getProMajIdea())) {
            log.debug("评审意见为空！");
        } else {
            proMajIdea = u.splitIdea(assessDto.getProMajIdea());
        }

        // 产品总监姓名
        String proMajName = backInvoiceJudgeService.selectUserName(assessDto
                .getProMajId());
        // 采购主管姓名
        String buyManName = backInvoiceJudgeService.selectUserName(assessDto
                .getBuyManId());
        // 运营总监姓名
        String opeMajName = backInvoiceJudgeService.selectUserName(assessDto
                .getOpeMajId());

        assessDto.setProMajName(proMajName);
        assessDto.setBuyManName(buyManName);
        assessDto.setOpeMajName(opeMajName);

        request.setAttribute("assessDto", assessDto); // 第一个Dto
        request.setAttribute("ProMajIdea", proMajIdea);

        /* 返厂单信息 */
        ModifyBuyBackGoodsInfoDto modifyBuyBackGoodsInfoDto = buyBackGoodsService
                .getModifyBuyBackGoodsInfo(sendid);

        request.setAttribute("modifyBuyBackGoodsInfoDto", modifyBuyBackGoodsInfoDto); // 第二个Dto

        /* 入库产品信息 */
        List<CreateBuyBackGoodsInfoDto> list = buyBackGoodsService
                .getModifyBuyBackGoodsDetail(modifyBuyBackGoodsInfoDto);

        request.setAttribute("backGoogsCreateInfoList", list); // 第三个Dto

        UserEntity userInfo = getLoginUserInfo(request);
        request.setAttribute("roleId", userInfo.getRoleId());

        return mapping.findForward("stockbuybackgoodsprint");
    }
}
