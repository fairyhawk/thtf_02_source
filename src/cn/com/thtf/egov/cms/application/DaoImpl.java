/**
 * ClassName  GenericDAO
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
 * GenericDAO
 * 
 * @author Lubo
 * 
 */
public class DaoImpl<T extends Serializable> implements IDao<T> {

	private SqlMapClient sqlMapClient = null;

	@SuppressWarnings("unchecked")
	public List<T> queryForlist(String sqlKey, T param) throws Exception {
		return getTemplate().queryForList(sqlKey, param);
	}

	/**
	 * delete
	 */
	public int delete(String sqlKey, T param) throws Exception {
		return getTemplate().delete(sqlKey, param);
	}

	/**
	 * get
	 */
	@SuppressWarnings("unchecked")
	public T get(String sqlkey, T param) throws Exception {
		return (T) getTemplate().queryForObject(sqlkey, param);
	}

	/**
	 * getCount
	 */
	public int getCount(String nameSpace) throws Exception {
		Integer count = (Integer) getTemplate().queryForObject(
				nameSpace + "recordCount");
		return count == null ? 0 : count.intValue();
	}

	/**
	 * getCount
	 */
	public int getCount(ListRange<T> range, String nameSpace) throws Exception {
		Integer count = (Integer) getTemplate().queryForObject(
				nameSpace + "recordCount", range);
		return count == null ? 0 : count.intValue();
	}

	/**
	 * queryRecords
	 */
	@SuppressWarnings("unchecked")
	public List<T> queryRecords(String sqlKey, ListRange<T> range)
			throws Exception {
		return getTemplate().queryForList(sqlKey, range,
				range.getPage().getOffset(), range.getPage().getPageSize());
	}

	/**
	 * queryRecordsAll
	 */
	@SuppressWarnings("unchecked")
	public List<T> queryRecordsAll(String sqlKey) throws Exception {
		return getTemplate().queryForList(sqlKey);
	}

	/**
	 * insert
	 */
	public int insert(String sqlKey, T target) throws Exception {
		Integer result = (Integer) getTemplate().insert(sqlKey, target);
		return result == null ? 0 : result.intValue();
	}

	/**
	 * update
	 */
	public int update(String sqlKey, T target) throws Exception {
		return getTemplate().update(sqlKey, target);
	}

	public ListRange<T> queryRecordsByPage(String sqlKey, T param, Page page)
			throws Exception {
		ListRange<T> range = new ListRange<T>(page);
		range.setQuery(param);
		range.setRecords(queryRecords(sqlKey, range));
		page.setTotalSize(getCount(range, sqlKey.substring(0, sqlKey
				.indexOf(".") + 1)));
		page.setThisPage(page.getOffset() / page.getPageSize() + 1);

		page.setOffset((page.getTotalSize() - 1) / page.getPageSize() + 1);
		range.clear();
		return range;
	}

	/**
	 * @return SqlMapClientTemplate
	 */
	public SqlMapClient getTemplate() {
		return sqlMapClient;
	}

	@SuppressWarnings("unchecked")
	public List queryForlist1(String sqlKey, Object param) throws Exception {
		// TODO Auto-generated method stub
		return getTemplate().queryForList(sqlKey, param);
	}

	public int update1(String sqlKey, Object param) throws Exception {
		// TODO Auto-generated method stub
		return getTemplate().update(sqlKey, param);
	}

	public int delete1(String sqlKey, Object obj) throws Exception {
		// TODO Auto-generated method stub
		return getTemplate().delete(sqlKey, obj);
	}

	public int update(String sqlKey, Object obj) throws Exception {
		// TODO Auto-generated method stub
		return getTemplate().update(sqlKey, obj);
	}

	public Object insert1(String sqlKey, Object obj) throws Exception {
		// TODO Auto-generated method stub
		return getTemplate().insert(sqlKey, obj);
	}

	public Connection getConnection() throws Exception {
		Connection con = null;
		con = sqlMapClient.getDataSource().getConnection();
		return con;
	}

	public void closeConnection(Connection con) throws Exception {
		if (con != null)
			con.close();
	}

	public void closePreparedStatement(PreparedStatement ps) throws Exception {
		if (ps != null)
			ps.close();
	}

	public void closeResultSet(ResultSet rs) throws Exception {
		if (rs != null)
			rs.close();
	}

	public void commitConnection(Connection con) throws Exception {
		if (con != null)
			con.commit();
	}

	public void rollbackConnection(Connection con) throws Exception {
		if (con != null)
			con.rollback();
	}

	/**
	 * @param sqlMapClient
	 *            the sqlMapClient to set
	 */
	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * 
	 * @see cn.com.thtf.egov.cms.application.IDao#getSqlMap()
	 */
	public SqlMapClient getSqlMap() throws Exception {
		return sqlMapClient;
	}

}
