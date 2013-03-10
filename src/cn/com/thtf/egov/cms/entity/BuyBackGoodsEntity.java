package cn.com.thtf.egov.cms.entity;

import java.math.BigDecimal;

import cn.com.thtf.egov.cms.application.BaseEntity;

public class BuyBackGoodsEntity extends BaseEntity {

    private static final long serialVersionUID = 592746951471095042L;

    private String id;

    private Integer productTypeId;

    private Integer supplierId;

    private String supplierName;

    private String inStockId;

    private String date;

    private String requestDate;

    private String sendDate;

    private BigDecimal money;

    private Integer status;

    private Integer stockroomId;

    private Integer supplierAddressId;

    private Integer transportWay;

    private String takeName;

    private String takeNumber;

    private String userId;

    private String userName;

    private String text;

    private String proMajId;

    private String proMajName;

    private String proMajDate;

    private String proMajIdea;

    private String proMajText;

    private String buyManId;

    private String buyManName;

    private String buyManDate;

    private String buyManIdea;

    private String buyManText;

    private String opeMajId;

    private String opeMajName;

    private String opeMajDate;

    private String opeMajIdea;

    private String opeMajText;

    private String stkAdmId;

    private String stkAdmName;

    private String stkAdmDate;

    private String stkAdmIdea;

    private String stkAdmText;

    private String datetime;

    /**
     * @return the stkAdmId
     */
    public String getStkAdmId() {
        return stkAdmId;
    }

    /**
     * @param stkAdmId
     *            the stkAdmId to set
     */
    public void setStkAdmId(String stkAdmId) {
        this.stkAdmId = stkAdmId;
    }

    /**
     * @return the stkAdmName
     */
    public String getStkAdmName() {
        return stkAdmName;
    }

    /**
     * @param stkAdmName
     *            the stkAdmName to set
     */
    public void setStkAdmName(String stkAdmName) {
        this.stkAdmName = stkAdmName;
    }

    /**
     * @return the stkAdmDate
     */
    public String getStkAdmDate() {
        return stkAdmDate;
    }

    /**
     * @param stkAdmDate
     *            the stkAdmDate to set
     */
    public void setStkAdmDate(String stkAdmDate) {
        this.stkAdmDate = stkAdmDate;
    }

    /**
     * @return the stkAdmIdea
     */
    public String getStkAdmIdea() {
        return stkAdmIdea;
    }

    /**
     * @param stkAdmIdea
     *            the stkAdmIdea to set
     */
    public void setStkAdmIdea(String stkAdmIdea) {
        this.stkAdmIdea = stkAdmIdea;
    }

    /**
     * @return the stkAdmText
     */
    public String getStkAdmText() {
        return stkAdmText;
    }

    /**
     * @param stkAdmText
     *            the stkAdmText to set
     */
    public void setStkAdmText(String stkAdmText) {
        this.stkAdmText = stkAdmText;
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

    public String getInStockId() {
        return inStockId;
    }

    public void setInStockId(String inStockId) {
        this.inStockId = inStockId;
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

    public Integer getSupplierAddressId() {
        return supplierAddressId;
    }

    public void setSupplierAddressId(Integer supplierAddressId) {
        this.supplierAddressId = supplierAddressId;
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

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}