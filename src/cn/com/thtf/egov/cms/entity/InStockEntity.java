package cn.com.thtf.egov.cms.entity;

import java.math.BigDecimal;

import cn.com.thtf.egov.cms.application.BaseEntity;

public class InStockEntity extends BaseEntity {

	private static final long serialVersionUID = -7716789375983848957L;

	private String id;

	private String buyContractId;

	private Integer supplierId;

	private String supplierName;

	private Integer productTypeId;

	private Integer type;

	private Integer receiveGoodsDetailId;

	private Integer stockroomId;

	private String date;

	private String userId;

	private String userName;

	private String text;

	private BigDecimal money;

	private Integer status;

	private String proMajId;

	private String proMajName;

	private String proMajDate;

	private String proMajIdea;

	private String proMajText;

	private String stkAdmId;

	private String stkAdmName;

	private String stkAdmDate;

	private String stkAdmIdea;

	private String stkAdmText;

	private String dateTime;

	/** 厂家到货日期 */
	private String factorySendDate;
	/** 要求到账日期 */
	private String requestAccountDate;

	public String getFactorySendDate() {
		return factorySendDate;
	}

	public void setFactorySendDate(String factorySendDate) {
		this.factorySendDate = factorySendDate;
	}

	public String getRequestAccountDate() {
		return requestAccountDate;
	}

	public void setRequestAccountDate(String requestAccountDate) {
		this.requestAccountDate = requestAccountDate;
	}

	public String getDateTime() {
		return this.dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBuyContractId() {
		return buyContractId;
	}

	public void setBuyContractId(String buyContractId) {
		this.buyContractId = buyContractId;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public Integer getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(Integer productTypeId) {
		this.productTypeId = productTypeId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getReceiveGoodsDetailId() {
		return receiveGoodsDetailId;
	}

	public void setReceiveGoodsDetailId(Integer receiveGoodsDetailId) {
		this.receiveGoodsDetailId = receiveGoodsDetailId;
	}

	public Integer getStockroomId() {
		return stockroomId;
	}

	public void setStockroomId(Integer stockroomId) {
		this.stockroomId = stockroomId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getProMajId() {
		return proMajId;
	}

	public void setProMajId(String proMajId) {
		this.proMajId = proMajId;
	}

	public String getProMajName() {
		return proMajName;
	}

	public void setProMajName(String proMajName) {
		this.proMajName = proMajName;
	}

	public String getProMajDate() {
		return proMajDate;
	}

	public void setProMajDate(String proMajDate) {
		this.proMajDate = proMajDate;
	}

	public String getProMajIdea() {
		return proMajIdea;
	}

	public void setProMajIdea(String proMajIdea) {
		this.proMajIdea = proMajIdea;
	}

	public String getProMajText() {
		return proMajText;
	}

	public void setProMajText(String proMajText) {
		this.proMajText = proMajText;
	}

	public String getStkAdmId() {
		return stkAdmId;
	}

	public void setStkAdmId(String stkAdmId) {
		this.stkAdmId = stkAdmId;
	}

	public String getStkAdmName() {
		return stkAdmName;
	}

	public void setStkAdmName(String stkAdmName) {
		this.stkAdmName = stkAdmName;
	}

	public String getStkAdmDate() {
		return stkAdmDate;
	}

	public void setStkAdmDate(String stkAdmDate) {
		this.stkAdmDate = stkAdmDate;
	}

	public String getStkAdmIdea() {
		return stkAdmIdea;
	}

	public void setStkAdmIdea(String stkAdmIdea) {
		this.stkAdmIdea = stkAdmIdea;
	}

	public String getStkAdmText() {
		return stkAdmText;
	}

	public void setStkAdmText(String stkAdmText) {
		this.stkAdmText = stkAdmText;
	}
}