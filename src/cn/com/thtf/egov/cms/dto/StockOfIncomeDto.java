package cn.com.thtf.egov.cms.dto;


public class StockOfIncomeDto {
    private Integer id;

    private Integer productId;

    private Integer stockroomId;

    private Integer num;

    private Integer sendLock;

    private Integer prepared;
    
    private String timeStamp;
    
    private String oldTimeStamp;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getStockroomId() {
		return stockroomId;
	}

	public void setStockroomId(Integer stockroomId) {
		this.stockroomId = stockroomId;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getSendLock() {
		return sendLock;
	}

	public void setSendLock(Integer sendLock) {
		this.sendLock = sendLock;
	}

	public Integer getPrepared() {
		return prepared;
	}

	public void setPrepared(Integer prepared) {
		this.prepared = prepared;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getOldTimeStamp() {
		return oldTimeStamp;
	}

	public void setOldTimeStamp(String oldTimeStamp) {
		this.oldTimeStamp = oldTimeStamp;
	}
}
