/**
 * ClassName  MailServiceImp
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-6-2
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.serviceimpl.mail;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.Address;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.BaseService;
import cn.com.thtf.egov.cms.application.NewIDao;
import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.constant.Constants;
import cn.com.thtf.egov.cms.dto.CompanyInfoDto;
import cn.com.thtf.egov.cms.dto.MailDto;
import cn.com.thtf.egov.cms.dto.MailSendgoodDto;
import cn.com.thtf.egov.cms.dto.MreturnDto;
import cn.com.thtf.egov.cms.dto.SendGoodsDto;
import cn.com.thtf.egov.cms.entity.MailAddresseeEntity;
import cn.com.thtf.egov.cms.entity.MailEntity;
import cn.com.thtf.egov.cms.entity.MreturnEntity;
import cn.com.thtf.egov.cms.iservice.mail.IMailService;
import cn.com.thtf.egov.cms.util.Container;
import cn.com.thtf.egov.cms.util.MailSenderInfo;
import cn.com.thtf.egov.cms.util.SendMail;
import cn.com.thtf.egov.cms.util.Util;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * MailServiceImp
 * 
 * @author Lubo
 */
public class MailServiceImp extends BaseService implements IMailService {

    /** log */
    private static Logger log = LoggerFactory.getLogger(MailServiceImp.class);
    /** NewIDao */
    private NewIDao dao;

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.mail.IMailService#mreturn(cn.com.thtf.egov
     * .cms.entity.MreturnEntity)
     */
    public boolean mreturn(MreturnEntity para) {
        boolean result = false;
        try {
            MreturnDto mreturnDto = (MreturnDto) dao.queryForObject(
                    "mreturn_sqlMap.selectMreturnById", para.getId());

            Map<String, MreturnDto> ftlPara = new HashMap<String, MreturnDto>();
            ftlPara.put("mreturn", mreturnDto);

            Configuration config = Container.getTemplateConfig();
            Template template = config.getTemplate(Constants.RETURN_TEMPLATE);
            StringWriter outWriter = new StringWriter();
            template.process(ftlPara, outWriter);

            MailEntity mailEntity = new MailEntity();
            mailEntity.setType(10);
            mailEntity.setUserName(mreturnDto.getUserName());
            mailEntity.setSubject(new StringBuffer("回款确认_").append(mreturnDto.getId())
                    .toString());
            mailEntity.setText(outWriter.toString());
            mailEntity.setFlag(0);

            String mailId = dao.insert("mail_sqlMap.insertMail", mailEntity);

            MailAddresseeEntity addresseeEntity = new MailAddresseeEntity();
            addresseeEntity.setMailId(Integer.parseInt(mailId));
            addresseeEntity.setMail(Constants.RETURN_TO_ADDRESS);
            addresseeEntity.setName(Constants.RETURN_NAME);

            dao.insert("mail_sqlMap.insertMailAddressee", addresseeEntity);

            result = true;
        } catch (Exception e) {
            log.error("资金管理员发送邮件失败", e);
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.mail.IMailService#sendMail(cn.com.thtf.
     * egov.cms.dto.MailDto)
     */
    @SuppressWarnings("unchecked")
    public boolean sendMail(MailDto para) {
        boolean result = false;
        try {
            MailDto mail = getMail(para);
            /* 发送邮件 */
            MailSenderInfo mailSender = new MailSenderInfo();

            List<MailDto> detailList = dao.queryForlist("mail_sqlMap.selectMailDetail",
                    mail);
            Address[] addressArr = new Address[detailList.size()];
            for (int i = 0; i < detailList.size(); i++) {
                addressArr[i] = mailSender.getAddress(detailList.get(i).getMail(),
                        detailList.get(i).getName());
            }

            mailSender.setType(Constants.MAIL_TYPE);
            mailSender.setContent(mail.getText());
            mailSender.setSubject(mail.getSubject());
            mailSender.setInternetAddressArr(addressArr);
            mailSender.setToAddress(null);
            result = new SendMail().sendMail(mailSender);

            if (result) {
                mail.setDatetime(Util.getDateTime());
                mail.setFlag("1");
                dao.update("mail_sqlMap.updateMail", mail);
            }
        } catch (Exception e) {
            log.error("发送邮件异常", e);
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.mail.IMailService#getMail(cn.com.thtf.egov
     * .cms.dto.MailDto)
     */
    @SuppressWarnings("unchecked")
    public MailDto getMail(MailDto para) {
        MailDto result = null;

        try {
            result = (MailDto) dao.queryForObject("mail_sqlMap.selectMail", para);

            List<MailDto> detailList = dao.queryForlist("mail_sqlMap.selectMailDetail",
                    result);
            StringBuffer sb = new StringBuffer();
            for (MailDto mailDto : detailList) {
                sb.append(mailDto.getName()).append(" ");
            }
            result.setName(sb.toString());
        } catch (Exception e) {
            log.error("查看邮件错误!", e);
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.mail.IMailService#getMail(cn.com.thtf.egov
     * .cms.dto.MailDto, cn.com.thtf.egov.cms.application.NewPage)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<MailDto> getMailList(MailDto para, NewPage page) {
        List result = null;
        try {

            if (StringUtils.isNotBlank(para.getName())) {
                List<MailDto> detailResult = dao.queryForlist(
                        "mail_sqlMap.selectMailDetailByName", para);

                StringBuffer sb = new StringBuffer();
                for (MailDto mailDto : detailResult) {
                    sb.append(mailDto.getMailId()).append(",");
                }
                if (sb.length() > 0) {
                    para.setIdArr(sb.substring(0, sb.length() - 1));
                }
            }

            result = queryRecords(dao, "selectMail.date", para, page);
            for (Object object : result) {
                MailDto mail = (MailDto) object;
                List<MailDto> detailList = dao.queryForlist(
                        "mail_sqlMap.selectMailDetail", mail);
                StringBuffer sb = new StringBuffer();
                for (MailDto mailDto : detailList) {
                    sb.append(mailDto.getName()).append(" ");
                }
                mail.setName(sb.toString());
            }

        } catch (Exception e) {
            log.error("检索邮件列表错误！", e);
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.mail.IMailService#sendGoodsException(java
     * .lang.String)
     */
    public boolean sendGoodsException(SendGoodsDto para) {

        boolean result = false;
        try {

            Map<String, SendGoodsDto> ftlPara = new HashMap<String, SendGoodsDto>();
            ftlPara.put("sendGoods", para);

            Configuration config = Container.getTemplateConfig();
            Template template = config.getTemplate(Constants.ERR_SENDGOODS_TEMPLATE);
            StringWriter outWriter = new StringWriter();

            template.process(ftlPara, outWriter);

            String subject = new StringBuffer("发货异常_").append(para.getId()).toString();

            /* 发送邮件 */
            MailSenderInfo mailSender = new MailSenderInfo();

            mailSender.setType(Constants.MAIL_TYPE);
            mailSender.setContent(outWriter.toString());
            mailSender.setSubject(subject);
            mailSender.setToAddress(mailSender.getAddress(para.getToUserMail(),
                    para.getToUserName()));

            boolean sendResult = new SendMail().sendMail(mailSender);

            MailEntity mailEntity = new MailEntity();
            mailEntity.setType(12);
            mailEntity.setUserName(para.getUserName());
            mailEntity.setSubject(subject);
            mailEntity.setText(outWriter.toString());

            if (sendResult) {
                mailEntity.setFlag(1);
                mailEntity.setDatetime(Util.getDateTime());
            } else {
                mailEntity.setFlag(0);
            }

            String mailId = dao.insert("mail_sqlMap.insertMail", mailEntity);

            MailAddresseeEntity addresseeEntity = new MailAddresseeEntity();
            addresseeEntity.setMailId(Integer.parseInt(mailId));
            addresseeEntity.setMail(para.getToUserMail());
            addresseeEntity.setName(para.getToUserName());

            dao.insert("mail_sqlMap.insertMailAddressee", addresseeEntity);

            result = true;
        } catch (Exception e) {
            log.error("出库通知K3失败", e);
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.mail.IMailService#sendGoodsMsg(cn.com.thtf
     * .egov.cms.dto.SendGoodsAddDto)
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean outStockroom(String orderId, String orderType) {
        boolean result = false;
        try {
            /* 公司税 */
            CompanyInfoDto companyInfo = (CompanyInfoDto) dao.queryForObject(
                    "base_sqlMap.companyInfo", null);
            BigDecimal taxRate = new BigDecimal(companyInfo.getTax_rate()).add(
                    new BigDecimal("100")).divide(new BigDecimal("100"), 15,
                    BigDecimal.ROUND_HALF_UP);

            /* 给K3库管系统发Email */
            Map<String, List<MailSendgoodDto>> ftlPara = new HashMap<String, List<MailSendgoodDto>>();

            List<MailSendgoodDto> resultList = null;

            MailEntity mailEntity = new MailEntity();

            /* 根据单据类型不同分别检索模板内容 */
            if (StringUtils.equals(orderType, "1")) {
                resultList = dao.queryForlist(
                        "mail_sqlMap.selectK3MailVauleBySendgoodsId", orderId);

                mailEntity.setType(1);
                mailEntity.setSubject(new StringBuffer("出库_").append(orderId).toString());
            } else if (StringUtils.equals(orderType, "2")) {
                resultList = dao.queryForlist(
                        "mail_sqlMap.selectK3MailVauleByBuyBackGoodsId", orderId);

                mailEntity.setType(3);
                mailEntity.setSubject(new StringBuffer("返厂_").append(orderId).toString());
            } else if (StringUtils.equals(orderType, "3")) {
                resultList = dao.queryForlist("mail_sqlMap.selectK3MailVauleByMoveId",
                        orderId);
                for (MailSendgoodDto mailSendgoodDto : resultList) {
                    mailSendgoodDto.setPrice(mailSendgoodDto.getPrice().multiply(taxRate)
                            .setScale(2, BigDecimal.ROUND_HALF_UP));
                    mailSendgoodDto.setMoney(mailSendgoodDto.getPrice().multiply(
                            new BigDecimal(mailSendgoodDto.getCount())));
                }

                mailEntity.setType(5);
                mailEntity.setSubject(new StringBuffer("移库出库_").append(orderId)
                        .toString());
            } else if (StringUtils.equals(orderType, "4")) {
                resultList = dao.queryForlist(
                        "mail_sqlMap.selectK3MailVauleBySampleOutId", orderId);

                mailEntity.setType(7);
                mailEntity.setSubject(new StringBuffer("样品借出_").append(orderId)
                        .toString());
            } else {
                log.error("不存在的类型！");
                return false;
            }

            ftlPara.put("sendGoodsList", resultList);

            Configuration config = Container.getTemplateConfig();
            Template template = config.getTemplate(Constants.K3_SENDGOODS_TEMPLATE);
            StringWriter outWriter = new StringWriter();

            template.process(ftlPara, outWriter);

            mailEntity.setUserName(resultList.get(0).getUserName());
            mailEntity.setText(outWriter.toString());
            mailEntity.setFlag(0);

            String mailId = dao.insert("mail_sqlMap.insertMail", mailEntity);

            MailAddresseeEntity addresseeEntity = new MailAddresseeEntity();
            addresseeEntity.setMailId(Integer.parseInt(mailId));
            addresseeEntity.setMail(Constants.K3_TO_ADDRESS);
            addresseeEntity.setName(Constants.K3_TO_NAME);

            dao.insert("mail_sqlMap.insertMailAddressee", addresseeEntity);

            result = true;
        } catch (Exception e) {
            log.error("出库通知K3失败", e);
        }
        return result;

    }

    /**
     * @return the dao
     */
    public NewIDao getDao() {
        return dao;
    }

    /**
     * @param dao
     *            the dao to set
     */
    public void setDao(NewIDao dao) {
        this.dao = dao;
    }

}
