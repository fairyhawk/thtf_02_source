/**
 * ClassName  ReceiveInvoiceServiceImpl
 *
 * History
 * Create User: ChenHuajiang
 * Create Date: 2010-6-9
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.serviceimpl.invoice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.BaseService;
import cn.com.thtf.egov.cms.application.NewIDao;
import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.dto.InStockProdDetailDto;
import cn.com.thtf.egov.cms.dto.ReceiveInvoiceDto;
import cn.com.thtf.egov.cms.entity.ReceiveInvoiceDetailEntity;
import cn.com.thtf.egov.cms.form.InStockSearchForm;
import cn.com.thtf.egov.cms.form.ReceiveInvoiceForm;
import cn.com.thtf.egov.cms.iservice.invoice.IReceiveInvoiceService;

/**
 * 收票管理
 * 
 * @author ChenHuajiang
 */

public class ReceiveInvoiceServiceImpl extends BaseService implements
        IReceiveInvoiceService {
    /** log */
    private static Logger log = LoggerFactory.getLogger(ReceiveInvoiceServiceImpl.class);
    /** NewIDao */
    private NewIDao dao;

    public NewIDao getDao() {
        return dao;
    }

    public void setDao(NewIDao dao) {
        this.dao = dao;
    }

    /*
     * 收票管理列表
     */
    @SuppressWarnings("unchecked")
    @Override
    public List getReceiveInvoiceList(ReceiveInvoiceForm param, NewPage page) {
        log.info("获取收票管理列表信息");
        List list = null;
        try {
            // 分页处理
            int pageNum = page.getThisPage();
            int pageSize = page.getPageSize();
            if (pageNum == 1) {
                param.setFromLimit(0);
            } else {
                param.setFromLimit(pageSize * (pageNum - 1));
            }
            param.setToLimit(pageSize);

            list = queryRecords(dao, "receiveInvoice.selectReceiveInvoice", param, page);
        } catch (Exception e) {
            log.error("获取收票管理列表信息失败" + e);
        }
        return list;
    }

    /* 产品分类 */
    @SuppressWarnings("unchecked")
    @Override
    public List getProdTypeDeptList(String userId) {
        log.info("获取产品分类");
        HashMap map = new HashMap();
        map.put("userId", userId);
        List list = null;
        try {
            list = dao.queryForlist("productType.selectProductTypeByUserId", map);
        } catch (Exception e) {
            log.error("获取产品分类失败" + e);
        }
        return list;
    }

    /* 供应商信息 */
    @SuppressWarnings("unchecked")
    @Override
    public List getSuppliers(String name, NewPage page) {
        log.info("获取供应商信息");
        HashMap map = new HashMap();
        map.put("supplierName", name);
        List list = null;
        try {
            list = queryRecords(dao, "invoiceSupplier.selectAllSuppliers", map, page);
        } catch (Exception e) {
            log.error("获取供应商信息失败" + e);
        }
        return list;
    }

    /*
     * 采购发票录入
     */
    @Override
    public Integer addReceiveInvoice(ReceiveInvoiceForm param) {
        log.error("采购发票录入");
        Integer intCnt = 0;
        try {
            dao.insert("invoiceInsert.insertReceiveInvoice", param);
            intCnt = 1;
        } catch (Exception e) {
            log.error("采购发票录入失败", e);
        }
        return intCnt;
    }

    /* 采购发票信息 */
    @Override
    public ReceiveInvoiceDto getInvoiceInfoById(String id) {
        log.info("获取采购发票信息");
        ReceiveInvoiceDto dto = null;
        try {
            dto = (ReceiveInvoiceDto) dao.queryForObject(
                    "invoiceInfo.selectInvoiceInfoById", id);
        } catch (Exception e) {
            log.error("获取采购发票信息失败" + e);
        }
        return dto;
    }

    /* 入库明细选择 */
    @SuppressWarnings("unchecked")
    @Override
    public List getInStockProdList(InStockSearchForm param, NewPage page) {
        log.info("获取入库明细选择");
        List list = null;
        try {
            list = queryRecords(dao, "inStockDetail.selectInStockProd", param, page);
        } catch (Exception e) {
            log.error("获取入库明细选择失败" + e);
        }
        return list;
    }

    /* 采购发票查看 */
    @SuppressWarnings("unchecked")
    @Override
    public List getInvoiceProdList(String id) {
        log.info("采购发票查看");
        List list = null;
        try {
            list = dao.queryForlist("invoiceView.selectInvoiceInfo", id);
        } catch (Exception e) {
            log.error("采购发票查看失败" + e);
        }
        return list;
    }

    /* 根据收货发票ID获取记录数 */

    @Override
    public Integer getRecordCountById(String id) {
        log.info("根据收货发票ID获取记录数");
        Integer intCnt = 0;
        try {
            intCnt = dao.getCount("reInvoiceDetail.getRecordCountById", id);
        } catch (Exception e) {
            log.error("根据收货发票ID获取记录数失败" + e);
        }
        return intCnt;
    }

    /* 根据收货发票ID删除记录 */
    @Override
    public Integer delRecordById(String id) {
        log.info("根据收货发票ID删除记录");
        Integer intCnt = 0;
        try {
            intCnt = dao.delete("del.deleteRecordById", id);
        } catch (Exception e) {
            log.error("根据收货发票ID删除记录失败" + e);
        }
        return intCnt;
    }

    /* 根据收货发票ID更新记录 (退票) */
    @SuppressWarnings("unchecked")
    @Override
    public Integer modifyRecordById(String id) {
        log.info("根据收货发票ID更新记录(退票)");
        Integer intCnt = 0;
        try {
            dao.beginTransaction();
            intCnt = dao.update("update.updateRecordById", id);
            // 得到该发票原来所指定的各产品信息
            List<InStockProdDetailDto> ispds = dao.queryForlist(
                    "invoiceView.selReceiveInvoiceCount", id);
            dao.startBatch();// 执行批处理
            // 此处主要用作处理用户再次指定时所删除的产品
            for (int x = 0; x < ispds.size(); x++) {// 循环未移除的产品
                InStockProdDetailDto ispd = ispds.get(x);// 得到入库选择明细
                if (ispd.getInvoiceType().intValue() != 1) {// 1表示为增值税
                    break;
                }
                // 合同税率金额= 入库数量*合同价格/(1+合同税率)
                BigDecimal buyMoney = ((new BigDecimal(ispd.getReceiveInvoiceCount()))
                        .multiply(new BigDecimal(ispd.getBuyUnitPrice()))).divide(
                        new BigDecimal(1 + (new Double(ispd.getTaxRate())) / 100), 10,
                        BigDecimal.ROUND_HALF_UP);
                // 发票税率金额= 入库数量*合同价格/(1+发票税率)
                BigDecimal retMonety = ((new BigDecimal(ispd.getReceiveInvoiceCount()))
                        .multiply(new BigDecimal(ispd.getBuyUnitPrice()))).divide(
                        new BigDecimal(1 + (new Double(ispd.getReceiveRate())) / 100),
                        10, BigDecimal.ROUND_HALF_UP);
                // 当前的库存金额=现有库存金额+合同税率金额-发票税率金额
                BigDecimal money = buyMoney.subtract(retMonety);
                Map map = new HashMap();
                map.put("money", money.doubleValue());
                map.put("id", ispd.getProdId());
                dao.update("invoiceView.upProduct", map);// 修改库存金额
            }
            dao.executeBatch();
            dao.commitTransaction();
        } catch (Exception e) {
            log.error("根据收货发票ID更新记录(退票)失败" + e);
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.error("添加发票明细失败:关闭事务异常—server  " + e);
            }
        }
        return intCnt;
    }

    /* 查找是否有相同发票号 */
    @Override
    public boolean isExistByNumber(String number) {
        log.info("查找是否有相同发票号");
        Integer intCnt = 0;
        try {
            intCnt = dao.getCount("invoice.getRecordCountByNumber", number);
        } catch (Exception e) {
            log.error("查找是否有相同发票号失败" + e);
        }
        if (intCnt > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 添加采购发票明细
     * 
     * @author HanHaiyun
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean addReciveInvoiceDetail(
            List<ReceiveInvoiceDetailEntity> receiveInvoiceDetails,
            BigDecimal receiveRate, Integer receiveType, String receiveInvoiceId) {
        log.info("添加发票明细【开始】");
        try {
            BigDecimal[] moneys = new BigDecimal[receiveInvoiceDetails.size()];
            dao.beginTransaction();
            // 得到该发票原来所指定的各产品信息
            List<InStockProdDetailDto> ispds = dao.queryForlist(
                    "invoiceView.selReceiveInvoiceCount", receiveInvoiceId);
            delReciveInvoiceDetailById(receiveInvoiceId);// 根据发票id删除发票明细
            dao.startBatch();// 执行批处理

            for (int i = 0; i < receiveInvoiceDetails.size(); i++) {
                // 发票指定明细
                ReceiveInvoiceDetailEntity ride = receiveInvoiceDetails.get(i);
                if (receiveType == 1) {// 1表示增值税
                    BigDecimal buyMoney = new BigDecimal(0);// 计算合同税率后的产品库存金额
                    BigDecimal retMonety = new BigDecimal(0);// 计算发票税率后的产品库存金额
                    boolean b = false;// 用于判断某产品是否已经指定过
                    moneys[i] = new BigDecimal(ride.getProdMoney());// 得到原来的金额
                    for (int j = 0; j < ispds.size(); j++) {
                        InStockProdDetailDto ispd = ispds.get(j);// 得到入库选择明细
                        // 如果入库单号和产品编号都相等 则说明该产品已经指定过
                        if ((new Integer(ispd.getProdId()).intValue() == ride
                                .getProductId().intValue())
                                && ispd.getInStockId().equals(ride.getInStockId())) {
                            // 合同税率金额= 入库数量（指定数量-已指定数量）*合同价格/(1+合同税率)
                            buyMoney = (((new BigDecimal(ride.getCount()))
                                    .subtract((new BigDecimal(ispd
                                            .getReceiveInvoiceCount())))).multiply(ride
                                    .getProdPrice())).divide(new BigDecimal(
                                    1 + (new Double(ride.getTaxRate())) / 100), 15,
                                    BigDecimal.ROUND_HALF_UP);
                            // 发票税率金额= 入库数量（指定数量-已指定数量）*合同价格/(1+发票税率)
                            retMonety = (((new BigDecimal(ride.getCount()))
                                    .subtract((new BigDecimal(ispd
                                            .getReceiveInvoiceCount())))).multiply(ride
                                    .getProdPrice())).divide(new BigDecimal(
                                    1 + receiveRate.doubleValue() / 100), 15,
                                    BigDecimal.ROUND_HALF_UP);
                            // 当前的库存金额=现有库存金额-合同税率金额+发票税率金额
                            ispds.remove(j);// 移除该产品信息
                            b = true;// 标记该产品已经指定
                            break;
                        }
                    }
                    if (!b) {
                        // 合同税率金额= 入库数量*合同价格/(1+合同税率)
                        buyMoney = ((new BigDecimal(ride.getCount())).multiply(ride// 1
                                // 1
                                .getProdPrice()).divide(new BigDecimal(1 + (new Double(
                                ride.getTaxRate())) / 100), 15, BigDecimal.ROUND_HALF_UP));
                        // 发票税率金额= 入库数量*合同价格/(1+发票税率)
                        retMonety = ((new BigDecimal(ride.getCount())).multiply(ride
                                .getProdPrice()).divide(new BigDecimal(1 + receiveRate
                                .doubleValue() / 100), 15, BigDecimal.ROUND_HALF_UP));
                    }
                    // 当前的库存金额=现有库存金额-合同税率金额+发票税率金额
                    moneys[i] = retMonety.subtract(buyMoney);
                    Map map = new HashMap();
                    map.put("money", moneys[i].doubleValue());
                    map.put("id", ride.getProductId());
                    dao.update("invoiceView.upProduct", map);// 修改库存金额
                }
                dao.insert("invoiceInsert.insertInvoiceDetail", ride);
            }
            if (receiveType == 1) {// 1表示增值税
                // 此处主要用作处理用户再次指定时所删除的产品
                for (int x = 0; x < ispds.size(); x++) {// 循环未移除的产品
                    InStockProdDetailDto ispd = ispds.get(x);// 得到入库选择明细
                    // 合同税率金额= 入库数量*合同价格/(1+合同税率)
                    BigDecimal buyMoney = ((new BigDecimal(ispd.getReceiveInvoiceCount()))
                            .multiply(new BigDecimal(ispd.getBuyUnitPrice()))).divide(
                            new BigDecimal(1 + (new Double(ispd.getTaxRate())) / 100),
                            15, BigDecimal.ROUND_HALF_UP);
                    // 发票税率金额= 入库数量*合同价格/(1+发票税率)
                    BigDecimal retMonety = ((new BigDecimal(ispd.getReceiveInvoiceCount()))
                            .multiply(new BigDecimal(ispd.getBuyUnitPrice()))).divide(
                            new BigDecimal(1 + receiveRate.doubleValue() / 100), 15,
                            BigDecimal.ROUND_HALF_UP);
                    // 当前的库存金额=现有库存金额+合同税率金额-发票税率金额
                    BigDecimal money = buyMoney.subtract(retMonety);
                    Map map = new HashMap();
                    map.put("money", money.doubleValue());
                    map.put("id", ispd.getProdId());
                    dao.update("invoiceView.upProduct", map);// 修改库存金额
                }
            }
            dao.executeBatch();
            dao.commitTransaction();
            log.info("添加发票明细【结束】—server");
            return true;
        } catch (Exception e) {
            log.error("添加发票明细【失败】—server  " + e);
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.error("添加发票明细失败:关闭事务异常—server  " + e);
                return false;
            }
        }
        return false;
    }

    /**
     * 删除采购发票明细
     * 
     * @author HanHaiyun
     */
    @Override
    public boolean delReciveInvoiceDetailById(String reciveInvoiceId) {
        log.info("删除发票明细【开始】—server");
        try {
            dao.delete("del.deleteReceiveInvoiceDetailByReceiveInfoiceId",
                    reciveInvoiceId);
            log.info("删除发票明细【结束】—server");
            return true;
        } catch (Exception e) {
            log.error("删除发票明细失败—server  " + e);
            return false;
        }
    }
}
