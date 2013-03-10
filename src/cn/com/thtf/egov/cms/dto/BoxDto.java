/**
 * ClassName  BoxDto
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-6-8
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

/**
 * BoxDto
 * 
 * @author Lubo
 */
public class BoxDto {

    /** 库房ID */
    private String stockroomId;
    /** 库房名 */
    private String stockroomName;
    /** 产品分类ID */
    private String productTypeId;
    /** 运货方式 */
    private String transportWay;
    /** 物流公司ID */
    private String logisticsId;
    /** 物流公司Name */
    private String logisticsName;
    /** 装箱件数 */
    private String count;
    /** 预计发货费用 */
    private String money;
    /** 发货单号 */
    private String[] orderIdArr;
    /** 发货单类型 */
    private String[] orderTypeArr;

    /** 装箱单号 */
    private String id;
    /** 装箱单状态 */
    private String status;
    /** 发货日期 */
    private String date;
    /** 登录名 */
    private String userId;
    /** 人员名称 */
    private String userName;
    /** 新建日期 */
    private String datetime;

    /** 产品分类名称 */
    private String productTypeName;
    /** 发货单号 */
    private String orderId;
    /** 提交还是保存 */
    private String submitType;
    /** 新增还是修改 */
    private boolean pageType;

    /** 客户名称 */
    private String customerName;
    /** 客户运货方式 */
    private String customerTransportWay;
    /** 提货人姓名 */
    private String takeName;
    /** 提货人证件号 */
    private String takeNumber;
    /** 收货单位名称 */
    private String customerAddressName;

    /** 收货地址 */
    private String customerAddress;
    /** 联系人 */
    private String linkman;
    /** 邮编 */
    private String postcode;
    /** 电话 */
    private String tel;
    /** 手机 */
    private String mobile;

    /** 运单号 */
    private String no;
    /** 实际货运方式 */
    private String realityWay;
    /** 预计到货日期 */
    private String estimateDate;
    /** 货运联系电话 */
    private String noTel;
    /** 在途信息 */
    private String info;

    /** 到货日期 */
    private String arrivalDate;
    /** 签收日期 */
    private String signoffDate;
    /** 签收人 */
    private String signoffName;
    /** 回执确认日期 */
    private String confirmDate;
    /** 物流管理员用户名 */
    private String logAdmName;

    /** 库房电话 */
    private String stockroomTel;
    
    /** 确认标识 */
    private String tbcFlag;
    /** 确认时间 */
    private String tbcdate;

    public String getTbcFlag() {
        return tbcFlag;
    }

    public void setTbcFlag(String tbcFlag) {
        this.tbcFlag = tbcFlag;
    }

    public String getTbcdate() {
        return tbcdate;
    }

    public void setTbcdate(String tbcdate) {
        this.tbcdate = tbcdate;
    }

    public String getStockroomTel() {
        return stockroomTel;
    }

    public void setStockroomTel(String stockroomTel) {
        this.stockroomTel = stockroomTel;
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
     * @return the realityWay
     */
    public String getRealityWay() {
        return realityWay;
    }

    /**
     * @param realityWay
     *            the realityWay to set
     */
    public void setRealityWay(String realityWay) {
        this.realityWay = realityWay;
    }

    /**
     * @return the estimateDate
     */
    public String getEstimateDate() {
        return estimateDate;
    }

    /**
     * @param estimateDate
     *            the estimateDate to set
     */
    public void setEstimateDate(String estimateDate) {
        this.estimateDate = estimateDate;
    }

    /**
     * @return the noTel
     */
    public String getNoTel() {
        return noTel;
    }

    /**
     * @param noTel
     *            the noTel to set
     */
    public void setNoTel(String noTel) {
        this.noTel = noTel;
    }

    /**
     * @return the info
     */
    public String getInfo() {
        return info;
    }

    /**
     * @param info
     *            the info to set
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * @return the arrivalDate
     */
    public String getArrivalDate() {
        return arrivalDate;
    }

    /**
     * @param arrivalDate
     *            the arrivalDate to set
     */
    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    /**
     * @return the signoffDate
     */
    public String getSignoffDate() {
        return signoffDate;
    }

    /**
     * @param signoffDate
     *            the signoffDate to set
     */
    public void setSignoffDate(String signoffDate) {
        this.signoffDate = signoffDate;
    }

    /**
     * @return the signoffName
     */
    public String getSignoffName() {
        return signoffName;
    }

    /**
     * @param signoffName
     *            the signoffName to set
     */
    public void setSignoffName(String signoffName) {
        this.signoffName = signoffName;
    }

    /**
     * @return the confirmDate
     */
    public String getConfirmDate() {
        return confirmDate;
    }

    /**
     * @param confirmDate
     *            the confirmDate to set
     */
    public void setConfirmDate(String confirmDate) {
        this.confirmDate = confirmDate;
    }

    /**
     * @return the logAdmName
     */
    public String getLogAdmName() {
        return logAdmName;
    }

    /**
     * @param logAdmName
     *            the logAdmName to set
     */
    public void setLogAdmName(String logAdmName) {
        this.logAdmName = logAdmName;
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
     * @return the customerTransportWay
     */
    public String getCustomerTransportWay() {
        return customerTransportWay;
    }

    /**
     * @param customerTransportWay
     *            the customerTransportWay to set
     */
    public void setCustomerTransportWay(String customerTransportWay) {
        this.customerTransportWay = customerTransportWay;
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
     * @return the submitType
     */
    public String getSubmitType() {
        return submitType;
    }

    /**
     * @param submitType
     *            the submitType to set
     */
    public void setSubmitType(String submitType) {
        this.submitType = submitType;
    }

    /**
     * @return the pageType
     */
    public boolean isPageType() {
        return pageType;
    }

    /**
     * @param pageType
     *            the pageType to set
     */
    public void setPageType(boolean pageType) {
        this.pageType = pageType;
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
     * @return the count
     */
    public String getCount() {
        return count;
    }

    /**
     * @param count
     *            the count to set
     */
    public void setCount(String count) {
        this.count = count;
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
     * @return the datetime
     */
    public String getDatetime() {
        return datetime;
    }

    /**
     * @param datetime
     *            the datetime to set
     */
    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    /**
     * @return the logisticsId
     */
    public String getLogisticsId() {
        return logisticsId;
    }

    /**
     * @param logisticsId
     *            the logisticsId to set
     */
    public void setLogisticsId(String logisticsId) {
        this.logisticsId = logisticsId;
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

}
