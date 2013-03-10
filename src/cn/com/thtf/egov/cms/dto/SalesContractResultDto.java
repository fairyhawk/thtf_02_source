/**
 * ClassName  SalesContractResultDto
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-4-12
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

/**
 * 销售合同列表
 * 
 * @author Lubo
 */

public class SalesContractResultDto {

    /** 合同流水号 */
    public String sellContractId;
    /** 产品分类名称 */
    public String productTypeName;
    /** 产品合同号 */
    public String productContractCode;
    /** 公司合同号 */
    public String companyContractCode;
    /** 客户名称 */
    public String customerName;

    /** 盖章类型 */
    public String sellContractStampType;
    /** 模版类型 */
    public String sellContractTemplateType;
    /** 付款方式 */
    public String sellContractPaymentWay;
    /** 签订日期 */
    public String sellContractDate;
    /** 人员名称 */
    public String sellContractUserName;

    /** 合同状态 */
    public String sellContractStatus;
    /** 合同金额 */
    public String sellContractMoney;
    /** 发货金额 */
    public String deliveryAmountDeliveryAmountMoney;
    /** 备货金额 */
    public String reserveAmountReserveAmountMoney;
    /** 指定金额 */
    public String specifyAmountSpecifyAmountMoney;

    /** 预收金额 */
    public String advanceAmountAdvanceAmountMoney;
    /** 冻结金额 */
    public String freezeAmounFreezeAmountMoney;

    /** 开票金额 */
    public String makeAmountMakeAmountMoney;
    /** 退货合同金额 */
    public String backAmountBackAmountMoney;
    /** 退货金额 */
    public String sellbackAmountSellbackAmountMoney;

    /** 产品分类编号 */
    public Integer sellContractProductTypeId;
    /** 人员区域编号 */
    public Integer sellContractUserAreaId;
    /** 客户编号 */
    public Integer sellContractCustomerId;
    /** 用户编号 */
    public String sellContractUserId;
    /** 信用类型编号 */
    public Integer sellContractCreditTypeId;
    public String contractProName;
    public String getContractProName() {
        return contractProName;
    }

    public void setContractProName(String contractProName) {
        this.contractProName = contractProName;
    }

    /**
     * @return the sellContractUserId
     */
    public String getSellContractUserId() {
        return sellContractUserId;
    }

    /**
     * @param sellContractUserId
     *            the sellContractUserId to set
     */
    public void setSellContractUserId(String sellContractUserId) {
        this.sellContractUserId = sellContractUserId;
    }

    /**
     * @return the freezeAmounFreezeAmountMoney
     */
    public String getFreezeAmounFreezeAmountMoney() {
        return freezeAmounFreezeAmountMoney;
    }

    /**
     * @param freezeAmounFreezeAmountMoney
     *            the freezeAmounFreezeAmountMoney to set
     */
    public void setFreezeAmounFreezeAmountMoney(String freezeAmounFreezeAmountMoney) {
        this.freezeAmounFreezeAmountMoney = freezeAmounFreezeAmountMoney;
    }

    /**
     * @return the sellContractId
     */
    public String getSellContractId() {
        return sellContractId;
    }

    /**
     * @param sellContractId
     *            the sellContractId to set
     */
    public void setSellContractId(String sellContractId) {
        this.sellContractId = sellContractId;
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

    public String getCompanyContractCode() {
        return this.companyContractCode;
    }

    public void setCompanyContractCode(String companyContractCode) {
        this.companyContractCode = companyContractCode;
    }

    /**
     * @return the customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName
     *            the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return the sellContractStampType
     */
    public String getSellContractStampType() {
        return sellContractStampType;
    }

    /**
     * @param sellContractStampType
     *            the sellContractStampType to set
     */
    public void setSellContractStampType(String sellContractStampType) {
        this.sellContractStampType = sellContractStampType;
    }

    /**
     * @return the sellContractTemplateType
     */
    public String getSellContractTemplateType() {
        return sellContractTemplateType;
    }

    /**
     * @param sellContractTemplateType
     *            the sellContractTemplateType to set
     */
    public void setSellContractTemplateType(String sellContractTemplateType) {
        this.sellContractTemplateType = sellContractTemplateType;
    }

    /**
     * @return the sellContractPaymentWay
     */
    public String getSellContractPaymentWay() {
        return sellContractPaymentWay;
    }

    /**
     * @param sellContractPaymentWay
     *            the sellContractPaymentWay to set
     */
    public void setSellContractPaymentWay(String sellContractPaymentWay) {
        this.sellContractPaymentWay = sellContractPaymentWay;
    }

    /**
     * @return the sellContractDate
     */
    public String getSellContractDate() {
        return sellContractDate;
    }

    /**
     * @param sellContractDate
     *            the sellContractDate to set
     */
    public void setSellContractDate(String sellContractDate) {
        this.sellContractDate = sellContractDate;
    }

    /**
     * @return the sellContractUserName
     */
    public String getSellContractUserName() {
        return sellContractUserName;
    }

    /**
     * @param sellContractUserName
     *            the sellContractUserName to set
     */
    public void setSellContractUserName(String sellContractUserName) {
        this.sellContractUserName = sellContractUserName;
    }

    /**
     * @return the sellContractStatus
     */
    public String getSellContractStatus() {
        return sellContractStatus;
    }

    /**
     * @param sellContractStatus
     *            the sellContractStatus to set
     */
    public void setSellContractStatus(String sellContractStatus) {
        this.sellContractStatus = sellContractStatus;
    }

    /**
     * @return the sellContractMoney
     */
    public String getSellContractMoney() {
        return sellContractMoney;
    }

    /**
     * @param sellContractMoney
     *            the sellContractMoney to set
     */
    public void setSellContractMoney(String sellContractMoney) {
        this.sellContractMoney = sellContractMoney;
    }

    /**
     * @return the deliveryAmountDeliveryAmountMoney
     */
    public String getDeliveryAmountDeliveryAmountMoney() {
        return deliveryAmountDeliveryAmountMoney;
    }

    /**
     * @param deliveryAmountDeliveryAmountMoney
     *            the deliveryAmountDeliveryAmountMoney to set
     */
    public void setDeliveryAmountDeliveryAmountMoney(
            String deliveryAmountDeliveryAmountMoney) {
        this.deliveryAmountDeliveryAmountMoney = deliveryAmountDeliveryAmountMoney;
    }

    /**
     * @return the reserveAmountReserveAmountMoney
     */
    public String getReserveAmountReserveAmountMoney() {
        return reserveAmountReserveAmountMoney;
    }

    /**
     * @param reserveAmountReserveAmountMoney
     *            the reserveAmountReserveAmountMoney to set
     */
    public void setReserveAmountReserveAmountMoney(String reserveAmountReserveAmountMoney) {
        this.reserveAmountReserveAmountMoney = reserveAmountReserveAmountMoney;
    }

    /**
     * @return the specifyAmountSpecifyAmountMoney
     */
    public String getSpecifyAmountSpecifyAmountMoney() {
        return specifyAmountSpecifyAmountMoney;
    }

    /**
     * @param specifyAmountSpecifyAmountMoney
     *            the specifyAmountSpecifyAmountMoney to set
     */
    public void setSpecifyAmountSpecifyAmountMoney(String specifyAmountSpecifyAmountMoney) {
        this.specifyAmountSpecifyAmountMoney = specifyAmountSpecifyAmountMoney;
    }

    /**
     * @return the advanceAmountAdvanceAmountMoney
     */
    public String getAdvanceAmountAdvanceAmountMoney() {
        return advanceAmountAdvanceAmountMoney;
    }

    /**
     * @param advanceAmountAdvanceAmountMoney
     *            the advanceAmountAdvanceAmountMoney to set
     */
    public void setAdvanceAmountAdvanceAmountMoney(String advanceAmountAdvanceAmountMoney) {
        this.advanceAmountAdvanceAmountMoney = advanceAmountAdvanceAmountMoney;
    }

    /**
     * @return the makeAmountMakeAmountMoney
     */
    public String getMakeAmountMakeAmountMoney() {
        return makeAmountMakeAmountMoney;
    }

    /**
     * @param makeAmountMakeAmountMoney
     *            the makeAmountMakeAmountMoney to set
     */
    public void setMakeAmountMakeAmountMoney(String makeAmountMakeAmountMoney) {
        this.makeAmountMakeAmountMoney = makeAmountMakeAmountMoney;
    }

    /**
     * @return the backAmountBackAmountMoney
     */
    public String getBackAmountBackAmountMoney() {
        return backAmountBackAmountMoney;
    }

    /**
     * @param backAmountBackAmountMoney
     *            the backAmountBackAmountMoney to set
     */
    public void setBackAmountBackAmountMoney(String backAmountBackAmountMoney) {
        this.backAmountBackAmountMoney = backAmountBackAmountMoney;
    }

    /**
     * @return the sellbackAmountSellbackAmountMoney
     */
    public String getSellbackAmountSellbackAmountMoney() {
        return sellbackAmountSellbackAmountMoney;
    }

    /**
     * @param sellbackAmountSellbackAmountMoney
     *            the sellbackAmountSellbackAmountMoney to set
     */
    public void setSellbackAmountSellbackAmountMoney(
            String sellbackAmountSellbackAmountMoney) {
        this.sellbackAmountSellbackAmountMoney = sellbackAmountSellbackAmountMoney;
    }

    /**
     * @return the sellContractProductTypeId
     */
    public Integer getSellContractProductTypeId() {
        return sellContractProductTypeId;
    }

    /**
     * @param sellContractProductTypeId
     *            the sellContractProductTypeId to set
     */
    public void setSellContractProductTypeId(Integer sellContractProductTypeId) {
        this.sellContractProductTypeId = sellContractProductTypeId;
    }

    /**
     * @return the sellContractUserAreaId
     */
    public Integer getSellContractUserAreaId() {
        return sellContractUserAreaId;
    }

    /**
     * @param sellContractUserAreaId
     *            the sellContractUserAreaId to set
     */
    public void setSellContractUserAreaId(Integer sellContractUserAreaId) {
        this.sellContractUserAreaId = sellContractUserAreaId;
    }

    /**
     * @return the sellContractCustomerId
     */
    public Integer getSellContractCustomerId() {
        return sellContractCustomerId;
    }

    /**
     * @param sellContractCustomerId
     *            the sellContractCustomerId to set
     */
    public void setSellContractCustomerId(Integer sellContractCustomerId) {
        this.sellContractCustomerId = sellContractCustomerId;
    }

    /**
     * @return the sellContractCreditTypeId
     */
    public Integer getSellContractCreditTypeId() {
        return sellContractCreditTypeId;
    }

    /**
     * @param sellContractCreditTypeId
     *            the sellContractCreditTypeId to set
     */
    public void setSellContractCreditTypeId(Integer sellContractCreditTypeId) {
        this.sellContractCreditTypeId = sellContractCreditTypeId;
    }

}
