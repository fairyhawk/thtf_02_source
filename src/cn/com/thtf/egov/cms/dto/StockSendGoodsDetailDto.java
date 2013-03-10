/**
 * ClassName  StockSendGoodsDetailDto
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-6-9
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

/**
 * StockSendGoodsDetailDto
 * 
 * @author Lubo
 */
public class StockSendGoodsDetailDto {

    /** 发货单类型 */
    private String orderType;
    /** 发货单号 */
    private String orderId;
    /** 产品编码 */
    private String productCode;
    /** 产品名称 */
    private String productName;
    /** 产品型号 */
    private String productType;
    /** 产品单位 */
    private String productUnit;

    /** 发货数量 */
    private String count;
    /** 库存数 */
    private String num;
    /** 库房名称 */
    private String stockroomName;
    /** 配送方式 */
    private String transportWay;

    // /** str */
    // private String str;
    //
    // /**
    // * @return the str
    // */
    // public String getStr() {
    // return str;
    // }
    //
    // /**
    // * @param str the str to set
    // */
    // public void setStr(String str) {
    // this.str = str;
    // }

    public String getTransportWay() {
        return transportWay;
    }

    public void setTransportWay(String transportWay) {
        this.transportWay = transportWay;
    }

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
     * @return the orderType
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     * @param orderType the orderType to set
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
     * @return the num
     */
    public String getNum() {
        return num;
    }

    /**
     * @param num
     *            the num to set
     */
    public void setNum(String num) {
        this.num = num;
    }

}
