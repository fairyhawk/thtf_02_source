package cn.com.thtf.egov.cms.entity;

import java.math.BigDecimal;

import cn.com.thtf.egov.cms.application.BaseEntity;

public class ReceiveInvoiceDetailEntity extends BaseEntity {

    private static final long serialVersionUID = -6645804251729950996L;

    private Integer id;

    private String receiveInvoiceId;

    private String inStockId;

    private Integer productId;

    private Integer count;

    private BigDecimal money;
    private String taxRate;// 合同税率
    private String prodMoney;// 产品库存金额
    private BigDecimal prodPrice;// 合同价格

    public BigDecimal getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(BigDecimal prodPrice) {
        this.prodPrice = prodPrice;
    }

   // public String getReceiveRate() {
     //   return receiveRate;
   // }

   // public void setReceiveRate(String receiveRate) {
    //    this.receiveRate = receiveRate;
  //  }

    //private String receiveRate;// 发票税率

    public String getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }

    public String getProdMoney() {
        return prodMoney;
    }

    public void setProdMoney(String prodMoney) {
        this.prodMoney = prodMoney;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReceiveInvoiceId() {
        return receiveInvoiceId;
    }

    public void setReceiveInvoiceId(String receiveInvoiceId) {
        this.receiveInvoiceId = receiveInvoiceId;
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
}