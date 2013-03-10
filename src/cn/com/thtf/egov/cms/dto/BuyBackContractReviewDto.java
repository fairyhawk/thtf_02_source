package cn.com.thtf.egov.cms.dto;

import java.io.Serializable;

/**
 * 采购退货合同查看评审表
 * 
 * @author HanHaiyun
 */
public class BuyBackContractReviewDto implements Serializable {
    private static final long serialVersionUID = -5692083368610619874L;
    /**
     * 采购退货合同编号
     */
    private String buyBackContractId;

    /**
     * 采购合同产品合同号
     */
    private String buyProdContractCode;
    /**
     * 原采购合同金额
     */
    private String buyContractMoney;
    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 产品合同号
     */
    private String buyBackProdContractCode;
    /**
     * 供货商名称
     */
    private String supplierName;
    /**
     * 公司合同号
     */
    private String compContractCode;
    /**
     * 合同类型
     */
    private String contractType;
    /**
     * 模板类型
     */
    private String templateType;
    /**
     * 产品分类
     */
    private String prodTypeName;
    /**
     * 产品序列号
     */
    private String prodSerialNumber;
    /**
     * 发票类型
     */
    private String invoiceType;
    /**
     * 退款方式
     */
    private String backWay;
    /**
     * 要求发货日期
     */
    private String requestSendDate;
    /**
     * 重要事项说明
     */
    private String text;
    /**
     * 送审人签字
     */
    private String userName;
    /**
     * 日期
     */

    private String dateTime;
    /**
     * 状态
     */
    private String status;
    // 法务专员登录名
    private String legalId;
    // 法务专员用户名
    private String legalName;
    // 法务专员评审日期
    private String legalDate;
    // 法务专员评审意见
    private String legalIdea;
    // 采购主管登录名
    private String buyManId;
    // 法务专员补充说明
    private String legalText;
    // 采购主管用户名
    private String buyManName;
    // 采购主管评审日期
    private String buyManDate;
    // 采购主管评审意见
    private String buyManIdea;
    // 采购主管产品要求是否符合评审意见
    private String buyManProductIdea;
    // 采购主管付款方式是否符合评审意见
    private String buyManPaymentTypeIdea;
    // 采购主管售前服务是否符合评审意见
    private String buyManBeforeserviceIdea;
    // 采购主管补充说明
    private String buyManText;
    // 运营总监登录名
    private String opeMajId;
    // 运营总监用户名
    private String opeMajName;
    // 运营总监评审日期
    private String opeMajDate;
    // 运营总监评审意见
    private String opeMajIdea;
    // 运营总监补充说明
    private String opeMajText;

    public String getBuyBackContractId() {
        return buyBackContractId;
    }

    public void setBuyBackContractId(String buyBackContractId) {
        this.buyBackContractId = buyBackContractId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBuyProdContractCode() {
        return buyProdContractCode;
    }

    public void setBuyProdContractCode(String buyProdContractCode) {
        this.buyProdContractCode = buyProdContractCode;
    }

    public String getBuyContractMoney() {
        return buyContractMoney;
    }

    public void setBuyContractMoney(String buyContractMoney) {
        this.buyContractMoney = buyContractMoney;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getBuyBackProdContractCode() {
        return buyBackProdContractCode;
    }

    public void setBuyBackProdContractCode(String buyBackProdContractCode) {
        this.buyBackProdContractCode = buyBackProdContractCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getCompContractCode() {
        return compContractCode;
    }

    public void setCompContractCode(String compContractCode) {
        this.compContractCode = compContractCode;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    public String getProdTypeName() {
        return prodTypeName;
    }

    public void setProdTypeName(String prodTypeName) {
        this.prodTypeName = prodTypeName;
    }

    public String getProdSerialNumber() {
        return prodSerialNumber;
    }

    public void setProdSerialNumber(String prodSerialNumber) {
        this.prodSerialNumber = prodSerialNumber;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getBackWay() {
        return backWay;
    }

    public void setBackWay(String backWay) {
        this.backWay = backWay;
    }

    public String getRequestSendDate() {
        return requestSendDate;
    }

    public void setRequestSendDate(String requestSendDate) {
        this.requestSendDate = requestSendDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getLegalId() {
        return legalId;
    }

    public void setLegalId(String legalId) {
        this.legalId = legalId;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public String getLegalDate() {
        return legalDate;
    }

    public void setLegalDate(String legalDate) {
        this.legalDate = legalDate;
    }

    public String getLegalIdea() {
        return legalIdea;
    }

    public void setLegalIdea(String legalIdea) {
        this.legalIdea = legalIdea;
    }

    public String getBuyManId() {
        return buyManId;
    }

    public void setBuyManId(String buyManId) {
        this.buyManId = buyManId;
    }

    public String getLegalText() {
        return legalText;
    }

    public void setLegalText(String legalText) {
        this.legalText = legalText;
    }

    public String getBuyManName() {
        return buyManName;
    }

    public void setBuyManName(String buyManName) {
        this.buyManName = buyManName;
    }

    public String getBuyManDate() {
        return buyManDate;
    }

    public void setBuyManDate(String buyManDate) {
        this.buyManDate = buyManDate;
    }

    public String getBuyManIdea() {
        return buyManIdea;
    }

    public void setBuyManIdea(String buyManIdea) {
        if (null == buyManIdea || "".equals(buyManIdea)) {
            this.setBuyManProductIdea(null);
            this.setBuyManPaymentTypeIdea(null);
            this.setBuyManBeforeserviceIdea(null);
        } else {
            String[] buyManIdeas = buyManIdea.split("");
            this.setBuyManProductIdea(buyManIdeas.length >= 1 ? buyManIdeas[1] : null);
            this
                    .setBuyManPaymentTypeIdea(buyManIdeas.length >= 2 ? buyManIdeas[2]
                            : null);
            this.setBuyManBeforeserviceIdea(buyManIdeas.length >= 3 ? buyManIdeas[3]
                    : null);
        }
        this.buyManIdea = buyManIdea;
    }

    public String getBuyManProductIdea() {
        return buyManProductIdea;
    }

    public void setBuyManProductIdea(String buyManProductIdea) {
        this.buyManProductIdea = buyManProductIdea;
    }

    public String getBuyManPaymentTypeIdea() {
        return buyManPaymentTypeIdea;
    }

    public void setBuyManPaymentTypeIdea(String buyManPaymentTypeIdea) {
        this.buyManPaymentTypeIdea = buyManPaymentTypeIdea;
    }

    public String getBuyManBeforeserviceIdea() {
        return buyManBeforeserviceIdea;
    }

    public void setBuyManBeforeserviceIdea(String buyManBeforeserviceIdea) {
        this.buyManBeforeserviceIdea = buyManBeforeserviceIdea;
    }

    public String getBuyManText() {
        return buyManText;
    }

    public void setBuyManText(String buyManText) {
        this.buyManText = buyManText;
    }

    public String getOpeMajId() {
        return opeMajId;
    }

    public void setOpeMajId(String opeMajId) {
        this.opeMajId = opeMajId;
    }

    public String getOpeMajName() {
        return opeMajName;
    }

    public void setOpeMajName(String opeMajName) {
        this.opeMajName = opeMajName;
    }

    public String getOpeMajDate() {
        return opeMajDate;
    }

    public void setOpeMajDate(String opeMajDate) {
        this.opeMajDate = opeMajDate;
    }

    public String getOpeMajIdea() {
        return opeMajIdea;
    }

    public void setOpeMajIdea(String opeMajIdea) {
        this.opeMajIdea = opeMajIdea;
    }

    public String getOpeMajText() {
        return opeMajText;
    }

    public void setOpeMajText(String opeMajText) {
        this.opeMajText = opeMajText;
    }
}
