package cn.com.thtf.egov.cms.form;

import org.apache.struts.action.ActionForm;

import cn.com.thtf.egov.cms.dto.IncomeStockListDto;

public class IncomeStockListForm extends ActionForm{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private IncomeStockListDto incomeStockListDto=new IncomeStockListDto();

public IncomeStockListDto getIncomeStockListDto() {
	return incomeStockListDto;
}

public void setIncomeStockListDto(IncomeStockListDto incomeStockListDto) {
	this.incomeStockListDto = incomeStockListDto;
}
}
