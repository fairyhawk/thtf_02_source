/**
 * ClassName  UserAreaMappingEntity
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-11-23
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.entity;

import cn.com.thtf.egov.cms.application.BaseEntity;

/**
 * UserAreaMappingEntity
 * 
 * @author Lubo
 */
public class UserAreaMappingEntity extends BaseEntity {

    /** 自动生成序列化ID */
    private static final long serialVersionUID = 5218956859792267451L;
    /** ID */
    private Integer id;
    /** 用户ID */
    private String userId;
    /** 区域ID */
    private Integer userAreaId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getUserAreaId() {
        return userAreaId;
    }

    public void setUserAreaId(Integer userAreaId) {
        this.userAreaId = userAreaId;
    }

}
