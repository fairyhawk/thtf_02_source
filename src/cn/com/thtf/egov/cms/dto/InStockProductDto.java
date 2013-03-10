/**
 * ClassName  InStockProductDto
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-5-28
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

import java.math.BigDecimal;

/**
 * InStockProductDto
 * 
 * @author Lubo
 */

public class InStockProductDto {

    /** 产品ID */
    private String productId;
    /** 产品编码 */
    private String productCode;
    /** 产品名称 */
    private String productName;
    /** 产品类型 */
    private String productType;
    /** 产品单位 */
    private String productUnit;

    /** 应入库数 */
    private String receiveGoodsDetailCount;
    /** 采购单价 */
    private BigDecimal buyContractDetailPrice;
    /** 采购金额 */
    private String money;
    /** 实际入库数 */
    private String inStockCount;
    /** 指定数 */
    private String paymentCount;

    /** 收票数 */
    private String receiveInvoiceCount;
    /** 返厂数 */
    private String buyBackGoodsCount;

    /** 本入库单入库数 */
    private Integer count;

    /**
     * @return the productId
     */
    public String getProductId() {
        return productId;
    }

    /**
     * @param productId
     *            the productId to set
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * @return the productCode
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * @param productCode
     *            the productCode to set
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName
     *            the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
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
     * @return the productUnit
     */
    public String getProductUnit() {
        return productUnit;
    }

    /**
     * @param productUnit
     *            the productUnit to set
     */
    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    /**
     * @return the receiveGoodsDetailCount
     */
    public String getReceiveGoodsDetailCount() {
        return receiveGoodsDetailCount;
    }

    /**
     * @param receiveGoodsDetailCount
     *            the receiveGoodsDetailCount to set
     */
    public void setReceiveGoodsDetailCount(String receiveGoodsDetailCount) {
        this.receiveGoodsDetailCount = receiveGoodsDetailCount;
    }

    /**
     * @return the buyContractDetailPrice
     */
    public BigDecimal getBuyContractDetailPrice() {
        return buyContractDetailPrice;
    }

    /**
     * @param buyContractDetailPrice
     *            the buyContractDetailPrice to set
     */
    public void setBuyContractDetailPrice(BigDecimal buyContractDetailPrice) {
        this.buyContractDetailPrice = buyContractDetailPrice;
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
     * @return the inStockCount
     */
    public String getInStockCount() {
        return inStockCount;
    }

    /**
     * @param inStockCount
     *            the inStockCount to set
     */
    public void setInStockCount(String inStockCount) {
        this.inStockCount = inStockCount;
    }

    /**
     * @return the paymentCount
     */
    public String getPaymentCount() {
        return paymentCount;
    }

    /**
     * @param paymentCount
     *            the paymentCount to set
     */
    public void setPaymentCount(String paymentCount) {
        this.paymentCount = paymentCount;
    }

    /**
     * @return the receiveInvoiceCount
     */
    public String getReceiveInvoiceCount() {
        return receiveInvoiceCount;
    }

    /**
     * @param receiveInvoiceCount
     *            the receiveInvoiceCount to set
     */
    public void setReceiveInvoiceCount(String receiveInvoiceCount) {
        this.receiveInvoiceCount = receiveInvoiceCount;
    }

    /**
     * @return the buyBackGoodsCount
     */
    public String getBuyBackGoodsCount() {
        return buyBackGoodsCount;
    }

    /**
     * @param buyBackGoodsCount
     *            the buyBackGoodsCount to set
     */
    public void setBuyBackGoodsCount(String buyBackGoodsCount) {
        this.buyBackGoodsCount = buyBackGoodsCount;
    }

    /**
     * @return the count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * @param count
     *            the count to set
     */
    public void setCount(Integer count) {
        this.count = count;
    }

}
