/**
 * ClassName  SellBackAddDto
 *
 * History
 * Create User: liuqg
 * Create Date: 2010-6-1
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

/**
 * 申请退款用
 * 
 * @author liuqg
 */

public class SellBackAddDto {
	private String id;// 退款申请单号
	private String returnId;// 回款流水号
	private Integer productTypeId;// 产品分类编号
	private Integer customerId;// 客户编号
	private String customerName;// 客户名称
	private String customerLinkmanId;// 客户联系人编号
	private String backWay;// 退款方式
	private String number;// 凭证号
	private String money;// 退款金额
	private String status;// 退款状态
	private String date;// 申请日期
	private String userId;// 申请人登录名
	private String userName;// 申请人用户名
	private String userAreaId;// 人员区域编号
	private String text;// 特殊说明
	private String backDate;// 退款日期
	private String confirmId;// 确认人登录名
	private String confirmName;// 确认人用户名
	private String sell_majId;// 销售总监登录名

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the returnId
	 */
	public String getReturnId() {
		return returnId;
	}

	/**
	 * @param returnId
	 *            the returnId to set
	 */
	public void setReturnId(String returnId) {
		this.returnId = returnId;
	}

	/**
	 * @return the productTypeId
	 */
	public Integer getProductTypeId() {
		return productTypeId;
	}

	/**
	 * @param productTypeId
	 *            the productTypeId to set
	 */
	public void setProductTypeId(Integer productTypeId) {
		this.productTypeId = productTypeId;
	}

	/**
	 * @return the customerId
	 */
	public Integer getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId
	 *            the customerId to set
	 */
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName
	 *            the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return the customerLinkmanId
	 */
	public String getCustomerLinkmanId() {
		return customerLinkmanId;
	}

	/**
	 * @param customerLinkmanId
	 *            the customerLinkmanId to set
	 */
	public void setCustomerLinkmanId(String customerLinkmanId) {
		this.customerLinkmanId = customerLinkmanId;
	}

	/**
	 * @return the backWay
	 */
	public String getBackWay() {
		return backWay;
	}

	/**
	 * @param backWay
	 *            the backWay to set
	 */
	public void setBackWay(String backWay) {
		this.backWay = backWay;
	}

	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number
	 *            the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @return the money
	 */
	public String getMoney() {
		return money;
	}

	/**
	 * @param money
	 *            the money to set
	 */
	public void setMoney(String money) {
		this.money = money;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the userAreaId
	 */
	public String getUserAreaId() {
		return userAreaId;
	}

	/**
	 * @param userAreaId
	 *            the userAreaId to set
	 */
	public void setUserAreaId(String userAreaId) {
		this.userAreaId = userAreaId;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the backDate
	 */
	public String getBackDate() {
		return backDate;
	}

	/**
	 * @param backDate
	 *            the backDate to set
	 */
	public void setBackDate(String backDate) {
		this.backDate = backDate;
	}

	/**
	 * @return the confirmId
	 */
	public String getConfirmId() {
		return confirmId;
	}

	/**
	 * @param confirmId
	 *            the confirmId to set
	 */
	public void setConfirmId(String confirmId) {
		this.confirmId = confirmId;
	}

	/**
	 * @return the confirmName
	 */
	public String getConfirmName() {
		return confirmName;
	}

	/**
	 * @param confirmName
	 *            the confirmName to set
	 */
	public void setConfirmName(String confirmName) {
		this.confirmName = confirmName;
	}

	/**
	 * @return the sell_majId
	 */
	public String getSell_majId() {
		return sell_majId;
	}

	/**
	 * @param sellMajId
	 *            the sell_majId to set
	 */
	public void setSell_majId(String sellMajId) {
		sell_majId = sellMajId;
	}

	/**
	 * @return the sell_majName
	 */
	public String getSell_majName() {
		return sell_majName;
	}

	/**
	 * @param sellMajName
	 *            the sell_majName to set
	 */
	public void setSell_majName(String sellMajName) {
		sell_majName = sellMajName;
	}

	/**
	 * @return the sell_majDate
	 */
	public String getSell_majDate() {
		return sell_majDate;
	}

	/**
	 * @param sellMajDate
	 *            the sell_majDate to set
	 */
	public void setSell_majDate(String sellMajDate) {
		sell_majDate = sellMajDate;
	}

	/**
	 * @return the sell_majIdea
	 */
	public String getSell_majIdea() {
		return sell_majIdea;
	}

	/**
	 * @param sellMajIdea
	 *            the sell_majIdea to set
	 */
	public void setSell_majIdea(String sellMajIdea) {
		sell_majIdea = sellMajIdea;
	}

	/**
	 * @return the sell_majText
	 */
	public String getSell_majText() {
		return sell_majText;
	}

	/**
	 * @param sellMajText
	 *            the sell_majText to set
	 */
	public void setSell_majText(String sellMajText) {
		sell_majText = sellMajText;
	}

	/**
	 * @return the ope_majId
	 */
	public String getOpe_majId() {
		return ope_majId;
	}

	/**
	 * @param opeMajId
	 *            the ope_majId to set
	 */
	public void setOpe_majId(String opeMajId) {
		ope_majId = opeMajId;
	}

	/**
	 * @return the ope_majName
	 */
	public String getOpe_majName() {
		return ope_majName;
	}

	/**
	 * @param opeMajName
	 *            the ope_majName to set
	 */
	public void setOpe_majName(String opeMajName) {
		ope_majName = opeMajName;
	}

	/**
	 * @return the ope_majDate
	 */
	public String getOpe_majDate() {
		return ope_majDate;
	}

	/**
	 * @param opeMajDate
	 *            the ope_majDate to set
	 */
	public void setOpe_majDate(String opeMajDate) {
		ope_majDate = opeMajDate;
	}

	/**
	 * @return the ope_majIdea
	 */
	public String getOpe_majIdea() {
		return ope_majIdea;
	}

	/**
	 * @param opeMajIdea
	 *            the ope_majIdea to set
	 */
	public void setOpe_majIdea(String opeMajIdea) {
		ope_majIdea = opeMajIdea;
	}

	/**
	 * @return the ope_majText
	 */
	public String getOpe_majText() {
		return ope_majText;
	}

	/**
	 * @param opeMajText
	 *            the ope_majText to set
	 */
	public void setOpe_majText(String opeMajText) {
		ope_majText = opeMajText;
	}

	/**
	 * @return the datetime
	 */
	public String getDatetime() {
		return datetime;
	}

	/**
	 * @param datetime
	 *            the datetime to set
	 */
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	private String sell_majName;// 销售总监用户名
	private String sell_majDate;// 销售总监评审日期
	private String sell_majIdea;// 销售总监评审意见
	private String sell_majText;// 销售总监补充说明
	private String ope_majId;// 运营总监登录名
	private String ope_majName;// 运营总监用户名
	private String ope_majDate;// 运营总监评审日期
	private String ope_majIdea;// 运营总监评审意见
	private String ope_majText;// 运营总监补充说明
	private String datetime;// 新建日期

}
