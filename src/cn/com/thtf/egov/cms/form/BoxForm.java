/**
 * ClassName  BoxForm
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-6-8
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.form;

import org.apache.struts.action.ActionForm;

import cn.com.thtf.egov.cms.dto.BoxDto;
import cn.com.thtf.egov.cms.dto.BoxQueryDto;

/**
 * BoxForm
 * 
 * @author Lubo
 */
public class BoxForm extends ActionForm {

    /** 自动生成序列化ID */
    private static final long serialVersionUID = 1L;
    /** 新增装箱单 */
    private BoxDto addPara = new BoxDto();
    /** 检索装箱单 */
    private BoxQueryDto queryPara = new BoxQueryDto();

    /**
     * @return the queryPara
     */
    public BoxQueryDto getQueryPara() {
        return queryPara;
    }

    /**
     * @param queryPara the queryPara to set
     */
    public void setQueryPara(BoxQueryDto queryPara) {
        this.queryPara = queryPara;
    }

    /**
     * @return the addPara
     */
    public BoxDto getAddPara() {
        return addPara;
    }

    /**
     * @param addPara
     *            the addPara to set
     */
    public void setAddPara(BoxDto addPara) {
        this.addPara = addPara;
    }

}
