package cn.com.thtf.egov.cms.serviceimpl.salesBackContract;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.BaseService;
import cn.com.thtf.egov.cms.application.NewIDao;
import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.constant.Constants;
import cn.com.thtf.egov.cms.dto.SaleBackContractOfAddDto;
import cn.com.thtf.egov.cms.dto.SalesBackContractDetailDto;
import cn.com.thtf.egov.cms.dto.SalesBackContractDto;
import cn.com.thtf.egov.cms.dto.SalesBackQueryDto;
import cn.com.thtf.egov.cms.dto.WorkReceiverDto;
import cn.com.thtf.egov.cms.entity.SellBackGoodsDetailEntity;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.entity.WorkEntity;
import cn.com.thtf.egov.cms.iservice.common.ICommonService;
import cn.com.thtf.egov.cms.iservice.salesBackContract.ISalesBackContractService;
import cn.com.thtf.egov.cms.iservice.work.ITodoWorkService;
import cn.com.thtf.egov.cms.util.Container;

public class SalesBackContractServiceImpl extends BaseService implements
		ISalesBackContractService {

	/* 日志记录 */
	private static Logger log = LoggerFactory
			.getLogger(SalesBackContractServiceImpl.class);

	private NewIDao dao;

	public NewIDao getDao() {
		return dao;
	}

	public void setDao(NewIDao dao) {
		this.dao = dao;
	}

	/**
	 * 根据条件查询 所有的销售退货合同 creator: lilewei
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List getAllSalesBackContractByCondition(
			SalesBackQueryDto queryCondition, NewPage page) {

		List salesBackContractList = null;

		try {
			if (queryCondition.getRoleId() == Constants.ROLE_SALES_ASSISTANT) {// 助理
				queryCondition
						.setUserAreaProductList(dao
								.queryForlist(
										"salesBackContract_sqlMap.getAreaIdAndProductTypeIdOfRoldIs3",
										queryCondition.getUserId()));
			}
			if (queryCondition.getRoleId() == Constants.ROLE_REGIONAL_DIRECTOR) {// 区域总监
				queryCondition
						.setUserAreaProductList(dao
								.queryForlist(
										"salesBackContract_sqlMap.getAreaIdAndProductTypeIdOfRoldIs9",
										queryCondition.getUserId()));
				queryCondition.setRoleId(9);
			}
			if(queryCondition.getRoleId() == Constants.ROLE_BIGAREA_MANAGER || queryCondition.getRoleId() == Constants.ROLE_AREA_MANAGER){
			    queryCondition.setRoleId(19);
			}
			salesBackContractList = queryRecords(dao,
					"salesBackContract.selectAllSalesBackContractByCondition",
					queryCondition, page);

		} catch (Exception e) {
			log.error("根据条件查询 所有的销售退货合同失败", e);
			salesBackContractList = null;
		}

		return salesBackContractList;
	}

	/**
	 * 根据销售合同id查找合同信息 hrb
	 */
	public Object getSalesContractOfBack(String id) {
		try {
			return dao.queryForObject(
					"salesBackContract_sqlMap.getSalesContractOfBack", id);
		} catch (Exception e) {
			log.error("根据销售合同id查找合同信息失败", e);
		}
		return null;
	}

	/**
	 * 根据销售合同id查找产品信息 hrb
	 */

	public List<Object> getGoodsInfoById(NewPage nPage, String id) {
		try {
			return queryRecords(dao, "getGoodsInfoById.getDate", id, nPage);
		} catch (Exception e) {
			log.error("根据销售合同id查找产品信息失败", e);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Object> getStockRoomList() {
		// TODO Auto-generated method stub
		try {
			return dao
					.queryForlist("salesBackContract_sqlMap.getStoreRoom", "");
		} catch (Exception e) {
			log.error("获取库房信息失败", e);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Object> getReceiveInfo(String id) {
		try {
			return dao.queryForlist("salesBackContract_sqlMap.getReceiveInfo",
					id);
		} catch (Exception e) {
			log.error("获取收获地址失败", e);
		}
		return null;
	}

	public int addSaleBackContract(
			SaleBackContractOfAddDto saleBackContractOfAddDto, String JsonDate) {
		int issuccess = 0;
		try {
			/** 事物开始 */
			dao.beginTransaction();
			/** 插入销售退货合同 */
			dao.insert("salesBackContract_sqlMap.addSaleBackContract",
					saleBackContractOfAddDto);
			dao.startBatch();
			/** 插入销售退货合同明细 */
			org.json.JSONObject json = new JSONObject(JsonDate);
			int sum = json.getInt("resultSum");
			for (int i = 0; i < sum; i++) {
				JSONObject rows = json.getJSONArray("rows").getJSONObject(i);
				String backContractCount = rows.getString("backContractCount");// 发货单号
				String productId = rows.getString("productId");// 产品编号
				SellBackGoodsDetailEntity sellBackGoodsDetailEntity = new SellBackGoodsDetailEntity();
				sellBackGoodsDetailEntity.setCount(Integer
						.parseInt(backContractCount));
				sellBackGoodsDetailEntity.setProductId(Integer
						.parseInt(productId));
				sellBackGoodsDetailEntity
						.setSellBackGoodsId(saleBackContractOfAddDto.getId());
				dao.insert(
						"salesBackContract_sqlMap.addSaleBackContractDetail",
						sellBackGoodsDetailEntity);
			}
			dao.executeBatch();
			ITodoWorkService todoWork = (ITodoWorkService) Container
					.getBean("todoWrokServiceImpl");
			/** 插入事务 法务专员 */
			if ("2".equals(saleBackContractOfAddDto.getStatus())) {
				WorkEntity work = new WorkEntity();
				work.setCount(1);
				work.setUserId(saleBackContractOfAddDto.getLegalId());
				work.setWorkId(8);
				todoWork.addTodoWorks(work);

			}
			/** 插入事务 销售助理 */
			if ("4".equals(saleBackContractOfAddDto.getStatus())) {
				WorkEntity work = new WorkEntity();
				work.setCount(1);
				work.setUserId(saleBackContractOfAddDto.getSellMajId());
				work.setWorkId(8);
				todoWork.addTodoWorks(work);
			}
			/** 插入事务 区域总监 */
			if (Constants.BACKCONTRACT_STATUS_AREAMAJWAITCOM.toString().equals(
					saleBackContractOfAddDto.getStatus())) {
				WorkEntity work = new WorkEntity();
				work.setCount(1);
				work.setUserId(saleBackContractOfAddDto.getAreaMajId());
				work.setWorkId(8);
				todoWork.addTodoWorks(work);
			}
			dao.commitTransaction();
			issuccess = 1;
		} catch (Exception e) {
			issuccess = 0;
			try {
				dao.endTransaction();
			} catch (Exception e1) {
				log.error("添加销售退货合同失败！", e1);
			}
			log.error("添加销售退货合同失败！", e);
		} finally {
			try {
				dao.endTransaction();
			} catch (Exception e) {
				log.error("添加销售退货合同失败！", e);
			}
		}
		return issuccess;
	}

	/*
	 * 根据id查询销售退货合同
	 * 
	 * creator : lilewei
	 */
	@Override
	public SalesBackContractDto getSalesBackContractById(
			String salesBackContractId) {

		SalesBackContractDto salesBackContract = null;

		try {
			salesBackContract = (SalesBackContractDto) dao.queryForObject(
					"salesBackContract_sqlMap.selectSalesBackContractById",
					salesBackContractId);
		} catch (Exception e) {
			log.error("根据id 查找销售退货合同失败", e);
		}

		return salesBackContract;
	}

	/*
	 * 查询销售退货合同的全部明细
	 * 
	 * @author lilewei
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<SalesBackContractDetailDto> getAllDetailsOfSalesBackContract(
			String salesBackContractId) {

		List<SalesBackContractDetailDto> allDetails = null;

		try {
			allDetails = dao
					.queryForlist(
							"salesBackContract_sqlMap.selectSalesBackContractDetailBySalesContractId",
							salesBackContractId);
		} catch (Exception e) {
			log.error("查询销售退货合同的全部明细失败", e);
		}

		return allDetails;
	}

	public Object getModifySalesContractOfBack(String id) {
		try {
			return dao
					.queryForObject(
							"salesBackContract_sqlMap.getModifySalesContractOfBack",
							id);
		} catch (Exception e) {
			log.error("根据销售合同修改显示失败", e);
		}
		return null;

	}

	@SuppressWarnings("unchecked")
	public List<Object> getGoodsInfoByIdOfShow(String id) {

		try {
			return dao.queryForlist(
					"salesBackContract_sqlMap.getGoodsInfoByIdOfShow", id);
		} catch (Exception e) {
			log.error("根据销售退货合同id查找产品信息 显示失败", e);
			return null;
		}

	}

	public int modifySaleBackContract(
			SaleBackContractOfAddDto saleBackContractOfAddDto, String JsonDate) {
		int issuccess = 0;
		try {
			/** 事物开始 */
			dao.beginTransaction();
			/** 插入销售退货合同 */
			dao.insert("salesBackContract_sqlMap.modifySaleBackContract",
					saleBackContractOfAddDto);
			dao.startBatch();
			/** 删除销售退货合同明细 */
			dao.delete("salesBackContract_sqlMap.deleteSaleBackContractDetail",
					saleBackContractOfAddDto.getId());
			/** 插入销售退货合同明细 */
			org.json.JSONObject json = new JSONObject(JsonDate);
			int sum = json.getInt("resultSum");
			for (int i = 0; i < sum; i++) {
				JSONObject rows = json.getJSONArray("rows").getJSONObject(i);
				String backContractCount = rows.getString("backContractCount");// 发货单号
				String productId = rows.getString("productId");// 产品编号
				SellBackGoodsDetailEntity sellBackGoodsDetailEntity = new SellBackGoodsDetailEntity();
				sellBackGoodsDetailEntity.setCount(Integer
						.parseInt(backContractCount));
				sellBackGoodsDetailEntity.setProductId(Integer
						.parseInt(productId));
				sellBackGoodsDetailEntity
						.setSellBackGoodsId(saleBackContractOfAddDto.getId());
				dao.insert(
						"salesBackContract_sqlMap.addSaleBackContractDetail",
						sellBackGoodsDetailEntity);
			}
			dao.executeBatch();
			ITodoWorkService todoWork = (ITodoWorkService) Container
					.getBean("todoWrokServiceImpl");
			/** 插入事务 法务专员 */
			if ("2".equals(saleBackContractOfAddDto.getStatus())) {
				WorkEntity work = new WorkEntity();
				work.setCount(1);
				work.setUserId(saleBackContractOfAddDto.getLegalId());
				work.setWorkId(8);
				todoWork.addTodoWorks(work);

			}
			/** 插入事务 销售助理 */
			if ("4".equals(saleBackContractOfAddDto.getStatus())) {
				WorkEntity work = new WorkEntity();
				work.setCount(1);
				work.setUserId(saleBackContractOfAddDto.getSellMajId());
				work.setWorkId(8);
				todoWork.addTodoWorks(work);
			}
			/** 插入事务 区域总监 */
			if (Constants.BACKCONTRACT_STATUS_AREAMAJWAITCOM.toString().equals(
					saleBackContractOfAddDto.getStatus())) {
				WorkEntity work = new WorkEntity();
				work.setCount(1);
				work.setUserId(saleBackContractOfAddDto.getAreaMajId());
				work.setWorkId(8);
				todoWork.addTodoWorks(work);
			}
			dao.commitTransaction();
			issuccess = 1;
		} catch (Exception e) {
			issuccess = 0;
			try {
				dao.endTransaction();
			} catch (Exception e1) {
				log.error("修改销售退货合同失败！", e1);
			}
			log.error("修改销售退货合同失败！", e);
		} finally {
			try {
				dao.endTransaction();
			} catch (Exception e) {
				log.error("修改销售退货合同失败！", e);
			}
		}
		return issuccess;
	}
	/**
	 * 获得销售退货合同所在的区域编号
	 * @param backContractId
	 * @return
	 */
    public int getAreaIdBySalesBackContractId(String backContractId){
    	int isAreaId=0;
    	try{
    		isAreaId=(Integer)dao.queryForObject("salesBackContract_sqlMap.getAreaIdBySalesBackContractId", backContractId);
    	}catch(Exception e){
    		log.error("获取退货合同区域编号失败！",e);
    	}
    	return isAreaId;
    }
	public int modifySaleBackContractOfComment(
			SaleBackContractOfAddDto saleBackContractOfAddDto, UserEntity user) {
		int issuccess = 0;
		try {
			ITodoWorkService todoWork = (ITodoWorkService) Container
					.getBean("todoWrokServiceImpl");
			WorkEntity work = new WorkEntity();
			/** 事物开始 */
			dao.beginTransaction();
			/** 销售总监 */
			if (user.getRoleId() == Constants.ROLE_SALES_DIRECTOR) {
				/** 销售总监 符合时给运营总监加事务 */
				if ("111".equals(saleBackContractOfAddDto.getSellMajIdea())
						&& "6".equals(saleBackContractOfAddDto.getStatus())) {
					work.setCount(1);
					work.setUserId(saleBackContractOfAddDto.getOpeMajId());
					work.setWorkId(8);
					todoWork.addTodoWorks(work);
				}
				/** 销售总监 减事务 */
				work.setCount(-1);
				work.setUserId(user.getId());
				work.setWorkId(8);
				todoWork.addTodoWorks(work);
			}
			/** 运营总监 */
			if (user.getRoleId() == Constants.ROLE_DIRECTOR_OF_OPERATIONS) {
				/** 运营总监 符合时给销售助理加事务 */
				if ("1".equals(saleBackContractOfAddDto.getOpeMajIdea())
						&& "8".equals(saleBackContractOfAddDto.getStatus())) {
					ICommonService common = (ICommonService) Container
							.getBean("commonServiceImpl");

					/** 获取销售助理id */
					WorkReceiverDto workReceiverDto = common
							.getUserIdByCondition(Integer
									.parseInt(saleBackContractOfAddDto
											.getProductTypeId()), Integer
									.parseInt(saleBackContractOfAddDto
											.getUserAreaId()), null);
					java.text.SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy-MM-dd");
					saleBackContractOfAddDto.setDate(sdf.format(new Date()));
					if (workReceiverDto == null) {
						log.error("评审售退货合同失败！ 无销售助理");
						return 2;
					}
					work.setCount(1);
					work.setUserId(workReceiverDto.getUserId());
					work.setWorkId(9);
					todoWork.addTodoWorks(work);
				}
				/** 运营总监 减事务 */
				work.setCount(-1);
				work.setUserId(user.getId());
				work.setWorkId(8);
				todoWork.addTodoWorks(work);
			}
			/** 法务专员 */
			if (user.getRoleId() == Constants.ROLE_COMPLIANCE_OFFICER) {
				/** 法务专员 符合时给区域总监添加事务 如果区域总监不存在给销售总监加事务 */
				if ("1".equals(saleBackContractOfAddDto.getLegalIdea())
						&& ("4".equals(saleBackContractOfAddDto.getStatus()) || saleBackContractOfAddDto
								.getStatus()
								.equals(
										Constants.BACKCONTRACT_STATUS_AREAMAJWAITCOM
												.toString()))) {
					work.setCount(1);
					if (saleBackContractOfAddDto.getStatus().equals(//销售总监待评审
							Constants.BACKCONTRACT_STATUS_SELLMAJWAITCOM
									.toString())) {
						work.setUserId(saleBackContractOfAddDto.getSellMajId());
					} else {//区域总监待评审
						work.setUserId(saleBackContractOfAddDto.getAreaMajId());
					}
					work.setWorkId(8);
					todoWork.addTodoWorks(work);
				}
				/** 法务专员 减事务 */
				work.setCount(-1);
				work.setUserId(user.getId());
				work.setWorkId(8);
				todoWork.addTodoWorks(work);
			}
			/** 区域总监 */
			if (user.getRoleId() == Constants.ROLE_REGIONAL_DIRECTOR) {
				/** 区域总监 符合时给销售总监加事务 */
				if ("111".equals(saleBackContractOfAddDto.getAreaMajIdea())
						&& "4".equals(saleBackContractOfAddDto.getStatus())) {
					work.setCount(1);
					work.setUserId(saleBackContractOfAddDto.getSellMajId());
					work.setWorkId(8);
					todoWork.addTodoWorks(work);
				}
				/** 区域总监 减事务 */
				work.setCount(-1);
				work.setUserId(user.getId());
				work.setWorkId(8);
				todoWork.addTodoWorks(work);
			}
			dao.update(
					"salesBackContract_sqlMap.modifySaleBackContractOfComment",
					saleBackContractOfAddDto);
			dao.commitTransaction();
			issuccess = 1;
		} catch (Exception e) {
			issuccess = 0;
			try {
				dao.endTransaction();
			} catch (Exception e1) {
				log.error("评审售退货合同失败！", e1);
			}
			log.error("评审售退货合同失败！", e);
		} finally {
			try {
				dao.endTransaction();
			} catch (Exception e) {
				log.error("评审售退货合同失败！", e);
			}
		}
		return issuccess;
	}

	public int deleteSalesBackContract(String id) {
		int issuccess = 0;
		try {
			/** 事物开始 */
			dao.beginTransaction();
			/** 删除退货合同明细 */
			dao.delete("salesBackContract_sqlMap.deleteSaleBackContractDetail",
					id);
			/** 删除退货合同 */
			dao.delete("salesBackContract_sqlMap.deleteSalesBackContract", id);
			dao.commitTransaction();
			issuccess = 1;
		} catch (Exception e) {
			issuccess = 0;
			try {
				dao.endTransaction();
			} catch (Exception e1) {
				log.error("删除售退货合同失败！", e1);
			}
			log.error("删除售退货合同失败！", e);
		} finally {
			try {
				dao.endTransaction();
			} catch (Exception e) {
				log.error("删除售退货合同失败！", e);
			}
		}
		return issuccess;
	}

	public Object getCommentTable(String id) {
		try {
			return dao.queryForObject(
					"salesBackContract_sqlMap.getCommentTable", id);
		} catch (Exception e) {
			log.error("查看评审表显示失败", e);
		}
		return null;
	}

	public int modifyCommentTableOfStatus(String id, UserEntity user) {
		int isSuccess = 0;
		try {
			String status = dao.queryForObject(
					"salesBackContract_sqlMap.selectStatusOfSaleBack", id)
					.toString();
			if ("8".equals(status)) {
				/** 改状态 */
				isSuccess = dao.update(
						"salesBackContract_sqlMap.modifyCommentTableOfStatus",
						id);
				/** 减事物 */
				if (isSuccess != 0) {
					ITodoWorkService todoWork = (ITodoWorkService) Container
							.getBean("todoWrokServiceImpl");
					WorkEntity work = new WorkEntity();
					work.setCount(-1);
					work.setUserId(user.getId());
					work.setWorkId(9);
					todoWork.addTodoWorks(work);
				}

			}

			isSuccess = 1;
		} catch (Exception e) {
			log.error("改状态失败", e);
			isSuccess = 0;
		}
		return isSuccess;
	}

	public int modifySaleBackContractOfDecide(
			SaleBackContractOfAddDto saleBackContractOfAddDto) {
		try {
			return dao.update(
					"salesBackContract_sqlMap.modifySaleBackContractOfDecide",
					saleBackContractOfAddDto);
		} catch (Exception e) {
			log.error("确认销售合同失败", e);
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	public List<Object> getSalesBackContractIsExist(
			SaleBackContractOfAddDto saleBackContractOfAddDto) {
		try {
			return dao.queryForlist(
					"salesBackContract_sqlMap.getSalesBackContractIsExist",
					saleBackContractOfAddDto);
		} catch (Exception e) {
			log.error("判断合同号是否存在失败", e);
		}
		return null;
	}

}
