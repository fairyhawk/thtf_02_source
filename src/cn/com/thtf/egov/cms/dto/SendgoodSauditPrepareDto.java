package cn.com.thtf.egov.cms.dto;

import java.math.BigDecimal;

public class SendgoodSauditPrepareDto {

    /** 产品编码 */
    private String productcode;
    /** 产品名称 */
    private String productname;
    /** 规格型号 */
    private String producttype;
    /** 单位 */
    private String productunit; 
    /** 销售数 */
    private String count;   
    /** 销售单价 */
    private String price;
    /** 销售金额 */
    private String money;
    /** 发货数 */
    private String yfhnum;
    /** 其它库房备货数 */
    private String qtkfnum;
    /** 本库房备货数 */
    private String bkfnum;   
    /** 备货可用数 */
    private String fhkynum;
    /** 发货单退货数**/
    private String thsnum;
    /** 备货金额 */
    private BigDecimal bfmoney;
    /**产品ID*/
    private String productId;
    
    /** 合同退货数**/
    private String htthsnum;
    
    public String getHtthsnum() {
        return htthsnum;
    }

    public void setHtthsnum(String htthsnum) {
        this.htthsnum = htthsnum;
    }
    /**
     * @return the productId
     */
    public String getProductId() {
        return productId;
    }
    /**
     * @param productId the productId to set
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }
    public BigDecimal getBfmoney() {
        return bfmoney;
    }
    public void setBfmoney(BigDecimal bfmoney) {
        this.bfmoney = bfmoney;
    }
    public String getProductcode() {
        return productcode;
    }
    public void setProductcode(String productcode) {
        this.productcode = productcode;
    }
    public String getProductname() {
        return productname;
    }
    public void setProductname(String productname) {
        this.productname = productname;
    }
    public String getProducttype() {
        return producttype;
    }
    public void setProducttype(String producttype) {
        this.producttype = producttype;
    }
    public String getProductunit() {
        return productunit;
    }
    public void setProductunit(String productunit) {
        this.productunit = productunit;
    }
    public String getCount() {
        return count;
    }
    public void setCount(String count) {
        this.count = count;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public String getMoney() {
        return money;
    }
    public void setMoney(String money) {
        this.money = money;
    }
    public String getYfhnum() {
        return yfhnum;
    }
    public void setYfhnum(String yfhnum) {
        this.yfhnum = yfhnum;
    }
    public String getQtkfnum() {
        return qtkfnum;
    }
    public void setQtkfnum(String qtkfnum) {
        this.qtkfnum = qtkfnum;
    }
    public String getBkfnum() {
        return bkfnum;
    }
    public void setBkfnum(String bkfnum) {
        this.bkfnum = bkfnum;
    }
    public String getFhkynum() {
        return fhkynum;
    }
    public void setFhkynum(String fhkynum) {
        this.fhkynum = fhkynum;
    }
    public String getThsnum() {
        return thsnum;
    }
    public void setThsnum(String thsnum) {
        this.thsnum = thsnum;
    }
}
