package cn.com.thtf.egov.cms.iservice.sell;

import java.util.List;

import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.dto.CustomerInfoDto;
import cn.com.thtf.egov.cms.dto.InvoiceDetailDto;
import cn.com.thtf.egov.cms.dto.InvoiceInfoDto;
import cn.com.thtf.egov.cms.dto.InvoiceListDto;
import cn.com.thtf.egov.cms.dto.MakeInvoiceAddDto;
import cn.com.thtf.egov.cms.dto.ProductTypeInfoDto;
import cn.com.thtf.egov.cms.dto.SellInvocleOfAddDto;
import cn.com.thtf.egov.cms.dto.SendGoodsParticularListDto;
import cn.com.thtf.egov.cms.entity.MailEntity;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.util.MailSenderInfo;

public interface IInvoiceListService {
    /**
     * 开票显示列表
     * 
     * @param InvoiceListDto
     * @return List<ProductTypeInfoDto>
     * @throws Exception 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<Object> getInvoiceList(NewPage nPage, UserEntity user) throws Exception;

    /**
     * 产品分类
     * 
     * @param ProductTypeInfoDto
     * @return List<InvoiceListDto>
     * @throws Exception
     */
    public List<ProductTypeInfoDto> getProductList(ProductTypeInfoDto productDto)
            throws Exception;// queryProductList

    /**
     * 检索开票
     * 
     * @param InvoiceListDto
     * @return List<InvoiceListDto>
     * @throws Exception
     */
    public List<Object> getInvoiceListByObj(NewPage nPage, UserEntity user,
            InvoiceListDto invoiceDto);

    /**
     * 删除开票
     * 
     * @return boolean 
     * @throws Exception
     */
    public boolean removeInvoice(String id);

    /**
     * 获得产品分类列表
     * 
     * @return List分类列表
     * @throws Exception
     */
    public List<ProductTypeInfoDto> getProductType();

    /**
     * 获得客户信息列表
     * 
     * @return List<ProductTypeInfoDto>
     * @throws Exception
     */
    public List<Object> getCustomerList(NewPage nPage, CustomerInfoDto customerDto);

    /**
     * 查找客户信息列表
     * @param 分页 客户名字
     * @return List<ProductTypeInfoDto>
     * @throws Exception
     */
    public List<Object> getCustomerListByName(NewPage nPage, CustomerInfoDto customerDto);

    /**
     * 获得json格式的客户信息
     * 
     * @param List
     *            <Object> customerList
     * @return String json格式的客户信息
     * @throws Exception
     */
    public String getCustomerInfoOfJSON(List<Object> customerList);

    /**
     * 获得发货明细信息列表
     * 
     * @param NewPage
     *            nPage
     * @param SendGoodsParticularListDto
     *            sendGoodsParticularListDto
     * @return List<Object>
     * @throws Exception
     */
    public List<Object> getSendGoodsList(NewPage nPage,
    		SendGoodsParticularListDto sendGoodsParticularListDto);

    /**
     * 查找发货明细信息列表
     * 
     * @param NewPage
     *            nPage
     * @param SendGoodsParticularListDto
     *            SendGoodsDto
     * @return List<Object>
     * @throws Exception
     */
    public List<Object> getSendGoodsListByObj(NewPage nPage,
    		SendGoodsParticularListDto SendGoodsDto);
    /**
     * 添加开票
     * 
     * @param MakeInvoiceAddDto makeInvoiceAddDto dto String returnValueToServer json数据
     * @throws Exception
     */
    public boolean addInvoice(MakeInvoiceAddDto makeInvoiceAddDto,String returnValueToServer) throws Exception;


    /**
     * 查找开票之客户信息
     * 
     * @param 开票
     *            ，id
     * @throws Exception
     */
    public Object getInvoiceOfCustomer(String id);

    /**
     * 查找开票之明细
     * 
     * @param 开票
     *            ，id
     * @throws Exception
     */
    public List<Object> getInvoiceOfDetail(String id);

    /**
     * 修改开票
     * 
     * @param MakeInvoiceAddDto
     *            makeInvoiceAddDto开票对象 productOfJson json格式数据
     * @throws Exception
     */
    public boolean modifyInvoice(MakeInvoiceAddDto makeInvoiceAddDto,String productOfJson) throws Exception;

    /**
     * 删除开票之明细
     * 
     * @param 开票明细
     *            ，id
     * @throws Exception
     */
    public int modifyInvoiceOfDetailTodel(String id) throws Exception;

    /**
     * 根据id查询开票信息
     * 
     * @author lilewei
     * @param invoiceId
     * @return InvoiceInfoDto
     * 
     */
    public InvoiceInfoDto getInvoiceById(String invoiceId);

    /**
     * 根据开票id查询开票明细列表
     * 
     * @author lilewei
     * @param invoiceId
     *            开票id
     * @return
     */
    public List<InvoiceDetailDto> getInvoiceDetailListByInvoiceId(String invoiceId);

    /**
     * 开票通知更新开票
     * 
     * @author lilewei     *
     * @param invoice isTrue邮件是否成功 mailEntity
     * @return
     */
    public boolean modifyInvoiceforNotify(InvoiceInfoDto invoice,boolean isTrue,MailEntity mailEntity,MailSenderInfo mail);
    /**
     * 发票列表
     * @param 开票id
     * @throws Exception 
     */	
	public List<Object> getSellInvoiceListById(String id);
    /**
     * 显示开票明细的未开发票列表
     * @param 开票id
     * @throws Exception 
     */
	public List<Object> getInvoiceOfDetailToSellById(String id);
    /**
     * 添加发票
     * @param SellInvocleOfAddDto sellInvocleOfAddDto  
     * @param String[] invoiceDetail开票明细数组
     * @param UserEntity user
     * @throws Exception 
     */
	public int addSellInvoice(SellInvocleOfAddDto sellInvocleOfAddDto,String invoiceDetail[],UserEntity user);
    /**
     * 删除发票
     * @param sellInvoiceid 发票id  sellInvoiceDetailIdfa
     * @throws Exception 
     * @return true false 
     */
	public boolean deleteSellInvoice(String sellInvoiceid,String sellInvoiceDetailId,String makeInvoiceId,UserEntity user);
    /**
     * 获取发票number是否存在
     * @param id 发票号
     * @throws Exception 
     * @return true false 
     */
	public List<SellInvocleOfAddDto> getSellInvoiceNumber(String id);
	/**
	 * K3确认开票，修改状态为开票成功
	 * @param invoiceDto
	 * @return
	 */
	public boolean k3ConfirmInvoice(InvoiceInfoDto invoiceDto);
	
}
