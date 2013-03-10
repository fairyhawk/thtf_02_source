/**
 * ClassName  BuyBackContractDto
 *
 * History
 * Create User: ChenHuajiang
 * Create Date: 2010-6-13
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

import java.io.Serializable;

/**
 * 新建采购退货合同-采购合同信息
 * 
 * @author ChenHuajiang
 */

public class BuyBackContractDto implements Serializable {

    private static final long serialVersionUID = 3310464351313387488L;
    /** 采购后退货合同编号 */
    private String buyBackContractId;
    /** 产品分类ID */
    private String prodTypeId;

    /** 产品分类名称 */
    private String prodTypeName;

    /** 产品合同号 */
    private String prodContractCode;

    /** 公司合同号 */
    private String compContractCode;

    /** 供货商ID */
    private String supplierId;

    /** 供货商名称 */
    private String supplierName;

    /** 合同类型 */
    private String contractType;

    /** 签订地点 */
    private String place;

    /** 模版类型 */
    private String templateType;

    /** 付款方式 */
    private String paymentWay;

    /** 签订日期 */
    private String signDate;

    /** 合同金额 */
    private String contractMoney;

    /** 发票类型 */
    private String invoiceType;

    /** 增值税税率 */
    private String taxRate;

    /** 付款类型 */
    private String paymentType;

    /** 预付金额 */
    private String prepayMoney;

    /** 供货商所属省 */
    private String province;

    /** 供货商所属市 */
    private String city;

    /** 汇款银行名称 */
    private String remitBankName;

    /** 汇款银行账号 */
    private String remitBankAccount;

    /** 联系人Id */
    private Integer linkmanId;

    /** 联系人姓名 */
    private String linkman;

    /** 联系人电话 */
    private String tel;

    /** 联系人传真 */
    private String fax;
    private String userName;
    private String backContractMoney;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBackContractMoney() {
        return backContractMoney;
    }

    public void setBackContractMoney(String backContractMoney) {
        this.backContractMoney = backContractMoney;
    }

    public String getBuyBackContractId() {
        return buyBackContractId;
    }

    public void setBuyBackContractId(String buyBackContractId) {
        this.buyBackContractId = buyBackContractId;
    }

    public String getProdTypeId() {
        return this.prodTypeId;
    }

    public void setProdTypeId(String prodTypeId) {
        this.prodTypeId = prodTypeId;
    }

    public String getProdTypeName() {
        return this.prodTypeName;
    }

    public void setProdTypeName(String prodTypeName) {
        this.prodTypeName = prodTypeName;
    }

    public String getProdContractCode() {
        return this.prodContractCode;
    }

    public void setProdContractCode(String prodContractCode) {
        this.prodContractCode = prodContractCode;
    }

    public String getCompContractCode() {
        return this.compContractCode;
    }

    public void setCompContractCode(String compContractCode) {
        this.compContractCode = compContractCode;
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

    public String getSignDate() {
        return this.signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }

    public String getContractMoney() {
        return this.contractMoney;
    }

    public void setContractMoney(String contractMoney) {
        this.contractMoney = contractMoney;
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

    public String getPrepayMoney() {
        return this.prepayMoney;
    }

    public void setPrepayMoney(String prepayMoney) {
        this.prepayMoney = prepayMoney;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRemitBankName() {
        return this.remitBankName;
    }

    public void setRemitBankName(String remitBankName) {
        this.remitBankName = remitBankName;
    }

    public String getRemitBankAccount() {
        return this.remitBankAccount;
    }

    public void setRemitBankAccount(String remitBankAccount) {
        this.remitBankAccount = remitBankAccount;
    }

    public Integer getLinkmanId() {
        return this.linkmanId;
    }

    public void setLinkmanId(Integer linkmanId) {
        this.linkmanId = linkmanId;
    }

    public String getLinkman() {
        return this.linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getTel() {
        return this.tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return this.fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getPlace() {
        return this.place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
