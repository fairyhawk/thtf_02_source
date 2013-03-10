/**
 * ClassName  MReturnAddAction
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

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.NewBaseAction;
import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.constant.Constants;
import cn.com.thtf.egov.cms.dto.MReturnInfoDto;
import cn.com.thtf.egov.cms.dto.MreturnArriveInfoDto;
import cn.com.thtf.egov.cms.entity.MreturnEntity;
import cn.com.thtf.egov.cms.entity.ProductTypeEntity;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.form.MReturnCustomerForm;
import cn.com.thtf.egov.cms.form.MreturnForm;
import cn.com.thtf.egov.cms.iservice.common.ICommonService;
import cn.com.thtf.egov.cms.iservice.mreturn.IMReturnService;
import cn.com.thtf.egov.cms.util.Container;
import cn.com.thtf.egov.cms.util.Util;

/**
 * 新增回款
 * 
 * @author Lubo
 */
public class MReturnAddAction extends NewBaseAction {

    /** log */
    private static Logger log = LoggerFactory.getLogger(MReturnAppointAction.class);
    /** IMReturnService */
    private IMReturnService mReturnService;
    /** ICommonService */
    private ICommonService commonService;

    /**
     * 回款创建初始化
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward createMReturnInit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("创建回款单初始化！");
        /* 获取bean容器 */
        mReturnService = (IMReturnService) Container.getBean("mReturnServiceImp");
        /* 获取当前登陆用户 */
        UserEntity user = getLoginUserInfo(request);
        String userId = user.getId();
        /* 产品分类 */
        List<ProductTypeEntity> producttypelist = mReturnService
                .getAllProductTypeByUserId(userId);

        request.setAttribute("producttypelist", producttypelist);
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 客户选择小页面
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public ActionForward selectCustomer(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("客户选择小页面");
        /* 检索form */
        MReturnCustomerForm mReturnListForm = (MReturnCustomerForm) form;
        /* 获取bean容器 */
        mReturnService = (IMReturnService) Container.getBean("mReturnServiceImp");
        /* 翻页 */
        NewPage newPage = getNewPage(request);
        /* 翻页url */
        newPage.setUrl("getMReturnCustomerSelect.do");
        newPage.setQuery("customerE.name", mReturnListForm.getCustomerE().getName());
        List list = mReturnService.getMReturnCustomer(newPage, mReturnListForm
                .getCustomerE());

        request.setAttribute("mReturnListForm", mReturnListForm);
        request.setAttribute("NewPage", newPage);
        request.setAttribute("list", list);
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 回款初始化页
     * 
     * @author LiXS
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping
     * @throws Exception
     */
    public ActionForward arriveMReturn(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        String mrid = request.getParameter("id");
        mReturnService = (IMReturnService) this.getBean("mReturnServiceImp");
        MReturnInfoDto mid = mReturnService.getArriveInitialization(mrid);
        request.setAttribute("mid", mid);
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 到账-回款
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward mewarriveMReturn(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        MreturnForm mf = (MreturnForm) form;
        UserEntity userInfo = getLoginUserInfo(request);
        log.info("User:{},回款到帐:{}", userInfo.getId(), mf.getmReturnEntity().getId());
        mReturnService = (IMReturnService) this.getBean("mReturnServiceImp");
        /* 回款表时间戳+客户表时间戳等信息 */
        List<MreturnArriveInfoDto> maid = mReturnService.selectarriveinfodto(mf
                .getmReturnEntity().getId());
        // if (StringUtils.isEmpty(maid.getReturntimestamp())) {
        // /* 回款表时间戳 */
         String mtime = mReturnService.selectMreturnTimesTamp(mf
         .getmReturnEntity().getId());
         mf.getmReturnEntity().setTimestamp(mtime);
        // }
        /* 客户表时间戳 */
        // String customerTimestamp =
        // mReturnService.selectCustomeerTimesTamp(mf.getmReturnEntity().getId());
        /* 更新回款表 */
        boolean result = mReturnService.mewarriveMReturn(mf.getmReturnEntity(), maid);
        if (result == false) {
            log.info("User:{},到帐 失败", userInfo.getId());
        } else {
            log.info("User:{},到帐 成功", userInfo.getId());
        }
        response.getWriter().print(result);
        return null;
    }

    /**
     * 回款创建
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward createMReturn(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("回款创建！");
        UserEntity userInfo = getLoginUserInfo(request);
        log.info("User:{},回款 创建", userInfo.getId());
        // 封装回款信息
        MreturnEntity mReturnE = new MreturnEntity();
        /* 生成回款ID */
        commonService = (ICommonService) getBean("commonServiceImpl");
        String id = commonService.getSerialNumber("HK", "mreturn");
        mReturnE.setId(id);
        mReturnE.setReturnType(Integer.parseInt(request.getParameter("returnType")));
        mReturnE.setNo(request.getParameter("no"));
        if (StringUtils.isNotEmpty(request.getParameter("returnWay"))) {
            mReturnE.setReturnWay(Integer.parseInt(request.getParameter("returnWay")));
        }
        mReturnE.setNumber(request.getParameter("number"));
        if (StringUtils.isNotEmpty(request.getParameter("ptoductTypeId"))) {
            mReturnE.setProductTypeId(Integer.parseInt(request
                    .getParameter("ptoductTypeId")));
        }
        if (StringUtils.isNotEmpty(request.getParameter("customerId"))) {
            mReturnE.setCustomerId(Integer.parseInt(request.getParameter("customerId")));
        }
        mReturnE.setCustomerName(request.getParameter("customerName"));
        mReturnE.setReturnDate(request.getParameter("returnDate"));
        if (StringUtils.isNotEmpty(request.getParameter("money"))) {
            mReturnE.setMoney(new BigDecimal(request.getParameter("money")));
        }
        mReturnE.setText(request.getParameter("text"));
        mReturnE.setDate(Util.getDate());
        mReturnE.setUserAreaId(userInfo.getUserAreaId());
        mReturnE.setUserId(userInfo.getId());
        mReturnE.setUserName(userInfo.getName());
        mReturnService = (IMReturnService) this.getBean("mReturnServiceImp");
        boolean result = mReturnService.createMReturn(mReturnE);
        if (result == false) {
            log.info("User:{},回款 创建失败", userInfo.getId());
        } else {
            log.info("User:{},回款 创建成功", userInfo.getId());
        }
        response.getWriter().print(result + "," + id);
        return null;
    }

}
