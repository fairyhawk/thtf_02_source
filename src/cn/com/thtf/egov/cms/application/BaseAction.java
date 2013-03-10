/**
 * ClassName  BaseAction
 *
 * History
 * Create User: Lubo
 * Create Date: 2009-11-18
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.application;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.struts.ActionSupport;

import cn.com.thtf.egov.cms.constant.Constants;

/**
 * BaseAction
 * 
 * @author Lubo
 * 
 */
public abstract class BaseAction extends ActionSupport {

    /** log */
    private static Logger log = LoggerFactory.getLogger(BaseAction.class);

    /** REQUEST_PAGE_OFFSET */
    private static final String REQUEST_PAGE_OFFSET = "pager.offset";

    /** REQUEST_PAGE_SIZE */
    private static final String REQUEST_PAGE_SIZE = "pageSize";

    /** clazz */
    protected Class<? extends BaseAction> clazz = this.getClass();

    /** methods */
    protected Map<String, Method> methods = new HashMap<String, Method>();

    /** types */
    protected Class<?> types[] = { ActionMapping.class, ActionForm.class,
            HttpServletRequest.class, HttpServletResponse.class };

    /**
     * Execute
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        boolean isLogin = isLogin(request);
        if (!isLogin) {
            return new ActionForward("/notLogin.do");
        } else {
            String parameter = mapping.getParameter();
            String method = request.getParameter(parameter);

            if (log.isDebugEnabled()) {
                log.debug("Parameter:" + parameter);
                log.debug("Method:" + method);
            }

            if (StringUtils.isEmpty(method) || StringUtils.equals("execute", method)) {
                return null;
            }
            return dispatchMethod(mapping, form, request, response, method);
        }

    }

    /**
     * isLogin
     * 
     * @param request
     * @return
     */
    private boolean isLogin(HttpServletRequest request) {
        return request.getSession().getAttribute(Constants.USERLOGIN) != null
                || (Constants.WORKPATH + "/login.do").equals(request.getRequestURI())
                || (Constants.WORKPATH + "/userRigester.do").equals(request
                        .getRequestURI())
                || (Constants.WORKPATH + "/sp001.do").equals(request.getRequestURI());
    }

    /**
     * dispatchMethod
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @param name
     * @return
     * @throws Exception
     */
    protected ActionForward dispatchMethod(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response, String name)
            throws Exception {
        Method method = null;
        try {
            method = getMethod(name);
        } catch (Exception e) {
            log.error("DispatchMethod", e);
            return null;
        }
        Object[] args = { mapping, form, request, response };
        return (ActionForward) method.invoke(this, args);
    }

    /**
     * getMethod
     * 
     * @param name
     * @return
     * @throws NoSuchMethodException
     */
    protected Method getMethod(String name) throws NoSuchMethodException {
        synchronized (methods) {
            Method method = (Method) methods.get(name);
            if (method == null) {
                method = clazz.getMethod(name, types);
                methods.put(name, method);
            }
            return (method);
        }
    }

    /**
     * getNewPage
     * 
     * @param request
     * @param para
     * @return
     */
    protected NewPage getNewPage(HttpServletRequest request) {
        return getNewPage(request, null);
    }

    /**
     * getNewPage
     * 
     * @param request
     * @param para
     * @param psize
     * @return
     */
    protected NewPage getNewPage(HttpServletRequest request, String psize) {
        String thisPage = request.getParameter(Constants.NEW_THISPAGE);
        String pageSize = null;

        if (psize != null) {
            pageSize = psize;
        } else {
            pageSize = Constants.DEFAULT_PAGE_SIZE;
        }

        if (StringUtils.isEmpty(thisPage)) {
            thisPage = "1";
        }

        NewPage page = new NewPage();
        page.setThisPage(Integer.parseInt(thisPage));
        page.setPageSize(Integer.parseInt(pageSize));
        return page;
    }

    /**
     * getPage
     * 
     * @param request
     * @return
     */
    protected Page getPage(HttpServletRequest request) {
        return getPage(request, null);
    }

    /**
     * getPage
     * 
     * @param request
     * @param psize
     * @return
     */
    protected Page getPage(HttpServletRequest request, String psize) {
        String offset = request.getParameter(REQUEST_PAGE_OFFSET);
        String pageSize = request.getParameter(REQUEST_PAGE_SIZE);
        String ye = request.getParameter("ye");

        if (psize != null) {
            pageSize = psize;
        } else {
            pageSize = Constants.DEFAULT_PAGE_SIZE;
        }

        if (ye == null || ye.equals("")) {

        } else {
            offset = String
                    .valueOf((Integer.valueOf(ye) - 1) * Integer.valueOf(pageSize));
            // pageSize = "10";

        }

        if (StringUtils.isEmpty(offset)) {
            offset = "0";
        }
        // if (StringUtils.isEmpty(pageSize)) {

        // }

        Page page = new Page(Integer.parseInt(offset), Integer.parseInt(pageSize));
        return page;
    }

    /**
     * 将textarea的内容格式化存入数据库
     * 
     * @param encodeString
     * @return
     */
    public String HtmlEncode(String encodeString) {
        encodeString = encodeString.replace("<", "&lt;");
        encodeString = encodeString.replace(">", "&gt;");
        encodeString = encodeString.replace("   ", "&nbsp;");
        encodeString = encodeString.replace("’", "'");
        encodeString = encodeString.replace("\n", "<br>");
        return encodeString;
    }

    /**
     * 将数据库Textarea的内容格式化输出到页面
     * 
     * @param encodeString
     * @return
     */
    public String HtmlDecode(String encodeString) {
        encodeString = encodeString.replace("&lt;", "<");
        encodeString = encodeString.replace("&gt;", ">");
        encodeString = encodeString.replace("&nbsp;", "   ");
        encodeString = encodeString.replace("'", "’");
        encodeString = encodeString.replace("<br>", "\n");
        return encodeString;
    }
}
