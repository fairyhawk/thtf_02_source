/**
 * ClassName  MailAction
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-7-1
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.action.mail;

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
import cn.com.thtf.egov.cms.dto.MailDto;
import cn.com.thtf.egov.cms.form.MailForm;
import cn.com.thtf.egov.cms.iservice.mail.IMailService;
import cn.com.thtf.egov.cms.util.Container;

/**
 * MailAction
 * 
 * @author Lubo
 */
public class MailAction extends NewBaseAction {

    /** log */
    private static Logger log = LoggerFactory.getLogger(MailAction.class);
    /** IMailService */
    private IMailService mailService = null;

    /**
     * 邮件查看
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward mailView(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("邮件查看");
        MailForm mailForm = (MailForm) form;
        MailDto queryPara = mailForm.getMailPara();
        mailService = (IMailService) Container.getBean("mailServiceImp");
        MailDto result = mailService.getMail(queryPara);

        request.setAttribute("mail", result);
        return mapping.findForward("mailView");
    }

    /**
     * 邮件发送
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward sendMail(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("邮件发送");
        MailForm mailForm = (MailForm) form;
        MailDto para = mailForm.getMailPara();

        mailService = (IMailService) Container.getBean("mailServiceImp");

        boolean result = mailService.sendMail(para);
        if (result) {
            return mapping.findForward("mailList");
        } else {
            request.setAttribute("errorMsg", "发送失败!");
            return mapping.findForward("mailView");
        }
    }

    /**
     * 邮件管理
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward mailList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("邮件管理");
        MailForm mailForm = (MailForm) form;
        MailDto queryPara = mailForm.getMailPara();

        /* 封装翻页参数 */
        NewPage newPage = getNewPage(request);
        newPage.setUrl("mailList.do");
        newPage.setQuery("mailPara.starttime", queryPara.getStarttime());
        newPage.setQuery("mailPara.endtime", queryPara.getEndtime());
        newPage.setQuery("mailPara.type", queryPara.getType());
        newPage.setQuery("mailPara.subject", queryPara.getSubject());
        newPage.setQuery("mailPara.userName", queryPara.getUserName());

        newPage.setQuery("mailPara.name", queryPara.getName());
        newPage.setQuery("mailPara.flag", queryPara.getFlag());

        mailService = (IMailService) Container.getBean("mailServiceImp");
        List<MailDto> result = mailService.getMailList(queryPara, newPage);

        request.setAttribute("mailList", result);
        request.setAttribute("NewPage", newPage);
        request.setAttribute("mailPara", queryPara);

        return mapping.findForward("mailList");
    }
}
