package cn.com.thtf.egov.cms.iservice;

import java.util.Map;

import cn.com.thtf.egov.cms.application.Page;

public interface IAllCustomerSearchService {
	/**
	 * 
	 * @param 
	 * @param 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Object[] allCustomer(Map map, Page page);
}
