/**
 * ClassName  ISendGoodsCreditLogicService
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-4-20
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.iservice.common;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import cn.com.thtf.egov.cms.dto.ContractIdDto;
import cn.com.thtf.egov.cms.dto.CustomerCreditCommonDto;
import cn.com.thtf.egov.cms.dto.CustomerFundsDto;
import cn.com.thtf.egov.cms.dto.WorkReceiverDto;
import cn.com.thtf.egov.cms.entity.BusiLogEntity;
import cn.com.thtf.egov.cms.entity.ProductTypeEntity;

/**
 * 产品销售可用数、客户信用、回款获取
 * 
 * @author Lubo
 */

public interface ICommonService {

    /**
     * 获取客户信用编号
     * 
     * @param customerId
     * @param productTypeId
     * @return
     */
    public String getCustomerCreditId(Integer customerId, Integer productTypeId);

    /**
     * 新增操作记录
     * 
     * @param para
     * @return
     */
    public boolean addBusiLog(BusiLogEntity para);

    /**
     * 获取产品销售可用数
     * 
     * @param prodId
     *            产品Id
     * @return 销售可用数
     */
    public Integer getAvailProdCnt(String prodId);

    /**
     * 获取客户信用
     * 
     * @param customerId
     *            客户Id
     * @param prodTypeId
     *            产品类型Id
     * @param customerCrdId
     *            客户信用类型Id：查看客户一条信用时填写，否则为null
     * @return CustomerCreditCommonDto
     */
    public List<CustomerCreditCommonDto> getCustomerCredit(String customerId,
            String prodTypeId, String customerCrdId);

    /**
     * 获取产品回款数
     * 
     * @param prodId
     *            产品Id
     * @return 销售可用数
     */
    public BigDecimal getReceivedPayments();

    /**
     * 客户超期欠款判断
     * 
     * @param productTypeId
     * @param customerId
     * @return
     */
    public List<CustomerFundsDto> expiredLogic(Integer customerId);

    /**
     * 回款
     * 
     * @param sellContractId
     * @param appointType
     * @return
     */
    public BigDecimal getSellContractResultMoney(String sellContractId, String appointType);

    /**
     * 在途款
     * 
     * @param sellContractId
     * @param appointType
     * @return
     */
    public BigDecimal getSellContractInRransit(String sellContractId, String appointType);

    /**
     * 合同编号生成
     * 
     * @param prefix
     *            编号前缀
     * @param prodTypeId
     *            产品分类Id
     * @param table
     *            表名
     * @return 生成的编号
     * @throws Exception
     */
    public String getSerialNumber(String prefix, String table);

    /**
     * 产品合同号生成
     * 
     * @param prefix
     *            编号前缀
     * @param prodTypeId
     *            产品分类Id
     * @param table
     *            表名
     * @return 生成产品合同编号
     * @throws Exception
     */
    public String getProdContractSN(String prefix, String prodTypeId, String table);

    /**
     * 产品退货合同号生成
     * 
     * @param prodContSN
     *            产品合同号
     * @param table
     *            表名
     * @return 生成的编号
     * @throws Exception
     */
    public String getProdContractBackSN(String prodContSN, String table);

    /**
     * 产品库存平均价、预计采购价
     * 
     * @param prodId
     *            产品Id
     * @param flag
     *            求平均价和采购价的标识 0:平均价; 1:采购价
     * @return 平均价、预计采购价
     * @throws Exception
     */
    public String getProdAvePrice(String prodId, String flag);

    /**
     * 锁表,锁定选中的产品记录
     * 
     * @param map
     *            产品Id
     * @return true or false
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public boolean lockProductRecord(Map map);

    /**
     * 根据条件检索 接收事物人员ID
     * 
     * @param productTypeId
     *            产品分类ID
     * @param userAreaId
     *            区域ID
     * @param stockroomId
     *            库房ID
     * @param roleIdArr
     *            权限ID
     * @return
     */
    public WorkReceiverDto getUserIdByCondition(Integer productTypeId,
            Integer userAreaId, Integer stockroomId, Integer... roleIdArr);

    /**
     * 查看所有产品分类
     * 
     * @param id
     *            产品分类ID，可以为null
     * @param userId
     *            当前用户ID，可以为null
     * @param roleId
     *            当前用户角色ID，可以为null; userId与roleId必须同时为null或者同时有值
     * @return ProductTypeEntity
     */
    public List<ProductTypeEntity> getAllProductTypes(String id, String userId,
            String roleId);

    /**
     * 根据产品分类获取产品编号
     * 
     * @param prodTypeId
     *            产品分类ID
     * @return prodNo
     */
    public String getProdNoById(String prodTypeId);

    /**
     * 根据产品ID更新产品库存金额
     * 
     * @param prodId
     *            产品ID
     * @param money
     *            库存金额
     * @return true false
     */
    public boolean modifyProdMoneyById(String prodId, String money);

    /**
     * 初始化时获取个表单的最大ID
     * 
     * @return dto
     */
    public ContractIdDto getMaxIdDto();

    /**
     * 根据合同Id,获取合同状态
     * 
     * @param table
     *            表名
     * @param id
     *            合同ID
     * @return 合同状态 -99表示记录已被删除
     */
    public String getStatusById(String table, String id);
}
