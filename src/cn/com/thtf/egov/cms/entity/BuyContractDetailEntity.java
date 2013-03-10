package cn.com.thtf.egov.cms.entity;

import java.math.BigDecimal;

import cn.com.thtf.egov.cms.application.BaseEntity;

public class BuyContractDetailEntity extends BaseEntity {

    private static final long serialVersionUID = 2378983359538381870L;

    private Integer id;

    private String buyContractId;

    private Integer productId;

    private Integer count;

    private BigDecimal price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBuyContractId() {
        return buyContractId;
    }

    public void setBuyContractId(String buyContractId) {
        this.buyContractId = buyContractId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}