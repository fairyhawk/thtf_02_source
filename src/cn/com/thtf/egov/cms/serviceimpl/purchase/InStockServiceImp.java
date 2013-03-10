/**
 * ClassName  InStockServiceImp
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-5-27
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.serviceimpl.purchase;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.BaseService;
import cn.com.thtf.egov.cms.application.NewIDao;
import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.constant.Constants;
import cn.com.thtf.egov.cms.dto.AddInStockDto;
import cn.com.thtf.egov.cms.dto.BuyContractDto;
import cn.com.thtf.egov.cms.dto.InStockProductDto;
import cn.com.thtf.egov.cms.dto.InStockQueryDto;
import cn.com.thtf.egov.cms.dto.InStockResultDto;
import cn.com.thtf.egov.cms.dto.IncomeStoreRoomDto;
import cn.com.thtf.egov.cms.dto.ReceiveGoodsDetailDto;
import cn.com.thtf.egov.cms.dto.WorkReceiverDto;
import cn.com.thtf.egov.cms.entity.InStockEntity;
import cn.com.thtf.egov.cms.entity.ReceiveGoodsDetailEntity;
import cn.com.thtf.egov.cms.entity.StockroomEntity;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.entity.UserProductEntity;
import cn.com.thtf.egov.cms.entity.WorkEntity;
import cn.com.thtf.egov.cms.iservice.common.ICommonService;
import cn.com.thtf.egov.cms.iservice.purchase.IInStockService;
import cn.com.thtf.egov.cms.iservice.stock.IIncomeStockService;
import cn.com.thtf.egov.cms.iservice.work.ITodoWorkService;
import cn.com.thtf.egov.cms.util.Container;
import cn.com.thtf.egov.cms.util.Util;

/**
 * InStockServiceImp
 * 
 * @author Lubo
 */
public class InStockServiceImp extends BaseService implements IInStockService {

    /** log */
    private static Logger log = LoggerFactory.getLogger(InStockServiceImp.class);
    /** NewIDao */
    private NewIDao dao;
    /** ICommonService */
    private ICommonService commonService;

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.purchase.IInStockService#auditInstock(cn
     * .com.thtf.egov.cms.entity.InStockEntity)
     */
    public boolean auditInstock(InStockEntity auditPara) {
        try {
            dao.beginTransaction();
            // 添加代办事物
            ITodoWorkService todoWorkService = (ITodoWorkService) Container
                    .getBean("todoWrokServiceImpl");
            WorkEntity work = new WorkEntity();
            work.setWorkId(Constants.SELL15);
            work.setUserId(auditPara.getProMajId());
            work.setCount(-1);
            todoWorkService.addTodoWorks(work);

            if (StringUtils.equals(auditPara.getProMajIdea(), "0")) {
                auditPara.setStatus(3);
            } else {
                // 如果是虚拟库 则需入,不需要库房管理员评审
                StockroomEntity stockroom = (StockroomEntity) dao.queryForObject(
                        "instock_sqlMap.selectStockroomTypeByInStockId",
                        auditPara.getId());

                if (stockroom.getType() == 0) {

                    // InStockEntity inStockEntity = (InStockEntity)
                    // dao.queryForObject(
                    // "instock_sqlMap.selectStkAdmById", auditPara.getId());

                    IIncomeStockService incomeStockService = (IIncomeStockService) Container
                            .getBean("incomeStockServiceImp");
                    IncomeStoreRoomDto incomeStoreRoomDto = new IncomeStoreRoomDto();
                    incomeStoreRoomDto.setId(auditPara.getId());
                    incomeStoreRoomDto.setStkAdmDate(Util.getDate());
                    // incomeStoreRoomDto.setStkAdmIdea("111");
                    // incomeStoreRoomDto.setStkAdmName(inStockEntity.getStkAdmName());
                    // incomeStoreRoomDto.setStkAdmText("");
                    incomeStoreRoomDto.setBillType("1");
                    incomeStoreRoomDto.setStatus(6);

                    int success = incomeStockService
                            .modifyIncomeStoreroomOfIn(incomeStoreRoomDto);
                    if (success == 0) {
                        return false;
                    }
                    auditPara.setStatus(6);
                } else {
                    auditPara.setStatus(4);
                }
            }

            dao.update("instock_sqlMap.updateAudit", auditPara);
            dao.commitTransaction();
            return true;
        } catch (Exception e) {
            log.error("评审入库单失败！", e);
            return false;
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.error("事务关闭失败！", e);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.purchase.IInStockService#removeInstock(
     * java.lang.String)
     */
    public boolean removeInstock(String instockId) {
        try {
            dao.delete("instock_sqlMap.deleteInstock", instockId);
            return true;
        } catch (Exception e) {
            log.error("删除入库单失败！", e);
            return false;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.purchase.IInStockService#getInStockList
     * (cn.com.thtf.egov.cms.dto.InStockQueryDto,
     * cn.com.thtf.egov.cms.application.NewPage)
     */
    @SuppressWarnings("unchecked")
    public List<InStockResultDto> getInStockList(InStockQueryDto para, NewPage page) {
        List result = null;
        try {
            /* 当前用户的负责产品 */
            UserEntity userInfo = new UserEntity();

            if (para.getRoleId() == Constants.ROLE_PRODUCT_DIRECTOR
                    && StringUtils.isNotBlank(para.getInit())) {
                para.setUserProduct("true");
            }

            /* 产品总监和采购专员要加上区域 */
            if (para.getRoleId() == Constants.ROLE_PRODUCT_DIRECTOR
                    || para.getRoleId() == Constants.ROLE_PROCUREMENT_COMMISSIONER
                    || para.getRoleId() == Constants.ROLE_PROCUREMENT_OFFICER) {
                userInfo.setId(para.getUserId());
                List<UserProductEntity> userProductList = (List<UserProductEntity>) dao
                        .getSqlMap().queryForList("salesContract_sqlMap.getUserProduct",
                                userInfo);

                if (userProductList.size() > 0) {
                    /* 封装查询参数 */
                    setUserProduct(para, userProductList);
                    result = queryRecords(dao, "selectInStockList.data", para, page);
                } else {
                    result = new ArrayList();
                }

            } else {
                result = queryRecords(dao, "selectInStockList.data", para, page);
            }

            if (result != null) {
                for (Object object : result) {
                    InStockResultDto dto = (InStockResultDto) object;
                    String money = (String) dao.queryForObject(
                            "instock_sqlMap.selectInStockListZ", dto.getInStockId());
                    if (result == null) {
                        dto.setBuyBackGoodsMoney("0");
                    } else {
                        dto.setBuyBackGoodsMoney(money);
                    }
                }
            }
        } catch (Exception e) {
            log.error("检索入库单列表错误！", e);
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.purchase.IInStockService#getInStock(java
     * .lang.String)
     */
    @Override
    public InStockEntity getInStock(String id) {
        InStockEntity result = null;
        try {
            result = (InStockEntity) dao.queryForObject(
                    "instock_sqlMap.selectInStockById", id);
        } catch (Exception e) {
            log.error("检索入库单信息错误:", e);

        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.purchase.IInStockService#addInStock(cn.
     * com.thtf.egov.cms.dto.AddInStockDto)
     */
    @Override
    public boolean addInStock(AddInStockDto para) {
        try {
            dao.beginTransaction();
            commonService = (ICommonService) Container.getBean("commonServiceImpl");

            WorkReceiverDto workReceiverResult = null;

            StockroomEntity stockroom = (StockroomEntity) dao.queryForObject(
                    "inventtory_sqlMap.getStockroomById", para.getStockroomId());

            if (stockroom.getType() != 0) {

                // 检索是否存在对应的库房管理员
                workReceiverResult = commonService.getUserIdByCondition(
                        para.getProductTypeId(), null,
                        Integer.parseInt(para.getStockroomId()));

                if (workReceiverResult == null) {
                    log.error("未找到库房管理员");
                    return false;
                }
                para.setStkAdmId(workReceiverResult.getUserId());
            }

            // 检索是否存在对应的产品总监
            workReceiverResult = commonService.getUserIdByCondition(
                    para.getProductTypeId(), null, null, 10);

            if (workReceiverResult == null) {
                log.error("未找到产品总监");
                return false;
            }

            para.setProMajId(workReceiverResult.getUserIdByRoleId(10));

            // 保存时不添加代办事物
            if (StringUtils.equals(para.getSubmitType(), "2")) {
                ITodoWorkService todoWorkService = (ITodoWorkService) Container
                        .getBean("todoWrokServiceImpl");
                WorkEntity work = new WorkEntity();
                work.setWorkId(Constants.SELL15);
                work.setCount(1);
                work.setUserId(workReceiverResult.getUserIdByRoleId(10));

                todoWorkService.addTodoWorks(work);
            }

            BigDecimal totPrice = new BigDecimal(0);
            for (int i = 0; i < para.getProductIdArr().length; i++) {
                totPrice = totPrice.add(new BigDecimal(para.getProductCountArr()[i])
                        .multiply(new BigDecimal(para.getProductPriceArr()[i])));
            }
            para.setMoney(totPrice.toString());

            if (para.isPageType()) {
                // delete 入库单明细
                dao.delete("instock_sqlMap.deleteInstockDetail", para.getId());
                // update 入库单
                dao.update("instock_sqlMap.updateInstock", para);
            } else {
                /* 插入入库单明细 */
                dao.insert("instock_sqlMap.insertInstock", para);
            }

            dao.startBatch();
            /* 插入发货单明细表 */
            AddInStockDto detailPara = null;
            for (int i = 0; i < para.getProductIdArr().length; i++) {
                detailPara = new AddInStockDto();
                detailPara.setId(para.getId());

                detailPara.setProductId(para.getProductIdArr()[i]);
                detailPara.setProductCount(para.getProductCountArr()[i]);
                detailPara.setProductPrice(para.getProductPriceArr()[i]);

                dao.insert("instock_sqlMap.insertInstockDetail", detailPara);
            }
            dao.executeBatch();
            dao.commitTransaction();

            return true;
        } catch (Exception e) {
            log.error("保存入库单,发生错误:", e);
            return false;
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e1) {
                log.error("保存入库事物回滚,发生错误:", e1);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.purchase.IInStockService#getBuyContract
     * (java.lang.String)
     */
    @Override
    public BuyContractDto getBuyContract(String buyContractId) {
        BuyContractDto result = null;
        try {
            result = (BuyContractDto) dao.queryForObject(
                    "instock_sqlMap.selectBuyContractById", buyContractId);
        } catch (Exception e) {
            log.error("查询采购合同信息错误", e);
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.purchase.IInStockService#getReceiveGoodsDetail
     * (java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<ReceiveGoodsDetailDto> getReceiveGoodsDetail(ReceiveGoodsDetailEntity para) {
        List<ReceiveGoodsDetailDto> result = null;
        try {
            result = (List<ReceiveGoodsDetailDto>) dao.queryForlist(
                    "instock_sqlMap.selectReceiveGoodsDetail", para);
        } catch (Exception e) {
            log.error("选择收获地址", e);
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.purchase.IInStockService#getProductList
     * (cn.com.thtf.egov.cms.dto.ReceiveGoodsDetailDto)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<InStockProductDto> getProductList(ReceiveGoodsDetailDto para) {
        List<InStockProductDto> result = null;
        try {
            List<String> idList = dao.queryForlist(
                    "instock_sqlMap.selectReceiveGoodsDetailId", para);
            para.setIdList(idList);

            result = (List<InStockProductDto>) dao.queryForlist(
                    "instock_sqlMap.selectProductList", para);
        } catch (Exception e) {
            log.error("选择产品", e);
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.purchase.IInStockService#getProductListView
     * (cn.com.thtf.egov.cms.dto.ReceiveGoodsDetailDto)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<InStockProductDto> getProductListView(ReceiveGoodsDetailDto para) {
        List<InStockProductDto> result = null;
        try {
            List<String> idList = dao.queryForlist(
                    "instock_sqlMap.selectReceiveGoodsDetailId", para);
            para.setIdList(idList);

            result = (List<InStockProductDto>) dao.queryForlist(
                    "instock_sqlMap.selectProductListView", para);
        } catch (Exception e) {
            log.error("选择产品", e);
        }
        return result;
    }

    /**
     * 封装 查询参数 区域产品
     * 
     * @param salesDto
     * @param userAreaProductList
     */
    private void setUserProduct(InStockQueryDto para,
            List<UserProductEntity> userProductList) {
        StringBuffer product = new StringBuffer();

        for (UserProductEntity userProduct : userProductList) {
            product.append(userProduct.getProductTypeId()).append(",");
        }

        if (product.length() > 0) {
            para.setProductTypeIdArr(product.substring(0, product.length() - 1));
        }
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
