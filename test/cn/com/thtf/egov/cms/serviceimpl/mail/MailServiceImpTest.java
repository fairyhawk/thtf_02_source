/**
 * ClassName  MailServiceImpTest
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-6-3
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.serviceimpl.mail;

import org.junit.Test;

import cn.com.thtf.egov.cms.constant.Constants;
import cn.com.thtf.egov.cms.dto.SendGoodsDto;
import cn.com.thtf.egov.cms.iservice.mail.IMailService;
import cn.com.thtf.egov.cms.util.Container;
import cn.com.thtf.egov.cms.util.Util;

/**
 * MailServiceImpTest
 * 
 * @author Lubo
 */
public class MailServiceImpTest {

    public void outStockroom() {
        Constants.FM_TEMPLATEDIR = "E:\\清华同方\\Svn\\20_Source\\WebRoot\\WEB-INF\\template\\";
        Constants.K3_SENDGOODS_TEMPLATE = "K3Template\\sendgoodTemplate.ftl";
        Constants.K3_TO_ADDRESS = "mail@mail.com";
        Constants.K3_TO_NAME = "k3admin";

        IMailService mailService = (IMailService) Container.getBean("mailServiceImp");

        mailService.outStockroom("FH100608001", "1");
    }

    @Test
    public void sendGoodsException() {
        Constants.FM_TEMPLATEDIR = "E:\\清华同方\\Svn\\20_Source\\WebRoot\\WEB-INF\\template\\";
        Constants.ERR_SENDGOODS_TEMPLATE = "sendgoodsErrTemplate\\sendgoodsErrTemplate.ftl";
        //Constants.ERR_TO_NAME = "ceshi ";

        Constants.FROM_ADDRESS = "b@b.b";
        Constants.NICKNAME = "abc大傻子";
        Constants.K3_TO_ADDRESS = "333";
        Constants.MAIL_SERVER_PORT = "25";

        Constants.MAIL_SMTP_HOST = "mail.smtp.host";
        Constants.MAIL_SERVER_HOST = "smtp.thtf.com.cn";
        Constants.MAIL_SMTP_AUTH = "mail.smtp.auth";
        Constants.AUTH_VALIDATE = "true";
        Constants.MAIL_TYPE_CODING = "utf-8";
        Constants.USER_NAME = "cms_server";
        Constants.PASSWORD = "600100";
        Constants.FROM_ADDRESS = "cms_server@thtf.com.cn";
        Constants.MAIL_SEND_CONFER = "smtp";
        // Constants.MAIL_TYPE = "html";

        IMailService mailService = (IMailService) Container.getBean("mailServiceImp");
        SendGoodsDto auditPara = new SendGoodsDto();
        auditPara.setId("发货单号");
        auditPara.setStatus(4);
        auditPara.setStkAdmIdea("010");
        auditPara.setStkAdmText("阿斯大时代");

        auditPara.setStkAdmId("luffyday");
        auditPara.setStkAdmName("用户名");
        auditPara.setStkAdmDate(Util.getDate());
        auditPara.setToUserMail("lub@bbut.com.cn");
        auditPara.setUserName("路博收");

        mailService.sendGoodsException(auditPara);
    }

}
