/**
 * ClassName  AreaInfo
 *
 * History
 * Create User: Administrator
 * Create Date: 2009-12-16
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

import java.io.Serializable;

/**
 * @author ss
 * 
 */
public class SellContractDetailInfoDto implements Serializable {

	/**
	 * 销售明细
	 */
	private static final long serialVersionUID = 7946901168153686412L;

	private Integer id;

	private String sell_contract_id;// 合同Id

	private Integer product_id;// 产品Id

	private Integer sell_count;// 销售数量

	private String buy_price;// 预计采购价

	private String sell_price;// 销售单价

	private String productname;// 产品名称

	private String producttype;// 产品规格型号

	private String productnuit;// 产品单位

	private String sellmoney;// 销售金额

	private String productmoney;// 产品库存金额

	private Integer canuse;// 销售可用数

	private Integer stockcanuse;// 库存表中可用数

	private String contractMoney;// 销售合同金额

	private String totalRate;// 总利率

	private String margin;// 毛利率

	public String getMargin() {
		return margin;
	}

	public void setMargin(String margin) {
		this.margin = margin;
	}

	public String getTotalRate() {
		return totalRate;
	}

	public void setTotalRate(String totalRate) {
		this.totalRate = totalRate;
	}

	public Integer getStockcanuse() {
		return stockcanuse;
	}

	public void setStockcanuse(Integer stockcanuse) {
		this.stockcanuse = stockcanuse;
	}

	public Integer getCanuse() {
		return canuse;
	}

	public void setCanuse(Integer canuse) {
		this.canuse = canuse;
	}

	public String getProductmoney() {
		return productmoney;
	}

	public void setProductmoney(String productmoney) {
		this.productmoney = productmoney;
	}

	public String getSellmoney() {
		return sellmoney;
	}

	public void setSellmoney(String sellmoney) {
		this.sellmoney = sellmoney;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getProductnuit() {
		return productnuit;
	}

	public void setProductnuit(String productnuit) {
		this.productnuit = productnuit;
	}

	public String getProducttype() {
		return producttype;
	}

	public void setProducttype(String producttype) {
		this.producttype = producttype;
	}

	public String getBuy_price() {
		return buy_price;
	}

	public void setBuy_price(String buy_price) {
		this.buy_price = buy_price;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}

	public String getSell_contract_id() {
		return sell_contract_id;
	}

	public void setSell_contract_id(String sell_contract_id) {
		this.sell_contract_id = sell_contract_id;
	}

	public Integer getSell_count() {
		return sell_count;
	}

	public void setSell_count(Integer sell_count) {
		this.sell_count = sell_count;
	}

	public String getSell_price() {
		return sell_price;
	}

	public void setSell_price(String sell_price) {
		this.sell_price = sell_price;
	}

	public String getContractMoney() {
		return contractMoney;
	}

	public void setContractMoney(String contractMoney) {
		this.contractMoney = contractMoney;
	}

}
