/**
 * ClassName  CustwaybillDto
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-8-17
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

/**
 * CustwaybillDto
 * 
 * @author Lubo
 */
public class CustwaybillDto {

	/** 发货单ID */
	private String id;
	/** 产品分类名称 */
	private String productTypeName;
	/** 产品合同号 */
	private String productContractCode;
	/** 公司合同号 */
	private String companyContractCode;
	/** 客户名称 */
	private String customerName;

	/** 发货日期 */
	private String sendDate;
	/** 要求到帐日期 */
	private String date;
	/** 人员名称 */
	private String userName;
	/** 运单ID */
	private String boxId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	public String getProductContractCode() {
		return productContractCode;
	}

	public void setProductContractCode(String productContractCode) {
		this.productContractCode = productContractCode;
	}

	public String getCompanyContractCode() {
		return companyContractCode;
	}

	public void setCompanyContractCode(String companyContractCode) {
		this.companyContractCode = companyContractCode;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBoxId() {
		return boxId;
	}

	public void setBoxId(String boxId) {
		this.boxId = boxId;
	}

}
