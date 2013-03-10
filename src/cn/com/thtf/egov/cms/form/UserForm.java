/**
 * ClassName  UserForm
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-4-1
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.form;

import org.apache.struts.action.ActionForm;

import cn.com.thtf.egov.cms.entity.UserEntity;

/**
 * UserForm
 * 
 * @author Lubo
 */

public class UserForm extends ActionForm {

	/** 自动生成序列化ID */
	private static final long serialVersionUID = 1L;

	/** UserInfo */
	private UserEntity userInfo = new UserEntity();
	/** 新密码 */
	private String newpassword;
	/** 确认密码 */
	private String comformpassword;

	/**
	 * @return the userInfo
	 */
	public UserEntity getUserInfo() {
		return userInfo;
	}

	/**
	 * @param userInfo
	 *            the userInfo to set
	 */
	public void setUserInfo(UserEntity userInfo) {
		this.userInfo = userInfo;
	}

	/**
	 * @return the newpassword
	 */
	public String getNewpassword() {
		return newpassword;
	}

	/**
	 * @param newpassword
	 *            the newpassword to set
	 */
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	/**
	 * @return the comformpassword
	 */
	public String getComformpassword() {
		return comformpassword;
	}

	/**
	 * @param comformpassword
	 *            the comformpassword to set
	 */
	public void setComformpassword(String comformpassword) {
		this.comformpassword = comformpassword;
	}

}
