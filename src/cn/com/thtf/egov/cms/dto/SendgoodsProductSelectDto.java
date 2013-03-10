/**
 * ClassName  SendgoodsProductSelectDto
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-4-24
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

import java.sql.Timestamp;

/**
 * SendgoodsProductSelectDto
 * 
 * @author Lubo
 */

public class SendgoodsProductSelectDto {

    /** id */
    private Integer id;
    /** id */
    private String sendGoodsId;

    /** 产品ID */
    private String productId;
    /** 产品编码 */
    private String productCode;
    /** 产品名称 */
    private String productName;
    /** 规格型号 */
    private String productType;
    /** 单位 */
    private String productUnit;

    /** 销售数 */
    private Integer count;
    /** 销售单价 */
    private String price;
    /** 销售金额 */
    private String money;

    /** 已发货数 */
    private Integer yfhNum;
    /** 其它库房备货数 */
    private Integer qtkfNum;

    /** 本库房备货数 */
    private Integer bkfNum;
    /** 发货可用数 */
    private Integer fhkyNum;
    /** 退货数 */
    private Integer thsNum;
    /** 时间戳**/
    private Timestamp timeStamp;
    
    /**已备货数**/    
    private Integer ybfNum;
    
    public Integer getYbfNum() {
        return ybfNum;
    }

    public void setYbfNum(Integer ybfNum) {
        this.ybfNum = ybfNum;
    }

    /**
     * @return the sendGoodsId
     */
    public String getSendGoodsId() {
        return sendGoodsId;
    }

    /**
     * @param sendGoodsId the sendGoodsId to set
     */
    public void setSendGoodsId(String sendGoodsId) {
        this.sendGoodsId = sendGoodsId;
    }
    
    /**
     * @return the timeStamp
     */
    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    /**
     * @param timeStamp the timeStamp to set
     */
    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * @return the productId
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

    /**
     * @return the price
     */
    public String getPrice() {
        return price;
    }

    /**
     * @param price
     *            the price to set
     */
    public void setPrice(String price) {
        this.price = price;
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
     * @return the yfhNum
     */
    public Integer getYfhNum() {
        return yfhNum;
    }

    /**
     * @param yfhNum
     *            the yfhNum to set
     */
    public void setYfhNum(Integer yfhNum) {
        this.yfhNum = yfhNum;
    }

    /**
     * @return the qtkfNum
     */
    public Integer getQtkfNum() {
        return qtkfNum;
    }

    /**
     * @param qtkfNum
     *            the qtkfNum to set
     */
    public void setQtkfNum(Integer qtkfNum) {
        this.qtkfNum = qtkfNum;
    }

    /**
     * @return the bkfNum
     */
    public Integer getBkfNum() {
        return bkfNum;
    }

    /**
     * @param bkfNum
     *            the bkfNum to set
     */
    public void setBkfNum(Integer bkfNum) {
        this.bkfNum = bkfNum;
    }

    /**
     * @return the fhkyNum
     */
    public Integer getFhkyNum() {
        return fhkyNum;
    }

    /**
     * @param fhkyNum
     *            the fhkyNum to set
     */
    public void setFhkyNum(Integer fhkyNum) {
        this.fhkyNum = fhkyNum;
    }

    /**
     * @return the thsNum
     */
    public Integer getThsNum() {
        return thsNum;
    }

    /**
     * @param thsNum
     *            the thsNum to set
     */
    public void setThsNum(Integer thsNum) {
        this.thsNum = thsNum;
    }

}
