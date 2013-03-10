/**
 * ClassName  CustomerFundsDto
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-4-20
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

import java.math.BigDecimal;

/**
 * 客户回款、超期欠款、在途款
 * 
 * @author Lubo
 */

public class CustomerFundsDto {

    /** 产品分类名称 */
    private String productTypeName;
    /** 欠款金额 */
    private BigDecimal exceedMoney; 
    /** 回款金额 */
    private BigDecimal returnMoney; 
    /** 在途金额 */
    private BigDecimal inRransitMoney; 

    /**
     * @return the returnMoney
     */
    public BigDecimal getReturnMoney() {
        return returnMoney;
    }

    /**
     * @param returnMoney the returnMoney to set
     */
    public void setReturnMoney(BigDecimal returnMoney) {
        this.returnMoney = returnMoney;
    }

    /**
     * @return the inRransitMoney
     */
    public BigDecimal getInRransitMoney() {
        return inRransitMoney;
    }

    /**
     * @param inRransitMoney the inRransitMoney to set
     */
    public void setInRransitMoney(BigDecimal inRransitMoney) {
        this.inRransitMoney = inRransitMoney;
    }

    /**
     * @return the exceedMoney
     */
    public BigDecimal getExceedMoney() {
        return exceedMoney;
    }

    /**
     * @param exceedMoney the exceedMoney to set
     */
    public void setExceedMoney(BigDecimal exceedMoney) {
        this.exceedMoney = exceedMoney;
    }

    /**
     * @return the productTypeName
     */
    public String getProductTypeName() {
        return productTypeName;
    }

    /**
     * @param productTypeName
     *            the productTypeName to set
     */
    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }
}
