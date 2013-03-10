package cn.com.thtf.egov.cms.serviceimpl;

import java.util.HashMap;
import java.util.Map;

import cn.com.thtf.egov.cms.application.IDao;
import cn.com.thtf.egov.cms.iservice.ISalesAssistantAddBeforeCheck1Service;

public class SalesAssistantAddBeforeCheck1ServiceImpl implements
		ISalesAssistantAddBeforeCheck1Service {
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
	 * @seecn.com.thtf.egov.cms.iservice.ISalesAssistantAddBeforeCheck1Service#
	 * checkRegionalProduct1(java.lang.String, java.lang.String)
	 */
	public Object[] checkRegionalProduct1(String regional, String product) {
		Object[] obj = new Object[6];
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_area_id", regional);
		map.put("product_type_id", product);
		try {
			obj[0] = Integer.valueOf(dao.queryForlist1(
					"userManagement.salesAssistantAddBefore_8", map).get(0)
					.toString()) == 0 ? "yes" : "no";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
}
