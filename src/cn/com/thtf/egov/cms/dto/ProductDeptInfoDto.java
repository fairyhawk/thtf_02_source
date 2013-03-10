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
public class ProductDeptInfoDto implements Serializable {

	/**
     * 
     */
	private static final long serialVersionUID = 7946901168153686412L;

	private Integer id;// 产品部门id

	private String no;// 部门编号

	private String name;// 部门名称

	private String account;// 账号

	private String fax;// 传真����

	public String getName() {
		return name;
	}

	public Integer getId() {
		return id;
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

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

}
