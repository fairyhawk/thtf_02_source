/**
 * ClassName  BaseService
 *
 * History
 * Create User: Lubo
 * Create Date: 2009-11-18
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.application;

import java.util.List;

/**
 * BaseService
 * 
 * @author Lubo
 * 
 */
public class BaseService {

	/**
	 * queryRecords
	 * 
	 * @return
	 */
	public List<Object> queryRecords(NewIDao newDao, String sql, Object para,
			NewPage page) throws Exception {

		int count = newDao.getCount(sql.substring(0, sql.indexOf(".") + 1)
				+ "recordCount", para);
		page.setTotalResultSize(count);

		int totalPageSize = (page.getTotalResultSize() - 1)
				/ page.getPageSize() + 1;
		page.setTotalPageSize(totalPageSize);

		return newDao.queryRecords(sql, para, (page.getThisPage() - 1)
				* page.getPageSize(), page.getPageSize());

	}

}
