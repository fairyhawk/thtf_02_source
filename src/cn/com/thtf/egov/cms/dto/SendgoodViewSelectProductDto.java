package cn.com.thtf.egov.cms.dto;

import java.math.BigDecimal;

public class SendgoodViewSelectProductDto {

    /** 产品ID */
    private String productId;

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
    /** 已发货数 */
    private String yfhnum;
    /** 其它库房备货数 */
    private String qtkfnum;
    /** 本库房备货数 */
    private String bkfnum;

    /** 发货可用数 */
    private String fhkynum;
    /** 发货单退货数  */
    private String thsnum;
    /** 开票金额 **/
    private String kpjemoney;
    /** 信用金额 **/
    private String xyjemoney;
    /** 回款指定金额 **/
    private String hkzdjemoney;
    /** 在途指定金额 **/
    private String ztzdjemoney;
    /** 发货数 **/
    private String ffcount;
    /** 发货金额 **/
    private BigDecimal ffmoney;
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
    
    public BigDecimal getFfmoney() {
        return ffmoney;
    }

    public void setFfmoney(BigDecimal ffmoney) {
        this.ffmoney = ffmoney;
    }

    public String getFfcount() {
        return ffcount;
    }

    public void setFfcount(String ffcount) {
        this.ffcount = ffcount;
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

    public String getKpjemoney() {
        return kpjemoney;
    }

    public void setKpjemoney(String kpjemoney) {
        this.kpjemoney = kpjemoney;
    }

    public String getXyjemoney() {
        return xyjemoney;
    }

    public void setXyjemoney(String xyjemoney) {
        this.xyjemoney = xyjemoney;
    }

    public String getHkzdjemoney() {
        return hkzdjemoney;
    }

    public void setHkzdjemoney(String hkzdjemoney) {
        this.hkzdjemoney = hkzdjemoney;
    }

    public String getZtzdjemoney() {
        return ztzdjemoney;
    }

    public void setZtzdjemoney(String ztzdjemoney) {
        this.ztzdjemoney = ztzdjemoney;
    }

}
