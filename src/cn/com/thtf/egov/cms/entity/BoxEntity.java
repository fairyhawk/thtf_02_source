package cn.com.thtf.egov.cms.entity;

import java.math.BigDecimal;

import cn.com.thtf.egov.cms.application.BaseEntity;

public class BoxEntity extends BaseEntity {

    private static final long serialVersionUID = -9060523608480425084L;

    private String id;

    private Integer stockroomId;

    private Integer productTypeId;

    private Integer status;

    private String date;

    private Integer logisticsId;

    private Integer requestWay;

    private Integer count;

    private BigDecimal money;

    private String userId;

    private String userName;

    private String no;

    private Integer transportWay;

    private String estimateDate;

    private String tel;

    private String info;

    private String arrivalDate;

    private String signoffDate;

    private String signoffName;

    private String confirmDate;

    private String logAdmName;

    private String datetime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getStockroomId() {
        return stockroomId;
    }

    public void setStockroomId(Integer stockroomId) {
        this.stockroomId = stockroomId;
    }

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getLogisticsId() {
        return logisticsId;
    }

    public void setLogisticsId(Integer logisticsId) {
        this.logisticsId = logisticsId;
    }

    public Integer getRequestWay() {
        return requestWay;
    }

    public void setRequestWay(Integer requestWay) {
        this.requestWay = requestWay;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Integer getTransportWay() {
        return transportWay;
    }

    public void setTransportWay(Integer transportWay) {
        this.transportWay = transportWay;
    }

    public String getEstimateDate() {
        return estimateDate;
    }

    public void setEstimateDate(String estimateDate) {
        this.estimateDate = estimateDate;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getSignoffDate() {
        return signoffDate;
    }

    public void setSignoffDate(String signoffDate) {
        this.signoffDate = signoffDate;
    }

    public String getSignoffName() {
        return signoffName;
    }

    public void setSignoffName(String signoffName) {
        this.signoffName = signoffName;
    }

    public String getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(String confirmDate) {
        this.confirmDate = confirmDate;
    }

    public String getLogAdmName() {
        return logAdmName;
    }

    public void setLogAdmName(String logAdmName) {
        this.logAdmName = logAdmName;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}