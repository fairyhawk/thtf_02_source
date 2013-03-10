package cn.com.thtf.egov.cms.entity;

import cn.com.thtf.egov.cms.application.BaseEntity;

public class BrandEntity extends BaseEntity {

    private static final long serialVersionUID = -5093538170723507692L;

    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
