/**
 * ClassName  ICreateTreeService
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-4-7
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.iservice;

/**
 * ICreateTreeService
 * 
 * @author Lubo
 */

public interface ICreateTreeService {

	/**
	 * 根据roleId创建后台系统左侧目录
	 * 
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public String getLeftTree(Integer roleId) throws Exception;

}
