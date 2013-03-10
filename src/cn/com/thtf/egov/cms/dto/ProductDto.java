/**
 * ClassName  ProductDto
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-5-9
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

import java.math.BigDecimal;

/**
 * ProductDto
 * 
 * @author Lubo
 */

public class ProductDto {

    /** 产品编号 */
    private String productId;
    /** 发货数 */
    private String count;
    /** 库存数 */
    private String num;
    /** 销售单价 */
    private String price;
    /** 销售金额 */
    private BigDecimal money;
    /** 单号 */
    private String orderId;
    
    /**
     * @return the orderId
     */
    public String getOrderId() {
        return orderId;
    }
    /**
     * @param orderId the orderId to set
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    /**
     * @return the num
     */
    public String getNum() {
        return num;
    }
    /**
     * @param num the num to set
     */
    public void setNum(String num) {
        this.num = num;
    }
    /**
     * @return the price
     */
    public String getPrice() {
        return price;
    }
    /**
     * @param price the price to set
     */
    public void setPrice(String price) {
        this.price = price;
    } 
    
    /**
     * @return the productId
     */
    public String getProductId() {
        return productId;
    }
    /**
     * @param productId the productId to set
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }
    /**
     * @return the count
     */
    public String getCount() {
        return count;
    }
    /**
     * @param count the count to set
     */
    public void setCount(String count) {
        this.count = count;
    }
    /**
     * @return the money
     */
    public BigDecimal getMoney() {
        return money;
    }
    /**
     * @param money the money to set
     */
    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
