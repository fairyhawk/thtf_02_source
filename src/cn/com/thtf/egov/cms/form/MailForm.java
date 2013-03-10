/**
 * ClassName  MailForm
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-7-1
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.form;

import org.apache.struts.action.ActionForm;

import cn.com.thtf.egov.cms.dto.MailDto;

/**
 * MailForm
 * 
 * @author Lubo
 */
public class MailForm extends ActionForm {

    /** 自动生成序列化ID */
    private static final long serialVersionUID = 1L;
    /** MailDto */
    private MailDto mailPara = new MailDto();

    /**
     * @return the mailPara
     */
    public MailDto getMailPara() {
        return mailPara;
    }

    /**
     * @param mailPara
     *            the mailPara to set
     */
    public void setMailPara(MailDto mailPara) {
        this.mailPara = mailPara;
    }

}
