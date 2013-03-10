package cn.com.thtf.egov.cms.entity;

import java.math.BigDecimal;
import java.util.Date;

import cn.com.thtf.egov.cms.application.BaseEntity;

public class CustomerCreditEntity extends BaseEntity {

    private static final long serialVersionUID = 4697735949171284485L;

    private Integer id;

    private Integer productTypeId;

    private Integer creditTypeId;

    private Integer customerId;

    private String projectName;

    private String enable;

    private Integer arterm;

    private BigDecimal climit;

    private String userId;

    private String userName;

    private String datetime;

    private Date timeStamp;

    private String agreementCode;

    private String text;

    public String getAgreementCode() {
        return this.agreementCode;
    }

    public void setAgreementCode(String agreementCode) {
        this.agreementCode = agreementCode;
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

    public void setClimit(BigDecimal climit) {
        this.climit = climit;
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

    public Date getTimeStamp() {
        return this.timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
