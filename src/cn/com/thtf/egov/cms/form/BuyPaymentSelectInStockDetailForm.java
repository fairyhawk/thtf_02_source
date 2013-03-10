package cn.com.thtf.egov.cms.form;

import org.apache.struts.action.ActionForm;

public class BuyPaymentSelectInStockDetailForm extends ActionForm {

    /**
     * 自动序列化
     */
    private static final long serialVersionUID = -7140727488852827246L;
    /** 入库单号 **/
    private String inStockId;
    /** 产品合同号 **/
    private String productContractCode;
    /** 公司合同号 **/
    private String companyContractCode;
    /** 产品编码 **/
    private String productNo;
    /** 产品名称 **/
    private String productName;
    /** 规格型号 **/
    private String productType;
    /**入库要求起始日期**/
    private String startRequestAccountDate;
    /**入库要求终止日期**/
    private String endRequestAccountDate;
    
    public String getStartRequestAccountDate() {
        return startRequestAccountDate;
    }

    public void setStartRequestAccountDate(String startRequestAccountDate) {
        this.startRequestAccountDate = startRequestAccountDate;
    }

    public String getEndRequestAccountDate() {
        return endRequestAccountDate;
    }

    public void setEndRequestAccountDate(String endRequestAccountDate) {
        this.endRequestAccountDate = endRequestAccountDate;
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

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
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

    public String getInStockId() {
        return inStockId;
    }

    public void setInStockId(String inStockId) {
        this.inStockId = inStockId;
    }
    
}
