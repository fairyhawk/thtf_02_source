package cn.com.thtf.egov.cms.dto;

import java.math.BigDecimal;
/**
 * 销售退货管理
 * 产品信息DTO
 * @author lxs
 */
public class NewReturnsProductDto {

    /** 发货单号 **/
    private String sendId;

    /** 产品合同号 **/
    private String productContractCode;

    /** 公司合同号 **/
    private String companyContractCode;

    /** 产品编码 **/
    private String productCode;

    /** 产品名称 **/
    private String productName;

    /** 规格型号 **/
    private String productType;

    /** 单位 **/
    private String productUnit;

    /** 销售单价 **/
    private BigDecimal price;

    /** 发货数 **/
    private Integer ffcount;

    /** 备货数 **/
    private Integer bfcount;

    /** 指定金额 **/
    private BigDecimal zdmoney;

    /** 开票金额 **/
    private BigDecimal kpmoney;

    /** 已退货数 **/
    private Integer yBackCount;

    /** 退货数 **/
    private Integer thcount;

    /** 退货金额 **/
    private BigDecimal thmoney;
    
    /**发货金额**/
    private BigDecimal ffMoney;
    
    public BigDecimal getFfMoney() {
        return ffMoney;
    }

    public void setFfMoney(BigDecimal ffMoney) {
        this.ffMoney = ffMoney;
    }

    /** 产品编号 **/
    private Integer productId;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getSendId() {
        return sendId;
    }

    public void setSendId(String sendId) {
        this.sendId = sendId;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getFfcount() {
        return ffcount;
    }

    public void setFfcount(Integer ffcount) {
        this.ffcount = ffcount;
    }

    public Integer getBfcount() {
        return bfcount;
    }

    public void setBfcount(Integer bfcount) {
        this.bfcount = bfcount;
    }

    public BigDecimal getZdmoney() {
        return zdmoney;
    }

    public void setZdmoney(BigDecimal zdmoney) {
        this.zdmoney = zdmoney;
    }

    public BigDecimal getKpmoney() {
        return kpmoney;
    }

    public void setKpmoney(BigDecimal kpmoney) {
        this.kpmoney = kpmoney;
    }



    public Integer getyBackCount() {
        return yBackCount;
    }

    public void setyBackCount(Integer yBackCount) {
        this.yBackCount = yBackCount;
    }

    public Integer getThcount() {
        return thcount;
    }

    public void setThcount(Integer thcount) {
        this.thcount = thcount;
    }

    public BigDecimal getThmoney() {
        return thmoney;
    }

    public void setThmoney(BigDecimal thmoney) {
        this.thmoney = thmoney;
    }


}
