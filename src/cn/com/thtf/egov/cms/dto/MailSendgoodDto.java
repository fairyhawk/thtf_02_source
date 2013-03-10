/**
 * ClassName  MailSendgoodDto
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-6-3
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

import java.math.BigDecimal;

/**
 * MailSendgoodDto
 * 
 * @author Lubo
 */
public class MailSendgoodDto {

    /** 部门名称 */
    private String productDeptName;
    /** 出库日期 */
    private String sendDate;
    /** 客户名称 */
    private String customerName;
    /** 产品合同号 */
    private String productContractCode;
    /** 出库单号 */
    private String id;

    /** 库房名称 */
    private String stockroomname;
    /** 产品分类 */
    private String producttypename;
    /** 产品编码 */
    private String productCode;
    /** 产品名称 */
    private String productName;
    /** 规格型号 */
    private String productType;

    /** 数量 */
    private String count;
    /** 单价 */
    private BigDecimal price;
    /** 总价 */
    private BigDecimal money;
    /** 经办人 */
    private String userName;
    /** 发货人 */
    private String stkAdmName;
    /** 单位 */
    private String unit;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * @return the stkAdmName
     */
    public String getStkAdmName() {
        return stkAdmName;
    }

    /**
     * @param stkAdmName
     *            the stkAdmName to set
     */
    public void setStkAdmName(String stkAdmName) {
        this.stkAdmName = stkAdmName;
    }

    /**
     * @return the productDeptName
     */
    public String getProductDeptName() {
        return productDeptName;
    }

    /**
     * @param productDeptName
     *            the productDeptName to set
     */
    public void setProductDeptName(String productDeptName) {
        this.productDeptName = productDeptName;
    }

    /**
     * @return the sendDate
     */
    public String getSendDate() {
        return sendDate;
    }

    /**
     * @param sendDate
     *            the sendDate to set
     */
    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
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
     * @return the stockroomname
     */
    public String getStockroomname() {
        return stockroomname;
    }

    /**
     * @param stockroomname
     *            the stockroomname to set
     */
    public void setStockroomname(String stockroomname) {
        this.stockroomname = stockroomname;
    }

    /**
     * @return the producttypename
     */
    public String getProducttypename() {
        return producttypename;
    }

    /**
     * @param producttypename
     *            the producttypename to set
     */
    public void setProducttypename(String producttypename) {
        this.producttypename = producttypename;
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
     * @return the count
     */
    public String getCount() {
        return count;
    }

    /**
     * @param count
     *            the count to set
     */
    public void setCount(String count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
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
