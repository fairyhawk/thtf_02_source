/**
 * ClassName  MoveStockServiceImpl
 *
 * History
 * Create User: liuqg
 * Create Date: 2010-6-28
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.serviceimpl.stock;

import java.util.ArrayList;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.BaseService;
import cn.com.thtf.egov.cms.application.NewIDao;
import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.dto.ErrorMsgDto;
import cn.com.thtf.egov.cms.dto.MoveOutStockDto;
import cn.com.thtf.egov.cms.dto.MoveStockListDto;
import cn.com.thtf.egov.cms.dto.MoveStockProductDto;
import cn.com.thtf.egov.cms.dto.MoveStockSearchDto;
import cn.com.thtf.egov.cms.entity.MoveDetailEntity;
import cn.com.thtf.egov.cms.entity.MoveEntity;
import cn.com.thtf.egov.cms.entity.ProductTypeEntity;
import cn.com.thtf.egov.cms.entity.StockEntity;
import cn.com.thtf.egov.cms.entity.StockroomAddressEntity;
import cn.com.thtf.egov.cms.entity.StockroomEntity;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.entity.WorkEntity;
import cn.com.thtf.egov.cms.dto.MoveStockAssessDto;
import cn.com.thtf.egov.cms.iservice.stock.IMoveStockService;
import cn.com.thtf.egov.cms.iservice.work.ITodoWorkService;
import cn.com.thtf.egov.cms.serviceimpl.work.TodoWorkService;
import cn.com.thtf.egov.cms.util.Container;

/**
 * 移库单
 * 
 * @author liuqg
 */

public class MoveStockServiceImpl extends BaseService implements IMoveStockService {
    /** log */
    private static Logger log = LoggerFactory.getLogger(MoveStockServiceImpl.class);
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
        MoveStockServiceImpl.log = log;
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
     * 查询移库单评审
     * 
     * @author jiangmx
     * @param moveStockId
     * @return MoveStockAssessDto
     */
    @Override
    public MoveStockAssessDto getMoveStockById(String moveStockId) {
        MoveStockAssessDto assess = null;

        try {
            assess = (MoveStockAssessDto) dao.queryForObject(
                    "moveStock_sqlMap.selectMoveStockInfo", moveStockId);
        } catch (Exception e) {
            log.error("移库评审信息失败", e);
        }
        return assess;

    }

    /**
     * 查询移出库房信息
     * 
     * @author jiangmx
     * @param assessDto
     * @return MoveOutStockDto
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public List<MoveOutStockDto> getMoveOutStockInfo(MoveStockAssessDto assessDto) {

        List result = null;

        try {
            result = dao.queryForlist("moveStock_sqlMap.selectOutStockInfo", assessDto);
        } catch (Exception e) {
            log.error("查询移出库房信息失败", e);
        }
        return result;

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
     * 移库评审不发待办事务
     * 
     * @author jiangmx
     * @param moveStockAssessDto
     * @param workself
     * @return boolean
     */
    @Override
    public boolean updateAssessNoWork(MoveStockAssessDto moveStockAssessDto,
            WorkEntity workself, List<StockEntity> stockEntities, ErrorMsgDto errorMsgDto) {
        int count = -1;
        boolean isSuccess = false;

        try {
            dao.beginTransaction();
            count = dao.update("moveStock_sqlMap.updateMoveStockAssess",
                    moveStockAssessDto);

            for (int i = 0; i < stockEntities.size(); i++) {

                /* 更新产品的冻结数 */
                StockEntity stockEntity = (StockEntity) stockEntities.get(i);
                int recount = dao.update("moveStock_sqlMap.updateLockNum", stockEntity);
                if (recount != 1) {
                    errorMsgDto.setErrmsg("产品库存已被其他用户修改");
                    return false;
                }
            }

            boolean assessManself = NextAssessManWork(workself);
            if (count != 1 || assessManself == false) {
                return isSuccess;
            }
            dao.commitTransaction();

            isSuccess = true;
        } catch (Exception e) {
            log.error("移库评审未通过失败", e);
            isSuccess = false;
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.debug("移库评审未通过失败", e);
                isSuccess = false;
            }
        }
        return isSuccess;
    }

    /**
     * 移库评审待办事务自减
     * 
     * @author jiangmx
     * @param moveStockAssessDto
     * @param work
     * @param workself
     * @return boolean
     */
    @Override
    public boolean updateMoveStockAssess(MoveStockAssessDto moveStockAssessDto,
            WorkEntity workself) {
        int count = -1;
        boolean isSuccess = false;

        try {
            dao.beginTransaction();
            count = dao.update("moveStock_sqlMap.updateMoveStockAssess",
                    moveStockAssessDto);
            boolean assessManself = NextAssessManWork(workself);
            if (count != 1 || assessManself == false) {
                return isSuccess;
            }
            dao.commitTransaction();

            isSuccess = true;
        } catch (Exception e) {
            log.error("移库评审通过失败", e);
            isSuccess = false;
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.debug("移库评审通过失败", e);
                isSuccess = false;
            }
        }
        return isSuccess;
    }

    /**
     * 移库列表初始化 by zhangzx
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<MoveStockListDto> getMoveStockList(NewPage page, UserEntity user) {
        List result = null;
        try {
            result = queryRecords(dao, "selectMoveStockList.selectMoveStockList", user,
                    page);
        } catch (Exception e) {
            log.error("获取移库单一览错误:", e);
        }
        return result;
    }

    /**
     * 获得产品分类列表 by zhangzx
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<ProductTypeEntity> getProductType() {
        List result = null;
        try {
            result = dao.queryForlist("moveStock_sqlMap.selectProductTypeList", null);
        } catch (Exception e) {
            log.error("检索产品分类错误:", e);
        }
        return result;
    }

    /**
     * 获得所有库房信息(不含虚拟库) by zhangzx
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<StockroomEntity> getStockroom() {
        List result = null;
        try {
            result = dao.queryForlist("moveStock_sqlMap.selectStockroom", null);
        } catch (Exception e) {
            log.error("检索库房信息错误:", e);
        }
        return result;
    }

    /**
     * 根据条件检索移库单 by zhangzx
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<MoveStockListDto> getMoveStockListByCondition(NewPage page,
            MoveStockSearchDto moveStockSearchDto) {
        List result = null;
        try {
            result = queryRecords(dao,
                    "selectMoveStockListByCondition.selectMoveStockListByCondition",
                    moveStockSearchDto, page);
        } catch (Exception e) {
            log.error("获取检索移库单一览错误:", e);
        }
        return result;
    }

    /**
     * 根据库房号查询收货地址 by zhangzx
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<StockroomAddressEntity> selectSendGoodsAddress(NewPage page,
            String stockroomId) {
        List result = null;
        try {
            result = queryRecords(
                    dao,
                    "selectSendGoodsAddressByStockroomId.selectSendGoodsAddressByStockroomId",
                    stockroomId, page);
        } catch (Exception e) {
            log.error("获取收货地址一览错误:", e);
        }
        return result;
    }

    /**
     * 修改移库保存
     * 
     * by zhangzx
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean modifyMoveStockSave(MoveEntity modifyMoveStockDto, List list) {
        boolean isSuccess = false;
        try {
            dao.beginTransaction();
            /* 移库 */
            int count1 = dao.update("moveStock_sqlMap.updateMoveStockSave",
                    modifyMoveStockDto);

            String moveStockId = modifyMoveStockDto.getId();

            dao.delete("moveStock_sqlMap.deleteMoveStockDetail", moveStockId);
            /* 移库明细 */
            for (int i = 0; i < list.size(); i++) {
                MoveDetailEntity moveDetailEntity = (MoveDetailEntity) list.get(i);
                dao.insert("moveStock_sqlMap.insertMoveStockDetail", moveDetailEntity);
            }

            if (count1 != 1) {
                return isSuccess;
            }
            dao.commitTransaction();
            isSuccess = true;
        } catch (Exception e) {
            log.error("移库修改保存失败", e);
            isSuccess = false;
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.debug("移库修改保存失败", e);
                isSuccess = false;
            }
        }
        return isSuccess;
    }

    /**
     * 修改移库提交(待办事务)
     * 
     * by zhangzx
     * */
    @SuppressWarnings("unchecked")
    @Override
    public boolean modifyMoveStockSubmitWork(MoveEntity modifyMoveStockDto, List list,
            WorkEntity workEntity, List<StockEntity> stockEntities,
            ErrorMsgDto errorMsgDto) {
        boolean isSuccess = false;
        try {
            dao.beginTransaction();
            /* 移库 */
            int count1 = dao.update("moveStock_sqlMap.updateMoveStock",
                    modifyMoveStockDto);
            int count2 = 0;

            String moveStockId = modifyMoveStockDto.getId();

            dao.delete("moveStock_sqlMap.deleteMoveStockDetail", moveStockId);
            /* 移库明细 库存表 */
            for (int i = 0; i < list.size(); i++) {
                MoveDetailEntity moveDetailEntity = (MoveDetailEntity) list.get(i);
                dao.insert("moveStock_sqlMap.insertMoveStockDetail", moveDetailEntity);

                StockEntity stockEntity = stockEntities.get(i);

                count2 = dao.update("moveStock_sqlMap.updateStockLockNum", stockEntity);

                if (count2 != 1) {
                    errorMsgDto.setErrmsg("产品库存已被其他用户修改");
                    return isSuccess;
                }
            }
            /* 待办事务 */
            boolean work = NextAssessManWork(workEntity);

            if (count1 != 1 || work == false) {
                return isSuccess;
            }
            dao.commitTransaction();
            isSuccess = true;
        } catch (Exception e) {
            log.error("移库修改提交失败", e);
            isSuccess = false;
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.debug("移库修改提交失败", e);
                isSuccess = false;
            }
        }
        return isSuccess;
    }

    /**
     * 修改移库提交(无待办事务)
     * 
     * by zhangzx
     * */
    @SuppressWarnings("unchecked")
    @Override
    public boolean modifyMoveStockSubmitNoWork(MoveEntity modifyMoveStockDto, List list,
            List<StockEntity> stockEntities, ErrorMsgDto errorMsgDto) {
        boolean isSuccess = false;
        try {
            dao.beginTransaction();
            /* 移库 */
            int count1 = dao.update("moveStock_sqlMap.updateMoveStock",
                    modifyMoveStockDto);
            int count2 = 0;

            String moveStockId = modifyMoveStockDto.getId();

            dao.delete("moveStock_sqlMap.deleteMoveStockDetail", moveStockId);
            /* 移库明细 库存表 */
            for (int i = 0; i < list.size(); i++) {
                MoveDetailEntity moveDetailEntity = (MoveDetailEntity) list.get(i);
                dao.insert("moveStock_sqlMap.insertMoveStockDetail", moveDetailEntity);

                StockEntity stockEntity = stockEntities.get(i);

                count2 = dao.update("moveStock_sqlMap.updateStockLockNum", stockEntity);

                if (count2 != 1) {
                    errorMsgDto.setErrmsg("产品库存已被其他用户修改");
                    return isSuccess;
                }
            }
            if (count1 != 1) {
                return isSuccess;
            }
            dao.commitTransaction();
            isSuccess = true;
        } catch (Exception e) {
            log.error("移库修改提交失败", e);
            isSuccess = false;
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.debug("移库修改提交失败", e);
                isSuccess = false;
            }
        }
        return isSuccess;
    }

    /**
     * 检索产品信息
     * 
     * @author liuqg
     * @param moveStockProductDto
     * @param newPage
     * @return
     */

    @SuppressWarnings("unchecked")
    public List<MoveOutStockDto> selectProductList(
            MoveStockProductDto moveStockProductDto, NewPage newPage) {
        List list = new ArrayList<MoveOutStockDto>();
        try {
            list = queryRecords(dao, "selectMoveProducts.selectInfo",
                    moveStockProductDto, newPage);

        } catch (Exception e) {
            log.debug("检索移库产品信息错误" + e);
        }

        return list;
    }

    /**
     * 新建移库单保存
     * 
     * @author liuqg
     * @param MoveStockAssessDto
     *            adddto
     * @param List
     *            <MoveDetailEntity>
     * @return
     */
    @Override
    public boolean addMoveStockSave(MoveStockAssessDto adddto, List<MoveDetailEntity> list) {

        try {
            dao.beginTransaction();
            /* 移库单 */
            dao.insert("moveStock_sqlMap.insertMoveStock", adddto);
            /* 移库单明细 */
            for (int i = 0; i < list.size(); i++) {
                MoveDetailEntity detailEntity = (MoveDetailEntity) list.get(i);
                dao.insert("moveStock_sqlMap.insertMoveStockDetail", detailEntity);
            }
            dao.commitTransaction();
            return true;
        } catch (Exception e) {
            log.error("新建移库单保存失败", e);
            return false;
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.debug("新建移库单保存失败", e);
                return false;
            }
        }
    }

    /**
     * 新建移库单提交
     * 
     * @author liuqg
     * @param moveStockProductDto
     *            移库单
     * @param WorkEntity
     *            work 待办事务
     * @param List
     *            <MoveDetailEntity> moveDetailEntities 移库明细'
     * @param List
     *            <StockEntity> stockEntities 库存
     * @return
     */
    @Override
    public boolean addMoveStockSubmit(MoveStockAssessDto adddto, WorkEntity work,
            List<MoveDetailEntity> moveDetailEntities, List<StockEntity> stockEntities,
            ErrorMsgDto errorMsgDto) {
        boolean isSuccess = false;
        try {
            dao.beginTransaction();
            /* 移库单 */
            dao.insert("moveStock_sqlMap.insertMoveStock", adddto);
            for (int i = 0; i < moveDetailEntities.size(); i++) {
                MoveDetailEntity detailEntity = (MoveDetailEntity) moveDetailEntities
                        .get(i);
                /* 移库单明细 插入 */
                dao.insert("moveStock_sqlMap.insertMoveStockDetail", detailEntity);

                /* 更新产品的冻结数 */
                StockEntity stockEntity = (StockEntity) stockEntities.get(i);
                int count = dao
                        .update("moveStock_sqlMap.updateStockLockNum", stockEntity);
                if (count != 1) {
                    log.error("产品库存已被其他用户修改");
                    errorMsgDto.setErrmsg("产品库存已被其他用户修改");
                    return false;
                }
            }
            /* 待办事务 采购提交时发待办事务 */
            if (2 == adddto.getStatus()) {
                isSuccess = NextAssessManWork(work);
                if (isSuccess == false) {
                    errorMsgDto.setErrmsg("待办事务添加失败");
                    return isSuccess;
                }
            }
            dao.commitTransaction();
            isSuccess = true;
        } catch (Exception e) {
            log.error("新建移库单提交失败", e);
            isSuccess = false;
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.debug("新建移库单提交失败", e);
                isSuccess = false;
            }
        }
        return isSuccess;
    }

    /**
     * 删除移库单
     * 
     * @param moveStockId
     *            移库单号
     * @return
     */
    public boolean deleteMOveStock(String moveStockId) {
        try {
            dao.beginTransaction();
            dao.delete("moveStock_sqlMap.deleteMoveStockDetail", moveStockId);
            dao.delete("moveStock_sqlMap.deleteMoveStock", moveStockId);
            dao.commitTransaction();
            return true;
        } catch (Exception e) {
            log.error("删除移库单失败", e);
            return false;
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e2) {
                log.error("删除移库单失败", e2);
                return false;
            }
        }
    }

    /**
     * 查看和评审查看时用
     * 
     * @author liuqg
     * @param MoveStockAssessDto
     *            assessDto
     */
    @SuppressWarnings("unchecked")
    public List<MoveOutStockDto> getMoveOutStockInfoView(MoveStockAssessDto assessDto) {
        List result = new ArrayList<MoveOutStockDto>();

        try {
            result = dao.queryForlist("moveStock_sqlMap.selectMoveProductsView",
                    assessDto);
        } catch (Exception e) {
            log.error("查看移出库房信息失败", e);
        }
        return result;
    }

    /**
     * 获得产品库存信息，验证移库数和借出数时用
     * @param stockRoomId
     * @param productId
     * @return
     * @throws Exception
     */
    @Override
    public MoveOutStockDto  getStockByStockRoomAndProduct(MoveStockProductDto moveStockProductDto) throws Exception {
        MoveOutStockDto dto = new MoveOutStockDto() ;
        log.info(moveStockProductDto.getOutStockroomId()+"--"+moveStockProductDto.getProSerieId());
        try {
            dto = (MoveOutStockDto)dao.queryForObject("moveStock_sqlMap.getStockByStockRoomAndProduct",
                    moveStockProductDto);
        } catch (Exception e) {
            log.error("查看产品库存信息错误", e);
        } 
        return dto;
    }

}
