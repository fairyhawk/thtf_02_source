package cn.com.thtf.egov.cms.form;

import org.apache.struts.action.ActionForm;

import cn.com.thtf.egov.cms.dto.BuyContractReviewDto;

/**
 * 采购合同评审表单
 * 
 * @author HanHaiyun
 */
public class BuyContractReviewForm extends ActionForm {

    private static final long serialVersionUID = -5290926994446941732L;
    private BuyContractReviewDto buyContractReviewDto = new BuyContractReviewDto();

    public BuyContractReviewDto getBuyContractReviewDto() {
        return buyContractReviewDto;
    }

    public void setBuyContractReviewDto(BuyContractReviewDto buyContractReviewDto) {
        this.buyContractReviewDto = buyContractReviewDto;
    }

}
