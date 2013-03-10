package cn.com.thtf.egov.cms.entity;

import cn.com.thtf.egov.cms.application.BaseEntity;

public class BuyBackGoodsDetailEntity extends BaseEntity {

    private static final long serialVersionUID = 4593003138940068479L;

    private Integer id;

    private String buyBackGoodsId;

    private Integer productId;

    private Integer count;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBuyBackGoodsId() {
        return buyBackGoodsId;
    }

    public void setBuyBackGoodsId(String buyBackGoodsId) {
        this.buyBackGoodsId = buyBackGoodsId;
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