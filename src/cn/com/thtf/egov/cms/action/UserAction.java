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

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.BaseAction;
import cn.com.thtf.egov.cms.constant.Constants;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.form.UserForm;
import cn.com.thtf.egov.cms.iservice.IUserService;
import cn.com.thtf.egov.cms.util.CryptUtil;

/**
 * 个人信息
 * 
 * @author Administrator
 * 
 */
public class UserAction extends BaseAction {
    @SuppressWarnings("unused")
    private static Logger log = LoggerFactory.getLogger(UserAction.class);

    private IUserService iuser;

    /**
     * 显示列表
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward userMang(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        iuser = (IUserService) getWebApplicationContext().getBean("userServiceImp");
        UserEntity uinfo = iuser.getUserById(((UserEntity) request.getSession()
                .getAttribute(Constants.USERLOGIN)).getId());
        request.setAttribute("uinfo", uinfo);
        return mapping.findForward("userMsg");
    }

    /**
     * 修改
     * 
     * 
     */
    public ActionForward updateUser(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserForm userForm = (UserForm) form;
        UserEntity userInfoForm = userForm.getUserInfo();

        String userId = ((UserEntity) request.getSession().getAttribute(
                Constants.USERLOGIN)).getId();
        iuser = (IUserService) getWebApplicationContext().getBean("userServiceImp");
        UserEntity userInfoDb = iuser.getUserById(userId);

        if (StringUtils.isNotEmpty(userInfoForm.getPassword())) {
            if (!StringUtils.equals(CryptUtil.MD5(userInfoForm.getPassword()),
                    userInfoDb.getPassword())) {
                request.setAttribute("err", "密码不对！");
                return userMang(mapping, form, request, response);
            }
            if (StringUtils.isNotEmpty(userForm.getNewpassword())) {
                userInfoForm.setPassword(CryptUtil.MD5(userForm.getNewpassword()));
            }else{
                userInfoForm.setPassword(null);
            }
        }

        userInfoForm.setId(userId);
        iuser.updateUser(userInfoForm);
        // request.getSession().setAttribute(Constants.USERLOGIN, userInfoForm);

        return userMang(mapping, form, request, response);
    }

}
