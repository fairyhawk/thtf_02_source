package cn.com.thtf.egov.cms.entity;

import java.math.BigDecimal;

import cn.com.thtf.egov.cms.application.BaseEntity;

public class PaymentDetailEntity extends BaseEntity {

    private static final long serialVersionUID = 8412868560829008166L;

    private Integer id;

    private String paymentId;

    private Integer appointType;

    private String buyContractId;

    private String inStockId;

    private Integer productId;

    private BigDecimal money;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Integer getAppointType() {
        return appointType;
    }

    public void setAppointType(Integer appointType) {
        this.appointType = appointType;
    }


    public String getBuyContractId() {
        return this.buyContractId;
    }

    public void setBuyContractId(String buyContractId) {
        this.buyContractId = buyContractId;
    }

    public String getInStockId() {
        return inStockId;
    }

    public void setInStockId(String inStockId) {
        this.inStockId = inStockId;
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