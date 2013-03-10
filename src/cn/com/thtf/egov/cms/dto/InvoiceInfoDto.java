package cn.com.thtf.egov.cms.dto;

import java.math.BigDecimal;

import cn.com.thtf.egov.cms.constant.Constants;

/**
 * 开票信息Dto
 * 
 * @author LiLewei
 */
public class InvoiceInfoDto {

    private String id; // 开票申请单号

    private Integer status; // 开票状态

    private Integer customerId; // 客户id

    private String customerName;// 客户名称

    private Integer invoiceType; // 发票类型

    private String taxNumber; // 税号

    private String invoiceBankName; // 开票银行名称

    private String invoiceBankAccount; // 开票银行账号

    private String invoiceBankAddress; // 开票银行地址

    private String invoiceBankTel; // 开票银行电话

    private Integer productTypeId;// 产品分类id

    private String productTypeName;// 产品分类名称

    private String userId;// 申请人id

    private String userName; // 申请人

    private Integer userAreaId;// 申请人区域id

    private String date; // 申请日期

    private String notifyId;// 通知人 id

    private String notifyName;// 通知人 name

    private String notifyDate;// 通知日期

    private BigDecimal money;// 开票金额

    private String text; // 特殊说明

    private String confirmId; // K3确认登录名
    private String confirmName; // K3确认用户名
    private String confirmDate;//K3确认时间
    
    private String text_k3;//K3补充说

    /**
     * @return the text_k3
     */
    public String getText_k3() {
        return text_k3;
    }

    /**
     * @param text_k3 the text_k3 to set
     */
    public void setText_k3(String text_k3) {
        this.text_k3 = text_k3;
    }

    /**
     * @return the confirmDate
     */
    public String getConfirmDate() {
        return confirmDate;
    }

    /**
     * @param confirmDate the confirmDate to set
     */
    public void setConfirmDate(String confirmDate) {
        this.confirmDate = confirmDate;
    }

    @SuppressWarnings("unused")
    private boolean waitForNotify;

    public boolean isWaitForNotify() {
        return status == Constants.SELL6.intValue();
    }

    public String getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(String notifyId) {
        this.notifyId = notifyId;
    }

    public String getNotifyName() {
        return notifyName;
    }

    public void setNotifyName(String notifyName) {
        this.notifyName = notifyName;
    }

    public String getNotifyDate() {
        return notifyDate;
    }

    public void setNotifyDate(String notifyDate) {
        this.notifyDate = notifyDate;
    }

    public void setWaitForNotify(boolean waitForNotify) {
        this.waitForNotify = waitForNotify;
    }

    public String getId() {
        return id;
    }

    public Integer getUserAreaId() {
        return userAreaId;
    }

    public void setUserAreaId(Integer userAreaId) {
        this.userAreaId = userAreaId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(Integer invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getInvoiceBankName() {
        return invoiceBankName;
    }

    public void setInvoiceBankName(String invoiceBankName) {
        this.invoiceBankName = invoiceBankName;
    }

    public String getInvoiceBankAccount() {
        return invoiceBankAccount;
    }

    public void setInvoiceBankAccount(String invoiceBankAccount) {
        this.invoiceBankAccount = invoiceBankAccount;
    }

    public String getInvoiceBankAddress() {
        return invoiceBankAddress;
    }

    public void setInvoiceBankAddress(String invoiceBankAddress) {
        this.invoiceBankAddress = invoiceBankAddress;
    }

    public String getInvoiceBankTel() {
        return invoiceBankTel;
    }

    public void setInvoiceBankTel(String invoiceBankTel) {
        this.invoiceBankTel = invoiceBankTel;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getMoney() {
        return this.money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the confirmId
     */
    public String getConfirmId() {
        return confirmId;
    }

    /**
     * @param confirmId the confirmId to set
     */
    public void setConfirmId(String confirmId) {
        this.confirmId = confirmId;
    }

    /**
     * @return the confirmName
     */
    public String getConfirmName() {
        return confirmName;
    }

    /**
     * @param confirmName the confirmName to set
     */
    public void setConfirmName(String confirmName) {
        this.confirmName = confirmName;
    }

}
