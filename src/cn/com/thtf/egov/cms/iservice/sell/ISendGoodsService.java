/**
 * ClassName  ISendGoodsService
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-4-19
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.iservice.sell;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.dto.SendGoodInfoDto;
import cn.com.thtf.egov.cms.dto.CreditLogicQueryDto;
import cn.com.thtf.egov.cms.dto.CreditLogicResultDto;
import cn.com.thtf.egov.cms.dto.SendGoodsAddDto;
import cn.com.thtf.egov.cms.dto.SendGoodsListInfoDto;
import cn.com.thtf.egov.cms.dto.SendGoodsListQueryDto;
import cn.com.thtf.egov.cms.dto.SendgoodSauditPrepareDto;
import cn.com.thtf.egov.cms.dto.SendgoodViewSelectProductDto;
import cn.com.thtf.egov.cms.dto.SendgoodsProductSelectDto;
import cn.com.thtf.egov.cms.entity.*;

/**
 * ISendGoodsService
 * 
 * @author Lubo
 */
public interface ISendGoodsService {

	/**
	 * 修改发货单状态
	 * 
	 * @param para
	 * @return
	 */
	public boolean modifySendgoodStatus(SendGoodsAddDto para);

	/**
	 * 保存发货单
	 * 
	 * @param para
	 * @return
	 */
	public boolean seveSendgoods(SendGoodsAddDto para);

	/**
	 * 新建发货单
	 * 
	 * @param para
	 * @return
	 */
	public boolean addSendgoods(SendGoodsAddDto para);

	/**
	 * 验证库存数
	 * 
	 * @param stockroomId
	 * @param sellContractId
	 * @param productIdArr
	 * @param countArr
	 * @return
	 */
	public boolean checkStockNum(String stockroomId, String sellContractId,
			String[] productIdArr, String[] countArr);

	/**
	 * 新建销售合同 产品选择小页面
	 * 
	 * @param stockroomId
	 * @param sellContractId
	 * @return
	 */
	public List<SendgoodsProductSelectDto> getProductListBySrIdAndScId(
			String stockroomId, String sellContractId);

	/**
	 * 发货单的信用判断
	 * 
	 * @param para
	 * @return
	 */
	public CreditLogicResultDto creditLogic(CreditLogicQueryDto para);

	/**
	 * 发货单列表页查询
	 * 
	 * @param page
	 * @param sendGoodListDto
	 * @param user
	 * @return
	 */
	public List<SendGoodsListInfoDto> getSendGoodsList(NewPage page,
			SendGoodsListQueryDto sendGoodListDto, UserEntity user);

	/**
	 * 发货单查看
	 * 
	 * @param id
	 * @return
	 */
	public SendGoodInfoDto getSendGoodsView(String id);

	/**
	 * 获取所有产品分类集合
	 * 
	 * @return
	 */
	public List<ProductTypeEntity> getAllProductType();

	/**
	 * 发货单删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean removeSendGoods(String id, String sgstatus);

	/**
	 * 获取所有产品分类集合(备货)
	 * 
	 * @return
	 */
	public List<SendgoodsProductSelectDto> getReserveProductListBySrIdAndScId(
			String stockroomId, String sellContractId);

	/**
	 * 备货评审
	 * 
	 * @param id
	 * @return
	 */
	public boolean modifySendStockingAssessment(SendGoodsEntity sge,
			String iderpd);

	/**
	 * 发货单查看-产品信息
	 * 
	 * @param id
	 * @return
	 */
	public List<SendgoodViewSelectProductDto> sendGoodsViewProduct(
			SendGoodInfoDto sgifd);

	/**
	 * 新建备货单
	 * 
	 * @param sendGoodE
	 * @param timeMap
	 * @return
	 */
	public Boolean addReservegoods(SendGoodsEntity sendGoodE,
			List<SendGoodsDetailEntity> sendGoodsDetailList,
			Map<String, Timestamp> timeMap);

	/**
	 * 备货评审-产品信息
	 * 
	 * @param id
	 * @return
	 */
	public List<SendgoodSauditPrepareDto> selectSauditPrepareView(
			SendGoodInfoDto sgifd);

	/**
	 * 计算判断库存
	 * 
	 * @param stockroomId
	 * @param sellContractId
	 * @param productIdArr
	 * @param countArr
	 * @return
	 */
	public Map<String, Object> checkStockNumReserve(String stockroomId,
			String sellContractId, String[] productIdArr, String[] countArr);

	/**
	 * 
	 * 备货查看
	 * 
	 * @param id
	 * @return
	 */
	public SendGoodInfoDto getReservegoodsView(String id);

	/**
	 * 修改备货单
	 * 
	 * @param sendGoodE
	 * @param timeMap
	 * @return
	 */
	public Boolean modifyReservegoods(SendGoodsEntity sendGoodE,
			List<SendGoodsDetailEntity> sendGoodsDetailList,
			Map<String, Timestamp> timeMap);
	
	/**
	 * 根据发货单ID获取装箱单ID
	 * 
	 * @author LuP
	 * @param sendGoodId
	 * @return
	 */
	public String getBoxIdBySendGoodId(String sendGoodId);
}
