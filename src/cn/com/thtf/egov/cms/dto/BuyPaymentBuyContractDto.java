package cn.com.thtf.egov.cms.dto;

import java.math.BigDecimal;

public class BuyPaymentBuyContractDto {

    /* 合同ID* */
    private String buyContractId;

    public String getBuyContractId() {
        return buyContractId;
    }

    public void setBuyContractId(String buyContractId) {
        this.buyContractId = buyContractId;
    }

    /* 产品合同号* */
    private String productContractCode;
    /* 公司合同号* */
    private String companyContractCode;
    /* 合同金额* */
    private BigDecimal contractMomey;
    /* 入库金额* */
    private BigDecimal inStockMoney;
    /* 指定金额* */
    private BigDecimal zdMoney;
    /* 已预付金额* */
    private BigDecimal yysMoney;
    /* 收票金额* */
    private BigDecimal spMoney;
    /* 退货合同金额* */
    private BigDecimal backContractMoney;
    /* 返厂金额* */
    private BigDecimal fcMoney;
    /* 预收金额* */
    private BigDecimal yfMoney;

    /* 入库单号* */
    private String inStockId;
    /** 产品ID **/
    private String productId;
    /** 入库数 **/
    private String inStockCount;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getInStockCount() {
        return inStockCount;
    }

    public void setInStockCount(String inStockCount) {
        this.inStockCount = inStockCount;
    }

    /* 产品编码* */
    private String productCode;
    /* 产品名称* */
    private String productName;
    /* 规格型号* */
    private String productType;
    /* 单位* */
    private String productUnit;
    /* 采购单价* */
    private BigDecimal buyContractPrice;
    /* 已指定金额* */
    private BigDecimal yzdMoney;
    //要求到帐日期
    private String requestAccountDate;
    

    public String getRequestAccountDate() {
        return requestAccountDate;
    }

    public void setRequestAccountDate(String requestAccountDate) {
        this.requestAccountDate = requestAccountDate;
    }

    public String getInStockId() {
        return inStockId;
    }

    public void setInStockId(String inStockId) {
        this.inStockId = inStockId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
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

    public BigDecimal getBuyContractPrice() {
        return buyContractPrice;
    }

    public void setBuyContractPrice(BigDecimal buyContractPrice) {
        this.buyContractPrice = buyContractPrice;
    }

    public BigDecimal getYzdMoney() {
        return yzdMoney;
    }

    public void setYzdMoney(BigDecimal yzdMoney) {
        this.yzdMoney = yzdMoney;
    }

    public String getProductContractCode() {
        return productContractCode;
    }

    public void setProductContractCode(String productContractCode) {
        this.productContractCode = productContractCode;
    }

    public String getCompanyContractCode() {
        return companyContractCode;
    }

    public void setCompanyContractCode(String companyContractCode) {
        this.companyContractCode = companyContractCode;
    }

    public BigDecimal getContractMomey() {
        return contractMomey;
    }

    public void setContractMomey(BigDecimal contractMomey) {
        this.contractMomey = contractMomey;
    }

    public BigDecimal getInStockMoney() {
        return inStockMoney;
    }

    public void setInStockMoney(BigDecimal inStockMoney) {
        this.inStockMoney = inStockMoney;
    }

    public BigDecimal getZdMoney() {
        return zdMoney;
    }

    public void setZdMoney(BigDecimal zdMoney) {
        this.zdMoney = zdMoney;
    }

    public BigDecimal getYysMoney() {
        return yysMoney;
    }

    public void setYysMoney(BigDecimal yysMoney) {
        this.yysMoney = yysMoney;
    }

    public BigDecimal getSpMoney() {
        return spMoney;
    }

    public void setSpMoney(BigDecimal spMoney) {
        this.spMoney = spMoney;
    }

    public BigDecimal getBackContractMoney() {
        return backContractMoney;
    }

    public void setBackContractMoney(BigDecimal backContractMoney) {
        this.backContractMoney = backContractMoney;
    }

    public BigDecimal getFcMoney() {
        return fcMoney;
    }

    public void setFcMoney(BigDecimal fcMoney) {
        this.fcMoney = fcMoney;
    }

    public BigDecimal getYfMoney() {
        return yfMoney;
    }

    public void setYfMoney(BigDecimal yfMoney) {
        this.yfMoney = yfMoney;
    }

}
