/**
 * ClassName  StartUp
 *
 * History
 * Create User: Lubo
 * Create Date: 2009-11-18
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.controller;

import java.io.File;
import java.io.InputStream;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.constant.Constants;
import cn.com.thtf.egov.cms.dto.ContractIdDto;
import cn.com.thtf.egov.cms.iservice.IUserService;
import cn.com.thtf.egov.cms.iservice.common.ICommonService;
import cn.com.thtf.egov.cms.util.Container;
import cn.com.thtf.egov.cms.util.HClient;

/**
 * 系统初始化类
 * 
 * @author Lubo
 */
public class StartUp implements ServletContextListener {

    /** log */
    private static Logger log = LoggerFactory.getLogger(StartUp.class);
    private static ICommonService commonService = (ICommonService) Container
            .getBean("commonServiceImpl");

    /**
     * StartUp
     */
    public StartUp() {
        super();
    }

    /**
     * contextDestroyed
     */
    public void contextDestroyed(ServletContextEvent arg0) {
        log.info("Server is shut down!");
    }

    /**
     * contextInitialized
     */
    public void contextInitialized(ServletContextEvent servletContextEvt) {
        try {
            Class.forName("cn.com.thtf.egov.cms.util.Container");
            HClient.getInstance();
            initConfig(servletContextEvt);
            Container.getTemplateConfig();
            initIdValue();
        } catch (ClassNotFoundException e) {
            log.error("Container not found", e);
        } catch (DocumentException e) {
            log.error("Init Config Error", e);
        }

        log.info("开始把所有员工状态更新成离线");
        IUserService userService = (IUserService) Container.getBean("userServiceImp");
        boolean result = userService.modifyUserOnline();
        log.info("更新员工在线状态结果：" + result);
    }

    /**
     * 初始化各合同、单的id当日最大值
     */
    private void initIdValue() {
        Constants.CONTRACT_MAX_ID_DTO = new ContractIdDto();
        commonService.getMaxIdDto();
    }

    private void initConfig(ServletContextEvent servletContextEvt)
            throws DocumentException {
        SAXReader reader = new SAXReader();
        InputStream input = this.getClass().getResourceAsStream("/config.xml");
        Document doc = reader.read(input);
        Node root = doc.getRootElement();
        Constants.CONTRACT_MONEY = root.selectSingleNode("//config/contract_money")
                .getText();

        Constants.CONTRACT_RATE = root.selectSingleNode("//config/contract_rate")
                .getText();
        Constants.SELL_PLACE = root.selectSingleNode("//config/sell_place").getText();

        String tmpStr = root.selectSingleNode("//config/sell_protect").getText();
        Constants.SELL_PROTECT = Integer.parseInt(tmpStr.trim());
        tmpStr = root.selectSingleNode("//config/sell_check").getText();
        Constants.SELL_CHECK = Integer.parseInt(tmpStr.trim());
        Constants.SELL_BREAK = root.selectSingleNode("//config/sell_break").getText();
        Constants.SELL_LAWSUIT = root.selectSingleNode("//config/sell_lawsuit").getText();
        Constants.DIR_NAME = root.selectSingleNode("//config/dir_name").getText();
        Constants.DOWNLOAD_FILE_NAME_PREFIX_SELL = root.selectSingleNode(
                "//config/sell_prefix_name").getText();
        Constants.DOWNLOAD_FILE_NAME_PREFIX_SELL_BACK = root.selectSingleNode(
                "//config/sell_back_prefix_name").getText();
        Constants.DOWNLOAD_FILE_NAME_PREFIX_BUY = root.selectSingleNode(
                "//config/buy_prefix_name").getText();
        Constants.DOWNLOAD_FILE_NAME_PREFIX_BUY_BACK = root.selectSingleNode(
                "//config/buy_back_prefix_name").getText();
        Constants.MAIL_SERVER_HOST = root.selectSingleNode("//config/mail_server_host")
                .getText();
        Constants.MAIL_SERVER_PORT = root.selectSingleNode("//config/mail_server_port")
                .getText();
        Constants.AUTH_VALIDATE = root.selectSingleNode("//config/auth_validate")
                .getText();
        Constants.FROM_ADDRESS = root.selectSingleNode("//config/from_address").getText();
        // Constants.TO_ADDRESS =
        // root.selectSingleNode("//config/to_address").getText();
        Constants.USER_NAME = root.selectSingleNode("//config/user_name").getText();
        Constants.PASSWORD = root.selectSingleNode("//config/password").getText();
        Constants.SUBJECT = root.selectSingleNode("//config/subject").getText();
        Constants.MAIL_SMTP_HOST = root.selectSingleNode("//config/mail_smtp_host")
                .getText();
        Constants.MAIL_SMTP_AUTH = root.selectSingleNode("//config/mail_smtp_auth")
                .getText();
        Constants.MAIL_SEND_CONFER = root.selectSingleNode("//config/mail_send_confer")
                .getText();
        Constants.MAIL_TYPE = root.selectSingleNode("//config/mail_type").getText();
        Constants.MAIL_TYPE_CODING = root.selectSingleNode("//config/mail_type_coding")
                .getText();
        Constants.NICKNAME = root.selectSingleNode("//config/nickname").getText();

        Constants.EXTEND_EXCEED_DAYS = Integer.parseInt(root.selectSingleNode(
                "//config/extend_exceed_days").getText());

        // 转换相对路径为绝对路径
        Constants.FM_TEMPLATEDIR = servletContextEvt.getServletContext().getRealPath(
                "/WEB-INF/")
                + File.separator + Constants.FM_TEMPLATEDIR;
        Constants.FINA_TEMPLATE = root.selectSingleNode("//config/fina_template")
                .getText();

        Constants.K3_SENDGOODS_TEMPLATE = root.selectSingleNode(
                "//config/k3_sendgoods_template").getText();
        Constants.K3_INCOMESTOCK_TEMPLATE = root.selectSingleNode(
                "//config/k3_incomeStock_template").getText();
        Constants.K3_TO_ADDRESS = root.selectSingleNode("//config/k3_to_address")
                .getText();
        Constants.K3_TO_NAME = root.selectSingleNode("//config/k3_to_name").getText();

        Constants.PURCHASE_MONEY = root.selectSingleNode("//config/purchase_money")
                .getText();

        Constants.ERR_SENDGOODS_TEMPLATE = root.selectSingleNode(
                "//config/err_sendgoods_template").getText();
        // Constants.ERR_TO_NAME =
        // root.selectSingleNode("//config/err_to_name").getText();

        Constants.RETURN_TEMPLATE = root.selectSingleNode("//config/return_template")
                .getText();
        Constants.RETURN_NAME = root.selectSingleNode("//config/return_name").getText();
        Constants.RETURN_TO_ADDRESS = root.selectSingleNode("//config/return_to_address")
                .getText();

        // 采购合同
        Constants.BUY_CONTRACT_MONEY = root.selectSingleNode(
                "//config/buy_contract_money").getText();
        Constants.PROD_GROWTH_RATE = root.selectSingleNode("//config/prod_growth_rate")
                .getText();

        // 上传文件大小设置
        Constants.MAX_POST_SIZE = Integer.parseInt(root.selectSingleNode(
                "//config/max_post_size").getText().trim());
        // 上传文件过大导致失败的错误提示
        Constants.MAX_POST_SIZE_ERR_MSG = root.selectSingleNode(
                "//config/max_post_size_err_msg").getText();

        // 每页显示的记录条数
        Constants.DEFAULT_PAGE_SIZE = root.selectSingleNode("//config/default_page_size")
                .getText();
    }
}