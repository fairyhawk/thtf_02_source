/**
 * ClassName  IReceiveInvoiceService
 *
 * History
 * Create User: ChenHuajiang
 * Create Date: 2010-6-9
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.iservice.invoice;

import java.math.BigDecimal;
import java.util.List;

import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.dto.ReceiveInvoiceDto;
import cn.com.thtf.egov.cms.entity.ReceiveInvoiceDetailEntity;
import cn.com.thtf.egov.cms.form.InStockSearchForm;
import cn.com.thtf.egov.cms.form.ReceiveInvoiceForm;

/**
 * 收票管理
 * 
 * @author ChenHuajiang
 */

public interface IReceiveInvoiceService {
    /**
     * 收票管理列表
     * 
     * @param param
     * @return List
     */
    @SuppressWarnings("unchecked")
    public List getReceiveInvoiceList(ReceiveInvoiceForm param, NewPage page);

    /**
     * 产品分类
     * 
     * @param userId
     *            当前用户ID
     * @return List
     */
    @SuppressWarnings("unchecked")
    public List getProdTypeDeptList(String userId);

    /**
     * 供应商信息
     * 
     * @param name
     *            供应商名称
     * @return list
     */
    @SuppressWarnings("unchecked")
    public List getSuppliers(String name, NewPage page);

    /**
     * 采购发票录入
     * 
     * @param param
     * 
     * @return Integer
     */
    public Integer addReceiveInvoice(ReceiveInvoiceForm param);

    /**
     * 采购发票信息
     * 
     * @param id
     *            收货发票ID
     * 
     * @return ReceiveInvoiceDto
     */
    public ReceiveInvoiceDto getInvoiceInfoById(String id);

    /**
     * 入库明细选择
     * 
     * @param param
     *            检索条件
     * 
     * @return ReceiveInvoiceDto
     */
    @SuppressWarnings("unchecked")
    public List getInStockProdList(InStockSearchForm param, NewPage page);

    /**
     * 采购发票查看
     * 
     * @param id
     *            收票ID
     * 
     * @return List
     */
    @SuppressWarnings("unchecked")
    public List getInvoiceProdList(String id);

    /**
     * 根据收货发票ID获取记录数
     * 
     * @param id
     *            收货发票ID
     * 
     * @return Integer
     */
    public Integer getRecordCountById(String id);

    /**
     * 根据收货发票ID删除记录
     * 
     * @param id
     *            收货发票ID
     * 
     * @return Integer
     */
    public Integer delRecordById(String id);

    /**
     * 根据收货发票ID更新记录 (退票)
     * 
     * @param id
     *            收货发票ID
     * 
     * @return Integer
     */
    public Integer modifyRecordById(String id);

    /**
     * 查找是否有相同发票号
     * 
     * @param number
     *            发票号Number
     * 
     * @return Integer
     */
    public boolean isExistByNumber(String number);
    /**
     * 删除采购发票明细信息
     * @author HanHaiyun
     * @param reciveInvoiceId 发票流水号
     * @return 是否删除成功
     */
    public boolean delReciveInvoiceDetailById(String reciveInvoiceId);
    /**
     * 添加采购发票明细信息
     * @author HanHaiyun
     * @param receiveInvoiceDetail 发票明细信息
     * @return 是否添加成功
     */
    public boolean addReciveInvoiceDetail(List<ReceiveInvoiceDetailEntity> receiveInvoiceDetails,BigDecimal receiveRate,Integer receiveType,String receiveInvoiceId);
}
