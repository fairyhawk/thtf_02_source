package cn.com.thtf.egov.cms.dto;

import java.io.Serializable;

/**
 * 采购退货合同预览__产品信息
 * 
 * @author HanHaiyun
 */
public class BuyBackContractPreviewProdDto implements Serializable {

    private static final long serialVersionUID = -5174039096276789239L;
    /**
     * 产品名称
     */
    private String prodName;
    /**
     * 规格型号
     */
    private String prodType;
    /**
     * 计量单位
     */
    private String prodUnit;
    /**
     * 退货数量
     */
    private Integer backContractProdCount;
    /**
     * 单价
     */
    private String buyContractPrice;
    /**
     * 金额
     */
    private String money;
    /**
     * 采购数量
     */
    private String buyContractCount;
    /**
     * 库存总数
     */
    private String prodCount;

    public String getBuyContractCount() {
        return buyContractCount;
    }

    public void setBuyContractCount(String buyContractCount) {
        this.buyContractCount = buyContractCount;
    }

    public String getProdCount() {
        return prodCount;
    }

    public void setProdCount(String prodCount) {
        this.prodCount = prodCount;
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

    public Integer getBackContractProdCount() {
        return backContractProdCount;
    }

    public void setBackContractProdCount(Integer backContractProdCount) {
        this.backContractProdCount = backContractProdCount;
    }

    public String getBuyContractPrice() {
        return buyContractPrice;
    }

    public void setBuyContractPrice(String buyContractPrice) {
        this.buyContractPrice = buyContractPrice;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
