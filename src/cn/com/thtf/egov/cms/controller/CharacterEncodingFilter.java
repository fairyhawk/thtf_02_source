/**
 * ClassName  CharacterEncodingFilter
 *
 * History
 * Create User: Lubo
 * Create Date: 2009-11-18
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 过滤器
 * 
 * @author Lubo
 */
public class CharacterEncodingFilter implements Filter {

	/** log */
	@SuppressWarnings("unused")
	private static Logger log = LoggerFactory
			.getLogger(CharacterEncodingFilter.class);

	protected String encoding = null;

	protected FilterConfig filterConfig = null;

	public void destroy() {
		this.encoding = null;
		this.filterConfig = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (encoding != null) {
			request.setCharacterEncoding(encoding);
		}
		// if (log.isDebugEnabled()) {
		// HttpServletRequest http = (HttpServletRequest) request;
		// String uri = http.getRequestURI();
		// String workPath = http.getContextPath() + "/";
		// log.debug("URI:" + uri + "    WORKPATH" + workPath);
		// }

		chain.doFilter(request, response);

	}

	public void init(FilterConfig config) throws ServletException {
		this.filterConfig = config;
		this.encoding = config.getInitParameter("encoding");
	}
}
