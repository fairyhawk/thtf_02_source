/**
 * ClassName  SellBackServiceImpl
 *
 * History
 * Create User: liuqg
 * Create Date: 2010-5-31
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.serviceimpl.sell;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.BaseService;
import cn.com.thtf.egov.cms.application.NewIDao;
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
import cn.com.thtf.egov.cms.iservice.sell.ISellBackService;
import cn.com.thtf.egov.cms.iservice.work.ITodoWorkService;
import cn.com.thtf.egov.cms.serviceimpl.work.TodoWorkService;
import cn.com.thtf.egov.cms.util.Container;

/**
 * 销售退款管理
 * 
 * @author liuqg
 */

public class SellBackServiceImpl extends BaseService implements
		ISellBackService {
	private NewIDao dao;

	private static Logger log = LoggerFactory
			.getLogger(SellBackServiceImpl.class);

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
		SellBackServiceImpl.log = log;
	}

	/**
	 * 根据登录经理的ID查询退款列表 by zzx
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SellBackListDto> getSellBackList(NewPage page, UserEntity user)
			 {
		List result = null;
		try {
			result = queryRecords(dao, "selectSellBackList.selectSellBackList",
					user, page);
		} catch (Exception e) {
			log.error("获取销售退款一览错误:", e);
		}
		return result;
	}

	/**
	 * 获得所有产品分类 by zzx
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ProductTypeEntity> getProductType()  {
		List result = null;
		try {
			result = dao.queryForlist("sellBack.selectProductTypeList", null);
		} catch (Exception e) {
			log.error("检索产品分类错误:", e);
		}
		return result;
	}

	/**
	 * 根据检索条件查找记录by zzx
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SellBackListDto> getSellBackListByCondition(NewPage page,
			SellBackSearchDto sellBackSearchDto)  {
		List result = null;
		try {
			result = queryRecords(dao,
					"selectSellBackByCondition.selectSellBackByCondition",
					sellBackSearchDto, page);
		} catch (Exception e) {
			log.error("检索销售退票一览错误:", e);
		}
		return result;
	}

	/**
	 *查询退款评审
	 * 
	 * @author jiangmx
	 * @param sellBackId
	 * @return SellBackListDto
	 */
	@Override
	public SellBackAssessDto getSellBackById(String sellBackId)
			 {

		SellBackAssessDto assess = null;

		try {
			assess = (SellBackAssessDto) dao.queryForObject(
					"sellBack.selectSellBackCustomerInfo", sellBackId);
		} catch (Exception e) {
			log.error("退款评审信息失败", e);
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
	 * 退款评审发待办事务
	 * 
	 * @author jiangmx
	 * @param sellBackAssessDto
	 * @param work
	 * @param workself
	 * @return boolean
	 */
	@Override
	public boolean updateSellBackAssess(SellBackAssessDto sellBackAssessDto,
			WorkEntity work, WorkEntity workself)  {
		int count = -1;
		boolean isSuccess = false;

		try {
			dao.beginTransaction();
			count = dao.update("sellBack.updateSellBackAssess",
					sellBackAssessDto);
			boolean assessMan = NextAssessManWork(work);
			boolean assessManself = NextAssessManWork(workself);
			if (count != 1 || assessMan == false || assessManself == false) {
				return isSuccess;
			}
			dao.commitTransaction();

			isSuccess = true;
		} catch (Exception e) {
			log.error("退款评审通过失败", e);
			isSuccess = false;
		} finally {
			try {
				dao.endTransaction();
			} catch (Exception e) {
				log.debug("退款评审通过失败", e);
				isSuccess = false;
			}
		}
		return isSuccess;
	}

	/**
	 * 退款评审不发待办事务
	 * 
	 * @author jiangmx
	 * @param sellBackAssessDto
	 * @param workself
	 * @return boolean
	 */
	@Override
	public boolean updateAssessNoWork(SellBackAssessDto sellBackAssessDto,
			WorkEntity workself)  {
		int count = -1;
		boolean isSuccess = false;

		try {
			dao.beginTransaction();
			count = dao.update("sellBack.updateSellBackAssess",
					sellBackAssessDto);
			boolean assessManself = NextAssessManWork(workself);
			if (count != 1 || assessManself == false) {
				return isSuccess;
			}
			dao.commitTransaction();

			isSuccess = true;
		} catch (Exception e) {
			log.error("退款评审未通过失败", e);
			isSuccess = false;
		} finally {
			try {
				dao.endTransaction();
			} catch (Exception e) {
				log.debug("退款评审未通过失败", e);
				isSuccess = false;
			}
		}
		return isSuccess;
	}
	/**
	 * 删除退款
	 * 
	 * @author jiangmx
	 */
	@Override
	public boolean removeSellBack(String sellBackId)  {

		boolean isSuccess = false;

		try {
			dao.beginTransaction();
			int countInfo = dao.delete("sellBack.deleteSellBack", sellBackId);
			if (countInfo < 1) {
				dao.endTransaction();
				return false;
			}
			dao.commitTransaction();

			isSuccess = true;

		} catch (Exception e) {
			log.debug("删除销售退款失败", e);
			isSuccess = false;
		} finally {

			try {
				dao.endTransaction();
			} catch (Exception e) {
				log.debug("删除销售退款失败", e);
				isSuccess = false;
			}

		}
		return isSuccess;
	}

	/**
	 * 退款确认 by zzx
	 */
	@Override
	public boolean confirmSellBack(SellBackListDto sellBack)  {
		int count = -1;
		boolean isSuccess = false;
		try {
			dao.beginTransaction();
			count = dao.update("sellBack.modifySellBackConfirm", sellBack);
			if (count != 1) {
				dao.endTransaction();
				return isSuccess;
			}
			dao.commitTransaction();
			isSuccess = true;
		} catch (Exception e) {
			log.error("销售退票确认错误", e);
			isSuccess = false;
		} finally {
			try {
				dao.endTransaction();
			} catch (Exception e) {
				log.error("销售退票确认事务回滚错误", e);
				isSuccess = false;
			}
		}
		return isSuccess;
	}

	/**
	 * 退款打印 by zzx
	 */
	@Override
	public boolean printSellBack(String sellBackId, WorkEntity work)
			 {
		int count = -1;
		boolean isSuccess = false;
		try {
			dao.beginTransaction();
			count = dao.update("sellBack.modifySellBackPrint", sellBackId);
			boolean assessManself = NextAssessManWork(work);
			if (count != 1 || assessManself == false) {
				dao.endTransaction();
				return isSuccess;
			}
			dao.commitTransaction();
			isSuccess = true;
		} catch (Exception e) {
			log.error("销售退票确认错误", e);
			isSuccess = false;
		} finally {
			try {
				dao.endTransaction();
			} catch (Exception e) {
				log.error("销售退票确认事务回滚错误", e);
				isSuccess = false;
			}
		}
		return isSuccess;
	}

	/**
	 * 根据回款单显示退款申请信息
	 * 
	 * @author liuqg
	 * @param mreturnId
	 * @return SellBackForMreturnDto
	 */

	public SellBackForMreturnDto selectInfoByMreturnId(String mreturnId)
			 {
		SellBackForMreturnDto sellBackForMreturnDto = null;
		try {
			sellBackForMreturnDto = (SellBackForMreturnDto) dao.queryForObject(
					"sellBack.selectInfoByMreturnId", mreturnId);
		} catch (Exception e) {
			log.error("查询退款申请信息(根据回款单)错误", e);
		}
		return sellBackForMreturnDto;

	}

	/**
	 * 根据回款单获得产品分类预收金额合计
	 * 
	 * @author liuqg
	 * @param mreturnId
	 * @return List<CustomerLinkmanEntity>
	 */

	@SuppressWarnings("unchecked")
	public List<CustomerLinkmanEntity> selectCustomerInfoByMId(String mreturnId)
			 {
		List<CustomerLinkmanEntity> result = null;
		try {
			result = dao.queryForlist("sellBack.selectCustomerInfoByMId",
					mreturnId);
		} catch (Exception e) {
			log.error("查询客户联系人信息(根据回款单)错误", e);
		}
		return result;
	}

	/**
	 * 新建销售合同 根据联系人Id查看联系人相关信息
	 * 
	 * @author liuqg
	 */
	@Override
	public CustomerLinkmanEntity getLinkMsgByLinkManId(String linkManId) {
		CustomerLinkmanEntity customerLinkman = new CustomerLinkmanEntity();
		try {
			customerLinkman = (CustomerLinkmanEntity) dao.queryForObject(
					"sellBack.selectLinkMsgByLinkManId", linkManId);
		} catch (Exception e) {
			log.error("根据联系人Id查看相关信息失败", e);
		}
		return customerLinkman;
	}

	/**
	 * 获得回款信息
	 * 
	 * @author liuqg
	 * @param mreturnId
	 */
	@Override
	public MreturnEntity selectMreturnById(String mreturnId)  {
		MreturnEntity mreturnEntity = null;
		try {
			mreturnEntity = (MreturnEntity) dao.queryForObject(
					"sellBack.selectMreturnById", mreturnId);
		} catch (Exception e) {
			log.error("根据Id查看回款单信息失败", e);
		}
		return mreturnEntity;

	}

	/**
	 * 保存退款
	 * 
	 * @author liuqg
	 * @param backAddDto
	 */
	@Override
	public boolean insertSellBackSave(SellBackAddDto backAddDto)
			 {
		try {
			dao.beginTransaction();
			// 插入到退款表中
			dao.insert("sellBack.insertSellBack", backAddDto);
			dao.commitTransaction();
			return true;
		} catch (Exception e) {
			log.error("提交退款失败", e);
			return false;
		} finally {
			try {
				dao.endTransaction();
			} catch (Exception e) {
				log.debug("提交退款失败", e);
				return false;
			}
		}
	}

	/**
	 * 提交退款
	 * 
	 * @author liuqg
	 * @param backAddDto
	 * @param work
	 */
	@Override
	public boolean insertSellBackSubmit(SellBackAddDto backAddDto,
			WorkEntity work)  {
		try {
			dao.beginTransaction();
			// 插入到退款表中
			dao.insert("sellBack.insertSellBack", backAddDto);
			// 给销售总监发待办事务
			modifyNextAssessManWork(work);
			dao.commitTransaction();
			return true;
		} catch (Exception e) {
			log.error("提交退款失败", e);
			return false;
		} finally {
			try {
				dao.endTransaction();
			} catch (Exception e) {
				log.debug("提交退款失败", e);
				return false;
			}
		}

	}

	/**
	 * 下一个评审人的待办事项
	 * 
	 * @author zzx
	 * @param work
	 */
	private boolean modifyNextAssessManWork(WorkEntity work) {
		boolean isSuccess = false;

		ITodoWorkService todoWorkService = (TodoWorkService) Container
				.getBean("todoWrokServiceImpl");

		isSuccess = todoWorkService.addTodoWorks(work);

		return isSuccess;
	}

	/**
	 * 根据回款单获得产品分类预收金额合计
	 * 
	 * @author liuqg
	 * @param mreturnId
	 * @return double
	 */
	@Override
	public BigDecimal selectProdMoneySumByMreturnId(MreturnEntity mreturnEntity)
			 {
		BigDecimal result = new BigDecimal(0);
		try {
			result = (BigDecimal) dao.queryForObject(
					"sellBack.selectProductAdvanceMoneySum", mreturnEntity);
		} catch (Exception e) {
			log.error("获得产品分类预收金额合计错误" + e);
			 
		}
		return result;
	}

	/**
	 * 根据回款单获得产品分类可退款金额
	 * 
	 * @author liuqg
	 * @param mreturnId
	 * @return BigDecimal
	 */
	@Override
	public BigDecimal selectProdCanBackMoneyByMreturnId(MreturnEntity mreturnEntity)
			 {
		BigDecimal result = new BigDecimal(0);
		try {
			result = (BigDecimal) dao.queryForObject(
					"sellBack.selectProductCanBackMoney", mreturnEntity);
		} catch (Exception e) {
			log.error("获得产品分类可退款金额错误" + e);
		}
		return result;
	}

	/**
	 * 获得产品分类信用额度列表显示
	 * 
	 * @author liuqg
	 * @param mreturnEntity
	 */

	@SuppressWarnings("unchecked")
	public List<CustomerProInfoListDto> selectCustomerProInfoList(
			MreturnEntity mreturnEntity)  {
		List<CustomerProInfoListDto> list = new ArrayList<CustomerProInfoListDto>();
		try {
			list = dao.queryForlist("sellBack.selectCustomerProInfoList",
					mreturnEntity);
		} catch (Exception e) {
			log.error("获得产品分类信用额度列表错误" + e);
		}
		return list;
	}

	/**
	 * 查询退款表信息和客户信息 by zzx
	 */
	@Override
	public SellBackAssessDto getSellBackCustomerInfo(String sellBackId)
			 {
		SellBackAssessDto sellBackAssessDto = null;
		try {
			sellBackAssessDto = (SellBackAssessDto) dao.queryForObject(
					"sellBack.selectSellBackCustomerInfo", sellBackId);
		} catch (Exception e) {
			log.error("查询退款信息失败", e);

		}
		return sellBackAssessDto;
	}

	/**
	 * 查询产品部门名称 by zzx
	 */
	@Override
	public String getProductDeptName(String productTypeId)  {
		String productDeptName = "";
		try {
			productDeptName = (String) dao.queryForObject(
					"sellBack.selectProductDeptName", productTypeId);
		} catch (Exception e) {
			log.error("查询产品部门名称失败", e);

		}
		return productDeptName;
	}

	/**
	 * 修改退款-保存
	 * 
	 * @author liuqg
	 * @param assessDto
	 * @param work
	 */
	@Override
	public boolean modifySellBackSubmit(SellBackAssessDto assessDto,
			WorkEntity work)  {
		try {
			dao.beginTransaction();
			// 更新退款表
			dao.update("sellBack.updateSellBackSubmit", assessDto);
			// 给销售总监发待办事务
			modifyNextAssessManWork(work);
			dao.commitTransaction();
			return true;
		} catch (Exception e) {
			log.error("提交退款失败", e);
			return false;
		} finally {
			try {
				dao.endTransaction();
			} catch (Exception e) {
				log.debug("提交退款失败", e);
				return false;
			}
		}
	}

	/**
	 * 修改退款-提交
	 * 
	 * @author liuqg
	 * @param assessDto
	 */
	@Override
	public boolean modifySellBackSave(SellBackAssessDto assessDto)
			 {
		try {
			dao.beginTransaction();
			// 更新退款表
			dao.update("sellBack.updateSellBackSave", assessDto);

			dao.commitTransaction();
			return true;
		} catch (Exception e) {
			log.error("保存退款失败", e);
			return false;
		} finally {
			try {
				dao.endTransaction();
			} catch (Exception e) {
				log.debug("保存退款失败", e);
				return false;
			}
		}

	}

	/**
	 * 评审页面显示客户信用信息
	 * 
	 * @author liuqg
	 * @param customerId
	 * @return List<SellBackForMreturnDto>
	 */

	@SuppressWarnings("unchecked")
	@Override
	public List<SellBackForMreturnDto> selectCustomerCreditList(
			String customerId)  {
		List<SellBackForMreturnDto> list = new ArrayList<SellBackForMreturnDto>();
		try {
			list = dao.queryForlist("sellBack.selectCustomerCreditList",
					customerId);
		} catch (Exception e) {
			log.error("获取客户产品分类信用信息错误" + e);
		}

		return list;
	}
	
	/**
	 * 评审页面显示客户可退款金额
	 * 
	 * @author liuqg
	 * @param customerId
	 * @return List<SellBackForMreturnDto>
	 * @throws Exception
	 */

	public BigDecimal selectCustomerBackMoney(
			String customerId) {
		BigDecimal re =new BigDecimal(0);
		try {
			re = (BigDecimal)dao.queryForObject("sellBack.selectCustomerbackmoney",
					customerId);
		} catch (Exception e) {
			log.error("获取客户产品分类信用信息错误" + e);
		}

		return re;
		
	};
	
}
