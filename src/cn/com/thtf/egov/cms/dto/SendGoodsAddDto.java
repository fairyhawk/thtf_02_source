/**
 * ClassName  SendGoodsAddDto
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-4-28
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

import java.util.List;

import cn.com.thtf.egov.cms.entity.StockEntity;

/**
 * SendGoodsAddDto
 * 
 * @author Lubo
 */

public class SendGoodsAddDto {

    /** 付款方式 */
    private Integer paymentWay;
    /** 发货金额 */
    private String sendGoodsMoney;
    /** 客户信用ID */
    private Integer customerCreditId;
    /** 发货单号 */
    private String id;
    /** 销售合同编号 */
    private String sellContractId;
    /** 现结金额 */
    private String cashMoney;

    /** 产品分类编号 */
    private Integer productTypeId;
    /** 客户编号 */
    private Integer customerId;
    /** 客户名称 */
    private String customerName;
    /** 发货单类型 */
    private String sendGoodsType;
    /** 申请日期 */
    private String date;

    /** 要求发货日期 */
    private String requestDate;
    /** 实际发货日期 */
    private String sendDate;
    /** 信用类型编号 */
    private String creditTypeId;
    /** 项目名称 */
    private String projectName;
    /** 帐期 */
    private String arterm;

    /** 信用额度 */
    private String climit;
    /** 可用额度 */
    private String free;
    /** 回款预收金额 */
    private String prepayMoney;
    /** 在途预收金额 */
    private String transitMoney;

    /** 发货单状态 */
    private String status;
    /** 库房编号 */
    private String stockroomId;
    /** 客户发货地址编号 */
    private String customerAddressId;

    /** 货运方式 */
    private String transportWay;
    /** 提货人姓名 */
    private String takeName;
    /** 提货人身份证号 */
    private String takeNumber;

    /** 登录名 */
    private String userId;
    /** 人员名称 */
    private String userName;
    /** 人员区域编号 */
    private String userAreaId;
    /** 特殊说明 */
    private String text;

    /** 新建日期 */
    private String datetime;

    /** 产品编号 */
    private String productId;
    /** 发货数 */
    private String count;
    /** 库存单价 */
    private String price;

    /** 产品编号 */
    private String[] productIdArr;
    /** 发货数 */
    private String[] countArr;
    /** 销售单价 */
    private String[] moneyArr;

    /** 提交还是保存 */
    private String submitType;
    /** 新增还是修改 */
    private boolean pageType;

    /** 信用判断结果 */
    private CreditLogicResultDto creditLogicResultDto;
    /** 客户信用 */
    private CustomerCreditCommonDto customerCredit;
    /** 回款 */
    private List<MreturnDto> mreturnList;
    /** 回款 正常 */
    private List<MreturnDto> returnDetailList;
    /** 回款 在途 */
    private List<MreturnDto> returnDetailInList;
    /** 库存 */
    private List<StockEntity> stockList;

    /** 宽限天数 */
    private Integer extendDays;

    /** 0:选择的 其它:手添的 */
    private String caType;

    /** 货物接收单位名称 */
    private String caName;
    /** 发货地址 */
    private String caAddress;
    /** 邮编 */
    private String caPostcode;
    /** 联系人 */
    private String caLinkman;
    /** 电话 */
    private String caTel;

    /** 手机 */
    private String caMobile;

    public Integer getPaymentWay() {
        return paymentWay;
    }

    public void setPaymentWay(Integer paymentWay) {
        this.paymentWay = paymentWay;
    }

    public String getSendGoodsMoney() {
        return sendGoodsMoney;
    }

    public void setSendGoodsMoney(String sendGoodsMoney) {
        this.sendGoodsMoney = sendGoodsMoney;
    }

    public Integer getCustomerCreditId() {
        return customerCreditId;
    }

    public void setCustomerCreditId(Integer customerCreditId) {
        this.customerCreditId = customerCreditId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSellContractId() {
        return sellContractId;
    }

    public void setSellContractId(String sellContractId) {
        this.sellContractId = sellContractId;
    }

    public String getCashMoney() {
        return cashMoney;
    }

    public void setCashMoney(String cashMoney) {
        this.cashMoney = cashMoney;
    }

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSendGoodsType() {
        return sendGoodsType;
    }

    public void setSendGoodsType(String sendGoodsType) {
        this.sendGoodsType = sendGoodsType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public String getCreditTypeId() {
        return creditTypeId;
    }

    public void setCreditTypeId(String creditTypeId) {
        this.creditTypeId = creditTypeId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getArterm() {
        return arterm;
    }

    public void setArterm(String arterm) {
        this.arterm = arterm;
    }

    public String getClimit() {
        return climit;
    }

    public void setClimit(String climit) {
        this.climit = climit;
    }

    public String getFree() {
        return free;
    }

    public void setFree(String free) {
        this.free = free;
    }

    public String getPrepayMoney() {
        return prepayMoney;
    }

    public void setPrepayMoney(String prepayMoney) {
        this.prepayMoney = prepayMoney;
    }

    public String getTransitMoney() {
        return transitMoney;
    }

    public void setTransitMoney(String transitMoney) {
        this.transitMoney = transitMoney;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStockroomId() {
        return stockroomId;
    }

    public void setStockroomId(String stockroomId) {
        this.stockroomId = stockroomId;
    }

    public String getCustomerAddressId() {
        return customerAddressId;
    }

    public void setCustomerAddressId(String customerAddressId) {
        this.customerAddressId = customerAddressId;
    }

    public String getTransportWay() {
        return transportWay;
    }

    public void setTransportWay(String transportWay) {
        this.transportWay = transportWay;
    }

    public String getTakeName() {
        return takeName;
    }

    public void setTakeName(String takeName) {
        this.takeName = takeName;
    }

    public String getTakeNumber() {
        return takeNumber;
    }

    public void setTakeNumber(String takeNumber) {
        this.takeNumber = takeNumber;
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

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
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

    public String[] getProductIdArr() {
        return productIdArr;
    }

    public void setProductIdArr(String[] productIdArr) {
        this.productIdArr = productIdArr;
    }

    public String[] getCountArr() {
        return countArr;
    }

    public void setCountArr(String[] countArr) {
        this.countArr = countArr;
    }

    public String[] getMoneyArr() {
        return moneyArr;
    }

    public void setMoneyArr(String[] moneyArr) {
        this.moneyArr = moneyArr;
    }

    public String getSubmitType() {
        return submitType;
    }

    public void setSubmitType(String submitType) {
        this.submitType = submitType;
    }

    public boolean isPageType() {
        return pageType;
    }

    public void setPageType(boolean pageType) {
        this.pageType = pageType;
    }

    public CreditLogicResultDto getCreditLogicResultDto() {
        return creditLogicResultDto;
    }

    public void setCreditLogicResultDto(CreditLogicResultDto creditLogicResultDto) {
        this.creditLogicResultDto = creditLogicResultDto;
    }

    public CustomerCreditCommonDto getCustomerCredit() {
        return customerCredit;
    }

    public void setCustomerCredit(CustomerCreditCommonDto customerCredit) {
        this.customerCredit = customerCredit;
    }

    public List<MreturnDto> getMreturnList() {
        return mreturnList;
    }

    public void setMreturnList(List<MreturnDto> mreturnList) {
        this.mreturnList = mreturnList;
    }

    public List<MreturnDto> getReturnDetailList() {
        return returnDetailList;
    }

    public void setReturnDetailList(List<MreturnDto> returnDetailList) {
        this.returnDetailList = returnDetailList;
    }

    public List<MreturnDto> getReturnDetailInList() {
        return returnDetailInList;
    }

    public void setReturnDetailInList(List<MreturnDto> returnDetailInList) {
        this.returnDetailInList = returnDetailInList;
    }

    public List<StockEntity> getStockList() {
        return stockList;
    }

    public void setStockList(List<StockEntity> stockList) {
        this.stockList = stockList;
    }

    public Integer getExtendDays() {
        return extendDays;
    }

    public void setExtendDays(Integer extendDays) {
        this.extendDays = extendDays;
    }

    public String getCaType() {
        return caType;
    }

    public void setCaType(String caType) {
        this.caType = caType;
    }

    public String getCaName() {
        return caName;
    }

    public void setCaName(String caName) {
        this.caName = caName;
    }

    public String getCaAddress() {
        return caAddress;
    }

    public void setCaAddress(String caAddress) {
        this.caAddress = caAddress;
    }

    public String getCaPostcode() {
        return caPostcode;
    }

    public void setCaPostcode(String caPostcode) {
        this.caPostcode = caPostcode;
    }

    public String getCaLinkman() {
        return caLinkman;
    }

    public void setCaLinkman(String caLinkman) {
        this.caLinkman = caLinkman;
    }

    public String getCaTel() {
        return caTel;
    }

    public void setCaTel(String caTel) {
        this.caTel = caTel;
    }

    public String getCaMobile() {
        return caMobile;
    }

    public void setCaMobile(String caMobile) {
        this.caMobile = caMobile;
    }

}
