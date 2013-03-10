package cn.com.thtf.egov.cms.action.sell;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.NewBaseAction;
import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.constant.Constants;
import cn.com.thtf.egov.cms.dto.AssessManDto;
import cn.com.thtf.egov.cms.dto.CustomerInfoDto;
import cn.com.thtf.egov.cms.dto.InvoiceDetailDto;
import cn.com.thtf.egov.cms.dto.InvoiceInfoDto;
import cn.com.thtf.egov.cms.dto.MakeInvoiceAddDto;
import cn.com.thtf.egov.cms.dto.SellInvocleOfAddDto;
import cn.com.thtf.egov.cms.dto.SendGoodsParticularListDto;
import cn.com.thtf.egov.cms.dto.WorkReceiverDto;
import cn.com.thtf.egov.cms.entity.MailEntity;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.form.InvoiceListForm;
import cn.com.thtf.egov.cms.form.MakeInvoiceAddForm;
import cn.com.thtf.egov.cms.form.SendGoodsParticularListForm;
import cn.com.thtf.egov.cms.iservice.common.ICommonService;
import cn.com.thtf.egov.cms.iservice.purchase.IBuyContractService;
import cn.com.thtf.egov.cms.iservice.sell.IInvoiceListService;
import cn.com.thtf.egov.cms.iservice.sell.ISalesContractManagementService;
import cn.com.thtf.egov.cms.util.Container;
import cn.com.thtf.egov.cms.util.MailSenderInfo;
import cn.com.thtf.egov.cms.util.SendMail;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;

/**
 * 开票管理检索、开票申请
 * 
 * @author hanrubing
 */
public class InvoiceListAction extends NewBaseAction {

    private static Logger log = LoggerFactory.getLogger(InvoiceListAction.class);

    private IInvoiceListService invoice = (IInvoiceListService) getBean("invoiceListServiceImp");
    private ICommonService common = (ICommonService) getBean("commonServiceImpl");
    /** 销售合同 */
    private ISalesContractManagementService salesContract = (ISalesContractManagementService) getBean("salesContractServiceImp");
    private java.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private IBuyContractService buyContractService = (IBuyContractService) getBean("buyContractServiceImpl");

    /**
     * 开票管理
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward invoiceManagement(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},开票管理列表【开始】", user.getId());

        /** 权限id */
        int role = user.getRoleId();
        /** 分页 */
        NewPage newPage = getNewPage(request);
        newPage.setUrl("getInvoiceList.do");
        request.setAttribute("NewPage", newPage);
        request.setAttribute("role", role);// 产品分类列表框

        /* 产品分类 */
        List list = null;
        list = common.getAllProductTypes(null, user.getId(), user.getRoleId() + "");
        request.setAttribute("productType", list);

        /** 开票列表 */
        List<Object> invoiceList = null;
        try {
            invoiceList = invoice.getInvoiceList(newPage, user);
        } catch (Exception e) {
            log.error("开票列表错误", e);
        }
        request.setAttribute("invoiceList", invoiceList);
        /** 登录名 */
        request.setAttribute("userid", user.getId());
        log.info("User:{},开票管理列表【结束】", user.getId());
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 开票检索
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward findInvoiceList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},开票检索【开始】", user.getId());
        /** 获取开票页面的参数 */
        InvoiceListForm invoiceListForm = (InvoiceListForm) form;
        invoiceListForm.getInvoiceListDto().setProductTypeId(
                invoiceListForm.getInvoiceListDto().getProductTypeName());
        /** 去掉空格 */
        if (invoiceListForm.getInvoiceListDto().getSendGoodsId() != null) {
            invoiceListForm.getInvoiceListDto().setSendGoodsId(
                    invoiceListForm.getInvoiceListDto().getSendGoodsId().trim());
        }
        /** 分页 */
        NewPage newPage = getNewPage(request);
        newPage.setUrl("findInvoiceList.do");
        request.setAttribute("NewPage", newPage);
        newPage.setQuery("invoiceListDto.id", invoiceListForm.getInvoiceListDto().getId());
        newPage.setQuery("invoiceListDto.productTypeName", invoiceListForm
                .getInvoiceListDto().getProductTypeName());
        newPage.setQuery("invoiceListDto.productTypeId", invoiceListForm
                .getInvoiceListDto().getProductTypeName());
        newPage.setQuery("invoiceListDto.customerName", invoiceListForm
                .getInvoiceListDto().getCustomerName());
        newPage.setQuery("invoiceListDto.invoiceType", invoiceListForm
                .getInvoiceListDto().getInvoiceType());
        newPage.setQuery("invoiceListDto.date", invoiceListForm.getInvoiceListDto()
                .getDate());
        newPage.setQuery("invoiceListDto.applyEndTime", invoiceListForm
                .getInvoiceListDto().getApplyEndTime());
        newPage.setQuery("invoiceListDto.notifyDate", invoiceListForm.getInvoiceListDto()
                .getNotifyDate());
        newPage.setQuery("invoiceListDto.notifyEndTime", invoiceListForm
                .getInvoiceListDto().getNotifyEndTime());
        newPage.setQuery("invoiceListDto.userName", invoiceListForm.getInvoiceListDto()
                .getUserName());
        newPage.setQuery("invoiceListDto.notifyName", invoiceListForm.getInvoiceListDto()
                .getNotifyName());
        newPage.setQuery("invoiceListDto.status", invoiceListForm.getInvoiceListDto()
                .getStatus());
        newPage.setQuery("invoiceListDto.userId", invoiceListForm.getInvoiceListDto()
                .getUserId());
        newPage.setQuery("invoiceListDto.type", user.getRoleId().toString());
        invoiceListForm.getInvoiceListDto().setType(this.roleType(user.getRoleId()));
        request.setAttribute("invoiceDto", invoiceListForm.getInvoiceListDto());

        newPage.setQuery("invoiceListDto.sendGoodsId", invoiceListForm
                .getInvoiceListDto().getSendGoodsId());
        /* 产品分类 */
        List list = null;
        list = common.getAllProductTypes(null, user.getId(), user.getRoleId() + "");
        request.setAttribute("productType", list);

        /** 超找后的开票列表 */
        request.setAttribute(
                "invoiceList",
                invoice.getInvoiceListByObj(newPage, user,
                        invoiceListForm.getInvoiceListDto()));
        /** 登录名和权限 */
        request.setAttribute("role", user.getRoleId());
        request.setAttribute("userid", user.getId());
        log.info("User:{},开票检索【结束】", user.getId());
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 开票删除
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward delInvoice(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},开票删除【开始】", user.getId());
        /** 开票id */
        String id = request.getParameter("id");
        /** 开票删除 by id */
        boolean isTrue = invoice.removeInvoice(id);
        if (isTrue) {
            log.info("User:{},开票删除【结束】", user.getId());
            return mapping.findForward(Constants.SUCCESS);
        } else {// 此页面链接,当改变成不允许的状态时，提示错误
            request.setAttribute("redirect", request.getHeader("Referer") + "&error=true");
            log.info("User:{},开票删除【失败】", user.getId());
            return mapping.findForward(Constants.FAIL);
        }
    }

    /**
     * 开票申请 显示
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addInvoiceOfShow(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {

        saveToken(request);
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 开票申请
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addInvoice(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},开票申请【开始】", user.getId());
        try {
            /** 获取开票页面的值 */
            MakeInvoiceAddForm makeInvoiceAddForm = (MakeInvoiceAddForm) form;
            if (isTokenValid(request, true)) {
                resetToken(request);
            } else {
                request.setAttribute("redirect", request.getHeader("Referer"));
                return mapping.findForward(Constants.FAIL);
            }
            /** submit 提交 save 保存 */
            String type = request.getParameter("type");
            /** 判断有无客户 */
            if (StringUtils.isBlank(makeInvoiceAddForm.getMakeInvoiceAddDto()
                    .getCustomerId())) {
                request.setAttribute("redirect", request.getHeader("Referer")
                        + "&addInvoiceError=true");
                return mapping.findForward(Constants.FAIL);
            }
            /** 判断有无分类 */
            if (StringUtils.isBlank(makeInvoiceAddForm.getMakeInvoiceAddDto()
                    .getProductTypeId())) {
                request.setAttribute("redirect", request.getHeader("Referer")
                        + "&addInvoiceErrorOfType=true");
                return mapping.findForward(Constants.FAIL);
            }
            /** 29 新建日期存入dto里 */
            makeInvoiceAddForm.getMakeInvoiceAddDto().setDateTime(sdf.format(new Date()));
            /** 点击提交时 */
            if ("submit".equals(type)) {
                /** 获取销售助理id */
                WorkReceiverDto workReceiverDto = common.getUserIdByCondition(Integer
                        .parseInt(makeInvoiceAddForm.getMakeInvoiceAddDto()
                                .getProductTypeId()), Integer.parseInt(user
                        .getUserAreaId().toString()), null);
                /** 助理dto为空时 */
                if (workReceiverDto == null) {
                    log.info("User:{},开票申请【失败】：销售助理不存在", user.getId());
                    request.setAttribute("redirect", request.getHeader("Referer")
                            + "&addError=true");
                    return mapping.findForward(Constants.FAIL);
                }
                /** 插入事务 */
                String sellAssId = workReceiverDto.getUserId();
                makeInvoiceAddForm.getMakeInvoiceAddDto().setSellAssId(sellAssId);
                makeInvoiceAddForm.getMakeInvoiceAddDto().setStatus("6");// 开票状态

                // 待评审
                makeInvoiceAddForm.getMakeInvoiceAddDto().setDate(sdf.format(new Date()));// 申请日期

                AssessManDto assessManDto = new AssessManDto();
                assessManDto.setAssessManId(sellAssId);
                assessManDto.setWorkId(6);
                /** 判断是否存在 */
//                boolean isExist = salesContract.isNextAssessManWorkExist(assessManDto);
//                if (isExist) {
//                    salesContract.updateNextAssessManWork(assessManDto);
//                } else {
//                    salesContract.insertNextAssessManWork(assessManDto);
//                }

            }
            /** 点击保存时 */
            else {
                makeInvoiceAddForm.getMakeInvoiceAddDto().setStatus("1");// 开票状态
                // 未提交
            }
            makeInvoiceAddForm.getMakeInvoiceAddDto().setUserAreaId(
                    user.getUserAreaId().toString());// 10人员区域编号
            makeInvoiceAddForm.getMakeInvoiceAddDto().setUserName(user.getName());// 用户名

            makeInvoiceAddForm.getMakeInvoiceAddDto().setUserId(user.getId());// 登录名

            makeInvoiceAddForm.getMakeInvoiceAddDto().setDate(sdf.format(new Date()));// 申请日期
            makeInvoiceAddForm.getMakeInvoiceAddDto().setText(
                    request.getParameter("text"));// text 特殊说明
            String invoiceId = common.getSerialNumber("KP", "make_invoice");// 获取主键id
            makeInvoiceAddForm.getMakeInvoiceAddDto().setId(invoiceId);// id 1

            /** 获取发货明细数据 */
            String returnValueToServer = request.getParameter("returnValueToServer");
            /** 添加开票 */
            boolean isSuccess = invoice.addInvoice(
                    makeInvoiceAddForm.getMakeInvoiceAddDto(), returnValueToServer);
            if (isSuccess) {
                log.info("User:{},开票申请【结束】", user.getId());
                return mapping.findForward(Constants.SUCCESS);
            }
            /** 添加失败 */
            else {
                log.info("User:{},开票申请【失败】", user.getId());
                request.setAttribute("redirect", request.getHeader("Referer")
                        + "&addError=true");
                return mapping.findForward(Constants.FAIL);
            }
        } catch (Exception e) {
            log.error("User:{},开票申请【失败】:{}", user.getId(), e);
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&addError=true");
            return mapping.findForward(Constants.FAIL);
        }
    }

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
    public ActionForward getCustomerList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},客户列表【开始】", user.getId());
        /** 分页 */
        NewPage newPage = getNewPage(request);
        newPage.setUrl("getCustomerList.do");
        request.setAttribute("NewPage", newPage);
        CustomerInfoDto customerDto = new CustomerInfoDto();
        customerDto.setUserid(user.getId());
        /** 获得客户信息 */
        List<Object> customerList = invoice.getCustomerList(newPage, customerDto);
        /** 获得json格式客户信息 */
        String customerInfo = invoice.getCustomerInfoOfJSON(customerList);
        /** 返回客户列表与信息 */
        request.setAttribute("customerList", customerList);
        request.setAttribute("customerInfo", customerInfo);
        log.info("User:{},客户列表【结束】", user.getId());
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 客户检索
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward findCustomerList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},客户检索【开始】", user.getId());
        /** 获得客户名字 */
        String name = request.getParameter("name");
        if (name != null) {
            name = name.trim();
        }
        /** 分页 */
        NewPage newPage = getNewPage(request);
        newPage.setUrl("findCustomerList.do");
        newPage.setQuery("name", name);
        request.setAttribute("NewPage", newPage);
        CustomerInfoDto customerDto = new CustomerInfoDto();
        customerDto.setUserid(user.getId());
        customerDto.setName(name);
        /** 检索客户信息 */
        List<Object> findCustomerList = invoice.getCustomerListByName(newPage,
                customerDto);
        if (findCustomerList.size() == 0) {
            request.setAttribute("customerSize", "0");
        }
        /** 获得json格式客户信息 */
        String customerInfo = invoice.getCustomerInfoOfJSON(findCustomerList);
        /** 返回查找后的客户列表与信息 */
        request.setAttribute("customerInfo", customerInfo);
        request.setAttribute("customerList", findCustomerList);
        log.info("User:{},客户检索【结束】", user.getId());
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 获得发货信息列表
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getSendGoodsParticularList(ActionMapping mapping,
            ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},获得发货信息列表【开始】", user.getId());
        /** 发货列表dto */
        SendGoodsParticularListDto sendGood = new SendGoodsParticularListDto();
        /** 发货列表form */
        SendGoodsParticularListForm sendGoodForm = new SendGoodsParticularListForm();
        /** 获得客户id */
        String customerId = request.getParameter("cid");
        sendGood.setCustomerId(customerId);
        sendGood.setUserId(user.getId());
        /** 分页 */
        NewPage newPage = getNewPage(request);
        newPage.setUrl("findSendGoodsParticularList.do");
        newPage.setQuery("sendGoodsParticularListDto.customerId", customerId);

        request.setAttribute("NewPage", newPage);
        /** 获得分类id */
        String productTypeId = request.getParameter("pTypeId");
        /** 分类为空 */
        if (StringUtils.isBlank(productTypeId)) {
            request.setAttribute("sendGoodsList", null);
        } else {
            /** 分类有值，查询此分类的信息 */
            newPage.setQuery("sendGoodsParticularListDto.productTypeName", productTypeId);
            newPage.setQuery("isTrue", "true");
            sendGood.setProductTypeId(productTypeId.trim());
            sendGood.setProductTypeName(productTypeId.trim());
            sendGoodForm.setSendGoodsParticularListDto(sendGood);
            request.setAttribute("sendGoods", sendGoodForm);
            request.setAttribute("isTrue", "true");
            request.setAttribute("sendGoodsList",
                    invoice.getSendGoodsList(newPage, sendGood));
        }
        /* 产品分类 */
        List list = null;
        list = common.getAllProductTypes(null, user.getId(), user.getRoleId() + "");
        request.setAttribute("productType", list);

        log.info("User:{},获得发货信息列表【结束】", user.getId());
        /** 产品分类列表框 */
        return mapping.findForward(Constants.SUCCESS);

    }

    /**
     * 检索发货信息列表
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward findSendGoodsParticularList(ActionMapping mapping,
            ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},检索发货信息列表【开始】", user.getId());
        /** 获得查找参数 */
        SendGoodsParticularListForm sendGood = (SendGoodsParticularListForm) form;
        sendGood.getSendGoodsParticularListDto().setProductTypeId(
                sendGood.getSendGoodsParticularListDto().getProductTypeName());
        /** 分页 */
        NewPage newPage = getNewPage(request);
        newPage.setUrl("findSendGoodsParticularList.do");
        request.setAttribute("NewPage", newPage);
        newPage.setQuery("sendGoodsParticularListDto.id", sendGood
                .getSendGoodsParticularListDto().getId());

        newPage.setQuery("sendGoodsParticularListDto.productContractNum", sendGood
                .getSendGoodsParticularListDto().getProductContractNum());
        newPage.setQuery("sendGoodsParticularListDto.companyContractNum", sendGood
                .getSendGoodsParticularListDto().getCompanyContractNum());
        newPage.setQuery("sendGoodsParticularListDto.productCode", sendGood
                .getSendGoodsParticularListDto().getProductCode());
        newPage.setQuery("sendGoodsParticularListDto.productName", sendGood
                .getSendGoodsParticularListDto().getProductName());
        newPage.setQuery("sendGoodsParticularListDto.specModel", sendGood
                .getSendGoodsParticularListDto().getSpecModel());
        newPage.setQuery("sendGoodsParticularListDto.customerId", sendGood
                .getSendGoodsParticularListDto().getCustomerId());
        newPage.setQuery("sendGoodsParticularListDto.productTypeName", sendGood
                .getSendGoodsParticularListDto().getProductTypeName());
        newPage.setQuery("sendGoodsParticularListDto.productTypeId", sendGood
                .getSendGoodsParticularListDto().getProductTypeName());
        newPage.setQuery("sendGoodsParticularListDto.beginTime", sendGood
                .getSendGoodsParticularListDto().getBeginTime());
        newPage.setQuery("sendGoodsParticularListDto.endTime", sendGood
                .getSendGoodsParticularListDto().getEndTime());
        newPage.setQuery("isTrue", request.getParameter("isTrue"));
        sendGood.getSendGoodsParticularListDto().setUserId(user.getId());
        /** 检索发货信息列表 */
        List sendGoodsList = invoice.getSendGoodsListByObj(newPage,
                sendGood.getSendGoodsParticularListDto());
        if (sendGoodsList.size() == 0)
            sendGoodsList = null;
        request.setAttribute("sendGoodsList", sendGoodsList);
        request.setAttribute("sendGoods", sendGood);
        /** 产品分类列表框 */
        request.setAttribute("isTrue", request.getParameter("isTrue"));

        /* 产品分类 */
        List list = null;
        list = common.getAllProductTypes(null, user.getId(), user.getRoleId() + "");
        request.setAttribute("productType", list);

        log.info("User:{},检索发货信息列表【结束】", user.getId());
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 开票修改 显示
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward modifyInvoiceOfShow(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},开票修改 显示【开始】", user.getId());
        /** 获得开票id */
        String id = request.getParameter("id");
        /** 获得开票客户信息 by id */
        MakeInvoiceAddDto makeInvoiceAddDto = (MakeInvoiceAddDto) invoice
                .getInvoiceOfCustomer(id);
        /** 获得开票明细 */
        List<Object> sendGoodsList = invoice
                .getInvoiceOfDetail(makeInvoiceAddDto.getId());
        /** 传到页面 */
        request.setAttribute("customerInfo", makeInvoiceAddDto);

        request.setAttribute("sendGoodsList", sendGoodsList);
        log.info("User:{},开票修改 显示【结束】", user.getId());
        saveToken(request);
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 开票修改 提交
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward modifyInvoiceOfSubmit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},开票修改 提交【开始】", user.getId());
        try {
            /** 开票form */
            MakeInvoiceAddForm makeInvoiceAddForm = (MakeInvoiceAddForm) form;
            if (isTokenValid(request, true)) {
                resetToken(request);
            } else {
                request.setAttribute("redirect", request.getHeader("Referer"));
                return mapping.findForward(Constants.FAIL);
            }
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("table", "make_invoice");
            map.put("id", request.getParameter("invoiceId"));
            int status = buyContractService.getStrutsOfAll(map);
            /** 判断是否待未提交 */
            if (status != 1) {
                request.setAttribute("redirect", request.getHeader("Referer")
                        + "&statusError=true");
                return mapping.findForward(Constants.FAIL);
            }
            /** submit 提交 save 为保存 */
            String type = request.getParameter("type");
            /** 判断有无客户 */
            if (StringUtils.isBlank(makeInvoiceAddForm.getMakeInvoiceAddDto()
                    .getCustomerId())) {
                request.setAttribute("redirect", request.getHeader("Referer")
                        + "&addInvoiceError=true");
                return mapping.findForward("error");
            }
            /** 判断有无分类 */
            if (StringUtils.isBlank(makeInvoiceAddForm.getMakeInvoiceAddDto()
                    .getProductTypeId())) {
                request.setAttribute("redirect", request.getHeader("Referer")
                        + "&addInvoiceErrorOfType=true");
                return mapping.findForward("error");
            }
            /** 提交时 */
            if ("submit".equals(type)) {
                WorkReceiverDto workReceiverDto = common.getUserIdByCondition(Integer
                        .parseInt(makeInvoiceAddForm.getMakeInvoiceAddDto()
                                .getProductTypeId()), Integer.parseInt(user
                        .getUserAreaId().toString()), null);
                /** 助理dto为空时 */
                if (workReceiverDto == null) {
                    log.info("User:{},开票修改 提交【失败】：销售助理不存在", user.getId());
                    request.setAttribute("redirect", request.getHeader("Referer")
                            + "&addError=true");
                    return mapping.findForward("error");
                }
                String sellAssId = workReceiverDto.getUserId();
                makeInvoiceAddForm.getMakeInvoiceAddDto().setSellAssId(sellAssId);
                makeInvoiceAddForm.getMakeInvoiceAddDto().setStatus("6");// 开票状态
                // 6
                // 待评审
                makeInvoiceAddForm.getMakeInvoiceAddDto().setDate(sdf.format(new Date()));// 申请日期
                // 7
                AssessManDto assessManDto = new AssessManDto();
                assessManDto.setAssessManId(sellAssId);
                assessManDto.setWorkId(6);
//                boolean isExist = salesContract.isNextAssessManWorkExist(assessManDto);
//                if (isExist) {
//                    salesContract.updateNextAssessManWork(assessManDto);
//                } else {
//                    salesContract.insertNextAssessManWork(assessManDto);
//                }
            }
            /** 保存时 */
            else {
                makeInvoiceAddForm.getMakeInvoiceAddDto().setStatus("1");// 开票状态
                // 6
                // 未提交
            }
            makeInvoiceAddForm.getMakeInvoiceAddDto().setText(
                    request.getParameter("text"));// text 特殊说明
            makeInvoiceAddForm.getMakeInvoiceAddDto().setId(
                    request.getParameter("invoiceId"));// id 1 productTypeId 2 、
            // money 15 、
            // invoice_type 5
            /** 获得发货明细数据 */
            String returnValueToServer = request.getParameter("returnValueToServer");
            /** 修改开票 */
            invoice.modifyInvoice(makeInvoiceAddForm.getMakeInvoiceAddDto(),
                    returnValueToServer);
        } catch (Exception e) {
            log.error("User:{},开票修改 提交【失败】:{}", user.getId(), e);
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&addError=true");
            return mapping.findForward("error");
        }
        log.info("User:{},开票修改 提交【结束】", user.getId());
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 查看开票
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward lookInvoice(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        /** 获得查看开票的id */
        String id = request.getParameter("id");
        /** 查找开票信息 */
        MakeInvoiceAddDto makeInvoiceAddDto = (MakeInvoiceAddDto) invoice
                .getInvoiceOfCustomer(id);
        /** 查找开票明细 */
        List<Object> sendGoodsList = invoice
                .getInvoiceOfDetail(makeInvoiceAddDto.getId());
        request.setAttribute("customerInfo", makeInvoiceAddDto);

        request.setAttribute("sendGoodsList", sendGoodsList);
        return mapping.findForward(Constants.SUCCESS);

    }

    /**
     * 开票确认
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward decideInvoice(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        /** 开票id */
        String id = request.getParameter("id");
        /** user实例 */
        UserEntity user = getLoginUserInfo(request);
        /** 判断是否助理 */
        if (user.getRoleId() != Constants.ROLE_SALES_ASSISTANT) {
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&passError=true");
            return mapping.findForward(Constants.FAIL);
        }
        /** 查找开票信息 */
        MakeInvoiceAddDto makeInvoiceAddDto = (MakeInvoiceAddDto) invoice
                .getInvoiceOfCustomer(id);
        /** 查找发票 返回发票明细列表 */
        List<Object> sellInvoiceList = invoice.getSellInvoiceListById(id);
        request.setAttribute("customerInfo", makeInvoiceAddDto);
        request.setAttribute("sellInvoiceList", sellInvoiceList);
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 发票添加显示
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getSellInvoice(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        /** 开票id */
        String id = request.getParameter("id");
        /** user实例 */
        UserEntity user = getLoginUserInfo(request);
        /** 判断是否助理 */
        if (user.getRoleId() != Constants.ROLE_SALES_ASSISTANT) {
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&passError=true");
            return mapping.findForward(Constants.FAIL);
        }
        /** 查找开票信息 */
        MakeInvoiceAddDto makeInvoiceAddDto = (MakeInvoiceAddDto) invoice
                .getInvoiceOfCustomer(id);
        /** 查找开票明细之未开发票信息 */
        List<Object> sendGoodsList = invoice
                .getInvoiceOfDetailToSellById(makeInvoiceAddDto.getId());

        request.setAttribute("customerInfo", makeInvoiceAddDto);
        request.setAttribute("sendGoodsList", sendGoodsList);
        saveToken(request);
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 发票添加执行
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addSellInvoice(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        /** user实例 */
        UserEntity user = getLoginUserInfo(request);
        /** 判断是否助理 */
        if (user.getRoleId() != Constants.ROLE_SALES_ASSISTANT) {
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&passError=true");
            return mapping.findForward(Constants.FAIL);
        }
        if (isTokenValid(request, true)) {
            resetToken(request);
        } else {
            request.setAttribute("redirect", request.getHeader("Referer"));
            return mapping.findForward(Constants.FAIL);
        }
        String makeInvoiceId = request.getParameter("makeInvoiceId");
        String money = request.getParameter("money");
        String date = request.getParameter("date");
        String makeMoney = request.getParameter("makeMoney");
        String number = request.getParameter("invoiceId");

        String userId = request.getParameter("userId");
        String userName = request.getParameter("userName");

        String remark = request.getParameter("remark");
        /** 判断日期是否为空 */
        if (StringUtils.isBlank(date)) {
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&messageError=true");
            return mapping.findForward(Constants.FAIL);
        }
        /** 判断发票金额是否为空 */
        if (StringUtils.isBlank(money)
                || Double.parseDouble(money) != Double.parseDouble(makeMoney)) {//
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&messageError=true");
            return mapping.findForward(Constants.FAIL);
        }
        /** 判断发票号是否为空 */
        if (StringUtils.isBlank(number)) {
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&messageError=true");
            return mapping.findForward(Constants.FAIL);
        }
        /** 判断选中 */
        String invoiceDetail[] = request.getParameterValues("invoiceDetail");
        if (invoiceDetail.length == 0) {
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&messageError=true");
            return mapping.findForward(Constants.FAIL);
        }
        /** 判断发票号是否存在 */
        List<SellInvocleOfAddDto> sellInvocleList = invoice.getSellInvoiceNumber(number
                .trim());
        if (sellInvocleList.size() > 0) {
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&messageOfNumberError=true");
            return mapping.findForward(Constants.FAIL);
        }
        InvoiceInfoDto makeInvoiceDto = invoice.getInvoiceById(makeInvoiceId);
        if (makeInvoiceDto == null) {
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&messageError=true");
            log.error("申请发票不存在");
            return mapping.findForward(Constants.FAIL);
        }
        SellInvocleOfAddDto sellInvocleOfAddDto = new SellInvocleOfAddDto();
        sellInvocleOfAddDto.setDate(date);
        sellInvocleOfAddDto.setMakeInvoiceId(makeInvoiceId);
        sellInvocleOfAddDto.setMoney(money);
        sellInvocleOfAddDto.setNumber(number);
        sellInvocleOfAddDto.setCustomerId(request.getParameter("customerId"));
        sellInvocleOfAddDto.setCustomerName(request.getParameter("customerName"));
        sellInvocleOfAddDto.setInvoiceType(request.getParameter("invoiceType"));
        sellInvocleOfAddDto.setProductTypeId(request.getParameter("productTypeId"));
        sellInvocleOfAddDto.setConfirmId(userId);
        sellInvocleOfAddDto.setConfirmName(userName);
        sellInvocleOfAddDto.setUserAreaId(makeInvoiceDto.getUserAreaId());
        sellInvocleOfAddDto.setRemark(remark);
        /** 添加发票 */
        int issuccess = invoice.addSellInvoice(sellInvocleOfAddDto, invoiceDetail, user);
        if (issuccess == 1) {
            request.setAttribute("redirect", request.getContextPath()
                    + "/decideInvoice.do?id=" + makeInvoiceId);
            return mapping.findForward(Constants.FAIL);
        } else if (issuccess == 2) {
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&messageAddCountError=true");
            return mapping.findForward(Constants.FAIL);
        } else {
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&messageAddError=true");
            return mapping.findForward(Constants.FAIL);
        }

    }

    /**
     * 根据role找到别名 查询数据库
     * 
     * @param role
     *            权限id
     * @return 类型名字
     * @throws Exception
     */
    private String roleType(int role) {
        switch (role) {
        case Constants.ROLE_SALES_MANAGER: {// 销售经理
            return "sellManage";
        }
        case Constants.ROLE_SALES_DIRECTOR: {// 销售总监 5
            return "sellMajordomo";
        }
        case Constants.ROLE_DIRECTOR_OF_OPERATIONS: {// 运营总监 17
            return "runMajordomo";
        }
        case Constants.ROLE_SALES_ASSISTANT: {// 销售助理 3，9一样
            return "sellAssistant";
        }
        case Constants.ROLE_REGIONAL_DIRECTOR: {// 区域总监 9
            return "areaMajordomo";
        }
        case Constants.ROLE_AREA_MANAGER: {// 区域经理 大区经理19,20
            return "areaManage";
        }
        case Constants.ROLE_BIGAREA_MANAGER: {// 区域经理 大区经理19,20
            return "areaManage";
        }
        case Constants.ROLE_CREDIT_COMMISSIONER: {// 信用专员 采购主管
            return "productCreditOffice"; // 法务专员、运营总监助理、信用主管、总经理一样参数
        }
        case Constants.ROLE_PROCUREMENT_OFFICER: {// 采购主管 11区域总监信用专员
            return "productCreditOffice";
        }
        case Constants.ROLE_GENERAL_MANAGER: {// 总经理 18
            return "productCreditOffice";
        }
        case Constants.ROLE_CREDIT_CHARGE: {// 信用主管 7
            return "productCreditOffice";
        }
        case Constants.ROLE_AST_DIRECTOR_OF_OPERATIONS: {// 运营总监助理 16
            return "productCreditOffice";
        }
        case Constants.ROLE_COMPLIANCE_OFFICER: {// 法务专员 15
            return "productCreditOffice";
        }

        default:
            ;
        }
        return "";
    }

    /**
     * 开票通知显示
     * 
     * @author lilewei
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward showNotifiedInvoice(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},开票通知显示【开始】", user.getId());

        String invoiceId = request.getParameter("invoiceId");

        if (StringUtils.isEmpty(invoiceId)) {
            log.info("User:{},开票通知显示【失败】：无效数据", user.getId());
            request.setAttribute("msg", "无效数据");
            return mapping.findForward(Constants.FAIL);
        }

        // 开票
        InvoiceInfoDto invoiceDto = invoice.getInvoiceById(invoiceId);
        // 开票明细列表
        List<InvoiceDetailDto> invoiceDetailList = invoice
                .getInvoiceDetailListByInvoiceId(invoiceId);
        // 数据合法性检查
        if (invoiceDto == null || invoiceDetailList.size() == 0) {
            log.info("User:{},开票通知显示【失败】：无效数据", user.getId());
            request.setAttribute("msg", "无效数据");
            return mapping.findForward(Constants.FAIL);
        }

        request.setAttribute("invoice", invoiceDto);
        request.setAttribute("invoiceDetailList", invoiceDetailList);
        log.info("User:{},开票通知显示【结束】", user.getId());
        return mapping.findForward(Constants.SUCCESS);

    }

    /**
     * K3开票确认显示
     * 
     * @author liuqg
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward showConfirmInvoice(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},K3开票确认显示【开始】", user.getId());

        String invoiceId = request.getParameter("invoiceId");

        if (StringUtils.isEmpty(invoiceId)) {
            log.info("User:{},K3开票确认显示【失败】：无效数据", user.getId());
            request.setAttribute("msg", "无效数据");
            return mapping.findForward(Constants.FAIL);
        }

        // 开票
        InvoiceInfoDto invoiceDto = invoice.getInvoiceById(invoiceId);
        // 开票明细列表
        List<InvoiceDetailDto> invoiceDetailList = invoice
                .getInvoiceDetailListByInvoiceId(invoiceId);
        // 数据合法性检查
        if (invoiceDto == null || invoiceDetailList.size() == 0) {
            log.info("User:{},K3开票确认显示【失败】：无效数据", user.getId());
            request.setAttribute("msg", "无效数据");
            return mapping.findForward(Constants.FAIL);
        }

        request.setAttribute("invoice", invoiceDto);
        request.setAttribute("invoiceDetailList", invoiceDetailList);
        log.info("User:{},K3开票确认显示【结束】", user.getId());
        return mapping.findForward(Constants.SUCCESS);

    }

    /**
     * K3开票确认
     * 
     * @author liuqg
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward K3ConfirmInvoice(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        String invoiceId = request.getParameter("invoiceId");
        String text_k3 =request.getParameter("text_k3");
        if (StringUtils.isEmpty(invoiceId)) {
            request.setAttribute("msg", "无效数据");
            return mapping.findForward(Constants.SUCCESS);
        }

        // 开票
        InvoiceInfoDto invoiceDto = new InvoiceInfoDto();;
        invoiceDto.setId(invoiceId);
        invoiceDto.setStatus(7);
        invoiceDto.setConfirmId(user.getId());
        invoiceDto.setConfirmName(user.getName());
        invoiceDto.setConfirmDate(sdf.format(new Date()));
        invoiceDto.setText_k3(text_k3);
        if (invoice.k3ConfirmInvoice(invoiceDto)) {
            log.info("User:{},K3开票确认成功,ID:{}", user.getId(),invoiceId);
        } else {
            request.setAttribute("msg", "开票确认异常");
            log.info("User:{},K3开票确认失败,ID:{}", user.getId(),invoiceId);
        } 
        return mapping.findForward(Constants.SUCCESS);

    }

    /**
     * 开票通知
     * 
     * @author lilewei
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public ActionForward notifyInvoice(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},开票通知【开始】", user.getId());
        String invoiceId = request.getParameter("invoiceId");

        if (StringUtils.isNotEmpty(invoiceId)) {

            boolean isSuccess = false;
            //Configuration config = Container.getTemplateConfig();
            // 开票申请信息
            InvoiceInfoDto invoiceDto = invoice.getInvoiceById(invoiceId);
            // 格式化开票的金额
            // invoiceDto.setMoney(Util.getFormatCurrency(invoiceDto.getMoney()));

//            List<InvoiceDetailDto> invoiceDetailList = invoice
//                    .getInvoiceDetailListByInvoiceId(invoiceId);
            invoiceDto.setNotifyName(user.getName());
//            Map map = new HashMap();
//            map.put("invoice", invoiceDto);
//            map.put("product", invoiceDetailList);
//            freemarker.template.Template template = null;
            MailEntity mailEntity = new MailEntity();
            MailSenderInfo mail = new MailSenderInfo();
            /*
             * try { template = config.getTemplate(Constants.FINA_TEMPLATE);
             * StringWriter out = new StringWriter(); // 邮件信息
             * 
             * template.process(map, out); mail.setType(Constants.MAIL_TYPE);
             * mail.setContent(out.toString()); SendMail sender = new
             * SendMail(); successBySend=sender.sendMail(mail); // MailEntity
             * mail实体类 mailEntity.setUserName(user.getName());
             * mailEntity.setType(9); mailEntity.setText(out.toString());
             * mailEntity.setSubject("开票通知_" + invoiceId); isSuccess = true; }
             * catch (TemplateException e) {
             * log.error("User:{},开票通知【失败】：发送邮件模版失败:{}", user.getId(), e);
             * isSuccess = false; } catch (IOException e) {
             * log.error("User:{},开票通知【失败】：请确认邮件模版是否有误:{}", user.getId(), e);
             * isSuccess = false; }
             */
            /*
             * invoiceDto.setUserId(user.getId()); if (successBySend &&
             * isSuccess && isNotNotifyied(invoiceDto)) { // 发送成功同时更新开票状态
             * invoiceDto.setStatus(7); invoiceDto.setNotifyId(user.getId());
             * invoiceDto.setNotifyName(user.getName());
             * invoiceDto.setNotifyDate(sdf.format(new Date())); // 更新数据库
             * isSuccess = invoice.modifyInvoiceforNotify(invoiceDto, true,
             * mailEntity, mail);
             * 
             * } else { isSuccess = invoice.modifyInvoiceforNotify(invoiceDto,
             * false, mailEntity, mail);
             * 
             * }
             */
            invoiceDto.setUserId(user.getId());
            if (isNotNotifyied(invoiceDto)) {
                // 更新开票状态 为待确认
                invoiceDto.setStatus(2);
                invoiceDto.setNotifyId(user.getId());
                invoiceDto.setNotifyName(user.getName());
                invoiceDto.setNotifyDate(sdf.format(new Date()));
                // 更新数据库
                isSuccess = invoice.modifyInvoiceforNotify(invoiceDto, true, mailEntity,
                        mail);

            }
            PrintWriter writer = response.getWriter();
            if (isSuccess) {
                writer.write(Constants.SUCCESS);
            } else {
                writer.write(Constants.FAIL);
            }
            writer.close();
        }
        log.info("User:{},开票通知【结束】", user.getId());
        return null;
    }

    private boolean isNotNotifyied(InvoiceInfoDto invoiceDto) {
        return invoiceDto.getStatus().equals(Constants.SELL6)
                && StringUtils.isBlank(invoiceDto.getNotifyId());
    }

    /**
     * 发票删除
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward delSellInvoiceOfInvoice(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},发票删除【开始】", user.getId());
        /** 发票id */
        String sellInvoice = request.getParameter("SIId");
        String makeInvoiceId = request.getParameter("mid");
        String sellInvoiceDetail = request.getParameter("SIDId");
        //
        // if(makeInvoiceId==null && makeInvoiceId==""){
        // request.setAttribute("redirect", request.getHeader("Referer")
        // + "&delError=true");
        // log.info("User:{},发票删除【失败】", user.getId());
        // return mapping.findForward(Constants.FAIL);
        //
        // }
        /** 判断是否助理 */
        if (user.getRoleId() != Constants.ROLE_SALES_ASSISTANT) {
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&passError=true");
            log.info("User:{},发票删除【失败】：销售助理不存在", user.getId());
            return mapping.findForward(Constants.FAIL);
        }
        /** 删除发票和明细 */
        boolean isSucess = invoice.deleteSellInvoice(sellInvoice, sellInvoiceDetail,
                makeInvoiceId, user);
        // 成功或失败都会跳转到列表页 fowrard的地址都是Constants.FAIL
        if (isSucess) {
            request.setAttribute("redirect", request.getHeader("Referer"));
            log.info("User:{},发票删除【结束】", user.getId());
            return mapping.findForward(Constants.FAIL);
        } else {
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&delError=true");
            log.info("User:{},发票删除【失败】", user.getId());
            return mapping.findForward(Constants.FAIL);
        }

    }

}
