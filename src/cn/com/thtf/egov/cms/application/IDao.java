/**
 * ClassName  IDao
 *
 * History
 * Create User: Lubo
 * Create Date: 2009-6-30
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.application;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * IDao
 * 
 * @author Lubo
 * 
 */
public interface IDao<T extends Serializable> {

	/** insert */
	public int insert(String sqlKey, T param) throws Exception;

	/** insert1 */
	public Object insert1(String sqlKey, Object obj) throws Exception;

	/** update */
	public int update(String sqlKey, Object obj) throws Exception;

	/** delete */
	public int delete(String sqlKey, T param) throws Exception;

	/** delete1 */
	public int delete1(String sqlKey, Object obj) throws Exception;

	/** get */
	public T get(String sqlkey, T param) throws Exception;

	/** */
	public int getCount(String nameSpace) throws Exception;

	/** */
	public int getCount(ListRange<T> range, String nameSpace) throws Exception;

	/** queryRecords */
	public List<T> queryRecords(String sqlKey, ListRange<T> range)
			throws Exception;

	/** queryRecordsAll */
	public List<T> queryRecordsAll(String sqlKey) throws Exception;

	public ListRange<T> queryRecordsByPage(String sqlKey, T param, Page page)
			throws Exception;

	public List<T> queryForlist(String sqlKey, T param) throws Exception;

	@SuppressWarnings("unchecked")
	public List queryForlist1(String sqlKey, Object param) throws Exception;

	public int update1(String sqlKey, Object param) throws Exception;

	public SqlMapClient getSqlMap() throws Exception;

	public Connection getConnection() throws Exception;// 获得基本连接Connection

	public void closePreparedStatement(PreparedStatement ps) throws Exception;// 关闭Preparedstatement

	public void closeResultSet(ResultSet rs) throws Exception;// 关闭ResultSet

	public void closeConnection(Connection con) throws Exception;// 关闭Connection

	public void commitConnection(Connection con) throws Exception;// 提交Connection事务

	public void rollbackConnection(Connection con) throws Exception;// 回滚Connection事务
}
