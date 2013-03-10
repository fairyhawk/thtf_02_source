package cn.com.thtf.egov.cms.entity;


import java.sql.Timestamp;

import cn.com.thtf.egov.cms.application.BaseEntity;

public class StockEntity extends BaseEntity {

    private static final long serialVersionUID = -4574686479710661476L;

    private Integer id;

    private Integer productId;

    private Integer stockroomId;

    private Integer num;

    private Integer sendLock;

    private Integer prepared;
    
    private Timestamp timeStamp;

    /**
     * @return the timeStamp
     */
    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    /**
     * @param timeStamp the timeStamp to set
     */
    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getStockroomId() {
        return stockroomId;
    }

    public void setStockroomId(Integer stockroomId) {
        this.stockroomId = stockroomId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getSendLock() {
        return sendLock;
    }

    public void setSendLock(Integer sendLock) {
        this.sendLock = sendLock;
    }

    public Integer getPrepared() {
        return prepared;
    }

    public void setPrepared(Integer prepared) {
        this.prepared = prepared;
    }
}
