package cn.com.thtf.egov.cms.form;

import org.apache.struts.action.ActionForm;

import cn.com.thtf.egov.cms.dto.BuyContractOfAddDto;

public class BuyContractOfAddForm extends ActionForm{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/**
	 * @author hanrubing
	 */
private BuyContractOfAddDto buyContractOfAddDto=new BuyContractOfAddDto();

public BuyContractOfAddDto getBuyContractOfAddDto() {
	return buyContractOfAddDto;
}

public void setBuyContractOfAddDto(BuyContractOfAddDto buyContractOfAddDto) {
	this.buyContractOfAddDto = buyContractOfAddDto;
}
}
