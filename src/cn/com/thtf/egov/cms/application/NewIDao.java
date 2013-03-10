/**
 * ClassName  NewIDao
 *
 * History
 * Create User: Chenhj
 * Create Date: 2010-4-1
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.application;

import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * 2010-04-01后使用的DAO基类
 * 
 * @author Chenhj
 * 
 */
public interface NewIDao {

    public SqlMapClient getSqlMap() throws Exception;

    /** 开始事务 */
    public void beginTransaction() throws Exception;

    /** 提交事务 */
    public void commitTransaction() throws Exception;

    /** 结束事务 */
    public void endTransaction() throws Exception;

    /** 开始批处理 */
    public void startBatch() throws Exception;

    /** 执行批处理 */
    public void executeBatch() throws Exception;

    /** insert */
    public String insert(String sqlKey, Object param) throws Exception;

    /** update */
    public int update(String sqlKey, Object param) throws Exception;

    /** delete */
    public int delete(String sqlKey, Object param) throws Exception;

    /**
     * 翻页查询 求数据
     * 
     * @param sqlKey
     * @param para
     * @param pageNum
     * @param pageCount
     * @return
     * @throws Exception
     */
    public List<Object> queryRecords(String sqlKey, Object para, int pageNum,
            int pageCount) throws Exception;

    /**
     * 翻页查询 求返回结果
     * 
     * @param sqlKey
     * @param para
     * @return
     * @throws Exception
     */
    public int getCount(String sqlKey, Object para) throws Exception;

    /**
     * 查询List
     * 
     * @param sqlKey
     * @param param
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List queryForlist(String sqlKey, Object param) throws Exception;

    /**
     * 查询单一结果
     * 
     * @param sqlKey
     * @param param
     * @return
     * @throws Exception
     */
    public Object queryForObject(String sqlKey, Object param) throws Exception;
}