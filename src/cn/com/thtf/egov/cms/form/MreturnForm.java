package cn.com.thtf.egov.cms.form;

import org.apache.struts.action.ActionForm;

import cn.com.thtf.egov.cms.entity.MreturnEntity;


public class MreturnForm extends ActionForm {

    /**
     * 自动序列化
     */
    private static final long serialVersionUID = -7422114395232411982L;
    /**回款信息**/
    private MreturnEntity mReturnEntity = new MreturnEntity();
    public MreturnEntity getmReturnEntity() {
        return mReturnEntity;
    }
    public void setmReturnEntity(MreturnEntity mReturnEntity) {
        this.mReturnEntity = mReturnEntity;
    }
    
    
}
