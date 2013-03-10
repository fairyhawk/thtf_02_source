/**
 * ClassName  WayBillViewAction
 *
 * History
 * Create User: jiangmingxing
 * Create Date: 2010-5-13
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.action.wayBill;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.NewBaseAction;
import cn.com.thtf.egov.cms.dto.WayBillViewDto;
import cn.com.thtf.egov.cms.iservice.wayBill.IWayBillViewService;

/**
 * 运单查看
 * 
 * @author jiangmingxing
 * 
 */
public class WayBillViewAction extends NewBaseAction {
	/** log */
	private static Logger log = LoggerFactory
			.getLogger(WayBillViewAction.class);

	private IWayBillViewService wayBillViewService = null;

	/**
	 * 运单查看页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */

	public ActionForward wayBillView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.debug("进入获取运单查看");

		// 获取spring的bean容器
		wayBillViewService = (IWayBillViewService) this.getApplicationContext()
				.getBean("wayBillViewServiceImpl");

		// 获取传递的ID
		String box_id = request.getParameter("boxId");
		//发货时用,跳转到界面不同
		String back = request.getParameter("back");
		
		WayBillViewDto   wayBillViewDto = wayBillViewService.getWayBillById(box_id);
		if (wayBillViewDto==null){ 
			request.setAttribute("msg", "获取运单信息错误！");
			if ("true".equals(back)){
				 return mapping.findForward("failureSend");
			}else{
			  return mapping.findForward("failure");
			}
		}

		// 将Dto放在request中，画面显示
		request.setAttribute("wayBillViewDto", wayBillViewDto);
		
		//发货时用,跳转到界面不同
		request.setAttribute("back", back);

		// 跳转到运单查看页面
		return mapping.findForward("success");
	}

}
