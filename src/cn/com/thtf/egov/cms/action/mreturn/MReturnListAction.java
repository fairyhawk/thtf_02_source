/**
 * ClassName  MReturnListAction
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-5-13
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.action.mreturn;

import java.math.BigDecimal;
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
import cn.com.thtf.egov.cms.constant.Constants;
import cn.com.thtf.egov.cms.dto.AppointSellDetailDto;
import cn.com.thtf.egov.cms.dto.AppointSendGoodsDetailDto;
import cn.com.thtf.egov.cms.dto.MreturnAppointDto;
import cn.com.thtf.egov.cms.dto.MreturnDto;
import cn.com.thtf.egov.cms.entity.ProductTypeEntity;
import cn.com.thtf.egov.cms.entity.ReturnDetailEntity;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.form.MReturnListForm;
import cn.com.thtf.egov.cms.iservice.mreturn.IMReturnService;
import cn.com.thtf.egov.cms.util.Container;

/**
 * 回款列表
 * 
 * @author Lubo
 */
public class MReturnListAction extends NewBaseAction {

    /** log */
    private static Logger log = LoggerFactory.getLogger(MReturnListAction.class);
    /** IMReturnService */
    private IMReturnService mReturnService;

    /**
     * 回款管理列表页
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public ActionForward getMReturnList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("回款管理列表页！");
        /* 检索form */
        MReturnListForm mReturnListForm = (MReturnListForm) form;
        /* 获取bean容器 */
        mReturnService = (IMReturnService) Container.getBean("mReturnServiceImp");
        /* 获取当前登陆用户 */
        UserEntity user = (UserEntity) request.getSession().getAttribute(
                Constants.USERLOGIN);
        /* 注入检索权限 */
        mReturnListForm.getmReturnListQueryDto().setNowUserId(user.getId());
        mReturnListForm.getmReturnListQueryDto().setUserRole(user.getRoleId());
        mReturnListForm.getmReturnListQueryDto().setNowUserAreaId(user.getUserAreaId());
        /* 翻页 */
        NewPage newPage = getNewPage(request);
        /* 翻页url */
        newPage.setUrl("mreturn.do");
        /* 翻页传参 */
        pageParam(newPage, mReturnListForm);
        /* 回款信息 */
        List list = mReturnService.getMReturnList(newPage,
                mReturnListForm.getmReturnListQueryDto(), user);
        /* 产品分类 */
        List<ProductTypeEntity> producttypelist = mReturnService.getAllProductType();
        request.setAttribute("roleId", user.getRoleId());
        request.setAttribute("mReturnListForm", mReturnListForm);
        request.setAttribute("producttypelist", producttypelist);
        request.setAttribute("list", list);
        request.setAttribute("NewPage", newPage);
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 回款删除
     * 
     * @author LiXS
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping
     * @throws Exception
     */
    public ActionForward deldteMReturn(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("进入回款删除！");
        String mrid = request.getParameter("id");
        UserEntity userInfo = getLoginUserInfo(request);
        log.info("User:{},回款删除:{}", userInfo.getId(), mrid);
        /* 获取bean容器 */
        mReturnService = (IMReturnService) Container.getBean("mReturnServiceImp");
        /* 执行删除 */
        Integer result = mReturnService.removeMReturn(mrid, userInfo);
        if (result == 1) {
            log.info("User:{},回款删除 成功", userInfo.getId());
        } else {
            log.info("User:{},回款删除 失败", userInfo.getId());
        }
        response.getWriter().print(result);
        return null;
    }

    /**
     * 回款查看
     * 
     * @author LiXS
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping
     * @throws Exception
     */
    public ActionForward getMReturn(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("进入回款查看！");
        String mrid = request.getParameter("id");
        mReturnService = (IMReturnService) Container.getBean("mReturnServiceImp");
        MreturnDto mreturnDto = mReturnService.getMReturnById(mrid);
        MreturnAppointDto para = new MreturnAppointDto();
        para.setMreturnId(mrid);

        /* 检索已指定的合同和发货单 */
        List<AppointSellDetailDto> sellList = mReturnService.getSellContractDetail(para,
                null);
        List<AppointSendGoodsDetailDto> sendGoodsList = mReturnService
                .getSendGoodsDetail(para, null);

        ReturnDetailEntity rde = new ReturnDetailEntity();
        rde.setReturnId(mrid);
        /* 产品信息指定金额 */
        rde.setAppointType(4);
        BigDecimal productMoney = mReturnService.sumMoney(rde);
        /* 合同信息指定金额 */
        rde.setAppointType(2);
        BigDecimal sellContractMoney = mReturnService.sumMoney(rde);
        /* 剩余额度 */
        BigDecimal symoney = ((mreturnDto.getMoney().subtract(mreturnDto.getBackMoney()))
                .subtract(productMoney)).subtract(sellContractMoney);

        request.setAttribute("symoney", symoney);
        request.setAttribute("sellContractMoney", sellContractMoney);
        request.setAttribute("productMoney", productMoney);
        request.setAttribute("sendGoodsList", sendGoodsList);
        request.setAttribute("sellList", sellList);
        request.setAttribute("mreturnDto", mreturnDto);
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 抽象翻页检索传参
     * 
     * @param newPage
     * @param mReturnListForm
     */
    private void pageParam(NewPage newPage, MReturnListForm mReturnListForm) {
        newPage.setQuery("mReturnListQueryDto.id", mReturnListForm
                .getmReturnListQueryDto().getId());
        newPage.setQuery("mReturnListQueryDto.no", mReturnListForm
                .getmReturnListQueryDto().getNo());
        newPage.setQuery("mReturnListQueryDto.ptoductTypeId", mReturnListForm
                .getmReturnListQueryDto().getPtoductTypeId());
        newPage.setQuery("mReturnListQueryDto.customerName", mReturnListForm
                .getmReturnListQueryDto().getCustomerName());
        newPage.setQuery("mReturnListQueryDto.money", mReturnListForm
                .getmReturnListQueryDto().getMoney());
        newPage.setQuery("mReturnListQueryDto.returnWay", mReturnListForm
                .getmReturnListQueryDto().getReturnWay());
        newPage.setQuery("mReturnListQueryDto.startDate", mReturnListForm
                .getmReturnListQueryDto().getStartDate());
        newPage.setQuery("mReturnListQueryDto.endDate", mReturnListForm
                .getmReturnListQueryDto().getEndDate());
        newPage.setQuery("mReturnListQueryDto.startReturnDate", mReturnListForm
                .getmReturnListQueryDto().getStartReturnDate());
        newPage.setQuery("mReturnListQueryDto.endReturnDate", mReturnListForm
                .getmReturnListQueryDto().getEndReturnDate());
        newPage.setQuery("mReturnListQueryDto.number", mReturnListForm
                .getmReturnListQueryDto().getNumber());
        newPage.setQuery("mReturnListQueryDto.userName", mReturnListForm
                .getmReturnListQueryDto().getUserName());
        newPage.setQuery("mReturnListQueryDto.returnType0", mReturnListForm
                .getmReturnListQueryDto().getReturnType0());
        newPage.setQuery("mReturnListQueryDto.returnType1", mReturnListForm
                .getmReturnListQueryDto().getReturnType1());
        newPage.setQuery("mReturnListQueryDto.returnType2", mReturnListForm
                .getmReturnListQueryDto().getReturnType2());
        newPage.setQuery("mReturnListQueryDto.productContractCode", mReturnListForm
                .getmReturnListQueryDto().getProductContractCode());
        newPage.setQuery("mReturnListQueryDto.sendGoodId", mReturnListForm
                .getmReturnListQueryDto().getSendGoodId());
    }

}
