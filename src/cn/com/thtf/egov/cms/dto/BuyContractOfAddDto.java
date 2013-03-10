package cn.com.thtf.egov.cms.dto;

import org.apache.struts.upload.FormFile;

public class BuyContractOfAddDto {
    /**
     * @author hanrubing
     */
    private String id;// 合同流水号

    private String productContractCode;// 产品合同号

    private String companyContractCode;// 公司合同号

    private String date;// 签订日期

    private String status;// 合同状态

    private String contractType;// 合同类型

    private String templateType;// 模板类型

    private String place;// 签订地点

    private String quality;// 质量标准

    private String checkLimit;// 验收期限

    private String protectLimit;// 质保期

    private String clause;// 附加条款

    private FormFile file;// 文件

    private String fileName;// 文件name

    private String supplierId;// 供货商编号

    private String supplierName;// 供货商名称

    private String invoiceType;// 发票类型

    private String taxRate;// 增值税税率

    private String supplierLinkmanId;// 供货商联系人编号

    private String productTypeId;// 产品分类编号

    private String paymentType;// 付款类型

    private String paymentWay;// 付款方式

    private String arterm;// 帐期

    private String contractPaymentTime;// 签订后付款期限

    private String prepayMoney;// 预付金额

    private String sendPaymentTime;// 货到后付款期限

    private String requestDate;// 要求到货日期

    private String transportWay;// 货运方式

    private String userId;// 产品总监登录名

    private String userName;// 人员名称

    private String text;// 特殊说明

    private String money;// 合同金额

    private String legalId;// 法务专员登录名

    private String legalName;// 法务专员用户名

    private String legalDate;// 法务专员评审日期

    private String legalIdea;// 法务专员评审意见

    private String legalText;// 法务专员补充说明

    private String buyManId;// 采购主管登录名

    private String buyManName;// 采购主管用户名

    private String buyManDate;// 采购主管评审日期

    private String buyManIdea;// 采购主管评审意见

    private String buyManText;// 采购主管补充说明

    private String opeMajId;// 运营总监用户名

    private String opeMajName;// 运营总监登录名

    private String opeMajDate;// 运营总监评审日期

    private String opeMajIdea;// 运营总监评审意见

    private String opeMajText;// 运营总监补充说明

    private String dateTime;// 新建日期

    private String submitType;//提交类型
    /** 显示用 */
    private String linkmanTel;// 电话
    private String linkmanFox;// 传真

    private String province;// 省份
    private String city;// 城市
    private String remitBankName;// 汇款银行名称
    private String remitBankAccount;// 汇款银行帐号
    private String productDeptName;// 产品部门名称

    private String deliveryTerms;//发货方式
    public String getDeliveryTerms() {
		return deliveryTerms;
	}

	public void setDeliveryTerms(String deliveryTerms) {
		this.deliveryTerms = deliveryTerms;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductContractCode() {
        return productContractCode;
    }

    public void setProductContractCode(String productContractCode) {
        this.productContractCode = productContractCode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCompanyContractCode() {
        return this.companyContractCode;
    }

    public void setCompanyContractCode(String companyContractCode) {
        this.companyContractCode = companyContractCode;
    }

    public String getContractType() {
        return this.contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getCheckLimit() {
        return checkLimit;
    }

    public void setCheckLimit(String checkLimit) {
        this.checkLimit = checkLimit;
    }

    public String getProtectLimit() {
        return protectLimit;
    }

    public void setProtectLimit(String protectLimit) {
        this.protectLimit = protectLimit;
    }

    public String getClause() {
        return clause;
    }

    public void setClause(String clause) {
        this.clause = clause;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }

    public String getSupplierLinkmanId() {
        return supplierLinkmanId;
    }

    public void setSupplierLinkmanId(String supplierLinkmanId) {
        this.supplierLinkmanId = supplierLinkmanId;
    }

    public String getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(String productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentWay() {
        return paymentWay;
    }

    public void setPaymentWay(String paymentWay) {
        this.paymentWay = paymentWay;
    }

    public String getArterm() {
        return arterm;
    }

    public void setArterm(String arterm) {
        this.arterm = arterm;
    }

    public String getContractPaymentTime() {
        return this.contractPaymentTime;
    }

    public void setContractPaymentTime(String contractPaymentTime) {
        this.contractPaymentTime = contractPaymentTime;
    }

    public String getPrepayMoney() {
        return prepayMoney;
    }

    public void setPrepayMoney(String prepayMoney) {
        this.prepayMoney = prepayMoney;
    }

    public String getSendPaymentTime() {
        return sendPaymentTime;
    }

    public void setSendPaymentTime(String sendPaymentTime) {
        this.sendPaymentTime = sendPaymentTime;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getTransportWay() {
        return transportWay;
    }

    public void setTransportWay(String transportWay) {
        this.transportWay = transportWay;
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

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getLegalId() {
        return legalId;
    }

    public void setLegalId(String legalId) {
        this.legalId = legalId;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public String getLegalDate() {
        return legalDate;
    }

    public void setLegalDate(String legalDate) {
        this.legalDate = legalDate;
    }

    public String getLegalIdea() {
        return legalIdea;
    }

    public void setLegalIdea(String legalIdea) {
        this.legalIdea = legalIdea;
    }

    public String getLegalText() {
        return legalText;
    }

    public void setLegalText(String legalText) {
        this.legalText = legalText;
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

    public String getOpeMajIdea() {
        return opeMajIdea;
    }

    public void setOpeMajIdea(String opeMajIdea) {
        this.opeMajIdea = opeMajIdea;
    }

    public String getOpeMajText() {
        return opeMajText;
    }

    public void setOpeMajText(String opeMajText) {
        this.opeMajText = opeMajText;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public FormFile getFile() {
        return file;
    }

    public void setFile(FormFile file) {
        this.file = file;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getLinkmanTel() {
        return linkmanTel;
    }

    public void setLinkmanTel(String linkmanTel) {
        this.linkmanTel = linkmanTel;
    }

    public String getLinkmanFox() {
        return linkmanFox;
    }

    public void setLinkmanFox(String linkmanFox) {
        this.linkmanFox = linkmanFox;
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

    public String getProductDeptName() {
        return productDeptName;
    }

    public void setProductDeptName(String productDeptName) {
        this.productDeptName = productDeptName;
    }

	public String getSubmitType() {
		return submitType;
	}

	public void setSubmitType(String submitType) {
		this.submitType = submitType;
	}

}
