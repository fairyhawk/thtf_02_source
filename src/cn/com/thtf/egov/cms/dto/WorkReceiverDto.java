/**
 * ClassName  WorkReceiverDto
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-5-24
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

import java.util.Map;

/**
 * WorkReceiverDto
 * 
 * @author Lubo
 */

public class WorkReceiverDto {

    /** 事物接收者ID */
    private String userId;
    /** 事物接收者ID Map */
    private Map<Integer, String> roleIdMap = null;

    /**
     * 根据权限ID检索对应事物接收者ID
     * 
     * @param roleId
     * @return
     */
    public String getUserIdByRoleId(Integer roleId) {
        return roleIdMap.get(roleId);
    }

    /**
     * 单一权限时,获得事物接收者ID
     * 
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param roleIdMap
     *            the roleIdMap to set
     */
    public void setRoleIdMap(Map<Integer, String> roleIdMap) {
        this.roleIdMap = roleIdMap;
    }

    /**
     * @param userId
     *            the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

}
