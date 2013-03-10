package cn.com.thtf.egov.cms.dto;

import java.math.BigDecimal;

public class SellAndCustInfoDto {

	//销售合同
	private String sellid;

	private String productContractCode;

	private String companyContarctCode;

	private String selldate;

	private Integer sellstatus;

	private Integer stampType;

	private Integer templateType;

	private String place;

	private Integer protectLimit;

	private Integer checkLimit;

	private String breach;

	private String lawsuit;

	private String clause;

	private String file;

	private Integer customerId;

	private String customerName;

	private Integer customerLinkmanId;

	private Integer productTypeId;

	private Integer paymentWay;

	private Integer batchWay;

	private BigDecimal cashMoney;

	private BigDecimal batchMaxMoney;

	private Integer customerCreditId;

	private Integer creditTypeId;

	private String projectName;

	private Integer arterm;

	private BigDecimal climit;

	private BigDecimal free;

//	private BigDecimal lockCredit;

	private Integer customerAddressId;

	private String requestSendDate;

	private String userId;

	private String userName;

	private Integer userAreaId;

	private String text;

	private BigDecimal money;

	private BigDecimal interestRate;

	private String proMajId;

	private String proMajName;

	private String proMajDate;

	private String proMajIdea;

	private String proMajText;

	private String legalId;

	private String legalName;

	private String legalDate;

	private Integer legalIdea;

	private String legalText;

	private String sellMajId;

	private String sellMajName;

	private String sellMajDate;

	private String sellMajIdea;

	private String sellMajText;

	private String opeMajId;

	private String opeMajName;

	private String opeMajDate;

	private Integer opeMajIdea;

	private String opeMajText;
	
	private String datetime;
	
	//客户
	private Integer custid;
	
	private Integer	customer_id;

	private String custname;

	private String address;

	private String postcode;

	public String getSellid() {
		return sellid;
	}

	public void setSellid(String sellid) {
		this.sellid = sellid;
	}

	public String getProductContractCode() {
		return productContractCode;
	}

	public void setProductContractCode(String productContractCode) {
		this.productContractCode = productContractCode;
	}

	public String getCompanyContarctCode() {
		return companyContarctCode;
	}

	public void setCompanyContarctCode(String companyContarctCode) {
		this.companyContarctCode = companyContarctCode;
	}

	public String getSelldate() {
		return selldate;
	}

	public void setSelldate(String selldate) {
		this.selldate = selldate;
	}

	public Integer getSellstatus() {
		return sellstatus;
	}

	public void setSellstatus(Integer sellstatus) {
		this.sellstatus = sellstatus;
	}

	public Integer getStampType() {
		return stampType;
	}

	public void setStampType(Integer stampType) {
		this.stampType = stampType;
	}

	public Integer getTemplateType() {
		return templateType;
	}

	public void setTemplateType(Integer templateType) {
		this.templateType = templateType;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Integer getProtectLimit() {
		return protectLimit;
	}

	public void setProtectLimit(Integer protectLimit) {
		this.protectLimit = protectLimit;
	}

	public Integer getCheckLimit() {
		return checkLimit;
	}

	public void setCheckLimit(Integer checkLimit) {
		this.checkLimit = checkLimit;
	}

	public String getBreach() {
		return breach;
	}

	public void setBreach(String breach) {
		this.breach = breach;
	}

	public String getLawsuit() {
		return lawsuit;
	}

	public void setLawsuit(String lawsuit) {
		this.lawsuit = lawsuit;
	}

	public String getClause() {
		return clause;
	}

	public void setClause(String clause) {
		this.clause = clause;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Integer getCustomerLinkmanId() {
		return customerLinkmanId;
	}

	public void setCustomerLinkmanId(Integer customerLinkmanId) {
		this.customerLinkmanId = customerLinkmanId;
	}

	public Integer getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(Integer productTypeId) {
		this.productTypeId = productTypeId;
	}

	public Integer getPaymentWay() {
		return paymentWay;
	}

	public void setPaymentWay(Integer paymentWay) {
		this.paymentWay = paymentWay;
	}

	public Integer getBatchWay() {
		return batchWay;
	}

	public void setBatchWay(Integer batchWay) {
		this.batchWay = batchWay;
	}

	public BigDecimal getCashMoney() {
		return cashMoney;
	}

	public void setCashMoney(BigDecimal cashMoney) {
		this.cashMoney = cashMoney;
	}

	public BigDecimal getBatchMaxMoney() {
		return batchMaxMoney;
	}

	public void setBatchMaxMoney(BigDecimal batchMaxMoney) {
		this.batchMaxMoney = batchMaxMoney;
	}

	public Integer getCustomerCreditId() {
		return customerCreditId;
	}

	public void setCustomerCreditId(Integer customerCreditId) {
		this.customerCreditId = customerCreditId;
	}

	public Integer getCreditTypeId() {
		return creditTypeId;
	}

	public void setCreditTypeId(Integer creditTypeId) {
		this.creditTypeId = creditTypeId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Integer getArterm() {
		return arterm;
	}

	public void setArterm(Integer arterm) {
		this.arterm = arterm;
	}

	public BigDecimal getClimit() {
		return climit;
	}

	public void setClimit(BigDecimal climit) {
		this.climit = climit;
	}

	public BigDecimal getFree() {
		return free;
	}

	public void setFree(BigDecimal free) {
		this.free = free;
	}

	public Integer getCustomerAddressId() {
		return customerAddressId;
	}

	public void setCustomerAddressId(Integer customerAddressId) {
		this.customerAddressId = customerAddressId;
	}

	public String getRequestSendDate() {
		return requestSendDate;
	}

	public void setRequestSendDate(String requestSendDate) {
		this.requestSendDate = requestSendDate;
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

	public Integer getUserAreaId() {
		return userAreaId;
	}

	public void setUserAreaId(Integer userAreaId) {
		this.userAreaId = userAreaId;
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

	public BigDecimal getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
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

	public String getLegalId() {
		return legalId;
	}

	public void setLegalId(String legalId) {
		this.legalId = legalId;
	}

	public String getLegalName() {
		return legalName;
	}

	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}

	public String getLegalDate() {
		return legalDate;
	}

	public void setLegalDate(String legalDate) {
		this.legalDate = legalDate;
	}

	public Integer getLegalIdea() {
		return legalIdea;
	}

	public void setLegalIdea(Integer legalIdea) {
		this.legalIdea = legalIdea;
	}

	public String getLegalText() {
		return legalText;
	}

	public void setLegalText(String legalText) {
		this.legalText = legalText;
	}

	public String getSellMajId() {
		return sellMajId;
	}

	public void setSellMajId(String sellMajId) {
		this.sellMajId = sellMajId;
	}

	public String getSellMajName() {
		return sellMajName;
	}

	public void setSellMajName(String sellMajName) {
		this.sellMajName = sellMajName;
	}

	public String getSellMajDate() {
		return sellMajDate;
	}

	public void setSellMajDate(String sellMajDate) {
		this.sellMajDate = sellMajDate;
	}

	public String getSellMajIdea() {
		return sellMajIdea;
	}

	public void setSellMajIdea(String sellMajIdea) {
		this.sellMajIdea = sellMajIdea;
	}

	public String getSellMajText() {
		return sellMajText;
	}

	public void setSellMajText(String sellMajText) {
		this.sellMajText = sellMajText;
	}

	public String getOpeMajId() {
		return opeMajId;
	}

	public void setOpeMajId(String opeMajId) {
		this.opeMajId = opeMajId;
	}

	public String getOpeMajName() {
		return opeMajName;
	}

	public void setOpeMajName(String opeMajName) {
		this.opeMajName = opeMajName;
	}

	public String getOpeMajDate() {
		return opeMajDate;
	}

	public void setOpeMajDate(String opeMajDate) {
		this.opeMajDate = opeMajDate;
	}

	public Integer getOpeMajIdea() {
		return opeMajIdea;
	}

	public void setOpeMajIdea(Integer opeMajIdea) {
		this.opeMajIdea = opeMajIdea;
	}

	public String getOpeMajText() {
		return opeMajText;
	}

	public void setOpeMajText(String opeMajText) {
		this.opeMajText = opeMajText;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public Integer getCustid() {
		return custid;
	}

	public void setCustid(Integer custid) {
		this.custid = custid;
	}

	public Integer getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Integer customerId) {
		customer_id = customerId;
	}

	public String getCustname() {
		return custname;
	}

	public void setCustname(String custname) {
		this.custname = custname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	private String linkman;

	private String tel;

	private String mobile;
	
}
