package cn.com.thtf.egov.cms.entity;

import java.math.BigDecimal;
import java.util.Date;

import cn.com.thtf.egov.cms.application.BaseEntity;

public class CreditTypeLimitEntity extends BaseEntity {

    private static final long serialVersionUID = -2730959438160894240L;

    private Integer id;

    private Integer productTypeId;

    private Integer creditTypeId;

    private BigDecimal climit;

    private Date timeStamp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    public Integer getCreditTypeId() {
        return creditTypeId;
    }

    public void setCreditTypeId(Integer creditTypeId) {
        this.creditTypeId = creditTypeId;
    }

    public BigDecimal getClimit() {
        return climit;
    }

    public void setClimit(BigDecimal climit) {
        this.climit = climit;
    }

    public Date getTimeStamp() {
        return this.timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}
