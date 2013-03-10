/**
 * ClassName  ReceiveDetailDto
 *
 * History
 * Create User: Administrator
 * Create Date: 2010-6-7
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

import java.io.Serializable;

/**
 * 收货地址添加Dto
 * 
 * @author ChenHuajiang
 */

public class ReceiveDetailDto implements Serializable {

    private static final long serialVersionUID = 2947215419419105268L;

    // 采购明细id
    private String id;
    
    // 产品编码
    private String prodCode;

    // 产品
    private String prodId;
    
    // 产品名称
    private String prodName;

    // 规格型号
    private String prodType;

    // 单位
    private String prodUnit;

    // 采购合同ID
    private String buyContractId;

    // 单位类型
    private String companyType;

    // 单位ID
    private String companyId;

    // 地址ID
    private String addressId;

    // 采购数
    private Integer buyCount;

    // 其他地址收货数(汇总) 	其他地址收获数 已选择数
    private Integer receiveTotalCount;

    // 收获数=采购数-其他地址收货数(汇总)
    private Integer receiveCount;

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

    public String getBuyContractId() {
        return this.buyContractId;
    }

    public void setBuyContractId(String buyContractId) {
        this.buyContractId = buyContractId;
    }

    public String getCompanyType() {
        return this.companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getCompanyId() {
        return this.companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getAddressId() {
        return this.addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public Integer getBuyCount() {
        return this.buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public Integer getReceiveTotalCount() {
        return this.receiveTotalCount;
    }

    public void setReceiveTotalCount(Integer receiveTotalCount) {
        this.receiveTotalCount = receiveTotalCount;
    }

    public Integer getReceiveCount() {
        return this.receiveCount;
    }

    public void setReceiveCount(Integer receiveCount) {
        this.receiveCount = receiveCount;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
}
