package cn.com.thtf.egov.cms.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class BuyPaymentInfoDto implements Serializable {

    /**
     * 自动序列化
     */
    private static final long serialVersionUID = 6927813030938587065L;

    /** 付款单号 **/
    private String id;

    /** 产品分类ID **/
    private Integer productTypeId;

    /** 产品分类名称 **/
    private String productTypeName;

    /** 申请日期 **/
    private String date;

    /** 付款方式 **/
    private Integer paymentWay;

    /** 承兑开具日期 **/
    private String acceptDate;

    /** 帐期 **/
    private Integer arterm;

    /** 承兑号码 **/
    private String acceptNumber;

    /** 付款日期 **/
    private String paymentDate;

    /** 凭证号 **/
    private String number;

    /** 付款总额 **/
    private BigDecimal money;

    /** 指定金额 **/
    private BigDecimal appointMoney1;

    /** 合同 预付金额 **/
    private BigDecimal appointMoney0;

    /** 产品 预付金额 **/
    private BigDecimal productMoney;

    /** 退款金额 **/
    private BigDecimal backMoney;

    /** 付款单状态 **/
    private Integer status;

    /** 登录名 **/
    private String userId;

    /** 人员名称 **/
    private String userName;

    /** 特殊说明 **/
    private String text;

    /** 产品总监登录名 **/
    private String proMajId;

    /** 产品总监用户名 **/
    private String proMajName;

    /** 产品总监评审日期 **/
    private String proMajDate;

    /** 产品总监评审意见 **/
    private String proMajIder;

    /** 产品总监补充说明 **/
    private String proMajText;

    /** 采购主管登录名 **/
    private String buyManId;

    /** 采购主管用户名 **/
    private String buyManName;

    /** 采购主管评审日期 **/
    private String buyManDate;

    /** 采购主管评审意见 **/
    private String buyManIdea;

    /** 采购主管补充说明 **/
    private String buyManText;

    /** 运营总监登录名 **/
    private String opeMajId;

    /** 运营总监用户名 **/
    private String opeMajName;

    /** 运营总监评审日期 **/
    private String opeMajDate;

    /** 运营总监评审意见 **/
    private String opeMajIder;

    /** 运营总监补充说明 **/
    private String opeMajText;

    /** 新建日期 **/
    private String datetime;

    // 供货商

    /** 供货商编号 **/
    private Integer supplierId;

    /** 供货商名称 **/
    private String supplierName;

    /** 供货商联系人ID **/
    private Integer supplierLinkmanId;

    /** 供货商联系人名称 **/
    private String linkmanName;

    /** 省份 **/
    private String supplierProvince;

    /** 联系人电话 **/
    private String linkmanTel;

    /** 城市 **/
    private String supplierCity;

    /** 联系人传真 **/
    private String linkmanFax;

    /** 汇款银行名称 **/
    private String supplierRemitBankName;

    /** 汇款银行帐号 **/
    private String supplierRemitBankAccount;

    /** 发票类型 **/
    private Integer supplierInvoiceType;

    /** 增值税税率 **/
    private Integer supplierTaxRate;

    /** 采购合同类型 **/
    private String contractType;

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getPaymentWay() {
        return paymentWay;
    }

    public void setPaymentWay(Integer paymentWay) {
        this.paymentWay = paymentWay;
    }

    public String getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(String acceptDate) {
        this.acceptDate = acceptDate;
    }

    public Integer getArterm() {
        return arterm;
    }

    public void setArterm(Integer arterm) {
        this.arterm = arterm;
    }

    public String getAcceptNumber() {
        return acceptNumber;
    }

    public void setAcceptNumber(String acceptNumber) {
        this.acceptNumber = acceptNumber;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getProMajId() {
        return proMajId;
    }

    public void setProMajId(String proMajId) {
        this.proMajId = proMajId;
    }

    public String getProMajName() {
        return proMajName;
    }

    public void setProMajName(String proMajName) {
        this.proMajName = proMajName;
    }

    public String getProMajDate() {
        return proMajDate;
    }

    public void setProMajDate(String proMajDate) {
        this.proMajDate = proMajDate;
    }

    public String getProMajIder() {
        return proMajIder;
    }

    public void setProMajIder(String proMajIder) {
        this.proMajIder = proMajIder;
    }

    public String getProMajText() {
        return proMajText;
    }

    public void setProMajText(String proMajText) {
        this.proMajText = proMajText;
    }

    public String getBuyManId() {
        return buyManId;
    }

    public void setBuyManId(String buyManId) {
        this.buyManId = buyManId;
    }

    public String getBuyManName() {
        return buyManName;
    }

    public void setBuyManName(String buyManName) {
        this.buyManName = buyManName;
    }

    public String getBuyManDate() {
        return buyManDate;
    }

    public void setBuyManDate(String buyManDate) {
        this.buyManDate = buyManDate;
    }

    public String getBuyManIdea() {
        return buyManIdea;
    }

    public void setBuyManIdea(String buyManIdea) {
        this.buyManIdea = buyManIdea;
    }

    public String getBuyManText() {
        return buyManText;
    }

    public void setBuyManText(String buyManText) {
        this.buyManText = buyManText;
    }

    public String getOpeMajId() {
        return opeMajId;
    }

    public void setOpeMajId(String opeMajId) {
        this.opeMajId = opeMajId;
    }

    public String getOpeMajName() {
        return opeMajName;
    }

    public void setOpeMajName(String opeMajName) {
        this.opeMajName = opeMajName;
    }

    public String getOpeMajDate() {
        return opeMajDate;
    }

    public void setOpeMajDate(String opeMajDate) {
        this.opeMajDate = opeMajDate;
    }

    public String getOpeMajIder() {
        return opeMajIder;
    }

    public void setOpeMajIder(String opeMajIder) {
        this.opeMajIder = opeMajIder;
    }

    public String getOpeMajText() {
        return opeMajText;
    }

    public void setOpeMajText(String opeMajText) {
        this.opeMajText = opeMajText;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Integer getSupplierLinkmanId() {
        return supplierLinkmanId;
    }

    public void setSupplierLinkmanId(Integer supplierLinkmanId) {
        this.supplierLinkmanId = supplierLinkmanId;
    }

    public String getLinkmanName() {
        return linkmanName;
    }

    public void setLinkmanName(String linkmanName) {
        this.linkmanName = linkmanName;
    }

    public String getSupplierProvince() {
        return supplierProvince;
    }

    public void setSupplierProvince(String supplierProvince) {
        this.supplierProvince = supplierProvince;
    }

    public String getLinkmanTel() {
        return linkmanTel;
    }

    public void setLinkmanTel(String linkmanTel) {
        this.linkmanTel = linkmanTel;
    }

    public String getSupplierCity() {
        return supplierCity;
    }

    public void setSupplierCity(String supplierCity) {
        this.supplierCity = supplierCity;
    }

    public String getLinkmanFax() {
        return linkmanFax;
    }

    public void setLinkmanFax(String linkmanFax) {
        this.linkmanFax = linkmanFax;
    }

    public String getSupplierRemitBankName() {
        return supplierRemitBankName;
    }

    public void setSupplierRemitBankName(String supplierRemitBankName) {
        this.supplierRemitBankName = supplierRemitBankName;
    }

    public String getSupplierRemitBankAccount() {
        return supplierRemitBankAccount;
    }

    public void setSupplierRemitBankAccount(String supplierRemitBankAccount) {
        this.supplierRemitBankAccount = supplierRemitBankAccount;
    }

    public Integer getSupplierInvoiceType() {
        return supplierInvoiceType;
    }

    public void setSupplierInvoiceType(Integer supplierInvoiceType) {
        this.supplierInvoiceType = supplierInvoiceType;
    }

    public Integer getSupplierTaxRate() {
        return supplierTaxRate;
    }

    public void setSupplierTaxRate(Integer supplierTaxRate) {
        this.supplierTaxRate = supplierTaxRate;
    }

    public BigDecimal getAppointMoney1() {
        return appointMoney1;
    }

    public void setAppointMoney1(BigDecimal appointMoney1) {
        this.appointMoney1 = appointMoney1;
    }

    public BigDecimal getAppointMoney0() {
        return appointMoney0;
    }

    public void setAppointMoney0(BigDecimal appointMoney0) {
        this.appointMoney0 = appointMoney0;
    }

    public BigDecimal getBackMoney() {
        return backMoney;
    }

    public void setBackMoney(BigDecimal backMoney) {
        this.backMoney = backMoney;
    }

    public BigDecimal getProductMoney() {
        return productMoney;
    }

    public void setProductMoney(BigDecimal productMoney) {
        this.productMoney = productMoney;
    }

}
