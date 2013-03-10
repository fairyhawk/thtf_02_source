/**
 * ClassName  CreateTreeServiceImpTest
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-4-7
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.serviceimpl;

import org.junit.Test;

import cn.com.thtf.egov.cms.iservice.ICreateTreeService;
import cn.com.thtf.egov.cms.util.Container;

/**
 * CreateTreeServiceImpTest
 * 
 * @author Lubo
 */

public class CreateTreeServiceImpTest {

    /**
     * Test method for
     * {@link cn.com.thtf.egov.cms.serviceimpl.CreateTreeServiceImp#getLeftTree(java.lang.Integer)}
     * .
     */
    @Test
    public void testGetLeftTree() {
        ICreateTreeService createTreeService = (ICreateTreeService) Container
                .getBean("createTreeServiceImp");
        
        try {
            System.out.println(createTreeService.getLeftTree(1));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
