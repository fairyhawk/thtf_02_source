/**
 * ClassName  ReceiveInvoiceDto
 *
 * History
 * Create User: ChenHuajiang
 * Create Date: 2010-6-9
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

import java.io.Serializable;

/**
 * 收票管理列表
 * 
 * @author ChenHuajiang
 */

public class ReceiveInvoiceDto implements Serializable {

    private static final long serialVersionUID = -4653541825137858988L;

    // 收票流水号
    private String receiveInvoiceId;

    // 产品分类ID
    private String prodTypeId;

    // 产品分类名称
    private String prodTypeName;

    // 供货商ID
    private String supplierId;

    // 供货商名称
    private String supplierName;

    // 发票金额
    private String invoiceMoney;

    // 发票状态
    private Integer status;

    // 发票类型
    private Integer invoiceType;

    // 发票号
    private String invoiceNo;

    // 收票日期
    private String receiveDate;

    // 退票日期
    private String backDate;

    // 人员名称
    private String userName;

    // 税率
    private String taxRate;

    // 特殊说明
    private String text;
    //已经指定的产品个数
    private Integer invoiceCount;
    public Integer getInvoiceCount() {
        return invoiceCount;
    }

    public void setInvoiceCount(Integer invoiceCount) {
        this.invoiceCount = invoiceCount;
    }

    public String getReceiveInvoiceId() {
        return this.receiveInvoiceId;
    }

    public void setReceiveInvoiceId(String receiveInvoiceId) {
        this.receiveInvoiceId = receiveInvoiceId;
    }

    public String getProdTypeId() {
        return this.prodTypeId;
    }

    public void setProdTypeId(String prodTypeId) {
        this.prodTypeId = prodTypeId;
    }

    public String getProdTypeName() {
        return this.prodTypeName;
    }

    public void setProdTypeName(String prodTypeName) {
        this.prodTypeName = prodTypeName;
    }

    public String getSupplierId() {
        return this.supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return this.supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getInvoiceMoney() {
        return this.invoiceMoney;
    }

    public void setInvoiceMoney(String invoiceMoney) {
        this.invoiceMoney = invoiceMoney;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getInvoiceType() {
        return this.invoiceType;
    }

    public void setInvoiceType(Integer invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getInvoiceNo() {
        return this.invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getReceiveDate() {
        return this.receiveDate;
    }

    public void setReceiveDate(String receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getBackDate() {
        return this.backDate;
    }

    public void setBackDate(String backDate) {
        this.backDate = backDate;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTaxRate() {
        return this.taxRate;
    }

    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
