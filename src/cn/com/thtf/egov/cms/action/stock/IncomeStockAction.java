package cn.com.thtf.egov.cms.action.stock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

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
import cn.com.thtf.egov.cms.dto.BuyContractDto;
import cn.com.thtf.egov.cms.dto.InStockProductDto;
import cn.com.thtf.egov.cms.dto.IncomeStoreRoomDto;
import cn.com.thtf.egov.cms.dto.MoveOutStockDto;
import cn.com.thtf.egov.cms.dto.MoveStockAssessDto;
import cn.com.thtf.egov.cms.dto.NewReturnsProductDto;
import cn.com.thtf.egov.cms.dto.ProductTypeInfoDto;
import cn.com.thtf.egov.cms.dto.ReceiveGoodsDetailDto;
import cn.com.thtf.egov.cms.dto.SampleInAssessDto;
import cn.com.thtf.egov.cms.dto.SampleInProductInfoDto;
import cn.com.thtf.egov.cms.entity.InStockEntity;
import cn.com.thtf.egov.cms.entity.ReceiveGoodsDetailEntity;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.form.IncomeStockListForm;
import cn.com.thtf.egov.cms.iservice.IProductTypeService;
import cn.com.thtf.egov.cms.iservice.purchase.IBuyContractService;
import cn.com.thtf.egov.cms.iservice.purchase.IInStockService;
import cn.com.thtf.egov.cms.iservice.sell.IBackInvoiceService;
import cn.com.thtf.egov.cms.iservice.sell.ISaleReturnsService;
import cn.com.thtf.egov.cms.iservice.stock.IIncomeStockService;
import cn.com.thtf.egov.cms.iservice.stock.IMoveStockService;
import cn.com.thtf.egov.cms.iservice.stock.ISampleInService;

public class IncomeStockAction extends NewBaseAction {

    private static Logger log = LoggerFactory.getLogger(IncomeStockAction.class);
    private java.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private IIncomeStockService incomeStockServiceImp = (IIncomeStockService) getBean("incomeStockServiceImp");
    private ISaleReturnsService saleReturnsService;
    private IInStockService inStockService;
    private IMoveStockService moveStockService;
    private ISampleInService sampleInService;
    private IBuyContractService buyContractService;
    /**
     * 入库管理列表
     * 
     * @author hanrubing
     */
    @SuppressWarnings("unchecked")
	public ActionForward getIncomeStockList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},入库管理列表【开始】", user.getId());

        IncomeStockListForm incomeStockListForm = (IncomeStockListForm) form;
        String billTypes[] = null;
        String billType = "";
        if ("true".equals(request.getParameter("limitType"))) {
            billType = request.getParameter("incomeStockListDto.billType");
            if (!StringUtils.isBlank(billType)) {
                billTypes = billType.split("@");
            }
        } else {
            billTypes = request.getParameterValues("incomeStockListDto.billType");
            if (billTypes != null) {
                billType = billType.trim();
                for (int i = 0; i < billTypes.length; i++) {
                    billType += billTypes[i] + "@";
                }
            }
        }
        /* 产品分类 */
        IProductTypeService productTypeService = (IProductTypeService) getBean("productTypeServiceImp");
        List<ProductTypeInfoDto> productTypeList = productTypeService
                .fingProductTypeAll();
        /** 产品类型列表 */
        request.setAttribute("productTypeList", productTypeList);
        /** 库房名称列表 */
        request.setAttribute("storeRoomList", incomeStockServiceImp.getStoreRoomList());
        incomeStockListForm.getIncomeStockListDto().setUserId(user.getId());
        /** 分页 */
        NewPage newPage = getNewPage(request);
        newPage.setUrl("getIncomeStockList.do");
        request.setAttribute("NewPage", newPage);
        newPage.setQuery("incomeStockListDto.incomeStockId", incomeStockListForm
                .getIncomeStockListDto().getIncomeStockId());
        newPage.setQuery("incomeStockListDto.storeroomId", incomeStockListForm
                .getIncomeStockListDto().getStoreroomId());
        newPage.setQuery("incomeStockListDto.productTypeId", incomeStockListForm
                .getIncomeStockListDto().getProductTypeId());
        newPage.setQuery("incomeStockListDto.supplierName", incomeStockListForm
                .getIncomeStockListDto().getSupplierName());
        newPage.setQuery("incomeStockListDto.incomeDate", incomeStockListForm
                .getIncomeStockListDto().getIncomeDate());
        newPage.setQuery("incomeStockListDto.endIncomeDate", incomeStockListForm
                .getIncomeStockListDto().getEndIncomeDate());
        newPage.setQuery("incomeStockListDto.userName", incomeStockListForm
                .getIncomeStockListDto().getUserName());
        newPage.setQuery("incomeStockListDto.status", incomeStockListForm
                .getIncomeStockListDto().getStatus());
        newPage.setQuery("incomeStockListDto.billType", billType);
        newPage.setQuery("limitType", "true");
        newPage.setQuery("incomeStockListDto.type", incomeStockListForm
                .getIncomeStockListDto().getType());
        request.setAttribute("billType", billType);
        incomeStockListForm.getIncomeStockListDto().setBillTypes(billTypes);
        /** 入库管理列表 */
        request.setAttribute("incomeStockList", incomeStockServiceImp.getStockList(
                incomeStockListForm.getIncomeStockListDto(), newPage));
        request.setAttribute("roleId", user.getRoleId());
        log.info("User:{},入库管理列表【结束】", user.getId());
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 入库管理 查看
     * 
     * @author hanrubing
     */
    public ActionForward incomeStoreRoomOfView(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},入库管理 查看【开始】", user.getId());
        /** 入库单号 */
        String id = request.getParameter("id");
        /** 发货单号 */
        String sendGoodsId = request.getParameter("sendGoodId");
        /** 单据类型 */
        String billType = request.getParameter("billType");
        if (billType == null || !this.isNum(billType, null)) {
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&billTypeError=true");
            log.info("User:{},入库管理 查看【失败】", user.getId());
            return mapping.findForward(Constants.FAIL);
        }
        switch (Integer.parseInt(billType)) {
        case 1:
            request.setAttribute("redirect", request.getContextPath()
                    + "/getInstockOfInCome.do?id=" + id + "&comment=false");
            log.info("User:{},入库管理 查看【结束】", user.getId());
            return mapping.findForward(Constants.SUCCESS);
        case 2:
            request.setAttribute("redirect", request.getContextPath()
                    + "/viewSaleReturnsOfInCome.do?returnGoodsId=" + id
                    + "&comment=false&sendGoodsId=" + sendGoodsId);
            log.info("User:{},入库管理 查看【结束】", user.getId());
            return mapping.findForward(Constants.SUCCESS);
        case 3:
            request.setAttribute("redirect", request.getContextPath()
                    + "/viewMoveOfInCome.do?moveStockId=" + id + "&comment=false");
            log.info("User:{},入库管理 查看【结束】", user.getId());
            return mapping.findForward(Constants.SUCCESS);
        case 4:
        	request.setAttribute("redirect", request.getContextPath()
                    + "/viewSampleInAssessOfInCome.do?id=" + id + "&comment=false");
            log.info("User:{},入库管理 查看【结束】", user.getId());
            return mapping.findForward(Constants.SUCCESS);
        }
        log.info("User:{},入库管理 查看【失败】", user.getId());
        return mapping.findForward(Constants.FAIL);
    }

    /**
     * 入库管理 核对 显示
     * 
     * @author hanrubing
     */
    public ActionForward incomeStoreRoomOfComment(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},入库管理 核对【开始】", user.getId());
        /** 入库单号 */
        String id = request.getParameter("id");
        /** 发货单号 */
        String sendGoodsId = request.getParameter("sendGoodId");
        /** 单据类型 */
        String billType = request.getParameter("billType");
        if (billType == null || !this.isNum(billType, null)) {
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&billTypeError=true");
            log.info("User:{},入库管理 核对【失败】", user.getId());
            return mapping.findForward(Constants.FAIL);
        }
        switch (Integer.parseInt(billType)) {
        case 1:
            request.setAttribute("redirect", request.getContextPath()
                    + "/getInstockOfInCome.do?id=" + id + "&comment=true");
            log.info("User:{},入库管理 核对【结束】", user.getId());
            saveToken(request);
            return mapping.findForward(Constants.SUCCESS);
        case 2:
            request.setAttribute("redirect", request.getContextPath()
                    + "/viewSaleReturnsOfInCome.do?returnGoodsId=" + id
                    + "&comment=true&sendGoodsId=" + sendGoodsId);
            log.info("User:{},入库管理 核对【结束】", user.getId());
            saveToken(request);
            return mapping.findForward(Constants.SUCCESS);
        case 3:
            request.setAttribute("redirect", request.getContextPath()
                    + "/viewMoveOfInCome.do?moveStockId=" + id + "&comment=true");
            log.info("User:{},入库管理 核对【结束】", user.getId());
            saveToken(request);
            return mapping.findForward(Constants.SUCCESS);
        case 4:
        	request.setAttribute("redirect", request.getContextPath()
                    + "/viewSampleInAssessOfInCome.do?id=" + id + "&comment=true");
            log.info("User:{},入库管理 核对【结束】", user.getId());
            saveToken(request);
            return mapping.findForward(Constants.SUCCESS);
        }
        log.info("User:{},入库管理 核对【失败】", user.getId());
        return mapping.findForward(Constants.FAIL);
    }

    /**
     * 销售退货单查看 评审 显示
     * 
     * @author LuPing
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward viewSaleReturnsOfInCome(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},退货单查看【开始】", user.getId());

        String returnGoodsId = request.getParameter("returnGoodsId");
        String sendGoodsId = request.getParameter("sendGoodsId");
        /* 获取bean容器 */
        saleReturnsService = (ISaleReturnsService) getBean("saleReturnsServiceImpl");
        List<NewReturnsProductDto> list = saleReturnsService.modifySaleReturnsProduct(
                sendGoodsId, returnGoodsId);
        request.setAttribute("list", list);
        request.setAttribute("returnGoods", incomeStockServiceImp.getReturnGoodsById(returnGoodsId));
        request.setAttribute("comment", request.getParameter("comment"));
        log.info("User:{},退货单查看【结束】", user.getId());
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 入库单查看 评审 显示
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getInstockOfInCome(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},入库单查看【开始】", user.getId());

        inStockService = (IInStockService) getBean("inStockServiceImp");

        /* 入库单ID */
        String instockId = request.getParameter("id");

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

        request.setAttribute("comment", request.getParameter("comment"));
        log.info("User:{},入库单查看【结束】", user.getId());
        return mapping.findForward("auditView");
    }
    /**
	 * 样品归还评审显示 查看 
	 * 
	 * @author 蒋名星
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward viewSampleInAssessOfInCome(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserEntity user = getLoginUserInfo(request);
		log.debug("进入显示样品归还评审");
		log.info("User:{},入库单查看【开始】", user.getId());
		String sampleInId = request.getParameter("id");

		// 获得spring的bean容器
		IBackInvoiceService backInvoiceJudgeService = (IBackInvoiceService) this
				.getApplicationContext().getBean("backInvoiceServiceImpl");

		sampleInService = (ISampleInService) this.getApplicationContext()
				.getBean("sampleInServiceImpl");

		// 根据id获得样品归还评审信息
		SampleInAssessDto assessDto = sampleInService
				.getSampleInById(sampleInId.trim());
		if (assessDto == null) {
			request.setAttribute("msg", "样品归还评审显示失败!");
			return mapping.findForward("failure");
		}

		// 采购主管姓名

		String buyManName = backInvoiceJudgeService.selectUserName(assessDto
				.getBuyManId());

		assessDto.setBuyManName(buyManName);

		request.setAttribute("assessDto", assessDto);

		List<SampleInProductInfoDto> list = sampleInService
				.getProductInfo(assessDto);

		request.setAttribute("list", list);

		/* 获得区分信息 view 跳转到查看页面 ，showAssess 跳转到显示评审页面 */
		request.setAttribute("comment", request.getParameter("comment"));
        log.info("User:{},入库单查看【结束】", user.getId());
        return mapping.findForward("success");
	}
    /**
     * 入库管理 评审 执行
     * 
     * @author hanrubing
     */
    public ActionForward modifyInStockOfincomeStoreComment(ActionMapping mapping,
            ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},入库管理 评审【开始】", user.getId());

        /** 判断是否库房管理员 */
        if (user.getRoleId() != Constants.ROLE_TREASURY_MANAGER) {
            request.setAttribute("redirect", request.getHeader("Referer")
                    + "&roleError=true");
            log.info("User:{},入库管理 评审【失败】：库房管理员不存在", user.getId());
            return mapping.findForward(Constants.FAIL);
        }
        /** json数据 */
        String jsonData=request.getParameter("jsonData");
        /** 库房id */
        String stockroomId=request.getParameter("stockroomId");
        //System.out.println(stockroomId);
    	/** 入库单号 */
    	String id=request.getParameter("id");
    	/** 通过  不通过 */
    	String type=request.getParameter("type");
    	/** 库房管理员核对意见 */
    	String productSpec=request.getParameter("productSpec");
    	String productQuality=request.getParameter("productQuality");
    	String productSum=request.getParameter("productSum");
    	/** 说明 */
    	String text=request.getParameter("text");
    	if(StringUtils.isBlank(productSpec) || StringUtils.isBlank(productQuality) || StringUtils.isBlank(productSum) || !this.isNum(productSpec, null) || !this.isNum(productQuality, null) || !this.isNum(productSum, null)){
    		request.setAttribute("redirect", request.getHeader("Referer")
                    + "&stkAdmIdeaError=true");
            return mapping.findForward(Constants.FAIL);
        }
        String stkAdmIdea = productSpec + productQuality + productSum;
        IncomeStoreRoomDto incomeStoreRoomDto = new IncomeStoreRoomDto();
        String billType = request.getParameter("billType");

    	incomeStoreRoomDto.setId(id);
    	incomeStoreRoomDto.setStkAdmDate(sdf.format(new Date()));
    	incomeStoreRoomDto.setStkAdmIdea(stkAdmIdea);
    	incomeStoreRoomDto.setStkAdmId(user.getId());
    	incomeStoreRoomDto.setStkAdmName(user.getName());
    	incomeStoreRoomDto.setStkAdmText(text);
    	incomeStoreRoomDto.setBillType(billType);
    	incomeStoreRoomDto.setJsonData(jsonData);
    	incomeStoreRoomDto.setStockroomId(stockroomId);
    	/** 入库单专有数据 */
    	incomeStoreRoomDto.setTaxRate(request.getParameter("taxRate"));
    	incomeStoreRoomDto.setInvoiceType(request.getParameter("invoiceType"));
    	/** 销售退货单专有数据 */
    	incomeStoreRoomDto.setCustomerCreditId(request.getParameter("customerCreditId"));
    	incomeStoreRoomDto.setSendGoodsId(request.getParameter("sendGoodsId"));
    	if(billType==null || !this.isNum(billType,null)){
    		request.setAttribute("redirect", request.getHeader("Referer")
                    + "&billTypeError=true");
    		return mapping.findForward(Constants.FAIL); 
    	}
    	buyContractService = (IBuyContractService) getBean("buyContractServiceImpl");
    	HashMap<String, String> map=new HashMap<String, String>();
    	int isSuccess=0;
    	int status=0;
    	switch(Integer.parseInt(billType)){
    	case 1:
	    	if (isTokenValid(request, true)){
	            resetToken(request);          	
		    }else{
	        	return mapping.findForward("error");
		    }
	        map.put("table", "in_stock");
	        map.put("id", id);
	         status=buyContractService.getStrutsOfAll(map);
	        /**判断是否待评审*/
	        if(status!=4){
	        	return mapping.findForward("error");
	        }
	        incomeStoreRoomDto.setStatus("pass".equals(type)?6:5);incomeStoreRoomDto.setIsSuccess("pass".equals(type)?1:0);isSuccess=incomeStockServiceImp.modifyIncomeStoreroomOfIntock(incomeStoreRoomDto);
    		break;
    	case 2:
	    	if (isTokenValid(request, true)){
	            resetToken(request);          	
		    }else{
		    return mapping.findForward("error");
		    }
	        map.put("table", "sell_back_goods");
	        map.put("id", id);
	        status=buyContractService.getStrutsOfAll(map);
	        /**判断是否待评审*/
	        if(status!=6){
	        	return mapping.findForward("error");
	        }
	        incomeStoreRoomDto.setStatus("pass".equals(type)?8:7);incomeStoreRoomDto.setIsSuccess("pass".equals(type)?1:0);isSuccess=incomeStockServiceImp.modifyIncomeStoreroomOfIntock(incomeStoreRoomDto);
			break;
    	case 3:
    	if (isTokenValid(request, true)){
            resetToken(request);          	
	    }else{
	    return mapping.findForward("error");
	    }
        map.put("table", "move");
        map.put("id", id);
         status=buyContractService.getStrutsOfAll(map);
        /**判断是否待评审*/
        if(status!=7){
        	return mapping.findForward("error");
        }
        incomeStoreRoomDto.setStatus("pass".equals(type)?8:7);incomeStoreRoomDto.setIsSuccess("pass".equals(type)?1:0);isSuccess=incomeStockServiceImp.modifyIncomeStoreroomOfIntock(incomeStoreRoomDto);
		break;
    	case 4:
	    	if (isTokenValid(request, true)){
	            resetToken(request);          	
		    }else{
		    return mapping.findForward("error");
		    }
	    	map.put("table", "sample_in");
	        map.put("id", id);
	         status=buyContractService.getStrutsOfAll(map);
	        /**判断是否待评审*/
	        if(status!=4){
	        	return mapping.findForward("error");
	        }
	        incomeStoreRoomDto.setStatus("pass".equals(type)?6:5);incomeStoreRoomDto.setIsSuccess("pass".equals(type)?1:0);isSuccess=incomeStockServiceImp.modifyIncomeStoreroomOfIntock(incomeStoreRoomDto);
			break;
    	}
    	
    	if(isSuccess!=0){return mapping.findForward(Constants.SUCCESS);}else{
    		request.setAttribute("redirect", request.getHeader("Referer")
                + "&commentError=true");
    		return mapping.findForward(Constants.FAIL);}
    	
    } 

    /**
     * 移库评审显示 查看
     * 
     * @author 蒋名星
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward viewMoveOfInCome(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},移库评审显示【开始】", user.getId());

        String moveStockId = request.getParameter("moveStockId");

        moveStockService = (IMoveStockService) this.getApplicationContext().getBean(
                "moveStockServiceImpl");

        // 根据id获得移库评审信息
        MoveStockAssessDto assessDto = moveStockService.getMoveStockById(moveStockId
                .trim());
        if (assessDto == null) {
            log.info("User:{},移库评审显示【失败】", user.getId());
            request.setAttribute("msg", "移库评审显示失败!");
            return mapping.findForward("failure");
        }

        request.setAttribute("assessDto", assessDto);

        List<MoveOutStockDto> list = moveStockService.getMoveOutStockInfoView(assessDto);

        request.setAttribute("list", list);
        request.setAttribute("comment", request.getParameter("comment"));
        log.info("User:{},移库评审显示【结束】", user.getId());
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 判断是否位数字
     * 
     * @param 要判断的字符串val
     * @param dot
     *            "." 没有传空 如果有’.’只能出现一次 0.11 和.11都正确
     * @author hanrubing
     */
    private boolean isNum(String val, String dot) {
        return ".".equals(dot) ? Pattern.compile("[\\d]*[.]?[\\d]*").matcher(val)
                .matches() : Pattern.compile("[\\d]*").matcher(val).matches();
    }

}
