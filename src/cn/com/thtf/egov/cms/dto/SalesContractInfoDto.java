/**
 * ClassName  SalesContractInfoDto
 *
 * History
 * Create User: zhaolei
 * Create Date: 2010-4-19
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.struts.upload.FormFile;

/**
 * 销售合同
 * 
 * @author zhaolei
 * 
 */
public class SalesContractInfoDto implements Serializable {

    /** 自动生成序列化ID */
    private static final long serialVersionUID = 1L;
    /** 回款预收金额 */
    private String prepayMoney;
    /** 在途预收金额 */
    private String transitMoney;

    private String id;// 合同流水号

    private String productContractCode;// 产品合同号

    private String companyContractCode;// 公司合同号

    private String date;// 签订日期

    private Integer status;// 合同状态

    private Integer stampType;// 盖章类型

    private String templateType;// 模板类型

    private String place;// 签订地点

    private Integer protectLimit;// 保价期限

    private Integer checkLimit;// 验收期限

    private String breach;// 违约金标准

    private String lawsuit;// 诉讼地

    private String clause;// 附加条款

    private String fileName;// 文件名称

    private String dateTime;// 新建日期

    // 客户信息定义
    private Integer customerId;// 客户编号

    private String customerName;// 客户名称

    private Integer customerLinkmanId;// 客户联系人编号

    private String customerLinkmanName;// 客户联系人姓名

    private String customerProvince;// 客户省份

    private String customerCity;// 客户城市

    private String customerLinkmanTel;// 客户联系人电话

    private String customerLinkmanFax; // 客户联系人传真

    private String customerInvoiceBankName;// 客户开票银行名称

    private String customerInvoiceBankAccount;// 客户开票银行帐号

    private String customerTaxNumber;// 客户税号

    private Integer customerInvoiceType;// 客户发票类型

    private Integer productTypeId;// 产品分类编号

    private String productTypeName;// 产品类型名称

    private String productTypeNo; // 产品类型编号

    private String productDeptName; // 产品部门名称

    private String productDeptNo;// 产品部门编号

    private Integer paymentWay;// 付款方式

    private Integer batchWay;// 分批方式

    private BigDecimal cashMoney;// 现结金额

    private BigDecimal batchMaxMoney;// 单批最大金额

    private Integer customerCreditId;// 客户信用编号

    private Integer creditTypeId;// 信用类型编号

    private String creditTypeName; // 信用类型名称

    private String projectName;// 项目名称

    private String creditProjectName;// 信用项目名称

    private String agreementCode;// 协议编号

    private Integer arterm;// 帐期

    private BigDecimal climit;// 信用额度

    private BigDecimal usedLimit;// 已用

    private BigDecimal lockLimit;// 冻结

    private BigDecimal freeLimit; // 可用额度

    private String text;// 特殊说明

    private String money;// 合同金额

    private String interestRate;// 总利率

    private String interestRateRound;// 总利率四舍五入

    private FormFile file;

    // 发货信息定义
    private Integer customerAddressId;// 客户发货地址编号

    private String customerAddressName;// 客户发货地址单位名称

    private String customerAddressAddress;// 客户发货地址

    private String customerAddressLinkman;// 客户发货地址联系人

    private String customerAddressPostcode; // 客户发货地址邮编

    private String customerAddressTel;// 客户发货地址电话

    private String customerAddressMobile;// 客户发货地址手机

    private String requestSendDate;// 要求发货日期

    private String userId;// 销售经理登录名

    private String userName;// 人员名称

    private Integer userAreaId;// 人员区域编号

    private String userAreaName;// 人员区域名称

    private Integer userRoleId;// 人员角色Id

    private String userTel;// 用户电话

    private String proMajFlag;// 产品总监有无标识

    private String proMajId;// 产品总监登录名

    private String proMajName;// 产品总监用户名

    private String proMajDate;// 产品总监评审日期

    private String proMajDate2;// 产品总监评审日期(去掉时分秒)

    private String proMajIdea;// 产品总监评审意见（插入数据库用）

    private String proMajIdea1;// 产品总监评审意见1

    private String proMajIdea2;// 产品总监评审意见2

    private String proMajIdea3;// 产品总监评审意见3

    private String proMajText;// 产品总监补充说明

    private String legalFlag;// 法务专员有无标识

    private String legalId;// 法务专员登录名

    private String legalName;// 法务专员用户名

    private String legalDate;// 法务专员评审日期

    private String legalDate2;// 法务专员评审日期（去掉时分秒）

    private String legalIdea;// 法务专员评审意见

    private String legalIdea1;// 法务专员评审意见

    private String legalText;// 法务专员补充说明

    private String sellMajId;// 销售总监登录名

    private String sellMajName;// 销售总监用户名

    private String sellMajDate;// 销售总监评审日期

    private String sellMajDate2;// 销售总监评审日期（去掉时分秒）

    private String sellMajIdea;// 销售总监评审意见（插入数据库用）

    private String sellMajIdea1;// 销售总监评审意见1

    private String sellMajIdea2;// 销售总监评审意见2

    private String sellMajIdea3;// 销售总监评审意见3

    private String sellMajIdea4;// 销售总监评审意见4

    private String sellMajText;// 销售总监补充说明

    private String opeMajId;// 运营总监登录名

    private String opeMajName;// 运营总监用户名

    private String opeMajDate;// 运营总监评审日期

    private String opeMajDate2;// 运营总监评审日期（去掉时分秒）

    private String opeMajIdea;// 运营总监评审意见

    private String opeMajIdea1;// 运营总监评审意见

    private String opeMajText;// 运营总监补充说明

    private String areaMajId;// 区域总监登录名

    private String areaMajName;// 区域总监用户名

    private String areaMajIdea;// 区域总监评审意见

    private String areaMajIdea1;// 区域总监意见1

    private String areaMajIdea2;// 区域总监意见2

    private String areaMajIdea3;// 区域总监意见3

    private String areaMajIdea4;// 区域总监意见4

    private String areaMajText;// 区域总监补充说明

    private String areaMajDate;// 区域总监评审日期

    private String prodTypeName;// 产品分类名称

    private String invoiceType;// 发票类型

    private String deptName;// 部门名称

    private String productDeptFax;// 部门传真号

    private String productDeptAccount;// 部门账号

    private String remitBankName;// 供方开户行

    private boolean LackProduct; // 是否缺货 1:缺货, 0不缺货

    /** 销售项目名称 */
    private String contractProName;

    public String getContractProName() {
        return contractProName;
    }

    public void setContractProName(String contractProName) {
        this.contractProName = contractProName;
    }

    public boolean isLackProduct() {
        return LackProduct;
    }

    public void setLackProduct(boolean lackProduct) {
        LackProduct = lackProduct;
    }

    /**
     * @return the prepayMoney
     */
    public String getPrepayMoney() {
        return prepayMoney;
    }

    /**
     * @param prepayMoney
     *            the prepayMoney to set
     */
    public void setPrepayMoney(String prepayMoney) {
        this.prepayMoney = prepayMoney;
    }

    /**
     * @return the transitMoney
     */
    public String getTransitMoney() {
        return transitMoney;
    }

    /**
     * @param transitMoney
     *            the transitMoney to set
     */
    public void setTransitMoney(String transitMoney) {
        this.transitMoney = transitMoney;
    }

    public String getRemitBankName() {
        return remitBankName;
    }

    public void setRemitBankName(String remitBankName) {
        this.remitBankName = remitBankName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getClimit() {
        return climit;
    }

    public void setClimit(BigDecimal climit) {
        this.climit = climit;
    }

    public BigDecimal getFreeLimit() {
        return freeLimit;
    }

    public void setFreeLimit(BigDecimal freeLimit) {
        this.freeLimit = freeLimit;
    }

    public String getProductContractCode() {
        return productContractCode;
    }

    public void setProductContractCode(String productContractCode) {
        this.productContractCode = productContractCode;
    }

    public String getCompanyContractCode() {
        return this.companyContractCode;
    }

    public String getCreditProjectName() {
        return creditProjectName;
    }

    public void setCreditProjectName(String creditProjectName) {
        this.creditProjectName = creditProjectName;
    }

    public void setCompanyContractCode(String companyContractCode) {
        this.companyContractCode = companyContractCode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStampType() {
        return stampType;
    }

    public void setStampType(Integer stampType) {
        this.stampType = stampType;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Integer getProtectLimit() {
        return protectLimit;
    }

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    public void setProtectLimit(Integer protectLimit) {
        this.protectLimit = protectLimit;
    }

    public Integer getCheckLimit() {
        return checkLimit;
    }

    public void setCheckLimit(Integer checkLimit) {
        this.checkLimit = checkLimit;
    }

    public String getBreach() {
        return breach;
    }

    public void setBreach(String breach) {
        this.breach = breach;
    }

    public String getLawsuit() {
        return lawsuit;
    }

    public void setLawsuit(String lawsuit) {
        this.lawsuit = lawsuit;
    }

    public String getClause() {
        return clause;
    }

    public void setClause(String clause) {
        this.clause = clause;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getCustomerLinkmanId() {
        return customerLinkmanId;
    }

    public void setCustomerLinkmanId(Integer customerLinkmanId) {
        this.customerLinkmanId = customerLinkmanId;
    }

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    public Integer getPaymentWay() {
        return paymentWay;
    }

    public void setPaymentWay(Integer paymentWay) {
        this.paymentWay = paymentWay;
    }

    public Integer getBatchWay() {
        return batchWay;
    }

    public void setBatchWay(Integer batchWay) {
        this.batchWay = batchWay;
    }

    public Integer getCustomerCreditId() {
        return customerCreditId;
    }

    public BigDecimal getCashMoney() {
        return cashMoney;
    }

    public void setCashMoney(BigDecimal cashMoney) {
        this.cashMoney = cashMoney;
    }

    public BigDecimal getBatchMaxMoney() {
        return batchMaxMoney;
    }

    public void setBatchMaxMoney(BigDecimal batchMaxMoney) {
        this.batchMaxMoney = batchMaxMoney;
    }

    public void setCustomerCreditId(Integer customerCreditId) {
        this.customerCreditId = customerCreditId;
    }

    public Integer getCreditTypeId() {
        return creditTypeId;
    }

    public void setCreditTypeId(Integer creditTypeId) {
        this.creditTypeId = creditTypeId;
    }

    public String getCreditTypeName() {
        return creditTypeName;
    }

    public void setCreditTypeName(String creditTypeName) {
        this.creditTypeName = creditTypeName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * @return the arterm
     */
    public Integer getArterm() {
        return arterm;
    }

    /**
     * @param arterm
     *            the arterm to set
     */
    public void setArterm(Integer arterm) {
        this.arterm = arterm;
    }

    /**
     * @return the climit
     */

    public BigDecimal getUsedLimit() {
        return usedLimit;
    }

    public void setUsedLimit(BigDecimal usedLimit) {
        this.usedLimit = usedLimit;
    }

    public BigDecimal getLockLimit() {
        return lockLimit;
    }

    public void setLockLimit(BigDecimal lockLimit) {
        this.lockLimit = lockLimit;
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

    public String getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(String interestRate) {
        this.interestRate = interestRate;
    }

    public FormFile getFile() {
        return file;
    }

    public void setFile(FormFile file) {
        this.file = file;
    }

    public Integer getCustomerAddressId() {
        return customerAddressId;
    }

    public void setCustomerAddressId(Integer customerAddressId) {
        this.customerAddressId = customerAddressId;
    }

    public String getRequestSendDate() {
        return requestSendDate;
    }

    public void setRequestSendDate(String requestSendDate) {
        this.requestSendDate = requestSendDate;
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

    public Integer getUserAreaId() {
        return userAreaId;
    }

    public void setUserAreaId(Integer userAreaId) {
        this.userAreaId = userAreaId;
    }

    public String getUserAreaName() {
        return userAreaName;
    }

    public void setUserAreaName(String userAreaName) {
        this.userAreaName = userAreaName;
    }

    public String getProMajFlag() {
        return proMajFlag;
    }

    public void setProMajFlag(String proMajFlag) {
        this.proMajFlag = proMajFlag;
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

    public String getProMajIdea() {
        return proMajIdea;
    }

    public void setProMajIdea(String proMajIdea) {
        this.proMajIdea = proMajIdea;
    }

    public String getProMajIdea1() {
        return proMajIdea1;
    }

    public void setProMajIdea1(String proMajIdea1) {
        this.proMajIdea1 = proMajIdea1;
    }

    public String getProMajIdea2() {
        return proMajIdea2;
    }

    public void setProMajIdea2(String proMajIdea2) {
        this.proMajIdea2 = proMajIdea2;
    }

    public String getProMajIdea3() {
        return proMajIdea3;
    }

    public void setProMajIdea3(String proMajIdea3) {
        this.proMajIdea3 = proMajIdea3;
    }

    public String getProMajText() {
        return proMajText;
    }

    public void setProMajText(String proMajText) {
        this.proMajText = proMajText;
    }

    public String getLegalFlag() {
        return legalFlag;
    }

    public void setLegalFlag(String legalFlag) {
        this.legalFlag = legalFlag;
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

    public String getLegalIdea1() {
        return legalIdea1;
    }

    public void setLegalIdea1(String legalIdea1) {
        this.legalIdea1 = legalIdea1;
    }

    public String getLegalText() {
        return legalText;
    }

    public void setLegalText(String legalText) {
        this.legalText = legalText;
    }

    public String getSellMajId() {
        return sellMajId;
    }

    public void setSellMajId(String sellMajId) {
        this.sellMajId = sellMajId;
    }

    public String getSellMajName() {
        return sellMajName;
    }

    public void setSellMajName(String sellMajName) {
        this.sellMajName = sellMajName;
    }

    public String getSellMajDate() {
        return sellMajDate;
    }

    public void setSellMajDate(String sellMajDate) {
        this.sellMajDate = sellMajDate;
    }

    public String getSellMajIdea() {
        return sellMajIdea;
    }

    public void setSellMajIdea(String sellMajIdea) {
        this.sellMajIdea = sellMajIdea;
    }

    public String getSellMajIdea1() {
        return sellMajIdea1;
    }

    public void setSellMajIdea1(String sellMajIdea1) {
        this.sellMajIdea1 = sellMajIdea1;
    }

    public String getSellMajIdea2() {
        return sellMajIdea2;
    }

    public void setSellMajIdea2(String sellMajIdea2) {
        this.sellMajIdea2 = sellMajIdea2;
    }

    public String getSellMajIdea3() {
        return sellMajIdea3;
    }

    public void setSellMajIdea3(String sellMajIdea3) {
        this.sellMajIdea3 = sellMajIdea3;
    }

    public String getSellMajIdea4() {
        return sellMajIdea4;
    }

    public void setSellMajIdea4(String sellMajIdea4) {
        this.sellMajIdea4 = sellMajIdea4;
    }

    public String getSellMajText() {
        return sellMajText;
    }

    public void setSellMajText(String sellMajText) {
        this.sellMajText = sellMajText;
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

    public String getOpeMajIdea1() {
        return opeMajIdea1;
    }

    public void setOpeMajIdea1(String opeMajIdea1) {
        this.opeMajIdea1 = opeMajIdea1;
    }

    public String getOpeMajText() {
        return opeMajText;
    }

    public void setOpeMajText(String opeMajText) {
        this.opeMajText = opeMajText;
    }

    public String getProdTypeName() {
        return prodTypeName;
    }

    public void setProdTypeName(String prodTypeName) {
        this.prodTypeName = prodTypeName;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getProductDeptFax() {
        return productDeptFax;
    }

    public void setProductDeptFax(String productDeptFax) {
        this.productDeptFax = productDeptFax;
    }

    public String getProductDeptAccount() {
        return productDeptAccount;
    }

    public void setProductDeptAccount(String productDeptAccount) {
        this.productDeptAccount = productDeptAccount;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getCustomerProvince() {
        return customerProvince;
    }

    public void setCustomerProvince(String customerProvince) {
        this.customerProvince = customerProvince;
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }

    public String getCustomerLinkmanTel() {
        return customerLinkmanTel;
    }

    public void setCustomerLinkmanTel(String customerLinkmanTel) {
        this.customerLinkmanTel = customerLinkmanTel;
    }

    public String getCustomerLinkmanFax() {
        return customerLinkmanFax;
    }

    public void setCustomerLinkmanFax(String customerLinkmanFax) {
        this.customerLinkmanFax = customerLinkmanFax;
    }

    public String getAgreementCode() {
        return agreementCode;
    }

    public void setAgreementCode(String agreementCode) {
        this.agreementCode = agreementCode;
    }

    public String getCustomerInvoiceBankName() {
        return customerInvoiceBankName;
    }

    public void setCustomerInvoiceBankName(String customerInvoiceBankName) {
        this.customerInvoiceBankName = customerInvoiceBankName;
    }

    public String getCustomerInvoiceBankAccount() {
        return customerInvoiceBankAccount;
    }

    public void setCustomerInvoiceBankAccount(String customerInvoiceBankAccount) {
        this.customerInvoiceBankAccount = customerInvoiceBankAccount;
    }

    public String getCustomerTaxNumber() {
        return customerTaxNumber;
    }

    public void setCustomerTaxNumber(String customerTaxNumber) {
        this.customerTaxNumber = customerTaxNumber;
    }

    public Integer getCustomerInvoiceType() {
        return customerInvoiceType;
    }

    public void setCustomerInvoiceType(Integer customerInvoiceType) {
        this.customerInvoiceType = customerInvoiceType;
    }

    public String getCustomerAddressName() {
        return customerAddressName;
    }

    public void setCustomerAddressName(String customerAddressName) {
        this.customerAddressName = customerAddressName;
    }

    public String getCustomerAddressAddress() {
        return customerAddressAddress;
    }

    public void setCustomerAddressAddress(String customerAddressAddress) {
        this.customerAddressAddress = customerAddressAddress;
    }

    public String getCustomerAddressLinkman() {
        return customerAddressLinkman;
    }

    public void setCustomerAddressLinkman(String customerAddressLinkman) {
        this.customerAddressLinkman = customerAddressLinkman;
    }

    public String getCustomerAddressPostcode() {
        return customerAddressPostcode;
    }

    public void setCustomerAddressPostcode(String customerAddressPostcode) {
        this.customerAddressPostcode = customerAddressPostcode;
    }

    public String getCustomerAddressTel() {
        return customerAddressTel;
    }

    public void setCustomerAddressTel(String customerAddressTel) {
        this.customerAddressTel = customerAddressTel;
    }

    public String getCustomerAddressMobile() {
        return customerAddressMobile;
    }

    public void setCustomerAddressMobile(String customerAddressMobile) {
        this.customerAddressMobile = customerAddressMobile;
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

    public String getProductTypeNo() {
        return productTypeNo;
    }

    public void setProductTypeNo(String productTypeNo) {
        this.productTypeNo = productTypeNo;
    }

    public String getProductDeptNo() {
        return productDeptNo;
    }

    public void setProductDeptNo(String productDeptNo) {
        this.productDeptNo = productDeptNo;
    }

    public String getCustomerLinkmanName() {
        return customerLinkmanName;
    }

    public void setCustomerLinkmanName(String customerLinkmanName) {
        this.customerLinkmanName = customerLinkmanName;
    }

    public String getInterestRateRound() {
        return interestRateRound;
    }

    public void setInterestRateRound(String interestRateRound) {
        this.interestRateRound = interestRateRound;
    }

    public String getProMajDate2() {
        return proMajDate2;
    }

    public void setProMajDate2(String proMajDate2) {
        this.proMajDate2 = proMajDate2;
    }

    public String getLegalDate2() {
        return legalDate2;
    }

    public void setLegalDate2(String legalDate2) {
        this.legalDate2 = legalDate2;
    }

    public String getSellMajDate2() {
        return sellMajDate2;
    }

    public void setSellMajDate2(String sellMajDate2) {
        this.sellMajDate2 = sellMajDate2;
    }

    public String getOpeMajDate2() {
        return opeMajDate2;
    }

    public void setOpeMajDate2(String opeMajDate2) {
        this.opeMajDate2 = opeMajDate2;
    }

    public String getAreaMajId() {
        return areaMajId;
    }

    public void setAreaMajId(String areaMajId) {
        this.areaMajId = areaMajId;
    }

    public String getAreaMajName() {
        return areaMajName;
    }

    public void setAreaMajName(String areaMajName) {
        this.areaMajName = areaMajName;
    }

    public String getAreaMajIdea() {
        if (this.areaMajIdea1 != null && this.areaMajIdea2 != null
                && this.areaMajIdea3 != null && this.areaMajIdea4 != null) {
            areaMajIdea = this.areaMajIdea1 + this.areaMajIdea2 + this.areaMajIdea3
                    + this.areaMajIdea4;
        }
        return areaMajIdea;
    }

    public void setAreaMajIdea(String areaMajIdea) {
        if (areaMajIdea != null && areaMajIdea.length() == 4) {
            this.areaMajIdea1 = areaMajIdea.substring(0, 1);
            this.areaMajIdea2 = areaMajIdea.substring(1, 2);
            this.areaMajIdea3 = areaMajIdea.substring(2, 3);
            this.areaMajIdea4 = areaMajIdea.substring(3, 4);
        }
        this.areaMajIdea = areaMajIdea;
    }

    public String getAreaMajIdea1() {
        return areaMajIdea1;
    }

    public void setAreaMajIdea1(String areaMajIdea1) {
        this.areaMajIdea1 = areaMajIdea1;
    }

    public String getAreaMajIdea2() {
        return areaMajIdea2;
    }

    public void setAreaMajIdea2(String areaMajIdea2) {
        this.areaMajIdea2 = areaMajIdea2;
    }

    public String getAreaMajIdea3() {
        return areaMajIdea3;
    }

    public void setAreaMajIdea3(String areaMajIdea3) {
        this.areaMajIdea3 = areaMajIdea3;
    }

    public String getAreaMajIdea4() {
        return areaMajIdea4;
    }

    public void setAreaMajIdea4(String areaMajIdea4) {
        this.areaMajIdea4 = areaMajIdea4;
    }

    public String getAreaMajText() {
        return areaMajText;
    }

    public void setAreaMajText(String areaMajText) {
        this.areaMajText = areaMajText;
    }

    public String getAreaMajDate() {
        return areaMajDate;
    }

    public void setAreaMajDate(String areaMajDate) {
        this.areaMajDate = areaMajDate;
    }

    /**
     * @return the dateTime
     */
    public String getDateTime() {
        return dateTime;
    }

    /**
     * @param dateTime
     *            the dateTime to set
     */
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }
}
