/**
 * ClassName  BuyContractDto
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-5-27
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

/**
 * BuyContractDto
 * 
 * @author Lubo
 */
public class BuyContractDto {

    /** 合同流水号 */
    private String id;

    /** 产品分类ID */
    private String productTypeId;

    /** 产品分类名称 */
    private String productTypeName;

    /** 产品合同号 */
    private String productContractCode;

    /** 公司合同号 */
    private String companyContractCode;

    /** 供货商ID */
    private String supplierId;

    /** 供货商名称 */
    private String supplierName;

    /** 合同类型 */
    private String contractType;

    /** 模版类型 */
    private String templateType;

    /** 付款方式 */
    private String paymentWay;

    /** 帐期 */
    private String arterm;

    /** 签订日期 */
    private String date;

    /** 签订日期 */
    private String signDate;

    /** 人员名称 */
    private String userName;

    /** 合同状态 */
    private String status;

    /** 合同金额 */
    private double contractMoney;

    /** 入库金额 */
    private double instockMoney;

    /** 指定金额 */
    private String appointMoney;

    /** 预付金额 */
    private String advanceMoney;

    /** 收票金额 */
    private String invoiceMoney;

    /** 退货合同金额 */
    private double backContractMoney;

    /** 返厂金额 */
    private double backGoodsMoney;

    /** 合同签订起始日期 */
    private String startTime;

    /** 合同签订终止日期 */
    private String endTime;

    /** 角色ID */
    private String roleId;

    /** 发票类型 */
    private String invoiceType;

    /** 增值税税率 */
    private String taxRate;

    /** 付款类型 */
    private String paymentType;

    /** 签订后付款期限 */
    private String contarctPaymentTime;

    /** 预付金额 */
    private String prepayMoney;

    /** 货到后付款期限 */
    private String sendPaymentTime;
    /** 付款单号 **/
    private String paymentId;

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductTypeId() {
        return this.productTypeId;
    }

    public void setProductTypeId(String productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getProductTypeName() {
        return this.productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public String getProductContractCode() {
        return this.productContractCode;
    }

    public void setProductContractCode(String productContractCode) {
        this.productContractCode = productContractCode;
    }

    public String getCompanyContractCode() {
        return this.companyContractCode;
    }

    public void setCompanyContractCode(String companyContractCode) {
        this.companyContractCode = companyContractCode;
    }

    public String getSupplierId() {
        return this.supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return this.supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getContractType() {
        return this.contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getTemplateType() {
        return this.templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    public String getPaymentWay() {
        return this.paymentWay;
    }

    public void setPaymentWay(String paymentWay) {
        this.paymentWay = paymentWay;
    }

    public String getArterm() {
        return this.arterm;
    }

    public void setArterm(String arterm) {
        this.arterm = arterm;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSignDate() {
        return this.signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getContractMoney() {
        return this.contractMoney;
    }

    public void setContractMoney(double contractMoney) {
        this.contractMoney = contractMoney;
    }

    public double getInstockMoney() {
        return this.instockMoney;
    }

    public void setInstockMoney(double instockMoney) {
        this.instockMoney = instockMoney;
    }

    public String getAppointMoney() {
        return this.appointMoney;
    }

    public void setAppointMoney(String appointMoney) {
        this.appointMoney = appointMoney;
    }

    public String getAdvanceMoney() {
        return this.advanceMoney;
    }

    public void setAdvanceMoney(String advanceMoney) {
        this.advanceMoney = advanceMoney;
    }

    public String getInvoiceMoney() {
        return this.invoiceMoney;
    }

    public void setInvoiceMoney(String invoiceMoney) {
        this.invoiceMoney = invoiceMoney;
    }

    public double getBackContractMoney() {
        return this.backContractMoney;
    }

    public void setBackContractMoney(double backContractMoney) {
        this.backContractMoney = backContractMoney;
    }

    public double getBackGoodsMoney() {
        return this.backGoodsMoney;
    }

    public void setBackGoodsMoney(double backGoodsMoney) {
        this.backGoodsMoney = backGoodsMoney;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getRoleId() {
        return this.roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getInvoiceType() {
        return this.invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getTaxRate() {
        return this.taxRate;
    }

    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }

    public String getPaymentType() {
        return this.paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getContarctPaymentTime() {
        return this.contarctPaymentTime;
    }

    public void setContarctPaymentTime(String contarctPaymentTime) {
        this.contarctPaymentTime = contarctPaymentTime;
    }

    public String getPrepayMoney() {
        return this.prepayMoney;
    }

    public void setPrepayMoney(String prepayMoney) {
        this.prepayMoney = prepayMoney;
    }

    public String getSendPaymentTime() {
        return this.sendPaymentTime;
    }

    public void setSendPaymentTime(String sendPaymentTime) {
        this.sendPaymentTime = sendPaymentTime;
    }

}
