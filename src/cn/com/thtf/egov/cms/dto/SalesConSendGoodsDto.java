/**
 * ClassName  ProdCreditLimit
 *
 * History
 * Create User: ChenHuajiang
 * Create Date: 2010-4-8
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

/**
 * 新建销售合同的发货信息的DTO
 * 
 * @author shensi
 */

public class SalesConSendGoodsDto {

    private String customerId;// 客户ID

    private String customerName;// 客户名称

    private String proTypeId;// 产品分类ID

    private String proTypeName;// 产品分类名称

    private String salesConTotalMoney;// 合同总金额

    private String creditTypeId;// 信用类型ID

    private String creditTypeName;// 信用类型名称

    private String projectName;// 项目名称

    private String arterm;// 账期

    private String cusClimit;// 信用额度

    private String useableLimit;// 可用金额

    private String paymentWay;// 付款方式

    private String cashMoney;// 现结金额

    private String batchWay;// 分批方式

    private String batchMaxMoney;// 分批最大 金额
    
    private String free;//可用信用

    public String getFree() {
        return free;
    }

    public void setFree(String free) {
        this.free = free;
    }

    public String getCreditTypeId() {
        return creditTypeId;
    }

    public void setCreditTypeId(String creditTypeId) {
        this.creditTypeId = creditTypeId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProTypeId() {
        return proTypeId;
    }

    public void setProTypeId(String proTypeId) {
        this.proTypeId = proTypeId;
    }

    public String getProTypeName() {
        return proTypeName;
    }

    public void setProTypeName(String proTypeName) {
        this.proTypeName = proTypeName;
    }

    public String getSalesConTotalMoney() {
        return salesConTotalMoney;
    }

    public void setSalesConTotalMoney(String salesConTotalMoney) {
        this.salesConTotalMoney = salesConTotalMoney;
    }

    public String getCreditTypeName() {
        return creditTypeName;
    }

    public void setCreditTypeName(String creditTypeName) {
        this.creditTypeName = creditTypeName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getArterm() {
        return arterm;
    }

    public void setArterm(String arterm) {
        this.arterm = arterm;
    }

    public String getCusClimit() {
        return cusClimit;
    }

    public void setCusClimit(String cusClimit) {
        this.cusClimit = cusClimit;
    }

    public String getUseableLimit() {
        return useableLimit;
    }

    public void setUseableLimit(String useableLimit) {
        this.useableLimit = useableLimit;
    }

    public String getPaymentWay() {
        return paymentWay;
    }

    public void setPaymentWay(String paymentWay) {
        this.paymentWay = paymentWay;
    }

    public String getCashMoney() {
        return cashMoney;
    }

    public void setCashMoney(String cashMoney) {
        this.cashMoney = cashMoney;
    }

    public String getBatchWay() {
        return batchWay;
    }

    public void setBatchWay(String batchWay) {
        this.batchWay = batchWay;
    }

    public String getBatchMaxMoney() {
        return batchMaxMoney;
    }

    public void setBatchMaxMoney(String batchMaxMoney) {
        this.batchMaxMoney = batchMaxMoney;
    }

}
