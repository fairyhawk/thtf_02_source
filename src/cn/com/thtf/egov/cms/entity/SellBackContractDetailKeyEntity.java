package cn.com.thtf.egov.cms.entity;

import cn.com.thtf.egov.cms.application.BaseEntity;

public class SellBackContractDetailKeyEntity extends BaseEntity {

    private static final long serialVersionUID = 8551739565598958592L;

    private Integer count;

    private Integer id;

    private Integer productId;

    private String sellBackContractId;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getSellBackContractId() {
        return sellBackContractId;
    }

    public void setSellBackContractId(String sellBackContractId) {
        this.sellBackContractId = sellBackContractId;
    }
}
