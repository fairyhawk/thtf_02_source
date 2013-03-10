package cn.com.thtf.egov.cms.dto;

import java.util.List;

public class IncomeStockListDto {
	/** 单据类型 */
private String billType;
/** 单据类型数组 */
private String billTypes[];
	/** 入库单号   */
private String incomeStockId;
/** 库房名称 */
private String storeroom;
/** 库房id */
private String storeroomId;
/** 产品分类名称   */
private String productTypeName;
/** 产品分类id   */
private String productTypeId;
/** 申请日期 */
private String date;
/** 入库日期  */
private String incomeDate;
/** 人员名称 */
private String userName;
/** 入库单状态 */
private String status;
/** 供货商名称 */
private String supplierName;
/** 入库终止日期  */
private String endIncomeDate;

private String sendGoodsId;//

private String type;//
/** 用户id  */
private String userId;
/** 列表的产品分类、区域 */
private List<IncomeOfListDto> stockList;
public String getBillType() {
	return billType;
}
public void setBillType(String billType) {
	this.billType = billType;
}
public String getIncomeStockId() {
	
	return incomeStockId!=null?incomeStockId.trim():incomeStockId;
}
public void setIncomeStockId(String incomeStockId) {
	this.incomeStockId = incomeStockId;
}
public String getStoreroom() {
	return storeroom;
}
public void setStoreroom(String storeroom) {
	this.storeroom = storeroom;
}
public String getProductTypeName() {
	return productTypeName;
}
public void setProductTypeName(String productTypeName) {
	this.productTypeName = productTypeName;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public String getIncomeDate() {
	return incomeDate;
}
public void setIncomeDate(String incomeDate) {
	this.incomeDate = incomeDate;
}
public String getUserName() {
	return userName!=null?userName.trim():userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getSupplierName() {
	return supplierName!=null?supplierName.trim():supplierName;
}
public void setSupplierName(String supplierName) {
	this.supplierName = supplierName;
}
public String getEndIncomeDate() {
	return endIncomeDate;
}
public void setEndIncomeDate(String endIncomeDate) {
	this.endIncomeDate = endIncomeDate;
}
public String getStoreroomId() {
	return storeroomId;
}
public void setStoreroomId(String storeroomId) {
	this.storeroomId = storeroomId;
}
public String getProductTypeId() {
	return productTypeId;
}
public void setProductTypeId(String productTypeId) {
	this.productTypeId = productTypeId;
}
public String[] getBillTypes() {
	return billTypes;
}
public void setBillTypes(String[] billTypes) {
	this.billTypes = billTypes;
}
public String getSendGoodsId() {
	return sendGoodsId;
}
public void setSendGoodsId(String sendGoodsId) {
	this.sendGoodsId = sendGoodsId;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public List<IncomeOfListDto> getStockList() {
	return stockList;
}
public void setStockList(List<IncomeOfListDto> stockList) {
	this.stockList = stockList;
}
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}


}
