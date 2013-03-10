package cn.com.thtf.egov.cms.entity;

import cn.com.thtf.egov.cms.application.BaseEntity;

public class SupplierEntity extends BaseEntity {

    private static final long serialVersionUID = 1024497697911677465L;

    private Integer id;

    private String name;

    private String area;

    private String province;

    private String city;

    private String address;

    private String postcode;

    private String invoiceBankName;

    private String invoiceBankAccount;

    private String taxNumber;

    private String invoiceBankAddress;

    private String invoiceBankTel;

    private Integer invoiceType;

    private Integer taxRate;

    private String remitBankName;

    private String remitBankAccount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getInvoiceBankName() {
        return invoiceBankName;
    }

    public void setInvoiceBankName(String invoiceBankName) {
        this.invoiceBankName = invoiceBankName;
    }

    public String getInvoiceBankAccount() {
        return invoiceBankAccount;
    }

    public void setInvoiceBankAccount(String invoiceBankAccount) {
        this.invoiceBankAccount = invoiceBankAccount;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getInvoiceBankAddress() {
        return invoiceBankAddress;
    }

    public void setInvoiceBankAddress(String invoiceBankAddress) {
        this.invoiceBankAddress = invoiceBankAddress;
    }

    public String getInvoiceBankTel() {
        return invoiceBankTel;
    }

    public void setInvoiceBankTel(String invoiceBankTel) {
        this.invoiceBankTel = invoiceBankTel;
    }

    public Integer getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(Integer invoiceType) {
        this.invoiceType = invoiceType;
    }

    public Integer getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Integer taxRate) {
        this.taxRate = taxRate;
    }

    public String getRemitBankName() {
        return remitBankName;
    }

    public void setRemitBankName(String remitBankName) {
        this.remitBankName = remitBankName;
    }

    public String getRemitBankAccount() {
        return remitBankAccount;
    }

    public void setRemitBankAccount(String remitBankAccount) {
        this.remitBankAccount = remitBankAccount;
    }
}
