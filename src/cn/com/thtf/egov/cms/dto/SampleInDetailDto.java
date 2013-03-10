package cn.com.thtf.egov.cms.dto;

import cn.com.thtf.egov.cms.application.BaseEntity;

public class SampleInDetailDto extends BaseEntity {

    private static final long serialVersionUID = 7684398902170889553L;

    private String id;

    private String sampleInId;

    private String productId;

    private String count;

   

    public String getSampleInId() {
        return sampleInId;
    }

    public void setSampleInId(String sampleInId) {
        this.sampleInId = sampleInId;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the productId
     */
    public String getProductId() {
        return productId;
    }

    /**
     * @param productId the productId to set
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * @return the count
     */
    public String getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(String count) {
        this.count = count;
    }

    /**
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

   
}