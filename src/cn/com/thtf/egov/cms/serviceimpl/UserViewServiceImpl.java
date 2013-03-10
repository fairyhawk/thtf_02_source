package cn.com.thtf.egov.cms.serviceimpl;

import java.util.List;

import cn.com.thtf.egov.cms.application.IDao;
import cn.com.thtf.egov.cms.iservice.IUserViewService;

public class UserViewServiceImpl implements IUserViewService {

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
	 * @see
	 * cn.com.thtf.egov.cms.iservice.IUserViewService#userView(java.lang.String,
	 * java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public Object[] userView(String id, Integer viewid) {
		Object[] obj = new Object[3];
		try {
			if (viewid == 1) {
				obj[0] = ((List) dao.queryForlist1("userManagement.userView_1",
						id)).get(0);
			} else if (viewid == 2) {
				obj[0] = ((List) dao.queryForlist1("userManagement.userView_1",
						id)).get(0);
				obj[1] = dao.queryForlist1("userManagement.userView_2", id);
			} else if (viewid == 3) {
				obj[0] = ((List) dao.queryForlist1(
						"userManagement.userView_14", id)).get(0);
				obj[1] = dao.queryForlist1("userManagement.userView_2", id);
			} else if (viewid == 4) {
				obj[0] = ((List) dao.queryForlist1(
						"userManagement.userView_14", id)).get(0);
				obj[1] = dao.queryForlist1("userManagement.userView_2", id);
				obj[2] = dao.queryForlist1("userManagement.userView_3", id);
			} else if (viewid == 5) {
				obj[0] = dao.queryForlist1("userManagement.userView_13", id)
						.size() == 0 ? null : ((List) dao.queryForlist1(
						"userManagement.userView_13", id)).get(0);
			} else if (viewid == 6) {
				obj[0] = dao.queryForlist1("userManagement.userView_1", id)
						.size() == 0 ? null : ((List) dao.queryForlist1(
						"userManagement.userView_1", id)).get(0);
				obj[1] = dao.queryForlist1(
						"userManagement.salesAssistantAddBefore_4", id);
			} else if (viewid == 7) {
				obj[0] = dao.queryForlist1("userManagement.userView_1", id)
						.size() == 0 ? null : ((List) dao.queryForlist1(
						"userManagement.userView_1", id)).get(0);
				obj[1] = dao.queryForlist1(
						"userManagement.salesAssistantAddBefore_12", id);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}

}
