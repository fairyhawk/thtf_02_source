/**
 * ClassName  CreditServiceImpl
 *
 * History
 * Create User: ChenHuajiang
 * Create Date: 2010-4-7
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.serviceimpl.credit;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.BaseService;
import cn.com.thtf.egov.cms.application.NewIDao;
import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.dto.CreditTypeLimitDto;
import cn.com.thtf.egov.cms.dto.CustomerCreditDto;
import cn.com.thtf.egov.cms.dto.CustomerCreditLimitDto;
import cn.com.thtf.egov.cms.dto.ProdCreditLimitDto;
import cn.com.thtf.egov.cms.entity.CreditTypeEntity;
import cn.com.thtf.egov.cms.entity.CreditTypeLimitEntity;
import cn.com.thtf.egov.cms.entity.CustomerCreditDetailEntity;
import cn.com.thtf.egov.cms.entity.CustomerCreditEntity;
import cn.com.thtf.egov.cms.entity.ProductTypeEntity;
import cn.com.thtf.egov.cms.iservice.credit.ICreditService;

/**
 * 信用管理模块Service实现
 * 
 * @author Chenhj
 */

public class CreditServiceImpl extends BaseService implements ICreditService {

    private static Logger log = LoggerFactory.getLogger(CreditServiceImpl.class);

    private NewIDao dao;

    public NewIDao getDao() {
        return dao;
    }

    public void setDao(NewIDao dao) {
        this.dao = dao;
    }

    /**
     * 根据id删除客户信用
     * 
     * creator: 李乐伟
     */
    @Override
    public boolean deleteCustomerCreditLimit(CustomerCreditLimitDto dto) {

        boolean isSuccess = false;

        CreditTypeLimitEntity creditType = new CreditTypeLimitEntity();
        creditType.setCreditTypeId(dto.getCreditTypeId());
        creditType.setProductTypeId(dto.getProductTypeId());
        creditType.setTimeStamp(new Timestamp(System.currentTimeMillis()));

        try {

            dao.beginTransaction();

            int intCnt = dao.delete("credit_sqlMap.deleteCustomerCreditLimit", dto);

            if (intCnt > 0) {
                isSuccess = updateCreditTypeLimitTimestamp(creditType);
            }

            dao.commitTransaction();

        } catch (Exception e) {

            isSuccess = false;
            log.error("删除客户信用失败", e);

        } finally {

            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.error("删除客户信用-->事务回滚失败", e);
                isSuccess = false;
            }
        }
        return isSuccess;
    }

    /**
     * 产品分类信用类型额度删除
     * 
     * creator:zhaolei
     */
    @Override
    public boolean deleteProdAssortCreditTypeLimit(CreditTypeLimitDto creditTypeLimit) {
        boolean isSuccess = false;
        try {
            int count = dao.delete("credit_sqlMap.deleteProdAssortCreditTypeLimit",
                    creditTypeLimit);
            if (count > 0) {
                isSuccess = true;
            }
        } catch (Exception e) {
            isSuccess = false;
            log.error("产品分类信用类型额度删除失败", e);
        }
        return isSuccess;
    }

    /**
     * 查看所有的信用类型
     */
    @SuppressWarnings("unchecked")
    @Override
    public List findAllCreditType(NewPage page) {
        List list = null;
        try {
            list = queryRecords(dao, "creditType.findAllCreditType", null, page);
        } catch (Exception e) {
            log.error("获取所有信用类型失败", e);
        }
        return list;

    }

    /**
     * 查看所有产品分类信用额度
     * 
     * creator:zhaolei
     */
    @SuppressWarnings("unchecked")
    @Override
    public List findAllProdAssortCreditLimit(NewPage page,
            CreditTypeLimitDto creditTypeLimitDto) {
        List list = null;
        try {
            if (creditTypeLimitDto == null) {
                list = queryRecords(
                        dao,
                        "prodAssortCreditLimitForCreditAdmin.findAllProdAssortCreditLimit",
                        null, page);
            } else {
                list = queryRecords(dao,
                        "prodAssortCreditLimit.findAllProdAssortCreditLimit",
                        creditTypeLimitDto, page);
            }
        } catch (Exception e) {
            log.error("获取所有产品分类信用额度失败", e);
        }
        return list;
    }

    /**
     * 根据ID查看产品分类信用总额 相关数据
     * 
     * creator:zhaolei
     */
    @Override
    public CreditTypeLimitDto findProdCreditLimitById(CreditTypeLimitDto creditTypeLimit) {
        CreditTypeLimitDto returnCtl = null;
        try {
            returnCtl = (CreditTypeLimitDto) dao.queryForObject(
                    "credit_sqlMap.getProdAssortCreditLimit", creditTypeLimit);
        } catch (Exception e) {
            log.error("根据ID查看产品分类信用总额 相关数据失败", e);
        }
        return returnCtl;
    }

    /**
     * 查看所有产品分类信用额度
     */
    @SuppressWarnings("unchecked")
    @Override
    public List findAllProdCreditLimit(NewPage page, ProdCreditLimitDto prodCreditLimitDto) {
        List list = null;
        try {
            if (prodCreditLimitDto == null) {
                // 信用主管
                list = queryRecords(dao, "creditTotalLimitCreMang.creditProductTypeAll",
                        null, page);
            } else {
                // 其它
                list = queryRecords(dao, "creditTotalLimit.creditProductTypeAll",
                        prodCreditLimitDto, page);
            }
        } catch (Exception e) {
            log.error("获取所有信用类型失败", e);
        }
        return list;
    }

    /**
     * 根据ID查看信用类型
     */
    @Override
    public CreditTypeEntity findCreditTypeById(String id) {
        CreditTypeEntity creditType = null;
        try {
            creditType = (CreditTypeEntity) dao.queryForObject(
                    "credit_sqlMap.findCreditTypeById", id);
        } catch (Exception e) {
            log.error("获取客户信用名称失败", e);
        }
        return creditType;

    }

    /**
     * 根据id查找客户信用
     * 
     * creator: 李乐伟
     */
    @Override
    public CustomerCreditLimitDto findCustomerCreditLimitById(String id) {

        CustomerCreditLimitDto customerCreditLimitDto = null;

        if (StringUtils.isNotBlank(id)) {

            Integer integerId = Integer.parseInt(id.trim());

            try {
                customerCreditLimitDto = (CustomerCreditLimitDto) dao.queryForObject(
                        "credit_sqlMap.queryCustomerCreditLimitById", integerId);
            } catch (Exception e) {
                log.error("查询客户信用失败", e);
            }
        }

        return customerCreditLimitDto;
    }

    /**
     * 根据ID查看产品分类信用额度
     */
    @Override
    public ProdCreditLimitDto findProdCreditLimitById(String prodId) {
        ProdCreditLimitDto prodCreditLimit = null;
        try {
            prodCreditLimit = (ProdCreditLimitDto) dao.queryForObject(
                    "credit_sqlMap.findProdCreditLimitById", prodId);
        } catch (Exception e) {
            log.error("获取产品信用相关信息失败", e);
        }
        return prodCreditLimit;
    }

    /**
     * 添加客户信用 creator:李乐伟
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean insertCustomerCreditLimit(List insertData) {

        boolean isSuccess = false;

        try {
            dao.beginTransaction();
            CustomerCreditLimitDto customerCredit = (CustomerCreditLimitDto) insertData
                    .get(0);
            dao.insert("credit_sqlMap.insertCustomerCreditLimit", customerCredit);
            isSuccess = updateCreditTypeLimitTimestamp((CreditTypeLimitEntity) insertData
                    .get(1));
            dao.commitTransaction();

        } catch (Exception e) {
            isSuccess = false;
            log.error("添加客户信用错误", e);
        } finally {

            try {
                dao.endTransaction();
            } catch (Exception e) {
                isSuccess = false;
                log.error("添加客户信用--> 事务回滚 失败", e);
            }
        }

        return isSuccess;
    }

    /**
     * 添加产品分类信用类型额度
     * 
     * creator:zhaolei
     */
    @Override
    public boolean insertProdAssortCreditTypeLimit(CreditTypeLimitDto creditTypeLimit) {
        boolean isSuccess = false;
        try {
            dao.insert("credit_sqlMap.insertProdAssortCreditLimit", creditTypeLimit);
            isSuccess = true;
        } catch (Exception e) {
            isSuccess = false;
            log.error("添加信用额度失败", e);
        }
        return isSuccess;
    }

    /*
     * 修改信用类型
     */
    @Override
    public boolean updateCreditType(CreditTypeEntity creditType) {
        boolean isSuccess = false;
        try {
            int intCnt = dao.update("credit_sqlMap.updateCreditType", creditType);
            if (intCnt > 0) {
                isSuccess = true;
            }
        } catch (Exception e) {
            log.error("更新信用类型失败", e);
            isSuccess = false;
        }
        return isSuccess;

    }

    /*
     * 更新客户信用
     * 
     * @author 李乐伟
     */

    @SuppressWarnings("unchecked")
    @Override
    public boolean updateCustomerCreditLimit(List updateData) {

        boolean isSuccess = false;

        try {
            dao.beginTransaction();
            int updateCount = dao.update("credit_sqlMap.updateCustomerCreditLimit",
                    (CustomerCreditLimitDto) updateData.get(0));

            if (updateCount > 0) {
                isSuccess = updateCreditTypeLimitTimestamp((CreditTypeLimitEntity) updateData
                        .get(1));
            }
            // 更新总的可分配额度时间戳

            dao.commitTransaction();

        } catch (Exception e) {

            log.error("修改客户信用失败", e);
            return false;

        } finally {

            try {
                dao.endTransaction();
            } catch (Exception e) {

                isSuccess = false;
                log.error("修改客户信用--> 事务回滚 失败", e);
            }

        }

        return isSuccess;

    }

    /**
     * 修改产品分类信用总额
     * 
     * creator: zhaolei
     */
    @Override
    public boolean updateCreditTypeLimit(CreditTypeLimitDto creditTypeLimit) {
        boolean isSuccess = false;
        try {
            int count = dao.update("credit_sqlMap.updateProdAssortCreditLimit",
                    creditTypeLimit);
            if (count > 0) {
                isSuccess = true;
            }
        } catch (Exception e) {
            isSuccess = false;
            log.error("修改产品分类信用总额失败", e);
        }
        return isSuccess;
    }

    /**
     * 查找数据库中是否存在相同的记录
     * 
     * creator:zhaolei
     */
    @Override
    public boolean findCreditTypeByCondition(CreditTypeLimitDto creditTypeLimit) {
        boolean isExist = false;
        try {
            int count = dao.getCount("credit_sqlMap.findCreditTypeByCondition",
                    creditTypeLimit);
            if (count > 0) {
                isExist = true;
            }
        } catch (Exception e) {
            isExist = false;
            log.error("查找相同产品分类、相同信用类型失败", e);
        }
        return isExist;
    }

    /**
     * 取得添加页面的可分配额度 根据产品大类Id查询给产品信用额度分配情况
     * 
     * creator:zhaolei
     */
    @Override
    public String findProdAssortCreditLimitById(Integer prodTypeId) {
        String assignLimit = null;
        try {
            assignLimit = (String) dao.queryForObject("credit_sqlMap.getCanAssingClimit",
                    prodTypeId);
        } catch (Exception e) {
            log.error("取得添加页面的可分配额度失败", e);
        }

        return assignLimit;
    }

    /**
     * 查询所有的信用类型
     * 
     * creator:zhaolei
     */
    @SuppressWarnings("unchecked")
    @Override
    public List findAllCreditType() {
        List list = null;
        try {
            list = dao.queryForlist("credit_sqlMap.findAllCreditType", null);
        } catch (Exception e) {
            log.error("查询所有的信用类型失败", e);
        }
        return list;
    }

    /**
     * 根据条件检索客户信用 creator:李乐伟
     */
    @SuppressWarnings("unchecked")
    @Override
    public List findAllCustomerCreditByCondition(CustomerCreditLimitDto customerCredit,
            NewPage page) {

        List customerCreditList = null;

        try {
            customerCreditList = queryRecords(dao,
                    "customerCredit.queryCustomerCreditLimitByCondition", customerCredit,
                    page);
        } catch (Exception e) {
            log.error("根据条件查询客户信用失败", e);
        }

        return customerCreditList;
    }

    /**
     * 根据客户信用id 查询已用额度 creator:李乐伟
     * 
     */
    @Override
    public Double getUsedCreditById(String id) {

        Integer intId = Integer.parseInt(id);
        Double usedCredit = null;

        try {
            usedCredit = (Double) dao.queryForObject("credit_sqlMap.getUsedCreditById",
                    intId);
        } catch (Exception e) {
            log.error("根据客户信用id 查询已用额度 失败", e);
        }
        return usedCredit;
    }

    /**
     * 查询可分配额度 根据productTypeId 和 creditTypeId
     * 
     * creator: 李乐伟
     */
    @Override
    public CustomerCreditLimitDto getTotalClimtByProIdCrdtId(
            CustomerCreditLimitDto dtoCredit) {

        CustomerCreditLimitDto dto = null;

        try {

            dto = (CustomerCreditLimitDto) dao.queryForObject(
                    "credit_sqlMap.queryTotalClimt", dtoCredit);

        } catch (Exception e) {
            log.error("查询可分配额度 失败", e);
        }
        return dto;
    }

    /**
     * 更新可分配额度 creator: 李乐伟
     */
    @Override
    public boolean updateCreditTypeLimitTimestamp(CreditTypeLimitEntity creditType) {

        boolean isSuccess = false;

        try {

            int count = dao
                    .update("credit_sqlMap.updateTotalDistributeClimt", creditType);

            if (count > 0) {
                isSuccess = true;
            }

        } catch (Exception e) {
            log.error("更新可分配额度失败", e);
            isSuccess = false;
        }

        return isSuccess;
    }

    /**
     * 查询当前用户负责的全部客户
     * 
     * cretor:李乐伟
     */
    @SuppressWarnings("unchecked")
    @Override
    public List findAllCustomerOfUser(Map conditionMap, NewPage page) {

        List allCustomer = null;

        try {
            allCustomer = queryRecords(dao, "customerNew.findAllCustomerOfUser",
                    conditionMap, page);
        } catch (Exception e) {
            log.error("查询当前用户负责的全部客户", e);
        }

        return allCustomer;

    }

    /**
     * 判断客户信用是否存在
     * 
     * cretor:李乐伟
     */
    @Override
    public boolean isCustomerCreditExist(CustomerCreditLimitDto customerCredit) {
        Integer count = 0;
        boolean isExist = false;
        try {
            count = (Integer) dao.queryForObject("credit_sqlMap.isCustomerCreditExist",
                    customerCredit);
            if (count > 0) {
                isExist = true;
            }
        } catch (Exception e) {
            log.error("判断客户信用是否存在失败", e);
            isExist = true;

        }
        return isExist;
    }

    public boolean updateProdCreditLimit(ProductTypeEntity productType) {
        boolean isSuccess = false;
        try {
            int intCnt = dao.update("credit_sqlMap.updateProdCreditLimit", productType);
            if (intCnt > 0) {
                isSuccess = true;
            }
        } catch (Exception e) {
            log.error("更新产品分类信用额度失败", e);
            isSuccess = false;
        }
        return isSuccess;
    }

    /**
     * 查询所有的产品分类类型
     * 
     * creator:zhaolei
     */
    @SuppressWarnings("unchecked")
    @Override
    public List findAllProductType(HashMap map) {
        List list = null;
        try {
            list = dao.queryForlist("credit_sqlMap.findAllProductType", map);
        } catch (Exception e) {
            log.error("查询所有的产品分类类型失败", e);
        }
        return list;
    }

    /** 根据修改条件查找信用类型，判断信用类型是否已存在 */
    public boolean findNameByCondition(String cond) {
        boolean isSuccess = false;
        try {
            int intCnt = dao.getCount("credit_sqlMap.findNameByCondition", cond);
            if (intCnt > 0) {
                isSuccess = true;
            }
        } catch (Exception e) {
            log.error("查找信用类型是否存在失败", e);
            isSuccess = false;
        }
        return isSuccess;
    }

    /**
     * 取得客户信用类型已分配额度
     * 
     * creator:zhaolei
     */
    @Override
    public String findCustomerAssignLimit(CustomerCreditEntity customerCredit) {
        String costomerAssignLimit = null;
        try {
            costomerAssignLimit = (String) dao.queryForObject(
                    "credit_sqlMap.findCostomerAssignLimit", customerCredit);
        } catch (Exception e) {
            log.error("查找客户信用类型已分配额度失败", e);
        }
        return costomerAssignLimit;
    }

    /**
     * 查询所有已分配信用额度的产品分类 creator:李乐伟
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<ProductTypeEntity> findAllHasClimitProductType() {

        List<ProductTypeEntity> productTypeList = null;

        try {
            productTypeList = dao.queryForlist(
                    "credit_sqlMap.findAllHasClimitProductType", null);
        } catch (Exception e) {
            productTypeList = null;
            log.error("查询所有已分配信用额度的产品分类失败", e);
        }
        return productTypeList;
    }

    /**
     * 查询所有产品类型下已分配信用额度的信用类型 creator: 李乐伟
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<CreditTypeEntity> findAllHasClimtCreditTypeByPid(Integer productTypeId) {

        List<CreditTypeEntity> allCreditType = null;

        try {
            allCreditType = dao.queryForlist(
                    "credit_sqlMap.findAllHasClimtCreditTypeByPid", productTypeId);
        } catch (Exception e) {
            allCreditType = null;
            log.error("查询所有产品类型下已分配信用额度的信用类型失败", e);
        }

        return allCreditType;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.credit.ICreditService#addCustomerCreditDetail
     * (cn.com.thtf.egov.cms.entity.CustomerCreditDetailEntity)
     */
    @Override
    public void addCustomerCreditDetail(CustomerCreditDetailEntity customerCreditDetail) {
        try {
            dao.insert("credit_sqlMap.insertCustomerCreditDetail", customerCreditDetail);
        } catch (Exception e) {
            log.error("新增客户信用明细错误:", e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @seecn.com.thtf.egov.cms.iservice.credit.ICreditService#
     * modifyCustomerCreditTimeStamp
     * (cn.com.thtf.egov.cms.entity.CustomerCreditEntity)
     */
    @Override
    public boolean modifyCustomerCreditTimeStamp(CustomerCreditEntity customerCreditEntity) {
        int result = 0;
        try {
            result = dao.update("credit_sqlMap.updateCustomerCreditTimeStamp",
                    customerCreditEntity);
        } catch (Exception e) {
            log.error("更新客户信用表 时间戳:", e);
        }

        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @seecn.com.thtf.egov.cms.iservice.credit.ICreditService#
     * modifyCustomerCreditDetail
     * (cn.com.thtf.egov.cms.entity.CustomerCreditDetailEntity)
     */
    @Override
    public boolean modifyCustomerCreditDetail(CustomerCreditDetailEntity para) {
        try {
            CustomerCreditDto result = (CustomerCreditDto) dao.queryForObject(
                    "credit_sqlMap.getCreditAndDetailByCondition", para);

            if (result.getDetileId() == null) {
                // insert 信用
                addCustomerCreditDetail(para);
                return true;
            } else {
                para.setId(result.getDetileId());
                // 修改信用
                int updateResult = dao.update("credit_sqlMap.updateCreditDetileMoney",
                        para);
                if (updateResult != 1) {
                    log.error("更新使用额度错误");
                    return false;
                } else {
                    return true;
                }
            }
        } catch (Exception e) {
            log.error("更新客户信用明细错误:", e);
            return false;
        }
    }

    /* 查找是否有相同项目名称 */
    @SuppressWarnings("unchecked")
    @Override
    public boolean isExistProjName(Map map) {
        log.info("查找是否有相同项目名称");
        Integer intCnt = 0;
        try {
            intCnt = dao.getCount("credit.selectProjectNameByCondtion", map);
        } catch (Exception e) {
            log.error("查找是否有相同项目名称失败" + e);
        }
        if (intCnt > 0) {
            return true;
        } else {
            return false;
        }
    }
}
