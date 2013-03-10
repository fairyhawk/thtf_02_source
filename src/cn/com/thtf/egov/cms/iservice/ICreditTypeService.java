package cn.com.thtf.egov.cms.iservice;

import cn.com.thtf.egov.cms.application.ListRange;
import cn.com.thtf.egov.cms.application.Page;

/**
 * @author sxf
 * 
 */
public interface ICreditTypeService {
	/**
	 * 信用类型
	 * 
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ListRange queryCreditTypeAll(Page page) throws Exception;

}
