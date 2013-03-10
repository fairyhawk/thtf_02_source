package cn.com.thtf.egov.cms.dto;

import java.io.Serializable;

/**
 * 市的实体类
 * 
 * @author sxf
 * 
 */
public class CityInfoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CityInfoDto() {
		// TODO Auto-generated constructor stub
	}

	private int id;

	private String name;

	private int provinceId;

	private int areaId;

	private String areaName;// 大区的名称

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}

}
