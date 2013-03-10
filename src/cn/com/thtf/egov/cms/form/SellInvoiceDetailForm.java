package cn.com.thtf.egov.cms.form;

import org.apache.struts.action.ActionForm;

@SuppressWarnings("serial")
public class SellInvoiceDetailForm extends ActionForm{
private String id;
private String sellInvoiceId;//sell_invoice_id
private String sendGoodsId;
private String productId;
private String count;
private String money;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getSellInvoiceId() {
	return sellInvoiceId;
}
public void setSellInvoiceId(String sellInvoiceId) {
	this.sellInvoiceId = sellInvoiceId;
}
public String getSendGoodsId() {
	return sendGoodsId;
}
public void setSendGoodsId(String sendGoodsId) {
	this.sendGoodsId = sendGoodsId;
}
public String getProductId() {
	return productId;
}
public void setProductId(String productId) {
	this.productId = productId;
}
public String getCount() {
	return count;
}
public void setCount(String count) {
	this.count = count;
}
public String getMoney() {
	return money;
}
public void setMoney(String money) {
	this.money = money;
}
}
