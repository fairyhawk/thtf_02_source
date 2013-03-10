package cn.com.thtf.egov.cms.entity;

import cn.com.thtf.egov.cms.application.BaseEntity;

public class StockroomEntity extends BaseEntity {

    private static final long serialVersionUID = 1806699713651966122L;

    private Integer id;

    private Integer type;

    private String name;

    private Integer productDeptId;
    
    private String shortName;

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProductDeptId() {
        return productDeptId;
    }

    public void setProductDeptId(Integer productDeptId) {
        this.productDeptId = productDeptId;
    }
}
