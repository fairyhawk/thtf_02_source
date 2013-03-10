/**
 * ClassName  PaymentInfoForBuyBackDto
 *
 * History
 * Create User: liuqg
 * Create Date: 2010-6-9
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

import java.math.BigDecimal;

/**
 * 获得付款信息 采购退款用
 * 
 * @author liuqg
 */

public class PaymentInfoForBuyBackDto {

	private String id;// 付款单号
	private Integer productTypeId;// 产品分类编码
	private String productTypeName;// 产品分类名称
	private Integer supplierId;// 供应商编码
	private String supplierName;// 供应商名称
	private BigDecimal paymentMoney;// 付款金额
	private BigDecimal alreadyAppointMoney;// 指定金额
	private BigDecimal alreadyBackMoney;// 已退款金额
	private BigDecimal canBackMoney;// 可退款金额

	/**
	 * @return the productTypeId
	 */
	public Integer getProductTypeId() {
		return productTypeId;
	}

	/**
	 * @param productTypeId
	 *            the productTypeId to set
	 */
	public void setProductTypeId(Integer productTypeId) {
		this.productTypeId = productTypeId;
	}

	/**
	 * @return the productTypeName
	 */
	public String getProductTypeName() {
		return productTypeName;
	}

	/**
	 * @param productTypeName
	 *            the productTypeName to set
	 */
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	/**
	 * @return the supplierId
	 */
	public Integer getSupplierId() {
		return supplierId;
	}

	/**
	 * @param supplierId
	 *            the supplierId to set
	 */
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
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
	 * @return the paymentMoney
	 */
	public BigDecimal getPaymentMoney() {
		return paymentMoney;
	}

	/**
	 * @param paymentMoney
	 *            the paymentMoney to set
	 */
	public void setPaymentMoney(BigDecimal paymentMoney) {
		this.paymentMoney = paymentMoney;
	}

	/**
	 * @return the alreadyAppointMoney
	 */
	public BigDecimal getAlreadyAppointMoney() {
		return alreadyAppointMoney;
	}

	/**
	 * @param alreadyAppointMoney
	 *            the alreadyAppointMoney to set
	 */
	public void setAlreadyAppointMoney(BigDecimal alreadyAppointMoney) {
		this.alreadyAppointMoney = alreadyAppointMoney;
	}

	/**
	 * @return the alreadyBackMoney
	 */
	public BigDecimal getAlreadyBackMoney() {
		return alreadyBackMoney;
	}

	/**
	 * @param alreadyBackMoney
	 *            the alreadyBackMoney to set
	 */
	public void setAlreadyBackMoney(BigDecimal alreadyBackMoney) {
		this.alreadyBackMoney = alreadyBackMoney;
	}

	/**
	 * @return the canBackMoney
	 */
	public BigDecimal getCanBackMoney() {
		return canBackMoney;
	}

	/**
	 * @param canBackMoney
	 *            the canBackMoney to set
	 */
	public void setCanBackMoney(BigDecimal canBackMoney) {
		this.canBackMoney = canBackMoney;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

}
