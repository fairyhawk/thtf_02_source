/**
 * ClassName  MailDto
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-7-1
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

/**
 * MailDto
 * 
 * @author Lubo
 */
public class MailDto {

    /** 邮件ID */
    private Integer id;
    /** 邮件ID */
    private String idArr;
    /** 发送时间 */
    private String datetime;
    /** 邮件类型 */
    private String type;
    /** 收件人 */
    private String userName;
    /** 主题 */
    private String subject;

    /** 内容 */
    private String text;
    /** 标识 0未发送 1已发送 */
    private String flag;

    /** 邮件ID */
    private Integer mailId;
    /** 收件人姓名 */
    private String name;
    /** 邮件地址 */
    private String mail;

    /** 发件起始日期 */
    private String starttime;
    /** 发件终止日期 */
    private String endtime;

    /**
     * @return the idArr
     */
    public String getIdArr() {
        return idArr;
    }

    /**
     * @param idArr
     *            the idArr to set
     */
    public void setIdArr(String idArr) {
        this.idArr = idArr;
    }

    /**
     * @return the starttime
     */
    public String getStarttime() {
        return starttime;
    }

    /**
     * @param starttime
     *            the starttime to set
     */
    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    /**
     * @return the endtime
     */
    public String getEndtime() {
        return endtime;
    }

    /**
     * @param endtime
     *            the endtime to set
     */
    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

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
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(String type) {
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
    public String getFlag() {
        return flag;
    }

    /**
     * @param flag
     *            the flag to set
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }

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
