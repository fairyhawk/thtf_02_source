/**
 * ClassName  MreturnDto
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-5-6
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * MreturnDto
 * 
 * @author Lubo
 * 
 */
public class MreturnDto {

    /** 回款流水号 */
    private String id;
    /** 回款编号 */
    private String no;
    /** 回款类型 */
    private String returnType;
    /** 产品分类编号 */
    private String productTypeId;
    /** 产品分类名称 */
    private String productName;

    /** 客户编号 */
    private String customerId;
    /** 客户名称 */
    private String customerName;
    /** 录入名称 */
    private String date;
    /** 回款日期 */
    private String returnDate;
    /** 回款方式 */
    private Integer returnWay;
    
    /** 凭证号 */
    private String number;
    /** 回款金额 */
    private BigDecimal money;
    /** 登录名 */
    private String userId;
    /** 人员名称 */
    private String userName;
    /** 人员区域编号 */
    private Integer userAreaId;
    
    /** 特殊说明 */
    private String text;
    /** 时间戳 */
    private Timestamp timeStamp;
    /** 退货金额 */
    private BigDecimal backMoney;

    /** 回款明细编号 */
    private String detailId;
    /** 指定类型 */
    private String appointType;
    /** 合同流水号 */
    private String sellContractId;
    /** 发货单号 */
    private String sendGoodsId;
    /** 指定金额 */
    private String appointMoney;
    /** 产品编号 */
    private String productId;
    
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
     * @return the customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName
     *            the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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
     * @return the returnDate
     */
    public String getReturnDate() {
        return returnDate;
    }

    /**
     * @param returnDate
     *            the returnDate to set
     */
    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    /**
     * @return the returnWay
     */
    public Integer getReturnWay() {
        return returnWay;
    }

    /**
     * @param returnWay
     *            the returnWay to set
     */
    public void setReturnWay(Integer returnWay) {
        this.returnWay = returnWay;
    }

    /**
     * @return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param number
     *            the number to set
     */
    public void setNumber(String number) {
        this.number = number;
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
     * @return the userAreaId
     */
    public Integer getUserAreaId() {
        return userAreaId;
    }

    /**
     * @param userAreaId
     *            the userAreaId to set
     */
    public void setUserAreaId(Integer userAreaId) {
        this.userAreaId = userAreaId;
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
     * @return the backMoney
     */
    public BigDecimal getBackMoney() {
        return backMoney;
    }

    /**
     * @param backMoney
     *            the backMoney to set
     */
    public void setBackMoney(BigDecimal backMoney) {
        this.backMoney = backMoney;
    }

    /**
     * @return the sendGoodsId
     */
    public String getSendGoodsId() {
        return sendGoodsId;
    }

    /**
     * @param sendGoodsId
     *            the sendGoodsId to set
     */
    public void setSendGoodsId(String sendGoodsId) {
        this.sendGoodsId = sendGoodsId;
    }

    /**
     * @return the productId
     */
    public String getProductId() {
        return productId;
    }

    /**
     * @param productId
     *            the productId to set
     */
    public void setProductId(String productId) {
        this.productId = productId;
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
     * @return the no
     */
    public String getNo() {
        return no;
    }

    /**
     * @param no
     *            the no to set
     */
    public void setNo(String no) {
        this.no = no;
    }

    /**
     * @return the returnType
     */
    public String getReturnType() {
        return returnType;
    }

    /**
     * @param returnType
     *            the returnType to set
     */
    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    /**
     * @return the productTypeId
     */
    public String getProductTypeId() {
        return productTypeId;
    }

    /**
     * @param productTypeId
     *            the productTypeId to set
     */
    public void setProductTypeId(String productTypeId) {
        this.productTypeId = productTypeId;
    }

    /**
     * @return the customerId
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId
     *            the customerId to set
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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
     * @return the detailId
     */
    public String getDetailId() {
        return detailId;
    }

    /**
     * @param detailId
     *            the detailId to set
     */
    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    /**
     * @return the appointType
     */
    public String getAppointType() {
        return appointType;
    }

    /**
     * @param appointType
     *            the appointType to set
     */
    public void setAppointType(String appointType) {
        this.appointType = appointType;
    }

    /**
     * @return the sellContractId
     */
    public String getSellContractId() {
        return sellContractId;
    }

    /**
     * @param sellContractId
     *            the sellContractId to set
     */
    public void setSellContractId(String sellContractId) {
        this.sellContractId = sellContractId;
    }

    /**
     * @return the appointMoney
     */
    public String getAppointMoney() {
        return appointMoney;
    }

    /**
     * @param appointMoney
     *            the appointMoney to set
     */
    public void setAppointMoney(String appointMoney) {
        this.appointMoney = appointMoney;
    }
}
