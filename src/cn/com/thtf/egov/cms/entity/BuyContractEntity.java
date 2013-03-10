package cn.com.thtf.egov.cms.entity;

import java.math.BigDecimal;
import java.util.Date;

import cn.com.thtf.egov.cms.application.BaseEntity;

public class BuyContractEntity extends BaseEntity {

    private static final long serialVersionUID = -7344017619188041813L;

    private String id;

    private String productContractCode;

    private String companyContarctCode;

    private Date date;

    private Integer status;

    private Integer contractType;

    private Integer templateType;

    private String place;

    private String quality;

    private Integer check;

    private String protect;

    private String clause;

    private String file;

    private Integer supplierId;

    private Integer supplierLinkmanId;

    private Integer productTypeId;

    private Integer paymentType;

    private Integer paymentWay;

    private Integer arterm;

    private Integer contarctPaymentTime;

    private BigDecimal prepayMoney;

    private Integer sendPaymentTime;

    private Integer creditTypeId;

    private String projectName;

    private BigDecimal climit;

    private BigDecimal free;

    private String requestDate;

    private String userId;

    private String userName;

    private String text;

    private BigDecimal money;

    private Integer legalFlag;

    private String legalName;

    private String legalDate;

    private Integer legalIdea;

    private String legalText;

    private String buyMajName;

    private String buyMajDate;

    private Integer buyMajIdea1;

    private Integer buyMajIdea2;

    private Integer buyMajIdea3;

    private String buyMajText;

    private String opeMajName;

    private String opeMajDate;

    private Integer opeMajIdea;

    private String opeMajText;

    private String dateTime;

    private Integer deliveryTerms;

    public Integer getDeliveryTerms() {
        return this.deliveryTerms;
    }

    public void setDeliveryTerms(Integer deliveryTerms) {
        this.deliveryTerms = deliveryTerms;
    }

    public String getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductContractCode() {
        return productContractCode;
    }

    public void setProductContractCode(String productContractCode) {
        this.productContractCode = productContractCode;
    }

    public String getCompanyContarctCode() {
        return companyContarctCode;
    }

    public void setCompanyContarctCode(String companyContarctCode) {
        this.companyContarctCode = companyContarctCode;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getContractType() {
        return this.contractType;
    }

    public void setContractType(Integer contractType) {
        this.contractType = contractType;
    }

    public Integer getTemplateType() {
        return templateType;
    }

    public void setTemplateType(Integer templateType) {
        this.templateType = templateType;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public Integer getCheck() {
        return check;
    }

    public void setCheck(Integer check) {
        this.check = check;
    }

    public String getProtect() {
        return protect;
    }

    public void setProtect(String protect) {
        this.protect = protect;
    }

    public String getClause() {
        return clause;
    }

    public void setClause(String clause) {
        this.clause = clause;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getSupplierLinkmanId() {
        return supplierLinkmanId;
    }

    public void setSupplierLinkmanId(Integer supplierLinkmanId) {
        this.supplierLinkmanId = supplierLinkmanId;
    }

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getPaymentWay() {
        return paymentWay;
    }

    public void setPaymentWay(Integer paymentWay) {
        this.paymentWay = paymentWay;
    }

    public Integer getArterm() {
        return arterm;
    }

    public void setArterm(Integer arterm) {
        this.arterm = arterm;
    }

    public Integer getContarctPaymentTime() {
        return contarctPaymentTime;
    }

    public void setContarctPaymentTime(Integer contarctPaymentTime) {
        this.contarctPaymentTime = contarctPaymentTime;
    }

    public BigDecimal getPrepayMoney() {
        return prepayMoney;
    }

    public void setPrepayMoney(BigDecimal prepayMoney) {
        this.prepayMoney = prepayMoney;
    }

    public Integer getSendPaymentTime() {
        return sendPaymentTime;
    }

    public void setSendPaymentTime(Integer sendPaymentTime) {
        this.sendPaymentTime = sendPaymentTime;
    }

    public Integer getCreditTypeId() {
        return creditTypeId;
    }

    public void setCreditTypeId(Integer creditTypeId) {
        this.creditTypeId = creditTypeId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public BigDecimal getClimit() {
        return climit;
    }

    public void setClimit(BigDecimal climit) {
        this.climit = climit;
    }

    public BigDecimal getFree() {
        return free;
    }

    public void setFree(BigDecimal free) {
        this.free = free;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getLegalFlag() {
        return legalFlag;
    }

    public void setLegalFlag(Integer legalFlag) {
        this.legalFlag = legalFlag;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public String getLegalDate() {
        return legalDate;
    }

    public void setLegalDate(String legalDate) {
        this.legalDate = legalDate;
    }

    public Integer getLegalIdea() {
        return legalIdea;
    }

    public void setLegalIdea(Integer legalIdea) {
        this.legalIdea = legalIdea;
    }

    public String getLegalText() {
        return legalText;
    }

    public void setLegalText(String legalText) {
        this.legalText = legalText;
    }

    public String getBuyMajName() {
        return buyMajName;
    }

    public void setBuyMajName(String buyMajName) {
        this.buyMajName = buyMajName;
    }

    public String getBuyMajDate() {
        return buyMajDate;
    }

    public void setBuyMajDate(String buyMajDate) {
        this.buyMajDate = buyMajDate;
    }

    public Integer getBuyMajIdea1() {
        return buyMajIdea1;
    }

    public void setBuyMajIdea1(Integer buyMajIdea1) {
        this.buyMajIdea1 = buyMajIdea1;
    }

    public Integer getBuyMajIdea2() {
        return buyMajIdea2;
    }

    public void setBuyMajIdea2(Integer buyMajIdea2) {
        this.buyMajIdea2 = buyMajIdea2;
    }

    public Integer getBuyMajIdea3() {
        return buyMajIdea3;
    }

    public void setBuyMajIdea3(Integer buyMajIdea3) {
        this.buyMajIdea3 = buyMajIdea3;
    }

    public String getBuyMajText() {
        return buyMajText;
    }

    public void setBuyMajText(String buyMajText) {
        this.buyMajText = buyMajText;
    }

    public String getOpeMajName() {
        return opeMajName;
    }

    public void setOpeMajName(String opeMajName) {
        this.opeMajName = opeMajName;
    }

    public String getOpeMajDate() {
        return opeMajDate;
    }

    public void setOpeMajDate(String opeMajDate) {
        this.opeMajDate = opeMajDate;
    }

    public Integer getOpeMajIdea() {
        return opeMajIdea;
    }

    public void setOpeMajIdea(Integer opeMajIdea) {
        this.opeMajIdea = opeMajIdea;
    }

    public String getOpeMajText() {
        return opeMajText;
    }

    public void setOpeMajText(String opeMajText) {
        this.opeMajText = opeMajText;
    }
}
