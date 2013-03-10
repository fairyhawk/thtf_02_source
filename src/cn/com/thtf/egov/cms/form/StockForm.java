/**
 * ClassName  StockForm
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-6-3
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.form;

import org.apache.struts.action.ActionForm;

import cn.com.thtf.egov.cms.dto.StockQueryDto;

/**
 * StockForm
 * 
 * @author Lubo
 */
public class StockForm extends ActionForm {

    /** 自动生成序列化ID */
    private static final long serialVersionUID = 1L;
    /** StockQueryDto */
    private StockQueryDto queryPara = new StockQueryDto();

    /**
     * @return the queryPara
     */
    public StockQueryDto getQueryPara() {
        return queryPara;
    }

    /**
     * @param queryPara
     *            the queryPara to set
     */
    public void setQueryPara(StockQueryDto queryPara) {
        this.queryPara = queryPara;
    }

}
