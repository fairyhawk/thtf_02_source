package cn.com.thtf.egov.cms.entity;

import cn.com.thtf.egov.cms.application.BaseEntity;

public class BuyBackContractDetailEntity extends BaseEntity {

    private static final long serialVersionUID = 50156713098266929L;

    private Integer id;

    private String buyBackContractId;

    private Integer productId;

    private Integer count;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBuyBackContractId() {
        return buyBackContractId;
    }

    public void setBuyBackContractId(String buyBackContractId) {
        this.buyBackContractId = buyBackContractId;
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