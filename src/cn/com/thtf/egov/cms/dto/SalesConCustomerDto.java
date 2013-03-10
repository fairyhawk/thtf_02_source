/**
 * ClassName  ProdCreditLimit
 *
 * History
 * Create User: ChenHuajiang
 * Create Date: 2010-4-8
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

/**
 * 新建销售合同的客户信息的DTO
 * 
 * @author shensi
 */

public class SalesConCustomerDto {

    private String customerId;// 客户Id

    private String customerName;// 客户名称

    private String cusLinkId;// 客户联系人ID

    private String cusLinkName;// 客户联系人姓名

    private String cusProvince;// 客户省份

    private String cusCity;// 客户城市

    private String cusInvoiceBankName;// 客户的开票银行名称

    private String cusTaxNumber;// 税号

    private String cusInvoiceBankAccount;// 客户的开票银行账号

    private String cusInvoiceType;// 客户的发票类型

    private String cusLinkManName;// 客户联系人姓名

    private String cusLinkTel;// 联系人电话

    private String cusLinkFax;// 联系人传真

    private String userId;// 用户ID

    public String getCusLinkManName() {
        return cusLinkManName;
    }

    public void setCusLinkManName(String cusLinkManName) {
        this.cusLinkManName = cusLinkManName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCusLinkId() {
        return cusLinkId;
    }

    public void setCusLinkId(String cusLinkId) {
        this.cusLinkId = cusLinkId;
    }

    public String getCusLinkName() {
        return cusLinkName;
    }

    public void setCusLinkName(String cusLinkName) {
        this.cusLinkName = cusLinkName;
    }

    public String getCusProvince() {
        return cusProvince;
    }

    public void setCusProvince(String cusProvince) {
        this.cusProvince = cusProvince;
    }

    public String getCusCity() {
        return cusCity;
    }

    public void setCusCity(String cusCity) {
        this.cusCity = cusCity;
    }

    public String getCusInvoiceBankName() {
        return cusInvoiceBankName;
    }

    public void setCusInvoiceBankName(String cusInvoiceBankName) {
        this.cusInvoiceBankName = cusInvoiceBankName;
    }

    public String getCusTaxNumber() {
        return cusTaxNumber;
    }

    public void setCusTaxNumber(String cusTaxNumber) {
        this.cusTaxNumber = cusTaxNumber;
    }

    public String getCusInvoiceBankAccount() {
        return cusInvoiceBankAccount;
    }

    public void setCusInvoiceBankAccount(String cusInvoiceBankAccount) {
        this.cusInvoiceBankAccount = cusInvoiceBankAccount;
    }

    public String getCusInvoiceType() {
        return cusInvoiceType;
    }

    public void setCusInvoiceType(String cusInvoiceType) {
        this.cusInvoiceType = cusInvoiceType;
    }

    public String getCusLinkTel() {
        return cusLinkTel;
    }

    public void setCusLinkTel(String cusLinkTel) {
        this.cusLinkTel = cusLinkTel;
    }

    public String getCusLinkFax() {
        return cusLinkFax;
    }

    public void setCusLinkFax(String cusLinkFax) {
        this.cusLinkFax = cusLinkFax;
    }

}
