/**
 * ClassName  BoxForm
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-6-8
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import cn.com.thtf.egov.cms.entity.BuyBackContractEntity;

/**
 * 新建采购退货合同表单
 * 
 * @author HanHaiyun
 */
public class BuyBackContractAddForm extends ActionForm {

    private static final long serialVersionUID = -1338300915067863808L;
    BuyBackContractEntity bbce = new BuyBackContractEntity();

    public BuyBackContractEntity getBbce() {
        return bbce;
    }

    public void setBbce(BuyBackContractEntity bbce) {
        this.bbce = bbce;
    }

    private FormFile file;// 文件
    private String[] prodid;// 产品编号
    private String[] count;// 退货合同数
    private String[] price;//采购单价

    public String[] getPrice() {
        return price;
    }

    public void setPrice(String[] price) {
        this.price = price;
    }

    public String[] getProdid() {
        return prodid;
    }

    public void setProdid(String[] prodid) {
        this.prodid = prodid;
    }

    public String[] getCount() {
        return count;
    }

    public void setCount(String[] count) {
        this.count = count;
    }

    public FormFile getFile() {
        return file;
    }

    public void setFile(FormFile file) {
        this.file = file;
    }

}
