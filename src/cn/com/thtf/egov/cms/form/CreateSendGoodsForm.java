/**
 * ClassName  CreateSendGoodsForm
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-4-22
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.form;

import org.apache.struts.action.ActionForm;

import cn.com.thtf.egov.cms.dto.SendGoodsAddDto;

/**
 * 新建发货单
 * 
 * @author Lubo
 */

public class CreateSendGoodsForm extends ActionForm {

    /** 自动生成序列化ID */
    private static final long serialVersionUID = 1L;
    /** SendGoodsAddDto */
    private SendGoodsAddDto addPara = new SendGoodsAddDto(); 

    /**
     * @return the addPara
     */
    public SendGoodsAddDto getAddPara() {
        return addPara;
    }

    /**
     * @param addPara
     *            the addPara to set
     */
    public void setAddPara(SendGoodsAddDto addPara) {
        this.addPara = addPara;
    }

}
