/**
 * ClassName  OrderForm
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-6-7
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.form;

import org.apache.struts.action.ActionForm;

import cn.com.thtf.egov.cms.dto.StockSendGoodsDto;

/**
 * OrderForm
 * 
 * @author Lubo
 */
public class OrderForm extends ActionForm {

    /** 自动生成序列化ID */
    private static final long serialVersionUID = 1L;
    /** 检索参数 */
    private StockSendGoodsDto queryPara = new StockSendGoodsDto();

    /**
     * @return the queryPara
     */
    public StockSendGoodsDto getQueryPara() {
        return queryPara;
    }

    /**
     * @param queryPara
     *            the queryPara to set
     */
    public void setQueryPara(StockSendGoodsDto queryPara) {
        this.queryPara = queryPara;
    }

}
