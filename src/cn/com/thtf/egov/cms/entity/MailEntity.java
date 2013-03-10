/**
 * ClassName  MailEntity
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
 * MailEntity
 * 
 * @author Lubo
 */
public class MailEntity extends BaseEntity {

    /** 自动生成序列化ID */
    private static final long serialVersionUID = 1L;

    /** 邮件ID */
    private Integer id;
    /** 发送时间 */
    private String datetime;
    /** 邮件类型 */
    private Integer type;
    /** 收件人 */
    private String userName;
    /** 主题 */
    private String subject;

    /** 内容 */
    private String text;
    /** 标识 0未发送 1已发送 */
    private Integer flag;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the datetime
     */
    public String getDatetime() {
        return datetime;
    }

    /**
     * @param datetime
     *            the datetime to set
     */
    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    /**
     * @return the type
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     *            the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject
     *            the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text
     *            the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the flag
     */
    public Integer getFlag() {
        return flag;
    }

    /**
     * @param flag
     *            the flag to set
     */
    public void setFlag(Integer flag) {
        this.flag = flag;
    }

}
