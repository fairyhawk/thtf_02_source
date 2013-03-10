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
public class DemandInfoDto implements Serializable {

	/**
	 * 需求单
	 */
	private static final long serialVersionUID = 7946901168153686412L;
	private Integer id;

	private String sell_contract_id;

	private Integer product_id;

	private Integer count;

	private String user_id;

	private String user_name;

	private Integer user_area_id;

	private String confirm_id;

	private String confirm_name;

	private String date;

	private String status;

	public String getConfirm_id() {
		return confirm_id;
	}

	public void setConfirm_id(String confirm_id) {
		this.confirm_id = confirm_id;
	}

	public String getConfirm_name() {
		return confirm_name;
	}

	public void setConfirm_name(String confirm_name) {
		this.confirm_name = confirm_name;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}

	public String getSell_contract_id() {
		return sell_contract_id;
	}

	public void setSell_contract_id(String sell_contract_id) {
		this.sell_contract_id = sell_contract_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getUser_area_id() {
		return user_area_id;
	}

	public void setUser_area_id(Integer user_area_id) {
		this.user_area_id = user_area_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

}
