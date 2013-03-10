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
import cn.com.thtf.egov.cms.constant.Constants;
import cn.com.thtf.egov.cms.dto.CityInfoDto;
import cn.com.thtf.egov.cms.dto.CustomerInfoDto;
import cn.com.thtf.egov.cms.dto.SupplierInfoDto;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.iservice.ICustomerService;
import cn.com.thtf.egov.cms.iservice.IProvinceCityService;

/**
 * 客户Action
 * 
 * @author sxf
 * 
 */

public class CustomerAction extends BaseAction {
    private static Logger log = LoggerFactory.getLogger(CustomerAction.class);

    private ICustomerService customerService;

    private IProvinceCityService provinceCityService;// 省市业务类

    /**
     * 客户列表
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
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaActionForm dform = (DynaActionForm) form;
        CustomerInfoDto info = (CustomerInfoDto) dform.get("supplier");
        customerService = (ICustomerService) getWebApplicationContext().getBean(
                "customerServiceImp");
        UserEntity user = (UserEntity) request.getSession().getAttribute(
                Constants.USERLOGIN);
        Integer roleId = user.getRoleId();
        String userId = user.getId();
        ListRange list = null;
        user.getUserAreaId();
        if (roleId == Constants.ROLE_SALES_MANAGER) {
            list = customerService.queryCustomerByUserId(getPage(request),
                    userId.toString(), info);
        } else if (roleId == Constants.ROLE_REGIONAL_DIRECTOR) {
            list = customerService.queryCustomerByAreaManagerId(getPage(request),
                    userId.toString(), info);
        } else if (roleId == 19 || roleId == 20) {
            list = customerService.queryCustomerByUserAreaMapping(getPage(request),
                    userId.toString(), info);
        } else {
            list = customerService.queryAllCustomer(getPage(request), info);
        }

        provinceCityService = (IProvinceCityService) getWebApplicationContext().getBean(
                "provinceCityServiceImp");
        List provinceList = provinceCityService.queryAllProvince();// 查询所有省
        List areaList = provinceCityService.queryAllArea();// 查询所有区域
        List cityList = provinceCityService.queryAllCityByProvinceId(info.getProvince());// 根据省名字查询所有市

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
     * 客户查看
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
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        CustomerInfoDto info = null;
        customerService = (ICustomerService) getWebApplicationContext().getBean(
                "customerServiceImp");
        List linkList = customerService.queryCustomerLinkmanListById(Integer.valueOf(id));
        List addressList = customerService.queryCustomerAddressListById(Integer
                .valueOf(id));
        info = customerService.queryCustomerById(Integer.valueOf(id));
        request.setAttribute("linkList", linkList);
        request.setAttribute("addressList", addressList);
        request.setAttribute("supplier", info);
        return mapping.findForward("supplierLook");
    }

    /**
     * 显示客户修改
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public ActionForward showSupplierUpdate(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer id = 0;
        String id1 = request.getParameter("id");
        Integer id2 = request.getAttribute("id") == null ? null : (Integer) request
                .getAttribute("id");
        if (id1 != null && id1.length() > 0) {
            id = new Integer(id1);
        } else if (id2 != null) {
            id = id2;
        }
        saveToken(request);
        CustomerInfoDto info = null;
        customerService = (ICustomerService) getWebApplicationContext().getBean(
                "customerServiceImp");
        provinceCityService = (IProvinceCityService) getWebApplicationContext().getBean(
                "provinceCityServiceImp");
        List linkList = customerService.queryCustomerLinkmanListById(id);
        List addressList = customerService.queryCustomerAddressListById(id);
        info = customerService.queryCustomerById(id);
        request.setAttribute("linkManList", linkList);
        request.setAttribute("addressList", addressList);
        request.setAttribute("supplierInfo", info);
        List provinceList = provinceCityService.queryAllProvince();
        // 根据客户现在的省Name去查询所有City列表
        request.setAttribute("provinceList", provinceList);
        List cityList = provinceCityService.queryAllCityByProvinceId(info.getProvince());
        request.setAttribute("cityList", cityList);
        return mapping.findForward("010602modify");
    }

    /**
     * 显示客户添加页面
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public ActionForward showSupplierAdd(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        provinceCityService = (IProvinceCityService) getWebApplicationContext().getBean(
                "provinceCityServiceImp");
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
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        saveToken(request);
        String supplierId = request.getParameter("supplierId");
        String supplier = request.getParameter("suppliername");
        request.setAttribute("supplierId", supplierId);
        request.setAttribute("supplierName", supplier);
        return mapping.findForward("showLinkmanAdd");
    }

    /**
     * 显示客户发货地址添加
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward showAddressAdd(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        saveToken(request);
        String supplierId = request.getParameter("supplierId");
        String supplier = request.getParameter("suppliername");

        request.setAttribute("supplierId", supplierId);
        request.setAttribute("supplierName", supplier);
        return mapping.findForward("showAddressAdd");
    }

    /**
     * 添加客户联系人
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addSupplierLinkman(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

       // DynaActionForm dform = (DynaActionForm) form;
        //CustomerInfoDto info = (CustomerInfoDto) dform.get("supplier");
        
        CustomerInfoDto info = new CustomerInfoDto();
        info.setId(Integer.parseInt(request.getParameter("id")));  
        info.setLinkman(request.getParameter("linkman"));  
        info.setRole(request.getParameter("role"));  
        info.setTel(request.getParameter("tel"));  
        info.setFax(request.getParameter("fax"));  
        info.setMobile(request.getParameter("mobile"));  
        info.setMail(request.getParameter("mail"));  
        info.setQq(request.getParameter("qq"));  
        info.setMsn(request.getParameter("msn"));  
        
        
        customerService = (ICustomerService) getWebApplicationContext().getBean(
                "customerServiceImp");
//        /* 防止重复提交 */
//        if (isTokenValid(request)) {
//            resetToken(request);
//        } else {
//            return showSupplierUpdate(mapping, form, request, response);
//        }
        String same = customerService.addCustomerLinkman(info);
        
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        
        if (same != null) {
            log.debug("新增客户联系人出错!");
            writer.write("0");
        } else {
            writer.write("1");
        }
        //request.setAttribute("id", info.getId());
        
        //return this.showSupplierUpdate(mapping, form, request, response);
        return null;
    }

    /**
     * 添加客户发货地址
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addSupplierAddress(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        CustomerInfoDto info = new CustomerInfoDto();
        info.setId(Integer.parseInt(request.getParameter("id")));
        info.setReceiveName(request.getParameter("receiveName"));
        info.setSupplierAddress(request.getParameter("supplierAddress"));
        info.setSupplierPostcode(request.getParameter("supplierPostcode"));
        info.setSupplierLinkman(request.getParameter("supplierLinkman"));
        info.setSupplierTel(request.getParameter("supplierTel"));
        info.setSupplierMobile(request.getParameter("supplierMobile"));
        
        customerService = (ICustomerService) getWebApplicationContext().getBean(
                "customerServiceImp");
        
        request.setAttribute("id", info.getId());
        String same = customerService.addCustomerAddress(info);
        if (same != null) {
            log.debug("新增客户地址出错!");
            // System.out.println("----新增客户地址出错---");
            response.getWriter().write("false");
        }

        return null;
    }

    /**
     * 显示客户联系人修改页面
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward showLinkmanUpdate(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        customerService = (ICustomerService) getWebApplicationContext().getBean(
                "customerServiceImp");
        saveToken(request);
        String linkmanId = request.getParameter("linkmanId");
        CustomerInfoDto info = customerService.queryLinkmanByLinkmanId(Integer
                .valueOf(linkmanId));
        request.setAttribute("supplierInfo", info);
        return mapping.findForward("010602linkman_modify");
    }

    /**
     * 显示客户发货地址修改页面
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward showAddressUpdate(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        customerService = (ICustomerService) getWebApplicationContext().getBean(
                "customerServiceImp");
        saveToken(request);
        String addressId = request.getParameter("addressId");
        CustomerInfoDto info = customerService.queryAddressByAddressId(Integer
                .valueOf(addressId));
        request.setAttribute("supplierInfo", info);
        return mapping.findForward("010602address_modify");
    }

    /**
     * 客户联系人修改后保存
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward saveLinkman(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        CustomerInfoDto info = new CustomerInfoDto();
        info.setId(Integer.parseInt(request.getParameter("id")));
        info.setLinkmanId(Integer.parseInt(request.getParameter("linkmanId")));
        info.setLinkman(request.getParameter("linkman"));
        info.setRole(request.getParameter("role"));
        info.setTel(request.getParameter("tel"));
        info.setFax(request.getParameter("fax"));
        info.setMobile(request.getParameter("mobile"));
        info.setMail(request.getParameter("mail"));
        info.setMsn(request.getParameter("msn"));
        info.setQq(request.getParameter("qq"));
        customerService = (ICustomerService) getWebApplicationContext().getBean(
                "customerServiceImp");
        
        customerService.saveLinkman(info);
        request.setAttribute("id", info.getId());
        return null;
    }

    /**
     * 客户发货地址修改后保存
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward saveAddress(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        CustomerInfoDto info = new CustomerInfoDto();
        info.setId(Integer.parseInt(request.getParameter("id")));
        info.setReceiveName(request.getParameter("receiveName"));
        info.setSupplierAddress(request.getParameter("supplierAddress"));
        info.setSupplierPostcode(request.getParameter("supplierPostcode"));
        info.setSupplierLinkman(request.getParameter("supplierLinkman"));
        info.setSupplierTel(request.getParameter("supplierTel"));
        info.setSupplierMobile(request.getParameter("supplierMobile"));
        info.setReceiveName(request.getParameter("receiveName"));
        info.setReceiveId(Integer.parseInt(request.getParameter("receiveId")));
        customerService = (ICustomerService) getWebApplicationContext().getBean(
                "customerServiceImp");
       
        customerService.saveAddress(info);
        request.setAttribute("id", info.getId());
        return null;
    }

    /**
     * 客户添加
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
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaActionForm dform = (DynaActionForm) form;
        customerService = (ICustomerService) getWebApplicationContext().getBean(
                "customerServiceImp");
        provinceCityService = (IProvinceCityService) getWebApplicationContext().getBean(
                "provinceCityServiceImp");
        CustomerInfoDto info = (CustomerInfoDto) dform.get("supplier");

        List list = provinceCityService.queryAllCityByProvinceId(info.getProvince());
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
        String same = customerService.addCustomer(info);
        if (same != null) {
            request.setAttribute("err", same);
        }
        return this.showSupplierAdd(mapping, form, request, response);
    }

    /**
     * 客户修改后保存
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
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        DynaActionForm dform = (DynaActionForm) form;
        customerService = (ICustomerService) getWebApplicationContext().getBean(
                "customerServiceImp");
        provinceCityService = (IProvinceCityService) getWebApplicationContext().getBean(
                "provinceCityServiceImp");
        CustomerInfoDto info = (CustomerInfoDto) dform.get("supplier");

        UserEntity user = (UserEntity) request.getSession().getAttribute(
                Constants.USERLOGIN);
        request.setAttribute("roleId", user.getRoleId());

        List list = provinceCityService.queryAllCityByProvinceId(info.getProvince());
        CityInfoDto cityInfo = (CityInfoDto) list.get(0);
        String area = cityInfo.getAreaName();
        info.setArea(area);
        if (info.getInvoiceType() == 0)
            info.setTaxRate(0);// 普通发票，增值税为0
        /* 防止重复提交 */
        if (isTokenValid(request)) {
            resetToken(request);
            String same = customerService.saveCustomer(info);
            if (same != null) {
                request.setAttribute("err", same);
                request.setAttribute("id", info.getId());
                return showSupplierUpdate(mapping, form, request, response);
            }

        }
        // 修改完后跳转到列表页面
        CustomerInfoDto info1 = new CustomerInfoDto();
        ListRange list1 = customerService.queryAllCustomer(getPage(request), info1);
        provinceCityService = (IProvinceCityService) getWebApplicationContext().getBean(
                "provinceCityServiceImp");
        List provinceList = provinceCityService.queryAllProvince();// 查询所有省
        List areaList = provinceCityService.queryAllArea();// 查询所有区域
        List cityList = provinceCityService.queryAllCityByProvinceId(info1.getProvince());// 根据省名字查询所有市
        request.setAttribute("provinceList", provinceList);
        request.setAttribute("areaList", areaList);
        request.setAttribute("cityList", cityList);
        request.setAttribute("supplierList", list1);
        request.setAttribute("Page", list1.getPage());
        request.setAttribute("supplier", info1);
        return mapping.findForward("supplierList");
    }

    /**
     * 删除客户联系人
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteLinkman(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        customerService = (ICustomerService) getWebApplicationContext().getBean(
                "customerServiceImp");
        String idStr = request.getParameter("sxf");
        String supplierId = request.getParameter("supplierId");
        String[] id = idStr.split(",");
        for (int i = 0; i < id.length; i++) {
            customerService.deleteLinkman(Integer.parseInt(id[i]));
        }
        request.setAttribute("id", Integer.valueOf(supplierId));
        return this.showSupplierUpdate(mapping, form, request, response);
    }

    /**
     * 删除客户发货地址
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteAddress(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        customerService = (ICustomerService) getWebApplicationContext().getBean(
                "customerServiceImp");
        String idStr = request.getParameter("sxf");
        String supplierId = request.getParameter("supplierId");
        String[] id = idStr.split(",");
        for (int i = 0; i < id.length; i++) {
            customerService.deleteAddress(Integer.parseInt(id[i]));
        }
        request.setAttribute("id", Integer.valueOf(supplierId));
        return this.showSupplierUpdate(mapping, form, request, response);
    }

    /**
     * 删除客户
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
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        DynaActionForm dform = (DynaActionForm) form;
        customerService = (ICustomerService) getWebApplicationContext().getBean(
                "customerServiceImp");
        CustomerInfoDto info = (CustomerInfoDto) dform.get("supplier");
        String idStr = request.getParameter("id");
        // String[] id = idStr.split(",");
        String result = "";
        // for (int i = 0; i < id.length; i++) {
        // try {
        // customerService.deleteCustomer(Integer.parseInt(id[i]));
        // } catch (Exception e) {
        // result = "删除失败！";
        // }
        // }
        result = customerService.deleteCustomer(idStr);
        request.setAttribute("err", result);
        ListRange list = customerService.queryAllCustomer(getPage(request), info);
        provinceCityService = (IProvinceCityService) getWebApplicationContext().getBean(
                "provinceCityServiceImp");
        List provinceList = provinceCityService.queryAllProvince();// 查询所有省
        List areaList = provinceCityService.queryAllArea();// 查询所有区域
        List cityList = provinceCityService.queryAllCityByProvinceId(info.getProvince());// 根据省名字查询所有市
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
