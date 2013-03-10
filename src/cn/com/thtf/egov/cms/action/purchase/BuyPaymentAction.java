package cn.com.thtf.egov.cms.action.purchase;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import cn.com.thtf.egov.cms.dto.BuyPaymentAddDto;
import cn.com.thtf.egov.cms.dto.BuyPaymentBuyContractDto;
import cn.com.thtf.egov.cms.dto.BuyPaymentInfoDto;
import cn.com.thtf.egov.cms.entity.PaymentEntity;
import cn.com.thtf.egov.cms.entity.ProductTypeEntity;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.form.BuyPaymentListQueryForm;
import cn.com.thtf.egov.cms.form.BuyPaymentSelectBuyContractForm;
import cn.com.thtf.egov.cms.form.BuyPaymentSelectInStockDetailForm;
import cn.com.thtf.egov.cms.iservice.common.ICommonService;
import cn.com.thtf.egov.cms.iservice.purchase.IBuyPaymentService;
import cn.com.thtf.egov.cms.util.Container;
import cn.com.thtf.egov.cms.util.JsonUtil;
import cn.com.thtf.egov.cms.util.Util;

/**
 * 采购付款
 * 
 * @author lxs
 */
public class BuyPaymentAction extends NewBaseAction {

    /* 采购付款Serviec */
    private IBuyPaymentService buyPaymentService;
    /* Log */
    private static Logger log = LoggerFactory.getLogger(BuyPaymentAction.class);
    /** ICommonService */
    private ICommonService commonService;

    /**
     * 采购付款列表检索
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
    public ActionForward getBuyPaymentList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("付款列表页!");
        BuyPaymentListQueryForm buyPaymentListQueryForm = (BuyPaymentListQueryForm) form;
        /* 翻页 */
        NewPage page = getNewPage(request);
        /* 翻页url */
        page.setUrl("buyPayment.do");
        /* 翻页传参 */
        this.pageParam(page, buyPaymentListQueryForm);
        /* 获取当前登陆用户 */
        UserEntity user = getLoginUserInfo(request);
        buyPaymentListQueryForm.getBuyPaymentListQueryDto().setNowUserId(user.getId());
        buyPaymentListQueryForm.getBuyPaymentListQueryDto().setNowUserRole(
                user.getRoleId());
        /* 获取Bean容器 */
        buyPaymentService = (IBuyPaymentService) getBean("buyPaymentServiceImpl");
        /* 获取付款单 */
        List list = buyPaymentService.getBuyPaymentList(page,
                buyPaymentListQueryForm.getBuyPaymentListQueryDto());
        /* 产品分类 */
        List<ProductTypeEntity> producttypelist = buyPaymentService.getAllProductType();
        request.setAttribute("NewPage", page);
        request.setAttribute("buyPaymentListQueryForm", buyPaymentListQueryForm);
        request.setAttribute("producttypelist", producttypelist);
        request.setAttribute("list", list);
        request.setAttribute("userRoleId", user.getRoleId());
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 付款单创建初始化
     * 
     * @author luping
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public ActionForward addBuyPaymentInit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("付款创建初始化!");
        /* 获取当前登陆用户 */
        UserEntity user = getLoginUserInfo(request);
        /* 获取Bean容器 */
        buyPaymentService = (IBuyPaymentService) getBean("buyPaymentServiceImpl");
        List<ProductTypeEntity> producttypelist = buyPaymentService
                .getBuyPaymentProductTypeByUserId(user.getId());

        request.setAttribute("producttypelist", producttypelist);
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 付款单创建
     * 
     * @author luping
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addBuyPayment(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取当前登陆用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},付款单创建:{}", user.getId(), request.getParameter("id"));
        StringBuffer backResult = null;
        // 获取前台封装数据
        BuyPaymentAddDto buyP = new BuyPaymentAddDto(request);
        /* 生成付款单ID */
        commonService = (ICommonService) getBean("commonServiceImpl");
        String paymentId = commonService.getSerialNumber("FK", "payment");
        // 封装数据
        buyP.setUserId(user.getId());
        buyP.setUserName(user.getName());
        buyP.setPaymentId(paymentId);
        buyP.setDate(Util.getDate());
        // 判断保存或新增
        if (StringUtils.equals(buyP.getBtnClick(), "0")) {
            buyP.setStatus("1");
            backResult = new StringBuffer("0,");
        } else if (StringUtils.equals(buyP.getBtnClick(), "1")) {
            buyP.setStatus("2");
            backResult = new StringBuffer("1,");
        }
        /* 获取Bean容器 */
        buyPaymentService = (IBuyPaymentService) getBean("buyPaymentServiceImpl");
        Boolean result = false;
        // 判断外单不用做验证
        if (StringUtils.equals(buyP.getContractType(), "0")) {
            // 验证合同金额
            result = buyPaymentService.validatePaymentMoney(buyP);
        } else {
            result = true;
        }
        if (result) {// 如果成功执行新增
            result = buyPaymentService.addBuyPayment(buyP);
        } else {// 如果失败通知前台
            backResult = new StringBuffer("2,");
        }
        backResult.append(result);
        log.debug("\n返回数据：" + backResult);
        response.getWriter().print(backResult);
        if (result == false) {
            log.info("User:{},付款单创建 失败", user.getId());
        } else {
            log.info("User:{},付款单创建  成功", user.getId());
        }
        return null;
    }

    /**
     * 付款修改初始化
     * 
     * @author LuPing
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public ActionForward modifyBuyPaymentInit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("付款修改初始化!");
        String id = request.getParameter("id");
        /* 获取当前登陆用户 */
        UserEntity user = getLoginUserInfo(request);
        /* 获取Bean容器 */
        buyPaymentService = (IBuyPaymentService) getBean("buyPaymentServiceImpl");
        List<ProductTypeEntity> producttypelist = buyPaymentService
                .getBuyPaymentProductTypeByUserId(user.getId());
        request.setAttribute("producttypelist", producttypelist);
        // 供货商信息、付款信息及评审信息
        BuyPaymentInfoDto bpid = buyPaymentService.viewBuyPayment(id);
        // 采购合同信息
        List<BuyPaymentBuyContractDto> list = buyPaymentService
                .getBuyPaymentBuyContract(id);
        // 产品信息
        List<BuyPaymentBuyContractDto> productList = buyPaymentService
                .getBuyPaymentBuyProduct(id);
        Util u = new Util();
        // 产品总监评审意见
        Integer[] ProMajIder = null;
        // 评审意见拆分
        if (StringUtils.isEmpty(bpid.getProMajIder())) {
            log.debug("评审意见为空！");
        } else {
            ProMajIder = u.splitIdea(bpid.getProMajIder());
        }
        request.setAttribute("producttypelist", producttypelist);
        request.setAttribute("userRoleId", user.getRoleId());
        request.setAttribute("ProMajIder", ProMajIder);
        request.setAttribute("bpid", bpid);
        request.setAttribute("id", id);
        request.setAttribute("productList", productList);
        request.setAttribute("BuyContractList", list);
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 付款单修改
     * 
     * @author luping
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward modifyBuyPayment(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取当前登陆用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},付款单修改:{}", user.getId(), request.getParameter("id"));
        StringBuffer backResult = null;
        // 获取前台封装数据
        BuyPaymentAddDto buyP = new BuyPaymentAddDto(request);

        // 封装数据
        buyP.setUserId(user.getId());
        buyP.setUserName(user.getName());
        buyP.setDate(Util.getDate());
        // 判断保存或新增
        if (StringUtils.equals(buyP.getBtnClick(), "0")) {
            buyP.setStatus("1");
            backResult = new StringBuffer("0,");
        } else if (StringUtils.equals(buyP.getBtnClick(), "1")) {
            buyP.setStatus("2");
            backResult = new StringBuffer("1,");
        }
        /* 获取Bean容器 */
        buyPaymentService = (IBuyPaymentService) getBean("buyPaymentServiceImpl");
        Boolean result = false;
        // 判断外单不用做验证
        if (StringUtils.equals(buyP.getContractType(), "0")) {
            result = buyPaymentService.validatePaymentMoney(buyP);
        } else {
            result = true;
        }
        // 验证合同金额
        if (result) {// 如果成功执行修改
            result = buyPaymentService.modifyBuyPayment(buyP);
        } else {// 如果失败通知前台
            backResult = new StringBuffer("2,");
        }
        backResult.append(result);
        log.debug("\n返回数据：" + backResult);
        response.getWriter().print(backResult);
        if (result == false) {
            log.info("User:{},付款单修改 失败", user.getId());
        } else {
            log.info("User:{},付款单修改  成功", user.getId());
        }
        return null;
    }

    /**
     * 付款查看
     * 
     * @author lxs
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward viewBuyPayment(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("付款查看!");
        String id = request.getParameter("id");
        buyPaymentService = (IBuyPaymentService) Container
                .getBean("buyPaymentServiceImpl");
        // 供货商信息、付款信息及评审信息
        BuyPaymentInfoDto bpid = buyPaymentService.viewBuyPayment(id);
        // 采购合同信息
        List<BuyPaymentBuyContractDto> list = buyPaymentService
                .getBuyPaymentBuyContract(id);

        // 产品信息
        List<BuyPaymentBuyContractDto> productList = buyPaymentService
                .getBuyPaymentBuyProduct(id);
        Util u = new Util();
        // 产品总监评审意见
        Integer[] ProMajIder = null;
        // 评审意见拆分
        if (StringUtils.isEmpty(bpid.getProMajIder())) {
            log.debug("评审意见为空！");
        } else {
            ProMajIder = u.splitIdea(bpid.getProMajIder());
        }
        UserEntity userInfo = getLoginUserInfo(request);
        request.setAttribute("userRoleId", userInfo.getRoleId());
        request.setAttribute("ProMajIder", ProMajIder);
        request.setAttribute("bpid", bpid);
        request.setAttribute("id", id);
        request.setAttribute("productList", productList);
        request.setAttribute("BuyContractList", list);
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 付款评审初始化
     * 
     * @author lxs
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward auditBuyPaymentInit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("付款评审初始化!");
        String id = request.getParameter("id");
        buyPaymentService = (IBuyPaymentService) Container
                .getBean("buyPaymentServiceImpl");
        // 供货商信息、付款信息及评审信息
        BuyPaymentInfoDto bpid = buyPaymentService.viewBuyPayment(id);
        Map<String, String> map = new HashMap<String, String>();
        map.put("supplierId", bpid.getSupplierId().toString());
        map.put("productTypeId", bpid.getProductTypeId().toString());
        // 未指定金额查询
        BigDecimal wzdMoney = buyPaymentService.productTypeIdwzdMoney(map);
        request.setAttribute("wzdMoney", wzdMoney);
        // 采购合同信息
        List<BuyPaymentBuyContractDto> list = buyPaymentService
                .getBuyPaymentBuyContract(id);

        // 产品信息
        List<BuyPaymentBuyContractDto> productList = buyPaymentService
                .getBuyPaymentBuyProduct(id);
        Util u = new Util();
        // 产品总监评审意见
        Integer[] ProMajIder = null;
        // 评审意见拆分
        if (StringUtils.isEmpty(bpid.getProMajIder())) {
            log.debug("评审意见为空！");
        } else {
            ProMajIder = u.splitIdea(bpid.getProMajIder());
        }
        UserEntity userInfo = getLoginUserInfo(request);
        request.setAttribute("userRoleId", userInfo.getRoleId());
        request.setAttribute("ProMajIder", ProMajIder);
        request.setAttribute("bpid", bpid);
        request.setAttribute("id", id);
        request.setAttribute("productList", productList);
        request.setAttribute("BuyContractList", list);
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 付款评审
     * 
     * @author lxs
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward auditBuyPayment(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserEntity userInfo = getLoginUserInfo(request);
        log.info("User:{},付款单评审:{}", userInfo.getId(), request.getParameter("id"));
        PaymentEntity p = new PaymentEntity();
        // 付款单号
        String id = request.getParameter("id");
        log.debug((request.getParameter("iderpd")) + "点击按钮0-未通过、1-过通");

        // 退货单状态的判断
        ICommonService commonService = (ICommonService) getBean("commonServiceImpl");
        String status = commonService.getStatusById("payment", id);
        if ((StringUtils.equals("2", status) && userInfo.getRoleId() == 10)
                || (StringUtils.equals("4", status) && userInfo.getRoleId() == 11)
                || (StringUtils.equals("6", status) && userInfo.getRoleId() == 16)
                || (StringUtils.equals("8", status) && userInfo.getRoleId() == 17)) {

            if (userInfo.getRoleId() == Constants.ROLE_PRODUCT_DIRECTOR) {
                StringBuffer sb = new StringBuffer();
                p.setProMajId(userInfo.getId());
                p.setProMajName(userInfo.getName());
                sb.append(request.getParameter("proMajIder0"));
                sb.append(request.getParameter("proMajIder1"));
                sb.append(request.getParameter("proMajIder2"));
                sb.append(request.getParameter("proMajIder3"));
                sb.append(request.getParameter("proMajIder4"));
                p.setProMajIdea(sb.toString());
                p.setProMajDate(Util.getDate());
                p.setProMajText(request.getParameter("proMajText"));
                if (StringUtils.equals(p.getProMajIdea(), "11111")
                        && StringUtils.equals(request.getParameter("iderpd"), "1")) {
                    p.setStatus(4);
                } else {
                    p.setStatus(3);
                }
                if (!StringUtils.equals(p.getProMajIdea(), "11111")
                        && StringUtils.equals(request.getParameter("iderpd"), "1")) {
                    response.getWriter().print("3");
                    return null;
                }
            }
            if (userInfo.getRoleId() == Constants.ROLE_PROCUREMENT_OFFICER) {
                p.setBuyManId(userInfo.getId());
                p.setBuyManName(userInfo.getName());
                p.setBuyManIdea(request.getParameter("buyManIdea"));
                p.setBuyManDate(Util.getDate());
                p.setBuyManText(request.getParameter("buyManText"));
                if (StringUtils.equals(p.getBuyManIdea(), "1")
                        && StringUtils.equals(request.getParameter("iderpd"), "1")) {
                    if (Float.parseFloat(request.getParameter("fkMoney")) < Float
                            .parseFloat(Constants.PURCHASE_MONEY)) {
                        p.setStatus(6);
                    } else {
                        p.setStatus(8);
                    }
                } else {
                    p.setStatus(5);
                }
                if (!StringUtils.equals(p.getBuyManIdea(), "1")
                        && StringUtils.equals(request.getParameter("iderpd"), "1")) {
                    response.getWriter().print("3");
                    return null;
                }
            }
            if (userInfo.getRoleId() == Constants.ROLE_AST_DIRECTOR_OF_OPERATIONS
                    || userInfo.getRoleId() == Constants.ROLE_DIRECTOR_OF_OPERATIONS) {
                // 当前用户ID
                p.setUserId(userInfo.getId());
                // 当前用户NAME
                p.setUserName(userInfo.getName());
                p.setOpeMajId(userInfo.getId());
                p.setOpeMajName(userInfo.getName());
                p.setOpeMajIdea(request.getParameter("opeMajIder"));
                p.setOpeMajDate(Util.getDate());
                p.setOpeMajText(request.getParameter("opeMajText"));
                if (userInfo.getRoleId() == Constants.ROLE_AST_DIRECTOR_OF_OPERATIONS) {
                    if (StringUtils.equals(p.getOpeMajIdea(), "1")
                            && StringUtils.equals(request.getParameter("iderpd"), "1")) {
                        p.setStatus(10);
                    } else {
                        p.setStatus(7);
                    }
                    if (!StringUtils.equals(p.getOpeMajIdea(), "1")
                            && StringUtils.equals(request.getParameter("iderpd"), "1")) {
                        response.getWriter().print("3");
                        return null;
                    }
                } else {
                    if (StringUtils.equals(p.getOpeMajIdea(), "1")
                            && StringUtils.equals(request.getParameter("iderpd"), "1")) {
                        p.setStatus(10);
                    } else {
                        p.setStatus(9);
                    }
                    if (!StringUtils.equals(p.getOpeMajIdea(), "1")
                            && StringUtils.equals(request.getParameter("iderpd"), "1")) {
                        response.getWriter().print("3");
                        return null;
                    }
                }
            }

            log.debug((p.getStatus()) + "付款单状态");
            p.setId(id);
            p.setProductTypeId(Integer.parseInt(request.getParameter("productTypeId")));
            buyPaymentService = (IBuyPaymentService) Container
                    .getBean("buyPaymentServiceImpl");
            boolean result = buyPaymentService.buyPaymentBuyAudit(p);
            /* 防止重复提交 */
            this.saveToken(request);

            if (result == false) {
                log.info("User:{},付款单评审 失败", userInfo.getId());
                response.getWriter().print(2);
            } else {
                log.info("User:{},付款单评审  成功", userInfo.getId());
                response.getWriter().print(1);
            }
        } else {
            log.info("User:{},付款单评审 失败{}", userInfo.getId(), status);
            response.getWriter().print(2);
        }
        return null;
    }

    /**
     * 承兑确认
     * 
     * @author lxs
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward acceptBuyPayment(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},付款单承兑确认:{}", user.getId(), request.getParameter("id"));
        PaymentEntity pe = new PaymentEntity();
        // 付款单号
        pe.setId(request.getParameter("id"));
        // 承兑开具日期
        pe.setAcceptDate(request.getParameter("acceptDate"));
        // 帐期
        pe.setArterm(Integer.parseInt(request.getParameter("arterm")));
        // 承兑票号
        pe.setAcceptNumber(request.getParameter("acceptNumber"));
        // 付款单状态
        pe.setStatus(Integer.parseInt(request.getParameter("status")));
        // 当前用户
        pe.setUserId(user.getId());
        log.debug("付款单号:" + request.getParameter("id") + "付款单状态:"
                + request.getParameter("status"));
        buyPaymentService = (IBuyPaymentService) Container
                .getBean("buyPaymentServiceImpl");
        boolean result = buyPaymentService.acceptBuyPayment(pe);
        response.getWriter().print(result);
        if (result == false) {
            log.info("User:{},付款单承兑确定 失败", user.getId());
        } else {
            log.info("User:{},付款单承兑确定  成功", user.getId());
        }
        return null;
    }

    /**
     * 确认
     * 
     * @author lxs
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * 
     *             public ActionForward confirmBuyPayment(ActionMapping mapping,
     *             ActionForm form, HttpServletRequest request,
     *             HttpServletResponse response) throws Exception { UserEntity
     *             user = getLoginUserInfo(request);
     *             log.info("User:{},付款单确认:{}", user.getId(),
     *             request.getParameter("id")); PaymentEntity pe = new
     *             PaymentEntity(); // 付款单号
     *             pe.setId(request.getParameter("id")); // 付款日期
     *             pe.setPaymentDate(request.getParameter("paymentDate")); //
     *             凭证号 pe.setNumber(request.getParameter("number")); // 付款单状态
     *             pe.
     *             setStatus(Integer.parseInt(request.getParameter("status")));
     *             // 用户ID pe.setUserId(user.getId()); log.debug("付款单号:" +
     *             request.getParameter("id") + "付款单状态:" +
     *             request.getParameter("status")); buyPaymentService =
     *             (IBuyPaymentService) Container
     *             .getBean("buyPaymentServiceImpl"); boolean result =
     *             buyPaymentService.confirmBuyPayment(pe);
     *             response.getWriter().print(result); if (result == false) {
     *             log.info("User:{},付款单确定 失败", user.getId()); } else {
     *             log.info("User:{},付款单确定  成功", user.getId()); } return
     *             mapping.findForward(Constants.SUCCESS); }
     */

    /**
     * 付款单指定初始化
     * 
     * @author lxs
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward appointBuyPaymentInit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("付款指定初始化!");
        String id = request.getParameter("id");
        buyPaymentService = (IBuyPaymentService) Container
                .getBean("buyPaymentServiceImpl");
        // 供货商信息、付款信息及评审信息
        BuyPaymentInfoDto bpid = buyPaymentService.viewBuyPayment(id);
        // 采购合同信息
        List<BuyPaymentBuyContractDto> list = buyPaymentService
                .getBuyPaymentBuyContract(id);

        // 产品信息
        List<BuyPaymentBuyContractDto> productList = buyPaymentService
                .getBuyPaymentBuyProduct(id);
        UserEntity userInfo = getLoginUserInfo(request);
        request.setAttribute("userRoleId", userInfo.getRoleId());
        request.setAttribute("bpid", bpid);
        request.setAttribute("id", id);
        request.setAttribute("productList", productList);
        request.setAttribute("BuyContractList", list);
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 付款单指定
     * 
     * @author lxs
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward appointBuyPayment(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        boolean backResult = true;
        // 获取前台封装数据
        BuyPaymentAddDto buyP = new BuyPaymentAddDto(request);
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},付款单指定:{}", user.getId(), buyP.getPaymentId());
        /* 获取Bean容器 */
        buyPaymentService = (IBuyPaymentService) getBean("buyPaymentServiceImpl");
        backResult = buyPaymentService.appointBuyPayment(buyP);
        log.debug("\n返回数据：" + backResult);
        response.getWriter().print(backResult);
        if (backResult == false) {
            log.info("User:{},付款单指定 失败", user.getId());
        } else {
            log.info("User:{},付款单指定 成功", user.getId());
        }
        return null;
    }

    /**
     * 付款单再指定初始化
     * 
     * @author lxs
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public ActionForward againAppointBuyPaymentInit(ActionMapping mapping,
            ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        log.debug("付款再指定初始化!");
        String id = request.getParameter("id");
        String backMoney = request.getParameter("backMoney");
        log.debug("退款金额" + backMoney);
        if (StringUtils.isEmpty(backMoney)) {
            backMoney = "0";
        }
        buyPaymentService = (IBuyPaymentService) Container
                .getBean("buyPaymentServiceImpl");
        // 供货商信息、付款信息及评审信息
        BuyPaymentInfoDto bpid = buyPaymentService.viewBuyPayment(id);
        // 采购合同信息
        List<BuyPaymentBuyContractDto> list = buyPaymentService
                .getBuyPaymentBuyContract(id);

        // 产品信息
        List<BuyPaymentBuyContractDto> productList = buyPaymentService
                .getBuyPaymentBuyProduct(id);
        UserEntity userInfo = getLoginUserInfo(request);
        List<ProductTypeEntity> producttypelist = buyPaymentService
                .getBuyPaymentProductTypeByUserId(userInfo.getId());
        request.setAttribute("backMoney", backMoney);
        request.setAttribute("producttypelist", producttypelist);
        request.setAttribute("userRoleId", userInfo.getRoleId());
        request.setAttribute("bpid", bpid);
        request.setAttribute("id", id);
        request.setAttribute("productList", productList);
        request.setAttribute("BuyContractList", list);
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 付款单再指定
     * 
     * @author lxs
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward againAppointBuyPayment(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取前台封装数据
        BuyPaymentAddDto buyP = new BuyPaymentAddDto(request);
        /* 获取Bean容器 */
        buyPaymentService = (IBuyPaymentService) getBean("buyPaymentServiceImpl");
        // 获取当前登陆用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},付款单再指定:{}", user.getId(), buyP.getPaymentId());
        // 封装数据
        buyP.setUserId(user.getId());
        buyP.setUserName(user.getName());
        buyP.setDate(Util.getDate());
        // 合同类型
        String contractType = request.getParameter("contractType");
        int err = 0;
        boolean result = true;
        if (StringUtils.equals(contractType, "0")) {
            result = buyPaymentService.validatePaymentMoney(buyP);
        }
        if (result) {
            result = buyPaymentService.againAppointBuyPayment(buyP, contractType);
        } else {
            err = 2;
        }
        if (result) {
            err = 1;
        }
        log.debug("返回值状态" + err);
        response.getWriter().print(err);
        if (result == false) {
            log.info("User:{},付款单再指定 失败", user.getId());
        } else {
            log.info("User:{},付款单再指定 成功", user.getId());
        }
        return null;
    }

    /**
     * 供货商选择 获取所有供货商
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
    public ActionForward selectSupplierInit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("采购付款-供货商选择小页面！");
        String supplierName = request.getParameter("supplierName");
        /* 翻页 */
        NewPage page = getNewPage(request);
        /* 翻页url */
        page.setUrl("buyPaymentSelectSupplier.do");
        /* 翻页传参 */
        page.setQuery("supplierName", supplierName);
        /* 获取Bean容器 */
        buyPaymentService = (IBuyPaymentService) getBean("buyPaymentServiceImpl");
        List list = buyPaymentService.getBuyPaymentAllSupplier(supplierName, page);
        request.setAttribute("supplierName", supplierName);
        request.setAttribute("NewPage", page);
        request.setAttribute("list", list);
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 抽象翻页检索传参
     * 
     * @param newPage
     * @param mReturnListForm
     */
    private void pageParam(NewPage newPage, BuyPaymentListQueryForm bf) {
        newPage.setQuery("buyPaymentListQueryDto.id", bf.getBuyPaymentListQueryDto()
                .getId());
        newPage.setQuery("buyPaymentListQueryDto.productTypeId", bf
                .getBuyPaymentListQueryDto().getProductTypeId());
        newPage.setQuery("buyPaymentListQueryDto.supplierName", bf
                .getBuyPaymentListQueryDto().getSupplierName());
        newPage.setQuery("buyPaymentListQueryDto.money", bf.getBuyPaymentListQueryDto()
                .getMoney());
        newPage.setQuery("buyPaymentListQueryDto.paymentWay", bf
                .getBuyPaymentListQueryDto().getPaymentWay());
        newPage.setQuery("buyPaymentListQueryDto.status", bf.getBuyPaymentListQueryDto()
                .getStatus());
        newPage.setQuery("buyPaymentListQueryDto.startDate", bf
                .getBuyPaymentListQueryDto().getStartDate());
        newPage.setQuery("buyPaymentListQueryDto.endDate", bf.getBuyPaymentListQueryDto()
                .getEndDate());
        newPage.setQuery("buyPaymentListQueryDto.startAcceptDate", bf
                .getBuyPaymentListQueryDto().getStartAcceptDate());
        newPage.setQuery("buyPaymentListQueryDto.endAcceptDate", bf
                .getBuyPaymentListQueryDto().getEndAcceptDate());
        newPage.setQuery("buyPaymentListQueryDto.startPaymentDate", bf
                .getBuyPaymentListQueryDto().getStartPaymentDate());
        newPage.setQuery("buyPaymentListQueryDto.endPaymentDate", bf
                .getBuyPaymentListQueryDto().getEndPaymentDate());
        newPage.setQuery("buyPaymentListQueryDto.userName", bf
                .getBuyPaymentListQueryDto().getUserName());
        newPage.setQuery("buyPaymentListQueryDto.inStockId", bf
                .getBuyPaymentListQueryDto().getInStockId());
        newPage.setQuery("buyPaymentListQueryDto.productContractCode", bf
                .getBuyPaymentListQueryDto().getProductContractCode());
        newPage.setQuery("buyPaymentListQueryDto.init", bf.getBuyPaymentListQueryDto()
                .getInit());
    }

    /**
     * 供货商联系人
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
    public ActionForward getSupplierLinkMan(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("供货商联系人");
        String supplierId = request.getParameter("supplierId");
        log.debug("供货商ID：" + supplierId);
        List list = new ArrayList();
        list = buyPaymentService.getBuyPaymentSupplierLinkmanBySupplierId(supplierId);
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(JsonUtil.list2json(list));
        writer.close();
        return null;
    }

    /**
     * 采购合同选择
     * 
     * @author LuPing
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes" })
    public ActionForward getBuyPaymentSelectBuyContract(ActionMapping mapping,
            ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        log.debug("采购合同选择小页面");
        BuyPaymentSelectBuyContractForm formBuy = (BuyPaymentSelectBuyContractForm) form;

        String supplierId = request.getParameter("supplierId");
        String productTypeId = request.getParameter("productTypeId");
        // 封装查询参数
        Map<String, String> map = new HashMap<String, String>();
        map.put("supplierId", supplierId);
        map.put("productTypeId", productTypeId);
        map.put("companyContractCode", formBuy.getCompanyContractCode());
        map.put("productContractCode", formBuy.getProductContractCode());
        /* 翻页 */
        NewPage page = getNewPage(request);
        /* 翻页url */
        page.setUrl("buyPaymentSelectBuyContract.do");
        /* 翻页传参 */
        page.setQuery("supplierId", supplierId);
        page.setQuery("productTypeId", productTypeId);
        page.setQuery("companyContractCode", formBuy.getCompanyContractCode());
        page.setQuery("productContractCode", formBuy.getProductContractCode());
        /* 获取Bean容器 */
        buyPaymentService = (IBuyPaymentService) getBean("buyPaymentServiceImpl");
        List list = buyPaymentService.getBuyPaymentSelectBuyContracts(map, page);
        request.setAttribute("formBuy", formBuy);
        request.setAttribute("supplierId", supplierId);
        request.setAttribute("productTypeId", productTypeId);
        request.setAttribute("NewPage", page);
        request.setAttribute("list", list);
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 入库明细选择
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
    public ActionForward getBuyPaymentInStockDetail(ActionMapping mapping,
            ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        log.debug("入库明细选择小页面");
        BuyPaymentSelectInStockDetailForm formInStock = (BuyPaymentSelectInStockDetailForm) form;
        String supplierId = request.getParameter("supplierId");
        String productTypeId = request.getParameter("productTypeId");
        // 封装查询参数
        Map<String, String> map = new HashMap<String, String>();
        map.put("supplierId", supplierId);
        map.put("productTypeId", productTypeId);
        map.put("inStockId", formInStock.getInStockId());
        map.put("productContractCode", formInStock.getProductContractCode());
        map.put("companyContractCode", formInStock.getCompanyContractCode());
        map.put("productNo", formInStock.getProductNo());
        map.put("productName", formInStock.getProductName());
        map.put("productType", formInStock.getProductType());
        map.put("startRequestAccountDate", formInStock.getStartRequestAccountDate());
        map.put("endRequestAccountDate", formInStock.getEndRequestAccountDate());
        /* 翻页 */
        NewPage page = getNewPage(request);
        /* 翻页url */
        page.setUrl("buyPaymentSelectInStockDetail.do");
        /* 翻页传参 */
        page.setQuery("supplierId", supplierId);
        page.setQuery("productTypeId", productTypeId);
        page.setQuery("inStockId", formInStock.getInStockId());
        page.setQuery("productContractCode", formInStock.getProductContractCode());
        page.setQuery("companyContractCode", formInStock.getCompanyContractCode());
        page.setQuery("productNo", formInStock.getProductNo());
        page.setQuery("productName", formInStock.getProductName());
        page.setQuery("productType", formInStock.getProductType());
        page.setQuery("startRequestAccountDate", formInStock.getStartRequestAccountDate());
        page.setQuery("endRequestAccountDate", formInStock.getEndRequestAccountDate());
        /* 获取Bean容器 */
        buyPaymentService = (IBuyPaymentService) getBean("buyPaymentServiceImpl");
        List list = buyPaymentService.getBuyPaymentInStockDetail(map, page);
        request.setAttribute("NewPage", page);
        request.setAttribute("productTypeId", productTypeId);
        request.setAttribute("supplierId", supplierId);
        request.setAttribute("formInStock", formInStock);
        request.setAttribute("list", list);
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 付款打印
     * 
     * @author lxs
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward buyPaymentPrint(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("付款打印!");
        UserEntity userInfo = getLoginUserInfo(request);
        PaymentEntity pe = new PaymentEntity();
        // 付款单号
        pe.setId(request.getParameter("id"));
        // 付款单状态
        //pe.setStatus(Integer.parseInt(request.getParameter("status")));
        // 退货单状态的判断
        ICommonService commonService = (ICommonService) getBean("commonServiceImpl");
        String status = commonService.getStatusById("payment", request.getParameter("id"));
        pe.setStatus(Integer.parseInt(status));
        // 付款方式
        pe.setPaymentWay(Integer.parseInt(request.getParameter("paymentWay")));
        pe.setUserId(userInfo.getId());
        /* 获取Bean容器 */
        buyPaymentService = (IBuyPaymentService) getBean("buyPaymentServiceImpl");
        boolean result = buyPaymentService.buyPaymentPrint(pe);
        response.getWriter().print(result);
        return null;
    }

    /**
     * 付款删除
     * 
     * @author lxs
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteBuyPayment(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        UserEntity userInfo = getLoginUserInfo(request);
        log.info("User:{},付款单删除:{}", userInfo.getId(), id);
        buyPaymentService = (IBuyPaymentService) getBean("buyPaymentServiceImpl");
        boolean result = buyPaymentService.deleteBuyPayment(id);
        if (result == false) {
            log.info("User:{},付款单删除 失败", userInfo.getId());
            request.setAttribute("err", "付款单删除失败！");
        } else {
            log.info("User:{},付款单删除 成功", userInfo.getId());
        }
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 付款确认初始化
     * 
     * @author lxs
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward confirmBuyPaymentModifyInfo(ActionMapping mapping,
            ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String id = request.getParameter("id");
        buyPaymentService = (IBuyPaymentService) Container
                .getBean("buyPaymentServiceImpl");
        // 供货商信息、付款信息及评审信息
        BuyPaymentInfoDto bpid = buyPaymentService.viewBuyPayment(id);
        // 采购合同信息
        List<BuyPaymentBuyContractDto> list = buyPaymentService
                .getBuyPaymentBuyContract(id);

        // 产品信息
        List<BuyPaymentBuyContractDto> productList = buyPaymentService
                .getBuyPaymentBuyProduct(id);
        Util u = new Util();
        // 产品总监评审意见
        Integer[] ProMajIder = null;
        // 评审意见拆分
        if (StringUtils.isEmpty(bpid.getProMajIder())) {
            log.debug("评审意见为空！");
        } else {
            ProMajIder = u.splitIdea(bpid.getProMajIder());
        }
        UserEntity userInfo = getLoginUserInfo(request);
        request.setAttribute("userRoleId", userInfo.getRoleId());
        request.setAttribute("ProMajIder", ProMajIder);
        request.setAttribute("bpid", bpid);
        request.setAttribute("id", id);
        request.setAttribute("productList", productList);
        request.setAttribute("BuyContractList", list);
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 确认
     * 
     * @author lxs
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward confirmBuyPaymentModify(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},付款单确认:{}", user.getId(), request.getParameter("id"));
        PaymentEntity pe = new PaymentEntity();
        // 付款单号
        pe.setId(request.getParameter("id"));
        // 付款日期
        pe.setPaymentDate(request.getParameter("paymentDate"));
        // 凭证号
        pe.setNumber(request.getParameter("number"));
        // 付款单状态
        pe.setStatus(Integer.parseInt(request.getParameter("status")));
        // 合同类型
        String contractType = request.getParameter("contractType");
        log.debug("合同类型:" + contractType);
        // 入库单ID
        String[] productId = request.getParameterValues("productId");
        // 产品ID
        String[] inStockId = request.getParameterValues("inStockId");
        // 指定金额
        String[] appointMoney = request.getParameterValues("appointMoney");
        // 付款金额（外单状态下应该更新付款金额）
        String money = request.getParameter("money");
        if (StringUtils.isNotBlank(money)) {
            pe.setMoney(new BigDecimal(money));
        } else {
            log.error("获取付款金额错误", new Exception("获取金额为空"));
            return null;
        }
        // 用户ID
        pe.setUserId(user.getId());
        log.debug("付款单号:" + request.getParameter("id") + "付款单状态:"
                + request.getParameter("status"));
        buyPaymentService = (IBuyPaymentService) Container
                .getBean("buyPaymentServiceImpl");
        boolean result = buyPaymentService.confirmBuyPayment(pe, inStockId, productId,
                appointMoney, contractType);
        if (result == false) {
            log.info("User:{},付款单确定 失败", user.getId());
        } else {
            log.info("User:{},付款单确定  成功", user.getId());
        }
        response.getWriter().print(result);
        return null;
    }

    /**
     * 外单判断
     * 
     * @author lxs
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward buyPaymentzd(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("判断是否是外单！");
        String id = request.getParameter("id");
        buyPaymentService = (IBuyPaymentService) Container
                .getBean("buyPaymentServiceImpl");
        Integer result = buyPaymentService.buyPaymentzd(id);
        log.debug("类型为" + result);
        response.getWriter().print(result);
        return null;
    }

}
