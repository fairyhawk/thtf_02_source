/**
 * ClassName  MReturnCustomerForm
 *
 * History
 * Create User: balance
 * Create Date: 2010-5-20
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.form;

import org.apache.struts.action.ActionForm;

import cn.com.thtf.egov.cms.entity.CustomerEntity;

/**
 * 
 * @author balance
 */

public class MReturnCustomerForm extends ActionForm {

    /**
     * 自动序列化
     */
    private static final long serialVersionUID = 5781418458643667294L;
    
    /**
     * @return the customerE
     */
    public CustomerEntity getCustomerE() {
        return customerE;
    }

    /**
     * @param customerE the customerE to set
     */
    public void setCustomerE(CustomerEntity customerE) {
        this.customerE = customerE;
    }

    private CustomerEntity customerE = new CustomerEntity();
}
