package cn.com.thtf.egov.cms.dto;

import java.io.Serializable;

/**
 *採購合同評審
 * 
 * @author HanHaiyun
 */
public class BuyContractReviewDto implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -2285600002285387731L;
    // 采购合同Id
    private String id;
    // 产品类型Id
    private String prodTypeId;
    // 状态
    private Integer status;
    // 法务专员登录名
    private String legalId;
    // 法务专员用户名
    private String legalName;
    // 法务专员评审日期
    private String legalDate;
    // 法务专员评审意见
    private String legalIdea;
    // 法务专员补充说明
    private String legalText;
    // 采购主管登录名
    private String buyManId;
    // 采购主管用户名
    private String buyManName;
    // 采购主管评审日期
    private String buyManDate;
    // 采购主管评审意见
    private String buyManIdea;
    // 采购主管评审补充说明
    private String buyManText;
    // 运营总监登录名
    private String opeMajId;
    // 运营总监用户名
    private String opeMajName;
    // 运营总监评审日期
    private String opeMajDate;
    // 运营总监评审意见
    private String opeMajIdea;
    // 运营总监评审补充说明
    private String opeMajText;
    // 运营总监角色Id
    private int opeMajRoleId;
    // 产品总金额
    private String prodTotalMoney;
    // 但产品增长率
    private double increaserate;
    // 产品合同号
    private String prodContractCode;

    public String getProdContractCode() {
        return prodContractCode;
    }

    public void setProdContractCode(String prodContractCode) {
        this.prodContractCode = prodContractCode;
    }

    public int getOpeMajRoleId() {
        return opeMajRoleId;
    }

    public void setOpeMajRoleId(int opeMajRoleId) {
        this.opeMajRoleId = opeMajRoleId;
    }

    public String getProdTypeId() {
        return prodTypeId;
    }

    public void setProdTypeId(String prodTypeId) {
        this.prodTypeId = prodTypeId;
    }

    public String getProdTotalMoney() {
        return prodTotalMoney;
    }

    public void setProdTotalMoney(String prodTotalMoney) {
        this.prodTotalMoney = prodTotalMoney;
    }

    public double getIncreaserate() {
        return increaserate;
    }

    public void setIncreaserate(double increaserate) {
        this.increaserate = increaserate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getLegalText() {
        return legalText;
    }

    public void setLegalText(String legalText) {
        this.legalText = legalText;
    }

    public String getBuyManId() {
        return buyManId;
    }

    public void setBuyManId(String buyManId) {
        this.buyManId = buyManId;
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
        this.buyManIdea = buyManIdea;
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

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
