/**
 * ClassName  MoveOutStockInfo
 *
 * History
 * Create User: jiangmingxing
 * Create Date: 2010-6-29
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import java.sql.Timestamp;

/**
 * 移出库房信息
 * @author jiangmingxing
 */

public class MoveOutStockDto implements Serializable {

	private static final long serialVersionUID = -4392212926697619043L;

	private String id;     //产品编号
	
	private String code;     //产品编码
	
	private String productName;     //产品名称
	
	private String type;      //规格型号
	
	private String unit;      //单位
	
	private BigDecimal price;     //库存单价
	
	private BigDecimal priceTax;  //库存单价(含税)

	private Integer count;        //移库数
	
	private Integer useCount;    //移库可用数
	
	private Timestamp timeStamp;    //时间戳

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the unit
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * @param unit the unit to set
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * @return the count
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(Integer count) {
		this.count = count;
	}

	/**
	 * @return the userCount
	 */
	public Integer getUseCount() {
		return useCount;
	}

	/**
	 * @param userCount the userCount to set
	 */
	public void setUseCount(Integer userCount) {
		this.useCount = userCount;
	}

	/**
	 * @return the timeStamp
	 */
	public Timestamp getTimeStamp() {
		return timeStamp;
	}

	/**
	 * @param timeStamp the timeStamp to set
	 */
	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}

	/**
	 * @return the priceTax
	 */
	public BigDecimal getPriceTax() {
		return priceTax;
	}

	/**
	 * @param priceTax the priceTax to set
	 */
	public void setPriceTax(BigDecimal priceTax) {
		this.priceTax = priceTax;
	}

	

}
