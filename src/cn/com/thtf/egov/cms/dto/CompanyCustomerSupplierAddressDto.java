/**
 * ClassName  CompanyCustomerSupplierAddressDto
 *
 * History
 * Create User: Administrator
 * Create Date: 2010-7-9
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

/**
 * 公司、客户、供货商发货地址
 * @author zhangzx
 */

public class CompanyCustomerSupplierAddressDto {
    
    private Integer addressId;          //发货地址编号
    
    private String goodsReceiveUnitName; // 货物接收单位名称

    private String linkman;              // 联系人

    private String address;             // 发货地址

    private String postcode;             // 邮编

    private String mobile;              // 手机

    private String tel;                // 电话

    /**
     * @return the addressId
     */
    public Integer getAddressId() {
        return addressId;
    }

    /**
     * @param addressId the addressId to set
     */
    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    /**
     * @return the goodsReceiveUnitName
     */
    public String getGoodsReceiveUnitName() {
        return goodsReceiveUnitName;
    }

    /**
     * @param goodsReceiveUnitName the goodsReceiveUnitName to set
     */
    public void setGoodsReceiveUnitName(String goodsReceiveUnitName) {
        this.goodsReceiveUnitName = goodsReceiveUnitName;
    }

    /**
     * @return the linkman
     */
    public String getLinkman() {
        return linkman;
    }

    /**
     * @param linkman the linkman to set
     */
    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the postcode
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * @param postcode the postcode to set
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    /**
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return the tel
     */
    public String getTel() {
        return tel;
    }

    /**
     * @param tel the tel to set
     */
    public void setTel(String tel) {
        this.tel = tel;
    }


}
