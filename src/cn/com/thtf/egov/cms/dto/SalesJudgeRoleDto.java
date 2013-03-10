/**
 * ClassName  RoleNodeDto
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-4-7
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

/**
 * RoleNodeDto
 * 
 * @author shensi
 */

public class SalesJudgeRoleDto {
    
    private String userId;//用户Id
    
    private String userName;//用户名
    
    private int RoleId;//角色Id
    
    private String proTypeId;//产品分类ID

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

    public int getRoleId() {
        return RoleId;
    }

    public void setRoleId(int roleId) {
        RoleId = roleId;
    }

    public String getProTypeId() {
        return proTypeId;
    }

    public void setProTypeId(String proTypeId) {
        this.proTypeId = proTypeId;
    }

}
