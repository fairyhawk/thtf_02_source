/**
 * ClassName  IAreaService
 *
 * History
 * Create User: Administrator
 * Create Date: 2009-12-16
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.iservice;

import java.util.List;

import cn.com.thtf.egov.cms.dto.CompanyAddressInfoDto;
import cn.com.thtf.egov.cms.dto.CompanyInfoDto;

/**
 * 公司
 * 
 * @author Administrator
 * 
 */
public interface ICompanyService {
	/**
	 * 选择所有的公司地址
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List queryCompanyAdress() throws Exception;

	/**
	 * 添加公司地址
	 * 
	 * @throws Exception
	 */
	public String addCompanyAddress(CompanyAddressInfoDto cadressInfo);

	/**
	 * 显示修改页面
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public CompanyAddressInfoDto showUpdate(Integer id) throws Exception;

	/**
	 * 修改
	 * 
	 * @param cainfo
	 * @throws Exception
	 */
	public String saveUpdate(CompanyAddressInfoDto cainfo);

	/**
	 * 公司添加
	 * 
	 * @throws Exception
	 */
	public void saveCompany(CompanyInfoDto cinfo) throws Exception;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public CompanyInfoDto companyAll(Integer id) throws Exception;

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public Integer companyId() throws Exception;

	/**
	 * 修改
	 * 
	 * @param cinfo
	 * @throws Exception
	 */
	public void updateCompany(CompanyInfoDto cinfo) throws Exception;

	/**
	 * 删除
	 * 
	 * @throws Exception
	 */
	public void deleteAddress(Integer id) throws Exception;

}
