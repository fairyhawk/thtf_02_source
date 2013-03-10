package cn.com.thtf.egov.cms.entity;

import cn.com.thtf.egov.cms.application.BaseEntity;

public class SellInvoiceDetailEntity extends BaseEntity {

    private static final long serialVersionUID = -1500730796324891585L;

    private Integer id;

    private Integer sellInvoiceId;

    private String sendGoodsId;

    private Integer productId;

    private Integer count;

    private String money;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSellInvoiceId() {
        return sellInvoiceId;
    }

    public void setSellInvoiceId(Integer sellInvoiceId) {
        this.sellInvoiceId = sellInvoiceId;
    }

    public String getSendGoodsId() {
        return sendGoodsId;
    }

    public void setSendGoodsId(String sendGoodsId) {
        this.sendGoodsId = sendGoodsId;
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

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

}