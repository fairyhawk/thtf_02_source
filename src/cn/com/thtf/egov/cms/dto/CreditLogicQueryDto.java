/**
 * ClassName  CreditLogicQueryDto
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-4-21
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

/**
 * 发货信用判断 查询条件
 * 
 * @author Lubo
 */

public class CreditLogicQueryDto {

    /** 客户编号 */
    private Integer customerId;
    /** 产品分类编号 */
    private Integer productTypeId;
    /** 付款方式 */
    private Integer paymentWay;
    /** 销售合同编号 */
    private String sellContractId;
    /** 发货金额 */
    private String sendGoodsMoney;

    /** 客户信用ID */
    private Integer customerCreditId;
    /** 指定类型 - 内部方法需要参数 */
    private String appointType;
    
    /** 现结金额 */
    private String cashMoney;

    /**
     * @return the cashMoney
     */
    public String getCashMoney() {
        return cashMoney;
    }

    /**
     * @param cashMoney the cashMoney to set
     */
    public void setCashMoney(String cashMoney) {
        this.cashMoney = cashMoney;
    }

    /**
     * @return the customerCreditId
     */
    public Integer getCustomerCreditId() {
        return customerCreditId;
    }

    /**
     * @param customerCreditId
     *            the customerCreditId to set
     */
    public void setCustomerCreditId(Integer customerCreditId) {
        this.customerCreditId = customerCreditId;
    }

    /**
     * @return the appointType
     */
    public String getAppointType() {
        return appointType;
    }

    /**
     * @param appointType
     *            the appointType to set
     */
    public void setAppointType(String appointType) {
        this.appointType = appointType;
    }

    /**
     * @return the sendGoodsMoney
     */
    public String getSendGoodsMoney() {
        return sendGoodsMoney;
    }

    /**
     * @param sendGoodsMoney
     *            the sendGoodsMoney to set
     */
    public void setSendGoodsMoney(String sendGoodsMoney) {
        this.sendGoodsMoney = sendGoodsMoney;
    }

    /**
     * @return the sellContractId
     */
    public String getSellContractId() {
        return sellContractId;
    }

    /**
     * @param sellContractId
     *            the sellContractId to set
     */
    public void setSellContractId(String sellContractId) {
        this.sellContractId = sellContractId;
    }

    /**
     * @return the customerId
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId
     *            the customerId to set
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * @return the productTypeId
     */
    public Integer getProductTypeId() {
        return productTypeId;
    }

    /**
     * @param productTypeId
     *            the productTypeId to set
     */
    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    /**
     * @return the paymentWay
     */
    public Integer getPaymentWay() {
        return paymentWay;
    }

    /**
     * @param paymentWay
     *            the paymentWay to set
     */
    public void setPaymentWay(Integer paymentWay) {
        this.paymentWay = paymentWay;
    }
}
