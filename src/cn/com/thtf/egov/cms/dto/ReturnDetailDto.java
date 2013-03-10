/**
 * ClassName  ReturnDetailDto
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-5-21
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

import java.math.BigDecimal;

/**
 * ReturnDetailDto
 * 
 * @author Lubo
 */

public class ReturnDetailDto {

    /** 回款明细ID */
    private Integer id;
    /** 回款ID */
    private String returnId;
    /** 指定类型 */
    private Integer appointType;
    /** 产品分类ID */
    private Integer productTypeId;
    /** 销售合同ID */
    private String sellContractId;

    /** 发货单ID */
    private String sendGoodsId;
    /** 产品ID */
    private Integer productId;
    /** 指定金额 */
    private BigDecimal money;
    /** 客户信用ID */
    private Integer customerCreditId;
    
    /** 信用类型ID */
    private Integer creditTypeId;
    /** 客户ID */
    private Integer customerId;
   
    public Integer getCreditTypeId() {
        return creditTypeId;
    }

    public void setCreditTypeId(Integer creditTypeId) {
        this.creditTypeId = creditTypeId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the returnId
     */
    public String getReturnId() {
        return returnId;
    }

    /**
     * @param returnId
     *            the returnId to set
     */
    public void setReturnId(String returnId) {
        this.returnId = returnId;
    }

    /**
     * @return the appointType
     */
    public Integer getAppointType() {
        return appointType;
    }

    /**
     * @param appointType
     *            the appointType to set
     */
    public void setAppointType(Integer appointType) {
        this.appointType = appointType;
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
     * @return the sendGoodsId
     */
    public String getSendGoodsId() {
        return sendGoodsId;
    }

    /**
     * @param sendGoodsId
     *            the sendGoodsId to set
     */
    public void setSendGoodsId(String sendGoodsId) {
        this.sendGoodsId = sendGoodsId;
    }

    /**
     * @return the productId
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * @param productId
     *            the productId to set
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * @return the money
     */
    public BigDecimal getMoney() {
        return money;
    }

    /**
     * @param money
     *            the money to set
     */
    public void setMoney(BigDecimal money) {
        this.money = money;
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

}
