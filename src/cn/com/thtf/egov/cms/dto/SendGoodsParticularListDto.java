package cn.com.thtf.egov.cms.dto;

import java.math.BigDecimal;

public class SendGoodsParticularListDto {
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
	private String invoiceMoney;//13申请开票金额
	private String returnMoney;//14退货金额
	private String productTypeId;//产品分类Id
	private String productId;//产品Id
	private String customerId;//客户编号
	private String makeInvoiceId;//开票id、
	private String makeInvoiceCount;//未开的发票 申请开票数
	private String appointCount;//申请发票数
	private String makeInvoiceMoney;//开票金额
	
	private String requestDate;//要求发货日期
	private BigDecimal arterm;//发货单帐期
	
	private String getDate;//到账日期
	
	private String beginTime;//到账起始日期
	private String endTime;//到账终止日期
	
	private int sellBackGoodsCount;
	private int flag;
	private String userId;//销售经理用户名
public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
public String getId() {
	return id!=null?id.trim():id;
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
	return productContractNum!=null?productContractNum.trim():productContractNum;
}
public void setProductContractNum(String productContractNum) {
	this.productContractNum = productContractNum;
}
public String getCompanyContractNum() {
	return companyContractNum!=null?companyContractNum.trim():companyContractNum;
}
public void setCompanyContractNum(String companyContractNum) {
	this.companyContractNum = companyContractNum;
}
public String getProductCode() {
	return productCode!=null?productCode.trim():productCode;
}
public void setProductCode(String productCode) {
	this.productCode = productCode;
}
public String getProductName() {
	return productName!=null?productName.trim():productName;
}
public void setProductName(String productName) {
	this.productName = productName;
}
public String getSpecModel() {
	return specModel!=null?specModel.trim():specModel;
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
public String getAppointCount() {
	return appointCount;
}
public void setAppointCount(String appointCount) {
	this.appointCount = appointCount;
}
public String getMakeInvoiceMoney() {
	return makeInvoiceMoney;
}
public void setMakeInvoiceMoney(String makeInvoiceMoney) {
	this.makeInvoiceMoney = makeInvoiceMoney;
}
public String getRequestDate() {
	return requestDate;
}
public void setRequestDate(String requestDate) {
	this.requestDate = requestDate;
}
public BigDecimal getArterm() {
	return arterm;
}
public void setArterm(BigDecimal arterm) {
	this.arterm = arterm;
}
public String getGetDate() {
	//getDate=Util.dateAnd(this.requestDate,this.arterm.intValue());
	return getDate;
}
public void setGetDate(String getDate) {
	this.getDate = getDate;
}
public String getBeginTime() {
	return beginTime;
}
public void setBeginTime(String beginTime) {
	this.beginTime = beginTime;
}
public String getEndTime() {
	return endTime;
}
public void setEndTime(String endTime) {
	this.endTime = endTime;
}
public int getSellBackGoodsCount() {
	return sellBackGoodsCount;
}
public void setSellBackGoodsCount(int sellBackGoodsCount) {
	this.sellBackGoodsCount = sellBackGoodsCount;
}
public int getFlag() {
	return flag;
}
public void setFlag(int flag) {
	this.flag = flag;
}
}
