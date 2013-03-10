package cn.com.thtf.egov.cms.dto;

import org.apache.struts.upload.FormFile;

public class SaleBackContractOfAddDto {
	private String id;// 销售退货合同流水号
	private String sellContractId;// 销售合同流水号
	private String productContractCode;// 产品合同号
	private String companyContractCode;// 公司合同号
	private String date;// 签订日期
	private String backDate;// 退货日期
	private String status;// 合同状态
	private String templateType;// 模板类型
	private String place;// 签订地点
	private String stockroomId;// 库房编号
	private String stockroomAddressId;// 库房收货地址编号
	private String backWay;// 退款退货方式
	private String userId;// 销售经理登录名
	private String userName;// 人员名称
	private String userAreaId;// 人员区域编号
	private String text;// 特殊说明
	private String money;// 合同金额
	private String datetime;// 新建日期
	private FormFile file;// 文件
	private String fileName;// 文件名字
	private String storeRoomId;// 库房名称
	private String storeRoomAddressId;// 库房收货地址编号
	
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
	
	private String productTypeId;//产品分类id
	
	private String submitType;//提交类型
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSellContractId() {
		return sellContractId;
	}

	public void setSellContractId(String sellContractId) {
		this.sellContractId = sellContractId;
	}

	public String getProductContractCode() {
		return productContractCode;
	}

	public void setProductContractCode(String productContractCode) {
		this.productContractCode = productContractCode;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getStockroomId() {
		return stockroomId;
	}

	public void setStockroomId(String stockroomId) {
		this.stockroomId = stockroomId;
	}

	public String getStockroomAddressId() {
		return stockroomAddressId;
	}

	public void setStockroomAddressId(String stockroomAddressId) {
		this.stockroomAddressId = stockroomAddressId;
	}

	public String getBackWay() {
		return backWay;
	}

	public void setBackWay(String backWay) {
		this.backWay = backWay;
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

	public String getUserAreaId() {
		return userAreaId;
	}

	public void setUserAreaId(String userAreaId) {
		this.userAreaId = userAreaId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getStoreRoomId() {
		return storeRoomId;
	}

	public void setStoreRoomId(String storeRoomId) {
		this.storeRoomId = storeRoomId;
	}

	public String getStoreRoomAddressId() {
		return storeRoomAddressId;
	}

	public void setStoreRoomAddressId(String storeRoomAddressId) {
		this.storeRoomAddressId = storeRoomAddressId;
	}

	public FormFile getFile() {
		return file;
	}

	public void setFile(FormFile file) {
		this.file = file;
	}

	public String getLegalId() {
		return legalId;
	}

	public void setLegalId(String legalId) {
		this.legalId = legalId;
	}

	public String getSellMajId() {
		return sellMajId;
	}

	public void setSellMajId(String sellMajId) {
		this.sellMajId = sellMajId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getBackDate() {
		return backDate;
	}

	public void setBackDate(String backDate) {
		this.backDate = backDate;
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

	public String getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(String productTypeId) {
		this.productTypeId = productTypeId;
	}

	public String getSubmitType() {
		return submitType;
	}

	public void setSubmitType(String submitType) {
		this.submitType = submitType;
	}

	public String getCompanyContractCode() {
		return companyContractCode;
	}

	public void setCompanyContractCode(String companyContractCode) {
		this.companyContractCode = companyContractCode;
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
