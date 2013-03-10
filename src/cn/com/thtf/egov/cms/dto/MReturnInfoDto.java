package cn.com.thtf.egov.cms.dto;

import java.math.BigDecimal;

public class MReturnInfoDto {

    /** 回款流水号 **/
    private String id;

    /** 回款编号 **/
    private String mrno;

    /** 回款类型 **/
    private Integer mrreturntype;

    /** 产品分类编号 **/
    private Integer mrproducttypeid;

    /** 客户编号 **/
    private Integer mrcustomerid;

    /** 客户名称 **/
    private String mrcustomername;

    /** 录入日期 **/
    private String mrdate;

    /** 回款日期 **/
    private String mrreturndate;

    /** 回款方式 **/
    private Integer mrreturnway;

    /** 凭证号 **/
    private String mrnumber;

    /** 回款金额 **/
    private BigDecimal mrmoney;

    /** 登录名 **/
    private String mruserid;

    /** 人员名称 **/
    private String mrusername;

    /** 人员区域编号 **/
    private Integer mruserareaid;

    /** 特殊说明 **/
    private String mrtest;

    /** 时间戳 **/
    private String mrtimestamp;

    /** 产品分类名称 **/
    private String productname;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMrno() {
        return mrno;
    }

    public void setMrno(String mrno) {
        this.mrno = mrno;
    }

    public Integer getMrreturntype() {
        return mrreturntype;
    }

    public void setMrreturntype(Integer mrreturntype) {
        this.mrreturntype = mrreturntype;
    }

    public Integer getMrproducttypeid() {
        return mrproducttypeid;
    }

    public void setMrproducttypeid(Integer mrproducttypeid) {
        this.mrproducttypeid = mrproducttypeid;
    }

    public Integer getMrcustomerid() {
        return mrcustomerid;
    }

    public void setMrcustomerid(Integer mrcustomerid) {
        this.mrcustomerid = mrcustomerid;
    }

    public String getMrcustomername() {
        return mrcustomername;
    }

    public void setMrcustomername(String mrcustomername) {
        this.mrcustomername = mrcustomername;
    }

    public String getMrdate() {
        return mrdate;
    }

    public void setMrdate(String mrdate) {
        this.mrdate = mrdate;
    }

    public String getMrreturndate() {
        return mrreturndate;
    }

    public void setMrreturndate(String mrreturndate) {
        this.mrreturndate = mrreturndate;
    }

    public Integer getMrreturnway() {
        return mrreturnway;
    }

    public void setMrreturnway(Integer mrreturnway) {
        this.mrreturnway = mrreturnway;
    }

    public String getMrnumber() {
        return mrnumber;
    }

    public void setMrnumber(String mrnumber) {
        this.mrnumber = mrnumber;
    }

    public BigDecimal getMrmoney() {
        return mrmoney;
    }

    public void setMrmoney(BigDecimal mrmoney) {
        this.mrmoney = mrmoney;
    }

    public String getMruserid() {
        return mruserid;
    }

    public void setMruserid(String mruserid) {
        this.mruserid = mruserid;
    }

    public String getMrusername() {
        return mrusername;
    }

    public void setMrusername(String mrusername) {
        this.mrusername = mrusername;
    }

    public Integer getMruserareaid() {
        return mruserareaid;
    }

    public void setMruserareaid(Integer mruserareaid) {
        this.mruserareaid = mruserareaid;
    }

    public String getMrtest() {
        return mrtest;
    }

    public void setMrtest(String mrtest) {
        this.mrtest = mrtest;
    }

    public String getMrtimestamp() {
        return mrtimestamp;
    }

    public void setMrtimestamp(String mrtimestamp) {
        this.mrtimestamp = mrtimestamp;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

}
