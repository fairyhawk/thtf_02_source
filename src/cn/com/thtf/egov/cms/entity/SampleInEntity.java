package cn.com.thtf.egov.cms.entity;

import java.math.BigDecimal;

import cn.com.thtf.egov.cms.application.BaseEntity;

public class SampleInEntity extends BaseEntity {

    private static final long serialVersionUID = -4721166952540724938L;

    private String id;

    private String date;

    private String sampleOutId;

    private Integer inStockroomId;

    private Integer stockroomAddressId;

    private String userId;

    private String userName;

    private String text;

    private BigDecimal money;

    private Integer status;

    private String buyManId;

    private String buyManName;

    private String buyManDate;

    private String buyManIdea;

    private String buyManText;

    private String stkAdmId;

    private String stkAdmName;

    private String stkAdmDate;

    private String stkAdmIdea;

    private String stkAdmText;

    private String datetime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSampleOutId() {
        return sampleOutId;
    }

    public void setSampleOutId(String sampleOutId) {
        this.sampleOutId = sampleOutId;
    }

    public Integer getInStockroomId() {
        return inStockroomId;
    }

    public void setInStockroomId(Integer inStockroomId) {
        this.inStockroomId = inStockroomId;
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