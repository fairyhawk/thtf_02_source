/**
 * ClassName  TodoWorkService
 *
 * History
 * Create User: Shiy
 * Create Date: 2010-4-29
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.serviceimpl.work;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.BaseService;
import cn.com.thtf.egov.cms.application.NewIDao;
import cn.com.thtf.egov.cms.dto.TodoWorkDto;
import cn.com.thtf.egov.cms.entity.WorkEntity;
import cn.com.thtf.egov.cms.iservice.work.ITodoWorkService;

/**
 * 
 * @author Shiy
 */

public class TodoWorkService extends BaseService implements ITodoWorkService {

    /** log */
    private static Logger log = LoggerFactory.getLogger(TodoWorkService.class);
    /** dao */
    private NewIDao dao = null;

    /**
     * @param dao
     *            the dao to set
     */
    public void setDao(NewIDao dao) {
        this.dao = dao;
    }

    /**
     * @see cn.com.thtf.egov.cms.iservice.work.ITodoWorkService#getTodoWorkCount()
     */
    @Override
    public int getTodoWorkCount(String userid) {
        int cnt = 0;

        try {
            cnt = (Integer) dao.queryForObject("todowork.getTodoWorkCount", userid);
        } catch (Exception e) {
            // log.error("getTodoWorkCount error", e);
            // 无待办事务时也会报错，所以屏蔽过多的错误log
        }

        return cnt;
    }

    /**
     * @see cn.com.thtf.egov.cms.iservice.work.ITodoWorkService#getTodoWorks()
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<TodoWorkDto> getTodoWorks(String userid) {
        List<TodoWorkDto> list = null;
        try {
            list = dao.queryForlist("todowork.getTodoWorkList", userid);
        } catch (Exception e) {
            log.error("getTodoWorks error", e);
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.work.ITodoWorkService#addWorkCount(cn.com
     * .thtf.egov.cms.entity.WorkEntity)
     */
    public boolean addTodoWorks(WorkEntity work) {
        log.info("更新待办事务,User:{},Count:{}", work.getUserId(), work.getCount());
        Integer count = 0;
        try {
            count = (Integer) dao.queryForObject("todowork.selectWorkCount", work);

            if (count == null) {
                dao.insert("todowork.insertWork", work);
            } else {
                dao.update("todowork.updateWork", work);
            }

            return true;
        } catch (Exception e) {
            log.error("添加代办事物件数失败", e);
            return false;
        }

    }
}
