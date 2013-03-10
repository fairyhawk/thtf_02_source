/**
 * ClassName  BoxAction
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-6-8
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.action.inventory;

import java.io.PrintWriter;
import java.util.List;

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
import cn.com.thtf.egov.cms.dto.BoxDto;
import cn.com.thtf.egov.cms.dto.BoxQueryDto;
import cn.com.thtf.egov.cms.dto.ProductTypeInfoDto;
import cn.com.thtf.egov.cms.dto.StockSendGoodsDetailDto;
import cn.com.thtf.egov.cms.dto.StockSendGoodsDto;
import cn.com.thtf.egov.cms.entity.LogisticsEntity;
import cn.com.thtf.egov.cms.entity.StockroomEntity;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.form.BoxForm;
import cn.com.thtf.egov.cms.iservice.ILogisticsService;
import cn.com.thtf.egov.cms.iservice.IProductTypeService;
import cn.com.thtf.egov.cms.iservice.common.ICommonService;
import cn.com.thtf.egov.cms.iservice.inventory.IBoxService;
import cn.com.thtf.egov.cms.iservice.inventory.IStockroomService;
import cn.com.thtf.egov.cms.util.Container;
import cn.com.thtf.egov.cms.util.Util;

/**
 * 装箱单
 * 
 * @author Lubo
 */
public class BoxAction extends NewBaseAction {

    /** log */
    private static Logger log = LoggerFactory.getLogger(BoxAction.class);
    /** IProductTypeService */
    private IProductTypeService productTypeService;
    /** IBoxService */
    private IBoxService boxService;

    /**
     * 装箱单已付款
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward boxTbcPay(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserEntity userInfo = getLoginUserInfo(request);
        log.info("User:{}确认已付款", userInfo.getId());

        BoxForm boxForm = (BoxForm) form;
        BoxDto para = boxForm.getAddPara();
        boxService = (IBoxService) Container.getBean("boxServiceImp");
        para.setStatus("6"); 

        boolean result = boxService.boxTbcPay(para);
        if (!result) {
            request.setAttribute("errorMsg", "确认失败！");
        }

        return mapping.findForward("boxList");
    }

    /**
     * 装箱单回执
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward boxTbc(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        BoxForm boxForm = (BoxForm) form;
        BoxDto para = boxForm.getAddPara();

        UserEntity userInfo = getLoginUserInfo(request);
        log.info("User:{}确认了装箱单{}", userInfo.getId(), para.getTbcFlag());

        boxService = (IBoxService) Container.getBean("boxServiceImp");
        para.setTbcdate(Util.getDate());

        boolean result = boxService.boxTbc(para);
        if (!result) {
            request.setAttribute("errorMsg", "确认失败！");
        }

        return mapping.findForward("boxList");
    }

    /**
     * 装箱单确认初始化
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward boxTbcInit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        BoxForm boxForm = (BoxForm) form;
        BoxDto para = boxForm.getAddPara();

        boxService = (IBoxService) Container.getBean("boxServiceImp");
        /* 装箱单信息 */
        BoxDto boxDto = boxService.getBox(para.getId());
        /* 产品信息 */
        StockSendGoodsDto stockSendGoods = new StockSendGoodsDto();
        stockSendGoods.setOrderIdArr(boxDto.getOrderIdArr());

        List<StockSendGoodsDetailDto> result = boxService.getOrderDetail(stockSendGoods);

        request.setAttribute("box", boxDto);
        request.setAttribute("productListSize", result.size());
        request.setAttribute("boxProductList", result);

        return mapping.findForward("toBeConfirm");
    }

    /**
     * 装箱单管理
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public ActionForward boxList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("装箱单管理");

        BoxForm boxForm = (BoxForm) form;
        BoxQueryDto queryPara = boxForm.getQueryPara();

        UserEntity userInfo = getLoginUserInfo(request);
        queryPara.setUser(userInfo);

        /* 检索所有库房 */
        IStockroomService stockroomService = (IStockroomService) getBean("stockroomServiceImp");
        List<StockroomEntity> stockroomList = stockroomService.getAllStockRoom();

        /* 产品分类 */
        IProductTypeService productTypeService = (IProductTypeService) getBean("productTypeServiceImp");
        List<ProductTypeInfoDto> productTypeList = productTypeService
                .fingProductTypeAll();

        /* 封装翻页参数 */
        NewPage newPage = getNewPage(request);
        newPage.setUrl("boxList.do");

        newPage.setQuery("queryPara.stockroomId", queryPara.getStockroomId());
        newPage.setQuery("queryPara.productTypeId", queryPara.getProductTypeId());
        newPage.setQuery("queryPara.status", queryPara.getStatus());
        newPage.setQuery("queryPara.id", queryPara.getId());
        newPage.setQuery("queryPara.no", queryPara.getNo());

        newPage.setQuery("queryPara.logisticsName", queryPara.getLogisticsName());
        newPage.setQuery("queryPara.customerName", queryPara.getCustomerName());
        newPage.setQuery("queryPara.userName", queryPara.getUserName());
        newPage.setQuery("queryPara.sendDateStarttime", queryPara.getSendDateStarttime());
        newPage.setQuery("queryPara.sendDateEndtime", queryPara.getSendDateEndtime());

        newPage.setQuery("queryPara.estimateDateStarttime",
                queryPara.getEstimateDateStarttime());
        newPage.setQuery("queryPara.estimateDateEndtime",
                queryPara.getEstimateDateEndtime());
        newPage.setQuery("queryPara.arrivalDateStarttime",
                queryPara.getArrivalDateStarttime());
        newPage.setQuery("queryPara.arrivalDateEndtime",
                queryPara.getArrivalDateEndtime());
        newPage.setQuery("queryPara.signoffDateStarttime",
                queryPara.getSignoffDateStarttime());

        newPage.setQuery("queryPara.signoffDateEndtime",
                queryPara.getSignoffDateEndtime());
        newPage.setQuery("queryPara.confirmDateStarttime",
                queryPara.getConfirmDateStarttime());
        newPage.setQuery("queryPara.confirmDateEndtime",
                queryPara.getConfirmDateEndtime());

        newPage.setQuery("queryPara.orderId", queryPara.getOrderId());

        boxService = (IBoxService) Container.getBean("boxServiceImp");
        List<BoxDto> boxList = boxService.getBoxList(queryPara, newPage);

        request.setAttribute("boxList", boxList);
        request.setAttribute("roleId", userInfo.getRoleId());
        request.setAttribute("NewPage", newPage);
        request.setAttribute("queryPara", queryPara);
        request.setAttribute("stockroomList", stockroomList);
        request.setAttribute("productTypeList", productTypeList);
        return mapping.findForward("boxList");
    }

    /**
     * 装箱单删除
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward boxDel(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("装箱单删除");

        BoxForm boxForm = (BoxForm) form;
        BoxDto para = boxForm.getAddPara();
        boxService = (IBoxService) Container.getBean("boxServiceImp");
        boolean result = boxService.removeBox(para.getId());
        if (!result) {
            request.setAttribute("errorMsg", "删除失败！");
        }
        return mapping.findForward("boxList");
    }

    /**
     * 装箱单回执
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward boxConfirm(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("装箱单回执");

        BoxForm boxForm = (BoxForm) form;
        BoxDto para = boxForm.getAddPara();
        boxService = (IBoxService) Container.getBean("boxServiceImp");
        para.setStatus("5");
        para.setConfirmDate(Util.getDate());

        boolean result = boxService.boxConfirm(para);
        if (!result) {
            request.setAttribute("errorMsg", "确认失败！");
        }

        return mapping.findForward("boxList");
    }

    /**
     * 装箱单回执初始化
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward boxConfirmInit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        BoxForm boxForm = (BoxForm) form;
        BoxDto para = boxForm.getAddPara();

        boxService = (IBoxService) Container.getBean("boxServiceImp");
        /* 装箱单信息 */
        BoxDto boxDto = boxService.getBox(para.getId());
        /* 产品信息 */
        StockSendGoodsDto stockSendGoods = new StockSendGoodsDto();
        stockSendGoods.setOrderIdArr(boxDto.getOrderIdArr());

        List<StockSendGoodsDetailDto> result = boxService.getOrderDetail(stockSendGoods);

        request.setAttribute("box", boxDto);
        request.setAttribute("productListSize", result.size());
        request.setAttribute("boxProductList", result);

        return mapping.findForward("toConfirm");
    }

    /**
     * 装箱单打印
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getBoxPreView(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("装箱单打印");

        BoxForm boxForm = (BoxForm) form;
        BoxDto para = boxForm.getAddPara();

        boxService = (IBoxService) Container.getBean("boxServiceImp");
        /* 装箱单信息 */
        BoxDto boxDto = boxService.getBox(para.getId());
        /* 产品信息 */
        StockSendGoodsDto stockSendGoods = new StockSendGoodsDto();
        stockSendGoods.setOrderIdArr(boxDto.getOrderIdArr());

        List<StockSendGoodsDetailDto> result = boxService.getOrderDetail(stockSendGoods);
        List<StockSendGoodsDto> textResult = boxService.getOrderText(stockSendGoods);

        request.setAttribute("box", boxDto);
        request.setAttribute("productListSize", result.size());
        request.setAttribute("boxProductList", result);
        request.setAttribute("orderText", textResult);
        request.setAttribute("orderTextSize", textResult.size());

        return mapping.findForward("view");
    }

    /**
     * 装箱单连纸打印
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getBoxPrintView(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("装箱单连纸打印");

        BoxForm boxForm = (BoxForm) form;
        BoxDto para = boxForm.getAddPara();

        boxService = (IBoxService) Container.getBean("boxServiceImp");
        /* 装箱单信息 */
        BoxDto boxDto = boxService.getBoxPrint(para.getId());
        /* 产品信息 */
        StockSendGoodsDto stockSendGoods = new StockSendGoodsDto();
        stockSendGoods.setOrderIdArr(boxDto.getOrderIdArr());
        List<StockSendGoodsDetailDto> result = boxService.getOrderDetail(stockSendGoods);
        int max = 10 - result.size() % 10;
        if (max != 10) {
            for (int i = 0; i < max; i++) {
                result.add(new StockSendGoodsDetailDto());
            }
        }

        request.setAttribute("box", boxDto);
        request.setAttribute("productListSize", result.size());
        request.setAttribute("productPageCount", (result.size() - 1) / 10 + 1);
        request.setAttribute("boxProductList", result);

        return mapping.findForward("view");
    }

    /**
     * 装箱单查看
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getBox(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("装箱单查看");

        BoxForm boxForm = (BoxForm) form;
        BoxDto para = boxForm.getAddPara();

        boxService = (IBoxService) Container.getBean("boxServiceImp");
        /* 装箱单信息 */
        BoxDto boxDto = boxService.getBox(para.getId());
        /* 产品信息 */
        StockSendGoodsDto stockSendGoods = new StockSendGoodsDto();
        stockSendGoods.setOrderIdArr(boxDto.getOrderIdArr());

        List<StockSendGoodsDetailDto> result = boxService.getOrderDetail(stockSendGoods);

        request.setAttribute("box", boxDto);
        request.setAttribute("productListSize", result.size());
        request.setAttribute("boxProductList", result);

        return mapping.findForward("view");
    }

    /**
     * 新建装箱单初始化
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public ActionForward addBoxInit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("新建装箱单初始化");

        /* 产品分类 */
        productTypeService = (IProductTypeService) getBean("productTypeServiceImp");
        List<ProductTypeInfoDto> productTypeList = productTypeService
                .fingProductTypeAll();
        /* 物流公司 */
        ILogisticsService logisticsService = (ILogisticsService) getBean("logisticsServiceImp");
        List<LogisticsEntity> logisticsResult = logisticsService.getAllLogistics();

        BoxForm boxForm = (BoxForm) form;
        BoxDto para = boxForm.getAddPara();

        if (StringUtils.isNotBlank(para.getId())) {
            boxService = (IBoxService) Container.getBean("boxServiceImp");
            /* 装箱单信息 */
            BoxDto boxDto = boxService.getBox(para.getId());
            /* 产品信息 */
            StockSendGoodsDto stockSendGoods = new StockSendGoodsDto();
            stockSendGoods.setOrderIdArr(boxDto.getOrderIdArr());

            List<StockSendGoodsDetailDto> result = boxService
                    .getOrderDetail(stockSendGoods);

            request.setAttribute("box", boxDto);
            request.setAttribute("productListSize", result.size());
            request.setAttribute("boxProductList", result);
        }

        /* 防止重复提交 */
        this.saveToken(request);

        request.setAttribute("logisticsResult", logisticsResult);
        request.setAttribute("productTypeList", productTypeList);
        return mapping.findForward("addBoxInit");
    }

    /**
     * 新建装箱单
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addBox(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("新建装箱单");

        /* 防止重复提交 */
        if (isTokenValid(request)) {
            resetToken(request);
        } else {
            return mapping.findForward("submitRepeat");
        }

        BoxForm boxForm = (BoxForm) form;
        BoxDto para = boxForm.getAddPara();
        UserEntity userInfo = getLoginUserInfo(request);

        para.setUserId(userInfo.getId());
        para.setUserName(userInfo.getName());

        if (StringUtils.isBlank(para.getId())) {
            ICommonService commonService = (ICommonService) getBean("commonServiceImpl");
            String id = commonService.getSerialNumber("ZX", "BOX");
            para.setId(id);
        } else {
            para.setPageType(true);
        }

        if (StringUtils.equals("2", para.getSubmitType())) {
            if (StringUtils.equals(para.getTransportWay(), "1")) {
                para.setStatus("5");
            } else {
                para.setStatus("2");
            }
            para.setDate(Util.getDate());
        } else {
            para.setStatus("1");
        }
        para.setDatetime(Util.getDate());

        if (StringUtils.isBlank(para.getLogisticsId())) {
            para.setLogisticsId("0");
        }

        boxService = (IBoxService) Container.getBean("boxServiceImp");
        boolean result = boxService.addBox(para);

        if (result) {
            if (StringUtils.equals("2", para.getSubmitType())) {
                StringBuffer failUrl = new StringBuffer();
                failUrl.append("/getBoxView.do?addPara.id=");
                failUrl.append(para.getId());
                return new ActionForward(failUrl.toString());
            } else {
                return mapping.findForward("createSuccessSave");
            }
        } else {
            request.setAttribute("errorMsg", "新建失败！");
            return mapping.findForward("createFail");
        }
    }

    /**
     * 选择发货单小页面
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getOrder(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("选择发货单小页面");

        BoxForm boxForm = (BoxForm) form;
        UserEntity userInfo = getLoginUserInfo(request);

        StockSendGoodsDto para = new StockSendGoodsDto();
        para.setProductType(boxForm.getAddPara().getProductTypeId());
        para.setUser(userInfo);

        boxService = (IBoxService) Container.getBean("boxServiceImp");
        List<StockSendGoodsDto> resultList = boxService.getOrderList(para);

        request.setAttribute("resultList", resultList);
        request.setAttribute("productTypeName", boxForm.getAddPara().getProductTypeName());
        return mapping.findForward("getOrder");
    }

    /**
     * 选择发货单小页面 发货单明细
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getOrderDetail(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("选择发货单小页面 发货单明细");

        BoxForm boxForm = (BoxForm) form;
        String orderId = boxForm.getAddPara().getOrderId();
        // String orderType = boxForm.getOrderType();

        log.debug("orderId:" + orderId);
        // log.debug("orderType:" + orderType);

        boxService = (IBoxService) Container.getBean("boxServiceImp");
        StockSendGoodsDto para = new StockSendGoodsDto();
        para.setOrderIdArr(orderId.split(","));

        List<StockSendGoodsDetailDto> result = boxService.getOrderDetail(para);
        String str = null;

        if (result != null) {
            str = getStrbySendGoodsDetail(result);
        } else {
            str = "";
        }

        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(str);
        writer.close();
        return null;
    }

    /**
     * 封装返回结果
     * 
     * @param para
     * @return
     */
    private String getStrbySendGoodsDetail(List<StockSendGoodsDetailDto> para) {
        StringBuffer sb = new StringBuffer();
        for (StockSendGoodsDetailDto stockSendGoodsDetailDto : para) {
            sb.append(stockSendGoodsDetailDto.getOrderType()).append(",");
            sb.append(stockSendGoodsDetailDto.getOrderId()).append(",");
            sb.append(stockSendGoodsDetailDto.getStockroomName()).append(",");
            sb.append(stockSendGoodsDetailDto.getProductCode()).append(",");
            sb.append(stockSendGoodsDetailDto.getProductName()).append(",");

            sb.append(stockSendGoodsDetailDto.getProductType()).append(",");
            sb.append(stockSendGoodsDetailDto.getProductUnit()).append(",");
            sb.append(stockSendGoodsDetailDto.getCount()).append(",");

            sb.append(stockSendGoodsDetailDto.getNum()).append("&");
        }

        if (sb.length() > 0) {
            return sb.substring(0, sb.length() - 1);
        } else {
            return sb.toString();
        }

    }
}
