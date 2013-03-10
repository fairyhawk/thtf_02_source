/**
 * ClassName  BuyContractAction
 *
 * History
 * Create User: ChenHuajiang
 * Create Date: 2010-6-1
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.action.purchase;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.NewBaseAction;
import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.constant.Arith;
import cn.com.thtf.egov.cms.constant.Constants;
import cn.com.thtf.egov.cms.dto.BuyContractOfAddDto;
import cn.com.thtf.egov.cms.dto.BuyContractPreviewDto;
import cn.com.thtf.egov.cms.dto.BuyContractProductInfoDto;
import cn.com.thtf.egov.cms.dto.BuyContractReviewDto;
import cn.com.thtf.egov.cms.dto.BuyContractSelectDto;
import cn.com.thtf.egov.cms.dto.ProductTypeInfoDto;
import cn.com.thtf.egov.cms.dto.WorkReceiverDto;
import cn.com.thtf.egov.cms.entity.SupplierLinkmanEntity;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.form.BuyContractOfAddForm;
import cn.com.thtf.egov.cms.form.BuyContractReviewForm;
import cn.com.thtf.egov.cms.form.BuyContractSearchForm;
import cn.com.thtf.egov.cms.form.ProductSearchForm;
import cn.com.thtf.egov.cms.iservice.common.ICommonService;
import cn.com.thtf.egov.cms.iservice.purchase.IBuyContractService;
import cn.com.thtf.egov.cms.util.JsonUtil;

/**
 * 采购合同
 * 
 * @author ChenHuajiang
 */

public class BuyContractAction extends NewBaseAction {
    private static Logger log = LoggerFactory.getLogger(BuyContractAction.class);
    private IBuyContractService buyContractService = null;
    private ICommonService common = (ICommonService) getBean("commonServiceImpl");
    private java.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private IBuyContractService buyService = (IBuyContractService) getBean("buyContractServiceImpl");

    /**
     * 采购合同列表
     * 
     * @author ChenHuajiang
     */
    @SuppressWarnings("unchecked")
    public ActionForward getBuyContracts(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},采购合同列表【开始】", user.getId());

        // 采购合同列表
        buyContractService = (IBuyContractService) getBean("buyContractServiceImpl");
        // 产品类型列表
        common = (ICommonService) getBean("commonServiceImpl");

        BuyContractSearchForm contractForm = (BuyContractSearchForm) form;

        if (!isValidPermission(user)) {
            log.info("User:{},越权操作", user.getId());
            request.setAttribute("msg", "越权操作");
            return null;
        }

        // 分页
        NewPage page = getNewPage(request);
        page.setUrl("buyContractList.do");

        // 用于验证是 检索方式的查询或初始页面查询的参数
        String initStr = request.getParameter("init");
        page.setQuery("init", initStr);

        contractForm.setRoleId(user.getRoleId() + "");
        contractForm.setUserId(user.getId());
        contractForm.setInitStr(initStr);

        if ("null".equals(contractForm.getProductTypeId())) {
            contractForm.setProductTypeId(null);
        }
        if ("null".equals(contractForm.getTemplateType())) {
            contractForm.setTemplateType(null);
        }
        if ("null".equals(contractForm.getStatus())) {
            contractForm.setStatus(null);
        }

        // 获取采购合同
        List list = null;
        contractForm.setStar((page.getThisPage() - 1) * page.getPageSize());
        contractForm.setRows(page.getPageSize());
        list = buyContractService.getBuyContracts(contractForm, page);

        // 将查询条件加到分页中
        setSearchConditionToPage(contractForm, page);
        request.setAttribute("NewPage", page);
        // 当前用户
        request.setAttribute("user", user);
        // 查询条件
        request.setAttribute("searchForm", contractForm);
        // 产品分类列表
        request.setAttribute("productTypeList",
                common.getAllProductTypes(null, user.getId(), user.getRoleId() + ""));
        // 采购合同列表
        request.setAttribute("buyContractList", list);
        // 用户可用动作
        request.setAttribute("userAction", setUserAction(user));
        log.info("User:{},采购合同列表【结束】", user.getId());
        if (user.getRoleId() == Constants.ROLE_PRODUCT_DIRECTOR) {
            /** 产品总监 */
            request.setAttribute("addBuyContract", "true");
        }
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 新建采购合同 显示
     * 
     * @author hanhaiyun
     */
    @SuppressWarnings("unchecked")
    public ActionForward addBuyContract(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},新建采购合同 显示【开始】", user.getId());

        BuyContractOfAddForm buyContractOfAddForm = (BuyContractOfAddForm) form;
        // 采购合同列表
        buyContractService = (IBuyContractService) getBean("buyContractServiceImpl");

        List<ProductTypeInfoDto> list = null;
        list = (List<ProductTypeInfoDto>) buyContractService.getProdTypeDeptList(user
                .getId());
        /** 发货信息 */
        request.setAttribute("productList", list);
        request.setAttribute("jsonOfSum", "{resultSum:0}");
        /** 地址 */
        buyContractOfAddForm.getBuyContractOfAddDto().setPlace(Constants.SELL_PLACE);
        /** 模板 */
        buyContractOfAddForm.getBuyContractOfAddDto().setTemplateType("0");
        request.setAttribute("buyContractOfAddForm", buyContractOfAddForm);
        log.info("User:{},新建采购合同 显示【结束】", user.getId());
        saveToken(request);
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 查看采购合同信息
     * 
     * @author HanHaiyun
     */
    public ActionForward getBuyContract(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},查看采购合同信息【开始】", user.getId());

        // 采购合同列表
        buyContractService = (IBuyContractService) getBean("buyContractServiceImpl");
        BuyContractSelectDto buyContractSelectDto = null;
        // List<BuyContractReceivingInfoDto> buyContractReceivings = null;
        List<BuyContractProductInfoDto> buyContractProducts = null;
        String buyContractId = request.getParameter("buyContractId");
        String review = request.getParameter("review");
        if (buyContractId == null || "".equals(buyContractId)) {
            return mapping.findForward(Constants.FAIL);
        } else {
            try {
                // 得到采购合同的基本信息
                buyContractSelectDto = buyContractService
                        .getBuyContractById(buyContractId);
                // 得到采购合同的产品信息
                buyContractProducts = buyContractService
                        .getBuyContractProductInfoById(buyContractId);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                buyContractSelectDto.setDateTime(sdf.format(new Date()));
                request.setAttribute("buyContractSelectDto", buyContractSelectDto);
                request.setAttribute("buyContractReceivings",
                        buyContractService.getReceiceInfoOfShow(buyContractId));// 得到采购合同的收货信息
                request.setAttribute("buyContractProducts", buyContractProducts);
            } catch (Exception e) {
                log.error("User:{},查看采购合同信息【失败】:{}", user.getId(), e);
            }
            log.info("User:{},查看采购合同信息【结束】", user.getId());
        }
        if (review == null || "".equals(review)) {
            return mapping.findForward(Constants.SUCCESS);
        } else {
            /* 防止重复提交 */
            this.saveToken(request);
            return mapping.findForward("reviewpage");
        }
    }

    /**
     * 采购合同信息预览
     * 
     * @author HanHaiyun
     */
    public ActionForward getBuyContractPreview(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},采购合同预览【开始】", user.getId());

        // 获取采购合同号
        String buyContractId = request.getParameter("buyContractId");
        if (buyContractId == null || "".equals(buyContractId)) {
            return mapping.findForward(Constants.FAIL);
        } else {
            try {
                buyContractService = (IBuyContractService) getBean("buyContractServiceImpl");
                BuyContractPreviewDto buyContractPreviewDto = buyContractService
                        .getBuyContractPreviewByContractId(buyContractId);
                // 得到采购合同的产品信息
                List<BuyContractProductInfoDto> buyContractProducts = buyContractService
                        .getBuyContractProductInfoById(buyContractId);
                Double totalMoney = 0.0;// 产品总额
                // 遍历集合得到产品总额
                for (int i = 0; i < buyContractProducts.size(); i++) {
                    totalMoney += buyContractProducts.get(i).getBuyCount()
                            * Double.parseDouble(buyContractProducts.get(i).getBuyPrice()
                                    .toString());
                }
                // 将小写金额转换为大写金额
                String totalMoenyUp = Arith.Const(totalMoney.toString());
                request.setAttribute("totalMoney", totalMoenyUp);// 保存到request中
                List buyContractOfReceiveInfoDtoList = buyContractService
                        .getReceiceInfoOfShow(buyContractId);
                request.setAttribute("showAdjunct",
                        buyContractOfReceiveInfoDtoList.size() > 1 ? true : false);
              //判断是否存在收获地址
                if(buyContractOfReceiveInfoDtoList.size()>0){
                    request.setAttribute("buyContractReceivings",
                            buyContractOfReceiveInfoDtoList.get(0));// 得到采购合同的收货信息
                }else{
                    request.setAttribute("buyContractReceivings", null);
                }
                request.setAttribute("buyContractPreviewDto", buyContractPreviewDto);
                request.setAttribute("buyContractProducts", buyContractProducts);
                if ("true".equals(request.getParameter("print"))) {
                    request.setAttribute("print", "true");
                }
            } catch (Exception e) {
                log.error("User:{},采购合同预览【失败】:{}", user.getId(), e);
            }
        }
        log.info("User:{},采购合同预览【结束】", user.getId());
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 采购合同评审
     * 
     * @author HanHaiyun
     */
    public ActionForward getBuyContractsReview(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        /* 防止重复提交 */
        if (isTokenValid(request, true)) {
            resetToken(request);
        } else {
            log.info("User:{},采购合同评审失败： 不能重复提交", user.getId());
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&isTokenValid=true");
            return mapping.findForward(Constants.FAIL);
        }
        log.info("User:{},采购合同评审【开始】", user.getId());

        BuyContractReviewForm buyContractReviewForm = (BuyContractReviewForm) form;
        BuyContractReviewDto buyContractReviewDto = buyContractReviewForm
                .getBuyContractReviewDto();
        buyContractService = (IBuyContractService) getBean("buyContractServiceImpl");
        WorkReceiverDto workReceiverDto = null;

        // 得到当前登录用户
        UserEntity userEntity = getLoginUserInfo(request);
        UserEntity buySpecialist = null;// 采购专员
        try {
            if (userEntity.getRoleId() == Constants.ROLE_COMPLIANCE_OFFICER) {// 当前用户为法务专员
                workReceiverDto = common.getUserIdByCondition(new Integer(
                        buyContractReviewDto.getProdTypeId()), null, null,
                        Constants.ROLE_PROCUREMENT_OFFICER);// 采购主管
                String roleid = workReceiverDto
                        .getUserIdByRoleId(Constants.ROLE_PROCUREMENT_OFFICER);
                if (roleid == null || "".equals(roleid)) {
                    log.info("User:{},采购合同评审【失败】：采购主管不存在", user.getId());
                    request.setAttribute("redirect", request.getHeader("Referer")
                            + "&procurementOfficerError=true");
                    return mapping.findForward(Constants.FAIL);
                } else {
                    buyContractReviewDto.setBuyManId(roleid);
                }
            } else if (userEntity.getRoleId() == Constants.ROLE_PROCUREMENT_OFFICER) {// 当前用户为采购主管
                // 如果产品总额小于50万并且单产品利润小于10%,那么将由运营总监助理进行评审，否则将由运营总监进行评审
                // 合同金额
                BigDecimal contractMoney = new BigDecimal(
                        buyContractReviewDto.getProdTotalMoney());
                BigDecimal reuqestMoney = new BigDecimal(Constants.BUY_CONTRACT_MONEY);
                // 合同增长率
                BigDecimal increaseRate = new BigDecimal(
                        buyContractReviewDto.getIncreaserate());
                BigDecimal requestReate = new BigDecimal(Constants.PROD_GROWTH_RATE);
                if (reuqestMoney.subtract(contractMoney).doubleValue() > 0
                        && requestReate.subtract(increaseRate).doubleValue() > 0) {
                    workReceiverDto = common.getUserIdByCondition(null, null, null,
                            Constants.ROLE_AST_DIRECTOR_OF_OPERATIONS);// 获得运营总监助理
                    String opeMajId = workReceiverDto
                            .getUserIdByRoleId(Constants.ROLE_AST_DIRECTOR_OF_OPERATIONS);
                    if (opeMajId == null || "".equals(opeMajId)) {
                        log.info("User:{},采购合同评审【失败】：运营总监助理不存在", user.getId());
                        request.setAttribute("redirect", request.getHeader("Referer")
                                + "&astDirectorOfOperationsError=true");
                        return mapping.findForward(Constants.FAIL);
                    } else {
                        buyContractReviewDto
                                .setOpeMajId(workReceiverDto
                                        .getUserIdByRoleId(Constants.ROLE_AST_DIRECTOR_OF_OPERATIONS));
                        buyContractReviewDto
                                .setOpeMajRoleId(Constants.ROLE_AST_DIRECTOR_OF_OPERATIONS);
                    }

                } else {
                    workReceiverDto = common.getUserIdByCondition(null, null, null,
                            Constants.ROLE_DIRECTOR_OF_OPERATIONS);// 获得运营总监
                    String opeMajId = workReceiverDto
                            .getUserIdByRoleId(Constants.ROLE_DIRECTOR_OF_OPERATIONS);
                    if (opeMajId == null || "".equals(opeMajId)) {
                        log.info("User:{},采购合同评审【失败】：运营总监不存在", user.getId());
                        request.setAttribute("redirect", request.getHeader("Referer")
                                + "&directorOfOperationsError=true");
                        return mapping.findForward(Constants.FAIL);
                    } else {
                        buyContractReviewDto.setOpeMajId(opeMajId);
                        buyContractReviewDto
                                .setOpeMajRoleId(Constants.ROLE_DIRECTOR_OF_OPERATIONS);
                    }
                }
            } else if (userEntity.getRoleId() == Constants.ROLE_DIRECTOR_OF_OPERATIONS
                    || userEntity.getRoleId() == Constants.ROLE_AST_DIRECTOR_OF_OPERATIONS) {// 用户身份为运营总监或运营总监助理
                buySpecialist = buyContractService.getBuySpecialist(buyContractReviewDto
                        .getId());
                if (buySpecialist == null) {
                    log.info("User:{},采购合同评审【失败】：采购专员不存在", user.getId());
                    request.setAttribute("redirect", request.getHeader("Referer")
                            + "&buySpecialistError=true");
                    return mapping.findForward(Constants.FAIL);

                }
                if (buyContractReviewDto.getStatus() == 1) {
                    buyContractReviewDto.setProdContractCode(common.getProdContractSN(
                            "CG", buyContractReviewDto.getProdTypeId(), "buy_contract"));
                }
            }
            // 采购合同评审
            buyContractService.updateReview(buyContractReviewDto, userEntity,
                    buyContractReviewDto.getStatus(), buySpecialist);
        } catch (Exception e) {
            log.error("User:{},采购合同评审【失败】:{}", user.getId(), e);
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&reviewError=true");
            return mapping.findForward(Constants.FAIL);
        }
        log.info("User:{},采购合同评审【结束】", user.getId());
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 获取供应商信息
     * 
     * @author ChenHuajiang
     */
    @SuppressWarnings("unchecked")
    public ActionForward getSuppliers(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},选择供应商【开始】", user.getId());

        buyContractService = (IBuyContractService) getBean("buyContractServiceImpl");
        // 分页
        NewPage page = getNewPage(request);
        page.setUrl("supplierList.do");
        String name = request.getParameter("supplierName");
        if (StringUtils.isNotEmpty(name)) {
            name = name.trim();
        }
        List list = null;
        list = buyContractService.getSuppliers(name, page);

        page.setQuery("supplierName", name);
        request.setAttribute("NewPage", page);
        request.setAttribute("supplierName", name);
        request.setAttribute("supplierList", list);
        log.info("User:{},选择供应商【结束】", user.getId());
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 根据供应商Id获取相应的联系人
     * 
     * @author ChenHuajiang
     */
    @SuppressWarnings("unchecked")
    public ActionForward getLinkmanBySupplierId(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},选择供应商联系人【开始】", user.getId());

        String supplierId = request.getParameter("supplierId");

        try {
            buyContractService = (IBuyContractService) getBean("buyContractServiceImpl");
            List list = null;
            list = buyContractService.getSupplierLinkmanBySupplierId(supplierId);
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write(JsonUtil.list2json(list));
            writer.close();
        } catch (Exception e) {
            log.error("User:{},选择供应商联系人【失败】:{}", user.getId(), e);
        }
        log.info("User:{},选择供应商联系人【结束】", user.getId());
        return null;
    }

    /**
     * 根据供应商联系人Id获取联系人相关信息
     * 
     * @author ChenHuajiang
     */
    public ActionForward getLinkmanById(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},选择联系人信息【开始】", user.getId());

        String id = request.getParameter("id");

        try {
            buyContractService = (IBuyContractService) getBean("buyContractServiceImpl");
            SupplierLinkmanEntity entity = null;
            entity = buyContractService.getSupplierLinkmanById(id);
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write(JsonUtil.bean2json(entity));
            writer.close();
        } catch (Exception e) {
            log.error("User:{},选择联系人信息【失败】:{}", user.getId(), e);
        }
        log.info("User:{},选择联系人信息【结束】", user.getId());
        return null;
    }

    /**
     * 根据条件检索产品信息
     * 
     * @author ChenHuajiang
     */
    @SuppressWarnings("unchecked")
    public ActionForward getProdInfoByCondition(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},根据条件检索产品信息【开始】", user.getId());

        ProductSearchForm prodForm = (ProductSearchForm) form;
        // 分页
        NewPage page = getNewPage(request);
        page.setUrl("getProdInfoByCondition.do");
        page.setQuery("prodTypeId", prodForm.getProdTypeId() + "");
        page.setQuery("prodTypeName", prodForm.getProdTypeName());
        page.setQuery("prodSerieId", prodForm.getProdSerieId());
        page.setQuery("prodSeriesName", prodForm.getProdSeriesName());
        page.setQuery("prodCode", prodForm.getProdCode());
        page.setQuery("prodName", prodForm.getProdName());
        page.setQuery("prodType", prodForm.getProdType());

        buyContractService = (IBuyContractService) getBean("buyContractServiceImpl");
        // 产品信息
        List prodList = null;
        prodList = buyContractService.getProdInfoByCondition(prodForm, page);

        // 产品系列
        List prodSerieList = null;
        prodSerieList = buyContractService.getProdSeriesByProdTypeId(prodForm
                .getProdTypeId() + "");

        // 查询条件
        request.setAttribute("searchForm", prodForm);
        request.setAttribute("NewPage", page);
        request.setAttribute("prodInfoList", prodList);
        request.setAttribute("prodSerieList", prodSerieList);

        int listSize = 0;
        if (prodList != null) {
            listSize = prodList.size();
        }
        request.setAttribute("listSize", listSize);
        log.info("User:{},根据条件检索产品信息【结束】", user.getId());
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 库房收货地址
     * 
     * @author ChenHuajiang
     */
    @SuppressWarnings("unchecked")
    public ActionForward getStockroomAddressList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},获取库房收货地址【开始】", user.getId());
        // 分页
        NewPage page = getNewPage(request);
        page.setUrl("getStockroomAddressList.do");

        buyContractService = (IBuyContractService) getBean("buyContractServiceImpl");
        // 库房收获地址
        List stockroomList = null;
        stockroomList = buyContractService.getStockroomAddressList(page);

        request.setAttribute("NewPage", page);
        request.setAttribute("stockroomList", stockroomList);
        log.info("User:{},获取库房收货地址【结束】", user.getId());
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 客户收货地址
     * 
     * @author ChenHuajiang
     */
    @SuppressWarnings("unchecked")
    public ActionForward getCustomerAddressList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},获取客户收货地址【开始】", user.getId());

        String customerName = request.getParameter("customerName");
        // 分页
        NewPage page = getNewPage(request);
        page.setUrl("getCustomerAddressList.do");
        page.setQuery("customerName", customerName);

        buyContractService = (IBuyContractService) getBean("buyContractServiceImpl");
        // 库房收获地址
        List custAddressList = null;
        custAddressList = buyContractService.getCustomerAddressList(customerName, page);

        request.setAttribute("NewPage", page);
        request.setAttribute("customerAddressList", custAddressList);
        log.info("User:{},获取客户收货地址【结束】", user.getId());
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 公司收货地址
     * 
     * @author ChenHuajiang
     */
    @SuppressWarnings("unchecked")
    public ActionForward getCompanyAddressList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},获取公司收货地址【开始】", user.getId());

        buyContractService = (IBuyContractService) getBean("buyContractServiceImpl");
        // 公司收获地址
        List list = null;
        list = buyContractService.getCompanyAddressList();

        request.setAttribute("companyAddressList", list);
        log.info("User:{},获取公司收货地址【结束】", user.getId());
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 收货地址添加
     * 
     * @author ChenHuajiang
     */
    @SuppressWarnings("unchecked")
    public ActionForward goReceiveDetailAddSub(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},收货地址添加【开始】", user.getId());

        buyContractService = (IBuyContractService) getBean("buyContractServiceImpl");
        String buyContractId = request.getParameter("buyContractId");
        // 公司收获地址
        List list = null;
        list = buyContractService.getReceiveAddress(buyContractId);

        request.setAttribute("companyAddressList", list);

        log.info("User:{},收货地址添加【结束】", user.getId());
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 将查询条件加到 page中
     * 
     * @param searchForm
     *            页面查询条件
     * @param page
     */
    private void setSearchConditionToPage(BuyContractSearchForm searchForm, NewPage page) {
        page.setQuery("productContractCode", searchForm.getProductContractCode());
        page.setQuery("companyContarctCode", searchForm.getCompanyContractCode());
        page.setQuery("productTypeId", searchForm.getProductTypeId() + "");
        page.setQuery("supplierName", searchForm.getSupplierName());
        page.setQuery("contractType", searchForm.getContractType());
        page.setQuery("templateType", searchForm.getTemplateType() + "");
        page.setQuery("userName", searchForm.getUserName());
        page.setQuery("status", searchForm.getStatus() + "");
        page.setQuery("startTime", searchForm.getStartTime());
        page.setQuery("endTime", searchForm.getEndTime());
    }

    /**
     * 权限判断
     * 
     * @author ChenHuajiang
     * @param user
     * @return true or false
     */
    private boolean isValidPermission(UserEntity user) {
        boolean isValid = false;
        log.info("检查当前用户权限");
        if (user == null) {
            return isValid;
        }
        int roleId = user.getRoleId();
        if (roleId == Constants.ROLE_PRODUCT_DIRECTOR
                || roleId == Constants.ROLE_COMPLIANCE_OFFICER
                || roleId == Constants.ROLE_PROCUREMENT_OFFICER
                || roleId == Constants.ROLE_DIRECTOR_OF_OPERATIONS
                || roleId == Constants.ROLE_AST_DIRECTOR_OF_OPERATIONS
                || roleId == Constants.ROLE_PROCUREMENT_COMMISSIONER
                || roleId == Constants.ROLE_GENERAL_MANAGER) {
            isValid = true;
        }
        return isValid;
    }

    /**
     * 设置用户的操作权限
     * 
     * @author ChenHuajiang
     * @param user
     * @return 可以操作的功能
     */
    private String setUserAction(UserEntity user) {
        log.info("设置用户的操作权限");
        if (user == null) {
            return null;
        }

        Integer roleId = user.getRoleId();
        // 采购专员 打印、生效
        if (roleId == Constants.ROLE_PROCUREMENT_COMMISSIONER) {
            return "printE";
        }

        String action = "query";
        // 总经理 查看
        if (roleId == Constants.ROLE_GENERAL_MANAGER) {
            return action;
        }
        // 产品总监 查看、评审、修改、删除、退合同
        if (roleId == Constants.ROLE_PRODUCT_DIRECTOR) {
            return action += "UDB";
        }
        // 采购主管、 法务专员、运营总监助理、 运营总监 查看、评审
        if (roleId == Constants.ROLE_PROCUREMENT_OFFICER
                || roleId == Constants.ROLE_COMPLIANCE_OFFICER
                || roleId == Constants.ROLE_AST_DIRECTOR_OF_OPERATIONS
                || roleId == Constants.ROLE_DIRECTOR_OF_OPERATIONS) {
            return action += "A";
        }
        return null;
    }

    /**
     * 添加采购合同 执行
     * 
     * @author hanrubing
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addBuyContractOfTransact(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},添加采购合同 执行【开始】", user.getId());

        BuyContractOfAddForm buyContractOfAddForm = (BuyContractOfAddForm) form;
        if (isTokenValid(request, true)) {
            resetToken(request);
        } else {
            request.setAttribute("redirect", request.getHeader("Referer"));
            return mapping.findForward(Constants.FAIL);
        }
        /** 判断是否产品总监 */
        if (user.getRoleId() != Constants.ROLE_PRODUCT_DIRECTOR) {
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&roleError=true");
            return mapping.findForward(Constants.FAIL);
        }
        /** 模板 */
        String templateType = buyContractOfAddForm.getBuyContractOfAddDto()
                .getTemplateType();
        /** 文件名 */
        String fname = null;
        if (!StringUtils.isBlank(templateType) && "1".equals(templateType)) {// 非模版
            /** 判断文件 */
            if (StringUtils.isBlank(request.getParameter("fileName"))) {
                request.setAttribute("redirect", request.getContextPath()
                        + "/addBuyContract.do?1=1" + "&fileExistError=true");
                return mapping.findForward(Constants.FAIL);
            }
            /** 判断文件大小 */
            if (buyContractOfAddForm.getBuyContractOfAddDto().getFile().getFileSize() > Constants.MAX_POST_SIZE) {
                log.info("User:{},上传文件失败:文件大与" + Constants.MAX_POST_SIZE, user.getId());
                request.setAttribute(
                        "redirect",
                        request.getHeader("Referer")
                                + "&fileMaxError=true&msg="
                                + java.net.URLEncoder.encode(
                                        Constants.MAX_POST_SIZE_ERR_MSG, "utf-8")
                                        .toString());
                return mapping.findForward(Constants.FAIL);
            }
            fname = loadFile(buyContractOfAddForm.getBuyContractOfAddDto().getFile(),
                    Constants.DIR_NAME, request);
            if (StringUtils.isBlank(fname)) {
                request.setAttribute("redirect", request.getContextPath()
                        + "/addBuyContract.do?1=1" + "&fileError=true");
                return mapping.findForward(Constants.FAIL);
            }
            buyContractOfAddForm.getBuyContractOfAddDto().setFileName(fname);
            /** 地点 */
            buyContractOfAddForm.getBuyContractOfAddDto().setPlace(null);
            buyContractOfAddForm.getBuyContractOfAddDto().setCheckLimit(null);
            buyContractOfAddForm.getBuyContractOfAddDto().setQuality(null);
            buyContractOfAddForm.getBuyContractOfAddDto().setProtectLimit(null);
        }
        if (!StringUtils.isBlank(templateType) && "0".equals(templateType)) {// 模板
            /** 验收期限 */
            if (StringUtils.isBlank(buyContractOfAddForm.getBuyContractOfAddDto()
                    .getCheckLimit())
                    || !this.isNum(buyContractOfAddForm.getBuyContractOfAddDto()
                            .getCheckLimit(), null)) {
                request.setAttribute("redirect", request.getContextPath()
                        + "/addBuyContract.do?1=1" + "&checkLimitError=true");
                return mapping.findForward(Constants.FAIL);
            }
            /** 保质期 */
            if (StringUtils.isBlank(buyContractOfAddForm.getBuyContractOfAddDto()
                    .getProtectLimit())) {
                request.setAttribute("redirect", request.getContextPath()
                        + "/addBuyContract.do?1=1" + "&protectLimitError=true");
                return mapping.findForward(Constants.FAIL);
            }
            /** 质量标准 */
            if (StringUtils.isBlank(buyContractOfAddForm.getBuyContractOfAddDto()
                    .getQuality())) {
                request.setAttribute("redirect", request.getContextPath()
                        + "/addBuyContract.do?1=1" + "&qualityError=true");
                return mapping.findForward(Constants.FAIL);
            }
        }
        /** 供货商 */
        if (StringUtils.isBlank(buyContractOfAddForm.getBuyContractOfAddDto()
                .getSupplierId())) {
            request.setAttribute("redirect", request.getContextPath()
                    + "/addBuyContract.do?1=1" + "&supplierIdError=true");
            return mapping.findForward(Constants.FAIL);
        }
        /** 联系人 */
        if (StringUtils.isBlank(buyContractOfAddForm.getBuyContractOfAddDto()
                .getSupplierLinkmanId())) {
            request.setAttribute("redirect", request.getContextPath()
                    + "/addBuyContract.do?1=1" + "&supplierLinkmanIdError=true");
            return mapping.findForward(Constants.FAIL);
        }
        /** 产品分类名称 */
        if (StringUtils.isBlank(buyContractOfAddForm.getBuyContractOfAddDto()
                .getProductTypeId())) {
            request.setAttribute("redirect", request.getContextPath()
                    + "/addBuyContract.do?1=1" + "&productTypeIdError=true");
            return mapping.findForward(Constants.FAIL);
        }
        /** 产品信息 */
        String productInfo = request.getParameter("returnValueToServer");
        org.json.JSONObject json = new JSONObject(productInfo);
        int sum = json.getInt("resultSum");
        if (sum == 0) {
            request.setAttribute("redirect", request.getContextPath()
                    + "/addBuyContract.do?1=1" + "&productInfoError=true");
            return mapping.findForward(Constants.FAIL);
        }
        /** 产品信息 里的数字与金额 */
        for (int i = 0; i < sum; i++) {
            JSONObject rows = json.getJSONArray("rows").getJSONObject(i);
            String buyCount = rows.getString("buyCount");
            String buyMoney = rows.getString("buyMoney");
            if (StringUtils.isBlank(buyCount) || !this.isNum(buyCount, null)) {
                request.setAttribute("redirect", request.getContextPath()
                        + "/addBuyContract.do?1=1" + "&buyCountError=true");
                return mapping.findForward(Constants.FAIL);
            }
            if (StringUtils.isBlank(buyMoney) || !this.isNum(buyMoney, ".")) {
                request.setAttribute("redirect", request.getContextPath()
                        + "/addBuyContract.do?1=1" + "&buyMoneyError=true");
                return mapping.findForward(Constants.FAIL);
            }
        }
        /** 付款方式 */
        if (StringUtils.isBlank(buyContractOfAddForm.getBuyContractOfAddDto()
                .getPaymentWay())) {
            request.setAttribute("redirect", request.getContextPath()
                    + "/addBuyContract.do?1=1" + "&paymentWayError=true");
            return mapping.findForward(Constants.FAIL);
        } else {
            if ("5".equals(buyContractOfAddForm.getBuyContractOfAddDto().getPaymentWay())) {
                if (StringUtils.isBlank(buyContractOfAddForm.getBuyContractOfAddDto()
                        .getArterm())) {
                    buyContractOfAddForm.getBuyContractOfAddDto().setArterm(null);
                } else if (!this.isNum(buyContractOfAddForm.getBuyContractOfAddDto()
                        .getArterm(), null)) {
                    request.setAttribute("redirect", request.getContextPath()
                            + "/addBuyContract.do?1=1" + "&artermError=true");
                    return mapping.findForward(Constants.FAIL);
                }

            } else {
                buyContractOfAddForm.getBuyContractOfAddDto().setArterm(null);

            }
        }
        /** 付款类型 */
        if (StringUtils.isBlank(buyContractOfAddForm.getBuyContractOfAddDto()
                .getPaymentType())) {
            request.setAttribute("redirect", request.getContextPath()
                    + "/addBuyContract.do?1=1" + "&paymentTypeError=true");
            return mapping.findForward(Constants.FAIL);
        } else {
            if ("0".equals(buyContractOfAddForm.getBuyContractOfAddDto().getPaymentType())) {
                if (!isNum(buyContractOfAddForm.getBuyContractOfAddDto()
                        .getContractPaymentTime(), null)) {
                    request.setAttribute("redirect", request.getContextPath()
                            + "/addBuyContract.do?1=1" + "&contarctPaymentTimeError=true");
                    return mapping.findForward(Constants.FAIL);
                }
                buyContractOfAddForm.getBuyContractOfAddDto().setPrepayMoney(null);
                buyContractOfAddForm.getBuyContractOfAddDto().setSendPaymentTime("0");
                if (StringUtils.isBlank(buyContractOfAddForm.getBuyContractOfAddDto()
                        .getContractPaymentTime())) {
                    buyContractOfAddForm.getBuyContractOfAddDto().setContractPaymentTime(
                            null);
                }
            }
            if ("1".equals(buyContractOfAddForm.getBuyContractOfAddDto().getPaymentType())) {
                if (!isNum(buyContractOfAddForm.getBuyContractOfAddDto()
                        .getContractPaymentTime(), null)) {
                    request.setAttribute("redirect", request.getContextPath()
                            + "/addBuyContract.do?1=1" + "&contarctPaymentTimeError=true");
                    return mapping.findForward(Constants.FAIL);
                }
                if (!isNum(
                        buyContractOfAddForm.getBuyContractOfAddDto().getPrepayMoney(),
                        ".")) {
                    request.setAttribute("redirect", request.getContextPath()
                            + "/addBuyContract.do?1=1" + "&prepayMoneyError=true");
                    return mapping.findForward(Constants.FAIL);
                }
                if (!isNum(buyContractOfAddForm.getBuyContractOfAddDto()
                        .getSendPaymentTime(), null)) {
                    request.setAttribute("redirect", request.getContextPath()
                            + "/addBuyContract.do?1=1" + "&sendPaymentTimeError=true");
                    return mapping.findForward(Constants.FAIL);
                }
                if (StringUtils.isBlank(buyContractOfAddForm.getBuyContractOfAddDto()
                        .getContractPaymentTime())) {
                    buyContractOfAddForm.getBuyContractOfAddDto().setContractPaymentTime(
                            null);
                }
                if (StringUtils.isBlank(buyContractOfAddForm.getBuyContractOfAddDto()
                        .getSendPaymentTime())) {
                    buyContractOfAddForm.getBuyContractOfAddDto()
                            .setSendPaymentTime("0");
                }
                if (StringUtils.isBlank(buyContractOfAddForm.getBuyContractOfAddDto()
                        .getPrepayMoney())) {
                    buyContractOfAddForm.getBuyContractOfAddDto().setPrepayMoney(null);
                }
            }
            if ("2".equals(buyContractOfAddForm.getBuyContractOfAddDto().getPaymentType())) {
                if (!isNum(buyContractOfAddForm.getBuyContractOfAddDto()
                        .getSendPaymentTime(), null)) {
                    request.setAttribute("redirect", request.getContextPath()
                            + "/addBuyContract.do?1=1" + "&sendPaymentTimeError=true");
                    return mapping.findForward(Constants.FAIL);
                }
                buyContractOfAddForm.getBuyContractOfAddDto()
                        .setContractPaymentTime(null);
                buyContractOfAddForm.getBuyContractOfAddDto().setPrepayMoney(null);
                if (StringUtils.isBlank(buyContractOfAddForm.getBuyContractOfAddDto()
                        .getSendPaymentTime())) {
                    buyContractOfAddForm.getBuyContractOfAddDto()
                            .setSendPaymentTime("0");
                }
            }
        }
        /** 供货日期 */
        if (StringUtils.isBlank(buyContractOfAddForm.getBuyContractOfAddDto()
                .getRequestDate())) {
            request.setAttribute("redirect", request.getContextPath()
                    + "/addBuyContract.do?1=1" + "&requestDateError=true");
            return mapping.findForward(Constants.FAIL);
        }
        /** 提交类型 */
        String submitType = request.getParameter("submitType");

        /** userid */
        buyContractOfAddForm.getBuyContractOfAddDto().setUserId(user.getId());
        /** username */
        buyContractOfAddForm.getBuyContractOfAddDto().setUserName(user.getName());
        /** dateTime */
        buyContractOfAddForm.getBuyContractOfAddDto().setDateTime(sdf.format(new Date()));
        /** id */
        buyContractOfAddForm.getBuyContractOfAddDto().setId(
                common.getSerialNumber("CG", "buy_contract"));
        /** 执行添加 */
        int isSuccess = buyService.addBuyContractOfTransact(
                buyContractOfAddForm.getBuyContractOfAddDto(), productInfo);
        log.info("User:{},添加采购合同 执行【结束】", user.getId());
        if (isSuccess == 0) {
            request.setAttribute("redirect", request.getContextPath()
                    + "/addBuyContract.do?1=1" + "&addError=true");
            return mapping.findForward(Constants.FAIL);
        }
        if ("save".equals(submitType)) {
            return mapping.findForward(Constants.SUCCESS);
        } else {
            request.setAttribute("buyContractId", buyContractOfAddForm
                    .getBuyContractOfAddDto().getId());
            return mapping.findForward("sendInfo");
        }
    }

    /**
     * 修改采购合同 执行
     * 
     * @author hanrubing
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward modifyBuyContractOfTransact(ActionMapping mapping,
            ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},修改采购合同 执行【开始】", user.getId());

        BuyContractOfAddForm buyContractOfAddForm = (BuyContractOfAddForm) form;
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("table", "buy_contract");
        map.put("id", buyContractOfAddForm.getBuyContractOfAddDto().getId());
        int status = buyContractService.getStrutsOfAll(map);
        /** 判断是否待未提交 */
        if (status == 2 || status == 4 || status == 6 || status == 8 || status == 10
                || status == 11 || status == 13) {
            request.setAttribute("redirect", request.getContextPath()
                    + "/modifyBuyContractOfShow.do?id="
                    + buyContractOfAddForm.getBuyContractOfAddDto().getId() + ""
                    + "&statusError=true");
            return mapping.findForward(Constants.FAIL);
        }
        /** 判断是否产品总监 */
        if (user.getRoleId() != Constants.ROLE_PRODUCT_DIRECTOR) {
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&roleError=true");
            return mapping.findForward(Constants.FAIL);
        }
        /** 模板 */
        String templateType = buyContractOfAddForm.getBuyContractOfAddDto()
                .getTemplateType();
        /** 文件名 */
        String fname = null;
        if (!StringUtils.isBlank(templateType) && "1".equals(templateType)) {// 非模版
            /** 判断文件 */
            if (StringUtils.isBlank(request.getParameter("fileName"))) {
                request.setAttribute("redirect", request.getContextPath()
                        + "/modifyBuyContractOfShow.do?id="
                        + buyContractOfAddForm.getBuyContractOfAddDto().getId() + ""
                        + "&fileExistError=true");
                return mapping.findForward(Constants.FAIL);
            }
            if (!request.getParameter("fileName").toString()
                    .equals(request.getParameter("oldFile").toString())) {
                /** 判断文件大小 */
                if (buyContractOfAddForm.getBuyContractOfAddDto().getFile().getFileSize() > Constants.MAX_POST_SIZE) {
                    log.info("User:{},上传文件失败:文件大与" + Constants.MAX_POST_SIZE,
                            user.getId());
                    request.setAttribute(
                            "redirect",
                            request.getHeader("Referer")
                                    + "&fileMaxError=true&msg="
                                    + java.net.URLEncoder.encode(
                                            Constants.MAX_POST_SIZE_ERR_MSG, "utf-8")
                                            .toString());
                    return mapping.findForward(Constants.FAIL);
                }
                fname = loadFile(buyContractOfAddForm.getBuyContractOfAddDto().getFile(),
                        Constants.DIR_NAME, request);
            } else {
                fname = request.getParameter("fileName");
            }
            if (StringUtils.isBlank(fname)) {
                request.setAttribute("redirect", request.getContextPath()
                        + "/modifyBuyContractOfShow.do?id="
                        + buyContractOfAddForm.getBuyContractOfAddDto().getId() + ""
                        + "&fileError=true");
                return mapping.findForward(Constants.FAIL);
            }
            buyContractOfAddForm.getBuyContractOfAddDto().setFileName(fname);
            /** 地点 */
            buyContractOfAddForm.getBuyContractOfAddDto().setPlace(null);
            buyContractOfAddForm.getBuyContractOfAddDto().setCheckLimit(null);
            buyContractOfAddForm.getBuyContractOfAddDto().setQuality(null);
            buyContractOfAddForm.getBuyContractOfAddDto().setProtectLimit(null);
        }
        if (!StringUtils.isBlank(templateType) && "0".equals(templateType)) {// 模板
            /** 验收期限 */
            if (StringUtils.isBlank(buyContractOfAddForm.getBuyContractOfAddDto()
                    .getCheckLimit())
                    || !this.isNum(buyContractOfAddForm.getBuyContractOfAddDto()
                            .getCheckLimit(), null)) {
                request.setAttribute("redirect", request.getContextPath()
                        + "/modifyBuyContractOfShow.do?id="
                        + buyContractOfAddForm.getBuyContractOfAddDto().getId() + ""
                        + "&checkLimitError=true");
                return mapping.findForward(Constants.FAIL);
            }
            /** 保质期 */
            if (StringUtils.isBlank(buyContractOfAddForm.getBuyContractOfAddDto()
                    .getProtectLimit())) {
                request.setAttribute("redirect", request.getContextPath()
                        + "/modifyBuyContractOfShow.do?id="
                        + buyContractOfAddForm.getBuyContractOfAddDto().getId() + ""
                        + "&protectLimitError=true");
                return mapping.findForward(Constants.FAIL);
            }
            /** 质量标准 */
            if (StringUtils.isBlank(buyContractOfAddForm.getBuyContractOfAddDto()
                    .getQuality())) {
                request.setAttribute("redirect", request.getContextPath()
                        + "/modifyBuyContractOfShow.do?id="
                        + buyContractOfAddForm.getBuyContractOfAddDto().getId() + ""
                        + "&qualityError=true");
                return mapping.findForward(Constants.FAIL);
            }
        }
        /** 供货商 */
        if (StringUtils.isBlank(buyContractOfAddForm.getBuyContractOfAddDto()
                .getSupplierId())) {
            request.setAttribute("redirect", request.getContextPath()
                    + "/modifyBuyContractOfShow.do?id="
                    + buyContractOfAddForm.getBuyContractOfAddDto().getId() + ""
                    + "&supplierIdError=true");
            return mapping.findForward(Constants.FAIL);
        }
        /** 联系人 */
        if (StringUtils.isBlank(buyContractOfAddForm.getBuyContractOfAddDto()
                .getSupplierLinkmanId())) {
            request.setAttribute("redirect", request.getContextPath()
                    + "/modifyBuyContractOfShow.do?id="
                    + buyContractOfAddForm.getBuyContractOfAddDto().getId() + ""
                    + "&supplierLinkmanIdError=true");
            return mapping.findForward(Constants.FAIL);
        }
        /** 产品分类名称 */
        if (StringUtils.isBlank(buyContractOfAddForm.getBuyContractOfAddDto()
                .getProductTypeId())) {
            request.setAttribute("redirect", request.getContextPath()
                    + "/modifyBuyContractOfShow.do?id="
                    + buyContractOfAddForm.getBuyContractOfAddDto().getId() + ""
                    + "&productTypeIdError=true");
            return mapping.findForward(Constants.FAIL);
        }
        /** 产品信息 */
        String productInfo = request.getParameter("returnValueToServer");
        org.json.JSONObject json = new JSONObject(productInfo);
        int sum = json.getInt("resultSum");
        if (sum == 0) {
            request.setAttribute("redirect", request.getContextPath()
                    + "/modifyBuyContractOfShow.do?id="
                    + buyContractOfAddForm.getBuyContractOfAddDto().getId() + ""
                    + "&productInfoError=true");
            return mapping.findForward(Constants.FAIL);
        }
        /** 产品信息 里的数字与金额 */
        for (int i = 0; i < sum; i++) {
            JSONObject rows = json.getJSONArray("rows").getJSONObject(i);
            String buyCount = rows.getString("buyCount");
            String buyMoney = rows.getString("buyMoney");
            if (StringUtils.isBlank(buyCount) || !this.isNum(buyCount, null)) {
                request.setAttribute("redirect", request.getContextPath()
                        + "/modifyBuyContractOfShow.do?id="
                        + buyContractOfAddForm.getBuyContractOfAddDto().getId() + ""
                        + "&buyCountError=true");
                return mapping.findForward(Constants.FAIL);
            }
            if (StringUtils.isBlank(buyMoney) || !this.isNum(buyMoney, ".")) {
                request.setAttribute("redirect", request.getContextPath()
                        + "/modifyBuyContractOfShow.do?id="
                        + buyContractOfAddForm.getBuyContractOfAddDto().getId() + ""
                        + "&buyMoneyError=true");
                return mapping.findForward(Constants.FAIL);
            }
        }
        /** 付款方式 */
        if (StringUtils.isBlank(buyContractOfAddForm.getBuyContractOfAddDto()
                .getPaymentWay())) {
            request.setAttribute("redirect", request.getContextPath()
                    + "/modifyBuyContractOfShow.do?id="
                    + buyContractOfAddForm.getBuyContractOfAddDto().getId() + ""
                    + "&paymentWayError=true");
            return mapping.findForward(Constants.FAIL);
        } else {
            if ("5".equals(buyContractOfAddForm.getBuyContractOfAddDto().getPaymentWay())) {
                if (StringUtils.isBlank(buyContractOfAddForm.getBuyContractOfAddDto()
                        .getArterm())) {
                    buyContractOfAddForm.getBuyContractOfAddDto().setArterm(null);
                } else if (!this.isNum(buyContractOfAddForm.getBuyContractOfAddDto()
                        .getArterm(), null)) {
                    request.setAttribute("redirect", request.getContextPath()
                            + "/addBuyContract.do?1=1" + "&artermError=true");
                    return mapping.findForward(Constants.FAIL);
                }
            } else {
                buyContractOfAddForm.getBuyContractOfAddDto().setArterm(null);

            }
        }
        /** 付款类型 */
        if (StringUtils.isBlank(buyContractOfAddForm.getBuyContractOfAddDto()
                .getPaymentType())) {
            request.setAttribute("redirect", request.getContextPath()
                    + "/modifyBuyContractOfShow.do?id="
                    + buyContractOfAddForm.getBuyContractOfAddDto().getId() + ""
                    + "&paymentTypeError=true");
            return mapping.findForward(Constants.FAIL);
        } else {
            if ("0".equals(buyContractOfAddForm.getBuyContractOfAddDto().getPaymentType())) {
                if (!isNum(buyContractOfAddForm.getBuyContractOfAddDto()
                        .getContractPaymentTime(), null)) {
                    request.setAttribute("redirect", request.getContextPath()
                            + "/modifyBuyContractOfShow.do?id="
                            + buyContractOfAddForm.getBuyContractOfAddDto().getId() + ""
                            + "&contarctPaymentTimeError=true");
                    return mapping.findForward(Constants.FAIL);
                }
                buyContractOfAddForm.getBuyContractOfAddDto().setPrepayMoney(null);
                buyContractOfAddForm.getBuyContractOfAddDto().setSendPaymentTime("0");
                if (StringUtils.isBlank(buyContractOfAddForm.getBuyContractOfAddDto()
                        .getContractPaymentTime())) {
                    buyContractOfAddForm.getBuyContractOfAddDto().setContractPaymentTime(
                            null);
                }
            }
            if ("1".equals(buyContractOfAddForm.getBuyContractOfAddDto().getPaymentType())) {
                if (!isNum(buyContractOfAddForm.getBuyContractOfAddDto()
                        .getContractPaymentTime(), null)) {
                    request.setAttribute("redirect", request.getContextPath()
                            + "/modifyBuyContractOfShow.do?id="
                            + buyContractOfAddForm.getBuyContractOfAddDto().getId() + ""
                            + "&contarctPaymentTimeError=true");
                    return mapping.findForward(Constants.FAIL);
                }
                if (!isNum(
                        buyContractOfAddForm.getBuyContractOfAddDto().getPrepayMoney(),
                        ".")) {
                    request.setAttribute("redirect", request.getContextPath()
                            + "/modifyBuyContractOfShow.do?id="
                            + buyContractOfAddForm.getBuyContractOfAddDto().getId() + ""
                            + "&prepayMoneyError=true");
                    return mapping.findForward(Constants.FAIL);
                }
                if (!isNum(buyContractOfAddForm.getBuyContractOfAddDto()
                        .getSendPaymentTime(), null)) {
                    request.setAttribute("redirect", request.getContextPath()
                            + "/modifyBuyContractOfShow.do?id="
                            + buyContractOfAddForm.getBuyContractOfAddDto().getId() + ""
                            + "&sendPaymentTimeError=true");
                    return mapping.findForward(Constants.FAIL);
                }
                if (StringUtils.isBlank(buyContractOfAddForm.getBuyContractOfAddDto()
                        .getContractPaymentTime())) {
                    buyContractOfAddForm.getBuyContractOfAddDto().setContractPaymentTime(
                            null);
                }
                if (StringUtils.isBlank(buyContractOfAddForm.getBuyContractOfAddDto()
                        .getSendPaymentTime())) {
                    buyContractOfAddForm.getBuyContractOfAddDto()
                            .setSendPaymentTime("0");
                }
                if (StringUtils.isBlank(buyContractOfAddForm.getBuyContractOfAddDto()
                        .getPrepayMoney())) {
                    buyContractOfAddForm.getBuyContractOfAddDto().setPrepayMoney(null);
                }
            }
            if ("2".equals(buyContractOfAddForm.getBuyContractOfAddDto().getPaymentType())) {
                if (!isNum(buyContractOfAddForm.getBuyContractOfAddDto()
                        .getSendPaymentTime(), null)) {
                    request.setAttribute("redirect", request.getContextPath()
                            + "/modifyBuyContractOfShow.do?id="
                            + buyContractOfAddForm.getBuyContractOfAddDto().getId() + ""
                            + "&sendPaymentTimeError=true");
                    return mapping.findForward(Constants.FAIL);
                }
                buyContractOfAddForm.getBuyContractOfAddDto()
                        .setContractPaymentTime(null);
                buyContractOfAddForm.getBuyContractOfAddDto().setPrepayMoney(null);
                if (StringUtils.isBlank(buyContractOfAddForm.getBuyContractOfAddDto()
                        .getSendPaymentTime())) {
                    buyContractOfAddForm.getBuyContractOfAddDto()
                            .setSendPaymentTime("0");
                }
            }
        }
        /** 供货日期 */
        if (StringUtils.isBlank(buyContractOfAddForm.getBuyContractOfAddDto()
                .getRequestDate())) {
            request.setAttribute("redirect", request.getContextPath()
                    + "/modifyBuyContractOfShow.do?id="
                    + buyContractOfAddForm.getBuyContractOfAddDto().getId() + ""
                    + "&requestDateError=true");
            return mapping.findForward(Constants.FAIL);
        }
        /** 提交类型 */
        String submitType = request.getParameter("submitType");
        /** dateTime */
        buyContractOfAddForm.getBuyContractOfAddDto().setDateTime(sdf.format(new Date()));
        int isSuccess = 0;
        if ("submit".equals(submitType)) {
            buyContractOfAddForm.getBuyContractOfAddDto().setSubmitType("submit");
            if ("1".equals(templateType)) {
                /** 法务专员 id */
                WorkReceiverDto workReceiverDto = common.getUserIdByCondition(null, null,
                        null, Constants.ROLE_COMPLIANCE_OFFICER);
                buyContractOfAddForm.getBuyContractOfAddDto().setStatus("2");
                buyContractOfAddForm.getBuyContractOfAddDto().setLegalId(
                        workReceiverDto
                                .getUserIdByRoleId(Constants.ROLE_COMPLIANCE_OFFICER));
            }
            if ("0".equals(templateType)) {
                /** 采购主管 id */
                WorkReceiverDto workReceiverDto = null;
                String buyManId = null;
                try {
                    workReceiverDto = common.getUserIdByCondition(Integer
                            .parseInt(buyContractOfAddForm.getBuyContractOfAddDto()
                                    .getProductTypeId()), null, null,
                            Constants.ROLE_PROCUREMENT_OFFICER);
                    buyManId = workReceiverDto
                            .getUserIdByRoleId(Constants.ROLE_PROCUREMENT_OFFICER);
                } catch (Exception e) {
                    log.error("无采购主管！", e);
                }
                if (buyManId == null) {
                    request.setAttribute("redirect", request.getContextPath()
                            + "/modifyBuyContractOfShow.do?id="
                            + buyContractOfAddForm.getBuyContractOfAddDto().getId() + ""
                            + "&notFindError=true");
                    return mapping.findForward(Constants.FAIL);
                }
                buyContractOfAddForm.getBuyContractOfAddDto().setStatus("4");
                buyContractOfAddForm.getBuyContractOfAddDto().setBuyManId(buyManId);
            }
        } else {
            buyContractOfAddForm.getBuyContractOfAddDto().setStatus("1");
        }
        /** 执行修改 */
        isSuccess = buyService.modifyBuyContractOfTransact(
                buyContractOfAddForm.getBuyContractOfAddDto(), productInfo);
        log.info("User:{},修改采购合同 执行【结束】", user.getId());
        if (isSuccess == 0) {
            request.setAttribute("redirect", request.getContextPath()
                    + "/modifyBuyContractOfShow.do?id="
                    + buyContractOfAddForm.getBuyContractOfAddDto().getId() + ""
                    + "&addError=true");
            return mapping.findForward(Constants.FAIL);
        }

        if ("save".equals(submitType) || "submit".equals(submitType)) {
            return mapping.findForward(Constants.SUCCESS);
        } else {
            request.setAttribute("buyContractId", buyContractOfAddForm
                    .getBuyContractOfAddDto().getId());
            return mapping.findForward("sendInfo");
        }
    }

    /**
     * 获取联系人by供应商Id
     * 
     * @author hanrubing
     */
    @SuppressWarnings("unchecked")
    public ActionForward getLinkman(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},获取联系人by供应商Id【开始】", user.getId());

        String supplierId = request.getParameter("id");// 供应商Id
        buyContractService = (IBuyContractService) getBean("buyContractServiceImpl");
        List list = null;
        list = buyContractService.getSupplierLinkmanBySupplierId(supplierId);
        response.setCharacterEncoding("UTF-8");
        String returnValue = "{success:true,resultCount:" + list.size() + ",rows:[";
        for (int i = 0; i < list.size(); i++) {
            SupplierLinkmanEntity supplierLinkmanEntity = (SupplierLinkmanEntity) list
                    .get(i);
            returnValue += "{id:" + supplierLinkmanEntity.getId() + ",";
            returnValue += "name:\"" + supplierLinkmanEntity.getName() + "\",";
            returnValue += "tel:\"" + supplierLinkmanEntity.getTel() + "\",";
            returnValue += "fax:\"" + supplierLinkmanEntity.getFax() + "\"},";
        }

        if (list.size() != 0 && list != null) {
            returnValue = returnValue.substring(0, returnValue.lastIndexOf(","));
        }
        returnValue += "]}";
        PrintWriter writer = response.getWriter();
        writer.write(returnValue);
        writer.close();
        log.info("User:{},获取联系人by供应商Id【结束】", user.getId());
        return null;
    }

    /**
     * 添加收获地址选择
     * 
     * @author hanrubing
     */
    public ActionForward addReceiveOfBuyContract(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},添加收获地址选择【开始】", user.getId());

        String returnVlaue = request.getParameter("returnVlaue");// json数据
        response.setCharacterEncoding("UTF-8");
        int isSuccess = buyService.addReceiveOfBuyContract(returnVlaue);

        PrintWriter writer = response.getWriter();
        if (isSuccess == 0) {
            writer.write("false");
        } else {
            writer.write("true");
        }
        writer.close();
        log.info("User:{},添加收获地址选择【结束】", user.getId());
        return null;
    }

    /**
     * 删除收获地址选择
     * 
     * @author hanrubing
     */
    public ActionForward deleteReceiveOfBuyContract(ActionMapping mapping,
            ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},删除收获地址【开始】", user.getId());

        String returnVlaue = request.getParameter("returnVlaue");// 删除数据
        response.setCharacterEncoding("UTF-8");
        int isSuccess = buyService.deleteReceiveOfBuyContract(returnVlaue);
        PrintWriter writer = response.getWriter();
        if (isSuccess == 0) {
            writer.write("false");
        } else {
            writer.write("true");
        }
        writer.close();
        log.info("User:{},删除收获地址【结束】", user.getId());
        return null;
    }

    /**
     * 采购合同 收货信息修列表
     * 
     * @author hanrubing
     */
    public ActionForward getReceiceInfoModify(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},采购合同 收货信息修列表【开始】", user.getId());

        String buyContractId = (String) request.getAttribute("buyContractId");
        if (StringUtils.isBlank(buyContractId)) {
            buyContractId = request.getParameter("buyContractId");
            request.setAttribute("typeOflook", "true");
        }
        String jsonDate = buyContractService.getReceiceInfoModify(buyContractId);// 页面数据
        request.setAttribute("buyContractId", buyContractId);
        request.setAttribute("jsonDate", jsonDate);
        log.info("User:{},采购合同 收货信息修列表【结束】", user.getId());
        request.setAttribute("type", request.getParameter("typeOflook"));
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 修改采购合同 显示
     * 
     * @author hanrubing
     */
    @SuppressWarnings("unchecked")
    public ActionForward modifyBuyContractOfShow(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},修改采购合同 显示【开始】", user.getId());

        String id = request.getParameter("id");// 采购合同id
        List<ProductTypeInfoDto> list = null;
        list = (List<ProductTypeInfoDto>) buyContractService.getProdTypeDeptList(user
                .getId());

        request.setAttribute("productList", list);
        BuyContractOfAddForm buyContractOfAddForm = new BuyContractOfAddForm();

        BuyContractOfAddDto buyContractOfAddDto = (BuyContractOfAddDto) buyService
                .modifyBuyContractOfShow(id);
        buyContractOfAddForm.setBuyContractOfAddDto(buyContractOfAddDto);
        /** 产品信息列表 */
        if (StringUtils.isBlank(buyContractOfAddForm.getBuyContractOfAddDto().getId())) {
            request.setAttribute("redirect", request.getContextPath()
                    + "/modifyBuyContractOfShow.do?id="
                    + buyContractOfAddForm.getBuyContractOfAddDto().getId() + ""
                    + "&Error=true");
            return mapping.findForward(Constants.FAIL);

        }
        List<BuyContractProductInfoDto> productInfoList = buyContractService
                .getBuyContractProductInfoOfModify(buyContractOfAddForm
                        .getBuyContractOfAddDto().getId());
        request.setAttribute("buyContractProducts", productInfoList);
        /** 收货信息 */
        request.setAttribute("buyContractReceivings", buyContractService
                .getReceiceInfoOfShow(buyContractOfAddForm.getBuyContractOfAddDto()
                        .getId()));
        /** 收货信息总数 */
        String jsonOfSum = buyContractService.getReceiceInfoCount(productInfoList, id);
        request.setAttribute("jsonOfSum", jsonOfSum);
        buyContractOfAddForm.getBuyContractOfAddDto().setPlace(Constants.SELL_PLACE);
        /** 采购合同信息 */
        request.setAttribute("buyContractOfAddForm", buyContractOfAddForm);
        /** 联系人列表 */
        request.setAttribute("linkMan", buyContractService
                .getSupplierLinkmanBySupplierId(buyContractOfAddForm
                        .getBuyContractOfAddDto().getSupplierId()));
        /** 联系人 */
        buyContractService = (IBuyContractService) getBean("buyContractServiceImpl");
        List linkmanList = null;
        linkmanList = buyContractService
                .getSupplierLinkmanBySupplierId(buyContractOfAddForm
                        .getBuyContractOfAddDto().getSupplierId());
        response.setCharacterEncoding("UTF-8");
        String returnValue = "{success:true,resultCount:" + linkmanList.size()
                + ",rows:[";
        for (int i = 0; i < linkmanList.size(); i++) {
            SupplierLinkmanEntity supplierLinkmanEntity = (SupplierLinkmanEntity) linkmanList
                    .get(i);
            returnValue += "{id:" + supplierLinkmanEntity.getId() + ",";
            returnValue += "name:\"" + supplierLinkmanEntity.getName() + "\",";
            returnValue += "tel:\"" + supplierLinkmanEntity.getTel() + "\",";
            returnValue += "fax:\"" + supplierLinkmanEntity.getFax() + "\"},";
        }

        if (linkmanList.size() != 0 && linkmanList != null) {
            returnValue = returnValue.substring(0, returnValue.lastIndexOf(","));
        }
        returnValue += "]}";
        request.setAttribute("jsonDate", returnValue);
        log.info("User:{},修改采购合同 显示【结束】", user.getId());
        saveToken(request);
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 合同确认 显示
     * 
     * @author hanrubing
     */
    public ActionForward getBuyContractDecideOfShow(ActionMapping mapping,
            ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},合同确认 显示【开始】", user.getId());

        buyContractService = (IBuyContractService) getBean("buyContractServiceImpl");
        BuyContractSelectDto buyContractSelectDto = null;
        List<BuyContractProductInfoDto> buyContractProducts = null;
        String buyContractId = request.getParameter("buyContractId");

        // 得到采购合同的基本信息
        buyContractSelectDto = buyContractService.getBuyContractById(buyContractId);
        // 得到采购合同的产品信息
        buyContractProducts = buyContractService
                .getBuyContractProductInfoById(buyContractId);
        request.setAttribute("buyContractSelectDto", buyContractSelectDto);
        request.setAttribute("buyContractReceivings",
                buyContractService.getReceiceInfoOfShow(buyContractId));// 得到采购合同的收货信息
        request.setAttribute("buyContractProducts", buyContractProducts);
        request.setAttribute("id", buyContractId);
        log.info("User:{},合同确认 显示【结束】", user.getId());
        saveToken(request);
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 合同确认 执行
     * 
     * @author hanrubing
     */
    public ActionForward getBuyContractDecideOfTransact(ActionMapping mapping,
            ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},合同确认 执行【开始】", user.getId());
        if (isTokenValid(request, true)) {
            resetToken(request);
        } else {
            request.setAttribute("redirect", request.getHeader("Referer"));
            return mapping.findForward(Constants.FAIL);
        }
        buyContractService = (IBuyContractService) getBean("buyContractServiceImpl");
        BuyContractOfAddForm buyContractOfAddForm = (BuyContractOfAddForm) form;
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("table", "buy_contract");
        map.put("id", buyContractOfAddForm.getBuyContractOfAddDto().getId());
        int status = buyContractService.getStrutsOfAll(map);
        /** 判断是否待确认 */
        if (status != 11) {
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&statusError=true");
            return mapping.findForward(Constants.FAIL);
        }
        /** 判断是否为采购专员 */
        if (user.getRoleId() != Constants.ROLE_PROCUREMENT_COMMISSIONER) {
            return mapping.findForward(Constants.FAIL);
        }
        /** 是否生效 */
        String type = request.getParameter("decideType");
        if (StringUtils.isEmpty(type)) {
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&decideError=true");
            return mapping.findForward(Constants.FAIL);
        }

        if ("true".equals(type)) {
            buyContractOfAddForm.getBuyContractOfAddDto().setStatus("13");
            /** 公司合同号 */
            String companyContarctCode = buyContractOfAddForm.getBuyContractOfAddDto()
                    .getCompanyContractCode();
            /** 判断公司合同号是否为空 */
            if (StringUtils.isBlank(companyContarctCode)) {
                request.setAttribute("redirect", request.getHeader("Referer")
                        + "&codeError=true");
                return mapping.findForward(Constants.FAIL);
            }
            companyContarctCode = companyContarctCode.trim();
            buyContractOfAddForm.getBuyContractOfAddDto().setCompanyContractCode(
                    companyContarctCode);
            /** 判断公司合同号是否存在 */
            String isExist = buyContractService
                    .getBuyContractIsExist(buyContractOfAddForm.getBuyContractOfAddDto());
            if (!"0".equals(isExist)) {
                request.setAttribute("redirect", request.getHeader("Referer")
                        + "&codeExistError=true");
                return mapping.findForward(Constants.FAIL);
            }
        } else {
            buyContractOfAddForm.getBuyContractOfAddDto().setStatus("12");
            buyContractOfAddForm.getBuyContractOfAddDto().setCompanyContractCode("");
        }
        buyContractService.updateBuyContractOfDecide(buyContractOfAddForm
                .getBuyContractOfAddDto());
        log.info("User:{},合同确认 执行【结束】", user.getId());
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 采购合同文件下载
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward downloadBuyContractFile(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},采购合同文件下载【开始】", user.getId());

        // String buyContractId = request.getParameter("buyContractId");
        String fileName = request.getParameter("fileName");
        log.info("fileName:" + fileName);

        if (StringUtils.isBlank(fileName)) {
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&notExistError=true");
            return mapping.findForward(Constants.FAIL);
        }

        // 实现文件的下载
        try {
            downFile(fileName, Constants.DIR_NAME,
                    Constants.DOWNLOAD_FILE_NAME_PREFIX_BUY, request, response);
        } catch (Exception e) {
            log.error("User:{},采购合同文件下载【失败】:{}", user.getId(), e);
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&downLoadError=true");
            return mapping.findForward(Constants.FAIL);
        }
        log.info("User:{},采购合同文件下载【结束】", user.getId());
        return null;
    }

    /**
     * 查看评审表
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward lookBuyContractCommentTable(ActionMapping mapping,
            ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},查看评审表", user.getId());
        /** 采购合同id */
        String id = request.getParameter("buyContractId");
        /** 采购合同信息 */
        request.setAttribute("buyContractOfAddDto",
                buyContractService.getBuyContractById(id));
        /** 采购合同明细信息 */
        request.setAttribute("prodInfoList",
                buyContractService.getBuyContractProductInfoOfModify(id));

        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 打印评审表
     * 
     * @author hanrubing
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward printBuyContractCommentTable(ActionMapping mapping,
            ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},打印评审表", user.getId());
        /** 采购合同id */
        String id = request.getParameter("id");
        /** 改变状态 */
        int isSuccess = buyContractService.modifyCommentTableOfStatus(id, user);
        if (isSuccess == 0) {
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&modifyError=true");
            return mapping.findForward(Constants.FAIL);
        }
        return null;
    }

    /**
     * 删除采购合同
     * 
     * @author hanrubing
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteBuyContract(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},删除采购合同", user.getId());
        /** 采购合同id */
        String id = request.getParameter("id");

        /** 判断是否为产品总监 */
        if (user.getRoleId() != Constants.ROLE_PRODUCT_DIRECTOR) {
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&deleteError=true");
            return mapping.findForward(Constants.FAIL);
        }
        /** 改变状态 */
        int isSuccess = buyContractService.deleteBuyContract(id);
        if (isSuccess == 0) {
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&delError=true");
            return mapping.findForward(Constants.FAIL);
        }
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 显示打印多个发货地址
     * 
     * @author hanrubing
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward printBuyContractOfSendAddress(ActionMapping mapping,
            ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},显示打印发货地址", user.getId());
        /** 采购合同id */
        String id = request.getParameter("id");
        String productContractCode = request.getParameter("pc");
        String companyContractCode = request.getParameter("cc");
        String jsonDate = buyContractService.getReceiceInfoModify(id);// 页面数据
        request.setAttribute("productContractCode", productContractCode);
        request.setAttribute("companyContractCode", companyContractCode);
        request.setAttribute("jsonDate", jsonDate);
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 判断是否位数字
     * 
     * @param 要判断的字符串val
     * @param dot
     *            "." 没有传空 如果有’.’只能出现一次 0.11 和.11都正确
     * @author hanrubing
     */
    private boolean isNum(String val, String dot) {
        return ".".equals(dot) ? Pattern.compile("[\\d]*[.]?[\\d]*").matcher(val)
                .matches() : Pattern.compile("[\\d]*").matcher(val).matches();
    }
}
