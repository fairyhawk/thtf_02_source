package cn.com.thtf.egov.cms.dto;

import java.io.Serializable;

public class ProductCreditListInfoDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductCreditListInfoDto() {

	}

	// 产品分类ID
	private Integer pid;

	// 产品分类名称
	private String ptName;

	// 信用额度
	private String ptClimit;

	// 已分配额度
	private String yassignedClimit;

	// 未分配额度
	private String nassignedClimit;

	// 已用额度
	private String yuseClimit;

	// 冻结额度
	private String nuseClimit;

	// 可用额度
	private String freeClimit;

	public String getFreeClimit() {
		return freeClimit;
	}

	public void setFreeClimit(String freeClimit) {
		this.freeClimit = freeClimit;
	}

	public String getPtClimit() {
		return ptClimit;
	}

	public void setPtClimit(String ptClimit) {
		this.ptClimit = ptClimit;
	}

	public String getPtName() {
		return ptName;
	}

	public void setPtName(String ptName) {
		this.ptName = ptName;
	}

	public String getNassignedClimit() {
		return nassignedClimit;
	}

	public void setNassignedClimit(String nassignedClimit) {
		this.nassignedClimit = nassignedClimit;
	}

	public String getNuseClimit() {
		return nuseClimit;
	}

	public void setNuseClimit(String nuseClimit) {
		this.nuseClimit = nuseClimit;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getYassignedClimit() {
		return yassignedClimit;
	}

	public void setYassignedClimit(String yassignedClimit) {
		this.yassignedClimit = yassignedClimit;
	}

	public String getYuseClimit() {
		return yuseClimit;
	}

	public void setYuseClimit(String yuseClimit) {
		this.yuseClimit = yuseClimit;
	}

}
