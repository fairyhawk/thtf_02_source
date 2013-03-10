/**
 * ClassName  SampleOutSearchDto
 *
 * History
 * Create User: Administrator
 * Create Date: 2010-7-6
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author Administrator
 */

public class SampleOutSearchDto {

    private String id;// 借出单号

    private String outStockroomId;// 库房编号

    private String outStockroomName;// 库房名称

    private String productTypeId;// 产品分类编号

    private String productTypeName;// 产品分类名称

    private String companyType;// 借出单位类型

    private String companyId;// 借出单位编号

    private String companyName;// 借出单位名称

    private String type;// 借出单类型

    private String date;// 申请日期

    public String dateStart;// 申请起始日期

    public String dateEnd;// 申请终止日期

    private String requestDate;// 要求发货日期

    public String requestDateStart;// 要求发货起始日期

    public String requestDateEnd;// 要求发货终止日期

    private String inDate;// 预计归还日期

    private String inDateStart;// 预计归还起始日期

    private String inDateEnd;// 预计归还终止日期

    private String sendDate;// 发货日期

    public String sendDateStart;// 发货起始日期

    public String sendDateEnd;// 发货终止日期

    private String userId;// 登录名

    private String userName;// 申请人员名称

    private String custosName;// 保管人姓名

    private String addressId;// 发货地址编号

    private String sampleOutStatus;// 借出单状态

    private String sampleInStatus;// 归还单状态

    private String sampleInSumMoney;// 归还金额

    private String loginId;// 用户id

    private String roleId; // 角色id

    /**
     * @return the id
     */
    public String getId() {
        return StringUtils.trim(id);
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the outStockroomId
     */
    public String getOutStockroomId() {
        return outStockroomId;
    }

    /**
     * @param outStockroomId
     *            the outStockroomId to set
     */
    public void setOutStockroomId(String outStockroomId) {
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
     * @return the companyType
     */
    public String getCompanyType() {
        return companyType;
    }

    /**
     * @param companyType
     *            the companyType to set
     */
    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    /**
     * @return the companyId
     */
    public String getCompanyId() {
        return companyId;
    }

    /**
     * @param companyId
     *            the companyId to set
     */
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
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
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(String type) {
        this.type = type;
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
     * @return the requestDate
     */
    public String getRequestDate() {
        return requestDate;
    }

    /**
     * @param requestDate
     *            the requestDate to set
     */
    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    /**
     * @return the requestDateStart
     */
    public String getRequestDateStart() {
        return requestDateStart;
    }

    /**
     * @param requestDateStart
     *            the requestDateStart to set
     */
    public void setRequestDateStart(String requestDateStart) {
        this.requestDateStart = requestDateStart;
    }

    /**
     * @return the requestDateEnd
     */
    public String getRequestDateEnd() {
        return requestDateEnd;
    }

    /**
     * @param requestDateEnd
     *            the requestDateEnd to set
     */
    public void setRequestDateEnd(String requestDateEnd) {
        this.requestDateEnd = requestDateEnd;
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
     * @return the sendDateStart
     */
    public String getSendDateStart() {
        return sendDateStart;
    }

    /**
     * @param sendDateStart
     *            the sendDateStart to set
     */
    public void setSendDateStart(String sendDateStart) {
        this.sendDateStart = sendDateStart;
    }

    /**
     * @return the sendDateEnd
     */
    public String getSendDateEnd() {
        return sendDateEnd;
    }

    /**
     * @param sendDateEnd
     *            the sendDateEnd to set
     */
    public void setSendDateEnd(String sendDateEnd) {
        this.sendDateEnd = sendDateEnd;
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
     * @return the custosName
     */
    public String getCustosName() {
        return StringUtils.trim(custosName);
    }

    /**
     * @param custosName
     *            the custosName to set
     */
    public void setCustosName(String custosName) {
        this.custosName = custosName;
    }

    /**
     * @return the addressId
     */
    public String getAddressId() {
        return addressId;
    }

    /**
     * @param addressId
     *            the addressId to set
     */
    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    /**
     * @return the sampleOutStatus
     */
    public String getSampleOutStatus() {
        return sampleOutStatus;
    }

    /**
     * @param sampleOutStatus
     *            the sampleOutStatus to set
     */
    public void setSampleOutStatus(String sampleOutStatus) {
        this.sampleOutStatus = sampleOutStatus;
    }

    /**
     * @return the sampleInStatus
     */
    public String getSampleInStatus() {
        return sampleInStatus;
    }

    /**
     * @param sampleInStatus
     *            the sampleInStatus to set
     */
    public void setSampleInStatus(String sampleInStatus) {
        this.sampleInStatus = sampleInStatus;
    }

    /**
     * @return the sampleInSumMoney
     */
    public String getSampleInSumMoney() {
        return sampleInSumMoney;
    }

    /**
     * @param sampleInSumMoney
     *            the sampleInSumMoney to set
     */
    public void setSampleInSumMoney(String sampleInSumMoney) {
        this.sampleInSumMoney = sampleInSumMoney;
    }

    /**
     * @return the loginId
     */
    public String getLoginId() {
        return loginId;
    }

    /**
     * @param loginId
     *            the loginId to set
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
     * @param roleId
     *            the roleId to set
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

}
