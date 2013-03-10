/**
 * ClassName  ViladataFilter
 *
 * History
 * Create User: chen
 * Create Date: 2009年12月21日
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.thtf.egov.cms.constant.Constants;

/**
 * ViladataFilter 用户验证过滤器
 * 
 * @author chenchen
 * 
 */
public class ViladataFilter implements Filter {

	/**
	 * ViladataFilter
	 */
	public ViladataFilter() {
	}

	/** FilterConfig */
	@SuppressWarnings("unused")
	private FilterConfig filterConfig;

	/**
	 * destroy
	 */
	public void destroy() {
		this.filterConfig = null;
	}

	/**
	 * doFilter
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		if (httpRequest.getSession() != null
				&& httpRequest.getSession().getAttribute(Constants.USERLOGIN) == null) {
			if (httpRequest.getRequestURI().indexOf("login.do") > 0
					|| httpRequest.getRequestURI().indexOf("login.jsp") > 0
					|| httpRequest.getRequestURI().indexOf("queryWaybill.do") > 0
					|| httpRequest.getRequestURI().indexOf("queryWaybillDetail.do") > 0
					|| httpRequest.getRequestURI().indexOf("loginOK") > 0) {
				chain.doFilter(request, response);
			} else {
				httpResponse.sendRedirect("login.jsp");
			}
		} else {
			chain.doFilter(request, response);
		}
	}

	/**
	 * init
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}
}
