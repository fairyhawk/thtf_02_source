package cn.com.thtf.egov.cms.dto;

import java.io.Serializable;

/**
 * 省的实体类
 * 
 * @author sxf
 * 
 */
public class ProvinceInfoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProvinceInfoDto() {

	}

	private int id;

	private String name;

	private int areaId;

	private String areaName;// 大区的名称

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
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

}
