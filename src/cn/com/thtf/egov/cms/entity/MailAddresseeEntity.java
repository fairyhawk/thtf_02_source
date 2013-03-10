/**
 * ClassName  MailAddresseeEntity
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-6-3
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.entity;

import cn.com.thtf.egov.cms.application.BaseEntity;

/**
 * MailAddresseeEntity
 * 
 * @author Lubo
 */
public class MailAddresseeEntity extends BaseEntity {

    /** 自动生成序列化ID */
    private static final long serialVersionUID = 1L;
    /** 邮件ID */
    private Integer mailId;
    /** 收件人姓名 */
    private String name;
    /** 邮件地址 */
    private String mail;

    /**
     * @return the mailId
     */
    public Integer getMailId() {
        return mailId;
    }

    /**
     * @param mailId
     *            the mailId to set
     */
    public void setMailId(Integer mailId) {
        this.mailId = mailId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * @param mail
     *            the mail to set
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

}
