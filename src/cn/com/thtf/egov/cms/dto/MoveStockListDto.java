/**
 * ClassName  MoveStockListDto
 *
 * History
 * Create User: Administrator
 * Create Date: 2010-6-28
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

/**
 * 移库管理DTO
 * 
 * @author zhangzx
 * 
 */
public class MoveStockListDto {
    
    public String id;// 移库单号

    public String productTypeId;// 产品分类编号
    
    public String productTypeName;//产品分类名称

    public String date;// 申请日期

    public Integer type;// 移库单类型

    public Integer outStockroomId;// 移出库房编号

    public String outStockroomName;// 移出库房名称

    public Integer inStockroomId;// 移入库房编号

    public String inStockroomName;// 移入库房名称

    public Integer stockroomAddressId;// 库房收货地址编号

    public String stockroomAddressName;// 库房收货地址名称

    public Integer transportWay;// 货运方式

    public String requestDate;// 要求发货日期

    public String sendDate;// 发货日期

    public Integer status;// 移库单状态

    public String userId;// 登录名

    public String userName;// 人员名称
    
    public String inAdmDate ;//入库日期

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
     * @return the inStockroomId
     */
    public Integer getInStockroomId() {
        return inStockroomId;
    }

    /**
     * @param inStockroomId the inStockroomId to set
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
     * @param inStockroomName the inStockroomName to set
     */
    public void setInStockroomName(String inStockroomName) {
        this.inStockroomName = inStockroomName;
    }

    /**
     * @return the stockroomAddressId
     */
    public Integer getStockroomAddressId() {
        return stockroomAddressId;
    }

    /**
     * @param stockroomAddressId the stockroomAddressId to set
     */
    public void setStockroomAddressId(Integer stockroomAddressId) {
        this.stockroomAddressId = stockroomAddressId;
    }

    /**
     * @return the stockroomAddressName
     */
    public String getStockroomAddressName() {
        return stockroomAddressName;
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
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Integer status) {
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
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
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
}
