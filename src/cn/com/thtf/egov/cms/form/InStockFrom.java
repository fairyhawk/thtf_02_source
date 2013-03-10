/**
 * ClassName  InStockFrom
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-5-27
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.form;

import org.apache.struts.action.ActionForm;

import cn.com.thtf.egov.cms.dto.AddInStockDto;
import cn.com.thtf.egov.cms.dto.InStockQueryDto;
import cn.com.thtf.egov.cms.entity.InStockEntity;

/**
 * InStockFrom
 * 
 * @author Lubo
 */ 
public class InStockFrom extends ActionForm {

    /** 自动生成序列化ID */
    private static final long serialVersionUID = 1L;
    /** 新增入库单 */
    private AddInStockDto addPara = new AddInStockDto();
    /** 检索参数 */
    private InStockQueryDto queryPara = new InStockQueryDto();
    /** 评审参数 */
    private InStockEntity auditPara = new InStockEntity();
    /** 是否是评审 */
    private String isAudit = null;

    /**
     * @return the auditPara
     */
    public InStockEntity getAuditPara() {
        return auditPara;
    }

    /**
     * @param auditPara the auditPara to set
     */
    public void setAuditPara(InStockEntity auditPara) {
        this.auditPara = auditPara;
    }

    /**
     * @return the isAudit
     */
    public String getIsAudit() {
        return isAudit;
    }

    /**
     * @param isAudit the isAudit to set
     */
    public void setIsAudit(String isAudit) {
        this.isAudit = isAudit;
    }

    /**
     * @return the queryPara
     */
    public InStockQueryDto getQueryPara() {
        return queryPara;
    }

    /**
     * @param queryPara
     *            the queryPara to set
     */
    public void setQueryPara(InStockQueryDto queryPara) {
        this.queryPara = queryPara;
    }

    /**
     * @return the addPara
     */
    public AddInStockDto getAddPara() {
        return addPara;
    }

    /**
     * @param addPara
     *            the addPara to set
     */
    public void setAddPara(AddInStockDto addPara) {
        this.addPara = addPara;
    }

}
