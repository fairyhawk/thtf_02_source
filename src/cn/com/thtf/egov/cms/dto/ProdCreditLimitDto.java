/**
 * ClassName  ProdCreditLimit
 *
 * History
 * Create User: ChenHuajiang
 * Create Date: 2010-4-8
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

/**
 * 产品信用总额管理模块使用
 * 
 * @author ChenHuajiang
 */

public class ProdCreditLimitDto {
    private Integer prodId; // 产品分类ID

    private String prodName; // 产品分类名称

    private String totalLimit; // 产品信用总额

    private String assignedLimit; // 已分配额度

    private String unassignedLimit; // 未分配额度

    private String usedLimit; // 已使用额度

    private String lockedLimit; // 已锁定额度

    private String freeLimit; // 未使用额度

    private String userId;// 登陆Id

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getProdId() {
        return this.prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return this.prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getTotalLimit() {
        return this.totalLimit;
    }

    public void setTotalLimit(String totalLimit) {
        this.totalLimit = totalLimit;
    }

    public String getAssignedLimit() {
        return this.assignedLimit;
    }

    public void setAssignedLimit(String assignedLimit) {
        this.assignedLimit = assignedLimit;
    }

    public String getUnassignedLimit() {
        return this.unassignedLimit;
    }

    public void setUnassignedLimit(String unassignedLimit) {
        this.unassignedLimit = unassignedLimit;
    }

    public String getUsedLimit() {
        return this.usedLimit;
    }

    public void setUsedLimit(String usedLimit) {
        this.usedLimit = usedLimit;
    }

    public String getLockedLimit() {
        return this.lockedLimit;
    }

    public void setLockedLimit(String lockedLimit) {
        this.lockedLimit = lockedLimit;
    }

    public String getFreeLimit() {
        return this.freeLimit;
    }

    public void setFreeLimit(String freeLimit) {
        this.freeLimit = freeLimit;
    }

}
