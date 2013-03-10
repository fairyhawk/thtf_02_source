package cn.com.thtf.egov.cms.dto;

public class SaleReturnsAddressSelectDto {

    /**名称**/
    private String stockRoomName;
    
    /**货物接收单位名称**/
    private String stockRoomAddressName;
    
    /**发货地址**/
    private String address;
    
    /**邮编**/
    private String postcode;
    
    /**联系人**/
    private String linkman;
    
    /**电话**/
    private String tel;
    
    /**手机**/
    private String mobile;

    public String getStockRoomName() {
        return stockRoomName;
    }

    public void setStockRoomName(String stockRoomName) {
        this.stockRoomName = stockRoomName;
    }

    public String getStockRoomAddressName() {
        return stockRoomAddressName;
    }

    public void setStockRoomAddressName(String stockRoomAddressName) {
        this.stockRoomAddressName = stockRoomAddressName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
    
}
