/**
 * ClassName  SalesContractListForm
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-4-12
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.form;

import org.apache.struts.action.ActionForm;

/**
 * 销售合同列表
 * 
 * @author Lubo
 */

public class SalesContractListForm extends ActionForm {

    /** 自动生成序列化ID */
    private static final long serialVersionUID = 1L;

    /** 产品分类ID */
    private String productTypeId;
    /** 产品合同号 */
    private String productContractCode;
    /** 公司合同号 */
    private String companyContarctCode;
    /** 客户名称 */
    private String customerName;
    /** 盖章类型 */
    private String stampType;

    /** 模版类型 */
    private String templateType;
    /** 付款方式 */
    private String paymentWay;
    /** 人员名称 */
    private String userName;
    /** 合同签订起始时间 */
    private String starttime;
    /** 合同签订终止时间 */
    private String endtime;
    /** 销售项目名称 */
    private String contractProName;
    /** 发货未完成 */
    private int unfinished;

    /** 合同状态 */
    private String status;

    public int getUnfinished() {
        return unfinished;
    }

    public void setUnfinished(int unfinished) {
        this.unfinished = unfinished;
    }

    public String getContractProName() {
        return contractProName;
    }

    public void setContractProName(String contractProName) {
        this.contractProName = contractProName;
    }

    /**
     * @return the productTypeId
     */
    public String getProductTypeId() {
        return productTypeId;
    }

    /**
     * @param productTypeId
     *            the productTypeId to set
     */
    public void setProductTypeId(String productTypeId) {
        this.productTypeId = productTypeId;
    }

    /**
     * @return the productContractCode
     */
    public String getProductContractCode() {
        return productContractCode;
    }

    /**
     * @param productContractCode
     *            the productContractCode to set
     */
    public void setProductContractCode(String productContractCode) {
        this.productContractCode = productContractCode;
    }

    /**
     * @return the companyContarctCode
     */
    public String getCompanyContarctCode() {
        return companyContarctCode;
    }

    /**
     * @param companyContarctCode
     *            the companyContarctCode to set
     */
    public void setCompanyContarctCode(String companyContarctCode) {
        this.companyContarctCode = companyContarctCode;
    }

    /**
     * @return the customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName
     *            the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return the stampType
     */
    public String getStampType() {
        return stampType;
    }

    /**
     * @param stampType
     *            the stampType to set
     */
    public void setStampType(String stampType) {
        this.stampType = stampType;
    }

    /**
     * @return the templateType
     */
    public String getTemplateType() {
        return templateType;
    }

    /**
     * @param templateType
     *            the templateType to set
     */
    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    /**
     * @return the paymentWay
     */
    public String getPaymentWay() {
        return paymentWay;
    }

    /**
     * @param paymentWay
     *            the paymentWay to set
     */
    public void setPaymentWay(String paymentWay) {
        this.paymentWay = paymentWay;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     *            the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the starttime
     */
    public String getStarttime() {
        return starttime;
    }

    /**
     * @param starttime
     *            the starttime to set
     */
    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    /**
     * @return the endtime
     */
    public String getEndtime() {
        return endtime;
    }

    /**
     * @param endtime
     *            the endtime to set
     */
    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

}
