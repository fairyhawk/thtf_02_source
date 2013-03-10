/**
 * ClassName  OnLineUserListener
 *
 * History
 * Create User: chen
 * Create Date: 2009年12月21日
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.controller;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * OnLineUserListener
 * 
 * @author chen
 * 
 */
public class OnLineUserListener implements HttpSessionListener {

	/** log */
	private static Logger log = LoggerFactory
			.getLogger(OnLineUserListener.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http
	 * .HttpSessionEvent)
	 */
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet
	 * .http.HttpSessionEvent)
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		log.debug("session超时,sessionId:" + arg0.getSession().getId());
		Controller.doOffline(arg0.getSession());
	}
}
