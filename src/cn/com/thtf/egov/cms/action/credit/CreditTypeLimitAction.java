/**
 * ClassName  CreditTypeAction
 *
 * History
 * Create User: zhaolei
 * Create Date: 2010-1-11
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.action.credit;

import java.io.PrintWriter;
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
import cn.com.thtf.egov.cms.dto.CreditTypeLimitDto;
import cn.com.thtf.egov.cms.entity.CustomerCreditEntity;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.iservice.common.ICommonService;
import cn.com.thtf.egov.cms.iservice.credit.ICreditService;

/**
 * @author zhaolei 信用类型额度
 */
public class CreditTypeLimitAction extends NewBaseAction {

    private static Logger log = LoggerFactory.getLogger(CreditTypeLimitAction.class);
    private ICommonService commonService = null;

    /** 查看所有产品分类信用额度 显示列表页 */
    @SuppressWarnings("unchecked")
    public ActionForward findAllProdAssortCreditLimit(ActionMapping mapping,
            ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},信用类型额度列表【开始】", user.getId());

        ICreditService creditService = (ICreditService) this
                .getBean("creditTypeServiceImp");

        // 分頁
        NewPage newPage = getNewPage(request);
        newPage.setUrl("creditTypeLimit.do");

        String userId = user.getId();
        CreditTypeLimitDto creditTypeLimitDto = new CreditTypeLimitDto();

        List list = null;
        // 进行权限判断
        if (user.getRoleId() == Constants.ROLE_CREDIT_CHARGE
                || user.getRoleId() == Constants.ROLE_AST_DIRECTOR_OF_OPERATIONS
                || user.getRoleId() == Constants.ROLE_DIRECTOR_OF_OPERATIONS
                || user.getRoleId() == Constants.ROLE_GENERAL_MANAGER) {
            // 权限判断 信用主管 运营总监助理、运营总监、总经理
            // 列表
            list = creditService.findAllProdAssortCreditLimit(newPage, null);
        } else {
            creditTypeLimitDto.setUserId(userId);
            list = creditService
                    .findAllProdAssortCreditLimit(newPage, creditTypeLimitDto);
        }

        request.setAttribute("roleId", user.getRoleId());
        request.setAttribute("NewPage", newPage);
        request.setAttribute("creditlimit", list);
        log.info("User:{},信用类型额度列表【结束】", user.getId());
        return mapping.findForward(Constants.SUCCESS);
    }

    /** 查看对应产品分类，对应信用类型的信用额度 用于修改额度 返回到修改页面 */
    public ActionForward findProdAssortCreditLimitById(ActionMapping mapping,
            ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        this.saveToken(request);
        ICreditService creditService = (ICreditService) this
                .getBean("creditTypeServiceImp");

        // 取得信用类型ID
        String creditTypeId = request.getParameter("creditTypeId");

        // 取得产品类型ID
        String productTypeId = request.getParameter("productTypeId");

        // 新建实体对象
        CreditTypeLimitDto creditTypeLimitEntity = new CreditTypeLimitDto();
        creditTypeLimitEntity.setProdId(Integer.parseInt(productTypeId));
        creditTypeLimitEntity.setCrdttpId(Integer.parseInt(creditTypeId));

        // 取得页面数据
        CreditTypeLimitDto creditTypeLimit = creditService
                .findProdCreditLimitById(creditTypeLimitEntity);

        request.setAttribute("creditTypeLimit", creditTypeLimit);
        request.setAttribute("creditTypeId", creditTypeId);
        request.setAttribute("productTypeId", productTypeId);
        return mapping.findForward(Constants.SUCCESS);
    }

    /** 用于修改信用额度 */
    public ActionForward updateProdAssortCreditLimit(ActionMapping mapping,
            ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // 获取当前登陆用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},修改产品分类信用额度", user.getId());
        /* 防止重复提交 */
        if (this.isTokenValid(request)) {
            this.resetToken(request);
        } else {
            log.info("User:{},修改失败：请勿重复提交", user.getId());
            request.setAttribute("msg", "修改失败：请勿重复提交");
            return mapping.findForward(Constants.FAIL);
        }
        boolean isSuccess = false;
        ICreditService creditService = (ICreditService) this
                .getBean("creditTypeServiceImp");

        // 取得页面修改的数据值
        String creditLimit = request.getParameter("creditlimit.climit");
        String creditTypeId = request.getParameter("creditTypeId");
        String productTypeId = request.getParameter("productTypeId");
        String timeStamp = request.getParameter("timeStamp");

        // 新建实体对象
        CreditTypeLimitDto creditTypeLimitEntity = new CreditTypeLimitDto();
        creditTypeLimitEntity.setProdId(Integer.parseInt(productTypeId));
        creditTypeLimitEntity.setCrdttpId(Integer.parseInt(creditTypeId));
        creditTypeLimitEntity.setTotalLimit(creditLimit);
        creditTypeLimitEntity.setTimeStamp(timeStamp);

        // 进行更新操作
        isSuccess = creditService.updateCreditTypeLimit(creditTypeLimitEntity);
        if (isSuccess) {
            return mapping.findForward(Constants.SUCCESS);
        } else {
            log.info("User:{},修改失败：信用额度已被其他用户修改", user.getId());
            request.setAttribute("msg", "修改失败：信用额度已被其他用户修改");
            return mapping.findForward(Constants.FAIL);
        }
    }

    /** 添加产品分类信用额度 */
    public ActionForward insertNewProdAssortCreditTypeLimit(ActionMapping mapping,
            ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // 获取当前登陆用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},添加产品分类信用额度", user.getId());

        // 取得页面的数据值
        String creditLimit = request.getParameter("creditlimit.climit");
        String creditTypeId = request.getParameter("creditTypeId");
        String productTypeId = request.getParameter("productTypeId");
        // 传回页面，重新获取数据
        request.setAttribute("productTypeId", productTypeId);
        request.setAttribute("creditTypeId", creditTypeId);
        request.setAttribute("climit", creditLimit);
        /* 防止重复提交 */
        if (this.isTokenValid(request)) {
            this.resetToken(request);
        } else {
            log.info("User:{},添加失败：请勿重复提交", user.getId());
            request.setAttribute("msg", "添加失败：请勿重复提交");
            return mapping.findForward(Constants.FAIL);
        }

        ICreditService creditService = (ICreditService) this
                .getBean("creditTypeServiceImp");
        // 新建实体对象
        CreditTypeLimitDto creditTypeLimit = new CreditTypeLimitDto();
        creditTypeLimit.setProdId(Integer.parseInt(productTypeId));
        creditTypeLimit.setCrdttpId(Integer.parseInt(creditTypeId));
        creditTypeLimit.setTotalLimit(creditLimit);

        // 判断取得的页面数据与从数据库里查到的“可分配额度”的大小
        String freeLimit = creditService.findProdAssortCreditLimitById(Integer
                .parseInt(productTypeId));

        if (Double.parseDouble(creditLimit) <= Double.parseDouble(freeLimit)) {
            // 判断是否存在相同的记录
            boolean isExist = creditService.findCreditTypeByCondition(creditTypeLimit);

            if (!isExist) {
                boolean isSuccess = creditService
                        .insertProdAssortCreditTypeLimit(creditTypeLimit);
                if (isSuccess) {
                    return mapping.findForward(Constants.SUCCESS);
                } else {
                    log.info("User:{},产品分类信用额度添加失败", user.getId());
                    request.setAttribute("msg", "产品分类信用额度添加失败");
                    return mapping.findForward(Constants.FAIL);
                }
            } else {
                log.info("User:{},产品分类信用额度添加失败:数据库中存在相同的记录", user.getId());
                request.setAttribute("msg", "添加失败：数据库中存在相同的记录 ");
                return mapping.findForward(Constants.FAIL);
            }
        } else {
            log.info("User:{},产品分类信用额度添加失败:信用额度应大于或等于可分配额度", user.getId());
            request.setAttribute("msg", "添加失败:信用额度应大于或等于可分配额度");
            return mapping.findForward(Constants.FAIL);
        }
    }

    /** Ajax调用 点击产品分类时取得"可分配额度" */
    public ActionForward findCanAssignClimit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        PrintWriter writer = response.getWriter();

        ICreditService creditService = (ICreditService) this
                .getBean("creditTypeServiceImp");

        // 取得页面传过来的数据
        String productTypeId = request.getParameter("productTypeId");
        CreditTypeLimitDto creditTypeLimit = new CreditTypeLimitDto();
        creditTypeLimit.setProdId(Integer.parseInt(productTypeId));

        // 获得可分配额度
        String canAssignLimit = creditService.findProdAssortCreditLimitById(Integer
                .parseInt(productTypeId));

        // 返回可分配额度
        writer.print(canAssignLimit);
        writer.close();
        return null;
    }

    /** 删除产品分类信用额度 */
    public ActionForward deleteProdAssortCreditTypeLimit(ActionMapping mapping,
            ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 获取当前登陆用户
        UserEntity user = getLoginUserInfo(request);
        ICreditService creditService = (ICreditService) this
                .getBean("creditTypeServiceImp");

        // 取得页面传过来的数据
        String creditTypeId = request.getParameter("creditTypeId");
        String productTypeId = request.getParameter("productTypeId");
        String productTypeName = request.getParameter("productTypeName");
        String creditTypeName = request.getParameter("creditTypeName");

        // 
        CreditTypeLimitDto creditTypeLimit = new CreditTypeLimitDto();
        creditTypeLimit.setProdId(Integer.parseInt(productTypeId));
        creditTypeLimit.setCrdttpId(Integer.parseInt(creditTypeId));

        // 查询对应下的信用类型额度表的已分配额度是否为0
        CustomerCreditEntity customerCredit = new CustomerCreditEntity();
        customerCredit.setProductTypeId(Integer.parseInt(productTypeId));
        customerCredit.setCreditTypeId(Integer.parseInt(creditTypeId));
        String customAssignLimit = creditService.findCustomerAssignLimit(customerCredit);
        if ("0".equals(customAssignLimit) || customAssignLimit == null) {// 可以删除

            boolean isSuccess = creditService
                    .deleteProdAssortCreditTypeLimit(creditTypeLimit);
            if (isSuccess) {
                return mapping.findForward(Constants.SUCCESS);
            } else {
                log.info("User:{},类信用额度删除失败", user.getId());
                request.setAttribute("msg", "类信用额度删除失败");
                return mapping.findForward(Constants.FAIL);
            }
        } else {
            log.info("User:{},类信用额度删除失败: " + productTypeName + "\\" + creditTypeName
                    + " 已被使用", user.getId());
            request.setAttribute("msg", "删除失败： " + productTypeName + "\\"
                    + creditTypeName + " 已被使用");
            return mapping.findForward(Constants.FAIL);
        }
    }

    /** 进入"产品分类信用额度添加"页面 查找所有产品分类 */
    @SuppressWarnings("unchecked")
    public ActionForward findAllProdType(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 获取当前登陆用户
        UserEntity user = getLoginUserInfo(request);
        log.info("User:{},查找产品分类", user.getId());
        this.saveToken(request);

        ICreditService creditService = (ICreditService) this
                .getBean("creditTypeServiceImp");

        /* 产品分类 */
        commonService = (ICommonService) getBean("commonServiceImpl");
        List list = null;
        if (user.getRoleId() == Constants.ROLE_CREDIT_CHARGE) {// 信用主管不受产品分类限制
            list = commonService.getAllProductTypes(null, null, null);
        } else {
            list = commonService.getAllProductTypes(null, user.getId(), user.getRoleId()
                    + "");
        }
        request.setAttribute("prodTypeList", list);

        // 取得所有的信用类型
        List creditTypeList = creditService.findAllCreditType();
        request.setAttribute("creditTypeList", creditTypeList);
        return mapping.findForward(Constants.SUCCESS);
    }
}
