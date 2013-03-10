/**
 * ClassName  IBuyBackGoodsService
 *
 * History
 * Create User: liuqg
 * Create Date: 2010-6-11
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.iservice.purchase;

import java.util.List;

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

/**
 * 采购返厂
 * @author liuqg
 */

public interface IBuyBackGoodsService {
	
	/**
	 *查询返厂评审
	 * 
	 * @author 蒋名星
	 * @param buyBackGoodsId
	 * @return BuyBackGoodsAssessDto
	 * @throws Exception
	 */
	public BuyBackGoodsAssessDto getBuyBackGoodsById(String buyBackGoodsId)
	         throws Exception;
	
		/**
	 * 采购返厂评审发待办事务
	 * 
	 * @author 蒋名星
	 * @param buyBackGoodsAssessDto
	 * @param work
	 * @param workself
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateBuyBackGoodsAssess(BuyBackGoodsAssessDto buyBackGoodsAssessDto,
			WorkEntity work, WorkEntity workself) throws Exception;

	/**
	 * 采购返厂评审不发待办事务
	 * 
	 * @author 蒋名星
	 * @param buyBackGoodsAssessDto
	 * @param work
	 * @param workself
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateAssessNoWork(BuyBackGoodsAssessDto buyBackGoodsAssessDto,
			WorkEntity workself) throws Exception;
	
 /**
     * 返厂单列表初始化
     * 
     * @param page
     * @param user
     * @return
     * @throws Exception
     * @author zhangzx
     */
    public List<BuyBackGoodsListDto> getBuyBackGoodsList(NewPage page, UserEntity user)
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
     * 根据条件查询返厂单列表
     * @param page
     * @param sellBackSearchDto
     * @return
     * @throws Exception
     * @author zhangzx
     */
    public List<BuyBackGoodsListDto> getBuyBackGoodsListByCondition(NewPage page,
            BuyBackGoodsSearchDto buyBackGoodsSearchDto) throws Exception;
    
    /**
     * 修改采购返厂显示用
     * 
     * @author zhangzx
     * @param  buyBackGoodsId              
     * @return 
     * @throws Exception
     */

    public ModifyBuyBackGoodsInfoDto getModifyBuyBackGoodsInfo(String buyBackGoodsId)
            throws Exception;
    /**
     * 修改采购返厂显示返厂明细
     * @param buyBackGoodsEntity
     * @return
     * @author zhangzx
     */
    public List<CreateBuyBackGoodsInfoDto> getModifyBuyBackGoodsDetail(ModifyBuyBackGoodsInfoDto modifyBuyBackGoodsInfoDto);
    /**
     * 修改返厂保存
     * @param buyBackGoodsEntity
     * @return
     * @author zhangzx
     */
    public boolean modifyBuyBackGoodsSave(BuyBackGoodsEntity buyBackGoodsEntity,List<BuyBackGoodsDetailEntity> list);
    /**
     * 修改返厂提交
     * @author zhangzx
     */
    public boolean modifyBuyBackGoodsSubmit(BuyBackGoodsEntity buyBackGoodsEntity,List<BuyBackGoodsDetailEntity> list,WorkEntity workEntity);

	/**
	 * 新建采购返厂显示用
	 * 
	 * @author liuqg
	 * @param id
	 *            入库单号
	 * @return List<CreateBuyBackGoodsInfo>
	 * @throws Exception
	 */

	public CreateBuyBackGoodsInfoDto selectInStockInfo(String id)
			throws Exception;

	/**
	 * 获取库房
	 * 
	 * @author liuqg
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<StockroomEntity> selectStockroomEntity() throws Exception;

	/**
	 * 新建采购返厂显示用 表格
	 * 
	 * @author liuqg
	 * @param id
	 *            入库单号
	 * @return List<CreateBuyBackGoodsInfo>
	 * @throws Exception
	 */

	public List<CreateBuyBackGoodsInfoDto> selectCreateBuyBackGoodsInfo(
			BuyBackGoodsEntity backGoodsEntity,NewPage newPage) throws Exception;
	/**
	 * 供应商发货地址
	 * 
	 * @author liuqg
	 * @param supplierId
	 *            供应商编码
	 * @return List<SupplierAddressEntity>
	 * @throws Exception
	 */
	
	public List<SupplierAddressEntity> selecSupplierAddress(String supplierId,NewPage newPage)throws Exception;
	/**
	 * 新建返厂单提交
	 * 
	 * @param backGoodsEntity
	 * @param work
	 *            产品总监加待办事务
	 * @param List
	 *            <BuyBackGoodsDetailEntity> 返厂明细
	 * @return
	 * @throws Exception
	 */
	public boolean addBugBackGoodSubmit(BuyBackGoodsEntity backGoodsEntity,
			WorkEntity work, List<BuyBackGoodsDetailEntity> list)
			throws Exception;

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
			List<BuyBackGoodsDetailEntity> list) throws Exception;
	/**
	 * 删除返厂
	 * @author liuqg
	 * @param id
	 * @return
	 * @throws Exception
	 */
	  public boolean deleteBuyBackGoods(
	            String id) throws Exception ;
}
