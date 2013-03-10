/**
 * ClassName  MreturnAppointDto
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-5-14
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

import java.util.List;

import cn.com.thtf.egov.cms.entity.UserAreaProductEntity;

/**
 * 回款指定查询参数
 * 
 * @author Lubo
 */
public class MreturnAppointDto {
    
    /** 回款ID */
    private String mreturnId;
    /** 负责区域 */
    private String userArea;
    /** 负责产品分类 */
    private String userProductType;
    /** 产品分类ID */
    private String productTypeId; 
    /** 客户ID */
    private String customerId; 
    
    /** 产品合同号 */
    private String productContractCode;
    /** 公司合同号 */
    private String companyContractCode;
    
    /** 发货单号 */
    private String sendGoodsId;
    /** 产品名称 */
    private String productName;
    /** 产品编码 */
    private String productCode;
    /** 规格型号 */
    private String productType;
    /** 人员名称 */
    private String userName;
    
    /** 指定金额 */
    private String appointMoney;
    /** 冻结的回款指定 */
    private String isFreeze;
    
    /**要求到帐起始日期**/
    private String startSgrqartermdate;
    /**要求到帐终止日期**/
    private String endSgrqartermdate;
    

    public String getStartSgrqartermdate() {
        return startSgrqartermdate;
    }
    public void setStartSgrqartermdate(String startSgrqartermdate) {
        this.startSgrqartermdate = startSgrqartermdate;
    }
    public String getEndSgrqartermdate() {
        return endSgrqartermdate;
    }
    public void setEndSgrqartermdate(String endSgrqartermdate) {
        this.endSgrqartermdate = endSgrqartermdate;
    }
    /** 用户区域 产品分类 */
    private List<UserAreaProductEntity> userAreaProductList;
    
    /**
     * @return the userAreaProductList
     */
    public List<UserAreaProductEntity> getUserAreaProductList() {
        return userAreaProductList;
    }
    /**
     * @param userAreaProductList the userAreaProductList to set
     */
    public void setUserAreaProductList(List<UserAreaProductEntity> userAreaProductList) {
        this.userAreaProductList = userAreaProductList;
    }
    /**
     * @return the isFreeze
     */
    public String getIsFreeze() {
        return isFreeze;
    }
    /**
     * @param isFreeze the isFreeze to set
     */
    public void setIsFreeze(String isFreeze) {
        this.isFreeze = isFreeze;
    }
    /**
     * @return the appointMoney
     */
    public String getAppointMoney() {
        return appointMoney;
    }
    /**
     * @param appointMoney the appointMoney to set
     */
    public void setAppointMoney(String appointMoney) {
        this.appointMoney = appointMoney;
    }
    /**
     * @return the sendGoodsId
     */
    public String getSendGoodsId() {
        return sendGoodsId;
    }
    /**
     * @param sendGoodsId the sendGoodsId to set
     */
    public void setSendGoodsId(String sendGoodsId) {
        this.sendGoodsId = sendGoodsId;
    }
    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }
    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }
    /**
     * @return the productCode
     */
    public String getProductCode() {
        return productCode;
    }
    /**
     * @param productCode the productCode to set
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
    /**
     * @return the productType
     */
    public String getProductType() {
        return productType;
    }
    /**
     * @param productType the productType to set
     */
    public void setProductType(String productType) {
        this.productType = productType;
    }
    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }
    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**
     * @return the mreturnId
     */
    public String getMreturnId() {
        return mreturnId;
    }
    /**
     * @param mreturnId the mreturnId to set
     */
    public void setMreturnId(String mreturnId) {
        this.mreturnId = mreturnId;
    }
    /**
     * @return the productContractCode
     */
    public String getProductContractCode() {
        return productContractCode;
    }
    /**
     * @param productContractCode the productContractCode to set
     */
    public void setProductContractCode(String productContractCode) {
        this.productContractCode = productContractCode;
    }
    /**
     * @return the companyContractCode
     */
    public String getCompanyContractCode() {
        return companyContractCode;
    }
    /**
     * @param companyContractCode the companyContractCode to set
     */
    public void setCompanyContractCode(String companyContractCode) {
        this.companyContractCode = companyContractCode;
    }
    /**
     * @return the productTypeId
     */
    public String getProductTypeId() {
        return productTypeId;
    }
    /**
     * @param productTypeId the productTypeId to set
     */
    public void setProductTypeId(String productTypeId) {
        this.productTypeId = productTypeId;
    }
    /**
     * @return the customerId
     */
    public String getCustomerId() {
        return customerId;
    }
    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    /**
     * @return the userArea
     */
    public String getUserArea() {
        return userArea;
    }
    /**
     * @param userArea the userArea to set
     */
    public void setUserArea(String userArea) {
        this.userArea = userArea;
    }
    /**
     * @return the userProductType
     */
    public String getUserProductType() {
        return userProductType;
    }
    /**
     * @param userProductType the userProductType to set
     */
    public void setUserProductType(String userProductType) {
        this.userProductType = userProductType;
    }

}
