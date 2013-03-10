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

import cn.com.thtf.egov.cms.entity.UserEntity;

/**
 * 个人信息
 * 
 * @author Administrator
 * 
 */
public interface IUserService {

    /**
     * 修改用户登录状态为离线
     * 
     * @return
     */
    public boolean modifyUserOnline();

    /**
     * 显示个人信息修改
     * 
     * @param id
     * @return @
     */
    public UserEntity getUserById(String id);

    /**
     * 修改
     * 
     * @param uinfo
     *            @
     */
    public void updateUser(UserEntity uinfo);

    /**
     * 修改在线状态
     * 
     * @param uinfo
     *            @
     */
    public void updateUserStatus(UserEntity uinfo);

    /**
     * 修改在线时间
     * 
     * @param uinfo
     *            @
     */
    public void updateUserDatetime(UserEntity uinfo);

    // /**
    // * 根据登录用户,检索左侧的目录树
    // *
    // * @param uinfo
    // * @return @
    // */
    // public Object[] getLeftTree(UserInfo uinfo);

}
