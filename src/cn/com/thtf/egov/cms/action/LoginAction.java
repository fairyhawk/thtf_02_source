/**
 * ClassName  LoginAction
 *
 * History
 * Create User: chen
 * Create Date: 2009年12月21日
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.action;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.BaseAction;
import cn.com.thtf.egov.cms.constant.Constants;
import cn.com.thtf.egov.cms.controller.Controller;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.iservice.ICreateTreeService;
import cn.com.thtf.egov.cms.iservice.IUserService;
import cn.com.thtf.egov.cms.util.Util;
import cn.com.thtf.egov.cms.util.HClient;

/**
 * LoginAction 后台用户登录
 * 
 * @author chenchen
 * 
 */
public class LoginAction extends BaseAction {

    /** log */
    private static Logger log = LoggerFactory.getLogger(LoginAction.class);
    /** userService */
    private IUserService userService = null;
    /** createTreeService */
    private ICreateTreeService createTreeService = null;

    /**
     * 登录验证
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward login(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("登录者IP:" + request.getRemoteAddr());

        DynaActionForm dform = (DynaActionForm) form;
        /* 获取参数 */
        String username = (String) dform.get("username");
        String password = (String) dform.get("password");

        /* log */
        log.debug("username:" + username);
        /* forward */
        String forward = "login.jsp";

        /* 验证码校验 */
        String sessionCode = (String) request.getSession().getAttribute(
                Constants.SESSION_CONFIRM_STR);
        String textCode = request.getParameter("code");

        // textCode = sessionCode;
        // password = "temp";

        if (StringUtils.isNotBlank(textCode) && StringUtils.equals(sessionCode, textCode)) {
            /* 校验参数 */
            if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
                request.setAttribute("LOGIN_TIPS", Constants.ERR_LOGIN_TIPS);
            } else {
                /* userService */
                userService = (IUserService) getWebApplicationContext().getBean(
                        "userServiceImp");
                UserEntity userinfo = userService.getUserById(username);

                /* 判断帐号 */
                if (userinfo == null) {
                    request.setAttribute("LOGIN_TIPS", Constants.ERR_LOGIN_TIPS);
                } else {
                    /* 判断密码 */
                    if (Util.isAuthorized(password, userinfo.getPassword())) {
                        if (StringUtils.equals(userinfo.getEnable(), "1")) {

                            /* 在session中添加用户,保正帐号单一登录 */
                            request.getSession().setAttribute(Constants.USERLOGIN,
                                    userinfo);

                            Controller.doOnline(request.getSession());

                            /* 更新在线时间 */
                            UserEntity userPara = new UserEntity();
                            userPara.setId(userinfo.getId());
                            userPara.setDatetime(Util.getDateTime());
                            userService.updateUserDatetime(userPara);

                            // Object[] result =
                            // userService.getLeftTree(userinfo);
                            // request.getSession().setAttribute("tree",
                            // result[0]);
                            // request.getSession().setAttribute("userRelations",
                            // result[1]);

                            /* 获取目录树 */
                            createTreeService = (ICreateTreeService) getWebApplicationContext()
                                    .getBean("createTreeServiceImp");
                            String resultTree = createTreeService.getLeftTree(userinfo
                                    .getRoleId());
                            request.getSession().setAttribute("tree", resultTree);

                            forward = "index.jsp";
                            // 增加与Report交互登录逻辑 2010-11-03 by Shiy
                            Map<String, String> paraMap = new HashMap<String, String>(1);
                            paraMap.put("userid", userinfo.getId());
                            try {
                                StringBuffer sbReport = new StringBuffer();
                                sbReport.append("http://127.0.0.1:");
                                sbReport.append(request.getServerPort());

                                String token = HClient.getInstance().send(
                                        sbReport.toString()
                                                + "/report/view/BackEndLogin.action",
                                        paraMap);
                                StringBuffer sbBase = new StringBuffer();
                                sbBase.append("http://");
                                sbBase.append(request.getServerName());
                                sbBase.append(":");
                                sbBase.append(request.getServerPort());

                                StringBuffer sb = new StringBuffer();
                                sb.append(sbBase);
                                sb.append("/report/view/Login.action?token=");
                                sb.append(URLEncoder.encode(token, "utf-8"));
                                request.getSession().setAttribute("reportLoginURI",
                                        sb.toString());
                                StringBuffer sbout = new StringBuffer();
                                sbout.append(sbBase);
                                sbout.append("/report/view/Login.action?logout=");
                                request.getSession().setAttribute("reportLogoutURI",
                                        sbout.toString());

                                // 无实际操作的请求 用于保持session
                                StringBuffer sbemp = new StringBuffer();
                                sbemp.append(sbBase);
                                sbemp.append("/report/view/Login.action?keep=");
                                request.getSession().setAttribute("reportKeepURI",
                                        sbemp.toString());
                            } catch (Exception e) {
                                log.error("report login token get error", e);
                            }
                            // **************************************

                        } else {
                            request.setAttribute("LOGIN_TIPS", "帐户停用,请联系管理员！");
                        }
                    } else {
                        request.setAttribute("LOGIN_TIPS", Constants.ERR_LOGIN_TIPS);
                    }
                }
            }
        } else {
            request.setAttribute("LOGIN_TIPS", Constants.ERR_LOGIN_CODE);
        }
        return mapping.findForward(forward);
    }
}
