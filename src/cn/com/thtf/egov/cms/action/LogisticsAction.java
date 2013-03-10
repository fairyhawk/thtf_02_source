package cn.com.thtf.egov.cms.action;

import java.io.PrintWriter;
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
import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.constant.Constants;
import cn.com.thtf.egov.cms.dto.CityInfoDto;
import cn.com.thtf.egov.cms.dto.LogisticsInfoDto;
import cn.com.thtf.egov.cms.entity.LogisticsEntity;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.iservice.ILogisticsService;
import cn.com.thtf.egov.cms.iservice.IProvinceCityService;

/**
 * 处理物流公司的Action
 * 
 * @author sxf
 * 
 */
@SuppressWarnings("unchecked")
public class LogisticsAction extends BaseAction {

	/** log */
	private static Logger log = LoggerFactory.getLogger(LogisticsAction.class);

	private ILogisticsService logisticsService;// 物流公司业务类

	private IProvinceCityService provinceCityService;// 省市业务类

	/**
	 * 查询所有物流公司列表
	 * 
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward logisticsAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// DynaActionForm dform = (DynaActionForm) form;

		logisticsService = (ILogisticsService) getWebApplicationContext()
				.getBean("logisticsServiceImp");
		// LogisticsInfo info = (LogisticsInfo) dform.get("logistics");
		// ListRange logisticsList =
		// logisticsService.queryAllLogistics(getPage(request),
		// info);
		// request.setAttribute("logisticsList", logisticsList);
		// request.setAttribute("Page", logisticsList.getPage());
		// request.setAttribute("logistics", info);
		String id = request.getParameter("logistics.id");
		String name = request.getParameter("logistics.name");

		log.debug("logistics.name:" + name);

		NewPage newPage = getNewPage(request);
		LogisticsEntity para = new LogisticsEntity();
		para.setName(name);

		newPage.setUrl("logistics.do");
		newPage.setQuery("method", "logisticsAll");
		if (StringUtils.isNotEmpty(para.getName())) {
			newPage.setQuery("logistics.name", para.getName());
		}

		List<LogisticsEntity> resultList = logisticsService.queryAllLogistics(
				para, newPage);

		request.setAttribute("NewPage", newPage);
		request.setAttribute("logisticsList", resultList);
		request.setAttribute("logisticsId", id);
		UserEntity user = (UserEntity) request.getSession().getAttribute(
				Constants.USERLOGIN);
		request.setAttribute("roleId", user.getRoleId());
		return mapping.findForward("logisticsList");
	}

	/**
	 * 显示添加物流公司
	 * 
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showLogisticsAdd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		provinceCityService = (IProvinceCityService) getWebApplicationContext()
				.getBean("provinceCityServiceImp");
		saveToken(request);
		List provinceList = provinceCityService.queryAllProvince();
		request.setAttribute("provinceList", provinceList);
		return mapping.findForward("logisticsAdd");
	}

	/**
	 * 根据省Id查询该省所有市区
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getCitysByProvinceId(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		provinceCityService = (IProvinceCityService) getWebApplicationContext()
				.getBean("provinceCityServiceImp");
		String provinceId = request.getParameter("provinceId");// 其实是 省Name
//		String provinceName = new String(provinceId.getBytes("iso-8859-1"),
//				"utf-8");// 字符转换
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		List cityList = provinceCityService
				.queryAllCityByProvinceId(provinceId);
		StringBuffer city = new StringBuffer("<citys>");
		String areaName = "";
		for (int i = 0; i < cityList.size(); i++) {
			CityInfoDto info = (CityInfoDto) cityList.get(i);
			areaName = info.getAreaName();
			city.append("<cityName>" + info.getName() + "</cityName>");
		}
		city.append("<areaName>" + areaName + "</areaName>");
		city.append("</citys>");
		// System.out.println(city.toString());
		PrintWriter out = response.getWriter();
		out.print(city.toString());
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 添加物流公司信息
	 * 
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addLogistics(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logisticsService = (ILogisticsService) getWebApplicationContext()
				.getBean("logisticsServiceImp");
		provinceCityService = (IProvinceCityService) getWebApplicationContext()
				.getBean("provinceCityServiceImp");
		LogisticsInfoDto info1 = new LogisticsInfoDto();
		info1.setName(request.getParameter("names").trim());
		info1.setDate(request.getParameter("dates"));
		info1.setCity(request.getParameter("citys"));
		info1.setProvince(request.getParameter("provinces"));
		List cityList = provinceCityService.queryAllCityByProvinceId(info1
				.getProvince());
		CityInfoDto cityinfo = (CityInfoDto) cityList.get(0);
		info1.setArea(cityinfo.getAreaName());
		info1.setPostcode(request.getParameter("postcodes").trim());
		info1.setAddress(request.getParameter("addresss").trim());
		info1.setLinkman(request.getParameter("linkmans").trim());
		info1.setRole(request.getParameter("roles").trim());
		info1.setTel(request.getParameter("tels").trim());
		info1.setRemitBankName(request.getParameter("remitBankNames").trim());
		info1.setRemitBankAccount(request.getParameter("remitBankAccounts")
				.trim());
		if (request.getParameter("faxs") != null) {
			info1.setFax(request.getParameter("faxs").trim());
		}
		if (request.getParameter("mails") != null) {
			info1.setMail(request.getParameter("mails").trim());
		}
		if (request.getParameter("qqs") != null) {
			info1.setQq(request.getParameter("qqs").trim());
		}
		if (request.getParameter("msns") != null) {
			info1.setMsn(request.getParameter("msns").trim());
		}
		if (request.getParameter("mobiles") != null) {
			info1.setMobile(request.getParameter("mobiles").trim());
		}
		/* 防止重复提交 */
		if (isTokenValid(request)) {
			resetToken(request);
		} else {
			return logisticsAll(mapping, form, request, response);
		}
		String result = logisticsService.addLogistics(info1);
		if (result != null) {
			request.setAttribute("err", result);
			// return mapping.findForward("logisticsAdd");
		}
		// else {
		return this.showLogisticsAdd(mapping, form, request, response);
		// }
	}

	/**
	 * 删除物流公司
	 * 
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteLogistics(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logisticsService = (ILogisticsService) getWebApplicationContext()
				.getBean("logisticsServiceImp");

		String idStr = request.getParameter("id");
		// String[] id = idStr.split(",");
		// for (int i = 0; i < id.length; i++) {
		// logisticsService.deleteLogistics(Integer.parseInt(id[i]));
		// }
		String result = logisticsService.deleteLogistics(idStr);
		request.setAttribute("err", result);
		DynaActionForm dform = (DynaActionForm) form;
		LogisticsInfoDto logisticsInfo = (LogisticsInfoDto) dform
				.get("logistics");
		logisticsInfo.setName(request.getParameter("name"));
		request.setAttribute("logistics", logisticsInfo);
		return this.logisticsAll(mapping, form, request, response);
	}

	/**
	 * 显示物流公司修改页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */

	public ActionForward logisticsUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logisticsService = (ILogisticsService) getWebApplicationContext()
				.getBean("logisticsServiceImp");
		provinceCityService = (IProvinceCityService) getWebApplicationContext()
				.getBean("provinceCityServiceImp");
		saveToken(request);
		DynaActionForm dform = (DynaActionForm) form;
		LogisticsInfoDto info = (LogisticsInfoDto) dform.get("logistics");
		if (info.getId() == null) {
			info = logisticsService.showUpdateLogistics(Integer
					.parseInt(request.getParameter("id")));
		} else {
			info = logisticsService.showUpdateLogistics(info.getId());
		}

		List provinceList = provinceCityService.queryAllProvince();
		// 根据物流公司现在的省Name去查询所有City列表
		request.setAttribute("provinceList", provinceList);
		List cityList = provinceCityService.queryAllCityByProvinceId(info
				.getProvince());
		request.setAttribute("logisticsInfo", info);
		request.setAttribute("cityList", cityList);
		return mapping.findForward("logisticsUpdate");
	}

	/**
	 * 物流公司修改后保存
	 * 
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveLogistics(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logisticsService = (ILogisticsService) getWebApplicationContext()
				.getBean("logisticsServiceImp");

		provinceCityService = (IProvinceCityService) getWebApplicationContext()
				.getBean("provinceCityServiceImp");
		LogisticsInfoDto info1 = new LogisticsInfoDto();
		info1.setId(Integer.parseInt(request.getParameter("id")));
		info1.setName(request.getParameter("names").trim());
		info1.setDate(request.getParameter("dates"));
		info1.setCity(request.getParameter("citys"));
		info1.setProvince(request.getParameter("provinces"));
		List cityList = provinceCityService.queryAllCityByProvinceId(info1
				.getProvince());
		CityInfoDto cityinfo = (CityInfoDto) cityList.get(0);
		info1.setArea(cityinfo.getAreaName());
		info1.setPostcode(request.getParameter("postcodes").trim());
		info1.setAddress(request.getParameter("addresss").trim());
		info1.setLinkman(request.getParameter("linkmans").trim());
		info1.setRole(request.getParameter("roles").trim());
		info1.setTel(request.getParameter("tels").trim());
		info1.setRemitBankName(request.getParameter("remitBankNames").trim());
		info1.setRemitBankAccount(request.getParameter("remitBankAccounts")
				.trim());
		if (request.getParameter("faxs") != null) {
			info1.setFax(request.getParameter("faxs").trim());
		}
		if (request.getParameter("mails") != null) {
			info1.setMail(request.getParameter("mails").trim());
		}
		if (request.getParameter("qqs") != null) {
			info1.setQq(request.getParameter("qqs").trim());
		}
		if (request.getParameter("msns") != null) {
			info1.setMsn(request.getParameter("msns").trim());
		}
		if (request.getParameter("mobiles") != null) {
			info1.setMobile(request.getParameter("mobiles").trim());
		}
		String result = logisticsService.saveLogistics(info1);
		if (result != null) {
			request.setAttribute("err", result);

			return logisticsUpdate(mapping, form, request, response);
		} else {
			return logisticsAll(mapping, form, request, response);
		}
	}

	/**
	 * 物流公司检索
	 * 
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkLogistics(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logisticsService = (ILogisticsService) getWebApplicationContext()
				.getBean("logisticsServiceImp");

		String name = request.getParameter("logisticsName").trim();
		// String name = new String(logisticsName.getBytes("iso-8859-1"),
		// "utf-8");// 字符转换
		DynaActionForm dform = (DynaActionForm) form;
		LogisticsInfoDto info = (LogisticsInfoDto) dform.get("logistics");
		info.setName(name);

		NewPage newPage = getNewPage(request);
		LogisticsEntity para = new LogisticsEntity();
		para.setName(name);

		newPage.setUrl("logistics.do");
		newPage.setQuery("method", "logisticsAll");
		if (StringUtils.isNotEmpty(para.getName())) {
			newPage.setQuery("logistics.name", para.getName());
		}

		List<LogisticsEntity> resultList = logisticsService.queryAllLogistics(
				para, newPage);
		UserEntity user = (UserEntity) request.getSession().getAttribute(
                Constants.USERLOGIN);
        request.setAttribute("roleId", user.getRoleId());
		request.setAttribute("NewPage", newPage);
		request.setAttribute("logisticsList", resultList);
		request.setAttribute("logistics", info);
		return mapping.findForward("logisticsList");
	}

	/**
	 * 查看
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward logisticsLooks(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logisticsService = (ILogisticsService) getWebApplicationContext()
				.getBean("logisticsServiceImp");
		DynaActionForm dform = (DynaActionForm) form;
		LogisticsInfoDto info = (LogisticsInfoDto) dform.get("logistics");
		info = logisticsService.showUpdateLogistics(Integer.parseInt(request
				.getParameter("id")));
		provinceCityService = (IProvinceCityService) getWebApplicationContext()
				.getBean("provinceCityServiceImp");

		request.setAttribute("logisticsInfo", info);

		return mapping.findForward("logisticsLook");
	}

}
