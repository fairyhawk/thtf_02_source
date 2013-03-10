/**
 * ClassName  ReceiveGoodsDetailDto
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-5-28
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

import java.util.List;

/**
 * BuyContractDto
 * 
 * @author Lubo
 */
public class ReceiveGoodsDetailDto {

    /** 入库单ID */
    private String inStockId;

    /** 合同收获地址ID */
    private Integer id;
    /** 合同ID */
    private String buyContractId;
    /** 单位类型 */
    private String companyType;
    /** 单位ID */
    private String companyId;
    /** 单位名称 */
    private String companyName;
    /** 地址ID */
    private String addressId;

    /** 产品ID */
    private String productId;
    /** 收获数 */
    private String count;

    /** 接收单位名称 */
    private String name;
    /** 接受单位地址 */
    private String address;
    /** 邮编 */
    private String postcode;
    /** 联系人 */
    private String linkman;
    /** 电话 */
    private String tel;

    /** 手机 */
    private String mobile;
    /** 库房ID */
    private String stockroomId;
    /** 库房名 */
    private String stockroomName;

    /** 发货地址ID */
    private List<String> idList;

    /**
     * @return the idList
     */
    public List<String> getIdList() {
        return idList;
    }

    /**
     * @param idList
     *            the idList to set
     */
    public void setIdList(List<String> idList) {
        this.idList = idList;
    }

    /**
     * @return the inStockId
     */
    public String getInStockId() {
        return inStockId;
    }

    /**
     * @param inStockId
     *            the inStockId to set
     */
    public void setInStockId(String inStockId) {
        this.inStockId = inStockId;
    }

    /**
     * @return the stockroomName
     */
    public String getStockroomName() {
        return stockroomName;
    }

    /**
     * @param stockroomName
     *            the stockroomName to set
     */
    public void setStockroomName(String stockroomName) {
        this.stockroomName = stockroomName;
    }

    /**
     * @return the stockroomId
     */
    public String getStockroomId() {
        return stockroomId;
    }

    /**
     * @param stockroomId
     *            the stockroomId to set
     */
    public void setStockroomId(String stockroomId) {
        this.stockroomId = stockroomId;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the buyContractId
     */
    public String getBuyContractId() {
        return buyContractId;
    }

    /**
     * @param buyContractId
     *            the buyContractId to set
     */
    public void setBuyContractId(String buyContractId) {
        this.buyContractId = buyContractId;
    }

    /**
     * @return the companyType
     */
    public String getCompanyType() {
        return companyType;
    }

    /**
     * @param companyType
     *            the companyType to set
     */
    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    /**
     * @return the companyId
     */
    public String getCompanyId() {
        return companyId;
    }

    /**
     * @param companyId
     *            the companyId to set
     */
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    /**
     * @return the companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName
     *            the companyName to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * @return the addressId
     */
    public String getAddressId() {
        return addressId;
    }

    /**
     * @param addressId
     *            the addressId to set
     */
    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    /**
     * @return the productId
     */
    public String getProductId() {
        return productId;
    }

    /**
     * @param productId
     *            the productId to set
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * @return the count
     */
    public String getCount() {
        return count;
    }

    /**
     * @param count
     *            the count to set
     */
    public void setCount(String count) {
        this.count = count;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     *            the address to set
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
     * @param postcode
     *            the postcode to set
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    /**
     * @return the linkman
     */
    public String getLinkman() {
        return linkman;
    }

    /**
     * @param linkman
     *            the linkman to set
     */
    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    /**
     * @return the tel
     */
    public String getTel() {
        return tel;
    }

    /**
     * @param tel
     *            the tel to set
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile
     *            the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}