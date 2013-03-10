package cn.com.thtf.egov.cms.dto;

import java.io.Serializable;

/**
 * @author Administrator
 * 
 */
public class ProductSerieInfoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7946901168153686412L;

	private String id;// 产品系列id

	private String no;// 产品系列编号

	private String name;// 产品系列名称

	private String product_type_id;// 产品分类id

	private String dno;

	private String dname;

	private String tno;

	private String tname;

	private String num;// 产品系列编码

	private String ssIdNo;// 系列Id+No

	private String deptName;// 产品分类所属产品部门名称

	private String deptAccount;// 产品分类所属产品部门账号

	private String deptFax;// 产品分类所属产品部门传真

	public String getDeptAccount() {
		return deptAccount;
	}

	public void setDeptAccount(String deptAccount) {
		this.deptAccount = deptAccount;
	}

	public String getDeptFax() {
		return deptFax;
	}

	public void setDeptFax(String deptFax) {
		this.deptFax = deptFax;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProduct_type_id() {
		return product_type_id;
	}

	public void setProduct_type_id(String product_type_id) {
		this.product_type_id = product_type_id;
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

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getTno() {
		return tno;
	}

	public void setTno(String tno) {
		this.tno = tno;
	}

	public String getSsIdNo() {
		return ssIdNo;
	}

	public void setSsIdNo(String ssIdNo) {
		this.ssIdNo = ssIdNo;
	}
}
