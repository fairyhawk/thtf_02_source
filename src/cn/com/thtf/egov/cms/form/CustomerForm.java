package cn.com.thtf.egov.cms.form;

import org.apache.struts.action.ActionForm;

public class CustomerForm extends ActionForm{


	/**
	 * CustomerForm 客户form
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;// 客户Id

	private String name;// 客户名称

	private String area;// 客户所属区域

	private String province;// 客户所属省

	private String city;// 客户所属市

	private String address;// 客户地址

	private String postcode;// 客户地址邮编

	private String invoiceBankName;// 开票银行名称

	private String invoiceBankAccount;// 开票银行账号

	private String taxNumber;// 税号

	private String invoiceBankAddress;// 开票银行地址

	private String invoiceBankTel;// 开票银行电话

	private Integer invoiceType;// 发票类型

	private Integer taxRate;// 增值税税率

	private String remitBankName;// 汇款银行名称

	private String remitBankAccount;// 汇款银行账号

	private Integer linkmanId;// 联系人Id

	private String linkman;// 联系人姓名

	private String role;// 联系人职务

	private String tel;// 联系人电话

	private String fax;// 联系人传真

	private String mobile;// 联系人手机

	private String mail;// 联系人mail

	private String msn;// 联系人msn

	private String qq;// 联系人qq

	// private Integer linkFlag;// 联系人是否正在使用

	private Integer receiveId;// 接受单位Id

	private String receiveName;// 接受单位名称

	private String supplierAddress;// 客户发货地址

	private String supplierPostcode;// 客户发货地址邮编

	private String supplierLinkman;// 客户发货地址联系人

	private String supplierTel;// 客户发货地址联系人电话

	private String supplierMobile;// 客户发货地址联系人手机

	// private Integer supplierFlag;// 客户的发货地址是否正在使用

	public String getReceiveName() {
		return receiveName;
	}

	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}

	public String getSupplierAddress() {
		return supplierAddress;
	}

	public void setSupplierAddress(String supplierAddress) {
		this.supplierAddress = supplierAddress;
	}

	public String getSupplierLinkman() {
		return supplierLinkman;
	}

	public void setSupplierLinkman(String supplierLinkman) {
		this.supplierLinkman = supplierLinkman;
	}

	public String getSupplierMobile() {
		return supplierMobile;
	}

	public void setSupplierMobile(String supplierMobile) {
		this.supplierMobile = supplierMobile;
	}

	public String getSupplierPostcode() {
		return supplierPostcode;
	}

	public void setSupplierPostcode(String supplierPostcode) {
		this.supplierPostcode = supplierPostcode;
	}

	public String getSupplierTel() {
		return supplierTel;
	}

	public void setSupplierTel(String supplierTel) {
		this.supplierTel = supplierTel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMsn() {
		return msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getRemitBankAccount() {
		return remitBankAccount;
	}

	public void setRemitBankAccount(String remitBankAccount) {
		this.remitBankAccount = remitBankAccount;
	}

	public String getRemitBankName() {
		return remitBankName;
	}

	public void setRemitBankName(String remitBankName) {
		this.remitBankName = remitBankName;
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

	public Integer getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(Integer taxRate) {
		this.taxRate = taxRate;
	}

	public Integer getLinkmanId() {
		return linkmanId;
	}

	public void setLinkmanId(Integer linkmanId) {
		this.linkmanId = linkmanId;
	}

	public Integer getReceiveId() {
		return receiveId;
	}

	public void setReceiveId(Integer receiveId) {
		this.receiveId = receiveId;
	}


}
