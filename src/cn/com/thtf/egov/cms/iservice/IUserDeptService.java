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
import cn.com.thtf.egov.cms.dto.UserDeptInfoDto;

/**
 * 人员部门
 * 
 * @author Administrator
 */
public interface IUserDeptService {
	/**
	 * 人员部门列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ListRange queryUserDept(Page page) throws Exception;

	/**
	 * 添加
	 * 
	 * @throws Exception
	 */
	public String addUserDept(UserDeptInfoDto uinfo);

	/**
	 * 去掉重复
	 * 
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public Integer isJudge(String name) throws Exception;

	/**
	 * 显示
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public UserDeptInfoDto showUpdateUserDept(Integer id) throws Exception;

	/**
	 * 修改
	 * 
	 * @param uinfo
	 * @throws Exception
	 */
	public String saveUserDept(UserDeptInfoDto uinfo);

	/**
	 *删除
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void deleteUserDept(String idStr) throws Exception;
}
