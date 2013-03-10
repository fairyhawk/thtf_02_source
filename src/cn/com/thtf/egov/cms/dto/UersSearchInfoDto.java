package cn.com.thtf.egov.cms.dto;

import java.io.Serializable;

public class UersSearchInfoDto implements Serializable {

	private static final long serialVersionUID = -8136040868928682178L;

	Object id;
	Object username;
	Object name;
	Object duty;
	Object regional;
	Object department;
	Object enable;
	Object online;

	public Object getDepartment() {
		return department;
	}

	public void setDepartment(Object department) {
		this.department = department;
	}

	public Object getDuty() {
		return duty;
	}

	public void setDuty(Object duty) {
		this.duty = duty;
	}

	public Object getEnable() {
		return enable;
	}

	public void setEnable(Object enable) {
		this.enable = enable;
	}

	public Object getName() {
		return name;
	}

	public void setName(Object name) {
		this.name = name;
	}

	public Object getOnline() {
		return online;
	}

	public void setOnline(Object online) {
		this.online = online;
	}

	public Object getRegional() {
		return regional;
	}

	public void setRegional(Object regional) {
		this.regional = regional;
	}

	public Object getUsername() {
		return username;
	}

	public void setUsername(Object username) {
		this.username = username;
	}

	public Object getId() {
		return id;
	}

	public void setId(Object id) {
		this.id = id;
	}
}
