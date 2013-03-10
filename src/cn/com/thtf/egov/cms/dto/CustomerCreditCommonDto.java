package cn.com.thtf.egov.cms.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class CustomerCreditCommonDto {

    private BigDecimal assignedLimit; // 已分配信用

    private BigDecimal usedLimit; // 已使用信用

    private BigDecimal lockedLimit; // 冻结信用

    private BigDecimal onTheWayLimit; // 在途信用

    private BigDecimal freeLimit; // 剩余信用

    private String creditTypeName; // 信用类型名称

    private String projectName;// 项目名称

    private Integer paymentTerm;// 帐期

    private String creditStatus;// 客户信用状态

    private String creditTypeId;// 信用类型ID

    private String customerId;// 客户ID

    private String customerCrdId;// 客户信用ID

    public String getCreditTypeId() {
        return creditTypeId;
    }

    public void setCreditTypeId(String creditTypeId) {
        this.creditTypeId = creditTypeId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    private Timestamp timeStamp;// 客户信用时间戳

    public BigDecimal getAssignedLimit() {
        return this.assignedLimit;
    }

    public void setAssignedLimit(BigDecimal assignedLimit) {
        this.assignedLimit = assignedLimit;
    }

    public BigDecimal getUsedLimit() {
        return this.usedLimit;
    }

    public void setUsedLimit(BigDecimal usedLimit) {
        this.usedLimit = usedLimit;
    }

    public BigDecimal getLockedLimit() {
        return this.lockedLimit;
    }

    public void setLockedLimit(BigDecimal lockedLimit) {
        this.lockedLimit = lockedLimit;
    }

    public BigDecimal getOnTheWayLimit() {
        return this.onTheWayLimit;
    }

    public void setOnTheWayLimit(BigDecimal onTheWayLimit) {
        this.onTheWayLimit = onTheWayLimit;
    }

    public BigDecimal getFreeLimit() {
        return this.freeLimit;
    }

    public void setFreeLimit(BigDecimal freeLimit) {
        this.freeLimit = freeLimit;
    }

    public String getCreditTypeName() {
        return this.creditTypeName;
    }

    public void setCreditTypeName(String creditTypeName) {
        this.creditTypeName = creditTypeName;
    }

    public String getProjectName() {
        return this.projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getPaymentTerm() {
        return this.paymentTerm;
    }

    public void setPaymentTerm(Integer paymentTerm) {
        this.paymentTerm = paymentTerm;
    }

    public String getCreditStatus() {
        return this.creditStatus;
    }

    public void setCreditStatus(String creditStatus) {
        this.creditStatus = creditStatus;
    }

    public Timestamp getTimeStamp() {
        return this.timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getCustomerCrdId() {
        return this.customerCrdId;
    }

    public void setCustomerCrdId(String customerCrdId) {
        this.customerCrdId = customerCrdId;
    }
}
