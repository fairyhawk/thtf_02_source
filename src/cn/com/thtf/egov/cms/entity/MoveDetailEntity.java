package cn.com.thtf.egov.cms.entity;

import java.math.BigDecimal;

import cn.com.thtf.egov.cms.application.BaseEntity;

public class MoveDetailEntity extends BaseEntity {

    private static final long serialVersionUID = 4448805295724586493L;

    private Integer id;

    private String moveId;

    private Integer productId;

    private Integer count;

    private BigDecimal price;

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMoveId() {
        return moveId;
    }

    public void setMoveId(String moveId) {
        this.moveId = moveId;
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
}