package cn.com.thtf.egov.cms.dto;

import java.io.Serializable;
/**
 * 销售退货检索DTO
 * 
 * @author LuPing
 */
public class ReturnGoodsListQueryDto implements Serializable {

    /**
     * 自动序列化
     */
    private static final long serialVersionUID = 3339154693046798090L;
    
    /** 初始化**/
    private String init ;
    /**退货单ID**/
    private String id;
    /**发货单ID**/
    private String sendGoodsId;
    /**库房ID**/
    private String stockRoomId;
    /**库房名称**/
    private String stockRoomName;
    /**产品合同号**/
    private String productContractCode;
    /**公司合同号**/
    private String companyContarctCode;
    /**产品分类ID**/
    private String productTypeId;
    /**产品分类名称**/
    private String productTypeName;
    /**客户ID**/
    private String customerId;
    /**客户名称**/
    private String customerName;
    /**用户ID**/
    private String userId;
    /**用户名称**/
    private String userName;
    /**申请起始日期**/
    private String startDate;
    /**申请终止日期**/
    private String endDate;
    /**退货起始日期**/
    private String startSBCDate;
    /**退货终止日期**/
    private String endSBCDate;
    /**退货状态**/
    private String status;
    /*当前登陆用户*/
        /**当前ID**/
    private String nowUserId;
        /**当前权限**/
    private Integer roleId;
        /**当前区域**/
    private Integer nowUserAreaId;
    
    public Integer getNowUserAreaId() {
            return nowUserAreaId;
        }
        public void setNowUserAreaId(Integer nowUserAreaId) {
            this.nowUserAreaId = nowUserAreaId;
        }
    public String getNowUserId() {
            return nowUserId;
        }
        public void setNowUserId(String nowUserId) {
            this.nowUserId = nowUserId;
        }
    public Integer getRoleId() {
            return roleId;
        }
        public void setRoleId(Integer roleId) {
            this.roleId = roleId;
        }
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
    public String getStockRoomName() {
        return stockRoomName;
    }
    public void setStockRoomName(String stockRoomName) {
        this.stockRoomName = stockRoomName;
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
    public String getProductTypeName() {
        return productTypeName;
    }
    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public String getStartSBCDate() {
        return startSBCDate;
    }
    public void setStartSBCDate(String startSBCDate) {
        this.startSBCDate = startSBCDate;
    }
    public String getEndSBCDate() {
        return endSBCDate;
    }
    public void setEndSBCDate(String endSBCDate) {
        this.endSBCDate = endSBCDate;
    }
    public String getStockRoomId() {
        return stockRoomId;
    }
    public void setStockRoomId(String stockRoomId) {
        this.stockRoomId = stockRoomId;
    }
    public String getProductTypeId() {
        return productTypeId;
    }
    public void setProductTypeId(String productTypeId) {
        this.productTypeId = productTypeId;
    }
    public String getCustomerId() {
        return customerId;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getInit() {
        return init;
    }
    public void setInit(String init) {
        this.init = init;
    }
   
    
}
