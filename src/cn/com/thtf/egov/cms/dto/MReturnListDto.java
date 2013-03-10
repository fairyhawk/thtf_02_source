/**
 * ClassName  MReturnListDto
 *
 * History
 * Create User: balance
 * Create Date: 2010-5-18
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @author balance
 */

public class MReturnListDto implements Serializable {

    /**
     * 自动序列化
     */
    private static final long serialVersionUID = -7142441005116277681L;
    /** 回款流水号 **/
    private String id;
    /** 回款编号 **/
    private String no;
    /** 产品分类ID **/
    private String ptoductTypeId;
    /** 产品分类名称 **/
    private String productTyepName;
    /** 客户ID **/
    private String customerId;
    /** 客户名称 **/
    private String customerName;
    /** 回款日期 **/
    private String returnDate;
    /** 回款金额 **/
    private String money;
    /** 指定金额 **/
    private String pointMoney;
    /** 合同预收金额 **/
    private String pointContractMoney;
    /** 产品分类预收金额 **/
    private String pointProductTypeMoney;
    /** 回款方式 **/
    private String returnWay;
    /** 用户ID **/
    private String userId;
    /** 用户名称 **/
    private String userName;
    /** 退款金额 **/
    private BigDecimal backMoney;
    /** 回款类型 **/
    private String retrunType;
    /** 录入日期 **/
    private String date;
    /** 凭证号 **/
    private String number;
    /** 退款状态 **/
    private String status;
    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
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
     * @return the productTyepName
     */
    public String getProductTyepName() {
        return productTyepName;
    }

    /**
     * @param productTyepName
     *            the productTyepName to set
     */
    public void setProductTyepName(String productTyepName) {
        this.productTyepName = productTyepName;
    }

    /**
     * @return the ptoductTypeId
     */
    public String getPtoductTypeId() {
        return ptoductTypeId;
    }

    /**
     * @param ptoductTypeId the ptoductTypeId to set
     */
    public void setPtoductTypeId(String ptoductTypeId) {
        this.ptoductTypeId = ptoductTypeId;
    }

    /**
     * @return the customerId
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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
     * @return the money
     */
    public String getMoney() {
        return money;
    }

    /**
     * @param money
     *            the money to set
     */
    public void setMoney(String money) {
        this.money = money;
    }

    /**
     * @return the pointMoney
     */
    public String getPointMoney() {
        return pointMoney;
    }

    /**
     * @param pointMoney
     *            the pointMoney to set
     */
    public void setPointMoney(String pointMoney) {
        this.pointMoney = pointMoney;
    }

    /**
     * @return the pointContractMoney
     */
    public String getPointContractMoney() {
        return pointContractMoney;
    }

    /**
     * @param pointContractMoney
     *            the pointContractMoney to set
     */
    public void setPointContractMoney(String pointContractMoney) {
        this.pointContractMoney = pointContractMoney;
    }

    /**
     * @return the pointProductTypeMoney
     */
    public String getPointProductTypeMoney() {
        return pointProductTypeMoney;
    }

    /**
     * @param pointProductTypeMoney
     *            the pointProductTypeMoney to set
     */
    public void setPointProductTypeMoney(String pointProductTypeMoney) {
        this.pointProductTypeMoney = pointProductTypeMoney;
    }

    /**
     * @return the returnWay
     */
    public String getReturnWay() {
        return returnWay;
    }

    /**
     * @param returnWay
     *            the returnWay to set
     */
    public void setReturnWay(String returnWay) {
        this.returnWay = returnWay;
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
     * @return the retrunType
     */
    public String getRetrunType() {
        return retrunType;
    }

    /**
     * @param retrunType
     *            the retrunType to set
     */
    public void setRetrunType(String retrunType) {
        this.retrunType = retrunType;
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
}
