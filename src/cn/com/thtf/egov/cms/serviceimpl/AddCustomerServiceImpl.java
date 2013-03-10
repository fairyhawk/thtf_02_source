package cn.com.thtf.egov.cms.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.thtf.egov.cms.application.IDao;
import cn.com.thtf.egov.cms.iservice.IAddCustomerService;

public class AddCustomerServiceImpl implements IAddCustomerService {
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

	/**
	 * 
	 * @param salesDto
	 * @param page
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public void addCustomer(Map map) {
		try {
			String[] Customers = map.get("obj").toString().split(",");
			String id = map.get("id").toString();
			for (String Customer : Customers) {
				Map<String, Object> map1 = new HashMap<String, Object>();
				map1.put("user_id", id);
				map1.put("customer_id", Customer);
				List list = dao.queryForlist1("userManagement.userChange_6",
						map1);
				if (list.size() == 0) {
					dao.insert1("userManagement.userChange_7", map1);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
