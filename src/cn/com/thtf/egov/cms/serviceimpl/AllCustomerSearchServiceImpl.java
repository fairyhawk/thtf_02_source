package cn.com.thtf.egov.cms.serviceimpl;

import java.io.Serializable;
import java.util.Map;

import cn.com.thtf.egov.cms.application.IDao;
import cn.com.thtf.egov.cms.application.Page;
import cn.com.thtf.egov.cms.iservice.IAllCustomerSearchService;

public class AllCustomerSearchServiceImpl implements IAllCustomerSearchService {
	@SuppressWarnings("unchecked")
	private IDao dao;

	@SuppressWarnings("unchecked")
	public IDao getDao() {
		return dao;
	}

	@SuppressWarnings("unchecked")
	public void setDao(IDao dao) {
		this.dao = dao;
	}

	@SuppressWarnings("unchecked")
	public Object[] allCustomer(Map map, Page page) {
		Object[] obj = new Object[6];
		try {
			obj[0] = dao.queryRecordsByPage("chen12.getCustomerAll",
					(Serializable) map, page);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}

}
