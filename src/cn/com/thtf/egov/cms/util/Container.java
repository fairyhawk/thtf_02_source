package cn.com.thtf.egov.cms.util;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.com.thtf.egov.cms.constant.Constants;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;

/**
 * 用于全局Object取得
 * 
 * @author Shiy
 */
public class Container {

    private static final Logger log = LoggerFactory.getLogger(Container.class);
    /**
     * spring
     */
    private static ApplicationContext applicationContext = null;

    private static Configuration fconfig = null;
    static {
        String[] locations = {
                "classpath:/cn/com/thtf/egov/cms/spring/applicationContext.xml",
                "classpath:/cn/com/thtf/egov/cms/spring/appBeans.xml",
                "classpath:/cn/com/thtf/egov/cms/spring/service.xml" };
        applicationContext = new ClassPathXmlApplicationContext(locations);

    }

    private static void initFConfig() {
        fconfig = new Configuration();
        try {
            fconfig.setDirectoryForTemplateLoading(new File(Constants.FM_TEMPLATEDIR));
        } catch (IOException e) {
            log.error("FreeMarker Config Error", e);
        }
        fconfig.setDefaultEncoding(Constants.FM_TEMPLATE_ENCODING);
        fconfig.setObjectWrapper(new DefaultObjectWrapper());
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    public static Configuration getTemplateConfig() {
        if (fconfig == null) {
            initFConfig();
        }
        return fconfig;
    }
}
