package cn.com.thtf.egov.cms.dto;

import java.io.Serializable;

public class BuyContractPreviewDto implements Serializable {

	/**
     * 
     */
	private static final long serialVersionUID = 893952906273398605L;
	private String id;
	// 产品合同编号
	private String productContractCode;
	// 公司合同编号
	private String companyContractCode;
	// 签订日期
	private String buyContractDate;
	// 签订地点
	private String place;
	// 质量标准
	private String quality;
	// 货运方式
	private Integer transportWay;
	// 付款方式
	private Integer paymentWay;
	// 付款类型
	private Integer paymentType;
	// 验收期限
	private Integer checkLimit;
	// 质保期
	private String protectLimit;
	// 账期
	private Integer arterm;
	// 签订后付款期限
	private Integer contractPaymentTime;
	// 预付金额
	private Integer prepayMoney;
	// 货到后付款期限
	private Integer sendPaymentTime;
	// 要求到货日期
	private String requestDate;
	// 联系人电话
	private String supplierLinkmanTel;
	// 联系人名称
	private String linkmanName;
	// 供货商名称
	private String supplierName;
	// 汇款银行名称
	private String remitBankName;
	// 汇款银行账号
	private String remitBankAccount;
	// 产品总监名称
	private String prodSuperintendentName;

	// 需方名称
	private String companyName;
	// 需方开户银行
	private String invoiceBankName;
	// 需方开户银行电话
	private String invoiceBankTel;
	// 需方开户银行地址
	private String invoiceBankAddress;
	// 需方开户银行账号
	private String invoiceBankAccount;
	// 税号
	private String taxNumber;
	private String deliveryTerms;

	public String getDeliveryTerms() {
		return deliveryTerms;
	}

	public void setDeliveryTerms(String deliveryTerms) {
		this.deliveryTerms = deliveryTerms;
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

	public String getCompanyContractCode() {
		return companyContractCode;
	}

	public void setCompanyContractCode(String companyContractCode) {
		this.companyContractCode = companyContractCode;
	}

	public String getBuyContractDate() {
		return buyContractDate;
	}

	public void setBuyContractDate(String buyContractDate) {
		this.buyContractDate = buyContractDate;
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

	public Integer getTransportWay() {
		return transportWay;
	}

	public void setTransportWay(Integer transportWay) {
		this.transportWay = transportWay;
	}

	public Integer getPaymentWay() {
		return paymentWay;
	}

	public void setPaymentWay(Integer paymentWay) {
		this.paymentWay = paymentWay;
	}

	public String getProtectLimit() {
		return protectLimit;
	}

	public void setProtectLimit(String protectLimit) {
		this.protectLimit = protectLimit;
	}

	public Integer getArterm() {
		return arterm;
	}

	public void setArterm(Integer arterm) {
		this.arterm = arterm;
	}

	public Integer getContractPaymentTime() {
		return contractPaymentTime;
	}

	public void setContractPaymentTime(Integer contractPaymentTime) {
		this.contractPaymentTime = contractPaymentTime;
	}

	public Integer getPrepayMoney() {
		return prepayMoney;
	}

	public void setPrepayMoney(Integer prepayMoney) {
		this.prepayMoney = prepayMoney;
	}

	public Integer getSendPaymentTime() {
		return sendPaymentTime;
	}

	public void setSendPaymentTime(Integer sendPaymentTime) {
		this.sendPaymentTime = sendPaymentTime;
	}

	public String getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}

	public String getSupplierLinkmanTel() {
		return supplierLinkmanTel;
	}

	public void setSupplierLinkmanTel(String supplierLinkmanTel) {
		this.supplierLinkmanTel = supplierLinkmanTel;
	}

	public String getLinkmanName() {
		return linkmanName;
	}

	public void setLinkmanName(String linkmanName) {
		this.linkmanName = linkmanName;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getRemitBankName() {
		return remitBankName;
	}

	public void setRemitBankName(String remitBankName) {
		this.remitBankName = remitBankName;
	}

	public String getRemitBankAccount() {
		return remitBankAccount;
	}

	public void setRemitBankAccount(String remitBankAccount) {
		this.remitBankAccount = remitBankAccount;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getInvoiceBankName() {
		return invoiceBankName;
	}

	public void setInvoiceBankName(String invoiceBankName) {
		this.invoiceBankName = invoiceBankName;
	}

	public String getInvoiceBankTel() {
		return invoiceBankTel;
	}

	public void setInvoiceBankTel(String invoiceBankTel) {
		this.invoiceBankTel = invoiceBankTel;
	}

	public String getInvoiceBankAddress() {
		return invoiceBankAddress;
	}

	public void setInvoiceBankAddress(String invoiceBankAddress) {
		this.invoiceBankAddress = invoiceBankAddress;
	}

	public String getInvoiceBankAccount() {
		return invoiceBankAccount;
	}

	public void setInvoiceBankAccount(String invoiceBankAccount) {
		this.invoiceBankAccount = invoiceBankAccount;
	}

	public String getTaxNumber() {
		return taxNumber;
	}

	public void setTaxNumber(String taxNumber) {
		this.taxNumber = taxNumber;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getCheckLimit() {
		return checkLimit;
	}

	public void setCheckLimit(Integer checkLimit) {
		this.checkLimit = checkLimit;
	}

	public String getProdSuperintendentName() {
		return prodSuperintendentName;
	}

	public void setProdSuperintendentName(String prodSuperintendentName) {
		this.prodSuperintendentName = prodSuperintendentName;
	}

	public Integer getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
	}
}
