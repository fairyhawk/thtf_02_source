/**
 * ClassName  ParaTest
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-5-24
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.util;

/**
 * ParaTest
 * 
 * @author Lubo
 */

public class ParaTest {

    public static void main(String[] args) {
        test();
        System.out.println("==============");
        test("aaaa");
        System.out.println("==============");
        test("aaaa","bbbb");
    }

    public static void test(String... name) {
        System.out.println(name.length);
    }
}
