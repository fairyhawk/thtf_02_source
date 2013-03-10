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

import cn.com.thtf.egov.cms.application.NewIDao;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.iservice.IUserService;

/**
 * @author Administrator
 * 
 */
public class UserServiceImp implements IUserService {

    /** log */
    private static Logger log = LoggerFactory.getLogger(UserServiceImp.class);

    /** NewIDao */
    private NewIDao dao;

    public UserServiceImp() {
    }

    public UserServiceImp(NewIDao dao) {
        this.dao = dao;
    }

    /**
     * 修改用户登录状态为离线
     * 
     * @return
     */
    public boolean modifyUserOnline() {
        try {
            dao.update("base_sqlMap.updateUserOnline", null);
            return true;
        } catch (Exception e) {
            log.error("修改用户登录状态为离线错误", e);
            return false;
        }
    }

    /**
     * 按id（登录名）取得userinfo
     * 
     * @see cn.com.thtf.egov.cms.iservice.IUserService#getUserById(java.lang.String)
     */
    public UserEntity getUserById(String id) {
        UserEntity uinfo = null;
        try {
            uinfo = (UserEntity) dao.queryForObject("base_sqlMap.userMsg", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uinfo;
    }

    /**
     * @see cn.com.thtf.egov.cms.iservice.IUserService#updateUser(cn.com.thtf.egov
     *      .cms.dto.UserEntity)
     */
    public void updateUser(UserEntity uinfo) {
        try {
            dao.update("base_sqlMap.updateUser", uinfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @see cn.com.thtf.egov.cms.iservice.IUserService#updateUser(cn.com.thtf.egov
     *      .cms.dto.UserEntity)
     */
    @Override
    public void updateUserDatetime(UserEntity uinfo) {
        try {
            dao.update("base_sqlMap.updateUserDatetime", uinfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * @see
     * cn.com.thtf.egov.cms.iservice.IUserService#updateUserStatus(cn.com.thtf
     * .egov.cms.entity.UserInfo)
     */
    @Override
    public void updateUserStatus(UserEntity uinfo) {
        try {
            dao.update("base_sqlMap.updateUserStatus", uinfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the dao
     */
    public NewIDao getDao() {
        return dao;
    }

    /**
     * @param dao
     *            the dao to set
     */
    public void setDao(NewIDao dao) {
        this.dao = dao;
    }

}