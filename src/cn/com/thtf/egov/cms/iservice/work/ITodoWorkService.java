/**
 * ClassName  ITodoWorkService
 *
 * History
 * Create User: Shiy
 * Create Date: 2010-4-29
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.iservice.work;

import java.util.List;
import cn.com.thtf.egov.cms.dto.TodoWorkDto;
import cn.com.thtf.egov.cms.entity.WorkEntity;

/**
 * 待办事务Service
 * 
 * @author Shiy
 */

public interface ITodoWorkService {

    /**
     * 根据userid取得该用户的待办事项数量
     * 
     * @param userid
     * @return
     */
    public int getTodoWorkCount(String userid);

    /**
     * 根据userid取得该用户的待办事项
     * 
     * @param userid
     * @return
     */
    public List<TodoWorkDto> getTodoWorks(String userid);

    /**
     * 新增代办事物
     * 
     * @param work
     * @return
     */
    public boolean addTodoWorks(WorkEntity work);

}
