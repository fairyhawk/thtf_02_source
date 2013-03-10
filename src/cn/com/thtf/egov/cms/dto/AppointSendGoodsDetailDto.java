/**
 * ClassName  AppointSellDetailDto
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-5-17
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;


/**
 * 指定回款 发货明细小页面
 * 
 * @author Lubo
 */
public class AppointSendGoodsDetailDto {

    /** 发货单号 */
    private String sendGoodsId;
    /** 产品合同号 */
    private String productContractCode;
    /** 公司合同号 */
    private String companyContractCode;
    /** 产品编码 */
    private String productCode;
    /** 产品名称 */
    private String productName;

    /** 产品ID */
    private String productId;
    /** 产品型号 */
    private String productType;
    /** 产品规格 */
    private String productUnit;
    /** 销售单价 */
    private String sellContractDetailPrice;

    /** 发货金额 */
    private String fhMoney;
    /** 指定明细金额 */
    private String sendGoodsMoney;
    /** 开票金额 */
    private String kpMoney;
    /** 退货单金额 */
    private String thMoney;

    /** 人员名称 */
    private String userName;

    /** 回款明细ID */
    private String mreturnDetileId;
    /** 指定金额 */
    private String appointMoney;

    /** 客户信用 */
    private String customerCreditId;
    /** 状态 */
    private String status;

    /** 要求到账日期 **/
    private String sgrqartermdate;

    public String getSgrqartermdate() {
        return sgrqartermdate;
    }

    public void setSgrqartermdate(String sgrqartermdate) {
        this.sgrqartermdate = sgrqartermdate;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the customerCreditId
     */
    public String getCustomerCreditId() {
        return customerCreditId;
    }

    /**
     * @param customerCreditId
     *            the customerCreditId to set
     */
    public void setCustomerCreditId(String customerCreditId) {
        this.customerCreditId = customerCreditId;
    }

    /**
     * @return the appointMoney
     */
    public String getAppointMoney() {
        return appointMoney;
    }

    /**
     * @param appointMoney
     *            the appointMoney to set
     */
    public void setAppointMoney(String appointMoney) {
        this.appointMoney = appointMoney;
    }

    /**
     * @return the mreturnDetileId
     */
    public String getMreturnDetileId() {
        return mreturnDetileId;
    }

    /**
     * @param mreturnDetileId
     *            the mreturnDetileId to set
     */
    public void setMreturnDetileId(String mreturnDetileId) {
        this.mreturnDetileId = mreturnDetileId;
    }

    /**
     * @return the productId
     */
    public String getProductId() {
        return productId;
    }

    /**
     * @param productId
     *            the productId to set
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * @return the sendGoodsId
     */
    public String getSendGoodsId() {
        return sendGoodsId;
    }

    /**
     * @param sendGoodsId
     *            the sendGoodsId to set
     */
    public void setSendGoodsId(String sendGoodsId) {
        this.sendGoodsId = sendGoodsId;
    }

    /**
     * @return the productContractCode
     */
    public String getProductContractCode() {
        return productContractCode;
    }

    /**
     * @param productContractCode
     *            the productContractCode to set
     */
    public void setProductContractCode(String productContractCode) {
        this.productContractCode = productContractCode;
    }

    /**
     * @return the companyContractCode
     */
    public String getCompanyContractCode() {
        return companyContractCode;
    }

    /**
     * @param companyContractCode
     *            the companyContractCode to set
     */
    public void setCompanyContractCode(String companyContractCode) {
        this.companyContractCode = companyContractCode;
    }

    /**
     * @return the productCode
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * @param productCode
     *            the productCode to set
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName
     *            the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return the productType
     */
    public String getProductType() {
        return productType;
    }

    /**
     * @param productType
     *            the productType to set
     */
    public void setProductType(String productType) {
        this.productType = productType;
    }

    /**
     * @return the productUnit
     */
    public String getProductUnit() {
        return productUnit;
    }

    /**
     * @param productUnit
     *            the productUnit to set
     */
    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    /**
     * @return the sellContractDetailPrice
     */
    public String getSellContractDetailPrice() {
        return sellContractDetailPrice;
    }

    /**
     * @param sellContractDetailPrice
     *            the sellContractDetailPrice to set
     */
    public void setSellContractDetailPrice(String sellContractDetailPrice) {
        this.sellContractDetailPrice = sellContractDetailPrice;
    }

    /**
     * @return the fhMoney
     */
    public String getFhMoney() {
        return fhMoney;
    }

    /**
     * @param fhMoney
     *            the fhMoney to set
     */
    public void setFhMoney(String fhMoney) {
        this.fhMoney = fhMoney;
    }

    /**
     * @return the sendGoodsMoney
     */
    public String getSendGoodsMoney() {
        return sendGoodsMoney;
    }

    /**
     * @param sendGoodsMoney
     *            the sendGoodsMoney to set
     */
    public void setSendGoodsMoney(String sendGoodsMoney) {
        this.sendGoodsMoney = sendGoodsMoney;
    }

    /**
     * @return the kpMoney
     */
    public String getKpMoney() {
        return kpMoney;
    }

    /**
     * @param kpMoney
     *            the kpMoney to set
     */
    public void setKpMoney(String kpMoney) {
        this.kpMoney = kpMoney;
    }

    /**
     * @return the thMoney
     */
    public String getThMoney() {
        return thMoney;
    }

    /**
     * @param thMoney
     *            the thMoney to set
     */
    public void setThMoney(String thMoney) {
        this.thMoney = thMoney;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     *            the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

}
