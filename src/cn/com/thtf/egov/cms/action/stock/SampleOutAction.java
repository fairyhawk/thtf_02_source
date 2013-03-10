/**
 * ClassName  SampleOutAction
 *
 * History
 * Create User: liuqg
 * Create Date: 2010-7-5
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.action.stock;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.NewBaseAction;
import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.constant.Constants;
import cn.com.thtf.egov.cms.dto.CompanyCustomerSupplierAddressDto;
import cn.com.thtf.egov.cms.dto.MoveOutStockDto;
import cn.com.thtf.egov.cms.dto.MoveStockProductDto;
import cn.com.thtf.egov.cms.dto.SalesConProductDto;
import cn.com.thtf.egov.cms.dto.SampleOutAssessDto;
import cn.com.thtf.egov.cms.dto.SampleOutListDto;
import cn.com.thtf.egov.cms.dto.SampleOutProductInfoDto;
import cn.com.thtf.egov.cms.dto.SampleOutSearchDto;
import cn.com.thtf.egov.cms.dto.WorkReceiverDto;
import cn.com.thtf.egov.cms.entity.CompanyEntity;
import cn.com.thtf.egov.cms.entity.CustomerEntity;
import cn.com.thtf.egov.cms.entity.ProductTypeEntity;
import cn.com.thtf.egov.cms.entity.SampleOutDetailEntity;
import cn.com.thtf.egov.cms.entity.SampleOutEntity;
import cn.com.thtf.egov.cms.entity.StockEntity;
import cn.com.thtf.egov.cms.entity.StockroomEntity;
import cn.com.thtf.egov.cms.entity.SupplierAddressEntity;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.entity.WorkEntity;
import cn.com.thtf.egov.cms.iservice.common.ICommonService;
import cn.com.thtf.egov.cms.iservice.invoice.IReceiveInvoiceService;
import cn.com.thtf.egov.cms.iservice.purchase.IBuyBackGoodsService;
import cn.com.thtf.egov.cms.iservice.purchase.IBuyContractService;
import cn.com.thtf.egov.cms.iservice.sell.ISalesContractManagementService;
import cn.com.thtf.egov.cms.iservice.stock.IMoveStockService;
import cn.com.thtf.egov.cms.iservice.stock.ISampleOutService;

/**
 * 
 * @author liuqg
 */

public class SampleOutAction extends NewBaseAction {
    private static Logger log = LoggerFactory.getLogger(SampleOutAction.class);
    private ISampleOutService sampleOutService = null;
    private ICommonService commonService = null;

    /**
     * 样品借出管理初始化
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @author zhangzx
     */
    public ActionForward getSampleOutList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("进入样品借出管理初始列表");

        sampleOutService = (ISampleOutService) getBean("sampleOutServiceImpl");

        // 获取用户ID
        UserEntity user = (UserEntity) request.getSession().getAttribute(
                Constants.USERLOGIN);
        // 分页
        NewPage newPage = getNewPage(request);
        newPage.setUrl("getSampleOutList.do");

        List<SampleOutListDto> sampleOutListDtos = null;
        List<ProductTypeEntity> productTypeEntities = null;
        List<StockroomEntity> stockroomEntities = null;

        try {
            sampleOutListDtos = sampleOutService.getSampleOutList(newPage, user);
            productTypeEntities = sampleOutService.getProductType();
            stockroomEntities = sampleOutService.getStockroom();
        } catch (Exception e) {
            log.error("获取样品借出单一览错误", e);
        }
        // 借出列表
        request.setAttribute("sampleOutList", sampleOutListDtos);
        // 产品分类
        request.setAttribute("productTypeEntitiesList", productTypeEntities);
        // 库房
        request.setAttribute("stockroomEntitiesList", stockroomEntities);
        // 分页
        request.setAttribute("NewPage", newPage);

        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 根据条件检索
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @author zhangzx
     */

	public ActionForward searchSampleOut(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        log.debug("检索样品借出单一览");

        // 检索条件放到request中,保存画面检索条件
        DynaActionForm dform = (DynaActionForm) form;

        SampleOutSearchDto sampleOutSearchDto = (SampleOutSearchDto) dform
                .get("searchSampleOutDto");

        // 获取用户ID
        UserEntity user = (UserEntity) request.getSession().getAttribute(
                Constants.USERLOGIN);

        // 将当前登录用户的ID和权限放在检索用DTO中
        sampleOutSearchDto.setLoginId(user.getId());
        sampleOutSearchDto.setRoleId(String.valueOf(user.getRoleId()));

        request.setAttribute("sampleOutSearchDto", sampleOutSearchDto);

        // 分页
        NewPage newPage = getNewPage(request);
        newPage.setUrl("searchSampleOut.do");
        // 页面检索条件保存
        setNewPagePara(newPage, sampleOutSearchDto);

        sampleOutService = (ISampleOutService) getBean("sampleOutServiceImpl");

        List<SampleOutListDto> sampleOutListDtos = null;
        List<ProductTypeEntity> productTypeEntities = null;
        List<StockroomEntity> stockroomEntities = null;

        try {
            sampleOutListDtos = sampleOutService.getSampleOutListByCondition(newPage,
                    sampleOutSearchDto);
            productTypeEntities = sampleOutService.getProductType();
            stockroomEntities = sampleOutService.getStockroom();
        } catch (Exception e) {
            log.error("获取样品借出单一览错误", e);
        }
        // 样品借出列表
        request.setAttribute("sampleOutList", sampleOutListDtos);
        // 产品分类
        request.setAttribute("productTypeEntitiesList", productTypeEntities);
        // 库房
        request.setAttribute("stockroomEntitiesList", stockroomEntities);
        // 分页
        request.setAttribute("NewPage", newPage);

        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 分页条件设置
     * 
     * @author zzx
     * @param newPage
     * @param moveStockSearchDto
     * @return {@link Void}
     */

    private void setNewPagePara(NewPage newPage, SampleOutSearchDto sampleOutSearchDto) {
        // 样品借出单号
        if (StringUtils.isNotEmpty(sampleOutSearchDto.getId())) {
            newPage.setQuery("searchSampleOutDto.id", sampleOutSearchDto.getId());
        }
        // 库房名称
        if (StringUtils.isNotEmpty(sampleOutSearchDto.getOutStockroomId())) {
            newPage.setQuery("searchSampleOutDto.outStockroomId", sampleOutSearchDto
                    .getOutStockroomId());
        }
        // 产品分类编号
        if (StringUtils.isNotEmpty(sampleOutSearchDto.getProductTypeId())) {
            newPage.setQuery("searchSampleOutDto.productTypeId", sampleOutSearchDto
                    .getProductTypeId());
        }
        // 借出单位类型
        if (StringUtils.isNotEmpty(sampleOutSearchDto.getCompanyType())) {
            newPage.setQuery("searchSampleOutDto.companyType", sampleOutSearchDto
                    .getCompanyType());
        }
        // 借出单位名称
        if (StringUtils.isNotEmpty(sampleOutSearchDto.getCompanyName())) {
            newPage.setQuery("searchSampleOutDto.companyName", sampleOutSearchDto
                    .getCompanyName());
        }
        // 申请起始日期
        if (StringUtils.isNotEmpty(sampleOutSearchDto.getDateStart())) {
            newPage.setQuery("searchSampleOutDto.dateStart", sampleOutSearchDto
                    .getDateStart());
        }
        // 申请终止日期
        if (StringUtils.isNotEmpty(sampleOutSearchDto.getDateEnd())) {
            newPage.setQuery("searchSampleOutDto.dateEnd", sampleOutSearchDto
                    .getDateEnd());
        }
        // 要求发货起始日期
        if (StringUtils.isNotEmpty(sampleOutSearchDto.getRequestDateStart())) {
            newPage.setQuery("searchSampleOutDto.requestDateStart", sampleOutSearchDto
                    .getRequestDateStart());
        }
        // 要求发货终止日期
        if (StringUtils.isNotEmpty(sampleOutSearchDto.getRequestDateEnd())) {
            newPage.setQuery("searchSampleOutDto.requestDateEnd", sampleOutSearchDto
                    .getRequestDateEnd());
        }
        // 预计归还起始日期
        if (StringUtils.isNotEmpty(sampleOutSearchDto.getInDateStart())) {
            newPage.setQuery("searchSampleOutDto.inDateStart", sampleOutSearchDto
                    .getInDateStart());
        }
        // 预计归还终止日期
        if (StringUtils.isNotEmpty(sampleOutSearchDto.getInDateEnd())) {
            newPage.setQuery("searchSampleOutDto.inDateEnd", sampleOutSearchDto
                    .getInDateEnd());
        }
        // 发货起始日期
        if (StringUtils.isNotEmpty(sampleOutSearchDto.getSendDateStart())) {
            newPage.setQuery("searchSampleOutDto.sendDateStart", sampleOutSearchDto
                    .getSendDateStart());
        }
        // 发货终止日期
        if (StringUtils.isNotEmpty(sampleOutSearchDto.getSendDateEnd())) {
            newPage.setQuery("searchSampleOutDto.sendDateEnd", sampleOutSearchDto
                    .getSendDateEnd());
        }
        // 申请人名称
        if (StringUtils.isNotEmpty(sampleOutSearchDto.getUserName())) {
            newPage.setQuery("searchSampleOutDto.userName", sampleOutSearchDto
                    .getUserName());
        }
        // 保管人名称
        if (StringUtils.isNotEmpty(sampleOutSearchDto.getCustosName())) {
            newPage.setQuery("searchSampleOutDto.custosName", sampleOutSearchDto
                    .getCustosName());
        }
        // 借出单类型
        if (StringUtils.isNotEmpty(sampleOutSearchDto.getType())) {
            newPage.setQuery("searchSampleOutDto.type", sampleOutSearchDto.getType());
        }
        // 借出单状态
        if (StringUtils.isNotEmpty(sampleOutSearchDto.getSampleOutStatus())) {
            newPage.setQuery("searchSampleOutDto.status", sampleOutSearchDto
                    .getSampleOutStatus());
        }
    }

    /**
     * 样品借出修改显示信息
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @author zhangzx
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public ActionForward getSampleOutModifyInfo(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("进入样品借出修改页面");

        String sampleOutId = request.getParameter("sampleOutId");

        // 获得spring的bean容器
        sampleOutService = (ISampleOutService) getBean("sampleOutServiceImpl");

        // 根据id获得样品信息
        SampleOutAssessDto assessDto = sampleOutService.getSampleOutById(sampleOutId);
        String companyType = String.valueOf(assessDto.getCompanyType());
        //获得发货地址
        Map map = new HashMap();
        map.put("companyType", companyType);
        map.put("addressId", assessDto.getAddressId());
        
        CompanyCustomerSupplierAddressDto addressDto = sampleOutService.getAddressInfo(map);
        
        request.setAttribute("addressDto", addressDto);

        request.setAttribute("assessDto", assessDto);

        List<ProductTypeEntity> productTypeEntities = null;
        List<StockroomEntity> stockroomEntities = null;
        List<SampleOutProductInfoDto> list = null;

        // 获取用户登陆Id
        UserEntity user = (UserEntity) request.getSession().getAttribute(
                Constants.USERLOGIN);
        /* 产品分类 */
        commonService = (ICommonService) getBean("commonServiceImpl");

        try {
            productTypeEntities = commonService.getAllProductTypes(null, user.getId(),
                    user.getRoleId() + "");
            stockroomEntities = sampleOutService.getStockroom();
            // 产品列表
            list = sampleOutService.getProductInfoModify(assessDto);
        } catch (Exception e) {
            log.error("获取样品借出单修改信息错误", e);
        }
        request.setAttribute("list", list);

        // 产品分类
        request.setAttribute("productTypeEntitiesList", productTypeEntities);
        // 库房
        request.setAttribute("stockroomEntitiesList", stockroomEntities);
        //获得公司名称
        CompanyEntity companyEntity = sampleOutService.selectcompany();
        request.setAttribute("companyEntity", companyEntity);
        
        /* 防止重复提交 */
        saveToken(request);

        return mapping.findForward(Constants.SUCCESS);
    }
    
    /**
     * 修改样品借出单保存、提交
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @author zhangzx
     */
    public ActionForward modifySampleOut(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("样品借出单修改");

        String method = request.getParameter("method");

        /* 防止重复提交 */
        if (isTokenValid(request)) {
            resetToken(request);
        } else {
            if ("save".equals(method)) {
                request.setAttribute("msg", "请勿重复保存 !");
            } else {
                request.setAttribute("msg", "请勿重复提交!");
            }
            return mapping.findForward(Constants.SUCCESS);
        }
        // 获得spring的bean容器
        sampleOutService = (ISampleOutService) getBean("sampleOutServiceImpl");
        // 获取用户登陆Id
        UserEntity user = (UserEntity) request.getSession().getAttribute(
                Constants.USERLOGIN);

        DynaActionForm actionForm = (DynaActionForm) form;

        SampleOutEntity modifySampleOutDto = (SampleOutEntity) actionForm.get("modifySampleOutDto");
        
        //modifySampleOutDto.setAddressId(Integer.valueOf(request.getParameter("modifySampleOutDto.addressId")));
        
        // 采购主管登录名
        WorkReceiverDto workReceiverDto = commonService.getUserIdByCondition(modifySampleOutDto
                .getProductTypeId(), null, null, Constants.ROLE_PROCUREMENT_OFFICER);
        String buyManId = workReceiverDto
                .getUserIdByRoleId(Constants.ROLE_PROCUREMENT_OFFICER);
        modifySampleOutDto.setBuyManId(buyManId);
        
       // 销售总监登录名
        workReceiverDto = commonService.getUserIdByCondition(modifySampleOutDto
                .getProductTypeId(),null, null,Constants.ROLE_SALES_DIRECTOR);
        String sellMajId = workReceiverDto
                .getUserIdByRoleId(Constants.ROLE_SALES_DIRECTOR);
        modifySampleOutDto.setSellMajId(sellMajId);

        // 库房管理员登录名
        workReceiverDto = commonService.getUserIdByCondition(modifySampleOutDto
                .getProductTypeId(),null,modifySampleOutDto
                .getOutStockroomId());
        String stkAdmId = workReceiverDto.getUserId();
        modifySampleOutDto.setStkAdmId(stkAdmId);
         

        String sampleOutId = modifySampleOutDto.getId();

        /* 样品借出明细添加 */
        List<SampleOutDetailEntity> list = new ArrayList<SampleOutDetailEntity>();
        /* 产品编号 */
        String[] productIds = request.getParameterValues("productIds");
        /* 借出数 */
        String[] counts = request.getParameterValues("counts");
        /* 时间戳 */
        String[] timeStamps = request.getParameterValues("timeStamps");
        /*重新获取库存单价*/
        //List productPriceList = sampleOutService.getProductPriceModify(productIds);
        String tmpprice="0";
        IMoveStockService moveStockService = (IMoveStockService) getBean("moveStockServiceImpl");
        for (int i = 0; i < productIds.length; i++) {
            SampleOutDetailEntity sampleOutDetailEntity = new SampleOutDetailEntity();
            sampleOutDetailEntity.setSampleOutId(sampleOutId);
            sampleOutDetailEntity.setCount(Integer.valueOf(counts[i]));
            sampleOutDetailEntity.setProductId(Integer.valueOf(productIds[i]));
            tmpprice= commonService.getProdAvePrice(productIds[i],"0");
            sampleOutDetailEntity.setPrice(new BigDecimal(tmpprice));
            list.add(sampleOutDetailEntity);
        }
      //计算借出金额
        modifySampleOutDto.setMoney(getMoney(list));
        //log.info("getMoney==:"+modifySampleOutDto.getMoney());
        if ("save".equals(method)) {
            // 修改返厂保存
            modifySampleOutDto.setStatus(1);
            /* 销售经理 */
            if ("4".equals(String.valueOf(user.getRoleId()))) {
                //借出单位类型为客户
                modifySampleOutDto.setCompanyType(2);
            }                

            if (sampleOutService.modifySampleOutSave(modifySampleOutDto, list)) {
                log.info("User:{},{} 修改样品借出保存成功", user.getId(), user.getName());
                return mapping.findForward("success");
            } else {
                log.info("User:{},{} 修改样品借出保存失败", user.getId(), user.getName());
                request.setAttribute("msg", "修改失败");
                return mapping.findForward("failure");
            }

        } else if ("submit".equals(method)) {

            /* 产品冻结数 */
            List<StockEntity> stockEntities = new ArrayList<StockEntity>();

            for (int i = 0; i < productIds.length; i++) {

                StockEntity stockEntity = new StockEntity();
                stockEntity.setSendLock(Integer.valueOf(counts[i]));// 本次要增加的冻结数
                stockEntity.setProductId(Integer.valueOf(productIds[i]));
                stockEntity.setStockroomId(modifySampleOutDto.getOutStockroomId());
             // 后台再次判断借出数是否正确 add 20110106 start
                MoveStockProductDto moveStockProductDto = new MoveStockProductDto();
                moveStockProductDto.setProTypeId(productIds[i]);
                moveStockProductDto.setOutStockroomId(modifySampleOutDto.getOutStockroomId().toString());
                MoveOutStockDto moveOutStockDto = moveStockService
                        .getStockByStockRoomAndProduct(moveStockProductDto);
                if (moveOutStockDto==null){
                    request.setAttribute("msg", "获取产品库存信息失败");
                    return mapping.findForward("failure");
                }
                if (stockEntity.getSendLock() > moveOutStockDto.getUseCount()) {
                    request.setAttribute("msg", "借出数量不能大于借出可用数");
                    return mapping.findForward("failure");
                }
                stockEntity.setTimeStamp(moveOutStockDto.getTimeStamp());
                // 后台再次判断借出数是否正确 add 20110106 end
                stockEntities.add(stockEntity);
            }

            /* 采购专员 */
            if ("8".equals(String.valueOf(user.getRoleId()))) {
                // 状态
                modifySampleOutDto.setStatus(4);
                /* 提交 给采购主管发待办事务 */
                WorkEntity work = new WorkEntity();
                work.setUserId(modifySampleOutDto.getBuyManId());
                work.setWorkId(23);// 借出单待评审
                work.setCount(1);

                if (sampleOutService.modifySampleOutSubmitWork(modifySampleOutDto, list, work, stockEntities)) {
                    log.info("User:{},{} 修改样品借出提交成功", user.getId(), user.getName());
                    return mapping.findForward("success");
                } else {
                    log.info("User:{},{} 修改样品借出提交失败", user.getId(), user.getName());
                    request.setAttribute("msg", "提交失败");
                    return mapping.findForward("failure");
                }
            } else if ("4".equals(String.valueOf(user.getRoleId()))) {
                /* 销售经理 */
                // 状态
                modifySampleOutDto.setStatus(2);
                modifySampleOutDto.setCompanyType(2);
                /* 提交 给销售总监发待办事务 */
                WorkEntity work = new WorkEntity();
                work.setUserId(modifySampleOutDto.getSellMajId());
                work.setWorkId(23);// 借出单待评审
                work.setCount(1);
                if (sampleOutService.modifySampleOutSubmitWork(modifySampleOutDto, list, work, stockEntities)) {
                    log.info("User:{},{} 修改样品借出保存成功", user.getId(), user.getName());
                    return mapping.findForward("success");
                } else {
                    log.info("User:{},{} 修改样品借出保存失败", user.getId(), user.getName());
                    request.setAttribute("msg", "修改失败");
                    return mapping.findForward("failure");
                }
            } else {
                request.setAttribute("msg", "非法提交");
                return mapping.findForward("failure");
            }
        } else {
            request.setAttribute("msg", "非法提交");
            return mapping.findForward("failure");
        }

    }
    /**
     * 计算借出金额
     * @param list
     * @return
     * @author zhangzx
     */
    private BigDecimal getMoney(List<SampleOutDetailEntity> list){
        BigDecimal money =new BigDecimal("0.00");
        for(int i=0;i<list.size();i++){
            SampleOutDetailEntity sampleOutDetailEntity = list.get(i);
            BigDecimal partMoney= sampleOutDetailEntity.getPrice().multiply(new BigDecimal(sampleOutDetailEntity.getCount()));   
            money = money.add(partMoney);
        }
        return money;
    }
    
    
    /**
     * 产品选择检索
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public ActionForward getSampleOutProductSelect(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        log.debug("进入样品借出产品选择画面");

        // 参数获取
        MoveStockProductDto moveStockProductDto = new MoveStockProductDto();

        String productTypeId = request.getParameter("productTypeId");
        String outStockroomId = request.getParameter("outStockroomId");
        moveStockProductDto.setProTypeId(productTypeId);
        moveStockProductDto.setOutStockroomId(outStockroomId);
        String productTypeName = request.getParameter("productTypeName");

        request.setAttribute("productTypeId", productTypeId);
        request.setAttribute("outStockroomId", outStockroomId);
        request.setAttribute("productTypeName", productTypeName);

        // 根据产品分类获得产品系列
        ISalesContractManagementService salesContractService = (ISalesContractManagementService) this
                .getBean("salesContractManagementServiceImp");
        List<SalesConProductDto> serieList = salesContractService
                .getProSerieByProTypeId(productTypeId);
        ;
        request.setAttribute("serieList", serieList);

        // 分頁
        NewPage newPage = getNewPage(request);
        newPage.setUrl("getSampleOutProductSelect.do");

        String productName = request.getParameter("productName");
        String serieId = request.getParameter("proSerieId");
        String proCode = request.getParameter("productCode");
        String productType = request.getParameter("productType");

        moveStockProductDto.setProductName(productName);
        moveStockProductDto.setProSerieId(serieId);
        moveStockProductDto.setProductCode(proCode);
        moveStockProductDto.setProductType(productType);

        // 分页条件判断
        if (StringUtils.isNotEmpty(productTypeName)) {
            newPage.setQuery("productTypeName", productTypeName);
        }
        if (StringUtils.isNotEmpty(productName)) {
            newPage.setQuery("productName", productName);
        }
        if (StringUtils.isNotEmpty(productTypeId)) {
            newPage.setQuery("productTypeId", productTypeId);
        }
        if (StringUtils.isNotEmpty(serieId)) {
            newPage.setQuery("proSerieId", serieId);
        }
        if (StringUtils.isNotEmpty(proCode)) {
            newPage.setQuery("productCode", proCode);
        }
        if (StringUtils.isNotEmpty(productType)) {
            newPage.setQuery("productType", productType);
        }
        if (StringUtils.isNotEmpty(outStockroomId)) {
            newPage.setQuery("outStockroomId", outStockroomId);
        }

        // 根据条件查看产品信息
        IMoveStockService moveStockService = (IMoveStockService) getBean("moveStockServiceImpl");
        List<MoveOutStockDto> productlist = moveStockService.selectProductList(
                moveStockProductDto, newPage);
        /* 产品放在SESSION中 */
        HttpSession session = request.getSession();
        session.setAttribute("moveStockProductlist", productlist);

        request.setAttribute("productlist", productlist);

        request.setAttribute("NewPage", newPage);

        request.setAttribute("moveStockProductDto", moveStockProductDto);

        return mapping.findForward(Constants.SUCCESS);

    }

    
     
    /**
	 * 样品借出评审显示
	 * 
	 * @author 蒋名星
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ActionForward getShowSampleOutAssess(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		log.debug("进入显示样品借出评审");
		
		String outId = (String) (request.getParameter("outId") == null ? request
				.getAttribute("outId")
				:request.getParameter("outId"));

        String type= request.getParameter("type");
		
		sampleOutService = (ISampleOutService) this.getApplicationContext()
		          .getBean("sampleOutServiceImpl");
		
		// 根据id获得样品借出评审信息
		SampleOutAssessDto assessDto = sampleOutService
				.getSampleOutById(outId.trim());
		if (assessDto == null) {
			request.setAttribute("msg", "样品借出评审显示失败!");
			return mapping.findForward("failure");
		}
		
		String userId = assessDto.getUserId();
		String temp = sampleOutService.selectUserRoleId(userId);
		
		request.setAttribute("temp",temp);
		
		request.setAttribute("assessDto", assessDto);     //第一个dto
		
		 String companyType = String.valueOf(assessDto.getCompanyType());
	        //获得发货地址
	        Map map = new HashMap();
	        map.put("companyType", companyType);
	        map.put("addressId", assessDto.getAddressId());
	        
	        CompanyCustomerSupplierAddressDto addressDto = sampleOutService.getAddressInfo(map);
	        
	    request.setAttribute("addressDto", addressDto);   //第二个dto
	
		List<SampleOutProductInfoDto> list =  sampleOutService
		                               .getProductInfo(assessDto);
		                         
		
		//产品放在SESSION中 
		HttpSession session = request.getSession();
		session.setAttribute("sampleOutProductlist", list);
		
		request.setAttribute("list", list);    
		
		/* 防止重复提交 */
		saveToken(request);
		
		if ("1".equals(type)){//查看
			return mapping.findForward("view"); 
		}else if ("2".equals(type)){//评审
			return mapping.findForward("success");
		}else{
			return mapping.findForward("failure");
		}
	}


	/**
	 * 样品借出评审
	 * 
	 * @author 蒋名星
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward getSampleOutAssess(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		log.debug("正在评审");
		/* 防止重复提交 */
		if (isTokenValid(request)) {
			resetToken(request);
		} else {
			request.setAttribute("msg", "请勿重复评审!");
			request.setAttribute("outId",request.getParameter("sampleOutAssess.id"));
			return mapping.findForward("failureToList");
		}

		// 获得用户id
		UserEntity user = (UserEntity) request.getSession().getAttribute(
				Constants.USERLOGIN);
		
        
		String assessResult = request.getParameter("assessResult");
        
		request.setAttribute("outId",request.getParameter("sampleOutAssess.id"));
		//防止重复评审
        String nowStatus=commonService.getStatusById("sample_out", request.getParameter("sampleOutAssess.id"));
        log.info("=====:"+nowStatus);
        if (user.getRoleId() == 11) {//采购主管
            if ( ! "4".equals(nowStatus)) {
                request.setAttribute("msg", "请勿重复评审!");
                return mapping.findForward("failureToList");
            } 
        }
        if (user.getRoleId() == 5) {//销售总监
            if ( !  "2".equals(nowStatus)) {
                request.setAttribute("msg", "请勿重复评审!");
                return mapping.findForward("failureToList");
            } 
        }
        
		DynaActionForm dform = (DynaActionForm) form;
		SampleOutAssessDto sampleOutAssessDto = (SampleOutAssessDto) dform
				.get("sampleOutAssess");

		sampleOutService = (ISampleOutService) this.getApplicationContext()
				.getBean("sampleOutServiceImpl");
		
		/* 产品编码 */
		String[] productIds = request.getParameterValues("productIds");
		/* 移库数 */
		String[] counts = request.getParameterValues("counts");

		/* 产品冻结数 */
		List<StockEntity> stockEntities = new ArrayList<StockEntity>();

		List<SampleOutProductInfoDto> dtos = (List<SampleOutProductInfoDto>) request
				.getSession().getAttribute("sampleOutProductlist");
		
		for (int i = 0; i < productIds.length; i++) {

			StockEntity stockEntity = new StockEntity();
			stockEntity.setSendLock(Integer.valueOf(counts[i]));// 本次要减少的冻结数
			stockEntity.setProductId(Integer.valueOf(productIds[i]));
			stockEntity.setStockroomId(sampleOutAssessDto.getOutStockroomId());
			stockEntity.setTimeStamp(getTimestampById(productIds[i], dtos));
			stockEntities.add(stockEntity);

		}

		int status = 0;
		boolean isSuccess = false;
		
		String userName = user.getName();

		String roleId = String.valueOf(user.getRoleId());
		sampleOutAssessDto.setRoleId(roleId);
		if ("11".equals(roleId)) {
			// 采购主管意见
			if (StringUtils.equals("pass", assessResult)) {
				status = 6;
			} else {
				status = 5;
			}

			sampleOutAssessDto.setStatus(status);
			sampleOutAssessDto.setBuyManName(userName);
			// 如果同意 修改状态
			if (status == 6) {
				// 把采购主管的待办事务减一
				WorkEntity workself = new WorkEntity();
				workself.setCount(-1);
				workself.setUserId(user.getId());
				workself.setWorkId(23);

				isSuccess = sampleOutService.updateSampleOutAssess
				                        (sampleOutAssessDto, workself);
				if (isSuccess) {
					log.info("User:{},{} 采购主管样品借出评审通过成功", user.getId(), user
							.getName());
				} else {
					log.info("User:{},{} 采购主管样品借出评审通过失败", user.getId(), user
							.getName());
				}

			} else {
				WorkEntity workself = new WorkEntity();
				workself.setCount(-1);
				workself.setUserId(user.getId());
				workself.setWorkId(23);
				// 不同意只修改状态
				isSuccess = sampleOutService.updateAssessNoWork(
						sampleOutAssessDto, workself,stockEntities);
				if (isSuccess) {
					request.getSession().setAttribute("sampleOutProductlist", null);
					log.info("User:{},{} 采购主管样品借出评审未通过成功", user.getId(), user
							.getName());
				} else {
					request.getSession().setAttribute("sampleOutProductlist", null);
					log.info("User:{},{} 采购主管样品借出评审未通过失败", user.getId(), user
							.getName());

				}
			}

		}else if ("5".equals(roleId)) {
			// 销售总监意见
			if (StringUtils.equals("pass", assessResult)) {
				status = 6;
			} else {
				status = 3;
			}

			sampleOutAssessDto.setStatus(status);
			sampleOutAssessDto.setSellMajName(userName);

			// 如果同意 修改状态
			if (status == 6) {
				// 把销售总监的待办事务减一
				WorkEntity workself = new WorkEntity();
				workself.setCount(-1);
				workself.setUserId(user.getId());
				workself.setWorkId(23);

				isSuccess = sampleOutService.updateSampleOutAssess(
						sampleOutAssessDto,workself);
				if (isSuccess) {
					log.info("User:{},{} 销售总监样品借出评审通过成功", user.getId(), user
							.getName());
				} else {
					log.info("User:{},{} 销售总监样品借出评审通过失败", user.getId(), user
							.getName());
				}

			} else {
				WorkEntity workself = new WorkEntity();
				workself.setCount(-1);
				workself.setUserId(user.getId());
				workself.setWorkId(23);
				// 不同意只修改状态
				isSuccess = sampleOutService.updateAssessNoWork(
						sampleOutAssessDto, workself,stockEntities);
				if (isSuccess) {
					request.getSession().setAttribute("sampleOutProductlist", null);
					log.info("User:{},{} 销售总监样品借出评审未通过成功", user.getId(), user
							.getName());
				} else {
					request.getSession().setAttribute("sampleOutProductlist", null);
					log.info("User:{},{} 销售总监样品借出评审未通过失败", user.getId(), user
							.getName());

				}
			}

		}
		if (!isSuccess) {
			request.setAttribute("msg", "评审失败");
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");
	} 
	
	/**
     * 新建样品借出申请显示
     * 
     * @author 蒋名星
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ActionForward createSampleOutInfo(ActionMapping mapping, ActionForm form,
			       HttpServletRequest request, HttpServletResponse response) 
	   throws Exception {
		
		log.debug("显示新建样品借出申请单");
		
		  sampleOutService = (ISampleOutService) getBean("sampleOutServiceImpl");

	        commonService = (ICommonService) getBean("commonServiceImpl");

	        /* 库房不包含虚拟库 */
	        List<StockroomEntity> stockroomEntities = sampleOutService.getStockroom();
	        // 获取用户登陆Id
	        UserEntity user = (UserEntity) request.getSession().getAttribute(
	                Constants.USERLOGIN);

	        request.setAttribute("stockroomEntities", stockroomEntities);

	        /* 产品分类 */

	        List<ProductTypeEntity> productTypeEntities = commonService.getAllProductTypes(
	                null, user.getId(), user.getRoleId() + "");
	        request.setAttribute("productTypeEntities", productTypeEntities);
	        
	        //获得公司名称
	        CompanyEntity companyEntity = sampleOutService.selectcompany();
	        request.setAttribute("companyEntity", companyEntity);
	        
	        /* 防止重复提交 */
	        saveToken(request);
	        return mapping.findForward("success");
	
	}
	
	
	
	/**
     * 新建样品借出申请
     * 
     * @author 蒋名星
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public ActionForward createSampleOut(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        log.debug("新建样品借出申请");
        /* 重复提交 */
        String subFlag = request.getParameter("subFlag");

         //防止重复提交 
        if (isTokenValid(request)) {
            resetToken(request);
        } else {
            if ("save".equals(subFlag)) {
                request.setAttribute("msg", "请勿重复保存 !");
            } else {
                request.setAttribute("msg", "请勿重复提交!");
            }
            return mapping.findForward("success");
        }

        DynaActionForm actionForm = (DynaActionForm) form;
        SampleOutAssessDto adddto = (SampleOutAssessDto) actionForm
                .get("createSampleOutDto");

        // 获取用户ID
        UserEntity user = (UserEntity) request.getSession().getAttribute(
                Constants.USERLOGIN);
        adddto.setUserId(user.getId());
        adddto.setUserName(user.getName());
         //样品借出单 ID生成 
        commonService = (ICommonService) getBean("commonServiceImpl");
        String outId = commonService.getSerialNumber("YP", "sample_out");
        adddto.setId(outId);

         //样品借出明细添加 
        List<SampleOutDetailEntity> sampleOutDetailEntities = new ArrayList<SampleOutDetailEntity>();

         //产品编码 
        String[] productIds = request.getParameterValues("productIds");
         //借出数 
        String[] counts = request.getParameterValues("counts");

         //产品冻结数 
        List<StockEntity> stockEntities = new ArrayList<StockEntity>();
        
        List<MoveOutStockDto> dtos = (List<MoveOutStockDto>) request
		.getSession().getAttribute("moveStockProductlist");

        /*重新获取库存单价*/
       
        String tmpprice= "0";
        IMoveStockService moveStockService = (IMoveStockService) getBean("moveStockServiceImpl");
        
        for (int i = 0; i < productIds.length; i++) {

        	SampleOutDetailEntity detailEntity = new SampleOutDetailEntity();
            detailEntity.setSampleOutId(outId);
            detailEntity.setCount(Integer.valueOf(counts[i]));
            detailEntity.setProductId(Integer.valueOf(productIds[i]));
            tmpprice= commonService.getProdAvePrice(productIds[i],"0");
            if (tmpprice==null){
            	tmpprice= "0";
            }
            detailEntity.setPrice(new BigDecimal(tmpprice));
            sampleOutDetailEntities.add(detailEntity);
           
            StockEntity stockEntity = new StockEntity();
            stockEntity.setSendLock(Integer.valueOf(counts[i]));// 本次要增加的冻结数
            stockEntity.setProductId(Integer.valueOf(productIds[i]));
            stockEntity.setStockroomId(adddto.getOutStockroomId());
            // 后台再次判断借出数是否正确 add 20110106 start
            MoveStockProductDto moveStockProductDto = new MoveStockProductDto();
            moveStockProductDto.setProTypeId(productIds[i]);
            moveStockProductDto.setOutStockroomId(adddto.getOutStockroomId().toString());
            MoveOutStockDto moveOutStockDto = moveStockService
                    .getStockByStockRoomAndProduct(moveStockProductDto);
            if (moveOutStockDto==null){
                request.setAttribute("msg", "获取产品库存信息失败");
                return mapping.findForward("failure");
            }
            if (stockEntity.getSendLock() > moveOutStockDto.getUseCount()) {
                request.setAttribute("msg", "借出数量不能大于借出可用数");
                return mapping.findForward("failure");
            }
            stockEntity.setTimeStamp(moveOutStockDto.getTimeStamp());
            // 后台再次判断借出数是否正确 add 20110106 end
            
            
            stockEntities.add(stockEntity);

        }
        
        //计算借出金额
        //log.info("getMoneyTotal:"+adddto.getMoneyTotal());
        adddto.setMoneyTotal(getMoney(sampleOutDetailEntities));
        //adddto.setMoneyTotal((adddto.getMoneyTotal().divide(new BigDecimal(1.17))).setScale(2,RoundingMode.HALF_UP));
         // 采购主管登录名
        WorkReceiverDto workReceiverDto = commonService.getUserIdByCondition(adddto
                .getProductTypeId(), null, null, Constants.ROLE_PROCUREMENT_OFFICER);
        String buyManId = workReceiverDto
                .getUserIdByRoleId(Constants.ROLE_PROCUREMENT_OFFICER);
        adddto.setBuyManId(buyManId);
        
       // 销售总监登录名
        workReceiverDto = commonService.getUserIdByCondition(adddto
                .getProductTypeId(),null, null,Constants.ROLE_SALES_DIRECTOR);
        String sellMajId = workReceiverDto
                .getUserIdByRoleId(Constants.ROLE_SALES_DIRECTOR);
       adddto.setSellMajId(sellMajId);

        // 库房管理员登录名
        workReceiverDto = commonService.getUserIdByCondition(adddto
                .getProductTypeId(),null,adddto
                .getOutStockroomId());
        String stkAdmId = workReceiverDto.getUserId();
        adddto.setStkAdmId(stkAdmId);

       

        sampleOutService = (ISampleOutService) getBean("sampleOutServiceImpl");
        if ("save".equals(subFlag)) {
             //保存 
            adddto.setStatus(1);
          
			if (sampleOutService.addSampleOutSave(adddto, sampleOutDetailEntities)) {
                log.info("User:{},{} 新建样品借出保存成功", user.getId(), user.getName());
                request.getSession().setAttribute("moveStockProductlist", null);
                return mapping.findForward("success");
            } else {
                log.info("User:{},{}新建样品借出保存失败", user.getId(), user.getName());
                request.setAttribute("msg", "新建样品借出失败");
                request.getSession().setAttribute("moveStockProductlist", null);
                return mapping.findForward("failure");
            }

        } else if ("submit".equals(subFlag)) {

            /** 采购专员 */
            WorkEntity work = new WorkEntity();
            if (user.getRoleId().equals(Constants.ROLE_PROCUREMENT_COMMISSIONER)) {
                // 提交 采购主管发待办事务 
                adddto.setStatus(4);
                work.setUserId(buyManId);
                work.setWorkId(23);// 样品借出单待评审
                work.setCount(1);

            }/** 销售经理 */
            else if (user.getRoleId().equals(Constants.ROLE_SALES_MANAGER)) {

                adddto.setStatus(2);
                work.setUserId(sellMajId);
                work.setWorkId(23);// 样品借出单待评审
                work.setCount(1);
            }

            if (sampleOutService.addSampleOutSubmit(adddto, work, sampleOutDetailEntities,
                    stockEntities)) {
                log.info("User:{},{} 新建样品借出提交成功", user.getId(), user.getName());
                /* 释放session中的产品信息 */ 
                request.getSession().setAttribute("moveStockProductlist", null);
                return mapping.findForward("success");
            } else {
                log.info("User:{},{}新建样品借出提交失败", user.getId(), user.getName());
                request.setAttribute("msg", "提交失败");
                request.getSession().setAttribute("moveStockProductlist", null);
                return mapping.findForward("failure");
            }
        } else {
            request.setAttribute("msg", "非法提交");
            return mapping.findForward("failure");
        }

        
    }
    
	/**
	 * 从session中获得时间戳
	 * 
	 * @param id
	 * @param list
	 * @return
	 */

	public Timestamp getTimestampById(String id, List<SampleOutProductInfoDto> list) {

		for (int i = 0; i < list.size(); i++) {
			SampleOutProductInfoDto dto = (SampleOutProductInfoDto)list.get(i);
			if (id.equals(dto.getId())) {
				return dto.getTimeStamp();
			}
		}
		return null;
	}
	
	public Timestamp getTimestampId(String id, List<MoveOutStockDto> list) {

		for (int i = 0; i < list.size(); i++) {
			MoveOutStockDto dto = (MoveOutStockDto)list.get(i);
			if (id.equals(dto.getId())) {
				return dto.getTimeStamp();
			}
		}
		return null;
	}
    

	/**
	 * 客户选择显示
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
 
	public ActionForward getSampleOutCoustomerSelect(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.debug("客户列表");
		/** 分页 */
		NewPage newPage = getNewPage(request);
		newPage.setUrl("getSampleOutCoustomerSelect.do");
		request.setAttribute("NewPage", newPage);
		sampleOutService = (ISampleOutService) getBean("sampleOutServiceImpl");
		UserEntity user = (UserEntity) request.getSession().getAttribute(
                Constants.USERLOGIN);
		
		/** 获得客户信息 */
		List<CustomerEntity> customerList = sampleOutService
				.selectCustomer(newPage,user);

		/** 返回客户列表与信息 */
		request.setAttribute("customerList", customerList);

		return mapping.findForward(Constants.SUCCESS);

	}

	/**
	 * 客户选择检索
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getSampleOutCoustomerSelectByName(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.debug("检索客户列表");
		String name = request.getParameter("name");
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setName(name);
		/** 分页 */
		NewPage newPage = getNewPage(request);
		newPage.setUrl("getSampleOutCoustomerSelectByName.do");
		newPage.setQuery("name", name);
		request.setAttribute("NewPage", newPage);
		sampleOutService = (ISampleOutService) getBean("sampleOutServiceImpl");

		/** 获得客户信息 */
		List<CustomerEntity> customerList = sampleOutService
				.selectCustomerByName(customerEntity, newPage);

		/** 返回客户列表与信息 */
		request.setAttribute("customerList", customerList);

		return mapping.findForward(Constants.SUCCESS);

	}

	/**
	 * 供应商选择
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */

	@SuppressWarnings("rawtypes")
    public ActionForward getSampleOutSupplierSelect(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.debug("选择供应商");
		IReceiveInvoiceService invoiceService = (IReceiveInvoiceService) getBean("receiveInvoiceServiceImpl");
		// 分页
		NewPage page = getNewPage(request);
		page.setUrl("getSampleOutSupplierSelect.do");
		String name = request.getParameter("supplierName");
		page.setQuery("supplierName", name);

		// 供应商信息
		List list = null;
		list = invoiceService.getSuppliers(name, page);

		request.setAttribute("NewPage", page);
		request.setAttribute("supplierName", name);
		request.setAttribute("supplierList", list);
		return mapping.findForward(Constants.SUCCESS);
	}   
	 
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		// 获得产品分类 列表
		UserEntity user = (UserEntity) request.getSession().getAttribute(
				Constants.USERLOGIN);
		commonService = (ICommonService) getBean("commonServiceImpl");
		List<ProductTypeEntity> productTypeEntities = null;
		productTypeEntities = commonService.getAllProductTypes(null, user
				.getId(),String.valueOf(user.getRoleId())
				); 
		request.setAttribute("productTypeEntitiesList", productTypeEntities);

		//获得库房列表
		List<StockroomEntity> stockroomEntities = null;
		sampleOutService = (ISampleOutService) getBean("sampleOutServiceImpl");

		stockroomEntities = sampleOutService.getStockroom();
		request.setAttribute("stockroomEntitiesList", stockroomEntities);
		
		
		return super.execute(mapping, form, request, response);
	}
	
  	/**
	 * 公司收货地址
	 * 
	 * @author ChenHuajiang
	 */
	@SuppressWarnings({ "rawtypes" })
	public ActionForward getSampleOutCompanyAddressList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.debug("获取公司收货地址 ");
		IBuyContractService buyContractService = (IBuyContractService) getBean("buyContractServiceImpl");
		// 公司收获地址
		List list = null;
		list = buyContractService.getCompanyAddressList();

		request.setAttribute("companyAddressList", list);
		return mapping.findForward(Constants.SUCCESS);
	}

	/**
	 * 客户收货地址
	 * 
	 * @author ChenHuajiang
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ActionForward getSampleOutCustomerAddressList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.debug("获取客户发货地址 ");

		String customerName = StringUtils.trim(request.getParameter("customerName"));
		String customerId = request.getParameter("customerId");
		request.setAttribute("customerId", customerId);
		// 分页
		NewPage page = getNewPage(request);
		page.setUrl("getSampleOutCustomerAddressList.do");
		page.setQuery("customerName", customerName);
		page.setQuery("customerId", customerId);
		HashMap map = new HashMap();
        map.put("customerName", customerName);
        map.put("customerId", customerId);
        sampleOutService = (ISampleOutService) getBean("sampleOutServiceImpl");
		// 库房收获地址
		List custAddressList = null;
		custAddressList = sampleOutService.getCustomerAddressList(
				map, page);

		request.setAttribute("NewPage", page);
		request.setAttribute("customerAddressList", custAddressList);
		request.setAttribute("customerName", StringUtils.trim(customerName));
		return mapping.findForward(Constants.SUCCESS);
	}

	/**
	 * 删除借出单
	 * 
	 * @author liuqg
	 */
	public ActionForward deleteSampleOut(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.debug("删除借出单 ");

		String id = request.getParameter("id");
		UserEntity user = (UserEntity) request.getSession().getAttribute(
				Constants.USERLOGIN);

		sampleOutService = (ISampleOutService) getBean("sampleOutServiceImpl");
		if (sampleOutService.deleteSampleOut(id)) {
			log.info("User:{},{} 删除借出单成功", user.getId(), user.getName());
		} else {
			log.info("User:{},{} 删除借出单失败", user.getId(), user.getName());
		}

		return mapping.findForward(Constants.SUCCESS);
	}
	
	/**
	 * 供应商发货地址
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getSampleOutAddressSupplierSelect(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.debug("进入供应商发货地址选择页面");
		String supplierId = request.getParameter("supplierId");
		IBuyBackGoodsService buyBackGoodsService = (IBuyBackGoodsService) getBean("buyBackGoodsServiceImpl");

		// 分页
		NewPage newPage = getNewPage(request);
		newPage.setUrl("getSampleOutSupplierAddressList.do");
		newPage.setQuery("supplierId", supplierId);

		List<SupplierAddressEntity> addressEntities = buyBackGoodsService
				.selecSupplierAddress(supplierId, newPage);

		request.setAttribute("addressEntities", addressEntities);
		request.setAttribute("NewPage", newPage);
		return mapping.findForward("success");
	}
    
}
