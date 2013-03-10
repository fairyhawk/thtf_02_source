/**
 * ClassName  StockQueryDto
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-6-3
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

import java.util.List;

import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.entity.UserStockroomProductEntity;

/**
 * StockQueryDto
 * 
 * @author Lubo
 */
public class StockQueryDto {

    /** 产品分类 */
    private String productTypeId;
    /** 产品系列 */
    private String productSerieId;
    /** 库房 */
    private String stockroomId;
    /** 库房类型 */
    private String stockroomType;
    /** 备货类型 */
    private String sendgoodsType;

    /** 产品编码 */
    private String productCode;
    /** 产品名称 */
    private String productName;
    /** 产品类型 */
    private String productType;

    /** 当前用户负责的产品分类 */
    private String productTypeIdArr;
    /** 当前用户负责的库房 */
    private String stockroomIdArr;
    /** 当前用户 */
    private UserEntity user;
    /** 产品ID */
    private String productId;

    private List<UserStockroomProductEntity> userStockroomList;

    /**
     * @return the userStockroomList
     */
    public List<UserStockroomProductEntity> getUserStockroomList() {
        return userStockroomList;
    }

    /**
     * @param userStockroomList
     *            the userStockroomList to set
     */
    public void setUserStockroomList(List<UserStockroomProductEntity> userStockroomList) {
        this.userStockroomList = userStockroomList;
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
     * @return the user
     */
    public UserEntity getUser() {
        return user;
    }

    /**
     * @param user
     *            the user to set
     */
    public void setUser(UserEntity user) {
        this.user = user;
    }

    /**
     * @return the productTypeIdArr
     */
    public String getProductTypeIdArr() {
        return productTypeIdArr;
    }

    /**
     * @param productTypeIdArr
     *            the productTypeIdArr to set
     */
    public void setProductTypeIdArr(String productTypeIdArr) {
        this.productTypeIdArr = productTypeIdArr;
    }

    /**
     * @return the stockroomIdArr
     */
    public String getStockroomIdArr() {
        return stockroomIdArr;
    }

    /**
     * @param stockroomIdArr
     *            the stockroomIdArr to set
     */
    public void setStockroomIdArr(String stockroomIdArr) {
        this.stockroomIdArr = stockroomIdArr;
    }

    /**
     * @return the stockroomId
     */
    public String getStockroomId() {
        return stockroomId;
    }

    /**
     * @param stockroomId
     *            the stockroomId to set
     */
    public void setStockroomId(String stockroomId) {
        this.stockroomId = stockroomId;
    }

    /**
     * @return the stockroomType
     */
    public String getStockroomType() {
        return stockroomType;
    }

    /**
     * @param stockroomType
     *            the stockroomType to set
     */
    public void setStockroomType(String stockroomType) {
        this.stockroomType = stockroomType;
    }

    /**
     * @return the sendgoodsType
     */
    public String getSendgoodsType() {
        return sendgoodsType;
    }

    /**
     * @param sendgoodsType
     *            the sendgoodsType to set
     */
    public void setSendgoodsType(String sendgoodsType) {
        this.sendgoodsType = sendgoodsType;
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
     * @return the productSerieId
     */
    public String getProductSerieId() {
        return productSerieId;
    }

    /**
     * @param productSerieId
     *            the productSerieId to set
     */
    public void setProductSerieId(String productSerieId) {
        this.productSerieId = productSerieId;
    }

    /**
     * @return the productTypeId
     */
    public String getProductTypeId() {
        return productTypeId;
    }

    /**
     * @param productTypeId
     *            the productTypeId to set
     */
    public void setProductTypeId(String productTypeId) {
        this.productTypeId = productTypeId;
    }

}
