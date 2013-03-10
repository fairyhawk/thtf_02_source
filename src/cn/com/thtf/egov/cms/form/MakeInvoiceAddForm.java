package cn.com.thtf.egov.cms.form;

import org.apache.struts.action.ActionForm;

import cn.com.thtf.egov.cms.dto.MakeInvoiceAddDto;

public class MakeInvoiceAddForm extends ActionForm{

	    private static final long serialVersionUID = 6289594417484928733L;

	    private String id;
	    
	    private String productTypeId;//产品分类编号 2

	    private String customerId;//客户编号 3
	    
	    private String customerName;//客户名称 4
	    
	    private String invoiceType;//发票类型 5

	    private String status;//状态
	    
	    private String date;//申请日期 7
	    
	    private String userId;//申请人登录名 8
	    
	    private String userName;//申请人用户名 9
	    
	    private String userAreaId;//人员区域名称 10
	    
	    private String text;//特殊说明 11
	    
	    private String notifyDate;//通知 12 
	    
	    private String notifyId;
	    
	    private String notifyName;
	    
	    private String money;//申请金额 15 
	    
	    private String flag;//审批标识   （去掉） 16
	    
	    private String sellMajId;//销售总监登录名 17
	    
	    private String sellMajName;
	    
	    private String sellMajDate;
	    
	    private String sellMajIdea;//标识 20
	    
	    private String sellMajText;
	    
	    private String opeMajId;//运营总监登录名 22
	    
	    private String opeMajName;
	    
	    private String opeMajDate;
	    
	    private String opeMajIdea;
	    
	    private String opeMajText;
	    
	    private String sellAssId;//销售助理登录名 27
	    
	    private String sellAssName;
	    
		private String invoiceBankName;// 开票银行名称
		
		private String invoiceBankAccount;// 开票银行账号
		
		private String taxNumber;// 税号
		
		private String invoiceBankAddress;// 开票银行地址
		
		private String invoiceBankTel;// 开票银行电话
		
		private String productName;//产品名称
		
	    private String dateTime;//时间
	    
	    private MakeInvoiceAddDto makeInvoiceAddDto=new MakeInvoiceAddDto();
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getProductTypeId() {
			return productTypeId;
		}
		public void setProductTypeId(String productTypeId) {
			this.productTypeId = productTypeId;
		}

		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getUserAreaId() {
			return userAreaId;
		}
		public void setUserAreaId(String userAreaId) {
			this.userAreaId = userAreaId;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		public String getNotifyDate() {
			return notifyDate;
		}
		public void setNotifyDate(String notifyDate) {
			this.notifyDate = notifyDate;
		}
		public String getNotifyId() {
			return notifyId;
		}
		public void setNotifyId(String notifyId) {
			this.notifyId = notifyId;
		}
		public String getNotifyName() {
			return notifyName;
		}
		public void setNotifyName(String notifyName) {
			this.notifyName = notifyName;
		}
		public String getMoney() {
			return money;
		}
		public void setMoney(String money) {
			this.money = money;
		}
		public String getFlag() {
			return flag;
		}
		public void setFlag(String flag) {
			this.flag = flag;
		}
		public String getSellMajId() {
			return sellMajId;
		}
		public void setSellMajId(String sellMajId) {
			this.sellMajId = sellMajId;
		}
		public String getSellMajName() {
			return sellMajName;
		}
		public void setSellMajName(String sellMajName) {
			this.sellMajName = sellMajName;
		}
		public String getSellMajDate() {
			return sellMajDate;
		}
		public void setSellMajDate(String sellMajDate) {
			this.sellMajDate = sellMajDate;
		}
		public String getSellMajIdea() {
			return sellMajIdea;
		}
		public void setSellMajIdea(String sellMajIdea) {
			this.sellMajIdea = sellMajIdea;
		}
		public String getSellMajText() {
			return sellMajText;
		}
		public void setSellMajText(String sellMajText) {
			this.sellMajText = sellMajText;
		}
		public String getOpeMajId() {
			return opeMajId;
		}
		public void setOpeMajId(String opeMajId) {
			this.opeMajId = opeMajId;
		}
		public String getOpeMajName() {
			return opeMajName;
		}
		public void setOpeMajName(String opeMajName) {
			this.opeMajName = opeMajName;
		}
		public String getOpeMajDate() {
			return opeMajDate;
		}
		public void setOpeMajDate(String opeMajDate) {
			this.opeMajDate = opeMajDate;
		}
		public String getOpeMajIdea() {
			return opeMajIdea;
		}
		public void setOpeMajIdea(String opeMajIdea) {
			this.opeMajIdea = opeMajIdea;
		}
		public String getOpeMajText() {
			return opeMajText;
		}
		public void setOpeMajText(String opeMajText) {
			this.opeMajText = opeMajText;
		}
		public String getDateTime() {
			return dateTime;
		}
		public void setDateTime(String dateTime) {
			this.dateTime = dateTime;
		}
		public static long getSerialVersionUID() {
			return serialVersionUID;
		}
		public String getCustomerId() {
			return customerId;
		}
		public void setCustomerId(String customerId) {
			this.customerId = customerId;
		}
		public String getCustomerName() {
			return customerName;
		}
		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}
		public String getInvoiceType() {
			return invoiceType;
		}
		public void setInvoiceType(String invoiceType) {
			this.invoiceType = invoiceType;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getSellAssId() {
			return sellAssId;
		}
		public void setSellAssId(String sellAssId) {
			this.sellAssId = sellAssId;
		}
		public String getSellAssName() {
			return sellAssName;
		}
		public void setSellAssName(String sellAssName) {
			this.sellAssName = sellAssName;
		}
		public String getInvoiceBankName() {
			return invoiceBankName;
		}
		public void setInvoiceBankName(String invoiceBankName) {
			this.invoiceBankName = invoiceBankName;
		}
		public String getInvoiceBankAccount() {
			return invoiceBankAccount;
		}
		public void setInvoiceBankAccount(String invoiceBankAccount) {
			this.invoiceBankAccount = invoiceBankAccount;
		}
		public String getTaxNumber() {
			return taxNumber;
		}
		public void setTaxNumber(String taxNumber) {
			this.taxNumber = taxNumber;
		}
		public String getInvoiceBankAddress() {
			return invoiceBankAddress;
		}
		public void setInvoiceBankAddress(String invoiceBankAddress) {
			this.invoiceBankAddress = invoiceBankAddress;
		}
		public String getInvoiceBankTel() {
			return invoiceBankTel;
		}
		public void setInvoiceBankTel(String invoiceBankTel) {
			this.invoiceBankTel = invoiceBankTel;
		}
		public String getProductName() {
			return productName;
		}
		public void setProductName(String productName) {
			this.productName = productName;
		}
		public MakeInvoiceAddDto getMakeInvoiceAddDto() {
			return makeInvoiceAddDto;
		}
		public void setMakeInvoiceAddDto(MakeInvoiceAddDto makeInvoiceAddDto) {
			this.makeInvoiceAddDto = makeInvoiceAddDto;
		}
	   
	}


