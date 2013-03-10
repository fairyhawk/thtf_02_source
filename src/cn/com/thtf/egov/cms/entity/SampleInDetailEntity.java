package cn.com.thtf.egov.cms.entity;

import cn.com.thtf.egov.cms.application.BaseEntity;

public class SampleInDetailEntity extends BaseEntity {

    private static final long serialVersionUID = 7684398902170889553L;

    private Integer id;

    private String sampleInId;

    private Integer productId;

    private Integer count;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSampleInId() {
        return sampleInId;
    }

    public void setSampleInId(String sampleInId) {
        this.sampleInId = sampleInId;
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