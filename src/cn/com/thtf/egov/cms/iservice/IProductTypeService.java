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

import cn.com.thtf.egov.cms.application.ListRange;
import cn.com.thtf.egov.cms.application.Page;
import cn.com.thtf.egov.cms.dto.ProductTypeInfoDto;

/**
 * @author Administrator 产品分类
 */
public interface IProductTypeService {
	/**
	 * 产品分类列表
	 * 
	 * @return
	 * @throws Exception
	 */

	@SuppressWarnings("unchecked")
	public ListRange queryProductType(Page page) throws Exception;

	/**
	 * 添加
	 * 
	 * @throws Exception
	 */
	public String addProductType(ProductTypeInfoDto ptinfo);

	/**
	 * 显示
	 * 
	 * @return
	 * @throws Exception
	 */
	public ProductTypeInfoDto findTypeByID(Integer id) throws Exception;

	/**
	 * 修改
	 * 
	 * @param pinfo
	 * @throws Exception
	 */
	public String updateProductType(ProductTypeInfoDto pinfo);

	/**
	 * 去重
	 * 
	 * @param pinfo
	 * @return
	 * @throws Exception
	 */
	public Integer isExit(ProductTypeInfoDto pinfo) throws Exception;

	/**
	 * 删除
	 * 
	 * @param id
	 * @throws Exception
	 */
	public String deleteProductType(String idStr);

	/**
	 * 选择所有产品名称
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List fingProductTypeAll() throws Exception;

	/**
	 * 用于销售
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List fingProductTypeAll1(String userid) throws Exception;

	/**
	 * 用于销售合同
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List fingProductTypeAll12(ProductTypeInfoDto pt) throws Exception;

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String findNoById(String id) throws Exception;

	/**
	 * 查询产品分类
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List productTypeAll() throws Exception;

	public String maxProductTypeNo() throws Exception;

}
