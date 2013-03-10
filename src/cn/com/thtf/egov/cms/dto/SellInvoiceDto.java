package cn.com.thtf.egov.cms.dto;

/**
 * 
 * 发票dto
 * 
 * @author zhangzx
 */
public class SellInvoiceDto {

	private Integer id; // 发票编号

	private String productTypeName;// 产品分类名称

	private String date;// 开票日期

	private String number;// 发票号

	private String makeInvoiceId;// 开票申请单号

	private String productTypeId;// 产品分类编号

	private String customerId;// 客户编号

	private String customerName;// 客户名称

	private String money; // 发票金额

	private String invoiceType;// 发票类型

	private Integer status;// 发票状态

	private String requestDate;// 退票申请日期

	private String userId;// 退票申请人登录名

	private String userName;// 退票申请人用户名

	private String userAreaId;// 人员区域编号

	private String text;// 特殊说明

	private String backDate;// 退票日期

	private String confirmId;// 退票确认人登录名

	private String confirmName;// 退票确认人用户名

	private String sellMajId;// 销售总监登录名

	private String sellMajName;// 销售总监用户名

	private String sellMajDate;// 销售总监评审日期

	private String sellMajIdea;// 销售总监评审意见

	private String sellMajText;// 销售总监补充说明

	private String opeMajId;// 运营总监登录名

	private String opeMajName;// 运营总监用户名

	private String opeMajDate;// 运营总监评审日期
	
	private String opeMajIdea;// 运营总监评审意见
	
	private String opeMajText;// 运营总监补充说明
	
	private String roleId;// 权限
	private String remark;// 备注信息
	
	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}


	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getRoleId() {
		return roleId;
	}


	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getMakeInvoiceId() {
		return makeInvoiceId;
	}

	public void setMakeInvoiceId(String makeInvoiceId) {
		this.makeInvoiceId = makeInvoiceId;
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

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
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

	public String getBackDate() {
		return backDate;
	}

	public void setBackDate(String backDate) {
		this.backDate = backDate;
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

	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

}
