/**
 * ClassName  SellInvoiceDetailDto
 *
 * History
 * Create User: zhangzx
 * Create Date: 2010-5-20
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

import java.math.BigDecimal;

/**
 * 发票明细Dto
 * 
 * @author Administrator
 */
public class SellInvoiceDetailDto {
    private String sellInvoiceId; //发票id
    
    private String sendGoodsId; //发货单号
    
    private String productContractCode; //产品合同号
    
    private String companyContractCode; //公司合同号
    
    private String productCode; //产品编码
    
    private String productName; //产品名称
    
    private String productType; //规格型号
    
    private String productUnit; //单位
    
    private String count; //开票数
    
    private BigDecimal price; //销售单价
    
    private BigDecimal makeInvoicePrice; //开票金额 

   

    public String getSellInvoiceId() {
        return sellInvoiceId;
    }


    public void setSellInvoiceId(String sellInvoiceId) {
        this.sellInvoiceId = sellInvoiceId;
    }


    public String getSendGoodsId() {
        return sendGoodsId;
    }

   
    public void setSendGoodsId(String sendGoodsId) {
        this.sendGoodsId = sendGoodsId;
    }


    public String getProductContractCode() {
        return productContractCode;
    }


    public void setProductContractCode(String productContractCode) {
        this.productContractCode = productContractCode;
    }


    public String getCompanyContractCode() {
        return companyContractCode;
    }


    public void setCompanyContractCode(String companyContractCode) {
        this.companyContractCode = companyContractCode;
    }

    public String getProductCode() {
        return productCode;
    }


    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

 
    public String getProductName() {
        return productName;
    }


    public void setProductName(String productName) {
        this.productName = productName;
    }


    public String getProductType() {
        return productType;
    }


    public void setProductType(String productType) {
        this.productType = productType;
    }


    public String getProductUnit() {
        return productUnit;
    }


    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }


    public String getCount() {
        return count;
    }


    public void setCount(String count) {
        this.count = count;
    }


    public BigDecimal getPrice() {
        return price;
    }


    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public BigDecimal getMakeInvoicePrice() {
        return makeInvoicePrice;
    }


    public void setMakeInvoicePrice(BigDecimal makeInvoicePrice) {
        this.makeInvoicePrice = makeInvoicePrice;
    }

    
}
