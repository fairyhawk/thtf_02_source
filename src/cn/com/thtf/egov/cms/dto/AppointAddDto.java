/**
 * ClassName  AppointAddDto
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-5-19
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

/**
 * 新增回款指定
 * 
 * @author Lubo
 */

public class AppointAddDto {

    /** 回款ID */
    private String mreturnId;

    /** 回款明细ID */
    private String[] sendGoodsMrDetileIdArr;
    /** 发货单ID */
    private String[] sendGoodsIdArr;
    /** 产品ID */
    private String[] productIdArr;
    /** 指定金额 发货明细 */
    private String[] sendGoodsPriceArr;
    // /** 客户信用 */
    // private String[] sendGoodsCustomerCreditId;
    // /** 发货金额 */
    // private String[] sendGoodsMoneyArr;
    // /** 已指定金额 */
    // private String[] appointMoneyArr;

    /** 回款明细ID */
    private String[] sellMrDetileIdArr;
    /** 合同ID */
    private String[] sellContractIdArr;
    /** 指定金额 合同 */
    private String[] sellPriceArr;

    // /** 客户信用 */
    // private String[] sellCustomerCreditId;

    /**
     * @return the sendGoodsMrDetileIdArr
     */
    public String[] getSendGoodsMrDetileIdArr() {
        return sendGoodsMrDetileIdArr;
    }

    /**
     * @param sendGoodsMrDetileIdArr
     *            the sendGoodsMrDetileIdArr to set
     */
    public void setSendGoodsMrDetileIdArr(String[] sendGoodsMrDetileIdArr) {
        this.sendGoodsMrDetileIdArr = sendGoodsMrDetileIdArr;
    }

    /**
     * @return the sellMrDetileIdArr
     */
    public String[] getSellMrDetileIdArr() {
        return sellMrDetileIdArr;
    }

    /**
     * @param sellMrDetileIdArr
     *            the sellMrDetileIdArr to set
     */
    public void setSellMrDetileIdArr(String[] sellMrDetileIdArr) {
        this.sellMrDetileIdArr = sellMrDetileIdArr;
    }

    /**
     * @return the mreturnId
     */
    public String getMreturnId() {
        return mreturnId;
    }

    /**
     * @param mreturnId
     *            the mreturnId to set
     */
    public void setMreturnId(String mreturnId) {
        this.mreturnId = mreturnId;
    }

    /**
     * @return the sendGoodsIdArr
     */
    public String[] getSendGoodsIdArr() {
        return sendGoodsIdArr;
    }

    /**
     * @param sendGoodsIdArr
     *            the sendGoodsIdArr to set
     */
    public void setSendGoodsIdArr(String[] sendGoodsIdArr) {
        this.sendGoodsIdArr = sendGoodsIdArr;
    }

    /**
     * @return the productIdArr
     */
    public String[] getProductIdArr() {
        return productIdArr;
    }

    /**
     * @param productIdArr
     *            the productIdArr to set
     */
    public void setProductIdArr(String[] productIdArr) {
        this.productIdArr = productIdArr;
    }

    /**
     * @return the sellContractIdArr
     */
    public String[] getSellContractIdArr() {
        return sellContractIdArr;
    }

    /**
     * @param sellContractIdArr
     *            the sellContractIdArr to set
     */
    public void setSellContractIdArr(String[] sellContractIdArr) {
        this.sellContractIdArr = sellContractIdArr;
    }

    /**
     * @return the sendGoodsPriceArr
     */
    public String[] getSendGoodsPriceArr() {
        return sendGoodsPriceArr;
    }

    /**
     * @param sendGoodsPriceArr
     *            the sendGoodsPriceArr to set
     */
    public void setSendGoodsPriceArr(String[] sendGoodsPriceArr) {
        this.sendGoodsPriceArr = sendGoodsPriceArr;
    }

    /**
     * @return the sellPriceArr
     */
    public String[] getSellPriceArr() {
        return sellPriceArr;
    }

    /**
     * @param sellPriceArr
     *            the sellPriceArr to set
     */
    public void setSellPriceArr(String[] sellPriceArr) {
        this.sellPriceArr = sellPriceArr;
    }

}
