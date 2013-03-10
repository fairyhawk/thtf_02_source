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
 * @author Administrator
 * 
 */
public class CustomerCreditInfoDto implements Serializable {

	/**
	 * 客户信用
	 */
	private static final long serialVersionUID = 7946901168153686412L;

	private Integer id;//

	private Integer customer_id;

	private Integer product_type_id;// 产品大类Id

	private Integer credit_type_id;// 信用类型Id

	private String project_name;// 项目名称

	private String enable;// 客户信用状态

	private Integer arterm;

	private String climit;// 授予客户的信用总额

	private String use;// 客户已用信用
	private String use_credit;

	private String lock;// 客户已冻结信用
	private String lock_credit;

	private String free;// 客户可用金额

	private String user_id;

	private String user_name;

	private String datetime;

	private Integer customerid;// 客户Id

	private String customername;// 客户名称

	private String creditname;// 信用类型名称

	private String productname;// 产品大类名称

	private String canClimit;// 一种产品大类，一种信用类型，可分配给客户的信用额

	private String hclimit;// 一种产品大类，一种信用类型已经分配给客户的额度

	public Integer getArterm() {
		return arterm;
	}

	public void setArterm(Integer arterm) {
		this.arterm = arterm;
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

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
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

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getCreditname() {
		return creditname;
	}

	public void setCreditname(String creditname) {
		this.creditname = creditname;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public Integer getCustomerid() {
		return customerid;
	}

	public void setCustomerid(Integer customerid) {
		this.customerid = customerid;
	}

	public String getCanClimit() {
		return canClimit;
	}

	public void setCanClimit(String canClimit) {
		this.canClimit = canClimit;
	}

	public String getClimit() {
		return climit;
	}

	public void setClimit(String climit) {
		this.climit = climit;
	}

	public String getFree() {
		return free;
	}

	public void setFree(String free) {
		this.free = free;
	}

	public String getLock() {
		return lock;
	}

	public void setLock(String lock) {
		this.lock = lock;
	}

	public String getUse() {
		return use;
	}

	public void setUse(String use) {
		this.use = use;
	}

	public String getHclimit() {
		return hclimit;
	}

	public void setHclimit(String hclimit) {
		this.hclimit = hclimit;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	/**
	 * @return the lock_credit
	 */
	public String getLock_credit() {
		return lock_credit;
	}

	/**
	 * @param lock_credit
	 *            the lock_credit to set
	 */
	public void setLock_credit(String lock_credit) {
		this.lock_credit = lock_credit;
	}

	/**
	 * @return the use_credit
	 */
	public String getUse_credit() {
		return use_credit;
	}

	/**
	 * @param use_credit
	 *            the use_credit to set
	 */
	public void setUse_credit(String use_credit) {
		this.use_credit = use_credit;
	}

}
