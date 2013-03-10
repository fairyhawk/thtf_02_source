/**
 * ClassName  IMoveStockService
 *
 * History
 * Create User: liuqg
 * Create Date: 2010-6-28
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.iservice.stock;

import cn.com.thtf.egov.cms.dto.MoveStockAssessDto;
import java.util.List;

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

/**
 * 移库
 * 
 * @author liuqg
 */

public interface IMoveStockService {

    /**
     * 查询移库评审
     * 
     * @author 蒋名星
     * @param moveStockId
     * @return MoveStockAssessDto
     * @throws Exception
     */
    public MoveStockAssessDto getMoveStockById(String moveStockId) throws Exception;

    /**
     * 查询移出库房信息
     * 
     * @author 蒋名星
     * @param assessDto
     * @return MoveOutStockDto
     * @throws Exception
     */
    public List<MoveOutStockDto> getMoveOutStockInfo(MoveStockAssessDto assessDto)
            throws Exception;

    /**
     * 移库管理评审办事务自减
     * 
     * @author 蒋名星
     * @param moveStockAssessDto
     * @param work
     * @param workself
     * @return boolean
     * @throws Exception
     */
    public boolean updateMoveStockAssess(MoveStockAssessDto moveStockAssessDto,
            WorkEntity workself) throws Exception;

    /**
     * 移库管理评审不发待办事务
     * 
     * @author 蒋名星
     * @param moveStockAssessDto
     * @param work
     * @param workself
     * @return boolean
     * @throws Exception
     */
    public boolean updateAssessNoWork(MoveStockAssessDto moveStockAssessDto,
            WorkEntity workself, List<StockEntity> stockEntities, ErrorMsgDto errorMsgDto)
            throws Exception;

    /**
     * 移库列表初始化
     * 
     * @param page
     * @param user
     * @return
     * @throws Exception
     * @author zhangzx
     */
    public List<MoveStockListDto> getMoveStockList(NewPage page, UserEntity user)
            throws Exception;

    /**
     * 获得产品分类列表
     * 
     * @return
     * @throws Exception
     * @author zhangzx
     */
    public List<ProductTypeEntity> getProductType() throws Exception;

    /**
     * 获得所有库房信息
     * 
     * @return
     * @throws Exception
     * @author zhangzx
     */
    public List<StockroomEntity> getStockroom() throws Exception;

    /**
     * 根据条件检索移库单
     * 
     * @param page
     * @param moveStockSearchDto
     * @return
     * @throws Exception
     * @author zhangzx
     */
    public List<MoveStockListDto> getMoveStockListByCondition(NewPage page,
            MoveStockSearchDto moveStockSearchDto) throws Exception;

    /**
     * 修改移库保存
     * 
     * @param modifyMoveStockDto
     * @param list
     * @return
     * @throws Exception
     * @author zhangzx
     */
    @SuppressWarnings("unchecked")
    public boolean modifyMoveStockSave(MoveEntity modifyMoveStockDto, List list)
            throws Exception;

    /**
     * 修改移库提交(发待办事务)
     * 
     * @author zhangzx
     */
    @SuppressWarnings("unchecked")
    public boolean modifyMoveStockSubmitWork(MoveEntity modifyMoveStockDto, List list,
            WorkEntity workEntity, List<StockEntity> stockEntities,
            ErrorMsgDto errorMsgDto);

    /**
     * 修改移库提交(无待办事务)
     * 
     * @author zhangzx
     */
    @SuppressWarnings("unchecked")
    public boolean modifyMoveStockSubmitNoWork(MoveEntity modifyMoveStockDto, List list,
            List<StockEntity> stockEntities, ErrorMsgDto errorMsgDto);

    /**
     * 根据移入库房id查询收货地址
     * 
     * @param page
     * @param stockroomId
     * @return
     * @throws Exception
     * @author zhangzx
     */
    public List<StockroomAddressEntity> selectSendGoodsAddress(NewPage page,
            String stockroomId) throws Exception;

    /**
     * 检索产品信息
     * 
     * @author liuqg
     * @param moveStockProductDto
     * @param newPage
     * @return
     * @throws Exception
     */
    public List<MoveOutStockDto> selectProductList(
            MoveStockProductDto moveStockProductDto, NewPage newPage) throws Exception;

    /**
     * 新建移库单保存
     * 
     * @author liuqg
     * @param moveStockProductDto
     * @param newPage
     * @return
     * @throws Exception
     */
    public boolean addMoveStockSave(MoveStockAssessDto adddto, List<MoveDetailEntity> list)
            throws Exception;

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
     * @throws Exception
     */

    public boolean addMoveStockSubmit(MoveStockAssessDto adddto, WorkEntity work,
            List<MoveDetailEntity> moveDetailEntities, List<StockEntity> stockEntities,
            ErrorMsgDto errorMsgDto) throws Exception;

    /**
     * 删除移库单
     * 
     * @param moveStockId
     *            移库单号
     * @return
     */
    public boolean deleteMOveStock(String moveStockId) throws Exception;

    /**
     * 查询移出库房信息 查看和评审时用
     * 
     * @author liuqg
     * @param assessDto
     * @return MoveOutStockDto
     * @throws Exception
     */
    public List<MoveOutStockDto> getMoveOutStockInfoView(MoveStockAssessDto assessDto)
            throws Exception;
    /**
     * 获得产品库存信息，验证移库数和借出数时用
     * @param stockRoomId
     * @param productId
     * @return
     * @throws Exception
     */
    public MoveOutStockDto getStockByStockRoomAndProduct(MoveStockProductDto moveStockProductDto)
    throws Exception;
    

}
