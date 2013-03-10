package cn.com.thtf.egov.cms.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import cn.com.thtf.egov.cms.application.IDao;
import cn.com.thtf.egov.cms.iservice.ITestService;

public class TestServiceImpl implements ITestService {
	@SuppressWarnings("unchecked")
	private IDao dao;

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
	 * @see cn.com.thtf.egov.cms.iservice.ITestService#test()
	 */
	@SuppressWarnings("unchecked")
	public Object test() {
		List list = new ArrayList();
		try {
			list = dao.queryRecordsAll("test.getall");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
