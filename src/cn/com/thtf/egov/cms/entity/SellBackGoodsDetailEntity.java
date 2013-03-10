package cn.com.thtf.egov.cms.entity;

import cn.com.thtf.egov.cms.application.BaseEntity;

public class SellBackGoodsDetailEntity extends BaseEntity {

    private static final long serialVersionUID = -6223349706191341633L;

    private Integer id;

    private String sellBackGoodsId;

    private Integer productId;

    private Integer count;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSellBackGoodsId() {
        return sellBackGoodsId;
    }

    public void setSellBackGoodsId(String sellBackGoodsId) {
        this.sellBackGoodsId = sellBackGoodsId;
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