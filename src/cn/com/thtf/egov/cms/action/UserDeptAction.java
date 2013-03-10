/**
 * ClassName  AreaAction
 *
 * History
 * Create User: Administrator
 * Create Date: 2009-12-16
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.BaseAction;
import cn.com.thtf.egov.cms.application.ListRange;
import cn.com.thtf.egov.cms.dto.UserDeptInfoDto;
import cn.com.thtf.egov.cms.iservice.IUserDeptService;

/**
 * @author Administrator
 * 
 */
public class UserDeptAction extends BaseAction {
	@SuppressWarnings("unused")
	private static Logger log = LoggerFactory.getLogger(UserDeptAction.class);

	private IUserDeptService iuserdept;

	/**
	 *显示列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward userDeptAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// DynaActionForm dform = (DynaActionForm) form;
		iuserdept = (IUserDeptService) getWebApplicationContext().getBean(
				"userDeptServiceImp");
		ListRange list = iuserdept.queryUserDept(getPage(request));
		request.setAttribute("userdeptlist", list);
		request.setAttribute("Page", list.getPage());
		return mapping.findForward("userDeptAll");
	}

	/**
	 * 显示添加页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showUserDeptAdd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		iuserdept = (IUserDeptService) getWebApplicationContext().getBean(
				"userDeptServiceImp");
		saveToken(request);
		return mapping.findForward("userDeptAdd");
	}

	/**
	 * 添加
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addUserDept(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm dform = (DynaActionForm) form;
		UserDeptInfoDto uinfo = (UserDeptInfoDto) dform.get("userdept");
		iuserdept = (IUserDeptService) getWebApplicationContext().getBean(
				"userDeptServiceImp");
		/* 防止重复提交 */
		if (isTokenValid(request)) {
			resetToken(request);
		} else {
			return userDeptAll(mapping, form, request, response);
		}
		String result = iuserdept.addUserDept(uinfo);
		if (result != null) {
			request.setAttribute("err", result);
			return mapping.findForward("userDepterr");
		} else {
			return mapping.findForward("addsucess");
		}
	}

	/**
	 * 显示修改页
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showUserDeptUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaActionForm dform = (DynaActionForm) form;
		UserDeptInfoDto uinfo = (UserDeptInfoDto) dform.get("userdept");
		iuserdept = (IUserDeptService) getWebApplicationContext().getBean(
				"userDeptServiceImp");
		saveToken(request);
		if (uinfo.getId() == null) {
			uinfo = iuserdept.showUpdateUserDept(Integer.parseInt(request
					.getParameter("id")));
		} else {
			uinfo = iuserdept.showUpdateUserDept(uinfo.getId());
		}
		request.setAttribute("userDeptName", uinfo);
		return mapping.findForward("showUserDeptUpdate");
	}

	/**
	 * 修改
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveUserDeptUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaActionForm dform = (DynaActionForm) form;
		UserDeptInfoDto uinfo = (UserDeptInfoDto) dform.get("userdept");
		iuserdept = (IUserDeptService) getWebApplicationContext().getBean(
				"userDeptServiceImp");
		/* 防止重复提交 */
		if (isTokenValid(request)) {
			resetToken(request);
		} else {
			return userDeptAll(mapping, form, request, response);
		}
		String result = iuserdept.saveUserDept(uinfo);
		if (result != null) {
			request.setAttribute("err", result);
			return showUserDeptUpdate(mapping, form, request, response);
		} else {
			return mapping.findForward("UserDeptUpdate");
		}
	}

	/**
	 * 删除
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteUserDept(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// DynaActionForm dform = (DynaActionForm) form;
		iuserdept = (IUserDeptService) getWebApplicationContext().getBean(
				"userDeptServiceImp");
		String idStr = request.getParameter("id");
		// String[] id = idStr.split(",");
		// for (int i = 0; i < id.length; i++) {
		// iuserdept.deleteUserDept(Integer.parseInt(id[i]));
		// }
		iuserdept.deleteUserDept(idStr);
		return mapping.findForward("deleteUserDept");
	}
}
