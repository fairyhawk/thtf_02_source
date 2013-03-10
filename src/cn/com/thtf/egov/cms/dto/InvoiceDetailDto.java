package cn.com.thtf.egov.cms.dto;

import java.math.BigDecimal;

/**
 * 开票明细dto
 * 
 * @author lilewei
 */
public class InvoiceDetailDto {

    private Integer id;

    private Integer productId;// 产品id

    private String productCode; // 产品编码

    private String productName; // 产品名

    private String productUnit; // 产品单位

    private String productType;// 产品类型

    private BigDecimal price;// 销售单价

    private String makeInvoiceId;// 开票id

    private String sendGoodsId;// 发货单id

    private int count;// 开票数

    private BigDecimal money;// 开票金额

    private String productContractCode;//产品合同号10.12.1 发邮件显示 新填
        
    
    public String getProductContractCode() {
        return productContractCode;
    }

    public void setProductContractCode(String productContractCode) {
        this.productContractCode = productContractCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getMakeInvoiceId() {
        return makeInvoiceId;
    }

    public void setMakeInvoiceId(String makeInvoiceId) {
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

}
