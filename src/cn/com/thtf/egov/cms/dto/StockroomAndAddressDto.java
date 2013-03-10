package cn.com.thtf.egov.cms.dto;
/**
 * 销售退货管理
 * 库房收货地址
 * 
 * @author lxs
 */
public class StockroomAndAddressDto {

    /** 库房收货地址 **/
    private String stockRoomId;
    
    /** 库房名称 **/
    private String stockRoomName;
    
    /** 库房收货地址ID **/
    private String stockRoomAddressId;
    
    /** 货物接收单位名称 **/
    private String goodsName;
    
    /** 收货地址 **/
    private String goodsAddress;
    
    /** 邮编 **/
    private String postcode;
    
    /** 联系人 **/
    private String linkman;
    
    /** 电话 **/
    private String tel;
    
    /** 手机 **/
    private String mobile;

    public String getStockRoomId() {
        return stockRoomId;
    }

    public void setStockRoomId(String stockRoomId) {
        this.stockRoomId = stockRoomId;
    }

    public String getStockRoomName() {
        return stockRoomName;
    }

    public void setStockRoomName(String stockRoomName) {
        this.stockRoomName = stockRoomName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsAddress() {
        return goodsAddress;
    }

    public void setGoodsAddress(String goodsAddress) {
        this.goodsAddress = goodsAddress;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStockRoomAddressId() {
        return stockRoomAddressId;
    }

    public void setStockRoomAddressId(String stockRoomAddressId) {
        this.stockRoomAddressId = stockRoomAddressId;
    }
    
    
}
