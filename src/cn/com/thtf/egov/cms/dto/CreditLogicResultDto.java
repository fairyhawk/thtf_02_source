/**
 * ClassName  CreditLogicResultDto
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-4-20
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

import java.util.List;

/**
 * 发货信用判断 返回结果
 * 
 * @author Lubo
 */

public class CreditLogicResultDto {

    /** 信用判断结果 */
    private boolean isAllow;
    /** 产品分类 */
    private List<CustomerFundsDto> productList;

    // /** 使用多少在途 */
    // private BigDecimal useInRransit;
    // /** 使用多少回款 */
    // private BigDecimal useReturn;
    // /** 使用多少信用 */
    // private BigDecimal useCredit;
    // /** 使用了在途 */
    // private boolean useInRransit;
    // /** 使用了回款 */
    // private boolean useReturn;
    // /** 使用了信用 */
    // private boolean useCredit;

    /** 错误信息 */
    private String errorMsg;

    /** 客户信用信息 */
    private CustomerCreditCommonDto customerCreditCommonDto;

    /**
     * @return the customerCreditCommonDto
     */
    public CustomerCreditCommonDto getCustomerCreditCommonDto() {
        return customerCreditCommonDto;
    }

    /**
     * @param customerCreditCommonDto
     *            the customerCreditCommonDto to set
     */
    public void setCustomerCreditCommonDto(CustomerCreditCommonDto customerCreditCommonDto) {
        this.customerCreditCommonDto = customerCreditCommonDto;
    }

    /**
     * @return the productList
     */
    public List<CustomerFundsDto> getProductList() {
        return productList;
    }

    /**
     * @param productList
     *            the productList to set
     */
    public void setProductList(List<CustomerFundsDto> productList) {
        this.productList = productList;
    }

    /**
     * @return the isAllow
     */
    public boolean isAllow() {
        return isAllow;
    }

    /**
     * @param isAllow
     *            the isAllow to set
     */
    public void setAllow(boolean isAllow) {
        this.isAllow = isAllow;
    }

    /**
     * @return the errorMsg
     */
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * @param errorMsg
     *            the errorMsg to set
     */
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

}
