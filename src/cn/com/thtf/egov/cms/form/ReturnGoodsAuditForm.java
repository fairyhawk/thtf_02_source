package cn.com.thtf.egov.cms.form;

import org.apache.struts.action.ActionForm;
/**
 * 销售退货评审FORM
 * @author LuPing
 */
public class ReturnGoodsAuditForm extends ActionForm {

    /**
     * 自动序列化
     */
    private static final long serialVersionUID = -7713072459973816218L;
    /**销售总监评审意见**/
    private String sellMajIdea0;
    private String sellMajIdea1;
    /**销售总监补充说明**/
    private String sellMajText;
    
    /**运营总监评审意见**/
    private String opeMajIdea0;
    private String opeMajIdea1;
    /**运营总监补充说明**/
    private String opeMajText;
    /**评审标记**/
    private String auditMart;
    public String getAuditMart() {
        return auditMart;
    }
    public void setAuditMart(String auditMart) {
        this.auditMart = auditMart;
    }
    public String getSellMajIdea0() {
        return sellMajIdea0;
    }
    public void setSellMajIdea0(String sellMajIdea0) {
        this.sellMajIdea0 = sellMajIdea0;
    }
    public String getSellMajIdea1() {
        return sellMajIdea1;
    }
    public void setSellMajIdea1(String sellMajIdea1) {
        this.sellMajIdea1 = sellMajIdea1;
    }
    public String getSellMajText() {
        return sellMajText;
    }
    public void setSellMajText(String sellMajText) {
        this.sellMajText = sellMajText;
    }
    public String getOpeMajIdea0() {
        return opeMajIdea0;
    }
    public void setOpeMajIdea0(String opeMajIdea0) {
        this.opeMajIdea0 = opeMajIdea0;
    }
    public String getOpeMajIdea1() {
        return opeMajIdea1;
    }
    public void setOpeMajIdea1(String opeMajIdea1) {
        this.opeMajIdea1 = opeMajIdea1;
    }
    public String getOpeMajText() {
        return opeMajText;
    }
    public void setOpeMajText(String opeMajText) {
        this.opeMajText = opeMajText;
    }
}
