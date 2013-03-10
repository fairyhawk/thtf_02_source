package cn.com.thtf.egov.cms.form;

import java.math.BigDecimal;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

/**
 * 销售合同form(新建，修改)
 * 
 * @author 李乐伟
 */
public class SellContractForm extends ActionForm {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String id; // 销售合同id

    private String productContractCode; // 产品合同编码

    private String companyContarctCode; // 公司合同编码

    private String date; // 签订日期

    private Integer status; // 合同状态

    private Integer stampType; // 盖章类型

    private Integer templateType;// 模板类型

    private String place; // 签订地

    private Integer protectLimit; // 保价期限

    private Integer checkLimit; // 验收期限

    private String breach; // 违约金

    private String lawsuit; // 诉讼地

    private String clause; // 附加条款(自定义模板用)

    private FormFile file; // 文件(非模板用)

    private Integer customerId; // 客户id

    private String customerName; // 客户名

    private Integer customerLinkmanId; // 客户联系人id

    private Integer productTypeId; // 产品类型id

    private Integer paymentWay; // 付款方式

    private Integer batchWay; // 分批方式

    private BigDecimal cashMoney; // 现结金额

    private BigDecimal batchMaxMoney; // 批最大金额

    private Integer customerCreditId; // 客户信用id

    private Integer creditTypeId; // 信用类型id

    private String projectName; // 项目名称

    private Integer arterm; // 帐期

    private BigDecimal climit; // 信用额度

    private BigDecimal useCredit; //

    private BigDecimal lockCredit; // 冻结额度

    private Integer customerAddressId; // 客户地址id

    private String requestSendDate; // 要求发货日期

    private String userId; // 用户id

    private String userName;// 用户名

    private Integer userAreaId; // 用户区域id

    private String text; // 特殊说明

    private BigDecimal money; // 合同金额

    private BigDecimal interestRate; // 合同利率

    private String proMajId; // 产品总监id

    private String proMajName;// 产品总监name

    private String proMajDate;// 产品总监评审日期

    private String proMajIdea;// 产品总监意见

    private String proMajText;// 产品总监补充说明

    private String legalId; // 法务专员id

    private String legalName;// 法务专员名

    private String legalDate;// 法务专员评审日期

    private Integer legalIdea;// 法务专员意见

    private String legalText;// 法务专员补充说明

    private String sellMajId;// 销售总监id

    private String sellMajName;// 销售总监name

    private String sellMajDate;// 销售总监评审日期

    private String sellMajIdea;// 销售总监意见

    private String sellMajText;// 销售总监补充说明

    private String opeMajId;// 运营总监id

    private String opeMajName;// 运营总监name

    private String opeMajDate;// 运营总监评审日期

    private Integer opeMajIdea;// 运营总监意见

    private String opeMajText;// 运营总监被说明

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

    public Integer getProtectLimit() {
        return protectLimit;
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

    public FormFile getFile() {
        return file;
    }

    public void setFile(FormFile file) {
        this.file = file;
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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getArterm() {
        return arterm;
    }

    public void setArterm(Integer arterm) {
        this.arterm = arterm;
    }

    public BigDecimal getClimit() {
        return climit;
    }

    public void setClimit(BigDecimal climit) {
        this.climit = climit;
    }

    public BigDecimal getUseCredit() {
        return useCredit;
    }

    public void setUseCredit(BigDecimal useCredit) {
        this.useCredit = useCredit;
    }

    public BigDecimal getLockCredit() {
        return lockCredit;
    }

    public void setLockCredit(BigDecimal lockCredit) {
        this.lockCredit = lockCredit;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
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

    public String getProMajText() {
        return proMajText;
    }

    public void setProMajText(String proMajText) {
        this.proMajText = proMajText;
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

    public Integer getLegalIdea() {
        return legalIdea;
    }

    public void setLegalIdea(Integer legalIdea) {
        this.legalIdea = legalIdea;
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

    public Integer getOpeMajIdea() {
        return opeMajIdea;
    }

    public void setOpeMajIdea(Integer opeMajIdea) {
        this.opeMajIdea = opeMajIdea;
    }

    public String getOpeMajText() {
        return opeMajText;
    }

    public void setOpeMajText(String opeMajText) {
        this.opeMajText = opeMajText;
    }

}
