/**
 * ClassName  DemandProductDto
 *
 * History
 * Create User: Administrator
 * Create Date: 2010-6-7
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

import java.io.Serializable;
import java.util.List;

import cn.com.thtf.egov.cms.entity.UserAreaProductEntity;

/**
 * 需求单列表Dto
 * 
 * @author ChenHuajiang
 */

public class DemandProductDto implements Serializable {

    private static final long serialVersionUID = -2384659295382043761L;

    // 需求单ID
    private Integer demandId;

    // 产品ID
    private Integer prodId;

    // 产品编码
    private String prodCode;

    // 产品合同号
    private String prodCntrtCode;

    // 公司合同号
    private String compCntrtCode;

    // 需求数
    private Integer demandCount;

    // 要求发货日期
    private String sendDate;

    // 客户名称
    private String customerName;

    // 人员名称
    private String userName;

    // 确认日期
    private String confirmDate;

    // 需求单状态
    private Integer demandStatus;

    // 产品名称
    private String prodName;

    // 规格型号
    private String prodType;

    // 单位
    private String prodUnit;

    // 库存总数
    private Integer stockTotalCount;

    // 未确认需求数
    private Integer unconfirmedCount;
    // 销售可用数
    private Integer freeCount;

    // 采购合同数
    private Integer buyCount;

    // 销售合同数
    private Integer sellCount;

    // 人员区域ID
    private Integer userAreaId;

    // 人员区域名称
    private String userAreaName;

    // 区域总监 所负责的区域和产品分类
    private List<UserAreaProductEntity> userAreaProductList;

    public Integer getDemandId() {
        return this.demandId;
    }

    public void setDemandId(Integer demandId) {
        this.demandId = demandId;
    }

    public Integer getProdId() {
        return this.prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public String getProdCode() {
        return this.prodCode;
    }

    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
    }

    public String getProdName() {
        return this.prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdType() {
        return this.prodType;
    }

    public void setProdType(String prodType) {
        this.prodType = prodType;
    }

    public String getProdUnit() {
        return this.prodUnit;
    }

    public void setProdUnit(String prodUnit) {
        this.prodUnit = prodUnit;
    }

    public Integer getStockTotalCount() {
        return this.stockTotalCount;
    }

    public void setStockTotalCount(Integer stockTotalCount) {
        this.stockTotalCount = stockTotalCount;
    }

    public Integer getUnconfirmedCount() {
        return this.unconfirmedCount;
    }

    public void setUnconfirmedCount(Integer unconfirmedCount) {
        this.unconfirmedCount = unconfirmedCount;
    }

    public Integer getFreeCount() {
        return this.freeCount;
    }

    public void setFreeCount(Integer freeCount) {
        this.freeCount = freeCount;
    }

    public Integer getBuyCount() {
        return this.buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public Integer getSellCount() {
        return this.sellCount;
    }

    public void setSellCount(Integer sellCount) {
        this.sellCount = sellCount;
    }

    public Integer getUserAreaId() {
        return this.userAreaId;
    }

    public void setUserAreaId(Integer userAreaId) {
        this.userAreaId = userAreaId;
    }

    public String getUserAreaName() {
        return this.userAreaName;
    }

    public void setUserAreaName(String userAreaName) {
        this.userAreaName = userAreaName;
    }

    public String getProdCntrtCode() {
        return this.prodCntrtCode;
    }

    public void setProdCntrtCode(String prodCntrtCode) {
        this.prodCntrtCode = prodCntrtCode;
    }

    public String getCompCntrtCode() {
        return this.compCntrtCode;
    }

    public void setCompCntrtCode(String compCntrtCode) {
        this.compCntrtCode = compCntrtCode;
    }

    public Integer getDemandCount() {
        return this.demandCount;
    }

    public void setDemandCount(Integer demandCount) {
        this.demandCount = demandCount;
    }

    public String getSendDate() {
        return this.sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getConfirmDate() {
        return this.confirmDate;
    }

    public void setConfirmDate(String confirmDate) {
        this.confirmDate = confirmDate;
    }

    public Integer getDemandStatus() {
        return this.demandStatus;
    }

    public void setDemandStatus(Integer demandStatus) {
        this.demandStatus = demandStatus;
    }

    public List<UserAreaProductEntity> getUserAreaProductList() {
        return this.userAreaProductList;
    }

    public void setUserAreaProductList(List<UserAreaProductEntity> userAreaProductList) {
        this.userAreaProductList = userAreaProductList;
    }
}
