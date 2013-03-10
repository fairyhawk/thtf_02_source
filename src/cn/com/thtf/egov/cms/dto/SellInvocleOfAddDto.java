package cn.com.thtf.egov.cms.dto;

public class SellInvocleOfAddDto {
private String id;//
private String date;//添加日期
private String makeInvoiceId;//开票id
private String number;//发票号
private String money;//发票金额
private String invoiceType;
private String productTypeId;
private String customerId;
private String customerName;
private String status;

private String confirmId;//退票确认人登录名
private String confirmName;//退票确认人用户名

private int userAreaId;//
private String remark;
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public String getMakeInvoiceId() {
	return makeInvoiceId;
}
public void setMakeInvoiceId(String makeInvoiceId) {
	this.makeInvoiceId = makeInvoiceId;
}
public String getNumber() {
	return number;
}
public void setNumber(String number) {
	this.number = number;
}
public String getMoney() {
	return money;
}
public void setMoney(String money) {
	this.money = money;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getInvoiceType() {
	return invoiceType;
}
public void setInvoiceType(String invoiceType) {
	this.invoiceType = invoiceType;
}
public String getProductTypeId() {
	return productTypeId;
}
public void setProductTypeId(String productTypeId) {
	this.productTypeId = productTypeId;
}
public String getCustomerId() {
	return customerId;
}
public void setCustomerId(String customerId) {
	this.customerId = customerId;
}
public String getCustomerName() {
	return customerName;
}
public void setCustomerName(String customerName) {
	this.customerName = customerName;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getConfirmId() {
	return confirmId;
}
public void setConfirmId(String confirmId) {
	this.confirmId = confirmId;
}
public String getConfirmName() {
	return confirmName;
}
public void setConfirmName(String confirmName) {
	this.confirmName = confirmName;
}
public int getUserAreaId() {
	return userAreaId;
}
public void setUserAreaId(int userAreaId) {
	this.userAreaId = userAreaId;
}
public String getRemark() {
	return remark;
}
public void setRemark(String remark) {
	this.remark = remark;
}

}
