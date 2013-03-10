/**
 * ClassName  InStockProdDetailDto
 *
 * History
 * Create User: ChenHuajiang
 * Create Date: 2010-6-11
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

import java.io.Serializable;

/**
 * 入库明细选择
 * 
 * @author ChenHuajiang
 */

public class InStockProdDetailDto implements Serializable {

    private static final long serialVersionUID = -4581108713434196094L;

    /** 入库单ID */
    private String inStockId;

    /** 产品ID */
    private String prodId;

    /** 产品编码 */
    private String prodCode;

    /** 产品名称 */
    private String prodName;

    /** 产品类型 */
    private String prodType;

    /** 产品单位 */
    private String prodUnit;

    /** 产品合同号 */
    private String prodContractCode;

    /** 公司合同号 */
    private String compContractCode;

    /** 产品税率 */
    private String taxRate;

    /** 产品库存金额 */
    private String money;

    /** 采购单价 */
    private String buyUnitPrice;

    /** 入库数 */
    private String inStockCount;

    /** 指定金额 */
    private String appointMoney;

    /** 收票数 */
    private String receiveInvoiceCount;

    /** 已收票数 */
    private String receivedInvoiceCount;

    /** 收票金额 */
    private String receiveInvoiceMoney;

    /** 返厂金额 */
    private String backGoodsMoney;
    /**发票税率*/
    private String receiveRate;
    /**
     * 发票类型
     */
    private Integer invoiceType;
    
    public Integer getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(Integer invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getReceiveRate() {
        return receiveRate;
    }

    public void setReceiveRate(String receiveRate) {
        this.receiveRate = receiveRate;
    }

    public String getMoney() {
        return this.money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTaxRate() {
        return this.taxRate;
    }

    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }

    public String getReceivedInvoiceCount() {
        return this.receivedInvoiceCount;
    }

    public void setReceivedInvoiceCount(String receivedInvoiceCount) {
        this.receivedInvoiceCount = receivedInvoiceCount;
    }

    public String getInStockId() {
        return this.inStockId;
    }

    public void setInStockId(String inStockId) {
        this.inStockId = inStockId;
    }

    public String getProdId() {
        return this.prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getProdCode() {
        return this.prodCode;
    }

    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
    }

    public String getProdName() {
        return this.prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdType() {
        return this.prodType;
    }

    public void setProdType(String prodType) {
        this.prodType = prodType;
    }

    public String getProdUnit() {
        return this.prodUnit;
    }

    public void setProdUnit(String prodUnit) {
        this.prodUnit = prodUnit;
    }

    public String getProdContractCode() {
        return this.prodContractCode;
    }

    public void setProdContractCode(String prodContractCode) {
        this.prodContractCode = prodContractCode;
    }

    public String getCompContractCode() {
        return this.compContractCode;
    }

    public void setCompContractCode(String compContractCode) {
        this.compContractCode = compContractCode;
    }

    public String getBuyUnitPrice() {
        return this.buyUnitPrice;
    }

    public void setBuyUnitPrice(String buyUnitPrice) {
        this.buyUnitPrice = buyUnitPrice;
    }

    public String getInStockCount() {
        return this.inStockCount;
    }

    public void setInStockCount(String inStockCount) {
        this.inStockCount = inStockCount;
    }

    public String getAppointMoney() {
        return this.appointMoney;
    }

    public void setAppointMoney(String appointMoney) {
        this.appointMoney = appointMoney;
    }

    public String getReceiveInvoiceCount() {
        return this.receiveInvoiceCount;
    }

    public void setReceiveInvoiceCount(String receiveInvoiceCount) {
        this.receiveInvoiceCount = receiveInvoiceCount;
    }

    public String getReceiveInvoiceMoney() {
        return this.receiveInvoiceMoney;
    }

    public void setReceiveInvoiceMoney(String receiveInvoiceMoney) {
        this.receiveInvoiceMoney = receiveInvoiceMoney;
    }

    public String getBackGoodsMoney() {
        return this.backGoodsMoney;
    }

    public void setBackGoodsMoney(String backGoodsMoney) {
        this.backGoodsMoney = backGoodsMoney;
    }

}
