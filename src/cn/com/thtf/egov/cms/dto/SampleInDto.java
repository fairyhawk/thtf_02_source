package cn.com.thtf.egov.cms.dto;


import cn.com.thtf.egov.cms.application.BaseEntity;

public class SampleInDto extends BaseEntity {

    private static final long serialVersionUID = -4721166952540724938L;

    private String id;

    private String date;

    private String sampleOutId;

    private Integer inStockroomId;

    private String stockroomAddressId;

    private String userId;

    private String userName;

    private String text;
    
    private Integer productTypeId;
    
    private String money;

    private String status;

    private String buyManId;

    private String buyManName;

    private String buyManDate;

    private String buyManIdea;

    private String buyManText;

    private String stkAdmId;

    private String stkAdmName;

    private String stkAdmDate;

    private String stkAdmIdea;

    private String stkAdmText;

    private String datetime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSampleOutId() {
        return sampleOutId;
    }

    public void setSampleOutId(String sampleOutId) {
        this.sampleOutId = sampleOutId;
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

  

    public String getBuyManId() {
        return buyManId;
    }

    public void setBuyManId(String buyManId) {
        this.buyManId = buyManId;
    }

    public String getBuyManName() {
        return buyManName;
    }

    public void setBuyManName(String buyManName) {
        this.buyManName = buyManName;
    }

    public String getBuyManDate() {
        return buyManDate;
    }

    public void setBuyManDate(String buyManDate) {
        this.buyManDate = buyManDate;
    }

    public String getBuyManIdea() {
        return buyManIdea;
    }

    public void setBuyManIdea(String buyManIdea) {
        this.buyManIdea = buyManIdea;
    }

    public String getBuyManText() {
        return buyManText;
    }

    public void setBuyManText(String buyManText) {
        this.buyManText = buyManText;
    }

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

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }


    /**
     * @return the stockroomAddressId
     */
    public String getStockroomAddressId() {
        return stockroomAddressId;
    }

    /**
     * @param stockroomAddressId the stockroomAddressId to set
     */
    public void setStockroomAddressId(String stockroomAddressId) {
        this.stockroomAddressId = stockroomAddressId;
    }

    /**
     * @return the money
     */
    public String getMoney() {
        return money;
    }

    /**
     * @param money the money to set
     */
    public void setMoney(String money) {
        this.money = money;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
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
     * @return the inStockroomId
     */
    public Integer getInStockroomId() {
        return inStockroomId;
    }

    /**
     * @param inStockroomId the inStockroomId to set
     */
    public void setInStockroomId(Integer inStockroomId) {
        this.inStockroomId = inStockroomId;
    }
}