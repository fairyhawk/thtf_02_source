package cn.com.thtf.egov.cms.dto;

import java.util.List;

import cn.com.thtf.egov.cms.entity.UserAreaProductEntity;

public class SalesBackQueryDto {

    private Integer productTypeId; // 产品分类id

    private String productContractCode; // 产品合同号

    private String companyContractCode; // 公司合同号

    private String customerName; // 客户名

    private Integer templateType; // 模板类型

    private String userName; // 用户名

    private Integer status; // 合同状态

    private String startTime; // 合同起始时间

    private String endTime;// 合同结束时间

    private String currentUserId;// 当前用户id

    private Integer roleId;// 角色id
    
    private String initStr;

    private String userId;
    
    private List<UserAreaProductEntity> userAreaProductList; 
    public String getInitStr() {
        return initStr;
    }

    public void setInitStr(String initStr) {
        this.initStr = initStr;
    }

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getProductContractCode() {
        return productContractCode;
    }

    public void setProductContractCode(String productContractCode) {
        this.productContractCode = productContractCode;
    }

    public String getCompanyContractCode() {
        return companyContractCode;
    }

    public void setCompanyContractCode(String companyContractCode) {
        this.companyContractCode = companyContractCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getTemplateType() {
        return templateType;
    }

    public void setTemplateType(Integer templateType) {
        this.templateType = templateType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(String currentUserId) {
        this.currentUserId = currentUserId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<UserAreaProductEntity> getUserAreaProductList() {
		return userAreaProductList;
	}

	public void setUserAreaProductList(
			List<UserAreaProductEntity> userAreaProductList) {
		this.userAreaProductList = userAreaProductList;
	}

}
