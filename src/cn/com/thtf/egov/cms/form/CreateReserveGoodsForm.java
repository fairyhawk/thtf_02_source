/**
 * ClassName  CreateReserveGoods
 *
 * History
 * Create User: balance
 * Create Date: 2010-4-28
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.form;

import java.math.BigDecimal;

import org.apache.struts.action.ActionForm;

import cn.com.thtf.egov.cms.entity.SendGoodsEntity;

/**
 * 
 * @author balance
 */

public class CreateReserveGoodsForm extends ActionForm {

    /**
     * 
     */
    private static final long serialVersionUID = 6714892658837058079L;

    /** 销售合同ID **/
    private String sellContractId;
    /** 发货单ID **/
    private String SendGoodId;
    /** 客户ID **/
    private String customerId;
    /** 库房ID **/
    private Integer stockroomSelect;
    /** 预计发货日期 **/
    private String willSendGoodsDate;
    /** 本库房备货数 **/
    private String[] reserveGoodsNum;
    /**
     * @return the sendGoodId
     */
    public String getSendGoodId() {
        return SendGoodId;
    }

    /**
     * @param sendGoodId the sendGoodId to set
     */
    public void setSendGoodId(String sendGoodId) {
        SendGoodId = sendGoodId;
    }

    /** 备货金额总计 **/
    private BigDecimal reserveGoodsMoneyE;
    /** 特殊说明 **/
    private String reserveText;
    /** 存储类型**/
    private String submitType;
    /** 产品ID **/
    private String[] productId;
    /** 备货单**/
    private SendGoodsEntity sendGoodE;
    /** 销售单价**/
    private String[] price;
    
    /**
     * @return the sellContractId
     */
    public String getSellContractId() {
        return sellContractId;
    }

    /**
     * @return the sendGoodE
     */
    public SendGoodsEntity getSendGoodE() {
        return sendGoodE;
    }

    /**
     * @param sendGoodE the sendGoodE to set
     */
    public void setSendGoodE(SendGoodsEntity sendGoodE) {
        this.sendGoodE = sendGoodE;
    }

    /**
     * @param sellContractId
     *            the sellContractId to set
     */
    public void setSellContractId(String sellContractId) {
        this.sellContractId = sellContractId;
    }

    /**
     * @return the customerId
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId
     *            the customerId to set
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }


    

    /**
     * @return the stockroomSelect
     */
    public Integer getStockroomSelect() {
        return stockroomSelect;
    }

    /**
     * @param stockroomSelect the stockroomSelect to set
     */
    public void setStockroomSelect(Integer stockroomSelect) {
        this.stockroomSelect = stockroomSelect;
    }

    /**
     * @return the productId
     */
    public String[] getProductId() {
        return productId;
    }

    /**
     * @param productId
     *            the productId to set
     */
    public void setProductId(String[] productId) {
        this.productId = productId;
    }

    /**
     * @return the reserveGoodsNum
     */
    public String[] getReserveGoodsNum() {
        return reserveGoodsNum;
    }

    /**
     * @param reserveGoodsNum
     *            the reserveGoodsNum to set
     */
    public void setReserveGoodsNum(String[] reserveGoodsNum) {
        this.reserveGoodsNum = reserveGoodsNum;
    }

    /**
     * @return the willSendGoodsDatel
     */
    public String getWillSendGoodsDate() {
        return willSendGoodsDate;
    }

    /**
     * @param willSendGoodsDatel
     *            the willSendGoodsDatel to set
     */
    public void setWillSendGoodsDate(String willSendGoodsDate) {
        this.willSendGoodsDate = willSendGoodsDate;
    }

    /**
     * @return the reserveGoodsMoneyE
     */
    public BigDecimal getReserveGoodsMoneyE() {
        return reserveGoodsMoneyE;
    }

    /**
     * @param reserveGoodsMoneyE
     *            the reserveGoodsMoneyE to set
     */
    public void setReserveGoodsMoneyE(BigDecimal reserveGoodsMoneyE) {
        this.reserveGoodsMoneyE = reserveGoodsMoneyE;
    }

    /**
     * @return the reserveText
     */
    public String getReserveText() {
        return reserveText;
    }

    /**
     * @param reserveText
     *            the reserveText to set
     */
    public void setReserveText(String reserveText) {
        this.reserveText = reserveText;
    }

    /**
     * @return the submitType
     */
    public String getSubmitType() {
        return submitType;
    }

    /**
     * @param submitType the submitType to set
     */
    public void setSubmitType(String submitType) {
        this.submitType = submitType;
    }

    /**
     * @return the price
     */
    public String[] getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(String[] price) {
        this.price = price;
    }
    
}
