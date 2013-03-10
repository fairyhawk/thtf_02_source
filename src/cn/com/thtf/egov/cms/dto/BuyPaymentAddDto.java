package cn.com.thtf.egov.cms.dto;


import javax.servlet.http.HttpServletRequest;

public class BuyPaymentAddDto  {
	/**
	 * 自动序列化
	 */
	private static final long serialVersionUID = -5870983481047766231L;
	
	/** 提交状态    0-保存  1-提交**/
	private String btnClick;
	/** 付款单号 **/
	private String paymentId;
	/** 产品分类编号 **/
	private String productTypeId;
	/** 供货商编号 **/
	private String supplierId;
	/** 供货商名称 **/
	private String supplierName;
	/** 供货商联系人编号 **/
	private String supplierLinkmanId;
	/** 申请日期 **/
	private String date;
	/** 付款方式 **/
	private String paymentWay;
	/** 帐期 **/
	private String arterm;
	/** 付款总额 **/
	private String money;
	/** 付款单状态 **/
	private String status;
	/** 登录名 **/
	private String userId;
	/** 人员名称 **/
	private String userName;
	/** 特殊说明 **/
	private String text;
	/** 合同类型**/
	private String contractType;
	/** 付款单明细 **/
		/* 指定类型 */
		private String appointType;
		/* 入库单ID和产品ID*/
		private String[] inStockIdAndProductId;
		/* 入库单ID */
		private String[] inStockId;
		/* 产品ID */
		private String[] productId;
		/* 合同流水号 */
		private String[] buyContractId;
		/* 入库明细合同流水号*/
		private String[] inStockBuyBuyContractId;
		/* 指定金额 */
		private String[] advanceMoney;
		private String[] appointMoney;
		
	
		
	public String getContractType() {
            return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String[] getInStockBuyBuyContractId() {
        return inStockBuyBuyContractId;
    }

    public void setInStockBuyBuyContractId(String[] inStockBuyBuyContractId) {
        this.inStockBuyBuyContractId = inStockBuyBuyContractId;
    }

    public String[] getInStockId() {
		return inStockId;
	}

	public String[] getProductId() {
		return productId;
	}

	public String[] getBuyContractId() {
		return buyContractId;
	}

	public void setInStockId(String[] inStockId) {
		this.inStockId = inStockId;
	}

	public void setProductId(String[] productId) {
		this.productId = productId;
	}

	public void setBuyContractId(String[] buyContractId) {
		this.buyContractId = buyContractId;
	}

	public String getAppointType() {
		return appointType;
	}

	public void setAppointType(String appointType) {
		this.appointType = appointType;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(String productTypeId) {
		this.productTypeId = productTypeId;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierLinkmanId() {
		return supplierLinkmanId;
	}

	public void setSupplierLinkmanId(String supplierLinkmanId) {
		this.supplierLinkmanId = supplierLinkmanId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPaymentWay() {
		return paymentWay;
	}

	public void setPaymentWay(String paymentWay) {
		this.paymentWay = paymentWay;
	}

	public String getArterm() {
		return arterm;
	}

	public void setArterm(String arterm) {
		this.arterm = arterm;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setInStockIdAndProductId(String[] inStockIdAndProductId) {
		this.inStockIdAndProductId = inStockIdAndProductId;
	}

	public String[] getInStockIdAndProductId() {
		return inStockIdAndProductId;
	}

	public String[] getAdvanceMoney() {
		return advanceMoney;
	}

	public void setAdvanceMoney(String[] advanceMoney) {
		this.advanceMoney = advanceMoney;
	}

	public String[] getAppointMoney() {
		return appointMoney;
	}

	public void setAppointMoney(String[] appointMoney) {
		this.appointMoney = appointMoney;
	}
	

	public void setBtnClick(String btnClick) {
		this.btnClick = btnClick;
	}

	public String getBtnClick() {
		return btnClick;
	}

	
	/**
	 * 创建对象时构造属性值
	 * @param request
	 */
	public BuyPaymentAddDto(HttpServletRequest request){
		btnClick = request.getParameter("btnClick");
	    paymentId = request.getParameter("paymentId");
		productTypeId = request.getParameter("productTypeId");
		supplierId = request.getParameter("supplierId");
		supplierName = request.getParameter("supplierName");
		supplierLinkmanId = request.getParameter("supplierLinkmanId");
		paymentWay = request.getParameter("paymentWay");
		arterm = request.getParameter("arterm");
		inStockId = request.getParameterValues("inStockId");
		productId = request.getParameterValues("productId");
		buyContractId = request.getParameterValues("buyContractId");
		text = request.getParameter("text");
		money = request.getParameter("money");
		advanceMoney = request.getParameterValues("advanceMoney");
		appointMoney = request.getParameterValues("appointMoney");
		inStockBuyBuyContractId = request.getParameterValues("inStockBuyBuyContractId");
		contractType = request.getParameter("contractType");
	}
}
