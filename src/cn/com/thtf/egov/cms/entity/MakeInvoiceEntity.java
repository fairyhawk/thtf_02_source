package cn.com.thtf.egov.cms.entity;

import cn.com.thtf.egov.cms.application.BaseEntity;

public class MakeInvoiceEntity extends BaseEntity {

    private static final long serialVersionUID = 6289594417484968733L;

    private String id;

    private Integer customerId;

    private Integer invoiceType;

    private Integer status;

    private String userName;

    private String text;

    private String date;

    private String number;

    private String money;

    private String sellMajName;

    private String sellMajDate;

    private String sellMajIdea;

    private String sellMajText;

    private String opeMajName;

    private String opeMajDate;

    private String opeMajIdea;

    private String opeMajText;

    private Integer notifyFlag;

    private String notifyDate;

    private String dateTime;

    public String getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(Integer invoiceType) {
        this.invoiceType = invoiceType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
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

    public Integer getNotifyFlag() {
        return notifyFlag;
    }

    public void setNotifyFlag(Integer notifyFlag) {
        this.notifyFlag = notifyFlag;
    }

    public String getNotifyDate() {
        return notifyDate;
    }

    public void setNotifyDate(String notifyDate) {
        this.notifyDate = notifyDate;
    }
}
