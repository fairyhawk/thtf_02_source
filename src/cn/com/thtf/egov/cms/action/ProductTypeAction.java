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

import java.util.List;

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
import cn.com.thtf.egov.cms.dto.ProductTypeInfoDto;
import cn.com.thtf.egov.cms.iservice.IProductDeptService;
import cn.com.thtf.egov.cms.iservice.IProductTypeService;

/**
 * @author Administrator
 * 
 */

public class ProductTypeAction extends BaseAction {
	@SuppressWarnings("unused")
	private static Logger log = LoggerFactory
			.getLogger(ProductTypeAction.class);

	private IProductTypeService itdept;
	private IProductDeptService idept;

	/**
	 * 显示产品类型类表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */

	@SuppressWarnings("unchecked")
	public ActionForward productTypeAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		itdept = (IProductTypeService) getWebApplicationContext().getBean(
				"productTypeServiceImp");
		ListRange list = itdept.queryProductType(getPage(request));
		request.setAttribute("producttypelist", list);
		request.setAttribute("Page", list.getPage());
		return mapping.findForward("productTypeAll");
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
	@SuppressWarnings("unchecked")
	public ActionForward showProductTypeAdd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		idept = (IProductDeptService) getWebApplicationContext().getBean(
				"productDeptServiceImp");
		saveToken(request);
		String no = itdept.maxProductTypeNo();
		if (no != null) {
			Integer maxno = Integer.valueOf(no) + 1;
			int i = no.length() - String.valueOf(maxno).length();
			StringBuffer sb = new StringBuffer();
			for (int j = 0; j < i; j++) {
				sb.append("0");
			}
			// no = sb.toString() + maxno + "";
			no = sb.append(maxno.toString()).toString();
			request.setAttribute("maxno", no);
		} else {
			request.setAttribute("maxno", "01");
		}
		List list = idept.queryProductDeptName();
		request.setAttribute("pdeptname", list);
		return mapping.findForward("showProductTypeAdd");
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
	public ActionForward ProductTypeAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm dform = (DynaActionForm) form;
		itdept = (IProductTypeService) getWebApplicationContext().getBean(
				"productTypeServiceImp");
		ProductTypeInfoDto ptinfo = (ProductTypeInfoDto) dform
				.get("producttype");
		/* 防止重复提交 */
		if (isTokenValid(request)) {
			resetToken(request);
		} else {
			return productTypeAll(mapping, form, request, response);
		}
		ptinfo.setProduct_dept_id(Integer.parseInt(request
				.getParameter("productdept.id")));
		ptinfo.setNo(request.getParameter("maxno"));
		String ss = itdept.addProductType(ptinfo);
		if (ss != null) {
			request.setAttribute("err", ss);
			return showProductTypeAdd(mapping, form, request, response);
		} else {
			return this.showProductTypeAdd(mapping, form, request, response);
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
	@SuppressWarnings("unchecked")
	public ActionForward showProductTypeUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaActionForm dform = (DynaActionForm) form;
		itdept = (IProductTypeService) getWebApplicationContext().getBean(
				"productTypeServiceImp");
		idept = (IProductDeptService) getWebApplicationContext().getBean(
				"productDeptServiceImp");
		saveToken(request);
		List list = idept.queryProductDeptName();
		request.setAttribute("pdeptname", list);
		ProductTypeInfoDto ptinfo = (ProductTypeInfoDto) dform
				.get("producttype");
		if (ptinfo.getId() == null) {
			ptinfo = itdept.findTypeByID(Integer.parseInt(request
					.getParameter("id")));
		}
		ptinfo = itdept.findTypeByID(ptinfo.getId());
		request.setAttribute("producttypemsg", ptinfo);
		return mapping.findForward("showProductTypeUpdate");
	}

	/**
	 * 修改޸
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward productTypeUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaActionForm dform = (DynaActionForm) form;
		ProductTypeInfoDto pinfo = (ProductTypeInfoDto) dform
				.get("producttype");
		itdept = (IProductTypeService) getWebApplicationContext().getBean(
				"productTypeServiceImp");
		idept = (IProductDeptService) getWebApplicationContext().getBean(
				"productDeptServiceImp");
		/* 防止重复提交 */
		if (isTokenValid(request)) {
			resetToken(request);
		} else {
			return productTypeAll(mapping, form, request, response);
		}
		String result = itdept.updateProductType(pinfo);
		if (result != null) {
			request.setAttribute("err", result);
			return showProductTypeUpdate(mapping, form, request, response);
		} else {
			return mapping.findForward("productTypeUpdate");
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
	public ActionForward deleteProductType(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// DynaActionForm dform = (DynaActionForm) form;
		itdept = (IProductTypeService) getWebApplicationContext().getBean(
				"productTypeServiceImp");
		String idStr = request.getParameter("id");
		// String[] id = idStr.split(",");
		// for (int i = 0; i < id.length; i++) {
		// result = itdept.deleteProductType(Integer.parseInt(id[i]));
		// }
		String result = itdept.deleteProductType(idStr);
		if (result != null) {
			request.setAttribute("err", result);
			return productTypeAll(mapping, form, request, response);

		}

		return mapping.findForward("productTypeDelete");
	}

}
