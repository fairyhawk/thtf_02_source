/**
 * ClassName  BuyBackAction
 *
 * History
 * Create User: liuqg
 * Create Date: 2010-6-9
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.action.purchase;

import java.math.BigDecimal;
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
import cn.com.thtf.egov.cms.dto.AddBuyBackListDto;
import cn.com.thtf.egov.cms.dto.BuyBackListDto;
import cn.com.thtf.egov.cms.dto.BuyBackSearchDto;
import cn.com.thtf.egov.cms.dto.PaymentInfoForBuyBackDto;
import cn.com.thtf.egov.cms.entity.ProductTypeEntity;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.iservice.common.ICommonService;
import cn.com.thtf.egov.cms.iservice.purchase.IBuyBackService;

/**
 * 采购退款
 * 
 * @author liuqg
 */

public class BuyBackAction extends NewBaseAction {
	private static Logger log = LoggerFactory.getLogger(BuyBackAction.class);
	private IBuyBackService buyBackService = null;
	private ICommonService commonService = null;

	/**
	 * @return the log
	 */
	public static Logger getLog() {
		return log;
	}

	/**
	 * @param log
	 *            the log to set
	 */
	public static void setLog(Logger log) {
		BuyBackAction.log = log;
	}

	/**
	 * 采购退款查看
	 * 
	 * @author 蒋名星
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getBuyBackView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		log.debug("进入采购退款查看");

		// 获取bean容器
		buyBackService = (IBuyBackService) this.getApplicationContext()
				.getBean("buyBackServiceImpl");

		String buyBackId = request.getParameter("buyBackId");

		BuyBackListDto buyBackViewDto = null;

		try {
			buyBackViewDto = buyBackService.selectBuyBackById(buyBackId);
		} catch (Exception e) {
			log.debug("获取采购退款查看错误", e);
			return mapping.findForward("failure");
		}

		request.setAttribute("buyBackViewDto", buyBackViewDto); // 第一个dto

		String paymentId = buyBackViewDto.getPaymentId();

		PaymentInfoForBuyBackDto paymentInfoDto = null;

		try {
			paymentInfoDto = buyBackService.selectPaymentById(paymentId);
		} catch (Exception e) {
			log.debug("获取付款信息错误", e);
			return mapping.findForward("failure");
		}

		request.setAttribute("paymentInfoDto", paymentInfoDto); // 第二个dto

		return mapping.findForward("success");
	}

	/**
	 * 采购退款删除
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author 蒋名星
	 */
	public ActionForward removeBuyBack(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// 获得用户id
		UserEntity user = (UserEntity) request.getSession().getAttribute(
				Constants.USERLOGIN);

		// 采购退款号
		String buyBackId = request.getParameter("buyBackId");

		// 获得bean容器
		buyBackService = (IBuyBackService) this.getApplicationContext()
				.getBean("buyBackServiceImpl");

		// 删除采购退款
		boolean isSuccess = buyBackService.removeBuyBack(buyBackId,user);

		if (isSuccess) {
			log.info("User:{},{} 删除采购退款成功", user.getId(), user.getName());
			return mapping.findForward("success");
		} else {
			log.info("User:{},{} 删除采购退款失败", user.getId(), user.getName());
			request.setAttribute("msg", "删除采购退款失败");
			return mapping.findForward("failure");
		}

	}

	/**
	 * 采购退款添加
	 * 
	 * @author liuqg
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward addBuyBack(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		log.debug("采购退款添加");

		/* 防止重复提交 */
		if (isTokenValid(request)) {
			resetToken(request);
		} else {
			request.setAttribute("msg", "请勿重复录入!");
			return mapping.findForward("success");
		}
		/* 画面值取得 */
		DynaActionForm dform = (DynaActionForm) form;
		AddBuyBackListDto backListDto = (AddBuyBackListDto) dform
				.get("addBuyBackForm");

		buyBackService = (IBuyBackService) getBean("buyBackServiceImpl");
		commonService = (ICommonService) getBean("commonServiceImpl");

		/* 当期用户 */

		UserEntity user = (UserEntity) request.getSession().getAttribute(
				Constants.USERLOGIN);
		/* 退款单号生成 */
		backListDto.setId(commonService.getSerialNumber("TFK", "buy_back"));
		backListDto.setUserId(user.getId());// 登录名 user_id
		backListDto.setUserName(user.getName());// 人员名称 user_name

		/* 检查是否可以继续退款 */
		PaymentInfoForBuyBackDto tmppaymentInfo = buyBackService
				.selectPaymentById(backListDto.getPaymentId());

		if (tmppaymentInfo.getCanBackMoney().floatValue() <= 0.0) {
			request.setAttribute("msg", "该付款单已不可再退款，请确认！");
			return mapping.findForward("success");
		}

		/* 插入数据 */
		if (buyBackService.addBuyBack(backListDto)) {
			log.info("User:{},{} 录入采购退款成功", user.getId(), user.getName());
			return mapping.findForward("success");
		} else {
			request.setAttribute("msg", "录入采购退款失败");
			request.setAttribute("backAddDto", backListDto);
			log.info("User:{},{} 录入采购退款失败", user.getId(), user.getName());

			/* 防止重复提交 */
			saveToken(request);

			/* 付款单信息保存 提交失败时用于重新显示画面 */
			PaymentInfoForBuyBackDto paymentInfo = new PaymentInfoForBuyBackDto();
			paymentInfo.setId(backListDto.getPaymentId());// backListDto.getId()
			paymentInfo.setProductTypeId(Integer.valueOf(backListDto
					.getProductTypeId()));
			paymentInfo.setProductTypeName(backListDto.getProductTypeName());
			paymentInfo.setSupplierId(Integer.valueOf(backListDto
					.getSupplierId()));
			paymentInfo.setSupplierName(backListDto.getSupplierName());

			paymentInfo.setPaymentMoney(new BigDecimal(request
					.getParameter("paymentMoney")));// 付款金额
			paymentInfo.setAlreadyAppointMoney(new BigDecimal(request
					.getParameter("alreadyAppointMoney")));// 指定金额
			paymentInfo.setAlreadyBackMoney(new BigDecimal(request
					.getParameter("alreadyBackMoney")));// 已退款金额
			paymentInfo.setCanBackMoney(new BigDecimal(request
					.getParameter("canBackMoney")));// 可退款金额

			request.setAttribute("paymentInfo", paymentInfo);

			return mapping.findForward("failure");
		}

	}

	/**
	 * 获取付款单信息（录入退款时用）
	 * 
	 * @author liuqg
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getPaymentInfoForAdd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.debug("录入退款时获取付款单信息");
		/* 付款单id */
		String id = request.getParameter("id");

		buyBackService = (IBuyBackService) getBean("buyBackServiceImpl");
		PaymentInfoForBuyBackDto paymentInfo = buyBackService
				.selectPaymentById(id);
		request.setAttribute("paymentInfo", paymentInfo);

		if (paymentInfo == null) {
			request.setAttribute("msg", "查询付款单信息错误");
			return mapping.findForward("failure");
		}
		/* 防止重复提交 */
		saveToken(request);

		return mapping.findForward("success");

	};

	/**
	 * 初始化检索
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author zzx
	 */
	public ActionForward getBuyBackList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.debug("进入采购退款管理初始列表");
		// 获得bean
		buyBackService = (IBuyBackService) getBean("buyBackServiceImpl");
		// 获得当前用户信息
		UserEntity user = (UserEntity) request.getSession().getAttribute(
				Constants.USERLOGIN);

		// 分页
		NewPage newPage = getNewPage(request);
		newPage.setUrl("getBuyBackList.do");

		List<BuyBackListDto> buyBackList = null;

		List<ProductTypeEntity> productTypeList = null;

		try {
			buyBackList = buyBackService.getBuyBackList(newPage, user);
			productTypeList = buyBackService.getProductType();
		} catch (Exception e) {
			log.error("获取采购退款一览错误", e);
		}
		// 将取得的采购退款列表放入request
		request.setAttribute("buyBackList", buyBackList);
		// 将产品类型列表放入request中
		request.setAttribute("productTypeList", productTypeList);
		// 分页
		request.setAttribute("NewPage", newPage);

		return mapping.findForward(Constants.SUCCESS);

	}

	/**
	 * 根据查询条件检索
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @author zzx
	 */
	public ActionForward searchBuyBack(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.debug("检索采购退款一览");

		// 检索条件放到request中,保存画面检索条件
		DynaActionForm dform = (DynaActionForm) form;

		BuyBackSearchDto buyBackSearchDto = (BuyBackSearchDto) dform
				.get("searchBuyBack");

		// 获取用户ID
		UserEntity user = (UserEntity) request.getSession().getAttribute(
				Constants.USERLOGIN);
		// 将当前登录用户的ID和权限放在检索用DTO中
		buyBackSearchDto.setLoginId(user.getId());
		buyBackSearchDto.setRoleId(String.valueOf(user.getRoleId()));

		request.setAttribute("buyBackSearchDto", buyBackSearchDto);

		// 分页
		NewPage newPage = getNewPage(request);
		newPage.setUrl("searchBuyBack.do");

		// 页面检索条件保存
		setNewPagePara(newPage, buyBackSearchDto);
		// 实现类
		buyBackService = (IBuyBackService) getBean("buyBackServiceImpl");

		List<BuyBackListDto> buyBackList = null;// 退票信息

		List<ProductTypeEntity> productTypeList = null;// 产品分类

		// 获取数据
		try {
			buyBackList = buyBackService.getBuyBackListByCondition(newPage,
					buyBackSearchDto);
			productTypeList = buyBackService.getProductType();
		} catch (Exception e) {
			request.setAttribute("msg", "检索错误!");
			log.error("检索退票一览信息错误：", e);
		}
		// 将取得的list放在request中
		request.setAttribute("buyBackList", buyBackList);
		// 产品分类
		request.setAttribute("productTypeList", productTypeList);
		request.setAttribute("NewPage", newPage);

		return mapping.findForward("success");
	}

	/**
	 * 分页条件设置
	 * 
	 * @author zzx
	 * @param newPage
	 * @param sellBackSearchDto
	 * @return {@link Void}
	 */

	private void setNewPagePara(NewPage newPage,
			BuyBackSearchDto buyBackSearchDto) {
		// 退款单号
		if (StringUtils.isNotEmpty(buyBackSearchDto.getId())) {
			newPage.setQuery("searchBuyBack.id", buyBackSearchDto.getId());
		}
		// 付款单号
		if (StringUtils.isNotEmpty(buyBackSearchDto.getPaymentId())) {
			newPage.setQuery("searchBuyBack.paymentId", buyBackSearchDto
					.getPaymentId());
		}
		// 退款编号
		if (StringUtils.isNotEmpty(buyBackSearchDto.getNo())) {
			newPage.setQuery("searchBuyBack.no", buyBackSearchDto.getNo());
		}
		// 产品分类编号

		if (StringUtils.isNotEmpty(buyBackSearchDto.getProductTypeId())) {
			newPage.setQuery("searchBuyBack.productTypeId", buyBackSearchDto
					.getProductTypeId());
		}
		// 供货商名称
		if (StringUtils.isNotEmpty(buyBackSearchDto.getSupplierName())) {
			newPage.setQuery("searchBuyBack.supplierName", buyBackSearchDto
					.getSupplierName());
		}
		// 退款金额
		if (StringUtils.isNotEmpty(buyBackSearchDto.getMoney())) {
			newPage
					.setQuery("searchBuyBack.money", buyBackSearchDto
							.getMoney());
		}
		// 录入起始日期
		if (StringUtils.isNotEmpty(buyBackSearchDto.getDateStart())) {
			newPage.setQuery("searchBuyBack.dateStart", buyBackSearchDto
					.getDateStart());
		}
		// 录入终止日期
		if (StringUtils.isNotEmpty(buyBackSearchDto.getDateEnd())) {
			newPage.setQuery("searchBackBack.dateEnd", buyBackSearchDto
					.getDateEnd());
		}

		// 退款起始日期
		if (StringUtils.isNotEmpty(buyBackSearchDto.getBackDateStart())) {
			newPage.setQuery("searchBuyBack.backDateStart", buyBackSearchDto
					.getBackDateStart());
		}
		// 退款终止日期
		if (StringUtils.isNotEmpty(buyBackSearchDto.getBackDateEnd())) {
			newPage.setQuery("searchBuyBack.backDateEnd", buyBackSearchDto
					.getBackDateEnd());
		}
		// 退款方式
		if (StringUtils.isNotEmpty(buyBackSearchDto.getBackWay())) {
			newPage.setQuery("searchBuyBack.backWay", buyBackSearchDto
					.getBackWay());
		}
		// 凭证号
		if (StringUtils.isNotEmpty(buyBackSearchDto.getNumber())) {
			newPage.setQuery("searchBuyBack.number", buyBackSearchDto
					.getNumber());
		}
		// 录入人
		if (StringUtils.isNotEmpty(buyBackSearchDto.getUserName())) {
			newPage.setQuery("searchBuyBack.userName", buyBackSearchDto
					.getUserName());
		}

	}

	/**
	 * 获取付款单信息（修改退款时用）
	 * 
	 * @author liuqg
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getPaymentInfoForUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.debug("修改退款获取付款单信息");
		/* 退款单id */
		String buyBackId = request.getParameter("id");

		BuyBackListDto buyBackViewDto = buyBackService
				.selectBuyBackById(buyBackId);

		if (buyBackViewDto == null) {
			log.debug("获取采购退款信息错误");
			return mapping.findForward("failure");

		}
		buyBackViewDto.setId(buyBackId);
		request.setAttribute("backAddDto", buyBackViewDto); // 第一个dto

		/* 付款单id */
		String paymentId = buyBackViewDto.getPaymentId();

		buyBackService = (IBuyBackService) getBean("buyBackServiceImpl");
		PaymentInfoForBuyBackDto paymentInfo = buyBackService
				.selectPaymentById(paymentId);
		if (paymentInfo == null) {
			request.setAttribute("msg", "查询付款单信息错误");
			return mapping.findForward("failure");
		}
		/* 已退款金额要减去本退款的 */
		paymentInfo.setAlreadyBackMoney(paymentInfo.getAlreadyBackMoney()
				.subtract(buyBackViewDto.getMoney()));
		/* 可退款金额要加本退款的 */
		paymentInfo.setCanBackMoney(paymentInfo.getCanBackMoney().add(
				buyBackViewDto.getMoney()));

		request.setAttribute("paymentInfo", paymentInfo);

		
		/* 防止重复提交 */
		saveToken(request);

		return mapping.findForward("success");

	};

	/**
	 * 采购退款修改
	 * 
	 * @author liuqg
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward modifyBuyBack(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		log.debug("采购退款修改");

		/* 防止重复提交 */
		if (isTokenValid(request)) {
			resetToken(request);
		} else {
			request.setAttribute("msg", "请勿重复录入!");
			return mapping.findForward("success");
		}
		/* 画面值取得 */
		DynaActionForm dform = (DynaActionForm) form;
		AddBuyBackListDto backListDto = (AddBuyBackListDto) dform
				.get("addBuyBackForm");

		buyBackService = (IBuyBackService) getBean("buyBackServiceImpl");

		/* 当期用户 */

		UserEntity user = (UserEntity) request.getSession().getAttribute(
				Constants.USERLOGIN);
		/* 退款单号 */
		backListDto.setId(backListDto.getId());
		/* 检查是否可以继续退款 */
		/*
		 * PaymentInfoForBuyBackDto tmppaymentInfo = buyBackService
		 * .selectPaymentById(backListDto.getPaymentId());
		 * 
		 * if (tmppaymentInfo.getCanBackMoney().floatValue() <= 0.0) {
		 * request.setAttribute("msg", "该付款单已不可再退款，请确认！"); return
		 * mapping.findForward("success"); }
		 */

		/* 插入数据 */
		if (buyBackService.modifyBuyBack(backListDto)) {
			log.info("User:{},{} 修改采购退款成功", user.getId(), user.getName());
			return mapping.findForward("success");
		} else {
			request.setAttribute("msg", "修改采购退款失败");
			request.setAttribute("backAddDto", backListDto);
			log.info("User:{},{} 修改采购退款失败", user.getId(), user.getName());

			/* 防止重复提交 */
			saveToken(request);

			/* 付款单信息保存 提交失败时用于重新显示画面 */
			PaymentInfoForBuyBackDto paymentInfo = new PaymentInfoForBuyBackDto();
			paymentInfo.setId(backListDto.getPaymentId());// backListDto.getId()
			paymentInfo.setProductTypeId(Integer.valueOf(backListDto
					.getProductTypeId()));
			paymentInfo.setProductTypeName(backListDto.getProductTypeName());
			paymentInfo.setSupplierId(Integer.valueOf(backListDto
					.getSupplierId()));
			paymentInfo.setSupplierName(backListDto.getSupplierName());

			paymentInfo.setPaymentMoney(new BigDecimal(request
					.getParameter("paymentMoney")));// 付款金额
			paymentInfo.setAlreadyAppointMoney(new BigDecimal(request
					.getParameter("alreadyAppointMoney")));// 指定金额
			paymentInfo.setAlreadyBackMoney(new BigDecimal(request
					.getParameter("alreadyBackMoney")));// 已退款金额
			paymentInfo.setCanBackMoney(new BigDecimal(request
					.getParameter("canBackMoney")));// 可退款金额

			request.setAttribute("paymentInfo", paymentInfo);

			return mapping.findForward("failure");
		}

	}
}
