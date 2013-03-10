/**
 * ClassName  IBackInvoiceService
 *
 * History
 * Create User: liuqg
 * Create Date: 2010-5-20
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.iservice.sell;

import java.util.List;
import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.dto.BackInvoicListDto;
import cn.com.thtf.egov.cms.dto.BackInvoicSearchDto;
import cn.com.thtf.egov.cms.dto.SellInvoiceDetailDto;
import cn.com.thtf.egov.cms.dto.SellInvoiceDto;
import cn.com.thtf.egov.cms.entity.WorkEntity;
import cn.com.thtf.egov.cms.entity.ProductEntity;
import cn.com.thtf.egov.cms.entity.UserEntity;

/**
 * 销售退票
 * 
 * @author liuqg
 */

public interface IBackInvoiceService {
	/**
	 * 查看发票明细 by zzx
	 * 
	 * @param sellInvoiceId
	 * @return List<SellInvoiceDetailDto>
	 * @throws Exception
	 */
	public List<SellInvoiceDetailDto> selectSellInvoiceDetailList(
			String sellInvoiceId) throws Exception;

	/**
	 * 根据发票id查询产品类型id by zzx
	 * 
	 * @param sellInvoiceId
	 * @return String
	 * @throws Exception
	 */
	public String selectProductTypeId(String sellInvoiceId) throws Exception;

	/**
	 * 提交退票 by zzx
	 * 
	 * @param sellInvoiceDto
	 * @param work
	 * @return
	 */
	public boolean modifySellInvoiceSubmit(SellInvoiceDto sellInvoiceDto,
			WorkEntity work) throws Exception;

	/**
	 *查询退票评审
	 * 
	 * @author 蒋名星
	 * @param sellInvoiceId
	 * @return SellInvoiceDto
	 * @throws Exception
	 */
	public SellInvoiceDto getBackInvoiceById(String sellInvoiceId)
			throws Exception;

	/**
	 * 销售退票评审发代办事务 author 蒋名星
	 * 
	 * @param backInvoiceAssessDto
	 * @param work
	 * @param workself
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateBackInvoiceAssess(SellInvoiceDto backInvoiceAssessDto,
			WorkEntity work, WorkEntity workself) throws Exception;

	/**
	 * 销售不发代办事务
	 * 
	 * @author 蒋名星
	 * @param backInvoiceAssessDto
	 * @param workself
	 * @return
	 * @throws Exception
	 */
	public boolean updateAssessNoWork(SellInvoiceDto backInvoiceAssessDto,
			WorkEntity workself) throws Exception;

	/**
	 * 运营总监退票评审
	 * 
	 * @author jiangmx
	 * @param backInvoiceAssessDto
	 * @param workself
	 * @throws Exception
	 */
	public boolean updateOpeAssess(SellInvoiceDto backInvoiceAssessDto,
			WorkEntity workself) throws Exception;

	/**
	 * 查看发票表 by zzx
	 * 
	 * @param sellInvoiceId
	 * @return SellInvoiceDto
	 * @throws Exception
	 */
	public SellInvoiceDto selectSellInvoiceInfo(String sellInvoiceId)
			throws Exception;

	/**
	 * 保存修改发票by zzx
	 * 
	 * @param sellInvoiceId
	 * @return {@link Boolean}
	 */
	public boolean updateSellInvoiceSave(SellInvoiceDto sellInvoiceDto)
			throws Exception;

	/**
	 * 根据产品类型id查询产品类型id by zzx
	 * 
	 * @param productTypeId
	 * @return {@link String}
	 */
	public String selectSellMajId(String productTypeId) throws Exception;

	/**
	 * 
	 * 查询运营总监id by zzx
	 * 
	 * @param productTypeId
	 * @return {@link String}
	 * @throws Exception
	 */
	public String selectOpeMajId(String productTypeId) throws Exception;

	/**
	 * 查询销售助理 by zzx
	 * 
	 * @param sellInvoiceId
	 * @return {@link String}
	 * @throws Exception
	 */
	public String selectSellAssistantId(String sellInvoiceId) throws Exception;

	/**
	 * 销售总监退票评审通过 by zzx
	 * 
	 * @param sellInvoiceDto
	 * @return {@link Boolean}
	 * @throws Exception
	 */
	public boolean updateSellInvoiceAssessPass(SellInvoiceDto sellInvoiceDto,
			WorkEntity work) throws Exception;

	/**
	 * 销售总监退票评审未通过或不发事务 by zzx
	 * 
	 * @param sellInvoiceDto
	 * @return {@link Boolean}
	 * @throws Exception
	 */
	public boolean updateSellInvoiceAssessNoWork(SellInvoiceDto sellInvoiceDto)
			throws Exception;

	/**
	 * 运营总监评审 by zzx
	 * 
	 * @param sellInvoiceDto
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateSellInvoiceAssessOpe(SellInvoiceDto sellInvoiceDto)
			throws Exception;

	/**
	 * 根据登录经理的ID查询退票列表 By LiuQingGang
	 * 
	 * @param page
	 * @param user
	 * @return List<BackInvoicListDto>
	 * @throws Exception
	 */
	public List<BackInvoicListDto> selectBackInvoicListByUserId(NewPage page,
			UserEntity user) throws Exception;

	/**
	 * 根据画面条件查询退票列表 By LiuQingGang
	 * 
	 * @param page
	 * @param backInvoicSearchDto
	 * @return List<BackInvoicListDto>
	 * @throws Exception
	 */
	public List<BackInvoicListDto> selectBackInvoicListByCondition(
			NewPage page, BackInvoicSearchDto backInvoicSearchDto)
			throws Exception;

	/**
	 * 获得产品分类名称 By LiuQingGang
	 * 
	 * @return List<ProductEntity>
	 * @throws Exception
	 */
	public List<ProductEntity> selectProductType() throws Exception;

	/**
	 * 退票确认 By LiuQingGang
	 * 
	 * @param backInvoicListDto
	 * @return boolean
	 * @throws Exception
	 */

	public boolean confirmBackInvoice(BackInvoicListDto backInvoicListDto)
			throws Exception;

	/**
	 * 查询用户姓名 by LiuQingGang
	 * 
	 * @param userId
	 * @return userName
	 * @throws Exception
	 */
	public String selectUserName(String userId) throws Exception;

}
