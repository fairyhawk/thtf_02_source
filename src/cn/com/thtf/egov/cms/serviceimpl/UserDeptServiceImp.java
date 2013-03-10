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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.DaoImpl;
import cn.com.thtf.egov.cms.application.IDao;
import cn.com.thtf.egov.cms.application.ListRange;
import cn.com.thtf.egov.cms.application.Page;
import cn.com.thtf.egov.cms.dto.UserDeptInfoDto;
import cn.com.thtf.egov.cms.iservice.IUserDeptService;

/**
 * @author Administrator
 * 
 */
public class UserDeptServiceImp implements IUserDeptService {

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

	private static final Logger log = LoggerFactory
			.getLogger(AreaServiceImp.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.thtf.egov.cms.iservice.IUserDeptService#queryUserDept()
	 */
	@SuppressWarnings("unchecked")
	public ListRange queryUserDept(Page page) throws Exception {
		ListRange list = (ListRange) dao.queryRecordsByPage(
				"areadept.userDeptAll", null, page);
		// TODO Auto-generated method stub
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.IUserDeptService#addUserDept(cn.com.thtf
	 * .egov.cms.dto.UserDeptInfo)
	 */
	@SuppressWarnings("unchecked")
	public String addUserDept(UserDeptInfoDto uinfo) {
		String ss = null;
		try {
			dao.insert("base_sqlMap.addUserDept", uinfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ss = "添加失败！";
		}
		// TODO Auto-generated method stub
		return ss;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.IUserDeptService#isJudge(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public Integer isJudge(String name) throws Exception {
		Integer same = (Integer) dao.get("base_sqlMap.isExits", name);
		// TODO Auto-generated method stub
		return same;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.IUserDeptService#showUpdateUserDept(java
	 * .lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public UserDeptInfoDto showUpdateUserDept(Integer id) throws Exception {
		UserDeptInfoDto uinfo = (UserDeptInfoDto) dao.get(
				"base_sqlMap.findUserDeptName", id);
		// TODO Auto-generated method stub
		return uinfo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.IUserDeptService#saveUserDept(cn.com.thtf
	 * .egov.cms.dto.UserDeptInfo)
	 */
	public String saveUserDept(UserDeptInfoDto uinfo) {
		String ss = null;
		try {
			dao.update("base_sqlMap.updateUserDept", uinfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ss = "修改失败";
		}
		// TODO Auto-generated method stub
		return ss;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.IUserDeptService#deleteUserDept(java.lang
	 * .Integer)
	 */
	@SuppressWarnings("unchecked")
	public void deleteUserDept(String idStr) throws Exception {
		String[] id = idStr.split(",");

		try {
			dao.getSqlMap().startTransaction();
			dao.getSqlMap().startBatch();
			// beginTransaction();
			for (int i = 0; i < id.length; i++) {
				dao.delete("base_sqlMap.deleteUserDept", Integer
						.parseInt(id[i]));
			}
			dao.getSqlMap().executeBatch();
			dao.getSqlMap().commitTransaction();
			// commitTransaction();
		} catch (Exception e) {
			log.error("insert user error", e);
		} finally {
			dao.getSqlMap().endTransaction();
			// endTransaction();
			// log.error("delete user db error", e);
		}
		// TODO Auto-generated method stub

	}

}
