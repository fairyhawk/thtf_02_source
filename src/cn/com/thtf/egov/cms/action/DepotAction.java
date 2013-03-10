/**
 * ClassName  DepotAction
 *
 * History
 * Create User: sxf
 * Create Date: 2009年12月21日
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.BaseAction;
import cn.com.thtf.egov.cms.application.ListRange;
import cn.com.thtf.egov.cms.dto.DepotInfoDto;
import cn.com.thtf.egov.cms.dto.UserDeptInfoDto;
import cn.com.thtf.egov.cms.iservice.IDepotService;
import cn.com.thtf.egov.cms.iservice.IProductDeptService;

/**
 * 处理库房信息的Action
 * 
 * @author sxf
 */
public class DepotAction extends BaseAction {

	/** 库房业务类 */
	private IDepotService depotService;
	/** 产品部门业务类 */
	private IProductDeptService productDeptService;
	/** log */
	private static Logger log = LoggerFactory.getLogger(DepotAction.class);

	/**
	 * 查询所有库房列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward depotAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.debug("depotAll");

		depotService = (IDepotService) getWebApplicationContext().getBean(
				"depotServiceImp");

		ListRange<DepotInfoDto> depotList = depotService
				.queryDepotAll(getPage(request));

		request.setAttribute("depotList", depotList);
		request.setAttribute("Page", depotList.getPage());
		return mapping.findForward("depotList");
	}

	/**
	 * 显示添加库房
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showAddDepot(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.debug("showAddDepot");

		depotService = (IDepotService) getWebApplicationContext().getBean(
				"depotServiceImp");
		productDeptService = (IProductDeptService) getWebApplicationContext()
				.getBean("productDeptServiceImp");

		saveToken(request);

		List<UserDeptInfoDto> productDeptList = productDeptService
				.queryProductDept1();

		request.setAttribute("productDeptList", productDeptList);
		return mapping.findForward("depotAdd");
	}

	/**
	 * 添加库房信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addDepot(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.debug("addDepot");
		DynaActionForm dform = (DynaActionForm) form;
		DepotInfoDto depotinfo = (DepotInfoDto) dform.get("depot");

		depotService = (IDepotService) getWebApplicationContext().getBean(
				"depotServiceImp");

		if (depotinfo.getType() == 0) {
			depotinfo.setName("虚拟库");
		}

		depotinfo.setName(depotinfo.getName().trim());

		/* 滤重 */
		Integer isExit = depotService.idExit(depotinfo);
		if (isExit != 0) {
			request.setAttribute("err", "添加失败！");
			return this.showAddDepot(mapping, form, request, response);
		}

		/* 防止重复提交 */
		if (isTokenValid(request)) {
			resetToken(request);
		} else {
			return depotAll(mapping, form, request, response);
		}

		String addResult = depotService.addDepot(depotinfo);
		if (addResult != null) {
			request.setAttribute("err", "添加失败！");
		}

		return this.showAddDepot(mapping, form, request, response);
	}

	/**
	 * 删除库房
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteDepot(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.debug("deleteDepot");

		depotService = (IDepotService) getWebApplicationContext().getBean(
				"depotServiceImp");

		String idStr = request.getParameter("id");
		// String[] idArr = idStr.split(",");
		String result = "";
		// for (int i = 0; i < idArr.length; i++) {
		// try {
		// depotService.deleteDepot(Integer.parseInt(idArr[i]));
		// } catch (Exception e) {
		// result = "删除失败！";
		// }
		// }
		result = depotService.deleteDepot(idStr);
		request.setAttribute("err", result);
		return depotAll(mapping, form, request, response);
	}

	/**
	 * 显示库房修改页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward depotUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.debug("depotUpdate");
		String mark = request.getParameter("mark");
		DynaActionForm dform = (DynaActionForm) form;
		DepotInfoDto depotInfo = (DepotInfoDto) dform.get("depot");

		depotService = (IDepotService) getWebApplicationContext().getBean(
				"depotServiceImp");
		productDeptService = (IProductDeptService) getWebApplicationContext()
				.getBean("productDeptServiceImp");

		if (depotInfo.getId() == null) {
			depotInfo = depotService.showUpdateDepot(Integer.valueOf(request
					.getParameter("id")));
		} else {
			depotInfo = depotService.showUpdateDepot(depotInfo.getId());
		}

		saveToken(request);

		List<UserDeptInfoDto> productDeptList = productDeptService
				.queryProductDept1();
		List<DepotInfoDto> addressList = depotService
				.queryAddressListByDepotId(depotInfo.getId());

		request.setAttribute("productDeptList", productDeptList);
		request.setAttribute("addressList", addressList);
		request.setAttribute("depotInfo", depotInfo);
		if(StringUtils.isNotEmpty(mark)){
			String depotName = depotInfo.getName().substring(0, depotInfo.getName().length()-3);
			request.setAttribute("depotName", depotName);
		    if (depotInfo.getType() == 0) {
	            return mapping.findForward("depotView0");
	        } else if (depotInfo.getType() == 1 || depotInfo.getType() == 2) {
	            return mapping.findForward("depotView1");
	        } else {
	            log.debug("错误的跳转!");
	            return depotAll(mapping, form, request, response);
	        }
		}
		if (depotInfo.getType() == 0) {
			return mapping.findForward("depotUpdate0");
		} else if (depotInfo.getType() == 1 || depotInfo.getType() == 2) {
			return mapping.findForward("depotUpdate1");
		} else {
			log.debug("错误的跳转!");
			return depotAll(mapping, form, request, response);
		}

	}

	/**
	 * 库房修改后保存
	 * 
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveDepot(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.debug("saveDepot");

		DynaActionForm dform = (DynaActionForm) form;
		DepotInfoDto depotInfo = (DepotInfoDto) dform.get("depot");

		depotService = (IDepotService) getWebApplicationContext().getBean(
				"depotServiceImp");

		depotInfo.setName(depotInfo.getName().trim());
		Integer depotId = depotService.idDepotUpdateExit(depotInfo);

		/* 滤重 */
		if (depotId != null && !depotId.equals(depotInfo.getId())) {
			request.setAttribute("err", "修改失败！");
			if (depotInfo.getType() == 0) {// 虚拟库
				return this.depotUpdate(mapping, form, request, response);
			} else if (depotInfo.getType() == 1 || depotInfo.getType() == 2) {// 正常库或者索赔库
				return this.depotUpdate(mapping, form, request, response);
			}
		}
		/* 防止重复提交 */
		if (isTokenValid(request)) {
			resetToken(request);
		} else {
			return depotAll(mapping, form, request, response);
		}

		String updateResult = depotService.saveUpdateDepot(depotInfo);
		if (updateResult != null) {
			request.setAttribute("err", "修改失败！");
			if (depotInfo.getType() == 0) {
				return this.depotUpdate(mapping, form, request, response);
			} else if (depotInfo.getType() == 1 || depotInfo.getType() == 2) {
				return this.depotUpdate(mapping, form, request, response);
			} else {
				log.debug("错误的跳转!");
				return depotAll(mapping, form, request, response);
			}
		} else {
			return depotAll(mapping, form, request, response);
		}

	}

	/**
	 * 显示库房发货地址添加
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
		log.debug("showAddressAdd");

		saveToken(request);

		String depotId = request.getParameter("depotId");
		request.setAttribute("depotId", depotId);

		return mapping.findForward("showAddressAdd");
	}

	/**
	 * 添加库房发货地址
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addDepotAddress(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.debug("addDepotAddress");

		DynaActionForm dform = (DynaActionForm) form;
		DepotInfoDto info = (DepotInfoDto) dform.get("depot");

		depotService = (IDepotService) getWebApplicationContext().getBean(
				"depotServiceImp");

		/* 防止重复提交 */
		if (isTokenValid(request)) {
			resetToken(request);
		} else {
			return depotUpdate(mapping, form, request, response);
		}

		boolean same = depotService.addDepotAddress(info);
		if (!same) {
			log.debug("新增库房地址出错!");
		}
		request.setAttribute("serverData", same);
		request.setAttribute("id", info.getId());
		return mapping.findForward("showAddressAdd");
	}

	/**
	 * 显示库房发货地址修改页面
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
		log.debug("showAddressUpdate");

		depotService = (IDepotService) getWebApplicationContext().getBean(
				"depotServiceImp");

		saveToken(request);

		String addressId = request.getParameter("addressId");
		DepotInfoDto info = depotService.queryAddressByAddressId(Integer
				.valueOf(addressId));
		request.setAttribute("supplierInfo", info);
		return mapping.findForward("depotaddress_modify");
	}

	/**
	 * 库房发货地址修改后保存
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
		log.debug("saveAddress");

		DynaActionForm dform = (DynaActionForm) form;
		DepotInfoDto info = (DepotInfoDto) dform.get("depot");

		depotService = (IDepotService) getWebApplicationContext().getBean(
				"depotServiceImp");
		/* 防止重复提交 */
		if (isTokenValid(request)) {
			resetToken(request);
		} else {
			return depotUpdate(mapping, form, request, response);
		}
		
		boolean updateResult = depotService.saveAddress(info);
		request.setAttribute("id", info.getId());
		request.setAttribute("serverData", updateResult);
		return showAddressUpdate(mapping, dform, request, response);
	}

	/**
	 * 删除库房发货地址
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
		log.debug("deleteAddress");

		DynaActionForm dform = (DynaActionForm) form;
		DepotInfoDto depotInfo = (DepotInfoDto) dform.get("depot");

		depotService = (IDepotService) getWebApplicationContext().getBean(
				"depotServiceImp");
		String idStr = request.getParameter("sxf");
		String[] idArr = idStr.split(",");
		for (int i = 0; i < idArr.length; i++) {
			depotService.deleteAddress(Integer.parseInt(idArr[i]));
		}
		request.setAttribute("id", depotInfo.getId());
		return this.depotUpdate(mapping, form, request, response);
	}

}
