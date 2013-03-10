package cn.com.thtf.egov.cms.entity;

import java.math.BigDecimal;

import cn.com.thtf.egov.cms.application.BaseEntity;

public class InStockDetailEntity extends BaseEntity {

    private static final long serialVersionUID = 1734505124689184937L;

    private Integer id;

    private String inStockId;

    private Integer productId;

    private Integer count;

    private BigDecimal price;

    private Integer tacRate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInStockId() {
        return inStockId;
    }

    public void setInStockId(String inStockId) {
        this.inStockId = inStockId;
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

    public Integer getTacRate() {
        return tacRate;
    }

    public void setTacRate(Integer tacRate) {
        this.tacRate = tacRate;
    }
}