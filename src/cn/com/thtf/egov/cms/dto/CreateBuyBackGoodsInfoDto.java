/**
 * ClassName  CreateBuyBackGoodsInfo
 *
 * History
 * Create User: liuqg
 * Create Date: 2010-6-12
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

import java.math.BigDecimal;

/**
 * 新建采购返厂显示用
 * 
 * @author liuqg
 */

public class CreateBuyBackGoodsInfoDto {

	private String id;// 入库单号
	private String productTypeName; // 产品分类名称
	private String productTypeId; // 产品分类编码
	private String supplierId; // 供货商编码
	private String supplierName; // 供货商名称
	private String productContractCode; // 供货商名称
	private String companyContractCode; // 公司合同号
	
	private String productId;// 产品编码
	private String code;// 产品编码
	private String productName;// 产品名称
	private String productType;// 规格型号
	private String productUnit;// 单位
	private String price;// 采购单价
	private BigDecimal inStockCount;// 入库数
	private BigDecimal inStockMoney;// 入库金额
	private BigDecimal paymentMoney;// 指定金额
	private BigDecimal receiveMoney;// 收票金额
	private Integer alreadyBackCount; // #已返厂数
	private BigDecimal backMoney;// #返厂金额
	private Integer stockNum;// 库存数

	private Integer backCount; // #返厂数

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the productTypeId
	 */
	public String getProductTypeId() {
		return productTypeId;
	}

	/**
	 * @param productTypeId
	 *            the productTypeId to set
	 */
	public void setProductTypeId(String productTypeId) {
		this.productTypeId = productTypeId;
	}

	/**
	 * @return the inStockCount
	 */
	public BigDecimal getInStockCount() {
		return inStockCount;
	}

	/**
	 * @param inStockCount
	 *            the inStockCount to set
	 */
	public void setInStockCount(BigDecimal inStockCount) {
		this.inStockCount = inStockCount;
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
	 * @return the productTypeName
	 */
	public String getProductTypeName() {
		return productTypeName;
	}

	/**
	 * @param productTypeName
	 *            the productTypeName to set
	 */
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
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
	 * @return the productContractCode
	 */
	public String getProductContractCode() {
		return productContractCode;
	}

	/**
	 * @param productContractCode
	 *            the productContractCode to set
	 */
	public void setProductContractCode(String productContractCode) {
		this.productContractCode = productContractCode;
	}

	/**
	 * @return the companyContractCode
	 */
	public String getCompanyContractCode() {
		return companyContractCode;
	}

	/**
	 * @param companyContractCode
	 *            the companyContractCode to set
	 */
	public void setCompanyContractCode(String companyContractCode) {
		this.companyContractCode = companyContractCode;
	}

	/**
	 * @return the alreadyBackCount
	 */
	public Integer getAlreadyBackCount() {
		return alreadyBackCount;
	}

	/**
	 * @param alreadyBackCount
	 *            the alreadyBackCount to set
	 */
	public void setAlreadyBackCount(Integer alreadyBackCount) {
		this.alreadyBackCount = alreadyBackCount;
	}

	/**
	 * @return the backMoney
	 */
	public BigDecimal getBackMoney() {
		return backMoney;
	}

	/**
	 * @param backMoney
	 *            the backMoney to set
	 */
	public void setBackMoney(BigDecimal backMoney) {
		this.backMoney = backMoney;
	}

	/**
	 * @return the stockNum
	 */
	public Integer getStockNum() {
		return stockNum;
	}

	/**
	 * @param stockNum
	 *            the stockNum to set
	 */
	public void setStockNum(Integer stockNum) {
		this.stockNum = stockNum;
	}

	/**
	 * @return the backCount
	 */
	public Integer getBackCount() {
		return backCount;
	}

	/**
	 * @param backCount
	 *            the backCount to set
	 */
	public void setBackCount(Integer backCount) {
		this.backCount = backCount;
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
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName
	 *            the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the productType
	 */
	public String getProductType() {
		return productType;
	}

	/**
	 * @param productType
	 *            the productType to set
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}

	/**
	 * @return the productUnit
	 */
	public String getProductUnit() {
		return productUnit;
	}

	/**
	 * @param productUnit
	 *            the productUnit to set
	 */
	public void setProductUnit(String productUnit) {
		this.productUnit = productUnit;
	}

	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * @return the inStockMoney
	 */
	public BigDecimal getInStockMoney() {
		return inStockMoney;
	}

	/**
	 * @param inStockMoney
	 *            the inStockMoney to set
	 */
	public void setInStockMoney(BigDecimal inStockMoney) {
		this.inStockMoney = inStockMoney;
	}

	/**
	 * @return the paymentMoney
	 */
	public BigDecimal getPaymentMoney() {
		return paymentMoney;
	}

	/**
	 * @param paymentMoney
	 *            the paymentMoney to set
	 */
	public void setPaymentMoney(BigDecimal paymentMoney) {
		this.paymentMoney = paymentMoney;
	}

	/**
	 * @return the receiveMoney
	 */
	public BigDecimal getReceiveMoney() {
		return receiveMoney;
	}

	/**
	 * @param receiveMoney
	 *            the receiveMoney to set
	 */
	public void setReceiveMoney(BigDecimal receiveMoney) {
		this.receiveMoney = receiveMoney;
	}

}
