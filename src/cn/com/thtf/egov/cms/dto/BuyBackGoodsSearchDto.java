/**
 * ClassName  BuyBackGoodsSearchDto
 *
 * History
 * Create User: Administrator
 * Create Date: 2010-6-12
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

import org.apache.commons.lang.StringUtils;


/**
 * 返厂检索dto
 * @author zhangzx
 */

public class BuyBackGoodsSearchDto {
    private String id; // 返厂单号

    private String stockroomName;// 库房名称
    
    private String stockroomId;//库房编号

    private String productContractCode;// 产品合同号

    private String companyContractCode;// 公司合同号

    private String productTypeId;// 产品分类编号

    private String productTypeName;// 产品分类名称

    private String supplierName;// 供货商名称

    private String money;// 返厂单金额

    private String userName;// 人员名称

    private String date;// 申请日期

    private String sendDate;// 实际返厂日期

    private String status;// 返厂单状态
    
    private String loginId;//用户id
    
    private String roleId; //角色id

    /**
     * @return the id
     */
    public String getId() {
        return StringUtils.trim(id);
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the stockroomName
     */
    public String getStockroomName() {
        return StringUtils.trim(stockroomName);
    }

    /**
     * @param stockroomName the stockroomName to set
     */
    public void setStockroomName(String stockroomName) {
        this.stockroomName = stockroomName;
    }

    /**
     * @return the productContractCode
     */
    public String getProductContractCode() {
        return StringUtils.trim(productContractCode);
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
        return StringUtils.trim(companyContractCode);
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
        return StringUtils.trim(productTypeId);
    }

    /**
     * @param productTypeId the productTypeId to set
     */
    public void setProductTypeId(String productTypeId) {
        this.productTypeId = productTypeId;
    }

    /**
     * @return the productTypeName
     */
    public String getProductTypeName() {
        return StringUtils.trim(productTypeName);
    }

    /**
     * @param productTypeName the productTypeName to set
     */
    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    /**
     * @return the supplierName
     */
    public String getSupplierName() {
        return StringUtils.trim(supplierName);
    }

    /**
     * @param supplierName the supplierName to set
     */
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    /**
     * @return the money
     */
    public String getMoney() {
        return StringUtils.trim(money);
    }

    /**
     * @param money the money to set
     */
    public void setMoney(String money) {
        this.money = money;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return StringUtils.trim(userName);
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the sendDate
     */
    public String getSendDate() {
        return sendDate;
    }

    /**
     * @param sendDate the sendDate to set
     */
    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the stockroomId
     */
    public String getStockroomId() {
        return stockroomId;
    }

    /**
     * @param stockroomId the stockroomId to set
     */
    public void setStockroomId(String stockroomId) {
        this.stockroomId = stockroomId;
    }

    /**
     * @return the loginId
     */
    public String getLoginId() {
        return loginId;
    }

    /**
     * @param loginId the loginId to set
     */
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    /**
     * @return the roleId
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * @param roleId the roleId to set
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    

}