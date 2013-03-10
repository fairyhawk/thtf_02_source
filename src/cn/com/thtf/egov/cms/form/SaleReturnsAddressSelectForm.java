package cn.com.thtf.egov.cms.form;

import java.math.BigDecimal;

import org.apache.struts.action.ActionForm;
/**
 * 销售退货管理
 * 创建退货单FORM
 * @author lxs
 */
public class SaleReturnsAddressSelectForm extends ActionForm {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String backId;

    public String getBackId() {
        return backId;
    }

    public void setBackId(String backId) {
        this.backId = backId;
    }

    private String sendGoodsId;

    private String productTypeId;

    private String customeId;

    private String customeName;

    private String stockroomId;

    private String stockRoomAddressId;

    private Integer thcount[];

    private BigDecimal price[];
    
    private Integer productId[];

    public Integer[] getProductId() {
        return productId;
    }

    public void setProductId(Integer[] productId) {
        this.productId = productId;
    }

    private String textarea;

    private Integer pd;

    public Integer getPd() {
        return pd;
    }

    public void setPd(Integer pd) {
        this.pd = pd;
    }

    public String getSendGoodsId() {
        return sendGoodsId;
    }

    public void setSendGoodsId(String sendGoodsId) {
        this.sendGoodsId = sendGoodsId;
    }

    public String getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(String productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getCustomeId() {
        return customeId;
    }

    public void setCustomeId(String customeId) {
        this.customeId = customeId;
    }

    public String getCustomeName() {
        return customeName;
    }

    public void setCustomeName(String customeName) {
        this.customeName = customeName;
    }

    public String getStockroomId() {
        return stockroomId;
    }

    public void setStockroomId(String stockroomId) {
        this.stockroomId = stockroomId;
    }

    public String getStockRoomAddressId() {
        return stockRoomAddressId;
    }

    public void setStockRoomAddressId(String stockRoomAddressId) {
        this.stockRoomAddressId = stockRoomAddressId;
    }

    public Integer[] getThcount() {
        return thcount;
    }

    public void setThcount(Integer[] thcount) {
        this.thcount = thcount;
    }

    public BigDecimal[] getPrice() {
        return price;
    }

    public void setPrice(BigDecimal[] price) {
        this.price = price;
    }

    public String getTextarea() {
        return textarea;
    }

    public void setTextarea(String textarea) {
        this.textarea = textarea;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    // private NewReturnsProductDto newReturnsProductDto;
    //    
    // public NewReturnsProductDto getNewReturnsProductDto() {
    // return newReturnsProductDto;
    // }
    //
    //
    // public void setNewReturnsProductDto(NewReturnsProductDto
    // newReturnsProductDto) {
    // this.newReturnsProductDto = newReturnsProductDto;
    // }
    //
    //
    // private SaleReturnsAddressSelectDto saleReturnsAddressSelect;
    //
    //
    // public SaleReturnsAddressSelectDto getSaleReturnsAddressSelect() {
    // return saleReturnsAddressSelect;
    // }
    //
    //
    // public void setSaleReturnsAddressSelect(
    // SaleReturnsAddressSelectDto saleReturnsAddressSelect) {
    // this.saleReturnsAddressSelect = saleReturnsAddressSelect;
    // }
    //    
    // private SaleReturnGoodsViewSelfDto srgvsd;
    //
    // public SaleReturnGoodsViewSelfDto getSrgvsd() {
    // return srgvsd;
    // }
    //
    //
    // public void setSrgvsd(SaleReturnGoodsViewSelfDto srgvsd) {
    // this.srgvsd = srgvsd;
    // }

}
