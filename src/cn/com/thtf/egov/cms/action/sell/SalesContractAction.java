/**
 * ClassName  SalesContractAction
 *
 * History
 * Create User: Administrator
 * Create Date: 2010-4-16
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.action.sell;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.NewBaseAction;
import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.constant.Arith;
import cn.com.thtf.egov.cms.constant.Constants;
import cn.com.thtf.egov.cms.dto.AssessManDto;
import cn.com.thtf.egov.cms.dto.CompanyInfoDto;
import cn.com.thtf.egov.cms.dto.ContractCalculateValueDto;
import cn.com.thtf.egov.cms.dto.ContractJudgeRole;
import cn.com.thtf.egov.cms.dto.ProductTypeInfoDto;
import cn.com.thtf.egov.cms.dto.SalesConCustomerDto;
import cn.com.thtf.egov.cms.dto.SalesConProductDto;
import cn.com.thtf.egov.cms.dto.SalesContractInfoDto;
import cn.com.thtf.egov.cms.dto.SalesContractProductDetailDto;
import cn.com.thtf.egov.cms.dto.WorkReceiverDto;
import cn.com.thtf.egov.cms.entity.CustomerLinkmanEntity;
import cn.com.thtf.egov.cms.entity.DemandEntity;
import cn.com.thtf.egov.cms.entity.RoleMEntity;
import cn.com.thtf.egov.cms.entity.SellContractDetailEntity;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.entity.WorkEntity;
import cn.com.thtf.egov.cms.form.ProductSearchForm;
import cn.com.thtf.egov.cms.form.SalesContractForm;
import cn.com.thtf.egov.cms.iservice.common.ICommonService;
import cn.com.thtf.egov.cms.iservice.sell.ISalesContractManagementService;
import cn.com.thtf.egov.cms.util.Container;
import cn.com.thtf.egov.cms.util.Util;

/**
 * 用于显示销售合同添加页面
 * 
 * @author Administrator
 */

public class SalesContractAction extends NewBaseAction {
    private static Logger log = LoggerFactory.getLogger(SalesContractAction.class);

    /**
     * 检索公司合同编号是否存在
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public ActionForward checkCompanycontractcode(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("检索公司合同编号是否存在");

        String code = request.getParameter("checkCode");

        // 获取spring的service
        ISalesContractManagementService salesContractService = (ISalesContractManagementService) this
                .getBean("salesContractManagementServiceImp");

        String dbcode = salesContractService.getCompanycontractcode(code);

        // 将json串写回客户端
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();

        if (StringUtils.isNotEmpty(dbcode)) {
            writer.write("0");
        } else {
            writer.write("1");
        }

        writer.close();
        return null;
    }
    
    /**
     * 新建销售合同 页面显示
     * 
     */
    @SuppressWarnings("unchecked")
    public ActionForward createSellContract(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 获取用户登陆Id
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},进入新建销售合同页面【开始】", user.getId());
        /* 防止重复提交 */
        this.saveToken(request);

        // 获取spring的service
        ISalesContractManagementService salesContractService = (ISalesContractManagementService) this
                .getBean("salesContractManagementServiceImp");

        // 从常量类里取出合同信息
        SalesContractInfoDto sellContract = new SalesContractInfoDto();
        sellContract.setPlace(Constants.SELL_PLACE);
        sellContract.setProtectLimit(Constants.SELL_PROTECT);
        sellContract.setCheckLimit(Constants.SELL_CHECK);
        sellContract.setBreach(Constants.SELL_BREAK);
        sellContract.setLawsuit(Constants.SELL_LAWSUIT);
        // 从session获取供方信息
        sellContract.setUserName(user.getName());
        sellContract.setUserTel(user.getTel());
        // 获取销售经理所属区域
        String areaName = salesContractService.getAreaNameByUserId(user.getId());
        sellContract.setUserAreaName(areaName);
        request.setAttribute("sellContract", sellContract);

        List list = null;
        list = (List<ProductTypeInfoDto>) salesContractService.getProdTypeDeptList(user
                .getId());
        // 产品信息
        request.setAttribute("productList", list);

        // 根据用户Id获取所负责的客户信息
        List cusList = salesContractService.getCustomerByUserId(user.getId());
        request.setAttribute("customerList", cusList);
        log.info("User:{},进入新建销售合同页面【结束】", user.getId());
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 根据客户Id获取相应的联系人信息
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public ActionForward getLinkManByCustomerId(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        if (StringUtils.isNotEmpty(request.getParameter("customerId"))) {
            // 获取spring的service
            ISalesContractManagementService salesContractService = (ISalesContractManagementService) this
                    .getBean("salesContractManagementServiceImp");
            try {
                String customerId = request.getParameter("customerId");
                List<SalesConCustomerDto> linkManList = salesContractService
                        .getLinkManByCustomerId(customerId);

                JSONArray jsonCusLinkManList = new JSONArray();

                // 将 查到的所有类型转换成json格式
                for (int i = 0; i < linkManList.size(); i++) {
                    JSONObject jsonLinkMan = new JSONObject();
                    jsonLinkMan.put("id", linkManList.get(i).getCusLinkId());
                    jsonLinkMan.put("name", linkManList.get(i).getCusLinkName());
                    jsonLinkMan.put("province", linkManList.get(i).getCusProvince());
                    jsonLinkMan.put("city", linkManList.get(i).getCusCity());
                    jsonLinkMan.put("bankName", linkManList.get(i)
                            .getCusInvoiceBankName());
                    jsonLinkMan.put("bankAccount", linkManList.get(i)
                            .getCusInvoiceBankAccount());
                    jsonLinkMan.put("taxNumber", linkManList.get(i).getCusTaxNumber());
                    jsonLinkMan
                            .put("invoiceType", linkManList.get(i).getCusInvoiceType());
                    jsonCusLinkManList.put(jsonLinkMan);
                }
                // 将json串写回客户端
                response.setCharacterEncoding("UTF-8");
                PrintWriter writer = response.getWriter();
                writer.write(jsonCusLinkManList.toString());
                writer.close();

            } catch (Exception e) {
                log.error("获取联系人失败", e);
            }
        }
        return null;
    }

    /**
     * 根据联系人Id获取相应的联系人信息
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getLinkMsgByLinkManId(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        if (StringUtils.isNotEmpty(request.getParameter("customerLinkmanId"))) {
            // 获取spring的service
            ISalesContractManagementService salesContractService = (ISalesContractManagementService) this
                    .getBean("salesContractManagementServiceImp");
            CustomerLinkmanEntity customerLinkman = null;
            try {
                String linkmanId = request.getParameter("customerLinkmanId");
                customerLinkman = salesContractService.getLinkMsgByLinkManId(linkmanId);
                JSONArray jsonLinkManMsg = new JSONArray();
                // 将 查到的所有类型转换成json格式
                JSONObject jsonCusLinkMan = new JSONObject();
                jsonCusLinkMan.put("fax", customerLinkman.getFax());
                jsonCusLinkMan.put("tel", customerLinkman.getTel());
                jsonLinkManMsg.put(jsonCusLinkMan);
                // 将json串写回客户端
                response.setCharacterEncoding("UTF-8");
                PrintWriter writer = response.getWriter();
                writer.write(jsonLinkManMsg.toString());
                writer.close();

            } catch (Exception e) {
                log.error("获取联系人的传真和电话失败", e);
            }
        }
        return null;
    }

    /**
     * 客户发货地址选择
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public ActionForward getSendGoodsAddress(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("进入发货地址选择页面");
        // 获取spring的service
        ISalesContractManagementService salesContractService = (ISalesContractManagementService) this
                .getBean("salesContractManagementServiceImp");
        // 分頁
        NewPage newPage = getNewPage(request);
        newPage.setUrl("getSendGoodsAddress.do");
        List sendAddresslist = null;
        String customerId = request.getParameter("customerId");
        if (StringUtils.isNotEmpty(customerId)) {
            newPage.setQuery("customerId", customerId);
        }

        // 查看客户的发货地址
        sendAddresslist = salesContractService.getSendGoodsAddress(newPage, customerId);
        request.setAttribute("NewPage", newPage);
        request.setAttribute("sendAddresslist", sendAddresslist);
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 查看销售合同信息
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public ActionForward getSalesContractInfoById(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取用户登陆Id
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},查看销售合同信息【开始】", user.getId());

        ISalesContractManagementService salesContractManagementService = (ISalesContractManagementService) getBean("salesContractManagementServiceImp");

        String salesContractId = (String) (request.getParameter("salesContractId") != null ? request
                .getParameter("salesContractId") : request
                .getAttribute("salesContractId"));
        // 根据合同流水号查询合同信息
        SalesContractInfoDto salesContract = salesContractManagementService
                .getSalesContractById(salesContractId);

        // 获得公司信息
        CompanyInfoDto CompanyInfo = salesContractManagementService.getCompanyInfo();

        request.setAttribute("CompanyInfo", CompanyInfo);

        // 查看产品明细
        List list = salesContractManagementService
                .selectSalesContractProductDetailList(salesContractId);
        request.setAttribute("salesConProductList", list);

        // 将金额转换成大写的形式
        String money = salesContract.getMoney();

        request.setAttribute("capitalMoney", Arith.Const(money));

        // 设置评审人意见
        // 判断是否有产品总监评审意见
        if (salesContract.getProMajIdea() == null) {
            salesContract.setProMajFlag("1");// 1代表没有产品总监的评审意见
        } else {
            String proMajIdea = salesContract.getProMajIdea();
            salesContract.setProMajIdea1(proMajIdea.substring(0, 1));
            salesContract.setProMajIdea2(proMajIdea.substring(1, 2));
            salesContract.setProMajIdea3(proMajIdea.substring(2, 3));
        }
        // 判断是否有产品总监id
        if (salesContract.getProMajId() == null) {
            salesContract.setProMajId("-1");// -1代表没有产品总监id
        }

        // 判断是否有法务专员评审意见
        if (salesContract.getLegalIdea() == null) {
            salesContract.setLegalFlag("1");// 1代表没有法务专员的评审意见
        }
        // 判断是否有法务专员id
        if (salesContract.getLegalId() == null) {
            salesContract.setLegalId("-1");// -1代表没有法务专员id
        }

        // 设置销售总监评审意见

        if (salesContract.getSellMajIdea() == null) {
            salesContract.setSellMajIdea("1"); // 1代表没有销售总监评审意见
        } else {
            String sellMajIdea = salesContract.getSellMajIdea();
            salesContract.setSellMajIdea1(sellMajIdea.substring(0, 1));
            salesContract.setSellMajIdea2(sellMajIdea.substring(1, 2));
            salesContract.setSellMajIdea3(sellMajIdea.substring(2, 3));
            salesContract.setSellMajIdea4(sellMajIdea.substring(3, 4));
        }
        request.setAttribute("salesContract", salesContract);
        request.setAttribute("roleId", user.getRoleId());
        // 判断是否预览
        String sign = request.getParameter("command");
        log.info("User:{},查看销售合同信息【结束】", user.getId());
        if ("preview".equals(sign)) {
            return mapping.findForward("preview");
        }
        if ("effective".equals(sign)) {
            return mapping.findForward("effective");
        }
        // 判断是否判断是否订货确认单
        if ("orderConfirm".equals(sign)) {
            return mapping.findForward("orderConfirm");
        }
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 文件的下载
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward downloadFile(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 获取当前登陆用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},下载文件", user.getId());

        String fileName = request.getParameter("fileName");
        log.info("fileName:" + fileName);
        if (StringUtils.isBlank(fileName)) {
            log.info("User:{},下载文件失败: 文件不存在", user.getId());
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&isNotExist=true");
            return mapping.findForward(Constants.FAIL);
        }
        // 实现文件的下载
        try {
            downFile(fileName, Constants.DIR_NAME,
                    Constants.DOWNLOAD_FILE_NAME_PREFIX_SELL, request, response);
        } catch (Exception e) {
            log.error("User:{},下载文件失败:{}", user.getId(), e);
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&downLoadError=true");
            return mapping.findForward(Constants.FAIL);
        }
        return null;
    }

    /**
     * 
     * 获取销售合同的付款方式 小页面
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public ActionForward getPaymentWay(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        log.debug("进入付款方式选择页面");
        // 获取spring的service
        ISalesContractManagementService salesContractService = (ISalesContractManagementService) this
                .getBean("salesContractManagementServiceImp");

        // 分頁
        NewPage newPage = getNewPage(request);
        newPage.setUrl("getPaymentWay.do");
        List paymentWaylist = null;

        // 获取参数
        String customerId = request.getParameter("customerId");
        String prodTypeId = request.getParameter("prodTypeId");
        Map map = new HashMap();
        map.put("customerId", customerId);
        map.put("prodTypeId", prodTypeId);

        String customerName = request.getParameter("customername");// 字符转换
        String proTypeName = request.getParameter("typename");// 字符转换
        String totalMoney = request.getParameter("totalmoney");
        request.setAttribute("totalMoney", totalMoney);
        request.setAttribute("proTypeName", proTypeName);
        request.setAttribute("customerName", customerName);

        // 分页条件判断
        if (StringUtils.isNotEmpty(customerId)) {
            newPage.setQuery("customerId", customerId);
        }
        if (StringUtils.isNotEmpty(prodTypeId)) {
            newPage.setQuery("prodTypeId", prodTypeId);
        }
        if (StringUtils.isNotEmpty(customerName)) {
            newPage.setQuery("customername", customerName);
        }
        if (StringUtils.isNotEmpty(proTypeName)) {
            newPage.setQuery("typename", proTypeName);
        }
        if (StringUtils.isNotEmpty(totalMoney)) {
            newPage.setQuery("totalmoney", totalMoney);
        }

        // 查看客户的发货地址
        paymentWaylist = salesContractService.getPaymentWay(map, newPage);
        request.setAttribute("NewPage", newPage);
        request.setAttribute("paymentWaylist", paymentWaylist);

        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 根据条件检索产品信息
     * 
     * @author ChenHuajiang
     */
    @SuppressWarnings("unchecked")
    public ActionForward getProdInfoByCondition(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取用户登陆Id
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},根据条件检索产品信息【开始】", user.getId());

        ProductSearchForm prodForm = (ProductSearchForm) form;
        // 分页
        NewPage page = getNewPage(request);
        page.setUrl("getProduct.do");
        page.setQuery("prodTypeId", prodForm.getProdTypeId() + "");
        page.setQuery("prodTypeName", prodForm.getProdTypeName());
        page.setQuery("prodSerieId", prodForm.getProdSerieId());
        page.setQuery("prodSeriesName", prodForm.getProdSeriesName());
        page.setQuery("prodCode", prodForm.getProdCode());
        page.setQuery("prodName", prodForm.getProdName());
        page.setQuery("prodType", prodForm.getProdType());

        ISalesContractManagementService salesContractService = (ISalesContractManagementService) this
                .getBean("salesContractManagementServiceImp");
        // 产品信息
        List prodList = null;
        prodList = salesContractService.getProdInfoByCondition(prodForm, page);

        // 产品系列
        List prodSerieList = null;
        prodSerieList = salesContractService.getProdSeriesByProdTypeId(prodForm
                .getProdTypeId() + "");

        // 查询条件
        request.setAttribute("searchForm", prodForm);
        request.setAttribute("NewPage", page);
        request.setAttribute("prodInfoList", prodList);
        request.setAttribute("prodSerieList", prodSerieList);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        request.setAttribute("date", sdf.format(new Date()));
        int listSize = 0;
        if (prodList != null) {
            listSize = prodList.size();
        }
        request.setAttribute("listSize", listSize);
        log.info("User:{},根据条件检索产品信息【结束】", user.getId());
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 产品选择
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public ActionForward getProduct(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取用户登陆Id
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},进入产品选择页面【开始】", user.getId());

        // 获取spring的service
        ISalesContractManagementService salesContractService = (ISalesContractManagementService) this
                .getBean("salesContractManagementServiceImp");

        // 根据用户Id获取负责的产品分类
        List proTypeList = salesContractService.getProTypeByUserId(user.getId());
        request.setAttribute("proTypeList", proTypeList);

        // 分頁
        NewPage newPage = getNewPage(request);
        newPage.setUrl("getProduct.do");

        // 参数获取
        SalesConProductDto salesConProductDto = new SalesConProductDto();
        String productName = request.getParameter("productName");
        if (StringUtils.isNotEmpty(productName)) {
            productName = productName.trim();// 字符转换
        }
        String productTypeId = request.getParameter("productTypeId");
        String serieId = request.getParameter("serieId");
        String proCode = request.getParameter("proCode");
        if (StringUtils.isNotEmpty(proCode)) {
            proCode = proCode.trim();
        }
        String productType = request.getParameter("productType");
        if (StringUtils.isNotEmpty(productType)) {
            productType = productType.trim();
        }
        salesConProductDto.setProductName(productName);
        salesConProductDto.setProTypeId(productTypeId);
        salesConProductDto.setProSerieId(serieId);
        salesConProductDto.setProductCode(proCode);
        salesConProductDto.setProductType(productType);

        String deptFax = request.getParameter("deptFax");// 传真
        String deptAccount = request.getParameter("deptAccount");// 帐号
        String deptName = request.getParameter("deptName");// 产品部门名称
        request.setAttribute("deptFax", deptFax);
        request.setAttribute("deptAccount", deptAccount);
        request.setAttribute("deptName", deptName);

        // 分页条件判断
        if (StringUtils.isNotEmpty(productName)) {
            newPage.setQuery("productName", productName);
        }
        if (StringUtils.isNotEmpty(productTypeId)) {
            newPage.setQuery("productTypeId", productTypeId);
        }
        if (StringUtils.isNotEmpty(serieId)) {
            newPage.setQuery("serieId", serieId);
        }
        if (StringUtils.isNotEmpty(proCode)) {
            newPage.setQuery("proCode", proCode);
        }
        if (StringUtils.isNotEmpty(productType)) {
            newPage.setQuery("productType", productType);
        }
        // request.setAttribute("productTypeId", productTypeId);
        // 根据条件查看产品信息
        List productList = salesContractService.getProduct(salesConProductDto, newPage);
        request.setAttribute("listSize", productList.size());
        request.setAttribute("productList", productList);
        request.setAttribute("NewPage", newPage);

        salesConProductDto.setProductName(request.getParameter("productName"));
        request.setAttribute("salesConProduct", salesConProductDto);

        request.setAttribute("deptFax", request.getParameter("deptFax"));
        request.setAttribute("deptAccount", request.getParameter("deptAccount"));
        request.setAttribute("deptName", request.getParameter("deptName"));
        log.info("User:{},进入产品选择页面【结束】", user.getId());
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 根据产品分类ID查看系列
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public ActionForward getProSerieByProTypeId(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        if (StringUtils.isNotEmpty(request.getParameter("productTypeId"))) {
            // 获取spring的service
            ISalesContractManagementService salesContractService = (ISalesContractManagementService) this
                    .getBean("salesContractManagementServiceImp");
            List<SalesConProductDto> serieList = null;
            try {
                String productTypeId = request.getParameter("productTypeId");
                serieList = salesContractService.getProSerieByProTypeId(productTypeId);
                JSONArray jsonSerieList = new JSONArray();
                // 将 查到的所有类型转换成json格式
                for (int i = 0; i < serieList.size(); i++) {
                    JSONObject jsonSerie = new JSONObject();
                    jsonSerie.put("serieId", serieList.get(i).getProSerieId());
                    jsonSerie.put("serieName", serieList.get(i).getProSerieName());
                    jsonSerie.put("deptName", serieList.get(i).getProDeptName());
                    jsonSerie.put("deptFax", serieList.get(i).getProDeptFax());
                    jsonSerie.put("deptAccount", serieList.get(i).getProDeptAccount());

                    jsonSerieList.put(jsonSerie);
                }
                // 将json串写回客户端
                response.setCharacterEncoding("UTF-8");
                PrintWriter writer = response.getWriter();
                writer.write(jsonSerieList.toString());
                writer.close();

            } catch (Exception e) {
                log.error("根据产品分类产看系列失败", e);
            }
        }
        return null;
    }

    /**
     * 销售合同显示
     * 
     * @author 李乐伟
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */

    public ActionForward showSalesContractForUpdate(ActionMapping mapping,
            ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // 获取当前登陆用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},销售合同显示", user.getId());
        /* 防止重复提交 */
        this.saveToken(request);

        // 权限检查
        if (user.getRoleId() != Constants.ROLE_SALES_MANAGER) {
            log.info("User:{},越权操作", user.getId());
            request.setAttribute("msg", "越权操作");
            return mapping.findForward("accessDenied");
        }

        ISalesContractManagementService contractService = (ISalesContractManagementService) Container
                .getBean("salesContractManagementServiceImp");
        // 合同id

        String contractId = request.getParameter("contractId") != null ? request
                .getParameter("contractId") : (String) request.getAttribute("contractId");

        // id检查
        if (StringUtils.isEmpty(contractId)) {
            log.info("User:{},销售合同显示失败: 非法合同ID", user.getId());
            request.setAttribute("msg", "销售合同显示失败: 非法合同ID");
            return mapping.findForward(Constants.FAIL);
        }
        // 根据id查询合同
        SalesContractInfoDto contract = contractService.getSalesContractById(contractId
                .trim());

        if (contract == null) {
            log.info("User:{},销售合同显示失败: 记录不存在", user.getId());
            request.setAttribute("msg", "销售合同显示失败: 记录不存在");
            return mapping.findForward(Constants.FAIL);
        }
        // 合法性检查
        if (!contract.getUserId().equals(user.getId())) {
            log.info("User:{},销售合同显示失败: 超出权限范围", user.getId());
            request.setAttribute("msg", "销售合同显示失败: 超出权限范围");
            return mapping.findForward(Constants.FAIL);
        }
        // 用户电话
        contract.setUserTel(user.getTel());
        // 客户列表
        request.setAttribute("customerList",
                contractService.getCustomerByUserId(user.getId()));
        // 客户联系人列表
        request.setAttribute("customerLinkmanList",
                contractService.getLinkManByCustomerId(contract.getCustomerId() + ""));
        // 合同明细
        request.setAttribute("contractProductDetailList",
                contractService.getSalesContractDetailListByContractId(contract.getId()));

        splitSalesContractIdea(contract);
        // 销售合同数据
        request.setAttribute("contract", contract);
        // 客户名称
        request.setAttribute("customerName", contract.getCustomerName());
        // 获得预览参数
        String command = request.getParameter("command");
        request.setAttribute("command", command);

        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 销售助理不存在的检查
     * 
     * @param contract
     * @return
     */
    private String isSaleAssiantNotExist(SalesContractInfoDto contract) {

        ICommonService commonService = (ICommonService) Container
                .getBean("commonServiceImpl");
        WorkReceiverDto salesAssiant;
        String userId = null;
        try {
            salesAssiant = commonService.getUserIdByCondition(
                    contract.getProductTypeId(), contract.getUserAreaId(), null);
            userId = salesAssiant.getUserId();
        } catch (Exception e) {
            log.error("查找销售助理失败", e);
            salesAssiant = null;
        }
        return userId;
    }

    /**
     * 获取运营总监、运营总监助理、法务专员ID
     * 
     * @param roleId
     *            角色ID
     * @return userId 评审人ID
     */
    private String getJudgeId(int roleId) {
        ICommonService commonService = (ICommonService) Container
                .getBean("commonServiceImpl");
        WorkReceiverDto salesAssiant;
        String userId = null;
        Integer[] arr = new Integer[] { roleId };
        try {
            salesAssiant = commonService.getUserIdByCondition(null, null, null, arr);
            userId = salesAssiant.getUserIdByRoleId(roleId);
        } catch (Exception e) {
            log.error("查运营总监、运营总监助理、法务专员失败", e);
            salesAssiant = null;
        }
        return userId;
    }

    /**
     * 新建销售合同
     * 
     * @author 申思
     */
    @SuppressWarnings("unchecked")
    public ActionForward addSalesContract(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 获取当前登陆用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},新建销售合同", user.getId());
        /* 防止重复提交 */
        if (this.isTokenValid(request)) {
            this.resetToken(request);
        } else {
            log.info("User:{},新建销售合同失败：请勿重复提交", user.getId());
            // 有不存在的评审人
            request.setAttribute("msg", "新建销售合同失败：请勿重复提交");
            return mapping.findForward(Constants.FAIL);
        }

        SalesContractInfoDto salesDto = getSalesContractDtoFromActionForm(form);

        if (StringUtils.isBlank(salesDto.getContractProName())) {
            request.setAttribute("msg", "请填写销售项目名称");
            return mapping.findForward(Constants.FAIL);
        }
        resetSalesContractInfoByTemplateType(salesDto);
        FormFile file = null;
        boolean isSuccess = false;// 操作成功标志

        // 销售合同spring
        ISalesContractManagementService service = (ISalesContractManagementService) Container
                .getBean("salesContractManagementServiceImp");
        // 共通
        ICommonService commonService = (ICommonService) Container
                .getBean("commonServiceImpl");

        // 销售合同的全部评审人
        ContractJudgeRole contractJudgeRole = new ContractJudgeRole(true,
                request.getParameter("productTypeId"), null, user.getUserAreaId(),
                getSalesContractService());

        // 检查全部评审人是否存在 其中区域总监可以没有
        String checkResult = checkAssessMan(contractJudgeRole);

        if (checkResult != null) {
            log.info("User:{},新建销售合同失败：评审人不存在", user.getId());
            // 有不存在的评审人
            request.setAttribute("msg", checkResult);
            return mapping.findForward(Constants.FAIL);
        }

        // 区域id
        salesDto.setUserAreaId(getLoginUserInfo(request).getUserAreaId());
        String usrId = isSaleAssiantNotExist(salesDto);
        if (usrId == null) {
            log.info("User:{},新建销售合同失败：销售助理不存在", user.getId());
            request.setAttribute("msg", "销售助理不存在");
            return mapping.findForward(Constants.FAIL);
        }

        // 从共通里获取销售合同编号id
        salesDto.setId(commonService.getSerialNumber("XS", "sell_contract"));

        if ("2".equals(salesDto.getTemplateType())) {

            // 非模板
            file = salesDto.getFile();
            if (file.getFileSize() > Constants.MAX_POST_SIZE) {// 大于5兆
                log.info("User:{},上传文件失败:" + Constants.MAX_POST_SIZE_ERR_MSG,
                        user.getId());
                request.setAttribute("msg", "上传文件失败:" + Constants.MAX_POST_SIZE_ERR_MSG);
                return mapping.findForward(Constants.FAIL);
            }

            String fname = null;
            fname = loadFile(file, Constants.DIR_NAME, request);
            if (fname != null) {
                salesDto.setFileName(fname);
            } else {
                log.info("User:{},新建销售合同失败：上传文件失败", user.getId());
                request.setAttribute("msg", "上传文件失败");
                return mapping.findForward(Constants.SUCCESS);
            }
        }

        // 用户相关信息
        salesDto.setUserId(user.getId());
        salesDto.setUserName(user.getName());
        salesDto.setUserAreaId(user.getUserAreaId());

        List salesContractDetailList = new ArrayList();
        // 产品总数量
        int totalCount = 0;
        // 产品总价格
        String totalPrice = "0.00";
        // 产品总预计采购单价
        String totalProdAvePrice = "0.00";
        // 添加合同明细
        String[] id = request.getParameterValues("productids");// 产品编号
        String[] count = request.getParameterValues("count");// 销售数
        String[] price = request.getParameterValues("price");// 销售单价
        // 预计采购价buy_price
        // 产品销售合同数（用于产品表的计算）
        String[] canuse = request.getParameterValues("canuse");// 产品的销售可用数

        // 用于添加销售合同
        for (int i = 0; i < id.length; i++) {
            SellContractDetailEntity salesConDetial = new SellContractDetailEntity();
            salesConDetial.setSellContractId(salesDto.getId());
            salesConDetial.setProductId(id[i]);
            salesConDetial.setCount(Integer.parseInt(count[i]));
            salesConDetial.setPrice(price[i]);
            String buyPrice = commonService.getProdAvePrice(id[i], "1");
            // 获取产品预计采购价
            salesConDetial.setBuyPrice(buyPrice);
            totalCount += Integer.parseInt(count[i]);
            totalPrice = Arith.add(totalPrice,
                    Arith.mul(price[i].toString(), salesConDetial.getCount().toString()));
            totalProdAvePrice = Arith.add(totalProdAvePrice,
                    Arith.mul(buyPrice, salesConDetial.getCount().toString()));
            salesContractDetailList.add(salesConDetial);
        }

        // 默认不缺货
        salesDto.setLackProduct(false);
        // 需求单表
        List demandlist = new ArrayList();
        for (int i = 0; i < count.length; i++) {
            // 销售数-可用数
            int cha = 0;
            if (Integer.parseInt(canuse[i]) < 0) {// 销售可用数为负时直接取销售合同数
                cha = Integer.parseInt(count[i]);
            } else {
                String result = Arith.sub(count[i], canuse[i]);
                cha = Integer.parseInt(result);
            }
            if (cha > 0) {// 无货
                salesDto.setLackProduct(true);
                DemandEntity demandinfo = new DemandEntity();
                demandinfo.setSellContractId(salesDto.getId());// 合同编号
                demandinfo.setProductId(id[i]);// 产品Id
                demandinfo.setCount(cha);// 需求数
                demandinfo.setUserId(user.getId());
                demandinfo.setProductTypeId(salesDto.getProductTypeId() + "");
                demandinfo.setUserName(user.getName());
                demandinfo.setUserAreaId(user.getUserAreaId());
                demandinfo.setStatus(0);// 需求单状态
                demandlist.add(demandinfo);
            }
        }

        salesDto.setMoney(totalPrice);
        String interestRate = (new BigDecimal(totalPrice)).doubleValue() == 0 ? "0"
                : Util.getInterestRate(new BigDecimal(totalPrice), new BigDecimal(
                        totalProdAvePrice), totalCount);
        salesDto.setInterestRate(interestRate);
        addJudgeRoleInfoToContract(salesDto, contractJudgeRole);

        // 代办事项
        WorkEntity work = new WorkEntity();
        work.setWorkId(Constants.SELL1);// 销售合同待评审
        AssessManDto next = getNextAssessManOfContract(salesDto);
        work.setUserId(next.getAssessManId());
        // 评审人初始化

        String sign = request.getParameter("method");
        resetContractStatusByMethod(salesDto, sign.equals("2") ? "submit" : " ");

        if (sign.equals("3")) {// 预览

            isSuccess = service.addSalesContarct(salesDto, salesContractDetailList,
                    demandlist, work, sign); // 保存

            if (isSuccess) {
                // 预览
                return new ActionForward(mapping.findForward("preview").getPath()
                        + "&contractId=" + salesDto.getId());

            } else {
                log.info("User:{},新建销售合同失败", user.getId());
                request.setAttribute("msg", "新建销售合同失败");
                return mapping.findForward(Constants.FAIL);
            }
        }

        isSuccess = service.addSalesContarct(salesDto, salesContractDetailList,
                demandlist, work, sign);
        if (isSuccess) {
            return mapping.findForward(Constants.SUCCESS);

        } else {
            log.info("User:{},新建销售合同失败", user.getId());
            request.setAttribute("msg", "新建销售合同失败");
            return mapping.findForward(Constants.FAIL);
        }
    }

    /**
     * 销售合同处理(预览，保存，提交)
     * 
     * @author 李乐伟
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward dealwithSalesContract(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 获取当前登陆用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},销售合同处理", user.getId());
        /* 防止重复提交 */
        if (this.isTokenValid(request)) {
            this.resetToken(request);
        } else {
            log.info("User:{},更新失败：请勿重复提交", user.getId());
            request.setAttribute("msg", "更新失败：请勿重复提交");
            return new ActionForward(mapping.findForward(Constants.FAIL).getPath()
                    + "?contractId=" + request.getParameter("id"));
        }

        // 页面合同值
        SalesContractInfoDto contract = getSalesContractDtoFromActionForm(form);
        if (StringUtils.isBlank(contract.getContractProName())) {
            request.setAttribute("msg", "更新失败：请填写销售项目名称");
            return new ActionForward(mapping.findForward(Constants.FAIL).getPath()
                    + "?contractId=" + request.getParameter("id"));
        }
        // 合同的模板类型为 非模板 并且合同文件存在时上传
        if (contract.getTemplateType().equals("2")
                && contract.getFile().getFileSize() > 0) {

            if (contract.getFile().getFileSize() > Constants.MAX_POST_SIZE) {// 大于5兆
                log.info("User:{},上传文件失败:" + Constants.MAX_POST_SIZE_ERR_MSG,
                        user.getId());
                request.setAttribute("msg", "上传文件失败:" + Constants.MAX_POST_SIZE_ERR_MSG);
                return new ActionForward(mapping.findForward(Constants.FAIL).getPath()
                        + "?contractId=" + request.getParameter("id"));
            }

            // 上传成功后的文件名
            String fileName = loadFile(contract.getFile(), Constants.DIR_NAME, request);

            if (null != fileName) {
                // 上传成功
                contract.setFileName(fileName);
            } else {
                log.info("User:{},销售合同处理：上传文件失败", user.getId());
                // 上传失败
                request.setAttribute("msg", "上传文件失败");
                return new ActionForward(mapping.findForward(Constants.FAIL).getPath()
                        + "?contractId=" + request.getParameter("id"));
            }
        } else {
            // 原来的合同文件名
            contract.setFileName(request.getParameter("contractFileName"));
        }

        // 更新销售合同
        boolean isSuccess = updateSalesContract(contract, request);
        if (isSuccess) {
            // 更新成功
            if (request.getParameter("method").equals("preview")) {
                // 预览
                ActionForward preveiwForward = new ActionForward(mapping.findForward(
                        "preview").getPath()
                        + "&contractId=" + request.getParameter("id"));
                // 重定向
                preveiwForward.setRedirect(true);

                return preveiwForward;
            } else {
                // 保存和提交 返回销售合同列表页面
                return mapping.findForward("salesContractList");
            }
        } else {

            if (request.getAttribute("msg") == null) {
                log.info("User:{},销售合同处理：销售合同更新失败", user.getId());
                request.setAttribute("msg", "销售合同更新失败");
            }
            return new ActionForward(mapping.findForward(Constants.FAIL).getPath()
                    + "?contractId=" + request.getParameter("id"));
        }
    }

    /**
     * 销售合同更新
     * 
     * @author 李乐伟
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @param contract
     *            页面销售合同值
     * @return
     * @throws Exception
     */

    private boolean updateSalesContract(SalesContractInfoDto contract,
            HttpServletRequest request) throws Exception {
        // 获取当前登陆用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},销售合同更新", user.getId());
        // 根据合同的模板类型设置合同的基本信息
        resetSalesContractInfoByTemplateType(contract);
        // 合同明细
        List<SalesContractProductDetailDto> contractDetailList = getAllContractDetailFromRequest(
                request, "update");

        if (contractDetailList == null || contractDetailList.size() == 0) {
            log.info("User:{},销售合同中必须有产品", user.getId());
            request.setAttribute("msg", "销售合同中必须有产品");
            return false;
        }
        String prodTypeId = request.getParameter("productTypeId");
        // 销售合同的全部评审人
        ContractJudgeRole contractJudgeRole = new ContractJudgeRole(true, prodTypeId,
                null, user.getUserAreaId(), getSalesContractService());
        // 检查全部评审人是否存在
        String checkResult = checkAssessMan(contractJudgeRole);

        if (checkResult != null) {
            // 有不存在的评审人
            request.setAttribute("msg", checkResult);
            return false;
        }

        // 区域id
        contract.setUserAreaId(getLoginUserInfo(request).getUserAreaId());
        String usrId = isSaleAssiantNotExist(contract);
        if (usrId == null) {
            log.info("User:{},销售合同更新失败：销售助理不存在", user.getId());
            request.setAttribute("msg", "销售助理不存在");
            return false;
        }
        // 根据合同明细计算得出的数据
        ContractCalculateValueDto resultData = getContractCalculateValueByContractDetail(contractDetailList);
        // 是否缺货,合同金额,利率
        contract.setLackProduct(resultData.isLackProcuct());
        contract.setMoney(resultData.getTotalMoney());
        contract.setInterestRate(resultData.getTotalRate());
        // 根据合同数据为合同加入对应评审人信息
        addJudgeRoleInfoToContract(contract, contractJudgeRole);
        // 客户的提交表单的形式
        String method = request.getParameter("method");
        // 缺货产品需求单
        List<DemandEntity> demandList = null;
        // 下一个评审人
        AssessManDto nextAssessMan = null;
        // 判断是提交还是保存 缺货时产品总监为评审人
        if (StringUtils.isNotEmpty(method)) {
            if ("submit".equals(method)) {
                nextAssessMan = getNextAssessManOfContract(contract);
                // 如果有缺货的产品更新需求单
                if (contract.isLackProduct()) {
                    // 缺货产品需求单
                    demandList = getDemandListAccordLackProductList(
                            resultData.getLackProductList(), request);
                }
            }

        }

        // 重新设置合同的状态
        resetContractStatusByMethod(contract, method);

        List<Object> updateData = new ArrayList<Object>();
        updateData.add(0, contract); // 销售合同
        updateData.add(1, contractDetailList); // 合同明细
        updateData.add(2, nextAssessMan); // 下一个评审人的待办事务
        updateData.add(3, demandList); // 需求单

        ISalesContractManagementService service = getSalesContractService();
        boolean isSuccess = service.modifySalesContract(updateData);
        return isSuccess;
    }

    /**
     * 更新销售合同的状态根据客户的动作
     * 
     * @param contract
     *            销售合同
     * @param method
     *            客户端动作(save/submit/preview)
     */
    public void resetContractStatusByMethod(SalesContractInfoDto contract, String method) {

        if (method.equals("submit")) {
            // 提交
            if (contract.isLackProduct()) {
                // 缺货时: 产品总监待评审
                contract.setStatus(Constants.SELLSTATUS_PROMAJWAITCOM);
            } else {
                // 模板类型为非标准模板时法务专待评审
                if (!isStandardTemplate(contract.getTemplateType())) {
                    contract.setStatus(Constants.SELLSTATUS_LEGALWAITCOM);
                } else {

                    // 如果存在区域总监 则区域总监评审 否则将由销售总监评审
                    if (contract.getAreaMajId() == null
                            || "".equals(contract.getAreaMajId())) {
                        // 销售总监待评审
                        contract.setStatus(Constants.SELLSTATUS_SELLMAJWAITCOM);
                    } else {
                        // 区域总监带评审 2010-09-07修改
                        contract.setStatus(Constants.SELLSTATUS_AREAMAJWAITCOM);
                    }
                }
            }
        } else {
            // 更新合同状态为: 未提交
            contract.setStatus(Constants.SELLSTATUS_NOSUBMIT);
        }
    }

    /**
     * 根据缺货产品列表生成需求单列表
     * 
     * @param lackProductList
     *            缺货产品列表
     * @return
     */
    private List<DemandEntity> getDemandListAccordLackProductList(
            List<SalesContractProductDetailDto> lackProductList,
            HttpServletRequest request) {

        List<DemandEntity> demandList = new ArrayList<DemandEntity>(
                lackProductList.size());

        UserEntity user = getLoginUserInfo(request);

        for (SalesContractProductDetailDto productDetail : lackProductList) {

            DemandEntity newDemand = new DemandEntity();
            // 需求数=销售数-可用数
            Integer demandCount = 0;
            if (productDetail.getProductUsableCount() < 0) {
                demandCount = productDetail.getDetailCount();
            } else {
                demandCount = productDetail.getDetailCount()
                        - productDetail.getProductUsableCount();
            }
            newDemand.setSellContractId(request.getParameter("id"));// 合同编号
            newDemand.setProductId(productDetail.getProductId());// 产品Id
            newDemand.setProductTypeId(productDetail.getProductTypeId());
            newDemand.setCount(demandCount);// 需求数
            newDemand.setUserId(user.getId());
            newDemand.setUserName(user.getName());
            newDemand.setUserAreaId(user.getUserAreaId());
            newDemand.setStatus(0);// 需求单状态
            demandList.add(newDemand);
        }

        return demandList;
    }

    /**
     * 根据合同数据为合同加入对应评审人信息
     * 
     * @param contract
     *            销售合同 注: 此时的contract的 isLackProduct, money,
     *            interestRate必须已经确定(计算完成)
     * @param contractJudgeRole
     *            与合同对应的全部的评审人
     */
    private void addJudgeRoleInfoToContract(SalesContractInfoDto contract,
            ContractJudgeRole contractJudgeRole) {
        // 加入销售总监 id
        contract.setSellMajId(contractJudgeRole
                .getUserIdByRoleType(Constants.ROLE_SALES_DIRECTOR));
        // 加入区域总监
        contract.setAreaMajId(contractJudgeRole
                .getUserIdByRoleType(Constants.ROLE_REGIONAL_DIRECTOR));
        // 缺货检查
        if (contract.isLackProduct()) {

            // 缺货加入产品总监id
            contract.setProMajId(contractJudgeRole
                    .getUserIdByRoleType(Constants.ROLE_PRODUCT_DIRECTOR));

        } else {
            // 不缺货清除合同的产品总监
            contract.setProMajId(null);
            contract.setProMajName(null);
        }

        // 合同模板类型检查
        if (isStandardTemplate(contract.getTemplateType())) {
            // 标准模板清除合同的法务专员
            contract.setLegalId(contractJudgeRole.getUserIdByRoleType(null));
        } else {
            // 非标准模板加入法务专员id
            contract.setLegalId(contractJudgeRole
                    .getUserIdByRoleType(Constants.ROLE_COMPLIANCE_OFFICER));
        }

        // 根据合同利率及金额加入运营总监或运营总监助理
        if (isOpeMajAssess(contract)) {
            // 运营总监
            contract.setOpeMajId(contractJudgeRole
                    .getUserIdByRoleType(Constants.ROLE_DIRECTOR_OF_OPERATIONS));
        } else {
            // 运营总监助理
            contract.setOpeMajId(contractJudgeRole
                    .getUserIdByRoleType(Constants.ROLE_AST_DIRECTOR_OF_OPERATIONS));
        }
    }

    /**
     * 根据合同值获取下一个评审人
     * 
     * @param contract
     *            contract的 templateType,isLackProduct不能为空
     * @return
     */
    private AssessManDto getNextAssessManOfContract(SalesContractInfoDto contract) {

        AssessManDto nextAssessMan = new AssessManDto();
        nextAssessMan.setWorkId(Constants.SELL1);
        // 缺货时评审人为产品总监
        if (contract.isLackProduct()) {
            nextAssessMan.setAssessManId(contract.getProMajId());

        } else {
            // 模板类型为非标准模板时法务专员为评审人
            if (!isStandardTemplate(contract.getTemplateType())) {
                nextAssessMan.setAssessManId(contract.getLegalId());

            } else {
                // 如果存在区域总监 则区域总监评审 否则将由销售总监评审
                if (contract.getAreaMajId() == null || "".equals(contract.getAreaMajId())) {
                    // 否则评审人为销售总监
                    nextAssessMan.setAssessManId(contract.getSellMajId());
                } else {
                    // 否则评审人为区域总监
                    nextAssessMan.setAssessManId(contract.getAreaMajId());
                }
            }
        }
        return nextAssessMan;
    }

    /**
     * 评审人检查
     * 
     * @return null 评审人都存在 message 评审人不存在的信息
     */
    private String checkAssessMan(ContractJudgeRole judgeRole) {

        RoleMEntity allRole[] = { new RoleMEntity(Constants.ROLE_SALES_DIRECTOR, "销售总监"),
                new RoleMEntity(Constants.ROLE_PRODUCT_DIRECTOR, "产品总监"),
                new RoleMEntity(Constants.ROLE_COMPLIANCE_OFFICER, "法务专员"),
                new RoleMEntity(Constants.ROLE_REGIONAL_DIRECTOR, "区域总监"),
                new RoleMEntity(Constants.ROLE_AST_DIRECTOR_OF_OPERATIONS, "运营总监助理"),
                new RoleMEntity(Constants.ROLE_DIRECTOR_OF_OPERATIONS, "运营总监") };

        String checkResult = null;
        // 全部评审人
        for (RoleMEntity role : allRole) {
            if (role.getId() == Constants.ROLE_REGIONAL_DIRECTOR
                    && judgeRole.getJudgeRoleByRoleType(-role.getId()) != null) {
                return checkResult = "有多个" + role.getName();
            }
            // 区域总监可以有可以没有
            if (role.getId() != Constants.ROLE_REGIONAL_DIRECTOR
                    && judgeRole.getJudgeRoleByRoleType(role.getId()) == null) {
                checkResult = role.getName() + "不存在";
                return checkResult;
            }
        }

        return checkResult;
    }

    public ISalesContractManagementService getSalesContractService() {
        return (ISalesContractManagementService) Container
                .getBean("salesContractManagementServiceImp");
    }

    /**
     * 计算合同的总金额和总利率
     * 
     * @author 李乐伟
     * @param contractDetailList
     * @return
     */

    private ContractCalculateValueDto getContractCalculateValueByContractDetail(
            List<SalesContractProductDetailDto> contractDetailList) {

        ICommonService commonService = (ICommonService) Container
                .getBean("commonServiceImpl");

        // 总销售价格
        BigDecimal totalSellMoney = new BigDecimal("0.00");
        // 总购买价格
        BigDecimal totalBuyMoney = new BigDecimal("0.00");
        // 运算结果数据
        ContractCalculateValueDto resultData = new ContractCalculateValueDto();
        int sellCount = 0;
        for (SalesContractProductDetailDto detail : contractDetailList) {

            // 设置产品的预计采购价
            String buyPrice;
            try {
                buyPrice = commonService.getProdAvePrice(detail.getProductId() + "", "1");
                BigDecimal _buyPrice = new BigDecimal(buyPrice);
                _buyPrice = _buyPrice.setScale(2, RoundingMode.HALF_UP);
                detail.setDetailBuyPrice(_buyPrice);
            } catch (Exception e) {
                log.error("设置产品的预计采购价(即库存平均价)失败", e);
            }

            totalSellMoney = totalSellMoney.add(detail.getDetailPrice().multiply(
                    new BigDecimal(detail.getDetailCount())));
            totalBuyMoney = totalBuyMoney.add(detail.getDetailBuyPrice().multiply(
                    new BigDecimal(detail.getDetailCount())));

            // 缺货判断
            if (detail.getDetailCount() > detail.getProductUsableCount()) {
                resultData.addLackProduct(detail);
            }
            sellCount += detail.getDetailCount();
        }

        // 总金额
        resultData.setTotalMoney(totalSellMoney.toString());

        if (totalSellMoney.doubleValue() == 0) {
            resultData.setTotalRate("0.00");
        } else {
            // 总利率
            String rate = Util.getInterestRate(totalSellMoney, totalBuyMoney, sellCount);
            resultData.setTotalRate(rate);
        }
        return resultData;
    }

    /**
     * 设置销售合同的信息，根据合同的模板类型
     * 
     * @param contract
     *            销售合同
     */
    public void resetSalesContractInfoByTemplateType(SalesContractInfoDto contract) {

        String templateType = contract.getTemplateType();
        // 标准模板，自定义模板
        if (isStandardTemplate(templateType)) {
            contract.setPlace(Constants.SELL_PLACE);
            contract.setProtectLimit(Constants.SELL_PROTECT);
            contract.setCheckLimit(Constants.SELL_CHECK);
            contract.setBreach(Constants.SELL_BREAK);
            contract.setLawsuit(Constants.SELL_LAWSUIT);
            contract.setClause(null);
        }

        if (isNotTemplate(contract.getTemplateType())) {
            // 非模板
            contract.setPlace(null);
            contract.setProtectLimit(null);
            contract.setCheckLimit(null);
            contract.setBreach(null);
            contract.setLawsuit(null);
        } else {
            contract.setFile(null);
        }
    }

    /**
     * 是否是标准模板
     * 
     * @param templateType
     * @return
     */
    private boolean isStandardTemplate(String templateType) {
        return templateType.equals("0");
    }

    /**
     * 是否是非模板
     * 
     * @param templateType
     * @return
     */
    private boolean isNotTemplate(String templateType) {
        return templateType.equals("2");
    }

    /**
     * 从Actionform中得到SalesContractInfoDto(页面值)
     * 
     * @author 李乐伟
     * @return SalesContractInfoDto 页面合同数据
     */
    private SalesContractInfoDto getSalesContractDtoFromActionForm(ActionForm form) {

        SalesContractInfoDto contract = new SalesContractInfoDto();
        SalesContractForm cForm = (SalesContractForm) form;

        contract.setId(cForm.getId());
        // 合同信息
        contract.setStampType(cForm.getStampType());
        contract.setPlace(cForm.getPlace());
        contract.setTemplateType(cForm.getTemplateType());
        contract.setProtectLimit(cForm.getProtectLimit());
        contract.setCheckLimit(cForm.getCheckLimit());
        contract.setBreach(cForm.getBreach());
        contract.setLawsuit(cForm.getLawsuit());
        contract.setClause(cForm.getClause());
        contract.setStatus(cForm.getStatus());
        contract.setText(cForm.getText());
        contract.setFile(cForm.getContractFile());
        contract.setDate(cForm.getDate());
        // 客户及客户联系人信息
        contract.setCustomerId(cForm.getCustomerId());
        contract.setCustomerName(cForm.getCustomerName());
        contract.setCustomerLinkmanId(cForm.getCustomerLinkmanId());
        contract.setCustomerAddressAddress(cForm.getCustomerAddressAddress());
        contract.setCustomerAddressLinkman(cForm.getCustomerAddressLinkman());
        contract.setCustomerAddressMobile(cForm.getCustomerAddressMobile());
        contract.setCustomerAddressName(cForm.getCustomerAddressName());
        contract.setCustomerAddressPostcode(cForm.getCustomerAddressPostcode());
        contract.setCustomerAddressTel(cForm.getCustomerAddressTel());

        contract.setContractProName(cForm.getContractProName());
        // 付款信息
        contract.setProductTypeId(cForm.getProductTypeId());
        contract.setPaymentWay(cForm.getPaymentWay());
        // 现结金额
        if (StringUtils.isNotEmpty(cForm.getCashMoney())) {
            contract.setCashMoney(new BigDecimal(cForm.getCashMoney().trim()));
        }

        // 全部现结
        if (isNotCreditPayment(cForm.getPaymentWay())) {
            contract.setBatchMaxMoney(null);
            contract.setBatchWay(0);

        } else {
            contract.setBatchWay(cForm.getBatchWay());
            if (StringUtils.isNotEmpty(cForm.getBatchMaxMoney())) {
                contract.setBatchMaxMoney(new BigDecimal(cForm.getBatchMaxMoney().trim()));

            }
        }
        contract.setCustomerCreditId(cForm.getCustomerCreditId());
        contract.setCreditTypeId(cForm.getCreditTypeId());
        contract.setProjectName(cForm.getProjectName());
        contract.setArterm(cForm.getArterm());
        contract.setFile(cForm.getContractFile());
        if (StringUtils.isNotEmpty(cForm.getFreeLimit())) {
            contract.setFreeLimit(new BigDecimal(cForm.getFreeLimit().trim()));
        }

        if (StringUtils.isNotEmpty(cForm.getClimit())) {
            contract.setClimit(new BigDecimal(cForm.getClimit()));
        }

        // 合同金额
        contract.setMoney(cForm.getMoney());
        contract.setCustomerAddressId(cForm.getCustomerAddressId());
        contract.setRequestSendDate(cForm.getRequestSendDate());
        return contract;
    }

    /**
     * 验证合同的付款方式是否为现结
     * 
     * @param payMentWay
     * @return
     */
    private boolean isNotCreditPayment(Integer payMentWay) {
        return payMentWay.equals(Constants.PAYMENT_WAY_CURRENT);
    }

    /**
     * 获取页面中合同明细的 销售合同修改用
     * 
     * @author 李乐伟
     * @param request
     * @param intention
     *            "update"/"assess"
     * @throws Exception
     *             如果 intention 不为 "update" 或 "assess"
     * @return 被修改预计采购价的合同明细list
     */
    private List<SalesContractProductDetailDto> getAllContractDetailFromRequest(
            HttpServletRequest request, String intention) throws Exception {

        if (!intention.equals("update") && !intention.equals("assess")) {
            throw new Exception("Invalid intention content");
        }

        ICommonService commonService = (ICommonService) Container
                .getBean("commonServiceImpl");

        List<SalesContractProductDetailDto> detailList = null;
        // 被修改预计采购价的合同明细
        String allDetailProductId[] = request.getParameterValues("proDetailId");
        String prodTypeId = request.getParameter("productTypeId");
        if (allDetailProductId == null) {
            return detailList;
        } else {

            detailList = new ArrayList<SalesContractProductDetailDto>();

            for (String detailProductId : allDetailProductId) {

                SalesContractProductDetailDto contractDetail = new SalesContractProductDetailDto();
                // 页面主id, 评审页面依赖 明细id , 修改页面依赖productId
                if (intention.equals("update")) {
                    contractDetail.setProductId(detailProductId);
                } else {
                    contractDetail.setDetailId(detailProductId);
                }

                String detailBuyPrice = "";

                if (intention.equals("update")) {
                    // 计算产品的预计采购价[修改时用]
                    detailBuyPrice = commonService.getProdAvePrice(detailProductId, "1");
                } else {
                    // 从页面获取预计采购价[评审时用]
                    detailBuyPrice = request.getParameter("detailBuyPrice"
                            + detailProductId);
                }

                BigDecimal buyPrice = new BigDecimal(detailBuyPrice);
                contractDetail.setDetailBuyPrice(buyPrice);

                contractDetail.setContractId(request.getParameter("id"));
                contractDetail.setDetailCount(Integer.parseInt(request
                        .getParameter("detailCount" + detailProductId)));
                contractDetail.setDetailPrice(new BigDecimal(request
                        .getParameter("detailPrice" + detailProductId)));

                contractDetail.setProductUsableCount(Integer.parseInt(request
                        .getParameter("productUsableCount" + detailProductId)));
                contractDetail.setProductTypeId(prodTypeId);
                detailList.add(contractDetail);
            }
            return detailList;
        }
    }

    /**
     * 销售合同意见分解
     * 
     * @author 李乐伟
     * @param contract
     */
    private void splitSalesContractIdea(SalesContractInfoDto contract) {
        // 销售总监意见分解 idea1,idea2,idea3,idea4
        String sellMajIdeas = contract.getSellMajIdea();

        if (StringUtils.isNotEmpty(sellMajIdeas)) {
            sellMajIdeas = sellMajIdeas.trim();
            contract.setSellMajIdea1(sellMajIdeas.substring(0, 1));
            contract.setSellMajIdea2(sellMajIdeas.substring(1, 2));
            contract.setSellMajIdea3(sellMajIdeas.substring(2, 3));
            contract.setSellMajIdea4(sellMajIdeas.substring(3, 4));
        }

        // 产品总监意见分解 idea1,idea2,idea3
        String productMajIdeas = contract.getProMajIdea();

        if (StringUtils.isNotEmpty(productMajIdeas)) {
            productMajIdeas = productMajIdeas.trim();
            contract.setProMajIdea1(productMajIdeas.substring(0, 1));
            contract.setProMajIdea2(productMajIdeas.substring(1, 2));
            contract.setProMajIdea3(productMajIdeas.substring(2, 3));
        }
    }

    /**
     * 销售合同评审显示
     * 
     * @author 李乐伟
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */

    public ActionForward showSalesContractForAssess(ActionMapping mapping,
            ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // 获取当前登陆用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},销售合同评审显示", user.getId());
        /* 防止重复提交 */
        this.saveToken(request);

        // 设置评审人标记,页面中使用
        // 产品总监
        if (user.getRoleId() == Constants.ROLE_PRODUCT_DIRECTOR) {
            request.setAttribute("assessTag", "productDirector");
        }
        // 法务专员
        if (user.getRoleId() == Constants.ROLE_COMPLIANCE_OFFICER) {
            request.setAttribute("assessTag", "complianceOfficer");
        }
        // 销售总监
        if (user.getRoleId() == Constants.ROLE_SALES_DIRECTOR) {
            request.setAttribute("assessTag", "salesDirector");
        }
        if (user.getRoleId() == Constants.ROLE_REGIONAL_DIRECTOR) {
            request.setAttribute("assessTag", "areaDirector");
        }
        // 运营总监或运营总监助理
        if (user.getRoleId() == Constants.ROLE_DIRECTOR_OF_OPERATIONS
                || user.getRoleId() == Constants.ROLE_AST_DIRECTOR_OF_OPERATIONS) {
            request.setAttribute("assessTag", "operationsDirector");
        }

        // 合同id
        String contractId = request.getParameter("contractId") == null ? (String) request
                .getAttribute("contractId") : request.getParameter("contractId");
        // id检查
        if (StringUtils.isEmpty(contractId)) {
            log.info("User:{},销售合同评审显示失败: 非法合同ID", user.getId());
            request.setAttribute("msg", "销售合同评审显示失败: 非法合同ID");
            return mapping.findForward(Constants.FAIL);
        }

        ISalesContractManagementService contractService = (ISalesContractManagementService) Container
                .getBean("salesContractManagementServiceImp");
        // 根据id查询合同
        SalesContractInfoDto contract = contractService.getSalesContractById(contractId
                .trim());

        if (contract == null) {
            log.info("User:{},销售合同评审显示失败: 获取合同数据失败", user.getId());
            request.setAttribute("msg", "销售合同评审显示失败: 获取合同数据失败");
            return mapping.findForward(Constants.FAIL);
        }

        // 合同明细
        List  contractProductDetailList =contractService.getSalesContractDetailListByContractId(contract.getId());
        request.setAttribute("contractProductDetailList",
                contractProductDetailList);
        BigDecimal toatlBuyPriceSum = new BigDecimal("0.00");
        for(int i=0;i<contractProductDetailList.size();i++){ 
            SalesContractProductDetailDto contractProductDetailDto =(SalesContractProductDetailDto)contractProductDetailList.get(i);
            BigDecimal detailcount= new BigDecimal(contractProductDetailDto.getDetailCount());
            toatlBuyPriceSum=toatlBuyPriceSum.add(contractProductDetailDto.getDetailBuyPrice().multiply(detailcount)); 
        }
        request.setAttribute("toatlBuyPriceSum", toatlBuyPriceSum);
        splitSalesContractIdea(contract);
        // 销售合同数据
        request.setAttribute("contract", contract);
        request.setAttribute("RMB", Util.changeConst(contract.getMoney()));

        return mapping.findForward("showSalesContractForAssess");
    }

    /**
     * 销售合同评审
     * 
     * @author 李乐伟
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public ActionForward assessSalesContract(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 获取当前登陆用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},销售合同评审", user.getId());
        /* 防止重复提交 */
        if (this.isTokenValid(request)) {
            this.resetToken(request);
        } else {
            log.info("User:{},评审失败：请勿重复提交", user.getId());
            request.setAttribute("redirect", request.getHeader("Referer")+ "&isTokenValid=true");
            return mapping.findForward(Constants.FAIL);
        }

        // 评审结果 (pass/nopass)
        String assessResult = request.getParameter("assessResult");
        String prodTypeId = request.getParameter("productTypeId");
        if (StringUtils.isEmpty(assessResult)) {
            assessResult = "nopass";
        }
        // 销售合同form
        SalesContractForm contractForm = (SalesContractForm) form;

        // id检查
        if (StringUtils.isEmpty(contractForm.getId())) {
            log.info("User:{},销售合同评审失败：合同ID为空", user.getId());
            request.setAttribute("msg", "销售合同评审失败：合同ID为空");
            return mapping.findForward("salesContractList");
        }
        // 获取service
        ISalesContractManagementService contractService = (ISalesContractManagementService) Container
                .getBean("salesContractManagementServiceImp");

        // 页面提交值
        SalesContractInfoDto contract = new SalesContractInfoDto();
        contract.setProductTypeId(contractForm.getProductTypeId());
        contract.setId(contractForm.getId());
        // 原合同值
        SalesContractInfoDto oldContract = contractService.getSalesContractById(contract
                .getId());

        // 下一个评审人
        AssessManDto next = new AssessManDto();
        next.setRoleId(0);
        contract.setUserAreaId(oldContract.getUserAreaId());
        // 合同明细数据(产品总监用)
        List<SalesContractProductDetailDto> allDetail = null;

        // 合同状态
        Integer contractStatus = null;
        // 设置评审人标记,页面中使用
        // 产品总监
        if (user.getRoleId().intValue() == Constants.ROLE_PRODUCT_DIRECTOR) {

            String ProMajIdea = "" + contractForm.getProMajIdea1()
                    + contractForm.getProMajIdea2() + contractForm.getProMajIdea3();
            // 设置产品总监的id,name,评审意见，日期，说明
            contract.setProMajId(user.getId());
            contract.setProMajName(user.getName());
            contract.setProMajDate(Util.getDate());
            contract.setProMajText(contractForm.getProMajText());
            contract.setProMajIdea(ProMajIdea);

            String interestRate = request.getParameter("interestRate");

            if (StringUtils.isEmpty(interestRate)) {
                log.info("User:{},销售合同评审失败：毛利率不能为空", user.getId());
                request.setAttribute("msg", "销售合同评审失败: 毛利率不能为空");
                request.setAttribute("contractId", contract.getId());
                return showSalesContractForAssess(mapping, form, request, response);
            }

            contract.setInterestRate(interestRate);
            // 合同明细数据
            allDetail = getAllContractDetailFromRequest(request, "assess");

            // 检查合同明细数据
            /*
             * if (allDetail == null || allDetail.size() == 0) {
             * 
             * request.setAttribute("contractId", contract.getId());
             * log.info("User:{},销售合同评审失败：获取合同明细数据失败", user.getId());
             * request.setAttribute("msg", "销售合同评审失败:获取合同明细数据失败"); return
             * showSalesContractForAssess(mapping, form, request, response); }
             */
            // 根据当前评审人角色和评审结果(通过/未通过)设置合同状态
            if (assessResult.equals("pass")) {
                // 产品总监评审通过-设置下一级评审人，法务或者销售总监

                String strId = oldContract.getLegalId();
                Integer role_id = null;
                if (strId != null) {
                    contractStatus = Constants.SELLSTATUS_LEGALWAITCOM;
                    role_id = Constants.ROLE_COMPLIANCE_OFFICER;
                } else {
                    contractStatus = Constants.SELLSTATUS_AREAMAJWAITCOM;
                    role_id = Constants.ROLE_REGIONAL_DIRECTOR;
                }

                // 销售合同下一评审人
                ContractJudgeRole contractJudgeRole = new ContractJudgeRole(false,
                        prodTypeId, role_id, contract.getUserAreaId(),
                        getSalesContractService());
                // 检查全部评审人是否存在
                if (strId != null) {
                    if (contractJudgeRole
                            .getUserIdByRoleType(Constants.ROLE_COMPLIANCE_OFFICER) == null) {
                        log.info("User:{},销售合同评审失败：法务专员不存在", user.getId());
                        request.setAttribute("msg", "销售合同评审失败：法务专员不存在");
                        request.setAttribute("contractId", contract.getId());
                        return showSalesContractForAssess(mapping, form, request,
                                response);
                    }
                } else {
                    if (contractJudgeRole
                            .getUserIdByRoleType(-Constants.ROLE_REGIONAL_DIRECTOR) != null) {
                        log.info("User:{},销售合同评审失败：区域总监存在多个", user.getId());
                        request.setAttribute("msg", "销售合同评审失败：有多个区域总监");
                        request.setAttribute("contractId", contract.getId());
                        return showSalesContractForAssess(mapping, form, request,
                                response);
                    }
                    if (contractJudgeRole
                            .getUserIdByRoleType(Constants.ROLE_REGIONAL_DIRECTOR) == null) {// 区域总监不存在
                                                                                             // 将由销售总监评审
                        role_id = null;
                        contractStatus = Constants.SELLSTATUS_SELLMAJWAITCOM;
                        role_id = Constants.ROLE_SALES_DIRECTOR;
                        // 销售合同下一评审人
                        contractJudgeRole = new ContractJudgeRole(false, prodTypeId,
                                role_id, contract.getUserAreaId(),
                                getSalesContractService());
                        if (contractJudgeRole
                                .getUserIdByRoleType(Constants.ROLE_SALES_DIRECTOR) == null) {
                            log.info("User:{},销售合同评审失败：销售总监不存在", user.getId());
                            request.setAttribute("msg", "销售合同评审失败：销售总监不存在");
                            request.setAttribute("contractId", contract.getId());
                            return showSalesContractForAssess(mapping, form, request,
                                    response);
                        }
                        // String id =
                        // _contractJudgeRole.getUserIdByRoleType(role_id);
                        // contract.setSellMajId(id);
                        // next.setAssessManId(id);
                        // next.setWorkId(Constants.SELL1);

                        // log.info("User:{},销售合同评审失败：区域总监不存在", user.getId());
                        // request.setAttribute("msg", "销售合同评审失败：区域总监不存在");
                        // request.setAttribute("contractId", contract.getId());
                        // return showSalesContractForAssess(mapping, form,
                        // request, response);
                    }

                }
                String id = contractJudgeRole.getUserIdByRoleType(role_id);
                if (strId != null) {
                    contract.setLegalId(id);
                } else {
                    if (contractJudgeRole
                            .getUserIdByRoleType(Constants.ROLE_REGIONAL_DIRECTOR) == null) {
                        contract.setSellMajId(id);
                    } else {
                        contract.setAreaMajId(id);
                    }
                }
                next.setAssessManId(id);
                next.setWorkId(Constants.SELL1);

            } else {
                // 产品总监评审未通过
                contractStatus = Constants.SELLSTATUS_PROMAJNOPASS;
                contractService.removeAllDemandOfContract(contract.getId());
            }
        }
        // 法务专员
        if (user.getRoleId().intValue() == Constants.ROLE_COMPLIANCE_OFFICER) {

            // 设置法务专员的id,name,评审意见，日期，说明
            contract.setLegalId(user.getId());
            contract.setLegalName(user.getName());
            contract.setLegalDate(Util.getDate());
            contract.setLegalText(contractForm.getLegalText());
            contract.setLegalIdea(contractForm.getLegalIdea() + "");

            // 根据当前评审人角色和评审结果(通过/未通过)设置合同状态
            if (assessResult.equals("pass")) {

                // 销售合同下一评审人
                ContractJudgeRole contractJudgeRole = new ContractJudgeRole(false,
                        prodTypeId, Constants.ROLE_REGIONAL_DIRECTOR,
                        contract.getUserAreaId(), getSalesContractService());
                if (contractJudgeRole
                        .getUserIdByRoleType(-Constants.ROLE_REGIONAL_DIRECTOR) != null) {
                    log.info("User:{},销售合同评审失败：区域总监存在多个", user.getId());
                    request.setAttribute("msg", "销售合同评审失败：有多个区域总监");
                    request.setAttribute("contractId", contract.getId());
                    return showSalesContractForAssess(mapping, form, request, response);
                }
                // 检查评审人是否存在
                if (contractJudgeRole
                        .getUserIdByRoleType(Constants.ROLE_REGIONAL_DIRECTOR) == null) {

                    Integer role_id = null;
                    contractStatus = Constants.SELLSTATUS_SELLMAJWAITCOM;
                    role_id = Constants.ROLE_SALES_DIRECTOR;
                    // 销售合同下一评审人
                    contractJudgeRole = new ContractJudgeRole(false, prodTypeId, role_id,
                            contract.getUserAreaId(), getSalesContractService());
                    if (contractJudgeRole
                            .getUserIdByRoleType(Constants.ROLE_SALES_DIRECTOR) == null) {
                        log.info("User:{},销售合同评审失败：销售总监不存在", user.getId());
                        request.setAttribute("msg", "销售合同评审失败：销售总监不存在");
                        request.setAttribute("contractId", contract.getId());
                        return showSalesContractForAssess(mapping, form, request,
                                response);
                    }
                    String id = contractJudgeRole.getUserIdByRoleType(role_id);
                    contract.setSellMajId(id);
                    next.setAssessManId(id);
                    next.setWorkId(Constants.SELL1);

                    /*
                     * log.info("User:{},销售合同评审失败：区域总监不存在", user.getId());
                     * request.setAttribute("msg", "销售合同评审失败：区域总监不存在");
                     * request.setAttribute("contractId", contract.getId());
                     * return showSalesContractForAssess(mapping, form, request,
                     * response);
                     */
                } else {
                    String id = contractJudgeRole
                            .getUserIdByRoleType(Constants.ROLE_REGIONAL_DIRECTOR);
                    contract.setAreaMajId(id);
                    next.setAssessManId(id);
                    contractStatus = Constants.SELLSTATUS_AREAMAJWAITCOM;
                    next.setWorkId(Constants.SELL1);
                }

            } else {
                contractStatus = Constants.SELLSTATUS_LEGALNOPASS;
                // 删除合同原来的全部需求单
                contractService.removeAllDemandOfContract(contract.getId());

            }

        }
        // 区域总监
        if (user.getRoleId().intValue() == Constants.ROLE_REGIONAL_DIRECTOR) {
            contract.setAreaMajId(user.getId());
            contract.setAreaMajName(user.getName());
            contract.setAreaMajDate(Util.getDate());
            contract.setAreaMajIdea(contractForm.getAreaMajIdea());
            contract.setAreaMajText(contractForm.getAreaMajText());
            if (assessResult.equals("pass")) {// 销售总监
                Integer role_id = null;
                contractStatus = Constants.SELLSTATUS_SELLMAJWAITCOM;
                role_id = Constants.ROLE_SALES_DIRECTOR;
                // 销售合同下一评审人
                ContractJudgeRole contractJudgeRole = new ContractJudgeRole(false,
                        prodTypeId, role_id, contract.getUserAreaId(),
                        getSalesContractService());
                if (contractJudgeRole.getUserIdByRoleType(Constants.ROLE_SALES_DIRECTOR) == null) {
                    log.info("User:{},销售合同评审失败：销售总监不存在", user.getId());
                    request.setAttribute("msg", "销售合同评审失败：销售总监不存在");
                    request.setAttribute("contractId", contract.getId());
                    return showSalesContractForAssess(mapping, form, request, response);
                }
                String id = contractJudgeRole.getUserIdByRoleType(role_id);
                contract.setSellMajId(id);
                next.setAssessManId(id);
                next.setWorkId(Constants.SELL1);
            } else {// 区域总监未通过
                contractStatus = Constants.SELLSTATUS_AREAMAJNOPASS;
                contractService.removeAllDemandOfContract(contract.getId());
            }
        }
        // 销售总监
        if (user.getRoleId().intValue() == Constants.ROLE_SALES_DIRECTOR) {

            String sellMajIdea = "" + contractForm.getSellMajIdea1()
                    + contractForm.getSellMajIdea2() + contractForm.getSellMajIdea3()
                    + contractForm.getSellMajIdea4();
            // 设置销售总监的id,name,评审意见，日期，说明
            contract.setSellMajId(user.getId());
            contract.setSellMajName(user.getName());
            contract.setSellMajDate(Util.getDate());
            contract.setSellMajText(contractForm.getSellMajText());
            contract.setSellMajIdea(sellMajIdea);

            // 根据当前评审人角色和评审结果(通过/未通过)设置合同状态
            if (assessResult.equals("pass")) {
                if (isOpeMajAssess(oldContract)) {// 运营总监
                    contractStatus = Constants.SELLSTATUS_OPEMAJWAITCOM;
                    String id = getJudgeId(Constants.ROLE_DIRECTOR_OF_OPERATIONS);
                    if (id == null) {
                        log.info("User:{},销售合同评审失败：运营总监不存在", user.getId());
                        request.setAttribute("msg", "销售合同评审失败：运营总监不存在");
                        request.setAttribute("contractId", contract.getId());
                        return showSalesContractForAssess(mapping, form, request,
                                response);
                    }
                    contract.setOpeMajId(id);
                    next.setAssessManId(id);
                    next.setRoleId(Constants.ROLE_DIRECTOR_OF_OPERATIONS);
                } else {// 运营总监助理
                    contractStatus = Constants.SELLSTATUS_OPEASSWAITCOM;
                    String id = getJudgeId(Constants.ROLE_AST_DIRECTOR_OF_OPERATIONS);
                    if (id == null) {
                        log.info("User:{},销售合同评审失败：运营总监助理不存在", user.getId());
                        request.setAttribute("msg", "销售合同评审失败：运营总监助理不存在");
                        request.setAttribute("contractId", contract.getId());
                        return showSalesContractForAssess(mapping, form, request,
                                response);
                    }
                    contract.setOpeMajId(id);
                    next.setAssessManId(id);
                    next.setRoleId(Constants.ROLE_AST_DIRECTOR_OF_OPERATIONS);
                }
                next.setWorkId(Constants.SELL1);
            } else {
                contractStatus = Constants.SELLSTATUS_DELLMAJNOPASS;
                contractService.removeAllDemandOfContract(contract.getId());
            }
        }

        // 运营总监 或助理
        if (user.getRoleId().intValue() == Constants.ROLE_DIRECTOR_OF_OPERATIONS
                || user.getRoleId().intValue() == Constants.ROLE_AST_DIRECTOR_OF_OPERATIONS) {

            // 共通
            ICommonService commonService = (ICommonService) Container
                    .getBean("commonServiceImpl");
            // 产品合同号
            String productContractCode;

            productContractCode = commonService.getProdContractSN("XS", prodTypeId,
                    "sell_contract");

            if (productContractCode == null) {
                log.info("User:{},销售合同评审失败：获取产品合同号失败", user.getId());
                request.setAttribute("contractId", contract.getId());
                request.setAttribute("msg", "销售合同评审失败:获取产品合同号失败");
                return showSalesContractForAssess(mapping, form, request, response);
            }

            // 设置运营总监或助理的id,name,评审意见，日期，说明
            contract.setOpeMajId(user.getId());
            contract.setOpeMajName(user.getName());
            contract.setOpeMajDate(Util.getDate());
            contract.setOpeMajText(contractForm.getOpeMajText());
            contract.setOpeMajIdea("" + contractForm.getOpeMajIdea());

            // 根据当前评审人角色和评审结果(通过/未通过)设置合同状态
            if (assessResult.equals("pass")) {

                contractStatus = Constants.SELLSTATUS_WAITPRINT;
                next.setWorkId(Constants.SELL2);
                contract.setProductContractCode(productContractCode);

                String usrId = isSaleAssiantNotExist(contract);
                if (usrId == null) {
                    log.info("User:{},销售合同评审失败：销售助理不存在", user.getId());
                    request.setAttribute("msg", "销售助理不存在");
                    request.setAttribute("contractId", contract.getId());
                    return showSalesContractForAssess(mapping, form, request, response);
                } else {
                    next.setAssessManId(usrId);
                }

            } else {
                contractStatus = (user.getRoleId() == Constants.ROLE_DIRECTOR_OF_OPERATIONS) ? Constants.SELLSTATUS_OPEMAJNOPASS
                        : Constants.SELLSTATUS_OPEASSNOPASS;
                contractService.removeAllDemandOfContract(contract.getId());
            }
        }

        // 当前评审人
        AssessManDto current = new AssessManDto();
        current.setAssessManId(user.getId());
        current.setWorkId(Constants.SELL1);

        // 更新合同状态
        contract.setStatus(contractStatus);
        // 要更新的数据集
        List updateData = new ArrayList(4);
        contract.setUserRoleId(user.getRoleId());
        updateData.add(0, contract);
        updateData.add(1, current);
        updateData.add(2, allDetail != null ? allDetail : null);
        updateData.add(3, assessResult.equals("pass") ? next : null);
        updateData.add(4, contract.getId());
        updateData.add(5, user.getRoleId());
        boolean isSuccess = contractService.modifySalesContractForAssess(updateData);
        if (isSuccess) {
            return mapping.findForward("salesContractList");
        } else {
            request.setAttribute("contractId", contract.getId());
            log.info("User:{},销售合同评审失败", user.getId());
            request.setAttribute("msg", "销售合同评审失败");
            return showSalesContractForAssess(mapping, form, request, response);
        }
    }

    /**
     * 判断是否运营总监评审
     * 
     * @param contract
     * @return true 运营总监处理 false 运营总监助理处理
     */
    private boolean isOpeMajAssess(SalesContractInfoDto contract) {

        boolean isOpeMajAssess = false;
        // 合同金额
        BigDecimal contractMoney = new BigDecimal(contract.getMoney());
        BigDecimal reuqestMoney = new BigDecimal(Constants.CONTRACT_MONEY);
        // 合同利率
        BigDecimal contractRate = new BigDecimal(contract.getInterestRate());
        BigDecimal requestReate = new BigDecimal(Constants.CONTRACT_RATE);

        // 合同金额<100万并且 合同利率 >=5%有运营总监处理
        isOpeMajAssess = !(reuqestMoney.subtract(contractMoney).doubleValue() > 0 && contractRate
                .subtract(requestReate).doubleValue() >= 0);

        return isOpeMajAssess;
    }

    /**
     * 销售合同文件下载
     * 
     * @param file
     * @param dirname
     * @param request
     * @param responseprefix_name
     * @return
     * @throws Exception
     */
    public ActionForward downLoadContractFile(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取当前登陆用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},销售合同文件下载", user.getId());
        // 文件名
        String contractId = request.getParameter("contractId");
        // 获取service
        ISalesContractManagementService contractService = (ISalesContractManagementService) Container
                .getBean("salesContractManagementServiceImp");

        SalesContractInfoDto contract = contractService.getSalesContractById(contractId);

        try {
            downFile(contract.getFileName(), Constants.DIR_NAME,
                    Constants.DOWNLOAD_FILE_NAME_PREFIX_SELL, request, response);

        } catch (Exception e) {
            log.error("User:{},销售合同评审：下载文件失败:{}", user.getId(), e);
            request.setAttribute("msg", "下载文件失败");
            request.setAttribute("contractId", contractId);
            return showSalesContractForAssess(mapping, form, request, response);
        }
        return null;
    }

    /**
     * 销售合同删除
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @author 张子旭
     */
    public ActionForward deleteSalesContract(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取当前登陆用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},销售合同删除", user.getId());
        // 合同号
        String contractId = request.getParameter("contractId");
        // 获取service
        ISalesContractManagementService contractService = (ISalesContractManagementService) Container
                .getBean("salesContractManagementServiceImp");

        // 删除合同
        boolean isSuccess = contractService.removeSalesContract(contractId);
        if (isSuccess) {
            return mapping.findForward(Constants.SUCCESS);
        } else {
            log.info("User:{},删除合同失败", user.getId());
            request.setAttribute("msg", "删除合同失败");
            return mapping.findForward(Constants.FAIL);
        }
    }

    /**
     * 更新合同状态信息（待打印->待确认）
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @author 张子旭
     */
    public ActionForward modifyContractStatus(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取当前登陆用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},更新合同状态信息 待确认", user.getId());

        // 合同号
        String contractId = request.getParameter("saleContractId");

        request.setAttribute("salesContractId", contractId);
        // 获取service
        ISalesContractManagementService contractService = (ISalesContractManagementService) Container
                .getBean("salesContractManagementServiceImp");

        // 删除自己一项待办事务
        WorkEntity work = new WorkEntity();
        work.setCount(-1);
        work.setUserId(user.getId());
        work.setWorkId(2);

        // 更新合同状态
        boolean isSuccess = contractService.modifyContractStatus(contractId, work);
        if (!isSuccess) {
            log.info("User:{},更新合同失败", user.getId());
            request.setAttribute("msg", "更新合同失败");
            return mapping.findForward(Constants.FAIL);
        }
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 更新合同状态信息（待确认->合同生效）
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @author 张子旭
     */
    @SuppressWarnings("unchecked")
    public ActionForward modifyContractStatusEffect(ActionMapping mapping,
            ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 获取当前登陆用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},更新合同状态信息 合同生效", user.getId());

        // 合同号
        String contractId = request.getParameter("salesContractId");

        // 公司合同号
        String companyContractCode = request.getParameter("companyContractCode");

        request.setAttribute("salesContractId", contractId);
        // 获取service
        ISalesContractManagementService contractService = (ISalesContractManagementService) Container
                .getBean("salesContractManagementServiceImp");

        Map map = new HashMap();

        map.put("contractId", contractId);

        map.put("companyContractCode", companyContractCode);

        // 更新合同状态
        boolean isSuccess = contractService.modifyContractStatusEffect(map);
        if (!isSuccess) {
            log.info("User:{},更新合同状态信息 合同未生效", user.getId());
            request.setAttribute("msg", "合同未生效");
            return mapping.findForward(Constants.FAIL);
        }
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 更新合同状态信息（待确认->合同取消）
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @author 张子旭
     */
    public ActionForward salesContractCancel(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取当前登陆用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},更新合同状态信息 合同取消", user.getId());

        // 合同号
        String contractId = request.getParameter("salesContractId");

        request.setAttribute("salesContractId", contractId);
        // 获取service
        ISalesContractManagementService contractService = (ISalesContractManagementService) Container
                .getBean("salesContractManagementServiceImp");

        // 更新合同状态
        boolean isSuccess = contractService.modifySalesContractCancel(contractId);

        if (!isSuccess) {
            log.info("User:{},更新合同状态信息 取消合同失败", user.getId());
            request.setAttribute("msg", "取消合同失败");
            return mapping.findForward(Constants.FAIL);
        }
        return mapping.findForward(Constants.SUCCESS);
    }
}
