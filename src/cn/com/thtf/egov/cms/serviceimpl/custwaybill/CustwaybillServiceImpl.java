/**
 * ClassName  CustwaybillServiceImpl
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-8-17
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.serviceimpl.custwaybill;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.BaseService;
import cn.com.thtf.egov.cms.application.NewIDao;
import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.dto.CustwaybillDto;
import cn.com.thtf.egov.cms.dto.WayBillViewDto;
import cn.com.thtf.egov.cms.iservice.custwaybill.ICustwaybillService;

/**
 * CustwaybillServiceImpl
 * 
 * @author Lubo
 */
public class CustwaybillServiceImpl extends BaseService implements
		ICustwaybillService {

	/** log */
	private static Logger log = LoggerFactory
			.getLogger(CustwaybillServiceImpl.class);
	/** NewIDao */
	private NewIDao dao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.thtf.egov.cms.iservice.custwaybill.ICustwaybillService#
	 * getWayBillDetail(java.lang.String)
	 */
	public WayBillViewDto getWayBillDetail(String id) {
		WayBillViewDto result = null;
		try {
			result = (WayBillViewDto) dao
					.queryForObject(
							"custwaybill_sqlMap.queryWayBillByBoxId",
							id);
		} catch (Exception e) {
			log.error("查看运单详情错误", e);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.custwaybill.ICustwaybillService#getWaybill
	 * (cn.com.thtf.egov.cms.dto.CustwaybillDto,
	 * cn.com.thtf.egov.cms.application.NewPage)
	 */
	@SuppressWarnings("unchecked")
	public List<CustwaybillDto> getWaybill(CustwaybillDto para, NewPage page) {
		List<CustwaybillDto> result = null;
		try {
			// result = queryRecords(dao, "queryCustwaybill.date", para, page);
			result = dao.queryForlist("queryCustwaybill.date", para);
		} catch (Exception e) {
			log.error("检索运单列表错误！", e);
		}
		return result;
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
}
