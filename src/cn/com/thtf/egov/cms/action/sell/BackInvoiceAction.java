/**
 * ClassName  BackInvoiceAction
 *
 * History
 * Create User: liuqg
 * Create Date: 2010-5-20
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.action.sell;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import cn.com.thtf.egov.cms.dto.BackInvoicListDto;
import cn.com.thtf.egov.cms.dto.BackInvoicSearchDto;

import cn.com.thtf.egov.cms.dto.SellInvoiceDetailDto;
import cn.com.thtf.egov.cms.dto.SellInvoiceDto;
import cn.com.thtf.egov.cms.entity.ProductEntity;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.entity.WorkEntity;
import cn.com.thtf.egov.cms.iservice.sell.IBackInvoiceService;

/**
 * 销售退票
 * 
 * @author liuqg
 */

public class BackInvoiceAction extends NewBaseAction {
	private static Logger log = LoggerFactory
			.getLogger(BackInvoiceAction.class);

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
		BackInvoiceAction.log = log;
	}

	/**
	 * 退票查看
	 * 
	 * @author zhangzx
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward getSellInvoiceList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.debug("进入退票页面");
		// 获得bean
		IBackInvoiceService backInvoiceService = (IBackInvoiceService) this
				.getBean("backInvoiceServiceImpl");
		// 获得传过来的发票号
		String sellInvoiceId = (String) (request.getParameter("sellInvoiceId") ==null?request.getAttribute("sellInvoiceId"):request.getParameter("sellInvoiceId"));
		// 根据发票号获得发票明细
		List sellInvoiceDetailList = backInvoiceService
				.selectSellInvoiceDetailList(sellInvoiceId);
		// 计算开票金额合计
		BigDecimal totalMakeInvoicePrice = getTotalPrice(sellInvoiceDetailList);
		// 根据发票号获得发票表信息
		SellInvoiceDto sellInvoiceDto = backInvoiceService
				.selectSellInvoiceInfo(sellInvoiceId);
		//特殊说明的信息
		String text = (String) request.getAttribute("textarea");
		
		if(text !=null){
		    sellInvoiceDto.setText(text);
		}
		
		request.setAttribute("totalMakeInvoicePrice", totalMakeInvoicePrice);

		request.setAttribute("sellInvoiceDetailList", sellInvoiceDetailList);

		request.setAttribute("sellInvoiceDto", sellInvoiceDto);
		/* 防止重复提交 */
		saveToken(request);

		// 转到修改页面
		if ("2".equals(request.getParameter("flag"))) {
			return mapping.findForward("modify");
		}
		// 转到申请页面
		if ("3".equals(request.getParameter("flag"))) {
			return mapping.findForward("add");
		}

		// 开票查看用(用于标志返回不同的页面)
		request.setAttribute("back", request.getParameter("back"));

		// 转到查看页面
		return mapping.findForward(Constants.SUCCESS);
	}

	@SuppressWarnings("unchecked")
	private BigDecimal getTotalPrice(List sellInvoiceDetailList) {
		BigDecimal totalMakeInvoicePrice = new BigDecimal("0.00");
		for (int i = 0; i < sellInvoiceDetailList.size(); i++) {
			SellInvoiceDetailDto sellInvoiceDetail = (SellInvoiceDetailDto) sellInvoiceDetailList
					.get(i);
			BigDecimal price = sellInvoiceDetail.getMakeInvoicePrice();
			totalMakeInvoicePrice = totalMakeInvoicePrice.add(price);
		}
		return totalMakeInvoicePrice;
	}

	/**
	 * 保存、提交修改发票
	 * 
	 * @author zzx
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward modifyBackInvoice(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

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

		// 获得bean
		IBackInvoiceService backInvoiceService = (IBackInvoiceService) this
				.getBean("backInvoiceServiceImpl");

		boolean isSuccess = false;

		String sellInvoiceId = request.getParameter("sellInvoiceId");
		
		request.setAttribute("sellInvoiceId", sellInvoiceId);

		String newText = request.getParameter("textarea");
		
		request.setAttribute("textarea", newText);

		UserEntity user = (UserEntity) request.getSession().getAttribute(
				Constants.USERLOGIN);

		// 失败信息
		String errmsg = "";
		if ("submit".equals(method)) {
			// 提交
			// 获得产品类型
			String productTypeId = backInvoiceService
					.selectProductTypeId(sellInvoiceId);
			// 获得销售总监id
			String sellMajId = backInvoiceService
					.selectSellMajId(productTypeId);
			// 获得运营总监id
			String opeMajId = backInvoiceService.selectOpeMajId(productTypeId);
			// 获得销售助理id
			String sellAssistantId = backInvoiceService
					.selectSellAssistantId(sellInvoiceId);

			SellInvoiceDto sellInvoiceDto = new SellInvoiceDto();
			sellInvoiceDto.setId(Integer.parseInt(sellInvoiceId));
			sellInvoiceDto.setText(newText);
			sellInvoiceDto.setSellMajId(sellMajId);
			sellInvoiceDto.setOpeMajId(opeMajId);
			sellInvoiceDto.setConfirmId(sellAssistantId);

			WorkEntity work = new WorkEntity();
			work.setUserId(sellMajId);
			work.setWorkId(7);
			work.setCount(1);
			// 提交
			isSuccess = backInvoiceService.modifySellInvoiceSubmit(
					sellInvoiceDto, work);
			if (isSuccess) {
				log.info("User:{},{} 修改退票成功", user.getId(), user.getName());
			} else {
				log.info("User:{},{} 修改退票失败", user.getId(), user.getName());
			}
			// 失败信息
			errmsg = "提交失败";

		} else if ("save".equals(method)) {
			// 保存
			SellInvoiceDto sellInvoiceDto = new SellInvoiceDto();
			sellInvoiceDto.setId(Integer.parseInt(sellInvoiceId));
			sellInvoiceDto.setText(newText);

			isSuccess = backInvoiceService
					.updateSellInvoiceSave(sellInvoiceDto);

			if (isSuccess) {
				log.info("User:{},{} 保存退票成功", user.getId(), user.getName());
			} else {
				log.info("User:{},{} 保存退票失败", user.getId(), user.getName());
			}

			// 失败信息
			errmsg = "保存失败";
		}
		if (!isSuccess) {
			request.setAttribute("msg", errmsg);
			return mapping.findForward(Constants.FAIL);
		}

		return mapping.findForward(Constants.SUCCESS);
	}

	/**
	 * 退票评审显示
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
	public ActionForward showBackInvoiceJudge(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("进入退票评审");

		// 发票id sellInvoiceId
		String sellInvoiceId = (String) (request.getParameter("sellInvoiceId") == null ? request
				.getAttribute("sellInvoiceId")
				: request.getParameter("sellInvoiceId"));
		
		
		// id检查
		if (StringUtils.isEmpty(sellInvoiceId)) {
			request.setAttribute("msg", "退票评审显示失败：非法发票id");

			return mapping.findForward("failure");
		}

		// 获得spring的bean容器
		IBackInvoiceService backInvoiceJudgeService = (IBackInvoiceService) this
				.getApplicationContext().getBean("backInvoiceServiceImpl");

		List sellInvoiceDetailList = backInvoiceJudgeService
				.selectSellInvoiceDetailList(sellInvoiceId);

		// 计算开票金额合计
		BigDecimal totalMakeInvoicePrice = getTotalPrice(sellInvoiceDetailList);

		// 根据id获得退票评审信息
		SellInvoiceDto assessDto = backInvoiceJudgeService
				.getBackInvoiceById(sellInvoiceId.trim());

		if (assessDto == null) {
			request.setAttribute("msg", "退票评审显示失败!");

			return mapping.findForward("failure");
		}
		

		request.setAttribute("sellInvoiceDetailList", sellInvoiceDetailList);
		request.setAttribute("totalMakeInvoicePrice", totalMakeInvoicePrice);
		request.setAttribute("assessDto", assessDto);
		

		/* 防止重复提交 */
		saveToken(request);

		return mapping.findForward("success");
	}

	/**
	 * 退票评审
	 * 
	 * @author 蒋名星
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward backInvoiceJudge(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("正在评审");
		/* 防止重复提交 */
		if (isTokenValid(request)) {
			resetToken(request);
		} else {
			request.setAttribute("msg", "请勿重复评审!");
			request.setAttribute("sellInvoiceId",request.getParameter("backInvoiceAssess.id"));
			return mapping.findForward("failureToList");
		}
		// 获得用户id
		UserEntity user = (UserEntity) request.getSession().getAttribute(
				Constants.USERLOGIN);

		String assessResult = request.getParameter("assessResult");
		String sellInvoiceId = request.getParameter("backInvoiceAssess.id");
		request.setAttribute("sellInvoiceId",sellInvoiceId);

		DynaActionForm dform = (DynaActionForm) form;
		SellInvoiceDto backInvoiceAssessDto = (SellInvoiceDto) dform
				.get("backInvoiceAssess");

		// 获得spring的bean容器
		IBackInvoiceService backInvoiceJudgeService = (IBackInvoiceService) this
				.getApplicationContext().getBean("backInvoiceServiceImpl");

		boolean isSuccess = false;

		int status = 0;

		// 查询发票表
		SellInvoiceDto sellInvoice = backInvoiceJudgeService
				.selectSellInvoiceInfo(String.valueOf(backInvoiceAssessDto
						.getId()));

		String roleId = String.valueOf(user.getRoleId());
		backInvoiceAssessDto.setRoleId(roleId);
		
		if ("5".equals(roleId)) {
			//销售总监姓名
			backInvoiceAssessDto.setSellMajName(user.getName());
			
			if ("pass".equals(assessResult)) {
				status = 4;
			} else if ("nopass".equals(assessResult)) {
				status = 3;
			}

			backInvoiceAssessDto.setStatus(status);
			backInvoiceAssessDto.setSellMajDate(new SimpleDateFormat(
					"yyyy-MM-dd").format(new Date()));
			// 如果同意 修改状态并且给运营总监发待办事务
			if (status == 4) {
				// 给运营总监发待办事务
				WorkEntity work = new WorkEntity();
				work.setCount(1);
				work.setUserId(sellInvoice.getOpeMajId());
				work.setWorkId(7);
				// 把销售总监的待办事务减一
				WorkEntity workself = new WorkEntity();
				workself.setCount(-1);
				workself.setUserId(user.getId());
				workself.setWorkId(7);
				
				 
				isSuccess = backInvoiceJudgeService.updateBackInvoiceAssess(
						backInvoiceAssessDto, work, workself);
				if (isSuccess) {
					log.info("User:{},{} 销售总监退票评审通过成功", user.getId(), user
							.getName());
				} else {
					log.info("User:{},{} 销售总监退票评审通过失败", user.getId(), user
							.getName());
				}
			} else {

				WorkEntity workself = new WorkEntity();
				workself.setCount(-1);
				workself.setUserId(user.getId());
				workself.setWorkId(7);
				// 不同意只修改状态
				isSuccess = backInvoiceJudgeService.updateAssessNoWork(
						backInvoiceAssessDto, workself);
				if (isSuccess) {
					log.info("User:{},{} 销售总监退票评审未通过成功", user.getId(), user
							.getName());
				} else {
					log.info("User:{},{} 销售总监退票评审未通过失败", user.getId(), user
							.getName());
				}
			}
		} else if ("17".equals(roleId)) {
			// 运营总监姓名 
			backInvoiceAssessDto.setOpeMajName(user.getName());
			
			
			// 运营总监意见0/1
			String logmsg = "";
			if ("pass".equals(assessResult)) {
				status = 6;
				logmsg = "通过";
			} else if ("nopass".equals(assessResult)) {
				status = 5;
				logmsg = "未通过";
			}

			backInvoiceAssessDto.setStatus(status);
			backInvoiceAssessDto.setOpeMajDate(new SimpleDateFormat(
					"yyyy-MM-dd").format(new Date()));

			WorkEntity workself = new WorkEntity();
			workself.setCount(-1);
			workself.setUserId(user.getId());
			workself.setWorkId(7); 
			
			// 运营总监不发待办事务 只改状态
			isSuccess = backInvoiceJudgeService.updateOpeAssess(
					backInvoiceAssessDto, workself);

			if (isSuccess) {
				log.info("User:{},{} 运营总监退票评审" + logmsg + "成功", user.getId(),
						user.getName());
			} else {
				log.info("User:{},{} 运营总监退票评审" + logmsg + "失败", user.getId(),
						user.getName());
			}

		}
		//request.setAttribute("backInvoiceAssessDto", backInvoiceAssessDto);
		if (!isSuccess) {
			request.setAttribute("msg", "评审失败");
			return mapping.findForward("failure");
		}
		return mapping.findForward("success");

	}

	/**
	 * 初始化检索,根据销售经理ID,检索其申请的发票
	 * 
	 * @author liuqg
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */

	public ActionForward getBackInvoiceByUserId(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.debug("获得退票一览");

		IBackInvoiceService backInvoiceService = null;
		// 获取用户ID
		UserEntity user = (UserEntity) request.getSession().getAttribute(
				Constants.USERLOGIN);
		// String userId = user.getId();
		// 分页
		NewPage newPage = getNewPage(request);
		newPage.setUrl("getBackInvoiceByUserId.do");
		backInvoiceService = (IBackInvoiceService) getBean("backInvoiceServiceImpl");

		List<BackInvoicListDto> backInvoicListDtos = null;// 退票信息
		List<ProductEntity> productEntities = null;// 产品分类
		// 获取数据
		try {
			backInvoicListDtos = backInvoiceService
					.selectBackInvoicListByUserId(newPage, user);
			productEntities = backInvoiceService.selectProductType();
		} catch (Exception e) {
			log.error("获取退票一览信息错误：", e);
		}
		// 将取得的退票list放在request中
		request.setAttribute("backInvoiceList", backInvoicListDtos);
		// 产品分类
		request.setAttribute("productEntitieList", productEntities);
		request.setAttribute("NewPage", newPage);

		return mapping.findForward("success");

	}

	/**
	 * 根据画面条件,检索退票
	 * 
	 * @author liuqg
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */

	public ActionForward getBackInvoiceByCondition(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.debug("检索退票一览");
		IBackInvoiceService backInvoiceService = null;
		// 检索条件放到request中,保存画面检索条件
		DynaActionForm dform = (DynaActionForm) form;
		BackInvoicSearchDto backInvoicSearchDto = (BackInvoicSearchDto) dform
				.get("searchBackIncoice");
		// 获取用户ID
		UserEntity user = (UserEntity) request.getSession().getAttribute(
				Constants.USERLOGIN);
		// 将当前登录用户的ID和权限放在检索用DTO中
		backInvoicSearchDto.setLoginId(user.getId());
		backInvoicSearchDto.setRoleId(String.valueOf(user.getRoleId()));

		request.setAttribute("searchBackIncoiceDto", backInvoicSearchDto);

		// 分页
		NewPage newPage = getNewPage(request);
		newPage.setUrl("searchBackIncoice.do");

		// 页面检索条件保存
		setNewPagePara(newPage, backInvoicSearchDto);
		// 实现类
		backInvoiceService = (IBackInvoiceService) getBean("backInvoiceServiceImpl");
		List<BackInvoicListDto> backInvoicListDtos = null;// 退票信息

		List<ProductEntity> productEntities = null;// 产品分类

		// 获取数据
		try {
			backInvoicListDtos = backInvoiceService
					.selectBackInvoicListByCondition(newPage,
							backInvoicSearchDto);
			productEntities = backInvoiceService.selectProductType();
		} catch (Exception e) {
			request.setAttribute("msg", "检索错误!");
			log.error("检索退票一览信息错误：", e);
		}
		// 将取得的list放在request中
		request.setAttribute("backInvoiceList", backInvoicListDtos);
		// 产品分类
		request.setAttribute("productEntitieList", productEntities);
		request.setAttribute("NewPage", newPage);

		return mapping.findForward("success");

	}

	/**
	 * 分页条件设置
	 * 
	 * @author liuqg
	 * @param newPage
	 * @param backInvoicSearchDto
	 * @return {@link Void}
	 */

	public void setNewPagePara(NewPage newPage,
			BackInvoicSearchDto backInvoicSearchDto) {
		// 发票号
		if (StringUtils.isNotEmpty(backInvoicSearchDto.getNumber())) {
			newPage.setQuery("searchBackIncoice.number", backInvoicSearchDto
					.getNumber());
		}
		// 开票申请单号
		if (StringUtils.isNotEmpty(backInvoicSearchDto.getMakeInvoiceId())) {
			newPage.setQuery("searchBackIncoice.makeInvoiceId",
					backInvoicSearchDto.getMakeInvoiceId());
		}
		// 客户名称
		if (StringUtils.isNotEmpty(backInvoicSearchDto.getCustomerName())) {
			newPage.setQuery("searchBackIncoice.customerName",
					backInvoicSearchDto.getCustomerName());
		}
		// 产品分类编号
		{
			if (StringUtils.isNotEmpty(backInvoicSearchDto.getProductTypeId())) {
				newPage.setQuery("searchBackIncoice.productTypeId", String
						.valueOf(backInvoicSearchDto.getProductTypeId()));
			}
		}

		// 发票类型
		if (StringUtils.isNotEmpty(backInvoicSearchDto.getInvoiceType())) {
			newPage.setQuery("searchBackIncoice.invoiceType",
					backInvoicSearchDto.getInvoiceType());
		}
		// 申请人名称
		if (StringUtils.isNotEmpty(backInvoicSearchDto.getUserName())) {
			newPage.setQuery("searchBackIncoice.userName", backInvoicSearchDto
					.getUserName());
		}
		// 确认人名称
		if (StringUtils.isNotEmpty(backInvoicSearchDto.getConfirmName())) {
			newPage.setQuery("searchBackIncoice.confirmName",
					backInvoicSearchDto.getConfirmName());
		}
		// 发票状态
		if (StringUtils.isNotEmpty(backInvoicSearchDto.getStatus())) {
			newPage.setQuery("searchBackIncoice.status", backInvoicSearchDto
					.getStatus());
		}
		// 开票开始日期
		if (StringUtils.isNotEmpty(backInvoicSearchDto.getDateStart())) {
			newPage.setQuery("searchBackIncoice.dateStart", backInvoicSearchDto
					.getDateStart());
		}
		// 开票终止日期
		if (StringUtils.isNotEmpty(backInvoicSearchDto.getDateEnd())) {
			newPage.setQuery("searchBackIncoice.dateEnd", backInvoicSearchDto
					.getDateEnd());
		}
		// 申请开始日期
		if (StringUtils.isNotEmpty(backInvoicSearchDto.getRequestDateStart())) {
			newPage.setQuery("searchBackIncoice.requestDateStart",
					backInvoicSearchDto.getRequestDateStart());
		}
		// 申请终止日期
		if (StringUtils.isNotEmpty(backInvoicSearchDto.getRequestDateEnd())) {
			newPage.setQuery("searchBackIncoice.requestDateEnd",
					backInvoicSearchDto.getRequestDateEnd());
		}
		// 退票开始日期
		if (StringUtils.isNotEmpty(backInvoicSearchDto.getBackDateStart())) {
			newPage.setQuery("searchBackIncoice.backDateStart",
					backInvoicSearchDto.getBackDateStart());
		}
		// 退票终止日期
		if (StringUtils.isNotEmpty(backInvoicSearchDto.getBackDateEnd())) {
			newPage.setQuery("searchBackIncoice.backDateEnd",
					backInvoicSearchDto.getBackDateEnd());
		}
		// 发货单号
        if (StringUtils.isNotEmpty(backInvoicSearchDto.getSendGoodsId())) {
            newPage.setQuery("searchBackIncoice.sendGoodsId",
                    backInvoicSearchDto.getSendGoodsId());
        }

	}

	/**
	 * 退票 确认
	 * 
	 * @author liuqg
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */

	public ActionForward confirmBackIncoice(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		log.debug("退票确认");
		IBackInvoiceService backInvoiceService = null;

		// 获取用户ID
		UserEntity user = (UserEntity) request.getSession().getAttribute(
				Constants.USERLOGIN);

		// 将用户名和发票ID，存入到dto中
		BackInvoicListDto backInvoicListDto = new BackInvoicListDto();
		backInvoicListDto.setId(request.getParameter("sellInvoiceId"));
		backInvoicListDto.setConfirmName(user.getName());
		String makeInvoiceId = request.getParameter("makeInvoiceId");
		backInvoicListDto.setMakeInvoiceId(makeInvoiceId);
		// 实现类
		backInvoiceService = (IBackInvoiceService) getBean("backInvoiceServiceImpl");

		// 更新数据
		try {
			if (backInvoiceService.confirmBackInvoice(backInvoicListDto)) {
				log.info("User:{},{} 退票确认成功", user.getId(), user.getName());
				request.setAttribute("msg", "退票确认成功!");
			} else {
				log.info("User:{},{} 退票确认失败", user.getId(), user.getName());
				request.setAttribute("msg", "退票确认失败!");
			}
		} catch (Exception e) {
			log.error("退票确认错误：", e);
		}

		return mapping.findForward("success");

	}
}
