package cn.com.thtf.egov.cms.action.credit;

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
import cn.com.thtf.egov.cms.constant.Arith;
import cn.com.thtf.egov.cms.constant.Constants;
import cn.com.thtf.egov.cms.dto.ProdCreditLimitDto;
import cn.com.thtf.egov.cms.entity.ProductTypeEntity;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.iservice.credit.ICreditService;

/**
 * 产品分类信用总额度管理
 * 
 * @author shensi
 */
public class CreditTotalLimitAction extends NewBaseAction {
    private static Logger log = LoggerFactory.getLogger(CreditTotalLimitAction.class);

    /**
     * 产品大类信用总额列表页
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public ActionForward findAllProdCreditLimit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 获取spring的service
        ICreditService creditService = (ICreditService) this
                .getBean("creditTypeServiceImp");

        // 获取当前登陆用户
        UserEntity user = getLoginUserInfo(request);
        request.setAttribute("roleId", user.getRoleId());

        // 分頁
        NewPage newPage = getNewPage(request);
        newPage.setUrl("prodCreditLimit.do");
        ProdCreditLimitDto prodCreditLimitDto = new ProdCreditLimitDto();
        // 查看所有产品分类信用总额
        List list = null;
        if (user.getRoleId() == Constants.ROLE_CREDIT_CHARGE
                || user.getRoleId() == Constants.ROLE_AST_DIRECTOR_OF_OPERATIONS
                || user.getRoleId() == Constants.ROLE_DIRECTOR_OF_OPERATIONS
                || user.getRoleId() == Constants.ROLE_GENERAL_MANAGER) {
            // 权限判断 信用主管 运营总监助理、运营总监、总经理
            list = creditService.findAllProdCreditLimit(newPage, null);
        } else {
            // 其它
            prodCreditLimitDto.setUserId(user.getId());
            list = creditService.findAllProdCreditLimit(newPage, prodCreditLimitDto);
        }
        request.setAttribute("NewPage", newPage);
        request.setAttribute("p_clist", list);
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 根据ID查看产品分类信用总额
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward findProdCreditLimitById(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 获取spring的service
        ICreditService creditService = (ICreditService) this
                .getBean("creditTypeServiceImp");
        ProdCreditLimitDto prodCreditLimit = null;

        // 获取参数的值
        String prodId = request.getParameter("prodId");

        // 查看所有产品分类信用总额
        prodCreditLimit = creditService.findProdCreditLimitById(prodId);
        request.setAttribute("pcli", prodCreditLimit);
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 修改产品分类信用总额
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward updateProdCreditLimit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        boolean isSuccess = false;
        // 获取当前登陆用户
        UserEntity user = getLoginUserInfo(request);
        // 获取spring的service
        ICreditService creditService = (ICreditService) this
                .getBean("creditTypeServiceImp");

        ProdCreditLimitDto prodCreditLimit = new ProdCreditLimitDto();
        ProductTypeEntity productType = new ProductTypeEntity();

        // 获取参数
        String totalLimit = request.getParameter("ptClimit");
        productType.setId(Integer.parseInt(request.getParameter("prodId")));
        productType.setClimit(totalLimit);

        // 查看此产品分类的已分配额度
        prodCreditLimit = creditService.findProdCreditLimitById(request
                .getParameter("prodId"));
        String result = Arith.sub(prodCreditLimit.getAssignedLimit(), totalLimit);

        // 信用额度必须大于等于已分配额度
        if (Double.valueOf(result) <= 0) {
            // 修改所有产品分类信用总额
            isSuccess = creditService.updateProdCreditLimit(productType);
            if (isSuccess) {
                return mapping.findForward(Constants.SUCCESS);
            } else {
                log.info("User:{},信用额度修改失败", user.getId());
                request.setAttribute("pcli", prodCreditLimit);
                request.setAttribute("msg", "信用额度修改失败");
                return mapping.findForward(Constants.FAIL);
            }
        } else {
            log.info("User:{},信用额度修改失败：信用额度必须大于等于已分配额度"
                    + prodCreditLimit.getAssignedLimit(), user.getId());
            request.setAttribute("pcli", prodCreditLimit);
            request.setAttribute("msg", "修改失败：信用额度必须大于等于已分配额度"
                    + prodCreditLimit.getAssignedLimit());
            return mapping.findForward(Constants.FAIL);
        }
    }
}
