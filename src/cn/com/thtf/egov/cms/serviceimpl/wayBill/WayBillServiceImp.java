/**
 * ClassName  WayBillServiceImp
 *
 * History
 * Create User: liuqg
 * Create Date: 2010-5-10
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.serviceimpl.wayBill;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.BaseService;
import cn.com.thtf.egov.cms.application.NewIDao;
import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.dto.WayBillViewDto;
import cn.com.thtf.egov.cms.dto.WayBillSearchDto;
import cn.com.thtf.egov.cms.dto.WayBillAllDto;
import cn.com.thtf.egov.cms.iservice.wayBill.IWayBillService;

/**
 * 运单管理
 * 
 * @author liuqg
 */

public class WayBillServiceImp extends BaseService implements IWayBillService {

	/** log */
	private static Logger log = LoggerFactory
			.getLogger(WayBillServiceImp.class);
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
		WayBillServiceImp.log = log;
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
	 * 运单一览获取
	 * 
	 * @author liuqg
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<WayBillAllDto> selectWayBillAll(NewPage page,
			Integer logisticsId) throws Exception {
		List result = null;
		try {
			result = queryRecords(dao, "wayBillAllPage.selectWayBillAll",
					logisticsId, page);
		} catch (Exception e) {
			log.error("获取运单一览错误:", e);
		}
		return result;
	}

	/**
	 * 根据画面条件检索运单一览
	 * 
	 * @author liuqg
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<WayBillAllDto> selectWayBillByCondition(NewPage page,
			WayBillSearchDto searchWayBillDto) throws Exception {
		List result = null;
		try {
			result = queryRecords(dao,
					"searchWayBillPage.selectWayBillByCondition",
					searchWayBillDto, page);
		} catch (Exception e) {
			log.error("检索运单一览错误:", e);
		}
		return result;
	}

	/**
	 * 根据装箱单号获取运单信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@Override
	public WayBillViewDto selectWayBillForModify(String boxId) throws Exception {
		WayBillViewDto result = null;
		try {
			result = (WayBillViewDto) dao.queryForObject(
					"wayBill.selectWayBillForModify", boxId);
		} catch (Exception e) {
			log.error("根据装箱单号运单号获取运单信息错误:", e);
		}
		return result;

	}

	/**
	 * 修改运单信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean updateWayBill(WayBillViewDto billModifyDto) throws Exception {

		try {
			dao.beginTransaction();
			dao.update("wayBill.updateWayBill", billModifyDto);
			dao.commitTransaction();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			log.error("修改运单信息错误", e);
			return false;
		} finally {
			try {
				dao.endTransaction();
			} catch (Exception e) {
				log.error("修改运单信息事务回滚错误", e);
			}
		}
	}

}
