package cn.com.thtf.egov.cms.entity;

import cn.com.thtf.egov.cms.application.BaseEntity;

public class RoleMEntity extends BaseEntity {

    private static final long serialVersionUID = 3546637923333881705L;

    private Integer id;

    private String name;

    public RoleMEntity() {

    }

    public RoleMEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

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
