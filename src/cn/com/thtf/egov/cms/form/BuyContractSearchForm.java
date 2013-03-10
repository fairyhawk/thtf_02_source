/**
 * ClassName  BuyContractSearchForm
 *
 * History
 * Create User: ChenHuajiang
 * Create Date: 2010-6-1
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.form;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;

/**
 * 列表页检索用Form
 * 
 * @author ChenHuajiang 2010-6-1
 */

public class BuyContractSearchForm extends ActionForm {

    private static final long serialVersionUID = -8165342831856748486L;

    /** 产品分类ID */
    private String productTypeId;

    /** 产品合同号 */
    private String productContractCode;

    /** 公司合同号 */
    private String companyContractCode;

    /** 供货商ID */
    private String supplierId;

    /** 供货商名称 */
    private String supplierName;

    /** 合同类型 */
    private String contractType;

    /** 模版类型 */
    private String templateType;

    /** 人员名称 */
    private String userName;

    /** 合同状态 */
    private String status;

    /** 合同签订起始日期 */
    private String startTime;

    /** 合同签订终止日期 */
    private String endTime;

    /** 角色ID */
    private String roleId;

    /** 用户ID */
    private String userId;

    /** 初始化标志 */
    private String initStr;
    
    /** star */
    private int star;
    /** end */
    private int rows;
    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getProductTypeId() {
        return this.productTypeId;
    }

    public void setProductTypeId(String productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getProductContractCode() {
        return this.productContractCode;
    }

    public void setProductContractCode(String productContractCode) {
        this.productContractCode = productContractCode;
        if (StringUtils.isNotEmpty(productContractCode)) {
            this.productContractCode = productContractCode.trim();
        }
    }

    public String getCompanyContractCode() {
        return this.companyContractCode;
    }

    public void setCompanyContractCode(String companyContractCode) {
        this.companyContractCode = companyContractCode;
        if (StringUtils.isNotEmpty(companyContractCode)) {
            this.companyContractCode = companyContractCode.trim();
        }
    }

    public String getSupplierId() {
        return this.supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return this.supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
        if (StringUtils.isNotEmpty(supplierName)) {
            this.supplierName = supplierName.trim();
        }
    }

    public String getContractType() {
        return this.contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getTemplateType() {
        return this.templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        if (StringUtils.isNotEmpty(userName)) {
            this.userName = userName.trim();
        }
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getRoleId() {
        return this.roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getInitStr() {
        return this.initStr;
    }

    public void setInitStr(String initStr) {
        this.initStr = initStr;
    }
}
