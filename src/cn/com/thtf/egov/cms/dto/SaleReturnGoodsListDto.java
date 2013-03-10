package cn.com.thtf.egov.cms.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class SaleReturnGoodsListDto implements Serializable {

    /**
     * 自动序列化
     */
    private static final long serialVersionUID = 8838403066911770326L;
    
    /**退货单ID**/
    private String id;
    /**发货单ID**/
    private String sendGoodsId;
    /**产品分类ID**/
    private Integer productTypeId;
    /**产品分类名称**/
    private String productTypeName;
    /**客户ID**/
    private Integer customerId;
    /**客户名称**/
    private String customerName;
    /**申请日期**/
    private String date;
    /**退货金额**/
    private BigDecimal money;
    /**退货状态**/
    private Integer status;
    /**库房ID**/
    private Integer stockRoomId;
    /**库房名称**/
    private String stockRoomName;
    /**用户ID**/
    private String userId;
    /**用户名称**/
    private String userName;
    /**产品合同号**/
    private String productContractCode;
    /**公司合同号**/
    private String companyContarctCode;
    /**退回日期**/
    private String sbcDate;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getSendGoodsId() {
        return sendGoodsId;
    }
    public void setSendGoodsId(String sendGoodsId) {
        this.sendGoodsId = sendGoodsId;
    }
    public Integer getProductTypeId() {
        return productTypeId;
    }
    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }
    public String getProductTypeName() {
        return productTypeName;
    }
    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }
    public Integer getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public BigDecimal getMoney() {
        return money;
    }
    public void setMoney(BigDecimal money) {
        this.money = money;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Integer getStockRoomId() {
        return stockRoomId;
    }
    public void setStockRoomId(Integer stockRoomId) {
        this.stockRoomId = stockRoomId;
    }
    public String getStockRoomName() {
        return stockRoomName;
    }
    public void setStockRoomName(String stockRoomName) {
        this.stockRoomName = stockRoomName;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getProductContractCode() {
        return productContractCode;
    }
    public void setProductContractCode(String productContractCode) {
        this.productContractCode = productContractCode;
    }
    public String getCompanyContarctCode() {
        return companyContarctCode;
    }
    public void setCompanyContarctCode(String companyContarctCode) {
        this.companyContarctCode = companyContarctCode;
    }
    public String getSbcDate() {
        return sbcDate;
    }
    public void setSbcDate(String sbcDate) {
        this.sbcDate = sbcDate;
    }
   
}
