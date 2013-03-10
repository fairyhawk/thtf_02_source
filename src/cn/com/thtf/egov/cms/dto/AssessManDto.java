package cn.com.thtf.egov.cms.dto;

/**
 * 销售合同评审人
 * 
 * @author 李乐伟
 */
public class AssessManDto {

    private String assessManId; // 评审人id

    private Integer workId; // 事物id

    private Integer roleId; // 角色ID

    public Integer getRoleId() {
        return this.roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getAssessManId() {
        return assessManId;
    }

    public void setAssessManId(String assessManId) {
        this.assessManId = assessManId;
    }

    public Integer getWorkId() {
        return workId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
    }

}
