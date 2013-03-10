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

import java.io.PrintWriter;
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
import cn.com.thtf.egov.cms.dto.ProductInfoDto;
import cn.com.thtf.egov.cms.dto.ProductSerieInfoDto;
import cn.com.thtf.egov.cms.iservice.IProductSerieService;
import cn.com.thtf.egov.cms.iservice.IProductService;
import cn.com.thtf.egov.cms.iservice.IProductTypeService;

/**
 * @author Administrator
 * 
 */

public class ProductAction extends BaseAction {
	@SuppressWarnings("unused")
	private static Logger log = LoggerFactory.getLogger(ProductAction.class);

	private IProductSerieService ipserie;

	private IProductTypeService itdept;

	private IProductService iproduct;

	/**
	 * 产品列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */

	@SuppressWarnings("unchecked")
	public ActionForward productAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DynaActionForm dform = (DynaActionForm) form;
		ProductInfoDto pinfo = (ProductInfoDto) dform.get("product");
		ipserie = (IProductSerieService) getWebApplicationContext().getBean(
				"productSerieServiceImp");
		iproduct = (IProductService) getWebApplicationContext().getBean(
				"productServiceImp");
		itdept = (IProductTypeService) getWebApplicationContext().getBean(
				"productTypeServiceImp");
		// 查询全部产品分类
		List tlist = itdept.fingProductTypeAll();

		Integer tId = 0;
		String sId = null;
		if (pinfo.getProductcode() != null
				&& pinfo.getProductcode().length() > 0) {
			pinfo.setTno(pinfo.getProductcode().substring(0, 2));
			pinfo.setSno(pinfo.getProductcode().substring(3, 6));
			pinfo.setNo(pinfo.getProductcode().substring(7, 11));
		}
		if (pinfo.getTname() != null && pinfo.getTname().length() > 0) {
			tId = Integer.valueOf(pinfo.getTname().split(",")[0]);
			if (tId != 0) {
				pinfo.setTid(tId);
				pinfo.setTno(pinfo.getTname().split(",")[1]);
			}
		}
		if (pinfo.getSname() != null && pinfo.getSname().length() > 0) {
			sId = pinfo.getSname().split(",")[0];
			if (!"0".equals(sId)) {
				pinfo.setProduct_series_id(sId);
				pinfo.setSno(pinfo.getSname().split(",")[1]);
			}
		}

		// 根据产品分类Id查询所有产品系列
		List slist = ipserie.productSerieNameAll(tId);
		ListRange list = iproduct.queryProductAll(getPage(request), pinfo);

		request.setAttribute("tlist", tlist);// 产品分类List
		request.setAttribute("slist", slist);
		request.setAttribute("productlist", list);
		request.setAttribute("Page", list.getPage());
		request.setAttribute("pinfo", pinfo);
		return mapping.findForward("productAll");
	}

	/**
	 * 根据产品大类和系列获取数据库总产品编号最大值
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getMaxProductNoByPSId(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		iproduct = (IProductService) getWebApplicationContext().getBean(
				"productServiceImp");
		String tidno = request.getParameter("tidno");
		String sidno = request.getParameter("sidno");
		String tid = "";
		String sid = "";
		if (!"".equals(tidno)) {
			tid = tidno.split(",")[0];
		}
		if (!"".equals(sidno)) {
			sid = sidno.split(",")[0];
		}
		ProductInfoDto info = new ProductInfoDto();
		info.setProduct_type_id(tid);
		info.setProduct_series_id(sid);
		String no = iproduct.autoProductNO(info);

		if (no != null) {

			Integer maxno = Integer.valueOf(no) + 1;
			int i = no.length() - String.valueOf(maxno).length();
			StringBuffer sb = new StringBuffer();
			for (int j = 0; j < i; j++) {
				sb.append("0");
			}
			// no = sb.toString() + maxno + "";
			no = sb.append(maxno.toString()).toString();
			//
			// request.setAttribute("maxno", no);
		} else {
			no = "0001";
			// request.setAttribute("maxno", "0001");
		}

		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		StringBuffer city = new StringBuffer("<ser>");
		city.append("<maxno>" + no + "</maxno>");
		city.append("</ser>");
		// System.out.println(city.toString());
		PrintWriter out = response.getWriter();
		out.print(city.toString());
		out.flush();
		out.close();
		return null;
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
	@SuppressWarnings("unchecked")
	public ActionForward showProductAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ipserie = (IProductSerieService) getWebApplicationContext().getBean(
				"productSerieServiceImp");
		iproduct = (IProductService) getWebApplicationContext().getBean(
				"productServiceImp");
		itdept = (IProductTypeService) getWebApplicationContext().getBean(
				"productTypeServiceImp");
		// 产品分类
		List tlist = itdept.fingProductTypeAll();
		request.setAttribute("tlist", tlist);
		// 产品系列
		// List slist = ipserie.productSerieNameAll();
		// request.setAttribute("slist", slist);
		return mapping.findForward("showProductAdd");
	}

	/**
	 * 根据产品分类Id查询系列列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward querySerieByTypeId(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ipserie = (IProductSerieService) getWebApplicationContext().getBean(
				"productSerieServiceImp");
		String typeIdNo = request.getParameter("typeId");
		String typeId = "0";
		if (!"".equals(typeIdNo)) {
			typeId = typeIdNo.split(",")[0];
		}
		List slist = ipserie.productSerieNameAll(Integer.valueOf(typeId));
		request.setAttribute("slist", slist);
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		StringBuffer serie = new StringBuffer("<series>");
		String deptName = "";
		String deptAccount = "";
		String deptFax = "";
		String typename = "";
		String typeid = "";
		for (int i = 0; i < slist.size(); i++) {
			ProductSerieInfoDto info = (ProductSerieInfoDto) slist.get(i);
			serie.append("<serieIdNo>" + info.getSsIdNo() + "</serieIdNo>");
			serie.append("<serieName>" + info.getName() + "</serieName>");
			deptName = info.getDeptName();
			deptAccount = info.getDeptAccount();
			deptFax = info.getDeptFax();
			typename = info.getTname();
			typeid = info.getProduct_type_id();

		}
		serie.append("<deptName>" + deptName + "</deptName>");
		serie.append("<deptAccount>" + deptAccount + "</deptAccount>");
		serie.append("<deptFax>" + deptFax + "</deptFax>");
		serie.append("<typename>" + typename + "</typename>");
		serie.append("<typeid>" + typeid + "</typeid>");
		serie.append("</series>");
		// System.out.println(serie.toString());
		PrintWriter out = response.getWriter();
		out.print(serie.toString());
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 添加产品信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward productAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ProductInfoDto pinfos = new ProductInfoDto();
		iproduct = (IProductService) getWebApplicationContext().getBean(
				"productServiceImp");
		String serieId = request.getParameter("product.sname1").split(",")[0];
		String sno = request.getParameter("product.sname1").split(",")[1];
		pinfos.setProduct_series_id(serieId);
		String tid = request.getParameter("product.tname1").split(",")[0];
		String tno = request.getParameter("product.tname1").split(",")[1];
		String pno = request.getParameter("ssno");
		String limitPrice = request.getParameter("limitPrice");
		pinfos.setLimitPrice(limitPrice);
		pinfos.setTid(Integer.parseInt(tid));
		pinfos.setName(request.getParameter("name1"));
		pinfos.setNo(pno);
		StringBuffer sb = new StringBuffer();
		sb.append(tno);
		sb.append(".");
		sb.append(sno);
		sb.append(".");
		sb.append(pno);
		pinfos.setProductcode(sb.toString());
		// pinfos.setProductcode(tno + "." + sno + "." + pno);
		pinfos.setType(request.getParameter("type"));
		pinfos.setUnit(request.getParameter("unit"));

		Integer same = iproduct.isExit(pinfos);
		if (same != 0) {
			request.setAttribute("err", "添加失败！");
			return showProductAdd(mapping, form, request, response);
		}
		String result = iproduct.addProduct(pinfos);
		if (result != null) {
			request.setAttribute("err", result);
			return showProductAdd(mapping, form, request, response);
		} else {
			return this.showProductAdd(mapping, form, request, response);
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
	public ActionForward showProductUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DynaActionForm dform = (DynaActionForm) form;
		ProductInfoDto pinfo = (ProductInfoDto) dform.get("product");
		ipserie = (IProductSerieService) getWebApplicationContext().getBean(
				"productSerieServiceImp");
		iproduct = (IProductService) getWebApplicationContext().getBean(
				"productServiceImp");
		itdept = (IProductTypeService) getWebApplicationContext().getBean(
				"productTypeServiceImp");
		// 产品分类
		// List tlist = itdept.fingProductTypeAll();
		// request.setAttribute("tlist", tlist);
		// 产品系列
		// List slist = ipserie.productSerieAll();

		if (pinfo.getId() != null) {
			pinfo = iproduct.showUpdate(pinfo.getId());
		} else {
			pinfo = iproduct.showUpdate(Integer.parseInt(request
					.getParameter("id")));
		}
		// List slist = ipserie.productSerieNameAll(pinfo.getTid());
		// request.setAttribute("slist", slist);
		request.setAttribute("pinfoMsg", pinfo);
		return mapping.findForward("showProductUpdate");
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
	public ActionForward saveProduct(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ProductInfoDto pinfos = new ProductInfoDto();
		iproduct = (IProductService) getWebApplicationContext().getBean(
				"productServiceImp");
		String sid = request.getParameter("product.product_serie_id1");
		String tid = request.getParameter("product.product_type_id1");
		String tno = request.getParameter("tno");
		String sno = request.getParameter("sno");
		String pno = request.getParameter("ssno");
		String limitPrice=request.getParameter("limitPrice");
		pinfos.setProduct_type_id(tid);
		pinfos.setProduct_series_id(sid);
		pinfos.setName(request.getParameter("product.name1"));
		pinfos.setType(request.getParameter("product.type1"));
		pinfos.setLimitPrice(limitPrice);
		StringBuffer sb = new StringBuffer();
		sb.append(tno);
		sb.append(".");
		sb.append(sno);
		sb.append(".");
		sb.append(pno);
		pinfos.setProductcode(sb.toString());
		// pinfos.setProductcode(tno + "." + sno + "." + pno);
		pinfos.setUnit(request.getParameter("product.unit1"));
		pinfos.setId(Integer.parseInt(request.getParameter("product.ids")));
		Integer ssid = iproduct.isExit1(pinfos);
		String result=null;
		if (ssid != null) {
			if (ssid.equals(Integer.parseInt(request
					.getParameter("product.ids")))) {
				result=iproduct.updateProduct(pinfos);
			} else {
				request.setAttribute("err", "错误");
				return showProductUpdate(mapping, form, request, response);
			}
		} else {
			result=iproduct.updateProduct(pinfos);
			
		}
		if(result!=null){
			request.setAttribute("err", result);
			return showProductUpdate(mapping, form, request, response);
		}
		// String ss = iproduct.updateProduct(pinfos);
		// if (ss != null) {
		// request.setAttribute("err", ss);
		// return showProductUpdate(mapping, form, request, response);

		// } else {
		return mapping.findForward("ProductUpdate");
		// }
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
	@SuppressWarnings("unchecked")
	public ActionForward productDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm dform = (DynaActionForm) form;
		ProductInfoDto pinfo = (ProductInfoDto) dform.get("product");
		iproduct = (IProductService) getWebApplicationContext().getBean(
				"productServiceImp");
		String idStr = request.getParameter("id");
		// String[] id = idStr.split(",");
		// String result = "";
		// for (int i = 0; i < id.length; i++) {
		// try {
		// iproduct.deleteProduct(Integer.parseInt(id[i]));
		// } catch (Exception e) {
		// result = "删除失败！";
		// }
		// }
		String result = iproduct.deleteProduct(idStr);
		ipserie = (IProductSerieService) getWebApplicationContext().getBean(
				"productSerieServiceImp");
		iproduct = (IProductService) getWebApplicationContext().getBean(
				"productServiceImp");
		itdept = (IProductTypeService) getWebApplicationContext().getBean(
				"productTypeServiceImp");
		// 查询全部产品分类
		List tlist = itdept.fingProductTypeAll();

		Integer tId = 0;
		String sId = null;
		if (pinfo.getProductcode() != null
				&& pinfo.getProductcode().length() > 0) {
			pinfo.setTno(pinfo.getProductcode().substring(0, 2));
			pinfo.setSno(pinfo.getProductcode().substring(3, 6));
			pinfo.setNo(pinfo.getProductcode().substring(7, 11));
		}
		if (pinfo.getTname() != null && pinfo.getTname().length() > 0) {
			tId = Integer.valueOf(pinfo.getTname().split(",")[0]);
			if (tId != 0) {
				pinfo.setTid(tId);
				pinfo.setTno(pinfo.getTname().split(",")[1]);
			}
		}
		if (pinfo.getSname() != null && pinfo.getSname().length() > 0) {
			sId = pinfo.getSname().split(",")[0];
			if (!"0".equals(sId)) {
				pinfo.setProduct_series_id(sId);
				pinfo.setSno(pinfo.getSname().split(",")[1]);
			}
		}
		// 根据产品分类Id查询所有产品系列
		List slist = ipserie.productSerieNameAll(tId);
		ListRange list = iproduct.queryProductAll(getPage(request), pinfo);
		request.setAttribute("err", result);
		request.setAttribute("tlist", tlist);// 产品分类List
		request.setAttribute("slist", slist);
		request.setAttribute("productlist", list);
		request.setAttribute("Page", list.getPage());
		request.setAttribute("pinfo", pinfo);
		return mapping.findForward("productAll");
	}
}
