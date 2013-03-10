package cn.com.thtf.egov.cms.dto;


public class BuyContractDetailDto {

    private String id;

    private String buyContractId;

    private String productId;

    private String count;

    private String price;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBuyContractId() {
		return buyContractId;
	}

	public void setBuyContractId(String buyContractId) {
		this.buyContractId = buyContractId;
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
}
