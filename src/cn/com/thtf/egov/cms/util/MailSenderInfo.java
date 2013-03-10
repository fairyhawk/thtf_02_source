package cn.com.thtf.egov.cms.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.mail.Address;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.constant.Constants;

/**
 * 设定邮件内容
 * 
 * @author ChenHuajiang
 * 
 */
public class MailSenderInfo {
    private static Logger log = LoggerFactory.getLogger(MailSenderInfo.class);
    // 邮件发送者的地址
    private InternetAddress fromAddress = null;

    // 邮件接收者的地址【点对点发送】
    private InternetAddress toAddress = null;

    // 邮件接收者的地址【群发】
    private ArrayList<String> addrList;
    // 邮件接收者的地址【群发】
    private Address[] internetAddressArr;

    // 登陆邮件发送服务器的用户名和密码
    private String userName = Constants.USER_NAME;
    private String password = Constants.PASSWORD;

    // 是否需要身份验证
    private boolean validate = Boolean.valueOf(Constants.AUTH_VALIDATE);

    // 邮件主题
    private String subject = Constants.SUBJECT;

    // 邮件的文本内容
    private String content;

    // 邮件类型
    private String type = null;

    public MailSenderInfo() {
        fromAddress = getAddress(Constants.FROM_ADDRESS, Constants.NICKNAME);
        toAddress = getAddress(Constants.K3_TO_ADDRESS, null);
    }

    /**
     * @return the internetAddressArr
     */
    public Address[] getInternetAddressArr() {
        return internetAddressArr;
    }

    /**
     * @param internetAddressArr
     *            the internetAddressArr to set
     */
    public void setInternetAddressArr(Address[] internetAddressArr) {
        this.internetAddressArr = internetAddressArr;
    }

    public InternetAddress getAddress(String addr, String name) {
        InternetAddress address = null;
        try {
            if (name != null) {// 设定发件人及发件人的名字
                try {
                    address = new InternetAddress(addr, name);
                } catch (UnsupportedEncodingException e) {
                    log.error("设定发件人错误", e);
                }
            } else {// 设定收件人
                address = new InternetAddress(addr);
            }
        } catch (AddressException e) {
            log.error("设定发【收】件人错误", e);
        }
        return address;
    }

    public InternetAddress getFromAddress() {
        return fromAddress;
    }

    public InternetAddress getToAddress() {
        return toAddress;
    }

    public String getUserName() {
        return userName;
    }

    public void setFromAddress(InternetAddress fromAddress) {
        this.fromAddress = fromAddress;
    }

    public void setToAddress(InternetAddress toAddress) {
        this.toAddress = toAddress;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPassword() {
        return password;
    }

    public String getSubject() {
        return subject;
    }

    public boolean isValidate() {
        return validate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArrayList<String> getAddrList() {
        return this.addrList;
    }

    public void setAddrList(ArrayList<String> addrList) {
        this.addrList = addrList;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
