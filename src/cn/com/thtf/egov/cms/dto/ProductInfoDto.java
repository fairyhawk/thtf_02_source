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
 * @author Administrator 产品信息
 */
public class ProductInfoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7946901168153686412L;

	private Integer id;// 产品ID

	private String name;// 产品名称

	private String no;// 产品编号

	private Integer brand_id;// 品牌编码

	private String type;// 规格型号

	private String unit;// 单位

	private String product_series_id;// 所属系列编码

	private String product_type_id;

	private float price;// 单价

	private Integer buy;// 采购合同数

	private Integer num;// 实际库存总输

	private Integer sell;// 销售合同书

	private Integer sellok;// 销售可用数

	private String money;// 库存金额

	private String limitPrice;// 限价

	private String tname;

	private String sname;

	private String productcode;

	private String bname;

	private Integer tid;

	private String tno;

	private String tIdNo;// 分类Id+No

	private String sIdNo;// 系列Id+No

	private String sid;

	private String sno;

	private String dname;

	// 库存
	private Integer stockid;

	private String stockproduct_id;

	private Integer stocknum;

	private Integer stocklock;

	private Integer stockprepared;

	private Integer stocklack;

	private Integer sbs;
	// jiewei

	private String user_id;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getTno() {
		return tno;
	}

	public void setTno(String tno) {
		this.tno = tno;
	}

	public Integer getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(Integer brand_id) {
		this.brand_id = brand_id;
	}

	public Integer getBuy() {
		return buy;
	}

	public void setBuy(Integer buy) {
		this.buy = buy;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getProduct_series_id() {
		return product_series_id;
	}

	public void setProduct_series_id(String product_series_id) {
		this.product_series_id = product_series_id;
	}

	public Integer getSell() {
		return sell;
	}

	public void setSell(Integer sell) {
		this.sell = sell;
	}

	public Integer getSellok() {
		return sellok;
	}

	public void setSellok(Integer sellok) {
		this.sellok = sellok;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public String getProductcode() {
		return productcode;
	}

	public void setProductcode(String productcode) {
		this.productcode = productcode;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public String getSIdNo() {
		return sIdNo;
	}

	public void setSIdNo(String idNo) {
		sIdNo = idNo;
	}

	public String getTIdNo() {
		return tIdNo;
	}

	public void setTIdNo(String idNo) {
		tIdNo = idNo;
	}

	public String getProduct_type_id() {
		return product_type_id;
	}

	public void setProduct_type_id(String product_type_id) {
		this.product_type_id = product_type_id;
	}

	public Integer getStockid() {
		return stockid;
	}

	public void setStockid(Integer stockid) {
		this.stockid = stockid;
	}

	public Integer getStocklack() {
		return stocklack;
	}

	public void setStocklack(Integer stocklack) {
		this.stocklack = stocklack;
	}

	public Integer getStocklock() {
		return stocklock;
	}

	public void setStocklock(Integer stocklock) {
		this.stocklock = stocklock;
	}

	public Integer getStocknum() {
		return stocknum;
	}

	public void setStocknum(Integer stocknum) {
		this.stocknum = stocknum;
	}

	public Integer getStockprepared() {
		return stockprepared;
	}

	public void setStockprepared(Integer stockprepared) {
		this.stockprepared = stockprepared;
	}

	public String getStockproduct_id() {
		return stockproduct_id;
	}

	public void setStockproduct_id(String stockproduct_id) {
		this.stockproduct_id = stockproduct_id;
	}

	public Integer getSbs() {
		return sbs;
	}

	public void setSbs(Integer sbs) {
		this.sbs = sbs;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getLimitPrice() {
		return limitPrice;
	}

	public void setLimitPrice(String limitPrice) {
		this.limitPrice = limitPrice;
	}

}
