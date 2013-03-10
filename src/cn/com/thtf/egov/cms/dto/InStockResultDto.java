/**
 * ClassName  InStockResultDto
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-6-1
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

/**
 * InStockResultDto
 * 
 * @author Lubo
 */
public class InStockResultDto {

    /** 入库单号 */
    private String inStockId;
    /** 供货商名 */
    private String inStockSupplierName;
    /** 入库金额 */
    private String inStockMoney;
    /** 申请时间 */
    private String inStockDate;
    /** 入库时间 */
    private String inStockStkAdmDate;

    /** 入库人员名 */
    private String inStockUserName;
    /** 入库状态 */
    private String inStockStatus;
    /** 库房名称 */
    private String stockroomName;
    /** 产品分类名称 */
    private String productTypeName;
    /** 产品合同号 */
    private String productContractCode;

    /** 公司合同号 */
    private String companyContractCode;
    /** 退货数 */
    private String buyBackGoodsMoney;

    /**
     * @return the inStockId
     */
    public String getInStockId() {
        return inStockId;
    }

    /**
     * @param inStockId
     *            the inStockId to set
     */
    public void setInStockId(String inStockId) {
        this.inStockId = inStockId;
    }

    /**
     * @return the inStockSupplierName
     */
    public String getInStockSupplierName() {
        return inStockSupplierName;
    }

    /**
     * @param inStockSupplierName
     *            the inStockSupplierName to set
     */
    public void setInStockSupplierName(String inStockSupplierName) {
        this.inStockSupplierName = inStockSupplierName;
    }

    /**
     * @return the inStockMoney
     */
    public String getInStockMoney() {
        return inStockMoney;
    }

    /**
     * @param inStockMoney
     *            the inStockMoney to set
     */
    public void setInStockMoney(String inStockMoney) {
        this.inStockMoney = inStockMoney;
    }

    /**
     * @return the inStockDate
     */
    public String getInStockDate() {
        return inStockDate;
    }

    /**
     * @param inStockDate
     *            the inStockDate to set
     */
    public void setInStockDate(String inStockDate) {
        this.inStockDate = inStockDate;
    }

    /**
     * @return the inStockStkAdmDate
     */
    public String getInStockStkAdmDate() {
        return inStockStkAdmDate;
    }

    /**
     * @param inStockStkAdmDate
     *            the inStockStkAdmDate to set
     */
    public void setInStockStkAdmDate(String inStockStkAdmDate) {
        this.inStockStkAdmDate = inStockStkAdmDate;
    }

    /**
     * @return the inStockUserName
     */
    public String getInStockUserName() {
        return inStockUserName;
    }

    /**
     * @param inStockUserName
     *            the inStockUserName to set
     */
    public void setInStockUserName(String inStockUserName) {
        this.inStockUserName = inStockUserName;
    }

    /**
     * @return the inStockStatus
     */
    public String getInStockStatus() {
        return inStockStatus;
    }

    /**
     * @param inStockStatus
     *            the inStockStatus to set
     */
    public void setInStockStatus(String inStockStatus) {
        this.inStockStatus = inStockStatus;
    }

    /**
     * @return the stockroomName
     */
    public String getStockroomName() {
        return stockroomName;
    }

    /**
     * @param stockroomName
     *            the stockroomName to set
     */
    public void setStockroomName(String stockroomName) {
        this.stockroomName = stockroomName;
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
     * @return the buyBackGoodsMoney
     */
    public String getBuyBackGoodsMoney() {
        return buyBackGoodsMoney;
    }

    /**
     * @param buyBackGoodsMoney
     *            the buyBackGoodsMoney to set
     */
    public void setBuyBackGoodsMoney(String buyBackGoodsMoney) {
        this.buyBackGoodsMoney = buyBackGoodsMoney;
    }

}
