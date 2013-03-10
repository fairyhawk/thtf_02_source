/**
 * ClassName  TodoWorkActionTest
 *
 * History
 * Create User: Shiy
 * Create Date: 2010-5-7
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.action.work;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import cn.com.thtf.egov.cms.constant.Constants;
import cn.com.thtf.egov.cms.entity.UserEntity;

/**
 * 
 * @author Shiy
 */
@RunWith(JMock.class)
public class TodoWorkActionTest {

    Mockery context = new JUnit4Mockery() {
        {
            setImposteriser(ClassImposteriser.INSTANCE);
        }
    };

    final TodoWorkAction action = new TodoWorkAction();
    final HttpServletRequest request = context.mock(HttpServletRequest.class);
    final HttpServletResponse response = context.mock(HttpServletResponse.class);
    final HttpSession session = context.mock(HttpSession.class);
    final ActionMapping mapping = context.mock(ActionMapping.class);
    final ActionForm form = context.mock(ActionForm.class);
    
    final UserEntity user = new UserEntity();
    final StringWriter sw = new StringWriter();
    final PrintWriter writer = new PrintWriter(sw);
    


    
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        user.setId("luffyday5");
        context.checking(new Expectations() {
            {
                (allowing(request)).getSession();
                will(returnValue(session));
                (allowing(session)).getAttribute(Constants.USERLOGIN);
                will(returnValue(user));
                (allowing(response)).getWriter();
                will(returnValue(writer));
                (allowing(response)).setCharacterEncoding("UTF-8");
            }
        });
    }

    /**
     * Test method for
     * {@link cn.com.thtf.egov.cms.action.work.TodoWorkAction#getTodoWorkCount(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)}
     * .
     * 
     * @throws Exception
     */
    @Test
    public void testGetTodoWorkCount() throws Exception {

        action.getTodoWorkCount(mapping, form, request, response);
        System.out.println(sw.toString());
    }

    /**
     * Test method for
     * {@link cn.com.thtf.egov.cms.action.work.TodoWorkAction#getTodoWorkList(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)}
     * .
     * @throws Exception 
     */
    @Test
    public void testGetTodoWorkList() throws Exception {
        action.getTodoWorkList(mapping, form, request, response);
        System.out.println(sw.toString());
    }

}
