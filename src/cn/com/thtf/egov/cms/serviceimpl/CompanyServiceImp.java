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
import cn.com.thtf.egov.cms.dto.CompanyAddressInfoDto;
import cn.com.thtf.egov.cms.dto.CompanyInfoDto;
import cn.com.thtf.egov.cms.iservice.ICompanyService;

/**
 * @author Administrator
 * 
 */
public class CompanyServiceImp implements ICompanyService {

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
	 * @see cn.com.thtf.egov.cms.iservice.ICompanyService#queryCompanyAdress()
	 */
	@SuppressWarnings("unchecked")
	public List queryCompanyAdress() throws Exception {
		List list = dao.queryRecordsAll("base_sqlMap.companyAddressAll");
		// TODO Auto-generated method stub
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.ICompanyService#addCompanyAddress(cn.com
	 * .thtf.egov.cms.dto.CompanyAddressInfo)
	 */
	@SuppressWarnings("unchecked")
	public String addCompanyAddress(CompanyAddressInfoDto cadressInfo) {
		String result = null;
		try {
			dao.insert("base_sqlMap.addCompanyAdress", cadressInfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result = "添加失败！";
		}
		// TODO Auto-generated method stub
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.ICompanyService#showUpdate(java.lang.Integer
	 * )
	 */
	@SuppressWarnings("unchecked")
	public CompanyAddressInfoDto showUpdate(Integer id) throws Exception {
		CompanyAddressInfoDto asinfo = (CompanyAddressInfoDto) dao.get(
				"base_sqlMap.updateCompanyAddress", id);
		// TODO Auto-generated method stub
		return asinfo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.ICompanyService#saveUpdate(cn.com.thtf.
	 * egov.cms.dto.CompanyAddressInfo)
	 */
	public String saveUpdate(CompanyAddressInfoDto cainfo) {
		String result = null;
		try {
			dao.update("base_sqlMap.saveUpdateAddress", cainfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result = "修改失败！";
		}
		// TODO Auto-generated method stub
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.ICompanyService#saveCompany(cn.com.thtf
	 * .egov.cms.dto.CompanyInfo)
	 */
	@SuppressWarnings("unchecked")
	public void saveCompany(CompanyInfoDto cinfo) throws Exception {
		dao.insert("base_sqlMap.addCompany", cinfo);
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.ICompanyService#companyAll(java.lang.Integer
	 * )
	 */
	@SuppressWarnings("unchecked")
	public CompanyInfoDto companyAll(Integer id) throws Exception {
		CompanyInfoDto cinfo = (CompanyInfoDto) dao.get(
				"base_sqlMap.companymsg", id);
		// TODO Auto-generated method stub
		return cinfo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.thtf.egov.cms.iservice.ICompanyService#companyId()
	 */
	@SuppressWarnings("unchecked")
	public Integer companyId() throws Exception {
		Integer id = (Integer) dao.get("base_sqlMap.companyId", null);
		// TODO Auto-generated method stub
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.ICompanyService#updateCompany(cn.com.thtf
	 * .egov.cms.dto.CompanyInfo)
	 */
	public void updateCompany(CompanyInfoDto cinfo) throws Exception {
		dao.update("base_sqlMap.saveUpdateCompany", cinfo);
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.thtf.egov.cms.iservice.ICompanyService#deleteAddress()
	 */
	@SuppressWarnings("unchecked")
	public void deleteAddress(Integer id) throws Exception {
		dao.delete("base_sqlMap.deleteAddress", id);
		// TODO Auto-generated method stub

	}

}
