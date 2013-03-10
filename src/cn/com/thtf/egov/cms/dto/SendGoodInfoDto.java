package cn.com.thtf.egov.cms.dto;

import java.math.BigDecimal;

public class SendGoodInfoDto {

    private String id;

    private String sendSellContractId;

    private Integer sendProductTypeId;

    private Integer sendCustomerId; // 发货单客户发货地址ID

    private String sendCustomerName;

    private Integer sendSendGoodsType;

    private String date;

    private String sendRequestDate;

    private String sendSendDate;

    private Integer sendarterm; // 发货单合同金额

    private BigDecimal htmoney;

    private Integer status;

    private Integer sendStockRoomId;

    private Integer sendCustomerAddressId;

    private Integer sendTransportWay;

    private String sendTakeName;

    private String sendTakeNumber;

    private String sendUserId;

    private String sendUserName;

    private Integer sendUserAreaId;

    private String sendtext; // 发货单特殊说明

    private Integer sendExceDays;

    private BigDecimal mreturn;

    private BigDecimal sendExceedReturn;

    private BigDecimal sendExceedCount;

    private Integer sendExtendExceedDays;

    private BigDecimal sendExtendReturn;

    private BigDecimal sendExtendExceedReturn;

    private String sendProMajId;

    private String sendProMajName;

    private String sendProMajDate;

    private String sendProMajIdea;

    private String sendProMajText;

    private String sendStkAdmId;

    private String sendStkAdmName;

    private String sendStkAdmDate;

    private String sendStkAdmIdea;

    private String sendStkAdmText;

    private String datetime;

    private Integer sendCreditTypeId;

    private String sendProjectName;

    private BigDecimal climit;

    private BigDecimal free;

    private BigDecimal sendPrepayMoney;

    private BigDecimal sendTransitMoney;

    private Integer creditid;

    private String creditname;

    private String sellid;

    private String productContractCode;

    private String companyContarctCode;

    private String selldate;

    private Integer sellstatus;

    private Integer stampType;

    private Integer templateType;

    private String place;

    private Integer protectLimit;

    private Integer checkLimit;

    private String breach;

    private String lawsuit;

    private String clause;

    private String file;

    private Integer customerId;

    private String customerName;

    private Integer customerLinkmanId;

    private Integer productTypeId;

    private Integer paymentWay;

    private Integer batchWay;

    private BigDecimal cashMoney;

    private BigDecimal batchMaxMoney;

    private Integer customerCreditId;

    private Integer creditTypeId;

    private String projectName;

    private Integer arterm;

    private BigDecimal sellclimit;

    private BigDecimal sellfree;

    private Integer customerAddressId;

    private String requestSendDate;

    private String userId;

    private String userName;

    private Integer userAreaId;

    private String text;

    private BigDecimal money;

    private BigDecimal interestRate;

    private String proMajId;

    private String proMajName;

    private String proMajDate;

    private String proMajIdea;

    private String proMajText;

    private String legalId;

    private String legalName;

    private String legalDate;

    private Integer legalIdea;

    private String legalText;

    private String sellMajId;

    private String sellMajName;

    private String sellMajDate;

    private String sellMajIdea;

    private String sellMajText;

    private String opeMajId;

    private String opeMajName;

    private String opeMajDate;

    private Integer opeMajIdea;

    private String opeMajText;

    private String selldatetime;

    // 客户
    private Integer custid;

    private Integer custCustomerId;

    private String custname;

    private String address;

    private String postcode;

    private String linkman;

    private String tel;

    private String mobile;

    private Integer areaid;

    private String areaname;

    private String sroomName;

    private String sroomId;

    private String ptName;

    private String sendAreaMajDate;
    private String sendAreaMajId;
    private String sendAreaMajIdea;
    private String sendAreaMajName;
    private String sendAreaMajText;

    /** 销售项目名称 */
    private String contractProName;

    public String getContractProName() {
        return contractProName;
    }

    public void setContractProName(String contractProName) {
        this.contractProName = contractProName;
    }

    /**
     * @return the sroomId
     */
    public String getSroomId() {
        return sroomId;
    }

    public String getSendAreaMajDate() {
        return sendAreaMajDate;
    }

    public void setSendAreaMajDate(String sendAreaMajDate) {
        this.sendAreaMajDate = sendAreaMajDate;
    }

    public String getSendAreaMajId() {
        return sendAreaMajId;
    }

    public void setSendAreaMajId(String sendAreaMajId) {
        this.sendAreaMajId = sendAreaMajId;
    }

    public String getSendAreaMajIdea() {
        return sendAreaMajIdea;
    }

    public void setSendAreaMajIdea(String sendAreaMajIdea) {
        this.sendAreaMajIdea = sendAreaMajIdea;
    }

    public String getSendAreaMajName() {
        return sendAreaMajName;
    }

    public void setSendAreaMajName(String sendAreaMajName) {
        this.sendAreaMajName = sendAreaMajName;
    }

    public String getSendAreaMajText() {
        return sendAreaMajText;
    }

    public void setSendAreaMajText(String sendAreaMajText) {
        this.sendAreaMajText = sendAreaMajText;
    }

    /**
     * @param sroomId
     *            the sroomId to set
     */
    public void setSroomId(String sroomId) {
        this.sroomId = sroomId;
    }

    public String getSroomName() {
        return sroomName;
    }

    public void setSroomName(String sroomName) {
        this.sroomName = sroomName;
    }

    public String getPtName() {
        return ptName;
    }

    public void setPtName(String ptName) {
        this.ptName = ptName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSendSellContractId() {
        return sendSellContractId;
    }

    public void setSendSellContractId(String sendSellContractId) {
        this.sendSellContractId = sendSellContractId;
    }

    public Integer getSendProductTypeId() {
        return sendProductTypeId;
    }

    public void setSendProductTypeId(Integer sendProductTypeId) {
        this.sendProductTypeId = sendProductTypeId;
    }

    public Integer getSendCustomerId() {
        return sendCustomerId;
    }

    public void setSendCustomerId(Integer sendCustomerId) {
        this.sendCustomerId = sendCustomerId;
    }

    public String getSendCustomerName() {
        return sendCustomerName;
    }

    public void setSendCustomerName(String sendCustomerName) {
        this.sendCustomerName = sendCustomerName;
    }

    public Integer getSendSendGoodsType() {
        return sendSendGoodsType;
    }

    public void setSendSendGoodsType(Integer sendSendGoodsType) {
        this.sendSendGoodsType = sendSendGoodsType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSendRequestDate() {
        return sendRequestDate;
    }

    public void setSendRequestDate(String sendRequestDate) {
        this.sendRequestDate = sendRequestDate;
    }

    public String getSendSendDate() {
        return sendSendDate;
    }

    public void setSendSendDate(String sendSendDate) {
        this.sendSendDate = sendSendDate;
    }

    public Integer getSendarterm() {
        return sendarterm;
    }

    public void setSendarterm(Integer sendarterm) {
        this.sendarterm = sendarterm;
    }

    public BigDecimal getHtmoney() {
        return htmoney;
    }

    public void setHtmoney(BigDecimal htmoney) {
        this.htmoney = htmoney;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSendStockRoomId() {
        return sendStockRoomId;
    }

    public void setSendStockRoomId(Integer sendStockRoomId) {
        this.sendStockRoomId = sendStockRoomId;
    }

    public Integer getSendCustomerAddressId() {
        return sendCustomerAddressId;
    }

    public void setSendCustomerAddressId(Integer sendCustomerAddressId) {
        this.sendCustomerAddressId = sendCustomerAddressId;
    }

    public Integer getSendTransportWay() {
        return sendTransportWay;
    }

    public void setSendTransportWay(Integer sendTransportWay) {
        this.sendTransportWay = sendTransportWay;
    }

    public String getSendTakeName() {
        return sendTakeName;
    }

    public void setSendTakeName(String sendTakeName) {
        this.sendTakeName = sendTakeName;
    }

    public String getSendTakeNumber() {
        return sendTakeNumber;
    }

    public void setSendTakeNumber(String sendTakeNumber) {
        this.sendTakeNumber = sendTakeNumber;
    }

    public String getSendUserId() {
        return sendUserId;
    }

    public void setSendUserId(String sendUserId) {
        this.sendUserId = sendUserId;
    }

    public String getSendUserName() {
        return sendUserName;
    }

    public void setSendUserName(String sendUserName) {
        this.sendUserName = sendUserName;
    }

    public Integer getSendUserAreaId() {
        return sendUserAreaId;
    }

    public void setSendUserAreaId(Integer sendUserAreaId) {
        this.sendUserAreaId = sendUserAreaId;
    }

    public String getSendtext() {
        return sendtext;
    }

    public void setSendtext(String sendtext) {
        this.sendtext = sendtext;
    }

    public Integer getSendExceDays() {
        return sendExceDays;
    }

    public void setSendExceDays(Integer sendExceDays) {
        this.sendExceDays = sendExceDays;
    }

    public BigDecimal getMreturn() {
        return mreturn;
    }

    public void setMreturn(BigDecimal mreturn) {
        this.mreturn = mreturn;
    }

    public BigDecimal getSendExceedReturn() {
        return sendExceedReturn;
    }

    public void setSendExceedReturn(BigDecimal sendExceedReturn) {
        this.sendExceedReturn = sendExceedReturn;
    }

    public BigDecimal getSendExceedCount() {
        return sendExceedCount;
    }

    public void setSendExceedCount(BigDecimal sendExceedCount) {
        this.sendExceedCount = sendExceedCount;
    }

    public Integer getSendExtendExceedDays() {
        return sendExtendExceedDays;
    }

    public void setSendExtendExceedDays(Integer sendExtendExceedDays) {
        this.sendExtendExceedDays = sendExtendExceedDays;
    }

    public BigDecimal getSendExtendReturn() {
        return sendExtendReturn;
    }

    public void setSendExtendReturn(BigDecimal sendExtendReturn) {
        this.sendExtendReturn = sendExtendReturn;
    }

    public BigDecimal getSendExtendExceedReturn() {
        return sendExtendExceedReturn;
    }

    public void setSendExtendExceedReturn(BigDecimal sendExtendExceedReturn) {
        this.sendExtendExceedReturn = sendExtendExceedReturn;
    }

    public String getSendProMajId() {
        return sendProMajId;
    }

    public void setSendProMajId(String sendProMajId) {
        this.sendProMajId = sendProMajId;
    }

    public String getSendProMajName() {
        return sendProMajName;
    }

    public void setSendProMajName(String sendProMajName) {
        this.sendProMajName = sendProMajName;
    }

    public String getSendProMajDate() {
        return sendProMajDate;
    }

    public void setSendProMajDate(String sendProMajDate) {
        this.sendProMajDate = sendProMajDate;
    }

    public String getSendProMajIdea() {
        return sendProMajIdea;
    }

    public void setSendProMajIdea(String sendProMajIdea) {
        this.sendProMajIdea = sendProMajIdea;
    }

    public String getSendProMajText() {
        return sendProMajText;
    }

    public void setSendProMajText(String sendProMajText) {
        this.sendProMajText = sendProMajText;
    }

    public String getSendStkAdmId() {
        return sendStkAdmId;
    }

    public void setSendStkAdmId(String sendStkAdmId) {
        this.sendStkAdmId = sendStkAdmId;
    }

    public String getSendStkAdmName() {
        return sendStkAdmName;
    }

    public void setSendStkAdmName(String sendStkAdmName) {
        this.sendStkAdmName = sendStkAdmName;
    }

    public String getSendStkAdmDate() {
        return sendStkAdmDate;
    }

    public void setSendStkAdmDate(String sendStkAdmDate) {
        this.sendStkAdmDate = sendStkAdmDate;
    }

    public String getSendStkAdmIdea() {
        return sendStkAdmIdea;
    }

    public void setSendStkAdmIdea(String sendStkAdmIdea) {
        this.sendStkAdmIdea = sendStkAdmIdea;
    }

    public String getSendStkAdmText() {
        return sendStkAdmText;
    }

    public void setSendStkAdmText(String sendStkAdmText) {
        this.sendStkAdmText = sendStkAdmText;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public Integer getSendCreditTypeId() {
        return sendCreditTypeId;
    }

    public void setSendCreditTypeId(Integer sendCreditTypeId) {
        this.sendCreditTypeId = sendCreditTypeId;
    }

    public String getSendProjectName() {
        return sendProjectName;
    }

    public void setSendProjectName(String sendProjectName) {
        this.sendProjectName = sendProjectName;
    }

    public BigDecimal getClimit() {
        return climit;
    }

    public void setClimit(BigDecimal climit) {
        this.climit = climit;
    }

    public BigDecimal getFree() {
        return free;
    }

    public void setFree(BigDecimal free) {
        this.free = free;
    }

    public BigDecimal getSendPrepayMoney() {
        return sendPrepayMoney;
    }

    public void setSendPrepayMoney(BigDecimal sendPrepayMoney) {
        this.sendPrepayMoney = sendPrepayMoney;
    }

    public BigDecimal getSendTransitMoney() {
        return sendTransitMoney;
    }

    public void setSendTransitMoney(BigDecimal sendTransitMoney) {
        this.sendTransitMoney = sendTransitMoney;
    }

    public Integer getCreditid() {
        return creditid;
    }

    public void setCreditid(Integer creditid) {
        this.creditid = creditid;
    }

    public String getCreditname() {
        return creditname;
    }

    public void setCreditname(String creditname) {
        this.creditname = creditname;
    }

    public String getSellid() {
        return sellid;
    }

    public void setSellid(String sellid) {
        this.sellid = sellid;
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

    public String getSelldate() {
        return selldate;
    }

    public void setSelldate(String selldate) {
        this.selldate = selldate;
    }

    public Integer getSellstatus() {
        return sellstatus;
    }

    public void setSellstatus(Integer sellstatus) {
        this.sellstatus = sellstatus;
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

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
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

    public BigDecimal getSellclimit() {
        return sellclimit;
    }

    public void setSellclimit(BigDecimal sellclimit) {
        this.sellclimit = sellclimit;
    }

    public BigDecimal getSellfree() {
        return sellfree;
    }

    public void setSellfree(BigDecimal sellfree) {
        this.sellfree = sellfree;
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

    public String getSelldatetime() {
        return selldatetime;
    }

    public void setSelldatetime(String selldatetime) {
        this.selldatetime = selldatetime;
    }

    public Integer getCustid() {
        return custid;
    }

    public void setCustid(Integer custid) {
        this.custid = custid;
    }

    public Integer getCustCustomerId() {
        return custCustomerId;
    }

    public void setCustCustomerId(Integer custCustomerId) {
        this.custCustomerId = custCustomerId;
    }

    public String getCustname() {
        return custname;
    }

    public void setCustname(String custname) {
        this.custname = custname;
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

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getAreaid() {
        return areaid;
    }

    public void setAreaid(Integer areaid) {
        this.areaid = areaid;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }

}
