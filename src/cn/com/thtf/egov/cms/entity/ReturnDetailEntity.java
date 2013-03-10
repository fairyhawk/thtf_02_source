package cn.com.thtf.egov.cms.entity;

import java.math.BigDecimal;

import cn.com.thtf.egov.cms.application.BaseEntity;

public class ReturnDetailEntity extends BaseEntity {

    private static final long serialVersionUID = -5856589887338757028L;

    private Integer id;

    private String returnId;

    private Integer appointType;

    private String sellContractId;

    private String sendGoodsId;

    private Integer productId;

    private BigDecimal money;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReturnId() {
        return returnId;
    }

    public void setReturnId(String returnId) {
        this.returnId = returnId;
    }

    public Integer getAppointType() {
        return appointType;
    }

    public void setAppointType(Integer appointType) {
        this.appointType = appointType;
    }

    public String getSellContractId() {
        return sellContractId;
    }

    public void setSellContractId(String sellContractId) {
        this.sellContractId = sellContractId;
    }

    public String getSendGoodsId() {
        return sendGoodsId;
    }

    public void setSendGoodsId(String sendGoodsId) {
        this.sendGoodsId = sendGoodsId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
