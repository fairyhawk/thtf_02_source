/**
 * ClassName  SampleOutListDto
 *
 * History
 * Create User: Administrator
 * Create Date: 2010-7-5
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

import java.math.BigDecimal;

/**
 * 
 * @author zhangzx
 * 
 */

public class SampleOutListDto {

    private String id;// 借出单号

    private Integer productTypeId;// 产品分类编号

    private String productTypeName;//产品分类名称

    private Integer type;// 借出单类型

    private String date;// 申请日期

    private String inDate;// 预计归还日期

    private Integer companyType;// 借出单位类型

    private Integer companyId;// 借出单位编号
    
    private String companyName;//借出单位名称

    private String custosName;// 保管人姓名

    private Integer outStockroomId;// 库房编号
    
    private String outStockroomName;//库房名称

    private Integer addressId;// 发货地址编号

    private String requestDate;// 要求发货日期

    private String sendDate;// 发货日期

    private Integer transportWay;// 货运方式

    private String takeName;// 提货人姓名

    private String takeNumber;// 提货人身份证号

    private BigDecimal sampleOutMoney;// 借出金额
    
    private BigDecimal sampleOutMoneyTax;// 借出金额(含税)

    private String userId;// 登录名

    private String userName;// 人员名称

    private String text;// 特殊说明

    private Integer sampleOutStatus;// 借出单状态
    
    private Integer sampleInStatus;//归还单状态
    
    private String sampleInSumMoney;//归还金额
    
    private String sampleInSumMoneyTax;//归还金额(含税)
    
    private Integer recount;//剩余可归还产品数量
 

	/**
	 * @return the recount
	 */
	public Integer getRecount() {
		return recount;
	}

	/**
	 * @param recount the recount to set
	 */
	public void setRecount(Integer recount) {
		this.recount = recount;
	}

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
     * @return the productTypeName
     */
    public String getProductTypeName() {
        return productTypeName;
    }

    /**
     * @param productTypeName the productTypeName to set
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
     * @param type the type to set
     */
    public void setType(Integer type) {
        this.type = type;
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
     * @return the inDate
     */
    public String getInDate() {
        return inDate;
    }

    /**
     * @param inDate the inDate to set
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
     * @param companyType the companyType to set
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
     * @param companyId the companyId to set
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
     * @param custosName the custosName to set
     */
    public void setCustosName(String custosName) {
        this.custosName = custosName;
    }

    /**
     * @return the outStockroomId
     */
    public Integer getOutStockroomId() {
        return outStockroomId;
    }

    /**
     * @param outStockroomId the outStockroomId to set
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
     * @param outStockroomName the outStockroomName to set
     */
    public void setOutStockroomName(String outStockroomName) {
        this.outStockroomName = outStockroomName;
    }

    /**
     * @return the addressId
     */
    public Integer getAddressId() {
        return addressId;
    }

    /**
     * @param addressId the addressId to set
     */
    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
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
     * @return the sampleInStatus
     */
    public Integer getSampleInStatus() {
        return sampleInStatus;
    }

    /**
     * @param sampleInStatus the sampleInStatus to set
     */
    public void setSampleInStatus(Integer sampleInStatus) {
        this.sampleInStatus = sampleInStatus;
    }

    /**
     * @return the sampleInSumMoney
     */
    public String getSampleInSumMoney() {
        return sampleInSumMoney;
    }

    /**
     * @param sampleInSumMoney the sampleInSumMoney to set
     */
    public void setSampleInSumMoney(String sampleInSumMoney) {
        this.sampleInSumMoney = sampleInSumMoney;
    }

    /**
     * @return the sampleOutMoney
     */
    public BigDecimal getSampleOutMoney() {
        return sampleOutMoney;
    }

    /**
     * @param sampleOutMoney the sampleOutMoney to set
     */
    public void setSampleOutMoney(BigDecimal sampleOutMoney) {
        this.sampleOutMoney = sampleOutMoney;
    }

    /**
     * @return the sampleOutStatus
     */
    public Integer getSampleOutStatus() {
        return sampleOutStatus;
    }

    /**
     * @param sampleOutStatus the sampleOutStatus to set
     */
    public void setSampleOutStatus(Integer sampleOutStatus) {
        this.sampleOutStatus = sampleOutStatus;
    }

    /**
     * @return the sampleOutMoneyTax
     */
    public BigDecimal getSampleOutMoneyTax() {
        return sampleOutMoneyTax;
    }

    /**
     * @param sampleOutMoneyTax the sampleOutMoneyTax to set
     */
    public void setSampleOutMoneyTax(BigDecimal sampleOutMoneyTax) {
        this.sampleOutMoneyTax = sampleOutMoneyTax;
    }

    /**
     * @return the sampleInSumMoneyTax
     */
    public String getSampleInSumMoneyTax() {
        return sampleInSumMoneyTax;
    }

    /**
     * @param sampleInSumMoneyTax the sampleInSumMoneyTax to set
     */
    public void setSampleInSumMoneyTax(String sampleInSumMoneyTax) {
        this.sampleInSumMoneyTax = sampleInSumMoneyTax;
    }

}
