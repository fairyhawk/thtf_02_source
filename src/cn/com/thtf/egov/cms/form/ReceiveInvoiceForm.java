/**
 * ClassName  ReceiveInvoiceForm
 *
 * History
 * Create User: ChenHuajiang
 * Create Date: 2010-6-9
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.form;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;

/**
 * 收票管理列表
 * 
 * @author ChenHuajiang
 */

public class ReceiveInvoiceForm extends ActionForm {

    private static final long serialVersionUID = 7487992817459901583L;

    // 收票流水号
    private String receiveInvoiceId;

    // 产品分类ID
    private String prodTypeId;

    // 供货商ID
    private String supplierId;

    // 供货商名称
    private String supplierName;

    // 发票状态
    private String status;

    // 发票类型
    private String invoiceType;

    // 发票号
    private String invoiceNo;

    // 发票金额
    private BigDecimal receiveMoney;

    // 税率
    private Integer taxRate;

    // 特殊说明
    private String text;

    // 收票日期
    private String receiveDate;

    // 收票起始日期
    private String receiveStartDate;

    // 收票终止日期
    private String receiveEndDate;

    // 退票日期
    private String backDate;

    // 退票起始日期
    private String backStartDate;

    // 退票终止日期
    private String backEndDate;

    // 角色ID
    private String roleId;

    // 人员ID
    private String userId;

    // 人员名称
    private String userName;
    // 入库单号
    private String inStockId;

    // 分页
    private int fromLimit;
    private int toLimit;

    public int getFromLimit() {
        return this.fromLimit;
    }

    public void setFromLimit(int fromLimit) {
        this.fromLimit = fromLimit;
    }

    public int getToLimit() {
        return this.toLimit;
    }

    public void setToLimit(int toLimit) {
        this.toLimit = toLimit;
    }

    public String getReceiveInvoiceId() {
        return this.receiveInvoiceId;
    }

    public void setReceiveInvoiceId(String receiveInvoiceId) {
        this.receiveInvoiceId = receiveInvoiceId;
        if (StringUtils.isNotEmpty(receiveInvoiceId)) {
            this.receiveInvoiceId = receiveInvoiceId.trim();
        }
    }

    public String getProdTypeId() {
        return this.prodTypeId;
    }

    public void setProdTypeId(String prodTypeId) {
        this.prodTypeId = prodTypeId;
    }

    public String getRoleId() {
        return this.roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
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
        if (StringUtils.isNotEmpty(supplierName)) {
            this.supplierName = supplierName.trim();
        }
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInvoiceType() {
        return this.invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
        if (StringUtils.isNotEmpty(invoiceType)) {
            this.invoiceType = invoiceType.trim();
        }
    }

    public String getInvoiceNo() {
        return this.invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
        if (StringUtils.isNotEmpty(invoiceNo)) {
            this.invoiceNo = invoiceNo.trim();
        }
    }

    public BigDecimal getReceiveMoney() {
        return this.receiveMoney;
    }

    public void setReceiveMoney(BigDecimal receiveMoney) {
        this.receiveMoney = receiveMoney;
    }

    public Integer getTaxRate() {
        return this.taxRate;
    }

    public void setTaxRate(Integer taxRate) {
        this.taxRate = taxRate;
    }

    public String getBackDate() {
        return this.backDate;
    }

    public void setBackDate(String backDate) {
        this.backDate = backDate;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
        if (StringUtils.isNotEmpty(text)) {
            this.text = text.trim();
        }
    }

    public String getReceiveDate() {
        return this.receiveDate;
    }

    public void setReceiveDate(String receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getReceiveStartDate() {
        return this.receiveStartDate;
    }

    public void setReceiveStartDate(String receiveStartDate) {
        this.receiveStartDate = receiveStartDate;
    }

    public String getReceiveEndDate() {
        return this.receiveEndDate;
    }

    public void setReceiveEndDate(String receiveEndDate) {
        this.receiveEndDate = receiveEndDate;
    }

    public String getBackStartDate() {
        return this.backStartDate;
    }

    public void setBackStartDate(String backStartDate) {
        this.backStartDate = backStartDate;
    }

    public String getBackEndDate() {
        return this.backEndDate;
    }

    public void setBackEndDate(String backEndDate) {
        this.backEndDate = backEndDate;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        if (StringUtils.isNotEmpty(userName)) {
            this.userName = userName.trim();
        }
    }

    public String getInStockId() {
        return inStockId;
    }

    public void setInStockId(String inStockId) {
        this.inStockId = inStockId;
        if (StringUtils.isNotEmpty(inStockId)) {
            this.inStockId = inStockId.trim();
        }
    }
}
