/**
 * ClassName  InStockQueryDto
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-6-1
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

/**
 * InStockQueryDto
 * 
 * @author Lubo
 */
public class InStockQueryDto {

	/** 入库单号 */
	private String instockId;
	/** 库房名称 */
	private String stockroomId;
	/** 产品分类名称 */
	private String productTypeId;
	/** 产品合同号 */
	private String productContractCode;
	/** 公司合同号 */
	private String companyContarctCode;

	/** 供货商名称 */
	private String supplierName;
	/** 申请起始日期 */
	private String starttime;
	/** 申请终止日期 */
	private String endtime;
	/** 入库起始日期 */
	private String stkStarttime;
	/** 入库终止日期 */
	private String stkEndtime;

	/** 人员名称 */
	private String userName;
	/** 合同状态 */
	private String status;

	/** 当前用户 */
	private String userId;
	/** 当前用户权限 */
	private int roleId;
	/** 当前用户负责的产品分类 */
	private String productTypeIdArr;
	/** 产品总监 */
	private String userProduct;
	/** 是否是第一次进入页面 */
	private String init;

	/** 要求到账日期 */
	private String requestAccountStarttime;
	/** 要求到账日期 */
	private String requestAccountEndtime;

	public String getRequestAccountStarttime() {
		return requestAccountStarttime;
	}

	public void setRequestAccountStarttime(String requestAccountStarttime) {
		this.requestAccountStarttime = requestAccountStarttime;
	}

	public String getRequestAccountEndtime() {
		return requestAccountEndtime;
	}

	public void setRequestAccountEndtime(String requestAccountEndtime) {
		this.requestAccountEndtime = requestAccountEndtime;
	}

	/**
	 * @return the roleId
	 */
	public int getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId
	 *            the roleId to set
	 */
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the init
	 */
	public String getInit() {
		return init;
	}

	/**
	 * @param init
	 *            the init to set
	 */
	public void setInit(String init) {
		this.init = init;
	}

	/**
	 * @return the userProduct
	 */
	public String getUserProduct() {
		return userProduct;
	}

	/**
	 * @param userProduct
	 *            the userProduct to set
	 */
	public void setUserProduct(String userProduct) {
		this.userProduct = userProduct;
	}

	/**
	 * @return the productTypeIdArr
	 */
	public String getProductTypeIdArr() {
		return productTypeIdArr;
	}

	/**
	 * @param productTypeIdArr
	 *            the productTypeIdArr to set
	 */
	public void setProductTypeIdArr(String productTypeIdArr) {
		this.productTypeIdArr = productTypeIdArr;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the instockId
	 */
	public String getInstockId() {
		return instockId;
	}

	/**
	 * @param instockId
	 *            the instockId to set
	 */
	public void setInstockId(String instockId) {
		this.instockId = instockId;
	}

	/**
	 * @return the productContractCode
	 */
	public String getProductContractCode() {
		return productContractCode;
	}

	/**
	 * @param productContractCode
	 *            the productContractCode to set
	 */
	public void setProductContractCode(String productContractCode) {
		this.productContractCode = productContractCode;
	}

	/**
	 * @return the companyContarctCode
	 */
	public String getCompanyContarctCode() {
		return companyContarctCode;
	}

	/**
	 * @param companyContarctCode
	 *            the companyContarctCode to set
	 */
	public void setCompanyContarctCode(String companyContarctCode) {
		this.companyContarctCode = companyContarctCode;
	}

	/**
	 * @return the supplierName
	 */
	public String getSupplierName() {
		return supplierName;
	}

	/**
	 * @param supplierName
	 *            the supplierName to set
	 */
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	/**
	 * @return the starttime
	 */
	public String getStarttime() {
		return starttime;
	}

	/**
	 * @param starttime
	 *            the starttime to set
	 */
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	/**
	 * @return the endtime
	 */
	public String getEndtime() {
		return endtime;
	}

	/**
	 * @param endtime
	 *            the endtime to set
	 */
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	/**
	 * @return the stkStarttime
	 */
	public String getStkStarttime() {
		return stkStarttime;
	}

	/**
	 * @param stkStarttime
	 *            the stkStarttime to set
	 */
	public void setStkStarttime(String stkStarttime) {
		this.stkStarttime = stkStarttime;
	}

	/**
	 * @return the stkEndtime
	 */
	public String getStkEndtime() {
		return stkEndtime;
	}

	/**
	 * @param stkEndtime
	 *            the stkEndtime to set
	 */
	public void setStkEndtime(String stkEndtime) {
		this.stkEndtime = stkEndtime;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the stockroomId
	 */
	public String getStockroomId() {
		return stockroomId;
	}

	/**
	 * @param stockroomId
	 *            the stockroomId to set
	 */
	public void setStockroomId(String stockroomId) {
		this.stockroomId = stockroomId;
	}

	/**
	 * @return the productTypeId
	 */
	public String getProductTypeId() {
		return productTypeId;
	}

	/**
	 * @param productTypeId
	 *            the productTypeId to set
	 */
	public void setProductTypeId(String productTypeId) {
		this.productTypeId = productTypeId;
	}

}
