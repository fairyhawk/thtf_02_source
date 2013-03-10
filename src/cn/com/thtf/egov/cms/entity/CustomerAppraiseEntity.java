package cn.com.thtf.egov.cms.entity;

import java.math.BigDecimal;

import cn.com.thtf.egov.cms.application.BaseEntity;

public class CustomerAppraiseEntity extends BaseEntity {

    private static final long serialVersionUID = -8301920444146288631L;

    private Integer id;

    private Integer productTypeId;

    private Integer customerId;

    private BigDecimal contractMoney;

    private BigDecimal sendMoney;

    private BigDecimal mreturn;

    private BigDecimal exceedReturn;

    private BigDecimal exceedMoney;

    private Integer exceedMax;

    private Integer exceedCount;

    private Integer sendCount;

    private Integer exceedAverage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public BigDecimal getContractMoney() {
        return contractMoney;
    }

    public void setContractMoney(BigDecimal contractMoney) {
        this.contractMoney = contractMoney;
    }

    public BigDecimal getSendMoney() {
        return sendMoney;
    }

    public void setSendMoney(BigDecimal sendMoney) {
        this.sendMoney = sendMoney;
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

    public BigDecimal getExceedMoney() {
        return this.exceedMoney;
    }

    public void setExceedMoney(BigDecimal exceedMoney) {
        this.exceedMoney = exceedMoney;
    }

    public Integer getExceedMax() {
        return exceedMax;
    }

    public void setExceedMax(Integer exceedMax) {
        this.exceedMax = exceedMax;
    }

    public Integer getExceedCount() {
        return exceedCount;
    }

    public void setExceedCount(Integer exceedCount) {
        this.exceedCount = exceedCount;
    }

    public Integer getSendCount() {
        return sendCount;
    }

    public void setSendCount(Integer sendCount) {
        this.sendCount = sendCount;
    }

    public Integer getExceedAverage() {
        return exceedAverage;
    }

    public void setExceedAverage(Integer exceedAverage) {
        this.exceedAverage = exceedAverage;
    }
}
