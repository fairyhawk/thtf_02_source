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
public class CompanyInfoDto implements Serializable {

	/**
	 * 公司管理
	 */
	private static final long serialVersionUID = 7946901168153686412L;

	private Integer id;// 公司ID

	private String name;// 公司名称
	private String invoice_bank_name;// 开票银行名称

	private String invoice_bank_account;// 开票银行账号

	private String tax_number;// 税号

	private String invoice_bank_address;// 开票银行地址

	private String invoice_bank_tel;// 开票银行电话

	private Integer tax_rate;// 增值税税率

	private String remit_bank_name;// 汇款银行名称

	private String remit_bank_account;// 汇款银行账号

	public String getInvoice_bank_account() {
		return invoice_bank_account;
	}

	public void setInvoice_bank_account(String invoice_bank_account) {
		this.invoice_bank_account = invoice_bank_account;
	}

	public String getInvoice_bank_address() {
		return invoice_bank_address;
	}

	public void setInvoice_bank_address(String invoice_bank_address) {
		this.invoice_bank_address = invoice_bank_address;
	}

	public String getInvoice_bank_name() {
		return invoice_bank_name;
	}

	public void setInvoice_bank_name(String invoice_bank_name) {
		this.invoice_bank_name = invoice_bank_name;
	}

	public String getInvoice_bank_tel() {
		return invoice_bank_tel;
	}

	public void setInvoice_bank_tel(String invoice_bank_tel) {
		this.invoice_bank_tel = invoice_bank_tel;
	}

	public String getRemit_bank_account() {
		return remit_bank_account;
	}

	public void setRemit_bank_account(String remit_bank_account) {
		this.remit_bank_account = remit_bank_account;
	}

	public String getRemit_bank_name() {
		return remit_bank_name;
	}

	public void setRemit_bank_name(String remit_bank_name) {
		this.remit_bank_name = remit_bank_name;
	}

	public String getTax_number() {
		return tax_number;
	}

	public void setTax_number(String tax_number) {
		this.tax_number = tax_number;
	}

	public Integer getTax_rate() {
		return tax_rate;
	}

	public void setTax_rate(Integer tax_rate) {
		this.tax_rate = tax_rate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
