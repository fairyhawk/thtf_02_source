package cn.com.thtf.egov.cms.action.salesBackContract;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
import cn.com.thtf.egov.cms.constant.Constants;
import cn.com.thtf.egov.cms.dto.CompanyInfoDto;
import cn.com.thtf.egov.cms.dto.SaleBackContractOfAddDto;
import cn.com.thtf.egov.cms.dto.SalesBackContractDto;
import cn.com.thtf.egov.cms.dto.SalesBackContractOfShowDto;
import cn.com.thtf.egov.cms.dto.SalesBackQueryDto;
import cn.com.thtf.egov.cms.dto.StockroomAndAddressDto;
import cn.com.thtf.egov.cms.dto.WorkReceiverDto;
import cn.com.thtf.egov.cms.entity.SellBackGoodsDetailEntity;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.form.SaleBackContractOfAddForm;
import cn.com.thtf.egov.cms.iservice.common.ICommonService;
import cn.com.thtf.egov.cms.iservice.purchase.IBuyContractService;
import cn.com.thtf.egov.cms.iservice.salesBackContract.ISalesBackContractService;
import cn.com.thtf.egov.cms.iservice.sell.ISalesContractManagementService;
import cn.com.thtf.egov.cms.util.Util;

/**
 * 销售退货合同 Action
 * 
 * @author lilewei
 */
public class SalesBackContractAction extends NewBaseAction {

    public static Logger log = LoggerFactory.getLogger(SalesBackContractAction.class);

    private ISalesBackContractService salesBackContractService = (ISalesBackContractService) getBean("saleBackContractServiceImpl");
    private ICommonService common = (ICommonService) getBean("commonServiceImpl");
    private java.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private IBuyContractService buyContractService = null;

    /**
     * 销售退货管理列表
     * 
     * @author lilewei
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */

    public ActionForward salesBackContractManage(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},销售退货管理列表【开始】", user.getId());

        // 分页
        NewPage page = getNewPage(request);
        page.setUrl("salesBackContractManage.do");

        // 查询条件
        SalesBackQueryDto queryCondition = getQueryConditionFromRequest(request);
        // 用于验证是 检索方式的查询或初始页面查询的参数
        String initStr = request.getParameter("init");
        if (StringUtils.isNotEmpty(initStr)) {
            queryCondition.setInitStr(initStr);
            page.setQuery("init", initStr);
        }

        queryCondition.setCurrentUserId(user.getId());
        // 设置当前用户的在sql文件中的统一roleId
        queryCondition.setRoleId(getSQLRoleIdOfUser(user));
        queryCondition.setUserId(user.getId());
        List<SalesBackContractDto> salesBackContractList = salesBackContractService
                .getAllSalesBackContractByCondition(queryCondition, page);
        // 产品类型列表
        request.setAttribute("productTypeList", common.getAllProductTypes(null, user
                .getId(), user.getRoleId() + ""));
        // 销售退货合同列表
        request.setAttribute("salesBackContractList", salesBackContractList);
        // 将查询条件加到分页中
        addQueryConditionToNewPage(queryCondition, page);
        request.setAttribute("NewPage", page);
        // 当前用户
        request.setAttribute("user", user);
        // 查询条件
        request.setAttribute("queryCondition", queryCondition);
        // 主要操作权限
        request.setAttribute("action", getMainAuthorityOfUser(user));
        log.info("User:{},销售退货管理列表【结束】", user.getId());
        return mapping.findForward("salesBackContractList");
    }

    /**
     * 将查询条件加到 page中
     * 
     * @param queryCondition
     *            页面查询条件
     * @param page
     */
    private void addQueryConditionToNewPage(SalesBackQueryDto queryCondition, NewPage page) {

        page.setQuery("productTypeId", queryCondition.getProductTypeId() + "");
        page.setQuery("productContractCode", queryCondition.getProductContractCode());
        page.setQuery("companyContractCode", queryCondition.getCompanyContractCode());
        page.setQuery("customerName", queryCondition.getCustomerName());
        page.setQuery("templateType", queryCondition.getTemplateType() + "");
        page.setQuery("userName", queryCondition.getUserName());
        page.setQuery("starttime", queryCondition.getStartTime());
        page.setQuery("endtime", queryCondition.getEndTime());
        page.setQuery("status", queryCondition.getStatus() + "");

    }

    /**
     * 从页面取查询条件
     * 
     * @author lilewei
     * @param actionform
     * @return
     */
    private SalesBackQueryDto getQueryConditionFromRequest(HttpServletRequest request) {

        SalesBackQueryDto queryCondition = new SalesBackQueryDto();

        String productTypeId = request.getParameter("productTypeId");
        if (StringUtils.isNotEmpty(productTypeId)) {

            try {
                queryCondition.setProductTypeId(Integer.parseInt(productTypeId.trim()));
            } catch (NumberFormatException e) {

            }
        }

        String productContractCode = request.getParameter("productContractCode");
        if (StringUtils.isNotEmpty(productContractCode)) {
            queryCondition.setProductContractCode(productContractCode.trim());
        }
        String companyContractCode = request.getParameter("companyContractCode");
        if (StringUtils.isNotEmpty(companyContractCode)) {
            queryCondition.setCompanyContractCode(companyContractCode.trim());
        }

        String customerName = request.getParameter("customerName");
        if (StringUtils.isNotEmpty(customerName)) {
            queryCondition.setCustomerName(customerName.trim());
        }

        String templateType = request.getParameter("templateType");
        if (StringUtils.isNotEmpty(templateType)) {
            try {
                queryCondition.setTemplateType(Integer.parseInt(templateType.trim()));
            } catch (Exception e) {

            }
        }

        String userName = request.getParameter("userName");
        if (StringUtils.isNotEmpty(userName)) {
            queryCondition.setUserName(userName.trim());
        }

        String startTime = request.getParameter("startTime");
        if (StringUtils.isNotEmpty(startTime)) {
            queryCondition.setStartTime(startTime.trim());
        }

        String endTime = request.getParameter("endTime");
        if (StringUtils.isNotEmpty(endTime)) {
            queryCondition.setEndTime(endTime.trim());
        }

        String status = request.getParameter("status");
        if (StringUtils.isNotEmpty(status)) {
            try {
                queryCondition.setStatus(Integer.parseInt(status));
            } catch (Exception e) {

            }
        }

        return queryCondition;
    }

    /**
     * 获取当前用户在ibatis sql语句中的roleId
     * 
     * @return
     */
    private Integer getSQLRoleIdOfUser(UserEntity user) {

        Integer roleId = user.getRoleId();

        if (roleId.equals(Constants.ROLE_PROCUREMENT_OFFICER)) {
            // 采购主管，信用专员使用了同一个 sql,统一为 6
            roleId = Constants.ROLE_CREDIT_COMMISSIONER;
        }

        if (roleId.equals(Constants.ROLE_AST_DIRECTOR_OF_OPERATIONS)
                || roleId.equals(Constants.ROLE_GENERAL_MANAGER)) {
            // 信用主管、运营总监助理、总经理使用了同一个 sql,统一为 7
            roleId = Constants.ROLE_CREDIT_CHARGE;
        }
        if (roleId.equals(Constants.ROLE_AREA_MANAGER)
                || roleId.equals(Constants.ROLE_BIGAREA_MANAGER)) {
            // 大区经理 区域经理 使用了同一个 sql,统一为 19
            roleId = Constants.ROLE_AREA_MANAGER;
        }
        return roleId;
    }

    private String getMainAuthorityOfUser(UserEntity user) {
        log.info("设置用户的操作权限");
        if (user == null) {
            return null;
        }

        Integer roleId = user.getRoleId();
        // 销售助理 打印、确认(生效)
        if (roleId == Constants.ROLE_SALES_ASSISTANT) {
            return "printE";
        }

        // 销售经理 修改、删除
        if (roleId == Constants.ROLE_SALES_MANAGER) {
            return "UD";
        }

        // 评审权限 销售总监 法务专员 运营总监
        if (roleId == Constants.ROLE_SALES_DIRECTOR
                || roleId == Constants.ROLE_COMPLIANCE_OFFICER
                || roleId == Constants.ROLE_DIRECTOR_OF_OPERATIONS
                || roleId == Constants.ROLE_REGIONAL_DIRECTOR) {
            return "assess";
        }

        // 查看评审权限 销售总监 法务专员 运营总监助理 运营总监 总经理
        if (roleId == Constants.ROLE_AST_DIRECTOR_OF_OPERATIONS
                || roleId == Constants.ROLE_GENERAL_MANAGER) {
            return "viewA";
        }

        return null;
    }

    /**
     * 销售退货合同预览
     * 
     * @author lilewei
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward salesBackContractPreview(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},销售退货合同预览【开始】", user.getId());

        String salesBackContractId = request.getParameter("backContractId");

        if (StringUtils.isBlank(salesBackContractId)) {
            log.info("User:{},销售退货合同预览【失败】：无效数据", user.getId());
            request.setAttribute("msg", "无效数据");
            return mapping.findForward(Constants.FAIL);
        }

        SalesBackContractDto salesBackContract = salesBackContractService
                .getSalesBackContractById(salesBackContractId.trim());

        if (salesBackContract == null) {
            log.info("User:{},销售退货合同预览【失败】：无效数据", user.getId());
            request.setAttribute("msg", "无效数据");
            return mapping.findForward(Constants.FAIL);
        }

        // 退货合同明细
        request.setAttribute("salesBackContractDetailList", salesBackContractService
                .getAllDetailsOfSalesBackContract(salesBackContractId));
        // 退货金额大写形式
        request.setAttribute("RMB", Util.changeConst(salesBackContract.getMoney()));
        request.setAttribute("salesBackContract", salesBackContract);

        ISalesContractManagementService salesContractManagementService = (ISalesContractManagementService) getBean("salesContractManagementServiceImp");

        // 获得公司信息
        CompanyInfoDto CompanyInfo = salesContractManagementService.getCompanyInfo();

        request.setAttribute("company", CompanyInfo);
        if (user.getRoleId() == Constants.ROLE_SALES_ASSISTANT) {
            request.setAttribute("print", "true");
        } else {
            request.setAttribute("print", "false");
        }
        log.info("User:{},销售退货合同预览【结束】", user.getId());
        return mapping.findForward(Constants.SUCCESS);

    }

    /**
     * 销售退货合同查看
     * 
     * @author lilewei
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward salesBackContractCheck(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},销售退货合同查看【开始】", user.getId());

        String salesBackContractId = request.getParameter("salesContractId");

        if (StringUtils.isBlank(salesBackContractId)) {
            log.info("User:{},销售退货合同查看【失败】：无效数据", user.getId());
            request.setAttribute("msg", "无效数据");
            return mapping.findForward(Constants.FAIL);

        }

        SalesBackContractDto salesBackContract = salesBackContractService
                .getSalesBackContractById(salesBackContractId.trim());

        if (salesBackContract == null) {
            log.info("User:{},销售退货合同查看【失败】：无效数据", user.getId());
            request.setAttribute("msg", "无效数据");
            return mapping.findForward(Constants.FAIL);
        }

        // 退货合同明细
        request.setAttribute("salesBackContractDetailList", salesBackContractService
                .getAllDetailsOfSalesBackContract(salesBackContractId));
        // 分解退货合同的评审意见
        splitSaleBackContractAssessIdea(salesBackContract);
        request.setAttribute("salesBackContract", salesBackContract);
        log.info("User:{},销售退货合同查看【结束】", user.getId());
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 销售退货合同文件下载
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward downloadSalesBackContractFile(ActionMapping mapping,
            ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},销售退货合同文件下载【开始】", user.getId());

        String contactId = request.getParameter("salesBackContractId");
        String fileName = request.getParameter("fileName");
        log.info("fileName:" + fileName);

        if (StringUtils.isBlank(fileName)) {
            log.info("User:{},销售退货合同文件下载【失败】：文件不存在", user.getId());
            request.setAttribute("msg", "销售退货合同文件不存在");
            request.setAttribute("redirect", request.getHeader("Referer")+"&fileError=true");
            return mapping.findForward(Constants.FAIL);
        }

        // 实现文件的下载
        try {
            downFile(fileName, Constants.DIR_NAME,
                    Constants.DOWNLOAD_FILE_NAME_PREFIX_SELL_BACK, request, response);
        } catch (Exception e) {
            log.error("User:{},销售退货合同文件下载【失败】:{}", user.getId(), e);
            request.setAttribute("msg", "下载文件失败");
            request.setAttribute("redirect", request.getHeader("Referer")+"&fileExistError=true");
            return mapping.findForward(Constants.FAIL);
        }

        return null;

    }

    /**
     * 分解销售退货合同的评审意见
     * 
     * @param contract
     */
    private void splitSaleBackContractAssessIdea(SalesBackContractDto contract) {

        if (StringUtils.isNotBlank(contract.getSellMajIdea())
                && contract.getSellMajIdea().length() == 3) {

            String sellMajIdea = contract.getSellMajIdea();
            contract.setSellMajIdea1(sellMajIdea.substring(0, 1));
            contract.setSellMajIdea2(sellMajIdea.substring(1, 2));
            contract.setSellMajIdea3(sellMajIdea.substring(2, 3));

        }
    }

    /**
     *根据销售合同id新增销售退货合同 显示
     * 
     * @author hanrubing
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */

    public ActionForward addSalesBackContractOfShow(ActionMapping mapping,
            ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        /** 销售合同id */
        String id = request.getParameter("id");
        /** 销售合同的信息 */
        SalesBackContractOfShowDto salesBackContractOfShowDto = (SalesBackContractOfShowDto) salesBackContractService
                .getSalesContractOfBack(id);
        salesBackContractOfShowDto.setPlace(Constants.SELL_PLACE);

        SaleBackContractOfAddForm saleBackContractOfAddForm = (SaleBackContractOfAddForm) form;
        saleBackContractOfAddForm.getSaleBackContractOfAddDto().setTemplateType("0");
        /** 库房列表信息 */
        request
                .setAttribute("stockRoomList", salesBackContractService
                        .getStockRoomList());
        request.setAttribute("salesBackContractOfShowDto", salesBackContractOfShowDto);
        request.setAttribute("saleBackContractOfAddForm", saleBackContractOfAddForm);
        saveToken(request);
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     **产品信息 查找
     * 
     * @author hanrubing
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */

    public ActionForward getSalesBackContractOfGoodsInfo(ActionMapping mapping,
            ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        /** 销售合同id */
        String id = request.getParameter("id");
        /** 分页 */
        NewPage newPage = getNewPage(request);
        newPage.setUrl("getSalesBackContractOfGoodsInfo.do");
        newPage.setQuery("id", id);
        request.setAttribute("NewPage", newPage);
        /** 产品的信息 */
        List<Object> goodsInfo = salesBackContractService.getGoodsInfoById(newPage, id);
        request.setAttribute("goodsInfo", goodsInfo);
        request.setAttribute("saleBackId", id);
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 库房收获地址小页面
     * 
     * @author hanrubing
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */

    public ActionForward getStoreRoomAddress(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        /** 库房id */
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        /** 库房收获地址 */
        List<Object> storeRoomAddressList = salesBackContractService.getReceiveInfo(id);
        request.setAttribute("storeRoomAddress", storeRoomAddressList);
        request.setAttribute("name", name);
        request.setAttribute("JsonData", this.getJsonData(storeRoomAddressList));
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     *新增销售退货合同 执行
     * 
     * @author hanrubing
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ActionForward addSalesBackContractOfTransact(ActionMapping mapping,
            ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},销售退货合同 执行【开始】", user.getId());

        /** 销售退货合同form */
        SaleBackContractOfAddForm saleBackContractOfAddForm = (SaleBackContractOfAddForm) form;
        if (isTokenValid(request, true)) {
            resetToken(request);
        } else {
            request.setAttribute("redirect", request.getHeader("Referer"));
            return mapping.findForward(Constants.FAIL);
        }
        /** productTypeId */
        saleBackContractOfAddForm.getSaleBackContractOfAddDto().setProductTypeId(
                request.getParameter("productTypeId"));
        /** 提交类型 */
        String type = request.getParameter("type");
        /** 判断是否是销售经理 */
        if (user.getRoleId() != Constants.ROLE_SALES_MANAGER) {
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&passError=true");
            return mapping.findForward(Constants.FAIL);
        }
        /** 文件名 */
        String fname = null;
        /** 判断模板 */
        if (StringUtils.isBlank(saleBackContractOfAddForm.getSaleBackContractOfAddDto()
                .getTemplateType())) {
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&templateTypeError=true");
            return mapping.findForward(Constants.FAIL);
        } else {
            /** 判断模板 */
            if ("1".equals(saleBackContractOfAddForm.getSaleBackContractOfAddDto()
                    .getTemplateType())) {
                /** 判断文件 */
                if (saleBackContractOfAddForm.getFile().getFileSize() > Constants.MAX_POST_SIZE) {// 大于5兆
                    log.info("User:{},上传文件失败:文件不能大于5兆", user.getId());
                    request.setAttribute("redirect", request.getHeader("Referer")
                            + "&fileMaxError=true&msg="+java.net.URLEncoder.encode(Constants.MAX_POST_SIZE_ERR_MSG,"utf-8").toString());
                    return mapping.findForward(Constants.FAIL);
                }

                fname = loadFile(saleBackContractOfAddForm.getFile(), Constants.DIR_NAME,
                        request);
                System.out.println(fname);
                saleBackContractOfAddForm.getSaleBackContractOfAddDto().setStatus("2");
                if (StringUtils.isBlank(fname)) {
                    request.setAttribute("redirect", request.getHeader("Referer")
                            + "&fileUplaodError=true");
                    return mapping.findForward(Constants.FAIL);
                }
                /** 文件放入dto */
                saleBackContractOfAddForm.getSaleBackContractOfAddDto()
                        .setFileName(fname);
                /** 非模板 && 提交状态 存入法务人员 放入dto */
                if ("submit".equals(type)) {
                    WorkReceiverDto workReceiverDto = common.getUserIdByCondition(null,
                            null, null, Constants.ROLE_COMPLIANCE_OFFICER);
                    saleBackContractOfAddForm
                            .getSaleBackContractOfAddDto()
                            .setLegalId(
                                    workReceiverDto
                                            .getUserIdByRoleId(Constants.ROLE_COMPLIANCE_OFFICER));
                }

            }

        }
        /** 判断时间 */
        if (StringUtils.isBlank(saleBackContractOfAddForm.getSaleBackContractOfAddDto()
                .getBackDate())) {
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&dateError=true");
            return mapping.findForward(Constants.FAIL);
        }
        /** 产品信息 */
        String returnValueToServer = request.getParameter("returnValueToServer");
        org.json.JSONObject json = new JSONObject(returnValueToServer);
        int sum = json.getInt("resultSum");
        /** 判断产品信息 */
        if (sum == 0) {
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&productError=true");
            return mapping.findForward(Constants.FAIL);
        }
        /** 判断退货退款信息 */
        if (StringUtils.isBlank(saleBackContractOfAddForm.getSaleBackContractOfAddDto()
                .getBackWay())) {
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&backWayError=true");
            return mapping.findForward(Constants.FAIL);
        }
        /** 判断收货信息 */
        if (StringUtils.isBlank(saleBackContractOfAddForm.getSaleBackContractOfAddDto()
                .getStockroomAddressId())) {
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&stockroomAddressError=true");
            return mapping.findForward(Constants.FAIL);
        }
        /** 判断原因 */
        if (StringUtils.isBlank(saleBackContractOfAddForm.getSaleBackContractOfAddDto()
                .getText())) {
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&textError=true");
            return mapping.findForward(Constants.FAIL);
        }

        /** 模板 &&　提交状态　存入销售助理 放入dto */
        if ("submit".equals(type)
                && "0".equals(saleBackContractOfAddForm.getSaleBackContractOfAddDto()
                        .getTemplateType())) {
            int productTypeId = -1;
            try {
                productTypeId = Integer.parseInt(request.getParameter("productTypeId"));
            } catch (Exception e) {
                log.error("User:{},销售退货合同 执行【失败】:{}", user.getId(), e);
                request.setAttribute("redirect", request.getHeader("Referer")
                        + "&addError=true");
                return mapping.findForward(Constants.FAIL);
            }
            /** 获取区域总监*/
            WorkReceiverDto workReceiverDto = common.getUserIdByCondition(productTypeId,
                    user.getUserAreaId(), null, Constants.ROLE_REGIONAL_DIRECTOR);
            /** 无区域总监 */
            if (StringUtils.isBlank(workReceiverDto
                    .getUserIdByRoleId(Constants.ROLE_REGIONAL_DIRECTOR))) {
            	 /** 获取销售总监id */
            	workReceiverDto = common.getUserIdByCondition(productTypeId,
                         null, null, Constants.ROLE_SALES_DIRECTOR);
            	 /** 无销售总监id */
                if (StringUtils.isBlank(workReceiverDto
                        .getUserIdByRoleId(Constants.ROLE_SALES_DIRECTOR))) {
                    request.setAttribute("redirect", request.getHeader("Referer")
                            + "&noUserIdError=true");
                    log.info("User:{},销售退货合同 执行【失败】: 销售总监不存在", user.getId());
                    return mapping.findForward(Constants.FAIL);
                }
                saleBackContractOfAddForm.getSaleBackContractOfAddDto().setSellMajId(
                        workReceiverDto.getUserIdByRoleId(Constants.ROLE_SALES_DIRECTOR));
                saleBackContractOfAddForm.getSaleBackContractOfAddDto().setStatus(Constants.BACKCONTRACT_STATUS_SELLMAJWAITCOM.toString());
            }else{
	            saleBackContractOfAddForm.getSaleBackContractOfAddDto().setAreaMajId(
	                    workReceiverDto.getUserIdByRoleId(Constants.ROLE_REGIONAL_DIRECTOR));
	            saleBackContractOfAddForm.getSaleBackContractOfAddDto().setStatus(Constants.BACKCONTRACT_STATUS_AREAMAJWAITCOM.toString());
            }
        }
        if ("save".equals(type)) {
            saleBackContractOfAddForm.getSaleBackContractOfAddDto().setStatus("1");
        }
        /** 销售退货合同id */
        saleBackContractOfAddForm.getSaleBackContractOfAddDto().setId(
                common.getSerialNumber("TXS", "sell_back_contract"));
        /** 时间 */
        saleBackContractOfAddForm.getSaleBackContractOfAddDto().setDatetime(
                sdf.format(new Date()));
        /** 销售经理登录名 */
        saleBackContractOfAddForm.getSaleBackContractOfAddDto().setUserId(user.getId());
        /** 人员名称 */
        saleBackContractOfAddForm.getSaleBackContractOfAddDto().setUserName(
                user.getName());
        /** 人员区域编号 */
        saleBackContractOfAddForm.getSaleBackContractOfAddDto().setUserAreaId(
                user.getUserAreaId().toString());
        BigDecimal totalMoney=new BigDecimal("0");
        for (int i = 0; i < sum; i++) {
            JSONObject rows = json.getJSONArray("rows").getJSONObject(i);
            String backContractCount = rows.getString("backContractCount");// 发货单号
            String sellprice=rows.getString("sellprice");
            totalMoney=totalMoney.add((new BigDecimal(backContractCount).multiply(new BigDecimal(sellprice))));
        }
        saleBackContractOfAddForm.getSaleBackContractOfAddDto().setMoney(totalMoney.toString());
        int isSccess = salesBackContractService.addSaleBackContract(
                saleBackContractOfAddForm.getSaleBackContractOfAddDto(),
                returnValueToServer);
        if (isSccess == 1) {

        } else {
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&addError=true");
            return mapping.findForward(Constants.FAIL);
        }
        /** 预览 */
        if ("look".equals(request.getParameter("additive"))) {
            request.setAttribute("redirect", request.getContextPath()
                    + "/modifySalesBackContractOfShow.do?preview=true&id="
                    + saleBackContractOfAddForm.getSaleBackContractOfAddDto().getId());
            return mapping.findForward(Constants.FAIL);
        }
        log.info("User:{},销售退货合同 执行【结束】", user.getId());
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 获取json格式数据 库房地址
     * 
     * @author hanrubing
     * @param storeRoomAddressList
     * @return String json
     */
    private String getJsonData(List<Object> storeRoomAddressList) {

        String jsonDate = "{resultCount:" + storeRoomAddressList.size() + ",rows:[";
        for (int i = 0; i < storeRoomAddressList.size(); i++) {
            StockroomAndAddressDto storeRoomAddress = (StockroomAndAddressDto) storeRoomAddressList
                    .get(i);
            jsonDate += "{id:" + storeRoomAddress.getStockRoomAddressId() + ",";
            jsonDate += "name:\"" + storeRoomAddress.getGoodsName() + "\",";
            jsonDate += "address:\"" + storeRoomAddress.getGoodsAddress() + "\",";
            jsonDate += "Linkman:\"" + storeRoomAddress.getLinkman() + "\",";
            jsonDate += "stockRoomId:\"" + storeRoomAddress.getStockRoomId() + "\",";
            jsonDate += "Postcode:\"" + storeRoomAddress.getPostcode() + "\",";
            jsonDate += "Tel:\"" + storeRoomAddress.getTel() + "\",";
            jsonDate += "Mobile:\"" + storeRoomAddress.getMobile() + "\"},";
        }
        if (storeRoomAddressList.size() != 0) {
            jsonDate = jsonDate.substring(0, jsonDate.lastIndexOf(","));
        }
        jsonDate += "]}";
        return jsonDate;
    }

    /**
     * 修改销售退货合同 显示
     * 
     * @author hanrubing
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */

    public ActionForward modifySalesBackContractOfShow(ActionMapping mapping,
            ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        /** 销售退货合同id */
        String id = request.getParameter("id");
        /** 销售退货合同信息 */
        SalesBackContractOfShowDto salesBackContractOfShowDto = (SalesBackContractOfShowDto) salesBackContractService
                .getModifySalesContractOfBack(id);
        SaleBackContractOfAddForm saleBackContractOfAddForm = (SaleBackContractOfAddForm) form;
        saleBackContractOfAddForm.getSaleBackContractOfAddDto().setBackWay(
                salesBackContractOfShowDto.getBackWay());
        saleBackContractOfAddForm.getSaleBackContractOfAddDto().setTemplateType(
                salesBackContractOfShowDto.getTemplateType());
        /** 销售退货合同明细信息 */
        List<Object> goodsInfo = salesBackContractService.getGoodsInfoByIdOfShow(id);
        request.setAttribute("goodsInfo", goodsInfo);
        /** 库房列表信息 */
        request
                .setAttribute("stockRoomList", salesBackContractService
                        .getStockRoomList());
        request.setAttribute("salesBackContractOfShowDto", salesBackContractOfShowDto);
        request.setAttribute("saleBackContractOfAddForm", saleBackContractOfAddForm);
        saveToken(request);
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 修改销售退货合同 执行
     * 
     * @author hanrubing
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */

    public ActionForward modifySalesBackContractOfTransact(ActionMapping mapping,
            ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},修改销售退货合同 执行【开始】", user.getId());

        /** 销售退货合同form */
        SaleBackContractOfAddForm saleBackContractOfAddForm = (SaleBackContractOfAddForm) form;
        if (isTokenValid(request, true)) {
            resetToken(request);
        } else {
            request.setAttribute("redirect", request.getHeader("Referer"));
            return mapping.findForward(Constants.FAIL);
        }
        buyContractService = (IBuyContractService) getBean("buyContractServiceImpl");
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("table", "sell_back_contract");
        map.put("id", request.getParameter("salesBackContractId"));
        int status = buyContractService.getStrutsOfAll(map);
        /** 判断是否待确认 */
        if (status != 1 && status != 3 && status != 5 && status != 7 && status != 10 && status != 13) {
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&statusError=true");
            return mapping.findForward(Constants.FAIL);
        }
        saleBackContractOfAddForm.getSaleBackContractOfAddDto().setProductTypeId(
                request.getParameter("productTypeId"));
        /** 提交类型 */
        String type = request.getParameter("type");
        /** 判断是否是销售经理 */
        if (user.getRoleId() != Constants.ROLE_SALES_MANAGER) {
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&passError=true");
            return mapping.findForward(Constants.FAIL);
        }
        /** 文件名 */
        String fname = null;
        /** 判断模板 */
        if (StringUtils.isBlank(saleBackContractOfAddForm.getSaleBackContractOfAddDto()
                .getTemplateType())) {
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&templateTypeError=true");
            return mapping.findForward(Constants.FAIL);
        } else {
            /** 判断模板 */
            if ("1".equals(saleBackContractOfAddForm.getSaleBackContractOfAddDto()
                    .getTemplateType())) {
                /** 判断文件 */
                if (StringUtils.isBlank(request.getParameter("fileName"))) {
                    request.setAttribute("redirect", request.getHeader("Referer")
                            + "&fileError=true");
                    return mapping.findForward(Constants.FAIL);
                }
                /** 判断文件是否改变 */
                if (!request.getParameter("fileName").equals(
                        request.getParameter("oldFile"))) {
                    if (saleBackContractOfAddForm.getFile().getFileSize() > Constants.MAX_POST_SIZE) {// 大于5兆
                        log.info("User:{},上传文件失败:文件不能大于5兆", user.getId());
                        request.setAttribute("redirect", request.getHeader("Referer")
                                + "&fileMaxError=true&msg="+java.net.URLEncoder.encode(Constants.MAX_POST_SIZE_ERR_MSG,"utf-8").toString());
                        return mapping.findForward(Constants.FAIL);
                    }
                    fname = loadFile(saleBackContractOfAddForm.getFile(),
                            Constants.DIR_NAME, request);
                } else {
                    fname = request.getParameter("oldFile");
                }
                System.out.println(fname);
                if (StringUtils.isBlank(fname)) {
                    request.setAttribute("redirect", request.getHeader("Referer")
                            + "&fileUplaodError=true");
                    return mapping.findForward(Constants.FAIL);
                }
                saleBackContractOfAddForm.getSaleBackContractOfAddDto().setStatus("2");
                /** 文件放入dto */
                saleBackContractOfAddForm.getSaleBackContractOfAddDto()
                        .setFileName(fname);
                /** 非模板 && 提交状态 存入法务人员 放入dto */
                if ("submit".equals(type)) {
                    WorkReceiverDto workReceiverDto = common.getUserIdByCondition(null,
                            null, null, Constants.ROLE_COMPLIANCE_OFFICER);
                    saleBackContractOfAddForm
                            .getSaleBackContractOfAddDto()
                            .setLegalId(
                                    workReceiverDto
                                            .getUserIdByRoleId(Constants.ROLE_COMPLIANCE_OFFICER));
                    saleBackContractOfAddForm.getSaleBackContractOfAddDto()
                            .setSubmitType("submit");
                }
            }
        }
        /** 判断时间 */
        if (StringUtils.isBlank(saleBackContractOfAddForm.getSaleBackContractOfAddDto()
                .getBackDate())) {
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&dateError=true");
            return mapping.findForward(Constants.FAIL);
        }
        /** 产品信息 */
        String returnValueToServer = request.getParameter("returnValueToServer");
        org.json.JSONObject json = new JSONObject(returnValueToServer);
        int sum = json.getInt("resultSum");
        /** 判断产品信息 */
        if (sum == 0) {
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&productError=true");
            return mapping.findForward(Constants.FAIL);
        }
        /** 判断退货退款信息 */
        if (StringUtils.isBlank(saleBackContractOfAddForm.getSaleBackContractOfAddDto()
                .getBackWay())) {
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&backWayError=true");
            return mapping.findForward(Constants.FAIL);
        }
        /** 判断收货信息 */
        if (StringUtils.isBlank(saleBackContractOfAddForm.getSaleBackContractOfAddDto()
                .getStockroomAddressId())) {
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&stockroomAddressError=true");
            return mapping.findForward(Constants.FAIL);
        }
        /** 判断原因 */
        if (StringUtils.isBlank(saleBackContractOfAddForm.getSaleBackContractOfAddDto()
                .getText())) {
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&textError=true");
            return mapping.findForward(Constants.FAIL);
        }

        /** 模板 &&　提交状态　存入销售助理 放入dto */
        if ("submit".equals(type)
                && "0".equals(saleBackContractOfAddForm.getSaleBackContractOfAddDto()
                        .getTemplateType())) {
            int productTypeId = -1;
            try {
                productTypeId = Integer.parseInt(request.getParameter("productTypeId"));
            } catch (Exception e) {
                log.error("User:{},修改销售退货合同 执行【失败】:{}", user.getId(), e);
                request.setAttribute("redirect", request.getHeader("Referer")
                        + "&addError=true");
                return mapping.findForward(Constants.FAIL);
            }
            
            /** 获取区域总监*/
            WorkReceiverDto workReceiverDto = common.getUserIdByCondition(productTypeId,
                    user.getUserAreaId(), null, Constants.ROLE_REGIONAL_DIRECTOR);
            /** 无区域总监 */
            if (StringUtils.isBlank(workReceiverDto
                    .getUserIdByRoleId(Constants.ROLE_REGIONAL_DIRECTOR))) {
            	 /** 获取销售总监id */
            	workReceiverDto = common.getUserIdByCondition(productTypeId,
                         null, null, Constants.ROLE_SALES_DIRECTOR);
            	 /** 无销售总监id */
                if (StringUtils.isBlank(workReceiverDto
                        .getUserIdByRoleId(Constants.ROLE_SALES_DIRECTOR))) {
                    request.setAttribute("redirect", request.getHeader("Referer")
                            + "&noUserIdError=true");
                    log.info("User:{},销售退货合同 执行【失败】: 销售总监不存在", user.getId());
                    return mapping.findForward(Constants.FAIL);
                }
                saleBackContractOfAddForm.getSaleBackContractOfAddDto().setSellMajId(
                        workReceiverDto.getUserIdByRoleId(Constants.ROLE_SALES_DIRECTOR));
                saleBackContractOfAddForm.getSaleBackContractOfAddDto().setStatus(Constants.BACKCONTRACT_STATUS_SELLMAJWAITCOM.toString());
            }else{
	            saleBackContractOfAddForm.getSaleBackContractOfAddDto().setAreaMajId(
	                    workReceiverDto.getUserIdByRoleId(Constants.ROLE_REGIONAL_DIRECTOR));
	            saleBackContractOfAddForm.getSaleBackContractOfAddDto().setStatus(Constants.BACKCONTRACT_STATUS_AREAMAJWAITCOM.toString());
            }
            saleBackContractOfAddForm.getSaleBackContractOfAddDto().setSubmitType(
                    "submit");

        }
        if ("save".equals(type)) {
            saleBackContractOfAddForm.getSaleBackContractOfAddDto().setStatus("1");
        }
        /** 销售退货合同id */
        saleBackContractOfAddForm.getSaleBackContractOfAddDto().setId(
                request.getParameter("salesBackContractId"));
        /** 时间 */
        saleBackContractOfAddForm.getSaleBackContractOfAddDto().setDatetime(
                sdf.format(new Date()));
        /** 销售经理登录名 */
        saleBackContractOfAddForm.getSaleBackContractOfAddDto().setUserId(user.getId());
        /** 人员名称 */
        saleBackContractOfAddForm.getSaleBackContractOfAddDto().setUserName(
                user.getName());
        /** 人员区域编号 */
        saleBackContractOfAddForm.getSaleBackContractOfAddDto().setUserAreaId(
                user.getUserAreaId().toString());
        BigDecimal totalMoney=new BigDecimal("0");
        for (int i = 0; i < sum; i++) {
            JSONObject rows = json.getJSONArray("rows").getJSONObject(i);
            String backContractCount = rows.getString("backContractCount");// 发货单号
            String sellprice=rows.getString("sellprice");
            totalMoney=totalMoney.add((new BigDecimal(backContractCount).multiply(new BigDecimal(sellprice))));
        }
        saleBackContractOfAddForm.getSaleBackContractOfAddDto().setMoney(totalMoney.toString());
        int isSccess = salesBackContractService.modifySaleBackContract(
                saleBackContractOfAddForm.getSaleBackContractOfAddDto(),
                returnValueToServer);
        if (isSccess == 1) {

        } else {
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&addError=true");
            return mapping.findForward(Constants.FAIL);
        }
        /** 预览 */
        if ("look".equals(request.getParameter("additive"))) {
            request.setAttribute("redirect", request.getContextPath()
                    + "/modifySalesBackContractOfShow.do?id="
                    + saleBackContractOfAddForm.getSaleBackContractOfAddDto().getId());
            return mapping.findForward(Constants.FAIL);
        }
        log.info("User:{},修改销售退货合同 执行【结束】", user.getId());
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 评审 销售退货合同 显示
     * 
     * @author hanrubing
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */

    public ActionForward commentSalesBackContractOfShow(ActionMapping mapping,
            ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        /** 销售退货合同id */
        String id = request.getParameter("id");
        /** user实体类 */
        UserEntity user = getLoginUserInfo(request);
        /** 销售退货合同信息 */
        SalesBackContractOfShowDto salesBackContractOfShowDto = (SalesBackContractOfShowDto) salesBackContractService
                .getModifySalesContractOfBack(id);
        /** 销售退货合同明细信息 */
        List<Object> goodsInfo = salesBackContractService.getGoodsInfoByIdOfShow(id);
        request.setAttribute("goodsInfo", goodsInfo);
        /** 库房列表信息 */
        request
                .setAttribute("stockRoomList", salesBackContractService
                        .getStockRoomList());
        request.setAttribute("salesBackContractOfShowDto", salesBackContractOfShowDto);
        /** 评审人的级别 */
        String type = null;
        if (user.getRoleId() == Constants.ROLE_SALES_DIRECTOR)
            type = "sell";
        if (user.getRoleId() == Constants.ROLE_DIRECTOR_OF_OPERATIONS)
            type = "ope";
        if (user.getRoleId() == Constants.ROLE_COMPLIANCE_OFFICER)
            type = "legal";
        if (user.getRoleId() == Constants.ROLE_REGIONAL_DIRECTOR){
        	type="area";
        }
        request.setAttribute("type", type);
        saveToken(request);
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 评审 销售退货合同 执行
     * 
     * @author hanrubing
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward commentSalesBackContractOfTransact(ActionMapping mapping,
            ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},销售退货合同评审【开始】", user.getId());

        /** 销售退货合同form */
        SaleBackContractOfAddForm saleBackContractOfAddForm = (SaleBackContractOfAddForm) form;
        if (isTokenValid(request, true)) {
            resetToken(request);
        } else {
            log.info("User:{},销售退货合同评审失败，不能重复提交",user.getId());
            request.setAttribute("redirect", request.getHeader("Referer")+"&isTokenValid=true");
            return mapping.findForward(Constants.FAIL);
        }
        /** 级别类型 */
        String type = request.getParameter("type");
        buyContractService = (IBuyContractService) getBean("buyContractServiceImpl");

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("table", "sell_back_contract");
        map.put("id", saleBackContractOfAddForm.getSaleBackContractOfAddDto().getId());
        int status = buyContractService.getStrutsOfAll(map);
        /** 判断是否合法状态 */
        if ("sell".equals(type)) {
            if (status != 4) {
                request.setAttribute("redirect", request.getHeader("Referer")
                        + "&statusError=true");
                return mapping.findForward(Constants.FAIL);
            }
        }
        if ("ope".equals(type)) {
            if (status != 6) {
                request.setAttribute("redirect", request.getHeader("Referer")
                        + "&statusError=true");
                return mapping.findForward(Constants.FAIL);
            }
        }
        if ("legal".equals(type)) {
            if (status != 2) {
                request.setAttribute("redirect", request.getHeader("Referer")
                        + "&statusError=true");
                return mapping.findForward(Constants.FAIL);
            }
        }
        if ("area".equals(type)) {
            if (status != Constants.BACKCONTRACT_STATUS_AREAMAJWAITCOM) {
                request.setAttribute("redirect", request.getHeader("Referer")
                        + "&statusError=true");
                return mapping.findForward(Constants.FAIL);
            }
        }
        /** 提交类型 */
        String submitType = request.getParameter("submitType");
        /** 区域总监*/
        if("area".equals(type)){
        	if(user.getRoleId()!=Constants.ROLE_REGIONAL_DIRECTOR){
        		 request.setAttribute("redirect", request.getHeader("Referer")
                         + "&roleError=true");
                 return mapping.findForward(Constants.FAIL);
        	}
        	  /** 区域总监 时间和名字 */
            saleBackContractOfAddForm.getSaleBackContractOfAddDto().setAreaMajDate(
                    sdf.format(new Date()));
            saleBackContractOfAddForm.getSaleBackContractOfAddDto().setAreaMajName(
                    user.getName());
            if ("111".equals(saleBackContractOfAddForm.getSaleBackContractOfAddDto()
                    .getAreaMajIdea())
                    && "pass".equals(submitType)) {
            	/** 获取区域总监或销售总监id */
                int productTypeId = -1;
                try {
                    productTypeId = Integer.parseInt(saleBackContractOfAddForm
                            .getSaleBackContractOfAddDto().getProductTypeId());
                } catch (Exception e) {
                    log.error("User:{},销售退货合同评审【失败】:{}", user.getId(), e);
                    request.setAttribute("redirect", request.getHeader("Referer")
                            + "&roleError=true");
                    return mapping.findForward(Constants.FAIL);
                }
            	/** 获取销售总监id */
            	WorkReceiverDto workReceiverDto = common.getUserIdByCondition(productTypeId,
                         null, null, Constants.ROLE_SALES_DIRECTOR);
            	 /** 无销售总监id */
                if (StringUtils.isBlank(workReceiverDto
                        .getUserIdByRoleId(Constants.ROLE_SALES_DIRECTOR))) {
                    request.setAttribute("redirect", request.getHeader("Referer")
                            + "&noUserIdError=true");
                    log.info("User:{},销售退货合同 执行【失败】: 销售总监不存在", user.getId());
                    return mapping.findForward(Constants.FAIL);
                }
                saleBackContractOfAddForm.getSaleBackContractOfAddDto().setSellMajId(
                        workReceiverDto.getUserIdByRoleId(Constants.ROLE_SALES_DIRECTOR));
                saleBackContractOfAddForm.getSaleBackContractOfAddDto().setStatus(Constants.BACKCONTRACT_STATUS_SELLMAJWAITCOM.toString());
            }else{
            	saleBackContractOfAddForm.getSaleBackContractOfAddDto().setStatus(Constants.BACKCONTRACT_STATUS_AREAMAJNOPASS.toString());
            }
        }
        /** 销售总监 */
        if ("sell".equals(type)) {
            if (user.getRoleId() != Constants.ROLE_SALES_DIRECTOR) {
                request.setAttribute("redirect", request.getHeader("Referer")
                        + "&roleError=true");
                return mapping.findForward(Constants.FAIL);
            }
            /** 退货数量是否符合 */
            String sellMajCountIdea = request.getParameter("sellMajCountIdea");
            /** 退货退款方式是否符合 */
            String sellMajQuomodoIdea = request.getParameter("sellMajQuomodoIdea");
            /** 退货地址是否符合 */
            String sellMajAddressIdea = request.getParameter("sellMajAddressIdea");
            if (StringUtils.isEmpty(sellMajCountIdea)
                    || StringUtils.isEmpty(sellMajQuomodoIdea)
                    || StringUtils.isEmpty(sellMajAddressIdea)) {
                request.setAttribute("redirect", request.getHeader("Referer")
                        + "&commentError=true");
                return mapping.findForward(Constants.FAIL);
            }
            /** 销售总监意见 */
            String sellMajIdea = sellMajCountIdea + sellMajQuomodoIdea
                    + sellMajAddressIdea;
            saleBackContractOfAddForm.getSaleBackContractOfAddDto().setSellMajIdea(
                    sellMajIdea);
            /** 销售总监 时间和名字 */
            saleBackContractOfAddForm.getSaleBackContractOfAddDto().setSellMajDate(
                    sdf.format(new Date()));
            saleBackContractOfAddForm.getSaleBackContractOfAddDto().setSellMajName(
                    user.getName());
            /** 销售总监 idea */
            if ("111".equals(sellMajIdea) && "pass".equals(submitType)) {
                /** 销售总监 状态 */
                saleBackContractOfAddForm.getSaleBackContractOfAddDto().setStatus("6");
                /** 获取运营总监id */
                WorkReceiverDto workReceiverDto = common.getUserIdByCondition(null, null,
                        null, Constants.ROLE_DIRECTOR_OF_OPERATIONS);
                /** 获取运营总监id放入dto */
                saleBackContractOfAddForm
                        .getSaleBackContractOfAddDto()
                        .setOpeMajId(
                                workReceiverDto
                                        .getUserIdByRoleId(Constants.ROLE_DIRECTOR_OF_OPERATIONS));
            } else {
                saleBackContractOfAddForm.getSaleBackContractOfAddDto().setStatus("5");
            }
        }
        /** 运营总监 */
        if ("ope".equals(type)) {
            if (user.getRoleId() != Constants.ROLE_DIRECTOR_OF_OPERATIONS) {
                request.setAttribute("redirect", request.getHeader("Referer")
                        + "&roleError=true");
                return mapping.findForward(Constants.FAIL);
            }
            /** 运营总监 时间和名字 */
            saleBackContractOfAddForm.getSaleBackContractOfAddDto().setOpeMajDate(
                    sdf.format(new Date()));
            saleBackContractOfAddForm.getSaleBackContractOfAddDto().setOpeMajName(
                    user.getName());
            /** 运营总监 idea */
            if ("1".equals(saleBackContractOfAddForm.getSaleBackContractOfAddDto()
                    .getOpeMajIdea())
                    && "pass".equals(submitType)) {
            	saleBackContractOfAddForm.getSaleBackContractOfAddDto().setUserAreaId(request.getParameter("userAreaId"));
                /** 运营总监 状态 */
                saleBackContractOfAddForm.getSaleBackContractOfAddDto().setStatus("8");
                /** 产品合同号 */
                saleBackContractOfAddForm.getSaleBackContractOfAddDto()
                        .setProductContractCode(
                                common.getProdContractBackSN(saleBackContractOfAddForm
                                        .getSaleBackContractOfAddDto()
                                        .getProductContractCode(), "sell_back_contract"));
            } else {
                saleBackContractOfAddForm.getSaleBackContractOfAddDto().setStatus("7");
                saleBackContractOfAddForm.getSaleBackContractOfAddDto()
                        .setProductContractCode("");
            }
        }
        /** 法务专员 */
        if ("legal".equals(type)) {
            if (user.getRoleId() != Constants.ROLE_COMPLIANCE_OFFICER) {
                request.setAttribute("redirect", request.getHeader("Referer")
                        + "&roleError=true");
                return mapping.findForward(Constants.FAIL);
            }
            /** 法务专员 时间和名字 */
            saleBackContractOfAddForm.getSaleBackContractOfAddDto().setLegalDate(
                    sdf.format(new Date()));
            saleBackContractOfAddForm.getSaleBackContractOfAddDto().setLegalName(
                    user.getName());
            /** 法务专员 idea */
            if ("1".equals(saleBackContractOfAddForm.getSaleBackContractOfAddDto()
                    .getLegalIdea())
                    && "pass".equals(submitType)) {
                /** 法务专员 状态 */
               // saleBackContractOfAddForm.getSaleBackContractOfAddDto().setStatus("4");
                /** 获取区域总监或销售总监id */
                int productTypeId = -1;
                try {
                    productTypeId = Integer.parseInt(saleBackContractOfAddForm
                            .getSaleBackContractOfAddDto().getProductTypeId());
                } catch (Exception e) {
                    log.error("User:{},销售退货合同评审【失败】:{}", user.getId(), e);
                    request.setAttribute("redirect", request.getHeader("Referer")
                            + "&roleError=true");
                    return mapping.findForward(Constants.FAIL);
                }
               /** 获取合同所在区域编号*/
                Integer areaId=salesBackContractService.getAreaIdBySalesBackContractId(saleBackContractOfAddForm.getSaleBackContractOfAddDto().getId());
                /** 获取区域总监*/
                WorkReceiverDto workReceiverDto = common.getUserIdByCondition(productTypeId,
                		areaId, null, Constants.ROLE_REGIONAL_DIRECTOR);
                /** 无区域总监 */
                if (StringUtils.isBlank(workReceiverDto
                        .getUserIdByRoleId(Constants.ROLE_REGIONAL_DIRECTOR))) {
                	 /** 获取销售总监id */
                	workReceiverDto = common.getUserIdByCondition(productTypeId,
                             null, null, Constants.ROLE_SALES_DIRECTOR);
                	 /** 无销售总监id */
                    if (StringUtils.isBlank(workReceiverDto
                            .getUserIdByRoleId(Constants.ROLE_SALES_DIRECTOR))) {
                        request.setAttribute("redirect", request.getHeader("Referer")
                                + "&noUserIdError=true");
                        log.info("User:{},销售退货合同 执行【失败】: 销售总监不存在", user.getId());
                        return mapping.findForward(Constants.FAIL);
                    }
                    saleBackContractOfAddForm.getSaleBackContractOfAddDto().setSellMajId(
                            workReceiverDto.getUserIdByRoleId(Constants.ROLE_SALES_DIRECTOR));
                    saleBackContractOfAddForm.getSaleBackContractOfAddDto().setStatus(Constants.BACKCONTRACT_STATUS_SELLMAJWAITCOM.toString());
                }else{
    	            saleBackContractOfAddForm.getSaleBackContractOfAddDto().setAreaMajId(
    	                    workReceiverDto.getUserIdByRoleId(Constants.ROLE_REGIONAL_DIRECTOR));
    	            saleBackContractOfAddForm.getSaleBackContractOfAddDto().setStatus(Constants.BACKCONTRACT_STATUS_AREAMAJWAITCOM.toString());
                }
            } else {
                saleBackContractOfAddForm.getSaleBackContractOfAddDto().setStatus("3");
            }
        }
        int isSuccess=salesBackContractService.modifySaleBackContractOfComment(
                saleBackContractOfAddForm.getSaleBackContractOfAddDto(), user);
        if(isSuccess==0){
        	request.setAttribute("redirect", request.getHeader("Referer")
                    + "&commentError=true");
            return mapping.findForward(Constants.FAIL);
        }
        if(isSuccess==2){
        	request.setAttribute("redirect", request.getHeader("Referer")
                    + "&noExistError=true");
            return mapping.findForward(Constants.FAIL);
        }
        log.info("User:{},销售退货合同评审【结束】", user.getId());
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 删除 销售退货合同
     * 
     * @author hanrubing
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteSalesBackContract(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},删除销售退货合同【开始】", user.getId());
        /** 销售退货合同 id */
        String id = request.getParameter("id");

        /** 判断是否为销售经理 */
        if (user.getRoleId() != Constants.ROLE_SALES_MANAGER) {
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&roleError=true");
            return mapping.findForward(Constants.FAIL);
        }
        int isSuccess = salesBackContractService.deleteSalesBackContract(id);
        if (isSuccess == 0) {
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&delError=true");
            return mapping.findForward(Constants.FAIL);
        }
        log.info("User:{},删除销售退货合同【结束】", user.getId());
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 查看评审表
     * 
     * @author hanrubing
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward lookCommentTable(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},查看评审表【开始】", user.getId());

        /** 销售退货合同id */
        String id = request.getParameter("id");
        /** 销售退货合同信息 */
        SalesBackContractOfShowDto salesBackContractOfShowDto = (SalesBackContractOfShowDto) salesBackContractService
                .getCommentTable(id);
        /** 销售退货合同明细信息 */
        List<Object> goodsInfo = salesBackContractService.getGoodsInfoByIdOfShow(id);
        request.setAttribute("goodsInfo", goodsInfo);
        request.setAttribute("salesBackContractOfShowDto", salesBackContractOfShowDto);
        if (user.getRoleId() == Constants.ROLE_SALES_ASSISTANT) {
            request.setAttribute("print", "true");
        } else {
            request.setAttribute("print", "false");
        }
        log.info("User:{},查看评审表【结束】", user.getId());
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
    public ActionForward printCommentTable(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},打印评审表【开始】", user.getId());
        /** 销售退货合同id */
        String id = request.getParameter("id");

        /** 改变状态 */
        int isSuccess = salesBackContractService.modifyCommentTableOfStatus(id, user);
        if (isSuccess == 0) {
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&modifyError=true");
            return mapping.findForward(Constants.FAIL);
        }
        log.info("User:{},打印评审表【结束】", user.getId());
        return null;
    }

    /**
     * 确认销售合同
     * 
     * @author hanrubing
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public ActionForward decideSaleBackContract(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},确认销售合同【开始】", user.getId());
        if (isTokenValid(request, true)) {
            resetToken(request);
        } else {
            request.setAttribute("redirect", request.getHeader("Referer"));
            return mapping.findForward(Constants.FAIL);
        }
        /** 销售退货合同id */
        String id = request.getParameter("saleBackContractOfAddDto.id");
        buyContractService = (IBuyContractService) getBean("buyContractServiceImpl");
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("table", "sell_back_contract");
        map.put("id", id);
        int status = buyContractService.getStrutsOfAll(map);
        /** 判断是否待确认 */
        if (status != 9) {
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&statusError=true");
            return mapping.findForward(Constants.FAIL);
        }

        /** 是否生效 */
        String type = request.getParameter("decideType");
        SaleBackContractOfAddDto saleBackContractOfAddDto = new SaleBackContractOfAddDto();
        if (StringUtils.isEmpty(type)) {
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&decideError=true");
            return mapping.findForward(Constants.FAIL);
        }

        if ("true".equals(type)) {
            saleBackContractOfAddDto.setStatus("11");
            /** 公司合同号 */
            String companyContractCode = request.getParameter("companyContractCode");
            /** 判断公司合同号是否为空 */
            if (StringUtils.isBlank(companyContractCode)) {
                request.setAttribute("redirect", request.getHeader("Referer")
                        + "&codeError=true");
                return mapping.findForward(Constants.FAIL);
            }
            saleBackContractOfAddDto.setCompanyContractCode(companyContractCode.trim());
            /** 判断公司合同号是否存在 */
            List companyContractCodeList = salesBackContractService
                    .getSalesBackContractIsExist(saleBackContractOfAddDto);
            if (companyContractCodeList == null || companyContractCodeList.size() > 0) {
                request.setAttribute("redirect", request.getHeader("Referer")
                        + "&codeExistError=true");
                return mapping.findForward(Constants.FAIL);
            }
            saleBackContractOfAddDto.setCompanyContractCode(companyContractCode);
        } else {
            saleBackContractOfAddDto.setStatus("10");
        }

        saleBackContractOfAddDto.setId(id);
        /** 执行生效 */
        int isSuccess = salesBackContractService
                .modifySaleBackContractOfDecide(saleBackContractOfAddDto);
        if (isSuccess == 0) {
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&decideError=true");
            return mapping.findForward(Constants.FAIL);
        }
        log.info("User:{},确认销售合同【结束】", user.getId());
        return mapping.findForward(Constants.SUCCESS);
    }

}
