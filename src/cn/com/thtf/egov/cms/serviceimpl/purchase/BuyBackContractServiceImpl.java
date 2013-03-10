/**
 * ClassName  BuyBackContractServiceImpl
 *
 * History
 * Create User: ChenHuajiang
 * Create Date: 2010-6-12
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.serviceimpl.purchase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.BaseService;
import cn.com.thtf.egov.cms.application.NewIDao;
import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.constant.Constants;
import cn.com.thtf.egov.cms.dto.BuyBackContractDto;
import cn.com.thtf.egov.cms.dto.BuyBackContractInfoDto;
import cn.com.thtf.egov.cms.dto.BuyBackContractPreviewDto;
import cn.com.thtf.egov.cms.dto.BuyBackContractPreviewProdDto;
import cn.com.thtf.egov.cms.dto.BuyBackContractReviewDto;
import cn.com.thtf.egov.cms.dto.BuyContractProductInfoDto;
import cn.com.thtf.egov.cms.dto.BuyContractReviewDto;
import cn.com.thtf.egov.cms.dto.SupplierAddressDto;
import cn.com.thtf.egov.cms.entity.BuyBackContractDetailEntity;
import cn.com.thtf.egov.cms.entity.BuyBackContractEntity;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.entity.WorkEntity;
import cn.com.thtf.egov.cms.form.BuyContractSearchForm;
import cn.com.thtf.egov.cms.iservice.purchase.IBuyBackContractService;
import cn.com.thtf.egov.cms.iservice.work.ITodoWorkService;
import cn.com.thtf.egov.cms.util.Container;

/**
 * 采购退货合同
 * 
 * @author ChenHuajiang
 */

public class BuyBackContractServiceImpl extends BaseService implements
        IBuyBackContractService {

    /** log */
    private static Logger log = LoggerFactory.getLogger(BuyBackContractServiceImpl.class);
    /** NewIDao */
    private NewIDao dao;

    public NewIDao getDao() {
        return dao;
    }

    public void setDao(NewIDao dao) {
        this.dao = dao;
    }

    /*
     * 检索采购退货合同信息
     */
    @SuppressWarnings("unchecked")
    @Override
    public List getBuyBackContracts(BuyContractSearchForm param, NewPage page) {
        log.info("获取采购退货合同列表数据");
        List list = null;
        try {
            list = queryRecords(dao, "buyBackContract_sqlMap.selectBuyBackContracts",
                    param, page);
        } catch (Exception e) {
            log.error("获取采购退货合同列表数据失败" + e);
        }
        return list;
    }

    /* 采购合同信息 */
    @Override
    public BuyBackContractDto getBuyContractInfoById(String id) {
        log.info("新建采购退货合同-采购合同信息");

        BuyBackContractDto dto = null;
        try {
            dto = (BuyBackContractDto) dao.queryForObject(
                    "buyBackContract_sqlMap.selectBuyContractsInfoById", id);
        } catch (Exception e) {
            log.error("新建采购退货合同-采购合同信息失败" + e);
        }
        return dto;
    }

    /* 产品选择 */
    @SuppressWarnings("unchecked")
    @Override
    public List getProdInfoListById(HashMap map, NewPage page) {
        log.info("获取产品信息");
        List list = null;
        try {
            list = queryRecords(dao, "buyBackCon_SqlMap.selectProdInfoById", map, page);
        } catch (Exception e) {
            log.error("获取产品信息失败" + e);
        }
        return list;
    }

    /**
     * 供应商信息
     * 
     * @author HanHaiyun
     */
    @SuppressWarnings("unchecked")
    @Override
    public List getSupplierAddress(String name, String buyContractId, NewPage page) {
        log.info("获取供应商信息");
        HashMap map = new HashMap();
        map.put("supplierName", name);
        map.put("buyContractId", buyContractId);
        List list = null;
        try {
            list = queryRecords(dao, "buyBackCon_SqlMapSup.selectSuppliersAddress", map,
                    page);
        } catch (Exception e) {
            log.error("获取供应商信息失败" + e);
        }
        return list;
    }

    /**
     * @author HanHaiyun 添加采购退货合同
     */
    @Override
    public boolean insertBuyBackContract(BuyBackContractEntity buyBackContractEntity,
            List<BuyBackContractDetailEntity> buyBackContractDetailEntitys,
            WorkEntity work) {
        try {
            log.info("添加采购退货合同【开始】-server ");
            ITodoWorkService todoWork = (ITodoWorkService) Container
                    .getBean("todoWrokServiceImpl");
            /** 事物开始 */
            dao.beginTransaction();
            /** 插入采购合同 */
            dao.insert("buyBackCon_SqlMap.insertBuyBack", buyBackContractEntity);
            if (work != null) {
                todoWork.addTodoWorks(work);// 添加事务
            }
            dao.startBatch();
            for (int i = 0; i < buyBackContractDetailEntitys.size(); i++) {
                BuyBackContractDetailEntity buyBackContractDetailEntity = buyBackContractDetailEntitys
                        .get(i);
                dao.insert("buyBackCon_SqlMap.insertBuyBackDetail",
                        buyBackContractDetailEntity);
            }
            dao.executeBatch();
            dao.commitTransaction();
            log.info("添加采购退货合同【结束】-server ");
        } catch (Exception e) {
            log.error("添加采购退货合同失败！-server", e);
            return false;
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.error("添加采购退货合同事务关闭失败-server！", e);
            }
        }
        return true;
    }

    /*
     * 得到采购退货合同信息
     * 
     * @author HanHaiyun
     */
    @Override
    public BuyBackContractInfoDto getBuyBackContract(String buyBackContractId) {
        BuyBackContractInfoDto buyBackContractInfoDto = null;
        try {
            log.info("得到采购退货合同信息开始-server");
            buyBackContractInfoDto = (BuyBackContractInfoDto) dao.queryForObject(
                    "buyBackCon_SqlMap.selectBuyBackContract", buyBackContractId);
            log.info("得到采购退货合同信息结束-server");
        } catch (Exception e) {
            log.error("得到采购退货合同信息失败 -server  " + e);
        }
        return buyBackContractInfoDto;
    }

    /*
     * @author HanHaiyun 与采购退货合同对应的所有产品信息
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<BuyContractProductInfoDto> getBuyBackContractProducts(
            String buyBackContractId, String buyContractId) {
        List<BuyContractProductInfoDto> buyContractProductInfoDtos = null;
        try {
            log.info("得到采购退货合同产品信息开始-server");
            HashMap map = new HashMap();
            map.put("buyBackContractId", buyBackContractId);
            map.put("buyContractId", buyContractId);
            buyContractProductInfoDtos = dao.queryForlist(
                    "buyBackCon_SqlMap.selectBuyBackContractProduct", map);
            log.info("得到采购退货合同产品信息开始-server");
        } catch (Exception e) {
            log.error("得到采购退货合同产品信息开始-server  " + e);
        }
        return buyContractProductInfoDtos;
    }

    /**
     * 单个供货商信息
     * 
     * @author HanHaiyun
     */
    @Override
    public SupplierAddressDto getSupplierAddressById(String supplierId) {
        log.info("获取单个供应商信息");
        SupplierAddressDto supplierAddressDto = null;
        try {
            supplierAddressDto = (SupplierAddressDto) dao.queryForObject(
                    "suppliers.selectSuppliersAddressById", supplierId);
        } catch (Exception e) {
            log.error("获取单个供应商信息失败" + e);
        }
        return supplierAddressDto;
    }

    /**
     * 删除采购退货明细
     * 
     * @author HanHaiyun
     */
    @Override
    public boolean delBackContractDetailByContructBackContractId(
            String contructBackContractId) {
        log.info("删除采购退货合同明细开始-server");
        try {
            dao.delete("buyBackCon_SqlMap.delBuyBackDetail", contructBackContractId);
            log.info("删除采购退货合同明细结束-server");
        } catch (Exception e) {
            log.error("删除采购退货合同明细失败-server" + e);
            return false;
        }
        return true;
    }

    /**
     * 修改采购退货合同
     * 
     * @author HanHaiyun
     */
    @Override
    public boolean updBuyBackContract(BuyBackContractEntity buyBackContractEntity,
            List<BuyBackContractDetailEntity> buyBackContractDetailEntitys,
            WorkEntity work) {
        try {
            log.info("修改采购退货合同【开始】-server ");
            ITodoWorkService todoWork = (ITodoWorkService) Container
                    .getBean("todoWrokServiceImpl");
            /** 事物开始 */
            dao.beginTransaction();
            /** 插入采购合同 */
            dao.update("buyBackCon_SqlMap.updateBuyBackContract", buyBackContractEntity);
            delBackContractDetailByContructBackContractId(buyBackContractEntity.getId());
            if (work != null) {
                todoWork.addTodoWorks(work);// 添加事务
            }
            dao.startBatch();
            for (int i = 0; i < buyBackContractDetailEntitys.size(); i++) {
                BuyBackContractDetailEntity buyBackContractDetailEntity = buyBackContractDetailEntitys
                        .get(i);
                dao.insert("buyBackCon_SqlMap.insertBuyBackDetail",
                        buyBackContractDetailEntity);
            }
            dao.executeBatch();
            dao.commitTransaction();
            log.info("修改采购退货合同【结束】-server ");
        } catch (Exception e) {
            log.error("修改采购退货合同失败！-server", e);
            return false;
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.error("修改采购退货合同事务关闭失败-server！", e);
            }
        }
        return true;
    }

    /**
     * 采购退货合同预览基本信息
     */
    @Override
    public BuyBackContractPreviewDto getBackContractProview(String buyBackContractId) {
        log.info("采购退货合同预览基础数据查询开始-server");
        BuyBackContractPreviewDto buyBackContractPreviewDto = null;
        try {
            buyBackContractPreviewDto = (BuyBackContractPreviewDto) dao.queryForObject(
                    "buyBackCon_SqlMap.getBuyBackPreview", buyBackContractId);
        } catch (Exception e) {
            log.error("采购退货合同预览基础数据查询失败-server " + e);
        }
        return buyBackContractPreviewDto;
    }

    /**
     * 采购退货合同预览产品信息
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<BuyBackContractPreviewProdDto> getBackContractProviewProds(
            String buyBackContractId) {
        log.info("采购退货合同预览产品信息查询开始-server");
        List<BuyBackContractPreviewProdDto> prods = null;
        try {
            prods = dao.queryForlist("buyBackCon_SqlMap.getBuyBackPreview_prods",
                    buyBackContractId);
            log.info("采购退货合同预览产品信息查询结束-server");
        } catch (Exception e) {
            log.error("采购退货合同预览产品信息查询失败-server " + e);
        }
        return prods;
    }

    /**
     * 采购退货合同评审
     */
    @Override
    public boolean buyBackContractReview(BuyContractReviewDto buyContractReviewDto,
            UserEntity userEntity, Integer status,UserEntity buySpecialist) {
        log.info("采购退货合同评审【开始】-service");
        try {
            dao.beginTransaction();// 开始事务
            if (userEntity.getRoleId() == Constants.ROLE_COMPLIANCE_OFFICER) {// 用户身份法务专员
                buyContractReviewDto.setStatus(status == 1 ? 4 : 3);// 状态等于1表示通过
                // 得到当前用户信息
                buyContractReviewDto.setLegalId(userEntity.getId());
                buyContractReviewDto.setLegalName(userEntity.getName());
                // 事务修改
                if (status == 1) {
                    this.addTodoWorks(1, buyContractReviewDto.getBuyManId(),
                            Constants.SELL19);
                }
                // 将自己的事物减少一个
                this
                        .addTodoWorks(-1, buyContractReviewDto.getLegalId(),
                                Constants.SELL19);
            } else if (userEntity.getRoleId() == Constants.ROLE_PROCUREMENT_OFFICER) {// 用户身份为为采购主管
                // 如果状态为1(通过)，那么将合同状态修改为6,如果不通过则为5
                buyContractReviewDto.setStatus(status == 1 ? 6 : 5);
                buyContractReviewDto.setBuyManId(userEntity.getId());
                buyContractReviewDto.setBuyManName(userEntity.getName());
                // 事务修改
                if (status == 1) {
                    this.addTodoWorks(1, buyContractReviewDto.getOpeMajId(),
                            Constants.SELL19);
                }
                // 将自己的事物减少一个
                this.addTodoWorks(-1, buyContractReviewDto.getBuyManId(),
                        Constants.SELL19);
            } else if (userEntity.getRoleId() == Constants.ROLE_DIRECTOR_OF_OPERATIONS) {// 用户身份为运营总监
                buyContractReviewDto.setStatus(status == 1 ? 8 : 7);
                buyContractReviewDto.setOpeMajId(userEntity.getId());
                buyContractReviewDto.setOpeMajName(userEntity.getName());
                // 事务修改
                if (status == 1) {//给采购专员添加事务
                    this.addTodoWorks(1, buySpecialist.getId(), Constants.SELL20);
                }
                // 将自己的事物减少一个
                this.addTodoWorks(-1, buyContractReviewDto.getOpeMajId(),
                        Constants.SELL19);

            } else {
                return false;
            }
            dao.update("buyBackCon_SqlMap.review", buyContractReviewDto);
            dao.commitTransaction();// 提交事务
            log.info("采购退货合同评审【成功】-service");
        } catch (Exception e) {
            log.error("采购退货合同评审【失败】-service  " + e);
            return false;
        } finally {
            try {
                dao.endTransaction();
                log.info("采购退货合同评审【结束】-service");
            } catch (Exception e) {
                log.error("采购退货合同评审关闭事务【失败】-service  " + e);
                return false;
            }
        }
        return true;
    }
    public UserEntity getBuySpecialist(String contractId){
        UserEntity buySpecialist = null;
        try {
            buySpecialist = (UserEntity) dao.queryForObject(
                    "buyBackCon_SqlMap.selectBuyContractUserProduct",
                    contractId);
        } catch (Exception e) {
            log.error("采购合同评审获取用户【失败】-service");
            return null;
        }
        return buySpecialist;
    }
    /**
     * 添加事务
     * 
     * @author HanHaiyun
     * @param count
     *            事务个数
     * @param userId
     *            用户Id
     * @param workId
     *            事务Id
     * @return 是否添加成功
     */
    private boolean addTodoWorks(Integer count, String userId, Integer workId) {
        ITodoWorkService todoWork = (ITodoWorkService) Container
                .getBean("todoWrokServiceImpl");
        WorkEntity work = null;
        work = new WorkEntity();
        work.setCount(count);
        work.setUserId(userId);
        work.setWorkId(workId);
        return todoWork.addTodoWorks(work);
    }

    /**
     * 删除采购退货合同
     */
    @Override
    public boolean delBackContractById(String id) {

        try {
            log.info("删除采购退货合同开始-server");
            /** 事物开始 */
            dao.beginTransaction();
            String buyContractId = id;
            delBackContractDetailByContructBackContractId(buyContractId);
            dao.delete("buyBackCon_SqlMap.delBuyBackContract", id);
            dao.commitTransaction();
            log.info("删除采购退货合同【结束】-server ");
        } catch (Exception e) {
            log.error("删除采购退货合同失败！-server ", e);
            return false;
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.error("删除采购退货合同事务关闭失败-server ", e);
            }
        }
        return true;
    }

    /**
     * 查看评审表
     */
    @Override
    public BuyBackContractReviewDto getBackContractReview(String buyBackContractId) {
        log.info("查询采购退货合同评审信息 开始-service");
        BuyBackContractReviewDto buyBackContractReviewDto = null;
        try {
            if (buyBackContractId == null || "".equals(buyBackContractId)) {
                log
                        .info("查询采购退货合同评审信息-service 方法getBackContractReview参数buyBackContractId为null");
                return null;
            }
            buyBackContractReviewDto = (BuyBackContractReviewDto) dao.queryForObject(
                    "buyBackCon_SqlMap.selBackContractReview", buyBackContractId);
            log.info("查询采购退货合同评审信息 结束-service");
        } catch (Exception e) {
            log.error("查询采购退货合同评审信息 失败-service" + e);
            return null;
        }
        return buyBackContractReviewDto;
    }

    /**
     * 采购退货合同评审查看
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<BuyBackContractPreviewProdDto> getBackContractReviewProds(
            String buyBackContractId) {
        log.info("采购退货合同评审表产品信息查询开始-server");
        List<BuyBackContractPreviewProdDto> prods = null;
        try {
            prods = dao.queryForlist("buyBackCon_SqlMap.getBuyBackReview_prods",
                    buyBackContractId);
            log.info("采购退货合同评审表产品信息查询结束-server");
        } catch (Exception e) {
            log.error("采购退货合同评审表产品信息查询失败-server " + e);
        }
        return prods;
    }

    /**
     * 更新采购退货合同状态
     */
    @Override
    public boolean upBuyBackContractStatus(Integer status, String buyBackContractId,
            UserEntity userEntity) {
        log.info("更新采购退货合同编号为[" + buyBackContractId + "]的状态开始-service");
        try {
            if (status == null || buyBackContractId == null
                    || "".equals(buyBackContractId)) {
                return false;
            }
            Map<String, String> map = new HashMap<String, String>();
            map.put("status", status.toString());
            map.put("buyBackContractId", buyBackContractId);
            dao.update("buyBackCon_SqlMap.upBuyBackContractStatus", map);
            this.addTodoWorks(-1, userEntity.getId(), Constants.SELL20);// 减少事务
            log.info("更新采购退货合同编号为[" + buyBackContractId + "]的状态成功-service");
        } catch (Exception e) {
            log.error("更新采购退货合同编号为[" + buyBackContractId + "]的状态失败-service");
        }
        return true;
    }

    /**
     * 采购退货合同确认
     */
    @Override
    public boolean buyBackContractConfirm(Integer status, String companyContractCode,
            String buyBackContractId) {
        log.info("采购退货合同编号为[" + buyBackContractId + "]确认开始-service");
        try {
            if (status == null || buyBackContractId == null
                    || "".equals(buyBackContractId)) {
                return false;
            }
            Integer count = (Integer) dao.queryForObject(
                    "buyBackCon_SqlMap.selBuyBackContractCount", companyContractCode);
            if (count > 0) {
                log.error("采购退货合同公司合同号为[" + companyContractCode + "]已存在-service");
                return false;
            }
            Map<String, String> map = new HashMap<String, String>();
            map.put("status", status.toString());
            map.put("buyBackContractId", buyBackContractId);
            map.put("companyContractCode", companyContractCode);
            dao.update("buyBackCon_SqlMap.upBuyBackContractStatus", map);
            log.info("采购退货合同编号为[" + buyBackContractId + "]确认成功-service");
        } catch (Exception e) {
            log.error("采购退货合同编号为[" + buyBackContractId + "]确认失败-service " + e);
            return false;

        }
        return true;
    }

    /**
     * 判断公司合同号是否存在
     */
    @Override
    public boolean isExistsCompanyContractCode(String companyContractCode) {
        Integer count = 0;
        try {
            log.info("搜索公司合同号为[" + companyContractCode + "]是否存在开始-service");
            count = (Integer) dao.queryForObject(
                    "buyBackCon_SqlMap.selBuyBackContractCount", companyContractCode);
            log.info("搜索公司合同号为[" + companyContractCode + "]是否存在结束-service");
        } catch (Exception e) {
            log.error("搜索公司合同号为[" + companyContractCode + "]是否存在失败-service");
        }
        return count > 0;
    }

}
