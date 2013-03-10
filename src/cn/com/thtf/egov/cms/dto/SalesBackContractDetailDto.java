package cn.com.thtf.egov.cms.dto;

import java.math.BigDecimal;

public class SalesBackContractDetailDto {

    private Integer id;

    private String productCode;

    private Integer productId;

    private String productName;

    private String productType;

    private String productUnit;

    private Integer sellCount;

    private BigDecimal sellPrice;

    private BigDecimal sellMoney;

    private BigDecimal sendGoodsMoney;

    private BigDecimal preparedGoodsMoney;

    private BigDecimal appointMoney;

    private BigDecimal makeInvoiceMoney;

    private BigDecimal backedContractCount;

    private Integer backCount;

    private Integer backContractCount;

    private BigDecimal backContractMoney;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public Integer getSellCount() {
        return sellCount;
    }

    public void setSellCount(Integer sellCount) {
        this.sellCount = sellCount;
    }

    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }

    public BigDecimal getSellMoney() {
        return sellMoney;
    }

    public void setSellMoney(BigDecimal sellMoney) {
        this.sellMoney = sellMoney;
    }

    public BigDecimal getSendGoodsMoney() {
        return sendGoodsMoney;
    }

    public void setSendGoodsMoney(BigDecimal sendGoodsMoney) {
        this.sendGoodsMoney = sendGoodsMoney;
    }

    public BigDecimal getPreparedGoodsMoney() {
        return preparedGoodsMoney;
    }

    public void setPreparedGoodsMoney(BigDecimal preparedGoodsMoney) {
        this.preparedGoodsMoney = preparedGoodsMoney;
    }

    public BigDecimal getAppointMoney() {
        return appointMoney;
    }

    public void setAppointMoney(BigDecimal appointMoney) {
        this.appointMoney = appointMoney;
    }

    public BigDecimal getMakeInvoiceMoney() {
        return makeInvoiceMoney;
    }

    public void setMakeInvoiceMoney(BigDecimal makeInvoiceMoney) {
        this.makeInvoiceMoney = makeInvoiceMoney;
    }

    public BigDecimal getBackedContractCount() {
        return backedContractCount;
    }

    public void setBackedContractCount(BigDecimal backedContractCount) {
        this.backedContractCount = backedContractCount;
    }

    public Integer getBackCount() {
        return backCount;
    }

    public void setBackCount(Integer backCount) {
        this.backCount = backCount;
    }

    public Integer getBackContractCount() {
        return backContractCount;
    }

    public void setBackContractCount(Integer backContractCount) {
        this.backContractCount = backContractCount;
    }

    public BigDecimal getBackContractMoney() {
        return backContractMoney;
    }

    public void setBackContractMoney(BigDecimal backContractMoney) {
        this.backContractMoney = backContractMoney;
    }

}
