/**
 * ClassName  AddInStockDto
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-5-27
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

/**
 * AddInStockDto
 * 
 * @author Lubo
 */

public class AddInStockDto {

	/** 入库单ID */
	private String id;
	/** 采购合同ID */
	private String buyContractId;
	/** 供应商ID */
	private String supplierId;
	/** 供应商姓名 */
	private String supplierName;
	/** 产品分类ID */
	private Integer productTypeId;

	/** 收货明细表ID */
	private String receiveId;
	/** 收货地址类型 */
	private String companyType;
	/** 收获地址ID */
	private String addressId;
	/** 库房ID */
	private String stockroomId;
	/** 申请日期 */
	private String date;

	/** 用户ID */
	private String userId;
	/** 用户姓名 */
	private String userName;
	/** 入库金额 */
	private String money;
	/** 入库状态 */
	private String status;
	/** 产品总监ID */
	private String proMajId;

	/** 库房管理员ID */
	private String stkAdmId;
	/** 日期 */
	private String datetime;

	/** 产品ID */
	private String[] productIdArr;
	/** 产品数量 */
	private String[] productCountArr;
	/** 采购单价 */
	private String[] productPriceArr;
	/** 产品ID */
	private String productId;
	/** 产品数量 */
	private String productCount;
	/** 采购单价 */
	private String productPrice;

	/** 提交类型 1保存 2提交 */
	private String submitType;
	/** 页面类型 修改:true */
	private boolean pageType;

	/** 特殊说明 */
	private String text;

	/** 厂家到货日期 */
	private String factorySendDate;
	/** 帐期 */
	private String sendPaymentTime;

	public String getSendPaymentTime() {
		return sendPaymentTime;
	}

	public void setSendPaymentTime(String sendPaymentTime) {
		this.sendPaymentTime = sendPaymentTime;
	}

	/**
	 * @return the productPriceArr
	 */
	public String[] getProductPriceArr() {
		return productPriceArr;
	}

	/**
	 * @param productPriceArr
	 *            the productPriceArr to set
	 */
	public void setProductPriceArr(String[] productPriceArr) {
		this.productPriceArr = productPriceArr;
	}

	/**
	 * @return the productId
	 */
	public String getProductId() {
		return productId;
	}

	/**
	 * @param productId
	 *            the productId to set
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}

	/**
	 * @return the productCount
	 */
	public String getProductCount() {
		return productCount;
	}

	/**
	 * @param productCount
	 *            the productCount to set
	 */
	public void setProductCount(String productCount) {
		this.productCount = productCount;
	}

	/**
	 * @return the productPrice
	 */
	public String getProductPrice() {
		return productPrice;
	}

	/**
	 * @param productPrice
	 *            the productPrice to set
	 */
	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	/**
	 * @return the supplierId
	 */
	public String getSupplierId() {
		return supplierId;
	}

	/**
	 * @param supplierId
	 *            the supplierId to set
	 */
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	/**
	 * @return the supplierName
	 */
	public String getSupplierName() {
		return supplierName;
	}

	/**
	 * @param supplierName
	 *            the supplierName to set
	 */
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	/**
	 * @return the productTypeId
	 */
	public Integer getProductTypeId() {
		return productTypeId;
	}

	/**
	 * @param productTypeId
	 *            the productTypeId to set
	 */
	public void setProductTypeId(Integer productTypeId) {
		this.productTypeId = productTypeId;
	}

	/**
	 * @return the stockroomId
	 */
	public String getStockroomId() {
		return stockroomId;
	}

	/**
	 * @param stockroomId
	 *            the stockroomId to set
	 */
	public void setStockroomId(String stockroomId) {
		this.stockroomId = stockroomId;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the money
	 */
	public String getMoney() {
		return money;
	}

	/**
	 * @param money
	 *            the money to set
	 */
	public void setMoney(String money) {
		this.money = money;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the proMajId
	 */
	public String getProMajId() {
		return proMajId;
	}

	/**
	 * @param proMajId
	 *            the proMajId to set
	 */
	public void setProMajId(String proMajId) {
		this.proMajId = proMajId;
	}

	/**
	 * @return the stkAdmId
	 */
	public String getStkAdmId() {
		return stkAdmId;
	}

	/**
	 * @param stkAdmId
	 *            the stkAdmId to set
	 */
	public void setStkAdmId(String stkAdmId) {
		this.stkAdmId = stkAdmId;
	}

	/**
	 * @return the datetime
	 */
	public String getDatetime() {
		return datetime;
	}

	/**
	 * @param datetime
	 *            the datetime to set
	 */
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the pageType
	 */
	public boolean isPageType() {
		return pageType;
	}

	/**
	 * @param pageType
	 *            the pageType to set
	 */
	public void setPageType(boolean pageType) {
		this.pageType = pageType;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the submitType
	 */
	public String getSubmitType() {
		return submitType;
	}

	/**
	 * @param submitType
	 *            the submitType to set
	 */
	public void setSubmitType(String submitType) {
		this.submitType = submitType;
	}

	/**
	 * @return the productIdArr
	 */
	public String[] getProductIdArr() {
		return productIdArr;
	}

	/**
	 * @param productIdArr
	 *            the productIdArr to set
	 */
	public void setProductIdArr(String[] productIdArr) {
		this.productIdArr = productIdArr;
	}

	/**
	 * @return the productCountArr
	 */
	public String[] getProductCountArr() {
		return productCountArr;
	}

	/**
	 * @param productCountArr
	 *            the productCountArr to set
	 */
	public void setProductCountArr(String[] productCountArr) {
		this.productCountArr = productCountArr;
	}

	/**
	 * @return the companyType
	 */
	public String getCompanyType() {
		return companyType;
	}

	/**
	 * @param companyType
	 *            the companyType to set
	 */
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	/**
	 * @return the addressId
	 */
	public String getAddressId() {
		return addressId;
	}

	/**
	 * @param addressId
	 *            the addressId to set
	 */
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	/**
	 * @return the receiveId
	 */
	public String getReceiveId() {
		return receiveId;
	}

	/**
	 * @param receiveId
	 *            the receiveId to set
	 */
	public void setReceiveId(String receiveId) {
		this.receiveId = receiveId;
	}

	/**
	 * @return the buyContractId
	 */
	public String getBuyContractId() {
		return buyContractId;
	}

	/**
	 * @param buyContractId
	 *            the buyContractId to set
	 */
	public void setBuyContractId(String buyContractId) {
		this.buyContractId = buyContractId;
	}

	public String getFactorySendDate() {
		return factorySendDate;
	}

	public void setFactorySendDate(String factorySendDate) {
		this.factorySendDate = factorySendDate;
	}
}
