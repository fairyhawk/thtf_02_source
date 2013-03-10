/**
 * ClassName  StockroomServiceImp
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-4-22
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.serviceimpl.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.NewIDao;
import cn.com.thtf.egov.cms.dto.StockroomAndAddressDto;
import cn.com.thtf.egov.cms.entity.SendGoodsEntity;
import cn.com.thtf.egov.cms.entity.StockEntity;
import cn.com.thtf.egov.cms.entity.StockroomEntity;
import cn.com.thtf.egov.cms.iservice.inventory.IStockroomService;

/**
 * StockroomServiceImp
 * 
 * @author Lubo
 */

public class StockroomServiceImp implements IStockroomService {

    /** log */
    private static Logger log = LoggerFactory.getLogger(StockroomServiceImp.class);
    /** NewIDao */
    private NewIDao dao;

    /*
     * (non-Javadoc)
     * 
     * @seecn.com.thtf.egov.cms.iservice.inventory.IStockroomService#
     * getStockByStockroomId(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<StockEntity> getStockByStockroomId(String stockroomId) {
        List<StockEntity> result = null;
        try {
            result = dao.queryForlist("inventtory_sqlMap.getStockByStockroomId",
                    stockroomId);
        } catch (Exception e) {
            log.error("获取指定库房的库存信息错误:", e);
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @seecn.com.thtf.egov.cms.iservice.inventory.IStockroomService#
     * getTypicallyAndVirtual()
     */
    @SuppressWarnings("unchecked")
    public List<StockroomEntity> getTypicallyAndVirtual(Integer productType) {
        List<StockroomEntity> result = null;
        try {
            result = dao.queryForlist("inventtory_sqlMap.getTypicallyAndVirtual",
                    productType);
        } catch (Exception e) {
            log.error("检索库房信息错误:", e);
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @seecn.com.thtf.egov.cms.iservice.inventory.IStockroomService#
     * getNotPrepareStockroom(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public List<StockroomEntity> getNotPrepareStockroom(String sellContractId,
            Integer productType) {
        List<StockroomEntity> result = null;
        try {
            SendGoodsEntity para = new SendGoodsEntity();
            para.setSellContractId(sellContractId);
            para.setProductTypeId(productType);
            result = dao.queryForlist("inventtory_sqlMap.getNotPrepareStockroom", para);
        } catch (Exception e) {
            log.error("检索库房信息错误:", e);
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @seecn.com.thtf.egov.cms.iservice.inventory.IStockroomService#
     * getTypicallyAndVirtual()
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<StockroomEntity> getTypicallyAndVirtual() {
        List<StockroomEntity> result = null;
        try {
            result = dao
                    .queryForlist("inventtory_sqlMap.getTypicallyAndVirtualAll", null);
        } catch (Exception e) {
            log.error("检索库房信息错误:", e);
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public List<StockroomEntity> getAllStockRoom() {
        List<StockroomEntity> result = null;
        try {
            result = dao.queryForlist("inventtory_sqlMap.queryAllStockRoomInventtory",
                    null);
        } catch (Exception e) {
            e.printStackTrace();
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

    /*
     * (non-Javadoc)
     * 
     * @seecn.com.thtf.egov.cms.iservice.inventory.IStockroomService#
     * quertConditionStockRoom(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public List<StockroomAndAddressDto> quertConditionStockRoom(String stockRoomId) {
        log.debug("库房名称及库房收货地址查询！");
        List<StockroomAndAddressDto> list = new ArrayList();
        Map<String, String> map = new HashMap<String, String>();
        map.put("stockRoomId", stockRoomId);
        try {
            list = dao.queryForlist("inventtory_sqlMap.queryAllConditionStockRoom", map);
        } catch (Exception e) {
            log.error("库房名称及库房收货地址查询失败！", e);
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @seecn.com.thtf.egov.cms.iservice.inventory.IStockroomService#
     * quertConditionStockRoom1(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public List<StockroomAndAddressDto> quertConditionStockRoom1(String sookRoomId) {
        log.debug("库房名称查询！");
        List<StockroomAndAddressDto> list = new ArrayList<StockroomAndAddressDto>();
        Map<String, String> map = new HashMap<String, String>();
        map.put("stockRoomId", sookRoomId);
        try {
            list = dao.queryForlist("inventtory_sqlMap.queryAllConditionStockRoom1", map);
        } catch (Exception e) {
            log.error("库房名称及库房收货地址查询失败！", e);
        }
        return list;
    }
}
