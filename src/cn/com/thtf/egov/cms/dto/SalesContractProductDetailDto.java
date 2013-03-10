package cn.com.thtf.egov.cms.dto;

import java.math.BigDecimal;

/**
 * 
 * 销售合同产品明细dto
 * 
 * @author 李乐伟
 */
public class SalesContractProductDetailDto {

    private String contractId;// 合同id

    private String productId; // 产品id

    private String productCode;// 产品编码

    private String productName;// 产品名

    private String productType;// 产品类型

    private String productTypeId;// 产品类型ID

    private String productUnit;// 产品单位

    private Integer productUsableCount;// 产品销售可用数

    private String detailId; // 明细id

    private Integer detailCount;// 明细销售数量

    private BigDecimal detailBuyPrice;// 明细预计采购价

    private BigDecimal detailPrice;// 明细销售价格
    
    private BigDecimal limitPrice;//限价
    
	public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductTypeId() {
        return this.productTypeId;
    }

    public void setProductTypeId(String productTypeId) {
        this.productTypeId = productTypeId;
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

    public Integer getProductUsableCount() {
        return productUsableCount;
    }

    public void setProductUsableCount(Integer productUsableCount) {
        this.productUsableCount = productUsableCount;
    }

    public String getDetailId() {
        return this.detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    public Integer getDetailCount() {
        return detailCount;
    }

    public void setDetailCount(Integer detailCount) {
        this.detailCount = detailCount;
    }

    public BigDecimal getDetailBuyPrice() {
        return detailBuyPrice;
    }

    public void setDetailBuyPrice(BigDecimal detailBuyPrice) {
        this.detailBuyPrice = detailBuyPrice;
    }

    public BigDecimal getDetailPrice() {
        return detailPrice;
    }

    public void setDetailPrice(BigDecimal detailPrice) {
        this.detailPrice = detailPrice;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }
    
    public BigDecimal getLimitPrice() {
		return limitPrice;
	}

	public void setLimitPrice(BigDecimal limitPrice) {
		this.limitPrice = limitPrice;
	}

}
