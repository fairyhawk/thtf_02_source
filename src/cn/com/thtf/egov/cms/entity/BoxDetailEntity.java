package cn.com.thtf.egov.cms.entity;

import cn.com.thtf.egov.cms.application.BaseEntity;

public class BoxDetailEntity extends BaseEntity {

    private static final long serialVersionUID = 8489956010680524779L;

    private Integer id;

    private String boxId;

    private String sendGoodsId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBoxId() {
        return boxId;
    }

    public void setBoxId(String boxId) {
        this.boxId = boxId;
    }

    public String getSendGoodsId() {
        return sendGoodsId;
    }

    public void setSendGoodsId(String sendGoodsId) {
        this.sendGoodsId = sendGoodsId;
    }
}