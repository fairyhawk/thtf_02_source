/**
 * ClassName  MoveStockAssessDto
 *
 * History
 * Create User: jiangmingxing
 * Create Date: 2010-6-29
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

/**
 * 
 * @author jiangmingxing
 */

public class MoveStockAssessDto {

    private String id; // 移库单号

    private Integer productTypeId; // 产品分类编号

    private String date; // 申请日期

    private String type; // 移库单类型

    private Integer outStockroomId; // 移出库房编号

    private Integer inStockroomId; // 移入库房编号

    private Integer stockroomAddressId; // 移入库房收货地址编号

    private String sendDate; // 发货日期

    private Integer status; // 移库单状态

    private String userId; // 登录名

    private String userName; // 人员名称

    private String moveOutName; // 移出库房名称

    private String productTypeName; // 产品分类名称

    private String moveInName; // 移入库房名称

    private String goodsReceiveUnitName; // 货物接收单位名称

    private String linkman; // 联系人

    private String address; // 收货地址

    private String postcode; // 邮编

    private String mobile; // 手机

    private String tel; // 电话

    private String requestDate; // 要求发货日期

    private Integer transportWay; // 货运方式

    private String text; // 特殊说明

    private String buyManId; // 采购主管登录名

    private String buyManName; // 采购主管用户名

    private String buyManDate; // 采购主管评审日期

    private String buyManIdea; // 采购主管评审意见

    private String buyManText; // 采购主管补充说明

    private String stkAdmId; // 移出库房管理员登录名

    private String stkAdmName; // 移出库房管理员用户名

    private String stkAdmDate; // 移出库房管理员评审日期

    private String stkAdmIdea; // 移出库房管理员评审意见

    private String stkAdmText; // 移出库房管理员补充说明

    private String inAdmId; // 移入库房管理员登录名

    private String inAdmName; // 移入库房管理员用户名

    private String inAdmDate; // 移入库房管理员评审日期

    private String inAdmIdea; // 移入库房管理员评审意见

    private String inAdmText; // 移入库房管理员补充说明

    private String proMajId; // 产品总监登录名

    private String proMajName; // 产品总监用户名

    private String roleId; // 权限

    /**
     * @return the sendDate
     */
    public String getSendDate() {
        return sendDate;
    }

    /**
     * @param sendDate
     *            the sendDate to set
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
     * @param status
     *            the status to set
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     *            the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     *            the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the proMajId
     */
    public String getProMajId() {
        return proMajId;
    }

    /**
     * @param proMajId
     *            the proMajId to set
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
     * @param proMajName
     *            the proMajName to set
     */
    public void setProMajName(String proMajName) {
        this.proMajName = proMajName;
    }

    /**
     * @return the roleId
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     *            the roleId to set
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the productTypeId
     */
    public Integer getProductTypeId() {
        return productTypeId;
    }

    /**
     * @param productTypeId
     *            the productTypeId to set
     */
    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date
     *            the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the outStockroomId
     */
    public Integer getOutStockroomId() {
        return outStockroomId;
    }

    /**
     * @param outStockroomId
     *            the outStockroomId to set
     */
    public void setOutStockroomId(Integer outStockroomId) {
        this.outStockroomId = outStockroomId;
    }

    /**
     * @return the inStockroomId
     */
    public Integer getInStockroomId() {
        return inStockroomId;
    }

    /**
     * @param inStockroomId
     *            the inStockroomId to set
     */
    public void setInStockroomId(Integer inStockroomId) {
        this.inStockroomId = inStockroomId;
    }

    /**
     * @return the stockroomAddressId
     */
    public Integer getStockroomAddressId() {
        return stockroomAddressId;
    }

    /**
     * @param stockroomAddressId
     *            the stockroomAddressId to set
     */
    public void setStockroomAddressId(Integer stockroomAddressId) {
        this.stockroomAddressId = stockroomAddressId;
    }

    /**
     * @return the moveOutName
     */
    public String getMoveOutName() {
        return moveOutName;
    }

    /**
     * @param moveOutName
     *            the moveOutName to set
     */
    public void setMoveOutName(String moveOutName) {
        this.moveOutName = moveOutName;
    }

    /**
     * @return the productTypeName
     */
    public String getProductTypeName() {
        return productTypeName;
    }

    /**
     * @param productTypeName
     *            the productTypeName to set
     */
    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    /**
     * @return the moveInName
     */
    public String getMoveInName() {
        return moveInName;
    }

    /**
     * @param moveInName
     *            the moveInName to set
     */
    public void setMoveInName(String moveInName) {
        this.moveInName = moveInName;
    }

    /**
     * @return the goodsReceiveUnitName
     */
    public String getGoodsReceiveUnitName() {
        return goodsReceiveUnitName;
    }

    /**
     * @param goodsReceiveUnitName
     *            the goodsReceiveUnitName to set
     */
    public void setGoodsReceiveUnitName(String goodsReceiveUnitName) {
        this.goodsReceiveUnitName = goodsReceiveUnitName;
    }

    /**
     * @return the linkman
     */
    public String getLinkman() {
        return linkman;
    }

    /**
     * @param linkman
     *            the linkman to set
     */
    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     *            the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the postcode
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * @param postcode
     *            the postcode to set
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    /**
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile
     *            the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return the tel
     */
    public String getTel() {
        return tel;
    }

    /**
     * @param tel
     *            the tel to set
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * @return the requestDate
     */
    public String getRequestDate() {
        return requestDate;
    }

    /**
     * @param requestDate
     *            the requestDate to set
     */
    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    /**
     * @return the transportWay
     */
    public Integer getTransportWay() {
        return transportWay;
    }

    /**
     * @param transportWay
     *            the transportWay to set
     */
    public void setTransportWay(Integer transportWay) {
        this.transportWay = transportWay;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text
     *            the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the buyManId
     */
    public String getBuyManId() {
        return buyManId;
    }

    /**
     * @param buyManId
     *            the buyManId to set
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
     * @param buyManName
     *            the buyManName to set
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
     * @param buyManDate
     *            the buyManDate to set
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
     * @param buyManIdea
     *            the buyManIdea to set
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
     * @param buyManText
     *            the buyManText to set
     */
    public void setBuyManText(String buyManText) {
        this.buyManText = buyManText;
    }

    /**
     * @return the stkAdmId
     */
    public String getStkAdmId() {
        return stkAdmId;
    }

    /**
     * @param stkAdmId
     *            the stkAdmId to set
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
     * @param stkAdmName
     *            the stkAdmName to set
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
     * @param stkAdmDate
     *            the stkAdmDate to set
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
     * @param stkAdmIdea
     *            the stkAdmIdea to set
     */
    public void setStkAdmIdea(String stkAdmIdea) {
        this.stkAdmIdea = stkAdmIdea;
    }

    /**
     * @return the stkAdmText
     */
    public String getStkAdmText() {
        return stkAdmText;
    }

    /**
     * @param stkAdmText
     *            the stkAdmText to set
     */
    public void setStkAdmText(String stkAdmText) {
        this.stkAdmText = stkAdmText;
    }

    /**
     * @return the inAdmId
     */
    public String getInAdmId() {
        return inAdmId;
    }

    /**
     * @param inAdmId
     *            the inAdmId to set
     */
    public void setInAdmId(String inAdmId) {
        this.inAdmId = inAdmId;
    }

    /**
     * @return the inAdmName
     */
    public String getInAdmName() {
        return inAdmName;
    }

    /**
     * @param inAdmName
     *            the inAdmName to set
     */
    public void setInAdmName(String inAdmName) {
        this.inAdmName = inAdmName;
    }

    /**
     * @return the inAdmDate
     */
    public String getInAdmDate() {
        return inAdmDate;
    }

    /**
     * @param inAdmDate
     *            the inAdmDate to set
     */
    public void setInAdmDate(String inAdmDate) {
        this.inAdmDate = inAdmDate;
    }

    /**
     * @return the inAdmIdea
     */
    public String getInAdmIdea() {
        return inAdmIdea;
    }

    /**
     * @param inAdmIdea
     *            the inAdmIdea to set
     */
    public void setInAdmIdea(String inAdmIdea) {
        this.inAdmIdea = inAdmIdea;
    }

    /**
     * @return the inAdmText
     */
    public String getInAdmText() {
        return inAdmText;
    }

    /**
     * @param inAdmText
     *            the inAdmText to set
     */
    public void setInAdmText(String inAdmText) {
        this.inAdmText = inAdmText;
    }

}
