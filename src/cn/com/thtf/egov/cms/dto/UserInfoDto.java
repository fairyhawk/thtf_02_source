/**
 * ClassName  UserInfo
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-4-1
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

/**
 * UserInfo
 * 
 * @author Lubo
 * 
 */
public class UserInfoDto extends BaseInfoDto {

    // /** 自动生成序列化ID */
    // private static final long serialVersionUID = 1L;

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
    private Integer role_id;
    /** 所属人员区域编号 */
    private Integer user_area_id;
    /** 所属人员部门编号 */
    private Integer user_dept_id;
    /** 所属物流公司编号 */
    private Integer logisticsId;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return this.tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMsn() {
        return this.msn;
    }

    public void setMsn(String msn) {
        this.msn = msn;
    }

    public String getQq() {
        return this.qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getDatetime() {
        return this.datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getEnable() {
        return this.enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public Integer getOnline() {
        return this.online;
    }

    public void setOnline(Integer online) {
        this.online = online;
    }

    public Integer getRole_id() {
        return this.role_id;
    }

    public void setRole_id(Integer roleId) {
        this.role_id = roleId;
    }

    public Integer getUser_area_id() {
        return this.user_area_id;
    }

    public void setUser_area_id(Integer userAreaId) {
        this.user_area_id = userAreaId;
    }

    public Integer getUser_dept_id() {
        return this.user_dept_id;
    }

    public void setUser_dept_id(Integer userDeptId) {
        this.user_dept_id = userDeptId;
    }

    public Integer getLogisticsId() {
        return this.logisticsId;
    }

    public void setLogisticsId(Integer logisticsId) {
        this.logisticsId = logisticsId;
    }
}
