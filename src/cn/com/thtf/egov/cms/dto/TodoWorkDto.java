/**
 * ClassName  TodoWorkDto
 *
 * History
 * Create User: Shiy
 * Create Date: 2010-4-29
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

/**
 * 待办事务的存储类
 * 
 * @author Shiy
 */

public class TodoWorkDto {

    /**
     * work_m表主键，待办事务id
     */
    private int work_id = 0;
    /**
     * 待办事务名称
     */
    private String work_name = null;
    /**
     * 待办事务数量
     */
    private int count = 0;
    /**
     * 跳转用url
     */
    private String url = null;

    /**
     * @return the work_id
     */
    public int getWork_id() {
        return work_id;
    }

    /**
     * @param workId
     *            the work_id to set
     */
    public void setWork_id(int workId) {
        work_id = workId;
    }

    /**
     * @return the work_name
     */
    public String getWork_name() {
        return work_name;
    }

    /**
     * @param workName
     *            the work_name to set
     */
    public void setWork_name(String workName) {
        work_name = workName;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     *            the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * @param count
     *            the count to set
     */
    public void setCount(int count) {
        this.count = count;
    }

}
