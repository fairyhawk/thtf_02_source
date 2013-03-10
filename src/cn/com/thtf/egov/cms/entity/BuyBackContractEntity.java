package cn.com.thtf.egov.cms.entity;

import java.math.BigDecimal;

import cn.com.thtf.egov.cms.application.BaseEntity;

public class BuyBackContractEntity extends BaseEntity {

    private static final long serialVersionUID = 5471770450487836557L;

    private String id;

    private String buyContractId;

    private Integer productTypeId;

    private String productContractCode;

    private String companyContractCode;

    private String date;

    private Integer status;

    private Integer templateType;

    private String place;

    private Integer supplierAddressId;

    private Integer backWay;

    private String requestSendDate;

    private String userId;

    private String userName;

    private String text;

    private BigDecimal money;

    private String legalId;

    private String legalName;

    private String legalDate;

    private String legalIdea;

    private String legalText;

    private String buyManId;

    private String buyManName;

    private String buyManDate;

    private String buyManIdea;

    private String buyManText;

    private String opeMajId;

    private String opeMajName;

    private String opeMajDate;

    private String opeMajIdea;

    private String opeMajText;

    private String datetime;

    private String file;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getProductTypeId() {
        return this.productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getBuyContractId() {
        return buyContractId;
    }

    public void setBuyContractId(String buyContractId) {
        this.buyContractId = buyContractId;
    }

    public String getProductContractCode() {
        return productContractCode;
    }

    public void setProductContractCode(String productContractCode) {
        this.productContractCode = productContractCode;
    }

    public String getCompanyContractCode() {
        return companyContractCode;
    }

    public void setCompanyContractCode(String companyContractCode) {
        this.companyContractCode = companyContractCode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Integer getSupplierAddressId() {
        return supplierAddressId;
    }

    public void setSupplierAddressId(Integer supplierAddressId) {
        this.supplierAddressId = supplierAddressId;
    }

    public Integer getBackWay() {
        return backWay;
    }

    public void setBackWay(Integer backWay) {
        this.backWay = backWay;
    }

    public String getRequestSendDate() {
        return requestSendDate;
    }

    public void setRequestSendDate(String requestSendDate) {
        this.requestSendDate = requestSendDate;
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

    public String getLegalId() {
        return legalId;
    }

    public void setLegalId(String legalId) {
        this.legalId = legalId;
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

    public String getLegalIdea() {
        return legalIdea;
    }

    public void setLegalIdea(String legalIdea) {
        this.legalIdea = legalIdea;
    }

    public String getLegalText() {
        return legalText;
    }

    public void setLegalText(String legalText) {
        this.legalText = legalText;
    }

    public String getBuyManId() {
        return buyManId;
    }

    public void setBuyManId(String buyManId) {
        this.buyManId = buyManId;
    }

    public String getBuyManName() {
        return buyManName;
    }

    public void setBuyManName(String buyManName) {
        this.buyManName = buyManName;
    }

    public String getBuyManDate() {
        return buyManDate;
    }

    public void setBuyManDate(String buyManDate) {
        this.buyManDate = buyManDate;
    }

    public String getBuyManIdea() {
        return buyManIdea;
    }

    public void setBuyManIdea(String buyManIdea) {
        this.buyManIdea = buyManIdea;
    }

    public String getBuyManText() {
        return buyManText;
    }

    public void setBuyManText(String buyManText) {
        this.buyManText = buyManText;
    }

    public String getOpeMajId() {
        return opeMajId;
    }

    public void setOpeMajId(String opeMajId) {
        this.opeMajId = opeMajId;
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

    public String getOpeMajIdea() {
        return opeMajIdea;
    }

    public void setOpeMajIdea(String opeMajIdea) {
        this.opeMajIdea = opeMajIdea;
    }

    public String getOpeMajText() {
        return opeMajText;
    }

    public void setOpeMajText(String opeMajText) {
        this.opeMajText = opeMajText;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getFile() {
        return this.file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}