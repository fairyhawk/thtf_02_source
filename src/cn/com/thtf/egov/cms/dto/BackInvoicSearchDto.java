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

import org.apache.commons.lang.StringUtils;

/**
 * 销售退票检索用dto
 * 
 * @author liuqg
 */

public class BackInvoicSearchDto {

	private String number;// 发票号
	private String makeInvoiceId;// 开票申请单号
	private String customerName;// 客户名称
	private String productTypeId;// 产品分类编号
	private String invoiceType;// 发票类型
	private String userName;// 申请人名称
	private String confirmName;// 确认人名称
	private String status; // 发票状态
	private String dateStart;// 开票开始日期
	private String dateEnd;// 开票终止日期
	private String requestDateStart;// 申请开始日期
	private String requestDateEnd;// 申请终止日期
	private String backDateStart;// 退票开始日期
	private String backDateEnd;// 退票终止日期
	private String sendGoodsId;//发货单号

	// 备用字段
	private String userId;// 申请人ID
	private String confirmId;// 确认人ID
	private String productTypeName;// 产品分类名称
	private String date;// 开票日期
	private String requestDate;// 申请日期
	private String backDate;// 退票日期

	// 当前登录用户信息
	private String roleId;
	private String loginId;

	/**
	 * @return the roleId
	 */
	public String getRoleId() {
		return myTrim(roleId);
	}

	/**
	 * @param roleId
	 *            the roleId to set
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the loginId
	 */
	public String getLoginId() {
		return myTrim(loginId);
	}

	/**
	 * @param loginId
	 *            the loginId to set
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	/**
	 * @return the number
	 */
	public String getNumber() {
		return myTrim(number);
	}

	/**
	 * @param number
	 *            the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @return the makeInvoiceId
	 */
	public String getMakeInvoiceId() {
		return myTrim(makeInvoiceId);
	}

	/**
	 * @param makeInvoiceId
	 *            the makeInvoiceId to set
	 */
	public void setMakeInvoiceId(String makeInvoiceId) {
		this.makeInvoiceId = makeInvoiceId;
	}

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return myTrim(customerName);
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
		return myTrim(productTypeName);
	}

	/**
	 * @param productTypeName
	 *            the productTypeName to set
	 */
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	/**
	 * @return the productTypeId
	 */
	public String getProductTypeId() {
		return productTypeId;
	}

	/**
	 * @param productTypeId
	 *            the productTypeId to set
	 */
	public void setProductTypeId(String productTypeId) {
		this.productTypeId = productTypeId;
	}

	/**
	 * @return the invoiceType
	 */
	public String getInvoiceType() {
		return myTrim(invoiceType);
	}

	/**
	 * @param invoiceType
	 *            the invoiceType to set
	 */
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return myTrim(userId);
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
		return myTrim(userName);
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
		return myTrim(confirmId);
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
		return myTrim(confirmName);
	}

	/**
	 * @param confirmName
	 *            the confirmName to set
	 */
	public void setConfirmName(String confirmName) {
		this.confirmName = confirmName;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return myTrim(status);
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the dateStart
	 */
	public String getDateStart() {
		return myTrim(dateStart);
	}

	/**
	 * @param dateStart
	 *            the dateStart to set
	 */
	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	/**
	 * @return the dateEnd
	 */
	public String getDateEnd() {
		return myTrim(dateEnd);
	}

	/**
	 * @param dateEnd
	 *            the dateEnd to set
	 */
	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	/**
	 * @return the requestDateStart
	 */
	public String getRequestDateStart() {
		return myTrim(requestDateStart);
	}

	/**
	 * @param requestDateStart
	 *            the requestDateStart to set
	 */
	public void setRequestDateStart(String requestDateStart) {
		this.requestDateStart = requestDateStart;
	}

	/**
	 * @return the requestDateEnd
	 */
	public String getRequestDateEnd() {
		return myTrim(requestDateEnd);
	}

	/**
	 * @param requestDateEnd
	 *            the requestDateEnd to set
	 */
	public void setRequestDateEnd(String requestDateEnd) {
		this.requestDateEnd = requestDateEnd;
	}

	/**
	 * @return the backDateStart
	 */
	public String getBackDateStart() {
		return myTrim(backDateStart);
	}

	/**
	 * @param backDateStart
	 *            the backDateStart to set
	 */
	public void setBackDateStart(String backDateStart) {
		this.backDateStart = backDateStart;
	}

	/**
	 * @return the backDateEnd
	 */
	public String getBackDateEnd() {
		return myTrim(backDateEnd);
	}

	/**
	 * @param backDateEnd
	 *            the backDateEnd to set
	 */
	public void setBackDateEnd(String backDateEnd) {
		this.backDateEnd = backDateEnd;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return myTrim(date);
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the requestDate
	 */
	public String getRequestDate() {
		return myTrim(requestDate);
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
		return myTrim(backDate);
	}

	/**
	 * @param backDate
	 *            the backDate to set
	 */
	public void setBackDate(String backDate) {
		this.backDate = backDate;
	}

	/**
	 * 去掉空格
	 */
	public static String myTrim(String str) {
		if (str != null)
			return str.trim();
		return str;
	}

    /**
     * @return the sendGoodsId
     */
    public String getSendGoodsId() {
        return StringUtils.trim(sendGoodsId);
    }

    /**
     * @param sendGoodsId the sendGoodsId to set
     */
    public void setSendGoodsId(String sendGoodsId) {
        this.sendGoodsId = sendGoodsId;
    }

}
