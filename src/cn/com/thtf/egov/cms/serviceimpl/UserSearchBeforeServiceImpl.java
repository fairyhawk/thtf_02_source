package cn.com.thtf.egov.cms.serviceimpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.IDao;
import cn.com.thtf.egov.cms.application.ListRange;
import cn.com.thtf.egov.cms.application.Page;
import cn.com.thtf.egov.cms.iservice.IUserSearchBeforeService;

public class UserSearchBeforeServiceImpl implements IUserSearchBeforeService {

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
			.getLogger(UserSearchBeforeServiceImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.IUserSearchBeforeService#userSearchBefore
	 * (cn.com.thtf.egov.cms.application.Page)
	 */
	@SuppressWarnings("unchecked")
	public Object[] userSearchBefore(Page page) {
		Object[] obj = new Object[5];
		try {
			List users = dao.queryRecordsAll("userManagement.userAll");
			List area = dao.queryRecordsAll("userManagement.userArea");
			List dept = dao.queryRecordsAll("userManagement.userDept");
			List duty = dao.queryRecordsAll("userManagement.userDuty");
			ListRange listRange = dao.queryRecordsByPage(
					"userSeacherBefore.getAll", null, page);
			obj[0] = users;
			obj[1] = area;
			obj[2] = dept;
			obj[3] = duty;
			obj[4] = listRange;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("userSearchBefore err",e);
			e.printStackTrace();
		}
		return obj;
	}

}
