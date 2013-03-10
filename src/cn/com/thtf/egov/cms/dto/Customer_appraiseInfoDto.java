/**
 * ClassName  AreaInfo
 *
 * History
 * Create User: Administrator
 * Create Date: 2009-12-16
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

import java.io.Serializable;

/**
 * @author ss
 * 
 */
public class Customer_appraiseInfoDto implements Serializable {

	/**
	 * 客户评价
	 */
	private static final long serialVersionUID = 7946901168153686412L;
	private Integer id;

	private Integer product_type_id;

	private Integer credit_type_id;

	private Integer customer_id;

	private String contract_money;

	private String send_money;

	private String mreturn;

	private String exceed_return;

	private String exceed_money;

	private Integer exceed_max;

	private Integer exceed_count;

	private Integer send_count;

	private Integer exceed_average;

	public String getContract_money() {
		return contract_money;
	}

	public void setContract_money(String contract_money) {
		this.contract_money = contract_money;
	}

	public Integer getCredit_type_id() {
		return credit_type_id;
	}

	public void setCredit_type_id(Integer credit_type_id) {
		this.credit_type_id = credit_type_id;
	}

	public Integer getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}

	public Integer getExceed_average() {
		return exceed_average;
	}

	public void setExceed_average(Integer exceed_average) {
		this.exceed_average = exceed_average;
	}

	public Integer getExceed_count() {
		return exceed_count;
	}

	public void setExceed_count(Integer exceed_count) {
		this.exceed_count = exceed_count;
	}

	public Integer getExceed_max() {
		return exceed_max;
	}

	public void setExceed_max(Integer exceed_max) {
		this.exceed_max = exceed_max;
	}

	public String getExceed_money() {
		return exceed_money;
	}

	public void setExceed_money(String exceed_money) {
		this.exceed_money = exceed_money;
	}

	public String getExceed_return() {
		return exceed_return;
	}

	public void setExceed_return(String exceed_return) {
		this.exceed_return = exceed_return;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProduct_type_id() {
		return product_type_id;
	}

	public void setProduct_type_id(Integer product_type_id) {
		this.product_type_id = product_type_id;
	}

	public String getMreturn() {
		return mreturn;
	}

	public void setMreturn(String mreturn) {
		this.mreturn = mreturn;
	}

	public Integer getSend_count() {
		return send_count;
	}

	public void setSend_count(Integer send_count) {
		this.send_count = send_count;
	}

	public String getSend_money() {
		return send_money;
	}

	public void setSend_money(String send_money) {
		this.send_money = send_money;
	}
}
