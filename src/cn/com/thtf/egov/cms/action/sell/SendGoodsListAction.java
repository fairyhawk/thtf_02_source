/**
 * ClassName  SendGoodsListAction
 *
 * History
 * Create User: 
 * Create Date: 2010-4-19
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.action.sell;

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
import cn.com.thtf.egov.cms.dto.SendGoodInfoDto;
import cn.com.thtf.egov.cms.dto.SendGoodsListInfoDto;
import cn.com.thtf.egov.cms.dto.SendgoodSauditPrepareDto;
import cn.com.thtf.egov.cms.dto.SendgoodViewSelectProductDto;
import cn.com.thtf.egov.cms.entity.ProductTypeEntity;
import cn.com.thtf.egov.cms.entity.SendGoodsEntity;
import cn.com.thtf.egov.cms.entity.StockroomEntity;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.form.SendGoodsListForm;
import cn.com.thtf.egov.cms.iservice.common.ICommonService;
import cn.com.thtf.egov.cms.iservice.inventory.IStockroomService;
import cn.com.thtf.egov.cms.iservice.sell.ISendGoodsService;
import cn.com.thtf.egov.cms.util.Util;

/**
 * 发货单列表
 * 
 * @author Luping
 */
public class SendGoodsListAction extends NewBaseAction {

    /** log */
    private static Logger log = LoggerFactory.getLogger(SendGoodsListAction.class);

    /**
     * 发货管理列表页
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getSendGoodsList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("进入发货管理列表页");
        /* 检索form */
        SendGoodsListForm sglistform = (SendGoodsListForm) form;
        /* 获取初始化条件 */
        String init = request.getParameter("sdlqd.init");
        sglistform.getSdlqd().setInit(init);
        /* 获取当前登陆用户 */
        UserEntity user = getLoginUserInfo(request);
        /* 获取bean容器 */
        ISendGoodsService sendGoodsService = (ISendGoodsService) getBean("sendGoodsServiceImpl");
        /* 产品分类 */
        List<ProductTypeEntity> producttypelist = null;
        /* 库房 */
        List<StockroomEntity> stockroomlist = null;
        /* 翻页 */
        NewPage newPage = getNewPage(request);
        /* 翻页url */
        newPage.setUrl("getSendGoodsList.do");
        /* 翻页传参 */
        pageParam(newPage, sglistform);
        /* 发货单列数据集 */
        List<SendGoodsListInfoDto> sendGoodsList = sendGoodsService.getSendGoodsList(
                newPage, sglistform.getSdlqd(), user);
        producttypelist = sendGoodsService.getAllProductType();
        // stockroomlist = sendGoodsService.getAllStockRoom();
        /* 检索所有库房 */
        IStockroomService stockroomService = (IStockroomService) getBean("stockroomServiceImp");
        stockroomlist = stockroomService.getTypicallyAndVirtual();
        /* 将数据返回页面 */
        request.setAttribute("roleId", user.getRoleId());
        request.setAttribute("stockroomlist", stockroomlist);
        request.setAttribute("producttypelist", producttypelist);
        request.setAttribute("NewPage", newPage);
        request.setAttribute("sendgoodsform", sglistform);
        request.setAttribute("sendGoodsList", sendGoodsList);
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 抽象翻页检索传参
     * 
     * @param newPage
     * @param sglistform
     */
    private void pageParam(NewPage newPage, SendGoodsListForm sglistform) {
        newPage.setQuery("sdlqd.sgid", sglistform.getSdlqd().getSgid());
        newPage.setQuery("sdlqd.stockroomid", sglistform.getSdlqd().getStockroomid());
        newPage.setQuery("sdlqd.producttypeid", sglistform.getSdlqd().getProducttypeid());
        newPage.setQuery("sdlqd.ccontractcode", sglistform.getSdlqd().getCcontractcode());
        newPage.setQuery("sdlqd.pcontractcode", sglistform.getSdlqd().getPcontractcode());
        newPage.setQuery("sdlqd.sgusername", sglistform.getSdlqd().getSgusername());
        newPage.setQuery("sdlqd.customername", sglistform.getSdlqd().getCustomername());
        newPage.setQuery("sdlqd.startapplydate", sglistform.getSdlqd()
                .getStartapplydate());
        newPage.setQuery("sdlqd.endapplydate", sglistform.getSdlqd().getEndapplydate());
        newPage.setQuery("sdlqd.startsendgoodsdate", sglistform.getSdlqd()
                .getStartsendgoodsdate());
        newPage.setQuery("sdlqd.endsendgoodsdate", sglistform.getSdlqd()
                .getEndsendgoodsdate());
        newPage.setQuery("sdlqd.startrequestdate", sglistform.getSdlqd()
                .getStartrequestdate());
        newPage.setQuery("sdlqd.endrequestdate", sglistform.getSdlqd()
                .getEndrequestdate());
        newPage.setQuery("sdlqd.sgsgtype", sglistform.getSdlqd().getSgsgtype());
        newPage.setQuery("sdlqd.sgstatus", sglistform.getSdlqd().getSgstatus());
        newPage.setQuery("sdlqd.init", sglistform.getSdlqd().getInit());

        newPage.setQuery("sdlqd.contractProName", sglistform.getSdlqd()
                .getContractProName());
    }

    /**
     * 发货单查看
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getSendGoodsView(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        String sendid = request.getParameter("id");
        if (StringUtils.isEmpty(sendid)) {
            request.setAttribute("err", "发货单不存在！");
            return mapping.findForward(Constants.FAIL);
        }

        log.debug("发货单查看页面,发货单ID:" + sendid);

        // 获取spring的service
        ISendGoodsService sendGoodsService = (ISendGoodsService) this
                .getBean("sendGoodsServiceImpl");
        Util util = new Util();
        SendGoodInfoDto sge = sendGoodsService.getSendGoodsView(sendid);
        List<SendgoodViewSelectProductDto> list = sendGoodsService
                .sendGoodsViewProduct(sge);
        Integer[] str = null;
        if (StringUtils.isEmpty(sge.getSendStkAdmIdea())) {
            log.debug("评审意见为空！");
        } else {
            str = util.splitIdea(sge.getSendStkAdmIdea());
        }
        request.setAttribute("svsplist", list);
        request.setAttribute("ideastr", str);
        request.setAttribute("sge", sge);
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 发货单删除
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward removeSendGoods(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("发货单删除！");
        String id = request.getParameter("id");
        UserEntity userInfo = getLoginUserInfo(request);
        log.info("User:{},发货单删除:{}", userInfo.getId(), id);
        String sgstatus = request.getParameter("sgstatus");
        if (StringUtils.isEmpty(id)) {
            log.debug("发货单号空值！");
            return mapping.findForward(Constants.FAIL);
        }
        ISendGoodsService sendGoodsService = (ISendGoodsService) this
                .getBean("sendGoodsServiceImpl");
        boolean result = sendGoodsService.removeSendGoods(id, sgstatus);
        if (result == false) {
            log.info("User:{},发货单删除 失败", userInfo.getId());
            request.setAttribute("err", "发货单删除失败！");
        } else {
            log.info("User:{},发货单删除 成功", userInfo.getId());
        }
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 备货单评审
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward stockingAssessment(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("备货评审初始化页");
        UserEntity userInfo = getLoginUserInfo(request);
        String sendid = request.getParameter("id");
        if (StringUtils.isEmpty(sendid)) {
            request.setAttribute("err", "备货单不存在！");
            return mapping.findForward(Constants.FAIL);
        }
        log.debug("备货单评审页面,备货单ID:" + sendid);

        // 获取spring的service
        ISendGoodsService sendGoodsService = (ISendGoodsService) this
                .getBean("sendGoodsServiceImpl");
        SendGoodInfoDto sge = sendGoodsService.getReservegoodsView(sendid);
        List<SendgoodSauditPrepareDto> list = sendGoodsService
                .selectSauditPrepareView(sge);
        request.setAttribute("sge", sge);
        request.setAttribute("list", list);
        request.setAttribute("roleId", userInfo.getRoleId());
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 备货评审保存
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward modifySendStockingAssessment(ActionMapping mapping,
            ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 获取当前登陆用户
        UserEntity userInfo = getLoginUserInfo(request);
        // 备货单ID
        String sendid = request.getParameter("id");

        log.info("User:{},备货单评审:{}", userInfo.getId(), sendid);
        log.debug("评审PD:" + request.getParameter("iderpd") + "区域总监评审意见:"
                + request.getParameter("AreaMajIdea") + "销售总监评审意见:"
                + request.getParameter("proMajIder") + "特殊说明:"
                + request.getParameter("textarea"));

        // 备货单状态的判断
        ICommonService commonService = (ICommonService) getBean("commonServiceImpl");
        String status = commonService.getStatusById("send_goods", sendid);
        if ((StringUtils.equals("6", status) && userInfo.getRoleId() == 5)
                || (StringUtils.equals("10", status) && userInfo.getRoleId() == 9)) {
            // 评审PD
            String iderpd = request.getParameter("iderpd");
            SendGoodsEntity sge = new SendGoodsEntity();
            // 备货单号
            sge.setId(request.getParameter("id"));
            // 产品分类ID
            sge.setProductTypeId(Integer.parseInt(request.getParameter("productTypeId")));
            if (userInfo.getRoleId() == 9) {
                sge.setAreaMajId(userInfo.getId());
                sge.setAreaMajName(userInfo.getName());
                sge.setAreaMajIdea(request.getParameter("AreaMajIdea"));
                sge.setAreaMajText(request.getParameter("textarea"));
                sge.setAreaMajDate(Util.getDate());
                if (Integer.parseInt(iderpd) == 1) {
                    sge.setStatus(6);
                } else {
                    sge.setStatus(11);
                }
            }
            if (userInfo.getRoleId() == 5) {
                sge.setProMajId(userInfo.getId());
                sge.setProMajName(userInfo.getName());
                sge.setProMajIdea(request.getParameter("proMajIder"));
                sge.setProMajText(request.getParameter("textarea"));
                sge.setProMajDate(Util.getDate());
                if (Integer.parseInt(iderpd) == 1) {
                    sge.setStatus(8);
                } else {
                    sge.setStatus(7);
                }
            }
            log.debug("区域总监:" + sge.getAreaMajId() + "销售经理:" + sge.getProMajId()
                    + "评审状态:" + sge.getStatus() + "用户权限:" + userInfo.getRoleId());
            ISendGoodsService sendGoodsService = (ISendGoodsService) this
                    .getBean("sendGoodsServiceImpl");
            boolean result = sendGoodsService.modifySendStockingAssessment(sge, iderpd);
            if (result == false) {
                log.info("User:{},备货单评审 失败", userInfo.getId());
            } else {
                log.info("User:{},备货单评审 成功", userInfo.getId());
            }
            response.getWriter().print(result);
        } else {
            log.info("User:{},备货单评审 失败{}", userInfo.getId(), status);
            response.getWriter().print(false);
        }

        return null;
    }

    /**
     * 备货单查看
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward sendgoosViewPrepareView(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("备货查看页");
        String sendid = request.getParameter("id");
        if (StringUtils.isEmpty(sendid)) {
            request.setAttribute("err", "备货单不存在！");
            return mapping.findForward(Constants.FAIL);
        }
        ISendGoodsService sendGoodsService = (ISendGoodsService) this
                .getBean("sendGoodsServiceImpl");
        SendGoodInfoDto sge = sendGoodsService.getReservegoodsView(sendid);
        List<SendgoodSauditPrepareDto> list = sendGoodsService
                .selectSauditPrepareView(sge);
        Util util = new Util();
        Integer[] str = null;
        if (StringUtils.isEmpty(sge.getSendProMajIdea())) {
            log.debug("评审意见为空！");
        } else {
            str = util.splitIdea(sge.getSendProMajIdea());
        }
        request.setAttribute("ProMajIder", str);
        Integer[] str1 = null;
        if (StringUtils.isEmpty(sge.getSendAreaMajIdea())) {
            log.debug("评审意见为空！");
        } else {
            str1 = util.splitIdea(sge.getSendAreaMajIdea());
        }
        request.setAttribute("AreaMajId", str1);
        request.setAttribute("sge", sge);
        request.setAttribute("list", list);
        return mapping.findForward(Constants.SUCCESS);
    }

    public ActionForward getBoxIdBySendGoodId(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("根据发货单号获取装箱单号！");
        String sendGoodId = request.getParameter("sendGoodId");
        ISendGoodsService sendGoodsService = (ISendGoodsService) this
                .getBean("sendGoodsServiceImpl");
        String boxId = sendGoodsService.getBoxIdBySendGoodId(sendGoodId);
        response.getWriter().print(boxId);
        return null;
    }
}