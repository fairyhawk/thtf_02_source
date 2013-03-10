/**
 * ClassName  ISellBackService
 *
 * History
 * Create User: liuqg
 * Create Date: 2010-5-31
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.iservice.sell;

import java.math.BigDecimal;
import java.util.List;

import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.dto.CustomerProInfoListDto;
import cn.com.thtf.egov.cms.dto.SellBackAddDto;
import cn.com.thtf.egov.cms.dto.SellBackAssessDto;
import cn.com.thtf.egov.cms.dto.SellBackForMreturnDto;
import cn.com.thtf.egov.cms.dto.SellBackListDto;
import cn.com.thtf.egov.cms.dto.SellBackSearchDto;
import cn.com.thtf.egov.cms.entity.CustomerLinkmanEntity;
import cn.com.thtf.egov.cms.entity.MreturnEntity;
import cn.com.thtf.egov.cms.entity.ProductTypeEntity;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.entity.WorkEntity;

/**
 * 销售退款管理
 * 
 * @author liuqg
 */

public interface ISellBackService {
	/**
	 * 根据登录经理的ID查询退款列表 by zzx
	 * 
	 * @param page
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<SellBackListDto> getSellBackList(NewPage page, UserEntity user)
			throws Exception;

	/**
	 * 获得产品分类by zzx
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<ProductTypeEntity> getProductType() throws Exception;

	/**
	 * 根据检索条件查找记录by zzx
	 * 
	 * @param page
	 * @param sellBackSearchDto
	 * @return
	 * @throws Exception
	 */
	public List<SellBackListDto> getSellBackListByCondition(NewPage page,
			SellBackSearchDto sellBackSearchDto) throws Exception;

	/**
	 * 退款确认 by zzx
	 * 
	 * @param sellBack
	 * @return
	 */
	public boolean confirmSellBack(SellBackListDto sellBack) throws Exception;

	/**
	 * 退款打印 by zzx
	 * 
	 * @param sellBack
	 * @return
	 * @throws Exception
	 */
	public boolean printSellBack(String sellBackId, WorkEntity work)
			throws Exception;

	/**
	 * 查询退款表信息和客户信息 by zzx
	 * 
	 * @param sellBackId
	 * @return
	 * @throws Exception
	 */
	public SellBackAssessDto getSellBackCustomerInfo(String sellBackId)
			throws Exception;

	/**
	 * 查询产品部门名称 by zzx
	 * 
	 * @param productTypeId
	 * @return
	 * @throws Exception
	 */
	public String getProductDeptName(String productTypeId) throws Exception;

	/**
	 *查询退款评审
	 * 
	 * @author 蒋名星
	 * @param sellBackId
	 * @return SellBackListDto
	 * @throws Exception
	 */
	public SellBackAssessDto getSellBackById(String sellBackId)
			throws Exception;

	/**
	 * 销售退款评审发待办事务
	 * 
	 * @author 蒋名星
	 * @param sellBackAssessDto
	 * @param work
	 * @param workself
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateSellBackAssess(SellBackAssessDto sellBackAssessDto,
			WorkEntity work, WorkEntity workself) throws Exception;

	/**
	 * 销售退款评审不发待办事务
	 * 
	 * @author 蒋名星
	 * @param sellBackAssessDto
	 * @param work
	 * @param workself
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateAssessNoWork(SellBackAssessDto sellBackAssessDto,
			WorkEntity workself) throws Exception;

	/**
	 *销售退款删除
	 * 
	 * @author 蒋名星
	 * @param sellBackId
	 * @return
	 * 
	 */
	public boolean removeSellBack(String sellBackId) throws Exception;

	// add by liu

	/**
	 * 根据回款单显示退款申请信息
	 * 
	 * @author liuqg
	 * @param mreturnId
	 * @return SellBackForMreturnDto
	 * @throws Exception
	 */

	public SellBackForMreturnDto selectInfoByMreturnId(String mreturnId)
			throws Exception;

	/**
	 * 根据回款单获得产品分类预收金额合计
	 * 
	 * @author liuqg
	 * @param mreturnId
	 * @return BigDecimal
	 * @throws Exception
	 */

	public BigDecimal selectProdMoneySumByMreturnId(MreturnEntity mreturnEntity)
			throws Exception;

	/**
	 * 根据回款单获得产品分类预收金额合计
	 * 
	 * @author liuqg
	 * @param mreturnId
	 * @return BigDecimal
	 * @throws Exception
	 */

	public BigDecimal selectProdCanBackMoneyByMreturnId(MreturnEntity mreturnEntity)
			throws Exception;

	/**
	 * 根据回款单获得产品分类预收金额合计
	 * 
	 * @author liuqg
	 * @param mreturnId
	 * @return List<CustomerLinkmanEntity>
	 * @throws Exception
	 */

	public List<CustomerLinkmanEntity> selectCustomerInfoByMId(String mreturnId)
			throws Exception;

	/**
	 * 获得联系人
	 * 
	 * @author liuqg
	 * @param linkManId
	 * @return CustomerLinkmanEntity
	 */
	public CustomerLinkmanEntity getLinkMsgByLinkManId(String linkManId)
			throws Exception;

	/**
	 * 获得回款信息
	 * 
	 * @author liuqg
	 * @param mreturnId
	 * @throws Exception
	 */
	public MreturnEntity selectMreturnById(String mreturnId) throws Exception;

	/**
	 * 提交退款
	 * 
	 * @author liuqg
	 * @param backAddDto
	 * @param work
	 * @throws Exception
	 */
	public boolean insertSellBackSubmit(SellBackAddDto backAddDto,
			WorkEntity work) throws Exception;

	/**
	 * 保存退款
	 * 
	 * @author liuqg
	 * @param backAddDto
	 * @throws Exception
	 */
	public boolean insertSellBackSave(SellBackAddDto backAddDto)
			throws Exception;

	/**
	 * 获得产品分类列表显示
	 * 
	 * @author liuqg
	 * @param mreturnEntity
	 * @throws Exception
	 */
	public List<CustomerProInfoListDto> selectCustomerProInfoList(
			MreturnEntity mreturnEntity) throws Exception;

	/**
	 * 修改退款-保存
	 * 
	 * @author liuqg
	 * @param assessDto
	 * @throws Exception
	 */

	public boolean modifySellBackSave(SellBackAssessDto assessDto)
			throws Exception;

	/**
	 * 修改退款-提交
	 * 
	 * @author liuqg
	 * @param assessDto
	 * @param work
	 * @throws Exception
	 */

	public boolean modifySellBackSubmit(SellBackAssessDto assessDto,
			WorkEntity work) throws Exception;

	/**
	 * 评审页面显示客户信用信息
	 * 
	 * @author liuqg
	 * @param customerId
	 * @return List<SellBackForMreturnDto>
	 * @throws Exception
	 */

	public List<SellBackForMreturnDto> selectCustomerCreditList(
			String customerId) throws Exception;
	
	/**
	 * 评审页面显示客户可退款金额
	 * 
	 * @author liuqg
	 * @param customerId
	 * @return List<SellBackForMreturnDto>
	 * @throws Exception
	 */

	public BigDecimal selectCustomerBackMoney(
			String customerId) throws Exception;
}
