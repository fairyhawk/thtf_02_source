package cn.com.thtf.egov.cms.dto;

import java.io.Serializable;

/**
 * 采购退货合同预览
 * 
 * @author HanHaiyun
 */
public class BuyBackContractPreviewDto implements Serializable {

	private static final long serialVersionUID = -4168948499400144468L;
	/**
	 * 供货商名称
	 */
	private String supplierName;
	/**
	 * 采购退货合同编号
	 */
	private String buyBackContractId;
	/**
	 * 采购退货合同签订日期
	 */
	private String buyBackContractDate;
	/**
	 * 采购退货合同签订地点
	 */
	private String buyBackContractPlace;
	/**
	 * 采购合同签订日期
	 */
	private String buyContractDate;
	/**
	 * 采购合同名称(由产品类型名称+“采购”)
	 */
	private String buyContractName;
	/**
	 * 采购合同金额
	 */
	private String buyContractMoney;
	/**
	 * 退货方式
	 */
	private Integer backWay;
	/**
	 * 采购合同编号
	 */
	private String buyContractId;
	/**
	 * 公司名称
	 */
	private String compName;
	/**
	 * 供货商联系人传真
	 */
	private String supLinkmanFax;
	/**
	 * 供货商开票银行名称
	 */
	private String supInvoiceBankName;
	/**
	 * 供货商开票银行账号
	 */
	private String supInvoiceBankAccount;
	/**
	 * 产品总监电话
	 */
	private String prodSuperintendentTel;
	/**
	 * 产品总监名称
	 */
	private String prodSuperintendentName;

	/**
	 * 产品部门传真
	 */
	private String prodDeptFax;
	/**
	 * 公司汇款银行名称
	 */
	private String comRemitBankName;
	/**
	 * 公司汇款银行账号
	 */
	private String compRemitBankAccount;
	/**
	 * 供货商联系人电话
	 */
	private String supLinkmanTel;
	/**
	 * 供货商联系人名称
	 */
	private String supLinkmanName;

	/**
	 * 发货地址
	 */
	private String supAddress;
	/**
	 * 要求到货日期
	 */
	private String requestSendDate;

	private String productContractCode;
	private String productContractCodeOfBuy;

	public String getProductContractCodeOfBuy() {
		return productContractCodeOfBuy;
	}

	public void setProductContractCodeOfBuy(String productContractCodeOfBuy) {
		this.productContractCodeOfBuy = productContractCodeOfBuy;
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

	private String companyContractCode;
	/**
	 * 产品类型名称
	 */
	private String prodTypeName;

	public String getProdTypeName() {
		return prodTypeName;
	}

	public void setProdTypeName(String prodTypeName) {
		this.prodTypeName = prodTypeName;
	}

	public String getSupAddress() {
		return supAddress;
	}

	public void setSupAddress(String supAddress) {
		this.supAddress = supAddress;
	}

	public String getRequestSendDate() {
		return requestSendDate;
	}

	public void setRequestSendDate(String requestSendDate) {
		this.requestSendDate = requestSendDate;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getBuyBackContractId() {
		return buyBackContractId;
	}

	public void setBuyBackContractId(String buyBackContractId) {
		this.buyBackContractId = buyBackContractId;
	}

	public String getBuyBackContractDate() {
		return buyBackContractDate;
	}

	public void setBuyBackContractDate(String buyBackContractDate) {
		this.buyBackContractDate = buyBackContractDate;
	}

	public String getBuyBackContractPlace() {
		return buyBackContractPlace;
	}

	public void setBuyBackContractPlace(String buyBackContractPlace) {
		this.buyBackContractPlace = buyBackContractPlace;
	}

	public String getBuyContractDate() {
		return buyContractDate;
	}

	public void setBuyContractDate(String buyContractDate) {
		this.buyContractDate = buyContractDate;
	}

	public String getBuyContractMoney() {
		return buyContractMoney;
	}

	public void setBuyContractMoney(String buyContractMoney) {
		this.buyContractMoney = buyContractMoney;
	}

	public Integer getBackWay() {
		return backWay;
	}

	public void setBackWay(Integer backWay) {
		this.backWay = backWay;
	}

	public String getBuyContractId() {
		return buyContractId;
	}

	public void setBuyContractId(String buyContractId) {
		this.buyContractId = buyContractId;
	}

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public String getSupLinkmanFax() {
		return supLinkmanFax;
	}

	public void setSupLinkmanFax(String supLinkmanFax) {
		this.supLinkmanFax = supLinkmanFax;
	}

	public String getSupInvoiceBankName() {
		return supInvoiceBankName;
	}

	public void setSupInvoiceBankName(String supInvoiceBankName) {
		this.supInvoiceBankName = supInvoiceBankName;
	}

	public String getSupInvoiceBankAccount() {
		return supInvoiceBankAccount;
	}

	public void setSupInvoiceBankAccount(String supInvoiceBankAccount) {
		this.supInvoiceBankAccount = supInvoiceBankAccount;
	}

	public String getProdSuperintendentTel() {
		return prodSuperintendentTel;
	}

	public void setProdSuperintendentTel(String prodSuperintendentTel) {
		this.prodSuperintendentTel = prodSuperintendentTel;
	}

	public String getProdDeptFax() {
		return prodDeptFax;
	}

	public void setProdDeptFax(String prodDeptFax) {
		this.prodDeptFax = prodDeptFax;
	}

	public String getComRemitBankName() {
		return comRemitBankName;
	}

	public void setComRemitBankName(String comRemitBankName) {
		this.comRemitBankName = comRemitBankName;
	}

	public String getCompRemitBankAccount() {
		return compRemitBankAccount;
	}

	public void setCompRemitBankAccount(String compRemitBankAccount) {
		this.compRemitBankAccount = compRemitBankAccount;
	}

	public String getSupLinkmanTel() {
		return supLinkmanTel;
	}

	public void setSupLinkmanTel(String supLinkmanTel) {
		this.supLinkmanTel = supLinkmanTel;
	}

	public String getBuyContractName() {
		return buyContractName;
	}

	public void setBuyContractName(String buyContractName) {
		if (buyContractName != null && !"".equals(buyContractName)) {
			this.buyContractName = buyContractName + "采购";
			this.setProdTypeName(buyContractName);
		}
	}

	public String getSupLinkmanName() {
		return supLinkmanName;
	}

	public void setSupLinkmanName(String supLinkmanName) {
		this.supLinkmanName = supLinkmanName;
	}

	public String getProdSuperintendentName() {
		return prodSuperintendentName;
	}

	public void setProdSuperintendentName(String prodSupperintendentName) {
		this.prodSuperintendentName = prodSupperintendentName;
	}

}
