/**
 * ClassName  AreaAction
 *
 * History
 * Create User: Administrator
 * Create Date: 2009-12-16
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.BaseAction;
import cn.com.thtf.egov.cms.application.ListRange;
import cn.com.thtf.egov.cms.dto.AreaInfoDto;
import cn.com.thtf.egov.cms.iservice.IAreaService;

/**
 * @author Administrator
 * 
 */

public class AreaAction extends BaseAction {

    private static final Logger log = LoggerFactory.getLogger(AreaAction.class);

    private IAreaService iarea;

    /**
     * 人员区域名称显示列表
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public ActionForward areaAll(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        // DynaActionForm dform = (DynaActionForm) form;
        iarea = (IAreaService) getWebApplicationContext().getBean("areaServiceImp");
        ListRange list = iarea.queryArea(getPage(request));
        request.setAttribute("Page", list.getPage());
        request.setAttribute("arealist", list);
        return mapping.findForward("areaAll");
    }

    /**
     * 显示修改页面
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward show(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaActionForm dform = (DynaActionForm) form;
        AreaInfoDto areainfo = (AreaInfoDto) dform.get("area");
        iarea = (IAreaService) getWebApplicationContext().getBean("areaServiceImp");
        saveToken(request);
        if (areainfo.getId() == null) {
            areainfo = (AreaInfoDto) iarea.findName(Integer.parseInt(request
                    .getParameter("id")));
        } else {
            areainfo = (AreaInfoDto) iarea.findName(areainfo.getId());
        }

        request.setAttribute("areaname", areainfo);
        return mapping.findForward("show");
    }

    /**
     * 修改
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward saveArea(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaActionForm dform = (DynaActionForm) form;
        AreaInfoDto areainfo = (AreaInfoDto) dform.get("area");
        iarea = (IAreaService) getWebApplicationContext().getBean("areaServiceImp");
        /* 防止重复提交 */
        if (isTokenValid(request)) {
            resetToken(request);
        } else {
            log.debug("重复提交");
            return areaAll(mapping, form, request, response);
        }
        String result = iarea.saveUpdate(areainfo);
        if (result != null) {
            log.debug("名称重复或更新错误");
            request.setAttribute("err", result);
            // return show(mapping, form, request, response);
            request.setAttribute("areaname", areainfo);
            saveToken(request);
            return mapping.findForward("show");
        } else {
            return mapping.findForward("savesuccess");
        }
    }

    /**
     * 显示添加
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward showAddArea(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        // DynaActionForm dform = (DynaActionForm) form;
        iarea = (IAreaService) getWebApplicationContext().getBean("areaServiceImp");
        saveToken(request);
        return mapping.findForward("showadd");
    }

    /**
     * 添加
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addArea(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaActionForm dform = (DynaActionForm) form;
        AreaInfoDto areainfo = (AreaInfoDto) dform.get("area");
        iarea = (IAreaService) getWebApplicationContext().getBean("areaServiceImp");
        /* 防止重复提交 */
        if (isTokenValid(request)) {
            resetToken(request);
        } else {
            return areaAll(mapping, form, request, response);
        }

        String result = iarea.addArea(areainfo);
        if (result != null) {
            request.setAttribute("err", result);
            return showAddArea(mapping, form, request, response);
            // return mapping.findForward("showadd");
        } else {
            return mapping.findForward("addsuccess");
        }
    }

    /**
     * 
     * 删除
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteArea(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        iarea = (IAreaService) getWebApplicationContext().getBean("areaServiceImp");
        String areaid = request.getParameter("id");
        // String[] id = areaid.split(",");
        // for (int i = 0; i < id.length; i++) {
        // iarea.deleteArea(Integer.parseInt(id[i]));
        // }
        iarea.deleteArea(areaid);
        return mapping.findForward("deletesuccess");
    }
}
