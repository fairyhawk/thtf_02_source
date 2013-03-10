/**
 * ClassName  CreditTypeAction
 *
 * History
 * Create User: Administrator
 * Create Date: 2010-1-11
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.action.credit;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.NewBaseAction;
import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.constant.Constants;
import cn.com.thtf.egov.cms.entity.CreditTypeEntity;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.iservice.credit.ICreditService;

/**
 * 信用类型管理
 * 
 * @author shensi
 */
public class CreditTypeAction extends NewBaseAction {

    private static Logger log = LoggerFactory.getLogger(CreditTypeAction.class);

    /**
     * 查看所有信用类型
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public ActionForward findAllCreditType(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 獲取spring的service
        ICreditService creditService = (ICreditService) this
                .getBean("creditTypeServiceImp");

        // 获取用户登陆权限
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},信用类型列表", user.getId());

        request.setAttribute("roleId", user.getRoleId());

        // 分頁
        NewPage newPage = getNewPage(request);
        newPage.setUrl("findAllCreditType.do");

        // 查看所有信用类型
        List list = creditService.findAllCreditType(newPage);
        request.setAttribute("NewPage", newPage);
        request.setAttribute("credittypelist", list);
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 根据ID查看信用类型
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward findCreditTypeById(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        ICreditService creditService = (ICreditService) this
                .getBean("creditTypeServiceImp");
        CreditTypeEntity creditType = null;

        // 获取参数传值
        String creditTypeId = request.getParameter("id");

        // 根据ID查看信用类型
        creditType = creditService.findCreditTypeById(creditTypeId);
        request.setAttribute("cinfo", creditType);
        request.setAttribute("creditTypeId", creditTypeId);
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 修改信用类型
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward updateCreditType(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        boolean isExist = false;
        boolean isSuccess = false;

        // 获取当前登陆用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},修改信用类型", user.getId());

        CreditTypeEntity creditTypeInfo = new CreditTypeEntity();

        // 参数获取
        String creditTypeName = request.getParameter("creditTypeName");
        String creditTypeId = request.getParameter("creditTypeId");
        creditTypeInfo.setId(Integer.parseInt(creditTypeId));
        creditTypeInfo.setName(creditTypeName);

        ICreditService creditService = (ICreditService) this
                .getBean("creditTypeServiceImp");
        // 判断信用类型是否存在
        isExist = creditService.findNameByCondition(creditTypeName);
        if (!isExist) {
            // 修改信用类型
            isSuccess = creditService.updateCreditType(creditTypeInfo);
            if (isSuccess) {
                return mapping.findForward(Constants.SUCCESS);
            } else {
                log.info("User:{},信用类型修改失败", user.getId());
                request.setAttribute("cinfo", creditTypeInfo);
                request.setAttribute("msg", "信用类型修改失败");
                return mapping.findForward(Constants.FAIL);
            }
        } else {
            log.info("User:{},信用类型修改失败 : " + creditTypeName + " 已存在", user.getId());
            request.setAttribute("cinfo", creditTypeInfo);
            request.setAttribute("msg", "修改失败： " + creditTypeName + " 已存在");
            return mapping.findForward(Constants.FAIL);
        }
    }
}
