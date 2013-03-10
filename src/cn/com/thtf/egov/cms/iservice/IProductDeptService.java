/**
 * ClassName  IProductDeptService
 *
 * History
 * Create User: Administrator
 * Create Date: 2009-12-16
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.iservice;

import java.util.List;

import cn.com.thtf.egov.cms.application.ListRange;
import cn.com.thtf.egov.cms.application.Page;
import cn.com.thtf.egov.cms.dto.ProductDeptInfoDto;
import cn.com.thtf.egov.cms.dto.UserDeptInfoDto;

/**
 * IProductDeptService
 * 
 * @author Administrator
 * 
 */
public interface IProductDeptService {

	/**
	 * 产品部门列表
	 * 
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public ListRange<ProductDeptInfoDto> queryProductDept(Page page)
			throws Exception;

	/**
	 * queryProductDept1
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<UserDeptInfoDto> queryProductDept1() throws Exception;

	/**
	 * addProductDept
	 * 
	 * @param pinfo
	 * @return
	 */
	public String addProductDept(ProductDeptInfoDto pinfo);

	/**
	 * showUpdateProductDept
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ProductDeptInfoDto showUpdateProductDept(Integer id)
			throws Exception;

	/**
	 * saveProductDept
	 * 
	 * @param pinfo
	 * @return
	 */
	public String saveProductDept(ProductDeptInfoDto pinfo);

	/**
	 * deleteProductDept
	 * 
	 * @param id
	 * @throws Exception
	 */
	public String deleteProductDept(String idStr) throws Exception;

	/**
	 * isExit
	 * 
	 * @param pinfo
	 * @return
	 * @throws Exception
	 */
	public Integer isExit(ProductDeptInfoDto pinfo) throws Exception;

	/**
	 * queryProductDeptName
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<ProductDeptInfoDto> queryProductDeptName() throws Exception;

	/**
	 * getProductDeptByProductTypeId
	 * 
	 * @param productTypeId
	 * @return
	 * @throws Exception
	 */
	public ProductDeptInfoDto getProductDeptByProductTypeId(
			Integer productTypeId) throws Exception;

}
