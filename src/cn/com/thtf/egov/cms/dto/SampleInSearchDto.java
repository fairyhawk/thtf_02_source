/**
 * ClassName  SampleInSearchDto
 *
 * History
 * Create User: Administrator
 * Create Date: 2010-7-12
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

import org.apache.commons.lang.StringUtils;


/**
 * 
 * @author Administrator
 */

public class SampleInSearchDto {

    private String sampleInId; // 归还单号

    private String sampleOutId;// 借出单号

    private String inStockroomId;// 库房编号

    private String inStockroomName;// 库房名称

    private String productTypeId;// 产品分类id

    private String productTypeName;// 产品分类名称

    private String companyName;// 借出单位名称

    private String stockroomAddressId;// 库房地址

    private String userId;// 申请人id

    private String userName;// 申请人姓名

    private String date;// 申请日期

    private String dateStart;// 申请起始日期

    private String dateEnd;// 申请终止日期

    private String inDate;// 预计归还日期

    private String inDateStart;// 预计归还起始日期

    private String inDateEnd;// 预计归还终止日期

    private String stkAdmDate;// 入库日期（库房管理员评审日期）

    private String stkAdmDateStart;// 入库起始日期（库房管理员评审日期）

    private String stkAdmDateEnd;// 入库终止日期（库房管理员评审日期）

    private String money;// 归还金额

    private String status;// 归还单状态
    
    private String loginId;// 用户id

    private String roleId; // 角色id

    /**
     * @return the sampleInId
     */
    public String getSampleInId() {
        return StringUtils.trim(sampleInId);
    }

    /**
     * @param sampleInId
     *            the sampleInId to set
     */
    public void setSampleInId(String sampleInId) {
        this.sampleInId = sampleInId;
    }

    /**
     * @return the sampleOutId
     */
    public String getSampleOutId() {
        return StringUtils.trim(sampleOutId);
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
    public String getInStockroomId() {
        return inStockroomId;
    }

    /**
     * @param inStockroomId
     *            the inStockroomId to set
     */
    public void setInStockroomId(String inStockroomId) {
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
        return StringUtils.trim(companyName);
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
    public String getStockroomAddressId() {
        return stockroomAddressId;
    }

    /**
     * @param stockroomAddressId
     *            the stockroomAddressId to set
     */
    public void setStockroomAddressId(String stockroomAddressId) {
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
        return StringUtils.trim(userName);
    }

    /**
     * @param userName
     *            the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
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
     * @return the dateStart
     */
    public String getDateStart() {
        return dateStart;
    }

    /**
     * @param dateStart
     *            the dateStart to set
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
     * @param dateEnd
     *            the dateEnd to set
     */
    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
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
     * @return the inDateStart
     */
    public String getInDateStart() {
        return inDateStart;
    }

    /**
     * @param inDateStart
     *            the inDateStart to set
     */
    public void setInDateStart(String inDateStart) {
        this.inDateStart = inDateStart;
    }

    /**
     * @return the inDateEnd
     */
    public String getInDateEnd() {
        return inDateEnd;
    }

    /**
     * @param inDateEnd
     *            the inDateEnd to set
     */
    public void setInDateEnd(String inDateEnd) {
        this.inDateEnd = inDateEnd;
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
     * @return the stkAdmDateStart
     */
    public String getStkAdmDateStart() {
        return stkAdmDateStart;
    }

    /**
     * @param stkAdmDateStart
     *            the stkAdmDateStart to set
     */
    public void setStkAdmDateStart(String stkAdmDateStart) {
        this.stkAdmDateStart = stkAdmDateStart;
    }

    /**
     * @return the stkAdmDateEnd
     */
    public String getStkAdmDateEnd() {
        return stkAdmDateEnd;
    }

    /**
     * @param stkAdmDateEnd
     *            the stkAdmDateEnd to set
     */
    public void setStkAdmDateEnd(String stkAdmDateEnd) {
        this.stkAdmDateEnd = stkAdmDateEnd;
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
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(String status) {
        this.status = status;
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
