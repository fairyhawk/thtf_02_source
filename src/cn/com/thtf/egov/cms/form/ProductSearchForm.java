/**
 * ClassName  ProductSearchForm
 *
 * History
 * Create User: ChenHuajiang
 * Create Date: 2010-6-3
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.form;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;

/**
 * 产品检索用
 * 
 * @author ChenHuajiang
 */

public class ProductSearchForm extends ActionForm {

    private static final long serialVersionUID = -5206279283847608791L;

    // 产品分类ID
    private String prodTypeId;

    // 产品分类名称
    private String prodTypeName;

    // 产品系列ID
    private String prodSerieId;

    // 产品系列名称
    private String prodSeriesName;

    // 产品编码
    private String prodCode;

    // 产品名称
    private String prodName;

    // 规格型号
    private String prodType;

    public String getProdTypeId() {
        return this.prodTypeId;
    }

    public void setProdTypeId(String prodTypeId) {
        this.prodTypeId = prodTypeId;
    }

    public String getProdTypeName() {
        return this.prodTypeName;
    }

    public void setProdTypeName(String prodTypeName) {
        this.prodTypeName = prodTypeName;
        if (StringUtils.isNotEmpty(prodTypeName)) {
            this.prodTypeName = prodTypeName.trim();
        }
    }

    public String getProdSerieId() {
        return this.prodSerieId;
    }

    public void setProdSerieId(String prodSerieId) {
        this.prodSerieId = prodSerieId;
    }

    public String getProdSeriesName() {
        return this.prodSeriesName;
    }

    public void setProdSeriesName(String prodSeriesName) {
        this.prodSeriesName = prodSeriesName;
        if (StringUtils.isNotEmpty(prodSeriesName)) {
            this.prodSeriesName = prodSeriesName.trim();
        }
    }

    public String getProdCode() {
        return this.prodCode;
    }

    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
        if (StringUtils.isNotEmpty(prodCode)) {
            this.prodCode = prodCode.trim();
        }
    }

    public String getProdName() {
        return this.prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
        if (StringUtils.isNotEmpty(prodName)) {
            this.prodName = prodName.trim();
        }
    }

    public String getProdType() {
        return this.prodType;
    }

    public void setProdType(String prodType) {
        this.prodType = prodType;
        if (StringUtils.isNotEmpty(prodType)) {
            this.prodType = prodType.trim();
        }
    }
}
