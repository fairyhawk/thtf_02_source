package cn.com.thtf.egov.cms.dto;

import java.math.BigDecimal;

public class MreturnArriveInfoDto {

    /** 客户信用明细ID **/
    private Integer customerdetailid;
    /** 回款明细ID **/
    private Integer returndetailid;
    /** 客户信用明细使用额度 **/
    private BigDecimal customerdetailmoney;
    /** 回款明细指定金额 **/
    private BigDecimal returndetailmoney;
    /** 客户信用时间戳 **/
    private String customertimestamp;
    /** 回款时间戳 **/
    private String returntimestamp;
    /** 回款流水号 **/
    private String returnid;
    /** 发货单编号 **/
    private String sendgoodsid;
    /** 产品编号 **/
    private Integer productid;

    public Integer getCustomerdetailid() {
        return customerdetailid;
    }

    public void setCustomerdetailid(Integer customerdetailid) {
        this.customerdetailid = customerdetailid;
    }

    public Integer getReturndetailid() {
        return returndetailid;
    }

    public void setReturndetailid(Integer returndetailid) {
        this.returndetailid = returndetailid;
    }

    public BigDecimal getCustomerdetailmoney() {
        return customerdetailmoney;
    }

    public void setCustomerdetailmoney(BigDecimal customerdetailmoney) {
        this.customerdetailmoney = customerdetailmoney;
    }

    public BigDecimal getReturndetailmoney() {
        return returndetailmoney;
    }

    public void setReturndetailmoney(BigDecimal returndetailmoney) {
        this.returndetailmoney = returndetailmoney;
    }

    public String getCustomertimestamp() {
        return customertimestamp;
    }

    public void setCustomertimestamp(String customertimestamp) {
        this.customertimestamp = customertimestamp;
    }

    public String getReturntimestamp() {
        return returntimestamp;
    }

    public void setReturntimestamp(String returntimestamp) {
        this.returntimestamp = returntimestamp;
    }

    public String getReturnid() {
        return returnid;
    }

    public void setReturnid(String returnid) {
        this.returnid = returnid;
    }

    public String getSendgoodsid() {
        return sendgoodsid;
    }

    public void setSendgoodsid(String sendgoodsid) {
        this.sendgoodsid = sendgoodsid;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

}
