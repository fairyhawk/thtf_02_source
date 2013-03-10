/**
 * ClassName  NewDaoImpl
 *
 * History
 * Create User: Chenhj
 * Create Date: 2010-4-1
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.application;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import cn.com.thtf.egov.cms.ibatis.ext.LimitSqlExecutor;
import cn.com.thtf.egov.cms.util.ReflectUtil;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.engine.execution.SqlExecutor;
import com.ibatis.sqlmap.engine.impl.ExtendedSqlMapClient;

/**
 * 2010-04-01后使用的DAOImpl类
 * 
 * @author Chenhj
 * 
 */
public class NewDaoImpl implements NewIDao {

    private SqlMapClient sqlMapClient = null;

    private SqlExecutor sqlExecutor;

    public SqlExecutor getSqlExecutor() {
        return sqlExecutor;
    }

    // public void setSqlExecutor(SqlExecutor sqlExecutor) {
    // this.sqlExecutor = sqlExecutor;
    // }

    public void setEnableLimit(boolean enableLimit) {
        if (sqlExecutor instanceof LimitSqlExecutor) {
            ((LimitSqlExecutor) sqlExecutor).setEnableLimit(enableLimit);
        }
    }

    public NewDaoImpl(SqlMapClient sqlMapClient, SqlExecutor sqlExecutor)
            throws IllegalAccessException, InvocationTargetException,
            NoSuchMethodException {
        this.sqlMapClient = sqlMapClient;
        this.sqlExecutor = sqlExecutor;
        if (sqlExecutor != null) {
            if (sqlMapClient instanceof ExtendedSqlMapClient) {
                ReflectUtil.setFieldValue(
                        ((ExtendedSqlMapClient) sqlMapClient).getDelegate(),
                        "sqlExecutor", SqlExecutor.class, sqlExecutor);
            }
        }
    }

    public String insert(String sqlKey, Object param) throws Exception {
        return (String) getSqlMap().insert(sqlKey, param);
    }

    public int update(String sqlKey, Object param) throws Exception {
        return (Integer) getSqlMap().update(sqlKey, param);
    }

    public int delete(String sqlKey, Object param) throws Exception {
        return (Integer) getSqlMap().delete(sqlKey, param);
    }

    @SuppressWarnings("rawtypes")
    public List queryForlist(String sqlKey, Object param) throws Exception {
        return getSqlMap().queryForList(sqlKey, param);
    }

    public Object queryForObject(String sqlKey, Object param) throws Exception {
        return getSqlMap().queryForObject(sqlKey, param);
    }

    public void beginTransaction() throws Exception {
        getSqlMap().startTransaction();
    }

    public void commitTransaction() throws Exception {
        getSqlMap().commitTransaction();
    }

    public void startBatch() throws Exception {
        getSqlMap().startBatch();
    }

    public void executeBatch() throws Exception {
        getSqlMap().executeBatch();
    }

    public void endTransaction() throws Exception {
        getSqlMap().endTransaction();
    }

    // public void setSqlMapClient(SqlMapClient sqlMapClient) {
    // this.sqlMapClient = sqlMapClient;
    // }

    @Override
    public SqlMapClient getSqlMap() throws Exception {
        return sqlMapClient;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.com.thtf.egov.cms.application.NewIDao#getCount(java.lang.String,
     * java.lang.Object)
     */
    @Override
    public int getCount(String sqlKey, Object para) throws Exception {
        return (Integer) getSqlMap().queryForObject(sqlKey, para);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.application.NewIDao#queryRecords(java.lang.String,
     * java.lang.Object, int)
     */
    @SuppressWarnings("unchecked")
    public List<Object> queryRecords(String sqlKey, Object para, int pageNum,
            int pageCount) throws Exception {
        return getSqlMap().queryForList(sqlKey, para, pageNum, pageCount);
    }
}