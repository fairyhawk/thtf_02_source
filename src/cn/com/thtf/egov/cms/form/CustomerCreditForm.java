package cn.com.thtf.egov.cms.form;

import java.sql.Timestamp;

import org.apache.struts.action.ActionForm;

public class CustomerCreditForm extends ActionForm {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Integer id; // 客户信用id

    private Integer productTypeId; // 产品类型id

    private Integer creditTypeId; // 信用类型id

    private Integer customerId; // 客户id

    private Double distributeCredit; // 可分配额度

    private String customerName; // 客户名

    private String productTypeName; // 产品分类名称

    private String projectName; // 项目名称

    private Integer arterm; // 帐期

    private Double creditLimit; // 信用额度

    private Double lockCredit; // 冻结额度

    private Timestamp timeStamp; // 时间戳

    private Integer enable; // 状态

    private String agreementCode;// 协议合同编号

    private String text;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getTimeStamp() {

        return timeStamp;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public Double getDistributeCredit() {
        return distributeCredit;
    }

    public void setDistributeCredit(Double distributeCredit) {
        this.distributeCredit = distributeCredit;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    public Integer getCreditTypeId() {
        return creditTypeId;
    }

    public void setCreditTypeId(Integer creditTypeId) {
        this.creditTypeId = creditTypeId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getArterm() {
        return arterm;
    }

    public void setArterm(Integer arterm) {
        this.arterm = arterm;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Double getLockCredit() {
        return lockCredit;
    }

    public void setLockCredit(Double lockCredit) {
        this.lockCredit = lockCredit;
    }

    public String getAgreementCode() {
        return agreementCode;
    }

    public void setAgreementCode(String agreementCode) {
        this.agreementCode = agreementCode;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
