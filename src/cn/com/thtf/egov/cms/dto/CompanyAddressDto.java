package cn.com.thtf.egov.cms.dto;

public class CompanyAddressDto {
private String id;

private String companyId;

private String name;

private String address;

private String postcode;

private String linkman;

private String tel;

private String mobile;

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public String getCompanyId() {
	return companyId;
}

public void setCompanyId(String companyId) {
	this.companyId = companyId;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

public String getPostcode() {
	return postcode;
}

public void setPostcode(String postcode) {
	this.postcode = postcode;
}

public String getLinkman() {
	return linkman;
}

public void setLinkman(String linkman) {
	this.linkman = linkman;
}

public String getTel() {
	return tel;
}

public void setTel(String tel) {
	this.tel = tel;
}

public String getMobile() {
	return mobile;
}

public void setMobile(String mobile) {
	this.mobile = mobile;
}
}
