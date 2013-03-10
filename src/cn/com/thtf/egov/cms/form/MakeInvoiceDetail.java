package cn.com.thtf.egov.cms.form;


import org.apache.struts.action.ActionForm;

public class MakeInvoiceDetail extends ActionForm{


    private static final long serialVersionUID = -2130726262706333449L;

    private Integer id;

    private String makeInvoiceId;

    private String sendGoodsId;

    private String productId;

    private String count;

    private String money;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMakeInvoiceId() {
		return makeInvoiceId;
	}

	public void setMakeInvoiceId(String makeInvoiceId) {
		this.makeInvoiceId = makeInvoiceId;
	}

	public String getSendGoodsId() {
		return sendGoodsId;
	}

	public void setSendGoodsId(String sendGoodsId) {
		this.sendGoodsId = sendGoodsId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

    

}
