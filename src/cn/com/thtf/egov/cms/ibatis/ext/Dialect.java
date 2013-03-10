/**
 * ClassName  Dialect
 *
 * History
 * Create User: Shiy
 * Create Date: 2010-8-24
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.ibatis.ext;

/**
 * 
 * 针对不同db的翻页控制接口
 * 
 * @author Shiy
 */

public interface Dialect {

    /**
     * 是否支持分页
     * 
     * @return
     */
    public boolean supportsLimit();

    /**
     * 取得分页sql
     * 
     * @param sql
     * @param hasOffset
     * @return
     */
    public String getLimitString(String sql, boolean hasOffset);

    /**
     * 取得分页sql
     * 
     * @param sql
     * @param offset
     * @param limit
     * @return
     */
    public String getLimitString(String sql, int offset, int limit);

}
