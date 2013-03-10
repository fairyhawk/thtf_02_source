/**
 * ClassName  MReturnQueryDto
 *
 * History
 * Create User: balance
 * Create Date: 2010-5-19
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

import java.io.Serializable;

/**
 * 
 * @author balance
 */

public class MReturnListQueryDto implements Serializable {

    /**
     * 自动序列化
     */
    private static final long serialVersionUID = 7366873673732756564L;
    
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
    /** 回款金额 **/
    private String money;
    /** 回款方式 **/
    private String returnWay;
    /** 用户ID **/
    private String userId;
    /** 用户名称 **/
    private String userName;
    /** 凭证号 **/
    private String number;
    /** 录入起始日期 **/
    private String startDate;
    /** 录入终止日期 **/
    private String endDate;
    /** 回款起始日期 **/
    private String startReturnDate;
    /** 回款起始日期 **/
    private String endReturnDate;
    /**回款类型**/
        /*回款*/
    private String returnType0;
        /*在途款*/
    private String returnType1;
        /*在途款到账*/
    private String returnType2;
    /** 产品合同号**/
    private String productContractCode;
    /** 发货单号 **/
    private String sendGoodId;
    /**权限控制**/
        /*当前登陆用户*/
    private String nowUserId;
        /*当前登陆用户权限*/
    private Integer userRole;
        /*当前登陆用户区域ID*/
    private Integer nowUserAreaId;
    public Integer getNowUserAreaId() {
        return nowUserAreaId;
    }
    public void setNowUserAreaId(Integer nowUserAreaId) {
        this.nowUserAreaId = nowUserAreaId;
    }
    /**
     * @return the nowUserId
     */
    public String getNowUserId() {
        return nowUserId;
    }
    /**
     * @param nowUserId the nowUserId to set
     */
    public void setNowUserId(String nowUserId) {
        this.nowUserId = nowUserId;
    }
    /**
     * @return the userRole
     */
    public Integer getUserRole() {
        return userRole;
    }
    /**
     * @param userRole the userRole to set
     */
    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
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
     * @return the no
     */
    public String getNo() {
        return no;
    }
    /**
     * @param no the no to set
     */
    public void setNo(String no) {
        this.no = no;
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
     * @return the productTyepName
     */
    public String getProductTyepName() {
        return productTyepName;
    }
    /**
     * @param productTyepName the productTyepName to set
     */
    public void setProductTyepName(String productTyepName) {
        this.productTyepName = productTyepName;
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
     * @param customerName the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    /**
     * @return the money
     */
    public String getMoney() {
        return money;
    }
    /**
     * @param money the money to set
     */
    public void setMoney(String money) {
        this.money = money;
    }
    /**
     * @return the returnWay
     */
    public String getReturnWay() {
        return returnWay;
    }
    /**
     * @param returnWay the returnWay to set
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
     * @return the number
     */
    public String getNumber() {
        return number;
    }
    /**
     * @param number the number to set
     */
    public void setNumber(String number) {
        this.number = number;
    }
    /**
     * @return the startDate
     */
    public String getStartDate() {
        return startDate;
    }
    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    /**
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }
    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    /**
     * @return the startReturnDate
     */
    public String getStartReturnDate() {
        return startReturnDate;
    }
    /**
     * @param startReturnDate the startReturnDate to set
     */
    public void setStartReturnDate(String startReturnDate) {
        this.startReturnDate = startReturnDate;
    }
    /**
     * @return the endReturnDate
     */
    public String getEndReturnDate() {
        return endReturnDate;
    }
    /**
     * @param endReturnDate the endReturnDate to set
     */
    public void setEndReturnDate(String endReturnDate) {
        this.endReturnDate = endReturnDate;
    }
    /**
     * @return the returnType0
     */
    public String getReturnType0() {
        return returnType0;
    }
    /**
     * @param returnType0 the returnType0 to set
     */
    public void setReturnType0(String returnType0) {
        this.returnType0 = returnType0;
    }
    /**
     * @return the returnType1
     */
    public String getReturnType1() {
        return returnType1;
    }
    /**
     * @param returnType1 the returnType1 to set
     */
    public void setReturnType1(String returnType1) {
        this.returnType1 = returnType1;
    }
    /**
     * @return the returnType2
     */
    public String getReturnType2() {
        return returnType2;
    }
    /**
     * @param returnType2 the returnType2 to set
     */
    public void setReturnType2(String returnType2) {
        this.returnType2 = returnType2;
    }
    public String getProductContractCode() {
        return productContractCode;
    }
    public void setProductContractCode(String productContractCode) {
        this.productContractCode = productContractCode;
    }
    public String getSendGoodId() {
        return sendGoodId;
    }
    public void setSendGoodId(String sendGoodId) {
        this.sendGoodId = sendGoodId;
    }
    
}
