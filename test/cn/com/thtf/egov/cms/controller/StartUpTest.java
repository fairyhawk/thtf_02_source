/**
 * ClassName  StartUpTest
 *
 * History
 * Create User: Shiy
 * Create Date: 2010-4-28
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cn.com.thtf.egov.cms.constant.Constants;

/**
 * 
 * @author Shiy
 */

public class StartUpTest {

    private StartUp startUp = null;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        startUp = new StartUp();
    }

    /**
     * Test method for
     * {@link cn.com.thtf.egov.cms.controller.StartUp#contextInitialized(javax.servlet.ServletContextEvent)}
     * .
     */
    @Test
    public void testContextInitialized() {
        assertNull(Constants.CONTRACT_MONEY);
        assertNull(Constants.CONTRACT_RATE);
        assertNull(Constants.SELL_PLACE);
        assertNull(Constants.SELL_PROTECT);
        assertNull(Constants.SELL_CHECK);
        assertNull(Constants.SELL_BREAK);
        assertNull(Constants.SELL_LAWSUIT);
        startUp.contextInitialized(null);
        assertEquals(Constants.CONTRACT_MONEY, "1000000.00");
        assertEquals(Constants.CONTRACT_RATE, "0.05");
        assertEquals(Constants.SELL_PLACE, "北京市海淀区");
        assertEquals(Constants.SELL_PROTECT, new Integer(60));
        assertEquals(Constants.SELL_CHECK, new Integer(2));
        assertEquals(Constants.SELL_BREAK, "欠款总额的1%/日");
        assertEquals(Constants.SELL_LAWSUIT, "合同签订地的人民法院提起诉讼");
    }

}
