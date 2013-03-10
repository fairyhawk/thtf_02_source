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

import cn.com.thtf.egov.cms.application.BaseAction;
import cn.com.thtf.egov.cms.application.ListRange;
import cn.com.thtf.egov.cms.dto.BrandInfoDto;
import cn.com.thtf.egov.cms.iservice.BrandService;

/**
 * @author Administrator
 * 
 */

public class BrandAction extends BaseAction {

	private BrandService bservice;

	/**
	 * 显示列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */

	@SuppressWarnings("unchecked")
	public ActionForward brandAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// DynaActionForm dform = (DynaActionForm) form;
		bservice = (BrandService) getWebApplicationContext().getBean(
				"brandServiceImp");
		ListRange list = bservice.querybrandAll(getPage(request));
		request.setAttribute("Page", list.getPage());
		request.setAttribute("brandlist", list);
		return mapping.findForward("brandAll");
	}

	/**
	 * 显示添加页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showBrandAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// DynaActionForm dform = (DynaActionForm) form;
		bservice = (BrandService) getWebApplicationContext().getBean(
				"brandServiceImp");
		saveToken(request);
		return mapping.findForward("showBrandAdd");
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
	public ActionForward brandAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm dform = (DynaActionForm) form;
		BrandInfoDto binfo = (BrandInfoDto) dform.get("brand");
		bservice = (BrandService) getWebApplicationContext().getBean(
				"brandServiceImp");
		if (isTokenValid(request)) {
			resetToken(request);
		} else {
			return brandAll(mapping, form, request, response);
		}
		Integer same = bservice.idExit(binfo.getName());
		if (same != 0) {
			request.setAttribute("err", "添加失败！");
			return showBrandAdd(mapping, form, request, response);
		} else {
			bservice.addBrand(binfo.getName());
		}
		return mapping.findForward("brandAdd");
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
	public ActionForward showBrandUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaActionForm dform = (DynaActionForm) form;
		BrandInfoDto binfo = (BrandInfoDto) dform.get("brand");
		bservice = (BrandService) getWebApplicationContext().getBean(
				"brandServiceImp");
		saveToken(request);
		if (binfo.getId() == null) {
			binfo = bservice.showUpdate(Integer.parseInt(request
					.getParameter("id")));
		} else {
			binfo = bservice.showUpdate(binfo.getId());
		}
		request.setAttribute("binfo", binfo);
		return mapping.findForward("showBrandUpdate");
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
	public ActionForward saveBrandUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaActionForm dform = (DynaActionForm) form;
		BrandInfoDto binfo = (BrandInfoDto) dform.get("brand");
		bservice = (BrandService) getWebApplicationContext().getBean(
				"brandServiceImp");
		if (isTokenValid(request)) {
			resetToken(request);
		} else {
			return brandAll(mapping, form, request, response);
		}
		// Integer same = bservice.idExit(binfo.getName());
		// if (same != 0) {
		// request.setAttribute("err", "修改失败！");
		// return showBrandUpdate(mapping, form, request, response);
		// } else {
		bservice.saveBrand(binfo);
		// }
		return mapping.findForward("saveBrandUpdate");
	}

	/**
	 * 删除
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteBrand(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		bservice = (BrandService) getWebApplicationContext().getBean(
				"brandServiceImp");
		String idStr = request.getParameter("id");
		String[] id = idStr.split(",");
		for (int i = 0; i < id.length; i++) {
			bservice.deleteBrand(Integer.parseInt(id[i]));
		}
		return mapping.findForward("deleteBrand");
	}
}
