/**
 * ClassName  BuyContractProductSelectDto
 *
 * History
 * Create User: ChenHuajiang
 * Create Date: 2010-6-3
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @author ChenHuajiang
 */

public class BuyContractProductSelectDto implements Serializable {

	private static final long serialVersionUID = 3921279686290340228L;

	// 产品ID
	private Integer prodId;

	// 产品编码
	private String prodCode;

	// 产品系列ID
	private String prodSerieId;

	// 产品名称
	private String prodName;

	// 规格型号
	private String prodType;

	// 单位
	private String prodUnit;

	// 库存总数
	private Integer stockTotalCount;

	// 需求数
	private Integer demandCount;

	// 库存单价
	private BigDecimal stockUnitPrice;

	// 销售可用数
	private String salesCanUse;

	// 限价
	private BigDecimal limitPrice;

	// 库存单价（未上税）
	private String noTaxOfPrice;

	public String getSalesCanUse() {
		return this.salesCanUse;
	}

	public void setSalesCanUse(String salesCanUse) {
		this.salesCanUse = salesCanUse;
	}

	public Integer getProdId() {
		return this.prodId;
	}

	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}

	public String getProdCode() {
		return this.prodCode;
	}

	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}

	public String getProdSerieId() {
		return this.prodSerieId;
	}

	public void setProdSerieId(String prodSerieId) {
		this.prodSerieId = prodSerieId;
	}

	public String getProdName() {
		return this.prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getProdType() {
		return this.prodType;
	}

	public void setProdType(String prodType) {
		this.prodType = prodType;
	}

	public String getProdUnit() {
		return this.prodUnit;
	}

	public void setProdUnit(String prodUnit) {
		this.prodUnit = prodUnit;
	}

	public Integer getStockTotalCount() {
		return this.stockTotalCount;
	}

	public void setStockTotalCount(Integer stockTotalCount) {
		this.stockTotalCount = stockTotalCount;
	}

	public Integer getDemandCount() {
		return this.demandCount;
	}

	public void setDemandCount(Integer demandCount) {
		this.demandCount = demandCount;
	}

	public BigDecimal getStockUnitPrice() {
		return this.stockUnitPrice;
	}

	public void setStockUnitPrice(BigDecimal stockUnitPrice) {
		this.stockUnitPrice = stockUnitPrice;
	}

	public String getNoTaxOfPrice() {
		return noTaxOfPrice;
	}

	public void setNoTaxOfPrice(String noTaxOfPrice) {
		this.noTaxOfPrice = noTaxOfPrice;
	}

	public BigDecimal getLimitPrice() {
		return limitPrice;
	}

	public void setLimitPrice(BigDecimal limitPrice) {
		this.limitPrice = limitPrice;
	}

}
