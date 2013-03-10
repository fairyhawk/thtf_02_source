/**
 * ClassName  MoveStockProductDto
 *
 * History
 * Create User: liuqg
 * Create Date: 2010-6-29
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

import org.apache.commons.lang.StringUtils;


/**
 * 移库产品列表检索用
 * 
 * @author liuqg
 */

public class MoveStockProductDto {

	private String proTypeId;// 产品分类ID

	private String proSerieId;// 产品系列ID

	private String productCode;// 产品编码

	private String productName;// 产品名称

	private String productType;// 产品规格型号

	private String outStockroomId;// 库房ID
 

	/**
	 * @return the proTypeId
	 */
	public String getProTypeId() {
		return StringUtils.trim(proTypeId);
	}

	/**
	 * @param proTypeId
	 *            the proTypeId to set
	 */
	public void setProTypeId(String proTypeId) {
		this.proTypeId = proTypeId;
	}

	/**
	 * @return the proSerieId
	 */
	public String getProSerieId() {
		return StringUtils.trim(proSerieId);
	}

	/**
	 * @param proSerieId
	 *            the proSerieId to set
	 */
	public void setProSerieId(String proSerieId) {
		this.proSerieId = proSerieId;
	}

	/**
	 * @return the productCode
	 */
	public String getProductCode() {
		return StringUtils.trim(productCode);
	}

	/**
	 * @param productCode
	 *            the productCode to set
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return StringUtils.trim(productName);
	}

	/**
	 * @param productName
	 *            the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the productType
	 */
	public String getProductType() {
		return StringUtils.trim(productType);
	}

	/**
	 * @param productType
	 *            the productType to set
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}

	/**
	 * @return the outStockroomId
	 */
	public String getOutStockroomId() {
		return StringUtils.trim(outStockroomId);
	}

	/**
	 * @param outStockroomId the outStockroomId to set
	 */
	public void setOutStockroomId(String outStockroomId) {
		this.outStockroomId = outStockroomId;
	}

	 

}
