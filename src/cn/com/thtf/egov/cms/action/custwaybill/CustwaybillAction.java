/**
 * ClassName  CustwaybillAction
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-8-17
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.action.custwaybill;

import java.util.Date;
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
import cn.com.thtf.egov.cms.constant.Constants;
import cn.com.thtf.egov.cms.dto.CustwaybillDto;
import cn.com.thtf.egov.cms.dto.WayBillViewDto;
import cn.com.thtf.egov.cms.form.CustwaybillForm;
import cn.com.thtf.egov.cms.iservice.custwaybill.ICustwaybillService;

/**
 * 运单查询
 * 
 * @author ChenHuajiang
 */
public class CustwaybillAction extends NewBaseAction {

	/** log */
	private static Logger log = LoggerFactory
			.getLogger(CustwaybillAction.class);
	/** ICustwaybillService */
	private ICustwaybillService custwaybillService = null;

	/**
	 * 运单查看
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward queryWaybillDetail(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String ip = request.getRemoteAddr();
		log.info("查询者IP:" + ip);

		Object obj = request.getSession().getAttribute(ip); 
		 
		if (obj == null
				|| new Date().getTime()
						- ((Long) request.getSession().getAttribute(ip))
								.longValue() > 3000) {

			request.getSession().setAttribute(ip, new Date().getTime());

			CustwaybillForm custwaybillForm = (CustwaybillForm) form;
			CustwaybillDto queryPara = custwaybillForm.getQueryPara();

			custwaybillService = (ICustwaybillService) getBean("custwaybillService");
			WayBillViewDto result = custwaybillService
					.getWayBillDetail(queryPara.getBoxId());

			request.setAttribute("wayBillData", result);
			if (result == null) {
				return mapping.findForward("waybillList");
			} else {
				return mapping.findForward("waybillDetail");
			}

		} else {
			return mapping.findForward("waybillList");
		}
	}

	/**
	 * 运单查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward queryWaybill(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.info("查询者IP:" + request.getRemoteAddr());

		CustwaybillForm custwaybillForm = (CustwaybillForm) form;
		CustwaybillDto queryPara = custwaybillForm.getQueryPara();

		/* 验证码校验 */
		String sessionCode = (String) request.getSession().getAttribute(
				Constants.SESSION_CONFIRM_STR);
		String textCode = request.getParameter("code");

		if (StringUtils.isNotEmpty(queryPara.getProductContractCode())
				|| StringUtils.isNotEmpty(queryPara.getCompanyContractCode())) {
			if (StringUtils.isNotEmpty(textCode)
					&& StringUtils.equals(sessionCode, textCode)) {
				/* 封装翻页参数 */
				NewPage newPage = getNewPage(request);
				newPage.setUrl("queryWaybill.do");
				newPage.setQuery("queryPara.productContractCode",
						queryPara.getProductContractCode());
				newPage.setQuery("queryPara.companyContractCode",
						queryPara.getCompanyContractCode());

				custwaybillService = (ICustwaybillService) getBean("custwaybillService");
				List<CustwaybillDto> resultList = custwaybillService
						.getWaybill(queryPara, newPage);

				// resultList.get(0).setBoxId("");
				// resultList.get(1).setBoxId(null);

				request.setAttribute("waybillList", resultList);
				request.setAttribute("NewPage", newPage);
				request.setAttribute("queryPara", queryPara);
			} else {
				request.setAttribute("errorMsg", "验证码错误！");
			}
		}
		return mapping.findForward("waybillList");
	}
}
