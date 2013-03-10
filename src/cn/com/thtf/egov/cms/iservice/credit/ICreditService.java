/**
 * ClassName  ICreditService
 *
 * History
 * Create User: Chenhj
 * Create Date: 2010-4-7
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.iservice.credit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.dto.CreditTypeLimitDto;
import cn.com.thtf.egov.cms.dto.CustomerCreditLimitDto;
import cn.com.thtf.egov.cms.dto.ProdCreditLimitDto;
import cn.com.thtf.egov.cms.entity.CreditTypeEntity;
import cn.com.thtf.egov.cms.entity.CreditTypeLimitEntity;
import cn.com.thtf.egov.cms.entity.CustomerCreditDetailEntity;
import cn.com.thtf.egov.cms.entity.CustomerCreditEntity;
import cn.com.thtf.egov.cms.entity.CustomerEntity;
import cn.com.thtf.egov.cms.entity.ProductTypeEntity;

/**
 * 信用管理模块Service接口
 * 
 * @author Chenhj
 */

public interface ICreditService {

    /** 查看所有信用类型 */
    @SuppressWarnings("unchecked")
    public List findAllCreditType(NewPage page);

    /** 根据ID查看信用类型 */
    public CreditTypeEntity findCreditTypeById(String id);

    /** 修改信用类型 */
    public boolean updateCreditType(CreditTypeEntity creditType);

    /** 查看所有产品分类信用总额 */
    @SuppressWarnings("unchecked")
    public List findAllProdCreditLimit(NewPage page, ProdCreditLimitDto prodCreditLimitDto);

    /** 根据ID查看产品分类信用总额 相关数据 */
    public CreditTypeLimitDto findProdCreditLimitById(CreditTypeLimitDto creditTypeLimit);

    /** 根据修改条件查找信用类型，判断信用类型是否已存在 */
    public boolean findNameByCondition(String cond);

    /** 根据ID查看产品分类信用总额 */
    public ProdCreditLimitDto findProdCreditLimitById(String prodId);

    /** 修改信用类型总额 */
    public boolean updateCreditTypeLimit(CreditTypeLimitDto creditTypeLimit);

    /** 修改产品信用总额 */
    public boolean updateProdCreditLimit(ProductTypeEntity productType);

    /** 查看所有产品分类信用额度 */
    @SuppressWarnings("unchecked")
    public List findAllProdAssortCreditLimit(NewPage page,
            CreditTypeLimitDto creditTypeLimitDto);

    /** 取得客户信用类型已分配额度 */
    public String findCustomerAssignLimit(CustomerCreditEntity customerCredit);

    /**
     * 取得添加页面的可分配额度 根据产品大类Id查询给产品信用额度分配情况 getCanAssignLimitByProductId
     */
    public String findProdAssortCreditLimitById(Integer prodTypeId);

    /** 根据ID查看客户信用 */
    public CustomerCreditLimitDto findCustomerCreditLimitById(String id);

    /** 查询所有的信用类型 */
    @SuppressWarnings("unchecked")
    public List findAllCreditType();

    /**
     * 添加产品分类信用类型额度
     * */
    public boolean insertProdAssortCreditTypeLimit(CreditTypeLimitDto creditTypeLimit);

    /** 查找数据库中是否存在相同的记录 */
    public boolean findCreditTypeByCondition(CreditTypeLimitDto creditTypeLimit);

    /** 产品分类信用类型额度删除 */
    public boolean deleteProdAssortCreditTypeLimit(CreditTypeLimitDto creditTypeLimit);

    /** 根据条件查询所有客户信用 */
    @SuppressWarnings("unchecked")
    public List findAllCustomerCreditByCondition(CustomerCreditLimitDto customerCredit,
            NewPage page);

    /** 添加客户信用 */
    @SuppressWarnings("unchecked")
    public boolean insertCustomerCreditLimit(List list);

    /** 修改客户信用 */
    @SuppressWarnings("unchecked")
    public boolean updateCustomerCreditLimit(List list);

    /** 客户信用删除 */
    public boolean deleteCustomerCreditLimit(CustomerCreditLimitDto dto);

    /**
     * 查询用户信用的已用额度
     * 
     * @param id
     * @return
     * @throws Exception
     */
    public Double getUsedCreditById(String id);

    /**
     * 根据product_type_id 和credit_type_id查询可分配额度
     * 
     * @param dtoCredit
     * @return
     * @throws Exception
     */
    public CustomerCreditLimitDto getTotalClimtByProIdCrdtId(
            CustomerCreditLimitDto dtoCredit);

    /**
     * 更新客户信用后的总可分配额度时间戳更新
     * 
     * @author 李乐伟
     * @param creditType
     * @return
     * @throws Exception
     */
    public boolean updateCreditTypeLimitTimestamp(CreditTypeLimitEntity creditType);

    /**
     * 查询全部产品分类(页面下拉列表中使用)
     * 
     * @author 李乐伟
     * @param map
     *            RoleId,UserId
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<ProductTypeEntity> findAllProductType(HashMap map);

    /**
     * 查找user负责的全部 customer
     * 
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<CustomerEntity> findAllCustomerOfUser(Map conditionMap, NewPage page);

    /**
     * 检查客户信用是否存在
     * 
     * @param customerCredit
     * @return
     */
    public boolean isCustomerCreditExist(CustomerCreditLimitDto customerCredit);

    /**
     * 查询所有已分配信用额度的产品分类
     * 
     * @author 李乐伟
     * @return
     */
    public List<ProductTypeEntity> findAllHasClimitProductType();

    /**
     * 查询所有产品类型下已分配信用额度的信用类型
     * 
     * @author 李乐伟
     * @return
     */
    public List<CreditTypeEntity> findAllHasClimtCreditTypeByPid(Integer productTypeId);

    /**
     * 更新客户信用表时间戳
     * 
     * @param customerCreditEntity
     * @return
     */
    public boolean modifyCustomerCreditTimeStamp(CustomerCreditEntity customerCreditEntity);

    /**
     * 新增客户信用明细
     * 
     * @param customerCreditDetail
     */
    public void addCustomerCreditDetail(CustomerCreditDetailEntity customerCreditDetail);

    /**
     * 更新客户信用明细
     * 
     * @param para
     * @return
     */
    public boolean modifyCustomerCreditDetail(CustomerCreditDetailEntity para);

    /**
     * 查找是否有相同项目名称
     * 
     * @param map
     *            客户ID,项目名称
     * @return True or False
     */
    @SuppressWarnings("unchecked")
    public boolean isExistProjName(Map map);
}
