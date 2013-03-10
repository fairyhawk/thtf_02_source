package cn.com.thtf.egov.cms.dto;

public class SalesBackContractOfShowDto {

private String salesBackContractId;// 销售退货合同id

private String file;// file

private String salesContractId;// 销售合同id

private String date;// 签订日期

private String backProductContractCode;// 产品合同号

private String backCompanyContractCode;// 公司合同号

private String productTypeName;// 产品分类名称

private String productTypeId;// 产品分类编号

private String productContractCode;// 产品合同号

private String companyContractCode;// 公司合同号

private String money;// 合同金额

private String preMoney;//预收金额

private Integer customerId;// 客户编号

private String customerName;// 客户名称

private String customerLinkmanName;// 客户联系人姓名

private String customerProvince;// 客户省份

private String customerLinkmanTel;// 客户联系人电话

private String customerCity;// 客户城市

private String customerLinkmanFax; // 客户联系人传真

private String customerInvoiceBankName;// 客户开票银行名称

private String customerInvoiceBankAccount;// 客户开票银行帐号

private String customerTaxNumber;// 客户税号

private Integer customerInvoiceType;// 客户发票类型

private String templateType;// 模板类型

private String place;// 签订地点

private String intendBackTime;// 签订退货时间

private String text;// 说明

private String status;// 状态

/** 库房id **/
private String stockRoomId;

/** 库房名称 **/
private String stockRoomName;

/** 库房收货地址ID **/
private String stockRoomAddressId;

/** 货物接收单位名称 **/
private String goodsName;

/** 收货地址 **/
private String goodsAddress;

/** 邮编 **/
private String postcode;

/** 联系人 **/
private String linkman;

/** 电话 **/
private String tel;

/** 手机 **/
private String mobile;

private String backDate;//退货时间

private String backWay;//退货退款方式

private String backMoney;//退货金额合计

private String legalId;//法务专员登录名

private String legalName;//法务专员用户名

private String legalDate;//法务专员评审日期

private String legalIdea;//法务专员评审意见

private String legalText;//法务专员补充说明

private String sellMajId;//销售总监登录名

private String sellMajName;//销售总监用户名

private String sellMajDate;//销售总监评审日期

private String sellMajIdea;//销售总监评审意见

private String sellMajText;//销售总监补充说明

private String opeMajId;//运营总监登录名

private String opeMajName;//运营总监用户名

private String opeMajDate;//运营总监评审日期

private String opeMajIdea;//运营总监评审意见

private String opeMajText;//运营总监补充说明

private String areaMajId;//区域总监登录名

private String areaMajName;//区域总监用户名

private String areaMajIdea;//区域总监评审意见

private String areaMajIdea1;// 区域总监意见1

private String areaMajIdea2;// 区域总监意见2

private String areaMajIdea3;// 区域总监意见3

private String areaMajText;//区域总监补充说明

private String areaMajDate;//区域总监评审日期

private String productTypeNo;//产品分类编号

private String productDeptNo;//产品部门编号

private String productDeptName;//部门名称

private String stampType;//盖章类型

private String userName;//送审人

private String datetime;//新建销售退货合同时间
private String userAreaId;//
public String getSalesContractId() {
	return salesContractId;
}

public void setSalesContractId(String salesContractId) {
	this.salesContractId = salesContractId;
}

public String getDate() {
	return date;
}

public void setDate(String date) {
	this.date = date;
}

public String getProductTypeName() {
	return productTypeName;
}

public void setProductTypeName(String productTypeName) {
	this.productTypeName = productTypeName;
}

public String getProductTypeId() {
	return productTypeId;
}

public void setProductTypeId(String productTypeId) {
	this.productTypeId = productTypeId;
}

public String getProductContractCode() {
	return productContractCode;
}

public void setProductContractCode(String productContractCode) {
	this.productContractCode = productContractCode;
}


public String getMoney() {
	return money;
}

public void setMoney(String money) {
	this.money = money;
}

public String getPreMoney() {
	return preMoney;
}

public void setPreMoney(String preMoney) {
	this.preMoney = preMoney;
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

public String getCustomerLinkmanName() {
	return customerLinkmanName;
}

public void setCustomerLinkmanName(String customerLinkmanName) {
	this.customerLinkmanName = customerLinkmanName;
}

public String getCustomerProvince() {
	return customerProvince;
}

public void setCustomerProvince(String customerProvince) {
	this.customerProvince = customerProvince;
}

public String getCustomerLinkmanTel() {
	return customerLinkmanTel;
}

public void setCustomerLinkmanTel(String customerLinkmanTel) {
	this.customerLinkmanTel = customerLinkmanTel;
}

public String getCustomerCity() {
	return customerCity;
}

public void setCustomerCity(String customerCity) {
	this.customerCity = customerCity;
}

public String getCustomerLinkmanFax() {
	return customerLinkmanFax;
}

public void setCustomerLinkmanFax(String customerLinkmanFax) {
	this.customerLinkmanFax = customerLinkmanFax;
}

public String getCustomerInvoiceBankName() {
	return customerInvoiceBankName;
}

public void setCustomerInvoiceBankName(String customerInvoiceBankName) {
	this.customerInvoiceBankName = customerInvoiceBankName;
}

public String getCustomerInvoiceBankAccount() {
	return customerInvoiceBankAccount;
}

public void setCustomerInvoiceBankAccount(String customerInvoiceBankAccount) {
	this.customerInvoiceBankAccount = customerInvoiceBankAccount;
}

public String getCustomerTaxNumber() {
	return customerTaxNumber;
}

public void setCustomerTaxNumber(String customerTaxNumber) {
	this.customerTaxNumber = customerTaxNumber;
}

public Integer getCustomerInvoiceType() {
	return customerInvoiceType;
}

public void setCustomerInvoiceType(Integer customerInvoiceType) {
	this.customerInvoiceType = customerInvoiceType;
}

public String getTemplateType() {
	return templateType;
}

public void setTemplateType(String templateType) {
	this.templateType = templateType;
}

public String getPlace() {
	return place;
}

public void setPlace(String place) {
	this.place = place;
}

public String getIntendBackTime() {
	return intendBackTime;
}

public void setIntendBackTime(String intendBackTime) {
	this.intendBackTime = intendBackTime;
}

public String getStockRoomId() {
	return stockRoomId;
}

public void setStockRoomId(String stockRoomId) {
	this.stockRoomId = stockRoomId;
}

public String getStockRoomName() {
	return stockRoomName;
}

public void setStockRoomName(String stockRoomName) {
	this.stockRoomName = stockRoomName;
}

public String getStockRoomAddressId() {
	return stockRoomAddressId;
}

public void setStockRoomAddressId(String stockRoomAddressId) {
	this.stockRoomAddressId = stockRoomAddressId;
}

public String getGoodsName() {
	return goodsName;
}

public void setGoodsName(String goodsName) {
	this.goodsName = goodsName;
}

public String getGoodsAddress() {
	return goodsAddress;
}

public void setGoodsAddress(String goodsAddress) {
	this.goodsAddress = goodsAddress;
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

public String getBackWay() {
	return backWay;
}

public void setBackWay(String backWay) {
	this.backWay = backWay;
}

public String getBackMoney() {
	return backMoney;
}

public void setBackMoney(String backMoney) {
	this.backMoney = backMoney;
}

public String getBackDate() {
	return backDate;
}

public void setBackDate(String backDate) {
	this.backDate = backDate;
}

public String getSalesBackContractId() {
	return salesBackContractId;
}

public void setSalesBackContractId(String salesBackContractId) {
	this.salesBackContractId = salesBackContractId;
}

public String getFile() {
	return file;
}

public void setFile(String file) {
	this.file = file;
}

public String getText() {
	return text;
}

public void setText(String text) {
	this.text = text;
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

public String getLegalIdea() {
	return legalIdea;
}

public void setLegalIdea(String legalIdea) {
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

public String getOpeMajIdea() {
	return opeMajIdea;
}

public void setOpeMajIdea(String opeMajIdea) {
	this.opeMajIdea = opeMajIdea;
}

public String getOpeMajText() {
	return opeMajText;
}

public void setOpeMajText(String opeMajText) {
	this.opeMajText = opeMajText;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}


public String getProductTypeNo() {
	return productTypeNo;
}

public void setProductTypeNo(String productTypeNo) {
	this.productTypeNo = productTypeNo;
}

public String getProductDeptNo() {
	return productDeptNo;
}

public void setProductDeptNo(String productDeptNo) {
	this.productDeptNo = productDeptNo;
}

public String getProductDeptName() {
	return productDeptName;
}

public void setProductDeptName(String productDeptName) {
	this.productDeptName = productDeptName;
}

public String getStampType() {
	return stampType;
}

public void setStampType(String stampType) {
	this.stampType = stampType;
}

public String getUserName() {
	return userName;
}

public void setUserName(String userName) {
	this.userName = userName;
}

public String getDatetime() {
	return datetime;
}

public void setDatetime(String datetime) {
	this.datetime = datetime;
}

public String getCompanyContractCode() {
	return companyContractCode;
}

public void setCompanyContractCode(String companyContractCode) {
	this.companyContractCode = companyContractCode;
}

public String getUserAreaId() {
	return userAreaId;
}

public void setUserAreaId(String userAreaId) {
	this.userAreaId = userAreaId;
}

public String getBackProductContractCode() {
	return backProductContractCode;
}

public void setBackProductContractCode(String backProductContractCode) {
	this.backProductContractCode = backProductContractCode;
}

public String getBackCompanyContractCode() {
	return backCompanyContractCode;
}

public void setBackCompanyContractCode(String backCompanyContractCode) {
	this.backCompanyContractCode = backCompanyContractCode;
}



public String getAreaMajId() {
	return areaMajId;
}

public void setAreaMajId(String areaMajId) {
	this.areaMajId = areaMajId;
}

public String getAreaMajName() {
	return areaMajName;
}

public void setAreaMajName(String areaMajName) {
	this.areaMajName = areaMajName;
}

public String getAreaMajIdea() {
	if(this.areaMajIdea1!=null&&this.areaMajIdea2!=null&&this.areaMajIdea3!=null){
		areaMajIdea=this.areaMajIdea1+this.areaMajIdea2+this.areaMajIdea3;
	}
	return areaMajIdea;
}

public void setAreaMajIdea(String areaMajIdea) {
	if(areaMajIdea!=null&&areaMajIdea.length()==3){
		this.areaMajIdea1=areaMajIdea.substring(0,1);
		this.areaMajIdea2=areaMajIdea.substring(1,2);
		this.areaMajIdea3=areaMajIdea.substring(2,3);
	}
	this.areaMajIdea = areaMajIdea;
}

public String getAreaMajIdea1() {
	return areaMajIdea1;
}

public void setAreaMajIdea1(String areaMajIdea1) {
	this.areaMajIdea1 = areaMajIdea1;
}

public String getAreaMajIdea2() {
	return areaMajIdea2;
}

public void setAreaMajIdea2(String areaMajIdea2) {
	this.areaMajIdea2 = areaMajIdea2;
}

public String getAreaMajIdea3() {
	return areaMajIdea3;
}

public void setAreaMajIdea3(String areaMajIdea3) {
	this.areaMajIdea3 = areaMajIdea3;
}

public String getAreaMajText() {
	return areaMajText;
}

public void setAreaMajText(String areaMajText) {
	this.areaMajText = areaMajText;
}

public String getAreaMajDate() {
	return areaMajDate;
}

public void setAreaMajDate(String areaMajDate) {
	this.areaMajDate = areaMajDate;
}

}
