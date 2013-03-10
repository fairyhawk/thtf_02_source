/**
 * ClassName  BuyBackGoodsServiceImpl
 *
 * History
 * Create User: liuqg
 * Create Date: 2010-6-11
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.serviceimpl.purchase;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.BaseService;
import cn.com.thtf.egov.cms.application.NewIDao;
import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.dto.BuyBackGoodsAssessDto;
import cn.com.thtf.egov.cms.dto.BuyBackGoodsListDto;
import cn.com.thtf.egov.cms.dto.BuyBackGoodsSearchDto;
import cn.com.thtf.egov.cms.dto.CreateBuyBackGoodsInfoDto;
import cn.com.thtf.egov.cms.dto.ModifyBuyBackGoodsInfoDto;
import cn.com.thtf.egov.cms.entity.BuyBackGoodsDetailEntity;
import cn.com.thtf.egov.cms.entity.BuyBackGoodsEntity;
import cn.com.thtf.egov.cms.entity.ProductTypeEntity;
import cn.com.thtf.egov.cms.entity.StockroomEntity;
import cn.com.thtf.egov.cms.entity.SupplierAddressEntity;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.entity.WorkEntity;
import cn.com.thtf.egov.cms.iservice.purchase.IBuyBackGoodsService;
import cn.com.thtf.egov.cms.iservice.work.ITodoWorkService;
import cn.com.thtf.egov.cms.serviceimpl.work.TodoWorkService;
import cn.com.thtf.egov.cms.util.Container;

/**
 * 采购返厂
 * 
 * @author liuqg
 */

public class BuyBackGoodsServiceImpl extends BaseService implements IBuyBackGoodsService {
    /** log */
    private static Logger log = LoggerFactory.getLogger(BuyBackGoodsServiceImpl.class);
    /** NewIDao */
    private NewIDao dao;

    /**
     * @return the log
     */
    public static Logger getLog() {
        return log;
    }

    /**
     * @param log
     *            the log to set
     */
    public static void setLog(Logger log) {
        BuyBackGoodsServiceImpl.log = log;
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

    /**
     *查询退款评审
     * 
     * @author jiangmx
     * @param buyBackGoodsId
     * @return BuyBackGoodsAssessDto
     */
    @Override
    public BuyBackGoodsAssessDto getBuyBackGoodsById(String buyBackGoodsId)
            throws Exception {

        BuyBackGoodsAssessDto assess = null;

        try {
            assess = (BuyBackGoodsAssessDto) dao.queryForObject(
                    "buyBackGoods_sqlMap.selectBuyBackGoodsInfo", buyBackGoodsId);
        } catch (Exception e) {
            log.error("返厂评审信息失败", e);
        }
        return assess;

    }

    /**
     * 下一个评审人的待办事项
     * 
     * @author jiangmx
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
     * 返厂评审不发待办事务
     * 
     * @author jiangmx
     * @param buyBackGoodsAssessDto
     * @param workself
     * @return boolean
     */
    @Override
    public boolean updateAssessNoWork(BuyBackGoodsAssessDto buyBackGoodsAssessDto,
            WorkEntity workself) throws Exception {
        int count = -1;
        boolean isSuccess = false;

        try {
            dao.beginTransaction();
            count = dao.update("buyBackGoods_sqlMap.updateBuyBackGoodsAssess",
                    buyBackGoodsAssessDto);
            boolean assessManself = NextAssessManWork(workself);
            if (count != 1 || assessManself == false) {
                return isSuccess;
            }
            dao.commitTransaction();

            isSuccess = true;
        } catch (Exception e) {
            log.error("返厂评审未通过失败", e);
            isSuccess = false;
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.debug("返厂评审未通过失败", e);
                isSuccess = false;
            }
        }
        return isSuccess;
    }

    /**
     * 返厂评审发待办事务
     * 
     * @author jiangmx
     * @param buyBackGoodsAssessDto
     * @param work
     * @param workself
     * @return boolean
     */
    @Override
    public boolean updateBuyBackGoodsAssess(BuyBackGoodsAssessDto buyBackGoodsAssessDto,
            WorkEntity work, WorkEntity workself) throws Exception {
        int count = -1;
        boolean isSuccess = false;

        try {
            dao.beginTransaction();
            count = dao.update("buyBackGoods_sqlMap.updateBuyBackGoodsAssess",
                    buyBackGoodsAssessDto);
            boolean assessMan = NextAssessManWork(work);
            boolean assessManself = NextAssessManWork(workself);
            if (count != 1 || assessMan == false || assessManself == false) {
                return isSuccess;
            }
            dao.commitTransaction();

            isSuccess = true;
        } catch (Exception e) {
            log.error("返厂评审通过失败", e);
            isSuccess = false;
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.debug("返厂评审通过失败", e);
                isSuccess = false;
            }
        }
        return isSuccess;
    }

    /**
     * 根据登录经理的ID查询返厂列表 by zzx
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<BuyBackGoodsListDto> getBuyBackGoodsList(NewPage page, UserEntity user)
            throws Exception {
        List result = null;
        try {
            result = queryRecords(dao, "selectBuyBackGoodsList.selectBuyBackGoodsList",
                    user, page);
        } catch (Exception e) {
            log.error("获取返厂单一览错误:", e);
        }
        return result;
    }

    /**
     * 获得所有产品分类 by zzx
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<ProductTypeEntity> getProductType() throws Exception {
        List result = null;
        try {
            result = dao.queryForlist("buyBackGoods_sqlMap.selectProductTypeList", null);
        } catch (Exception e) {
            log.error("检索产品分类错误:", e);
        }
        return result;
    }

    /**
     * 获得所有库房信息 by zzx
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<StockroomEntity> getStockroom() throws Exception {
        List result = null;
        try {
            result = dao.queryForlist("buyBackGoods_sqlMap.selectStockroom", null);
        } catch (Exception e) {
            log.error("检索库房信息错误:", e);
        }
        return result;
    }

    /**
     * 根据条件查询返厂单 by zzx
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<BuyBackGoodsListDto> getBuyBackGoodsListByCondition(NewPage page,
            BuyBackGoodsSearchDto buyBackGoodsSearchDto) throws Exception {
        List result = null;
        try {
            result = queryRecords(
                    dao,
                    "selectBuyBackGoodsListByCondition.selectBuyBackGoodsListByCondition",
                    buyBackGoodsSearchDto, page);
        } catch (Exception e) {
            log.error("获取检索返厂单一览错误:", e);
        }
        return result;
    }

    /**
     * 新建采购返厂显示用
     * 
     * @author liuqg
     * @param id
     *            入库单号
     * @return List<CreateBuyBackGoodsInfo>
     * @throws Exception
     */
    @Override
    public CreateBuyBackGoodsInfoDto selectInStockInfo(String id) throws Exception {
        CreateBuyBackGoodsInfoDto backGoodsInfo = new CreateBuyBackGoodsInfoDto();
        try {
            backGoodsInfo = (CreateBuyBackGoodsInfoDto) dao.queryForObject(
                    "buyBackGoods_sqlMap.selectInstockInfo", id);

        } catch (Exception e) {
            log.error("新建采购返厂显示", e);
        }
        return backGoodsInfo;
    }

    /**
     * 新建采购返厂显示用 表格
     * 
     * @author liuqg
     * @param id
     *            入库单号
     * @return List<CreateBuyBackGoodsInfo>
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<CreateBuyBackGoodsInfoDto> selectCreateBuyBackGoodsInfo(
            BuyBackGoodsEntity backGoodsEntity,NewPage newPage) throws Exception {
        List list =   new ArrayList<CreateBuyBackGoodsInfoDto>();
        try {
            list =  queryRecords(dao,
                    "selectCreateBuyBackGoodsInfo.seteclInfo", backGoodsEntity,newPage);

        } catch (Exception e) {
            log.error("新建采购返厂显示", e);
        }
        return list;
    }

    /**
     * 获取库房
     * 
     * @author liuqg
     * @param id
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<StockroomEntity> selectStockroomEntity() throws Exception {
        List<StockroomEntity> list = new ArrayList<StockroomEntity>();
        try {
            list = dao.queryForlist("buyBackGoods_sqlMap.selectStockroom", null);

        } catch (Exception e) {
            log.error("获取库房错误", e);
        }
        return list;
    }

    /**
     * 供应商发货地址
     * 
     * @author liuqg
     * @param supplierId
     *            供应商编码
     * @return List<SupplierAddressEntity>
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<SupplierAddressEntity> selecSupplierAddress(String supplierId,
            NewPage newPage) throws Exception {
        List list = new ArrayList();
        try {
            list = queryRecords(dao, "selecSupplierAddressForBuyBack.selectInfo",
                    supplierId, newPage);

        } catch (Exception e) {
            log.error("获取发货地址错误", e);
        }
        return list;
    }

    /**
     * 修改采购返厂显示用 by zhangzx
     */
    @Override
    public ModifyBuyBackGoodsInfoDto getModifyBuyBackGoodsInfo(String buyBackGoodsId)
            throws Exception {
        ModifyBuyBackGoodsInfoDto modifyBuyBackGoodsInfoDto = new ModifyBuyBackGoodsInfoDto();
        try {
            modifyBuyBackGoodsInfoDto = (ModifyBuyBackGoodsInfoDto) dao.queryForObject(
                    "buyBackGoods_sqlMap.selectModifyBuyBackGoodsInfo", buyBackGoodsId);
        } catch (Exception e) {
            log.error("修改采购返厂显示", e);
        }
        return modifyBuyBackGoodsInfoDto;
    }

    /**
     * 新建返厂单保存
     * 
     * @param backGoodsEntity
     * @param List
     *            <BuyBackGoodsDetailEntity> 返厂明细
     * @return
     * @throws Exception
     */
    public boolean addBugBackGoodSave(BuyBackGoodsEntity backGoodsEntity,
            List<BuyBackGoodsDetailEntity> list) throws Exception {

        try {
            dao.beginTransaction();
            /* 返厂 */
            dao.insert("buyBackGoods_sqlMap.insertBuyBackGoods", backGoodsEntity);
            /* 返厂明细 */
            for (int i = 0; i < list.size(); i++) {
                BuyBackGoodsDetailEntity backGoodsDetailEntity = (BuyBackGoodsDetailEntity) list
                        .get(i);
                dao.insert("buyBackGoods_sqlMap.insertBuyBackGoodsDetail",
                        backGoodsDetailEntity);
            }
            dao.commitTransaction();
            return true;
        } catch (Exception e) {
            log.error("新建返厂保存失败", e);
            return false;
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.debug("新建返厂保存失败", e);
                return false;
            }
        }
    }

    /**
     * 新建返厂单提交
     * 
     * @param backGoodsEntity
     * @param work
     *            产品总监加待办事务
     *@param List
     *            <BuyBackGoodsDetailEntity> 返厂明细
     * @return
     * @throws Exception
     */
    public boolean addBugBackGoodSubmit(BuyBackGoodsEntity backGoodsEntity,
            WorkEntity work, List<BuyBackGoodsDetailEntity> list) throws Exception {
        boolean isSuccess = false;
        try {
            dao.beginTransaction();
            /* 返厂 */
            dao.insert("buyBackGoods_sqlMap.insertBuyBackGoods", backGoodsEntity);
            /* 返厂明细 */
            for (int i = 0; i < list.size(); i++) {
                BuyBackGoodsDetailEntity backGoodsDetailEntity = (BuyBackGoodsDetailEntity) list
                        .get(i);
                dao.insert("buyBackGoods_sqlMap.insertBuyBackGoodsDetail",
                        backGoodsDetailEntity);
            }
            /* 待办事务 */
            isSuccess = NextAssessManWork(work);
            if (isSuccess == false) {
                return isSuccess;
            }
            dao.commitTransaction();
            isSuccess = true;
        } catch (Exception e) {
            log.error("新建返厂提交失败", e);
            isSuccess = false;
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.debug("新建返厂提交失败", e);
                isSuccess = false;
            }
        }
        return isSuccess;
    }

    /**
     * 
     * 修改返厂保存 by zzx
     * 
     */
    @Override
    public boolean modifyBuyBackGoodsSave(BuyBackGoodsEntity buyBackGoodsEntity,
            List<BuyBackGoodsDetailEntity> list) {
        boolean isSuccess = false;
        try {
            dao.beginTransaction();
            /* 返厂 */
            int count1 = dao.update("buyBackGoods_sqlMap.updateBuyBackGoodsSave",
                    buyBackGoodsEntity);

            String buyBackGoodsId = buyBackGoodsEntity.getId();

            dao.delete("buyBackGoods_sqlMap.deleteBuyBackGoodsDetail", buyBackGoodsId);
            /* 返厂明细 */
            for (int i = 0; i < list.size(); i++) {
                BuyBackGoodsDetailEntity backGoodsDetailEntity = (BuyBackGoodsDetailEntity) list
                        .get(i);
                dao.insert("buyBackGoods_sqlMap.insertBuyBackGoodsDetail",
                        backGoodsDetailEntity);
            }

            if (count1 != 1) {
                return isSuccess;
            }
            dao.commitTransaction();
            isSuccess = true;
        } catch (Exception e) {
            log.error("返厂修改保存失败", e);
            isSuccess = false;
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.debug("返厂修改保存失败", e);
                isSuccess = false;
            }
        }
        return isSuccess;
    }

    /**
     * 修改返厂提交
     * 
     * @author zhangzx
     */
    @Override
    public boolean modifyBuyBackGoodsSubmit(BuyBackGoodsEntity buyBackGoodsEntity,
            List<BuyBackGoodsDetailEntity> list, WorkEntity workEntity) {

        boolean isSuccess = false;
        try {
            dao.beginTransaction();
            /* 返厂 */
            int count1 = dao.update("buyBackGoods_sqlMap.updateBuyBackGoods",
                    buyBackGoodsEntity);

            String buyBackGoodsId = buyBackGoodsEntity.getId();

            dao.delete("buyBackGoods_sqlMap.deleteBuyBackGoodsDetail", buyBackGoodsId);
            /* 返厂明细 */
            for (int i = 0; i < list.size(); i++) {
                BuyBackGoodsDetailEntity backGoodsDetailEntity = (BuyBackGoodsDetailEntity) list
                        .get(i);
                dao.insert("buyBackGoods_sqlMap.insertBuyBackGoodsDetail",
                        backGoodsDetailEntity);
            }
            /* 待办事务 */
            boolean work = NextAssessManWork(workEntity);

            if (count1 != 1 || work == false) {
                return isSuccess;
            }
            dao.commitTransaction();
            isSuccess = true;
        } catch (Exception e) {
            log.error("返厂修改失败", e);
            isSuccess = false;
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.debug("返厂修改失败", e);
                isSuccess = false;
            }
        }
        return isSuccess;
    }

    /**
     *修改采购返厂显示返厂明细 by zhangzx
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<CreateBuyBackGoodsInfoDto> getModifyBuyBackGoodsDetail(
            ModifyBuyBackGoodsInfoDto modifyBuyBackGoodsInfoDto) {
        List list = new ArrayList();
        try {
            list = dao.queryForlist("buyBackGoods_sqlMap.selectBuyBackGoodsDetail",
                    modifyBuyBackGoodsInfoDto);
        } catch (Exception e) {
            log.error("修改采购返厂显示错误", e);
        }
        return list;
    }
    /**
     * 删除返厂
     * @param id
     * @return
     */
    @Override
    public boolean deleteBuyBackGoods(
            String id) {

		try {
			dao.beginTransaction();
			dao.delete("buyBackGoods_sqlMap.deleteBuyBackGoods", id);
			dao.delete("buyBackGoods_sqlMap.deleteBuyBackGoodsDetailT", id);
			dao.commitTransaction();
			return true;
		} catch (Exception e) {
			log.error("删除返厂错误", e);
			return false;
		} finally {
			try {
				dao.endTransaction();
			} catch (Exception e) {
				log.debug("删除返厂失败", e);
				return false;
			}
		}
    }
    
    
}
