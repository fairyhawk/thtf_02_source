/**
 * ClassName  DemandAction
 *
 * History
 * Create User: ChenHuajiang
 * Create Date: 2010-6-8
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.action.demand;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.NewBaseAction;
import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.constant.Constants;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.iservice.demand.IDemandService;

/**
 * 需求管理
 * 
 * @author ChenHuajiang
 */

public class DemandAction extends NewBaseAction {
    private static Logger log = LoggerFactory.getLogger(DemandAction.class);
    private IDemandService demandService = null;

    /**
     * 需求管理列表
     * 
     * @author ChenHuajiang
     */
    @SuppressWarnings("unchecked")
    public ActionForward getDemandList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        demandService = (IDemandService) getBean("demandServiceImpl");
        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},需求管理列表【开始】", user.getId());
        // 产品编码
        String prodCode = request.getParameter("prodCode");
        // 产品名称
        String prodName = request.getParameter("prodName");
        // 规格型号
        String prodType = request.getParameter("prodType");

        if (StringUtils.isNotEmpty(prodCode)) {
            prodCode = prodCode.trim();
        }
        if (StringUtils.isNotEmpty(prodName)) {
            prodName = prodName.trim();
        }
        if (StringUtils.isNotEmpty(prodType)) {
            prodType = prodType.trim();
        }

        // 分页
        NewPage page = getNewPage(request);
        page.setUrl("getDemandList.do");
        page.setQuery("prodCode", prodCode);
        page.setQuery("prodName", prodName);
        page.setQuery("prodType", prodType);

        HashMap map = new HashMap();
        map.put("prodCode", prodCode);
        map.put("prodName", prodName);
        map.put("prodType", prodType);
        map.put("userId", user.getId());
        map.put("roleId", user.getRoleId());

        if (user.getRoleId() == Constants.ROLE_PRODUCT_DIRECTOR
                || user.getRoleId() == Constants.ROLE_PROCUREMENT_OFFICER
                || user.getRoleId() == Constants.ROLE_SALES_DIRECTOR) {
            map.put("roleId", "51011");
        } else if(user.getRoleId()==Constants.ROLE_AREA_MANAGER
        		|| user.getRoleId()==Constants.ROLE_BIGAREA_MANAGER){//区域经理、大区经理
        	map.put("roleId", "1920");
        }else {
            map.put("roleId", user.getRoleId());
        }

        List list = null;
        list = demandService.getDemandProducts(map, page);

        request.setAttribute("NewPage", page);
        request.setAttribute("prodCode", prodCode);
        request.setAttribute("prodName", prodName);
        request.setAttribute("prodType", prodType);
        request.setAttribute("showColm", setUserAction(user));

        request.setAttribute("demandList", list);
        log.info("User:{},需求管理列表【结束】", user.getId());
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 需求管理列表明细
     * 
     * @author ChenHuajiang
     */
    @SuppressWarnings("unchecked")
    public ActionForward getDemandDetailList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},需求管理列表明细【开始】", user.getId());

        demandService = (IDemandService) getBean("demandServiceImpl");

        // 产品ID
        String prodId = request.getParameter("prodId");

        // 分页
        NewPage page = getNewPage(request);
        page.setUrl("getDemandDetailList.do");
        page.setQuery("prodId", prodId);

        HashMap map = new HashMap();
        map.put("userId", user.getId());
        if (user.getRoleId() == Constants.ROLE_PRODUCT_DIRECTOR
                || user.getRoleId() == Constants.ROLE_PROCUREMENT_OFFICER
                || user.getRoleId() == Constants.ROLE_SALES_DIRECTOR) {
            map.put("roleId", "51011");
        } else if(user.getRoleId()==Constants.ROLE_AREA_MANAGER
        		|| user.getRoleId()==Constants.ROLE_BIGAREA_MANAGER){//区域经理、大区经理
        	map.put("roleId", "1920");
        }else {
            map.put("roleId", user.getRoleId());
        }
        map.put("prodId", prodId);

        List list = null;
        list = demandService.getDemandProductsDetail(map, page);

        request.setAttribute("NewPage", page);
        request.setAttribute("showColm", setUserAction(user));

        request.setAttribute("demandDetailList", list);
        log.info("User:{},需求管理列表明细【结束】", user.getId());
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 修改需求单状态
     * 
     * @author ChenHuajiang
     */
    @SuppressWarnings("unchecked")
    public ActionForward modifyDemandById(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        demandService = (IDemandService) getBean("demandServiceImpl");
        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},修改需求单状态【开始】", user.getId());
        // 需求单ID
        String demandId = request.getParameter("demandId");
        // 产品ID
        String prodId = request.getParameter("prodId");

        HashMap map = new HashMap();
        map.put("userId", user.getId());
        map.put("userName", user.getName());
        map.put("demandId", demandId);

        int intCnt = 0;
        intCnt = demandService.modifyDemandById(map);
        request.setAttribute("showColm", setUserAction(user));
        if (intCnt == 0) {
            log.info("User:{},修改需求单状态【失败】", user.getId());
            request.setAttribute("msg", "操作失败，请重新确认");
        }
        log.info("User:{},修改需求单状态【结束】", user.getId());
        StringBuffer failUrl = new StringBuffer();
        failUrl.append("/getDemandDetailList.do?prodId=");
        failUrl.append(prodId);
        return new ActionForward(failUrl.toString());
    }

    /**
     * 设置用户的操作权限
     * 
     * @author ChenHuajiang
     * @param user
     * @return 可以操作的功能
     */
    private String setUserAction(UserEntity user) {
        log.info("设置用户的操作权限");
        if (user == null) {
            return null;
        }

        Integer roleId = user.getRoleId();
        // 产品总监
        if (roleId == Constants.ROLE_PRODUCT_DIRECTOR) {
            return "showNum";
        }

        // 区域总监
        if (roleId == Constants.ROLE_REGIONAL_DIRECTOR) {
            return "showArea";
        }
        // 采购主管 运营总监助理 运营总监 总经理
        if (roleId == Constants.ROLE_PROCUREMENT_OFFICER
                || roleId == Constants.ROLE_AST_DIRECTOR_OF_OPERATIONS
                || roleId == Constants.ROLE_DIRECTOR_OF_OPERATIONS
                || roleId == Constants.ROLE_GENERAL_MANAGER
                || roleId == Constants.ROLE_SALES_DIRECTOR
        		|| roleId == Constants.ROLE_AREA_MANAGER
                || roleId == Constants.ROLE_BIGAREA_MANAGER) {
            return "showAll";
        }
        return null;
    }
}
