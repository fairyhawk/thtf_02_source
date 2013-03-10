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
public class ProductTypeInfoDto implements Serializable {

	public ProductTypeInfoDto() {

	}

	private static final long serialVersionUID = 7946901168153686412L;

	private Integer id;// id

	private String no;// 产品类型编号

	private String name;// 名称

	private Integer product_dept_id;// 产品部门id

	private String climit;// 产品

	private String dno;

	private String dname;

	private String idNo;// id+no

	private String user_id;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getDno() {
		return dno;
	}

	public void setDno(String dno) {
		this.dno = dno;
	}

	public String getName() {
		return name;
	}

	public Integer getId() {
		return id;
	}

	public Integer getProduct_dept_id() {
		return product_dept_id;
	}

	public void setProduct_dept_id(Integer product_dept_id) {
		this.product_dept_id = product_dept_id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getClimit() {
		return climit;
	}

	public void setClimit(String climit) {
		this.climit = climit;
	}
}
