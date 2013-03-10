/**
 * ClassName  SalesContractListAction
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-4-12
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
import cn.com.thtf.egov.cms.dto.SalesContractQueryDto;
import cn.com.thtf.egov.cms.dto.SalesContractResultDto;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.form.SalesContractListForm;
import cn.com.thtf.egov.cms.iservice.common.ICommonService;
import cn.com.thtf.egov.cms.iservice.sell.ISalesContractManagementService;

/**
 * 销售合同列表
 * 
 * @author Lubo
 */
public class SalesContractListAction extends NewBaseAction {

    /** log */
    private static Logger log = LoggerFactory.getLogger(SalesContractListAction.class);
    /** ISalesContractManagementService */
    private ISalesContractManagementService salesContractService = null;
    private ICommonService commonService = null;

    /**
     * 销售合同检索列表
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public ActionForward getSalesContractsList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 当前登录用户
        UserEntity user = getLoginUserInfo(request);

        /* 产品分类 */
        commonService = (ICommonService) getBean("commonServiceImpl");
        List list = commonService.getAllProductTypes(null, user.getId(), user.getRoleId()
                + "");
        request.setAttribute("productTypeList", list);

        /* 检索 */
        SalesContractListForm salesForm = (SalesContractListForm) form;
        SalesContractQueryDto para = setQueryPara(salesForm);

        para.setUser(user);
        String initStr = request.getParameter("init");
        para.setInit(initStr);

        /* 封装翻页参数 */
        NewPage newPage = getNewPage(request);
        newPage.setUrl("getSalesContractsList.do");
        setPageQueryPara(para, newPage);
        salesContractService = (ISalesContractManagementService) getBean("salesContractServiceImp");
        List<SalesContractResultDto> resultList = (List<SalesContractResultDto>) salesContractService
                .getSalesContractList(para, newPage);
        if (resultList == null) {
            log.error("User:{},销售合同检索列表【失败】", user.getId());
        } else {
            for (SalesContractResultDto salesContractResultDto : resultList) {
                salesContractService.getSalesContractListDetail(salesContractResultDto);
            }
        }

        request.setAttribute("queryPara", para);
        request.setAttribute("user", user);
        request.setAttribute("NewPage", newPage);
        request.setAttribute("sellContractList", resultList);
        return mapping.findForward("salesContractList");
    }

    /**
     * 封装查询参数
     * 
     * @param salesForm
     * @return
     */
    private SalesContractQueryDto setQueryPara(SalesContractListForm form) {
        SalesContractQueryDto dto = new SalesContractQueryDto();
        if (StringUtils.isNotEmpty(form.getProductTypeId())) {
            dto.setProductTypeId(form.getProductTypeId().trim());
        }
        if (StringUtils.isNotEmpty(form.getProductContractCode())) {
            dto.setProductContractCode(form.getProductContractCode().trim());
        }
        if (StringUtils.isNotEmpty(form.getCompanyContarctCode())) {
            dto.setCompanyContractCode(form.getCompanyContarctCode().trim());
        }
        if (StringUtils.isNotEmpty(form.getCustomerName())) {
            dto.setCustomerName(form.getCustomerName().trim());
        }
        if (StringUtils.isNotEmpty(form.getStampType())) {
            dto.setStampType(form.getStampType().trim());
        }
        if (StringUtils.isNotEmpty(form.getTemplateType())) {
            dto.setTemplateType(form.getTemplateType().trim());
        }
        if (StringUtils.isNotEmpty(form.getPaymentWay())) {
            dto.setPaymentWay(form.getPaymentWay().trim());
        }
        if (StringUtils.isNotEmpty(form.getUserName())) {
            dto.setUserName(form.getUserName().trim());
        }
        if (StringUtils.isNotEmpty(form.getStarttime())) {
            dto.setStarttime(form.getStarttime().trim());
        }
        if (StringUtils.isNotEmpty(form.getEndtime())) {
            dto.setEndtime(form.getEndtime().trim());
        }
        if (StringUtils.isNotEmpty(form.getStatus())) {
            dto.setStatus(form.getStatus().trim());
        }
        if (StringUtils.isNotEmpty(form.getContractProName())) {
            dto.setContractProName(form.getContractProName().trim());
        }

        dto.setUnfinished(form.getUnfinished());

        return dto;
    }

    /**
     * 封装翻页参数
     * 
     * @param salesForm
     */
    private void setPageQueryPara(SalesContractQueryDto salesDto, NewPage newPage) {
        newPage.setQuery("productTypeId", salesDto.getProductTypeId());
        newPage.setQuery("productContractCode", salesDto.getProductContractCode());
        newPage.setQuery("companyContarctCode", salesDto.getCompanyContractCode());
        newPage.setQuery("customerName", salesDto.getCustomerName());
        newPage.setQuery("stampType", salesDto.getStampType());
        newPage.setQuery("templateType", salesDto.getTemplateType());
        newPage.setQuery("paymentWay", salesDto.getPaymentWay());
        newPage.setQuery("userName", salesDto.getUserName());
        newPage.setQuery("starttime", salesDto.getStarttime());
        newPage.setQuery("endtime", salesDto.getEndtime());
        newPage.setQuery("status", salesDto.getStatus());
        newPage.setQuery("init", salesDto.getInit());
        newPage.setQuery("unfinished", String.valueOf(salesDto.getUnfinished()));
        newPage.setQuery("contractProName", salesDto.getContractProName());
    }
}
