/**
 * ClassName  SendGoodsListInfoDto
 *
 * History
 * Create User: balance
 * Create Date: 2010-4-20
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

import cn.com.thtf.egov.cms.util.Util;

/**
 * 
 * @author balance
 */

public class SendGoodsListInfoDto implements Serializable {

    /**
     * 自动序列化
     */
    private static final long serialVersionUID = 1L;
    /** 发货单号 **/
    private String sgid;
    /** 库房名称 **/
    private String sgskname;
    /** 产品分类名称 **/
    private String sgptname;
    /** 产品合同号 **/
    private String sgpcontractcode;
    /** 公司合同号 **/
    private String sgccontractcode;
    /** 客户名称 **/
    private String sgcustomername;
    /** 发货/备货金额 **/
    private BigDecimal sgmoney;
    /** 退货金额 **/
    private BigDecimal sgbackmoney;
    /** 申请日期 **/
    private String sgdate;
    /** 发货日期 **/
    private String sgrequestdate;
    /** 人员名称 **/
    private String sgusername;
    /** 发货单类型标记 **/
    private Integer sgsgtype;
    /** 发货单状态标记 **/
    private Integer sgstatus;
    /** 实际发货日期 **/
    private String sgsenddate;
    /** 帐期 **/
    private BigDecimal sgarterm;
    /** 发货金额 **/
    private BigDecimal sendgoodsmoney;
    /** 要求到账日期 **/
    private String sgrqartermdate;
    /** 发货单类型 **/
    private String sgsgtypestr;
    /** 发货单状态 **/
    private String sgstatusstr;
    /** 备货金额 **/
    private BigDecimal stockgoodsmoney;
    private int sendCount;
    private int sellBackGoodsCount;
    private int  makeInvoiceCount;
    
    /** 销售项目名称 */
    private String contractProName;

    public String getContractProName() {
        return contractProName;
    }

    public void setContractProName(String contractProName) {
        this.contractProName = contractProName;
    }
    
    /**
     * @return the sgarterm
     */
    public BigDecimal getSgarterm() {
        return sgarterm;
    }

    /**
     * @param sgarterm
     *            the sgarterm to set
     */
    public void setSgarterm(BigDecimal sgarterm) {
        this.sgarterm = sgarterm;
    }

    /**
     * @return the sendgoodsmoney
     */
    public BigDecimal getSendgoodsmoney() {
        if (this.sgsgtype == 1) {
            this.sendgoodsmoney = sgmoney;
        }
        return sendgoodsmoney;
    }

    /**
     * @return the stockgoodsmoney
     */
    public BigDecimal getStockgoodsmoney() {
        if (this.sgsgtype == 0) {
            this.stockgoodsmoney = sgmoney;
        }
        return stockgoodsmoney;
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
     * @return the sgskname
     */
    public String getSgskname() {
        return sgskname;
    }

    /**
     * @param sgskname
     *            the sgskname to set
     */
    public void setSgskname(String sgskname) {
        this.sgskname = sgskname;
    }

    /**
     * @return the sgptname
     */
    public String getSgptname() {
        return sgptname;
    }

    /**
     * @param sgptname
     *            the sgptname to set
     */
    public void setSgptname(String sgptname) {
        this.sgptname = sgptname;
    }

    /**
     * @return the sgpcontractcode
     */
    public String getSgpcontractcode() {
        return sgpcontractcode;
    }

    /**
     * @param sgpcontractcode
     *            the sgpcontractcode to set
     */
    public void setSgpcontractcode(String sgpcontractcode) {
        this.sgpcontractcode = sgpcontractcode;
    }

    /**
     * @return the sgccontractcode
     */
    public String getSgccontractcode() {
        return sgccontractcode;
    }

    /**
     * @param sgccontractcode
     *            the sgccontractcode to set
     */
    public void setSgccontractcode(String sgccontractcode) {
        this.sgccontractcode = sgccontractcode;
    }

    /**
     * @return the sgcustomername
     */
    public String getSgcustomername() {
        return sgcustomername;
    }

    /**
     * @param sgcustomername
     *            the sgcustomername to set
     */
    public void setSgcustomername(String sgcustomername) {
        this.sgcustomername = sgcustomername;
    }

    /**
     * @return the sgmoney
     */
    public BigDecimal getSgmoney() {
        return sgmoney;
    }

    /**
     * @param sgmoney
     *            the sgmoney to set
     */
    public void setSgmoney(BigDecimal sgmoney) {
        this.sgmoney = sgmoney;
    }

    /**
     * @return the sgbackmoney
     */
    public BigDecimal getSgbackmoney() {
        return sgbackmoney;
    }

    /**
     * @param sgbackmoney
     *            the sgbackmoney to set
     */
    public void setSgbackmoney(BigDecimal sgbackmoney) {
        this.sgbackmoney = sgbackmoney;
    }

    /**
     * @return the sgdate
     */
    public String getSgdate() {
        return sgdate;
    }

    /**
     * @param sgdate
     *            the sgdate to set
     */
    public void setSgdate(String sgdate) {
        this.sgdate = sgdate;
    }

    /**
     * @return the sgrequestdate
     */
    public String getSgrequestdate() {
        return sgrequestdate;
    }

    /**
     * @param sgrequestdate
     *            the sgrequestdate to set
     */
    public void setSgrequestdate(String sgrequestdate) {
        this.sgrequestdate = sgrequestdate;
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
    public Integer getSgsgtype() {
        return sgsgtype;
    }

    /**
     * @param sgsgtype
     *            the sgsgtype to set
     */
    public void setSgsgtype(Integer sgsgtype) {
        this.sgsgtype = sgsgtype;
    }

    /**
     * @return the sgstatus
     */
    public Integer getSgstatus() {
        return sgstatus;
    }

    /**
     * @param sgstatus
     *            the sgstatus to set
     */
    public void setSgstatus(Integer sgstatus) {
        this.sgstatus = sgstatus;
    }

    /**
     * @return the sgsenddate
     */
    public String getSgsenddate() {
        return sgsenddate;
    }

    /**
     * @param sgsenddate
     *            the sgsenddate to set
     */
    public void setSgsenddate(String sgsenddate) {
        this.sgsenddate = sgsenddate;
    }

    /**
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /**
     * @return the sgstatusstr
     */
    public String getSgstatusstr() {
        this.sgstatusstr = "";
        if (this.sgstatus == 1) {
            this.sgstatusstr = "未提交";
        } else if (this.sgstatus == 2) {
            this.sgstatusstr = "待发货";
        } else if (this.sgstatus == 3) {
            this.sgstatusstr = "发货中";
        } else if (this.sgstatus == 4) {
            this.sgstatusstr = "发货异常";
        } else if (this.sgstatus == 5) {
            this.sgstatusstr = "发货成功";
        } else if (this.sgstatus == 6) {
            this.sgstatusstr = "销售总监待评审";
        } else if (this.sgstatus == 7) {
            this.sgstatusstr = "销售总监未通过";
        } else if (this.sgstatus == 8) {
            this.sgstatusstr = "备货成功";
        } else if (this.sgstatus == 9) {
            this.sgstatusstr = "备货超期";
        } else if (this.sgstatus == 10){
            this.sgstatusstr = "区域总监待评审";
        } else if (this.sgstatus == 11){
            this.sgstatusstr = "区域总监未通过";
        }
        return sgstatusstr;
    }

    /**
     * @return the sgsgtypestr
     */
    public String getSgsgtypestr() {
        this.sgsgtypestr = "";
        if (this.sgsgtype == 0) {
            this.sgsgtypestr = "备货";
        } else if (this.sgsgtype == 1) {
            this.sgsgtypestr = "发货";
        }
        return sgsgtypestr;
    }

    /**
     * @return the sgrqartermdate
     */
    public String getSgrqartermdate() {
        sgrqartermdate = "";
        if (StringUtils.isNotBlank(this.sgsenddate) && this.sgarterm != null) {
            this.sgrqartermdate = Util.dateAnd(this.sgsenddate, this.sgarterm.intValue());
        }
        return sgrqartermdate;
    }

	public int getSendCount() {
		return sendCount;
	}

	public void setSendCount(int sendCount) {
		this.sendCount = sendCount;
	}


	public int getMakeInvoiceCount() {
		return makeInvoiceCount;
	}

	public void setMakeInvoiceCount(int makeInvoiceCount) {
		this.makeInvoiceCount = makeInvoiceCount;
	}

	public int getSellBackGoodsCount() {
		return sellBackGoodsCount;
	}

	public void setSellBackGoodsCount(int sellBackGoodsCount) {
		this.sellBackGoodsCount = sellBackGoodsCount;
	}
}
