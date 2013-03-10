/**
 * ClassName  CustomerProInfoListDto
 *
 * History
 * Create User: liuqg
 * Create Date: 2010-6-2
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

/**
 * 
 * @author liuqg
 */

public class CustomerProInfoListDto {
	/*
	 * 产品分类名称
	 */
	private String productName;
	/*
	 * 信用类型名称
	 */
	private String creditTypeName;
	/*
	 * 项目名称
	 */
	private String projectName;
	/*
	 * 帐期
	 */
	private Integer arterm;
	/*
	 * 信用额度
	 */
	private double climit;
	/*
	 * 可用金额
	 */
	private double canUseLimit;
	/*
	 * 客户信用编号
	 */
	private Integer id;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName
	 *            the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the creditTypeName
	 */
	public String getCreditTypeName() {
		return creditTypeName;
	}

	/**
	 * @param creditTypeName
	 *            the creditTypeName to set
	 */
	public void setCreditTypeName(String creditTypeName) {
		this.creditTypeName = creditTypeName;
	}

	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * @param projectName
	 *            the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * @return the arterm
	 */
	public Integer getArterm() {
		return arterm;
	}

	/**
	 * @param arterm
	 *            the arterm to set
	 */
	public void setArterm(Integer arterm) {
		this.arterm = arterm;
	}

	/**
	 * @return the climit
	 */
	public double getClimit() {
		return climit;
	}

	/**
	 * @param climit
	 *            the climit to set
	 */
	public void setClimit(double climit) {
		this.climit = climit;
	}

	/**
	 * @return the canUseLimit
	 */
	public double getCanUseLimit() {
		return canUseLimit;
	}

	/**
	 * @param canUseLimit
	 *            the canUseLimit to set
	 */
	public void setCanUseLimit(double canUseLimit) {
		this.canUseLimit = canUseLimit;
	}

}
