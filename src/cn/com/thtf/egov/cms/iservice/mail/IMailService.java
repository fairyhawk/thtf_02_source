/**
 * ClassName  IMailService
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-6-2
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.iservice.mail;

import java.util.List;

import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.dto.MailDto;
import cn.com.thtf.egov.cms.dto.SendGoodsDto;
import cn.com.thtf.egov.cms.entity.MreturnEntity;

/**
 * IMailService
 * 
 * @author Lubo
 */
public interface IMailService {

    /**
     * 回款发送邮件
     * 
     * @param para
     * @return
     */
    public boolean mreturn(MreturnEntity para);

    /**
     * 发送邮件
     * 
     * @param para
     * @return
     */
    public boolean sendMail(MailDto para);

    /**
     * 查看邮件
     * 
     * @param para
     * @param page
     * @return
     */
    public MailDto getMail(MailDto para);

    /**
     * 邮件列表
     * 
     * @param para
     * @param page
     * @return
     */
    public List<MailDto> getMailList(MailDto para, NewPage page);

    /**
     * 出库 通知K3
     * 
     * @param orderId
     * @param orderType
     * @param userName
     * @return
     */
    public boolean outStockroom(String orderId, String orderType);

    /**
     * 发货异常 通知销售经理
     * 
     * @param para
     * @return
     */
    public boolean sendGoodsException(SendGoodsDto para);
}
