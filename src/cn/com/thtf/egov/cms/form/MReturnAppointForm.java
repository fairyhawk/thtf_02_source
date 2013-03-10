/**
 * ClassName  MReturnAppointForm
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-5-13
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.form;

import org.apache.struts.action.ActionForm;

import cn.com.thtf.egov.cms.dto.AppointAddDto;
import cn.com.thtf.egov.cms.dto.MreturnAppointDto;

/**
 * 回款指定
 * 
 * @author Lubo
 */

public class MReturnAppointForm extends ActionForm {

    /** 自动生成序列化ID */
    private static final long serialVersionUID = 1L;
    /** 检索DTO */
    private MreturnAppointDto mreturnPara = new MreturnAppointDto();
    /** 添加DTO */
    private AppointAddDto addPara = new AppointAddDto();

    /**
     * @return the addPara
     */
    public AppointAddDto getAddPara() {
        return addPara;
    }

    /**
     * @param addPara the addPara to set
     */
    public void setAddPara(AppointAddDto addPara) {
        this.addPara = addPara;
    }

    /**
     * @return the mreturnPara
     */
    public MreturnAppointDto getMreturnPara() {
        return mreturnPara;
    }

    /**
     * @param mreturnPara
     *            the mreturnPara to set
     */
    public void setMreturnPara(MreturnAppointDto mreturnPara) {
        this.mreturnPara = mreturnPara;
    }

}
