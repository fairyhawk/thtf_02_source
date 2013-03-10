package cn.com.thtf.egov.cms.dto;

import java.io.Serializable;

/**
 * 采购付款 列表检索DTO
 * 
 * @author LuPing
 */
public class BuyPaymentListQueryDto implements Serializable {
    /**
     * 自动序列化
     */
    private static final long serialVersionUID = -2778597112866271304L;
    /** 初始化检索**/
    private String init;
    /** 付款单号 **/
    private String id;
    /** 产品分类ID **/
    private String productTypeId;
    /** 供货商名称 **/
    private String supplierName;
    /** 付款金额 **/
    private String money;
    /** 付款方式 **/
    private String paymentWay;
    /** 付款状态 **/
    private String status;
    /** 申请起始日期 **/
    private String startDate;
    /** 申请终止日期 **/
    private String endDate;
    /** 承兑开具起始日期 **/
    private String startAcceptDate;
    /** 承兑开具终止日期 **/
    private String endAcceptDate;
    /** 付款起始日期 **/
    private String startPaymentDate;
    /** 付款终止日期 **/
    private String endPaymentDate;
    /** 申请人 **/
    private String userName;
    /**当前登陆用户**/
    private String nowUserId;
    /**当前登陆权限**/
    private Integer nowUserRole;
    /**入库单号**/
    private String inStockId;
    /**产品合同号**/
    private String productContractCode;
    
    public String getInStockId() {
        return inStockId;
    }

    public void setInStockId(String inStockId) {
        this.inStockId = inStockId;
    }

    public String getProductContractCode() {
        return productContractCode;
    }

    public void setProductContractCode(String productContractCode) {
        this.productContractCode = productContractCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(String productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getPaymentWay() {
        return paymentWay;
    }

    public void setPaymentWay(String paymentWay) {
        this.paymentWay = paymentWay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartAcceptDate() {
        return startAcceptDate;
    }

    public void setStartAcceptDate(String startAcceptDate) {
        this.startAcceptDate = startAcceptDate;
    }

    public String getEndAcceptDate() {
        return endAcceptDate;
    }

    public void setEndAcceptDate(String endAcceptDate) {
        this.endAcceptDate = endAcceptDate;
    }

    public String getStartPaymentDate() {
        return startPaymentDate;
    }

    public void setStartPaymentDate(String startPaymentDate) {
        this.startPaymentDate = startPaymentDate;
    }

    public String getEndPaymentDate() {
        return endPaymentDate;
    }

    public void setEndPaymentDate(String endPaymentDate) {
        this.endPaymentDate = endPaymentDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getInit() {
        return init;
    }

    public void setInit(String init) {
        this.init = init;
    }

    public String getNowUserId() {
        return nowUserId;
    }

    public void setNowUserId(String nowUserId) {
        this.nowUserId = nowUserId;
    }

    public Integer getNowUserRole() {
        return nowUserRole;
    }

    public void setNowUserRole(Integer nowUserRole) {
        this.nowUserRole = nowUserRole;
    }
}
