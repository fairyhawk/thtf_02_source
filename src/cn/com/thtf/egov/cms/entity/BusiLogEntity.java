/**
 * ClassName  BusiLogEntity
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-8-24
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.entity;

import java.sql.Timestamp;

import cn.com.thtf.egov.cms.application.BaseEntity;

/**
 * BusiLogEntity
 * 
 * @author Lubo
 */
public class BusiLogEntity extends BaseEntity {

    /** 自动生成序列化ID */
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private int id;
    /** 创建时间 */
    private Timestamp createtime;
    /** 创建人 */
    private String userid;
    /** 创建人姓名 */
    private String username;
    /** log信息 */
    private String content;
    /** 操作内容 */
    private int busiType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getBusiType() {
        return busiType;
    }

    public void setBusiType(int busiType) {
        this.busiType = busiType;
    }

}
