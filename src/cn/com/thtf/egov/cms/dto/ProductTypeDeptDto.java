/**
 * ClassName  ProductTypeDeptDto
 *
 * History
 * Create User: Administrator
 * Create Date: 2010-6-3
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

import java.io.Serializable;

/**
 * 
 * @author ChenHuajiang
 */

public class ProductTypeDeptDto implements Serializable {

    private static final long serialVersionUID = -5996895404622877584L;

    // 产品ID
    private Integer prodId;

    // 产品编号
    private String prodNo;

    // 产品名称
    private String prodName;

    // 产品部门id
    private Integer prodDeptId;

    // 产品部门名称
    private String prodDeptName;

    // 产品信用总额
    private String climit;

    public Integer getProdId() {
        return this.prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public String getProdNo() {
        return this.prodNo;
    }

    public void setProdNo(String prodNo) {
        this.prodNo = prodNo;
    }

    public String getProdName() {
        return this.prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public Integer getProdDeptId() {
        return this.prodDeptId;
    }

    public void setProdDeptId(Integer prodDeptId) {
        this.prodDeptId = prodDeptId;
    }

    public String getProdDeptName() {
        return this.prodDeptName;
    }

    public void setProdDeptName(String prodDeptName) {
        this.prodDeptName = prodDeptName;
    }

    public String getClimit() {
        return this.climit;
    }

    public void setClimit(String climit) {
        this.climit = climit;
    }
}
