/**
 * ClassName  AreaInfo
 *
 * History
 * Create User: zhaolei
 * Create Date: 2010-4-19
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

import java.io.Serializable;

import org.apache.struts.upload.FormFile;

/**
 * @author zhaolei
 * 
 */
public class SellContractInfoDto implements Serializable {

    /**
     * 销售合同
     */
    private static final long serialVersionUID = 1L;

    private String id;// 合同流水号

    private String productContractCode;// 产品合同号

    private String companyContarctCode;// 公司合同号

    private String date;// 签订日期

    private Integer status;// 合同状态

    private Integer stampType;// 盖章类型

    private Integer templateType;// 模板类型

    private String place;// 签订地点

    private Integer protect;// 保价期限

    private Integer protectLimit;// 保价期限

    private Integer check;// 验收期限

    private Integer checkLimit;// 验收期限

    private String breach;// 违约金标准

    private String lawsuit;// 诉讼地

    private String clause;// 附加条款

    private String fileName;// 文件名称

    private Integer customerId;// 客户编号

    private String customerName;// 客户名称
    
 


    private Integer customerLinkmanId;// 客户联系人编号

    private Integer productTypeId;// 产品分类编号

    private String productTypeName;// 产品分类名称

    private Integer paymentWay;// 付款方式

    private Integer batchWay;// 分批方式

    private String cashMoney;// 现结金额

    private String batchMaxMoney;// 单批最大金额

    private Integer customerCreditId;// 客户信用编号

    private Integer creditTypeId;// 信用类型编号

    private String creditTypeName; // 信用类型名称

    private String projectName;// 项目名称

    private Integer arterm;// 帐期

    private String climit;// 信用额度

    private String useCredit;// 已用

    private String lockCredit;// 冻结

    private String usableLimit; // 可用额度

    private String text;// 特殊说明

    private String money;// 合同金额

    private String interestRate;// 总利率

    private FormFile file;   //合同文件(合同类型为非模板时使用)

    private Integer customerAddressId;// 客户发货地址编号

    private String requestSendDate;// 要求发货日期

    private String userId;// 销售经理登录名

    private String userName;// 人员名称

    private Integer userAreaId;// 人员区域编号

    private String userAreaName;// 人员区域名称

    private String userTel;// 用户电话

    private String proMajFlag;// 产品总监有无标识

    private String proMajId;// 产品总监登录名

    private String proMajName;// 产品总监用户名

    private String proMajDate;// 产品总监评审日期

    private String proMajIdea;// 产品总监评审意见（插入数据库用）

    private String proMajIdea1;// 产品总监评审意见1

    private String proMajIdea2;// 产品总监评审意见2

    private String proMajIdea3;// 产品总监评审意见3

    private String proMajText;// 产品总监补充说明

    private Integer legalFlag;// 法务专员有无标识

    private String legalId;// 法务专员登录名

    private String legalName;// 法务专员用户名

    private String legalDate;// 法务专员评审日期

    private String legalIdea;// 法务专员评审意见

    private String legalIdea1;// 法务专员评审意见

    private String legalText;// 法务专员补充说明

    private String sellMajId;// 销售总监登录名

    private String sellMajName;// 销售总监用户名

    private String sellMajDate;// 销售总监评审日期

    private String sellMajIdea;// 销售总监评审意见（插入数据库用）

    private String sellMajIdea1;// 销售总监评审意见1

    private String sellMajIdea2;// 销售总监评审意见2

    private String sellMajIdea3;// 销售总监评审意见3

    private String sellMajIdea4;// 销售总监评审意见3

    private String sellMajText;// 销售总监补充说明

    private String opeMajId;// 运营总监登录名

    private String opeMajName;// 运营总监用户名

    private String opeMajDate;// 运营总监评审日期

    private String opeMajIdea;// 运营总监评审意见

    private String opeMajIdea1;// 运营总监评审意见

    private String opeMajText;// 运营总监补充说明

    private String prodTypeName;// 产品分类名称

    private String invoiceType;// 发票类型

    private String deptName;// 部门名称

    private String deptFax;// 部门传真号

    private String deptAccount;// 部门账号

    private String deptno;

    private String typeno;

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

    public String getCompanyContarctCode() {
        return companyContarctCode;
    }

    public void setCompanyContarctCode(String companyContarctCode) {
        this.companyContarctCode = companyContarctCode;
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

    public Integer getProtect() {
        return protect;
    }

    public void setProtect(Integer protect) {
        this.protect = protect;
    }

    public Integer getProtectLimit() {
        return protectLimit;
    }

    public void setProtectLimit(Integer protectLimit) {
        this.protectLimit = protectLimit;
    }

    public Integer getCheck() {
        return check;
    }

    public void setCheck(Integer check) {
        this.check = check;
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

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
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

    public String getCashMoney() {
        return cashMoney;
    }

    public void setCashMoney(String cashMoney) {
        this.cashMoney = cashMoney;
    }

    public String getBatchMaxMoney() {
        return batchMaxMoney;
    }

    public void setBatchMaxMoney(String batchMaxMoney) {
        this.batchMaxMoney = batchMaxMoney;
    }

    public Integer getCustomerCreditId() {
        return customerCreditId;
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
    public String getClimit() {
        return climit;
    }

    /**
     * @param climit
     *            the climit to set
     */
    public void setClimit(String climit) {
        this.climit = climit;
    }

    /**
     * @return the useCredit
     */
    public String getUseCredit() {
        return useCredit;
    }

    /**
     * @param useCredit
     *            the useCredit to set
     */
    public void setUseCredit(String useCredit) {
        this.useCredit = useCredit;
    }

    /**
     * @return the lockCredit
     */
    public String getLockCredit() {
        return lockCredit;
    }

    /**
     * @param lockCredit
     *            the lockCredit to set
     */
    public void setLockCredit(String lockCredit) {
        this.lockCredit = lockCredit;
    }

    /**
     * @return the usableLimit
     */
    public String getUsableLimit() {
        return usableLimit;
    }

    public void setUsableLimit(String usableLimit) {
        this.usableLimit = usableLimit;
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

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
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

    public Integer getLegalFlag() {
        return legalFlag;
    }

    public void setLegalFlag(Integer legalFlag) {
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

    public String getDeptFax() {
        return deptFax;
    }

    public void setDeptFax(String deptFax) {
        this.deptFax = deptFax;
    }

    public String getDeptAccount() {
        return deptAccount;
    }

    public void setDeptAccount(String deptAccount) {
        this.deptAccount = deptAccount;
    }

    public String getDeptno() {
        return deptno;
    }

    public void setDeptno(String deptno) {
        this.deptno = deptno;
    }

    public String getTypeno() {
        return typeno;
    }

    public void setTypeno(String typeno) {
        this.typeno = typeno;
    }

}
