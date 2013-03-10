/**
 * ClassName  BackInvoiceServiceImpl
 *
 * History
 * Create User: liuqg
 * Create Date: 2010-5-20
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.serviceimpl.sell;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.dto.SellInvoiceDto;

import cn.com.thtf.egov.cms.application.BaseService;
import cn.com.thtf.egov.cms.application.NewIDao;
import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.dto.BackInvoicListDto;
import cn.com.thtf.egov.cms.dto.BackInvoicSearchDto;
import cn.com.thtf.egov.cms.dto.SellInvocleOfAddDto;
import cn.com.thtf.egov.cms.dto.SellInvoiceDetailDto;
import cn.com.thtf.egov.cms.entity.MakeInvoiceDetailEntity;
import cn.com.thtf.egov.cms.entity.ProductEntity;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.entity.WorkEntity;
import cn.com.thtf.egov.cms.iservice.sell.IBackInvoiceService;
import cn.com.thtf.egov.cms.iservice.work.ITodoWorkService;
import cn.com.thtf.egov.cms.serviceimpl.work.TodoWorkService;
import cn.com.thtf.egov.cms.util.Container;

/**
 * 销售退票
 * 
 * @author liuqg
 */

public class BackInvoiceServiceImpl extends BaseService implements
		IBackInvoiceService {

	private NewIDao dao;

	private static Logger log = LoggerFactory
			.getLogger(BackInvoiceServiceImpl.class);

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
		BackInvoiceServiceImpl.log = log;
	}

	/**
	 * 查看发票明细 by 张子旭
	 * 
	 * @param sellInvoiceId
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SellInvoiceDetailDto> selectSellInvoiceDetailList(
			String sellInvoiceId) {
		List<SellInvoiceDetailDto> list = null;
		try {
			list = dao.queryForlist(
					"backInvoice_sqlMap.selectSellInvoiceDetailList",
					sellInvoiceId);

		} catch (Exception e) {
			log.error("查看产品信息失败", e);
		}

		return list;

	}

	/**
	 * 查看发票表 by zzx
	 * 
	 * @param sellInvoiceId
	 * @return SellInvoiceDto
	 */
	@Override
	public SellInvoiceDto selectSellInvoiceInfo(String sellInvoiceId) {
		SellInvoiceDto sellInvoiceDto = null;
		try {
			sellInvoiceDto = (SellInvoiceDto) dao.queryForObject(
					"backInvoice_sqlMap.selectSellInvoiceInfo", sellInvoiceId);

		} catch (Exception e) {
			log.error("查看产品信息失败", e);
		}

		return sellInvoiceDto;

	}

	/**
	 * 保存修改发票by zzx
	 * 
	 * @param sellInvoiceId
	 * @return {@link Boolean}
	 */
	@Override
	public boolean updateSellInvoiceSave(SellInvoiceDto sellInvoiceDto) {
		int count = -1;
		boolean isSuccess = true;
		try {
			dao.beginTransaction();
			count = dao.update("backInvoice_sqlMap.modifySellInvoiceInfo",
					sellInvoiceDto);
			if (count != 1) {
				isSuccess = false;
				return isSuccess;
			}
			dao.commitTransaction();

			isSuccess = true;

		} catch (Exception e) {
			log.error("保存修改退票失败", e);
			isSuccess = false;
		} finally {
			try {
				dao.endTransaction();
			} catch (Exception e) {
				log.debug("保存修改退票失败", e);
				isSuccess = false;
			}
		}

		return isSuccess;
	}

	/**
	 * 根据发票id查询产品类型id by zzx
	 * 
	 * @param sellInvoiceId
	 * @return String
	 */
	@Override
	public String selectProductTypeId(String sellInvoiceId) {
		String productTypeId = "";
		try {
			productTypeId = (String) dao.queryForObject(
					"backInvoice_sqlMap.selectProductTypeId", sellInvoiceId);
		} catch (Exception e) {
			log.error("查询产品类型id失败", e);
		}

		return productTypeId;
	}

	/**
	 * 根据产品类型id查询产品类型id
	 * 
	 * @author zzx
	 * @param productTypeId
	 * @return {@link String}
	 */
	@Override
	public String selectSellMajId(String productTypeId) {
		String selectSellMajId = "";
		try {
			selectSellMajId = (String) dao.queryForObject(
					"backInvoice_sqlMap.selectSellMajId", productTypeId);
		} catch (Exception e) {
			log.error("查询销售总监id失败", e);

		}

		return selectSellMajId;
	}

	/**
	 * 
	 * 查询运营总监id
	 * 
	 * @author zzx
	 * @param productTypeId
	 * @return {@link String}
	 */
	@Override
	public String selectOpeMajId(String productTypeId) {
		String selectOpeMajId = "";
		try {
			selectOpeMajId = (String) dao.queryForObject(
					"backInvoice_sqlMap.selectOpeMajId", null);
		} catch (Exception e) {
			log.error("查询运营总监id失败", e);
		}
		return selectOpeMajId;
	}

	/**
	 * 查询销售助理
	 * 
	 * @author zzx
	 * @param sellInvoiceId
	 * @return {@link String}
	 */
	@Override
	public String selectSellAssistantId(String sellInvoiceId) {
		String sellAssistantId = "";
		try {
			sellAssistantId = (String) dao.queryForObject(
					"backInvoice_sqlMap.selectSellAssistantId", sellInvoiceId);
		} catch (Exception e) {
			log.error("查询销售助理失败", e);
		}
		return sellAssistantId;
	}

	/**
	 * 提交退票
	 * 
	 * @author zzx
	 * @param sellInvoiceDto
	 * @param work
	 * @return {@link Boolean}
	 */
	@Override
	public boolean modifySellInvoiceSubmit(SellInvoiceDto sellInvoiceDto,
			WorkEntity work) {
		int count = -1;
		boolean isSuccess = false;
		try {
			dao.beginTransaction();
			// 更新发票表
			count = dao.update("backInvoice_sqlMap.modifySellInvoiceSubmit",
					sellInvoiceDto);
			// 给销售总监发待办事务
			boolean assessMan = modifyNextAssessManWork(work);

			if (count != 1 || assessMan == false) {
				return isSuccess;
			}
			dao.commitTransaction();

			isSuccess = true;
		} catch (Exception e) {
			log.error("提交退票失败", e);
			isSuccess = false;
		} finally {
			try {
				dao.endTransaction();
			} catch (Exception e) {
				log.debug("提交退票失败", e);
				isSuccess = false;
			}
		}

		return isSuccess;
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
	 *查询退票评审
	 * 
	 * @author jiangmx
	 * @param sellInvoiceId
	 * @return SellInvoiceDto
	 */

	@Override
	public SellInvoiceDto getBackInvoiceById(String sellInvoiceId) {

		SellInvoiceDto assess = null;
		try {
			assess = (SellInvoiceDto) dao.queryForObject(
					"backInvoice_sqlMap.selectSellInvoiceInfo", sellInvoiceId);
		} catch (Exception e) {
			log.error("退票评审信息失败!", e);
		}
		return assess;
	}

	/**
	 * 销售退票评审发代办事务
	 * 
	 * @author jiangmx
	 * @param backInvoiceAssessDto
	 * @param work
	 * @param workself
	 * @return boolean
	 */
	@Override
	public boolean updateBackInvoiceAssess(SellInvoiceDto backInvoiceAssessDto,
			WorkEntity work, WorkEntity workself) {
		int count = -1;
		boolean isSuccess = false;
		try {
			dao.beginTransaction();
			count = dao.update("backInvoice_sqlMap.updateBackInvoiceAssess",
					backInvoiceAssessDto);
			// 给运行总监发待办事务
			boolean assessMan = modifyNextAssessManWork(work);
			// 自身的待办事务要取消
			boolean assessManSelf = modifyNextAssessManWork(workself);
			if (count != 1 || assessMan == false || assessManSelf == false) {
				return isSuccess;
			}
			dao.commitTransaction();

			isSuccess = true;
		} catch (Exception e) {
			log.error("退票评审通过失败", e);
			isSuccess = false;
		} finally {
			try {
				dao.endTransaction();
			} catch (Exception e) {
				log.debug("退票评审通过失败", e);
				isSuccess = false;
			}
		}

		return isSuccess;
	}

	/**
	 * 销售不发代办事务
	 * 
	 * @author jiangmx
	 * @param backInvoiceAssessDto
	 * @param workself
	 * @return {@link Boolean}
	 */
	@Override
	public boolean updateAssessNoWork(SellInvoiceDto backInvoiceAssessDto,
			WorkEntity workself) {
		int count = -1;
		boolean isSuccess = false;
		try {
			dao.beginTransaction();
			count = dao.update("backInvoice_sqlMap.updateBackInvoiceAssess",
					backInvoiceAssessDto);
			// 自身的待办事务要减少1
			boolean assessMan2 = modifyNextAssessManWork(workself);
			if (count != 1 || assessMan2 == false) {
				return isSuccess;
			}
			dao.commitTransaction();

			isSuccess = true;
		} catch (Exception e) {
			log.error("退票评审未通过失败", e);
			isSuccess = false;
		} finally {
			try {
				dao.endTransaction();
			} catch (Exception e) {
				log.debug("退票评审未通过失败", e);
				isSuccess = false;
			}
		}

		return isSuccess;
	}

	/**
	 * 运营总监退票评审
	 * 
	 * @author jiangmx
	 * @param backInvoiceAssessDto
	 * @param workself
	 */
	@Override
	public boolean updateOpeAssess(SellInvoiceDto backInvoiceAssessDto,
			WorkEntity workself) {
		int count = -1;
		boolean isSuccess = false;
		try {
			dao.beginTransaction();
			count = dao.update("backInvoice_sqlMap.updateBackInvoiceAssess",
					backInvoiceAssessDto);
			// 自身的待办事务要减少1
			boolean assessMan2 = modifyNextAssessManWork(workself);
			if (count != 1 || assessMan2 == false) {
				return isSuccess;
			}
			dao.commitTransaction();

			isSuccess = true;
		} catch (Exception e) {
			log.error("评审失败", e);
			isSuccess = false;
		} finally {
			try {
				dao.endTransaction();
			} catch (Exception e) {
				log.debug("评审失败", e);
				isSuccess = false;
			}
		}

		return isSuccess;
	}

	/**
	 * 根据登录经理的ID查询退票列表
	 * 
	 * @author LiuQingGang
	 * @param page
	 * @param userId
	 * @return List<BackInvoicListDto>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BackInvoicListDto> selectBackInvoicListByUserId(NewPage page,
			UserEntity user)  {
		List result = null;
		try {
			result = queryRecords(dao,
					"selectBackInvoiceById.selectBackInvoiceByUserId", user,
					page);
		} catch (Exception e) {
			log.error("获取销售退票一览错误:", e);
		}
		return result;
	}

	/**
	 * 根据画面条件查询退票列表
	 * 
	 * @author LiuQingGang
	 * @param page
	 * @param backInvoicSearchDto
	 * @return List<BackInvoicListDto>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BackInvoicListDto> selectBackInvoicListByCondition(
			NewPage page, BackInvoicSearchDto backInvoicSearchDto)
			 {
		List result = null;
		try {
			result = queryRecords(
					dao,
					"selectBackInvoiceByCondition.selectBackInvoiceByCondition",
					backInvoicSearchDto, page);
		} catch (Exception e) {
			log.error("检索销售退票一览错误:", e);
		}
		return result;
	}

	/**
	 * 获得产品分类名称
	 * 
	 * @author LiuQingGang
	 * @return List<ProductEntity>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ProductEntity> selectProductType()  {
		List result = null;
		try {
			result = dao.queryForlist("backInvoice_sqlMap.getproductTypeList",
					null);
		} catch (Exception e) {
			log.error("检索产品分类错误:", e);
		}
		return result;
	}

	/**
	 * 退票确认
	 * 
	 * @author LiuQingGang
	 * @param backInvoicListDto
	 * @return boolean
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean confirmBackInvoice(BackInvoicListDto backInvoicListDto)
			 {
		try {
			dao.beginTransaction();
			dao.update("backInvoice_sqlMap.modifyBackInvoiceConfirm",
					backInvoicListDto);
			
			//开票明细的标志修改
			SellInvocleOfAddDto sellInvocleOfAddDto = new SellInvocleOfAddDto();
			sellInvocleOfAddDto.setId(backInvoicListDto.getId());
			sellInvocleOfAddDto.setMakeInvoiceId(backInvoicListDto.getMakeInvoiceId());
			//发票明细ID获得
			List<SellInvocleOfAddDto> ofAddDtos = dao.queryForlist("invoice_sqlMap.findMakeInvoiceDetailId", sellInvocleOfAddDto);
			 
			for(int i=0;i<ofAddDtos.size();i++){
				SellInvocleOfAddDto sellDetailDto = (SellInvocleOfAddDto)ofAddDtos.get(i);
				
				
				if (sellDetailDto.getMakeInvoiceId()!=null){
					MakeInvoiceDetailEntity makeInvoiceDetail = new MakeInvoiceDetailEntity();
					makeInvoiceDetail.setId(Integer.valueOf(sellDetailDto.getMakeInvoiceId()));
					makeInvoiceDetail.setFlag(1);
					dao.update("invoice_sqlMap.modifyFlagOfMakeInvoice", makeInvoiceDetail);
				}
				
				
			} 
			
			
			dao.commitTransaction();
			return true;
		} catch (Exception e) {
			log.error("销售发票确认错误", e);
			return false;
		} finally {
			try {
				dao.endTransaction();
			} catch (Exception e) {
				log.error("销售发票确认事务回滚错误", e);
			}
		}
	}

	/**
	 * 销售总监退票评审通过
	 * 
	 * @author lzzx
	 * @param sellInvoiceDto
	 * @return {@link Boolean}
	 */
	@Override
	public boolean updateSellInvoiceAssessPass(SellInvoiceDto sellInvoiceDto,
			WorkEntity work) {
		int count = -1;
		boolean isSuccess = false;
		try {
			dao.beginTransaction();
			count = dao.update("backInvoice_sqlMap.sellMajAssessBackInvoice",
					sellInvoiceDto);
			boolean assessMan = modifyNextAssessManWork(work);

			if (count != 1 || assessMan == false) {
				return isSuccess;
			}
			dao.commitTransaction();

			isSuccess = true;
		} catch (Exception e) {
			log.error("评审失败", e);
			isSuccess = false;
		} finally {
			try {
				dao.endTransaction();
			} catch (Exception e) {
				log.debug("评审失败", e);
				isSuccess = false;
			}
		}

		return isSuccess;
	}

	/**
	 * 销售总监退票评审未通过或不发事务
	 * 
	 * @author zzx
	 * @param sellInvoiceDto
	 * @return {@link Boolean}
	 */
	@Override
	public boolean updateSellInvoiceAssessNoWork(SellInvoiceDto sellInvoiceDto) {
		int count = -1;
		boolean isSuccess = false;
		try {
			dao.beginTransaction();
			count = dao.update("backInvoice_sqlMap.sellMajAssessBackInvoice",
					sellInvoiceDto);
			if (count != 1) {
				return isSuccess;
			}
			dao.commitTransaction();

			isSuccess = true;
		} catch (Exception e) {
			log.error("评审失败", e);
			isSuccess = false;
		} finally {
			try {
				dao.endTransaction();
			} catch (Exception e) {
				log.debug("评审失败", e);
				isSuccess = false;
			}
		}

		return isSuccess;
	}

	/**
	 * 运营总监评审
	 * 
	 * @author zzx
	 * @param sellInvoiceDto
	 * @return boolean
	 */
	@Override
	public boolean updateSellInvoiceAssessOpe(SellInvoiceDto sellInvoiceDto) {
		int count = -1;
		boolean isSuccess = false;
		try {
			dao.beginTransaction();
			count = dao.update("backInvoice_sqlMap.opeMajAssessBackInvoice",
					sellInvoiceDto);
			if (count != 1) {
				return isSuccess;
			}
			dao.commitTransaction();

			isSuccess = true;
		} catch (Exception e) {
			log.error("评审失败", e);
			isSuccess = false;
		} finally {
			try {
				dao.endTransaction();
			} catch (Exception e) {
				log.debug("评审失败", e);
				isSuccess = false;
			}
		}

		return isSuccess;
	}

	/**
	 * 查询用户姓名
	 * 
	 * @author LiuQingGang
	 * @param userId
	 * @return userName
	 */
	@Override
	public String selectUserName(String userId) {
		String userName = "";
		try {
			userName = (String) dao.queryForObject(
					"backInvoice_sqlMap.selectUserName", userId);
		} catch (Exception e) {
			log.error("查询用户姓名错误", e);
		}
		return userName;
	}

}
