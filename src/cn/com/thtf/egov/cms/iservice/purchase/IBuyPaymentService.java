package cn.com.thtf.egov.cms.iservice.purchase;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.dto.BuyPaymentAddDto;
import cn.com.thtf.egov.cms.dto.BuyPaymentInfoDto;
import cn.com.thtf.egov.cms.dto.BuyPaymentListQueryDto;
import cn.com.thtf.egov.cms.dto.BuyPaymentBuyContractDto;
import cn.com.thtf.egov.cms.entity.PaymentEntity;
import cn.com.thtf.egov.cms.entity.ProductTypeEntity;

/**
 * 采购付款
 * 
 * @author lxs
 */
public interface IBuyPaymentService {

    /**
     * 采购付款列表检索
     * 
     * @author LuPing
     * @param buyPaymentListQueryDto
     * @param page
     */

    public List<Object> getBuyPaymentList(NewPage page,
            BuyPaymentListQueryDto buyPaymentListQueryDto);

    /**
     * 付款单查看
     * 
     * @author lxs
     * @param id
     * @return
     */
    public BuyPaymentInfoDto viewBuyPayment(String id);

    /**
     * 获取所有产品分类
     * 
     * @author LuPing
     * @return
     */
    public List<ProductTypeEntity> getAllProductType();

    /**
     * 检索所有供货商
     * 
     * @author LuPing
     * @return
     */
    @SuppressWarnings("unchecked")
    public List getBuyPaymentAllSupplier(String supplierName, NewPage page);

    /**
     * 付款查看采购合同信息
     * 
     * @author lxs
     * @return
     */
    public List<BuyPaymentBuyContractDto> getBuyPaymentBuyContract(String id);

    /**
     * 根据供货商获取联系人信息
     * 
     * @author LuPing
     * @param supplierId
     * @return
     */
    @SuppressWarnings("unchecked")
    public List getBuyPaymentSupplierLinkmanBySupplierId(String supplierId);

    /**
     * 根据采购专员获取所对应的产品分类集合
     * 
     * @author LuPing
     * @param userId
     * @return
     */
    @SuppressWarnings("unchecked")
    public List getBuyPaymentProductTypeByUserId(String userId);

    /**
     * 付款申请 合同信息
     * 
     * @author LuPing
     * @param map
     * @param page
     * @return
     */
    @SuppressWarnings("unchecked")
    public List getBuyPaymentSelectBuyContracts(Map<String, String> map, NewPage page);

    /**
     * 付款查看产品信息
     * 
     * @author lxs
     * @return
     */
    public List<BuyPaymentBuyContractDto> getBuyPaymentBuyProduct(String id);

    /**
     * 付款评审
     * 
     * @author lxs
     * @param p
     * @param iderpd
     * @return
     */
    public Boolean buyPaymentBuyAudit(PaymentEntity p);

    /**
     * 入库明细信息
     * 
     * @author LuPing
     * @param map
     * @param page
     * @return
     */
    @SuppressWarnings("unchecked")
    public List getBuyPaymentInStockDetail(Map<String, String> map, NewPage page);

    /**
     * 付款申请
     * 
     * @author LuPing
     * @param buyP
     * @return
     */
    public Boolean addBuyPayment(BuyPaymentAddDto buyP);

    /**
     * 付款打印
     * 
     * @author lxs
     * @param id
     * @return
     */
    public Boolean buyPaymentPrint(PaymentEntity pe);

    /**
     * 付款承兑
     * 
     * @author lxs
     * @param map
     * @return
     */
    public Boolean acceptBuyPayment(PaymentEntity pe);

    /**
     * 付款确认
     * 
     * @author lxs
     * @param map
     * @return
     */
    public Boolean confirmBuyPayment(PaymentEntity pe, String[] inStockId,
            String[] productId, String[] appointMoney, String contractType);

    /**
     * 付款修改
     * 
     * @author LuPing
     * @param buyP
     * @return
     */
    public Boolean modifyBuyPayment(BuyPaymentAddDto buyP);

    /**
     * 付款指定
     * 
     * @author lxs
     * @param bpad
     * @return
     */
    public Boolean appointBuyPayment(BuyPaymentAddDto bpad);

    /**
     * 付款再指定
     * 
     * @author lxs
     * @param buyP
     * @return
     */
    public Boolean againAppointBuyPayment(BuyPaymentAddDto buyP, String contractType);

    /**
     * 付款删除
     * 
     * @author lxs
     * @param id
     * @return
     */
    public Boolean deleteBuyPayment(String id);

    /**
     * 外单查询
     * 
     * @param id
     * @return
     */
    public Integer buyPaymentzd(String id);
    
    /**
     * 未指定金额查询
     * 
     * @param id
     * @return
     */
    public BigDecimal productTypeIdwzdMoney(java.util.Map<String, String> map);
    
    /**
     * 合同金额验证
     * 
     * @author LuPing
     * @param BuyPaymentAddDto
     *            buyP
     * @return boolean
     * @throws Exception
     */
    public boolean validatePaymentMoney(BuyPaymentAddDto buyP) throws Exception;
}
