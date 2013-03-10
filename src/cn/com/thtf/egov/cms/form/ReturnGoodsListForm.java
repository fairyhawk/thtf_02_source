package cn.com.thtf.egov.cms.form;

import org.apache.struts.action.ActionForm;

import cn.com.thtf.egov.cms.dto.ReturnGoodsListQueryDto;
/**
 * 销售退货管理
 * 列表检索FORM
 * @author LuPing
 */
public class ReturnGoodsListForm extends ActionForm {

    /**
     * 自动序列化
     */
    private static final long serialVersionUID = -8084082039710842467L;
    
    private ReturnGoodsListQueryDto returnGoodsQuery = new ReturnGoodsListQueryDto();

    public ReturnGoodsListQueryDto getReturnGoodsQuery() {
        return returnGoodsQuery;
    }

    public void setReturnGoodsQuery(ReturnGoodsListQueryDto returnGoodsQuery) {
        this.returnGoodsQuery = returnGoodsQuery;
    }
    
}
