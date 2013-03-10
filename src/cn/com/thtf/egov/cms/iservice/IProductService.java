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

import cn.com.thtf.egov.cms.application.ListRange;
import cn.com.thtf.egov.cms.application.Page;
import cn.com.thtf.egov.cms.dto.ProductInfoDto;

/**
 * @author Administrator 产品
 * 
 */
public interface IProductService {
	/**
	 * 查询所有
	 * 
	 * @param page
	 * @param pinfo
	 * @return
	 * @throws Exception
	 */

	@SuppressWarnings("unchecked")
	public ListRange queryProductAll(Page page, ProductInfoDto pinfo)
			throws Exception;

	/**
	 * 自动生成
	 * 
	 * @return
	 * @throws Exception
	 */
	public String autoProductNO(ProductInfoDto info) throws Exception;

	/**
	 * 添加产品信息
	 * 
	 * @param pinfo
	 * @throws Exception
	 */
	public String addProduct(ProductInfoDto pinfo);

	/**
	 * 显示修改页面
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ProductInfoDto showUpdate(Integer id) throws Exception;

	/**
	 * 修改
	 * 
	 * @param pinfo
	 */
	public String updateProduct(ProductInfoDto pinfo);

	/**
	 * 检索
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ListRange checkProduct(ProductInfoDto pinfo, Page page)
			throws Exception;

	/**
	 * 删除
	 * 
	 * @throws Exception
	 */
	public String deleteProduct(String idStr) throws Exception;

	/**
	 * 去重
	 * 
	 * @return
	 * @throws Exception
	 */
	public Integer isExit(ProductInfoDto pinfo) throws Exception;

	public Integer isExit1(ProductInfoDto pinfo) throws Exception;

	/**
	 * 根据产品的id(pk)查询产品
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ProductInfoDto getProductById(Integer id) throws Exception;

}
