/**
 * ClassName  IBuyBackService
 *
 * History
 * Create User: liuqg
 * Create Date: 2010-6-9
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.iservice.purchase;

import java.util.List;

import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.dto.AddBuyBackListDto;

import cn.com.thtf.egov.cms.dto.BuyBackListDto;
import cn.com.thtf.egov.cms.dto.BuyBackSearchDto;
import cn.com.thtf.egov.cms.dto.PaymentInfoForBuyBackDto;
import cn.com.thtf.egov.cms.entity.ProductTypeEntity;
import cn.com.thtf.egov.cms.entity.UserEntity;

/**
 * 采购退款
 * 
 * @author liuqg
 */

public interface IBuyBackService {

	/**
	 * 采购退款添加
	 * 
	 * @author liuqg
	 * @param backListDto
	 * @return
	 * @throws Exception
	 */
	public boolean addBuyBack(AddBuyBackListDto backListDto) throws Exception;

	/**
	 *查看采购退款
	 * 
	 * @author 蒋名星
	 * @param buyBackId
	 * @return BuyBackListDto
	 * @throws Exception
	 */
	public BuyBackListDto selectBuyBackById(String buyBackId) throws Exception;

	/**
	 *采购退款删除
	 * 
	 * @author 蒋名星
	 * @param buyBackId
	 * @return
	 * 
	 */
	public boolean removeBuyBack(String buyBackId,UserEntity user) throws Exception;

	/**
	 *查询付款单信息
	 * 
	 * @author liuqg
	 * @param id
	 * @return PaymentInfoForBuyBackDto
	 * @throws Exception
	 */
	public PaymentInfoForBuyBackDto selectPaymentById(String id)
			throws Exception;

	/**
	 * 查询采购退款列表
	 * 
	 * @param page
	 * @param user
	 * @return
	 * @throws Exception
	 * @author zhangzx
	 */
	public List<BuyBackListDto> getBuyBackList(NewPage page, UserEntity user)
			throws Exception;

	/**
	 * 获得产品分类
	 * 
	 * @return
	 * @throws Exception
	 * @author zhangzx
	 */
	public List<ProductTypeEntity> getProductType() throws Exception;

	/**
	 * 根据检索条件查找记录
	 * 
	 * @param page
	 * @param buyBackSearchDto
	 * @return
	 * @throws Exception
	 * @author zhangzx
	 */
	public List<BuyBackListDto> getBuyBackListByCondition(NewPage page,
			BuyBackSearchDto buyBackSearchDto) throws Exception;

	/**
	 * 采购退款修改
	 * 
	 * @author liuqg
	 * @param backListDto
	 * @return
	 * @throws Exception
	 */
	public boolean modifyBuyBack(AddBuyBackListDto backListDto)
			throws Exception;
}
