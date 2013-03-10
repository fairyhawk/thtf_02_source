package cn.com.thtf.egov.cms.serviceimpl.sell;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.BaseService;
import cn.com.thtf.egov.cms.application.NewIDao;
import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.constant.Constants;
import cn.com.thtf.egov.cms.dto.NewReturnsProductDto;
import cn.com.thtf.egov.cms.dto.ReturnGoodsListQueryDto;
import cn.com.thtf.egov.cms.dto.SaleReturnGoodsViewSelfDto;
import cn.com.thtf.egov.cms.dto.WorkReceiverDto;
import cn.com.thtf.egov.cms.entity.SellBackGoodsDetailEntity;
import cn.com.thtf.egov.cms.entity.SellBackGoodsEntity;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.entity.WorkEntity;
import cn.com.thtf.egov.cms.iservice.common.ICommonService;
import cn.com.thtf.egov.cms.iservice.sell.ISaleReturnsService;
import cn.com.thtf.egov.cms.iservice.work.ITodoWorkService;
import cn.com.thtf.egov.cms.util.Container;

/**
 * 销售退货
 * 
 * @author LuPing
 */
public class SaleReturnsServiceImpl extends BaseService implements ISaleReturnsService {

    private NewIDao dao;

    private static Logger log = LoggerFactory.getLogger(ISaleReturnsService.class);

    /** ICommonService */
    private ICommonService commonService;
    /** ITodoWorkService */
    private ITodoWorkService todoWorkService;

    /**
     * 
     * @return
     */
    public NewIDao getDao() {
        return dao;
    }

    /**
     * 
     * @param dao
     */
    public void setDao(NewIDao dao) {
        this.dao = dao;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List getReturnGoodsList(NewPage page,
            ReturnGoodsListQueryDto returnGoodsListQueryDto) {
        List list = new ArrayList();
        try {
            list = queryRecords(dao, "selectReturnGoodsList.search",
                    returnGoodsListQueryDto, page);
        } catch (Exception e) {
            log.error("select sell_back_goods error!", e);
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.sell.ISaleReturnsService#selectAddressSelect
     * (cn.com.thtf.egov.cms.application.NewPage)
     */
    @SuppressWarnings("unchecked")
    public List selectAddressSelect(NewPage page) {
        List list = new ArrayList();
        try {
            list = queryRecords(dao, "selectSaleReturnsAddress.search", null, page);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.sell.ISaleReturnsService#newSaleReturnsInit
     * (java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public List<NewReturnsProductDto> newSaleReturnsInit(NewPage page, String sendid,
            String backGoodsId) {
        log.debug("产品信息小页面");
        List list = new ArrayList();
        Map<String, String> map = new HashMap<String, String>();
        map.put("sendGoodsId", sendid);
        map.put("backGoodsId", backGoodsId);
        try {
            list = queryRecords(dao, "selectNewReturnsProductList.search", map, page);
        } catch (Exception e) {
            log.error("新建退货单查询产品信息出错!", e);
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.sell.ISaleReturnsService#getReturnGoodsById
     * (java.lang.String)
     */
    @Override
    public SaleReturnGoodsViewSelfDto getReturnGoodsById(String returnGoodsId) {
        SaleReturnGoodsViewSelfDto saleReturnGoodsViewSelfDto = new SaleReturnGoodsViewSelfDto();
        try {
            saleReturnGoodsViewSelfDto = (SaleReturnGoodsViewSelfDto) dao.queryForObject(
                    "returnGoods_sqlMap.getReturnGoodsById", returnGoodsId);
        } catch (Exception e) {
            log.error("select sell_back_goods by id error! ", e);
        }
        return saleReturnGoodsViewSelfDto;
    }

    /*
     * (non-Javadoc)
     * 
     * @seecn.com.thtf.egov.cms.iservice.sell.ISaleReturnsService#
     * getReturnGoodsProductAndCustomer(java.lang.String)
     */
    public SaleReturnGoodsViewSelfDto getReturnGoodsProductAndCustomer(String id) {
        log.debug("新建退货单查询客户名称和产品分类名称查询!");
        SaleReturnGoodsViewSelfDto srgvsd = new SaleReturnGoodsViewSelfDto();
        try {
            srgvsd = (SaleReturnGoodsViewSelfDto) dao.queryForObject(
                    "returnGoods_sqlMap.selectProductAndCustomeName", id);
        } catch (Exception e) {
            log.error("新建退货单查询客户名称和产品分类名称查询出错！ ", e);
        }
        return srgvsd;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.sell.ISaleReturnsService#addReturnsGoods
     * (cn.com.thtf.egov.cms.entity.SellBackGoodsEntity)
     */
    @SuppressWarnings("unchecked")
    public Boolean addReturnsGoods(SellBackGoodsEntity sbge, Integer[] thcount,
            Integer[] productId) {
        log.debug("新建退货单service");
        boolean result = true;
        List<NewReturnsProductDto> list = new ArrayList<NewReturnsProductDto>();
        Map<String, String> map = new HashMap<String, String>();
        map.put("sendGoodsId", sbge.getSendGoodsId());
        // Integer[] productId = new Integer[thcount.length];
        BigDecimal thmony = new BigDecimal(0);
        BigDecimal pdMoney0 = new BigDecimal(0);
        BigDecimal pdMoney1 = new BigDecimal(0);
        BigDecimal pdMoney2 = new BigDecimal(0);
        try {
            dao.beginTransaction();
            list = dao.queryForlist("returnGoods_sqlMap.selectNewReturnsProductList1",
                    map);
            for(NewReturnsProductDto ss:list){
                log.debug(ss.getProductId()+"-"+ss.getProductCode()+"-"+ss.getZdmoney()+"-"+ss.getKpmoney());
            }
            int i = 0;
            for (NewReturnsProductDto nrpd : list) {
                for (int q = 0; q < productId.length; q++) {
                    if (!(nrpd.getProductId().equals(productId[q]))) {
                        continue;
                    }
                    log.debug(thcount[q] + "退货数");
                    if (thcount[q] != 0) {
                        pdMoney1 = nrpd.getPrice().multiply(
                                new BigDecimal(nrpd.getFfcount())).subtract(
                                nrpd.getZdmoney()).subtract(
                                nrpd.getPrice().multiply(
                                        new BigDecimal(nrpd.getyBackCount())));
                        log.debug(pdMoney1 + "条件一");
                        pdMoney0 = nrpd.getPrice().multiply(
                                new BigDecimal(nrpd.getFfcount())).subtract(
                                nrpd.getKpmoney()).subtract(
                                nrpd.getPrice().multiply(
                                        new BigDecimal(nrpd.getyBackCount())));
                        log.debug(pdMoney0 + "条件二");
                        pdMoney2 = nrpd.getPrice().multiply(new BigDecimal(thcount[i]));
                        log.debug(pdMoney2 + "退货金额");
                        if (pdMoney1.compareTo(pdMoney2) < 0
                                || pdMoney0.compareTo(pdMoney2) < 0) {
                            log.debug("验证条件不符合!");
                            throw new Exception();
                        }
                        thmony = thmony.add(pdMoney2);
                        // productId[i] = nrpd.getProductId();
                        // log.debug(productId[i] + "产品编号");
                        i++;
                    }
                }
            }
            if(sbge.getStatus() == 2){
                // 获取bean容器
                log.debug("产品分类："+sbge.getProductTypeId());
                todoWorkService = (ITodoWorkService) Container
                        .getBean("todoWrokServiceImpl");
                commonService = (ICommonService) Container.getBean("commonServiceImpl");
                WorkReceiverDto workReceiverDto = new WorkReceiverDto();
                workReceiverDto = commonService.getUserIdByCondition(sbge
                        .getProductTypeId(), null, null, 5);
                WorkEntity work = new WorkEntity();
                /** 销售总监事务 **/
                // 事务数量
                work.setCount(1);
                // 事务编号
                work.setWorkId(Constants.SELL12);
                // 事务接收人
                work.setUserId(workReceiverDto.getUserIdByRoleId(5));
                log.debug("事务接收人:"+work.getUserId());
                // 执行事务更新
                result = todoWorkService.addTodoWorks(work);
                if (!result) {
                    throw new Exception();
                }
                result = true;
            }
            // 退货金额
            sbge.setMoney(thmony.toString());
            // 退货单表
            dao.insert("returnGoods_sqlMap.insertReturnGoods", sbge);
            // 退货单明细
            SellBackGoodsDetailEntity sbgde = new SellBackGoodsDetailEntity();
            // 退货单号
            sbgde.setSellBackGoodsId(sbge.getId());
            dao.startBatch();
            for (int j = 0; j < thcount.length; j++) {
                if (thcount[j] != 0) {
                    // 产品编号
                    sbgde.setProductId(productId[j]);
                    // 退货数
                    sbgde.setCount(thcount[j]);
                    // 退货明细表
                    dao.insert("returnGoods_sqlMap.insertReturnGoodsDetail", sbgde);
                }
            }
            dao.executeBatch();
            dao.commitTransaction();
        } catch (Exception e) {
            result = false;
            log.error("新建退货单出错！ ", e);
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.error("退货单事物回滚,发生错误:", e);
            }
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @seecn.com.thtf.egov.cms.iservice.sell.ISaleReturnsService#
     * modifySaleReturnGoodsAudit
     * (cn.com.thtf.egov.cms.entity.SellBackGoodsEntity,
     * cn.com.thtf.egov.cms.entity.UserEntity)
     */
    public Boolean modifySaleReturnGoodsAudit(SellBackGoodsEntity sellBackGoodsE,
            UserEntity user) {
        Boolean result = false;
        Integer updateResult = 0;
        try {
            dao.beginTransaction();
            updateResult = dao.update("returnGoods_sqlMap.modifySaleReturnGoodsAudit",
                    sellBackGoodsE);
            if (updateResult == 0) {
                throw new Exception();
            }
            // 插入待办事务
            if (updateResult != 0) {
                // 获取bean容器
                todoWorkService = (ITodoWorkService) Container
                        .getBean("todoWrokServiceImpl");
                commonService = (ICommonService) Container.getBean("commonServiceImpl");
                WorkEntity work = new WorkEntity();
                if (sellBackGoodsE.getStatus() == 3) {
                    /** 减销售总监事务 **/
                    // 事务数量
                    work.setCount(-1);
                    // 事务编号
                    work.setWorkId(Constants.SELL12);
                    // 事务接收人
                    work.setUserId(user.getId());
                    // 执行事务更新
                    result = todoWorkService.addTodoWorks(work);
                    if (!result) {
                        throw new Exception();
                    }
                } else if (sellBackGoodsE.getStatus() == 4) {
                    /** 减销售总监事务 + 运营总监事务 **/
                    // 事务数量
                    work.setCount(-1);
                    // 事务编号
                    work.setWorkId(Constants.SELL12);
                    // 事务接收人
                    work.setUserId(user.getId());
                    // 执行事务更新
                    result = todoWorkService.addTodoWorks(work);
                    if (!result) {
                        throw new Exception();
                    }
                    result = false;
                    // 获取运营总监ID
                    WorkReceiverDto workReceiverDto = new WorkReceiverDto();
                    workReceiverDto = commonService.getUserIdByCondition(null, null,
                            null, 17);
                    // 事务数量
                    work.setCount(1);
                    // 事务编号
                    work.setWorkId(Constants.SELL12);
                    // 事务接收人
                    work.setUserId(workReceiverDto.getUserIdByRoleId(17));
                    // 执行事务更新
                    result = todoWorkService.addTodoWorks(work);
                    if (!result) {
                        throw new Exception();
                    }
                } else if (sellBackGoodsE.getStatus() == 5
                        || sellBackGoodsE.getStatus() == 6) {
                    // 减运营总监事务
                    // 事务数量
                    work.setCount(-1);
                    // 事务编号
                    work.setWorkId(Constants.SELL12);
                    // 事务接收人
                    work.setUserId(user.getId());
                    // 执行事务更新
                    result = todoWorkService.addTodoWorks(work);
                    if (!result) {
                        throw new Exception();
                    }
                }
                // 以上操作一切通过
                result = true;
            }
            dao.commitTransaction();
        } catch (Exception e) {
            result = false;
            log.error("update sell_back_goods audit error!", e);
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.error("退货单评审回滚,发生错误:", e);
            }
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.sell.ISaleReturnsService#modifyReturnsGoods
     * (cn.com.thtf.egov.cms.entity.SellBackGoodsEntity, java.lang.Integer[])
     */
    @SuppressWarnings("unchecked")
    public Boolean modifyReturnsGoods(SellBackGoodsEntity sbge, Integer[] thcount,
            Integer[] productId) {
        log.debug("修改退货单service");
        boolean result = true;
        List<NewReturnsProductDto> list = new ArrayList<NewReturnsProductDto>();
        Map<String, String> map = new HashMap<String, String>();
        map.put("sendGoodsId", sbge.getSendGoodsId());
        map.put("backGoodsId", sbge.getId());
        // Integer[] productId = new Integer[thcount.length];
        BigDecimal thmony = new BigDecimal(0);
        BigDecimal pdMoney0 = new BigDecimal(0);
        BigDecimal pdMoney1 = new BigDecimal(0);
        BigDecimal pdMoney2 = new BigDecimal(0);
        try {
            dao.beginTransaction();
            list = dao.queryForlist("returnGoods_sqlMap.selectNewReturnsProductList1",
                    map);
            int i = 0;
            for (NewReturnsProductDto nrpd : list) {
                for (int q = 0; q < productId.length; q++) {
                    if (!(nrpd.getProductId().equals(productId[q]))) {
                        continue;
                    }
                    log.debug(thcount[q] + "退货数");
                    if (thcount[q] != 0) {
                        pdMoney1 = nrpd.getPrice().multiply(
                                new BigDecimal(nrpd.getFfcount())).subtract(
                                nrpd.getZdmoney()).subtract(
                                nrpd.getPrice().multiply(
                                        new BigDecimal(nrpd.getyBackCount())));
                        log.debug(pdMoney1 + "条件一");
                        pdMoney0 = nrpd.getPrice().multiply(
                                new BigDecimal(nrpd.getFfcount())).subtract(
                                nrpd.getKpmoney()).subtract(
                                nrpd.getPrice().multiply(
                                        new BigDecimal(nrpd.getyBackCount())));
                        log.debug(pdMoney0 + "条件二");
                        pdMoney2 = nrpd.getPrice().multiply(new BigDecimal(thcount[i]));
                        log.debug(pdMoney2 + "退货金额");
                        if (pdMoney1.compareTo(pdMoney2) < 0
                                || pdMoney0.compareTo(pdMoney2) < 0) {
                            log.debug("验证条件不符合!");
                            throw new Exception();
                        }
                        thmony = thmony.add(pdMoney2);
                        // productId[i] = nrpd.getProductId();
                        // log.debug(productId[i] + "产品编号");
                        i++;
                    }
                }
            }
            if(sbge.getStatus() == 2){
                // 获取bean容器
                log.debug("产品分类："+sbge.getProductTypeId());
                todoWorkService = (ITodoWorkService) Container
                        .getBean("todoWrokServiceImpl");
                commonService = (ICommonService) Container.getBean("commonServiceImpl");
                WorkReceiverDto workReceiverDto = new WorkReceiverDto();
                workReceiverDto = commonService.getUserIdByCondition(sbge
                        .getProductTypeId(), null, null, 5);
                WorkEntity work = new WorkEntity();
                /** 销售总监事务 **/
                // 事务数量
                work.setCount(1);
                // 事务编号
                work.setWorkId(Constants.SELL12);
                // 事务接收人
                work.setUserId(workReceiverDto.getUserIdByRoleId(5));
                log.debug("事务接收人:"+work.getUserId());
                // 执行事务更新
                result = todoWorkService.addTodoWorks(work);
                if (!result) {
                    throw new Exception();
                }
                result = true;
            }
            // 退货金额
            sbge.setMoney(thmony.toString());
            // 修改退货单表
            if (sbge.getStatus() == 2) {
                dao.update("returnGoods_sqlMap.updateAudtoBackGoods", sbge);
            }
            dao.update("returnGoods_sqlMap.modifyReturnGoods", sbge);
            // 删除退货单明细
            dao.delete("returnGoods_sqlMap.deleteReturnsGoodDetail", sbge.getId());
            // 退货单明细
            SellBackGoodsDetailEntity sbgde = new SellBackGoodsDetailEntity();
            // 退货单号
            sbgde.setSellBackGoodsId(sbge.getId());
            for (int j = 0; j < thcount.length; j++) {
                if (thcount[j] != 0) {
                    // 产品编号
                    sbgde.setProductId(productId[j]);
                    // 退货数
                    sbgde.setCount(thcount[j]);
                    // 新增退货明细表
                    dao.insert("returnGoods_sqlMap.insertReturnGoodsDetail", sbgde);
                }
            }
            dao.commitTransaction();
        } catch (Exception e) {
            result = false;
            log.error("修改退货单失败！ ", e);
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.error("退货单事物回滚,发生错误:", e);
            }
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.sell.ISaleReturnsService#deleteReturnGoodsSale
     * (java.lang.String)
     */
    public Boolean deleteReturnGoodsSale(String id) {
        boolean result = true;
        try {
            dao.delete("returnGoods_sqlMap.deleteReturnsGood", id);
        } catch (Exception e) {
            result = false;
            log.error("delete sell_back_goods error!", e);
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @seecn.com.thtf.egov.cms.iservice.sell.ISaleReturnsService#
     * modifySaleReturnsProduct(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public List<NewReturnsProductDto> modifySaleReturnsProduct(String sendid,
            String backGoodsId) {
        log.debug("退货单查询产品信息--修改--查看");
        List<NewReturnsProductDto> list = new ArrayList<NewReturnsProductDto>();
        Map<String, String> map = new HashMap<String, String>();
        map.put("backGoodsId", backGoodsId);
        map.put("sendGoodsId", sendid);
        try {
            list = dao.queryForlist("returnGoods_sqlMap.viewSelectReturnGoods", map);
        } catch (Exception e) {
            log.error("查询产品信息出错!", e);
        }
        return list;
    }
}
