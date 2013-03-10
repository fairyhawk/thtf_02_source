/**
 * ClassName  TodoWorkAction
 *
 * History
 * Create User: Shiy
 * Create Date: 2010-4-29
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.action.work;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONArray;
import org.json.JSONObject;

import cn.com.thtf.egov.cms.application.NewBaseAction;
import cn.com.thtf.egov.cms.constant.Constants;
import cn.com.thtf.egov.cms.dto.TodoWorkDto;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.iservice.work.ITodoWorkService;

/**
 * 
 * @author Shiy
 */

public class TodoWorkAction extends NewBaseAction {

    private ITodoWorkService todoWorkService = null;

    public ActionForward getTodoWorkCount(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserEntity user = (UserEntity) request.getSession().getAttribute(
                Constants.USERLOGIN);
        todoWorkService = (ITodoWorkService) this.getApplicationContext().getBean(
                "todoWrokServiceImpl");
        int workCount = todoWorkService.getTodoWorkCount(user.getId());
        JSONObject workCntJson = new JSONObject();
        workCntJson.put("count", workCount);
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(workCntJson.toString());
        writer.close();

        return null;
    }

    public ActionForward getTodoWorkList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserEntity user = (UserEntity) request.getSession().getAttribute(
                Constants.USERLOGIN);
        todoWorkService = (ITodoWorkService) this.getApplicationContext().getBean(
                "todoWrokServiceImpl");
        List<TodoWorkDto> list = todoWorkService.getTodoWorks(user.getId());

        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();

        if (list == null || list.size() == 0) {
            writer.write("");
        } else {
            JSONArray todoListJson = new JSONArray();
            for (TodoWorkDto todoWorkDto : list) {
                JSONObject workJson = new JSONObject();
                workJson.put("work_name", todoWorkDto.getWork_name());
                workJson.put("count", todoWorkDto.getCount());
                workJson.put("url", todoWorkDto.getUrl());
                todoListJson.put(workJson);
            }
            writer.write(todoListJson.toString());
        }

        writer.close();

        return null;
    }
}
