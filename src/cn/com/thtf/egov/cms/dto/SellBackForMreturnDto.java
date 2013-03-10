/**
 * ClassName  SellBackForMreturnDto
 *
 * History
 * Create User: liuqg
 * Create Date: 2010-6-1
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

import java.math.BigDecimal;

/**
 * 根据回款单显示退款申请信息用
 * 
 * @author liuqg
 */

public class SellBackForMreturnDto {

	private String customerName;// #客户名称
	private String customerProvince;// 省份
	private String customerCity;// 城市
	private String remitBankName;// 汇款银行名称
	private String remitBankAccount;// 汇款银行帐号
	private String linkMan;// 联系人
	private String tel;// 电话
	private String fax;// 传真
	private String backWay;// 退款方式(页面选择)
	private BigDecimal backMoney;// 退款金额(页面输入的)

	private BigDecimal mreturnMoney;// 回款金额
	private BigDecimal contMoney;// 合同预收金额（指定到合同合计）
	private BigDecimal detailMoney;// 指定金额(指定到发货明细的合计)
	private BigDecimal backSuccessmoney;// 已退款金额（退款表中成功退款记录的合计）

	private BigDecimal prodMoney;// 产品分类预收金额(回款-指定金额-合同预收金额-已退款金额)
	private BigDecimal mreturnCanBackMoney;// 回款可退金额(回款-合同-指定-退款金额(2,4,6,7,8))
	private BigDecimal prodMoneySum;// 产品分类预收金额合计
	private BigDecimal proCanBackMoney;// 产品分类可退款金额

	/**
	 * @return the mreturnCanBackMoney
	 */
	public BigDecimal getMreturnCanBackMoney() {
		return mreturnCanBackMoney;
	}

	/**
	 * @param mreturnCanBackMoney
	 *            the mreturnCanBackMoney to set
	 */
	public void setMreturnCanBackMoney(BigDecimal mreturnCanBackMoney) {
		this.mreturnCanBackMoney = mreturnCanBackMoney;
	}

	/**
	 * @return the prodMoneySum
	 */
	public BigDecimal getProdMoneySum() {
		return prodMoneySum;
	}

	/**
	 * @param prodMoneySum
	 *            the prodMoneySum to set
	 */
	public void setProdMoneySum(BigDecimal prodMoneySum) {
		this.prodMoneySum = prodMoneySum;
	}

	/**
	 * @return the proCanBackMoney
	 */
	public BigDecimal getProCanBackMoney() {
		return proCanBackMoney;
	}

	/**
	 * @param proCanBackMoney
	 *            the proCanBackMoney to set
	 */
	public void setProCanBackMoney(BigDecimal proCanBackMoney) {
		this.proCanBackMoney = proCanBackMoney;
	}

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName
	 *            the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return the customerProvince
	 */
	public String getCustomerProvince() {
		return customerProvince;
	}

	/**
	 * @param customerProvince
	 *            the customerProvince to set
	 */
	public void setCustomerProvince(String customerProvince) {
		this.customerProvince = customerProvince;
	}

	/**
	 * @return the customerCity
	 */
	public String getCustomerCity() {
		return customerCity;
	}

	/**
	 * @param customerCity
	 *            the customerCity to set
	 */
	public void setCustomerCity(String customerCity) {
		this.customerCity = customerCity;
	}

	/**
	 * @return the remitBankName
	 */
	public String getRemitBankName() {
		return remitBankName;
	}

	/**
	 * @param remitBankName
	 *            the remitBankName to set
	 */
	public void setRemitBankName(String remitBankName) {
		this.remitBankName = remitBankName;
	}

	/**
	 * @return the remitBankAccount
	 */
	public String getRemitBankAccount() {
		return remitBankAccount;
	}

	/**
	 * @param remitBankAccount
	 *            the remitBankAccount to set
	 */
	public void setRemitBankAccount(String remitBankAccount) {
		this.remitBankAccount = remitBankAccount;
	}

	/**
	 * @return the linkMan
	 */
	public String getLinkMan() {
		return linkMan;
	}

	/**
	 * @param linkMan
	 *            the linkMan to set
	 */
	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	/**
	 * @return the tel
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * @param tel
	 *            the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}

	/**
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * @param fax
	 *            the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * @return the backWay
	 */
	public String getBackWay() {
		return backWay;
	}

	/**
	 * @param backWay
	 *            the backWay to set
	 */
	public void setBackWay(String backWay) {
		this.backWay = backWay;
	}

	/**
	 * @return the backMoney
	 */
	public BigDecimal getBackMoney() {
		return backMoney;
	}

	/**
	 * @param backMoney
	 *            the backMoney to set
	 */
	public void setBackMoney(BigDecimal backMoney) {
		this.backMoney = backMoney;
	}

	/**
	 * @return the mreturnMoney
	 */
	public BigDecimal getMreturnMoney() {
		return mreturnMoney;
	}

	/**
	 * @param mreturnMoney
	 *            the mreturnMoney to set
	 */
	public void setMreturnMoney(BigDecimal mreturnMoney) {
		this.mreturnMoney = mreturnMoney;
	}

	/**
	 * @return the contMoney
	 */
	public BigDecimal getContMoney() {
		return contMoney;
	}

	/**
	 * @param contMoney
	 *            the contMoney to set
	 */
	public void setContMoney(BigDecimal contMoney) {
		this.contMoney = contMoney;
	}

	/**
	 * @return the detailMoney
	 */
	public BigDecimal getDetailMoney() {
		return detailMoney;
	}

	/**
	 * @param detailMoney
	 *            the detailMoney to set
	 */
	public void setDetailMoney(BigDecimal detailMoney) {
		this.detailMoney = detailMoney;
	}

	/**
	 * @return the backSuccessmoney
	 */
	public BigDecimal getBackSuccessmoney() {
		return backSuccessmoney;
	}

	/**
	 * @param backSuccessmoney
	 *            the backSuccessmoney to set
	 */
	public void setBackSuccessmoney(BigDecimal backSuccessmoney) {
		this.backSuccessmoney = backSuccessmoney;
	}

	/**
	 * @return the prodMoney
	 */
	public BigDecimal getProdMoney() {
		return prodMoney;
	}

	/**
	 * @param prodMoney
	 *            the prodMoney to set
	 */
	public void setProdMoney(BigDecimal prodMoney) {
		this.prodMoney = prodMoney;
	}

}
