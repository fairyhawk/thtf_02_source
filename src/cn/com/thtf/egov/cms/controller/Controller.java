/**
 * ClassName  Controller
 *
 * History
 * Create User: chen
 * Create Date: 2009年12月21日
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.controller;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.constant.Constants;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.iservice.IUserService;
import cn.com.thtf.egov.cms.util.Container;

/**
 * Controller
 * 
 * @author chenchen
 * 
 */
public class Controller {

    /** log */
    private static Logger log = LoggerFactory.getLogger(Controller.class);

    /** userSessionMap */
    private static Map<String, HttpSession> userSessionMap = Collections
            .synchronizedMap(new ConcurrentHashMap<String, HttpSession>());

    /**
     * 上线控制
     * 
     * @param session
     */
    public static synchronized void doOnline(HttpSession session) {
        UserEntity userinfo = (UserEntity) session.getAttribute(Constants.USERLOGIN);

        log.debug("doOnline " + (userinfo == null));
        log.debug("doOnline session id:" + session.getId());

        if (userinfo != null) {
            if (userSessionMap.containsKey(userinfo.getId())) {
                HttpSession mSession = userSessionMap.get(userinfo.getId());

                if (mSession != null
                        && !StringUtils.equals(mSession.getId(), session.getId())) {
                    log.debug("doOnline MAP session id:" + mSession.getId());
                    try {
                        mSession.setAttribute(Constants.USERLOGIN, null);
                    } catch (IllegalStateException e) {
                        log.error("旧Session已经失效！", e);
                    }
                }
            }

            log.debug("doOnline "
                    + ((UserEntity) session.getAttribute(Constants.USERLOGIN) == null));

            /* 更新在线状态 */
            IUserService userService = (IUserService) Container.getBean("userServiceImp");
            userinfo.setOnline(Constants.USER_ONLINE_ON_NEW);
            userService.updateUserStatus(userinfo);

            userSessionMap.put(userinfo.getId(), session);
        }

    }

    /**
     * 下线控制
     * 
     * @param session
     */
    public static synchronized void doOffline(HttpSession session) {
        UserEntity userinfo = (UserEntity) session.getAttribute(Constants.USERLOGIN);

        if (userinfo != null) {
            if (userSessionMap.containsKey(userinfo.getId())) {
                userSessionMap.remove(userinfo.getId());
            }

            log.debug("Controller doOffline:" + userinfo.getId());

            /* 更新在线状态 */
            IUserService userService = (IUserService) Container.getBean("userServiceImp");

            userinfo.setOnline(0);
            userService.updateUserStatus(userinfo);

            log.debug("Controller doOffline:");
        }
    }
}
