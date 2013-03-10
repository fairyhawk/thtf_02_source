/**
 * ClassName  User
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-4-1
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.entity;

import cn.com.thtf.egov.cms.application.BaseEntity;

/**
 * User
 * 
 * @author Lubo
 * 
 */
public class UserEntity extends BaseEntity {

    private static final long serialVersionUID = 7444015520720893720L;
    /** 登录名 */
    private String id;
    /** 用户名 */
    private String name;
    /** 密码 */
    private String password;
    /** 电话 */
    private String tel;
    /** 登录名 */
    private String mobile;

    /** 邮箱 */
    private String mail;
    /** msn */
    private String msn;
    /** QQ */
    private String qq;
    /** 上次登录时间 */
    private String datetime;
    /** 使用状态 */
    private String enable;

    /** 在线状态 */
    private Integer online;
    /** 职务编号 */
    private Integer roleId;
    /** 所属人员区域编号 */
    private Integer userAreaId;

    /** 所属人员部门编号 */
    private Integer userDeptId;
    /** 所属物流公司编号 */
    private Integer logisticsId;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(String id) {
        this.id = id;
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
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the tel
     */
    public String getTel() {
        return tel;
    }

    /**
     * @param tel
     *            the tel to set
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile
     *            the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    /**
     * @return the msn
     */
    public String getMsn() {
        return msn;
    }

    /**
     * @param msn
     *            the msn to set
     */
    public void setMsn(String msn) {
        this.msn = msn;
    }

    /**
     * @return the qq
     */
    public String getQq() {
        return qq;
    }

    /**
     * @param qq
     *            the qq to set
     */
    public void setQq(String qq) {
        this.qq = qq;
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
     * @return the enable
     */
    public String getEnable() {
        return enable;
    }

    /**
     * @param enable
     *            the enable to set
     */
    public void setEnable(String enable) {
        this.enable = enable;
    }

    /**
     * @return the online
     */
    public Integer getOnline() {
        return online;
    }

    /**
     * @param online
     *            the online to set
     */
    public void setOnline(Integer online) {
        this.online = online;
    }

    /**
     * @return the roleId
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     *            the roleId to set
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * @return the userAreaId
     */
    public Integer getUserAreaId() {
        return userAreaId;
    }

    /**
     * @param userAreaId
     *            the userAreaId to set
     */
    public void setUserAreaId(Integer userAreaId) {
        this.userAreaId = userAreaId;
    }

    /**
     * @return the userDeptId
     */
    public Integer getUserDeptId() {
        return userDeptId;
    }

    /**
     * @param userDeptId
     *            the userDeptId to set
     */
    public void setUserDeptId(Integer userDeptId) {
        this.userDeptId = userDeptId;
    }

    /**
     * @return the logisticsId
     */
    public Integer getLogisticsId() {
        return logisticsId;
    }

    /**
     * @param logisticsId
     *            the logisticsId to set
     */
    public void setLogisticsId(Integer logisticsId) {
        this.logisticsId = logisticsId;
    }

}