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
import cn.com.thtf.egov.cms.constant.Constants;
import cn.com.thtf.egov.cms.dto.CityInfoDto;
import cn.com.thtf.egov.cms.dto.SupplierInfoDto;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.iservice.IProvinceCityService;
import cn.com.thtf.egov.cms.iservice.ISupplierService;

/**
 * 供货商Action
 * 
 * @author sxf
 * 
 */

public class SupplierAction extends BaseAction {
	private static Logger log = LoggerFactory.getLogger(SupplierAction.class);

	private ISupplierService supplierService;

	private IProvinceCityService provinceCityService;// 省市业务类

	/**
	 * 供货商列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward supplierAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm dform = (DynaActionForm) form;
		SupplierInfoDto info = (SupplierInfoDto) dform.get("supplier");
		supplierService = (ISupplierService) getWebApplicationContext()
				.getBean("supplierServiceImp");
		ListRange list = supplierService.queryAllSupplier(getPage(request),
				info);
		provinceCityService = (IProvinceCityService) getWebApplicationContext()
				.getBean("provinceCityServiceImp");
		List provinceList = provinceCityService.queryAllProvince();// 查询所有省
		List areaList = provinceCityService.queryAllArea();// 查询所有区域
		List cityList = provinceCityService.queryAllCityByProvinceId(info
				.getProvince());// 根据省名字查询所有市
		UserEntity user = (UserEntity) request.getSession().getAttribute(
				Constants.USERLOGIN);
		request.setAttribute("roleId", user.getRoleId());
		request.setAttribute("provinceList", provinceList);
		request.setAttribute("areaList", areaList);
		request.setAttribute("cityList", cityList);
		request.setAttribute("supplierList", list);
		request.setAttribute("Page", list.getPage());
		request.setAttribute("supplier", info);
		return mapping.findForward("supplierList");
	}

	/**
	 * 供货商查看
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward supplierLook(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id = request.getParameter("id");
		SupplierInfoDto info = null;
		supplierService = (ISupplierService) getWebApplicationContext()
				.getBean("supplierServiceImp");
		List linkList = supplierService.querySupplierLinkmanListById(Integer
				.valueOf(id));
		List addressList = supplierService.querySupplierAddressListById(Integer
				.valueOf(id));
		info = supplierService.querySupplierById(Integer.valueOf(id));
		request.setAttribute("linkList", linkList);
		request.setAttribute("addressList", addressList);
		request.setAttribute("supplier", info);
		return mapping.findForward("supplierLook");
	}

	/**
	 * 显示供货商修改
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward showSupplierUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Integer id = 0;
		String id1 = request.getParameter("id");
		if (id1 == null) {
			id1 = request.getParameter("supplier.id");
		}
		Integer id2 = request.getAttribute("id") == null ? null
				: (Integer) request.getAttribute("id");
		if (id1 != null && id1.length() > 0) {
			id = new Integer(id1);
		} else if (id2 != null) {
			id = id2;
		}
		saveToken(request);
		SupplierInfoDto info = null;
		supplierService = (ISupplierService) getWebApplicationContext()
				.getBean("supplierServiceImp");
		provinceCityService = (IProvinceCityService) getWebApplicationContext()
				.getBean("provinceCityServiceImp");
		List linkList = supplierService.querySupplierLinkmanListById(id);
		List addressList = supplierService.querySupplierAddressListById(id);
		info = supplierService.querySupplierById(id);
		request.setAttribute("linkManList", linkList);
		request.setAttribute("addressList", addressList);
		request.setAttribute("supplierInfo", info);
		List provinceList = provinceCityService.queryAllProvince();
		// 根据供货商现在的省Name去查询所有City列表
		request.setAttribute("provinceList", provinceList);
		List cityList = provinceCityService.queryAllCityByProvinceId(info
				.getProvince());
		request.setAttribute("cityList", cityList);
		return mapping.findForward("010602modify");
	}

	/**
	 * 显示供货商添加页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward showSupplierAdd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		provinceCityService = (IProvinceCityService) getWebApplicationContext()
				.getBean("provinceCityServiceImp");
		saveToken(request);
		List provinceList = provinceCityService.queryAllProvince();
		request.setAttribute("provinceList", provinceList);
		return mapping.findForward("showSupplierAdd");
	}

	/**
	 * 显示联系人添加
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showLinkmanAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		saveToken(request);
		String supplierId = request.getParameter("supplierId");
		String supplier = request.getParameter("suppliername");
//		String supplierName = new String(supplier.getBytes("iso-8859-1"),
//				"utf-8");// 字符转换
		request.setAttribute("supplierId", supplierId);
		request.setAttribute("supplierName", supplier);
		return mapping.findForward("showLinkmanAdd");
	}

	/**
	 * 显示供货商发货地址添加
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showAddressAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		saveToken(request);
		String supplierId = request.getParameter("supplierId");
		String supplier = request.getParameter("suppliername");
//		String supplierName = new String(supplier.getBytes("iso-8859-1"),
//				"utf-8");// 字符转换
		request.setAttribute("supplierId", supplierId);
		request.setAttribute("supplierName", supplier);
		return mapping.findForward("showAddressAdd");
	}

	/**
	 * 添加供货商联系人
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addSupplierLinkman(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		SupplierInfoDto info = new SupplierInfoDto();
		supplierService = (ISupplierService) getWebApplicationContext()
				.getBean("supplierServiceImp");
		
		info.setId(Integer.parseInt(request.getParameter("id")));
		info.setLinkman(request.getParameter("linkman"));
		info.setRole(request.getParameter("role"));
		info.setTel(request.getParameter("tel"));
		info.setFax(request.getParameter("fax"));
		info.setMobile(request.getParameter("mobile"));
		info.setMail(request.getParameter("mail"));
		info.setMsn(request.getParameter("msn"));
		info.setQq(request.getParameter("qq"));
		
		String same = supplierService.addSupplierLinkman(info);
		if (same != null) {
			log.debug("新增供货商联系人出错!");
			response.getWriter().write("false");
			// System.out.println("----新增供货商联系人出错---");
		}
		request.setAttribute("id", info.getId());
		return null;
	}

	/**
	 * 添加供货商发货地址
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addSupplierAddress(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SupplierInfoDto info = new SupplierInfoDto();
		info.setId(Integer.parseInt(request.getParameter("id")));
        info.setReceiveName(request.getParameter("receiveName"));
        info.setSupplierAddress(request.getParameter("supplierAddress"));
        info.setSupplierPostcode(request.getParameter("supplierPostcode"));
        info.setSupplierLinkman(request.getParameter("supplierLinkman"));
        info.setSupplierTel(request.getParameter("supplierTel"));
        info.setSupplierMobile(request.getParameter("supplierMobile"));
		supplierService = (ISupplierService) getWebApplicationContext()
				.getBean("supplierServiceImp");
		
		String same = supplierService.addSupplierAddress(info);
		if (same != null) {
			log.debug("新增供货商地址出错!");
			response.getWriter().write("false");
			// System.out.println("----新增供货商地址出错---");
		}
		request.setAttribute("id", info.getId());
		return null;
	}

	/**
	 * 显示供货商联系人修改页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showLinkmanUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		supplierService = (ISupplierService) getWebApplicationContext()
				.getBean("supplierServiceImp");
		saveToken(request);
		String linkmanId = request.getParameter("linkmanId");
		SupplierInfoDto info = supplierService.queryLinkmanByLinkmanId(Integer
				.valueOf(linkmanId));
		request.setAttribute("supplierInfo", info);
		return mapping.findForward("010602linkman_modify");
	}

	/**
	 * 显示供货商发货地址修改页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showAddressUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		supplierService = (ISupplierService) getWebApplicationContext()
				.getBean("supplierServiceImp");
		saveToken(request);
		String addressId = request.getParameter("addressId");
		SupplierInfoDto info = supplierService.queryAddressByAddressId(Integer
				.valueOf(addressId));
		request.setAttribute("supplierInfo", info);
		return mapping.findForward("010602address_modify");
	}

	/**
	 * 供货商联系人修改后保存
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveLinkman(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	    SupplierInfoDto info = new SupplierInfoDto();
	    info.setId(Integer.parseInt(request.getParameter("id")));
        info.setLinkman(request.getParameter("linkman"));
        info.setRole(request.getParameter("role"));
        info.setTel(request.getParameter("tel"));
        info.setFax(request.getParameter("fax"));
        info.setMobile(request.getParameter("mobile"));
        info.setMail(request.getParameter("mail"));
        info.setMsn(request.getParameter("msn"));
        info.setQq(request.getParameter("qq"));
        info.setLinkmanId(Integer.parseInt(request.getParameter("linkmanId")));
		supplierService = (ISupplierService) getWebApplicationContext()
				.getBean("supplierServiceImp");
		
		supplierService.saveLinkman(info);
		request.setAttribute("id", info.getId());
		return null;
	}

	/**
	 * 供货商发货地址修改后保存
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveAddress(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SupplierInfoDto info = new SupplierInfoDto();
		info.setId(Integer.parseInt(request.getParameter("id")));
        info.setReceiveName(request.getParameter("receiveName"));
        info.setSupplierAddress(request.getParameter("supplierAddress"));
        info.setSupplierPostcode(request.getParameter("supplierPostcode"));
        info.setSupplierLinkman(request.getParameter("supplierLinkman"));
        info.setSupplierTel(request.getParameter("supplierTel"));
        info.setSupplierMobile(request.getParameter("supplierMobile"));
        info.setReceiveName(request.getParameter("receiveName"));
        info.setReceiveId(Integer.parseInt(request.getParameter("receiveId")));
		supplierService = (ISupplierService) getWebApplicationContext()
				.getBean("supplierServiceImp");
		
		supplierService.saveAddress(info);
		request.setAttribute("id", info.getId());
		return this.showSupplierUpdate(mapping, form, request, response);
	}

	/**
	 * 供货商添加
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward addSupplier(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm dform = (DynaActionForm) form;
		supplierService = (ISupplierService) getWebApplicationContext()
				.getBean("supplierServiceImp");
		provinceCityService = (IProvinceCityService) getWebApplicationContext()
				.getBean("provinceCityServiceImp");
		SupplierInfoDto info = (SupplierInfoDto) dform.get("supplier");

		List list = provinceCityService.queryAllCityByProvinceId(info
				.getProvince());
		CityInfoDto cityInfo = (CityInfoDto) list.get(0);
		String area = cityInfo.getAreaName();
		info.setArea(area);
		if (info.getInvoiceType() == 0) {
			info.setTaxRate(0);
		}
		/* 防止重复提交 */
		if (isTokenValid(request)) {
			resetToken(request);
		} else {
			return showSupplierAdd(mapping, form, request, response);
		}
		String result = supplierService.addSupplier(info);
		if (result != null) {
			request.setAttribute("err", result);
		}
		// 成功不成功都回添加页面（需求）
		return this.showSupplierAdd(mapping, form, request, response);
	}

	/**
	 * 供货商修改后保存
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward saveSupplier(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DynaActionForm dform = (DynaActionForm) form;
		supplierService = (ISupplierService) getWebApplicationContext()
				.getBean("supplierServiceImp");
		provinceCityService = (IProvinceCityService) getWebApplicationContext()
				.getBean("provinceCityServiceImp");
		UserEntity user = (UserEntity) request.getSession().getAttribute(
				Constants.USERLOGIN);
		request.setAttribute("roleId", user.getRoleId());
		SupplierInfoDto info = (SupplierInfoDto) dform.get("supplier");
		List list = provinceCityService.queryAllCityByProvinceId(info
				.getProvince());
		CityInfoDto cityInfo = (CityInfoDto) list.get(0);
		String area = cityInfo.getAreaName();
		info.setArea(area);
		if (info.getInvoiceType() == 0)
			info.setTaxRate(0);// 普通发票，增值税为0

		/* 防止重复提交 */
		if (isTokenValid(request)) {
			resetToken(request);
			String result = supplierService.saveSupplier(info);
			if (result != null) {
				request.setAttribute("err", result);
				return this
						.showSupplierUpdate(mapping, form, request, response);
			}
		}
		// 修改完后跳转到列表页面
		SupplierInfoDto info1 = new SupplierInfoDto();
		ListRange list1 = supplierService.queryAllSupplier(getPage(request),
				info1);
		provinceCityService = (IProvinceCityService) getWebApplicationContext()
				.getBean("provinceCityServiceImp");
		List provinceList = provinceCityService.queryAllProvince();// 查询所有省
		List areaList = provinceCityService.queryAllArea();// 查询所有区域
		List cityList = provinceCityService.queryAllCityByProvinceId(info1
				.getProvince());// 根据省名字查询所有市
		request.setAttribute("provinceList", provinceList);
		request.setAttribute("areaList", areaList);
		request.setAttribute("cityList", cityList);
		request.setAttribute("supplierList", list1);
		request.setAttribute("Page", list1.getPage());
		request.setAttribute("supplier", info1);
		return mapping.findForward("supplierList");
	}

	/**
	 * 删除供货商联系人
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteLinkman(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		supplierService = (ISupplierService) getWebApplicationContext()
				.getBean("supplierServiceImp");
		String idStr = request.getParameter("sxf");
		String supplierId = request.getParameter("supplierId");
		String[] id = idStr.split(",");
		for (int i = 0; i < id.length; i++) {
			supplierService.deleteLinkman(Integer.parseInt(id[i]));
		}
		request.setAttribute("id", Integer.valueOf(supplierId));
		return this.showSupplierUpdate(mapping, form, request, response);
	}

	/**
	 * 删除供货商发货地址
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteAddress(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		supplierService = (ISupplierService) getWebApplicationContext()
				.getBean("supplierServiceImp");
		String idStr = request.getParameter("sxf");
		String supplierId = request.getParameter("supplierId");
		String[] id = idStr.split(",");
		for (int i = 0; i < id.length; i++) {
			supplierService.deleteAddress(Integer.parseInt(id[i]));
		}
		request.setAttribute("id", Integer.valueOf(supplierId));
		return this.showSupplierUpdate(mapping, form, request, response);
	}

	/**
	 * 删除供货商
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward deleteSupplier(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DynaActionForm dform = (DynaActionForm) form;
		supplierService = (ISupplierService) getWebApplicationContext()
				.getBean("supplierServiceImp");
		SupplierInfoDto info = (SupplierInfoDto) dform.get("supplier");
		String idStr = request.getParameter("id");
		// String[] id = idStr.split(",");
		// for (int i = 0; i < id.length; i++) {
		// supplierService.deleteSupplier(Integer.parseInt(id[i]));
		// }
		String result = supplierService.deleteSupplier(idStr);
		request.setAttribute("err", result);
		ListRange list = supplierService.queryAllSupplier(getPage(request),
				info);
		provinceCityService = (IProvinceCityService) getWebApplicationContext()
				.getBean("provinceCityServiceImp");
		List provinceList = provinceCityService.queryAllProvince();// 查询所有省
		List areaList = provinceCityService.queryAllArea();// 查询所有区域
		List cityList = provinceCityService.queryAllCityByProvinceId(info
				.getProvince());// 根据省名字查询所有市
		request.setAttribute("provinceList", provinceList);
		request.setAttribute("areaList", areaList);
		request.setAttribute("cityList", cityList);
		request.setAttribute("supplierList", list);
		request.setAttribute("Page", list.getPage());
		request.setAttribute("supplier", info);
		UserEntity user = (UserEntity) request.getSession().getAttribute(
				Constants.USERLOGIN);
		request.setAttribute("roleId", user.getRoleId());
		return mapping.findForward("supplierList");
	}
}
