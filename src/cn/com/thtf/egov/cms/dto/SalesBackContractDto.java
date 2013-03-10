package cn.com.thtf.egov.cms.dto;

import java.math.BigDecimal;

/**
 * 销售退货合同Dto
 * 
 * @author lilewei
 */
public class SalesBackContractDto {

    private String id; // id

    private String salesContractId;// 合同流水号

    private String productContractCode;// 产品合同号

    private String companyContractCode;// 公司合同号

    private String date;// 签订日期

    private String place;// 签订地点

    private String file;//

    private Integer status;// 合同状态

    private String templateType;// 模板类型

    private Integer productTypeId;

    private String productTypeName;//

    private String productDeptFax;// 产品部门传真

    private String productDeptAccount;// 产品部门账号

    private String customerName; // 客户名字

    private Integer companyType;//

    private Integer companyId;//

    private Integer addressId;//

    private Integer backWay;// 回款方式

    private String dateTime; // 签订日期

    private String backDate;// 退货日期

    private String text;// 特殊说明

    private String userId;// 人员id

    private String userName;// 人员名称

    private String userTel;// 人员电话

    private Integer userAreaId;// 人员区域编号

    private String money;// 退货金额

    private String legalId;// 法务专员登录名

    private String legalName;// 法务专员用户名

    private String legalDate;// 法务专员评审日期

    private String legalIdea;// 法务专员评审意见

    private String legalText;// 法务专员补充说明

    private String sellMajId;// 销售总监登录名

    private String sellMajName;// 销售总监用户名

    private String sellMajDate;// 销售总监评审日期

    private String sellMajIdea;// 销售总监评审意见（插入数据库用）

    private String sellMajIdea1;// 销售总监评审意见1

    private String sellMajIdea2;// 销售总监评审意见2

    private String sellMajIdea3;// 销售总监评审意见3

    private String sellMajText;// 销售总监补充说明

    private String opeMajId;// 运营总监登录名

    private String opeMajName;// 运营总监用户名

    private String opeMajDate;// 运营总监评审日期

    private String opeMajIdea;// 运营总监评审意见

    private String opeMajText;// 运营总监补充说明
    
	private String areaMajId;//区域总监登录名
	
	private String areaMajName;//区域总监用户名
	
	private String areaMajIdea;//区域总监评审意见
	
	private String areaMajIdea1;// 区域总监意见1

	private String areaMajIdea2;// 区域总监意见2

	private String areaMajIdea3;// 区域总监意见3
	
	private String areaMajText;//区域总监补充说明
	
	private String areaMajDate;//区域总监评审日期

    private String signDate; // 销售合同签订日期

    private String signPlace;// 销售合同签订地址

    private BigDecimal contractMoney;// 合同金额

    private BigDecimal advanceMoney;// 预收金额

    private String linkman; // 客户联系人

    private String customerProvince;// 客户省份

    private String customerCity;// 客户城市

    private String linkmanFax; // 联系人传真

    private String linkmanTel;// 联系人电话

    private String invoiceBankName; // 开票银行名

    private String invoiceBankAccount; // 开票银行账号

    private String invoiceType; // 开票类型

    private String customerTaxNumber;// 客户税号

    private String stockRoomName;// 库房名

    private String receiverUnitName; // 收货单位

    private String receiverAddress;// 收货地址

    private String receiverLinkman;// 收货联系人

    private String receiverPostcode;// 收货邮编

    private String receiverTel;// 收货电话

    private String receiverMobile;// 收货手机
    
    private String productContractCodeOfBack;// 产品合同号

    private String companyContractCodeOfBack;// 公司合同号

    public String getProductContractCodeOfBack() {
		return productContractCodeOfBack;
	}

	public void setProductContractCodeOfBack(String productContractCodeOfBack) {
		this.productContractCodeOfBack = productContractCodeOfBack;
	}

	public String getCompanyContractCodeOfBack() {
		return companyContractCodeOfBack;
	}

	public void setCompanyContractCodeOfBack(String companyContractCodeOfBack) {
		this.companyContractCodeOfBack = companyContractCodeOfBack;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public BigDecimal getContractMoney() {
        return contractMoney;
    }

    public String getSignDate() {
        return signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }

    public void setContractMoney(BigDecimal contractMoney) {
        this.contractMoney = contractMoney;
    }

    public BigDecimal getAdvanceMoney() {
        return advanceMoney;
    }

    public void setAdvanceMoney(BigDecimal advanceMoney) {
        this.advanceMoney = advanceMoney;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getCustomerProvince() {
        return customerProvince;
    }

    public void setCustomerProvince(String customerProvince) {
        this.customerProvince = customerProvince;
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }

    public String getLinkmanFax() {
        return linkmanFax;
    }

    public void setLinkmanFax(String linkmanFax) {
        this.linkmanFax = linkmanFax;
    }

    public String getLinkmanTel() {
        return linkmanTel;
    }

    public void setLinkmanTel(String linkmanTel) {
        this.linkmanTel = linkmanTel;
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

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getCustomerTaxNumber() {
        return customerTaxNumber;
    }

    public void setCustomerTaxNumber(String customerTaxNumber) {
        this.customerTaxNumber = customerTaxNumber;
    }

    public String getStockRoomName() {
        return stockRoomName;
    }

    public void setStockRoomName(String stockRoomName) {
        this.stockRoomName = stockRoomName;
    }

    public String getReceiverUnitName() {
        return receiverUnitName;
    }

    public void setReceiverUnitName(String receiverUnitName) {
        this.receiverUnitName = receiverUnitName;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getReceiverLinkman() {
        return receiverLinkman;
    }

    public void setReceiverLinkman(String receiverLinkman) {
        this.receiverLinkman = receiverLinkman;
    }

    public String getReceiverPostcode() {
        return receiverPostcode;
    }

    public void setReceiverPostcode(String receiverPostcode) {
        this.receiverPostcode = receiverPostcode;
    }

    public String getReceiverTel() {
        return receiverTel;
    }

    public void setReceiverTel(String receiverTel) {
        this.receiverTel = receiverTel;
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    public String getSalesContractId() {
        return salesContractId;
    }

    public void setSalesContractId(String salesContractId) {
        this.salesContractId = salesContractId;
    }

    public String getProductContractCode() {
        return productContractCode;
    }

    public void setProductContractCode(String productContractCode) {
        this.productContractCode = productContractCode;
    }

    public String getCompanyContractCode() {
        return companyContractCode;
    }

    public void setCompanyContractCode(String companyContractCode) {
        this.companyContractCode = companyContractCode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    public Integer getCompanyType() {
        return companyType;
    }

    public void setCompanyType(Integer companyType) {
        this.companyType = companyType;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getBackWay() {
        return backWay;
    }

    public void setBackWay(Integer backWay) {
        this.backWay = backWay;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    public Integer getUserAreaId() {
        return userAreaId;
    }

    public void setUserAreaId(Integer userAreaId) {
        this.userAreaId = userAreaId;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getLegalId() {
        return legalId;
    }

    public void setLegalId(String legalId) {
        this.legalId = legalId;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public String getLegalDate() {
        return legalDate;
    }

    public void setLegalDate(String legalDate) {
        this.legalDate = legalDate;
    }

    public String getLegalIdea() {
        return legalIdea;
    }

    public void setLegalIdea(String legalIdea) {
        this.legalIdea = legalIdea;
    }

    public String getLegalText() {
        return legalText;
    }

    public void setLegalText(String legalText) {
        this.legalText = legalText;
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

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getSellMajIdea1() {
        return sellMajIdea1;
    }

    public void setSellMajIdea1(String sellMajIdea1) {
        this.sellMajIdea1 = sellMajIdea1;
    }

    public String getSellMajIdea2() {
        return sellMajIdea2;
    }

    public void setSellMajIdea2(String sellMajIdea2) {
        this.sellMajIdea2 = sellMajIdea2;
    }

    public String getSellMajIdea3() {
        return sellMajIdea3;
    }

    public void setSellMajIdea3(String sellMajIdea3) {
        this.sellMajIdea3 = sellMajIdea3;
    }

    public String getProductDeptFax() {
        return productDeptFax;
    }

    public void setProductDeptFax(String productDeptFax) {
        this.productDeptFax = productDeptFax;
    }

    public String getProductDeptAccount() {
        return productDeptAccount;
    }

    public void setProductDeptAccount(String productDeptAccount) {
        this.productDeptAccount = productDeptAccount;
    }

    public String getBackDate() {
        return backDate;
    }

    public void setBackDate(String backDate) {
        this.backDate = backDate;
    }

    public String getSignPlace() {
        return signPlace;
    }

    public void setSignPlace(String signPlace) {
        this.signPlace = signPlace;
    }

	
	public String getAreaMajId() {
		return areaMajId;
	}

	public void setAreaMajId(String areaMajId) {
		this.areaMajId = areaMajId;
	}

	public String getAreaMajName() {
		return areaMajName;
	}

	public void setAreaMajName(String areaMajName) {
		this.areaMajName = areaMajName;
	}

	public String getAreaMajIdea() {
		if(this.areaMajIdea1!=null&&this.areaMajIdea2!=null&&this.areaMajIdea3!=null){
			areaMajIdea=this.areaMajIdea1+this.areaMajIdea2+this.areaMajIdea3;
		}
		return areaMajIdea;
	}

	public void setAreaMajIdea(String areaMajIdea) {
		if(areaMajIdea!=null&&areaMajIdea.length()==3){
			this.areaMajIdea1=areaMajIdea.substring(0,1);
			this.areaMajIdea2=areaMajIdea.substring(1,2);
			this.areaMajIdea3=areaMajIdea.substring(2,3);
		}
		this.areaMajIdea = areaMajIdea;
	}

	public String getAreaMajIdea1() {
		return areaMajIdea1;
	}

	public void setAreaMajIdea1(String areaMajIdea1) {
		this.areaMajIdea1 = areaMajIdea1;
	}

	public String getAreaMajIdea2() {
		return areaMajIdea2;
	}

	public void setAreaMajIdea2(String areaMajIdea2) {
		this.areaMajIdea2 = areaMajIdea2;
	}

	public String getAreaMajIdea3() {
		return areaMajIdea3;
	}

	public void setAreaMajIdea3(String areaMajIdea3) {
		this.areaMajIdea3 = areaMajIdea3;
	}

	public String getAreaMajText() {
		return areaMajText;
	}

	public void setAreaMajText(String areaMajText) {
		this.areaMajText = areaMajText;
	}

	public String getAreaMajDate() {
		return areaMajDate;
	}

	public void setAreaMajDate(String areaMajDate) {
		this.areaMajDate = areaMajDate;
	}
}
