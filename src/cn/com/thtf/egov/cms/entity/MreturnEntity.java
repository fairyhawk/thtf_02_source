package cn.com.thtf.egov.cms.entity;

import java.math.BigDecimal;

import cn.com.thtf.egov.cms.application.BaseEntity;

public class MreturnEntity extends BaseEntity {

    private static final long serialVersionUID = 4148970028065771210L;

    private String id;

    private String no;

    private Integer returnType;

    private Integer productTypeId;

    private Integer customerId;

    private String customerName;

    private String date;

    private String returnDate;

    private Integer returnWay;

    private String number;

    private BigDecimal money;

    private String userId;

    private String userName;

    private Integer userAreaId;

    private String text;
    
    private String timestamp;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Integer getReturnType() {
        return returnType;
    }

    public void setReturnType(Integer returnType) {
        this.returnType = returnType;
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

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public Integer getReturnWay() {
        return returnWay;
    }

    public void setReturnWay(Integer returnWay) {
        this.returnWay = returnWay;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
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
}
