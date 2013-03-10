/**
 * ClassName  CreditTypeLimit
 *
 * History
 * Create User: ChenHuajiang
 * Create Date: 2010-4-12
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

/**
 * 信用类型总额管理模块使用
 * 
 * @author ChenHuajiang
 */

public class CreditTypeLimitDto {
    private Integer prodId; // 产品分类ID

    private String prodName; // 产品分类名称

    private Integer crdttpId; // 信用类型ID

    private String crdttpName; // 信用类型名称

    private String totalLimit; // 信用类型总额

    private String assignedLimit; // 已分配额度

    private String unassignedLimit; // 未分配额度

    private String usedLimit; // 已使用额度

    private String lockedLimit; // 已锁定额度

    private String freeLimit; // 未使用额度

    private String selfLimit;

    private String timeStamp;
    
    private String userId;

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getSelfLimit() {
        return selfLimit;
    }

    public void setSelfLimit(String selfLimit) {
        this.selfLimit = selfLimit;
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

    public Integer getCrdttpId() {
        return this.crdttpId;
    }

    public void setCrdttpId(Integer crdttpId) {
        this.crdttpId = crdttpId;
    }

    public String getCrdttpName() {
        return this.crdttpName;
    }

    public void setCrdttpName(String crdttpName) {
        this.crdttpName = crdttpName;
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
