/**
 * ClassName  SampleInAction
 *
 * History
 * Create User: zhangzx
 * Create Date: 2010-7-12
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.action.stock;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import cn.com.thtf.egov.cms.dto.SampleInAssessDto;
import cn.com.thtf.egov.cms.dto.SampleInDetailDto;
import cn.com.thtf.egov.cms.dto.SampleInDto;
import cn.com.thtf.egov.cms.dto.SampleInListDto;
import cn.com.thtf.egov.cms.dto.SampleInProductInfoDto;
import cn.com.thtf.egov.cms.dto.SampleInSearchDto;
import cn.com.thtf.egov.cms.dto.SampleOutAssessDto;
import cn.com.thtf.egov.cms.dto.WorkReceiverDto;
import cn.com.thtf.egov.cms.entity.ProductTypeEntity;
import cn.com.thtf.egov.cms.entity.SampleOutDetailEntity;
import cn.com.thtf.egov.cms.entity.StockroomAddressEntity;
import cn.com.thtf.egov.cms.entity.StockroomEntity;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.entity.WorkEntity;
import cn.com.thtf.egov.cms.iservice.common.ICommonService;
import cn.com.thtf.egov.cms.iservice.stock.IMoveStockService;
import cn.com.thtf.egov.cms.iservice.stock.ISampleInService;
import cn.com.thtf.egov.cms.iservice.stock.ISampleOutService;

/**
 * 
 * @author zhangzx
 */

public class SampleInAction extends NewBaseAction {
    private static Logger log = LoggerFactory.getLogger(SampleInAction.class);
    private ISampleInService sampleInService = null;
    private ICommonService commonService = null;

 	/**
	 * 样品归还评审显示
	 * 
	 * @author 蒋名星
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward getShowSampleInAssess(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("进入显示样品归还评审");
        
		String sampleInId = (String) (request.getParameter("id") == null? request
				.getAttribute("id"):request.getParameter("id"));

		sampleInService = (ISampleInService) this.getApplicationContext()
				.getBean("sampleInServiceImpl");

		// 根据id获得样品归还评审信息
		SampleInAssessDto assessDto = sampleInService
				.getSampleInById(sampleInId.trim());
		if (assessDto == null) {
			request.setAttribute("msg", "样品归还评审显示失败!");
			return mapping.findForward("failure");
		}

		request.setAttribute("assessDto", assessDto);

		List<SampleInProductInfoDto> list = sampleInService
				.getProductInfo(assessDto);

		request.setAttribute("list", list);

		/* 防止重复提交 */
		saveToken(request);

		/* 获得区分信息 view 跳转到查看页面 ，showAssess 跳转到显示评审页面 */
		String type = request.getParameter("type");
		if ("showAssess".equals(type)) {
			return mapping.findForward("showAssess");
		} else if ("view".equals(type)) {
			return mapping.findForward("view");
		} else {
			return mapping.findForward("failure");
		}
	}

	/**
	 * 样品归还评审
	 * 
	 * @author 蒋名星
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward getSampleInAssess(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.debug("正在评审");
		/* 防止重复提交 */
		if (isTokenValid(request)) {
			resetToken(request);
		} else {
			request.setAttribute("msg", "请勿重复评审!");
			request.setAttribute("id", request.getParameter("sampleInAssess.id"));
			return mapping.findForward("failureToList");
		}

		// 获得用户id
		UserEntity user = (UserEntity) request.getSession().getAttribute(
				Constants.USERLOGIN);
         
		String assessResult = request.getParameter("assessResult");
		
		request.setAttribute("id", request.getParameter("sampleInAssess.id"));
		//防止重复评审
		 commonService = (ICommonService) getBean("commonServiceImpl");
        String nowStatus=commonService.getStatusById("sample_in", request.getParameter("sampleInAssess.id"));
        
        if (user.getRoleId() == 11) {//采购主管
            if ( !  "2".equals(nowStatus)) {
                request.setAttribute("msg", "请勿重复评审!");
                return mapping.findForward("failureToList");
            } 
        } 
		DynaActionForm dform = (DynaActionForm) form;
		SampleInAssessDto sampleInAssessDto = (SampleInAssessDto) dform
				.get("sampleInAssess");

		sampleInService = (ISampleInService) this.getApplicationContext()
				.getBean("sampleInServiceImpl");

		int status = 0;
		boolean isSuccess = false;
        
		String userName = user.getName();
		String roleId = String.valueOf(user.getRoleId());
		sampleInAssessDto.setRoleId(roleId);

		if ("11".equals(roleId)) {
			// 采购主管意见
			if (StringUtils.equals("pass", assessResult)) {
				status = 4;
			} else {
				status = 3;
			}

			sampleInAssessDto.setStatus(status);
			sampleInAssessDto.setBuyManName(userName);

			// 如果同意 修改状态
			if (status == 4) {
				// 把采购主管的待办事务减一
				WorkEntity workself = new WorkEntity();
				workself.setCount(-1);
				workself.setUserId(user.getId());
				workself.setWorkId(24);

				isSuccess = sampleInService.updateSampleInAssessDto(
						sampleInAssessDto, workself);
				if (isSuccess) {
					log.info("User:{},{} 采购主管样品归还评审通过成功", user.getId(), user
							.getName());
				} else {
					log.info("User:{},{} 采购主管样品归还评审通过失败", user.getId(), user
							.getName());
				}

			} else {
				WorkEntity workself = new WorkEntity();
				workself.setCount(-1);
				workself.setUserId(user.getId());
				workself.setWorkId(24);
				// 不同意只修改状态
				isSuccess = sampleInService.updateAssessNoWork(
						sampleInAssessDto, workself);
				if (isSuccess) {
					log.info("User:{},{} 采购主管样品归还评审未通过成功", user.getId(), user
							.getName());
				} else {
					log.info("User:{},{} 采购主管样品归还评审未通过失败", user.getId(), user
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
     * 新建样品归还单显示
     * 
     * @author 蒋名星
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ActionForward createSampleInInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		   log.debug("显示新建样品归还单");
		
		    sampleInService = (ISampleInService) getBean("sampleInServiceImpl");
	        
	        String outId = request.getParameter("sampleOutId");
	        
	        ISampleOutService sampleOutService = (ISampleOutService) this.getApplicationContext()
	          .getBean("sampleOutServiceImpl");
	
			// 根据id获得样品借出评审信息
			SampleOutAssessDto assessDto = sampleOutService
					.getSampleOutById(outId);
			if (assessDto == null) {
				request.setAttribute("msg", "样品借出评审显示失败!");
				return mapping.findForward("failure");
			}
			
			request.setAttribute("assessDto",assessDto);

			List<SampleInProductInfoDto> list = sampleInService
					.getNewProductInfo(assessDto);
			
			request.setAttribute("list", list);

	        /* 库房不包含虚拟库 */
	        List<StockroomEntity> stockroomEntities = sampleInService.getStockroom();

	        request.setAttribute("stockroomEntities", stockroomEntities);

	      
	        /* 防止重复提交 */
	        saveToken(request);
	        return mapping.findForward("success");
		
	}
	
	/**
     * 新建样品归还单
     * 
     * @author 蒋名星
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ActionForward createSampleIn(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		log.debug("新建样品归还单");
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
        SampleInAssessDto adddto = (SampleInAssessDto) actionForm
                .get("createSampleInDto");

        // 获取用户ID
        UserEntity user = (UserEntity) request.getSession().getAttribute(
                Constants.USERLOGIN);
        adddto.setUserId(user.getId());
        adddto.setUserName(user.getName());
         //样品归还单 ID生成 
        commonService = (ICommonService) getBean("commonServiceImpl");
        String sampleInId = commonService.getSerialNumber("TYP", "sample_in");
        adddto.setId(sampleInId);
        sampleInService = (ISampleInService) getBean("sampleInServiceImpl");
         //样品归还明细添加 
        List<SampleInDetailDto> sampleInDetailEntities = new ArrayList<SampleInDetailDto>();

        //产品编码 
        String[] productIds = request.getParameterValues("productIds");
        //归还数 
        String[] counts = request.getParameterValues("counts");
       BigDecimal tmptotalMoney = new BigDecimal("0.00");
       BigDecimal tmpprice = new  BigDecimal("0");
        for (int i = 0; i < productIds.length; i++) {

        	SampleInDetailDto detailEntity = new SampleInDetailDto();
                              
            if (counts[i] != "0" && counts[i] != "") {
                detailEntity.setSampleInId(sampleInId);
                detailEntity.setCount(counts[i]);
                detailEntity.setProductId(productIds[i]);
                sampleInDetailEntities.add(detailEntity);
                // 后台计算归还金额合计
                SampleOutDetailEntity outDetailEntity = new SampleOutDetailEntity();
                outDetailEntity.setSampleOutId(adddto.getSampleOutId());
                outDetailEntity.setProductId(Integer.valueOf(productIds[i]));
                tmpprice = sampleInService.getSampleOutPrice(outDetailEntity);
                tmptotalMoney = tmptotalMoney.add(tmpprice.multiply(new BigDecimal(
                        detailEntity.getCount())));
            }
          
        }
        adddto.setMoneyTotal(tmptotalMoney);
       

        // 采购主管登录名
        WorkReceiverDto workReceiverDto = commonService.getUserIdByCondition(adddto
                .getProductTypeId(), null, null, Constants.ROLE_PROCUREMENT_OFFICER);
        String buyManId = workReceiverDto
                .getUserIdByRoleId(Constants.ROLE_PROCUREMENT_OFFICER);
        adddto.setBuyManId(buyManId);
        

        // 库房管理员登录名
        workReceiverDto = commonService.getUserIdByCondition(adddto
                .getProductTypeId(),null, adddto
                .getInStockroomId());
        String stkAdmId = workReceiverDto.getUserId();
        adddto.setStkAdmId(stkAdmId);

        if ("save".equals(subFlag)) {
             //保存 
            adddto.setStatus(1);
             
			if (sampleInService.addSampleInSave(adddto, sampleInDetailEntities)) {
                log.info("User:{},{} 新建样品归还保存成功", user.getId(), user.getName());
                return mapping.findForward("success");
            } else {
                log.info("User:{},{}新建样品归还保存失败", user.getId(), user.getName());
                request.setAttribute("msg", "新建样品归还失败");
                return mapping.findForward("failure");
            }

        } else if ("submit".equals(subFlag)) {

            /** 采购专员、销售经理 */
            WorkEntity work = new WorkEntity();
            if (user.getRoleId().equals(Constants.ROLE_PROCUREMENT_COMMISSIONER)
            		|| user.getRoleId().equals(Constants.ROLE_SALES_MANAGER)) {
                // 提交 采购专员、销售经理 发待办事务 
                adddto.setStatus(2);
                work.setUserId(buyManId);
                work.setWorkId(24);// 样品归还单待评审
                work.setCount(1);

            }
            if (sampleInService.addSampleInSubmit(adddto, work, 
            		sampleInDetailEntities)) {
                log.info("User:{},{} 新建样品归还提交成功", user.getId(), user.getName());        
                return mapping.findForward("success");
            } else {
                log.info("User:{},{}新建样品归还提交失败", user.getId(), user.getName());
                request.setAttribute("msg", "提交失败");
                return mapping.findForward("failure");
            }
        } else {
            request.setAttribute("msg", "非法提交");
            return mapping.findForward("failure");
        }
	}

    /**
     * 样品归还管理初始化
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @author zhangzx
     */
    public ActionForward getSampleInList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("进入样品归还管理初始列表");

        sampleInService = (ISampleInService) getBean("sampleInServiceImpl");

        // 获取用户ID
        UserEntity user = (UserEntity) request.getSession().getAttribute(
                Constants.USERLOGIN);
        // 分页
        NewPage newPage = getNewPage(request);
        newPage.setUrl("getSampleInList.do");

        List<SampleInListDto> sampleInListDtos = null;
        List<ProductTypeEntity> productTypeEntities = null;
        List<StockroomEntity> stockroomEntities = null;

        try {
            sampleInListDtos = sampleInService.getSampleInList(newPage, user);
            productTypeEntities = sampleInService.getProductType();
            stockroomEntities = sampleInService.getStockroom();
        } catch (Exception e) {
            log.error("获取样品归还单一览错误", e);
        }
        // 归还列表
        request.setAttribute("sampleInList", sampleInListDtos);
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
    public ActionForward searchSampleIn(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        log.debug("检索样品归还单一览");

        // 检索条件放到request中,保存画面检索条件
        DynaActionForm dform = (DynaActionForm) form;

        SampleInSearchDto sampleInSearchDto = (SampleInSearchDto) dform
                .get("searchSampleInDto");

        // 获取用户ID
        UserEntity user = (UserEntity) request.getSession().getAttribute(
                Constants.USERLOGIN);

        // 将当前登录用户的ID和权限放在检索用DTO中
        sampleInSearchDto.setLoginId(user.getId());
        sampleInSearchDto.setRoleId(String.valueOf(user.getRoleId()));

        request.setAttribute("sampleInSearchDto", sampleInSearchDto);

        // 分页
        NewPage newPage = getNewPage(request);
        newPage.setUrl("searchSampleIn.do");
        // 页面检索条件保存
        setNewPagePara(newPage, sampleInSearchDto);

        sampleInService = (ISampleInService) getBean("sampleInServiceImpl");

        List<SampleInListDto> sampleInListDtos = null;
        List<ProductTypeEntity> productTypeEntities = null;
        List<StockroomEntity> stockroomEntities = null;

        try {
            sampleInListDtos = sampleInService.getSampleInListByCondition(newPage,
                    sampleInSearchDto);
            productTypeEntities = sampleInService.getProductType();
            stockroomEntities = sampleInService.getStockroom();
        } catch (Exception e) {
            log.error("获取样品归还单一览错误", e);
        }
        // 样品借出列表
        request.setAttribute("sampleInList", sampleInListDtos);
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
     * @param newPage
     * @param sampleInSearchDto
     * @author zhangzx
     */
    private void setNewPagePara(NewPage newPage, SampleInSearchDto sampleInSearchDto) {
        // 样品归还单号
        if (StringUtils.isNotEmpty(sampleInSearchDto.getSampleInId())) {
            newPage.setQuery("searchSampleInDto.sampleInId", sampleInSearchDto
                    .getSampleInId());
        }
        // 样品借出单号
        if (StringUtils.isNotEmpty(sampleInSearchDto.getSampleInId())) {
            newPage.setQuery("searchSampleInDto.sampleOutId", sampleInSearchDto
                    .getSampleOutId());
        }
        // 库房名称
        if (StringUtils.isNotEmpty(sampleInSearchDto.getInStockroomId())) {
            newPage.setQuery("searchSampleInDto.inStockroomId", sampleInSearchDto
                    .getInStockroomId());
        }
        // 产品分类编号
        if (StringUtils.isNotEmpty(sampleInSearchDto.getProductTypeId())) {
            newPage.setQuery("searchSampleInDto.productTypeId", sampleInSearchDto
                    .getProductTypeId());
        }
        // 借出单位名称
        if (StringUtils.isNotEmpty(sampleInSearchDto.getCompanyName())) {
            newPage.setQuery("searchSampleInDto.companyName", sampleInSearchDto
                    .getCompanyName());
        }
        // 申请起始日期
        if (StringUtils.isNotEmpty(sampleInSearchDto.getDateStart())) {
            newPage.setQuery("searchSampleInDto.dateStart", sampleInSearchDto
                    .getDateStart());
        }
        // 申请终止日期
        if (StringUtils.isNotEmpty(sampleInSearchDto.getDateEnd())) {
            newPage.setQuery("searchSampleInDto.dateEnd", sampleInSearchDto.getDateEnd());
        }
        // 预计归还起始日期
        if (StringUtils.isNotEmpty(sampleInSearchDto.getInDateStart())) {
            newPage.setQuery("searchSampleInDto.inDateStart", sampleInSearchDto
                    .getInDateStart());
        }
        // 预计归还终止日期
        if (StringUtils.isNotEmpty(sampleInSearchDto.getInDateEnd())) {
            newPage.setQuery("searchSampleInDto.inDateEnd", sampleInSearchDto
                    .getInDateEnd());
        }

        // 入库起始日期
        if (StringUtils.isNotEmpty(sampleInSearchDto.getStkAdmDateStart())) {
            newPage.setQuery("searchSampleInDto.stkAdmDateStart", sampleInSearchDto
                    .getStkAdmDateStart());
        }
        // 入库终止日期
        if (StringUtils.isNotEmpty(sampleInSearchDto.getStkAdmDateEnd())) {
            newPage.setQuery("searchSampleInDto.stkAdmDateStart", sampleInSearchDto
                    .getStkAdmDateEnd());
        }
        // 归还单状态
        if (StringUtils.isNotEmpty(sampleInSearchDto.getStatus())) {
            newPage.setQuery("searchSampleInDto.status", sampleInSearchDto.getStatus());
        }

        // 申请人名称
        if (StringUtils.isNotEmpty(sampleInSearchDto.getUserName())) {
            newPage.setQuery("searchSampleInDto.userName", sampleInSearchDto
                    .getUserName());
        }
    }

    /**
     * 样品归还修改显示信息
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @author zhangzx
     */
    public ActionForward getSampleInModifyInfo(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        log.debug("进入显示样品归还评审");

        String sampleInId = (String) (request.getParameter("id")==null?request.getAttribute("id"):request.getParameter("id"));

        sampleInService = (ISampleInService) this.getApplicationContext().getBean(
                "sampleInServiceImpl");

        // 根据id获得样品归还评审信息
        SampleInAssessDto assessDto = sampleInService.getSampleInById(sampleInId.trim());
        if (assessDto == null) {
            request.setAttribute("msg", "样品归还修改显示失败!");
            return mapping.findForward("failure");
        }

        request.setAttribute("assessDto", assessDto);

        List<ProductTypeEntity> productTypeEntities = null;
        List<StockroomEntity> stockroomEntities = null;
        List<SampleInProductInfoDto> list = null;

        // 获取用户登陆Id
        UserEntity user = (UserEntity) request.getSession().getAttribute(
                Constants.USERLOGIN);
        /* 产品分类 */
        commonService = (ICommonService) getBean("commonServiceImpl");

        try {
            productTypeEntities = commonService.getAllProductTypes(null, user.getId(),
                    user.getRoleId() + "");
            stockroomEntities = sampleInService.getStockroom();
            // 产品列表
            list = sampleInService.getProductInfoModify(assessDto);
        } catch (Exception e) {
            log.error("获取样品借出单修改信息错误", e);
        }
        request.setAttribute("list", list);

        // 产品分类
        request.setAttribute("productTypeEntitiesList", productTypeEntities);
        // 库房
        request.setAttribute("stockroomEntitiesList", stockroomEntities);

        /* 防止重复提交 */
        saveToken(request);

        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 发货地址选择(供应商)
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getAddressByStockroomId(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("进入发货地址选择页面");
        String stockroomId = request.getParameter("stockroomId");
        IMoveStockService moveStockService = (IMoveStockService) getBean("moveStockServiceImpl");

        // 分页
        NewPage newPage = getNewPage(request);
        newPage.setUrl("getAddressByStockroomId.do");
        newPage.setQuery("stockroomId", stockroomId);

        List<StockroomAddressEntity> stockroomAddressEntities = moveStockService
                .selectSendGoodsAddress(newPage, stockroomId);

        request.setAttribute("stockroomAddressEntities", stockroomAddressEntities);
        request.setAttribute("NewPage", newPage);
        return mapping.findForward(Constants.SUCCESS);

    }

    /**
     * 修改样品归还单保存、提交
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @author zhangzx
     */
    public ActionForward modifySampleIn(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("样品归还单修改");

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
        sampleInService = (ISampleInService) getBean("sampleInServiceImpl");
        // 获取用户登陆Id
        UserEntity user = (UserEntity) request.getSession().getAttribute(
                Constants.USERLOGIN);

        DynaActionForm actionForm = (DynaActionForm) form;

        SampleInDto sampleInDto = (SampleInDto) actionForm.get("modifySampleInDto");
        
        //采购主管登录名
        WorkReceiverDto workReceiverDto = commonService.getUserIdByCondition(sampleInDto
                .getProductTypeId(), null, null, Constants.ROLE_PROCUREMENT_OFFICER);
        String buyManId = workReceiverDto
                .getUserIdByRoleId(Constants.ROLE_PROCUREMENT_OFFICER);
        sampleInDto.setBuyManId(buyManId);
        

        // 库房管理员登录名
        workReceiverDto = commonService.getUserIdByCondition(sampleInDto
                .getProductTypeId(),null, sampleInDto
                .getInStockroomId());
        String stkAdmId = workReceiverDto.getUserId();
        sampleInDto.setStkAdmId(stkAdmId);

        String sampleInId = sampleInDto.getId();

        // 样品归还明细添加
        List<SampleInDetailDto> list = new ArrayList<SampleInDetailDto>();
        // 产品编号
        String[] productIds = request.getParameterValues("productIds");
        // 借出数
        String[] reCounts = request.getParameterValues("reCounts");
        BigDecimal tmptotalMoney = new BigDecimal("0.00");
        BigDecimal tmpprice = new  BigDecimal("0");
        for (int i = 0; i < productIds.length; i++) {
            if (StringUtils.isNotEmpty(reCounts[i]) && !"0".equals(reCounts[i])) {
                SampleInDetailDto sampleInDetailDto = new SampleInDetailDto();
                sampleInDetailDto.setSampleInId(sampleInId);
                sampleInDetailDto.setProductId(productIds[i]);
                sampleInDetailDto.setCount(reCounts[i]);
                list.add(sampleInDetailDto);
                // 后台计算归还金额合计
                SampleOutDetailEntity outDetailEntity = new SampleOutDetailEntity();
                outDetailEntity.setSampleOutId(sampleInDto.getSampleOutId());
                outDetailEntity.setProductId(Integer.valueOf(productIds[i]));
                tmpprice = sampleInService.getSampleOutPrice(outDetailEntity);
                tmptotalMoney = tmptotalMoney.add(tmpprice.multiply(new BigDecimal(
                        sampleInDetailDto.getCount())));
            }
        }
        sampleInDto.setMoney(tmptotalMoney.setScale(2, RoundingMode.HALF_UP).toString());
        if ("save".equals(method)) {
            // 修改返厂保存
            sampleInDto.setStatus("1");

            if (sampleInService.modifySampleInSave(sampleInDto, list)) {
                log.info("User:{},{} 修改样品归还保存成功", user.getId(), user.getName());
                return mapping.findForward("success");
            } else {
                log.info("User:{},{} 修改样品归还保存失败", user.getId(), user.getName());
                request.setAttribute("msg", "修改失败");
                request.setAttribute("id", sampleInId);
                return mapping.findForward("failure");
            }

        } else if ("submit".equals(method)) {

            // 状态
            sampleInDto.setStatus("2");
            /* 提交 给采购主管发待办事务 */
            WorkEntity work = new WorkEntity();
            work.setUserId(sampleInDto.getBuyManId());
            work.setWorkId(24);// 借出单待评审
            work.setCount(1);

            if (sampleInService.modifySampleInSubmit(sampleInDto, list, work)) {
                log.info("User:{},{} 修改样品归还提交成功", user.getId(), user.getName());
                return mapping.findForward("success");
            } else {
                log.info("User:{},{} 修改样品归还提交失败", user.getId(), user.getName());
                request.setAttribute("msg", "提交失败");
                request.setAttribute("id", sampleInId);
                return mapping.findForward("failure");
            }

        } else {
            request.setAttribute("msg", "非法提交");
            request.setAttribute("id", sampleInId);
            return mapping.findForward("failure");
        }

    }
    
    /**
     * 删除归还单
     * 
     * @author zhangzx
     */
    public ActionForward deleteSampleIn(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        log.debug("删除借出单 ");

        String sampleInId = request.getParameter("id");
        UserEntity user = (UserEntity) request.getSession().getAttribute(
                Constants.USERLOGIN);

        sampleInService = (ISampleInService) getBean("sampleInServiceImpl");
        if (sampleInService.deleteSampleIn(sampleInId)) {
            log.info("User:{},{} 删除归还单成功", user.getId(), user.getName());
        } else {
            request.setAttribute("msg", "删除失败");
            log.info("User:{},{} 删除归还单失败", user.getId(), user.getName());
            return mapping.findForward("failure");
        }

        return mapping.findForward(Constants.SUCCESS);
    }

}
