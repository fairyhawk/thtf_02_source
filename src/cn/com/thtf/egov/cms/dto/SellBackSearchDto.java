/**
 * ClassName  SellBackSearchDto
 *
 * History
 * Create User: Administrator
 * Create Date: 2010-5-31
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

import org.apache.commons.lang.StringUtils;

/**
 * 销售退款检索Dto
 * by zzx
 */

public class SellBackSearchDto {
    private String id; // 退款申请单号

    private String returnId; // 回款流水号

    private String productTypeId;// 产品分类编号

    private String productTypeName;// 产品分类名称

    private String customerId;// 客户编号

    private String customerName;// 客户名称

    private String customerLinkmanId;// 客户联系人编号

    private String backWay;// 退款方式

    private String number;// 凭证号

    private String money;// 退款金额

    private String status;// 退款状态

    private String date;// 申请日期
    
    private String dateStart; // 申请起始日期
    
    private String dateEnd; // 申请终止日期

    private String userId;// 申请人登录名

    private String userName;// 申请人用户名

    private String userAreaId;// 人员区域编号

    private String text;// 特殊说明

    private String backDate;// 退款日期
    
    private String backDateStart; //退款起始日期
    
    private String backDateEnd; //退款终止日期

    private String confirmId;// 确认人登录名

    private String confirmName;// 确认人用户名

    private String sellMajId;// 销售总监登录名

    private String sellMajName;// 销售总监用户名

    private String opeMajId;// 运营总监登录名

    private String opeMajName;// 运营总监用户名

    private String datetime;// 新建日期
    
    // 当前登录用户信息
    private String roleId;
    
    private String loginId;

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
     * @return the returnId
     */
    public String getReturnId() {
        return StringUtils.trim(returnId);
    }

    /**
     * @param returnId the returnId to set
     */
    public void setReturnId(String returnId) {
        this.returnId = returnId;
    }

    /**
     * @return the productTypeId
     */
    public String getProductTypeId() {
        return productTypeId;
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
     * @return the customerId
     */
    public String getCustomerId() {
        return StringUtils.trim(customerId);
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
        return StringUtils.trim(customerName);
    }

    /**
     * @param customerName the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return the customerLinkmanId
     */
    public String getCustomerLinkmanId() {
        return customerLinkmanId;
    }

    /**
     * @param customerLinkmanId the customerLinkmanId to set
     */
    public void setCustomerLinkmanId(String customerLinkmanId) {
        this.customerLinkmanId = customerLinkmanId;
    }

    /**
     * @return the backWay
     */
    public String getBackWay() {
        return backWay;
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
        return StringUtils.trim(number);
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
        return money;
    }

    /**
     * @param money the money to set
     */
    public void setMoney(String money) {
        this.money = money;
    }

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
     * @return the userId
     */
    public String getUserId() {
        return StringUtils.trim(userId);
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
     * @return the userAreaId
     */
    public String getUserAreaId() {
        return userAreaId;
    }

    /**
     * @param userAreaId the userAreaId to set
     */
    public void setUserAreaId(String userAreaId) {
        this.userAreaId = userAreaId;
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
     * @return the confirmId
     */
    public String getConfirmId() {
        return StringUtils.trim(confirmId);
    }

    /**
     * @param confirmId the confirmId to set
     */
    public void setConfirmId(String confirmId) {
        this.confirmId = confirmId;
    }

    /**
     * @return the confirmName
     */
    public String getConfirmName() {
        return StringUtils.trim(confirmName);
    }

    /**
     * @param confirmName the confirmName to set
     */
    public void setConfirmName(String confirmName) {
        this.confirmName = confirmName;
    }

    /**
     * @return the sellMajId
     */
    public String getSellMajId() {
        return sellMajId;
    }

    /**
     * @param sellMajId the sellMajId to set
     */
    public void setSellMajId(String sellMajId) {
        this.sellMajId = sellMajId;
    }

    /**
     * @return the sellMajName
     */
    public String getSellMajName() {
        return sellMajName;
    }

    /**
     * @param sellMajName the sellMajName to set
     */
    public void setSellMajName(String sellMajName) {
        this.sellMajName = sellMajName;
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
   
}
