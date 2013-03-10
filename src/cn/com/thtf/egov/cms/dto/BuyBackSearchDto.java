/**
 * ClassName  BuyBackListDto
 *
 * History
 * Create User: Administrator
 * Create Date: 2010-6-9
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * 采购退款检索DTO
 * 
 * @author zzx
 */

public class BuyBackSearchDto {

    private String id;// 退款单号

    private String paymentId;// 付款单号

    private String no;// 退款编号

    private String productTypeId;// 产品分类编号

    private String productTypeName;// 产品分类名称

    private String supplierId;// 供货商编号

    private String supplierName;// 供货商名称

    private String date;// 录入日期
    
    private String dateStart; // 录入起始日期
    
    private String dateEnd; // 录入终止日期

    private String backDate;// 退款日期
    
    private String backDateStart; //退款起始日期
    
    private String backDateEnd; //退款终止日期

    private String backWay;// 退款方式

    private String number;// 凭证号

    private String money;// 退款金额

    private String userId;// 登录名

    private String userName;// 人员名称

    private String text;// 特殊说明

    private String datetime;// 新建日期
    
    private String loginId;//用户登陆id
    
    private String roleId;//用户角色

    /**
     * @return the id
     */
    public String getId() {
        return StringUtils.trim(id);
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the paymentId
     */
    public String getPaymentId() {
        return  StringUtils.trim(paymentId);
    }

    /**
     * @param paymentId the paymentId to set
     */
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    /**
     * @return the no
     */
    public String getNo() {
        return  StringUtils.trim(no);
    }

    /**
     * @param no the no to set
     */
    public void setNo(String no) {
        this.no = no;
    }

    /**
     * @return the productTypeId
     */
    public String getProductTypeId() {
        return  StringUtils.trim(productTypeId);
    }

    /**
     * @param productTypeId the productTypeId to set
     */
    public void setProductTypeId(String productTypeId) {
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
     * @return the supplierId
     */
    public String getSupplierId() {
        return supplierId;
    }

    /**
     * @param supplierId the supplierId to set
     */
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    /**
     * @return the supplierName
     */
    public String getSupplierName() {
        return  StringUtils.trim(supplierName);
    }

    /**
     * @param supplierName the supplierName to set
     */
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
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
     * @return the dateStart
     */
    public String getDateStart() {
        return dateStart;
    }

    /**
     * @param dateStart the dateStart to set
     */
    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    /**
     * @return the dateEnd
     */
    public String getDateEnd() {
        return dateEnd;
    }

    /**
     * @param dateEnd the dateEnd to set
     */
    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    /**
     * @return the backDate
     */
    public String getBackDate() {
        return backDate;
    }

    /**
     * @param backDate the backDate to set
     */
    public void setBackDate(String backDate) {
        this.backDate = backDate;
    }

    /**
     * @return the backDateStart
     */
    public String getBackDateStart() {
        return backDateStart;
    }

    /**
     * @param backDateStart the backDateStart to set
     */
    public void setBackDateStart(String backDateStart) {
        this.backDateStart = backDateStart;
    }

    /**
     * @return the backDateEnd
     */
    public String getBackDateEnd() {
        return backDateEnd;
    }

    /**
     * @param backDateEnd the backDateEnd to set
     */
    public void setBackDateEnd(String backDateEnd) {
        this.backDateEnd = backDateEnd;
    }

    /**
     * @return the backWay
     */
    public String getBackWay() {
        return  StringUtils.trim(backWay);
    }

    /**
     * @param backWay the backWay to set
     */
    public void setBackWay(String backWay) {
        this.backWay = backWay;
    }

    /**
     * @return the number
     */
    public String getNumber() {
        return  StringUtils.trim(number);
    }

    /**
     * @param number the number to set
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * @return the money
     */
    public String getMoney() {
        return  StringUtils.trim(money);
    }

    /**
     * @param money the money to set
     */
    public void setMoney(String money) {
        this.money = money;
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
        return StringUtils.trim(userName);
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
     * @return the datetime
     */
    public String getDatetime() {
        return datetime;
    }

    /**
     * @param datetime the datetime to set
     */
    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    /**
     * @return the loginId
     */
    public String getLoginId() {
        return loginId;
    }

    /**
     * @param loginId the loginId to set
     */
    public void setLoginId(String loginId) {
        this.loginId = loginId;
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
 