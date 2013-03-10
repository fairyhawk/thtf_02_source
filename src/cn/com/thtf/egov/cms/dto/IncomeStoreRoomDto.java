package cn.com.thtf.egov.cms.dto;

public class IncomeStoreRoomDto {
    private String stkAdmId;

    private String stkAdmName;

    private String stkAdmDate;

    private String stkAdmIdea;

    private String stkAdmText;
    
    private String billType;//表单类型 1,2,3,4
    
    private Integer status;
    
    private String id;

    private int isSuccess;//是否提交 0  1 
    
    private String timestamp;//时间戳
    
    private String stockroomId;//库房id
    
    private String jsonData;//json产品数据
    
    private String invoiceType;//入库单专用
    
    private String taxRate;//入库单专用
    
    private String sendGoodsId;//销售退货单专用
    
    private String customerCreditId;//销售退货专用 客户信用id
    
    
	public String getStkAdmId() {
		return stkAdmId;
	}

	public void setStkAdmId(String stkAdmId) {
		this.stkAdmId = stkAdmId;
	}

	public String getStkAdmName() {
		return stkAdmName;
	}

	public void setStkAdmName(String stkAdmName) {
		this.stkAdmName = stkAdmName;
	}

	public String getStkAdmDate() {
		return stkAdmDate;
	}

	public void setStkAdmDate(String stkAdmDate) {
		this.stkAdmDate = stkAdmDate;
	}

	public String getStkAdmIdea() {
		return stkAdmIdea;
	}

	public void setStkAdmIdea(String stkAdmIdea) {
		this.stkAdmIdea = stkAdmIdea;
	}

	public String getStkAdmText() {
		return stkAdmText;
	}

	public void setStkAdmText(String stkAdmText) {
		this.stkAdmText = stkAdmText;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(int isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getStockroomId() {
		return stockroomId;
	}

	public void setStockroomId(String stockroomId) {
		this.stockroomId = stockroomId;
	}


	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}

	public String getSendGoodsId() {
		return sendGoodsId;
	}

	public void setSendGoodsId(String sendGoodsId) {
		this.sendGoodsId = sendGoodsId;
	}

	public String getCustomerCreditId() {
		return customerCreditId;
	}

	public void setCustomerCreditId(String customerCreditId) {
		this.customerCreditId = customerCreditId;
	}
}
