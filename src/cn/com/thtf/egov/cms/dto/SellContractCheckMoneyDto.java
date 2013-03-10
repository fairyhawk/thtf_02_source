package cn.com.thtf.egov.cms.dto;

import java.io.Serializable;

/**
 * @author llw
 * 
 *         销售合同查看的金额类
 * 
 */
public class SellContractCheckMoneyDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Double send_money; // 发货金额

	private Double prepared_money; // 备货金额

	private Double back_money; // 退货金额

	private Double back_contract_money; // 退货合同金额

	private Double make_invoice_money; // 开票金额

	private Double sure_money; // 指定金额

	public SellContractCheckMoneyDto() {

	}

	public double getBack_contract_money() {
		return back_contract_money;
	}

	public void setBack_contract_money(double back_contract_money) {
		this.back_contract_money = back_contract_money;
	}

	public double getBack_money() {
		return back_money;
	}

	public void setBack_money(double back_money) {
		this.back_money = back_money;
	}

	public double getPrepared_money() {
		return prepared_money;
	}

	public void setPrepared_money(double prepared_money) {
		this.prepared_money = prepared_money;
	}

	public double getSend_money() {
		return send_money;
	}

	public void setSend_money(double send_money) {
		this.send_money = send_money;
	}

	public double getMake_invoice_money() {
		return make_invoice_money;
	}

	public void setMake_invoice_money(double make_invoice_money) {
		this.make_invoice_money = make_invoice_money;
	}

	public double getSure_money() {
		return sure_money;
	}

	public void setSure_money(double sure_money) {
		this.sure_money = sure_money;
	}

}
