package cn.com.thtf.egov.cms.dto;

import java.io.Serializable;

/**
 * 采购合同DTO
 * 
 * @author HanHaiyun
 */
public class BuyContractSelectDto implements Serializable {

    private static final long serialVersionUID = -4494758791508846978L;
    private String id;

    // 状态
    private String status;
    // 供货商名称
    private String supplierName;
    // 省份
    private String province;
    // 城市
    private String city;
    // 开票银行名称
    private String remitBankName;
    // 开票银行账号
    private String remitBankAccount;
    // 发票类型
    private String invoiceType;
    // 增值税税率
    private String taxRate;
    // 联系人姓名
    private String linkmanName;
    // 联系人电话
    private String linkmanTel;
    // 传真
    private String linkmanFax;
    // 合同类型
    private Integer contractType;
    // 模板类型
    private Integer templateType;
    // 签订地点
    private String place;
    // 质量标准
    private String quality;
    // 验收期限
    private String checkLimit;
    // 质保期
    private String protectLimit;
    // 付款类型
    private Integer paymentType;
    // 付款方式
    private Integer paymentWay;
    // 账期
    private Integer arterm;
    // 文件名
    private String fileName;
    // 签订后付款期限
    private Integer contractPaymentTime;
    // 预付金额
    private Integer prepayMoney;
    // 货到后付款期限
    private Integer sendPaymentTime;
    // 要求到货日期
    private String requestDate;
    // 货运方式
    private Integer transportWay;
    // 特殊说明
    private String text;
    // 商品类型名称
    private String productTypeName;
    // 产品类型Id
    private String productTypeId;
    // 商品部门名称
    private String productDeptName;
    // 法务专员登录名
    private String legalId;
    // 法务专员用户名
    private String legalName;
    // 法务专员评审日期
    private String legalDate;
    // 法务专员评审意见
    private String legalIdea;
    // 采购主管登录名
    private String buyManId;
    // 法务专员补充说明
    private String legalText;
    // 采购主管用户名
    private String buyManName;
    // 采购主管评审日期
    private String buyManDate;
    // 采购主管评审意见
    private String buyManIdea;
    // 采购主管产品要求是否符合评审意见
    private String buyManProductIdea;
    // 采购主管付款方式是否符合评审意见
    private String buyManPaymentTypeIdea;
    // 采购主管售前服务是否符合评审意见
    private String buyManBeforeserviceIdea;
    // 采购主管补充说明
    private String buyManText;
    // 运营总监登录名
    private String opeMajId;
    // 运营总监用户名
    private String opeMajName;
    // 运营总监评审日期
    private String opeMajDate;
    // 运营总监评审意见
    private String opeMajIdea;
    // 运营总监补充说明
    private String opeMajText;
    // 部门编号
    private String productDeptNo;
    // 产品分类编号
    private String productTypeNo;
    // 产品合同号
    private String productContractCode;
    // 公司合同号
    private String companyContractCode;
    // 金额
    private String money;

    private String userName;

    private String dateTime;

    private String deliveryTerms;
    
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

    public String getLegalId() {
        return legalId;
    }

    public String getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(String productTypeId) {
        this.productTypeId = productTypeId;
    }

    public void setLegalId(String legalId) {
        this.legalId = legalId;
    }

    public String getBuyManId() {
        return buyManId;
    }

    public void setBuyManId(String buyManId) {
        this.buyManId = buyManId;
    }

    public String getOpeMajId() {
        return opeMajId;
    }

    public void setOpeMajId(String opeMajId) {
        this.opeMajId = opeMajId;
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
        if (null == buyManIdea || "".equals(buyManIdea)) {
            this.setBuyManProductIdea(null);
            this.setBuyManPaymentTypeIdea(null);
            this.setBuyManBeforeserviceIdea(null);
        } else {
            String[] buyManIdeas = buyManIdea.split("");
            this.setBuyManProductIdea(buyManIdeas.length >= 1 ? buyManIdeas[1] : null);
            this
                    .setBuyManPaymentTypeIdea(buyManIdeas.length >= 2 ? buyManIdeas[2]
                            : null);
            this.setBuyManBeforeserviceIdea(buyManIdeas.length >= 3 ? buyManIdeas[3]
                    : null);
        }
        this.buyManIdea = buyManIdea;
    }

    public String getBuyManProductIdea() {
        return buyManProductIdea;
    }

    public void setBuyManProductIdea(String buyManProductIdea) {
        this.buyManProductIdea = buyManProductIdea;
    }

    public String getBuyManPaymentTypeIdea() {
        return buyManPaymentTypeIdea;
    }

    public void setBuyManPaymentTypeIdea(String buyManPaymentTypeIdea) {
        this.buyManPaymentTypeIdea = buyManPaymentTypeIdea;
    }

    public String getBuyManBeforeserviceIdea() {
        return buyManBeforeserviceIdea;
    }

    public void setBuyManBeforeserviceIdea(String buyManBeforeserviceIdea) {
        this.buyManBeforeserviceIdea = buyManBeforeserviceIdea;
    }

    public String getBuyManText() {
        return buyManText;
    }

    public void setBuyManText(String buyManText) {
        this.buyManText = buyManText;
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

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
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

    public String getLinkmanName() {
        return linkmanName;
    }

    public void setLinkmanName(String linkmanName) {
        this.linkmanName = linkmanName;
    }

    public String getLinkmanTel() {
        return linkmanTel;
    }

    public void setLinkmanTel(String linkmanTel) {
        this.linkmanTel = linkmanTel;
    }

    public String getLinkmanFax() {
        return linkmanFax;
    }

    public void setLinkmanFax(String linkmanFax) {
        this.linkmanFax = linkmanFax;
    }

    public Integer getTemplateType() {
        return templateType;
    }

    public void setTemplateType(Integer templateType) {
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

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getPaymentWay() {
        return paymentWay;
    }

    public void setPaymentWay(Integer paymentWay) {
        this.paymentWay = paymentWay;
    }

    public Integer getArterm() {
        return arterm;
    }

    public void setArterm(Integer arterm) {
        this.arterm = arterm;
    }

    public Integer getContractPaymentTime() {
        return contractPaymentTime;
    }

    public void setContractPaymentTime(Integer contractPaymentTime) {
        this.contractPaymentTime = contractPaymentTime;
    }

    public Integer getPrepayMoney() {
        return prepayMoney;
    }

    public void setPrepayMoney(Integer prepayMoney) {
        this.prepayMoney = prepayMoney;
    }

    public Integer getSendPaymentTime() {
        return sendPaymentTime;
    }

    public void setSendPaymentTime(Integer sendPaymentTime) {
        this.sendPaymentTime = sendPaymentTime;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public Integer getTransportWay() {
        return transportWay;
    }

    public void setTransportWay(Integer transportWay) {
        this.transportWay = transportWay;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public String getProductDeptName() {
        return productDeptName;
    }

    public void setProductDeptName(String productDeptName) {
        this.productDeptName = productDeptName;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getProductDeptNo() {
        return productDeptNo;
    }

    public void setProductDeptNo(String productDeptNo) {
        this.productDeptNo = productDeptNo;
    }

    public String getProductTypeNo() {
        return productTypeNo;
    }

    public void setProductTypeNo(String productTypeNo) {
        this.productTypeNo = productTypeNo;
    }

    public String getProductContractCode() {
        return productContractCode;
    }

    public void setProductContractCode(String productContractCode) {
        this.productContractCode = productContractCode;
    }

    public Integer getContractType() {
        return this.contractType;
    }

    public void setContractType(Integer contractType) {
        this.contractType = contractType;
    }

    public String getCompanyContractCode() {
        return this.companyContractCode;
    }

    public void setCompanyContractCode(String companyContractCode) {
        this.companyContractCode = companyContractCode;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
