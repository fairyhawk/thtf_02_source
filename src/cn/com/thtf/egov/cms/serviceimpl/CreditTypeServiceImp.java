package cn.com.thtf.egov.cms.serviceimpl;

import cn.com.thtf.egov.cms.application.IDao;
import cn.com.thtf.egov.cms.application.ListRange;
import cn.com.thtf.egov.cms.application.Page;
import cn.com.thtf.egov.cms.iservice.ICreditTypeService;

/**
 *信用类型
 * 
 * @author sxf
 * 
 */
public class CreditTypeServiceImp implements ICreditTypeService {
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
	 * cn.com.thtf.egov.cms.iservice.ICreditTypeService#queryDepotAll(cn.com
	 * .thtf.egov.cms.application.Page)
	 */
	@SuppressWarnings("unchecked")
	public ListRange queryCreditTypeAll(Page page) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
