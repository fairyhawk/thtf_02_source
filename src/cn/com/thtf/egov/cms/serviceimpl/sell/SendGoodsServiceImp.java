/**
 * ClassName  SendGoodsServiceImp
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-4-19
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.serviceimpl.sell;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.BaseService;
import cn.com.thtf.egov.cms.application.NewIDao;
import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.constant.Constants;
import cn.com.thtf.egov.cms.dto.CreditLogicQueryDto;
import cn.com.thtf.egov.cms.dto.CreditLogicResultDto;
import cn.com.thtf.egov.cms.dto.CustomerCreditCommonDto;
import cn.com.thtf.egov.cms.dto.CustomerFundsDto;
import cn.com.thtf.egov.cms.dto.MreturnDto;
import cn.com.thtf.egov.cms.dto.ProductDto;
import cn.com.thtf.egov.cms.dto.SendGoodInfoDto;
import cn.com.thtf.egov.cms.dto.SendGoodsAddDto;
import cn.com.thtf.egov.cms.dto.SendGoodsListInfoDto;
import cn.com.thtf.egov.cms.dto.SendGoodsListQueryDto;
import cn.com.thtf.egov.cms.dto.SendgoodSauditPrepareDto;
import cn.com.thtf.egov.cms.dto.SendgoodViewSelectProductDto;
import cn.com.thtf.egov.cms.dto.SendgoodsProductSelectDto;
import cn.com.thtf.egov.cms.dto.WorkReceiverDto;
import cn.com.thtf.egov.cms.entity.BoxDetailEntity;
import cn.com.thtf.egov.cms.entity.CustomerCreditDetailEntity;
import cn.com.thtf.egov.cms.entity.CustomerCreditEntity;
import cn.com.thtf.egov.cms.entity.ProductTypeEntity;
import cn.com.thtf.egov.cms.entity.SendGoodsDetailEntity;
import cn.com.thtf.egov.cms.entity.SendGoodsEntity;
import cn.com.thtf.egov.cms.entity.StockEntity;
import cn.com.thtf.egov.cms.entity.StockroomEntity;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.entity.WorkEntity;
import cn.com.thtf.egov.cms.iservice.common.ICommonService;
import cn.com.thtf.egov.cms.iservice.credit.ICreditService;
import cn.com.thtf.egov.cms.iservice.inventory.IBoxService;
import cn.com.thtf.egov.cms.iservice.sell.ISendGoodsService;
import cn.com.thtf.egov.cms.iservice.work.ITodoWorkService;
import cn.com.thtf.egov.cms.util.Container;

/**
 * SendGoodsServiceImp
 * 
 * @author Lubo
 */

public class SendGoodsServiceImp extends BaseService implements ISendGoodsService {

    /** log */
    private static Logger log = LoggerFactory.getLogger(SendGoodsServiceImp.class);
    /** dao */
    private NewIDao dao;
    /** ICommonService */
    private ICommonService commonService;
    /** ICreditService */
    private ICreditService creditService;
    /** ITodoWorkService */
    private ITodoWorkService todoWorkService;

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.sell.ISendGoodsService#modifySendgoodStatus
     * (cn.com.thtf.egov.cms.dto.SendGoodsAddDto)
     */
    public boolean modifySendgoodStatus(SendGoodsAddDto para) {
        boolean result = false;
        try {
            dao.update("sendGoods_sqlMap.updateSendGoodsStatus", para);
            result = true;
        } catch (Exception e) {
            log.error("修改发货单状态发生错误!", e);
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.sell.ISendGoodsService#seveSendgoods(cn
     * .com.thtf.egov.cms.dto.SendGoodsAddDto)
     */
    public boolean seveSendgoods(SendGoodsAddDto para) {
        try {
            dao.beginTransaction();

            /* 如果是自提,不存储收货地址信息 */
            if (StringUtils.equals(para.getTransportWay(), "1")) {
                para.setCustomerAddressId("0");
                para.setCaAddress("");
                para.setCaLinkman("");
                para.setCaMobile("");
                para.setCaName("");
                para.setCaPostcode("");
                para.setCaTel("");
            }

            if (StringUtils.equals(para.getCaType(), "1")) {
                para.setCustomerAddressId("0");
            }
            if (StringUtils.isEmpty(para.getCustomerAddressId())) {
                para.setCustomerAddressId("0");
            }

            BigDecimal totPrice = new BigDecimal(0);
            for (int i = 0; i < para.getProductIdArr().length; i++) {
                totPrice = totPrice.add(new BigDecimal(para.getCountArr()[i])
                        .multiply(new BigDecimal(para.getMoneyArr()[i])));
            }
            para.setSendGoodsMoney(totPrice.toString());

            if (para.isPageType()) {
                // delete 明细
                dao.delete("sendGoods_sqlMap.deleteSendGoodsDetail", para.getId());
                // update 发货单
                dao.update("sendGoods_sqlMap.updateSendGoods", para);
            } else {
                /* 插入发货单表 */
                para.setExtendDays(Constants.EXTEND_EXCEED_DAYS);
                if (StringUtils.isBlank(para.getCustomerAddressId())) {
                    para.setCustomerAddressId(null);
                }
                dao.insert("sendGoods_sqlMap.insertSendgoods", para);
            }

            dao.startBatch();
            /* 插入发货单明细表 */
            SendGoodsAddDto detailPara = null;
            for (int i = 0; i < para.getProductIdArr().length; i++) {
                detailPara = new SendGoodsAddDto();
                detailPara.setId(para.getId());
                detailPara.setProductId(para.getProductIdArr()[i]);
                detailPara.setCount(para.getCountArr()[i]);

                // BigDecimal price = new
                // BigDecimal(para.getCountArr()[i]).multiply(new BigDecimal(
                // para.getMoneyArr()[i]));
                // detailPara.setPrice(price.toString());

                dao.insert("sendGoods_sqlMap.insertSendgoodsDetail", detailPara);
            }
            dao.executeBatch();
            dao.commitTransaction();

            return true;
        } catch (Exception e) {
            log.error("保存发货单,发生错误:", e);
            return false;
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e1) {
                log.error("保存发货单事物回滚,发生错误:", e1);
            }
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.sell.ISendGoodsService#addSendgoods(cn.
     * com.thtf.egov.cms.dto.SendGoodsAddDto)
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean addSendgoods(SendGoodsAddDto para) {

        try {
            commonService = (ICommonService) Container.getBean("commonServiceImpl");
            creditService = (ICreditService) Container.getBean("creditTypeServiceImp");
            /* 开始事物 */
            dao.beginTransaction();

            /* 判断是否是虚拟库,如果是虚拟库,则不需要判断库房管理员是否存在 */
            StockroomEntity stockroom = (StockroomEntity) dao.queryForObject(
                    "inventtory_sqlMap.getStockroomById", para.getStockroomId());

            if (stockroom.getType() != 0) {
                /* 检索是否存在对应的库房管理员,如果不存在,则返回 */
                WorkReceiverDto workReceiverResult = commonService.getUserIdByCondition(
                        para.getProductTypeId(), null,
                        Integer.parseInt(para.getStockroomId()));

                if (workReceiverResult == null) {
                    log.info("发货单:{},未找到库房管理员", para.getId());
                    return false;
                }
                SendGoodsEntity sendGoodsEntity = new SendGoodsEntity();
                sendGoodsEntity.setId(para.getId());
                sendGoodsEntity.setStkAdmId(workReceiverResult.getUserId());
                // 更新库房管理员ID
                dao.update("sendGoods_sqlMap.updateStockroom", sendGoodsEntity);
            }

            /* 获得客户信用记录,包括时间戳 */
            CustomerCreditCommonDto customerCredit = para.getCustomerCredit();

            /* 检索在回款记录,包括时间戳 */
            List<MreturnDto> returnDetailList = para.getReturnDetailList();
            /* 检索在在途款记录,包括时间戳 */
            List<MreturnDto> returnDetailInList = para.getReturnDetailInList();

            /* 检索在库存记录,包括时间戳 */
            List<StockEntity> stockList = para.getStockList();
            Map<Integer, StockEntity> stockMap = new HashMap<Integer, StockEntity>();
            for (StockEntity stockEntity : stockList) {
                stockMap.put(stockEntity.getProductId(), stockEntity);
            }

            /* 检索本合同本库房备货单 */
            SendGoodsAddDto reserveProductPara = new SendGoodsAddDto();
            reserveProductPara.setSellContractId(para.getSellContractId());
            reserveProductPara.setStockroomId(para.getStockroomId());
            List<SendgoodsProductSelectDto> reserveProductList = dao.queryForlist(
                    "sendGoods_sqlMap.selectReserveProduct", reserveProductPara);

            Map<Integer, SendgoodsProductSelectDto> bhMap = new HashMap<Integer, SendgoodsProductSelectDto>();
            for (SendgoodsProductSelectDto reslutDto : reserveProductList) {
                bhMap.put(Integer.parseInt(reslutDto.getProductId()), reslutDto);
            }

            /*
             * 更新库存
             */
            for (int i = 0; i < para.getProductIdArr().length; i++) {
                /* 发货产品Id,和发货数 */
                Integer productId = Integer.parseInt(para.getProductIdArr()[i]);
                Integer productCount = Integer.parseInt(para.getCountArr()[i]);

                /* 更新库存表参数 */
                StockEntity updateStockPara = new StockEntity();
                updateStockPara.setProductId(productId);
                updateStockPara.setStockroomId(Integer.parseInt(para.getStockroomId()));
                updateStockPara.setTimeStamp(stockMap.get(productId).getTimeStamp());
                /* 发货冻结数 */
                updateStockPara.setSendLock(stockMap.get(productId).getSendLock()
                        + productCount);

                /* 判断此产品是否存在备货 */
                if (bhMap.get(productId) != null) {
                    /* 备货数 */
                    Integer prepared = 0;

                    /*
                     * 如果此产品 发货数大于等于本库房备货数 那么: 库存表备货数-=本库房备货数,库存表冻结数+=发货数
                     * 并删除所有此产品的备货单明细
                     */
                    if (productCount >= bhMap.get(productId).getBkfNum()) {

                        /* 删除此产品的备货单明细 */
                        SendGoodsDetailEntity delPara = new SendGoodsDetailEntity();
                        delPara.setProductId(Integer.parseInt(para.getProductIdArr()[i]));
                        delPara.setSendGoodsId(bhMap.get(productId).getSendGoodsId());
                        dao.delete("sendGoods_sqlMap.deleteSendGoodsDetailByCondition",
                                delPara);
                        /* 判断备货单是否还有备货产品 */
                        int cnt = (Integer) dao.queryForObject(
                                "sendGoods_sqlMap.selectSendGoodsDetail",
                                bhMap.get(productId).getSendGoodsId());
                        if (cnt == 0) {
                            dao.delete("sendGoods_sqlMap.deleteSendGood",
                                    bhMap.get(productId).getSendGoodsId());
                        }
                        /* 计算冻结数和备货数 */
                        prepared = stockMap.get(productId).getPrepared()
                                - bhMap.get(productId).getBkfNum();

                    } else {
                        /* 更新备货单备货数 */
                        SendGoodsDetailEntity sendDetPara = new SendGoodsDetailEntity();
                        sendDetPara.setId(bhMap.get(productId).getId());
                        sendDetPara.setCount(bhMap.get(productId).getBkfNum()
                                - productCount);
                        dao.update("sendGoods_sqlMap.updateCount", sendDetPara);

                        /* 更新备货金额 */
                        SendGoodsEntity sendGoodsEntity = new SendGoodsEntity();
                        sendGoodsEntity.setId(bhMap.get(productId).getSendGoodsId());
                        SendGoodsEntity preparedMoneyResult = (SendGoodsEntity) dao
                                .queryForObject("sendGoods_sqlMap.selectPreparedMoney",
                                        sendGoodsEntity);

                        dao.update("sendGoods_sqlMap.updatePreparedMoney",
                                preparedMoneyResult);

                        /* 计算冻结数和备货数 */
                        prepared = stockMap.get(productId).getPrepared() - productCount;
                    }

                    /* 更新库存 */
                    updateStockPara.setPrepared(prepared);

                    int updateResult = dao.update(
                            "inventtory_sqlMap.updateSendLockAndPrepared",
                            updateStockPara);
                    if (updateResult != 1) {
                        log.info("发货单:{},库存已变更!", para.getId());
                        return false;
                    }
                } else {
                    /* 如果本库房不存在备货 */
                    int updateResult = dao.update("inventtory_sqlMap.updateSendLock",
                            updateStockPara);
                    if (updateResult != 1) {
                        log.info("发货单:{},库存已变更!", para.getId());
                        return false;
                    }
                }
            }

            /*
             * 插入在途明细 (包括回款和在途款)
             */

            /* 封装产品数据 */
            ConcurrentLinkedQueue<ProductDto> queue = getProductQueryToDistribution(
                    para.getProductIdArr(), para.getCountArr(), para.getMoneyArr());

            /* 存储使用的回款时间戳,统一更新 */
            Map<String, Timestamp> timeStampMap = new HashMap<String, Timestamp>();
            MreturnDto mreturnPara = null;

            /* 使用回款 */
            int numK = 0;
            for (int k = 0; k < returnDetailList.size(); k = numK) {
                BigDecimal diff = null;

                /* 判断是否有产品未分配 */
                if (!queue.isEmpty()) {
                    ProductDto thisProduct = queue.peek();
                    BigDecimal thisMoney = thisProduct.getMoney();

                    /* 如果产品金额大于等于回款金额 */
                    if (thisMoney.compareTo(returnDetailList.get(k).getMoney()) >= 0) {
                        /* 删除回款明细 指定到合同的记录 */
                        mreturnPara = new MreturnDto();
                        mreturnPara.setDetailId(returnDetailList.get(k).getDetailId());
                        dao.delete("mreturn_sqlMap.deleteReturnDetail", mreturnPara);
                        /* 减少此产品需使用金额 */
                        queue.peek().setMoney(
                                thisMoney.subtract(returnDetailList.get(k).getMoney()));
                        /* 计算指定到明细的金额 */
                        diff = returnDetailList.get(k).getMoney();
                    } else {
                        /* 更新回款明细 指定到合同的金额 */
                        mreturnPara = new MreturnDto();
                        mreturnPara.setDetailId(returnDetailList.get(k).getDetailId());
                        mreturnPara.setMoney(returnDetailList.get(k).getMoney()
                                .subtract(thisMoney));
                        dao.update("mreturn_sqlMap.updateReturnDetailMoney", mreturnPara);

                        /* 计算指定到明细的金额 */
                        diff = thisMoney;

                        /* 从队列中移除产品 */
                        queue.poll();

                        MreturnDto surplus = returnDetailList.get(k);
                        surplus.setMoney(returnDetailList.get(k).getMoney()
                                .subtract(thisMoney));

                        /* 下一产品继续使用本回款 */
                        numK--;
                    }

                    /* 插入回款明细 指定到发货单产品 */
                    mreturnPara = new MreturnDto();
                    mreturnPara.setId(returnDetailList.get(k).getId());
                    mreturnPara.setAppointType("4");
                    mreturnPara.setSendGoodsId(para.getId());
                    mreturnPara.setProductId(thisProduct.getProductId());
                    mreturnPara.setMoney(diff);
                    dao.insert("mreturn_sqlMap.insertReturnDetail", mreturnPara);

                    /* 记录回款时间戳 */
                    timeStampMap.put(returnDetailList.get(k).getId(), returnDetailList
                            .get(k).getTimeStamp());

                    numK++;
                } else {
                    break;
                }
            }

            /* 使用在途款 */
            int numZ = 0;
            for (int z = 0; z < returnDetailInList.size(); z = numZ) {
                BigDecimal diff = null;

                /* 判断是否有产品未分配 */
                if (!queue.isEmpty()) {
                    ProductDto thisProduct = queue.peek();
                    BigDecimal thisMoney = thisProduct.getMoney();

                    /* 如果产品金额大于等于回款金额 */
                    if (thisMoney.compareTo(returnDetailInList.get(z).getMoney()) >= 0) {
                        /* 删除回款明细 指定到合同的记录 */
                        mreturnPara = new MreturnDto();
                        mreturnPara.setDetailId(returnDetailInList.get(z).getDetailId());
                        dao.delete("mreturn_sqlMap.deleteReturnDetail", mreturnPara);
                        /* 减少此产品需使用金额 */
                        queue.peek().setMoney(
                                thisMoney.subtract(returnDetailInList.get(z).getMoney()));
                        /* 计算指定到明细的金额 */
                        diff = returnDetailInList.get(z).getMoney();
                    } else {
                        /* 更新回款明细 指定到合同的金额 */
                        mreturnPara = new MreturnDto();
                        mreturnPara.setDetailId(returnDetailInList.get(z).getDetailId());
                        mreturnPara.setMoney(returnDetailInList.get(z).getMoney()
                                .subtract(thisMoney));
                        dao.update("mreturn_sqlMap.updateReturnDetailMoney", mreturnPara);

                        /* 计算指定到明细的金额 */
                        diff = thisMoney;

                        /* 从队列中移除产品 */
                        queue.poll();

                        MreturnDto surplus = returnDetailInList.get(z);
                        surplus.setMoney(returnDetailInList.get(z).getMoney()
                                .subtract(thisMoney));

                        /* 下一产品继续使用本回款 */
                        numZ--;
                    }

                    /* 插入客户信用明细 */
                    CustomerCreditDetailEntity creditInsertPara = new CustomerCreditDetailEntity();
                    creditInsertPara.setCustomerCreditId(para.getCustomerCreditId());
                    creditInsertPara.setMoney(diff);
                    creditInsertPara.setSendGoodsId(para.getId());
                    creditInsertPara.setProductId(Integer.parseInt(thisProduct
                            .getProductId()));

                    boolean result = creditService
                            .modifyCustomerCreditDetail(creditInsertPara);
                    if (!result) {
                        return false;
                    }

                    /* 插入回款明细 指定到发货单产品 */
                    mreturnPara = new MreturnDto();
                    mreturnPara.setId(returnDetailInList.get(z).getId());
                    mreturnPara.setAppointType("4");
                    mreturnPara.setSendGoodsId(para.getId());
                    mreturnPara.setProductId(thisProduct.getProductId());
                    mreturnPara.setMoney(diff);

                    dao.insert("mreturn_sqlMap.insertReturnDetail", mreturnPara);

                    /* 记录回款时间戳 */
                    timeStampMap.put(returnDetailInList.get(z).getId(),
                            returnDetailInList.get(z).getTimeStamp());

                    numZ++;
                } else {
                    break;
                }
            }

            /* 更新回款表时间戳 */
            Set<String> set = timeStampMap.keySet();
            Iterator<String> iterator = set.iterator();
            /* set method */
            while (iterator.hasNext()) {
                String key = iterator.next();

                mreturnPara = new MreturnDto();
                mreturnPara.setId(key);
                mreturnPara.setTimeStamp(timeStampMap.get(key));

                int updateResult = dao.update("mreturn_sqlMap.updateMReturnTimestamp",
                        mreturnPara);
                if (updateResult != 1) {
                    log.info("发货单:{},回款已变更!", para.getId());
                    return false;
                }
            }

            /*
             * 插入客户信用明细表
             */
            while (!queue.isEmpty()) {
                ProductDto thisProduct = queue.poll();

                CustomerCreditDetailEntity creditDetail = new CustomerCreditDetailEntity();
                creditDetail.setSendGoodsId(para.getId());
                creditDetail.setProductId(Integer.parseInt(thisProduct.getProductId()));
                creditDetail.setCustomerCreditId(para.getCustomerCreditId());

                creditDetail.setMoney(thisProduct.getMoney());

                boolean result = creditService.modifyCustomerCreditDetail(creditDetail);
                if (!result) {
                    return false;
                }
            }

            /* 更新客户信用表时间戳 如果时间戳已变 则事物回滚 */
            CustomerCreditEntity creditUpdatePara = new CustomerCreditEntity();
            creditUpdatePara.setId(Integer.parseInt(customerCredit.getCustomerCrdId()));
            creditUpdatePara.setTimeStamp(customerCredit.getTimeStamp());
            boolean result = creditService
                    .modifyCustomerCreditTimeStamp(creditUpdatePara);
            if (!result) {
                log.info("发货单:{},信用已变更!", para.getId());
                return false;
            }

            /* 如果是虚拟库 则进行虚出 */
            if (stockroom.getType() == 0) {
                IBoxService boxService = (IBoxService) Container.getBean("boxServiceImp");
                boolean completeResult = boxService.completeSendGoodsFalse(para);
                if (!completeResult) {
                    log.info("发货单:{},虚出失败！", para.getId());
                    return false;
                }
            }

            dao.commitTransaction();
            return true;
        } catch (Exception e) {
            log.info("提交发货单:{},发生错误", para.getId());
            log.error("提交发货单,发生错误:", e);
            return false;
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e1) {
                log.info("提交发货单:{},事物回滚发生错误", para.getId());
                log.error("提交发货单事物回滚,发生错误:", e1);
            }
        }

    }

    /**
     * 封装产品List
     * 
     * @param productIdArr
     * @param countArr
     * @param moneyArr
     * @return
     */
    private ConcurrentLinkedQueue<ProductDto> getProductQueryToDistribution(
            String[] productIdArr, String[] countArr, String[] moneyArr) {
        ConcurrentLinkedQueue<ProductDto> result = new ConcurrentLinkedQueue<ProductDto>();
        ProductDto product = null;
        for (int i = 0; i < productIdArr.length; i++) {
            product = new ProductDto();
            product.setProductId(productIdArr[i]);
            product.setCount(countArr[i]);
            product.setPrice(moneyArr[i]);
            product.setMoney(new BigDecimal(moneyArr[i]).multiply(new BigDecimal(
                    countArr[i])));
            result.add(product);
        }

        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.sell.ISendGoodsService#checkStockNum(java
     * .lang.String, java.lang.String, java.lang.String[])
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean checkStockNum(String stockroomId, String sellContractId,
            String[] productIdArr, String[] countArr) {
        boolean checkResult = true;

        /* 获取库存数 */
        List<SendgoodsProductSelectDto> reslut = getProductListBySrIdAndScId(stockroomId,
                sellContractId);

        Map<String, Integer> map = new HashMap<String, Integer>();
        for (SendgoodsProductSelectDto reslutDto : reslut) {
            map.put(reslutDto.getProductId(), reslutDto.getFhkyNum());
        }

        /* 检索本合同本库房备货单 */
        SendGoodsAddDto reserveProductPara = new SendGoodsAddDto();
        reserveProductPara.setSellContractId(sellContractId);
        reserveProductPara.setStockroomId(stockroomId);
        List<SendgoodsProductSelectDto> reserveProductList = null;
        try {
            reserveProductList = dao.queryForlist(
                    "sendGoods_sqlMap.selectReserveProduct", reserveProductPara);
        } catch (Exception e) {
            log.error("取当前发货单备货数异常", e);
            return false;
        }

        Map<Integer, SendgoodsProductSelectDto> bhMap = new HashMap<Integer, SendgoodsProductSelectDto>();
        for (SendgoodsProductSelectDto reslutDto : reserveProductList) {
            bhMap.put(Integer.parseInt(reslutDto.getProductId()), reslutDto);
        }

        for (int i = 0; i < productIdArr.length; i++) {
            int bhNum = 0;
            if (bhMap.get(Integer.parseInt(productIdArr[i])) != null) {
                bhNum = bhMap.get(Integer.parseInt(productIdArr[i])).getBkfNum();
            }

            if (map.get(productIdArr[i]).intValue() + bhNum < Integer
                    .parseInt(countArr[i])) {
                checkResult = false;
                break;
            }
        }
        return checkResult;
    }

    /*
     * (non-Javadoc)
     * 
     * @seecn.com.thtf.egov.cms.iservice.sell.ISendGoodsService#
     * getProductListBySrIdAndScId(java.lang.String, java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<SendgoodsProductSelectDto> getProductListBySrIdAndScId(
            String stockroomId, String sellContractId) {
        List<SendgoodsProductSelectDto> reslut = null;
        SendGoodsEntity para = new SendGoodsEntity();
        para.setStockroomId(Integer.parseInt(stockroomId));
        para.setSellContractId(sellContractId);

        try {
            reslut = dao.queryForlist("sendGoods_sqlMap.selectProduct", para);
        } catch (Exception e) {
            log.error("根据销售合同ID和库房ID检索产品错误:", e);
        }

        return reslut;
    }

    /*
     * (non-Javadoc)
     * 
     * @seecn.com.thtf.egov.cms.iservice.sell.ISendGoodsService#
     * getProductListBySrIdAndScId(java.lang.String, java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<SendgoodsProductSelectDto> getReserveProductListBySrIdAndScId(
            String stockroomId, String sellContractId) {
        List<SendgoodsProductSelectDto> reslut = null;
        SendGoodsEntity para = new SendGoodsEntity();
        para.setStockroomId(Integer.parseInt(stockroomId));
        para.setSellContractId(sellContractId);

        try {
            reslut = dao.queryForlist("sendGoods_sqlMap.selectProduct", para);
        } catch (Exception e) {
            log.error("根据销售合同ID和库房ID检索产品错误:", e);
        }

        return reslut;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.common.ISendGoodsCreditLogicService#creditLogic
     * (cn.com.thtf.egov.cms.entity.SendGoodsEntity)
     */
    @Override
    public CreditLogicResultDto creditLogic(CreditLogicQueryDto para) {
        CreditLogicResultDto result = new CreditLogicResultDto();

        commonService = (ICommonService) Container.getBean("commonServiceImpl");

        /* 超期判断 */
        List<CustomerFundsDto> expiredResult = commonService.expiredLogic(para
                .getCustomerId());
        if (expiredResult.size() != 0) {
            log.debug("客户存在超期欠款,CustomerId:" + para.getCustomerId());
            result.setProductList(expiredResult);
        } else {
            /* 获取客户可用信用额度 */
            List<CustomerCreditCommonDto> customerCreditCommonList = commonService
                    .getCustomerCredit(para.getCustomerId().toString(), para
                            .getProductTypeId().toString(), para.getCustomerCreditId()
                            .toString());

            if (customerCreditCommonList == null || customerCreditCommonList.size() == 0) {
                log.error("获取客户信用错误");
                result.setErrorMsg("获取客户信用错误");
                return result;
            }

            CustomerCreditCommonDto customerCreditCommonDto = customerCreditCommonList
                    .get(0);

            result.setCustomerCreditCommonDto(customerCreditCommonDto);

            BigDecimal customerAc = customerCreditCommonDto.getFreeLimit();
            BigDecimal sendGoodsMoney = new BigDecimal(para.getSendGoodsMoney());

            /* 判断可用信用是否大于等于0(不考虑可用状态) */
            if (customerAc.doubleValue() >= 0) {
                /* 回款 指定到合同 */
                BigDecimal returnMoney = commonService.getSellContractResultMoney(
                        para.getSellContractId(), Constants.RETURN_DETAIL_TOCONTRACT);
                /* 在途 指定到合同 */
                BigDecimal inRransitMoney = commonService.getSellContractInRransit(
                        para.getSellContractId(), Constants.RETURN_DETAIL_TOCONTRACT);

                /* 回款 */
                BigDecimal returnMoneyToContract = returnMoney.add(inRransitMoney);

                /* 判断付款方式 */
                if (Constants.PAYMENT_WAY_CURRENT.equals(para.getPaymentWay())) {
                    /* 全部现结 */
                    /* 回(合)+在(合)>=发货金额 */
                    if (returnMoneyToContract.compareTo(sendGoodsMoney) >= 0) {
                        result.setAllow(true);

                        /* 计算使用的信用和回款 */
                        /* 如果发货金额大于回款,那么需要使用在途款 */
                        // if (sendGoodsMoney.compareTo(returnMoney) > 0) {
                        // result.setUseInRransit(sendGoodsMoney
                        // .subtract(returnMoney));
                        // result.setUseInRransit(true);
                        // }
                        /* 使用的回款 */
                        // result.setUseReturn(returnMoney);
                        // result.setUseReturn(true);
                    } else {
                        result.setErrorMsg("指定到合同的回款小于发货金额");
                        log.debug("指定到合同的回款小于发货金额");
                    }
                } else if (Constants.PAYMENT_WAY_CREDIT.equals(para.getPaymentWay())) {
                    /* 全部信用 */
                    /* 回款(合)>=发货金额 */
                    if (returnMoneyToContract.compareTo(sendGoodsMoney) >= 0) {
                        result.setAllow(true);

                        /* 计算使用的信用和回款 */
                        /* 如果发货金额大于回款,那么需要使用在途款 */
                        // if (sendGoodsMoney.compareTo(returnMoney) > 0) {
                        // result.setUseInRransit(sendGoodsMoney
                        // .subtract(returnMoney));
                        // result.setUseInRransit(true);

                        // }
                        /* 使用的回款 */
                        // result.setUseReturn(returnMoney);
                        // result.setUseReturn(true);

                    } else {
                        /* 信用是否启用状态 并且发货金额-回款(合)<=可用信用 */
                        if (StringUtils.equals(customerCreditCommonDto.getCreditStatus(),
                                "1")) {
                            if (sendGoodsMoney.subtract(returnMoneyToContract).compareTo(
                                    customerAc) <= 0) {
                                result.setAllow(true);

                                /* 计算使用的信用和回款 */
                                /* 计算使用的信用 */
                                // result.setUseCredit(sendGoodsMoney
                                // .subtract(returnMoneyToContract));
                                // result.setUseCredit(true);
                                /* 使用的回款 */
                                // result.setUseReturn(returnMoney);
                                // result.setUseReturn(true);
                                /* 计算使用的途款 */
                                // result.setUseInRransit(sendGoodsMoney.subtract(
                                // returnMoney).subtract(result.getUseCredit()));
                                // result.setUseInRransit(true);
                            } else {
                                result.setErrorMsg("客户信用不足");// (指定到合同的回款小于发货金额)
                                log.debug("客户信用不足(指定到合同的回款小于发货金额)");
                            }
                        } else {
                            result.setErrorMsg("客户信用不可用");// (指定到合同的回款小于发货金额)
                            log.debug("客户信用不可用(指定到合同的回款小于发货金额)");
                        }
                    }
                } else if (Constants.PAYMENT_WAY_CURRENT_ANDCREDIT.equals(para
                        .getPaymentWay())) {
                    /* 现结+信用 */
                    /* 回款 指定到明细 */
                    returnMoney = commonService.getSellContractResultMoney(
                            para.getSellContractId(), Constants.RETURN_DETAIL_TODETAIL);
                    /* 在途 指定到明细 */
                    inRransitMoney = commonService.getSellContractInRransit(
                            para.getSellContractId(), Constants.RETURN_DETAIL_TODETAIL);

                    BigDecimal returnMoneyToDetail = returnMoney.add(inRransitMoney);

                    /* 回款(合+明)>=现结金额 */
                    if (returnMoneyToContract.add(returnMoneyToDetail).compareTo(
                            new BigDecimal(para.getCashMoney())) >= 0) {

                        /* 发货金额<=回款(合) */
                        if (returnMoneyToContract.compareTo(sendGoodsMoney) >= 0) {
                            result.setAllow(true);

                            /* 计算使用的信用和回款 */
                            /* 如果发货金额大于回款,那么需要使用在途款 */
                            // if (sendGoodsMoney.compareTo(returnMoney) > 0) {
                            // result.setUseInRransit(sendGoodsMoney
                            // .subtract(returnMoney));
                            // result.setUseInRransit(true);
                            // }
                            /* 使用的回款 */
                            // result.setUseReturn(returnMoney);
                            // result.setUseReturn(true);
                        } else {
                            /* 信用是否启用状态 并且发货金额-回款(合)<=可用信用 */
                            if (StringUtils.equals(
                                    customerCreditCommonDto.getCreditStatus(), "1")) {
                                if (sendGoodsMoney.subtract(returnMoneyToContract)
                                        .compareTo(customerAc) <= 0) {
                                    result.setAllow(true);

                                    /* 计算使用的信用和回款 */
                                    /* 计算使用的信用 */
                                    // result.setUseCredit(sendGoodsMoney
                                    // .subtract(returnMoneyToContract));
                                    // result.setUseCredit(true);
                                    /* 使用的回款 */
                                    // result.setUseReturn(returnMoney);
                                    // result.setUseReturn(true);
                                    /* 计算使用的途款 */
                                    // result.setUseInRransit(sendGoodsMoney.subtract(
                                    // returnMoney).subtract(result.getUseCredit()));
                                    // result.setUseInRransit(true);
                                } else {
                                    result.setErrorMsg("客户信用不足");// (回款大于现结金额并且指定到合同的回款小于发货金额)
                                    log.debug("客户信用不可用(回款大于现结金额并且指定到合同的回款小于发货金额)");
                                }
                            } else {
                                result.setErrorMsg("客户信用不可用");// (回款大于现结金额并且指定到合同的回款小于发货金额)
                                log.debug("客户信用不可用(回款大于现结金额并且指定到合同的回款小于发货金额)");
                            }
                        }
                    } else {
                        /* 回款(合+明)>=发货金额 */
                        if (returnMoneyToContract.compareTo(sendGoodsMoney) >= 0) {
                            result.setAllow(true);

                            /* 计算使用的信用和回款 */
                            /* 如果发货金额大于回款,那么需要使用在途款 */
                            // if (sendGoodsMoney.compareTo(returnMoney) > 0) {
                            // result.setUseInRransit(sendGoodsMoney
                            // .subtract(returnMoney));
                            // result.setUseInRransit(true);
                            // }
                            /* 使用的回款 */
                            // result.setUseReturn(returnMoney);
                            // result.setUseReturn(true);
                        } else {
                            result.setErrorMsg("回款小于现结金额");// 并且指定到合同的回款小于发货金额
                            log.debug("回款小于现结金额(并且指定到合同的回款小于发货金额)");
                        }
                    }

                }
            } else {
                log.debug("客户信用为负:" + para.getCustomerId());
                result.setErrorMsg("客户信用为负");
            }
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.sell.ISendGoodsService#getSendGoodsList
     * (cn.com.thtf.egov.cms.application.NewPage,
     * cn.com.thtf.egov.cms.dto.SendGoodsListQueryDto,
     * cn.com.thtf.egov.cms.entity.UserEntity)
     */
    @SuppressWarnings("unchecked")
    public List getSendGoodsList(NewPage page, SendGoodsListQueryDto sendGoodListDto,
            UserEntity user) {
        sendGoodListDto.setUserid(user.getId());
        sendGoodListDto.setUserrole(user.getRoleId().toString());
        sendGoodListDto.setUserAreaId(user.getUserAreaId());
        List list = null;
        try {
            /* 传入查询参数，查询处根据条件并相匹配的数据存入List集合中 */
            list = queryRecords(dao, "querySendGoodsList.search", sendGoodListDto, page);
            if (list != null) {
                for (Object object : list) {
                    SendGoodsListInfoDto dto = (SendGoodsListInfoDto) object;
                    String result = (String) dao.queryForObject(
                            "sendGoods_sqlMap.querySendGoodsListZ", dto.getSgid());
                    if (result == null) {
                        dto.setSgbackmoney(new BigDecimal(0));
                    } else {
                        dto.setSgbackmoney(new BigDecimal(result));
                    }
                }
            }
        } catch (Exception e) {
            log.debug("select sendgoodslist error!", e);
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.sell.ISendGoodsService#getSendGoodsView
     * (java.lang.String)
     */
    public SendGoodInfoDto getSendGoodsView(String id) {
        log.debug("发货单查看Service层!");
        SendGoodInfoDto result = new SendGoodInfoDto();
        try {
            result = (SendGoodInfoDto) dao.queryForObject(
                    "sendGoods_sqlMap.selectSendGoodsView", id);
        } catch (Exception e) {
            log.error("发货单信息查询失败！", e);
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.sell.ISendGoodsService#sdQueryAllProductType
     * ()
     */
    @SuppressWarnings("unchecked")
    public List<ProductTypeEntity> getAllProductType() {
        List<ProductTypeEntity> list = new ArrayList<ProductTypeEntity>();
        try {
            list = dao.queryForlist("sendGoods_sqlMap.sdQueryAllProductType", null);
        } catch (Exception e) {
            log.error("select producttype error!", e);
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.sell.ISendGoodsService#removeSendGoods(
     * java.lang.String)
     */
    public boolean removeSendGoods(String id, String sgstatus) {
        boolean result = true;
        try {
            Integer sgst = Integer.parseInt(sgstatus);
            dao.beginTransaction();
            if (sgst == 8) {
                dao.update("sendGoods_sqlMap.updateStokePrepared", id);
                dao.delete("sendGoods_sqlMap.deleteSendGoodsDetail", id);
            }
            dao.delete("sendGoods_sqlMap.deleteSendGood", id);
            dao.commitTransaction();
        } catch (Exception e) {
            result = false;
            log.error("发货单删除失败！", e);
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.error("发货单删除失败-->事务回滚失败", e);
                result = false;
            }
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @seecn.com.thtf.egov.cms.iservice.sell.ISendGoodsService#
     * modifySendStockingAssessment(cn.com.thtf.egov.cms.entity.SendGoodsEntity)
     */
    public boolean modifySendStockingAssessment(SendGoodsEntity sge, String iderpd) {
        boolean result = true;
        try {
            dao.beginTransaction();
            if (Integer.parseInt(iderpd) == 1) {
                log.debug("备货评审通过！");
                dao.update("sendGoods_sqlMap.modifyStockingAssessment", sge);
            } else {
                log.debug("备货评审未通过！");
                dao.update("sendGoods_sqlMap.updateStokePrepared", sge.getId());
                dao.update("sendGoods_sqlMap.modifyStockingAssessment", sge);
            }
            todoWorkService = (ITodoWorkService) Container.getBean("todoWrokServiceImpl");
            commonService = (ICommonService) Container.getBean("commonServiceImpl");
            WorkEntity work = new WorkEntity();
            // 事务编号
            work.setWorkId(Constants.SELL4);
            Boolean pd = true;
            if (sge.getStatus() == 6 || sge.getStatus() == 11) {
                // 事物数量
                work.setCount(-1);
                work.setUserId(sge.getAreaMajId());
                pd = todoWorkService.addTodoWorks(work);
                if (pd == false) {
                    throw new Exception();
                }
                if (sge.getStatus() == 6) {
                    WorkReceiverDto workReceiverDto = commonService.getUserIdByCondition(
                            sge.getProductTypeId(), null, null,
                            Constants.ROLE_SALES_DIRECTOR);
                    String userId = workReceiverDto
                            .getUserIdByRoleId(Constants.ROLE_SALES_DIRECTOR);
                    work.setUserId(userId);
                    work.setCount(1);
                    // 执行新增事务
                    pd = todoWorkService.addTodoWorks(work);
                    if (pd == false) {
                        throw new Exception();
                    }
                }
            }
            if (sge.getStatus() == 7 || sge.getStatus() == 8) {
                // 事物数量
                work.setCount(-1);
                work.setUserId(sge.getProMajId());
                pd = todoWorkService.addTodoWorks(work);
                if (pd == false) {
                    throw new Exception();
                }
            }
            dao.commitTransaction();
        } catch (Exception e) {
            result = false;
            log.error("备货单评审失败!", e);
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.error("备货单评审失败-->事务回滚失败", e);
                result = false;
            }
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.sell.ISendGoodsService#sendGoodsViewProduct
     * (cn.com.thtf.egov.cms.dto.SendGoodInfoDto)
     */
    @SuppressWarnings("unchecked")
    public List<SendgoodViewSelectProductDto> sendGoodsViewProduct(SendGoodInfoDto sgifd) {
        List<SendgoodViewSelectProductDto> list = new ArrayList<SendgoodViewSelectProductDto>();
        try {
            list = dao.queryForlist("sendGoods_sqlMap.sendGoodsViewselectProduct", sgifd);
        } catch (Exception e) {
            log.error("发货单查看查询产品信息出错！", e);
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.sell.ISendGoodsService#SauditPrepareView
     * (cn.com.thtf.egov.cms.dto.SendGoodInfoDto)
     */
    @SuppressWarnings("unchecked")
    public List<SendgoodSauditPrepareDto> selectSauditPrepareView(SendGoodInfoDto sgifd) {
        List<SendgoodSauditPrepareDto> list = new ArrayList<SendgoodSauditPrepareDto>();
        try {
            list = dao.queryForlist("sendGoods_sqlMap.SauditPrepareView", sgifd);
        } catch (Exception e) {
            log.error("备货评审查询产品信息出错！", e);
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.sell.ISendGoodsService#addReservegoods(
     * cn.com.thtf.egov.cms.entity.SendGoodsEntity)
     */
    @Override
    public Boolean addReservegoods(SendGoodsEntity sendGoodE,
            List<SendGoodsDetailEntity> sendGoodsDetailList,
            Map<String, Timestamp> timeMap) {
        Boolean result = false;
        try {
            dao.beginTransaction();
            Integer updateResult = 0;
            // 新增备货单
            dao.insert("sendGoods_sqlMap.insertReservegoods", sendGoodE);
            for (SendGoodsDetailEntity sendGoodsDetailE : sendGoodsDetailList) {
                // 创建备货单明细
                StockEntity stockE = new StockEntity();
                stockE.setProductId(sendGoodsDetailE.getProductId());
                stockE.setStockroomId(sendGoodE.getStockroomId());
                stockE.setPrepared(sendGoodsDetailE.getCount());
                stockE.setTimeStamp(timeMap.get(sendGoodsDetailE.getProductId() + ""));
                // 新增备货单明细
                dao.insert("sendGoods_sqlMap.insertReservegoodsDetail", sendGoodsDetailE);
                // 更新库存
                // 注：以下代码应该备注释掉，但担心会引起问题所以保留
                if (sendGoodE.getStatus() == 2) {
                    updateResult = dao.update("sendGoods_sqlMap.modifyStockReservegoods",
                            stockE);
                    if (updateResult == 0) {
                        throw new Exception();
                    }
                }
            }
            log.debug("新建备货单插入事务");
            // 插入事务
            if (updateResult != 0) {
                Boolean pd = true;
                todoWorkService = (ITodoWorkService) Container
                        .getBean("todoWrokServiceImpl");
                commonService = (ICommonService) Container.getBean("commonServiceImpl");
                WorkEntity work = new WorkEntity();
                String quzj = commonService.getUserIdByCondition(
                        sendGoodE.getProductTypeId(), sendGoodE.getUserAreaId(), null,
                        Constants.ROLE_REGIONAL_DIRECTOR).getUserIdByRoleId(
                        Constants.ROLE_REGIONAL_DIRECTOR);
                String xszj = commonService.getUserIdByCondition(
                        sendGoodE.getProductTypeId(), null, null,
                        Constants.ROLE_SALES_DIRECTOR).getUserIdByRoleId(
                        Constants.ROLE_SALES_DIRECTOR);
                log.debug("区域总监:" + quzj + "        销售总监:" + xszj);
                work.setCount(1);
                work.setWorkId(Constants.SELL4);
                if (StringUtils.isEmpty(quzj)) {
                    work.setUserId(xszj);
                } else {
                    work.setUserId(quzj);
                    sendGoodE.setStatus(10);
                    dao.update("sendGoods_sqlMap.getSoodGoodsStatus", sendGoodE);
                }
                pd = todoWorkService.addTodoWorks(work);
                if (!pd) {
                    throw new Exception();
                }
            }
            result = true;
            dao.commitTransaction();
        } catch (Exception e) {

            result = false;
            log.error("insert Reservegoods error!", e);
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.error("close Transaction error!", e);
            }
        }

        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.sell.ISendGoodsService#checkStockNumReserve
     * (java.lang.String, java.lang.String, java.lang.String[],
     * java.lang.String[])
     */
    public Map<String, Object> checkStockNumReserve(String stockroomId,
            String sellContractId, String[] productIdArr, String[] countArr) {
        boolean checkResult = true;
        List<SendgoodsProductSelectDto> reslut = getProductListBySrIdAndScId(stockroomId,
                sellContractId);
        Map<String, Integer> map = new HashMap<String, Integer>();
        Map<String, Timestamp> timeMap = new HashMap<String, Timestamp>();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        for (SendgoodsProductSelectDto reslutDto : reslut) {
            if (reslutDto.getFhkyNum() == null) {
                reslutDto.setFhkyNum(0);
            }
            map.put(reslutDto.getProductId(), reslutDto.getFhkyNum());
            timeMap.put(reslutDto.getProductId(), reslutDto.getTimeStamp());
        }

        for (int i = 0; i < productIdArr.length; i++) {
            if (map.get(productIdArr[i]).intValue() < Integer.parseInt(countArr[i])) {
                checkResult = false;
                break;
            }
        }
        resultMap.put("timeMap", timeMap);
        resultMap.put("checkResult", checkResult);
        return resultMap;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.sell.ISendGoodsService#getReservegoodsView
     * (java.lang.String)
     */
    public SendGoodInfoDto getReservegoodsView(String id) {
        SendGoodInfoDto result = new SendGoodInfoDto();
        try {
            result = (SendGoodInfoDto) dao.queryForObject(
                    "sendGoods_sqlMap.selectReservegoodsView", id);
        } catch (Exception e) {
            log.error("备货单信息查询失败！", e);
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.sell.ISendGoodsService#modifyReservegoods
     * (cn.com.thtf.egov.cms.entity.SendGoodsEntity, java.util.List,
     * java.util.Map)
     */
    @Override
    public Boolean modifyReservegoods(SendGoodsEntity sendGoodE,
            List<SendGoodsDetailEntity> sendGoodsDetailList,
            Map<String, Timestamp> timeMap) {
        Boolean result = false;
        try {
            dao.beginTransaction();
            // 更新备货单
            Integer updateResult = 0;
            Integer saveupdateResult = dao.update("sendGoods_sqlMap.modifyReservegoods",
                    sendGoodE);
            if (saveupdateResult == 0) {
                log.debug("update Reservegoods error !");
                throw new Exception();
            }
            // 删除备货单明细
            dao.delete("sendGoods_sqlMap.deleteSendGoodsDetail", sendGoodE.getId());
            for (SendGoodsDetailEntity sendGoodsDetailE : sendGoodsDetailList) {
                // log.debug(sendGoodsDetailE.getSendGoodsId() + "__and__"
                // + sendGoodsDetailE.getProductId() + "__and__"
                // + sendGoodsDetailE.getCount() + "__and__"
                // + sendGoodsDetailE.getPrice() + "__and__"
                // + timeMap.get(sendGoodsDetailE.getProductId() + ""));
                // 创建备货单明细
                StockEntity stockE = new StockEntity();
                stockE.setProductId(sendGoodsDetailE.getProductId());
                stockE.setStockroomId(sendGoodE.getStockroomId());
                stockE.setPrepared(sendGoodsDetailE.getCount());
                stockE.setTimeStamp(timeMap.get(sendGoodsDetailE.getProductId() + ""));

                // 新增备货单明细
                dao.insert("sendGoods_sqlMap.insertReservegoodsDetail", sendGoodsDetailE);
                // 更新库存
                if (sendGoodE.getStatus() != 1) {
                    updateResult = dao.update("sendGoods_sqlMap.modifyStockReservegoods",
                            stockE);
                    if (updateResult == 0) {
                        throw new Exception();
                    }
                }
            }
            log.debug("备货单修改插入事务");
            // 插入事务
            if (updateResult != 0) {
                Boolean pd = true;
                todoWorkService = (ITodoWorkService) Container
                        .getBean("todoWrokServiceImpl");
                commonService = (ICommonService) Container.getBean("commonServiceImpl");
                WorkEntity work = new WorkEntity();
                String quzj = commonService.getUserIdByCondition(
                        sendGoodE.getProductTypeId(), sendGoodE.getUserAreaId(), null,
                        Constants.ROLE_REGIONAL_DIRECTOR).getUserIdByRoleId(
                        Constants.ROLE_REGIONAL_DIRECTOR);
                String xszj = commonService.getUserIdByCondition(
                        sendGoodE.getProductTypeId(), null, null,
                        Constants.ROLE_SALES_DIRECTOR).getUserIdByRoleId(
                        Constants.ROLE_SALES_DIRECTOR);
                log.debug("区域总监:" + quzj + "        销售总监:" + xszj);
                work.setCount(1);
                work.setWorkId(Constants.SELL4);
                if (StringUtils.isEmpty(quzj)) {
                    work.setUserId(xszj);
                } else {
                    work.setUserId(quzj);
                    sendGoodE.setStatus(10);
                    dao.update("sendGoods_sqlMap.getSoodGoodsStatus", sendGoodE);
                }
                pd = todoWorkService.addTodoWorks(work);
                if (!pd) {
                    throw new Exception();
                }
            }
            result = true;
            dao.commitTransaction();
        } catch (Exception e) {

            result = false;
            log.error("update Reservegoods error!", e);
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.error("close Transaction error!", e);
            }
        }

        return result;
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

    @Override
    public String getBoxIdBySendGoodId(String sendGoodId) {
        BoxDetailEntity box = new BoxDetailEntity();
        String boxId = "";
        Map<String, String> map = new HashMap<String, String>();
        map.put("sendGoodId", sendGoodId);
        try {

            box = (BoxDetailEntity) dao.queryForObject(
                    "sendGoods_sqlMap.getBoxIdBySendGoodId", map);
            boxId = box.getBoxId();
        } catch (Exception e) {
            log.error("select BoxIdBySendGoodId error!", e);
        }
        return boxId;
    }
}