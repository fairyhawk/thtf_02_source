/**
 * ClassName  ModifyBuyBackGoodsInfoDto
 *
 * History
 * Create User: zhangzx
 * Create Date: 2010-6-17
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

import java.math.BigDecimal;

/**
 * 修改采购返厂dto
 * 
 * @author zhangzx
 */

public class ModifyBuyBackGoodsInfoDto {

    private String id;// 返厂单号

    private String stockRoomName;// 库房名称
    
    private String stockroomId; // 库房编号

    private String productContractCode;// 产品合同号

    private String companyContractCode;// 公司合同号

    private Integer productTypeId;// 产品分类编号

    private String productTypeName;// 产品分类名称

    private String supplierName;// 供货商名称

    private BigDecimal money;// 返厂单金额

    private String userName;// 人员名称

    private String date;// 申请日期
    
    private String requestDate;//要求发货日期
    
    private String transportWay;//货运方式

    private String sendDate;// 实际返厂日期
    
    private String takeName;//提货人姓名
    
    private String takeNumber;//提货人身份证号

    private Integer status;// 返厂单状态

    private String instockId;//入库单号

    private String text;//特殊说明
    
    private String supplierId; //供货商id
    
    private String supplierLinkman; //供货商联系人
    
    private String supplierMobile; //供货商手机
    
    private String receiveName; //货物接收单位名称
    
    private String supplierPostcode;//供货商邮编
    
    private String supplierAddress;//发货地址
    
    private String supplierAddressId;//供货商发货地址编号
    
    private String supplierTel;//供货商电话

    private String proMajId;//产品总监登录名

    private String proMajName;//产品总监用户名

    private String proMajDate;//产品总监评审日期

    private String proMajIdea;//产品总监评审意见
    
    private String proMajIdea1;//产品总监评审意见1
    
    private String proMajIdea2;//产品总监评审意见2

    private String proMajText;//产品总监补充说明
    
    private String proMajFlag;//有无产品总监意见标示

    private String buyManId;//采购主管登录名

    private String buyManName;//采购主管用户名

    private String buyManDate;//采购主管评审日期

    private String buyManIdea;//采购主管评审意见

    private String buyManText;//采购主管补充说明
    
    private String buyManFlag;//有无采购主管意见标示

    private String opeMajId;//运营总监登录名

    private String opeMajName;//运营总监用户名

    private String opeMajDate;//运营总监评审日期

    private String opeMajIdea;//运营总监评审意见

    private String opeMajText;//运营总监补充说明
    
    private String opeMajFlag;//有无运营总监意见标示

    private String stkAdmId;//库房管理员登录名

    private String stkAdmName;//库房管理员用户名

    private String stkAdmDate;//库房管理员评审日期

    private String stkAdmIdea;//库房管理员评审意见
    
    private String stkAdmIdea1;//库房管理员评审意见1
    
    private String stkAdmIdea2;//库房管理员评审意见2
    
    private String stkAdmIdea3;//库房管理员评审意见3

    private String stkAdmText;//库房管理员补充说明
    
    private String stkAdmFlag;//有无库房管理员意见标示

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the stockRoomName
     */
    public String getStockRoomName() {
        return stockRoomName;
    }

    /**
     * @param stockRoomName the stockRoomName to set
     */
    public void setStockRoomName(String stockRoomName) {
        this.stockRoomName = stockRoomName;
    }

    /**
     * @return the stockroomId
     */
    public String getStockroomId() {
        return stockroomId;
    }

    /**
     * @param stockroomId the stockroomId to set
     */
    public void setStockroomId(String stockroomId) {
        this.stockroomId = stockroomId;
    }

    /**
     * @return the productContractCode
     */
    public String getProductContractCode() {
        return productContractCode;
    }

    /**
     * @param productContractCode the productContractCode to set
     */
    public void setProductContractCode(String productContractCode) {
        this.productContractCode = productContractCode;
    }

    /**
     * @return the companyContractCode
     */
    public String getCompanyContractCode() {
        return companyContractCode;
    }

    /**
     * @param companyContractCode the companyContractCode to set
     */
    public void setCompanyContractCode(String companyContractCode) {
        this.companyContractCode = companyContractCode;
    }

    /**
     * @return the productTypeId
     */
    public Integer getProductTypeId() {
        return productTypeId;
    }

    /**
     * @param productTypeId the productTypeId to set
     */
    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    /**
     * @return the productTypeName
     */
    public String getProductTypeName() {
        return productTypeName;
    }

    /**
     * @param productTypeName the productTypeName to set
     */
    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    /**
     * @return the supplierName
     */
    public String getSupplierName() {
        return supplierName;
    }

    /**
     * @param supplierName the supplierName to set
     */
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    /**
     * @return the money
     */
    public BigDecimal getMoney() {
        return money;
    }

    /**
     * @param money the money to set
     */
    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the sendDate
     */
    public String getSendDate() {
        return sendDate;
    }

    /**
     * @param sendDate the sendDate to set
     */
    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    /**
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return the instockId
     */
    public String getInstockId() {
        return instockId;
    }

    /**
     * @param instockId the instockId to set
     */
    public void setInstockId(String instockId) {
        this.instockId = instockId;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the supplierId
     */
    public String getSupplierId() {
        return supplierId;
    }

    /**
     * @param supplierId the supplierId to set
     */
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    /**
     * @return the supplierLinkman
     */
    public String getSupplierLinkman() {
        return supplierLinkman;
    }

    /**
     * @param supplierLinkman the supplierLinkman to set
     */
    public void setSupplierLinkman(String supplierLinkman) {
        this.supplierLinkman = supplierLinkman;
    }

    /**
     * @return the supplierMobile
     */
    public String getSupplierMobile() {
        return supplierMobile;
    }

    /**
     * @param supplierMobile the supplierMobile to set
     */
    public void setSupplierMobile(String supplierMobile) {
        this.supplierMobile = supplierMobile;
    }

    /**
     * @return the receiveName
     */
    public String getReceiveName() {
        return receiveName;
    }

    /**
     * @param receiveName the receiveName to set
     */
    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    /**
     * @return the supplierPostcode
     */
    public String getSupplierPostcode() {
        return supplierPostcode;
    }

    /**
     * @param supplierPostcode the supplierPostcode to set
     */
    public void setSupplierPostcode(String supplierPostcode) {
        this.supplierPostcode = supplierPostcode;
    }

    /**
     * @return the supplierAddress
     */
    public String getSupplierAddress() {
        return supplierAddress;
    }

    /**
     * @param supplierAddress the supplierAddress to set
     */
    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }

    /**
     * @return the supplierTel
     */
    public String getSupplierTel() {
        return supplierTel;
    }

    /**
     * @param supplierTel the supplierTel to set
     */
    public void setSupplierTel(String supplierTel) {
        this.supplierTel = supplierTel;
    }

    /**
     * @return the proMajId
     */
    public String getProMajId() {
        return proMajId;
    }

    /**
     * @param proMajId the proMajId to set
     */
    public void setProMajId(String proMajId) {
        this.proMajId = proMajId;
    }

    /**
     * @return the proMajName
     */
    public String getProMajName() {
        return proMajName;
    }

    /**
     * @param proMajName the proMajName to set
     */
    public void setProMajName(String proMajName) {
        this.proMajName = proMajName;
    }

    /**
     * @return the proMajDate
     */
    public String getProMajDate() {
        return proMajDate;
    }

    /**
     * @param proMajDate the proMajDate to set
     */
    public void setProMajDate(String proMajDate) {
        this.proMajDate = proMajDate;
    }

    /**
     * @return the proMajIdea
     */
    public String getProMajIdea() {
        return proMajIdea;
    }

    /**
     * @param proMajIdea the proMajIdea to set
     */
    public void setProMajIdea(String proMajIdea) {
        this.proMajIdea = proMajIdea;
    }

    /**
     * @return the proMajIdea1
     */
    public String getProMajIdea1() {
        return proMajIdea1;
    }

    /**
     * @param proMajIdea1 the proMajIdea1 to set
     */
    public void setProMajIdea1(String proMajIdea1) {
        this.proMajIdea1 = proMajIdea1;
    }

    /**
     * @return the proMajIdea2
     */
    public String getProMajIdea2() {
        return proMajIdea2;
    }

    /**
     * @param proMajIdea2 the proMajIdea2 to set
     */
    public void setProMajIdea2(String proMajIdea2) {
        this.proMajIdea2 = proMajIdea2;
    }

    /**
     * @return the proMajText
     */
    public String getProMajText() {
        return proMajText;
    }

    /**
     * @param proMajText the proMajText to set
     */
    public void setProMajText(String proMajText) {
        this.proMajText = proMajText;
    }

    /**
     * @return the buyManId
     */
    public String getBuyManId() {
        return buyManId;
    }

    /**
     * @param buyManId the buyManId to set
     */
    public void setBuyManId(String buyManId) {
        this.buyManId = buyManId;
    }

    /**
     * @return the buyManName
     */
    public String getBuyManName() {
        return buyManName;
    }

    /**
     * @param buyManName the buyManName to set
     */
    public void setBuyManName(String buyManName) {
        this.buyManName = buyManName;
    }

    /**
     * @return the buyManDate
     */
    public String getBuyManDate() {
        return buyManDate;
    }

    /**
     * @param buyManDate the buyManDate to set
     */
    public void setBuyManDate(String buyManDate) {
        this.buyManDate = buyManDate;
    }

    /**
     * @return the buyManIdea
     */
    public String getBuyManIdea() {
        return buyManIdea;
    }

    /**
     * @param buyManIdea the buyManIdea to set
     */
    public void setBuyManIdea(String buyManIdea) {
        this.buyManIdea = buyManIdea;
    }

    /**
     * @return the buyManText
     */
    public String getBuyManText() {
        return buyManText;
    }

    /**
     * @param buyManText the buyManText to set
     */
    public void setBuyManText(String buyManText) {
        this.buyManText = buyManText;
    }

    /**
     * @return the opeMajId
     */
    public String getOpeMajId() {
        return opeMajId;
    }

    /**
     * @param opeMajId the opeMajId to set
     */
    public void setOpeMajId(String opeMajId) {
        this.opeMajId = opeMajId;
    }

    /**
     * @return the opeMajName
     */
    public String getOpeMajName() {
        return opeMajName;
    }

    /**
     * @param opeMajName the opeMajName to set
     */
    public void setOpeMajName(String opeMajName) {
        this.opeMajName = opeMajName;
    }

    /**
     * @return the opeMajDate
     */
    public String getOpeMajDate() {
        return opeMajDate;
    }

    /**
     * @param opeMajDate the opeMajDate to set
     */
    public void setOpeMajDate(String opeMajDate) {
        this.opeMajDate = opeMajDate;
    }

    /**
     * @return the opeMajIdea
     */
    public String getOpeMajIdea() {
        return opeMajIdea;
    }

    /**
     * @param opeMajIdea the opeMajIdea to set
     */
    public void setOpeMajIdea(String opeMajIdea) {
        this.opeMajIdea = opeMajIdea;
    }

    /**
     * @return the opeMajText
     */
    public String getOpeMajText() {
        return opeMajText;
    }

    /**
     * @param opeMajText the opeMajText to set
     */
    public void setOpeMajText(String opeMajText) {
        this.opeMajText = opeMajText;
    }

    /**
     * @return the stkAdmId
     */
    public String getStkAdmId() {
        return stkAdmId;
    }

    /**
     * @param stkAdmId the stkAdmId to set
     */
    public void setStkAdmId(String stkAdmId) {
        this.stkAdmId = stkAdmId;
    }

    /**
     * @return the stkAdmName
     */
    public String getStkAdmName() {
        return stkAdmName;
    }

    /**
     * @param stkAdmName the stkAdmName to set
     */
    public void setStkAdmName(String stkAdmName) {
        this.stkAdmName = stkAdmName;
    }

    /**
     * @return the stkAdmDate
     */
    public String getStkAdmDate() {
        return stkAdmDate;
    }

    /**
     * @param stkAdmDate the stkAdmDate to set
     */
    public void setStkAdmDate(String stkAdmDate) {
        this.stkAdmDate = stkAdmDate;
    }

    /**
     * @return the stkAdmIdea
     */
    public String getStkAdmIdea() {
        return stkAdmIdea;
    }

    /**
     * @param stkAdmIdea the stkAdmIdea to set
     */
    public void setStkAdmIdea(String stkAdmIdea) {
        this.stkAdmIdea = stkAdmIdea;
    }

    /**
     * @return the stkAdmIdea1
     */
    public String getStkAdmIdea1() {
        return stkAdmIdea1;
    }

    /**
     * @param stkAdmIdea1 the stkAdmIdea1 to set
     */
    public void setStkAdmIdea1(String stkAdmIdea1) {
        this.stkAdmIdea1 = stkAdmIdea1;
    }

    /**
     * @return the stkAdmIdea2
     */
    public String getStkAdmIdea2() {
        return stkAdmIdea2;
    }

    /**
     * @param stkAdmIdea2 the stkAdmIdea2 to set
     */
    public void setStkAdmIdea2(String stkAdmIdea2) {
        this.stkAdmIdea2 = stkAdmIdea2;
    }

    /**
     * @return the stkAdmIdea3
     */
    public String getStkAdmIdea3() {
        return stkAdmIdea3;
    }

    /**
     * @param stkAdmIdea3 the stkAdmIdea3 to set
     */
    public void setStkAdmIdea3(String stkAdmIdea3) {
        this.stkAdmIdea3 = stkAdmIdea3;
    }

    /**
     * @return the stkAdmText
     */
    public String getStkAdmText() {
        return stkAdmText;
    }

    /**
     * @return the requestDate
     */
    public String getRequestDate() {
        return requestDate;
    }

    /**
     * @param requestDate the requestDate to set
     */
    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    /**
     * @return the transportWay
     */
    public String getTransportWay() {
        return transportWay;
    }

    /**
     * @param transportWay the transportWay to set
     */
    public void setTransportWay(String transportWay) {
        this.transportWay = transportWay;
    }

    /**
     * @return the takeName
     */
    public String getTakeName() {
        return takeName;
    }

    /**
     * @param takeName the takeName to set
     */
    public void setTakeName(String takeName) {
        this.takeName = takeName;
    }

    /**
     * @return the takeNumber
     */
    public String getTakeNumber() {
        return takeNumber;
    }

    /**
     * @param takeNumber the takeNumber to set
     */
    public void setTakeNumber(String takeNumber) {
        this.takeNumber = takeNumber;
    }

    /**
     * @param stkAdmText the stkAdmText to set
     */
    public void setStkAdmText(String stkAdmText) {
        this.stkAdmText = stkAdmText;
    }

    /**
     * @return the proMajFlag
     */
    public String getProMajFlag() {
        return proMajFlag;
    }

    /**
     * @param proMajFlag the proMajFlag to set
     */
    public void setProMajFlag(String proMajFlag) {
        this.proMajFlag = proMajFlag;
    }

    /**
     * @return the buyManFlag
     */
    public String getBuyManFlag() {
        return buyManFlag;
    }

    /**
     * @param buyManFlag the buyManFlag to set
     */
    public void setBuyManFlag(String buyManFlag) {
        this.buyManFlag = buyManFlag;
    }

    /**
     * @return the opeMajFlag
     */
    public String getOpeMajFlag() {
        return opeMajFlag;
    }

    /**
     * @param opeMajFlag the opeMajFlag to set
     */
    public void setOpeMajFlag(String opeMajFlag) {
        this.opeMajFlag = opeMajFlag;
    }

    /**
     * @return the stkAdmFlag
     */
    public String getStkAdmFlag() {
        return stkAdmFlag;
    }

    /**
     * @param stkAdmFlag the stkAdmFlag to set
     */
    public void setStkAdmFlag(String stkAdmFlag) {
        this.stkAdmFlag = stkAdmFlag;
    }

    /**
     * @return the supplierAddressId
     */
    public String getSupplierAddressId() {
        return supplierAddressId;
    }

    /**
     * @param supplierAddressId the supplierAddressId to set
     */
    public void setSupplierAddressId(String supplierAddressId) {
        this.supplierAddressId = supplierAddressId;
    }

    
}
