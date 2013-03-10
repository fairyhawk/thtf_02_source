package cn.com.thtf.egov.cms.dto;

import java.math.BigDecimal;

import java.sql.Timestamp;

/**
 * 用户信用 dto
 * 
 * @author 李乐伟
 */
public class CustomerCreditLimitDto {

    private Integer id;

    private Integer productTypeId;

    private Integer creditTypeId;

    private Integer customerId;

    private String projectName;

    private String customerName;

    private String creditTypeName;

    private String productTypeName;

    private String enable;

    private Integer arterm;

    private BigDecimal climit;

    private BigDecimal creditLimit;

    private BigDecimal usedCredit;

    private BigDecimal lockCredit;

    private BigDecimal distributeCredit; // 可分配额度

    private BigDecimal canUseCredit;

    private BigDecimal exceedOweMoney;// 超期欠款金额

    private Integer maxExceedDays;// 最大超期天数

    private String userId;

    private String userName;

    private String datetime;

    private Timestamp timeStamp;

    private Integer roleId;

    private Integer userAreaId;

    private String agreementCode;// 协议合同编号

    private String text;// 特殊说明
    
    private BigDecimal inTransitMoney;//在途款

    public Integer getUserAreaId() {
        return this.userAreaId;
    }

    public void setUserAreaId(Integer userAreaId) {
        this.userAreaId = userAreaId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    public Integer getCreditTypeId() {
        return creditTypeId;
    }

    public void setCreditTypeId(Integer creditTypeId) {
        this.creditTypeId = creditTypeId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCreditTypeName() {
        return creditTypeName;
    }

    public void setCreditTypeName(String creditTypeName) {
        this.creditTypeName = creditTypeName;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public Integer getArterm() {
        return arterm;
    }

    public void setArterm(Integer arterm) {
        this.arterm = arterm;
    }

    public BigDecimal getClimit() {
        return climit;
    }

    public BigDecimal getExceedOweMoney() {
        return exceedOweMoney;
    }

    public void setExceedOweMoney(BigDecimal exceedOweMoney) {
        this.exceedOweMoney = exceedOweMoney;
    }

    public Integer getMaxExceedDays() {
        return maxExceedDays;
    }

    public void setMaxExceedDays(Integer maxExceedDays) {
        this.maxExceedDays = maxExceedDays;
    }

    public void setClimit(BigDecimal climit) {
        this.climit = climit;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    public BigDecimal getUsedCredit() {
        return usedCredit;
    }

    public void setUsedCredit(BigDecimal usedCredit) {
        this.usedCredit = usedCredit;
    }

    public BigDecimal getLockCredit() {
        return lockCredit;
    }

    public void setLockCredit(BigDecimal lockCredit) {
        this.lockCredit = lockCredit;
    }

    public BigDecimal getDistributeCredit() {
        return distributeCredit;
    }

    public void setDistributeCredit(BigDecimal distributeCredit) {
        this.distributeCredit = distributeCredit;
    }

    public BigDecimal getCanUseCredit() {
        return canUseCredit;
    }

    public void setCanUseCredit(BigDecimal canUseCredit) {
        this.canUseCredit = canUseCredit;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getAgreementCode() {
        return agreementCode;
    }

    public void setAgreementCode(String agreementCode) {
        this.agreementCode = agreementCode;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    public BigDecimal getInTransitMoney() {
        return inTransitMoney;
    }

    public void setInTransitMoney(BigDecimal inTransitMoney) {
        this.inTransitMoney = inTransitMoney;
    }

}
