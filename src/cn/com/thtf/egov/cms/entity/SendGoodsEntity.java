package cn.com.thtf.egov.cms.entity;

import java.math.BigDecimal;

import cn.com.thtf.egov.cms.application.BaseEntity;

public class SendGoodsEntity extends BaseEntity {

    private static final long serialVersionUID = -2711558946660481161L;

    private String id;

    private String sellContractId;

    private Integer productTypeId;

    private Integer customerId;

    private String customerName;

    private Integer sendGoodsType;

    private String date;

    private String requestDate;

    private String sendDate;

    private Integer creditTypeId;

    private String projectName;

    private Integer arterm;

    private BigDecimal climit;

    private BigDecimal free;

    private BigDecimal prepayMoney;

    private BigDecimal transitMoney;

    private BigDecimal money;

    private Integer status;

    private Integer stockroomId;

    private Integer customerAddressId;

    private Integer transportWay;

    private String takeName;

    private String takeNumber;

    private String userId;

    private String userName;

    private Integer userAreaId;

    private String text;

    private Integer exceedDays;

    private BigDecimal mreturn;

    private BigDecimal exceedReturn;

    private Integer exceedCount;

    private Integer extendExceedDays;

    private BigDecimal extendReturn;

    private BigDecimal extendExceedReturn;

    private String proMajId;

    private String proMajName;

    private String proMajDate;

    private String proMajIdea;

    private String proMajText;

    private String stkAdmId;

    private String stkAdmName;

    private String stkAdmDate;

    private String stkAdmIdea;

    private String stkAdmText;

    private String datetime;

    private String closeFlag;

    private String areaMajDate;

    private String areaMajId;

    private String areaMajIdea;

    private String areaMajName;

    private String areaMajText;

    private String caName;

    private String caAddress;

    private String caPostcode;

    private String caLinkman;

    private String caTel;

    private String caMobile;

    public String getCaName() {
        return caName;
    }

    public void setCaName(String caName) {
        this.caName = caName;
    }

    public String getCaAddress() {
        return caAddress;
    }

    public void setCaAddress(String caAddress) {
        this.caAddress = caAddress;
    }

    public String getCaPostcode() {
        return caPostcode;
    }

    public void setCaPostcode(String caPostcode) {
        this.caPostcode = caPostcode;
    }

    public String getCaLinkman() {
        return caLinkman;
    }

    public void setCaLinkman(String caLinkman) {
        this.caLinkman = caLinkman;
    }

    public String getCaTel() {
        return caTel;
    }

    public void setCaTel(String caTel) {
        this.caTel = caTel;
    }

    public String getCaMobile() {
        return caMobile;
    }

    public void setCaMobile(String caMobile) {
        this.caMobile = caMobile;
    }

    public String getAreaMajDate() {
        return areaMajDate;
    }

    public void setAreaMajDate(String areaMajDate) {
        this.areaMajDate = areaMajDate;
    }

    public String getAreaMajId() {
        return areaMajId;
    }

    public void setAreaMajId(String areaMajId) {
        this.areaMajId = areaMajId;
    }

    public String getAreaMajIdea() {
        return areaMajIdea;
    }

    public void setAreaMajIdea(String areaMajIdea) {
        this.areaMajIdea = areaMajIdea;
    }

    public String getAreaMajName() {
        return areaMajName;
    }

    public void setAreaMajName(String areaMajName) {
        this.areaMajName = areaMajName;
    }

    public String getAreaMajText() {
        return areaMajText;
    }

    public void setAreaMajText(String areaMajText) {
        this.areaMajText = areaMajText;
    }

    /**
     * @return the closeFlag
     */
    public String getCloseFlag() {
        return closeFlag;
    }

    /**
     * @param closeFlag
     *            the closeFlag to set
     */
    public void setCloseFlag(String closeFlag) {
        this.closeFlag = closeFlag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSellContractId() {
        return sellContractId;
    }

    public void setSellContractId(String sellContractId) {
        this.sellContractId = sellContractId;
    }

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
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

    public Integer getSendGoodsType() {
        return sendGoodsType;
    }

    public void setSendGoodsType(Integer sendGoodsType) {
        this.sendGoodsType = sendGoodsType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
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

    public BigDecimal getFree() {
        return free;
    }

    public void setFree(BigDecimal free) {
        this.free = free;
    }

    public BigDecimal getPrepayMoney() {
        return prepayMoney;
    }

    public void setPrepayMoney(BigDecimal prepayMoney) {
        this.prepayMoney = prepayMoney;
    }

    public BigDecimal getTransitMoney() {
        return transitMoney;
    }

    public void setTransitMoney(BigDecimal transitMoney) {
        this.transitMoney = transitMoney;
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

    public Integer getStockroomId() {
        return stockroomId;
    }

    public void setStockroomId(Integer stockroomId) {
        this.stockroomId = stockroomId;
    }

    public Integer getCustomerAddressId() {
        return customerAddressId;
    }

    public void setCustomerAddressId(Integer customerAddressId) {
        this.customerAddressId = customerAddressId;
    }

    public Integer getTransportWay() {
        return transportWay;
    }

    public void setTransportWay(Integer transportWay) {
        this.transportWay = transportWay;
    }

    public String getTakeName() {
        return takeName;
    }

    public void setTakeName(String takeName) {
        this.takeName = takeName;
    }

    public String getTakeNumber() {
        return takeNumber;
    }

    public void setTakeNumber(String takeNumber) {
        this.takeNumber = takeNumber;
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

    public Integer getExceedDays() {
        return exceedDays;
    }

    public void setExceedDays(Integer exceedDays) {
        this.exceedDays = exceedDays;
    }

    public BigDecimal getMreturn() {
        return mreturn;
    }

    public void setMreturn(BigDecimal mreturn) {
        this.mreturn = mreturn;
    }

    public BigDecimal getExceedReturn() {
        return exceedReturn;
    }

    public void setExceedReturn(BigDecimal exceedReturn) {
        this.exceedReturn = exceedReturn;
    }

    public Integer getExceedCount() {
        return exceedCount;
    }

    public void setExceedCount(Integer exceedCount) {
        this.exceedCount = exceedCount;
    }

    public Integer getExtendExceedDays() {
        return extendExceedDays;
    }

    public void setExtendExceedDays(Integer extendExceedDays) {
        this.extendExceedDays = extendExceedDays;
    }

    public BigDecimal getExtendReturn() {
        return extendReturn;
    }

    public void setExtendReturn(BigDecimal extendReturn) {
        this.extendReturn = extendReturn;
    }

    public BigDecimal getExtendExceedReturn() {
        return extendExceedReturn;
    }

    public void setExtendExceedReturn(BigDecimal extendExceedReturn) {
        this.extendExceedReturn = extendExceedReturn;
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

    public String getStkAdmId() {
        return stkAdmId;
    }

    public void setStkAdmId(String stkAdmId) {
        this.stkAdmId = stkAdmId;
    }

    public String getStkAdmName() {
        return stkAdmName;
    }

    public void setStkAdmName(String stkAdmName) {
        this.stkAdmName = stkAdmName;
    }

    public String getStkAdmDate() {
        return stkAdmDate;
    }

    public void setStkAdmDate(String stkAdmDate) {
        this.stkAdmDate = stkAdmDate;
    }

    public String getStkAdmIdea() {
        return stkAdmIdea;
    }

    public void setStkAdmIdea(String stkAdmIdea) {
        this.stkAdmIdea = stkAdmIdea;
    }

    public String getStkAdmText() {
        return stkAdmText;
    }

    public void setStkAdmText(String stkAdmText) {
        this.stkAdmText = stkAdmText;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
