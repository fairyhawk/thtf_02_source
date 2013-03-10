package cn.com.thtf.egov.cms.serviceimpl;

import cn.com.thtf.egov.cms.application.IDao;
import cn.com.thtf.egov.cms.iservice.ITreasuryManagerAddBeforeService;

public class TreasuryManagerAddBeforeServiceImpl implements
		ITreasuryManagerAddBeforeService {
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
	 * @seecn.com.thtf.egov.cms.iservice.ITreasuryManagerAddBeforeService#
	 * queryTreasuryProduct()
	 */
	public Object[] queryTreasuryProduct() {
		// TODO Auto-generated method stub
		Object[] obj = new Object[6];
		try {
			obj[0] = dao
					.queryRecordsAll("userManagement.salesAssistantAddBefore_7");
			obj[1] = dao
					.queryRecordsAll("userManagement.salesAssistantAddBefore_2");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
}
