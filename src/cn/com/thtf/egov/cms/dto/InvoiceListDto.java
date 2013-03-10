package cn.com.thtf.egov.cms.dto;

import java.util.List;

import cn.com.thtf.egov.cms.entity.UserAreaProductEntity;


/**
 * 开票管理
 * 
 * @author hanrb
 * 
 */
public class InvoiceListDto{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;// 开票申请单号 1
	private String productTypeName;// 产品分类名称 2
	private String productTypeId;// 产品分类名称 2
	private String customerName;// 客户名称 3
	private String money;//申请金额 4
	private String invoiceMoney;//发票金额 5
	private String returnMoney;//退票金额 6
	private String invoiceType;// 发票类型 7
	private String date;// 申请起始日期 8
	private String notifyDate;// 通知起始日期 9
	private String userName;// 申请人 10
	private String notifyName;// 通知人 11
	private String status;// 开票状态 12
	private String confirmDate;// 确认日期
	
	
	
	
	/**
     * @return the confirmDate
     */
    public String getConfirmDate() {
        return confirmDate;
    }
    /**
     * @param confirmDate the confirmDate to set
     */
    public void setConfirmDate(String confirmDate) {
        this.confirmDate = confirmDate;
    }
    private String applyEndTime;// 申请终止日期
	private String notifyEndTime;// 通知终止日期
	
	//private int roleId;//开票状态
	//private int areaId;//区域id
	private String userId;//用户id
	private String type;//类型 是否经理
	
	private String sendGoodsId;//发货单号
	
	private int sellBackGoodsCount;
	private List<UserAreaProductEntity> userAreaProductList;
	public String getId() {
		return id!=null?id.trim():id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProductTypeName() {
		return productTypeName;
	}
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
	public String getCustomerName() {
		return customerName!=null?customerName.trim():customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getInvoiceMoney() {
		return invoiceMoney;
	}
	public void setInvoiceMoney(String invoiceMoney) {
		this.invoiceMoney = invoiceMoney;
	}
	public String getReturnMoney() {
		return returnMoney;
	}
	public void setReturnMoney(String returnMoney) {
		this.returnMoney = returnMoney;
	}
	public String getInvoiceType() {
		return invoiceType;
	}
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getNotifyDate() {
		return notifyDate;
	}
	public void setNotifyDate(String notifyDate) {
		this.notifyDate = notifyDate;
	}
	public String getUserName() {
		return userName!=null?userName.trim():userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getNotifyName() {
		return notifyName!=null?notifyName.trim():notifyName;
	}
	public void setNotifyName(String notifyName) {
		this.notifyName = notifyName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getApplyEndTime() {
		return applyEndTime;
	}
	public void setApplyEndTime(String applyEndTime) {
		this.applyEndTime = applyEndTime;
	}
	public String getNotifyEndTime() {
		return notifyEndTime;
	}
	public void setNotifyEndTime(String notifyEndTime) {
		this.notifyEndTime = notifyEndTime;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public String getProductTypeId() {
		return productTypeId;
	}
	public void setProductTypeId(String productTypeId) {
		this.productTypeId = productTypeId;
	}
	public List<UserAreaProductEntity> getUserAreaProductList() {
		return userAreaProductList;
	}
	public void setUserAreaProductList(
			List<UserAreaProductEntity> userAreaProductList) {
		this.userAreaProductList = userAreaProductList;
	}
	public String getSendGoodsId() {
		return sendGoodsId;
	}
	public void setSendGoodsId(String sendGoodsId) {
		this.sendGoodsId = sendGoodsId;
	}
	public int getSellBackGoodsCount() {
		return sellBackGoodsCount;
	}
	public void setSellBackGoodsCount(int sellBackGoodsCount) {
		this.sellBackGoodsCount = sellBackGoodsCount;
	}

	

}
