package cn.com.thtf.egov.cms.dto;

import java.io.Serializable;

/**
 * 库房信息实体
 * 
 * @author sxf
 * @date 2009.12.21
 * 
 */
public class DepotInfoDto implements Serializable {

	private static final long serialVersionUID = 1L;

	public DepotInfoDto() {

	}

	private Integer id;// 库房Id

	private String name;// 库房名称

	private String deptAndName;// 虚拟库房的组合名称
	
	//库房名称
	private String shortName;

	public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    private Integer type;// 库房类型 0：虚拟库 ，1：正常库

	private Integer productDeptId;// 库房所属产品部门Id

	// private Integer userAreaId;// 库房所属人员区域Id

	private String productDeptName;// 库房所属产品部门名称

	// private String userAreaName;// 库房所属人员区域名称

	private Integer receiveId;// 接受单位Id

	private String receiveName;// 接受单位名称

	private String supplierAddress;// 发货地址

	private String supplierPostcode;// 发货地址邮编

	private String supplierLinkman;// 发货地址联系人

	private String supplierTel;// 发货地址联系人电话

	private String supplierMobile;// 发货地址联系人手机

	public String getProductDeptName() {
		return productDeptName;
	}

	public void setProductDeptName(String productDeptName) {
		this.productDeptName = productDeptName;
	}

	// public void setProductDeptId(int productDeptId) {
	// this.productDeptId = productDeptId;
	// }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProductDeptId() {
		return productDeptId;
	}

	public void setProductDeptId(Integer productDeptId) {
		this.productDeptId = productDeptId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getReceiveId() {
		return receiveId;
	}

	public void setReceiveId(Integer receiveId) {
		this.receiveId = receiveId;
	}

	public String getReceiveName() {
		return receiveName;
	}

	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}

	public String getSupplierAddress() {
		return supplierAddress;
	}

	public void setSupplierAddress(String supplierAddress) {
		this.supplierAddress = supplierAddress;
	}

	public String getSupplierLinkman() {
		return supplierLinkman;
	}

	public void setSupplierLinkman(String supplierLinkman) {
		this.supplierLinkman = supplierLinkman;
	}

	public String getSupplierMobile() {
		return supplierMobile;
	}

	public void setSupplierMobile(String supplierMobile) {
		this.supplierMobile = supplierMobile;
	}

	public String getSupplierPostcode() {
		return supplierPostcode;
	}

	public void setSupplierPostcode(String supplierPostcode) {
		this.supplierPostcode = supplierPostcode;
	}

	public String getSupplierTel() {
		return supplierTel;
	}

	public void setSupplierTel(String supplierTel) {
		this.supplierTel = supplierTel;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getDeptAndName() {
		return deptAndName;
	}

	public void setDeptAndName(String deptAndName) {
		this.deptAndName = deptAndName;
	}
}
