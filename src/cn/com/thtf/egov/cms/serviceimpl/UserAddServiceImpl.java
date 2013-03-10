package cn.com.thtf.egov.cms.serviceimpl;

import cn.com.thtf.egov.cms.application.IDao;
import cn.com.thtf.egov.cms.iservice.IUserAddService;

public class UserAddServiceImpl implements IUserAddService {

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.thtf.egov.cms.iservice.IUserAddService#userAddBefore()
	 */
	public Object[] userAddBefore() {
		Object[] obj = new Object[6];
		try {
			obj[0] = dao.queryRecordsAll("userManagement.userDept");
			obj[1] = dao.queryRecordsAll("userManagement.userArea");
			obj[2] = dao.queryRecordsAll("userManagement.userAdd_1");
			obj[3] = dao.queryRecordsAll("userManagement.userAdd_5");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}

}
