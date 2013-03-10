/**
 * ClassName  InStockAction
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-5-27
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.action.purchase;

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
import cn.com.thtf.egov.cms.dto.AddInStockDto;
import cn.com.thtf.egov.cms.dto.BuyContractDto;
import cn.com.thtf.egov.cms.dto.InStockProductDto;
import cn.com.thtf.egov.cms.dto.InStockQueryDto;
import cn.com.thtf.egov.cms.dto.InStockResultDto;
import cn.com.thtf.egov.cms.dto.ProductTypeInfoDto;
import cn.com.thtf.egov.cms.dto.ReceiveGoodsDetailDto;
import cn.com.thtf.egov.cms.entity.InStockEntity;
import cn.com.thtf.egov.cms.entity.ReceiveGoodsDetailEntity;
import cn.com.thtf.egov.cms.entity.StockroomEntity;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.form.InStockFrom;
import cn.com.thtf.egov.cms.iservice.IProductTypeService;
import cn.com.thtf.egov.cms.iservice.common.ICommonService;
import cn.com.thtf.egov.cms.iservice.inventory.IStockroomService;
import cn.com.thtf.egov.cms.iservice.purchase.IInStockService;
import cn.com.thtf.egov.cms.util.Container;
import cn.com.thtf.egov.cms.util.Util;

/**
 * 入库单
 * 
 * @author Lubo
 */
public class InStockAction extends NewBaseAction {

    /** log */
    private static Logger log = LoggerFactory.getLogger(InStockAction.class);
    /** IInStockService */
    private IInStockService inStockService;
    /** ICommonService */
    private ICommonService commonService;
    /** IStockroomService */
    private IStockroomService stockroomService;
    /** IProductTypeService */
    private IProductTypeService productTypeService = null;

    /**
     * 入库单评审
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward auditInstock(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("入库单评审");
        /* 防止重复提交 */
        if (isTokenValid(request)) {
            resetToken(request);
        } else {
            log.info("重复提交错误!");
            request.setAttribute("errorMsg", "重复提交错误！");
            return mapping.findForward("createFail");
        }

        inStockService = (IInStockService) getBean("inStockServiceImp");
        commonService = (ICommonService) getBean("commonServiceImpl");
        InStockFrom inStockFrom = (InStockFrom) form;
        InStockEntity auditPara = inStockFrom.getAuditPara();

        UserEntity userInfo = getLoginUserInfo(request);

        log.info("User:{},入库单评审:{}", userInfo.getId(), auditPara.getId());
        log.info("入库单评审:{},评审意见:{}", auditPara.getId(), auditPara.getProMajIdea());

        // 入库单状态的判断
        String status = commonService.getStatusById("in_stock", auditPara.getId());
        if (StringUtils.equals("2", status)) {
            auditPara.setProMajId(userInfo.getId());
            auditPara.setProMajName(userInfo.getName());
            auditPara.setProMajDate(Util.getDate());

            boolean result = inStockService.auditInstock(auditPara);
            if (!result) {
                log.info("User:{},入库单评审,评审失败{}", userInfo.getId(), auditPara.getId());
                request.setAttribute("errorMsg", "评审失败！");
                return mapping.findForward("createFail");
            } else {
                log.info("User:{},入库单评审,评审成功:{}", userInfo.getId(), auditPara.getId());
                return mapping.findForward("createSuccess");
            }
        } else {
            log.info("User:{},入库单评审,评审失败{}", userInfo.getId(), auditPara.getId());
            log.info("User:{},入库单评审,评审失败{}", userInfo.getId(), status);
            request.setAttribute("errorMsg", "评审失败！");
            return mapping.findForward("createFail");
        }

    }

    /**
     * 入库单管理
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public ActionForward getInstockList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("入库单管理");

        inStockService = (IInStockService) getBean("inStockServiceImp");
        InStockFrom inStockFrom = (InStockFrom) form;
        InStockQueryDto queryPara = inStockFrom.getQueryPara();

        /* 检索所有库房 */
        stockroomService = (IStockroomService) getBean("stockroomServiceImp");
        List<StockroomEntity> stockroomList = stockroomService.getTypicallyAndVirtual();

        /* 产品分类 */
        productTypeService = (IProductTypeService) getBean("productTypeServiceImp");
        List<ProductTypeInfoDto> productTypeList = productTypeService
                .fingProductTypeAll();

        /* 封装翻页参数 */
        NewPage newPage = getNewPage(request);
        newPage.setUrl("instockList.do");
        newPage.setQuery("queryPara.instockId", queryPara.getInstockId());
        newPage.setQuery("queryPara.stockroomId", queryPara.getStockroomId());
        newPage.setQuery("queryPara.productTypeId", queryPara.getProductTypeId());
        newPage.setQuery("queryPara.productContractCode",
                queryPara.getProductContractCode());
        newPage.setQuery("queryPara.companyContarctCode",
                queryPara.getCompanyContarctCode());

        newPage.setQuery("queryPara.supplierName", queryPara.getSupplierName());
        newPage.setQuery("queryPara.starttime", queryPara.getStarttime());
        newPage.setQuery("queryPara.endtime", queryPara.getEndtime());
        newPage.setQuery("queryPara.stkStarttime", queryPara.getStkStarttime());
        newPage.setQuery("queryPara.stkEndtime", queryPara.getStkEndtime());

        newPage.setQuery("queryPara.userName", queryPara.getUserName());
        newPage.setQuery("queryPara.status", queryPara.getStatus());
        newPage.setQuery("queryPara.init", queryPara.getInit());
        newPage.setQuery("queryPara.requestAccountStarttime",
                queryPara.getRequestAccountStarttime());
        newPage.setQuery("queryPara.requestAccountEndtime",
                queryPara.getRequestAccountEndtime());

        UserEntity userInfo = getLoginUserInfo(request);
        queryPara.setUserId(userInfo.getId());
        queryPara.setRoleId(userInfo.getRoleId());

        List<InStockResultDto> instockList = inStockService.getInStockList(queryPara,
                newPage);

        request.setAttribute("roleId", userInfo.getRoleId());
        request.setAttribute("NewPage", newPage);
        request.setAttribute("instockList", instockList);
        request.setAttribute("queryPara", queryPara);
        request.setAttribute("productTypeList", productTypeList);
        request.setAttribute("stockroomList", stockroomList);
        return mapping.findForward("getInstockList");
    }

    /**
     * 入库单查看
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getInstock(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("入库单查看");

        inStockService = (IInStockService) getBean("inStockServiceImp");
        InStockFrom inStockFrom = (InStockFrom) form;

        /* 入库单ID */
        String instockId = inStockFrom.getAddPara().getId();

        /* 入库单信息 */
        InStockEntity inStockInfo = inStockService.getInStock(instockId);
        String buyContractId = inStockInfo.getBuyContractId();
        /* 收获地址信息 */
        ReceiveGoodsDetailEntity receiveGoodsPara = new ReceiveGoodsDetailEntity();
        receiveGoodsPara.setId(inStockInfo.getReceiveGoodsDetailId());

        List<ReceiveGoodsDetailDto> receiveGoodsResult = inStockService
                .getReceiveGoodsDetail(receiveGoodsPara);
        /* 产品信息 */
        ReceiveGoodsDetailDto productPara = new ReceiveGoodsDetailDto();
        productPara.setBuyContractId(buyContractId);
        productPara.setCompanyType(receiveGoodsResult.get(0).getCompanyType());
        productPara.setAddressId(receiveGoodsResult.get(0).getAddressId());
        productPara.setInStockId(instockId);

        List<InStockProductDto> productList = inStockService
                .getProductListView(productPara);

        BuyContractDto buyContract = inStockService.getBuyContract(buyContractId);

        request.setAttribute("productList", productList);
        request.setAttribute("productListSize", productList.size());
        request.setAttribute("receiveGoodsDetail", receiveGoodsResult.get(0));
        request.setAttribute("inStockInfo", inStockInfo);
        request.setAttribute("buyContract", buyContract);

        // 判断是否是评审
        if (StringUtils.isNotBlank(inStockFrom.getIsAudit())) {
            /* 防止重复提交 */
            this.saveToken(request);
            return mapping.findForward("auditView");
        } else {
            return mapping.findForward("getInstock");
        }
    }

    /**
     * 新建修改入库单初始化
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addItockInit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("新建、修改入库单初始化");

        inStockService = (IInStockService) getBean("inStockServiceImp");
        InStockFrom inStockFrom = (InStockFrom) form;
        String buyContractId = null;

        /* 入库单ID */
        String instockId = inStockFrom.getAddPara().getId();
        if (StringUtils.isNotBlank(instockId)) {
            /* 入库单信息 */
            InStockEntity inStockInfo = inStockService.getInStock(instockId);
            buyContractId = inStockInfo.getBuyContractId();
            /* 收获地址信息 */
            ReceiveGoodsDetailEntity receiveGoodsPara = new ReceiveGoodsDetailEntity();
            receiveGoodsPara.setId(inStockInfo.getReceiveGoodsDetailId());

            List<ReceiveGoodsDetailDto> receiveGoodsResult = inStockService
                    .getReceiveGoodsDetail(receiveGoodsPara);
            /* 产品信息 */
            ReceiveGoodsDetailDto productPara = new ReceiveGoodsDetailDto();
            productPara.setBuyContractId(buyContractId);
            productPara.setCompanyType(receiveGoodsResult.get(0).getCompanyType());
            productPara.setAddressId(receiveGoodsResult.get(0).getAddressId());
            productPara.setInStockId(instockId);

            List<InStockProductDto> productList = inStockService
                    .getProductListView(productPara);

            request.setAttribute("productList", productList);
            request.setAttribute("productListSize", productList.size());
            request.setAttribute("receiveGoodsDetail", receiveGoodsResult.get(0));
            request.setAttribute("inStockInfo", inStockInfo);
        } else {
            buyContractId = inStockFrom.getAddPara().getBuyContractId();
        }

        BuyContractDto buyContract = inStockService.getBuyContract(buyContractId);

        /* 防止重复提交 */
        this.saveToken(request);

        request.setAttribute("buyContract", buyContract);
        return mapping.findForward("inStock");
    }

    /**
     * 新建修改入库单
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addInStock(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("新建修改入库单");

        /* 防止重复提交 */
        if (isTokenValid(request)) {
            resetToken(request);
        } else {
            return mapping.findForward("submitRepeat");
        }

        inStockService = (IInStockService) getBean("inStockServiceImp");
        commonService = (ICommonService) getBean("commonServiceImpl");

        InStockFrom inStockFrom = (InStockFrom) form;
        UserEntity userInfo = getLoginUserInfo(request);
        log.info("User:{},新建修改入库单", userInfo.getId());

        AddInStockDto para = inStockFrom.getAddPara();
        para.setUserId(userInfo.getId());
        para.setUserName(userInfo.getName());

        /* 获取入库单编号 */
        if (StringUtils.isBlank(para.getId())) {
            String id = commonService.getSerialNumber("RK", "IN_STOCK");
            para.setId(id);
        } else {
            para.setPageType(true);
        }

        /* 如果点击的是保存 那么状态是1 date不更新 */
        if (StringUtils.equals(para.getSubmitType(), "1")) {
            para.setStatus("1");
        } else {
            para.setStatus("2");
            para.setDate(Util.getDate());
        }

        para.setDatetime(Util.getDate());

        boolean result = inStockService.addInStock(para);
        if (result) {
            log.info("User:{},新建入库单成功", userInfo.getId());
            return mapping.findForward("createSuccess");
        } else {
            log.info("User:{},新建入库单失败", userInfo.getId());
            request.setAttribute("errorMsg", "新建入库单错误！");
            return mapping.findForward("createFail");
        }
    }

    /**
     * 选择产品
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getProductList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("选择产品");

        InStockFrom inStockFrom = (InStockFrom) form;
        String buyContractId = inStockFrom.getAddPara().getBuyContractId();
        String companyType = inStockFrom.getAddPara().getCompanyType();
        String addressId = inStockFrom.getAddPara().getAddressId();

        ReceiveGoodsDetailDto para = new ReceiveGoodsDetailDto();
        para.setBuyContractId(buyContractId);
        para.setCompanyType(companyType);
        para.setAddressId(addressId);

        inStockService = (IInStockService) getBean("inStockServiceImp");
        List<InStockProductDto> resultList = inStockService.getProductList(para);

        request.setAttribute("resultList", resultList);
        return mapping.findForward("productList");
    }

    /**
     * 选择收获地址
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getReceiveGoodsDetail(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("选择收获地址");

        InStockFrom inStockFrom = (InStockFrom) form;
        String buyContractId = inStockFrom.getAddPara().getBuyContractId();
        String receiveId = inStockFrom.getAddPara().getReceiveId();

        inStockService = (IInStockService) Container.getBean("inStockServiceImp");
        ReceiveGoodsDetailEntity para = new ReceiveGoodsDetailEntity();
        para.setBuyContractId(buyContractId);

        List<ReceiveGoodsDetailDto> resultList = inStockService
                .getReceiveGoodsDetail(para);

        if (StringUtils.isNotBlank(receiveId)) {
            request.setAttribute("receiveId", receiveId);
        }
        request.setAttribute("resultList", resultList);
        return mapping.findForward("receiveGoodsDetailList");
    }

    /**
     * 入库单删除
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward removeInstock(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("入库单删除");

        inStockService = (IInStockService) getBean("inStockServiceImp");
        InStockFrom inStockFrom = (InStockFrom) form;
        /* 入库单ID */
        String instockId = inStockFrom.getAddPara().getId();

        UserEntity userInfo = getLoginUserInfo(request);
        log.info("User:{},入库单删除:{}", userInfo.getId(), instockId);

        boolean reuslt = inStockService.removeInstock(instockId);
        if (!reuslt) {
            log.info("User:{},入库单删除,失败:{}", userInfo.getId(), instockId);
            request.setAttribute("errorMsg", "删除失败！");
        }
        return mapping.findForward("getInstockList");
    }
}
