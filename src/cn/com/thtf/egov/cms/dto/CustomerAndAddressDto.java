/**
 * ClassName  CustomerAndAddressDto
 *
 * History
 * Create User: ChenHuajiang
 * Create Date: 2010-6-4
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

import java.io.Serializable;

/**
 * 客户地址选择Dto
 * 
 * @author ChenHuajiang
 */

public class CustomerAndAddressDto implements Serializable {

    private static final long serialVersionUID = -2063986317079599370L;

    private Integer customerId;// 客户Id

    private String customerName;// 客户名称

    private String addressId;// 收获地址ID

    private String addressName;// 收获人名称

    private String address;// 收获地址

    private String postcode;// 邮编

    private String linkman;// 联系人姓名

    private String tel;// 联系人电话

    private String fax;// 联系人传真

    private String mobile;// 联系人手机

    public Integer getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddressId() {
        return this.addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getAddressName() {
        return this.addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return this.postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getLinkman() {
        return this.linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getTel() {
        return this.tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return this.fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
