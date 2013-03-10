/**
 * ClassName  SendGoodsListQueryDto
 *
 * History
 * Create User: balance
 * Create Date: 2010-4-23
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

import java.io.Serializable;

/**
 * 发货管理检索Dto
 * @author Luping
 */

public class SendGoodsListQueryDto implements Serializable {
    /**
     * 自动生成序列化ID
     */
    private static final long serialVersionUID = 5517784159230176598L;
    
    /** 初始化检索**/
    private String init;
    /** 发货单号ID */
    private String sgid;
    /** 产品分类ID */
    private String producttypeid;
    /** 产品合同号ID */
    private String pcontractcode;
    /** 公司合同号ID */
    private String ccontractcode;
    /** 客户名称 */
    private String customername;
    /** 人员名称 */
    private String sgusername;
    /** 发货类型标记 */
    private String sgsgtype;
    /** 发货状态 标记 */
    private String sgstatus;
    /** 库房ID */
    private String stockroomid;
    /** 申请起始日期 */
    private String startapplydate;
    /** 申请终止日期 */
    private String endapplydate;
    /** 发货起始日期 */
    private String startsendgoodsdate;
    /** 发货终止日期 */
    private String endsendgoodsdate;
    /** 要求到账起始日期 */
    private String startrequestdate;
    /** 要求到账终止日期 */
    private String endrequestdate;
    /**用户ID**/
    private String userid;
    /**用户职务**/
    private String userrole;
    /**用户区域**/
    private Integer userAreaId;
    
    /** 销售项目名称 */
    private String contractProName;

    public String getContractProName() {
        return contractProName;
    }

    public void setContractProName(String contractProName) {
        this.contractProName = contractProName;
    }

    public Integer getUserAreaId() {
        return userAreaId;
    }

    public void setUserAreaId(Integer userAreaId) {
        this.userAreaId = userAreaId;
    }

    /**
     * @return the userrole
     */
    public String getUserrole() {
        return userrole;
    }

    /**
     * @param userrole the userrole to set
     */
    public void setUserrole(String userrole) {
        this.userrole = userrole;
    }

    /**
     * @return the startapplydate
     */
    public String getStartapplydate() {
        return startapplydate;
    }

    /**
     * @param startapplydate
     *            the startapplydate to set
     */
    public void setStartapplydate(String startapplydate) {
        this.startapplydate = startapplydate;
    }

    /**
     * @return the endapplydate
     */
    public String getEndapplydate() {
        return endapplydate;
    }

    /**
     * @param endapplydate
     *            the endapplydate to set
     */
    public void setEndapplydate(String endapplydate) {
        this.endapplydate = endapplydate;
    }

    /**
     * @return the startsendgoodsdate
     */
    public String getStartsendgoodsdate() {
        return startsendgoodsdate;
    }

    /**
     * @param startsendgoodsdate
     *            the startsendgoodsdate to set
     */
    public void setStartsendgoodsdate(String startsendgoodsdate) {
        this.startsendgoodsdate = startsendgoodsdate;
    }

    /**
     * @return the endsendgoodsdate
     */
    public String getEndsendgoodsdate() {
        return endsendgoodsdate;
    }

    /**
     * @param endsendgoodsdate
     *            the endsendgoodsdate to set
     */
    public void setEndsendgoodsdate(String endsendgoodsdate) {
        this.endsendgoodsdate = endsendgoodsdate;
    }

    /**
     * @return the startrequestdate
     */
    public String getStartrequestdate() {
        return startrequestdate;
    }

    /**
     * @param startrequestdate
     *            the startrequestdate to set
     */
    public void setStartrequestdate(String startrequestdate) {
        this.startrequestdate = startrequestdate;
    }

    /**
     * @return the endrequestdate
     */
    public String getEndrequestdate() {
        return endrequestdate;
    }

    /**
     * @param endrequestdate
     *            the endrequestdate to set
     */
    public void setEndrequestdate(String endrequestdate) {
        this.endrequestdate = endrequestdate;
    }

    /**
     * @return the stockroomid
     */
    public String getStockroomid() {
        return stockroomid;
    }

    /**
     * @param stockroomid
     *            the stockroomid to set
     */
    public void setStockroomid(String stockroomid) {
        this.stockroomid = stockroomid;
    }

    /**
     * @return the sgid
     */
    public String getSgid() {
        return sgid;
    }

    /**
     * @param sgid
     *            the sgid to set
     */
    public void setSgid(String sgid) {
        this.sgid = sgid;
    }

    /**
     * @return the producttypeid
     */
    public String getProducttypeid() {
        return producttypeid;
    }

    /**
     * @param producttypeid
     *            the producttypeid to set
     */
    public void setProducttypeid(String producttypeid) {
        this.producttypeid = producttypeid;
    }

    /**
     * @return the pcontractcode
     */
    public String getPcontractcode() {
        return pcontractcode;
    }

    /**
     * @param pcontractcode
     *            the pcontractcode to set
     */
    public void setPcontractcode(String pcontractcode) {
        this.pcontractcode = pcontractcode;
    }

    /**
     * @return the ccontractcode
     */
    public String getCcontractcode() {
        return ccontractcode;
    }

    /**
     * @param ccontractcode
     *            the ccontractcode to set
     */
    public void setCcontractcode(String ccontractcode) {
        this.ccontractcode = ccontractcode;
    }

    /**
     * @return the customername
     */
    public String getCustomername() {
        return customername;
    }

    /**
     * @param customername
     *            the customername to set
     */
    public void setCustomername(String customername) {
        this.customername = customername;
    }

    /**
     * @return the sgusername
     */
    public String getSgusername() {
        return sgusername;
    }

    /**
     * @param sgusername
     *            the sgusername to set
     */
    public void setSgusername(String sgusername) {
        this.sgusername = sgusername;
    }

    /**
     * @return the sgsgtype
     */
    public String getSgsgtype() {
        return sgsgtype;
    }

    /**
     * @param sgsgtype
     *            the sgsgtype to set
     */
    public void setSgsgtype(String sgsgtype) {
        this.sgsgtype = sgsgtype;
    }

    /**
     * @return the sgstatus
     */
    public String getSgstatus() {
        return sgstatus;
    }

    /**
     * @param sgstatus
     *            the sgstatus to set
     */
    public void setSgstatus(String sgstatus) {
        this.sgstatus = sgstatus;
    }

    /**
     * @return the userid
     */
    public String getUserid() {
        return userid;
    }

    /**
     * @param userid the userid to set
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getInit() {
        return init;
    }

    public void setInit(String init) {
        this.init = init;
    }
    
}
