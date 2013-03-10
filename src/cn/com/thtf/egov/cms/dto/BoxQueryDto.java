/**
 * ClassName  BoxQueryDto
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-6-11
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

import java.util.List;

import cn.com.thtf.egov.cms.entity.UserAreaProductEntity;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.entity.UserStockroomProductEntity;

/**
 * BoxQueryDto
 * 
 * @author Lubo
 */
public class BoxQueryDto {

    /** 库房ID */
    private String stockroomId;
    /** 产品分类ID */
    private String productTypeId;
    /** 装箱单状态 */
    private String status;

    /** 装箱单号 */
    private String id;
    /** 运单号 */
    private String no;
    /** 物流公司Name */
    private String logisticsName;
    /** 客户名称 */
    private String customerName;
    /** 人员名称 */
    private String userName;

    /** 发货日期 */
    private String sendDateStarttime;
    /** 发货日期 */
    private String sendDateEndtime;
    /** 预计到货日期 */
    private String estimateDateStarttime;
    /** 预计到货日期 */
    private String estimateDateEndtime;
    /** 到货日期 */
    private String arrivalDateStarttime;
    /** 到货日期 */
    private String arrivalDateEndtime;
    /** 签收日期 */
    private String signoffDateStarttime;
    /** 签收日期 */
    private String signoffDateEndtime;
    /** 回执确认日期 */
    private String confirmDateStarttime;
    /** 回执确认日期 */
    private String confirmDateEndtime;

    /** 当前用户负责的产品分类 */
    private String productTypeIdArr;
    /** 当前用户负责的库房 */
    private String stockroomIdArr;
    /** 当前用户 */
    private UserEntity user;
    /** 发货单号 */
    private String orderId;
    
    private List<UserStockroomProductEntity> userStockroomList;
    /** 用户区域 产品分类 */
    private List<UserAreaProductEntity> userAreaProductList;

    /**
     * @return the orderId
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * @param orderId the orderId to set
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * @return the userAreaProductList
     */
    public List<UserAreaProductEntity> getUserAreaProductList() {
        return userAreaProductList;
    }

    /**
     * @param userAreaProductList the userAreaProductList to set
     */
    public void setUserAreaProductList(List<UserAreaProductEntity> userAreaProductList) {
        this.userAreaProductList = userAreaProductList;
    }

    /**
     * @return the userStockroomList
     */
    public List<UserStockroomProductEntity> getUserStockroomList() {
        return userStockroomList;
    }

    /**
     * @param userStockroomList
     *            the userStockroomList to set
     */
    public void setUserStockroomList(List<UserStockroomProductEntity> userStockroomList) {
        this.userStockroomList = userStockroomList;
    }

    /**
     * @return the user
     */
    public UserEntity getUser() {
        return user;
    }

    /**
     * @param user
     *            the user to set
     */
    public void setUser(UserEntity user) {
        this.user = user;
    }

    /**
     * @return the stockroomId
     */
    public String getStockroomId() {
        return stockroomId;
    }

    /**
     * @param stockroomId
     *            the stockroomId to set
     */
    public void setStockroomId(String stockroomId) {
        this.stockroomId = stockroomId;
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
     * @return the logisticsName
     */
    public String getLogisticsName() {
        return logisticsName;
    }

    /**
     * @param logisticsName
     *            the logisticsName to set
     */
    public void setLogisticsName(String logisticsName) {
        this.logisticsName = logisticsName;
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
     * @return the sendDateStarttime
     */
    public String getSendDateStarttime() {
        return sendDateStarttime;
    }

    /**
     * @param sendDateStarttime
     *            the sendDateStarttime to set
     */
    public void setSendDateStarttime(String sendDateStarttime) {
        this.sendDateStarttime = sendDateStarttime;
    }

    /**
     * @return the sendDateEndtime
     */
    public String getSendDateEndtime() {
        return sendDateEndtime;
    }

    /**
     * @param sendDateEndtime
     *            the sendDateEndtime to set
     */
    public void setSendDateEndtime(String sendDateEndtime) {
        this.sendDateEndtime = sendDateEndtime;
    }

    /**
     * @return the estimateDateStarttime
     */
    public String getEstimateDateStarttime() {
        return estimateDateStarttime;
    }

    /**
     * @param estimateDateStarttime
     *            the estimateDateStarttime to set
     */
    public void setEstimateDateStarttime(String estimateDateStarttime) {
        this.estimateDateStarttime = estimateDateStarttime;
    }

    /**
     * @return the estimateDateEndtime
     */
    public String getEstimateDateEndtime() {
        return estimateDateEndtime;
    }

    /**
     * @param estimateDateEndtime
     *            the estimateDateEndtime to set
     */
    public void setEstimateDateEndtime(String estimateDateEndtime) {
        this.estimateDateEndtime = estimateDateEndtime;
    }

    /**
     * @return the arrivalDateStarttime
     */
    public String getArrivalDateStarttime() {
        return arrivalDateStarttime;
    }

    /**
     * @param arrivalDateStarttime
     *            the arrivalDateStarttime to set
     */
    public void setArrivalDateStarttime(String arrivalDateStarttime) {
        this.arrivalDateStarttime = arrivalDateStarttime;
    }

    /**
     * @return the arrivalDateEndtime
     */
    public String getArrivalDateEndtime() {
        return arrivalDateEndtime;
    }

    /**
     * @param arrivalDateEndtime
     *            the arrivalDateEndtime to set
     */
    public void setArrivalDateEndtime(String arrivalDateEndtime) {
        this.arrivalDateEndtime = arrivalDateEndtime;
    }

    /**
     * @return the signoffDateStarttime
     */
    public String getSignoffDateStarttime() {
        return signoffDateStarttime;
    }

    /**
     * @param signoffDateStarttime
     *            the signoffDateStarttime to set
     */
    public void setSignoffDateStarttime(String signoffDateStarttime) {
        this.signoffDateStarttime = signoffDateStarttime;
    }

    /**
     * @return the signoffDateEndtime
     */
    public String getSignoffDateEndtime() {
        return signoffDateEndtime;
    }

    /**
     * @param signoffDateEndtime
     *            the signoffDateEndtime to set
     */
    public void setSignoffDateEndtime(String signoffDateEndtime) {
        this.signoffDateEndtime = signoffDateEndtime;
    }

    /**
     * @return the confirmDateStarttime
     */
    public String getConfirmDateStarttime() {
        return confirmDateStarttime;
    }

    /**
     * @param confirmDateStarttime
     *            the confirmDateStarttime to set
     */
    public void setConfirmDateStarttime(String confirmDateStarttime) {
        this.confirmDateStarttime = confirmDateStarttime;
    }

    /**
     * @return the confirmDateEndtime
     */
    public String getConfirmDateEndtime() {
        return confirmDateEndtime;
    }

    /**
     * @param confirmDateEndtime
     *            the confirmDateEndtime to set
     */
    public void setConfirmDateEndtime(String confirmDateEndtime) {
        this.confirmDateEndtime = confirmDateEndtime;
    }

    /**
     * @return the productTypeIdArr
     */
    public String getProductTypeIdArr() {
        return productTypeIdArr;
    }

    /**
     * @param productTypeIdArr
     *            the productTypeIdArr to set
     */
    public void setProductTypeIdArr(String productTypeIdArr) {
        this.productTypeIdArr = productTypeIdArr;
    }

    /**
     * @return the stockroomIdArr
     */
    public String getStockroomIdArr() {
        return stockroomIdArr;
    }

    /**
     * @param stockroomIdArr
     *            the stockroomIdArr to set
     */
    public void setStockroomIdArr(String stockroomIdArr) {
        this.stockroomIdArr = stockroomIdArr;
    }

}
