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
import cn.com.thtf.egov.cms.dto.ProductDeptInfoDto;
import cn.com.thtf.egov.cms.iservice.IProductDeptService;

/**
 * @author Administrator
 * 
 */
public class ProductDeptAction extends BaseAction {

	@SuppressWarnings("unused")
	private static Logger log = LoggerFactory
			.getLogger(ProductDeptAction.class);

	private IProductDeptService ipdept;

	/**
	 * 产品部门列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward productDeptAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ipdept = (IProductDeptService) getWebApplicationContext().getBean(
				"productDeptServiceImp");
		ListRange list = ipdept.queryProductDept(getPage(request));
		request.setAttribute("productdeptlist", list);
		request.setAttribute("Page", list.getPage());
		return mapping.findForward("productDeptAll");
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
	public ActionForward showProductDept(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ipdept = (IProductDeptService) getWebApplicationContext().getBean(
				"productDeptServiceImp");
		saveToken(request);
		return mapping.findForward("showProductDeptAdd");
	}

	/**
	 * 保存添加
	 * 
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveProductDept(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaActionForm dform = (DynaActionForm) form;
		ProductDeptInfoDto pdinfo = (ProductDeptInfoDto) dform
				.get("productdept");
		ipdept = (IProductDeptService) getWebApplicationContext().getBean(
				"productDeptServiceImp");
		/* 防止重复提交 */
		if (isTokenValid(request)) {
			resetToken(request);
		} else {
			return productDeptAll(mapping, form, request, response);
		}
		String result = ipdept.addProductDept(pdinfo);
		if (result != null) {
			request.setAttribute("err", result);
			return showProductDept(mapping, form, request, response);
		} else {
			return this.showProductDept(mapping, form, request, response);
		}
	}

	/**
	 * 显示修改
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showProductDeptUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaActionForm dform = (DynaActionForm) form;
		ProductDeptInfoDto pinfo = (ProductDeptInfoDto) dform
				.get("productdept");
		ipdept = (IProductDeptService) getWebApplicationContext().getBean(
				"productDeptServiceImp");
		saveToken(request);
		if (pinfo.getId() == null) {
			pinfo = ipdept.showUpdateProductDept(Integer.parseInt(request
					.getParameter("id")));
		} else {
			pinfo = ipdept.showUpdateProductDept(pinfo.getId());
		}
		request.setAttribute("productdeptq", pinfo);
		return mapping.findForward("showproductDeptupdate");
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
	public ActionForward saveProductDeptUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaActionForm dform = (DynaActionForm) form;
		ProductDeptInfoDto pinfo = (ProductDeptInfoDto) dform
				.get("productdept");
		ipdept = (IProductDeptService) getWebApplicationContext().getBean(
				"productDeptServiceImp");
		/* 防止重复提交 */
		if (isTokenValid(request)) {
			resetToken(request);
		} else {
			return productDeptAll(mapping, form, request, response);
		}
		pinfo.setId(Integer.parseInt(request.getParameter("ss")));
		String result = ipdept.saveProductDept(pinfo);
		if (result != null) {
			request.setAttribute("err", result);
			return showProductDeptUpdate(mapping, form, request, response);
		} else {
			return mapping.findForward("saveproductDeptupdate");
		}
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
	public ActionForward deleteProductDept(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// DynaActionForm dform = (DynaActionForm) form;
		ipdept = (IProductDeptService) getWebApplicationContext().getBean(
				"productDeptServiceImp");
		String idStr = request.getParameter("id");
		// String[] id = idStr.split(",");
		// try {
		//
		// for (int i = 0; i < id.length; i++) {
		// ipdept.deleteProductDept(Integer.parseInt(id[i]));
		// }
		//
		// } catch (SQLException e) {
		// log.error("delete productDept error", e);
		// request.setAttribute("err", "删除失败!");
		// }
		String result = ipdept.deleteProductDept(idStr);
		request.setAttribute("err", result);
		return mapping.findForward("deleteproductDept");
	}
}
