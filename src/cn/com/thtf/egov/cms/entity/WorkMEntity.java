package cn.com.thtf.egov.cms.entity;

import cn.com.thtf.egov.cms.application.BaseEntity;

public class WorkMEntity extends BaseEntity {

    private static final long serialVersionUID = 1812767691435097310L;

    private Integer id;

    private String name;

    private String nodeId;

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

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }
}
