package cn.com.thtf.egov.cms.entity;

import cn.com.thtf.egov.cms.application.BaseEntity;

public class UserProductEntity extends BaseEntity {

    private static final long serialVersionUID = -2751132139895362819L;

    private Integer id;

    private String userId;

    private Integer productTypeId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }
}
