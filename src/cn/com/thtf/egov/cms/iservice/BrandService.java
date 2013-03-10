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
import cn.com.thtf.egov.cms.dto.BrandInfoDto;

/**
 * 品牌
 * 
 * @author Administrator
 * 
 */
public interface BrandService {
	/**
	 * 选择所有
	 * 
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ListRange querybrandAll(Page page) throws Exception;

	/**
	 * 品牌添加
	 * 
	 * @param name
	 * @throws Exception
	 */
	public void addBrand(String name) throws Exception;

	/**
	 * 去重复
	 * 
	 * @return
	 * @throws Exception
	 */
	public Integer idExit(String name) throws Exception;

	/**
	 * 显示修改页面
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public BrandInfoDto showUpdate(Integer id) throws Exception;

	/**
	 * 修改
	 * 
	 * @param binfo
	 * @throws Exception
	 */
	public void saveBrand(BrandInfoDto binfo) throws Exception;

	/**
	 * 删除
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void deleteBrand(Integer id) throws Exception;

	/**
	 * 选择所有品牌
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List brandAllName() throws Exception;
}
