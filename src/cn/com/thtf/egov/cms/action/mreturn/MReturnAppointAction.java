/**
 * ClassName  MReturnAppointAction
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-5-13
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.action.mreturn;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.NewBaseAction;
import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.dto.AppointSellDetailDto;
import cn.com.thtf.egov.cms.dto.AppointSendGoodsDetailDto;
import cn.com.thtf.egov.cms.dto.MreturnAppointDto;
import cn.com.thtf.egov.cms.dto.MreturnDto;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.form.MReturnAppointForm;
import cn.com.thtf.egov.cms.iservice.mreturn.IMReturnService;
import cn.com.thtf.egov.cms.util.Container;

/**
 * 回款指定
 * 
 * @author Lubo
 */
public class MReturnAppointAction extends NewBaseAction {

    /** log */
    private static Logger log = LoggerFactory.getLogger(MReturnAppointAction.class);
    /** IMReturnService */
    private IMReturnService mReturnService;

    /**
     * 初始化指定回款
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward appointInit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("初始化指定回款");

        MReturnAppointForm returnForm = (MReturnAppointForm) form;
        String mreturnId = returnForm.getAddPara().getMreturnId();

        mReturnService = (IMReturnService) Container.getBean("mReturnServiceImp");
        MreturnDto mreturnDto = mReturnService.getMReturnById(mreturnId);
        MreturnAppointDto para = new MreturnAppointDto();
        para.setMreturnId(mreturnId);

        /* 防止重复提交 */
        this.saveToken(request);

        /* 检索已指定的合同和发货单 */
        List<AppointSellDetailDto> sellList = mReturnService.getSellContractDetail(para,
                null);
        List<AppointSendGoodsDetailDto> sendGoodsList = mReturnService
                .getSendGoodsDetail(para, null);

        request.setAttribute("sellList", sellList);
        request.setAttribute("sellListSize", sellList.size());
        request.setAttribute("sendGoodsList", sendGoodsList);
        request.setAttribute("sendGoodsListSize", sendGoodsList.size());
        request.setAttribute("mreturn", mreturnDto);
        return mapping.findForward("appointList");
    }

    /**
     * 指定回款
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addMreturnAppoint(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("指定回款");

        /* 防止重复提交 */
        if (isTokenValid(request)) {
            resetToken(request);
        } else {
            return mapping.findForward("submitRepeat");
        }

        MReturnAppointForm para = (MReturnAppointForm) form;
        UserEntity userInfo = getLoginUserInfo(request);
        log.info("User:{},指定回款:{}", userInfo.getId(), para.getAddPara()
                .getMreturnId());

        mReturnService = (IMReturnService) Container.getBean("mReturnServiceImp");
        boolean result = mReturnService.addMreturnAppoint(para.getAddPara());

        if (result) {
            log.info("User:{},指定回款 成功", userInfo.getId());
            return mapping.findForward("createSuccess");
        } else {
            log.info("User:{},指定回款 失败", userInfo.getId());
            request.setAttribute("errorMsg", "回款指定失败！");
            // request.setAttribute("mreturnId",
            // para.getAddPara().getMreturnId());
            return mapping.findForward("createFail");
        }
    }

    /**
     * 发货单明细小页面
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getAppointSendGoods(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("初始化发货单明细小页面");

        MReturnAppointForm para = (MReturnAppointForm) form;

        mReturnService = (IMReturnService) Container.getBean("mReturnServiceImp");
        NewPage page = this.getNewPage(request);
        page.setUrl("getAppointSendGoods.do");
        page.setQuery("mreturnPara.mreturnId", para.getMreturnPara().getMreturnId());

        page.setQuery("mreturnPara.sendGoodsId", para.getMreturnPara().getSendGoodsId());
        page.setQuery("mreturnPara.productContractCode", para.getMreturnPara()
                .getProductContractCode());
        page.setQuery("mreturnPara.companyContractCode", para.getMreturnPara()
                .getCompanyContractCode());
        page.setQuery("mreturnPara.productCode", para.getMreturnPara().getProductCode());
        page.setQuery("mreturnPara.productName", para.getMreturnPara().getProductName());
        page.setQuery("mreturnPara.productType", para.getMreturnPara().getProductType());
        page.setQuery("mreturnPara.userName", para.getMreturnPara().getUserName());
        page.setQuery("mreturnPara.startArriveRequirementsDate", para.getMreturnPara()
                .getStartSgrqartermdate());
        page.setQuery("mreturnPara.endArriveRequirementsDate", para.getMreturnPara()
                .getEndSgrqartermdate());

        List<AppointSendGoodsDetailDto> sendGoodsList = mReturnService
                .getSendGoodsDetail(para.getMreturnPara(), page);

        request.setAttribute("para", para.getMreturnPara());
        request.setAttribute("NewPage", page);
        request.setAttribute("sendGoodsList", sendGoodsList);
        return mapping.findForward("sendGoods");
    }

    /**
     * 销售合同明细小页面
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getAppointSellContract(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("初始化销售合同明细小页面");

        MReturnAppointForm para = (MReturnAppointForm) form;

        mReturnService = (IMReturnService) Container.getBean("mReturnServiceImp");
        NewPage page = this.getNewPage(request);
        page.setUrl("getAppointSellContract.do");
        page.setQuery("mreturnPara.productContractCode", para.getMreturnPara()
                .getProductContractCode());
        page.setQuery("mreturnPara.companyContractCode", para.getMreturnPara()
                .getCompanyContractCode());
        page.setQuery("mreturnPara.mreturnId", para.getMreturnPara().getMreturnId());

        List<AppointSellDetailDto> sellList = mReturnService.getSellContractDetail(para
                .getMreturnPara(), page);

        request.setAttribute("para", para.getMreturnPara());
        request.setAttribute("NewPage", page);
        request.setAttribute("sellList", sellList);
        return mapping.findForward("sellContract");
    }
}
