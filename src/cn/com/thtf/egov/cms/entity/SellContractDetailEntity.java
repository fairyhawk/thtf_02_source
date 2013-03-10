package cn.com.thtf.egov.cms.entity;

import cn.com.thtf.egov.cms.application.BaseEntity;

public class SellContractDetailEntity extends BaseEntity {

    private static final long serialVersionUID = 1708577125404156594L;

    private Integer id;

    private String sellContractId;

    private String productId;

    private Integer count;

    private String buyPrice;

    private String price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSellContractId() {
        return sellContractId;
    }

    public void setSellContractId(String sellContractId) {
        this.sellContractId = sellContractId;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(String buyPrice) {
        this.buyPrice = buyPrice;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
