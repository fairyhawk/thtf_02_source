/**
 * ClassName  WayBillViewServiceImpl
 *
 * History
 * Create User: jiangmingxing
 * Create Date: 2010-5-13
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.serviceimpl.wayBill;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.BaseService;
import cn.com.thtf.egov.cms.application.NewIDao;
import cn.com.thtf.egov.cms.dto.WayBillViewDto;
import cn.com.thtf.egov.cms.iservice.wayBill.IWayBillViewService;

/**
 * 运单查看
 * 
 * @author jiangmingxing
 */

public class WayBillViewServiceImpl extends BaseService implements
		IWayBillViewService {
	/** log */
	private static Logger log = LoggerFactory
			.getLogger(WayBillViewServiceImpl.class);
	/** NewIDao */
	private NewIDao dao;

	/**
	 * @return the log
	 */
	public static Logger getLog() {
		return log;
	}

	/**
	 * @param log
	 *            the log to set
	 */
	public static void setLog(Logger log) {
		WayBillViewServiceImpl.log = log;
	}

	/**
	 * @return the dao
	 */
	public NewIDao getDao() {
		return dao;
	}

	/**
	 * @param dao
	 *            the dao to set
	 */
	public void setDao(NewIDao dao) {
		this.dao = dao;
	}

	/**
	 * 运单查看ID获取
	 * 
	 * @author jiangmingxing
	 * @throws Exception
	 */

	public WayBillViewDto getWayBillById(String box_id) {

		WayBillViewDto result = null;

		try {
			result = (WayBillViewDto) dao.queryForObject("wayBill.selectWayBillForModify",
					box_id);
		} catch (Exception e) {
			log.error("获取运单查看信息错误", e);
		}
		return result;
	}

}
