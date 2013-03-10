package cn.com.thtf.egov.cms.form;

import org.apache.struts.action.ActionForm;

public class BuyPaymentSelectBuyContractForm extends ActionForm {

    /**
     * 自动序列化
     */
    private static final long serialVersionUID = -3417949101314418774L;
    
    /** 公司合同号 **/
    private String companyContractCode;
    /** 产品合同号 **/
    private String productContractCode;
    public String getCompanyContractCode() {
        return companyContractCode;
    }
    public void setCompanyContractCode(String companyContractCode) {
        this.companyContractCode = companyContractCode;
    }
    public String getProductContractCode() {
        return productContractCode;
    }
    public void setProductContractCode(String productContractCode) {
        this.productContractCode = productContractCode;
    }
    
}
