/**
 * ClassName  MoveStockSearchDto
 *
 * History
 * Create User: Administrator
 * Create Date: 2010-6-29
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

import org.apache.commons.lang.StringUtils;

/**
 * 移库单检索
 * @author zhangzx
 */

public class MoveStockSearchDto {
    
    public String id;// 移库单号

    public String productTypeId;// 产品分类编号
    
    public String productTypeName;//产品分类名称

    public String date;// 申请日期
    
    public String dateStart;// 申请起始日期
    
    public String dateEnd;// 申请终止日期

    public String type;// 移库单类型

    public String outStockroomId;// 移出库房编号

    public String outStockroomName;// 移出库房名称

    public String inStockroomId;// 移入库房编号

    public String inStockroomName;// 移入库房名称

    public String stockroomAddressId;// 货物接收单位编号

    public String stockroomAddressName;// 货物接收单位名称

    public String transportWay;// 货运方式

    public String requestDate;// 要求发货日期
    
    public String requestDateStart;// 要求发货起始日期
    
    public String requestDateEnd;// 要求发货终止日期

    public String sendDate;// 发货日期
    
    public String sendDateStart;// 发货起始日期
    
    public String sendDateEnd;// 发货终止日期

    public String status;// 移库单状态

    public String userId;// 登录名

    public String userName;// 人员名称
    
    public String inAdmDate ;//入库日期
    
    public String inAdmDateStart ;//入库起始日期
    
    public String inAdmDateEnd ;//入库终止日期
    
    private String loginId;//用户id
    
    private String roleId; //角色id

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
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the outStockroomId
     */
    public String getOutStockroomId() {
        return outStockroomId;
    }

    /**
     * @param outStockroomId the outStockroomId to set
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
     * @param outStockroomName the outStockroomName to set
     */
    public void setOutStockroomName(String outStockroomName) {
        this.outStockroomName = outStockroomName;
    }

    /**
     * @return the inStockroomId
     */
    public String getInStockroomId() {
        return inStockroomId;
    }

    /**
     * @param inStockroomId the inStockroomId to set
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
     * @param inStockroomName the inStockroomName to set
     */
    public void setInStockroomName(String inStockroomName) {
        this.inStockroomName = inStockroomName;
    }

    /**
     * @return the stockroomAddressId
     */
    public String getStockroomAddressId() {
        return StringUtils.trim(stockroomAddressId);
    }

    /**
     * @param stockroomAddressId the stockroomAddressId to set
     */
    public void setStockroomAddressId(String stockroomAddressId) {
        this.stockroomAddressId = stockroomAddressId;
    }

    /**
     * @return the stockroomAddressName
     */
    public String getStockroomAddressName() {
        return StringUtils.trim(stockroomAddressName);
    }

    /**
     * @param stockroomAddressName the stockroomAddressName to set
     */
    public void setStockroomAddressName(String stockroomAddressName) {
        this.stockroomAddressName = stockroomAddressName;
    }

    /**
     * @return the transportWay
     */
    public String getTransportWay() {
        return transportWay;
    }

    /**
     * @param transportWay the transportWay to set
     */
    public void setTransportWay(String transportWay) {
        this.transportWay = transportWay;
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
     * @return the requestDateStart
     */
    public String getRequestDateStart() {
        return requestDateStart;
    }

    /**
     * @param requestDateStart the requestDateStart to set
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
     * @param requestDateEnd the requestDateEnd to set
     */
    public void setRequestDateEnd(String requestDateEnd) {
        this.requestDateEnd = requestDateEnd;
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
     * @return the sendDateStart
     */
    public String getSendDateStart() {
        return sendDateStart;
    }

    /**
     * @param sendDateStart the sendDateStart to set
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
     * @param sendDateEnd the sendDateEnd to set
     */
    public void setSendDateEnd(String sendDateEnd) {
        this.sendDateEnd = sendDateEnd;
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
     * @return the inAdmDate
     */
    public String getInAdmDate() {
        return inAdmDate;
    }

    /**
     * @param inAdmDate the inAdmDate to set
     */
    public void setInAdmDate(String inAdmDate) {
        this.inAdmDate = inAdmDate;
    }

    /**
     * @return the inAdmDateStart
     */
    public String getInAdmDateStart() {
        return inAdmDateStart;
    }

    /**
     * @param inAdmDateStart the inAdmDateStart to set
     */
    public void setInAdmDateStart(String inAdmDateStart) {
        this.inAdmDateStart = inAdmDateStart;
    }

    /**
     * @return the inAdmDateEnd
     */
    public String getInAdmDateEnd() {
        return inAdmDateEnd;
    }

    /**
     * @param inAdmDateEnd the inAdmDateEnd to set
     */
    public void setInAdmDateEnd(String inAdmDateEnd) {
        this.inAdmDateEnd = inAdmDateEnd;
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
