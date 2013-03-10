package cn.com.thtf.egov.cms.form;

import org.apache.struts.action.ActionForm;

import cn.com.thtf.egov.cms.dto.SendGoodsParticularListDto;

@SuppressWarnings("serial")
public class SendGoodsParticularListForm extends ActionForm{
	private String id;//1发货单号 
	private String productTypeName;//2产品分类名称
	private String productContractNum;//3产品合同号
	private String companyContractNum;//4公司合同号
	private String productCode;//5产品编码
	private String productName;//5.1产品名称
	private String specModel;//6规格型号
	private String unit;//7单位
	private String sellPrice;//8销售单价
	private String sendCount;//9发货数
	private String stockCount;//10备货数
	private String money;//11指定金额
	private String invoiceCount;//12开票数
	private String invoiceMoney;//13开票金额
	private String returnMoney;//14退货金额
	private String productTypeId;//产品分类Id
	private String productId;//产品Id
	private String customerId;//客户编号
	private String makeInvoiceId;//开票id、
	private String makeInvoiceCount;//未开的发票
	
	private SendGoodsParticularListDto sendGoodsParticularListDto=new SendGoodsParticularListDto();
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getProductTypeName() {
	return productTypeName;
}
public void setProductTypeName(String productTypeName) {
	this.productTypeName = productTypeName;
}
public String getProductContractNum() {
	return productContractNum;
}
public void setProductContractNum(String productContractNum) {
	this.productContractNum = productContractNum;
}
public String getCompanyContractNum() {
	return companyContractNum;
}
public void setCompanyContractNum(String companyContractNum) {
	this.companyContractNum = companyContractNum;
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
public String getSpecModel() {
	return specModel;
}
public void setSpecModel(String specModel) {
	this.specModel = specModel;
}
public String getUnit() {
	return unit;
}
public void setUnit(String unit) {
	this.unit = unit;
}
public String getSellPrice() {
	return sellPrice;
}
public void setSellPrice(String sellPrice) {
	this.sellPrice = sellPrice;
}
public String getSendCount() {
	return sendCount;
}
public void setSendCount(String sendCount) {
	this.sendCount = sendCount;
}
public String getStockCount() {
	return stockCount;
}
public void setStockCount(String stockCount) {
	this.stockCount = stockCount;
}
public String getInvoiceCount() {
	return invoiceCount;
}
public void setInvoiceCount(String invoiceCount) {
	this.invoiceCount = invoiceCount;
}
public String getInvoiceMoney() {
	return invoiceMoney;
}
public void setInvoiceMoney(String invoiceMoney) {
	this.invoiceMoney = invoiceMoney;
}
public String getReturnMoney() {
	return returnMoney;
}
public void setReturnMoney(String returnMoney) {
	this.returnMoney = returnMoney;
}
public String getCustomerId() {
	return customerId;
}
public void setCustomerId(String customerId) {
	this.customerId = customerId;
}
public String getMoney() {
	return money;
}
public void setMoney(String money) {
	this.money = money;
}
public String getProductTypeId() {
	return productTypeId;
}
public void setProductTypeId(String productTypeId) {
	this.productTypeId = productTypeId;
}
public String getProductId() {
	return productId;
}
public void setProductId(String productId) {
	this.productId = productId;
}
public String getMakeInvoiceCount() {
	return makeInvoiceCount;
}
public void setMakeInvoiceCount(String makeInvoiceCount) {
	this.makeInvoiceCount = makeInvoiceCount;
}
public String getMakeInvoiceId() {
	return makeInvoiceId;
}
public void setMakeInvoiceId(String makeInvoiceId) {
	this.makeInvoiceId = makeInvoiceId;
}
public SendGoodsParticularListDto getSendGoodsParticularListDto() {
	return sendGoodsParticularListDto;
}
public void setSendGoodsParticularListDto(
		SendGoodsParticularListDto sendGoodsParticularListDto) {
	this.sendGoodsParticularListDto = sendGoodsParticularListDto;
}
}
