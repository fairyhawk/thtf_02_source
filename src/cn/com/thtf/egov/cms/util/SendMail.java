/**
 * ClassName  SendMail
 *
 * History
 * Create User: ChenHuajiang
 * Create Date: 2010-05-17
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.constant.Constants;

/**
 * 邮件类
 * 
 * @author ChenHuajiang
 * 
 */
public class SendMail {
    private static Logger log = LoggerFactory.getLogger(SendMail.class);
    // 邮件服务器地址
    private String host = null;
    // 邮件服务器端口
    private int port = 25;
    // 用户名
    private String userName = null;
    // 密码
    private String passWord = null;

    public SendMail() throws FileNotFoundException, IOException, NullPointerException {
        host = Constants.MAIL_SERVER_HOST;
        port = Integer.parseInt(Constants.MAIL_SERVER_PORT);
        userName = Constants.USER_NAME;
        passWord = Constants.PASSWORD;
    }

    /**
     * 发送邮件
     * 
     * @param mailInfo
     *            待发送的邮件信息　　 　　
     */
    public boolean sendMail(MailSenderInfo mailInfo) {
        boolean isSuccess = false;
        log.info("开始发送邮件");

        Properties props = new Properties();
        // 设置发送邮件的邮件服务器的属性（smtp服务器）
        props.put(Constants.MAIL_SMTP_HOST, Constants.MAIL_SERVER_HOST);
        // 授权校验
        props.put(Constants.MAIL_SMTP_AUTH, Constants.AUTH_VALIDATE);
        // 使用props对象构建一个session
        Session session = Session.getDefaultInstance(props);
        // 根据session创建一个邮件消息
        Message message = new MimeMessage(session);

        try {
            // 邮件标题
            message.setSubject(mailInfo.getSubject());
            // 发送邮件日期
            message.setSentDate(new Date());
            // 发件人
            message.setFrom(mailInfo.getFromAddress());
            // 收件人 【点对点发送】

            if (mailInfo.getToAddress() != null) {
                message.addRecipient(Message.RecipientType.TO, mailInfo.getToAddress());
            }
            if (mailInfo.getAddrList() != null && mailInfo.getAddrList().size() > 0) {// 群发
                message.addRecipients(Message.RecipientType.TO, getToAddress(mailInfo
                        .getAddrList()));
            }
            if (mailInfo.getInternetAddressArr() != null
                    && mailInfo.getInternetAddressArr().length > 0) {// 群发
                message.addRecipients(Message.RecipientType.TO, mailInfo
                        .getInternetAddressArr());
            }
            // 设置邮件消息的主要内容　　　
            String mailContent = mailInfo.getContent();
            message.setText(mailContent);

            if (mailInfo.getType() != null && mailInfo.getType().equals("html")) {// 以HTML格式发送
                // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
                Multipart mainPart = new MimeMultipart();
                // 创建一个包含HTML内容的MimeBodyPart
                BodyPart html = new MimeBodyPart();
                // 设置HTML内容
                html.setContent(mailInfo.getContent(), Constants.MAIL_TYPE_CODING);
                mainPart.addBodyPart(html);
                // 将MiniMultipart对象设置为邮件内容
                message.setContent(mainPart);
            }

            // 发送邮件
            Transport transport = session.getTransport(Constants.MAIL_SEND_CONFER);
            // 连接服务器的邮箱
            transport.connect(host, port, userName, passWord);
            // 把邮件发送出去
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

            isSuccess = true;
        } catch (MessagingException ex) {
            log.error("发送邮件失败" + ex);
            return false;
        }
        log.info("发送邮件成功");
        return isSuccess;
    }

    /**
     * 收件人列表
     * 
     * @param arr
     * @return Address[]
     */
    private Address[] getToAddress(ArrayList<String> arr) {
        Address[] addr = new Address[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            try {
                addr[i] = new InternetAddress(arr.get(i));
            } catch (AddressException e) {
                log.error("设定接收人列表错误", e);
            }
        }
        return addr;
    }
}
