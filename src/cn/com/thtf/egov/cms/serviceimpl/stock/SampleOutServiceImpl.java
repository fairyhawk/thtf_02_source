/**
 * ClassName  SampleOutServiceImpl
 *
 * History
 * Create User: liuqg
 * Create Date: 2010-7-5
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.serviceimpl.stock;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.BaseService;
import cn.com.thtf.egov.cms.application.NewIDao;
import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.dto.CompanyCustomerSupplierAddressDto;
import cn.com.thtf.egov.cms.dto.SampleOutAssessDto;
import cn.com.thtf.egov.cms.dto.SampleOutListDto;
import cn.com.thtf.egov.cms.dto.SampleOutProductInfoDto;
import cn.com.thtf.egov.cms.dto.SampleOutSearchDto;
import cn.com.thtf.egov.cms.entity.CompanyEntity;
import cn.com.thtf.egov.cms.entity.CustomerEntity;
import cn.com.thtf.egov.cms.entity.ProductTypeEntity;
import cn.com.thtf.egov.cms.entity.SampleOutDetailEntity;
import cn.com.thtf.egov.cms.entity.SampleOutEntity;
import cn.com.thtf.egov.cms.entity.StockEntity;
import cn.com.thtf.egov.cms.entity.StockroomEntity;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.entity.WorkEntity;
import cn.com.thtf.egov.cms.iservice.stock.ISampleOutService;
import cn.com.thtf.egov.cms.iservice.work.ITodoWorkService;
import cn.com.thtf.egov.cms.serviceimpl.work.TodoWorkService;
import cn.com.thtf.egov.cms.util.Container;

/**
 * 
 * @author liuqg
 */

public class SampleOutServiceImpl extends BaseService implements
		ISampleOutService {
	/** log */
	private static Logger log = LoggerFactory
			.getLogger(SampleOutServiceImpl.class);
	/** NewIDao */
	private NewIDao dao;

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
		SampleOutServiceImpl.log = log;
	}

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
     * 样品借出列表初始化by zhangzx
     * 
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<SampleOutListDto> getSampleOutList(NewPage page, UserEntity user)  {
        List result = null;
        try {
            result = queryRecords(dao, "selectSampleOutList.selectSampleOutList", user,
                    page);
        } catch (Exception e) {
            log.error("获取样品借出列表错误:", e);
        }
        return result;
    }

    /**
     * 获得产品分类列表 by zhangzx
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<ProductTypeEntity> getProductType()  {
        List result = null;
        try {
            result = dao.queryForlist("sampleOut_sqlMap.selectProductTypeList", null);
        } catch (Exception e) {
            log.error("检索产品分类错误:", e);
        }
        return result;
    }

    /**
     * 获得所有库房信息(不含虚拟库) by zhangzx
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<StockroomEntity> getStockroom()  {
        List result = null;
        try {
            result = dao.queryForlist("sampleOut_sqlMap.selectStockroom", null);
        } catch (Exception e) {
            log.error("检索库房信息错误:", e);
        }
        return result;
    }

    /**
     * 根据条件检索样品借出单 by zhangzx
     *
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<SampleOutListDto> getSampleOutListByCondition(NewPage page,
            SampleOutSearchDto sampleOutSearchDto)  {
        List result = null;
        try {
            result = queryRecords(dao,
                    "selectSampleOutListByCondition.selectSampleOutListByCondition",
                    sampleOutSearchDto, page);
        } catch (Exception e) {
            log.error("获取检索样品借出单一览错误:", e);
        }
        return result;
    }


     /**
     * 查询修改页面借出单明细
     * @param assessDto
     * @return
     * @author zhangzx
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<SampleOutProductInfoDto> getProductInfoModify(SampleOutAssessDto assessDto)
             {
        List result = null;

        try {
            result = dao.queryForlist("sampleOut_sqlMap.selectProductInfoModify", assessDto);
        } catch (Exception e) {
            log.error("查询产品信息失败", e);
        }
        return result;
    }
    
    /**
     * 修改样品借出保存
     * @param modifySampleOutDto
     * @param list
     * @return
     * @author zhangzx
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean modifySampleOutSave(SampleOutEntity modifySampleOutDto, List list)
             {
        boolean isSuccess = false;
        try {
            dao.beginTransaction();
            /* 移库 */
            int count1 = dao.update("sampleOut_sqlMap.updateSampleOutSave",
                    modifySampleOutDto);

            String sampleOutId = modifySampleOutDto.getId();

            dao.delete("sampleOut_sqlMap.deleteSampleOutDetail", sampleOutId);
            /* 移库明细 */
            for (int i = 0; i < list.size(); i++) {
                SampleOutDetailEntity sampleOutDetailEntity = (SampleOutDetailEntity) list.get(i);
                dao.insert("sampleOut_sqlMap.insertSampleOutDetail", sampleOutDetailEntity);
            }

            if (count1 != 1) {
                return isSuccess;
            }
            dao.commitTransaction();
            isSuccess = true;
        } catch (Exception e) {
            log.error("样品借出修改保存失败", e);
            isSuccess = false;
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.debug("样品借出修改保存失败", e);
                isSuccess = false;
            }
        }
        return isSuccess;
    }
    
    

    /**
     * 修改样品借出提交(发待办事务)
     * @param modifySampleOutDto
     * @param list
     * @param stockEntities
     * @return
     * @author zhangzx
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean modifySampleOutSubmitWork(SampleOutEntity modifySampleOutDto,
            List list, WorkEntity workEntity, List<StockEntity> stockEntities)
             {
        boolean isSuccess = false;
        try {
            dao.beginTransaction();
            /* 移库 */
            int count1 = dao.update("sampleOut_sqlMap.updateSampleOut",
                    modifySampleOutDto);
            int count2 = 0;

            String sampleOutId = modifySampleOutDto.getId();

            dao.delete("sampleOut_sqlMap.deleteSampleOutDetail", sampleOutId);
            /* 移库明细 库存表 */
            for (int i = 0; i < list.size(); i++) {
                SampleOutDetailEntity sampleOutDetailEntity = (SampleOutDetailEntity) list.get(i);
                dao.insert("sampleOut_sqlMap.insertSampleOutDetail", sampleOutDetailEntity);

                StockEntity stockEntity = stockEntities.get(i);

                count2 = dao.update("sampleOut_sqlMap.updateStockLockNum", stockEntity);

                if (count2 != 1) {
                    return isSuccess;
                }
            }
            /* 待办事务 */
            boolean work = NextAssessManWork(workEntity);

            if (count1 != 1 || work == false) {
                return isSuccess;
            }
            dao.commitTransaction();
            isSuccess = true;
        } catch (Exception e) {
            log.error("样品借出修改提交失败", e);
            isSuccess = false;
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.debug("样品借出修改提交失败", e);
                isSuccess = false;
            }
        }
        return isSuccess;
    }
    
    /**获得收货地址
     * @author zhangzx
     */
    @SuppressWarnings("unchecked")
    @Override
    public CompanyCustomerSupplierAddressDto getAddressInfo(Map map)
             {
        CompanyCustomerSupplierAddressDto addressDto = null;

        try {
            addressDto = (CompanyCustomerSupplierAddressDto) dao.queryForObject(
                    "sampleOut_sqlMap.selectAddressInfo", map);
        } catch (Exception e) {
            log.error("获得收货地址失败", e);
        }
        return addressDto;
    }

    /**
     * 修改时计算库存单价
     * 
     * @param productId
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List getProductPriceModify(String[] productIds) {
        List prices = new ArrayList();
        try {
            for (int i = 0; i < productIds.length; i++) {
                String productId =  productIds[i];
                String price = (String) dao.queryForObject(
                        "sampleOut_sqlMap.selectProductPriceModify", productId);
                prices.add(price);
            }
        } catch (Exception e) {
            log.error("样品借出修改失败", e);
        }
        return prices;
    }

    
    
	/**
     *  查询样品借出评审
     * 
     * @author jiangmx
     * @param outId
     * @return SampleOutAssessDto
     */
	@Override
	public SampleOutAssessDto getSampleOutById(String outId)  {
		SampleOutAssessDto assess = null;

        try {
            assess = (SampleOutAssessDto) dao.queryForObject(
                    "sampleOut_sqlMap.selectSampleOutInfo", outId);
        } catch (Exception e) {
            log.error("样品借出评审信息失败", e);
        }
        return assess;
	}

	/**
     * 查询产品信息
     * 
     * @author jiangmx
     * @param assessDto
     * @return SampleOutProductInfoDto
     */
	@SuppressWarnings("unchecked")
	@Override
	public List<SampleOutProductInfoDto> getProductInfo(
			SampleOutAssessDto assessDto)  {
		 List result = null;

	        try {
	            result = dao.queryForlist("sampleOut_sqlMap.selectProductInfo", assessDto);
	        } catch (Exception e) {
	            log.error("查询产品信息失败", e);
	        }
	        return result;
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
     * 样品借出评审不发待办事务
     * 
     * @author jiangmx
     * @param sampleOutAssessDto
     * @param workself
     * @return boolean
     */
	@Override
	public boolean updateAssessNoWork(SampleOutAssessDto sampleOutAssessDto,
			WorkEntity workself, List<StockEntity> stockEntities)
			 {
		int count = -1;
        boolean isSuccess = false;

        try {
            dao.beginTransaction();
            count = dao.update("sampleOut_sqlMap.updateSampleOutAssess",
            		sampleOutAssessDto);

            for (int i = 0; i < stockEntities.size(); i++) {

                /* 更新产品的冻结数 */
                StockEntity stockEntity = (StockEntity) stockEntities.get(i);
                int recount = dao.update("sampleOut_sqlMap.updateLockNum", stockEntity);
                if (recount != 1) {
                    return false;
                }
            }

            boolean assessManself = NextAssessManWork(workself);
            if (count != 1 || assessManself == false) {
                return isSuccess;
            }
            dao.commitTransaction();

            isSuccess = true;
        } catch (Exception e) {
            log.error("样品借出评审未通过失败", e);
            isSuccess = false;
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.debug("样品借出评审未通过失败", e);
                isSuccess = false;
            }
        }
        return isSuccess;
	}

	/**
     * 样品借出评审待办事务自减
     * 
     * @author jiangmx
     * @param sampleOutAssessDto
     * @param work
     * @param workself
     * @return boolean
     */
	@Override
	public boolean updateSampleOutAssess(SampleOutAssessDto sampleOutAssessDto,
			WorkEntity workself)  {
		int count = -1;
        boolean isSuccess = false;

        try {
            dao.beginTransaction();
            count = dao.update("sampleOut_sqlMap.updateSampleOutAssess",
            		sampleOutAssessDto);
            boolean assessManself = NextAssessManWork(workself);
            if (count != 1 || assessManself == false) {
                return isSuccess;
            }
            dao.commitTransaction();

            isSuccess = true;
        } catch (Exception e) {
            log.error("样品借出评审通过失败", e);
            isSuccess = false;
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.debug("样品借出评审通过失败", e);
                isSuccess = false;
            }
        }
        return isSuccess;
	}
	
	/**
     * 新建样品借出单保存
     * 
     * @author jiangmx
     * @param SampleOutAssessDto
     *            adddto
     * @param List
     *            <SampleOutDetailEntity>
     * @return
     */
	@Override
	public boolean addSampleOutSave(SampleOutAssessDto adddto,
			List<SampleOutDetailEntity> list)  {
		
		  try {
	            dao.beginTransaction();
	            /* 样品借出 */
	            dao.insert("sampleOut_sqlMap.insertSampleOut", adddto);
	            /* 样品借出明细 */
	            for (int i = 0; i < list.size(); i++) {
	            	SampleOutDetailEntity detailEntity = (SampleOutDetailEntity) list.get(i);
	                dao.insert("sampleOut_sqlMap.insertSampleOutDetail", detailEntity);
	            }
	            dao.commitTransaction();
	            return true;
	        } catch (Exception e) {
	            log.error("新建样品借出单保存失败", e);
	            return false;
	        } finally {
	            try {
	                dao.endTransaction();
	            } catch (Exception e) {
	                log.debug("新建样品借出单保存失败", e);
	                return false;
	            }
	        }
	}
	
	/**
	 * 新建样品借出单提交
	 * 
	 * @author 蒋名星
	 * @param adddto
	 *            样品借出单
	 * @param WorkEntity
	 *            work 待办事务
	 * @param List
	 *            <SampleOutDetailEntity> sampleOutDetailEntities 样品借出明细
	 * @param List
	 *            <StockEntity> stockEntities 库存
	 * @return
	 */
	@Override
	public boolean addSampleOutSubmit(SampleOutAssessDto adddto,
			WorkEntity work,
			List<SampleOutDetailEntity> sampleOutDetailEntities,
			List<StockEntity> stockEntities)  {
		boolean isSuccess = false;
        try {
            dao.beginTransaction();
            /* 样品借出单 */
            dao.insert("sampleOut_sqlMap.insertSampleOut", adddto);
            for (int i = 0; i < sampleOutDetailEntities.size(); i++) {
            	SampleOutDetailEntity detailEntity = (SampleOutDetailEntity) sampleOutDetailEntities
                        .get(i);
                /* 样品借出单明细 插入 */
                dao.insert("sampleOut_sqlMap.insertSampleOutDetail", detailEntity);

                /* 更新产品的冻结数 */
                StockEntity stockEntity = (StockEntity) stockEntities.get(i);
                int count = dao
                        .update("sampleOut_sqlMap.updateStockLockNum", stockEntity);
                if (count != 1) {
                    log.error("库存已经被修改");
                    return false;
                }
            }
            /* 待办事务 采购专员提交时发待办事务 */
            if (4 == adddto.getStatus()) {
                isSuccess = NextAssessManWork(work);
                if (isSuccess == false) {
                    return isSuccess;
                }
            }
            /* 待办事务 销售经理提交时发待办事务 */
            else if(2 == adddto.getStatus()){
            	isSuccess = NextAssessManWork(work);
                if (isSuccess == false) {
                    return isSuccess;
                }
            }
            dao.commitTransaction();
            isSuccess = true;
        } catch (Exception e) {
            log.error("新建样品借出单提交失败", e);
            isSuccess = false;
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.debug("新建样品借出单提交失败", e);
                isSuccess = false;
            }
        }
        return isSuccess;
	}
	
	/**
	 * 查询用户权限
	 * 
	 * @author 蒋名星
	 * @param userId
	 * @return roleId
	 * @throws Exception
	 */
	@Override
	public String selectUserRoleId(String userId) throws Exception {
		String userRoleId = "";
		try {
			userRoleId = (String) dao.queryForObject(
					"sampleOut_sqlMap.selectUserRoleId", userId);
		} catch (Exception e) {
			log.error("查询用户权限错误", e);
		}
		return userRoleId;
	
	}
	
	/**
	 * @author liuqg 客户列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerEntity> selectCustomer(NewPage newPage,UserEntity user) {
		List list = new ArrayList<CustomerEntity>();
		try {

			list = queryRecords(dao, "selectSampleOutCustomer.getData", user,
					newPage);

		} catch (Exception e) {
			log.error("获取客户错误" + e);
		}
		return list;
	}

	/**
	 * 检索客户
	 * 
	 * @author liuqg
	 * @param name
	 */
	@SuppressWarnings("unchecked")
	public List<CustomerEntity> selectCustomerByName(CustomerEntity customerEntity,
			NewPage newPage) {
		List list = new ArrayList<CustomerEntity>();
		try {

			list = queryRecords(dao, "selectSampleOutCustomerObj.getData",
					customerEntity, newPage);

		} catch (Exception e) {
			log.error("获取客户错误" + e);
		}
		return list;
	}
	/**
	 * 删除借出单(同时删除明细)
	 * @param id
	 * @return
	 */
	@Override
	public boolean deleteSampleOut(String id) {
		try {
			dao.beginTransaction();
			dao.delete("sampleOut_sqlMap.deleteSampleOut", id);
			dao.delete("sampleOut_sqlMap.deleteSampleOutDetail", id);
			dao.commitTransaction(); 
			return true;
		} catch (Exception e) {
			log.error("删除借出单错误"); 
			return false;
		}finally{
			try {
				dao.endTransaction(); 
			} catch (Exception e2) {
				log.error("删除借出单错误");
				return false;
			}
		}
		
	}
	
	/**
	 * 获得公司名称
	 * @return
	 */
	
	public CompanyEntity selectcompany(){
		CompanyEntity  companyEntity = new CompanyEntity();
		try {
			companyEntity=(CompanyEntity) dao.queryForObject("sampleOut_sqlMap.selectcompany", null);
		} catch (Exception e) {
			log.error("获得公司名称错误");
		}
		return companyEntity;
		
	}
    /* 客户收获地址 */
    @SuppressWarnings("unchecked")
    @Override
    public List getCustomerAddressList(Map map, NewPage page) {
        log.info("获取客户收获地址信息");
        
        List list = null;
        try {
            list = queryRecords(dao, "customerAddressSampleOut.selectCustomers", map, page);
        } catch (Exception e) {
            log.error("获取客户收获地址信息失败" + e);
        }
        return list;
    }
   
}
