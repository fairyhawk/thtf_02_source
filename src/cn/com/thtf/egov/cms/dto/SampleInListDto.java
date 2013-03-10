/**
 * ClassName  SampleInListDto
 *
 * History
 * Create User: Administrator
 * Create Date: 2010-7-12
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

import java.math.BigDecimal;

/**
 * 
 * @author zhangzx
 */

public class SampleInListDto {

    private String sampleInId; // 归还单号

    private String date;// 申请日期

    private String sampleOutId;// 借出单号

    private Integer inStockroomId;// 库房编号

    private String inStockroomName;// 库房名称

    private Integer productTypeId;// 产品分类id

    private String productTypeName;// 产品分类名称

    private String companyName;// 借出单位名称

    private Integer stockroomAddressId;// 库房地址

    private String userId;// 申请人id

    private String userName;// 申请人姓名

    private BigDecimal money;// 归还金额
    
    private BigDecimal moneyTax;// 归还金额(含税)

    private Integer status;// 归还单状态

    private String inDate;// 预计归还日期

    private String stkAdmDate;// 入库日期（库房管理员评审日期）

    /**
     * @return the sampleInId
     */
    public String getSampleInId() {
        return sampleInId;
    }

    /**
     * @param sampleInId
     *            the sampleInId to set
     */
    public void setSampleInId(String sampleInId) {
        this.sampleInId = sampleInId;
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
     * @return the companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName
     *            the companyName to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

}
