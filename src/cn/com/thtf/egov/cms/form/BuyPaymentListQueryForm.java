package cn.com.thtf.egov.cms.form;

import org.apache.struts.action.ActionForm;

import cn.com.thtf.egov.cms.dto.BuyPaymentListQueryDto;

/**
 * 采购付款 列表检索FORM
 * 
 * @author LuPing
 */
public class BuyPaymentListQueryForm extends ActionForm {

    /**
     * 自动序列化
     */
    private static final long serialVersionUID = -114456846763348820L;

    private BuyPaymentListQueryDto buyPaymentListQueryDto = new BuyPaymentListQueryDto();

    public BuyPaymentListQueryDto getBuyPaymentListQueryDto() {
        return buyPaymentListQueryDto;
    }

    public void setBuyPaymentListQueryDto(BuyPaymentListQueryDto buyPaymentListQueryDto) {
        this.buyPaymentListQueryDto = buyPaymentListQueryDto;
    }
}
