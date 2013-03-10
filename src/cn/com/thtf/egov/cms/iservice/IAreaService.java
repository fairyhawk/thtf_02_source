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
import cn.com.thtf.egov.cms.dto.AreaInfoDto;

/**
 * 人员区域
 * 
 * @author Administrator
 * 
 */
public interface IAreaService {
	/**
	 * 
	 * 
	 *显示列表
	 */
	@SuppressWarnings("unchecked")
	public ListRange queryArea(Page page) throws Exception;

	@SuppressWarnings("unchecked")
	public List queryArea1() throws Exception;

	/**
	 * 显示修改
	 */
	public AreaInfoDto findName(Integer id) throws Exception;

	/**
	 * 保存修改
	 * 
	 * @param ainfo
	 * @throws Exception
	 */
	public String saveUpdate(AreaInfoDto ainfo);

	/**
	 * 添加
	 * 
	 * @param ainfo
	 * @throws Exception
	 */
	public String addArea(AreaInfoDto ainfo) throws Exception;

	/**
	 * 去掉重复
	 * 
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public Integer idExit(String name) throws Exception;

	/**
	 * 删除
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void deleteArea(String areaid) throws Exception;

}
