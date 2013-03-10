/**
 * ClassName  AppointSellDetailDto
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-5-14
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

/**
 * 指定回款 合同明细小页面
 * 
 * @author Lubo
 */
public class AppointSellDetailDto {

    /** 合同ID */
    private String id;
    /** 产品分类ID */
    private String productTypeId;
    /** 产品分类名称 */
    private String productTypeName;
    /** 产品合同号 */
    private String productContractCode;
    /** 公司合同号 */
    private String companyContractCode;

    /** 合同金额 */
    private String money;
    /** 发货金额 */
    private String fhMoney;
    /** 备货金额 */
    private String bhMoney;
    /** 指定明细金额 */
    private String sendGoodsMoney;
    /** 指定合同金额 */
    private String sellMoney;

    /** 开票金额 */
    private String kpMoney;
    /** 退货合同金额 */
    private String sbMoney;
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

    /** 现结金额 */
    private String cashMoney;

    public String getCashMoney() {
        return cashMoney;
    }

    public void setCashMoney(String cashMoney) {
        this.cashMoney = cashMoney;
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
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the productTypeId
     */
    public String getProductTypeId() {
        return productTypeId;
    }

    /**
     * @param productTypeId
     *            the productTypeId to set
     */
    public void setProductTypeId(String productTypeId) {
        this.productTypeId = productTypeId;
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
     * @return the money
     */
    public String getMoney() {
        return money;
    }

    /**
     * @param money
     *            the money to set
     */
    public void setMoney(String money) {
        this.money = money;
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
     * @return the bhMoney
     */
    public String getBhMoney() {
        return bhMoney;
    }

    /**
     * @param bhMoney
     *            the bhMoney to set
     */
    public void setBhMoney(String bhMoney) {
        this.bhMoney = bhMoney;
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
     * @return the sellMoney
     */
    public String getSellMoney() {
        return sellMoney;
    }

    /**
     * @param sellMoney
     *            the sellMoney to set
     */
    public void setSellMoney(String sellMoney) {
        this.sellMoney = sellMoney;
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
     * @return the sbMoney
     */
    public String getSbMoney() {
        return sbMoney;
    }

    /**
     * @param sbMoney
     *            the sbMoney to set
     */
    public void setSbMoney(String sbMoney) {
        this.sbMoney = sbMoney;
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
