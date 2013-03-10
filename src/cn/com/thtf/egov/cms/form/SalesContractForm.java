package cn.com.thtf.egov.cms.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

/**
 * 销售合同form(新建，修改)
 * 
 * @author 李乐伟
 */
public class SalesContractForm extends ActionForm {

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

    private String templateType;// 模板类型

    private String place; // 签订地

    private Integer protectLimit; // 保价期限

    private Integer checkLimit; // 验收期限

    private String breach; // 违约金

    private String lawsuit; // 诉讼地

    private String clause; // 附加条款(自定义模板用)

    private FormFile contractFile; // 文件(非模板用)

    private Integer customerId; // 客户id

    private String customerName; // 客户名

    private Integer customerLinkmanId; // 客户联系人id

    private Integer productTypeId; // 产品类型id

    private Integer paymentWay; // 付款方式

    private Integer batchWay; // 分批方式

    private String cashMoney; // 现结金额

    private String batchMaxMoney; // 批最大金额

    private Integer customerCreditId; // 客户信用id

    private Integer creditTypeId; // 信用类型id

    private String projectName; // 项目名称

    private Integer arterm; // 帐期

    private String climit; // 信用额度

    private String usedLimit; // 已用额度

    private String freeLimit;// 可用额度

    private String lockLimit; // 冻结额度

    private Integer customerAddressId; // 客户地址id
    // 发货信息定义

    private String customerAddressName;// 客户发货地址单位名称

    private String customerAddressAddress;// 客户发货地址

    private String customerAddressLinkman;// 客户发货地址联系人

    private String customerAddressPostcode; // 客户发货地址邮编

    private String customerAddressTel;// 客户发货地址电话

    private String customerAddressMobile;// 客户发货地址手机
    private String requestSendDate; // 要求发货日期

    private String userId; // 用户id

    private String userName;// 用户名

    private Integer userAreaId; // 用户区域id

    private String text; // 特殊说明

    private String money; // 合同金额

    private String interestRate; // 合同利率

    private String proMajId; // 产品总监id

    private String proMajName;// 产品总监name

    private String proMajDate;// 产品总监评审日期

    private String proMajIdea;// 产品总监意见

    private String proMajIdea1;// 产品总监意见1

    private String proMajIdea2;// 产品总监意见2

    private String proMajIdea3;// 产品总监意见3

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

    private String sellMajIdea1;// 销售总监意见1

    private String sellMajIdea2;// 销售总监意见2

    private String sellMajIdea3;// 销售总监意见3

    private String sellMajIdea4;// 销售总监意见4

    private String sellMajText;// 销售总监补充说明

    private String opeMajId;// 运营总监id

    private String opeMajName;// 运营总监name

    private String opeMajDate;// 运营总监评审日期

    private Integer opeMajIdea;// 运营总监意见

    private String opeMajText;// 运营总监被说明

    private String areaMajId;// 区域总监id

    private String areaMajName;// 区域总监name

    private String areaMajIdea;// 区域总监意见

    private String areaMajIdea1;// 区域总监意见1

    private String areaMajIdea2;// 区域总监意见2

    private String areaMajIdea3;// 区域总监意见3

    private String areaMajIdea4;// 区域总监意见4

    private String areaMajText;// 区域总监说明

    private String areaMajDate;// 区域总监评审日期

    private String dateTime;// 更新时间

    private String contractProName;//销售项目名称
    public String getContractProName() {
        return contractProName;
    }

    public void setContractProName(String contractProName) {
        this.contractProName = contractProName;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getFreeLimit() {
        return freeLimit;
    }

    public void setFreeLimit(String freeLimit) {
        this.freeLimit = freeLimit;
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

    public String getClimit() {
        return climit;
    }

    public void setClimit(String climit) {
        this.climit = climit;
    }

    public String getUsedLimit() {
        return usedLimit;
    }

    public void setUsedLimit(String usedLimit) {
        this.usedLimit = usedLimit;
    }

    public String getLockLimit() {
        return lockLimit;
    }

    public void setLockLimit(String lockLimit) {
        this.lockLimit = lockLimit;
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

    public FormFile getContractFile() {
        return contractFile;
    }

    public void setContractFile(FormFile contractFile) {
        this.contractFile = contractFile;
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
}
