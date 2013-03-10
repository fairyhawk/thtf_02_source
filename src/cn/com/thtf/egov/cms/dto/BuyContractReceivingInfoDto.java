package cn.com.thtf.egov.cms.dto;

import java.io.Serializable;

public class BuyContractReceivingInfoDto implements Serializable {

    /**
     * @author HanHaiyun
     */
    private static final long serialVersionUID = 5414473744714564073L;
    // 收货信息id
    private Integer id;
    // 名称
    private String stockroomName;
    // 货物接受单位名称
    private String stockroomAddressName;
    // 收货地址
    private String address;
    // 邮编
    private String postcode;
    // 联系人
    private String linkman;
    // 电话
    private String tel;
    // 手机
    private String mobile;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStockroomName() {
        return stockroomName;
    }

    public void setStockroomName(String stockroomName) {
        this.stockroomName = stockroomName;
    }

    public String getStockroomAddressName() {
        return stockroomAddressName;
    }

    public void setStockroomAddressName(String stockroomAddressName) {
        this.stockroomAddressName = stockroomAddressName;
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

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
