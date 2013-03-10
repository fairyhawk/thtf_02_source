/**
 * ClassName  SalesContractManagementServiceImp
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-4-12
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.serviceimpl.sell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.BaseService;
import cn.com.thtf.egov.cms.application.NewIDao;
import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.constant.Constants;
import cn.com.thtf.egov.cms.dto.AssessManDto;
import cn.com.thtf.egov.cms.dto.CompanyInfoDto;
import cn.com.thtf.egov.cms.dto.SalesConCustomerDto;
import cn.com.thtf.egov.cms.dto.SalesConProductDto;
import cn.com.thtf.egov.cms.dto.SalesContractInfoDto;
import cn.com.thtf.egov.cms.dto.SalesContractProductDetailDto;
import cn.com.thtf.egov.cms.dto.SalesContractQueryDto;
import cn.com.thtf.egov.cms.dto.SalesContractResultDto;
import cn.com.thtf.egov.cms.dto.SalesJudgeRoleDto;
import cn.com.thtf.egov.cms.entity.CustomerAddressEntity;
import cn.com.thtf.egov.cms.entity.CustomerLinkmanEntity;
import cn.com.thtf.egov.cms.entity.DemandEntity;
import cn.com.thtf.egov.cms.entity.SellContractDetailEntity;
import cn.com.thtf.egov.cms.entity.UserAreaMappingEntity;
import cn.com.thtf.egov.cms.entity.UserAreaProductEntity;
import cn.com.thtf.egov.cms.entity.UserProductEntity;
import cn.com.thtf.egov.cms.entity.WorkEntity;
import cn.com.thtf.egov.cms.form.ProductSearchForm;
import cn.com.thtf.egov.cms.iservice.common.ICommonService;
import cn.com.thtf.egov.cms.iservice.sell.ISalesContractManagementService;
import cn.com.thtf.egov.cms.iservice.work.ITodoWorkService;
import cn.com.thtf.egov.cms.serviceimpl.work.TodoWorkService;
import cn.com.thtf.egov.cms.util.Container;

/**
 * SalesContractManagementServiceImp
 * 
 * @author Lubo
 */

public class SalesContractManagementServiceImp extends BaseService implements
        ISalesContractManagementService {
    /** log */
    private static Logger log = LoggerFactory
            .getLogger(SalesContractManagementServiceImp.class);
    /** NewIDao */
    private NewIDao dao;

    /**
     * 检索公司合同号
     * 
     * @param code
     * @return
     */
    public String getCompanycontractcode(String code) {
        try {
            return (String) dao.queryForObject("salesContract_sqlMap.getCompanycontractcode", code);
        } catch (Exception e) {
            log.error("检索公司合同号错误!", e);
            return null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.com.thtf.egov.cms.iservice.sell.ISalesContractManagementService#
     * getSalesContractListDetail
     * (cn.com.thtf.egov.cms.dto.SalesContractResultDto)
     */
    @Override
    public void getSalesContractListDetail(SalesContractResultDto param) {
        try {
            // String result1 = (String) dao.queryForObject(
            // "salesContract_sqlMap.salesContractsDetailZ",
            // param.getSellContractId());
            // String result2 = (String) dao.queryForObject(
            // "salesContract_sqlMap.salesContractsDetailZ",
            // param.getSellContractId());
            // String result3 = (String) dao.queryForObject(
            // "salesContract_sqlMap.salesContractsDetailZ",
            // param.getSellContractId());
            // String result4 = (String) dao.queryForObject(
            // "salesContract_sqlMap.salesContractsDetailZ",
            // param.getSellContractId());
            // String result5 = (String) dao.queryForObject(
            // "salesContract_sqlMap.salesContractsDetailZ",
            // param.getSellContractId());
            // String result6 = (String) dao.queryForObject(
            // "salesContract_sqlMap.salesContractsDetailZ",
            // param.getSellContractId());
            // String result7 = (String) dao.queryForObject(
            // "salesContract_sqlMap.salesContractsDetailZ",
            // param.getSellContractId());
            // String result8 = (String) dao.queryForObject(
            // "salesContract_sqlMap.salesContractsDetailZ",
            // param.getSellContractId());
            //
            // param.setDeliveryAmountDeliveryAmountMoney(result1);
            // param.setReserveAmountReserveAmountMoney(result2);
            // param.setSpecifyAmountSpecifyAmountMoney(result3);
            // param.setAdvanceAmountAdvanceAmountMoney(result4);
            // param.setFreezeAmounFreezeAmountMoney(result5);
            // param.setMakeAmountMakeAmountMoney(result6);
            // param.setBackAmountBackAmountMoney(result7);
            // param.setSellbackAmountSellbackAmountMoney(result8);
            SalesContractResultDto result = (SalesContractResultDto) dao.queryForObject(
                    "salesContract_sqlMap.salesContractsDetailZ",
                    param.getSellContractId());
            param.setDeliveryAmountDeliveryAmountMoney(result
                    .getDeliveryAmountDeliveryAmountMoney());
            param.setReserveAmountReserveAmountMoney(result
                    .getReserveAmountReserveAmountMoney());
            param.setSpecifyAmountSpecifyAmountMoney(result
                    .getSpecifyAmountSpecifyAmountMoney());
            param.setAdvanceAmountAdvanceAmountMoney(result
                    .getAdvanceAmountAdvanceAmountMoney());
            param.setFreezeAmounFreezeAmountMoney(result
                    .getFreezeAmounFreezeAmountMoney());
            param.setMakeAmountMakeAmountMoney(result.getMakeAmountMakeAmountMoney());
            param.setBackAmountBackAmountMoney(result.getBackAmountBackAmountMoney());
            param.setSellbackAmountSellbackAmountMoney(result
                    .getSellbackAmountSellbackAmountMoney());

        } catch (Exception e) {
            log.error("检索销售合同详细数据错误!", e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SalesContractResultDto> getSalesContractList(
            SalesContractQueryDto salesDto, NewPage page) {
        /* result */
        List result = null;

        /* 权限ID */
        Integer roleId = salesDto.getUser().getRoleId();

        try {
            switch (roleId) {
            case Constants.ROLE_SALES_ASSISTANT: {
                /* 销售助理 */
                List<UserAreaProductEntity> userAreaProductList = (List<UserAreaProductEntity>) dao
                        .getSqlMap().queryForList(
                                "salesContract_sqlMap.getUserAreaProduct",
                                salesDto.getUser());

                if (userAreaProductList.size() > 0) {
                    /* 封装查询参数 */
                    // setUserAreaProduct(salesDto, userAreaProductList);
                    salesDto.setUserAreaProductList(userAreaProductList);

                    result = queryRecords(dao, "salesContractsList_xszl.search",
                            salesDto, page);
                } else {
                    result = new ArrayList();
                }
                break;
            }
            case Constants.ROLE_SALES_MANAGER: {
                /* 销售经理 */

                result = queryRecords(dao, "salesContractsList_xsjl.search", salesDto,
                        page);

                break;
            }
            case Constants.ROLE_SALES_DIRECTOR: {
                /* 销售总监 */
                List<UserProductEntity> userProductList = (List<UserProductEntity>) dao
                        .getSqlMap().queryForList("salesContract_sqlMap.getUserProduct",
                                salesDto.getUser());

                if (userProductList.size() > 0) {
                    /* 封装查询参数 */
                    setUserProduct(salesDto, userProductList);

                    result = queryRecords(dao, "salesContractsList_xszj.search",
                            salesDto, page);
                } else {
                    result = new ArrayList();
                }
                break;
            }
            case Constants.ROLE_REGIONAL_DIRECTOR: {
                /* 区域总监 */
                List<UserProductEntity> userProductList = (List<UserProductEntity>) dao
                        .getSqlMap().queryForList("salesContract_sqlMap.getUserProduct",
                                salesDto.getUser());

                if (userProductList.size() > 0) {
                    /* 封装查询参数 */
                    setUserProduct(salesDto, userProductList);

                    result = queryRecords(dao, "salesContractsList_qyzj.search",
                            salesDto, page);
                } else {
                    result = new ArrayList();
                }
                break;
            }
            case Constants.ROLE_PRODUCT_DIRECTOR: {
                /* 产品总监 */

                List<UserProductEntity> userProductList = (List<UserProductEntity>) dao
                        .getSqlMap().queryForList("salesContract_sqlMap.getUserProduct",
                                salesDto.getUser());

                if (userProductList.size() > 0) {
                    /* 封装查询参数 */
                    setUserProduct(salesDto, userProductList);

                    result = queryRecords(dao, "salesContractsList_cpzj.search",
                            salesDto, page);
                } else {
                    result = new ArrayList();
                }
                break;
            }
            case Constants.ROLE_COMPLIANCE_OFFICER: {
                /* 法务专员 */

                result = queryRecords(dao, "salesContractsList_fwzy.search", salesDto,
                        page);

                break;
            }
            case Constants.ROLE_AST_DIRECTOR_OF_OPERATIONS: {
                /* 运营总监助理 */

                result = queryRecords(dao, "salesContractsList_yyzjzl.search", salesDto,
                        page);

                break;
            }
            case Constants.ROLE_DIRECTOR_OF_OPERATIONS: {
                /* 运营总监 */

                result = queryRecords(dao, "salesContractsList_yyzj.search", salesDto,
                        page);

                break;
            }
            case Constants.ROLE_CREDIT_COMMISSIONER:
                /* 信用专员 */
            case Constants.ROLE_PROCUREMENT_OFFICER: {
                /* 采购主管 */

                List<UserProductEntity> userProductList = (List<UserProductEntity>) dao
                        .getSqlMap().queryForList("salesContract_sqlMap.getUserProduct",
                                salesDto.getUser());

                if (userProductList.size() > 0) {
                    /* 封装查询参数 */
                    setUserProduct(salesDto, userProductList);

                    result = queryRecords(dao, "salesContractsList_xyzy_cgzg.search",
                            salesDto, page);
                } else {
                    result = new ArrayList();
                }
                break;
            }
            case Constants.ROLE_CREDIT_CHARGE:
                /* 信用主管 */
            case Constants.ROLE_GENERAL_MANAGER: {
                /* 总经理 */

                result = queryRecords(dao, "salesContractsList_xyzg_zjl.search",
                        salesDto, page);

                break;
            }
            case Constants.ROLE_AREA_MANAGER:
                /* 区域经理 */
            case Constants.ROLE_BIGAREA_MANAGER: {
                /* 大区经理 */
                List<UserAreaMappingEntity> aresList = dao.queryForlist(
                        "salesContract_sqlMap.getUserArea", salesDto.getUser());

                if (aresList.size() > 0) {
                    /* 封装查询参数 */
                    setUserArea(salesDto, aresList);

                    result = queryRecords(dao, "salesContractsList_qy.search", salesDto,
                            page);
                } else {
                    result = new ArrayList();
                }
                break;
            }
            default: {
                log.error("不存在的权限");
                break;
            }
            }

        } catch (Exception e) {
            log.error("检索数据发生异常:", e);
        }

        return result;
    }

    // /**
    // * 封装 查询参数 区域产品
    // *
    // * @param salesDto
    // * @param userAreaProductList
    // */
    // private void setUserAreaProduct(SalesContractQueryDto salesDto,
    // List<UserAreaProductEntity> userAreaProductList) {
    // StringBuffer area = new StringBuffer();
    // StringBuffer product = new StringBuffer();
    //
    // for (UserAreaProductEntity userAreaProduct : userAreaProductList) {
    // area.append(userAreaProduct.getUserAreaId()).append(",");
    // product.append(userAreaProduct.getProductTypeId()).append(",");
    // }
    //
    // if (area.length() > 0) {
    // salesDto.setUserAreaIdArr(area.substring(0, area.length() - 1));
    // }
    // if (product.length() > 0) {
    // salesDto.setProductTypeIdArr(product.substring(0, product.length() - 1));
    // }
    // }

    /**
     * 封装 查询参数 区域
     * 
     * @param salesDto
     * @param list
     */
    private void setUserArea(SalesContractQueryDto salesDto,
            List<UserAreaMappingEntity> list) {
        StringBuffer product = new StringBuffer();

        for (UserAreaMappingEntity userArea : list) {
            product.append(userArea.getUserAreaId()).append(",");
        }

        if (product.length() > 0) {
            salesDto.setUserAreaIdArr(product.substring(0, product.length() - 1));
        }
    }

    /**
     * 封装 查询参数 区域产品
     * 
     * @param salesDto
     * @param userAreaProductList
     */
    private void setUserProduct(SalesContractQueryDto salesDto,
            List<UserProductEntity> userProductList) {
        StringBuffer product = new StringBuffer();

        for (UserProductEntity userProduct : userProductList) {
            product.append(userProduct.getProductTypeId()).append(",");
        }

        if (product.length() > 0) {
            salesDto.setProductTypeIdArr(product.substring(0, product.length() - 1));
        }
    }

    /**
     * 新建销售合同 根据用户查看负责的客户信息
     * 
     * @author shensi
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<SalesConCustomerDto> getCustomerByUserId(String userId) {
        List<SalesConCustomerDto> list = null;
        try {
            list = dao
                    .queryForlist("salesContract_sqlMap.selectCustomerByUserId", userId);
        } catch (Exception e) {
            log.error("根据用户查看负责的客户信息失败", e);
        }
        return list;
    }

    /*
     * 新建销售合同 根据客户Id查看联系人
     * 
     * @author shensi
     */
    @SuppressWarnings("unchecked")
    @Override
    public List getLinkManByCustomerId(String customerId) {
        List<SalesConCustomerDto> list = null;
        try {
            list = dao.queryForlist("salesContract_sqlMap.selectCusLinkManByCustomerId",
                    customerId);
        } catch (Exception e) {
            log.error("根据客户查看客户联系人信息失败", e);
        }
        return list;
    }

    /*
     * 新建销售合同 根据联系人Id查看联系人相关信息
     * 
     * @author shensi
     */
    @Override
    public CustomerLinkmanEntity getLinkMsgByLinkManId(String linkManId) {
        CustomerLinkmanEntity customerLinkman = new CustomerLinkmanEntity();
        try {
            customerLinkman = (CustomerLinkmanEntity) dao.queryForObject(
                    "salesContract_sqlMap.selectLinkMsgByLinkManId", linkManId);
        } catch (Exception e) {
            log.error("根据联系人Id查看想相关信息失败", e);
        }
        return customerLinkman;
    }

    /*
     * 新建销售合同 根据产品分类查看系列
     * 
     * @author shensi
     */
    @SuppressWarnings("unchecked")
    @Override
    public List getProSerieByProTypeId(String proTypeId) {
        List list = null;
        try {
            list = dao.queryForlist("salesContract_sqlMap.selectProSerieByProTypeId",
                    proTypeId);
        } catch (Exception e) {
            log.error("根据产品分类查看系列信息失败", e);
        }
        return list;
    }

    /*
     * 新建销售合同 根据用户Id查看负责的产品分类
     * 
     * @author shensi
     */
    @SuppressWarnings("unchecked")
    @Override
    public List getProTypeByUserId(String userId) {
        List list = null;
        try {
            list = dao.queryForlist("salesContract_sqlMap.selectProTypeByUserId", userId);
        } catch (Exception e) {
            log.error("根据用户Id查看负责的产品分类信息失败", e);
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @seecn.com.thtf.egov.cms.iservice.sell.ISalesContractManagementService#
     * getUserMagByUserId(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List getUserMagByUserId(String userId) {
        return null;
    }

    /**
     * 新建销售合同
     * 
     * @return
     * @author shensi
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean addSalesContarct(SalesContractInfoDto contract,

    List salesConDetialList, List demandList, WorkEntity work, String sign) {

        boolean isSuccess = false;

        try {

            dao.beginTransaction();

            // 新建销售合同表
            try {
                dao.insert("salesContract_sqlMap.insertSalesContract", contract);
            } catch (Exception e) {
                log.debug("插入销售合同表失败", e);
                isSuccess = false;
                return isSuccess;
            }

            List list = new ArrayList();
            // 新建合同明细表
            if (salesConDetialList != null && salesConDetialList.size() > 0) {
                for (Object obj : salesConDetialList) {
                    SellContractDetailEntity salesDetial = (SellContractDetailEntity) obj;
                    list.add(salesDetial.getProductId());
                    isSuccess = addSalesContarctDetial(salesDetial);

                    if (isSuccess == false) {
                        dao.endTransaction();
                        return isSuccess;
                    }
                }
            }

            // 锁表操作
            Map map = new HashMap();
            map.put("prodId", list);
            ICommonService service = null;
            service = (ICommonService) Container.getBean("commonServiceImpl");
            isSuccess = service.lockProductRecord(map);
            if (!isSuccess) {// 锁表失败
                dao.endTransaction();
                return false;
            }

            // 插入需求单
            if (contract.isLackProduct() && !sign.equals("1")) {// 无货并且不是保存
                if (demandList != null && demandList.size() > 0) {
                    for (Object obj : demandList) {
                        DemandEntity demandinfo = (DemandEntity) obj;
                        isSuccess = this.addDemand(demandinfo);

                        if (isSuccess == false) {
                            dao.endTransaction();
                            return isSuccess;
                        }
                    }
                }
            }

            // 代办事物
            if (!sign.equals("1")) {
                work.setCount(1);
                ITodoWorkService todoWorkService = (ITodoWorkService) Container
                        .getApplicationContext().getBean("todoWrokServiceImpl");

                isSuccess = todoWorkService.addTodoWorks(work);

                if (!isSuccess) {// 插入事务失败
                    dao.endTransaction();
                    return false;
                }
            }

            dao.commitTransaction();
            isSuccess = true;

        } catch (Exception e) {
            log.debug("新建销售合同失败", e);
            isSuccess = false;

        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.debug("新建销售合同失败", e);
                isSuccess = false;
            }
        }
        return isSuccess;
    }

    /**
     * 新建销售合同 根据用户Id查看所属区域
     * 
     * @auther shensi
     */
    @Override
    public String getAreaNameByUserId(String userId) {
        String userName = null;
        try {
            userName = (String) dao.queryForObject(
                    "salesContract_sqlMap.selectUserNameByUserId", userId);
        } catch (Exception e) {
            log.error("根据客户查看客户联系人信息失败", e);
        }
        return userName;
    }

    /**
     * 
     * 选择支付方式
     * 
     * @auther shensi
     */
    @SuppressWarnings("unchecked")
    @Override
    public List getPaymentWay(Map map, NewPage page) {
        List list = null;

        try {
            list = queryRecords(dao, "common.selectCustomerCreditById", map, page);
        } catch (Exception e) {
            log.error("选择支付方式失败", e);
        }
        return list;
    }

    /**
     * 产品选择小页面
     * 
     * @auther shensi
     */
    @SuppressWarnings("unchecked")
    @Override
    public List getProduct(SalesConProductDto salesConProductDto, NewPage page) {
        List productList = null;

        try {

            if (StringUtils.isNotEmpty(salesConProductDto.getProductName())) {
                salesConProductDto.setProductName(salesConProductDto.getProductName()
                        .replaceAll("'", "''"));
            }

            productList = queryRecords(dao, "salesProduct.selectProduct",
                    salesConProductDto, page);
        } catch (Exception e) {
            log.error("选择产品失败", e);
        }
        return productList;
    }

    /**
     * 新建销售合同 发货地址选择
     * 
     * @auther shensi
     */
    @SuppressWarnings("unchecked")
    @Override
    public List getSendGoodsAddress(NewPage page, String customerId) {
        List<CustomerAddressEntity> list = null;
        try {
            list = (List) queryRecords(dao, "sendAddress.selectSendGoodsAddress",
                    customerId, page);
        } catch (Exception e) {
            log.error("查看客户发货地址失败", e);
        }
        return list;
    }

    /**
     * 根据销售合同流水号查询产品信息 销售合同查看 creator:张子旭
     */
    @Override
    @SuppressWarnings("unchecked")
    public List selectSalesContractProductDetailList(String contractId) {
        List list = null;
        try {
            list = dao.queryForlist(
                    "salesContract_sqlMap.selectSalesContractProductDetailList",
                    contractId);

        } catch (Exception e) {
            log.error("查看产品信息失败", e);
        }

        return list;

    }

    /**
     * 根据id 查找销售合同
     * 
     * creator: 李乐伟
     * 
     */
    @Override
    public SalesContractInfoDto getSalesContractById(String contractId) {

        SalesContractInfoDto contract = null;

        try {
            contract = (SalesContractInfoDto) dao.queryForObject(
                    "salesContract_sqlMap.selectSalesContractById", contractId);
        } catch (Exception e) {
            log.error("根据id 查找销售合同错误 !", e);
        }

        return contract;
    }

    /**
     * 更新销售合同
     * 
     * creator: 李乐伟
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean modifySalesContract(List contractData) {

        boolean isSuccess = true;
        // 销售合同
        SalesContractInfoDto contract = (SalesContractInfoDto) contractData.get(0);
        // 合同明细
        List<SalesContractProductDetailDto> contractDetailList = (List<SalesContractProductDetailDto>) contractData
                .get(1);
        // 下一个评审人
        AssessManDto nextAssessMan = (AssessManDto) contractData.get(2);
        // 需求单
        List<DemandEntity> demandList = (List<DemandEntity>) contractData.get(3);

        try {
            dao.beginTransaction();

            ICommonService service = null;
            service = (ICommonService) Container.getBean("commonServiceImpl");
            String status = service.getStatusById("sell_contract", contract.getId());
            if ("-99".equals(status)) {// 记录不存在(已删除)
                log.error("该记录已被其他用户删除，请重新选择");
                return false;
            }
            // 可修改的状态集
            String arrStatus[] = { "1", "3", "5", "7", "9", "11", "13", "15", "17" };
            boolean isOK = false;
            for (int i = 0; i < arrStatus.length; i++) {
                if (status.equals(arrStatus[i])) {
                    isOK = true;
                    break;
                }
            }

            if (!isOK) {
                log.error("该记录已被其他用户修改，请重新选择");
                return false;
            }

            // 更新销售合同
            int updateCount = dao.update("salesContract_sqlMap.updateSalesContract",
                    contract);
            //
            if (updateCount <= 0) {
                dao.endTransaction();
                log.debug(" 销售合同修改 更新销售合同错误[更新数: 0]");
                return false;
            }

            if (nextAssessMan != null) {
                // 下一个评审人待办事务
                if (isNextAssessManWorkExist(nextAssessMan)) {
                    isSuccess = updateNextAssessManWork(nextAssessMan);
                } else {
                    isSuccess = insertNextAssessManWork(nextAssessMan);
                }
            }

            if (isSuccess == false) {
                dao.endTransaction();
                log.debug(" 销售合同修改 更新下一个评审人待办事务错误");
                return isSuccess;
            }
            // 删除合同原先的全部明细
            removeAllContractDetailByContractId(contract.getId());
            // 加入新的明细
            isSuccess = addSalesContractDetil(contractDetailList);

            if (isSuccess == false) {
                dao.endTransaction();
                log.debug(" 销售合同修改  更新合同明细错误");
                return isSuccess;
            }

            // 删除合同原来的全部需求单
            isSuccess = removeAllDemandOfContract(contract.getId());

            if (isSuccess == false) {
                dao.endTransaction();
                log.debug(" 销售合同修改  删除合同原来的全部需求单错误");

                return isSuccess;
            }

            // 需求单添加
            if (demandList != null) {
                for (DemandEntity demand : demandList) {

                    isSuccess = addDemand(demand);

                    if (isSuccess == false) {
                        dao.endTransaction();
                        log.debug(" 销售合同修改  加入新的需求单时错误");
                        return isSuccess;
                    }// if

                }// for
            }// if

            dao.commitTransaction();
        } catch (Exception e) {
            isSuccess = false;
            log.error("销售合同更新错误", e);
        } finally {

            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.error("销售合同更新事务回滚错误", e);
            }
        }

        return isSuccess;
    }

    /**
     * 根据销售合同id查询对应的合同明细
     * 
     * creator: 李乐伟
     */
    @Override
    @SuppressWarnings("unchecked")
    public List getSalesContractDetailListByContractId(String contractId) {

        List detailList = null;

        try {

            detailList = dao.queryForlist(
                    "salesContract_sqlMap.selectSalesContractDetailListByContractId",
                    contractId);

        } catch (Exception e) {
            log.error("根据销售合同id查询对应的合同明细错误", e);
        }

        return detailList;
    }

    /**
     * 根据流水号查询合同 creator: 张子旭
     */
    @Override
    public SalesContractInfoDto getSalesContractInfoById(String salesContractId) {
        SalesContractInfoDto salesContractInfoDto = null;
        try {
            salesContractInfoDto = (SalesContractInfoDto) dao.queryForObject(
                    "salesContract_sqlMap.selectSalesContractById", salesContractId);
        } catch (Exception e) {
            log.error("查询合同详细信息失败", e);
        }
        return salesContractInfoDto;
    }

    /**
     * 
     * 销售合同明细
     * 
     * @param sellContractDetail
     * @return
     * @author shensi
     */
    @Override
    public boolean addSalesContarctDetial(SellContractDetailEntity sellContractDetail) {
        boolean isSuccess = false;
        try {
            dao.insert("salesContract_sqlMap.insertSalesConDetail", sellContractDetail);
            isSuccess = true;
        } catch (Exception e) {
            log.error("添加销售明细失败", e);
            isSuccess = false;
        }

        return isSuccess;
    }

    /**
     * 插入需求单表
     * 
     * @param demandEntity
     * @return
     * @author shensi
     */
    @Override
    public boolean addDemand(DemandEntity demandEntity) {
        boolean isSuccess = false;
        try {
            dao.insert("salesContract_sqlMap.insertDemand", demandEntity);
            isSuccess = true;
        } catch (Exception e) {
            log.error("插入需求单表失败", e);
            isSuccess = false;
        }
        return isSuccess;
    }

    /**
     * 判断 查询评审人是否存在
     * 
     * @param proTypeId
     *            产品分类ID
     * @param flg
     *            评审标志： flg=true 新建、修改；flg=false 评审
     * @param roleId
     *            下一评审人角色ID
     * @return
     * @author shensi
     */
    @SuppressWarnings("unchecked")
    @Override
    public Map getJudgeRole(boolean flg, String proTypeId, Integer roleId,
            Integer regionalId) {
        Map map = new HashMap();
        Map mapParam = new HashMap();

        try {
            // 查询法务专员，运营总监，运营总监助理
            List listRole = null;
            if (flg) {// 新建、修改
                mapParam.put("proTypeId", "");
                mapParam.put("roleId", "");
                listRole = dao.queryForlist("salesContract_sqlMap.selectJudgeRole",
                        mapParam);
            } else if (roleId != null && Constants.ROLE_REGIONAL_DIRECTOR != roleId) {// 评审
                mapParam.put("proTypeId", proTypeId);
                mapParam.put("roleId", roleId);
                listRole = dao.queryForlist("salesContract_sqlMap.selectJudgeRole",
                        mapParam);
            }
            if ((roleId != null && Constants.ROLE_REGIONAL_DIRECTOR != roleId)
                    || roleId == null) {
                if (listRole == null || listRole.size() == 0) {
                    log.info("查询法务专员，运营总监，运营总监助理失败");
                    return map;
                }
                for (int i = 0; i < listRole.size(); i++) {
                    SalesJudgeRoleDto info = (SalesJudgeRoleDto) listRole.get(i);
                    map.put(info.getRoleId(), info);
                }
            }
            List listRoleByProTypeId = null;
            if (flg) {// 新建、修改
                mapParam.put("proTypeId", proTypeId);
                mapParam.put("roleId", "");
                listRoleByProTypeId = dao.queryForlist(
                        "salesContract_sqlMap.selectJudgeRoleById", mapParam);
            } else if (roleId != null && Constants.ROLE_REGIONAL_DIRECTOR != roleId) {// 评审
                mapParam.put("proTypeId", proTypeId);
                mapParam.put("roleId", "");
                listRoleByProTypeId = dao.queryForlist(
                        "salesContract_sqlMap.selectJudgeRoleById", mapParam);
            }
            if ((roleId != null && Constants.ROLE_REGIONAL_DIRECTOR != roleId)
                    || roleId == null) {
                if (listRoleByProTypeId == null || listRoleByProTypeId.size() == 0) {
                    log.info("查询产品总监，销售总监失败");
                    return map;
                }

                for (int i = 0; i < listRoleByProTypeId.size(); i++) {
                    SalesJudgeRoleDto info = (SalesJudgeRoleDto) listRoleByProTypeId
                            .get(i);
                    map.put(info.getRoleId(), info);
                }
            }
            // 区域总监查询
            List listRoleByProTypeIdAndArea = null;
            if (regionalId != null && !"".equals(regionalId)) {
                if (flg) {// 新建、修改
                    mapParam.put("roleId", "");
                } else {// 评审
                    mapParam.put("roleId", roleId);
                }
                mapParam.put("proTypeId", proTypeId);
                mapParam.put("regionalId", regionalId);
                listRoleByProTypeIdAndArea = dao.queryForlist(
                        "salesContract_sqlMap.selectJudgeRoleByRegionalId", mapParam);
            } else {
                log.info("区域编号为空");
            }
            if (listRoleByProTypeIdAndArea == null
                    || listRoleByProTypeIdAndArea.size() == 0) {
                log.info("查询区域总监失败");
                return map;
            } else if (listRoleByProTypeIdAndArea.size() > 1) {
                log.info("该产品类型在该区域存在" + listRoleByProTypeIdAndArea.size() + "个区域总监");
                SalesJudgeRoleDto info = (SalesJudgeRoleDto) listRoleByProTypeIdAndArea
                        .get(0);
                map.put(-info.getRoleId(), info);
            } else {
                SalesJudgeRoleDto info = (SalesJudgeRoleDto) listRoleByProTypeIdAndArea
                        .get(0);
                map.put(info.getRoleId(), info);
            }

        } catch (Exception e) {
            log.error("判断评审人是否存在失败", e);
        }
        return map;
    }

    /**
     * 销售合同评审更新
     * 
     * creator: 李乐伟 param updateData 销售合同评审要更新的所有数据 return ture 更新成功 false 更新失败
     */

    @SuppressWarnings("unchecked")
    @Override
    public boolean modifySalesContractForAssess(List updateData) {

        boolean isSuccess = false;
        int updateCount = 0;
        try {
            dao.beginTransaction();

            ICommonService service = null;
            service = (ICommonService) Container.getBean("commonServiceImpl");
            String status = service.getStatusById("sell_contract", updateData.get(4)
                    .toString());
            if ("-99".equals(status)) {// 记录不存在(已删除)
                log.error("该记录已被其他用户删除，请重新选择");
                return false;
            }

            boolean canUpdate = false;
            int roleId = Integer.parseInt(updateData.get(5).toString());
            if ((roleId == Constants.ROLE_PRODUCT_DIRECTOR && status.equals("2"))
                    || (roleId == Constants.ROLE_COMPLIANCE_OFFICER && status.equals("4"))
                    || (roleId == Constants.ROLE_SALES_DIRECTOR && status.equals("6"))
                    || (roleId == Constants.ROLE_AST_DIRECTOR_OF_OPERATIONS && status
                            .equals("8"))
                    || (roleId == Constants.ROLE_DIRECTOR_OF_OPERATIONS && status
                            .equals("10"))
                    || (roleId == Constants.ROLE_REGIONAL_DIRECTOR && status.equals("16"))) {
                canUpdate = true;

            }

            if (!canUpdate) {
                log.error("该记录已被其他用户修改，请重新选择");
                return false;
            }

            // 销售合同评审
            updateCount = dao.update("salesContract_sqlMap.updateSalesContractForAssess",
                    updateData.get(0));
            if (updateCount == 0) {
                log.debug("销售合同评审更新销售合同错误[更新数: 0]");
                return false;
            } else {
                isSuccess = true;
            }

            // 更新当前评审人的待办事务
            updateCount = dao.update("salesContract_sqlMap.updateCurrentAssessManWork",
                    updateData.get(1));
            if (updateCount == 0) {
                // 更行失败，回滚事务
                dao.endTransaction();
                log.debug("销售合同评审更新当前评审人的待办事务错误[更新数: 0]");
                return false;
            }
            // 产品总监评审更新合同明细列表
            if (updateData.get(2) != null) {

                isSuccess = updateSalesContractDeailForProMaj((List<SalesContractProductDetailDto>) updateData
                        .get(2));
                if (!isSuccess) {
                    dao.endTransaction();
                    log.debug("销售合同评审失败：更新合同明细失败");
                    return isSuccess;
                }

                // 评审未通过删除需求单表产品 Add By ChenHJ @2010-07-14
                if (updateData.get(3) == null) {
                    boolean isOK = false;
                    SalesContractInfoDto dto = (SalesContractInfoDto) updateData.get(0);
                    isOK = removeAllDemandOfContract(dto.getId());
                    if (!isOK) {
                        dao.endTransaction();
                        log.debug("销售合同评失败：删除需求单失败");
                        return isSuccess;
                    }
                }
            }// if

            // 下一个评审人的待办事项
            if (updateData.get(3) != null) {
                AssessManDto nextAssessMan = (AssessManDto) updateData.get(3);
                if (isNextAssessManWorkExist(nextAssessMan)) {
                    isSuccess = updateNextAssessManWork(nextAssessMan);
                    // 运营总监、运营总监助理时更新OPE_MAJ_ID
                    if (nextAssessMan.getRoleId() == Constants.ROLE_DIRECTOR_OF_OPERATIONS
                            || nextAssessMan.getRoleId() == Constants.ROLE_AST_DIRECTOR_OF_OPERATIONS) {
                        HashMap map = new HashMap();
                        map.put("userId", nextAssessMan.getAssessManId());
                        map.put("id", updateData.get(4));
                        int intCtn = dao.update("salesContract_sqlMap.updateOpeMajId",
                                map);
                        if (intCtn == 0) {
                            dao.endTransaction();
                            log.debug("更新运营总监、运营总监助理userId失败");
                            return false;
                        }
                    }
                } else {
                    isSuccess = insertNextAssessManWork(nextAssessMan);
                }

                if (isSuccess == false) {
                    dao.endTransaction();
                    log.debug("销售合同评审更新更新合同明细错误");
                    return isSuccess;
                }
            }// if

            dao.commitTransaction();

        } catch (Exception e) {
            log.error("销售合同评审错误", e);
        } finally {

            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.error("销售合同评审事务回滚错误", e);
                isSuccess = false;
            }
        }// finally

        return isSuccess;
    }

    /**
     * 产品总监评审更新合同明细列表
     * 
     * @author 李乐伟
     * @param allDetail
     * @return
     */

    private boolean updateSalesContractDeailForProMaj(
            List<SalesContractProductDetailDto> allDetail) {

        boolean isSuccess = false;
        try {
            for (SalesContractProductDetailDto detail : allDetail) {

                int count = dao.update(
                        "salesContract_sqlMap.updateSalesContractDeailForProMajAssess",
                        detail);

                if (count == 0) {
                    log.debug(" 产品总监评审更新合同明细列表错误[更新数: 0]");
                    return false;
                }// if
            }// for

            isSuccess = true;
        } catch (Exception e) {
            log.error("产品总监评审更新合同明细列表错误", e);
            isSuccess = false;
        }

        return isSuccess;
    }

    /**
     * 更新下一个评审人的待办事务
     * 
     * @author 李乐伟
     * @param allDetail
     * @return
     */
    public boolean updateNextAssessManWork(AssessManDto nextAssessMan) {

        boolean isSuccess = false;
        try {

            int updateCount = dao.update("salesContract_sqlMap.updateNextAssessManWork",
                    nextAssessMan);
            // 更新失败，回滚
            isSuccess = updateCount > 0;

        } catch (Exception e) {
            log.error("更新下一个评审人的待办事务错误", e);
            isSuccess = false;
        }

        return isSuccess;
    }

    /**
     * 新增下一个评审人的待办事务
     * 
     * @author 李乐伟
     * @param allDetail
     * @return
     */
    public boolean insertNextAssessManWork(AssessManDto nextAssessMan) {
        boolean isSuccess = true;
        try {
            dao.insert("salesContract_sqlMap.insertNextAssessManWork", nextAssessMan);
        } catch (Exception e) {
            log.error("新增下一个评审人的待办事务错误", e);
            isSuccess = false;
        }
        return isSuccess;
    }

    /**
     * 根据事务id,用户id查询下一个评审人的对应事务是否存在
     * 
     * @param contractData
     * @return
     * @author 李乐伟
     */
    @Override
    public boolean isNextAssessManWorkExist(AssessManDto nextAssessMan) {

        boolean isExist = false;

        try {

            int recordCount = (Integer) dao.queryForObject(
                    "salesContract_sqlMap.selectNextAssessManWorkRecordCount",
                    nextAssessMan);

            if (recordCount > 0) {
                isExist = true;
            }

        } catch (Exception e) {
            log.error("根据事务id,用户id查询下一个评审人的对应事务是否存在错误", e);
        }
        return isExist;
    }

    /**
     * 查询与销售合同对应的销售助理id
     * 
     * @author 李乐伟
     */
    @Override
    public String getSalesAssistantIdByContractId(String contractId) {

        String salesAssisantId = null;

        try {

            salesAssisantId = (String) dao.queryForObject(
                    "salesContract_sqlMap.selectSalesAssistantByContractId", contractId);

        } catch (Exception e) {
            log.error("查询与销售合同对应的销售助理id错误", e);
        }

        return salesAssisantId;
    }

    /**
     * 销售合同修改删除明细表产品 shensi
     */
    @Override
    public boolean removeSalesConDetail(SellContractDetailEntity sellContractDetailEntity) {
        boolean isSuccess = false;
        try {
            dao.delete("salesContract_sqlMap.deleteSalesConDetail",
                    sellContractDetailEntity);
            isSuccess = true;
        } catch (Exception e) {
            log.error("查销售合同修改删除明细表产品失败", e);
            isSuccess = false;
        }
        return isSuccess;
    }

    /**
     * 销售合同修改 删除需求单表 shensi
     */
    @Override
    public boolean removeDemand(DemandEntity demandEntity) {
        boolean isSuccess = false;
        try {
            dao.delete("salesContract_sqlMap.deleteDemand", demandEntity);
            isSuccess = true;
        } catch (Exception e) {
            log.error("销售合同修改 删除需求单表失败", e);
            isSuccess = false;
        }
        return isSuccess;
    }

    /**
     * 删除销售合同的全部合同明细
     * 
     * @author 李乐伟
     * @param contractId
     *            合同id
     */
    @Override
    public boolean removeAllContractDetailByContractId(String contractId) {

        boolean isSuccess = true;

        try {

            dao.delete("salesContract_sqlMap.deleteAllContractDetailByContractId",
                    contractId);

        } catch (Exception e) {

            log.error("删除销售合同的全部明细错误", e);
            isSuccess = false;
        }

        return isSuccess;
    }

    @Override
    public boolean addSalesContractDetil(
            List<SalesContractProductDetailDto> contractDetailList) {

        boolean isSuccess = true;

        for (SalesContractProductDetailDto dtoDetail : contractDetailList) {

            SellContractDetailEntity detailEntity = new SellContractDetailEntity();
            detailEntity.setBuyPrice(dtoDetail.getDetailBuyPrice().doubleValue() + "");
            detailEntity.setCount(dtoDetail.getDetailCount());
            detailEntity.setSellContractId(dtoDetail.getContractId());
            detailEntity.setPrice(dtoDetail.getDetailPrice().doubleValue() + "");
            detailEntity.setProductId(dtoDetail.getProductId());

            isSuccess = addSalesContarctDetial(detailEntity);

            if (isSuccess == false) {
                return isSuccess;
            }
        }
        return isSuccess;
    }

    /**
     * 删除销售合同的全部未确认需求单
     * 
     * @param contractId
     *            合同id
     * @return
     * @author 李乐伟
     */
    @Override
    public boolean removeAllDemandOfContract(String contractId) {

        boolean isSuccess = true;
        try {
            dao.delete("salesContract_sqlMap.deleteAllDemandOfContract", contractId);
        } catch (Exception e) {
            isSuccess = false;
            log.error("删除销售合同的全部合同明细错误", e);
        }

        return isSuccess;
    }

    /**
     * 删除销售合同主表 zhangzixu
     */
    private int removeSalesContractMaster(String contractId) throws Exception {
        int count = 0;
        count = dao.delete("salesContract_sqlMap.deleteSalesContract", contractId);
        return count;
    }

    /**
     * 删除销售合同明细 zhangzixu
     */
    private int removeSalesContractDetail(String contractId) throws Exception {
        int count = 0;
        count = dao.delete("salesContract_sqlMap.deleteSalesContractDetail", contractId);
        return count;
    }

    /**
     * 根据contractId查询应删除的明细表记录数 zhangzixu
     */
    private int getRemoveSalesContractDetailCount(String contractId) throws Exception {
        int count = -1;
        count = dao.getCount("salesContract_sqlMap.getSalesContractDetailCount",
                contractId);
        return count;
    }

    /**
     * 删除合同 zhangzixu
     */
    @Override
    public boolean removeSalesContract(String contractId) {
        boolean isSuccess = false;

        try {

            dao.beginTransaction();
            // 获得应删除的明细表记录数
            int needCountDetail = getRemoveSalesContractDetailCount(contractId);
            // 获得删除的明细表记录数
            int countDetail = removeSalesContractDetail(contractId);
            // 获得删除的合同表记录数
            int countMaster = removeSalesContractMaster(contractId);
            if (countDetail != needCountDetail || countMaster < 1) {
                dao.endTransaction();
                return false;
            }

            dao.commitTransaction();

            isSuccess = true;
        } catch (Exception e) {
            log.debug("删除销售合同失败", e);
            isSuccess = false;
        } finally {

            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.debug("删除销售合同失败", e);
                isSuccess = false;
            }
        }
        return isSuccess;
    }

    /**
     * 
     * 获得公司信息 zhangzixu
     */
    @Override
    public CompanyInfoDto getCompanyInfo() {
        CompanyInfoDto companyInfo = null;
        try {
            companyInfo = (CompanyInfoDto) dao.queryForObject(
                    "salesContract_sqlMap.getCompanyInfo", null);
        } catch (Exception e) {
            log.error("销售合同 删除明细表失败", e);
        }

        return companyInfo;
    }

    /**
     * 更新合同状态信息（待打印->待确认）zhangzixu
     * 
     */
    @Override
    public boolean modifyContractStatus(String contractId, WorkEntity work) {
        boolean isSuccess = false;

        try {

            dao.beginTransaction();
            // 获得更新的记录数
            int count = dao.update("salesContract_sqlMap.modifyPrintStatus", contractId);

            boolean workSelf = NextAssessManWork(work);

            if (count != 1 || workSelf == false) {
                dao.endTransaction();
                return false;
            }
            dao.commitTransaction();

            isSuccess = true;
        } catch (Exception e) {
            log.debug("更新销售合同失败", e);
            isSuccess = false;
        } finally {

            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.debug("更新销售合同失败", e);
                isSuccess = false;
            }
        }

        return isSuccess;
    }

    /**
     * 下一个评审人的待办事项
     * 
     * @author zhangzx
     * @param work
     */
    private boolean NextAssessManWork(WorkEntity work) {
        boolean isSuccess = false;

        ITodoWorkService todoWorkService = (TodoWorkService) Container
                .getBean("todoWrokServiceImpl");

        isSuccess = todoWorkService.addTodoWorks(work);

        return isSuccess;
    }

    /**
     * 更新合同状态信息（待确认->合同生效）zhangzixu
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean modifyContractStatusEffect(Map map) {
        boolean isSuccess = false;

        try {

            dao.beginTransaction();
            // 获得更新的记录数
            int count = dao
                    .update("salesContract_sqlMap.modifyContractStatusEffect", map);
            if (count != 1) {
                dao.endTransaction();
                return false;
            }

            dao.commitTransaction();

            isSuccess = true;
        } catch (Exception e) {
            log.debug("更新销售合同失败", e);
            isSuccess = false;
        } finally {

            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.debug("更新销售合同失败", e);
                isSuccess = false;
            }
        }

        return isSuccess;
    }

    private int removeDemand(String contractId) throws Exception {
        int count = 0;
        count = dao.delete("salesContract_sqlMap.deleteContractDemand", contractId);
        return count;
    }

    /**
     * 合同取消 zhangzixu
     */
    @Override
    public boolean modifySalesContractCancel(String contractId) {
        boolean isSuccess = false;

        try {
            dao.beginTransaction();
            // 获得更新的记录数
            int countUpdate = dao.update(
                    "salesContract_sqlMap.modifyContractStatusCancel", contractId);
            // 删除demand表数据
            removeDemand(contractId);

            if (countUpdate != 1) {
                dao.endTransaction();
                return false;
            }
            dao.commitTransaction();

            isSuccess = true;
        } catch (Exception e) {
            log.debug("取消销售合同失败", e);
            isSuccess = false;
        } finally {

            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.debug("取消销售合同失败", e);
                isSuccess = false;
            }
        }
        return isSuccess;
    }

    /* 产品分类及部门 */
    @SuppressWarnings("unchecked")
    @Override
    public List getProdTypeDeptList(String userId) {
        log.info("获取产品分类及部门");
        HashMap map = new HashMap();
        map.put("userId", userId);
        List list = null;
        try {
            list = dao.queryForlist("saleContract.selectProductTypeByUserId", map);
        } catch (Exception e) {
            log.error("获取产品分类及部门失败" + e);
        }
        return list;
    }

    /* 根据条件检索产品信息 */
    @SuppressWarnings("unchecked")
    @Override
    public List getProdInfoByCondition(ProductSearchForm param, NewPage page) {
        log.info("根据条件检索产品信息");
        List list = null;
        try {
            list = queryRecords(dao, "saleContractProd.selectProductByCondition", param,
                    page);
        } catch (Exception e) {
            log.error("根据条件检索产品信息失败" + e);
        }
        return list;
    }

    /* 根据产品分类ID检索产品系列信息 */
    @SuppressWarnings("unchecked")
    @Override
    public List getProdSeriesByProdTypeId(String prodTypeId) {
        log.info("根据产品分类ID检索产品系列信息");
        HashMap map = new HashMap();
        map.put("prodTypeId", prodTypeId);
        List list = null;
        try {
            list = dao.queryForlist("saleContractSeries.selectProdSeriesByProdTypeId",
                    map);
        } catch (Exception e) {
            log.error("根据产品分类ID检索产品系列信息失败" + e);
        }
        return list;
    }

    public NewIDao getDao() {
        return dao;
    }

    public void setDao(NewIDao dao) {
        this.dao = dao;
    }
}
