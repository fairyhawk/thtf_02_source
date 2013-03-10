package cn.com.thtf.egov.cms.entity;

import java.math.BigDecimal;

import cn.com.thtf.egov.cms.application.BaseEntity;

public class SampleOutDetailEntity extends BaseEntity {

    private static final long serialVersionUID = -8865297258127537050L;

    private Integer id;

    private String sampleOutId;

    private Integer productId;

    private Integer count;

    private BigDecimal price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSampleOutId() {
        return sampleOutId;
    }

    public void setSampleOutId(String sampleOutId) {
        this.sampleOutId = sampleOutId;
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