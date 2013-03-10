package cn.com.thtf.egov.cms.action.credit;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
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
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.NewBaseAction;
import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.constant.Constants;
import cn.com.thtf.egov.cms.dto.CustomerCreditLimitDto;
import cn.com.thtf.egov.cms.entity.CreditTypeEntity;
import cn.com.thtf.egov.cms.entity.CreditTypeLimitEntity;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.form.CustomerCreditForm;
import cn.com.thtf.egov.cms.iservice.common.ICommonService;
import cn.com.thtf.egov.cms.iservice.credit.ICreditService;
import cn.com.thtf.egov.cms.util.Util;

public class CreditCustomerLimitAction extends NewBaseAction {

    private static Logger log = LoggerFactory.getLogger(CreditCustomerLimitAction.class);
    private ICommonService commonService = null;

    /**
     * 客户信用列表
     * 
     * @author 李乐伟
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    @SuppressWarnings("unchecked")
    public ActionForward customerCreditManage(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {

        // 获取当前登陆用户
        UserEntity user = getLoginUserInfo(request);

        ICreditService creditService = (ICreditService) getBean("customerCreditService");

        // 检索条件
        String customerName = request.getParameter("customerName");
        String productTypeId = request.getParameter("productTypeId");
        String creditTypeId = request.getParameter("creditTypeId");
        String creditLimit = request.getParameter("creditLimit");
        String arterm = request.getParameter("arterm");
        String enable = request.getParameter("enable");

        CustomerCreditLimitDto dtoCustomerCredit = new CustomerCreditLimitDto();

        dtoCustomerCredit.setUserId(user.getId());
        dtoCustomerCredit.setRoleId(user.getRoleId());
        // 销售经理
        if (user.getRoleId() == Constants.ROLE_SALES_MANAGER) {
            dtoCustomerCredit.setRoleId(user.getRoleId());
        }
        //大区经理、区域经理
        if(user.getRoleId()==Constants.ROLE_BIGAREA_MANAGER||
        	user.getRoleId()==Constants.ROLE_AREA_MANAGER){
        	dtoCustomerCredit.setRoleId(1920);
        }
        // 销售助理
        if (user.getRoleId() == Constants.ROLE_SALES_ASSISTANT) {
            dtoCustomerCredit.setRoleId(user.getRoleId());
        }
        // 销售总监、信用专员、采购专员、区域总监、产品总监、采购主管负责产品分类
        if (user.getRoleId() == Constants.ROLE_SALES_DIRECTOR
                || user.getRoleId() == Constants.ROLE_CREDIT_COMMISSIONER
                || user.getRoleId() == Constants.ROLE_PROCUREMENT_COMMISSIONER
                || user.getRoleId() == Constants.ROLE_REGIONAL_DIRECTOR
                || user.getRoleId() == Constants.ROLE_PRODUCT_DIRECTOR
                || user.getRoleId() == Constants.ROLE_PROCUREMENT_OFFICER) {
            dtoCustomerCredit.setRoleId(5681011);// 负责产品分类的角色，使用一个角色值
        }
        // 区域总监能看到区域与自己相同的所有销售经理负责的客户
        // 区域总监能看到区域与自己相同的，自己负责产品分类的合同
        if (user.getRoleId() == Constants.ROLE_REGIONAL_DIRECTOR) {
            dtoCustomerCredit.setRoleId(9);
            dtoCustomerCredit.setUserAreaId(user.getUserAreaId());
        }

        // 分页
        NewPage page = getNewPage(request);
        page.setUrl("customerCreditManage.do");

        // 检索条件 customerName
        if (StringUtils.isNotBlank(customerName)) {
            customerName = customerName.trim();
            dtoCustomerCredit.setCustomerName(customerName);
            page.setQuery("customerName", customerName);
        }
        // 检索条件 客户信用状态
        if (StringUtils.isNotBlank(enable)) {
            dtoCustomerCredit.setEnable(enable.trim());
            page.setQuery("enable", enable);
        }
        // 检索条件 productTypeId
        if (StringUtils.isNotBlank(productTypeId)) {
            dtoCustomerCredit.setProductTypeId(Integer.parseInt(productTypeId.trim()));
            page.setQuery("productTypeId", productTypeId);
        }
        // 检索条件
        try {
            if (StringUtils.isNotBlank(creditTypeId)) {
                dtoCustomerCredit.setCreditTypeId(Integer.parseInt(creditTypeId.trim()));
                page.setQuery("creditTypeId", creditTypeId);
            }
            // 检索条件 帐期
            if (StringUtils.isNotBlank(arterm)) {
                dtoCustomerCredit.setArterm(Integer.parseInt(arterm.trim()));
                page.setQuery("arterm", arterm);
            }
            // 检索条件 信用额度
            if (StringUtils.isNotBlank(creditLimit)) {

                double doubleLimit = Double.parseDouble(creditLimit);
                dtoCustomerCredit.setCreditLimit(new BigDecimal(doubleLimit));
                page.setQuery("creditLimit", creditLimit);
            }
        } catch (NumberFormatException e) {
            log.error("User:{},非法检索条件:{} ", user.getId(), e);
            request.setAttribute("msg", "非法检索条件 !");
            return mapping.findForward("customerCreditList");
        }

        // 保存当前用户的权限到request (修改或查看权限)
        request.setAttribute("authority", hasModifyAuthority(user.getRoleId()) ? "modify"
                : "check");
        // 由条件检索
        List<CustomerCreditLimitDto> creditCustomerList = creditService
                .findAllCustomerCreditByCondition(dtoCustomerCredit, page);
        // 信用类型
        request.setAttribute("creditTypeList", creditService.findAllCreditType());

        /* 产品分类 */
        commonService = (ICommonService) getBean("commonServiceImpl");
        List list = null;
        if (user.getRoleId() == Constants.ROLE_CREDIT_CHARGE) {// 信用主管不受产品分类限制
            list = commonService.getAllProductTypes(null, null, null);
        } else {
            list = commonService.getAllProductTypes(null, user.getId(), user.getRoleId()
                    + "");
        }

        request.setAttribute("productTypeList", list);
        // 由条件检索的全部客户信用
        request.setAttribute("customerCreditList", creditCustomerList);
        dtoCustomerCredit.setCustomerName(customerName);
        // 用于保存页面的检索条件
        request.setAttribute("customerCredit", dtoCustomerCredit);
        request.setAttribute("NewPage", page);

        return mapping.findForward("customerCreditList");
    }

    /**
     * 显示要修改的客户信用
     * 
     * @author 李乐伟
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward showCustomerCredit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {

        // 获取当前登陆用户
        UserEntity user = getLoginUserInfo(request);
        ICreditService creditService = (ICreditService) getBean("customerCreditService");
        // 取要修改信用的id
        String customerCreditId = request.getParameter("customerCreditId");
        // 客户id
        String customerId = request.getParameter("customerId");

        if (StringUtils.isNotBlank(customerCreditId)) {

            try {
                // 根据id查找要修改的客户信用
                CustomerCreditLimitDto dtoCustomerCredit = creditService
                        .findCustomerCreditLimitById(customerCreditId.trim());

                // 总可分配额度
                CustomerCreditLimitDto totalLimt = creditService
                        .getTotalClimtByProIdCrdtId(dtoCustomerCredit);
                dtoCustomerCredit.setDistributeCredit(dtoCustomerCredit.getCreditLimit()
                        .add(totalLimt.getDistributeCredit()));
                if (null != dtoCustomerCredit) {
                    // 全部信用类型列表
                    request.setAttribute("creditTypeList", creditService
                            .findAllCreditType());
                    // 要显示的客户信用
                    request.setAttribute("customerCredit", dtoCustomerCredit);
                    // 客户ID
                    request.setAttribute("customerId", customerId);
                    return mapping.findForward(Constants.SUCCESS);
                }
            } catch (Exception e) {
                log.error("User:{},获取客户信用失败:{} ", user.getId(), e);
                return mapping.findForward(Constants.FAIL);
            }
        }
        return mapping.findForward(Constants.FAIL);
    }

    /**
     * 客户选择
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    @SuppressWarnings("unchecked")
    public ActionForward selectCustomer(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {

        // 获取当前登陆用户
        UserEntity user = getLoginUserInfo(request);

        // 权限检查
        boolean hasAuthority = hasModifyAuthority(((UserEntity) getLoginUserInfo(request))
                .getRoleId());

        if (!hasAuthority) {
            log.info("User:{},越权操作 ", user.getId());
            request.setAttribute("msg", "越权操作 !");
            return mapping.findForward("customerCreditList");
        }
        ICreditService creditService = (ICreditService) getBean("customerCreditService");
        // 检索条件 客户名称
        String customerName = request.getParameter("customerName");
        // 分页
        NewPage page = getNewPage(request);

        if (StringUtils.isNotBlank(customerName)) {
            customerName = customerName.trim();
            page.setQuery("customerName", customerName);
        }

        page.setUrl("selectCustomer.do");

        // 检索条件
        Map conditionMap = new HashMap();
        conditionMap.put("userId", user.getId());
        conditionMap.put("customerName", customerName);

        try {
            // 原页面值
            request.setAttribute("customerName", customerName);
            request.setAttribute("NewPage", page);
            // 查询结果
            request.setAttribute("customerList", creditService.findAllCustomerOfUser(
                    conditionMap, page));
        } catch (Exception e) {
            log.error("User:{},客户选择失败:{} ", user.getId(), e);
            request.setAttribute("msg", "客户选择错误 !");
            return mapping.findForward(Constants.FAIL);
        }

        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 显示添加客户信用页面
     * 
     * @author 李乐伟
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward 添加客户信用页面
     */
    @SuppressWarnings("unchecked")
    public ActionForward forwardNewCustomerCreditPage(ActionMapping mapping,
            ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        ICreditService creditService = (ICreditService) getBean("customerCreditService");
        // 信用类型列表
        request.setAttribute("creditTypeList", creditService.findAllCreditType());
        // 当前用户
        UserEntity user = getLoginUserInfo(request);
        /* 产品分类 */
        commonService = (ICommonService) getBean("commonServiceImpl");
        List list = null;
        if (user.getRoleId() == Constants.ROLE_CREDIT_CHARGE) {// 信用主管不受产品分类限制
            list = commonService.getAllProductTypes(null, null, null);
        } else {
            list = commonService.getAllProductTypes(null, user.getId(), user.getRoleId()
                    + "");
        }

        request.setAttribute("productTypeList", list);

        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 客户信用查看
     * 
     * @author 李乐伟
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */

    public ActionForward viewCustomerCredit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {

        ICreditService creditService = (ICreditService) getBean("customerCreditService");

        String cid = request.getParameter("customerCreditId");
        // 由唯一id查询客户信用
        request.setAttribute("customerCredit", creditService
                .findCustomerCreditLimitById(cid));

        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 添加客户信用
     * 
     * @author 李乐伟
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */

    @SuppressWarnings("unchecked")
    public ActionForward addCustomerCredit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        // 权限检查
        boolean hasAuthority = hasModifyAuthority(((UserEntity) getLoginUserInfo(request))
                .getRoleId());

        if (!hasAuthority) {
            log.info("User:{},越权操作", user.getId());
            request.setAttribute("msg", "越权操作 !");
            return mapping.findForward("customerCreditList");
        }

        ICreditService creditService = (ICreditService) getBean("customerCreditService");
        // 新建的客户信用
        CustomerCreditLimitDto newCredit = getCustomerCreditFromActionForm(form);
        int creditTypeId = newCredit.getCreditTypeId();

        try {
            // 信用类型是否为项目
            boolean isProject = newCredit.getCreditTypeId().equals(Integer.valueOf(5));
            // 判断客户信用是否存在
            if (!isProject && creditService.isCustomerCreditExist(newCredit)) {
                // 保存原页面数据
                setErrorMsgAndPageData("添加失败：客户信用已存在", request, form);
                log.info("User:{},添加失败：客户信用已存在", user.getId());
                return mapping.findForward(Constants.FAIL);
            }

            if (creditTypeId == 5) {// 信用类型为项目
                HashMap map = new HashMap();
                map.put("customerId", newCredit.getCustomerId());
                map.put("projectName", newCredit.getProjectName());
                newCredit.setAgreementCode("");
                // 查找是否有相同项目名称
                boolean isExist = creditService.isExistProjName(map);
                if (isExist) {
                    log.info("User:{},修改失败: 项目名称已存在", user.getId());
                    setErrorMsgAndPageData("修改失败: 项目名称已存在", request, form);
                    request.setAttribute("projectName", newCredit.getProjectName());
                    return mapping.findForward(Constants.FAIL);
                }
            }else if(creditTypeId==4){
            	newCredit.setProjectName("");
            	
            }

            // 全部可分配额度
            CustomerCreditLimitDto distributeLimtTotal = creditService
                    .getTotalClimtByProIdCrdtId(newCredit);

            if (distributeLimtTotal == null) {
                log.info("User:{},添加客户信用失败:无效的客户信用", user.getId());
                setErrorMsgAndPageData("添加客户信用失败:无效的客户信用", request, form);
                return mapping.findForward(Constants.FAIL);
            }

            // 信用额度合法检查
            if (isValidClimt(distributeLimtTotal, newCredit)) {
                // 设置新的客户信用的最后更新人,更新时间,状态
                setCustomerCreditLastModifyUser(newCredit, request);
                newCredit.setDatetime(Util.getDateTime());
                newCredit.setEnable("1");
                // 同步更新总额度数据
                CreditTypeLimitEntity creditType = new CreditTypeLimitEntity();
                // 信用类型
                creditType.setCreditTypeId(newCredit.getCreditTypeId());
                // 产品类型
                creditType.setProductTypeId(newCredit.getProductTypeId());
                // 信用额度
                creditType.setTimeStamp(new Timestamp(System.currentTimeMillis()));

                List updateData = new ArrayList();
                updateData.add(newCredit);
                updateData.add(creditType);

                boolean isSuccess = creditService.insertCustomerCreditLimit(updateData);

                if (isSuccess) {
                    return mapping.findForward(Constants.SUCCESS);
                } else {
                    log.info("User:{},添加客户信用失败", user.getId());
                    setErrorMsgAndPageData("添加客户信用失败 !", request, form);
                    if (creditTypeId == 5) {// 信用类型为项目
                        request.setAttribute("projectName", newCredit.getProjectName());
                    }
                    return mapping.findForward(Constants.FAIL);
                }
            } else {
                // 保存原页面数据
                setErrorMsgAndPageData("添加失败：信用类型额度不足!", request, form);
                if (creditTypeId == 5) {// 信用类型为项目
                    request.setAttribute("projectName", newCredit.getProjectName());
                }
                return mapping.findForward(Constants.FAIL);
            }

        } catch (Exception e) {
            log.info("User:{},添加客户信用失败:{}", user.getId(), e);
            setErrorMsgAndPageData("添加客户信用失败 !", request, form);
            if (creditTypeId == 5) {// 信用类型为项目
                request.setAttribute("projectName", newCredit.getProjectName());
            }
            return mapping.findForward(Constants.FAIL);
        }
    }

    /**
     * 修改客户信用
     * 
     * @author 李乐伟
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    @SuppressWarnings("unchecked")
    public ActionForward modifyCustomerCredit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {

        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        // 权限检查
        boolean hasAuthority = hasModifyAuthority(((UserEntity) getLoginUserInfo(request))
                .getRoleId());

        if (!hasAuthority) {
            log.info("User:{},越权操作", user.getId());
            request.setAttribute("msg", "越权操作 !");
            return mapping.findForward("customerCreditList");
        }

        ICreditService creditService = (ICreditService) getBean("customerCreditService");
        // 要修改的客户信用
        CustomerCreditLimitDto newCredit = getCustomerCreditFromActionForm(form);
        System.out.println(newCredit.getText());
        setCustomerCreditLastModifyUser(newCredit, request);
        // 修改时间
        newCredit.setDatetime(Util.getDateTime());
        try {
            int creditTypeId = newCredit.getCreditTypeId();
            if (creditTypeId == 5) {// 信用类型为项目
                String flag = request.getParameter("flag");
                if (flag.equals("0")) {// flag=0表示项目名称已做修改
                    HashMap map = new HashMap();
                    map.put("customerId", newCredit.getCustomerId());
                    map.put("projectName", newCredit.getProjectName());

                    // 查找是否有相同项目名称
                    boolean isExist = creditService.isExistProjName(map);
                    if (isExist) {
                        log.info("User:{},修改失败: 项目名称已存在", user.getId());
                        setErrorMsgAndPageData("修改失败: 项目名称已存在", request, form);
                        request.setAttribute("customerId", newCredit.getCustomerId());
                        return mapping.findForward(Constants.FAIL);
                    }
                }
            }
            if(creditTypeId != 4){
            	newCredit.setAgreementCode("");
            	
            }
            // 原信用额度
            CustomerCreditLimitDto oldCredit = creditService
                    .findCustomerCreditLimitById(newCredit.getId() + "");
            // 可分配额度
            CustomerCreditLimitDto totalLimt = creditService
                    .getTotalClimtByProIdCrdtId(newCredit);

            if (totalLimt == null) {
                setErrorMsgAndPageData("修改失败：可分配额度为空 ", request, form);
                log.info("User:{},修改失败: 可分配额度为空", user.getId());
                return mapping.findForward(Constants.FAIL);
            }

            oldCredit.setDistributeCredit(totalLimt.getDistributeCredit());

            oldCredit = creditService.findCustomerCreditLimitById(newCredit.getId() + "");

            // if (oldCredit == null) {
            // log.info("User:{},修改失败: 无效的客户信用", user.getId());
            // setErrorMsgAndPageData("修改失败: 无效的客户信用", request, form);
            // return mapping.findForward(Constants.FAIL);
            // }

            // 合法的信用额度
            // if ("1".equals(newCredit.getEnable())) {
            // if (!isValidClimt(oldCredit, newCredit)) {
            // // 保存原页面值
            // setErrorMsgAndPageData("修改失败：信用额度必须大于已用额度与冻结额度之和 ", request,
            // form);
            // log.info("User:{},修改失败：信用额度必须大于已用额度与冻结额度之和", user.getId());
            // return mapping.findForward(Constants.FAIL);
            // }
            // }

            // 同步更新的总额度
            CreditTypeLimitEntity creditType = new CreditTypeLimitEntity();
            creditType.setCreditTypeId(newCredit.getCreditTypeId());
            creditType.setProductTypeId(newCredit.getProductTypeId());
            creditType.setTimeStamp(new Timestamp(System.currentTimeMillis()));

            List updateData = new ArrayList();
            updateData.add(newCredit);
            updateData.add(creditType);
            // 客户信用修改更新
            boolean isSuccess = creditService.updateCustomerCreditLimit(updateData);

            if (isSuccess) {
                return mapping.findForward(Constants.SUCCESS);
            } else {
                log.info("User:{},修改失败：该记录已被其他用户修改", user.getId());
                setErrorMsgAndPageData("修改失败：该记录已被其他用户修改", request, form);
                return mapping.findForward(Constants.FAIL);
            }

        } catch (Exception e) {
            log.error("User:{},修改客户信用失败:{}", user.getId(), e);
            setErrorMsgAndPageData("修改失败", request, form);
            return mapping.findForward(Constants.FAIL);
        }
    }

    /**
     * 验证客户信用的信用额度是否合法
     * 
     * @author 李乐伟
     * @param oldCredit
     *            老的额度
     * @param newCredit
     *            新的额度
     * @return true 合法 false 不合法
     */
    private boolean isValidClimt(CustomerCreditLimitDto oldCredit,
            CustomerCreditLimitDto newCredit) {

        boolean isValidClimt = false;
        // 新额度
        double newClimt = newCredit.getCreditLimit().doubleValue();
        // 老额度
        double oldClimt = oldCredit.getCreditLimit().doubleValue();
        // 可分配额度
        double distributeClimt = oldCredit.getDistributeCredit().doubleValue();
        // 检查合法性
        isValidClimt = newClimt <= (oldClimt + distributeClimt);

        if (oldCredit.getUsedCredit() != null && oldCredit.getLockCredit() != null) {
            double lockClimit = oldCredit.getLockCredit().doubleValue();
            double usedClimit = oldCredit.getUsedCredit().doubleValue();
            isValidClimt = isValidClimt && (newClimt >= (lockClimit + usedClimit));
        }

        return isValidClimt;
    }

    /**
     * 删除客户信用
     * 
     * @author 李乐伟
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward removeCustomerCredit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        // 权限检查
        boolean hasAuthority = hasModifyAuthority(((UserEntity) getLoginUserInfo(request))
                .getRoleId());

        if (!hasAuthority) {
            log.info("User:{},越权操作", user.getId());
            request.setAttribute("msg", "越权操作 !");
            return mapping.findForward("customerCreditList");
        }

        ICreditService creditService = (ICreditService) getBean("customerCreditService");
        // 要删除客户信用的id
        String customerCreditId = request.getParameter("customerCreditId");
        String creditTypeId = request.getParameter("cid");
        String productTypeId = request.getParameter("pid");
        String timeStamp = request.getParameter("stamp");

        // 要删除客户信用
        CustomerCreditLimitDto customerCredit = new CustomerCreditLimitDto();
        try {

            customerCredit.setId(Integer.parseInt(customerCreditId));
            customerCredit.setTimeStamp(new Timestamp(Long.parseLong(timeStamp)));
            customerCredit.setProductTypeId(Integer.parseInt(productTypeId));
            customerCredit.setCreditTypeId(Integer.parseInt(creditTypeId));
            // 删除客户信用的已用额度
            Double usedCredit = creditService.getUsedCreditById(customerCreditId);

            if (usedCredit != null && usedCredit == 0) {
                // 客户信用没有被使用过,可以删除
                boolean isSuccess = creditService
                        .deleteCustomerCreditLimit(customerCredit);

                if (!isSuccess) {
                    log.info("User:{},删除失败：客户信用已被使用", user.getId());
                    request.setAttribute("msg", "删除失败：客户信用已被使用");
                }
            } else {

                if (usedCredit == null) {
                    // 数据错误
                    log.info("User:{},删除失败：数据异常", user.getId());
                    request.setAttribute("msg", "删除失败：数据异常");
                } else {
                    log.info("User:{},删除失败：客户信用已被使用", user.getId());
                    // 客户信用被使用过,不可删除
                    request.setAttribute("msg", "删除失败：客户信用已被使用");
                }
            }
        } catch (Exception e) {
            log.error("User:{},删除客户信用失败:{}", user.getId(), e);
            request.setAttribute("msg", "删除客户信用失败");
        }
        // 返回列表页面
        return mapping.findForward("customerCreditList");
    }

    /**
     * 根据productTypeId和creditTypeId 来查找可分配额度
     * 
     * @author 李乐伟
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward getCanDistributeLimit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        ICreditService creditService = (ICreditService) getBean("customerCreditService");
        // 页面产品类型id
        String productTypeId = request.getParameter("productTypeId");
        // 页面信用类型id
        String creditTypeId = request.getParameter("creditTypeId");

        // 页面值合法性检查
        if ((StringUtils.isNotBlank(productTypeId))
                && (StringUtils.isNotBlank(creditTypeId))) {

            PrintWriter writer = null;

            try {
                writer = response.getWriter();
                Integer pid = Integer.parseInt(productTypeId.trim());
                Integer cid = Integer.parseInt(creditTypeId.trim());
                // dto传输页面数据
                CustomerCreditLimitDto dto = new CustomerCreditLimitDto();
                dto.setProductTypeId(pid);
                dto.setCreditTypeId(cid);
                // 查询可分配额度
                CustomerCreditLimitDto result = creditService
                        .getTotalClimtByProIdCrdtId(dto);
                // 可分配额度写回页面
                writer.write(result != null ? result.getDistributeCredit().doubleValue()
                        + "" : "0");
                writer.close();

            } catch (NumberFormatException e) {
                log.error("User:{},非法的产品分类ID或信用类型ID:{}", user.getId(), e);
                writer.write("0");
            } catch (Exception e) {
                log.error("User:{},可分配额度不存在:{}", user.getId(), e);
                writer.write("0");
            } finally {
                // 关闭writer
                if (writer != null) {
                    writer.close();
                }
            }
        }
        return null;
    }

    /**
     * 从CustomerCreditForm中取得 CustomerCredit
     * 
     * @author 李乐伟
     * @param form
     * @return CustomerCredit 页面的CustomerCredit
     */
    private CustomerCreditLimitDto getCustomerCreditFromActionForm(ActionForm form) {
        // 自定义form
        CustomerCreditForm cForm = (CustomerCreditForm) form;

        CustomerCreditLimitDto customerCredit = new CustomerCreditLimitDto();
        // 页面值
        customerCredit.setId(cForm.getId());
        customerCredit.setCustomerName(cForm.getCustomerName());
        customerCredit.setProductTypeName(cForm.getProductTypeName());
        customerCredit.setCustomerId(cForm.getCustomerId());
        customerCredit.setProductTypeId(cForm.getProductTypeId());
        customerCredit.setCreditTypeId(cForm.getCreditTypeId());
        customerCredit.setProjectName(cForm.getProjectName().replace("null", ""));
        customerCredit.setAgreementCode(cForm.getAgreementCode());
        customerCredit.setText(cForm.getText());
        // 信用类型
        if (customerCredit.getCreditTypeId() != null
                && customerCredit.getCreditTypeId().equals(Integer.valueOf(5))) {
            customerCredit.setProjectName(cForm.getProjectName());
        }
        customerCredit.setArterm(cForm.getArterm());
        // 信用额度
        if (cForm.getCreditLimit() != null) {
            customerCredit.setCreditLimit(new BigDecimal(cForm.getCreditLimit()));
        }

        // 冻结额度
        if (cForm.getLockCredit() != null) {
            customerCredit.setLockCredit(new BigDecimal(cForm.getLockCredit()));
        }
        // 状态
        customerCredit.setEnable(cForm.getEnable() + "");
        customerCredit.setTimeStamp(cForm.getTimeStamp());
        // 可分配额度
        if (null != cForm.getDistributeCredit()) {
            customerCredit
                    .setDistributeCredit(new BigDecimal(cForm.getDistributeCredit()));
        }
        return customerCredit;
    }

    /**
     * 设置客户信用的最后更新人和更新时间
     * 
     * @author 李乐伟
     * @param customerCredit
     * @param request
     */
    private void setCustomerCreditLastModifyUser(CustomerCreditLimitDto customerCredit,
            HttpServletRequest request) {
        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        customerCredit.setUserId(user.getId());
        customerCredit.setUserName(user.getName());
    }

    /**
     * 当前用户的权限判断
     * 
     * @author 李乐伟
     * @param roleId
     * @return ture 当前用户具有增,删,改权限 false 只能查
     */
    private boolean hasModifyAuthority(Integer roleId) {
        // 修改、删除权限 信用主管，信用专员
        if (Constants.ROLE_CREDIT_COMMISSIONER == roleId
                || Constants.ROLE_CREDIT_CHARGE == roleId) {
            return true;
        }

        // 查看权限 销售助理、销售经理、销售总监、采购专员、区域总监、
        // 产品总监、采购主管、库房主管、法务专员、运营总监助理、
        // 运营总监、总经理、大区经理、区域经理
        if (Constants.ROLE_SALES_MANAGER == roleId
                || Constants.ROLE_SALES_ASSISTANT == roleId
                || Constants.ROLE_SALES_DIRECTOR == roleId
                || Constants.ROLE_PROCUREMENT_COMMISSIONER == roleId
                || Constants.ROLE_REGIONAL_DIRECTOR == roleId
                || Constants.ROLE_PRODUCT_DIRECTOR == roleId
                || Constants.ROLE_PROCUREMENT_OFFICER == roleId
                || Constants.ROLE_TREASURY_HEAD == roleId
                || Constants.ROLE_COMPLIANCE_OFFICER == roleId
                || Constants.ROLE_AST_DIRECTOR_OF_OPERATIONS == roleId
                || Constants.ROLE_DIRECTOR_OF_OPERATIONS == roleId
                || Constants.ROLE_GENERAL_MANAGER == roleId
                || Constants.ROLE_BIGAREA_MANAGER == roleId
                || Constants.ROLE_AREA_MANAGER == roleId) {
            return false;
        }
        // 其他用户
        return false;
    }

    /**
     * 设置页面错误消息
     * 
     * @param msg
     *            错误信息
     * @param request
     * @param form
     */
    @SuppressWarnings("unchecked")
    private void setErrorMsgAndPageData(String msg, HttpServletRequest request,
            ActionForm form) {
        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        ICreditService creditService = (ICreditService) getBean("customerCreditService");
        // 页面原值
        CustomerCreditLimitDto dto = getCustomerCreditFromActionForm(form);
        request.setAttribute("msg", msg);
        request.setAttribute("customerCredit", dto);
        request.setAttribute("customerName", request.getParameter("customerName"));

        try {
            // 全部信用类型
            if (null != dto.getProductTypeId())
                request.setAttribute("creditTypeList", creditService.findAllCreditType());

        } catch (Exception e) {
            log.error("User:{},获取信用类型列表失败:{}", user.getId(), e);
        }

        try {
            /* 产品分类 */
            commonService = (ICommonService) getBean("commonServiceImpl");
            List list = null;
            if (user.getRoleId() == Constants.ROLE_CREDIT_CHARGE) {// 信用主管不受产品分类限制
                list = commonService.getAllProductTypes(null, null, null);
            } else {
                list = commonService.getAllProductTypes(null, user.getId(), user
                        .getRoleId()
                        + "");
            }
            request.setAttribute("productTypeList", list);
        } catch (Exception e) {
            log.error("User:{},获取产品类型列表失败:{}", user.getId(), e);
        }
    }

    /**
     * 取得产品类型下已分配信用额度的信用类型ajax 下拉联动
     * 
     * @author 李乐伟
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */

    public ActionForward getAllCreditTypeOfProduct(ActionMapping mapping,
            ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        // 产品类型id
        String stringPid = request.getParameter("productTypeId");
        // 合法的产品类型id
        if (StringUtils.isNotBlank(stringPid)) {

            ICreditService creditService = (ICreditService) getBean("customerCreditService");

            try {

                Integer pid = Integer.parseInt(stringPid);
                // 查询全部的分配了额度的信用类型
                List<CreditTypeEntity> allCreditType = creditService
                        .findAllHasClimtCreditTypeByPid(pid);

                JSONArray jsonCreditTypeList = new JSONArray();
                // 将 查到的所有类型转换成json格式
                for (int i = 0; i < allCreditType.size(); i++) {
                    JSONObject jsonCreditType = new JSONObject();
                    jsonCreditType.put("id", allCreditType.get(i).getId());
                    jsonCreditType.put("name", allCreditType.get(i).getName());
                    jsonCreditTypeList.put(jsonCreditType);
                }
                // 将json串写回客户端
                response.setCharacterEncoding("UTF-8");
                PrintWriter writer = response.getWriter();
                writer.write(jsonCreditTypeList.toString());
                writer.close();

            } catch (Exception e) {
                log.error("非法的产品类型id", e);
            }
        }
        return null;
    }
}
