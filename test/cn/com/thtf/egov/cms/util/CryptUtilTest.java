/**
 * ClassName  CryptUtilTest
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-5-17
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.util;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.junit.Before;
import org.junit.Test;

import cn.com.thtf.egov.cms.controller.StartUp;

/**
 * md5加密测试
 * 
 * @author Lubo
 */
public class CryptUtilTest {

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
        startUp.contextInitialized(null);
        try {
            System.out.println(CryptUtil.MD5("tongfang"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
