/**
 * ClassName  BoxServiceImp
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-6-2
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.serviceimpl.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.BaseService;
import cn.com.thtf.egov.cms.application.NewIDao;
import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.dto.BoxDto;
import cn.com.thtf.egov.cms.dto.BoxQueryDto;
import cn.com.thtf.egov.cms.dto.ProductDto;
import cn.com.thtf.egov.cms.dto.SendGoodsAddDto;
import cn.com.thtf.egov.cms.dto.StockDto;
import cn.com.thtf.egov.cms.dto.StockSendGoodsDetailDto;
import cn.com.thtf.egov.cms.dto.StockSendGoodsDto;
import cn.com.thtf.egov.cms.entity.BoxEntity;
import cn.com.thtf.egov.cms.entity.BuyBackGoodsEntity;
import cn.com.thtf.egov.cms.entity.MoveEntity;
import cn.com.thtf.egov.cms.entity.SampleOutEntity;
import cn.com.thtf.egov.cms.entity.SendGoodsDetailEntity;
import cn.com.thtf.egov.cms.entity.SendGoodsEntity;
import cn.com.thtf.egov.cms.entity.StockEntity;
import cn.com.thtf.egov.cms.entity.UserAreaProductEntity;
import cn.com.thtf.egov.cms.entity.UserProductEntity;
import cn.com.thtf.egov.cms.entity.UserStockroomProductEntity;
import cn.com.thtf.egov.cms.iservice.inventory.IBoxService;
import cn.com.thtf.egov.cms.iservice.mail.IMailService;
import cn.com.thtf.egov.cms.util.Container;
import cn.com.thtf.egov.cms.util.Util;

/**
 * BoxServiceImp
 * 
 * @author Lubo
 */
public class BoxServiceImp extends BaseService implements IBoxService {

    /** log */
    private static Logger log = LoggerFactory.getLogger(BoxServiceImp.class);
    /** NewIDao */
    private NewIDao dao;

    /**
     * 发货单特殊说明
     * 
     * @param para
     * @return
     */
    public List<StockSendGoodsDto> getOrderText(StockSendGoodsDto para) {
        List result = null;
        try {
            result = dao.queryForlist("box_sqlMap.selectOrderText", para);
        } catch (Exception e) {
            log.error("发货单特殊说明！", e);
        }
        return result;
    }

    /**
     * 装箱单连纸打印
     * 
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    public BoxDto getBoxPrint(String id) {
        BoxDto result = null;
        try {
            result = (BoxDto) dao.queryForObject("box_sqlMap.selectBoxPrint", id);
            List<BoxDto> stockroomList = dao.queryForlist(
                    "box_sqlMap.selectStockroomAddress", result.getStockroomId());
            if (stockroomList != null && stockroomList.size() > 0) {
                result.setStockroomTel(stockroomList.get(0).getStockroomTel());
            }

            List<BoxDto> detailList = dao.queryForlist("box_sqlMap.selectBoxDetail", id);
            StringBuffer sb = new StringBuffer();
            for (BoxDto boxDto : detailList) {
                sb.append(boxDto.getOrderId()).append(",");
            }
            result.setOrderIdArr(sb.toString().split(","));
        } catch (Exception e) {
            log.error("装箱单查看错误！", e);
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.inventory.IBoxService#removeBox(java.lang
     * .String)
     */
    @SuppressWarnings("unchecked")
    public boolean removeBox(String id) {
        try {
            dao.beginTransaction();

            /* 更新发货单状态为代发货 */
            List<BoxDto> orderList = dao.queryForlist("box_sqlMap.selectBoxDetail", id);

            dao.startBatch();
            for (BoxDto boxDto : orderList) {
                String orderId = boxDto.getOrderId().substring(0, 2);
                if (StringUtils.equals("FH", orderId)) {
                    SendGoodsAddDto sendGoodsAdd = new SendGoodsAddDto();
                    sendGoodsAdd.setId(boxDto.getOrderId());
                    sendGoodsAdd.setStatus("2");
                    dao.update("sendGoods_sqlMap.updateSendGoodsStatus", sendGoodsAdd);
                } else if (StringUtils.equals("TR", orderId)) {
                    boxDto.setStatus("8");
                    dao.update("box_sqlMap.updateBuyBackGoodsStatus", boxDto);
                } else if (StringUtils.equals("YK", orderId)) {
                    boxDto.setStatus("4");
                    dao.update("box_sqlMap.updateMoveStatus", boxDto);
                } else if (StringUtils.equals("YP", orderId)) {
                    boxDto.setStatus("6");
                    dao.update("box_sqlMap.updateSampleOutStatus", boxDto);
                } else {
                    log.error("错误的发货单号");
                    return false;
                }
            }
            dao.executeBatch();

            dao.delete("box_sqlMap.deleteBoxById", id);

            dao.commitTransaction();
            return true;
        } catch (Exception e) {
            log.error("删除装箱单失败！", e);
            return false;
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e1) {
                log.error("保存装箱事物回滚,发生错误:", e1);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.inventory.IBoxService#boxTbcPay(cn.com.
     * thtf.egov.cms.dto.BoxDto)
     */
    public boolean boxTbcPay(BoxDto para) {
        try {
            BoxDto result = (BoxDto) dao.queryForObject("box_sqlMap.selectBoxStatus",
                    para.getId());
            if (StringUtils.equals(result.getStatus(), "5")) {
                BoxEntity boxParam = new BoxEntity();
                boxParam.setId(para.getId());
                boxParam.setStatus(Integer.parseInt(para.getStatus()));
                dao.update("box_sqlMap.updateBoxStatus", boxParam);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            log.error("更新装箱单失败！", e);
            return false;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.inventory.IBoxService#boxTbc(cn.com.thtf
     * .egov.cms.dto.BoxDto)
     */
    public boolean boxTbc(BoxDto para) {
        try {
            BoxDto result = (BoxDto) dao.queryForObject("box_sqlMap.selectBoxStatus",
                    para.getId());
            if (StringUtils.equals(result.getStatus(), "2")
                    || StringUtils.equals(result.getStatus(), "3")
                    || StringUtils.equals(result.getStatus(), "4")
                    && StringUtils.equals(result.getTbcFlag(), "0")) {

                dao.update("box_sqlMap.updateBoxMoneyAndCount", para);
                
                if (StringUtils.equals(para.getTbcFlag(), "1")) {
                    dao.update("box_sqlMap.updateBoxBtcStatus", para);
                }

                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            log.error("更新装箱单失败！", e);
            return false;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.inventory.IBoxService#boxConfirm(cn.com
     * .thtf.egov.cms.dto.BoxQueryDto)
     */
    public boolean boxConfirm(BoxDto para) {
        try {
            BoxDto result = (BoxDto) dao.queryForObject("box_sqlMap.selectBoxStatus",
                    para.getId());
            if (StringUtils.equals(result.getStatus(), "4")
                    && StringUtils.equals(result.getTbcFlag(), "1")) {
                dao.update("box_sqlMap.updateBoxStatusAndConfirmDate", para);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            log.error("更新装箱单失败！", e);
            return false;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.inventory.IBoxService#getBoxList(cn.com
     * .thtf.egov.cms.dto.BoxQueryDto, cn.com.thtf.egov.cms.application.NewPage)
     */
    @SuppressWarnings("unchecked")
    public List<BoxDto> getBoxList(BoxQueryDto para, NewPage page) {
        List result = null;

        try {
            if (para.getUser().getRoleId() == 12) {
                List<UserStockroomProductEntity> userStockroomList = (List<UserStockroomProductEntity>) dao
                        .getSqlMap().queryForList(
                                "inventtory_sqlMap.selectTreasuryManagerById",
                                para.getUser().getId());

                /* 封装查询参数 */
                // setUserStockroom(para, userStockroomList);
                if (userStockroomList.size() > 0) {
                    para.setUserStockroomList(userStockroomList);
                    result = queryRecords(dao, "selectBoxList.data", para, page);
                } else {
                    result = new ArrayList();
                }
            } else if (para.getUser().getRoleId() == 3) {
                /* 销售助理 */
                List<UserAreaProductEntity> userAreaProductList = (List<UserAreaProductEntity>) dao
                        .getSqlMap()
                        .queryForList("salesContract_sqlMap.getUserAreaProduct",
                                para.getUser());

                if (userAreaProductList.size() > 0) {
                    /* 封装查询参数 */
                    // setUserAreaProduct(salesDto, userAreaProductList);
                    para.setUserAreaProductList(userAreaProductList);

                    result = queryRecords(dao, "selectBoxList.data", para, page);
                } else {
                    result = new ArrayList();
                }
            } else if (para.getUser().getRoleId() == 4 || para.getUser().getRoleId() == 5
                    || para.getUser().getRoleId() == 6 || para.getUser().getRoleId() == 8
                    || para.getUser().getRoleId() == 9
                    || para.getUser().getRoleId() == 10
                    || para.getUser().getRoleId() == 11) {

                List<UserProductEntity> userProductList = (List<UserProductEntity>) dao
                        .getSqlMap().queryForList("salesContract_sqlMap.getUserProduct",
                                para.getUser());

                if (userProductList.size() > 0) {
                    /* 封装查询参数 */
                    setUserProduct(para, userProductList);
                    result = queryRecords(dao, "selectBoxList.data", para, page);
                } else {
                    result = new ArrayList();
                }

            } else {
                result = queryRecords(dao, "selectBoxList.data", para, page);
            }

        } catch (Exception e) {
            log.error("检索装箱单列表错误！", e);
        }

        return result;
    }

    /**
     * 封装 查询参数 区域产品
     * 
     * @param salesDto
     * @param userAreaProductList
     */
    private void setUserProduct(BoxQueryDto para, List<UserProductEntity> userProductList) {
        StringBuffer product = new StringBuffer();

        for (UserProductEntity userProduct : userProductList) {
            product.append(userProduct.getProductTypeId()).append(",");
        }

        if (product.length() > 0) {
            para.setProductTypeIdArr(product.substring(0, product.length() - 1));
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.inventory.IBoxService#getBox(java.lang.
     * String)
     */
    @SuppressWarnings("unchecked")
    public BoxDto getBox(String id) {
        BoxDto result = null;
        try {
            result = (BoxDto) dao.queryForObject("box_sqlMap.selectBox", id);

            List<BoxDto> detailList = dao.queryForlist("box_sqlMap.selectBoxDetail", id);
            StringBuffer sb = new StringBuffer();
            for (BoxDto boxDto : detailList) {
                sb.append(boxDto.getOrderId()).append(",");
            }
            result.setOrderIdArr(sb.toString().split(","));
        } catch (Exception e) {
            log.error("装箱单查看错误！", e);
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.inventory.IBoxService#addBox(cn.com.thtf
     * .egov.cms.dto.BoxDto)
     */
    @SuppressWarnings("unchecked")
    public boolean addBox(BoxDto para) {
        /* 执行保存操作 */
        try {
            dao.beginTransaction();
            if (para.isPageType()) {
                /* 更新发货单状态为待发货 */
                List<BoxDto> orderList = dao.queryForlist("box_sqlMap.selectBoxDetail",
                        para.getId());

                dao.startBatch();
                for (BoxDto boxDto : orderList) {
                    String orderId = boxDto.getOrderId().substring(0, 2);
                    if (StringUtils.equals("FH", orderId)) {
                        SendGoodsAddDto sendGoodsAdd = new SendGoodsAddDto();
                        sendGoodsAdd.setId(boxDto.getOrderId());
                        sendGoodsAdd.setStatus("2");
                        dao.update("sendGoods_sqlMap.updateSendGoodsStatus", sendGoodsAdd);
                    } else if (StringUtils.equals("TR", orderId)) {
                        boxDto.setStatus("8");
                        dao.update("box_sqlMap.updateBuyBackGoodsStatus", boxDto);
                    } else if (StringUtils.equals("YK", orderId)) {
                        boxDto.setStatus("4");
                        dao.update("box_sqlMap.updateMoveStatus", boxDto);
                    } else if (StringUtils.equals("YP", orderId)) {
                        boxDto.setStatus("6");
                        dao.update("box_sqlMap.updateSampleOutStatus", boxDto);
                    } else {
                        log.error("错误的发货单号");
                        return false;
                    }
                }
                dao.executeBatch();

                dao.delete("box_sqlMap.deleteBoxDetailById", para.getId());
                dao.update("box_sqlMap.updateBox", para);
            } else {
                dao.insert("box_sqlMap.insertBox", para);
            }

            dao.startBatch();

            /* 插入发货单明细表 */
            BoxDto detailPara = null;
            Map<String, String> orderMap = new HashMap<String, String>();
            for (int i = 0; i < para.getOrderIdArr().length; i++) {
                orderMap.put(para.getOrderIdArr()[i], para.getOrderTypeArr()[i]);
            }
            Set<String> set = orderMap.keySet();
            Iterator<String> iterator = set.iterator();
            /* set method */
            while (iterator.hasNext()) {
                String key = iterator.next();
                detailPara = new BoxDto();
                detailPara.setId(para.getId());
                detailPara.setOrderId(key);
                dao.insert("box_sqlMap.insertBoxDetail", detailPara);
            }

            dao.executeBatch();

            /* 如果是提交 */
            if (StringUtils.equals("2", para.getSubmitType())) {
                Integer stockroomId = null;
                List<StockDto> orderStock = null;

                set = orderMap.keySet();
                iterator = set.iterator();
                /* set method */
                while (iterator.hasNext()) {
                    String orderId = iterator.next();
                    String orderType = orderMap.get(orderId);
                    // for (int i = 0; i < para.getOrderIdArr().length; i++) {

                    log.debug("订单号:" + orderId);

                    /* 更新状态和发货时间,取得库房ID和明细信息 */
                    if (StringUtils.equals(orderType, "1")) {
                        SendGoodsEntity sendGoods = new SendGoodsEntity();
                        sendGoods.setId(orderId);
                        sendGoods.setStatus(5);
                        sendGoods.setSendDate(Util.getDate());

                        sendGoods.setStkAdmId(para.getUserId());
                        sendGoods.setStkAdmName(para.getUserName());
                        sendGoods.setStkAdmDate(Util.getDate());
                        sendGoods.setStkAdmIdea("111");

                        dao.update("box_sqlMap.updateSendGoodsComplete", sendGoods);

                        orderStock = dao.queryForlist("box_sqlMap.selectSendGoodsStock",
                                orderId);
                        stockroomId = orderStock.get(0).getStockroomId();

                    } else if (StringUtils.equals(orderType, "2")) {
                        BuyBackGoodsEntity buyBackGoods = new BuyBackGoodsEntity();
                        buyBackGoods.setId(orderId);
                        buyBackGoods.setStatus(11);
                        buyBackGoods.setSendDate(Util.getDate());

                        buyBackGoods.setStkAdmId(para.getUserId());
                        buyBackGoods.setStkAdmName(para.getUserName());
                        buyBackGoods.setStkAdmDate(Util.getDate());
                        buyBackGoods.setStkAdmIdea("111");

                        dao.update("box_sqlMap.updateBuyBackGoodsComplete", buyBackGoods);

                        orderStock = dao.queryForlist(
                                "box_sqlMap.selectBuyBackGoodsStock", orderId);
                        stockroomId = orderStock.get(0).getStockroomId();
                    } else if (StringUtils.equals(orderType, "3")) {
                        MoveEntity move = new MoveEntity();
                        move.setId(orderId);
                        move.setStatus(7);
                        move.setSendDate(Util.getDate());
                        move.setStkAdmId(para.getUserId());
                        move.setStkAdmName(para.getUserName());
                        move.setStkAdmDate(Util.getDate());
                        move.setStkAdmIdea("111");

                        dao.update("box_sqlMap.updateMoveComplete", move);

                        orderStock = dao.queryForlist("box_sqlMap.selectMoveStock",
                                orderId);
                        stockroomId = orderStock.get(0).getStockroomId();
                    } else if (StringUtils.equals(orderType, "4")) {
                        SampleOutEntity sampleOut = new SampleOutEntity();
                        sampleOut.setId(orderId);
                        sampleOut.setStatus(9);
                        sampleOut.setSendDate(Util.getDate());

                        sampleOut.setStkAdmId(para.getUserId());
                        sampleOut.setStkAdmName(para.getUserName());
                        sampleOut.setStkAdmDate(Util.getDate());
                        sampleOut.setStkAdmIdea("111");

                        dao.update("box_sqlMap.updateSampleOutComplete", sampleOut);

                        orderStock = dao.queryForlist("box_sqlMap.selectSampleOutStock",
                                orderId);
                        stockroomId = orderStock.get(0).getStockroomId();
                    } else {
                        log.error("不存在的类型！");
                        return false;
                    }

                    /* 更新库存信息 */
                    StockEntity stockPara = new StockEntity();
                    stockPara.setStockroomId(stockroomId);

                    log.debug("库房ID:" + stockroomId);
                    for (int j = 0; j < orderStock.size(); j++) {
                        /* 取库存时间戳 */
                        stockPara.setProductId(orderStock.get(j).getProductId());
                        log.debug("产品ID:" + orderStock.get(j).getProductId());
                        StockEntity result = (StockEntity) dao.queryForObject(
                                "inventtory_sqlMap.getStockByCondition", stockPara);

                        /* 更新库存金额 */
                        if (StringUtils.equals(orderType, "1")) {
                            StockEntity numResult = (StockEntity) dao.queryForObject(
                                    "inventtory_sqlMap.getStockMoneyByCondition",
                                    stockPara);

                            /* 更新发货明细库存单价 */
                            SendGoodsDetailEntity sendGoodsDetailEntity = new SendGoodsDetailEntity();
                            sendGoodsDetailEntity.setCount(numResult.getNum());
                            sendGoodsDetailEntity.setProductId(result.getProductId());
                            sendGoodsDetailEntity.setSendGoodsId(orderId);
                            dao.update("box_sqlMap.updateSendGoodsDetilePrice",
                                    sendGoodsDetailEntity);

                            ProductDto productDto = new ProductDto();
                            productDto.setProductId(result.getProductId().toString());
                            productDto.setCount(orderStock.get(j).getNum().toString());
                            productDto.setNum(numResult.getNum().toString());
                            dao.update("box_sqlMap.updateProductMoney", productDto);
                        } else if (StringUtils.equals(orderType, "2")) {
                            // BuyBackGoodsEntity buyBackGoods = new
                            // BuyBackGoodsEntity();
                            // buyBackGoods.setId(orderId);
                            // buyBackGoods.setStatus(9);
                            // buyBackGoods.setSendDate(Util.getDate());
                            // dao.update("box_sqlMap.updateBuyBackGoodsComplete",
                            // buyBackGoods);

                            ProductDto productDto = new ProductDto();
                            productDto.setProductId(result.getProductId().toString());
                            productDto.setOrderId(orderId);
                            dao.update("box_sqlMap.updateProductMoneyComplex", productDto);
                        } else if (StringUtils.equals(orderType, "3")) {
                            // MoveEntity move = new MoveEntity();
                            // move.setId(orderId);
                            // move.setStatus(5);
                            // move.setSendDate(Util.getDate());
                            // dao.update("box_sqlMap.updateMoveComplete",
                            // move);

                            ProductDto productDto = new ProductDto();
                            productDto.setProductId(result.getProductId().toString());
                            productDto.setNum(orderStock.get(j).getNum().toString());
                            productDto.setMoney(orderStock.get(j).getPrice());
                            dao.update("box_sqlMap.updateProductMoneySimp", productDto);
                        } else if (StringUtils.equals(orderType, "4")) {
                            // SampleOutEntity sampleOut = new
                            // SampleOutEntity();
                            // sampleOut.setId(orderId);
                            // sampleOut.setStatus(7);
                            // sampleOut.setSendDate(Util.getDate());
                            // dao.update("box_sqlMap.updateSampleOutComplete",
                            // sampleOut);

                            ProductDto productDto = new ProductDto();
                            productDto.setProductId(result.getProductId().toString());
                            productDto.setNum(orderStock.get(j).getNum().toString());
                            productDto.setMoney(orderStock.get(j).getPrice());
                            dao.update("box_sqlMap.updateProductMoneySimp", productDto);
                        } else {
                            log.error("不存在的类型！");
                            return false;
                        }

                        /* 更新库存表，减少库存数、库存金额、冻结数 更新库存时间戳 */
                        if (StringUtils.equals(orderType, "2")) {
                            result.setSendLock(result.getSendLock());
                        } else {
                            result.setSendLock(result.getSendLock()
                                    - orderStock.get(j).getNum());
                        }
                        result.setNum(result.getNum() - orderStock.get(j).getNum());

                        int updateResult = dao.update(
                                "inventtory_sqlMap.updateSendLockAndNum", result);

                        if (updateResult != 1) {
                            log.error("更新库存失败！");
                            return false;
                        }

                    }

                    /* 给K3发邮件 */
                    IMailService mailService = (IMailService) Container
                            .getBean("mailServiceImp");
                    boolean result = mailService.outStockroom(orderId, orderType);

                    if (!result) {
                        log.error("保存邮件失败！");
                        return false;
                    }

                }
            } else {
                set = orderMap.keySet();
                iterator = set.iterator();
                /* set method */
                while (iterator.hasNext()) {
                    String orderId = iterator.next();
                    String orderType = orderMap.get(orderId);
                    // for (int i = 0; i < para.getOrderIdArr().length; i++) {
                    /* 更新4种单子的状态为发货中 */
                    if (StringUtils.equals(orderType, "1")) {
                        SendGoodsEntity sendGoods = new SendGoodsEntity();
                        sendGoods.setId(orderId);
                        sendGoods.setStatus(3);
                        dao.update("box_sqlMap.updateSendGoodsComplete", sendGoods);
                    } else if (StringUtils.equals(orderType, "2")) {
                        BuyBackGoodsEntity buyBackGoods = new BuyBackGoodsEntity();
                        buyBackGoods.setId(orderId);
                        buyBackGoods.setStatus(9);
                        buyBackGoods.setSendDate(Util.getDate());
                        dao.update("box_sqlMap.updateBuyBackGoodsComplete", buyBackGoods);
                    } else if (StringUtils.equals(orderType, "3")) {
                        MoveEntity move = new MoveEntity();
                        move.setId(orderId);
                        move.setStatus(5);
                        move.setSendDate(Util.getDate());
                        dao.update("box_sqlMap.updateMoveComplete", move);
                    } else if (StringUtils.equals(orderType, "4")) {
                        SampleOutEntity sampleOut = new SampleOutEntity();
                        sampleOut.setId(orderId);
                        sampleOut.setStatus(7);
                        sampleOut.setSendDate(Util.getDate());
                        dao.update("box_sqlMap.updateSampleOutComplete", sampleOut);
                    } else {
                        log.error("不存在的类型！");
                        return false;
                    }
                }
            }

            dao.commitTransaction();

            return true;
        } catch (Exception e) {
            log.error("保存装箱单,发生错误:", e);
            return false;
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e1) {
                log.error("保存装箱事物回滚,发生错误:", e1);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.inventory.IBoxService#getOrderDetail(cn
     * .com.thtf.egov.cms.dto.StockSendGoodsDto)
     */
    @SuppressWarnings("unchecked")
    public List<StockSendGoodsDetailDto> getOrderDetail(StockSendGoodsDto para) {
        List result = null;
        try {
            result = dao.queryForlist("box_sqlMap.selectOrderDetail", para);
        } catch (Exception e) {
            log.error("发货单明细！", e);
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public List<StockSendGoodsDto> getOrderList(StockSendGoodsDto para) {
        List result = null;

        try {

            List<UserStockroomProductEntity> userStockroomList = (List<UserStockroomProductEntity>) dao
                    .getSqlMap().queryForList(
                            "inventtory_sqlMap.selectTreasuryManagerById",
                            para.getUser().getId());

            /* 封装查询参数 */
            // setUserStockroom(para, userStockroomList);
            para.setUserStockroomList(userStockroomList);
            result = dao.queryForlist("box_sqlMap.selectOrder", para);
        } catch (Exception e) {
            log.error("检索单据列表错误！", e);
        }

        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.inventory.IBoxService#completeSendGoodsFalse
     * (cn.com.thtf.egov.cms.dto.SendGoodsAddDto)
     */
    public boolean completeSendGoodsFalse(SendGoodsAddDto para) {

        /* 更新发货单状态为发货成功 虚入时只更新状态和时间 */
        try {
            // dao.beginTransaction();
            SendGoodsEntity sendGoods = new SendGoodsEntity();
            sendGoods.setId(para.getId());
            sendGoods.setStatus(5);
            sendGoods.setSendDate(Util.getDate());

            sendGoods.setStkAdmId("");
            sendGoods.setStkAdmName("");
            sendGoods.setStkAdmDate(Util.getDate());
            sendGoods.setStkAdmIdea("111");

            int updateResult = dao
                    .update("box_sqlMap.updateSendGoodsComplete", sendGoods);
            if (updateResult != 1) {
                log.error("更新发货单状态失败！");
                return false;
            }

            StockEntity stockPara = new StockEntity();
            stockPara.setStockroomId(Integer.parseInt(para.getStockroomId()));
            for (int i = 0; i < para.getProductIdArr().length; i++) {
                stockPara.setProductId(Integer.parseInt(para.getProductIdArr()[i]));
                StockEntity numResult = (StockEntity) dao.queryForObject(
                        "inventtory_sqlMap.getStockMoneyByCondition", stockPara);

                /* 更新发货明细库存单价 */
                SendGoodsDetailEntity sendGoodsDetailEntity = new SendGoodsDetailEntity();
                sendGoodsDetailEntity.setCount(numResult.getNum());
                sendGoodsDetailEntity.setProductId(Integer.parseInt(para
                        .getProductIdArr()[i]));
                sendGoodsDetailEntity.setSendGoodsId(para.getId());
                dao.update("box_sqlMap.updateSendGoodsDetilePrice", sendGoodsDetailEntity);

                ProductDto productDto = new ProductDto();
                productDto.setProductId(para.getProductIdArr()[i]);
                productDto.setCount(para.getCountArr()[i]);
                productDto.setNum(numResult.getNum().toString());
                dao.update("box_sqlMap.updateProductMoney", productDto);

                /* 取库存时间戳 */
                stockPara.setProductId(Integer.parseInt(para.getProductIdArr()[i]));

                StockEntity result = (StockEntity) dao.queryForObject(
                        "inventtory_sqlMap.getStockByCondition", stockPara);
                /* 更新库存表，减少库存数、库存金额、冻结数 更新库存时间戳 */
                result.setSendLock(result.getSendLock()
                        - Integer.parseInt(para.getCountArr()[i]));
                result.setNum(result.getNum() - Integer.parseInt(para.getCountArr()[i]));

                updateResult = dao.update("inventtory_sqlMap.updateSendLockAndNum",
                        result);

                if (updateResult != 1) {
                    log.error("更新库存失败！");
                    return false;
                }
            }

            IMailService mailService = (IMailService) Container.getBean("mailServiceImp");
            boolean result = mailService.outStockroom(para.getId(), "1");

            if (!result) {
                log.error("保存邮件失败！");
                return false;
            }

            // dao.commitTransaction();
            return true;
        } catch (Exception e) {
            log.error("执行发货成功 失败！", e);
            return false;
        }
        // finally {
        // try {
        // dao.endTransaction();
        // } catch (Exception e1) {
        // log.error("执行发货成功事物回滚,发生错误:", e1);
        // }
        // }

    }

    // /**
    // * 封装 查询参数 区域产品
    // *
    // * @param salesDto
    // * @param userAreaProductList
    // */
    // private void setUserStockroom(BoxQueryDto para,
    // List<UserStockroomProductEntity> userStockroomList) {
    // StringBuffer stockroom = new StringBuffer();
    // StringBuffer product = new StringBuffer();
    //
    // for (UserStockroomProductEntity userStockroom : userStockroomList) {
    // stockroom.append(userStockroom.getStockroomId()).append(",");
    // product.append(userStockroom.getProductTypeId()).append(",");
    // }
    // if (stockroom.length() > 0) {
    // para.setStockroomIdArr(stockroom.substring(0, stockroom.length() - 1));
    // }
    // if (product.length() > 0) {
    // para.setProductTypeIdArr(product.substring(0, product.length() - 1));
    // }
    // }
    //
    // /**
    // * 封装 查询参数 区域产品
    // *
    // * @param salesDto
    // * @param userAreaProductList
    // */
    // private void setUserStockroom(StockSendGoodsDto para,
    // List<UserStockroomProductEntity> userStockroomList) {
    // StringBuffer stockroom = new StringBuffer();
    // StringBuffer product = new StringBuffer();
    //
    // for (UserStockroomProductEntity userStockroom : userStockroomList) {
    // stockroom.append(userStockroom.getStockroomId()).append(",");
    // product.append(userStockroom.getProductTypeId()).append(",");
    // }
    // if (stockroom.length() > 0) {
    // para.setStockroomIdArr(stockroom.substring(0, stockroom.length() - 1));
    // }
    // if (product.length() > 0) {
    // para.setProductTypeIdArr(product.substring(0, product.length() - 1));
    // }
    // }

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
