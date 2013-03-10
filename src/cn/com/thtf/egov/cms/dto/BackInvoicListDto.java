/**
 * ClassName  BackInvoicListDto
 *
 * History
 * Create User: liuqg
 * Create Date: 2010-5-20
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

/**
 * 销售退票一览
 * 
 * @author liuqg
 */

public class BackInvoicListDto {
	
	private String id;// 发票编号

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

	private String date;// 开票日期
	private String number;// 发票号
	private String makeInvoiceId;// 开票申请单号
	private String customerName;// 客户名称
	private String productTypeName;// 产品分类名称
	private Double money;// 发票金额
	private Integer invoiceType;// 发票类型
	private String userId;// 申请人ID
	private String userName;// 申请人名称
	private String confirmId;// 确认人ID
	private String confirmName;// 确认人名称
	private String requestDate;// 申请日期
	private String backDate;// 退票日期
	private Integer status; // 发票状态

	/**
	 * @return the makeInvoiceId
	 */
	public String getMakeInvoiceId() {
		return makeInvoiceId;
	}

	/**
	 * @param makeInvoiceId
	 *            the makeInvoiceId to set
	 */
	public void setMakeInvoiceId(String makeInvoiceId) {
		this.makeInvoiceId = makeInvoiceId;
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
	 * @return the productTypeName
	 */
	public String getProductTypeName() {
		return productTypeName;
	}

	/**
	 * @param productTypeName
	 *            the productTypeName to set
	 */
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	/**
	 * @return the money
	 */
	public Double getMoney() {
		return money;
	}

	/**
	 * @param money
	 *            the money to set
	 */
	public void setMoney(Double money) {
		this.money = money;
	}

	/**
	 * @return the invoiceType
	 */
	public Integer getInvoiceType() {
		return invoiceType;
	}

	/**
	 * @param invoiceType
	 *            the invoiceType to set
	 */
	public void setInvoiceType(Integer invoiceType) {
		this.invoiceType = invoiceType;
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
	 * @return the requestDate
	 */
	public String getRequestDate() {
		return requestDate;
	}

	/**
	 * @param requestDate
	 *            the requestDate to set
	 */
	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
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
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

}
