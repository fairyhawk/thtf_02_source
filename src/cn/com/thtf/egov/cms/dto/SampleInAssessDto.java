/**
 * ClassName  SampleInAssessDto
 *
 * History
 * Create User: jiangmingxing
 * Create Date: 2010-7-12
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

import java.math.BigDecimal;

/**
 * 归还评审dto
 * 
 * @author jiangmingxing
 */

public class SampleInAssessDto {

	private Integer productTypeId; // 产品分类编号
	private String productTypeName; // 产品分类名称
	private Integer type; // 借出单类型
	private String inDate; // 预计归还日期
	private Integer companyType; // 借出单位类型
	private Integer companyId; // 借出单位编号
	private String companyName;      //借出单位
	private String custosName; // 保管人
	private String sendDate; // 发货日期
	private String id; // 归还单号
	private String date; // 申请日期
	private String sampleOutId; // 借出单号
	private Integer inStockroomId; // 库房编号
	private Integer outStockroomId; // 借出库房编号
	private String outStockroomName; // 借出库房名称
	private String inStockroomName; // 归还库房名称
	private Integer stockroomAddressId; // 库房收货地址编号
	private String goodsReceiveUnitName; // 货物接收单位名称
	private String linkman; // 联系人
	private String address; // 收货地址
	private String postcode; // 邮编
	private String mobile; // 手机
	private String tel; // 电话
	private String userId; // 登录名
	private String userName; // 人员名称
	private String text; // 特殊说明
	private BigDecimal moneyTotal; // 归还金额
	private BigDecimal moneyTotalTax; // 归还金额
	private Integer status; // 归还单状态
	private String buyManId; // 采购主管登录名
	private String buyManName; // 采购主管用户名
	private String buyManDate; // 采购主管评审日期
	private String buyManIdea; // 采购主管评审意见
	private String buyManText; // 采购主管补充说明
	private String stkAdmId; // 库房管理员登录名
	private String stkAdmName; // 库房管理员用户名
	private String stkAdmDate; // 库房管理员评审日期
	private String stkAdmIdea; // 库房管理员评审意见
	private String stkAdmText; // 库房管理员补充说明
	private String roleId;     //权限
	private BigDecimal taxRate;     //税率
	

	/**
	 * @return the roleId
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

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
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * @return the inDate
	 */
	public String getInDate() {
		return inDate;
	}

	/**
	 * @param inDate
	 *            the inDate to set
	 */
	public void setInDate(String inDate) {
		this.inDate = inDate;
	}

	/**
	 * @return the companyType
	 */
	public Integer getCompanyType() {
		return companyType;
	}

	/**
	 * @param companyType
	 *            the companyType to set
	 */
	public void setCompanyType(Integer companyType) {
		this.companyType = companyType;
	}

	/**
	 * @return the companyId
	 */
	public Integer getCompanyId() {
		return companyId;
	}

	/**
	 * @param companyId
	 *            the companyId to set
	 */
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the custosName
	 */
	public String getCustosName() {
		return custosName;
	}

	/**
	 * @param custosName
	 *            the custosName to set
	 */
	public void setCustosName(String custosName) {
		this.custosName = custosName;
	}

	/**
	 * @return the sendDate
	 */
	public String getSendDate() {
		return sendDate;
	}

	/**
	 * @param sendDate
	 *            the sendDate to set
	 */
	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
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

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the sampleOutId
	 */
	public String getSampleOutId() {
		return sampleOutId;
	}

	/**
	 * @param sampleOutId
	 *            the sampleOutId to set
	 */
	public void setSampleOutId(String sampleOutId) {
		this.sampleOutId = sampleOutId;
	}

	/**
	 * @return the inStockroomId
	 */
	public Integer getInStockroomId() {
		return inStockroomId;
	}

	/**
	 * @param inStockroomId
	 *            the inStockroomId to set
	 */
	public void setInStockroomId(Integer inStockroomId) {
		this.inStockroomId = inStockroomId;
	}

	/**
	 * @return the outStockroomId
	 */
	public Integer getOutStockroomId() {
		return outStockroomId;
	}

	/**
	 * @param outStockroomId
	 *            the outStockroomId to set
	 */
	public void setOutStockroomId(Integer outStockroomId) {
		this.outStockroomId = outStockroomId;
	}

	/**
	 * @return the outStockroomName
	 */
	public String getOutStockroomName() {
		return outStockroomName;
	}

	/**
	 * @param outStockroomName
	 *            the outStockroomName to set
	 */
	public void setOutStockroomName(String outStockroomName) {
		this.outStockroomName = outStockroomName;
	}

	/**
	 * @return the inStockroomName
	 */
	public String getInStockroomName() {
		return inStockroomName;
	}

	/**
	 * @param inStockroomName
	 *            the inStockroomName to set
	 */
	public void setInStockroomName(String inStockroomName) {
		this.inStockroomName = inStockroomName;
	}

	/**
	 * @return the stockroomAddressId
	 */
	public Integer getStockroomAddressId() {
		return stockroomAddressId;
	}

	/**
	 * @param stockroomAddressId
	 *            the stockroomAddressId to set
	 */
	public void setStockroomAddressId(Integer stockroomAddressId) {
		this.stockroomAddressId = stockroomAddressId;
	}

	/**
	 * @return the goodsReceiveUnitName
	 */
	public String getGoodsReceiveUnitName() {
		return goodsReceiveUnitName;
	}

	/**
	 * @param goodsReceiveUnitName
	 *            the goodsReceiveUnitName to set
	 */
	public void setGoodsReceiveUnitName(String goodsReceiveUnitName) {
		this.goodsReceiveUnitName = goodsReceiveUnitName;
	}

	/**
	 * @return the linkman
	 */
	public String getLinkman() {
		return linkman;
	}

	/**
	 * @param linkman
	 *            the linkman to set
	 */
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the postcode
	 */
	public String getPostcode() {
		return postcode;
	}

	/**
	 * @param postcode
	 *            the postcode to set
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile
	 *            the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the tel
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * @param tel
	 *            the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
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
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the moneyTotal
	 */
	public BigDecimal getMoneyTotal() {
		return moneyTotal;
	}

	/**
	 * @param moneyTotal the moneyTotal to set
	 */
	public void setMoneyTotal(BigDecimal moneyTotal) {
		this.moneyTotal = moneyTotal;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the buyManId
	 */
	public String getBuyManId() {
		return buyManId;
	}

	/**
	 * @param buyManId
	 *            the buyManId to set
	 */
	public void setBuyManId(String buyManId) {
		this.buyManId = buyManId;
	}

	/**
	 * @return the buyManName
	 */
	public String getBuyManName() {
		return buyManName;
	}

	/**
	 * @param buyManName
	 *            the buyManName to set
	 */
	public void setBuyManName(String buyManName) {
		this.buyManName = buyManName;
	}

	/**
	 * @return the buyManDate
	 */
	public String getBuyManDate() {
		return buyManDate;
	}

	/**
	 * @param buyManDate
	 *            the buyManDate to set
	 */
	public void setBuyManDate(String buyManDate) {
		this.buyManDate = buyManDate;
	}

	/**
	 * @return the buyManIdea
	 */
	public String getBuyManIdea() {
		return buyManIdea;
	}

	/**
	 * @param buyManIdea
	 *            the buyManIdea to set
	 */
	public void setBuyManIdea(String buyManIdea) {
		this.buyManIdea = buyManIdea;
	}

	/**
	 * @return the buyManText
	 */
	public String getBuyManText() {
		return buyManText;
	}

	/**
	 * @param buyManText
	 *            the buyManText to set
	 */
	public void setBuyManText(String buyManText) {
		this.buyManText = buyManText;
	}

	/**
	 * @return the stkAdmId
	 */
	public String getStkAdmId() {
		return stkAdmId;
	}

	/**
	 * @param stkAdmId
	 *            the stkAdmId to set
	 */
	public void setStkAdmId(String stkAdmId) {
		this.stkAdmId = stkAdmId;
	}

	/**
	 * @return the stkAdmName
	 */
	public String getStkAdmName() {
		return stkAdmName;
	}

	/**
	 * @param stkAdmName
	 *            the stkAdmName to set
	 */
	public void setStkAdmName(String stkAdmName) {
		this.stkAdmName = stkAdmName;
	}

	/**
	 * @return the stkAdmDate
	 */
	public String getStkAdmDate() {
		return stkAdmDate;
	}

	/**
	 * @param stkAdmDate
	 *            the stkAdmDate to set
	 */
	public void setStkAdmDate(String stkAdmDate) {
		this.stkAdmDate = stkAdmDate;
	}

	/**
	 * @return the stkAdmIdea
	 */
	public String getStkAdmIdea() {
		return stkAdmIdea;
	}

	/**
	 * @param stkAdmIdea
	 *            the stkAdmIdea to set
	 */
	public void setStkAdmIdea(String stkAdmIdea) {
		this.stkAdmIdea = stkAdmIdea;
	}

	/**
	 * @return the stkAdmText
	 */
	public String getStkAdmText() {
		return stkAdmText;
	}

	/**
	 * @param stkAdmText
	 *            the stkAdmText to set
	 */
	public void setStkAdmText(String stkAdmText) {
		this.stkAdmText = stkAdmText;
	}

    /**
     * @return the moneyTotalTax
     */
    public BigDecimal getMoneyTotalTax() {
        return moneyTotalTax;
    }

    /**
     * @param moneyTotalTax the moneyTotalTax to set
     */
    public void setMoneyTotalTax(BigDecimal moneyTotalTax) {
        this.moneyTotalTax = moneyTotalTax;
    }

    /**
     * @return the taxRate
     */
    public BigDecimal getTaxRate() {
        return taxRate;
    }

    /**
     * @param taxRate the taxRate to set
     */
    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

}
