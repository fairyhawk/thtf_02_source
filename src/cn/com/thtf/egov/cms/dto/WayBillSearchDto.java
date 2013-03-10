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
 * 检索运单一览信息
 * 
 * @author liuqg
 */

public class WayBillSearchDto {

	private String no;// 运单号
	private String boxId; // 装箱单号
	private String customer_name;// 客户名称
	private String status; // 运单状态
	private Integer logisticsId; // 用户所在的物流中心ID
	
	private String company_name;
	

	/**
	 * @return the company_name
	 */
	public String getCompany_name() {
		return myTrim(company_name);
	}

	/**
	 * @param companyName the company_name to set
	 */
	public void setCompany_name(String companyName) {
		company_name = companyName;
	}

	/**
	 * @return the logisticsId
	 */
	public Integer getLogisticsId() {

		return logisticsId;
	}

	/**
	 * @param logisticsId
	 *            the logisticsId to set
	 */
	public void setLogisticsId(Integer logisticsId) {
		this.logisticsId = logisticsId;
	}

	/**
	 * @return the no
	 */
	public String getNo() {
		return myTrim(no);
	}

	/**
	 * @param no
	 *            the no to set
	 */
	public void setNo(String no) {
		this.no = no;
	}

	/**
	 * @return the boxid
	 */
	public String getBoxId() {
		return myTrim(boxId);
	}

	/**
	 * @param boxid
	 *            the boxid to set
	 */
	public void setBoxId(String boxId) {
		this.boxId = boxId;
	}

	/**
	 * @return the customer_name
	 */
	public String getCustomer_name() {
		return myTrim(customer_name);
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
	 * @param customerName
	 *            the customer_name to set
	 */
	public void setCustomer_name(String customerName) {
		customer_name = customerName;
	}

	/**
	 * 去掉空格
	 */
	public static String myTrim(String str) {
		if (str != null)
			return str.trim();
		return str;
	}

}
