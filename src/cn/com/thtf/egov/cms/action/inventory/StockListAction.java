/**
 * ClassName  StockListAction
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-6-3
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.action.inventory;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.NewBaseAction;
import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.dto.ProductSerieInfoDto;
import cn.com.thtf.egov.cms.dto.ProductTypeInfoDto;
import cn.com.thtf.egov.cms.dto.StockProductDto;
import cn.com.thtf.egov.cms.dto.StockQueryDto;
import cn.com.thtf.egov.cms.entity.StockroomEntity;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.form.StockForm;
import cn.com.thtf.egov.cms.iservice.IProductSerieService;
import cn.com.thtf.egov.cms.iservice.IProductTypeService;
import cn.com.thtf.egov.cms.iservice.inventory.IStockService;
import cn.com.thtf.egov.cms.iservice.inventory.IStockroomService;

/**
 * StockListAction
 * 
 * @author Lubo
 */
public class StockListAction extends NewBaseAction {

    /** log */
    private static Logger log = LoggerFactory.getLogger(StockListAction.class);
    /** IStockroomService */
    private IStockroomService stockroomService;
    /** IProductTypeService */
    private IProductTypeService productTypeService;
    /** IStockService */
    private IStockService stockService;

    /**
     * 库存管理
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public ActionForward stockList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("库存管理");
        StockForm stockForm = (StockForm) form;
        StockQueryDto queryPara = stockForm.getQueryPara();

        UserEntity userInfo = getLoginUserInfo(request);
        queryPara.setUser(userInfo);

        /* 检索所有库房 */
        stockroomService = (IStockroomService) getBean("stockroomServiceImp");
        List<StockroomEntity> stockroomList = stockroomService.getAllStockRoom();

        /* 产品分类 */
        productTypeService = (IProductTypeService) getBean("productTypeServiceImp");
        List<ProductTypeInfoDto> productTypeList = productTypeService
                .fingProductTypeAll();

        /* 封装翻页参数 */
        NewPage newPage = getNewPage(request);
        newPage.setUrl("stock.do");
        newPage.setQuery("queryPara.productTypeId", queryPara.getProductTypeId());
        newPage.setQuery("queryPara.productSerieId", queryPara.getProductSerieId());
        newPage.setQuery("queryPara.productCode", queryPara.getProductCode());
        newPage.setQuery("queryPara.productName", queryPara.getProductName());
        newPage.setQuery("queryPara.productType", queryPara.getProductType());

        newPage.setQuery("queryPara.sendgoodsType", queryPara.getSendgoodsType());
        newPage.setQuery("queryPara.stockroomType", queryPara.getStockroomType());
        newPage.setQuery("queryPara.stockroomId", queryPara.getStockroomId());

        stockService = (IStockService) getBean("stockServiceImp");
        List<StockProductDto> stockList = stockService.getStockList(queryPara, newPage);

        request.setAttribute("roleId", userInfo.getRoleId());
        request.setAttribute("stockList", stockList);
        request.setAttribute("NewPage", newPage);
        request.setAttribute("queryPara", queryPara);
        request.setAttribute("stockroomList", stockroomList);
        request.setAttribute("productTypeList", productTypeList);
        return mapping.findForward("stockList");
    }

    /**
     * 根据产品分类名称获取系列名称
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public ActionForward getProductSerie(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("称获取系列名称");

        String productTypeId = ((StockForm) form).getQueryPara().getProductTypeId();

        IProductSerieService productSerieService = (IProductSerieService) getBean("productSerieServiceImp");
        List<ProductSerieInfoDto> result = productSerieService
                .productSerieNameAll(Integer.parseInt(productTypeId));

        // 将 查到的所有类型转换成json格式
        JSONArray resultList = new JSONArray();
        for (int i = 0; i < result.size(); i++) {
            JSONObject object = new JSONObject();
            object.put("id", result.get(i).getId());
            object.put("name", result.get(i).getName());
            resultList.put(object);
        }

        // 将json串写回客户端
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(resultList.toString());
        writer.close();
        return null;
    }

    /**
     * 库存明细
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getStock(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("库存明细");
        StockForm stockForm = (StockForm) form;
        StockQueryDto queryPara = stockForm.getQueryPara();
        UserEntity userInfo = getLoginUserInfo(request);

        /* 封装翻页参数 */
        NewPage newPage = getNewPage(request);
        newPage.setUrl("stockView.do");
        newPage.setQuery("queryPara.productId", queryPara.getProductId());
        newPage.setQuery("queryPara.stockroomType", queryPara.getStockroomType());
        newPage.setQuery("queryPara.stockroomId", queryPara.getStockroomId());

        stockService = (IStockService) getBean("stockServiceImp");
        List<StockProductDto> stockList = stockService.getStockDetileList(queryPara,
                newPage);
        request.setAttribute("roleId", userInfo.getRoleId());
        request.setAttribute("stockList", stockList);
        request.setAttribute("NewPage", newPage);
        return mapping.findForward("getStock");
    }
}
