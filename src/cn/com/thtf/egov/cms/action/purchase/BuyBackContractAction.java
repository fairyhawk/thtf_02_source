/**
 * ClassName  BuyBackContractAction
 *
 * History
 * Create User: ChenHuajiang
 * Create Date: 2010-6-12
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.action.purchase;

import java.io.PrintWriter;
import java.math.BigDecimal;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.NewBaseAction;
import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.constant.Arith;
import cn.com.thtf.egov.cms.constant.Constants;
import cn.com.thtf.egov.cms.dto.BuyBackContractDto;
import cn.com.thtf.egov.cms.dto.BuyBackContractInfoDto;
import cn.com.thtf.egov.cms.dto.BuyBackContractPreviewDto;
import cn.com.thtf.egov.cms.dto.BuyBackContractPreviewProdDto;
import cn.com.thtf.egov.cms.dto.BuyBackContractReviewDto;
import cn.com.thtf.egov.cms.dto.BuyContractProductInfoDto;
import cn.com.thtf.egov.cms.dto.BuyContractReviewDto;
import cn.com.thtf.egov.cms.dto.SupplierAddressDto;
import cn.com.thtf.egov.cms.dto.WorkReceiverDto;
import cn.com.thtf.egov.cms.entity.BuyBackContractDetailEntity;
import cn.com.thtf.egov.cms.entity.BuyBackContractEntity;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.entity.WorkEntity;
import cn.com.thtf.egov.cms.form.BuyBackContractAddForm;
import cn.com.thtf.egov.cms.form.BuyContractReviewForm;
import cn.com.thtf.egov.cms.form.BuyContractSearchForm;
import cn.com.thtf.egov.cms.iservice.common.ICommonService;
import cn.com.thtf.egov.cms.iservice.purchase.IBuyBackContractService;
import cn.com.thtf.egov.cms.iservice.purchase.IBuyContractService;

/**
 * 采购退货合同
 * 
 * @author ChenHuajiang
 */

public class BuyBackContractAction extends NewBaseAction {
    private static Logger log = LoggerFactory.getLogger(BuyBackContractAction.class);
    private IBuyBackContractService backContractService = null;
    private IBuyContractService buyContractService = null;
    private ICommonService common = (ICommonService) getBean("commonServiceImpl");

    /**
     * 采购退货合同列表
     * 
     * @author ChenHuajiang
     */
    @SuppressWarnings("unchecked")
    public ActionForward getBuyBackContracts(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},采购退货合同列表【开始】", user.getId());

        backContractService = (IBuyBackContractService) getBean("buyBackContractServiceImpl");

        BuyContractSearchForm contractForm = (BuyContractSearchForm) form;

        if (!isValidPermission(user)) {
            log.info("User:{},越权操作", user.getId());
            request.setAttribute("msg", "越权操作");
            return null;
        }

        // 分页
        NewPage page = getNewPage(request);
        page.setUrl("buyBackContractList.do");

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

        // 获取采购退货合同列表
        List list = null;
        list = backContractService.getBuyBackContracts(contractForm, page);

        // 将查询条件加到分页中
        setSearchConditionToPage(contractForm, page);
        request.setAttribute("NewPage", page);
        // 当前用户
        request.setAttribute("user", user);
        // 查询条件
        request.setAttribute("searchForm", contractForm);
        // 产品分类列表
        request.setAttribute("productTypeList", common.getAllProductTypes(null, user
                .getId(), user.getRoleId() + ""));
        // 采购退货合同列表
        request.setAttribute("buyBackContractList", list);
        // 用户可用动作
        request.setAttribute("userAction", setUserAction(user));
        log.info("User:{},采购退货合同列表【结束】", user.getId());
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 新建采购退货合同-采购合同信息
     * 
     * @author ChenHuajiang
     */
    public ActionForward getBuyContractInfo(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("新建采购退货合同-采购合同信息【开始】");
        /* 防止重复提交 */
        this.saveToken(request);
        backContractService = (IBuyBackContractService) getBean("buyBackContractServiceImpl");
        // 采购合同ID
        String buyContractId = request.getParameter("buyContractId");
        // 产品分类ID
        String prodTypeId = request.getParameter("prodTypeId");

        BuyBackContractDto dto = null;
        dto = backContractService.getBuyContractInfoById(buyContractId);
        dto.setPlace(Constants.SELL_PLACE);
        request.setAttribute("buyContractId", buyContractId);
        request.setAttribute("prodTypeId", prodTypeId);
        request.setAttribute("contractDto", dto);

        log.info("新建采购退货合同-采购合同信息【结束】");
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 产品选择
     * 
     * @author ChenHuajiang
     */
    @SuppressWarnings("unchecked")
    public ActionForward getProdInfoById(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},产品选择【开始】", user.getId());

        backContractService = (IBuyBackContractService) getBean("buyBackContractServiceImpl");
        // 采购合同ID
        String id = request.getParameter("buyContractId");

        if (!isValidPermission(user)) {
            log.info("User:{},越权操作", user.getId());
            request.setAttribute("msg", "越权操作");
            return null;
        }

        // 分页
        NewPage page = getNewPage(request);
        page.setUrl("getProdInfoById.do");
        page.setQuery("buyContractId", id);

        HashMap map = new HashMap();
        map.put("buyContractId", id);
        map.put("userId", user.getId());
        map.put("roleId", user.getRoleId());

        // 获取采购退货合同列表
        List list = null;
        list = backContractService.getProdInfoListById(map, page);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        request.setAttribute("date", sdf.format(new Date()));
        request.setAttribute("buyContractId", id);
        request.setAttribute("NewPage", page);
        // 产品列表
        request.setAttribute("prodInfoList", list);
        log.info("User:{},产品选择【结束】", user.getId());
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
        // 采购主管、 法务专员、 运营总监 查看、评审
        if (roleId == Constants.ROLE_PROCUREMENT_OFFICER
                || roleId == Constants.ROLE_COMPLIANCE_OFFICER
                || roleId == Constants.ROLE_DIRECTOR_OF_OPERATIONS) {
            return action += "A";
        }
        return null;
    }

    /**
     * 获取供应商地址信息
     * 
     * @author HanHaiyun
     */
    @SuppressWarnings("unchecked")
    public ActionForward getSupplierAddress(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        backContractService = (IBuyBackContractService) getBean("buyBackContractServiceImpl");
        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        // 分页
        NewPage page = getNewPage(request);
        page.setUrl("getSupplierAddress.do");// 设置翻页路径
        String name = request.getParameter("supplierName");
        if (StringUtils.isNotEmpty(name)) {
            name = name.trim();
        }
        // 得到采购合同编号
        String buyContractId = request.getParameter("buyContractId");
        if (buyContractId == null || "".equals(buyContractId)) {
            return mapping.findForward(Constants.FAIL);
        }
        page.setQuery("buyContractId", buyContractId);// 设置page翻页时参数
        List list = null;
        // 得到供货商所有地址信息
        list = backContractService.getSupplierAddress(name, buyContractId, page);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        page.setQuery("supplierName", name);
        request.setAttribute("date", sdf.format(new Date()));
        request.setAttribute("NewPage", page);
        request.setAttribute("supplierName", name);
        request.setAttribute("supplierAddressList", list);
        log.info("User{},选择供应商结束", user.getId());
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 新建采购退货合同
     * 
     * @author HanHaiyun
     */
    public ActionForward inertBuyBack(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        /* 防止重复提交 */
        if (isTokenValid(request)) {
            resetToken(request);
        } else {
            log.info("User:{},新建采购退货合同失败： 不能重复提交", user.getId());
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&isTokenValid=true");
            return mapping.findForward(Constants.FAIL);
        }
        WorkReceiverDto workReceiverDto = null;
        WorkEntity work = null;// 事务实体
        /** 判断是否产品总监 只有产品总监才有新建采购退货合同权限 */
        if (user.getRoleId() != Constants.ROLE_PRODUCT_DIRECTOR) {
            log.info("User:{},新建采购退货合同失败： 当前用户非产品总监角色", user.getId());
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&roleError=true");
            return mapping.findForward(Constants.FAIL);
        }
        BuyBackContractAddForm bbcaForm = (BuyBackContractAddForm) form;
        BuyBackContractEntity bbce = bbcaForm.getBbce();// 得到表单中的采购退货合同实体
        bbce.setUserId(user.getId());
        bbce.setUserName(user.getName());
        bbce.setDatetime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        bbce.setId(common.getSerialNumber("TCG", "buy_back_contract"));// 得到合同编号
        String templateType = bbce.getTemplateType().toString();// 得到模板类型
        /** 文件名 */
        String fname = null;
        if (!StringUtils.isBlank(templateType) && "1".equals(templateType)) {// 非模版
            /** 判断文件 是否存在 */
            if (bbcaForm.getFile() == null) {
                log.info("User:{},新建采购退货合同失败： 没有得到文件", user.getId());
                request.setAttribute("redirect", request.getHeader("Referer")
                        + "&fileExistError=true");
                return mapping.findForward(Constants.FAIL);
            }
            if (bbcaForm.getFile().getFileSize() > Constants.MAX_POST_SIZE) {// 大于5兆
                log.info("User:{},上传文件失败:" + Constants.MAX_POST_SIZE_ERR_MSG, user
                        .getId());
                request.setAttribute("redirect", request.getHeader("Referer")
                        + "&fileMaxError=true&msg="
                        + java.net.URLEncoder.encode(Constants.MAX_POST_SIZE_ERR_MSG,
                                "utf-8").toString());
                return mapping.findForward(Constants.FAIL);
            }

            // 上传文件，返回上传之后文件的名称
            fname = loadFile(bbcaForm.getFile(), Constants.DIR_NAME, request);
            if (StringUtils.isBlank(fname)) {
                log.info("User:{},新建采购退货合同失败： 文件上传失败", user.getId());
                request.setAttribute("redirect", request.getHeader("Referer")
                        + "&fileError=true");
                return mapping.findForward(Constants.FAIL);
            }
            bbce.setFile(fname);// 保存文件名称
            bbce.setPlace(null);// 清空签订地点
            if (!StringUtils.isBlank(bbce.getStatus().toString())
                    && bbce.getStatus() == 1) {// 合同是提交
                bbce.setStatus(2);// 法务专员待评审状态
                workReceiverDto = common.getUserIdByCondition(null, null, null,
                        Constants.ROLE_COMPLIANCE_OFFICER);// 法务专员
                String userid = workReceiverDto
                        .getUserIdByRoleId(Constants.ROLE_COMPLIANCE_OFFICER);// 获得法务专员对应的用户编号
                if (userid == null || userid == "") {// 判断角色为法务专员的用户是否存在
                    log.info("User:{},新建采购退货合同失败： 没有搜索到法务专员", user.getId());
                    request.setAttribute("redirect", request.getHeader("Referer")
                            + "&compliance=true");
                    return mapping.findForward(Constants.FAIL);
                }
                // 给法务专员添加事务
                work = getTodoWorks(1, userid, Constants.SELL19);
                bbce.setBuyManId(null);
                bbce.setLegalId(userid);// 设置采购退货合同的法务专员为得到的用户
            } else if (!StringUtils.isBlank(bbce.getStatus().toString())
                    && bbce.getStatus() == 0) {// 合同是保存
                bbce.setStatus(1);// 未提交状态
            }
        }
        if (!StringUtils.isBlank(templateType) && "0".equals(templateType)) {// 模板
            if (!StringUtils.isBlank(bbce.getStatus().toString())
                    && bbce.getStatus() == 1) {// 合同是提交
                bbce.setStatus(4);// 采购主管待评审
                String prodTypeId = bbce.getProductTypeId().toString();
                if (prodTypeId == null) {// 判断产品类型id是否存在
                    log.info("User:{},新建采购退货合同失败： 产品类型id不存在", user.getId());
                    request.setAttribute("redirect", request.getHeader("Referer")
                            + "&prodTypeId=true");
                    return mapping.findForward(Constants.FAIL);
                }
                workReceiverDto = common.getUserIdByCondition(new Integer(prodTypeId),
                        null, null, Constants.ROLE_PROCUREMENT_OFFICER);// 采购主管
                String userid = workReceiverDto
                        .getUserIdByRoleId(Constants.ROLE_PROCUREMENT_OFFICER);// 获得采购主管对应的用户编号
                if (userid == null || userid == "") {// 判断采购主管是否存在
                    log.info("User:{},新建采购退货合同失败： 没有搜索到与之对应 的采购主管", user.getId());
                    request.setAttribute("redirect", request.getHeader("Referer")
                            + "&principal=true");
                    return mapping.findForward(Constants.FAIL);
                }
                // 给采购主管添加事务
                work = getTodoWorks(1, userid, Constants.SELL19);
                bbce.setBuyManId(userid);
                bbce.setLegalId(null);
            } else {
                bbce.setStatus(1);
            }
        }
        if (StringUtils.isBlank(bbce.getBuyContractId())) {// 采购合同判断：采购退货合同必须与之对应一个采购合同
            log.info("User:{},新建采购退货合同失败： 没有与之对应的采购合同", user.getId());
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&buyContractId=true");
            return mapping.findForward(Constants.FAIL);
        }
        String money = bbce.getMoney().toString();// 采购退货总金额//采购退货合同金额必须大于0
        if (StringUtils.isBlank(money) || "".equals(money)
                || Double.parseDouble(money) <= 0) {
            log.info("User:{},新建采购退货合同失败： 采购退货合同金额小于等于0或为空", user.getId());
            request
                    .setAttribute("redirect", request.getHeader("Referer")
                            + "&money=true");
            return mapping.findForward(Constants.FAIL);
        }
        // 判断产品编号是否存在
        if (bbcaForm.getProdid() == null || bbcaForm.getProdid().length <= 0) {
            log.info("User:{},新建采购退货合同失败： 获取产品编号信息失败", user.getId());
            request
                    .setAttribute("redirect", request.getHeader("Referer")
                            + "&prods=true");
            return mapping.findForward(Constants.FAIL);
        }
        // 遍历得到所有的采购退货合同明细信息
        List<BuyBackContractDetailEntity> bbcdes = new ArrayList<BuyBackContractDetailEntity>();
        BigDecimal totalMoney=new BigDecimal("0");
        for (int i = 0; i < bbcaForm.getProdid().length; i++) {
            BuyBackContractDetailEntity bbcde = new BuyBackContractDetailEntity();
            bbcde.setBuyBackContractId(bbce.getId());// 设置采购退货合同明细中所对应的退货合同编号
            bbcde.setCount(new Integer(bbcaForm.getCount()[i]));
            bbcde.setProductId(new Integer(bbcaForm.getProdid()[i]));
            totalMoney=totalMoney.add((new BigDecimal(bbcaForm.getPrice()[i])).multiply(new BigDecimal(bbcaForm.getCount()[i])));
            bbcdes.add(bbcde);// 将明细添加到集合
        }
        bbce.setMoney(totalMoney);
        // 添加采购退货合同信息
        boolean isInsert = backContractService.insertBuyBackContract(bbce, bbcdes, work);
        if (!isInsert) {
            log.info("User:{},新建采购退货合同失败： 持久化采购退货合同信息失败", user.getId());
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&isInsert=true");
            return mapping.findForward(Constants.FAIL);
        }
        log.info("User:{},新建采购退货合同成功", user.getId());
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 跳转采购退货合同修改、查看界面
     * 
     * @author HanHaiyun
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward returnBackContractModifyPage(ActionMapping mapping,
            ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        BuyBackContractInfoDto buyBackContractInfoDto = null;
        List<BuyContractProductInfoDto> buyContractProductInfoDtos = null;
        BuyBackContractDto dto = null;
        // 获得采购退货合同编号
        String buyBackContractId = request.getParameter("buyBackContractId");
        String target = request.getParameter("targetPage");// 获得需要跳转的目标界面类型
        if (StringUtils.isBlank(buyBackContractId) || "".equals(buyBackContractId)) {
            log.info("User:{},界面跳转失败： 采购退货合同编号不存在", user.getId());
            return mapping.findForward(Constants.FAIL);
        }
        try {
            // 得到采购退货合同信息
            buyBackContractInfoDto = backContractService
                    .getBuyBackContract(buyBackContractId);
            if (buyBackContractInfoDto != null
                    && buyBackContractInfoDto.getBuyContractId() != null) {
                // 得到退货产品信息
                buyContractProductInfoDtos = backContractService
                        .getBuyBackContractProducts(buyBackContractId,
                                buyBackContractInfoDto.getBuyContractId());
                SupplierAddressDto supplierAddressDto = null;
                dto = backContractService.getBuyContractInfoById(buyBackContractInfoDto
                        .getBuyContractId());
                supplierAddressDto = backContractService
                        .getSupplierAddressById(buyBackContractInfoDto
                                .getSupplierAddressId());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                buyBackContractInfoDto.setDateTime(sdf.format(new Date()));
                request.setAttribute("contractDto", dto);
                request.setAttribute("supplierAddress", supplierAddressDto);
                request.setAttribute("prods", buyContractProductInfoDtos);
                request.setAttribute("buyBackContract", buyBackContractInfoDto);
            } else {
                log.info("User:{},界面跳转失败：采购合同编号或退货合同编号为空", user.getId());
                return mapping.findForward(Constants.FAIL);
            }
        } catch (Exception e) {
            log.error("User:{},界面跳转失败:{}", user.getId(), e);
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("modify", "modify");// 修改界面
        map.put("view", "view");// 查看界面
        map.put("review", "review");// 评审界面
        map.put("confirm", "confirm");// 确认界面
        if (target != null && map.containsKey(target)) {
            /* 防止重复提交 */
            this.saveToken(request);
            log.info("User:{},界面跳转成功", user.getId());
            if (map.get(target).equals("modify")) {
                dto.setPlace(Constants.SELL_PLACE);
            }
            return mapping.findForward(map.get(target));
        }
        log.info("User:{},跳转失败：没有找到你要跳转的界面", user.getId());
        return mapping.findForward(Constants.FAIL);
    }

    /**
     * @author HanHaiyun 修改采购退货合同
     */
    public ActionForward modifyBuyBackContract(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        /* 防止重复提交 */
        if (isTokenValid(request, true)) {
            resetToken(request);
        } else {
            log.info("User:{},修改采购退货合同失败： 不能重复提交", user.getId());
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&isTokenValid=true");
            return mapping.findForward(Constants.FAIL);
        }
        BuyBackContractAddForm bbcaForm = (BuyBackContractAddForm) form;
        BuyBackContractEntity bbce = bbcaForm.getBbce();// 采购退货合同实体
        String templateType = bbce.getTemplateType().toString();// 获取模板类型
        WorkReceiverDto workReceiverDto = null;
        WorkEntity work = null;// 事务实体
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("table", "buy_back_contract");
        map.put("id", bbce.getId());
        // 采购合同列表
        buyContractService = (IBuyContractService) getBean("buyContractServiceImpl");
        int status = buyContractService.getStrutsOfAll(map);
        /** 判断是否待未提交 */
        if (status == 2 || status == 4 || status == 6 || status == 8 || status == 9
                || status == 11) {
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&statusError=true");
            return mapping.findForward(Constants.FAIL);
        }
        /** 判断是否产品总监 */
        if (user.getRoleId() != Constants.ROLE_PRODUCT_DIRECTOR) {
            log.info("User:{},修改采购退货合同失败： 当前用户非产品总监角色", user.getId());
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&roleError=true");
            return mapping.findForward(Constants.FAIL);
        }
        /** 文件名 */
        String fname = null;
        if (!StringUtils.isBlank(templateType) && "1".equals(templateType)) {// 非模版
            /** 判断文件 */
            if (bbcaForm.getFile() == null) {
                log.info("User:{},修改采购退货合同失败： 没有得到文件", user.getId());
                request.setAttribute("redirect", request.getHeader("Referer")
                        + "&fileExistError=true");
                return mapping.findForward(Constants.FAIL);
            }
            // 判断用户是否修改了文件 空则表示没有修改
            if ("".equals(bbcaForm.getFile().getFileName())) {
                String fileName = request.getParameter("fileHidden");// 得到以前的文件名
                fname = fileName;
            } else {
                if (bbcaForm.getFile().getFileSize() > Constants.MAX_POST_SIZE) {// 大于5兆
                    log.info("User:{},上传文件失败:" + Constants.MAX_POST_SIZE_ERR_MSG, user
                            .getId());
                    request.setAttribute("redirect", request.getHeader("Referer")
                            + "&fileMaxError=true&msg="
                            + java.net.URLEncoder.encode(Constants.MAX_POST_SIZE_ERR_MSG,
                                    "utf-8").toString());
                    return mapping.findForward(Constants.FAIL);
                }
                fname = loadFile(bbcaForm.getFile(), Constants.DIR_NAME, request);
            }
            if (StringUtils.isBlank(fname)) {
                log.info("User:{},修改采购退货合同失败： 文件上传失败", user.getId());
                request.setAttribute("redirect", request.getHeader("Referer")
                        + "&fileError=true");
                return mapping.findForward(Constants.FAIL);
            }
            bbce.setFile(fname);
            bbce.setPlace(null);
            if (!StringUtils.isBlank(bbce.getStatus().toString())
                    && bbce.getStatus() == 1) {// 合同是提交
                bbce.setStatus(2);// 法务专员待评审
                workReceiverDto = common.getUserIdByCondition(null, null, null,
                        Constants.ROLE_COMPLIANCE_OFFICER);// 法务专员
                String userid = workReceiverDto
                        .getUserIdByRoleId(Constants.ROLE_COMPLIANCE_OFFICER);// 获得法务专员对应的用户编号
                if (userid == null || userid == "") {// 判断法务专员是否存在
                    log.info("User:{},修改采购退货合同失败： 法务专员不存在", user.getId());
                    request.setAttribute("redirect", request.getHeader("Referer")
                            + "&compliance=true");
                    return mapping.findForward(Constants.FAIL);
                }
                // 给法务专员添加事务
                work = getTodoWorks(1, userid, Constants.SELL19);
                bbce.setBuyManId(null);
                bbce.setLegalId(userid);
            } else {
                bbce.setStatus(1);
            }
        }
        if (!StringUtils.isBlank(templateType) && "0".equals(templateType)) {// 模板
            if (!StringUtils.isBlank(bbce.getStatus().toString())
                    && bbce.getStatus() == 1) {// 合同是提交
                bbce.setStatus(4);// 采购主管待评审

                String prodTypeId = bbce.getProductTypeId().toString();
                if (prodTypeId == null) {// 判断产品类型id是否存在
                    log.info("User:{},修改采购退货合同失败： 产品类型不存在", user.getId());
                    request.setAttribute("redirect", request.getHeader("Referer")
                            + "&prodTypeId=true");
                    return mapping.findForward(Constants.FAIL);
                }
                workReceiverDto = common.getUserIdByCondition(new Integer(prodTypeId),
                        null, null, Constants.ROLE_PROCUREMENT_OFFICER);// 采购主管
                String userid = workReceiverDto
                        .getUserIdByRoleId(Constants.ROLE_PROCUREMENT_OFFICER);// 获得采购主管对应的用户编号
                if (userid == null || userid == "") {// 判断采购主管是否存在
                    log.info("User:{},修改采购退货合同失败： 采购主管不存在", user.getId());
                    request.setAttribute("redirect", request.getHeader("Referer")
                            + "&principal=true");
                    return mapping.findForward(Constants.FAIL);
                }
                // 给采购主管添加事务
                work = getTodoWorks(1, userid, Constants.SELL19);
                bbce.setBuyManId(userid);
                bbce.setLegalId(null);
            } else {
                bbce.setStatus(1);
            }
        }
        if (StringUtils.isBlank(bbce.getBuyContractId())) {// 采购合同判断
            log.info("User:{},修改采购退货合同失败： 没有对应的采购合同", user.getId());
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&buyContractId=true");
            return mapping.findForward(Constants.FAIL);
        }
        String money = bbce.getMoney().toString();// 采购退货总金额
        if (StringUtils.isBlank(money) || "".equals(money) || Double.valueOf(money) <= 0) {
            log.info("User:{},修改采购退货合同失败： 采购退货合同总额小于等于0或为空", user.getId());
            request
                    .setAttribute("redirect", request.getHeader("Referer")
                            + "&money=true");
            return mapping.findForward(Constants.FAIL);
        }
        // 判断产品编号是否存在
        if (bbcaForm.getProdid() == null || bbcaForm.getProdid().length <= 0) {
            log.info("User:{},修改采购退货合同失败： 采购主管不存在", user.getId());
            request
                    .setAttribute("redirect", request.getHeader("Referer")
                            + "&prods=true");
            return mapping.findForward(Constants.FAIL);
        }
        // 遍历得到所有的采购退货合同明细信息
        List<BuyBackContractDetailEntity> bbcdes = new ArrayList<BuyBackContractDetailEntity>();
       BigDecimal totalMoney=new BigDecimal("0");
        for (int i = 0; i < bbcaForm.getProdid().length; i++) {
            BuyBackContractDetailEntity bbcde = new BuyBackContractDetailEntity();
            bbcde.setBuyBackContractId(bbce.getId());
            bbcde.setCount(new Integer(bbcaForm.getCount()[i]));
            bbcde.setProductId(new Integer(bbcaForm.getProdid()[i]));
            totalMoney=totalMoney.add((new BigDecimal(bbcaForm.getPrice()[i])).multiply(new BigDecimal(bbcaForm.getCount()[i])));
            bbcdes.add(bbcde);
        }
        bbce.setMoney(totalMoney);
        // 添加采购退货合同信息
        boolean isInsert = backContractService.updBuyBackContract(bbce, bbcdes, work);
        if (!isInsert) {
            log.info("User:{},修改采购退货合同失败： 更新采购退货合同失败", user.getId());
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&isInsert=true");
            return mapping.findForward(Constants.FAIL);
        }
        log.info("User:{},修改采购退货合同成功", user.getId());
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 跳转采购退货合同预览界面
     * 
     * @author HanHaiyun
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward returnBackContractPreview(ActionMapping mapping,
            ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        String buyBackContractId = request.getParameter("buyBackContractId");
        if (StringUtils.isBlank(buyBackContractId)) {
            log.info("User:{},采购退货合同预览失败", user.getId());
            return mapping.findForward(Constants.FAIL);
        }
        BuyBackContractPreviewDto buyBackContractPreviewDto = backContractService
                .getBackContractProview(buyBackContractId);
        List<BuyBackContractPreviewProdDto> prods = backContractService
                .getBackContractProviewProds(buyBackContractId);
        if (prods == null || prods.size() <= 0) {
            log.info("User:{},采购退货合同预览失败", user.getId());
            return mapping.findForward(Constants.FAIL);
        }
        Double totalMoney = 0.0;// 产品总额
        // 遍历集合得到产品总额
        for (int i = 0; i < prods.size(); i++) {
            totalMoney += prods.get(i).getBackContractProdCount()
                    * Double.parseDouble(prods.get(i).getBuyContractPrice().toString());
        }
        // 将小写金额转换为大写金额
        String totalMoenyUp = Arith.Const(totalMoney.toString());
        request.setAttribute("totalMoney", totalMoenyUp);// 保存到request中
        request.setAttribute("backContractPreviewDto", buyBackContractPreviewDto);
        request.setAttribute("prods", prods);
        if ("true".equals(request.getParameter("print"))) {
            request.setAttribute("print", "true");
        }
        log.info("User:{},采购退货合同预览结束", user.getId());
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 采购合同评审
     * 
     * @author HanHaiyun
     */
    public ActionForward getBackBuyContractsReview(ActionMapping mapping,
            ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        /* 防止重复提交 */
        if (isTokenValid(request, true)) {
            resetToken(request);
        } else {
            log.info("User:{},评审采购退货合同失败： 不能重复提交", user.getId());
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&isTokenValid=true");
            return mapping.findForward(Constants.FAIL);
        }
        UserEntity buySpecialist = null;// 采购专员
        BuyContractReviewForm buyContractReviewForm = (BuyContractReviewForm) form;
        BuyContractReviewDto buyContractReviewDto = buyContractReviewForm
                .getBuyContractReviewDto();
        WorkReceiverDto workReceiverDto = null;
        try {
            if (user.getRoleId() == Constants.ROLE_COMPLIANCE_OFFICER) {// 当前用户为法务专员
                workReceiverDto = common.getUserIdByCondition(new Integer(
                        buyContractReviewDto.getProdTypeId()), null, null,
                        Constants.ROLE_PROCUREMENT_OFFICER);// 采购主管
                String buyManId = workReceiverDto
                        .getUserIdByRoleId(Constants.ROLE_PROCUREMENT_OFFICER);// 获取采购主管的id
                if (buyManId == null || "".equals(buyManId)) {
                    log.info("User:{},采购退货合同评审【失败】：采购主管不存在", user.getId());
                    request.setAttribute("redirect", request.getHeader("Referer")
                            + "&procurementOfficerError=true");
                    return mapping.findForward(Constants.FAIL);
                }
                buyContractReviewDto.setBuyManId(buyManId);
            } else if (user.getRoleId() == Constants.ROLE_PROCUREMENT_OFFICER) {// 当前用户为采购主管
                workReceiverDto = common.getUserIdByCondition(null, null, null,
                        Constants.ROLE_DIRECTOR_OF_OPERATIONS);// 获得运营总监
                String opeMajId = workReceiverDto
                        .getUserIdByRoleId(Constants.ROLE_DIRECTOR_OF_OPERATIONS);// 获取运营总监的id
                if (opeMajId == null || "".equals(opeMajId)) {
                    log.info("User:{},采购退货合同评审【失败】：运营总监不存在", user.getId());
                    request.setAttribute("redirect", request.getHeader("Referer")
                            + "&directorOfOperationsError=true");
                    return mapping.findForward(Constants.FAIL);
                }
                buyContractReviewDto.setOpeMajId(opeMajId);
                buyContractReviewDto
                        .setOpeMajRoleId(Constants.ROLE_DIRECTOR_OF_OPERATIONS);
            } else if (user.getRoleId() == Constants.ROLE_DIRECTOR_OF_OPERATIONS) {// 用户身份为运营总监
                // 根据采购合同编号获取对应的采购专员
                buySpecialist = backContractService.getBuySpecialist(buyContractReviewDto
                        .getId());
                if (buySpecialist == null) {
                    log.info("User:{},采购退货合同评审【失败】：采购专员不存在", user.getId());
                    request.setAttribute("redirect", request.getHeader("Referer")
                            + "&buySpecialistError=true");
                    return mapping.findForward(Constants.FAIL);
                }
                // 生成产品合同号
                buyContractReviewDto.setProdContractCode(common.getProdContractBackSN(
                        buyContractReviewDto.getProdContractCode(), "buy_back_contract"));
            }
            // 采购退货合同评审
            backContractService.buyBackContractReview(buyContractReviewDto, user,
                    buyContractReviewDto.getStatus(), buySpecialist);
        } catch (Exception e) {
            log.error("User:{},采购退货合同评审失败:{}", user.getId(), e);
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&reviewError=true");
            return mapping.findForward(Constants.FAIL);
        }
        log.info("User:{},采购退货合同评审结束", user.getId());
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 删除退货采购合同
     * 
     * @author HanHaiyun
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward delBuyBackContract(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        String buyBackContractId = request.getParameter("buyBackContractId");
        try {
            if (buyBackContractId == null || "".equals(buyBackContractId)) {
                return mapping.findForward(Constants.FAIL);
            }
            backContractService.delBackContractById(buyBackContractId);

        } catch (Exception e) {
            log.error("User:{},采购退货合同删除开始失败:{}", user.getId(), e);
        }
        log.info("User:{},采购退货合同删除开始结束", user.getId());
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 采购退货合同文件下载
     * 
     * @author HanHaiyun
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward downloadBuyBackContractFile(ActionMapping mapping,
            ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        ;
        String fileName = request.getParameter("fileName");
        log.info("fileName:" + fileName);

        if (StringUtils.isBlank(fileName)) {
            log.info("User:{},采购退货合同文件下载失败：文件名为空", user.getId());
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&notExistError=true");
            return mapping.findForward(Constants.FAIL);
        }
        // 实现文件的下载
        try {
            downFile(fileName, Constants.DIR_NAME,
                    Constants.DOWNLOAD_FILE_NAME_PREFIX_BUY_BACK, request, response);
        } catch (Exception e) {
            log.error("User:{},采购退货合同文件下载失败:{}", user.getId(), e);
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&downLoadError=true");
            return mapping.findForward(Constants.FAIL);
        }
        log.info("User:{},采购退货合同文件下载结束", user.getId());
        return null;
    }

    /**
     * 采购退货合同评审查看
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward selBuyBackContractReview(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        BuyBackContractReviewDto buyBackContractReviewDto = null;

        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        try {
            String buyBackContractId = request.getParameter("buyBackContractId");
            buyBackContractReviewDto = backContractService
                    .getBackContractReview(buyBackContractId);
            List<BuyBackContractPreviewProdDto> prods = backContractService
                    .getBackContractReviewProds(buyBackContractId);
            request.setAttribute("prods", prods);
            request.setAttribute("reviewDto", buyBackContractReviewDto);
            if ("true".equals(request.getParameter("print"))) {
                request.setAttribute("print", "true");
            }
        } catch (Exception e) {
            log.error("User:{},采购退货合同评审查看失败:{}", user.getId(), e);
            request.setAttribute("redirect", "wrong404.jsp");
            mapping.findForward(Constants.FAIL);
        }
        log.info("User:{},采购退货合同评审查看结束", user.getId());
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * @author HanHaiyun 采购退货合同打印状态修改
     * */
    public ActionForward upBuyBackContractStatus(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserEntity user = getLoginUserInfo(request);
        try {
            // 得到当前登录用户
            UserEntity userEntity = getLoginUserInfo(request);
            String status = request.getParameter("status");
            String buyBackContractId = request.getParameter("buyBackContractId");
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            // 判断状态是否存在
            if (status == null || "".equals(status)) {
                log.error("User:{},更新采购退货合同状态失败,状态不存在或为空", user.getId());
                writer.write("false");
                writer.close();
                return null;
            }
            // 判断退货合同编号是否存在
            if (buyBackContractId == null || "".equals(buyBackContractId)) {
                log.error("User:{},更新采购退货合同状态失败,退货合同编号不存在或为空", user.getId());
                writer.write("false");
                writer.close();
                return null;
            }
            // 判断当前用户角色是否为采购专员
            if (userEntity.getRoleId() == Constants.ROLE_PROCUREMENT_COMMISSIONER) {
                boolean b = backContractService.upBuyBackContractStatus(new Integer(
                        status), buyBackContractId, userEntity);
                if (b) {
                    writer.write("true");
                } else {
                    log.error("User:{},更新采购退货合同状态失败,数据插入数据库失败", user.getId());
                    writer.write("false");
                }
                writer.close();
                return null;
            } else {
                log.error("User:{},更新采购退货合同状态失败,当前用户所属角色并非采购专员", user.getId());
                writer.write("false");
                writer.close();
            }
            log.info("User:{},更新采购退货合同状态结束", user.getId());
        } catch (Exception e) {
            log.error("User:{},更新采购退货合同状态失败:{} ", user.getId(), e);
        }
        return null;
    }

    /**
     * @author HanHaiyun 采购退货合同确认
     */
    public ActionForward buyBackContractConfirm(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 得到当前登录用户
        UserEntity userEntity = getLoginUserInfo(request);
        String companyContractCode = null;// 公司合同号
        String subType = request.getParameter("subType");// 提交类型：1,生效 0,取消
        if (subType == null || "".equals(subType)) {
            log.error("User{},采购退货合同确认失败,状态不存在或为空", userEntity.getId());
            return null;
        }
        Integer buyStatus = new Integer(subType);
        if ("1".equals(subType)) {
            /* 防止重复提交 */
            if (isTokenValid(request, true)) {
                resetToken(request);
            } else {
                log.info("User:{},采购退货合同确认失败： 不能重复提交", userEntity.getId());
                request.setAttribute("redirect", request.getHeader("Referer")
                        + "&isTokenValid=true");
                return mapping.findForward(Constants.FAIL);
            }
            companyContractCode = request.getParameter("companyContractCode");
            if ((companyContractCode == null || "".equals(companyContractCode)
                    && buyStatus != 10)) {
                log.error("User{},采购退货合同确认失败,退货合同编号不存在或为空", userEntity.getId());
                return mapping.findForward(Constants.FAIL);
            }
        }
        String buyBackContractId = request.getParameter("buyBackContractId");
        // 判断状态是否存在
        buyStatus = buyStatus == 0 ? 10 : (buyStatus == 1 ? 11 : 0);
        if (buyStatus == 0) {
            return mapping.findForward(Constants.FAIL);
        }
        // 判断退货合同编号是否存在
        if (buyBackContractId == null || "".equals(buyBackContractId)) {
            log.error("User{},采购退货合同确认失败,退货合同编号不存在或为空", userEntity.getId());
            return mapping.findForward(Constants.FAIL);
        }

        // 判断当前用户角色是否为采购专员
        if (userEntity.getRoleId() == Constants.ROLE_PROCUREMENT_COMMISSIONER) {
            boolean b = backContractService.buyBackContractConfirm(buyStatus,
                    companyContractCode, buyBackContractId);
            if (!b) {
                log.error("User{},采购退货合同确认失败, 数据插入数据库失败", userEntity.getId());
                return mapping.findForward(Constants.FAIL);
            }

        } else {
            log.error("User{},采购退货合同确认失败,当前用户所属角色并非采购专员", userEntity.getId());
            return mapping.findForward(Constants.FAIL);
        }
        log.info("User{},采购退货合同确认结束", userEntity.getId());
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 判断公司共同号是否存在
     * 
     * @author HanHaiyun
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward isExistsBuyBackContractCode(ActionMapping mapping,
            ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        UserEntity user = getLoginUserInfo(request);
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        // 得到需要搜索的公司合同号
        String companyContractCode = request.getParameter("companyContractCode");
        if ((companyContractCode == null || "".equals(companyContractCode))) {
            log.error("User:{},搜索公司合同号失败", user.getId());
            writer.write("null");
            writer.close();
            return null;
        }
        boolean bool = backContractService
                .isExistsCompanyContractCode(companyContractCode);
        if (bool) {
            writer.write("true");
            writer.close();
        } else {
            writer.write("false");
            writer.close();
        }
        log.info("User:{},搜索公司合同号为是否存在结束", user.getId());
        return null;
    }

    /**
     * 添加事务
     * 
     * @author HanHaiyun
     * @param count
     *            事务个数
     * @param userId
     *            用户Id
     * @param workId
     *            事务Id
     * @return 是否添加成功
     */
    private WorkEntity getTodoWorks(Integer count, String userId, Integer workId) {
        WorkEntity work = null;
        work = new WorkEntity();
        work.setCount(count);
        work.setUserId(userId);
        work.setWorkId(workId);
        return work;
    }

}
