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

import cn.com.thtf.egov.cms.application.BaseAction;
import cn.com.thtf.egov.cms.dto.CompanyAddressInfoDto;
import cn.com.thtf.egov.cms.dto.CompanyInfoDto;
import cn.com.thtf.egov.cms.iservice.ICompanyService;

/**
 * @author Administrator
 * 
 */

public class CompanyAction extends BaseAction {

	private ICompanyService ics;

	/**
	 * 公司管理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward companyAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CompanyInfoDto cinfo = null;
		ics = (ICompanyService) getWebApplicationContext().getBean(
				"companyServiceImp");
		List list = ics.queryCompanyAdress();
		request.setAttribute("cadresslist", list);
		if (ics.companyId() != null) {
			Integer id = ics.companyId();
			cinfo = ics.companyAll(id);
			request.setAttribute("cinfos", cinfo);
		}
		return mapping.findForward("showCompany");
	}

	/**
	 * 显示添加地址页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showCompanyAddressAdd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ics = (ICompanyService) getWebApplicationContext().getBean(
				"companyServiceImp");
		saveToken(request);
		return mapping.findForward("showCompanyAddress");
	}

	/**
	 * 添加地址
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward companyAddressAdd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaActionForm dform = (DynaActionForm) form;
		CompanyAddressInfoDto cainfo = (CompanyAddressInfoDto) dform
				.get("companyaddress");
		ics = (ICompanyService) getWebApplicationContext().getBean(
				"companyServiceImp");
		/* 防止重复提交 */
		if (isTokenValid(request)) {
			resetToken(request);
		} else {
			return companyAll(mapping, form, request, response);
		}
		String result = ics.addCompanyAddress(cainfo);
		if (result != null) {
			request.setAttribute("err", result);
			return showCompanyAddressAdd(mapping, form, request, response);
		} else {
			return showCompanyAddressAdd(mapping, form, request, response);
		}
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
	public ActionForward showCompanyAddressUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaActionForm dform = (DynaActionForm) form;
		CompanyAddressInfoDto cainfo = (CompanyAddressInfoDto) dform
				.get("companyaddress");
		ics = (ICompanyService) getWebApplicationContext().getBean(
				"companyServiceImp");
		saveToken(request);
		if (cainfo.getId() == null) {
			cainfo = ics.showUpdate(Integer
					.parseInt(request.getParameter("id")));
		} else {
			cainfo = ics.showUpdate(cainfo.getId());
		}
		request.setAttribute("cainfo", cainfo);
		return mapping.findForward("showUpdateAddress");
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
	public ActionForward companyAddressUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CompanyAddressInfoDto cainfo1 = new CompanyAddressInfoDto();
		ics = (ICompanyService) getWebApplicationContext().getBean(
				"companyServiceImp");
		/* 防止重复提交 */
		if (isTokenValid(request)) {
			resetToken(request);
		} else {
			return companyAll(mapping, form, request, response);
		}
		cainfo1.setName(request.getParameter("companyaddress.name"));
		cainfo1.setAddress(request.getParameter("companyaddress.address"));
		cainfo1.setPostcode(request.getParameter("companyaddress.postcode"));
		cainfo1.setMobile(request.getParameter("companyaddress.mobile"));
		cainfo1.setLinkman(request.getParameter("companyaddress.linkman"));
		cainfo1.setTel(request.getParameter("companyaddress.tel"));
		cainfo1.setId(Integer.parseInt(request
				.getParameter("companyaddress.id")));
		String result = ics.saveUpdate(cainfo1);
		if (result != null) {
			request.setAttribute("err", result);
			return showCompanyAddressUpdate(mapping, form, request, response);
		} else {
			return companyAll(mapping, form, request, response);
		}
	}

	/**
	 * 添加公司
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward companyAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm dform = (DynaActionForm) form;
		CompanyInfoDto cinfo = (CompanyInfoDto) dform.get("company");
		ics = (ICompanyService) getWebApplicationContext().getBean(
				"companyServiceImp");
		if (request.getParameter("company.id") != "") {
			ics.updateCompany(cinfo);
		} else {
			ics.saveCompany(cinfo);
		}
		return mapping.findForward("aaa");
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
	public ActionForward companyDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ics = (ICompanyService) getWebApplicationContext().getBean(
				"companyServiceImp");
		String idStr = request.getParameter("id");
		String[] id = idStr.split(",");
		for (int i = 0; i < id.length; i++) {
			ics.deleteAddress(Integer.parseInt(id[i]));
		}
		return companyAll(mapping, form, request, response);
	}
}
