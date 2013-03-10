package cn.com.thtf.egov.cms.dto;

import java.io.Serializable;

/**
 * 物流公司实体类
 * 
 * @author sxf
 * 
 */
public class LogisticsInfoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LogisticsInfoDto() {

	}

	private Integer id;

	private String name;

	private String area;

	private String province;

	private String city;

	private String address;

	private String postcode;

	private String linkman;

	private String role;

	private String tel;

	private String fax;

	private String mobile;

	private String mail;

	private String msn;

	private String qq;

	private String remitBankName;

	private String remitBankAccount;

	private String date;

	public String getDate() {
		return date;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMsn() {
		return msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getRemitBankAccount() {
		return remitBankAccount;
	}

	public void setRemitBankAccount(String remitBankAccount) {
		this.remitBankAccount = remitBankAccount;
	}

	public String getRemitBankName() {
		return remitBankName;
	}

	public void setRemitBankName(String remitBankName) {
		this.remitBankName = remitBankName;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
