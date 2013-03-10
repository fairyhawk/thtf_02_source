/**
 * ClassName  StockProductDto
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-6-4
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

/**
 * StockProductDto
 * 
 * @author Lubo
 */
public class StockProductDto {

    /** 产品分类名称 */
    private String productTypeName;
    /** 产品系列名称 */
    private String productSerieName;
    /** 产品编码 */
    private String productCode;
    /** 产品名称 */
    private String productName;
    /** 产品型号 */
    private String productType;

    /** 单位 */
    private String productUnit;
    /** 库存总数 */
    private String stockNum;
    /** 库存平均价 */
    private String stockPrice;
    /** 采购数 */
    private String buyContractCount;
    /** 发货数 */
    private String sellContractCount;

    /** 销售可用数 */
    private String sellUseCount;
    /** 冻结数 */
    private String stockSendLock;
    /** 备货数 */
    private String stockPrepared;
    /** 发货可用数 */
    private String sendGoodsUseCount;

    /** 库房的库存数 */
    private String stockroomNum;
    /** 产品ID */
    private String productId;
    /** 库房名 */
    private String stockroomName;

    /**
     * @return the stockroomName
     */
    public String getStockroomName() {
        return stockroomName;
    }

    /**
     * @param stockroomName the stockroomName to set
     */
    public void setStockroomName(String stockroomName) {
        this.stockroomName = stockroomName;
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
     * @return the productSerieName
     */
    public String getProductSerieName() {
        return productSerieName;
    }

    /**
     * @param productSerieName
     *            the productSerieName to set
     */
    public void setProductSerieName(String productSerieName) {
        this.productSerieName = productSerieName;
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
     * @return the stockNum
     */
    public String getStockNum() {
        return stockNum;
    }

    /**
     * @param stockNum
     *            the stockNum to set
     */
    public void setStockNum(String stockNum) {
        this.stockNum = stockNum;
    }

    /**
     * @return the stockPrice
     */
    public String getStockPrice() {
        return stockPrice;
    }

    /**
     * @param stockPrice
     *            the stockPrice to set
     */
    public void setStockPrice(String stockPrice) {
        this.stockPrice = stockPrice;
    }

    /**
     * @return the buyContractCount
     */
    public String getBuyContractCount() {
        return buyContractCount;
    }

    /**
     * @param buyContractCount
     *            the buyContractCount to set
     */
    public void setBuyContractCount(String buyContractCount) {
        this.buyContractCount = buyContractCount;
    }

    /**
     * @return the sellContractCount
     */
    public String getSellContractCount() {
        return sellContractCount;
    }

    /**
     * @param sellContractCount
     *            the sellContractCount to set
     */
    public void setSellContractCount(String sellContractCount) {
        this.sellContractCount = sellContractCount;
    }

    /**
     * @return the sellUseCount
     */
    public String getSellUseCount() {
        return sellUseCount;
    }

    /**
     * @param sellUseCount
     *            the sellUseCount to set
     */
    public void setSellUseCount(String sellUseCount) {
        this.sellUseCount = sellUseCount;
    }

    /**
     * @return the stockSendLock
     */
    public String getStockSendLock() {
        return stockSendLock;
    }

    /**
     * @param stockSendLock
     *            the stockSendLock to set
     */
    public void setStockSendLock(String stockSendLock) {
        this.stockSendLock = stockSendLock;
    }

    /**
     * @return the stockPrepared
     */
    public String getStockPrepared() {
        return stockPrepared;
    }

    /**
     * @param stockPrepared
     *            the stockPrepared to set
     */
    public void setStockPrepared(String stockPrepared) {
        this.stockPrepared = stockPrepared;
    }

    /**
     * @return the sendGoodsUseCount
     */
    public String getSendGoodsUseCount() {
        return sendGoodsUseCount;
    }

    /**
     * @param sendGoodsUseCount
     *            the sendGoodsUseCount to set
     */
    public void setSendGoodsUseCount(String sendGoodsUseCount) {
        this.sendGoodsUseCount = sendGoodsUseCount;
    }

    /**
     * @return the stockroomNum
     */
    public String getStockroomNum() {
        return stockroomNum;
    }

    /**
     * @param stockroomNum
     *            the stockroomNum to set
     */
    public void setStockroomNum(String stockroomNum) {
        this.stockroomNum = stockroomNum;
    }

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

}
