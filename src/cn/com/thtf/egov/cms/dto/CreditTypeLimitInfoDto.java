/**
 * ClassName  AreaInfo
 *
 * History
 * Create User: Administrator
 * Create Date: 2009-12-16
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

import java.io.Serializable;

/**
 * @author Administrator
 * 
 */
public class CreditTypeLimitInfoDto implements Serializable {

	/**
	 * 信用类型额度
	 */
	private static final long serialVersionUID = 7946901168153686412L;

	private Integer id;//

	private Integer product_type_id;// 产品分类Id

	private Integer pid;

	private Integer credit_type_id;// 信用类型Id

	private String climit;// 该产品，该信用类型总信用额度

	private String hlimit;// 该产品，该信用类型已分配额度

	private String nlimit;// 该产品，该信用类型未分配额度

	private String uses;// 该产品，该信用类型已用额度

	private String lockss;// 该产品，该信用类型冻结额度

	private String free;// 该产品，该信用类型可用额度

	private String canuse;// 该产品，该信用类型可用额度

	private String plimit;// 该产品大类总信用额度

	private String phlimit;// 该产品大类已分配额度

	private String pnlimit;// 该产品大类未分配额度

	private String productname;// 产品分类名称

	private String ctname;// 信用类型名称

	public String getCtname() {
		return ctname;
	}

	public void setCtname(String ctname) {
		this.ctname = ctname;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getCredit_type_id() {
		return credit_type_id;
	}

	public void setCredit_type_id(Integer credit_type_id) {
		this.credit_type_id = credit_type_id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProduct_type_id() {
		return product_type_id;
	}

	public void setProduct_type_id(Integer product_type_id) {
		this.product_type_id = product_type_id;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public void setCanuse(String canuse) {
		this.canuse = canuse;
	}

	public String getHlimit() {
		return hlimit;
	}

	public void setHlimit(String hlimit) {
		this.hlimit = hlimit;
	}

	public String getNlimit() {
		return nlimit;
	}

	public void setNlimit(String nlimit) {
		this.nlimit = nlimit;
	}

	public String getLockss() {
		return lockss;
	}

	public void setLockss(String lockss) {
		this.lockss = lockss;
	}

	public String getUses() {
		return uses;
	}

	public void setUses(String uses) {
		this.uses = uses;
	}

	public void setClimit(String climit) {
		this.climit = climit;
	}

	public String getCanuse() {
		return canuse;
	}

	public String getClimit() {
		return climit;
	}

	public String getFree() {
		return free;
	}

	public void setFree(String free) {
		this.free = free;
	}

	public String getPhlimit() {
		return phlimit;
	}

	public void setPhlimit(String phlimit) {
		this.phlimit = phlimit;
	}

	public String getPlimit() {
		return plimit;
	}

	public void setPlimit(String plimit) {
		this.plimit = plimit;
	}

	public String getPnlimit() {
		return pnlimit;
	}

	public void setPnlimit(String pnlimit) {
		this.pnlimit = pnlimit;
	}

}
