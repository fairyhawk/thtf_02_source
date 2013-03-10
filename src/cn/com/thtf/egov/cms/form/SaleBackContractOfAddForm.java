package cn.com.thtf.egov.cms.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import cn.com.thtf.egov.cms.dto.SaleBackContractOfAddDto;

public class SaleBackContractOfAddForm extends ActionForm{
	/**
	 * @author hanrubing
	 */
	private static final long serialVersionUID = 1L;
	private String id;// 销售退货合同流水号
	private String sellContractId;// 销售合同流水号
	private String productContractCode;// 产品合同号
	private String companyContarctCode;// 公司合同号
	private String date;// 签订日期
	private String backDate;// 签订日期
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
	private FormFile file;// 文件名字
	private String storeRoomId;// 库房名称
	private String storeRoomAddressId;// 库房收货地址编号
	private SaleBackContractOfAddDto saleBackContractOfAddDto=new SaleBackContractOfAddDto();//销售退货合同dto

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

	public String getCompanyContarctCode() {
		return companyContarctCode;
	}

	public void setCompanyContarctCode(String companyContarctCode) {
		this.companyContarctCode = companyContarctCode;
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

	public SaleBackContractOfAddDto getSaleBackContractOfAddDto() {
		return saleBackContractOfAddDto;
	}

	public void setSaleBackContractOfAddDto(
			SaleBackContractOfAddDto saleBackContractOfAddDto) {
		this.saleBackContractOfAddDto = saleBackContractOfAddDto;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
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

	public String getBackDate() {
		return backDate;
	}

	public void setBackDate(String backDate) {
		this.backDate = backDate;
	}
	
}
