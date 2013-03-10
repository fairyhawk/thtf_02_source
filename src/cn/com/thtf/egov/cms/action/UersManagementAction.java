/**
 * ClassName  UersManagementAction
 *
 * History
 * Create User: chen
 * Create Date: 2009年12月21日
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import cn.com.thtf.egov.cms.application.BaseAction;
import cn.com.thtf.egov.cms.application.ListRange;
import cn.com.thtf.egov.cms.constant.Constants;
import cn.com.thtf.egov.cms.dto.UersSearchInfoDto;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.iservice.IAddCustomerService;
import cn.com.thtf.egov.cms.iservice.IAllCustomerSearchService;
import cn.com.thtf.egov.cms.iservice.ISalesAssistantAddBeforeCheck1Service;
import cn.com.thtf.egov.cms.iservice.ISalesAssistantAddBeforeCheckService;
import cn.com.thtf.egov.cms.iservice.ISalesAssistantAddBeforeService;
import cn.com.thtf.egov.cms.iservice.ITreasuryManagerAddBeforeService;
import cn.com.thtf.egov.cms.iservice.IUserAdd1Service;
import cn.com.thtf.egov.cms.iservice.IUserAddService;
import cn.com.thtf.egov.cms.iservice.IUserChangeBeforeService;
import cn.com.thtf.egov.cms.iservice.IUserChangeService;
import cn.com.thtf.egov.cms.iservice.IUserRemoveService;
import cn.com.thtf.egov.cms.iservice.IUserSearchBeforeService;
import cn.com.thtf.egov.cms.iservice.IUserSearchService;
import cn.com.thtf.egov.cms.iservice.IUserViewService;
import cn.com.thtf.egov.cms.util.CryptUtil;

/**
 * UersManagementAction uznsz
 * 
 * @author chen
 * 
 */

public class UersManagementAction extends BaseAction {

    /** IUserSearchService */
    private IUserSearchService userSearchService = null;
    /** IUserSearchBeforeService */
    private IUserSearchBeforeService userSearchBeforeService = null;
    /** IUserRemoveService */
    private IUserRemoveService userRemoveService = null;
    /** IUserViewService */
    private IUserViewService userViewService = null;
    /** IUserChangeBeforeService */
    private IUserChangeBeforeService userChangeBeforeService = null;
    /** IUserChangeService */
    private IUserChangeService userChangeService = null;
    /** IAllCustomerSearchService */
    private IAllCustomerSearchService allCustomerSearchService = null;
    /** IAddCustomerService */
    private IAddCustomerService addCustomerService = null;
    /** IUserAddService */
    private IUserAddService userAddService = null;
    /** IUserAdd1Service */
    private IUserAdd1Service userAdd1Service = null;
    /** ISalesAssistantAddBeforeService */
    private ISalesAssistantAddBeforeService salesAssistantAddBeforeService = null;
    /** ISalesAssistantAddBeforeCheckService */
    private ISalesAssistantAddBeforeCheckService salesAssistantAddBeforeCheckService = null;
    /** ISalesAssistantAddBeforeService */
    private ITreasuryManagerAddBeforeService treasuryManagerAddBeforeService = null;
    /** ISalesAssistantAddBeforeCheck1Service */
    private ISalesAssistantAddBeforeCheck1Service salesAssistantAddBeforeCheck1Service = null;

    @SuppressWarnings("unchecked")
    public ActionForward searchUser(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        String forward = "userInformationManagement.jsp";
        UersSearchInfoDto userSearchInfo = new UersSearchInfoDto();
        userSearchInfo.setUsername(request.getParameter("username"));
        userSearchInfo.setName(request.getParameter("name"));
        userSearchInfo.setDuty(request.getParameter("duty"));
        userSearchInfo.setRegional(request.getParameter("regional"));
        userSearchInfo.setDepartment(request.getParameter("department"));
        userSearchInfo.setEnable(request.getParameter("enable"));
        userSearchInfo.setOnline(request.getParameter("online"));
        userSearchService = (IUserSearchService) getWebApplicationContext().getBean(
                "UserSearchService");
        Object[] obj = userSearchService.userSearch(userSearchInfo, getPage(request));
        request.setAttribute("userSearchInfo", userSearchInfo);
        request.setAttribute("username", request.getParameter("username"));
        request.setAttribute("name", request.getParameter("name"));
        request.setAttribute("usersSearch", obj[0]);
        request.setAttribute("areaSearch", obj[1]);
        request.setAttribute("deptSearch", obj[2]);
        request.setAttribute("dutySearch", obj[3]);
        request.setAttribute("listRange", obj[4]);
        request.setAttribute("Page", ((ListRange) obj[4]).getPage());
        UserEntity user = (UserEntity) request.getSession().getAttribute(
                Constants.USERLOGIN);
        request.setAttribute("roleId", user.getRoleId());
        return mapping.findForward(forward);
    }

    @SuppressWarnings("unchecked")
    public ActionForward searchUserBefore(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getSession();
        String forward = "userInformationManagement.jsp";
        userSearchBeforeService = (IUserSearchBeforeService) getWebApplicationContext()
                .getBean("UserSearchBeforeService");
        Object[] obj = userSearchBeforeService.userSearchBefore(getPage(request));
        request.setAttribute("usersSearch", obj[0]);
        request.setAttribute("areaSearch", obj[1]);
        request.setAttribute("deptSearch", obj[2]);
        request.setAttribute("dutySearch", obj[3]);
        request.setAttribute("listRange", obj[4]);
        request.setAttribute("Page", ((ListRange) obj[4]).getPage());
        UserEntity user = (UserEntity) request.getSession().getAttribute(
                Constants.USERLOGIN);
        request.setAttribute("roleId", user.getRoleId());
        return mapping.findForward(forward);
    }

    @SuppressWarnings("unchecked")
    public ActionForward removeUser(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        String forward = "userInformationManagement.jsp";
        UersSearchInfoDto userSearchInfo = new UersSearchInfoDto();
        userSearchInfo.setId(request.getParameter("id").toString());
        userSearchInfo.setUsername(request.getParameter("username"));
        userSearchInfo.setName(request.getParameter("name"));
        userSearchInfo.setDuty(request.getParameter("duty"));
        userSearchInfo.setRegional(request.getParameter("regional"));
        userSearchInfo.setDepartment(request.getParameter("department"));
        userSearchInfo.setEnable(request.getParameter("enable"));
        userSearchInfo.setOnline(request.getParameter("online"));
        userRemoveService = (IUserRemoveService) getWebApplicationContext().getBean(
                "UserRemoveService");
        String result = userRemoveService.userRemove(userSearchInfo);
        request.setAttribute("err", result);
        userSearchService = (IUserSearchService) getWebApplicationContext().getBean(
                "UserSearchService");
        Object[] obj = userSearchService.userSearch(userSearchInfo, getPage(request));
        request.setAttribute("userSearchInfo", userSearchInfo);
        request.setAttribute("usersSearch", obj[0]);
        request.setAttribute("areaSearch", obj[1]);
        request.setAttribute("deptSearch", obj[2]);
        request.setAttribute("dutySearch", obj[3]);
        request.setAttribute("listRange", obj[4]);
        request.setAttribute("Page", ((ListRange) obj[4]).getPage());
        UserEntity user = (UserEntity) request.getSession().getAttribute(
                Constants.USERLOGIN);
        request.setAttribute("roleId", user.getRoleId());
        return mapping.findForward(forward);
    }

    public ActionForward viewUser(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        String forward = "";
        userViewService = (IUserViewService) getWebApplicationContext().getBean(
                "UserViewService");
        Integer viewid = Integer.valueOf(request.getParameter("viewid"));
        if (viewid == 1) {
            forward = "administratorView.jsp";
            Object[] obj = userViewService.userView(request.getParameter("id"), viewid);
            request.setAttribute("view", obj[0]);
        } else if (viewid == 2) {
            forward = "salesDirectorView.jsp";
            Object[] obj = userViewService.userView(request.getParameter("id"), viewid);
            request.setAttribute("view", obj[0]);
            request.setAttribute("product", obj[1]);
        } else if (viewid == 3) {
            forward = "regionalDirectorView.jsp";
            Object[] obj = userViewService.userView(request.getParameter("id"), viewid);
            request.setAttribute("view", obj[0]);
            request.setAttribute("product", obj[1]);
        } else if (viewid == 4) {
            forward = "salesManagerView.jsp";
            Object[] obj = userViewService.userView(request.getParameter("id"), viewid);
            request.setAttribute("view", obj[0]);
            request.setAttribute("product", obj[1]);
            request.setAttribute("customer", obj[2]);
        } else if (viewid == 5) {
            forward = "logisticsManagerView.jsp";
            Object[] obj = userViewService.userView(request.getParameter("id"), viewid);
            request.setAttribute("view", obj[0]);
        } else if (viewid == 6) {
            forward = "salesAssistantView.jsp";
            Object[] obj = userViewService.userView(request.getParameter("id"), viewid);
            request.setAttribute("view", obj[0]);
            request.setAttribute("asd", obj[1]);
        } else if (viewid == 7) {
            forward = "treasuryManagerView.jsp";
            Object[] obj = userViewService.userView(request.getParameter("id"), viewid);
            request.setAttribute("view", obj[0]);
            request.setAttribute("asdga", obj[1]);
        } else if (viewid == 19 || viewid == 20) {
            userChangeBeforeService = (IUserChangeBeforeService) getWebApplicationContext()
                    .getBean("UserChangeBeforeService");
            // forward = "regionalDirectorChange.jsp";
            forward = "userAreaMappingView.jsp";
            Object[] obj = userChangeBeforeService.userChangeBefore(
                    request.getParameter("id"), viewid);
            request.setAttribute("view", obj[0]);
            request.setAttribute("userDept", obj[1]);
            request.setAttribute("userDuty", obj[2]);
            request.setAttribute("userAreaMapp", obj[3]);
            // request.setAttribute("userArea", obj[4]);
        }
        return mapping.findForward(forward);
    }

    public ActionForward changeBeforeUser(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        String forward = "";
        userChangeBeforeService = (IUserChangeBeforeService) getWebApplicationContext()
                .getBean("UserChangeBeforeService");
        Integer viewid = Integer.valueOf(request.getParameter("viewid"));
        if (viewid == 1) {
            forward = "administratorChange.jsp";
            Object[] obj = userChangeBeforeService.userChangeBefore(
                    request.getParameter("id"), viewid);
            request.setAttribute("view", obj[0]);
            request.setAttribute("userDept", obj[1]);
            request.setAttribute("userDuty", obj[2]);
        } else if (viewid == 2) {
            forward = "salesDirectorChange.jsp";
            Object[] obj = userChangeBeforeService.userChangeBefore(
                    request.getParameter("id"), viewid);
            request.setAttribute("view", obj[0]);
            request.setAttribute("userDept", obj[1]);
            request.setAttribute("userDuty", obj[2]);
            request.setAttribute("product", obj[3]);
        } else if (viewid == 3) {
            forward = "regionalDirectorChange.jsp";
            Object[] obj = userChangeBeforeService.userChangeBefore(
                    request.getParameter("id"), viewid);
            request.setAttribute("view", obj[0]);
            request.setAttribute("userDept", obj[1]);
            request.setAttribute("userDuty", obj[2]);
            request.setAttribute("product", obj[3]);
            request.setAttribute("userArea", obj[4]);
        } else if (viewid == 4) {
            forward = "salesManagerChange.jsp";
            Object[] obj = userChangeBeforeService.userChangeBefore(
                    request.getParameter("id"), viewid);
            request.setAttribute("view", obj[0]);
            request.setAttribute("userDept", obj[1]);
            request.setAttribute("userDuty", obj[2]);
            request.setAttribute("product", obj[3]);
            request.setAttribute("userArea", obj[4]);
            request.setAttribute("customer", obj[5]);
        } else if (viewid == 5) {
            forward = "logisticsManagerChange.jsp";
            Object[] obj = userChangeBeforeService.userChangeBefore(
                    request.getParameter("id"), viewid);
            request.setAttribute("view", obj[0]);
            request.setAttribute("userDept", obj[1]);
            request.setAttribute("userDuty", obj[2]);
        } else if (viewid == 6) {
            forward = "salesAssistantChange.jsp";
            Object[] obj = userChangeBeforeService.userChangeBefore(
                    request.getParameter("id"), viewid);
            request.setAttribute("view", obj[0]);
            request.setAttribute("RegionalProduct", obj[1]);
            request.setAttribute("userDept", obj[2]);
        } else if (viewid == 7) {
            forward = "treasuryManagerChange.jsp";
            Object[] obj = userChangeBeforeService.userChangeBefore(
                    request.getParameter("id"), viewid);
            request.setAttribute("view", obj[0]);
            request.setAttribute("TreasuryProducts", obj[1]);
            request.setAttribute("userDept", obj[2]);
        } else if (viewid == 19 || viewid == 20) {
            // forward = "regionalDirectorChange.jsp";
            forward = "userAreaMappingChange.jsp";
            Object[] obj = userChangeBeforeService.userChangeBefore(
                    request.getParameter("id"), viewid);
            request.setAttribute("view", obj[0]);
            request.setAttribute("userDept", obj[1]);
            request.setAttribute("userDuty", obj[2]);
            request.setAttribute("userAreaMapp", obj[3]);
            // request.setAttribute("userArea", obj[4]);
        }

        return mapping.findForward(forward);
    }

    public ActionForward changeUser(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        String forward = "searchUser.do?method=searchUserBefore";
        Map<String, Object> map = new HashMap<String, Object>();
        userChangeService = (IUserChangeService) getWebApplicationContext().getBean(
                "UserChangeService");
        Integer changeid = Integer.valueOf(request.getParameter("changeid"));
        String roleid = request.getParameter("roleid");

        map.put("role_id", roleid);

        /* 对应页面进行修改 在页面中不填写密码的情况下不修改密码 */
        String newPassword = request.getParameter("newPassword");
        String password = null;
        if (StringUtils.isNotEmpty(newPassword)) {
            password = CryptUtil.MD5(newPassword);
        } else {
            password = request.getParameter("password");
        }
        boolean result = true;
        map.put("enable", request.getParameter("enable"));
        if (StringUtils.equals((String) map.get("enable"), "0")) {
            result = userChangeService.workCount(request.getParameter("id"));
            if (result == false) {
                request.setAttribute("err", "用户有待办事务不能停用！");
                return mapping.findForward(forward);
            }
        }
        if (changeid == 1) {
            map.put("id", request.getParameter("id"));
            map.put("mail", request.getParameter("mail"));
            map.put("name", new String(request.getParameter("name")));
            map.put("password", password);
            map.put("tel", request.getParameter("tel"));
            map.put("mobile", request.getParameter("mobile"));
            map.put("msn", request.getParameter("msn"));
            map.put("qq", request.getParameter("qq"));
            // map.put("enable", request.getParameter("enable"));
            map.put("user_dept_id", request.getParameter("user_dept_id"));
            // map.put("role_id", request.getParameter("role_id"));
            result = userChangeService.userChange(changeid, map);
        } else if (changeid == 2) {
            map.put("id", request.getParameter("id"));
            map.put("mail", request.getParameter("mail"));
            map.put("name", new String(request.getParameter("name")));
            map.put("password", password);
            map.put("tel", request.getParameter("tel"));
            map.put("mobile", request.getParameter("mobile"));
            map.put("msn", request.getParameter("msn"));
            map.put("qq", request.getParameter("qq"));
            // map.put("enable", request.getParameter("enable"));
            map.put("user_dept_id", request.getParameter("user_dept_id"));
            // map.put("role_id", request.getParameter("role_id"));
            map.put("product", request.getParameterValues("product"));
            result = userChangeService.userChange(changeid, map);
        } else if (changeid == 3) {// 区域总监
            map.put("id", request.getParameter("id"));
            map.put("mail", request.getParameter("mail"));
            map.put("name", new String(request.getParameter("name")));
            map.put("password", password);
            map.put("tel", request.getParameter("tel"));
            map.put("mobile", request.getParameter("mobile"));
            map.put("msn", request.getParameter("msn"));
            map.put("qq", request.getParameter("qq"));
            // map.put("enable", request.getParameter("enable"));
            map.put("user_dept_id", request.getParameter("user_dept_id"));
            // map.put("role_id", request.getParameter("role_id"));
            map.put("product", request.getParameterValues("product"));
            map.put("user_area_id", request.getParameter("user_area_id"));
            result = userChangeService.userChange(changeid, map);
        } else if (changeid == 4) {
            map.put("id", request.getParameter("id"));
            map.put("mail", request.getParameter("mail"));
            map.put("name", new String(request.getParameter("name")));
            map.put("password", password);
            map.put("tel", request.getParameter("tel"));
            map.put("mobile", request.getParameter("mobile"));
            map.put("msn", request.getParameter("msn"));
            map.put("qq", request.getParameter("qq"));
            // map.put("enable", request.getParameter("enable"));
            map.put("user_dept_id", request.getParameter("user_dept_id"));
            // map.put("role_id", request.getParameter("role_id"));
            map.put("product", request.getParameterValues("product"));
            map.put("user_area_id", request.getParameter("user_area_id"));
            map.put("customer", request.getParameterValues("customer"));
            result = userChangeService.userChange(changeid, map);
        } else if (changeid == 5) {
            map.put("id", request.getParameter("id"));
            map.put("mail", request.getParameter("mail"));
            map.put("name", new String(request.getParameter("name")));
            map.put("password", password);
            map.put("tel", request.getParameter("tel"));
            map.put("mobile", request.getParameter("mobile"));
            map.put("msn", request.getParameter("msn"));
            map.put("qq", request.getParameter("qq"));
            // map.put("enable", request.getParameter("enable"));
            map.put("user_dept_id", request.getParameter("user_dept_id"));
            // map.put("role_id", request.getParameter("role_id"));
            result = userChangeService.userChange(changeid, map);
        } else if (changeid == 6) {
            map.put("id", request.getParameter("id"));
            map.put("mail", request.getParameter("mail"));
            map.put("name", new String(request.getParameter("name")));
            map.put("password", password);
            map.put("tel", request.getParameter("tel"));
            map.put("mobile", request.getParameter("mobile"));
            map.put("msn", request.getParameter("msn"));
            map.put("qq", request.getParameter("qq"));
            // map.put("enable", request.getParameter("enable"));
            map.put("user_dept_id", request.getParameter("user_dept_id"));
            // map.put("role_id", request.getParameter("role_id"));
            map.put("regionalProduct", request.getParameterValues("grqwrewq"));
            result = userChangeService.userChange(changeid, map);
        } else if (changeid == 7) {
            map.put("id", request.getParameter("id"));
            map.put("mail", request.getParameter("mail"));
            map.put("name", request.getParameter("name"));
            map.put("password", password);
            map.put("tel", request.getParameter("tel"));
            map.put("mobile", request.getParameter("mobile"));
            map.put("msn", request.getParameter("msn"));
            map.put("qq", request.getParameter("qq"));
            // map.put("enable", request.getParameter("enable"));
            map.put("user_dept_id", request.getParameter("user_dept_id"));
            // map.put("role_id", request.getParameter("role_id"));
            map.put("treasuryProduct", request.getParameterValues("grqwrewq1"));
            result = userChangeService.userChange(changeid, map);
        } else if (changeid == 39) {// 区域
            map.put("id", request.getParameter("id"));
            map.put("mail", request.getParameter("mail"));
            map.put("name", new String(request.getParameter("name")));
            map.put("password", password);
            map.put("tel", request.getParameter("tel"));
            map.put("mobile", request.getParameter("mobile"));
            map.put("msn", request.getParameter("msn"));
            map.put("qq", request.getParameter("qq"));
            // map.put("enable", request.getParameter("enable"));
            map.put("user_dept_id", request.getParameter("user_dept_id"));
            // map.put("role_id", request.getParameter("role_id"));
            // map.put("product", request.getParameterValues("product"));
            // map.put("user_area_id", request.getParameter("user_area_id"));
            map.put("areaList", request.getParameterValues("areaList"));
            result = userChangeService.userChange(changeid, map);
        }
        if (result == false) {
            request.setAttribute("err", "修改失败！");

        }
        return mapping.findForward(forward);
    }

    @SuppressWarnings("unchecked")
    public ActionForward salesManagerAddCustomer(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        String forward = "salesManagerAddCustomer.jsp";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", (request.getParameter("name")));
        allCustomerSearchService = (IAllCustomerSearchService) getWebApplicationContext()
                .getBean("AllCustomerSearchService");
        Object[] obj = allCustomerSearchService.allCustomer(map, getPage(request));
        request.setAttribute("name", (request.getParameter("name")));
        request.setAttribute("id", request.getParameter("id"));
        request.setAttribute("listRange", obj[0]);
        request.setAttribute("Page", ((ListRange) obj[0]).getPage());
        return mapping.findForward(forward);
    }

    public ActionForward addCustomer(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", request.getParameter("id"));
        map.put("obj", request.getParameter("obj"));
        addCustomerService = (IAddCustomerService) getWebApplicationContext().getBean(
                "AddCustomerService");
        addCustomerService.addCustomer(map);
        return null;
    }

    public ActionForward adduserBefore(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        String forward = "userAdd.jsp";
        userAddService = (IUserAddService) getWebApplicationContext().getBean(
                "UserAddService");
        Object[] obj = userAddService.userAddBefore();
        request.setAttribute("userDept", obj[0]);
        request.setAttribute("userArea", obj[1]);
        request.setAttribute("product", obj[2]);
        request.setAttribute("logistics", obj[3]);
        return mapping.findForward(forward);
    }

    @SuppressWarnings("unchecked")
    public ActionForward salesManagerAddCustomer1(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        String forward = "salesManagerAddCustomer1.jsp";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", (request.getParameter("name")));
        allCustomerSearchService = (IAllCustomerSearchService) getWebApplicationContext()
                .getBean("AllCustomerSearchService");
        Object[] obj = allCustomerSearchService.allCustomer(map, getPage(request));
        request.setAttribute("name", (request.getParameter("name")));
        request.setAttribute("id", request.getParameter("id"));
        request.setAttribute("listRange", obj[0]);
        request.setAttribute("Page", ((ListRange) obj[0]).getPage());
        return mapping.findForward(forward);
    }

    public ActionForward addUser(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        String forward = "searchUser.do?method=searchUserBefore";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", request.getParameter("id"));
        map.put("mail", request.getParameter("mail"));
        map.put("name", request.getParameter("name"));
        map.put("password", CryptUtil.MD5(request.getParameter("password")));
        map.put("tel", request.getParameter("tel"));
        map.put("mobile", request.getParameter("mobile"));
        map.put("msn", request.getParameter("msn"));
        map.put("qq", request.getParameter("qq"));
        map.put("enable", request.getParameter("enable"));
        map.put("user_dept_id", request.getParameter("user_dept_id"));
        map.put("role_id", request.getParameter("role_id"));
        map.put("user_area_id", request.getParameter("user_area_id"));
        map.put("product_type_id", request.getParameterValues("product_type_id"));
        map.put("customer", request.getParameterValues("customer"));
        map.put("logistics_id", request.getParameter("logistics"));
        map.put("regionalProduct", request.getParameterValues("regionalProduct"));
        map.put("treasuryProduct", request.getParameterValues("treasuryProduct"));
        map.put("areaList", request.getParameterValues("areaList"));
        userAdd1Service = (IUserAdd1Service) getWebApplicationContext().getBean(
                "UserAdd1Service");
        Boolean result = userAdd1Service.userAdd(map);
        if (!result) {
            request.setAttribute("err", "添加失败！");
        }
        return mapping.findForward(forward);
    }

    public ActionForward salesAssistantAddBefore(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        String forward = "salesAssistantAdd.jsp";
        salesAssistantAddBeforeService = (ISalesAssistantAddBeforeService) getWebApplicationContext()
                .getBean("SalesAssistantAddBeforeService");
        Object[] obj = salesAssistantAddBeforeService.queryRegionalProduct();
        request.setAttribute("regional", obj[0]);
        request.setAttribute("product", obj[1]);
        return mapping.findForward(forward);
    }

    public ActionForward salesAssistantAddBeforeCheck(ActionMapping mapping,
            ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        salesAssistantAddBeforeCheckService = (ISalesAssistantAddBeforeCheckService) getWebApplicationContext()
                .getBean("SalesAssistantAddBeforeCheckService");
        Object[] obj = salesAssistantAddBeforeCheckService.checkRegionalProduct(
                request.getParameter("regional"), request.getParameter("product"));
        response.getWriter().print(obj[0]);
        return null;
    }

    public ActionForward treasuryManagerAddBefore(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        String forward = "treasuryManagerAdd.jsp";
        treasuryManagerAddBeforeService = (ITreasuryManagerAddBeforeService) getWebApplicationContext()
                .getBean("TreasuryManagerAddBeforeService");
        Object[] obj = treasuryManagerAddBeforeService.queryTreasuryProduct();
        request.setAttribute("treasury", obj[0]);
        request.setAttribute("product", obj[1]);
        return mapping.findForward(forward);
    }

    public ActionForward salesAssistantAddBeforeCheck1(ActionMapping mapping,
            ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        salesAssistantAddBeforeCheck1Service = (ISalesAssistantAddBeforeCheck1Service) getWebApplicationContext()
                .getBean("SalesAssistantAddBeforeCheck1Service");
        Object[] obj = salesAssistantAddBeforeCheck1Service.checkRegionalProduct1(
                request.getParameter("regional"), request.getParameter("product"));
        response.getWriter().print(obj[0]);
        return null;
    }
}
