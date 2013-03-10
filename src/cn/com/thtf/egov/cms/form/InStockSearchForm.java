/**
 * ClassName  InStockSearchForm
 *
 * History
 * Create User: ChenHuajiang
 * Create Date: 2010-6-11
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.form;

import org.apache.struts.action.ActionForm;

/**
 * 入库明细选择
 * 
 * @author ChenHuajiang
 */

public class InStockSearchForm extends ActionForm {

    private static final long serialVersionUID = -3038999934778489575L;

    /** 用户ID */
    private String userId;

    /** 角色ID */
    private String roleId;

    /** 供货商ID */
    private String supplierId;

    /** 产品分类ID */
    private String prodTypeId;

    /** 产品分类名称 */
    private String prodTypeName;

    /** 入库单ID */
    private String inStockId;

    /** 产品合同号 */
    private String prodContractCode;

    /** 公司合同号 */
    private String compContractCode;

    /** 产品编码 */
    private String prodCode;

    /** 产品名称 */
    private String prodName;

    /** 规格型号 */
    private String prodType;

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return this.roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getProdTypeId() {
        return this.prodTypeId;
    }

    public String getSupplierId() {
        return this.supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public void setProdTypeId(String prodTypeId) {
        this.prodTypeId = prodTypeId;
    }

    public String getProdTypeName() {
        return this.prodTypeName;
    }

    public void setProdTypeName(String prodTypeName) {
        this.prodTypeName = prodTypeName;
    }

    public String getInStockId() {
        return this.inStockId!=null?this.inStockId.trim():this.inStockId;
    }

    public void setInStockId(String inStockId) {
        this.inStockId = inStockId;
    }

    public String getProdContractCode() {
        return  this.prodContractCode!=null?this.prodContractCode.trim():this.prodContractCode;
    }

    public void setProdContractCode(String prodContractCode) {
        this.prodContractCode = prodContractCode;
    }

    public String getCompContractCode() {
        return  this.compContractCode!=null? this.compContractCode.trim(): this.compContractCode;
    }

    public void setCompContractCode(String compContractCode) {
        this.compContractCode = compContractCode;
    }

    public String getProdCode() {
        return this.prodCode!=null?this.prodCode.trim():this.prodCode;
    }

    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
    }

    public String getProdName() {
        return this.prodName!=null?this.prodName.trim():this.prodName;
    }

    public void setProdName(String prodName) {
        
        this.prodName = prodName;
    }

    public String getProdType() {
        return this.prodType!=null?this.prodType.trim():this.prodType;
    }

    public void setProdType(String prodType) {
        this.prodType = prodType;
    }
}
