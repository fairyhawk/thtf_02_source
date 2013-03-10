package cn.com.thtf.egov.cms.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class BuyContractProductInfoDto implements Serializable {

    /**
     * 产品选择Dto
     * 
     * @author HanHaiyun
     */
    private static final long serialVersionUID = 2997053529503927379L;

    // 产品id
    private Integer prodId;

    // 产品编号
    private String prodCode;

    // 产品名称
    private String prodName;

    // 规格型号
    private String prodType;

    // 单位
    private String prodUnit;

    // 采购数
    private Integer buyCount;

    // 采购单价
    private BigDecimal buyPrice;

    // 产品分类名称
    private String prodTypeName;

    // 采购金额
    private BigDecimal buyMoney;

    // 入库金额
    private BigDecimal instockMoney;

    // 指定金额
    private BigDecimal appointMoney;

    // 收票金额
    private BigDecimal receiveInvoiceMoney;

    // 已退货合同数
    private BigDecimal backContractCount;

    // 退货合同金额
    private BigDecimal backContractMoney;
    // 退货合同数
    private BigDecimal buyBackContractCount;

    // 返厂数
    private Integer backCount;

    // 返厂金额
    private BigDecimal returnMoney;

    private BigDecimal stockTotalCount;// 库存总数

    // 库存总数
    private String stockTotalNum;
    // 需求数
    private String demandCount;
    // 库存单价
    private String stockUnitPrice;
    // 增长率
    private String increaserate;

    // 库存单价(未上税)
    private String noTaxOfPrice;
    public BigDecimal getBuyBackContractCount() {
        return buyBackContractCount;
    }

    public void setBuyBackContractCount(BigDecimal buyBackContractCount) {
        this.buyBackContractCount = buyBackContractCount;
    }

    public String getStockTotalNum() {
        return stockTotalNum;
    }

    public void setStockTotalNum(String stockTotalNum) {
        this.stockTotalNum = stockTotalNum;
    }

    public String getIncreaserate() {
        return increaserate;
    }

    public void setIncreaserate(String increaserate) {
        this.increaserate = increaserate;
    }

    public BigDecimal getBackContractCount() {
        return this.backContractCount;
    }

    public void setBackContractCount(BigDecimal backContractCount) {
        this.backContractCount = backContractCount;
    }

    public Integer getBackCount() {
        return this.backCount;
    }

    public void setBackCount(Integer backCount) {
        this.backCount = backCount;
    }

    public BigDecimal getBuyMoney() {
        return buyMoney;
    }

    public void setBuyMoney(BigDecimal buyMoney) {
        this.buyMoney = buyMoney;
    }

    public BigDecimal getInstockMoney() {
        return instockMoney;
    }

    public void setInstockMoney(BigDecimal instockMoney) {
        this.instockMoney = instockMoney;
    }

    public BigDecimal getAppointMoney() {
        return appointMoney;
    }

    public void setAppointMoney(BigDecimal appointMoney) {
        this.appointMoney = appointMoney;
    }

    public BigDecimal getReceiveInvoiceMoney() {
        return receiveInvoiceMoney;
    }

    public void setReceiveInvoiceMoney(BigDecimal receiveInvoiceMoney) {
        this.receiveInvoiceMoney = receiveInvoiceMoney;
    }

    public BigDecimal getBackContractMoney() {
        return backContractMoney;
    }

    public void setBackContractMoney(BigDecimal backContractMoney) {
        this.backContractMoney = backContractMoney;
    }

    public BigDecimal getReturnMoney() {
        return returnMoney;
    }

    public void setReturnMoney(BigDecimal returnMoney) {
        this.returnMoney = returnMoney;
    }

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public String getProdCode() {
        return prodCode;
    }

    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdType() {
        return prodType;
    }

    public void setProdType(String prodType) {
        this.prodType = prodType;
    }

    public String getProdUnit() {
        return prodUnit;
    }

    public void setProdUnit(String prodUnit) {
        this.prodUnit = prodUnit;
    }

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public BigDecimal getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(BigDecimal buyPrice) {
        this.buyPrice = buyPrice;
    }

    public String getProdTypeName() {
        return prodTypeName;
    }

    public void setProdTypeName(String prodTypeName) {
        this.prodTypeName = prodTypeName;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public BigDecimal getStockTotalCount() {
        return stockTotalCount;
    }

    public void setStockTotalCount(BigDecimal stockTotalCount) {
        this.stockTotalCount = stockTotalCount;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getDemandCount() {
        return demandCount;
    }

    public void setDemandCount(String demandCount) {
        this.demandCount = demandCount;
    }

    public String getStockUnitPrice() {
        return stockUnitPrice;
    }

    public void setStockUnitPrice(String stockUnitPrice) {
        this.stockUnitPrice = stockUnitPrice;
    }

	public String getNoTaxOfPrice() {
		return noTaxOfPrice;
	}

	public void setNoTaxOfPrice(String noTaxOfPrice) {
		this.noTaxOfPrice = noTaxOfPrice;
	}

}
