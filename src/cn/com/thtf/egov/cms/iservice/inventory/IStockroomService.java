/**
 * ClassName  IStockroomService
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-4-22
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.iservice.inventory;

import java.util.List;

import cn.com.thtf.egov.cms.dto.StockroomAndAddressDto;
import cn.com.thtf.egov.cms.entity.StockEntity;
import cn.com.thtf.egov.cms.entity.StockroomEntity;

/**
 * 库房信息
 * 
 * @author Lubo
 */

public interface IStockroomService {

    /**
     * 获取指定库房的库存信息
     * 
     * @param stockroomId
     * @return
     */
    public List<StockEntity> getStockByStockroomId(String stockroomId);

    /**
     * 获取所有正常库和虚拟库
     * 
     * @return
     */
    public List<StockroomEntity> getTypicallyAndVirtual();

    /**
     * 获取指定产品分类正常库和虚拟库
     * 
     * @param productType
     * @return
     */
    public List<StockroomEntity> getTypicallyAndVirtual(Integer productType);

    /**
     * 获取本合同的没有未备过货的库房
     * 
     * @param sellContractId
     * @param productType
     * @return
     */
    public List<StockroomEntity> getNotPrepareStockroom(String sellContractId,
            Integer productType);

    /**
     * 获取所有库房
     * 
     * @return
     */
    public List<StockroomEntity> getAllStockRoom();

    /**
     * 查询所有正常库和索赔库(如果有参数则查询相对应的库房收货地址)
     * 
     * @return
     */
    public List<StockroomAndAddressDto> quertConditionStockRoom(String sookRoomId);
    /**
     * 退货单根据当前库房ID查询在同一部门下的所有库房
     * 
     * @return
     */
    public List<StockroomAndAddressDto> quertConditionStockRoom1(String sookRoomId);
}
