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
import cn.com.thtf.egov.cms.dto.ProductSerieInfoDto;
import cn.com.thtf.egov.cms.iservice.IProductSerieService;
import cn.com.thtf.egov.cms.iservice.IProductTypeService;

/**
 * @author Administrator
 * 
 */

public class ProductSerieAction extends BaseAction {
    @SuppressWarnings("unused")
    private static Logger log = LoggerFactory.getLogger(ProductSerieAction.class);

    private IProductSerieService ipserie;

    private IProductTypeService itdept;

    /**
     * 产品系列列表
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */

    @SuppressWarnings("unchecked")
    public ActionForward productSerieAll(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaActionForm dform = (DynaActionForm) form;
        ProductSerieInfoDto psinfo = (ProductSerieInfoDto) dform.get("productserie");
        ipserie = (IProductSerieService) getWebApplicationContext().getBean(
                "productSerieServiceImp");
        itdept = (IProductTypeService) getWebApplicationContext().getBean(
                "productTypeServiceImp");
        // 产品分类
        List tlist = itdept.fingProductTypeAll();
        request.setAttribute("tlist", tlist);
        if (psinfo.getTname() != null) {
            psinfo.setTno(psinfo.getTname());
        }
        if (psinfo.getTno() != null) {
            if (psinfo.getTno().equals("0")) {
                psinfo.setTno(null);
            }
        }
        if (psinfo.getNum() != null) {
            if (psinfo.getNum() != "") {
                String num = psinfo.getNum();

                String sno = num.substring(0, 2);
                psinfo.setTno(sno);
                String nos = num.substring(3, 6);
                psinfo.setNo(nos);
            }
        }
        ListRange list = ipserie.queryProductSerieAll(psinfo, getPage(request));
        request.setAttribute("productserielist", list);
        request.setAttribute("Page", list.getPage());
        request.setAttribute("psinfo", psinfo);
        return mapping.findForward("productSerieAll");
    }

    /**
     * 根据产品大类Id查询数据库中最大的系列no
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getMaxSerNobyTypeId(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        ipserie = (IProductSerieService) getWebApplicationContext().getBean(
                "productSerieServiceImp");
        String typeid = request.getParameter("typeid");
        String no = ipserie.maxProductSerie(typeid);
        if (no != null) {

            Integer maxno = Integer.valueOf(no) + 1;
            int i = no.length() - String.valueOf(maxno).length();
            StringBuffer sb = new StringBuffer();
            for (int j = 0; j < i; j++) {
                sb.append("0");
            }
            // no = sb.toString() + maxno + "";
            no = sb.append(maxno.toString()).toString();
        } else {
            no = "001";
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
    public ActionForward showProductSerieAdd(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        // DynaActionForm dform = (DynaActionForm) form;
        ipserie = (IProductSerieService) getWebApplicationContext().getBean(
                "productSerieServiceImp");
        itdept = (IProductTypeService) getWebApplicationContext().getBean(
                "productTypeServiceImp");
        List list = itdept.fingProductTypeAll();
        request.setAttribute("ptypename", list);
        return mapping.findForward("showProductSerieAdd");
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
    public ActionForward showProductSerieUpdate(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaActionForm dform = (DynaActionForm) form;
        ipserie = (IProductSerieService) getWebApplicationContext().getBean(
                "productSerieServiceImp");
        ProductSerieInfoDto psinfo = (ProductSerieInfoDto) dform.get("productserie");
        itdept = (IProductTypeService) getWebApplicationContext().getBean(
                "productTypeServiceImp");
        saveToken(request);
        // List list = itdept.fingProductTypeAll();
        // request.setAttribute("ptypename", list);
        if (psinfo.getId() == null) {
            psinfo = ipserie.showUpdate(Integer.parseInt(request.getParameter("id")));
        } else {
            psinfo = ipserie.showUpdate(Integer.parseInt(psinfo.getId()));
        }
        psinfo.setNum(psinfo.getTno() + "." + psinfo.getNo());
        request.setAttribute("productMsg", psinfo);
        return mapping.findForward("showProductSerieUpdate");
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
    public ActionForward addProductSerie(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        ipserie = (IProductSerieService) getWebApplicationContext().getBean(
                "productSerieServiceImp");
        itdept = (IProductTypeService) getWebApplicationContext().getBean(
                "productTypeServiceImp");
        ProductSerieInfoDto psinfo1 = new ProductSerieInfoDto();

        String idNo = request.getParameter("productserie.product_type_id1");
        String id = idNo.split(",")[0];
        psinfo1.setProduct_type_id(id);
        psinfo1.setName(request.getParameter("productserie.name1"));
        Integer same = ipserie.isExit(psinfo1);

        if (same != 0) {
            request.setAttribute("err", "添加失败！");
            return showProductSerieAdd(mapping, form, request, response);
        } else {
            psinfo1.setNo(request.getParameter("max"));
            ipserie.addProductSerie(psinfo1);
        }
        return showProductSerieAdd(mapping, form, request, response);
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
    public ActionForward updateProductSerie(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        ipserie = (IProductSerieService) getWebApplicationContext().getBean(
                "productSerieServiceImp");
        ProductSerieInfoDto psinfo1 = new ProductSerieInfoDto();

        // String idNo = request.getParameter("productserie.product_type_id1");
        // String id = idNo.split(",")[0];
        psinfo1.setProduct_type_id(request.getParameter("productserie.product_type_id"));
        psinfo1.setName(request.getParameter("productserie.name1"));
        // psinfo1.setNo(request.getParameter("productserie.no1"));
        psinfo1.setId(request.getParameter("productserie.id"));
        Integer same = ipserie.isExit1(psinfo1);
        String productserieIdStr = request.getParameter("productserie.id");

        if (same == null) {
            ipserie.updateProductSerie(psinfo1);
        } else if (same.intValue() == Integer.parseInt(productserieIdStr)) {
            ipserie.updateProductSerie(psinfo1);
        } else {
            request.setAttribute("err", "修改失败！");
            return showProductSerieUpdate(mapping, form, request, response);
        }

        // if (StringUtils.isEmpty(productserieIdStr)
        // || Integer.parseInt(productserieIdStr) != same.intValue()) {
        // request.setAttribute("err", "修改失败！");
        // return showProductSerieUpdate(mapping, form, request, response);
        // } else {
        // ipserie.updateProductSerie(psinfo1);
        // }
        return mapping.findForward("updateProductSerie");
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
    public ActionForward deleteProductSerie(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        // DynaActionForm dform = (DynaActionForm) form;
        ipserie = (IProductSerieService) getWebApplicationContext().getBean(
                "productSerieServiceImp");
        // ProductSerieInfoDto psinfo = (ProductSerieInfoDto)
        // dform.get("productserie");
        String idStr = request.getParameter("id");
        // String[] id = idStr.split(",");
        String result = null;
        // for (int i = 0; i < id.length; i++) {
        // result = ipserie.deleteProductSerie(Integer.parseInt(id[i]));
        // psinfo.setName(request.getParameter("name").equals("") ? null
        // : new String(request.getParameter("name").toString()
        // .getBytes("ISO-8859-1"), "utf-8"));
        // psinfo.setTname(request.getParameter("tname").equals("") ? null
        // : new String(request.getParameter("tname").toString()
        // .getBytes("ISO-8859-1"), "utf-8"));
        // psinfo.setNum(request.getParameter("num").equals("") ? null
        // : request.getParameter("num").toString());
        // request.setAttribute("psinfo", psinfo);
        result = ipserie.deleteProductSerie(idStr);
        if (result != null) {
            request.setAttribute("err", result);
            return productSerieAll(mapping, form, request, response);
        }
        // }

        return productSerieAll(mapping, form, request, response);
    }

    /**
     * 检索
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public ActionForward checkProductSerie(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaActionForm dform = (DynaActionForm) form;
        ipserie = (IProductSerieService) getWebApplicationContext().getBean(
                "productSerieServiceImp");
        ProductSerieInfoDto psinfo = (ProductSerieInfoDto) dform.get("productserie");
        itdept = (IProductTypeService) getWebApplicationContext().getBean(
                "productTypeServiceImp");
        // 产品分类
        List tlist = itdept.fingProductTypeAll();
        request.setAttribute("tlist", tlist);
        if (psinfo.getTname() == "") {
            psinfo.setTname(null);
        }
        if (psinfo.getTno() == "") {
            psinfo.setTno(null);
        }
        if (psinfo.getName() == "") {
            psinfo.setName(null);
        }
        if (psinfo.getNum() == "") {
            psinfo.setNum(null);
        }
        if (request.getParameter("productserie.num") != "") {
            String num = request.getParameter("productserie.num");

            String sno = num.substring(0, 2);
            psinfo.setTno(sno);
            String nos = num.substring(3, 6);
            psinfo.setNo(nos);
        }
        if (!request.getParameter("productserie.tname").equals("0")) {
            psinfo.setTno(request.getParameter("productserie.tname"));
        }
        ListRange list = ipserie.checkProductSerie(psinfo, getPage(request));
        request.setAttribute("productserielist", list);
        request.setAttribute("Page", list.getPage());
        request.setAttribute("psinfo", psinfo);
        return productSerieAll(mapping, form, request, response);
    }
}
