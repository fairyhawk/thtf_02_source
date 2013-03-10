/**
 * ClassName  ContainerTest
 *
 * History
 * Create User: Shiy
 * Create Date: 2010-5-19
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.util;

import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import cn.com.thtf.egov.cms.constant.Constants;
import cn.com.thtf.egov.cms.controller.StartUp;
import cn.com.thtf.egov.cms.dto.InvoiceDetailDto;
import cn.com.thtf.egov.cms.dto.InvoiceInfoDto;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 
 * @author Shiy
 */
@RunWith(JMock.class)
public class ContainerTest {
    Mockery context = new JUnit4Mockery() {
        {
            setImposteriser(ClassImposteriser.INSTANCE);
        }
    };;
    final ServletContextEvent servletContextEvt = context.mock(ServletContextEvent.class);
    final ServletContext servletContext = context.mock(ServletContext.class);

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        context.checking(new Expectations() {
            {
                (allowing(servletContext)).getRealPath("/WEB-INF/");
                will(returnValue("d:\\Works\\020_同方\\40_SVN_BBUT\\trunk\\20_Source\\WebRoot\\WEB-INF"));
                (allowing(servletContextEvt)).getServletContext();
                will(returnValue(servletContext));
            }
        });

        StartUp up = new StartUp();
        up.contextInitialized(servletContextEvt);
    }

    /**
     * Test method for
     * {@link cn.com.thtf.egov.cms.util.Container#getTemplateConfig()}.
     */
    @Test
    public void testGetTemplateConfig() {
        Container.getTemplateConfig();
        System.out.println(Constants.FM_TEMPLATEDIR);
    }

    @Test
    public void testGetFinaTemplateConfig() throws IOException, TemplateException {
        Configuration fconfig = Container.getTemplateConfig();
        Template template = fconfig.getTemplate(Constants.FINA_TEMPLATE);

        InvoiceInfoDto invoiceInfo = new InvoiceInfoDto();
        invoiceInfo.setId("KP100001");
        invoiceInfo.setCustomerId(101);
        invoiceInfo.setCustomerName("测试用户");
        invoiceInfo.setDate("2010-06-21");
        invoiceInfo.setInvoiceBankAccount("11012345678901");
        invoiceInfo.setInvoiceBankAddress("清华大学东门中国银行清华支行");
        invoiceInfo.setInvoiceBankName("中国银行清华支行");
        invoiceInfo.setInvoiceBankTel("0106123456");
        invoiceInfo.setInvoiceType(1);
        invoiceInfo.setMoney(new BigDecimal("23456789"));
        invoiceInfo.setNotifyDate("2010-06-22");
        invoiceInfo.setTaxNumber("2201234567");
        invoiceInfo.setUserName("张三");
        invoiceInfo.setProductTypeName("康普");
        invoiceInfo.setText("特殊说明文字");

        List<InvoiceDetailDto> list = new ArrayList<InvoiceDetailDto>();
        InvoiceDetailDto invoiceDetail = new InvoiceDetailDto();
        invoiceDetail.setProductName("测试产品一");
        invoiceDetail.setProductType("康普");
        invoiceDetail.setProductUnit("个");
        invoiceDetail.setPrice(new BigDecimal("123456789.05"));
        invoiceDetail.setMoney(new BigDecimal("234567890.23"));
        invoiceDetail.setCount(5);
        list.add(invoiceDetail);

        invoiceDetail = new InvoiceDetailDto();
        invoiceDetail.setProductName("测试产品二");
        invoiceDetail.setProductType("康普");
        invoiceDetail.setProductUnit("台");
        invoiceDetail.setPrice(new BigDecimal("100000009.05"));
        invoiceDetail.setMoney(new BigDecimal("200000000.23"));
        invoiceDetail.setCount(10);
        list.add(invoiceDetail);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("invoice", invoiceInfo);
        map.put("product", list);

        StringWriter out = new StringWriter();
        template.process(map, out);

        System.out.println(out.toString());
    }

    
    @Test
    public void testGetSendGoodsTemplateConfig() throws IOException, TemplateException {
        
    }
}
