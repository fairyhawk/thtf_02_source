/**
 * ClassName  MReturnListForm
 *
 * History
 * Create User: balance
 * Create Date: 2010-5-19
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.form;

import org.apache.struts.action.ActionForm;

import cn.com.thtf.egov.cms.dto.MReturnListQueryDto;

/**
 * 
 * @author balance
 */

public class MReturnListForm extends ActionForm {

    /**
     * 自动序列化
     */
    private static final long serialVersionUID = -8006839215105762108L;
    /** 回款检索信息 **/
    MReturnListQueryDto mReturnListQueryDto = new MReturnListQueryDto();
    /**
     * @return the mReturnListQueryDto
     */
    public MReturnListQueryDto getmReturnListQueryDto() {
        return mReturnListQueryDto;
    }
    /**
     * @param mReturnListQueryDto the mReturnListQueryDto to set
     */
    public void setmReturnListQueryDto(MReturnListQueryDto mReturnListQueryDto) {
        this.mReturnListQueryDto = mReturnListQueryDto;
    }
    
   
}
