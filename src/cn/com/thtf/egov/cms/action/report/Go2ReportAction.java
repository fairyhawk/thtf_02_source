/**
 * ClassName  Go2ReportAction
 *
 * History
 * Create User: Shiy
 * Create Date: 2010-11-10
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.action.report;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
import org.apache.struts.action.DynaActionForm;

import cn.com.thtf.egov.cms.application.NewBaseAction;

/**
 * 从CMS跳转到report系统用，默认http协议，report路径
 * 
 * @author Shiy
 */

public class Go2ReportAction extends NewBaseAction {

    /**
     * 跳转Action
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward go2rpt(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaActionForm dform = (DynaActionForm) form;
        String url = dform.getString("url").replaceAll("@@", "&");
        StringBuffer sb = new StringBuffer();
        sb.append("http://");
        sb.append(request.getServerName());
        sb.append(":");
        sb.append(request.getServerPort());
        sb.append("/report");
        if (!url.startsWith("/")) {
            sb.append("/");
        }
        sb.append(url);

        return new ActionRedirect(sb.toString());
    }
}
