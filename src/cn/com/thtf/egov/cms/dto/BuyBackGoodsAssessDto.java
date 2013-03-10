/**
 * ClassName  BuyBackGoodsAssessDto
 *
 * History
 * Create User: jiangmingxing
 * Create Date: 2010-6-11
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

import java.math.BigDecimal;

/**
 * 采购返厂Dto
 * @author jiangmingxing
 */

public class BuyBackGoodsAssessDto {
	
	private String id;            //返厂单号
	
	private Integer productTypeId;   //产品分类编号
	
	private Integer supplierId;     //供货商编号
	
	private String supplierName;    //供货商名称
	
	private String inStockId;      //入库单号
	
	private String date;        //申请日期
 
	private String requestDate;    //要求发货日期
	
	private String sendDate;      //实际返厂日期
	
	private BigDecimal money;     //返厂金额
	
	private Integer status;       //返厂单状态
	
	private Integer stockroomId;    //库房编号
	
	private Integer supplierAddressId;   //供货商发货地址编号
	
	private Integer transportWay;      //货运方式
	
	private String takeName;      //提货人姓名
	
	private String takeNumber;     //提货人身份证号
	
	private String userId;       //登录号
	
	private String userName;     //人员名称
	
	private String text;        //特殊说明
	
	private String proMajId;      //产品总监登录名
	
	private String proMajName;      //产品总监用户名
	
	private String proMajDate;      //产品总监评审日期
	
	private String proMajIdea;      //产品总监评审意见
	
	private String proMajIdea1;      //产品总监评审意见1
	
	private String proMajText;     //产品总监补充说明
	
	private String buyManId;      //采购主管登录名
	
	private String buyManName;     //采购主管用户名
	
	private String buyManDate;     //采购主管评审日期
	
	private String buyManIdea;     //采购主管评审意见
	
	private String buyManText;     //采购主管补充说明
	
	private String opeMajId;      //运营总监登录名
	
	private String opeMajName;     //运营总监用户名
	
	private String opeMajDate;      //运营总监评审日期
	
	private String opeMajIdea;      //运营总监评审意见
	
	private String opeMajText;     //运营总监补充说明
	
	private String stkAdmId;       //库房管理员登录名
	
	private String stkAdmName;      //库房管理员用户名
	
	private String stkAdmDate;     //库房管理员评审日期
	
	private String stkAdmIdea;     //库房管理员评审意见
	
	private String stkAdmText;     //库房管理员补充说明
	
	private String roleId;        //权限

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
	 * @return the productTypeId
	 */
	public Integer getProductTypeId() {
		return productTypeId;
	}

	/**
	 * @param productTypeId the productTypeId to set
	 */
	public void setProductTypeId(Integer productTypeId) {
		this.productTypeId = productTypeId;
	}

	/**
	 * @return the supplierId
	 */
	public Integer getSupplierId() {
		return supplierId;
	}

	/**
	 * @param supplierId the supplierId to set
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
	 * @param supplierName the supplierName to set
	 */
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	/**
	 * @return the inStockId
	 */
	public String getInStockId() {
		return inStockId;
	}

	/**
	 * @param inStockId the inStockId to set
	 */
	public void setInStockId(String inStockId) {
		this.inStockId = inStockId;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the requestDate
	 */
	public String getRequestDate() {
		return requestDate;
	}

	/**
	 * @param requestDate the requestDate to set
	 */
	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}

	/**
	 * @return the sendDate
	 */
	public String getSendDate() {
		return sendDate;
	}

	/**
	 * @param sendDate the sendDate to set
	 */
	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	/**
	 * @return the money
	 */
	public BigDecimal getMoney() {
		return money;
	}

	/**
	 * @param money the money to set
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the stockroomId
	 */
	public Integer getStockroomId() {
		return stockroomId;
	}

	/**
	 * @param stockroomId the stockroomId to set
	 */
	public void setStockroomId(Integer stockroomId) {
		this.stockroomId = stockroomId;
	}

	/**
	 * @return the supplierAddressId
	 */
	public Integer getSupplierAddressId() {
		return supplierAddressId;
	}

	/**
	 * @param supplierAddressId the supplierAddressId to set
	 */
	public void setSupplierAddressId(Integer supplierAddressId) {
		this.supplierAddressId = supplierAddressId;
	}

	/**
	 * @return the transportWay
	 */
	public Integer getTransportWay() {
		return transportWay;
	}

	/**
	 * @param transportWay the transportWay to set
	 */
	public void setTransportWay(Integer transportWay) {
		this.transportWay = transportWay;
	}

	/**
	 * @return the takeName
	 */
	public String getTakeName() {
		return takeName;
	}

	/**
	 * @param takeName the takeName to set
	 */
	public void setTakeName(String takeName) {
		this.takeName = takeName;
	}

	/**
	 * @return the takeNumber
	 */
	public String getTakeNumber() {
		return takeNumber;
	}

	/**
	 * @param takeNumber the takeNumber to set
	 */
	public void setTakeNumber(String takeNumber) {
		this.takeNumber = takeNumber;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
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
	 * @param userName the userName to set
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
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the proMajId
	 */
	public String getProMajId() {
		return proMajId;
	}

	/**
	 * @param proMajId the proMajId to set
	 */
	public void setProMajId(String proMajId) {
		this.proMajId = proMajId;
	}

	/**
	 * @return the proMajName
	 */
	public String getProMajName() {
		return proMajName;
	}

	/**
	 * @param proMajName the proMajName to set
	 */
	public void setProMajName(String proMajName) {
		this.proMajName = proMajName;
	}

	/**
	 * @return the proMajDate
	 */
	public String getProMajDate() {
		return proMajDate;
	}

	/**
	 * @param proMajDate the proMajDate to set
	 */
	public void setProMajDate(String proMajDate) {
		this.proMajDate = proMajDate;
	}

	/**
	 * @return the proMajIdea
	 */
	public String getProMajIdea() {
		return proMajIdea;
	}

	/**
	 * @param proMajIdea the proMajIdea to set
	 */
	public void setProMajIdea(String proMajIdea) {
		this.proMajIdea = proMajIdea;
	}

	/**
	 * @return the proMajIdea1
	 */
	public String getProMajIdea1() {
		return proMajIdea1;
	}

	/**
	 * @param proMajIdea1 the proMajIdea1 to set
	 */
	public void setProMajIdea1(String proMajIdea1) {
		this.proMajIdea1 = proMajIdea1;
	}

	/**
	 * @return the proMajText
	 */
	public String getProMajText() {
		return proMajText;
	}

	/**
	 * @param proMajText the proMajText to set
	 */
	public void setProMajText(String proMajText) {
		this.proMajText = proMajText;
	}

	/**
	 * @return the buyManId
	 */
	public String getBuyManId() {
		return buyManId;
	}

	/**
	 * @param buyManId the buyManId to set
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
	 * @param buyManName the buyManName to set
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
	 * @param buyManDate the buyManDate to set
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
	 * @param buyManIdea the buyManIdea to set
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
	 * @param buyManText the buyManText to set
	 */
	public void setBuyManText(String buyManText) {
		this.buyManText = buyManText;
	}

	/**
	 * @return the opeMajId
	 */
	public String getOpeMajId() {
		return opeMajId;
	}

	/**
	 * @param opeMajId the opeMajId to set
	 */
	public void setOpeMajId(String opeMajId) {
		this.opeMajId = opeMajId;
	}

	/**
	 * @return the opeMajName
	 */
	public String getOpeMajName() {
		return opeMajName;
	}

	/**
	 * @param opeMajName the opeMajName to set
	 */
	public void setOpeMajName(String opeMajName) {
		this.opeMajName = opeMajName;
	}

	/**
	 * @return the opeMajDate
	 */
	public String getOpeMajDate() {
		return opeMajDate;
	}

	/**
	 * @param opeMajDate the opeMajDate to set
	 */
	public void setOpeMajDate(String opeMajDate) {
		this.opeMajDate = opeMajDate;
	}

	/**
	 * @return the opeMajIdea
	 */
	public String getOpeMajIdea() {
		return opeMajIdea;
	}

	/**
	 * @param opeMajIdea the opeMajIdea to set
	 */
	public void setOpeMajIdea(String opeMajIdea) {
		this.opeMajIdea = opeMajIdea;
	}

	/**
	 * @return the opeMajText
	 */
	public String getOpeMajText() {
		return opeMajText;
	}

	/**
	 * @param opeMajText the opeMajText to set
	 */
	public void setOpeMajText(String opeMajText) {
		this.opeMajText = opeMajText;
	}

	/**
	 * @return the stkAdmId
	 */
	public String getStkAdmId() {
		return stkAdmId;
	}

	/**
	 * @param stkAdmId the stkAdmId to set
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
	 * @param stkAdmName the stkAdmName to set
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
	 * @param stkAdmDate the stkAdmDate to set
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
	 * @param stkAdmIdea the stkAdmIdea to set
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
	 * @param stkAdmText the stkAdmText to set
	 */
	public void setStkAdmText(String stkAdmText) {
		this.stkAdmText = stkAdmText;
	}

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

	
	

}
