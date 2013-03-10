/**
 * ClassName  ReceiveInvoiceAction
 *
 * History
 * Create User: ChenHuajiang
 * Create Date: 2010-6-9
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.action.invoice;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.NewBaseAction;
import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.constant.Constants;
import cn.com.thtf.egov.cms.dto.ReceiveInvoiceDto;
import cn.com.thtf.egov.cms.entity.ReceiveInvoiceDetailEntity;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.form.InStockSearchForm;
import cn.com.thtf.egov.cms.form.ReceiveInvoiceForm;
import cn.com.thtf.egov.cms.iservice.common.ICommonService;
import cn.com.thtf.egov.cms.iservice.invoice.IReceiveInvoiceService;

/**
 * 收票管理
 * 
 * @author ChenHuajiang
 */

public class ReceiveInvoiceAction extends NewBaseAction {
    private static Logger log = LoggerFactory.getLogger(ReceiveInvoiceAction.class);
    private IReceiveInvoiceService invoiceService = null;
    private ICommonService commonService = null;

    /**
     * 收票管理列表
     * 
     * @author ChenHuajiang
     */
    @SuppressWarnings("unchecked")
    public ActionForward getReceiveInvoiceList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},收票管理列表【开始】", user.getId());

        invoiceService = (IReceiveInvoiceService) getBean("receiveInvoiceServiceImpl");
        commonService = (ICommonService) getBean("commonServiceImpl");

        ReceiveInvoiceForm invoiceForm = (ReceiveInvoiceForm) form;
        invoiceForm.setUserId(user.getId());
        invoiceForm.setRoleId(user.getRoleId() + "");

        // 分页
        NewPage page = getNewPage(request);
        page.setUrl("getReceiveInvoiceList.do");
        setPageQuery(invoiceForm, page);

        // 收票列表数据
        List list = null;
        list = invoiceService.getReceiveInvoiceList(invoiceForm, page);

        request.setAttribute("NewPage", page);
        request.setAttribute("userAction", setUserAction(user));
        request.setAttribute("searchForm", invoiceForm);
        // 产品分类列表
        request.setAttribute("productTypeList", commonService.getAllProductTypes(null,
                user.getId(), user.getRoleId() + ""));
        request.setAttribute("invoiceList", list);
        log.info("User:{},收票管理列表【结束】", user.getId());
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 采购发票录入
     * 
     * @author ChenHuajiang
     */
    public ActionForward addReceiveInvoice(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},采购发票录入【开始】", user.getId());

        invoiceService = (IReceiveInvoiceService) getBean("receiveInvoiceServiceImpl");
        commonService = (ICommonService) getBean("commonServiceImpl");

        ReceiveInvoiceForm invoiceForm = (ReceiveInvoiceForm) form;
        // 从共通里获取收票编号
        invoiceForm.setReceiveInvoiceId(commonService.getSerialNumber("SP",
                "receive_invoice"));
        invoiceForm.setUserId(user.getId());
        invoiceForm.setUserName(user.getName());
        invoiceForm.setStatus("1");
        invoiceForm.setBackDate(null);

        boolean isExist = true;
        isExist = invoiceService.isExistByNumber(invoiceForm.getInvoiceNo());
        if (isExist) {
            log.info("User:{},发票号已存在【失败】", user.getId());
            request.setAttribute("msg", "采购发票录入失败：发票号已存在");
            request.setAttribute("invoiceForm", invoiceForm);
            return mapping.findForward(Constants.FAIL);
        }
        Integer intCnt = 0;
        intCnt = invoiceService.addReceiveInvoice(invoiceForm);
        if (intCnt > 0) {
            log.info("User:{},采购发票录入【结束】", user.getId());
            return mapping.findForward(Constants.SUCCESS);
        } else {
            log.info("User:{},采购发票录入【失败】", user.getId());
            request.setAttribute("msg", "采购发票录入失败");
            request.setAttribute("invoiceForm", invoiceForm);
            return mapping.findForward(Constants.FAIL);
        }
    }

    /**
     * 采购发票查看
     * 
     * @author ChenHuajiang
     */
    @SuppressWarnings("unchecked")
    public ActionForward getInvoiceInfo(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        invoiceService = (IReceiveInvoiceService) getBean("receiveInvoiceServiceImpl");

        String id = request.getParameter("receiveInvoiceId");
        String flag = request.getParameter("flag");

        ReceiveInvoiceDto dto = null;
        dto = invoiceService.getInvoiceInfoById(id);
        request.setAttribute("dto", dto);
        List list = null;
        list = invoiceService.getInvoiceProdList(id);
        request.setAttribute("list", list);

        if (flag.equals("appoint")) {// 发票指定
            return mapping.findForward(Constants.SUCCESS);
        }
        if (flag.equals("view")) {// 发票查看
            return mapping.findForward(Constants.VIEW);
        }
        return null;
    }

    /**
     * 采购发票录入页面
     * 
     * @author ChenHuajiang
     */
    @SuppressWarnings("unchecked")
    public ActionForward goReceiveInvoicePage(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},采购发票录入页面【开始】", user.getId());

        // 产品类型列表
        commonService = (ICommonService) getBean("commonServiceImpl");

        // 产品分类列表
        List list = null;
        list = commonService
                .getAllProductTypes(null, user.getId(), user.getRoleId() + "");
        request.setAttribute("productTypeList", list);

        log.info("User:{},采购发票录入页面【结束】", user.getId());
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 获取供应商信息
     * 
     * @author ChenHuajiang
     */
    @SuppressWarnings("unchecked")
    public ActionForward getSuppliersList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},获取供应商信息【开始】", user.getId());

        invoiceService = (IReceiveInvoiceService) getBean("receiveInvoiceServiceImpl");

        // 分页
        NewPage page = getNewPage(request);
        page.setUrl("getSuppliersList.do");
        String name = request.getParameter("supplierName");
        page.setQuery("supplierName", name);

        // 供应商信息
        List list = null;
        list = invoiceService.getSuppliers(name, page);

        request.setAttribute("NewPage", page);
        request.setAttribute("supplierName", name);
        request.setAttribute("supplierList", list);
        log.info("User:{},获取供应商信息【结束】", user.getId());
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 采购发票指定--产品选择
     * 
     * @author ChenHuajiang
     */
    @SuppressWarnings("unchecked")
    public ActionForward getInStockProdList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},采购发票指定--产品选择【开始】", user.getId());

        invoiceService = (IReceiveInvoiceService) getBean("receiveInvoiceServiceImpl");

        String prodTypeId = request.getParameter("prodTypeId");
        String prodTypeName = request.getParameter("prodTypeName");
        InStockSearchForm inStockForm = (InStockSearchForm) form;
        inStockForm.setUserId(user.getId());
        inStockForm.setRoleId(user.getRoleId() + "");
        inStockForm.setProdTypeId(prodTypeId);
        inStockForm.setProdTypeName(prodTypeName);
        // 分页
        NewPage page = getNewPage(request);
        page.setUrl("getInStockProdList.do");
        page.setQuery("inStockId",inStockForm.getInStockId());
        page.setQuery("prodTypeId", inStockForm.getProdTypeId());
        page.setQuery("prodTypeName", inStockForm.getProdTypeName());
        page.setQuery("prodContractCode", inStockForm.getProdContractCode());
        page.setQuery("compContractCode", inStockForm.getCompContractCode());
        page.setQuery("prodCode", inStockForm.getProdCode());
        page.setQuery("prodName", inStockForm.getProdName());
        page.setQuery("prodType", inStockForm.getProdType());
        page.setQuery("supplierId", inStockForm.getSupplierId());

        // 收票列表数据
        List list = null;
        list = invoiceService.getInStockProdList(inStockForm, page);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        request.setAttribute("date", sdf.format(new Date()));
        request.setAttribute("NewPage", page);
        request.setAttribute("inStockForm", inStockForm);

        request.setAttribute("prodList", list);
        log.info("User:{},采购发票指定--产品选择【结束】", user.getId());
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 根据收货发票ID删除记录
     * 
     * @author ChenHuajiang
     */
    public ActionForward removeRecordById(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},根据收货发票ID删除记录【开始】", user.getId());

        invoiceService = (IReceiveInvoiceService) getBean("receiveInvoiceServiceImpl");
        String id = request.getParameter("reInvoiceId");

        Integer intCnt = 0;
        intCnt = invoiceService.getRecordCountById(id);

        if (intCnt > 0) {// 判断收票明细里是否有记录
            log.info("User:{},根据收货发票ID删除记录【失败】：该记录已被使用", user.getId());
            request.setAttribute("msg", "删除失败：该记录已被使用");
        } else {
            Integer intDelCnt = 0;
            intDelCnt = invoiceService.delRecordById(id);
            if (intDelCnt == 0) {
                log.info("User:{},根据收货发票ID删除记录【失败】", user.getId());
                request.setAttribute("msg", "删除失败");
            }
        }

        log.info("User:{},根据收货发票ID删除记录【结束】", user.getId());
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 采购发票指定
     * 
     * @author HanHaiyun
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addReceiveInvoiceAppoint(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},采购发票指定【开始】", user.getId());

        String receiveInvoiceId = request.getParameter("receiveInvoiceId");// 收票流水号
        Integer receiveType=new Integer(request.getParameter("receiveType"));//发票类型
        String receiveRate=request.getParameter("receiveRate");//发票税率
        String[] inStockIds = request.getParameterValues("inStockId");// 入库单号
        String[] productIds = request.getParameterValues("prodid");// 产品编号
        String[] productPrices=request.getParameterValues("price");
        String[] invoiceCounts = request.getParameterValues("receiveInvoiceCount");// 发票数量
        String[] invoiceMoneys = request.getParameterValues("receiveInvoiceMoney");// 发票金额
        String[] taxRate=request.getParameterValues("taxRate");//税率
        String[] prodMoney=request.getParameterValues("prodMoney");//产品库存金额
        //add by liuqg  添加发票金额判断
        BigDecimal invoiceMoney = new BigDecimal(request.getParameter("invoiceMoney"));
        BigDecimal sumBigDecimal = new BigDecimal("0.00000");
        for (int i=0;i<invoiceCounts.length;i++){
            BigDecimal tmpPrice = new BigDecimal(productPrices[i]);
            BigDecimal tmpcount = new BigDecimal(invoiceCounts[i]);
            sumBigDecimal =sumBigDecimal.add(tmpPrice.multiply(tmpcount));
        }
        if (!invoiceMoney.equals(sumBigDecimal)){
            log.error("User:{},采购发票指定【失败】:{}", user.getId(), "收票金额合计必须等于发票金额");
            //return mapping.findForward(Constants.SUCCESS);
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&isInvoice=true&invoiceMoneyCheck=false");
            return mapping.findForward(Constants.FAIL);
        }
        try {
            List<ReceiveInvoiceDetailEntity> receiveInvoiceDetailEntitys = new ArrayList<ReceiveInvoiceDetailEntity>();
            for (int i = 0; i < inStockIds.length; i++) {
                ReceiveInvoiceDetailEntity receiveInvoiceDetail = new ReceiveInvoiceDetailEntity();
                receiveInvoiceDetail.setInStockId(inStockIds[i]);
                receiveInvoiceDetail.setProductId(Integer.parseInt(productIds[i]));
                receiveInvoiceDetail.setReceiveInvoiceId(receiveInvoiceId);
                if (invoiceCounts[i].contains(".")) {
                    invoiceCounts[i] = invoiceCounts[i].substring(0, invoiceCounts[i]
                            .indexOf("."));
                }
                receiveInvoiceDetail.setCount(new Integer(invoiceCounts[i]));
                receiveInvoiceDetail.setMoney(new BigDecimal(invoiceMoneys[i]));
                if(receiveType==1){//1表示增值税
                    BigDecimal price=new BigDecimal(productPrices[i]);
                    receiveInvoiceDetail.setProdPrice(price);
                    receiveInvoiceDetail.setTaxRate(taxRate[i]);
                    receiveInvoiceDetail.setProdMoney(prodMoney[i]);
                }
                receiveInvoiceDetailEntitys.add(receiveInvoiceDetail);
            }
            boolean b = invoiceService.addReciveInvoiceDetail(
                    receiveInvoiceDetailEntitys,new BigDecimal(receiveRate),receiveType, receiveInvoiceId);
            if (!b) {
                request.setAttribute("redirect", request.getHeader("Referer")
                        + "&isInvoice=false");
                return mapping.findForward(Constants.FAIL);
            }
        } catch (Exception e) {
            log.error("User:{},采购发票指定【失败】:{}", user.getId(), e);
        }
        log.info("User:{},采购发票指定【结束】", user.getId());
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 根据收货发票ID更新记录(退票)
     * 
     * @author ChenHuajiang
     */
    public ActionForward modifyRecordById(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},根据收货发票ID更新记录(退票)【开始】", user.getId());

        invoiceService = (IReceiveInvoiceService) getBean("receiveInvoiceServiceImpl");
        String id = request.getParameter("reInvoiceId");

        Integer intCnt = 0;
        intCnt = invoiceService.modifyRecordById(id);
        if (intCnt == 0) {
            log.info("User:{},根据收货发票ID更新记录(退票)【失败】", user.getId());
            request.setAttribute("msg", "退票失败");
        }
        log.info("User:{},根据收货发票ID更新记录(退票)【结束】", user.getId());
        return mapping.findForward(Constants.SUCCESS);
    }

    // 设置分页查询条件
    private void setPageQuery(ReceiveInvoiceForm form, NewPage page) {
        page.setQuery("receiveInvoiceId", form.getReceiveInvoiceId());
        page.setQuery("prodTypeId", form.getProdTypeId());
        page.setQuery("supplierName", form.getSupplierName());
        page.setQuery("status", form.getStatus());
        page.setQuery("invoiceType", form.getInvoiceType());
        page.setQuery("invoiceNo", form.getInvoiceNo());
        page.setQuery("receiveStartDate", form.getReceiveStartDate());
        page.setQuery("receiveEndDate", form.getReceiveEndDate());
        page.setQuery("backStartDate", form.getBackStartDate());
        page.setQuery("backEndDate", form.getBackEndDate());
        page.setQuery("userName", form.getUserName());
        page.setQuery("inStockId", form.getInStockId());
    }

    /**
     * 设置用户的操作权限
     * 
     * @author ChenHuajiang
     * @param user
     * @return 可以操作的功能
     */
    private String setUserAction(UserEntity user) {
        log.info("设置用户的操作权限");
        if (user == null) {
            return null;
        }

        Integer roleId = user.getRoleId();
        // 采购专员
        if (roleId == Constants.ROLE_PROCUREMENT_COMMISSIONER) {
            return "all";
        }

        // 产品总监、采购主管、法务专员、运营总监助理、运营总监、总经理
        if (roleId == Constants.ROLE_PRODUCT_DIRECTOR
                || roleId == Constants.ROLE_PROCUREMENT_OFFICER
                || roleId == Constants.ROLE_COMPLIANCE_OFFICER
                || roleId == Constants.ROLE_AST_DIRECTOR_OF_OPERATIONS
                || roleId == Constants.ROLE_DIRECTOR_OF_OPERATIONS
                || roleId == Constants.ROLE_GENERAL_MANAGER) {
            return "read";
        }
        return null;
    }
}
