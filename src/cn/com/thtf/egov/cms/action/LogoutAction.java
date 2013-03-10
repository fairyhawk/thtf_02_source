package cn.com.thtf.egov.cms.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.BaseAction;
import cn.com.thtf.egov.cms.constant.Constants;
import cn.com.thtf.egov.cms.controller.Controller;

/**
 * 用户退出Action
 * 
 * @author sxf
 * 
 */
public class LogoutAction extends BaseAction {

	private static Logger log = LoggerFactory.getLogger(LogoutAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.debug("用户退出!");

		/* 注销session */
		Controller.doOffline(request.getSession());
		request.getSession().setAttribute(Constants.USERLOGIN, null);
		return mapping.findForward("login.jsp");
	}
}
