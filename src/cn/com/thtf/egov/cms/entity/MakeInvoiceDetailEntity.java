package cn.com.thtf.egov.cms.entity;

import java.math.BigDecimal;

import cn.com.thtf.egov.cms.application.BaseEntity;

public class MakeInvoiceDetailEntity extends BaseEntity {

    private static final long serialVersionUID = -2130726262706333449L;

    private Integer id;

    private Integer makeInvoiceId;

    private String sendGoodsId;

    private Integer productId;

    private Integer count;

    private BigDecimal money;

    private Integer flag;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMakeInvoiceId() {
        return makeInvoiceId;
    }

    public void setMakeInvoiceId(Integer makeInvoiceId) {
        this.makeInvoiceId = makeInvoiceId;
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}
}
