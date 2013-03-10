package cn.com.thtf.egov.cms.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 修改采购退货合同-采购退货合同信息
 * 
 * @author HanHaiyun
 */
public class BuyBackContractInfoDto implements Serializable {

    private static final long serialVersionUID = 4665627709274025111L;
    /** 采购退货合同编号 */
    private String buyBackContractId;
    /** 采购合同编号 */
    private String buyContractId;
    /** 产品合同号 */
    private String backProdContractCode;
    /** 公司合同号 */
    private String backCompContractCode;
    /** 模板类型 */
    private Integer templateType;
    /** 签订地点 */
    private String place;
    /** 文件 */
    private String file;
    /** 要求发货日期 */
    private String requestSendDate;
    /** 特殊说明 */
    private String text;
    /** 要求退货方式 */
    private Integer backWay;
    /** 供货地址编号 */
    private String supplierAddressId;
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
    private String dateTime;
    private String productTypeId;
    //采购退货合同状态
    private Integer status;
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(String productTypeId) {
        this.productTypeId = productTypeId;
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
    public String getBackProdContractCode() {
        return backProdContractCode;
    }

    public void setBackProdContractCode(String backProdContractCode) {
        this.backProdContractCode = backProdContractCode;
    }

    public String getBackCompContractCode() {
        return backCompContractCode;
    }

    public void setBackCompContractCode(String backCompContractCode) {
        this.backCompContractCode = backCompContractCode;
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

    public String getSupplierAddressId() {
        return supplierAddressId;
    }

    public void setSupplierAddressId(String supplierAddressId) {
        this.supplierAddressId = supplierAddressId;
    }

    public Integer getBackWay() {
        return backWay;
    }

    public void setBackWay(Integer backWay) {
        this.backWay = backWay;
    }

    public String getBuyBackContractId() {
        return buyBackContractId;
    }

    public void setBuyBackContractId(String buyBackContractId) {
        this.buyBackContractId = buyBackContractId;
    }

    public String getBuyContractId() {
        return buyContractId;
    }

    public void setBuyContractId(String buyContractId) {
        this.buyContractId = buyContractId;
    }

    public Integer getTemplateType() {
        return templateType;
    }

    public void setTemplateType(Integer templateType) {
        this.templateType = templateType;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
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

    public BigDecimal getBuyBackMoney() {
        return buyBackMoney;
    }

    public void setBuyBackMoney(BigDecimal buyBackMoney) {
        this.buyBackMoney = buyBackMoney;
    }

    /** 合同金额 */
    private BigDecimal buyBackMoney;
}
