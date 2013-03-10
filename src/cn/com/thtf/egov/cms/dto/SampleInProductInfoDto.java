/**
 * ClassName  SampleOutProductInfo
 *
 * History
 * Create User: jiangmingxing
 * Create Date: 2010-7-5
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

import cn.com.thtf.egov.cms.application.BaseEntity;

/**
 * 产品信息Dto
 * 
 * @author jiangmingxing
 */

public class SampleInProductInfoDto  extends BaseEntity {
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 651181096221792909L;

	private String id; // 产品编号

	private String code; // 产品编码

	private String productName; // 产品名称

	private String type; // 规格型号

	private String unit; // 单位

	private BigDecimal price; // 库存单价
	
	private BigDecimal priceTax; // 库存单价(含税)

	private Integer count; // 借出数

	private BigDecimal money; // 借出金额
	
	private BigDecimal moneyTax; // 借出金额（含税）

	private Integer reCount; // 归还数

	private Integer alreadyReCount; // 已归还数

	private BigDecimal returnMoney; // 归还金额
	
	private BigDecimal returnMoneyTax; // 归还金额（含税）

	private Timestamp timeStamp; // 时间戳

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

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
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
	 * @param productName
	 *            the productName to set
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
	 * @param type
	 *            the type to set
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
	 * @param unit
	 *            the unit to set
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
	 * @param price
	 *            the price to set
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
	 * @param count
	 *            the count to set
	 */
	public void setCount(Integer count) {
		this.count = count;
	}

	/**
	 * @return the money
	 */
	public BigDecimal getMoney() {
		return money;
	}

	/**
	 * @param money
	 *            the money to set
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	/**
	 * @return the reCount
	 */
	public Integer getReCount() {
		return reCount;
	}

	/**
	 * @param reCount
	 *            the reCount to set
	 */
	public void setReCount(Integer reCount) {
		this.reCount = reCount;
	}

	/**
	 * @return the alreadyReCount
	 */
	public Integer getAlreadyReCount() {
		return alreadyReCount;
	}

	/**
	 * @param alreadyReCount the alreadyReCount to set
	 */
	public void setAlreadyReCount(Integer alreadyReCount) {
		this.alreadyReCount = alreadyReCount;
	}

	/**
	 * @return the returnMoney
	 */
	public BigDecimal getReturnMoney() {
		return returnMoney;
	}

	/**
	 * @param returnMoney
	 *            the returnMoney to set
	 */
	public void setReturnMoney(BigDecimal returnMoney) {
		this.returnMoney = returnMoney;
	}

	/**
	 * @return the timeStamp
	 */
	public Timestamp getTimeStamp() {
		return timeStamp;
	}

	/**
	 * @param timeStamp
	 *            the timeStamp to set
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

    /**
     * @return the moneyTax
     */
    public BigDecimal getMoneyTax() {
        return moneyTax;
    }

    /**
     * @param moneyTax the moneyTax to set
     */
    public void setMoneyTax(BigDecimal moneyTax) {
        this.moneyTax = moneyTax;
    }

    /**
     * @return the returnMoneyTax
     */
    public BigDecimal getReturnMoneyTax() {
        return returnMoneyTax;
    }

    /**
     * @param returnMoneyTax the returnMoneyTax to set
     */
    public void setReturnMoneyTax(BigDecimal returnMoneyTax) {
        this.returnMoneyTax = returnMoneyTax;
    }

}
