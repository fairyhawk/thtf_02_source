/**
 * ClassName  WayBillAllAction
 *
 * History
 * Create User: liuqg
 * Create Date: 2010-5-10
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.action.wayBill;

import java.util.ArrayList;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.NewBaseAction;
import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.constant.Constants;
import cn.com.thtf.egov.cms.dto.WayBillViewDto;
import cn.com.thtf.egov.cms.dto.WayBillSearchDto;
import cn.com.thtf.egov.cms.dto.WayBillAllDto;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.form.WayBillModifyForm;
import cn.com.thtf.egov.cms.iservice.wayBill.IWayBillService;

/**
 * 运单查看一览页面
 * 
 * @author liuqg
 */

public class WayBillAllAction extends NewBaseAction {

	private static Logger log = LoggerFactory.getLogger(WayBillAllAction.class);

	private IWayBillService billService = null;

	/**
	 * 获得所有的运单信息
	 * 
	 * @author liuqg
	 */

	public ActionForward getWayBillList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		log.debug("获取运单一览:getWayBillList");

		// 获取用户登陆物流中心ID
		UserEntity user = (UserEntity) request.getSession().getAttribute(
				Constants.USERLOGIN);

		billService = (IWayBillService) getBean("wayBillServiceImp");
		List<WayBillAllDto> list = new ArrayList<WayBillAllDto>();

		// 分页
		NewPage newPage = getNewPage(request);
		newPage.setUrl("wayBillAll.do");
		try {

			list = billService.selectWayBillAll(newPage, user.getLogisticsId());

		} catch (Exception e) {
			log.debug("获取运单一览错误:" + e);
		}
		request.setAttribute("billAllDtoList", list);

		request.setAttribute("NewPage", newPage);

		return mapping.findForward("success");

	}

	/**
	 * 根据画面条件检索运单信息
	 * 
	 * @author liuqg
	 */

	public ActionForward getWayBillByCondition(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.debug("检索运单一览:searchWayBillList");

		// 获取用户登陆物流中心ID
		UserEntity user = (UserEntity) request.getSession().getAttribute(
				Constants.USERLOGIN);

		// 检索条件放到request中,保存画面检索条件

		DynaActionForm dform = (DynaActionForm) form;
		WayBillSearchDto searchWayBillDto = (WayBillSearchDto) dform
				.get("searchWayBill");

		searchWayBillDto.setLogisticsId(user.getLogisticsId());

		request.setAttribute("wayBillDto", searchWayBillDto);

		/* 实现类获得 */
		billService = (IWayBillService) getBean("wayBillServiceImp");
		List<WayBillAllDto> list = new ArrayList<WayBillAllDto>();

		// 分页
		NewPage newPage = getNewPage(request);
		newPage.setUrl("searchWayBill.do");
		newPage.setQuery("searchWayBill.status", searchWayBillDto.getStatus());
		newPage.setQuery("searchWayBill.no", searchWayBillDto.getNo());
		newPage.setQuery("searchWayBill.boxId", searchWayBillDto.getBoxId());
		newPage.setQuery("searchWayBill.company_name", searchWayBillDto
				.getCompany_name());

		// 跟据条件检索数据
		try {
			list = billService.selectWayBillByCondition(newPage,
					searchWayBillDto);

			request.setAttribute("billAllDtoList", list);
		} catch (Exception e) {
			log.debug("检索运单一览错误:" + e);
			return mapping.findForward("success");
		}
		// 检索的结果放在request中。名字同画面初始化时billAllDtoList
		request.setAttribute("billAllDtoList", list);

		request.setAttribute("NewPage", newPage);
		return mapping.findForward("success");

	}

	/**
	 * 修改链接跳转到修改画面
	 * 
	 * @author liuqg
	 */

	public ActionForward wayBillModifyView(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		log.debug("修改运单显示");
		
		// 从request中取得传来的参数放 
		String boxId = request.getParameter("boxId");

		/* 实现类获得 */
		billService = (IWayBillService) getBean("wayBillServiceImp");
		
		/* 取得dto */ 
		WayBillViewDto billViewDto  = billService.selectWayBillForModify(boxId);
		if (billViewDto==null){
			saveToken(request);
			request.setAttribute("msg", "获取运单信息错误！");
			log.debug("修改运单—跳转到修改画面错误");
			return mapping.findForward("failure");
		}  
		 
		// 将Dto放在request中，画面显示
		request.setAttribute("wayBillModifyDto", billViewDto);
		/* 防止重复提交 */
		saveToken(request);
		
		return mapping.findForward("success");

	}

	/**
	 * 修改运单信息
	 * 
	 * @author liuqg
	 */

	public ActionForward modifyWayBill(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		log.debug("修改运单:wayBillModify");
		/* 防止重复提交 */
		if (isTokenValid(request)) {
			resetToken(request);
		} else {
			request.setAttribute("msg", "请勿重复提交!");
			return mapping.findForward("success");
		} 
		// 获得画面填入的信息
		WayBillModifyForm billModifyForm = (WayBillModifyForm) form;

		// 将参数放在dto中,执行更新SQL时用

		WayBillViewDto billModifyDto = setWayBillModifyDto(request,
				billModifyForm);
		// 获取用户登陆物流中心ID
		UserEntity user = (UserEntity) request.getSession().getAttribute(
				Constants.USERLOGIN);
		billModifyDto.setLog_adm_name(user.getName());
		// 将Dto放在request中，用于修改失败时重新显示画面
		request.setAttribute("wayBillModifyDto", billModifyDto);

		/* 实现类获得 */
		billService = (IWayBillService) getBean("wayBillServiceImp");

		/* 执行更新操作 */
		try {
			if (billService.updateWayBill(billModifyDto)) {
				return mapping.findForward("success");
			} else {
				log.debug("修改运单错误失败:");
				request.setAttribute("msg", "修改运单信息失败！");
				return mapping.findForward("failure");
			}
		} catch (Exception e) {
			log.debug("修改运单错误:" + e);
			request.setAttribute("msg", "修改运单信息失败！");
			return mapping.findForward("failure");
		}
	}

	/**
	 * 将画面信息set到dto中
	 * 
	 * @author liuqg
	 */
	public WayBillViewDto setWayBillModifyDto(HttpServletRequest request,
			WayBillModifyForm billModifyForm) {

		WayBillViewDto billModifyDto = new WayBillViewDto();
		billModifyDto.setBox_id(billModifyForm.getBox_id());// 装箱单号
		billModifyDto.setNo(billModifyForm.getNo());
		billModifyDto.setBox_date(billModifyForm.getBox_date());// 发货日期
		billModifyDto.setCustomer_name(billModifyForm.getCustomer_name());// 客户名称
		billModifyDto.setName(billModifyForm.getName());// 货物接收单位名称
		billModifyDto.setCustomer_address(billModifyForm.getCustomer_address());// 发货地址
		billModifyDto.setLinkman(billModifyForm.getLinkman());// 联系人
		billModifyDto.setCustomer_postcode(billModifyForm
				.getCustomer_postcode());// 客户邮编
		billModifyDto.setCustomer_tel(billModifyForm.getCustomer_tel());// 客户电话
		billModifyDto.setCustomer_mobile(billModifyForm.getCustomer_mobile());// 客户手机
		billModifyDto.setBox_count(billModifyForm.getBox_count());// 装箱件数
		billModifyDto.setRequest_way(billModifyForm.getRequest_way());// 要求货运方式
		billModifyDto.setNo(billModifyForm.getNo());// 运单号
		billModifyDto.setReality_way(billModifyForm.getReality_way());// 实际货运方式
		billModifyDto.setEstimate_date(billModifyForm.getEstimate_date());// 预计到货日期
		billModifyDto.setBox_tel(billModifyForm.getBox_tel());// 物流公司联系电话
		billModifyDto.setBox_info(billModifyForm.getBox_info());// 在途信息
		billModifyDto.setArrival_date(billModifyForm.getArrival_date());// 到货日期
		billModifyDto.setSignoff_date(billModifyForm.getSignoff_date());// 签收日期
		billModifyDto.setSignoff_name(billModifyForm.getSignoff_name());// 签收人

		// 根据填入的信息判断是否需要更新运单状态
		if (billModifyForm.getSignoff_date() != "") {
			// 签收日期不为空时改为签收状态
			billModifyDto.setStatus(4);
		} else if (billModifyForm.getArrival_date() != "") {
			// 到达日期不为空时设置为3到达
			billModifyDto.setStatus(3);// billModifyForm.getArrival_date
		} else {
			// 否则为2在途
			billModifyDto.setStatus(2);
		}

		return billModifyDto;

	}
}
