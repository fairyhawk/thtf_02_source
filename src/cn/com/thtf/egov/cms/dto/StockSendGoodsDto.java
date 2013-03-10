/**
 * ClassName  StockSendGoodsDto
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-6-7
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

import java.util.List;

import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.entity.UserStockroomProductEntity;

/**
 * StockSendGoodsDto
 * 
 * @author Lubo
 */
public class StockSendGoodsDto {

    /** 单据类型 */
    private String orderType;
    /** 单据类型 */
    private String orderTypeArr[];

    /** 单据号 */
    private String orderId;
    /** 单据号 */
    private String[] orderIdArr;
    /** 库房ID */
    private String stockroomId;
    /** 库房名称 */
    private String stockroomName;
    /** 产品分类ID */
    private String productTypeId;

    /** 产品分类名称 */
    private String productType;
    /** 客户名称 */
    private String customerName;
    /** 客户收获地址名称 */
    private String customerAddressName;
    /** 客户收获地址 */
    private String customerAddress;
    /** 运货方式 */
    private String transportWay;

    /** 申请日期 */
    private String date;
    /** 要求日期 */
    private String requestDate;
    /** 实际发货日期 */
    private String sendDate;
    /** 人员名称 */
    private String userName;
    /** 单据状态 原始 */
    private String status;

    /** 单据状态 映射后 */
    private String changeStatus;

    /** 产品分类数组 */
    private String productTypeIdArr;
    /** 库房ID数组 */
    private String stockroomIdArr;
    /** 是否是初始化 */
    private String init;
    /** 发货起始时间 */
    private String starttime;
    /** 发货终止时间 */
    private String endtime;

    /** 权限ID */
    private Integer roleId;
    /** user */
    private UserEntity user;

    /** 提货人姓名 */
    private String takeName;
    /** 提货人证件号 */
    private String takeNumber;
    /** 联系人邮编 */
    private String postcode;
    /** 联系人姓名 */
    private String linkman;
    /** 联系人电话 */
    private String tel;
    /** 联系人手机 */
    private String mobile;
    /** 特殊说明 */
    private String text;

    private List<UserStockroomProductEntity> userStockroomList;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
     * @return the takeName
     */
    public String getTakeName() {
        return takeName;
    }

    /**
     * @param takeName
     *            the takeName to set
     */
    public void setTakeName(String takeName) {
        this.takeName = takeName;
    }

    /**
     * @return the takeNumber
     */
    public String getTakeNumber() {
        return takeNumber;
    }

    /**
     * @param takeNumber
     *            the takeNumber to set
     */
    public void setTakeNumber(String takeNumber) {
        this.takeNumber = takeNumber;
    }

    /**
     * @return the postcode
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * @param postcode
     *            the postcode to set
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    /**
     * @return the linkman
     */
    public String getLinkman() {
        return linkman;
    }

    /**
     * @param linkman
     *            the linkman to set
     */
    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    /**
     * @return the tel
     */
    public String getTel() {
        return tel;
    }

    /**
     * @param tel
     *            the tel to set
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile
     *            the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return the orderIdArr
     */
    public String[] getOrderIdArr() {
        return orderIdArr;
    }

    /**
     * @param orderIdArr
     *            the orderIdArr to set
     */
    public void setOrderIdArr(String[] orderIdArr) {
        this.orderIdArr = orderIdArr;
    }

    /**
     * @return the orderTypeArr
     */
    public String[] getOrderTypeArr() {
        return orderTypeArr;
    }

    /**
     * @param orderTypeArr
     *            the orderTypeArr to set
     */
    public void setOrderTypeArr(String[] orderTypeArr) {
        this.orderTypeArr = orderTypeArr;
    }

    /**
     * @return the roleId
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     *            the roleId to set
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
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
     * @return the orderType
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     * @param orderType
     *            the orderType to set
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    /**
     * @return the orderId
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * @param orderId
     *            the orderId to set
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
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
     * @return the stockroomName
     */
    public String getStockroomName() {
        return stockroomName;
    }

    /**
     * @param stockroomName
     *            the stockroomName to set
     */
    public void setStockroomName(String stockroomName) {
        this.stockroomName = stockroomName;
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
     * @return the productType
     */
    public String getProductType() {
        return productType;
    }

    /**
     * @param productType
     *            the productType to set
     */
    public void setProductType(String productType) {
        this.productType = productType;
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
     * @return the customerAddressName
     */
    public String getCustomerAddressName() {
        return customerAddressName;
    }

    /**
     * @param customerAddressName
     *            the customerAddressName to set
     */
    public void setCustomerAddressName(String customerAddressName) {
        this.customerAddressName = customerAddressName;
    }

    /**
     * @return the customerAddress
     */
    public String getCustomerAddress() {
        return customerAddress;
    }

    /**
     * @param customerAddress
     *            the customerAddress to set
     */
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    /**
     * @return the transportWay
     */
    public String getTransportWay() {
        return transportWay;
    }

    /**
     * @param transportWay
     *            the transportWay to set
     */
    public void setTransportWay(String transportWay) {
        this.transportWay = transportWay;
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
     * @return the changeStatus
     */
    public String getChangeStatus() {
        return changeStatus;
    }

    /**
     * @param changeStatus
     *            the changeStatus to set
     */
    public void setChangeStatus(String changeStatus) {
        this.changeStatus = changeStatus;
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

    /**
     * @return the init
     */
    public String getInit() {
        return init;
    }

    /**
     * @param init
     *            the init to set
     */
    public void setInit(String init) {
        this.init = init;
    }

    /**
     * @return the starttime
     */
    public String getStarttime() {
        return starttime;
    }

    /**
     * @param starttime
     *            the starttime to set
     */
    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    /**
     * @return the endtime
     */
    public String getEndtime() {
        return endtime;
    }

    /**
     * @param endtime
     *            the endtime to set
     */
    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

}
