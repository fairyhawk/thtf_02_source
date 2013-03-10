/**
 * ClassName  WayBillAllDto
 *
 * History
 * Create User: liuqg
 * Create Date: 2010-5-10
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

/**
 * 运单一览信息
 * 
 * @author liuqg
 */

public class WayBillAllDto {

	private String no;// 运单号
	private String id; // 装箱单号
	private String logistics_name;// 物流公司名称
	private String customer_name;// 客户名称
	private String company_name;// 货物接收单位名称
	private String address;// 发货地址
	private Integer count;// 装箱件数
	private Integer request_way;// 要求货运方式
	private Integer reality_way;// 实际货运方式

	private String stockroom_name;// 库房名称
	private String date;// 发货日期
	private String estimate_date;// 预计到货日期
	private String arrival_date;// 到货日期
	private String signoff_date;// 签收日期
	private String confirm_date;// 回执确认日期
	private String status; // 运单状态
	private String send_date ;//发货日期
	 
	/**
	 * @return the send_date
	 */
	public String getSend_date() {
		return send_date;
	}

	/**
	 * @param sendDate the send_date to set
	 */
	public void setSend_date(String sendDate) {
		send_date = sendDate;
	}

	/**
	 * @return the reality_way
	 */
	public Integer getReality_way() {
		return reality_way;
	}

	/**
	 * @param realityWay
	 *            the reality_way to set
	 */
	public void setReality_way(Integer realityWay) {
		reality_way = realityWay;
	}

	/**
	 * @return the no
	 */
	public String getNo() {
		return no;
	}

	/**
	 * @param no
	 *            the no to set
	 */
	public void setNo(String no) {
		this.no = no;
	}

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
	 * @return the logistics_name
	 */
	public String getLogistics_name() {
		return logistics_name;
	}

	/**
	 * @param logisticsName
	 *            the logistics_name to set
	 */
	public void setLogistics_name(String logisticsName) {
		logistics_name = logisticsName;
	}

	/**
	 * @return the customer_name
	 */
	public String getCustomer_name() {
		return customer_name;
	}

	/**
	 * @param customerName
	 *            the customer_name to set
	 */
	public void setCustomer_name(String customerName) {
		customer_name = customerName;
	}

	/**
	 * @return the company_name
	 */
	public String getCompany_name() {
		return company_name;
	}

	/**
	 * @param companyName
	 *            the company_name to set
	 */
	public void setCompany_name(String companyName) {
		company_name = companyName;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the count
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(Integer count) {
		this.count = count;
	}

	/**
	 * @return the request_way
	 */
	public Integer getRequest_way() {
		return request_way;
	}

	/**
	 * @param requestWay
	 *            the request_way to set
	 */
	public void setRequest_way(Integer requestWay) {
		request_way = requestWay;
	}

	/**
	 * @return the stockroom_name
	 */
	public String getStockroom_name() {
		return stockroom_name;
	}

	/**
	 * @param stockroomName
	 *            the stockroom_name to set
	 */
	public void setStockroom_name(String stockroomName) {
		stockroom_name = stockroomName;
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
	 * @return the estimate_date
	 */
	public String getEstimate_date() {
		return estimate_date;
	}

	/**
	 * @param estimateDate
	 *            the estimate_date to set
	 */
	public void setEstimate_date(String estimateDate) {
		estimate_date = estimateDate;
	}

	/**
	 * @return the arrival_date
	 */
	public String getArrival_date() {
		return arrival_date;
	}

	/**
	 * @param arrivalDate
	 *            the arrival_date to set
	 */
	public void setArrival_date(String arrivalDate) {
		arrival_date = arrivalDate;
	}

	/**
	 * @return the signoff_date
	 */
	public String getSignoff_date() {
		return signoff_date;
	}

	/**
	 * @param signoffDate
	 *            the signoff_date to set
	 */
	public void setSignoff_date(String signoffDate) {
		signoff_date = signoffDate;
	}

	/**
	 * @return the confirm_date
	 */
	public String getConfirm_date() {
		return confirm_date;
	}

	/**
	 * @param confirmDate
	 *            the confirm_date to set
	 */
	public void setConfirm_date(String confirmDate) {
		confirm_date = confirmDate;
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

}
