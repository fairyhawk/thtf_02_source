/**
 * ClassName  CustwaybillForm
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-8-17
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.form;

import org.apache.struts.action.ActionForm;

import cn.com.thtf.egov.cms.dto.CustwaybillDto;

/**
 * CustwaybillForm
 * 
 * @author Lubo
 */
public class CustwaybillForm extends ActionForm {

	/** 自动生成序列化ID */
	private static final long serialVersionUID = 1L;
	/** 查询参数 */
	private CustwaybillDto queryPara = new CustwaybillDto();

	public CustwaybillDto getQueryPara() {
		return queryPara;
	}

	public void setQueryPara(CustwaybillDto queryPara) {
		this.queryPara = queryPara;
	}
}
