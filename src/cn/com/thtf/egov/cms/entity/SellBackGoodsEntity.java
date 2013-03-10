package cn.com.thtf.egov.cms.entity;

import cn.com.thtf.egov.cms.application.BaseEntity;

public class SellBackGoodsEntity extends BaseEntity {

    private static final long serialVersionUID = 3462322416677378461L;

    private String id;

    private String sendGoodsId;

    private Integer productTypeId;

    private Integer customerId;

    private String customerName;

    private String date;

    private String money;

    private Integer status;

    private Integer stockroomId;

    private Integer stockroomAddressId;

    private String userId;

    private String userName;
    
    private Integer userAreaId;

    public Integer getUserAreaId() {
        return userAreaId;
    }

    public void setUserAreaId(Integer userAreaId) {
        this.userAreaId = userAreaId;
    }

    private String text;

    private String sellMajId;

    private String sellMajName;

    private String sellMajDate;

    private String sellMajIdea;

    private String sellMajText;

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

    private String dateTime;

    public String getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSendGoodsId() {
        return sendGoodsId;
    }

    public void setSendGoodsId(String sendGoodsId) {
        this.sendGoodsId = sendGoodsId;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
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

    public Integer getStockroomAddressId() {
        return stockroomAddressId;
    }

    public void setStockroomAddressId(Integer stockroomAddressId) {
        this.stockroomAddressId = stockroomAddressId;
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



    public String getSellMajId() {
        return sellMajId;
    }

    public void setSellMajId(String sellMajId) {
        this.sellMajId = sellMajId;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
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
}