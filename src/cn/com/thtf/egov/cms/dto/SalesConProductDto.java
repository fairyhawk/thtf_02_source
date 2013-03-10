/**
 * ClassName  ProdCreditLimit
 *
 * History
 * Create User: ChenHuajiang
 * Create Date: 2010-4-8
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

import java.math.BigDecimal;

/**
 * 新建销售合同产品信息的DTO
 * 
 * @author shensi
 */

public class SalesConProductDto {

	private String proTypeId;// 产品分类ID

	private String proTypeName;// 产品分类名称

	private String proSerieId;// 产品系列ID

	private String proSerieName;// 产品系列名称

	private String productCode;// 产品编码

	private String productName;// 产品名称

	private String productType;// 产品规格型号

	private BigDecimal limitPrice;// 限价

	private String productUnit;// 单位

	private String productDeptName;// 产品部门名称

	private String salesCount;// 销售数

	private String salesPrice;// 销售单价

	private String salesMoney;// 销售金额

	private String salesCanUse;// 销售可用数

	private String salesTotalMoney;// 合同总的销售金额

	private String sendGoodsMoney;// 发货金额

	private String readyGoodsMoney;// 备货金额

	private String backSellMoney;// 退货合同金额

	private String backGoodsMoney;// 退货金额

	private String appointMoney;// 指定金额

	private String invoiceMoney;// 开票金额
	private String userId;// 用户ID

	private String proDeptId;// 产品部门ID

	private String proDeptName;// 产品部门

	private String proDeptAccount;// 产品部门账号

	private String proDeptFax;// 产品部门传真

	private String proId;// 产品ID

	private String detailBuyPrice;// 预计采购价

	private String grossRate; // 销售毛利率

	private String grossRateRound; // 销售毛利率四舍五入 保留两位小数后的值

	public String getGrossRateRound() {
		return grossRateRound;
	}

	public void setGrossRateRound(String grossRateRound) {
		this.grossRateRound = grossRateRound;
	}

	public String getGrossRate() {
		return grossRate;
	}

	public void setGrossRate(String grossRate) {
		this.grossRate = grossRate;
	}

	public String getDetailBuyPrice() {
		return detailBuyPrice;
	}

	public void setDetailBuyPrice(String detailBuyPrice) {
		this.detailBuyPrice = detailBuyPrice;
	}

	public String getSendGoodsMoney() {
		return sendGoodsMoney;
	}

	public void setSendGoodsMoney(String sendGoodsMoney) {
		this.sendGoodsMoney = sendGoodsMoney;
	}

	public String getReadyGoodsMoney() {
		return readyGoodsMoney;
	}

	public void setReadyGoodsMoney(String readyGoodsMoney) {
		this.readyGoodsMoney = readyGoodsMoney;
	}

	public String getBackSellMoney() {
		return backSellMoney;
	}

	public void setBackSellMoney(String backSellMoney) {
		this.backSellMoney = backSellMoney;
	}

	public String getBackGoodsMoney() {
		return backGoodsMoney;
	}

	public void setBackGoodsMoney(String backGoodsMoney) {
		this.backGoodsMoney = backGoodsMoney;
	}

	public String getAppointMoney() {
		return appointMoney;
	}

	public void setAppointMoney(String appointMoney) {
		this.appointMoney = appointMoney;
	}

	public String getInvoiceMoney() {
		return invoiceMoney;
	}

	public void setInvoiceMoney(String invoiceMoney) {
		this.invoiceMoney = invoiceMoney;
	}

	public String getSalesCanUse() {
		return salesCanUse;
	}

	public void setSalesCanUse(String salesCanUse) {
		this.salesCanUse = salesCanUse;
	}

	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}

	public String getProDeptAccount() {
		return proDeptAccount;
	}

	public void setProDeptAccount(String proDeptAccount) {
		this.proDeptAccount = proDeptAccount;
	}

	public String getProDeptFax() {
		return proDeptFax;
	}

	public void setProDeptFax(String proDeptFax) {
		this.proDeptFax = proDeptFax;
	}

	public String getProDeptId() {
		return proDeptId;
	}

	public void setProDeptId(String proDeptId) {
		this.proDeptId = proDeptId;
	}

	public String getProDeptName() {
		return proDeptName;
	}

	public void setProDeptName(String proDeptName) {
		this.proDeptName = proDeptName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProTypeId() {
		return proTypeId;
	}

	public void setProTypeId(String proTypeId) {
		this.proTypeId = proTypeId;
	}

	public String getProTypeName() {
		return proTypeName;
	}

	public void setProTypeName(String proTypeName) {
		this.proTypeName = proTypeName;
	}

	public String getProSerieId() {
		return proSerieId;
	}

	public void setProSerieId(String proSerieId) {
		this.proSerieId = proSerieId;
	}

	public String getProSerieName() {
		return proSerieName;
	}

	public void setProSerieName(String proSerieName) {
		this.proSerieName = proSerieName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductUnit() {
		return productUnit;
	}

	public void setProductUnit(String productUnit) {
		this.productUnit = productUnit;
	}

	public String getProductDeptName() {
		return productDeptName;
	}

	public void setProductDeptName(String productDeptName) {
		this.productDeptName = productDeptName;
	}

	public String getSalesCount() {
		return salesCount;
	}

	public void setSalesCount(String salesCount) {
		this.salesCount = salesCount;
	}

	public String getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(String salesPrice) {
		this.salesPrice = salesPrice;
	}

	public String getSalesMoney() {
		return salesMoney;
	}

	public void setSalesMoney(String salesMoney) {
		this.salesMoney = salesMoney;
	}

	public String getSalesTotalMoney() {
		return salesTotalMoney;
	}

	public void setSalesTotalMoney(String salesTotalMoney) {
		this.salesTotalMoney = salesTotalMoney;
	}

	public BigDecimal getLimitPrice() {
		return limitPrice;
	}

	public void setLimitPrice(BigDecimal limitPrice) {
		this.limitPrice = limitPrice;
	}
}
