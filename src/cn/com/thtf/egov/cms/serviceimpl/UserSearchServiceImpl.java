package cn.com.thtf.egov.cms.serviceimpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.ListRange;
import cn.com.thtf.egov.cms.application.Page;

import cn.com.thtf.egov.cms.application.IDao;
import cn.com.thtf.egov.cms.dto.UersSearchInfoDto;
import cn.com.thtf.egov.cms.iservice.IUserSearchService;

public class UserSearchServiceImpl implements IUserSearchService {

	@SuppressWarnings("unchecked")
	private IDao dao;

	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public IDao getDao() {
		return dao;
	}

	/**
	 * 
	 * @param dao
	 */
	@SuppressWarnings("unchecked")
	public void setDao(IDao dao) {
		this.dao = dao;
	}

	private static final Logger log = LoggerFactory
			.getLogger(UserSearchServiceImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.IUserSearchService#userSearch(cn.com.thtf
	 * .egov.cms.dto.UersSearchInfoDto, cn.com.thtf.egov.cms.application.Page)
	 */
	@SuppressWarnings("unchecked")
	public Object[] userSearch(UersSearchInfoDto userSearchInfo, Page page) {
		Object[] obj = new Object[5];
		try {
			List users = dao.queryRecordsAll("userManagement.userAll");
			List area = dao.queryRecordsAll("userManagement.userArea");
			List dept = dao.queryRecordsAll("userManagement.userDept");
			List duty = dao.queryRecordsAll("userManagement.userDuty");
			ListRange listRange = dao.queryRecordsByPage("userSeacher.search",
					userSearchInfo, page);
			obj[0] = users;
			obj[1] = area;
			obj[2] = dept;
			obj[3] = duty;
			obj[4] = listRange;
		} catch (Exception e) {
			log.error("userSearch", e);
			e.printStackTrace();
		}
		return obj;
	}
}
