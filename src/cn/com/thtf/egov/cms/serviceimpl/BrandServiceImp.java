/**
 * ClassName  AreaService
 *
 * History
 * Create User: Administrator
 * Create Date: 2009-12-16
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.serviceimpl;

import java.util.List;

import cn.com.thtf.egov.cms.application.DaoImpl;
import cn.com.thtf.egov.cms.application.IDao;
import cn.com.thtf.egov.cms.application.ListRange;
import cn.com.thtf.egov.cms.application.Page;
import cn.com.thtf.egov.cms.dto.BrandInfoDto;
import cn.com.thtf.egov.cms.iservice.BrandService;

/**
 * @author Administrator
 * 
 */
public class BrandServiceImp implements BrandService {

	@SuppressWarnings("unchecked")
	private IDao dao;

	/**
	 * 
	 * @param dao
	 */
	@SuppressWarnings("unchecked")
	public void setDao(IDao dao) {
		this.dao = dao;
	}

	/**
	 * 
	 * @param dao
	 */

	@SuppressWarnings("unchecked")
	public void setDao(DaoImpl dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.BrandService#querybrandAll(cn.com.thtf.
	 * egov.cms.application.Page)
	 */
	@SuppressWarnings("unchecked")
	public ListRange querybrandAll(Page page) throws Exception {
		ListRange list = (ListRange) dao.queryRecordsByPage("brand.brandAll",
				null, page);
		// TODO Auto-generated method stub
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.BrandService#addBrand(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public void addBrand(String name) throws Exception {
		dao.insert("base_sqlMap.addBrand", name);
		// TODO Auto-generated method stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.thtf.egov.cms.iservice.BrandService#idExit(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public Integer idExit(String name) throws Exception {
		Integer same = (Integer) dao.get("base_sqlMap.brandIsExit", name);
		return same;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.BrandService#showUpdate(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public BrandInfoDto showUpdate(Integer id) throws Exception {
		BrandInfoDto binfo = (BrandInfoDto) dao.get(
				"base_sqlMap.showUpdateBrand", id);
		return binfo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.BrandService#saveBrand(cn.com.thtf.egov
	 * .cms.dto.BrandInfo)
	 */
	public void saveBrand(BrandInfoDto binfo) throws Exception {
		dao.update("base_sqlMap.saveBrand", binfo);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.BrandService#deleteBrand(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public void deleteBrand(Integer id) throws Exception {
		dao.delete("base_sqlMap.deleteBrand", id);
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.thtf.egov.cms.iservice.BrandService#brandAllName()
	 */
	@SuppressWarnings("unchecked")
	public List brandAllName() throws Exception {
		List list = (List) dao.queryRecordsAll("base_sqlMap.brandAllName");
		// TODO Auto-generated method stub
		return list;
	}

}
