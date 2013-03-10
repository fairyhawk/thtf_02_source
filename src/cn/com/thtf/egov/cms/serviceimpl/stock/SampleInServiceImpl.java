/**
 * ClassName  SampleInServiceImpl
 *
 * History
 * Create User: zhangzx
 * Create Date: 2010-7-12
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.serviceimpl.stock;


import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.BaseService;
import cn.com.thtf.egov.cms.application.NewIDao;
import cn.com.thtf.egov.cms.dto.SampleInAssessDto;
import cn.com.thtf.egov.cms.iservice.stock.ISampleInService;
import cn.com.thtf.egov.cms.iservice.work.ITodoWorkService;
import cn.com.thtf.egov.cms.serviceimpl.work.TodoWorkService;
import cn.com.thtf.egov.cms.util.Container;
import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.dto.SampleInDetailDto;
import cn.com.thtf.egov.cms.dto.SampleInDto;
import cn.com.thtf.egov.cms.dto.SampleInListDto;
import cn.com.thtf.egov.cms.dto.SampleInProductInfoDto;
import cn.com.thtf.egov.cms.dto.SampleInSearchDto;
import cn.com.thtf.egov.cms.dto.SampleOutAssessDto;
import cn.com.thtf.egov.cms.entity.ProductTypeEntity;
import cn.com.thtf.egov.cms.entity.SampleOutDetailEntity;
import cn.com.thtf.egov.cms.entity.StockroomEntity;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.entity.WorkEntity;

/**
 * 
 * @author zhangzx
 */

public class SampleInServiceImpl extends BaseService implements
		ISampleInService {
	/** log */
	private static Logger log = LoggerFactory
			.getLogger(SampleInServiceImpl.class);
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
		SampleInServiceImpl.log = log;
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
	 * 查询样品归还单评审
	 * 
	 * @author jiangmx
	 * @param id
	 * @return SampleInAssessDto
	 */
	@Override
	public SampleInAssessDto getSampleInById(String id) {
		SampleInAssessDto assess = null;

		try {
			assess = (SampleInAssessDto) dao.queryForObject(
					"sampleIn_sqlMap.selectSampleInInfo", id);
		} catch (Exception e) {
			log.error("样品归还评审信息失败", e);
		}
		return assess;
	}

	/**
	 * 查询产品信息
	 * 
	 * @author jiangmx
	 * @param assessDto
	 * @return SampleInProductInfoDto
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SampleInProductInfoDto> getProductInfo(
			SampleInAssessDto assessDto){
		List result = null;

		try {
			result = dao.queryForlist("sampleIn_sqlMap.selectSampleInProductInfoModify",
					assessDto);
		} catch (Exception e) {
			log.error("查询产品信息失败", e);
		}
		return result;
	
	}
	
	/**
	 * 查询新建产品信息
	 * 
	 * @author jiangmx
	 * @param assessDto
	 * @return SampleInProductInfoDto
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SampleInProductInfoDto> getNewProductInfo(
			SampleOutAssessDto assessDto){
		List result = null;

		try {
			result = dao.queryForlist("sampleIn_sqlMap.selectNewProductInfo",
					assessDto);
		} catch (Exception e) {
			log.error("查询新建产品信息失败", e);
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
     * 样品归还评审不发待办事务
     * 
     * @author jiangmx
     * @param sampleInAssessDto
     * @param workself
     * @return boolean
     */
	@Override
	public boolean updateAssessNoWork(SampleInAssessDto sampleInAssessDto,
			WorkEntity workself){
		int count = -1;
        boolean isSuccess = false;

        try {
            dao.beginTransaction();
            count = dao.update("sampleIn_sqlMap.updateSampleInAssess",
            		sampleInAssessDto);

            boolean assessManself = NextAssessManWork(workself);
            if (count != 1 || assessManself == false) {
                return isSuccess;
            }
            dao.commitTransaction();

            isSuccess = true;
        } catch (Exception e) {
            log.error("样品归还评审未通过失败", e);
            isSuccess = false;
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.debug("样品归还评审未通过失败", e);
                isSuccess = false;
            }
        }
        return isSuccess;
	}

	/**
     * 样品归还评审待办事务自减
     * 
     * @author jiangmx
     * @param sampleInAssessDto
     * @param work
     * @param workself
     * @return boolean
     */
	@Override
	public boolean updateSampleInAssessDto(SampleInAssessDto sampleInAssessDto,
			WorkEntity workself){
		int count = -1;
        boolean isSuccess = false;

        try {
            dao.beginTransaction();
            count = dao.update("sampleIn_sqlMap.updateSampleInAssess",
            		sampleInAssessDto);
            boolean assessManself = NextAssessManWork(workself);
            if (count != 1 || assessManself == false) {
                return isSuccess;
            }
            dao.commitTransaction();

            isSuccess = true;
        } catch (Exception e) {
            log.error("样品归还评审通过失败", e);
            isSuccess = false;
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.debug("样品归还评审通过失败", e);
                isSuccess = false;
            }
        }
        return isSuccess;
	}
	
	/**
     * 新建样品归还单保存
     * 
     * @author jiangmx
     * @param SampleInAssessDto
     *            adddto
     * @param List
     *            <SampleInDetailDto>
     * @return
     * @throws Exception
     */
	@Override
	public boolean addSampleInSave(SampleInAssessDto adddto,
			List<SampleInDetailDto> list){
		 try {
	            dao.beginTransaction();
	            /* 样品归还单 */
	            dao.insert("sampleIn_sqlMap.insertSampleIn", adddto);
	            /* 样品归还单明细 */
	            for (int i = 0; i < list.size(); i++) {
	            	SampleInDetailDto detailEntity = (SampleInDetailDto) list.get(i);
	                dao.insert("sampleIn_sqlMap.insertSampleInDetailDto", detailEntity);
	            }
	            dao.commitTransaction();
	            return true;
	        } catch (Exception e) {
	            log.error("新建样品归还单保存失败", e);
	            return false;
	        } finally {
	            try {
	                dao.endTransaction();
	            } catch (Exception e) {
	                log.debug("新建样品归还单保存失败", e);
	                return false;
	            }
	        }
	}

	/**
	 * 新建样品归还单提交
	 * 
	 * @author 蒋名星
	 * @param adddto
	 *            样品借出单
	 * @param WorkEntity
	 *            work 待办事务
	 * @param List
	 *            <SampleInDetailDto> sampleInDetailEntities 样品借出明细
	 * @param List
	 *            <StockEntity> stockEntities 库存
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean addSampleInSubmit(SampleInAssessDto adddto, WorkEntity work,
			List<SampleInDetailDto> sampleInDetailEntities){
		boolean isSuccess = false;
        try {
            dao.beginTransaction();
            /* 样品归还单 */
            dao.insert("sampleIn_sqlMap.insertSampleIn", adddto);
            for (int i = 0; i < sampleInDetailEntities.size(); i++) {
            	SampleInDetailDto detailEntity = (SampleInDetailDto) sampleInDetailEntities
                        .get(i);
                /* 样品归还单明细 插入 */
                dao.insert("sampleIn_sqlMap.insertSampleInDetailDto", detailEntity);

            }
            /* 待办事务 采购专员提交时发待办事务 或 销售经理提交时发待办事务 */
            if (2 == adddto.getStatus()) {
                isSuccess = NextAssessManWork(work);
                if (isSuccess == false) {
                    return isSuccess;
                }
            }
            
            dao.commitTransaction();
            isSuccess = true;
        } catch (Exception e) {
            log.error("新建样品归还单提交失败", e);
            isSuccess = false;
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.debug("新建样品归还单提交失败", e);
                isSuccess = false;
            }
        }
        return isSuccess;
	}


		 /**
     * 获得产品分类列表
     * 
     * @return
     * @throws Exception
     * @author zhangzx
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<ProductTypeEntity> getProductType(){
        List result = null;
        try {
            result = dao.queryForlist("sampleIn_sqlMap.selectProductTypeList", null);
        } catch (Exception e) {
            log.error("检索产品分类错误:", e);
        }
        return result;
    }

    /**
     * 样品归还列表初始化
     * 
     * @param page
     * @param user
     * @return
     * @throws Exception
     * @author zhangzx
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<SampleInListDto> getSampleInList(NewPage page, UserEntity user){
        List result = null;
        try {
            result = queryRecords(dao, "selectSampleInList.selectSampleInList", user,
                    page);
        } catch (Exception e) {
            log.error("获取样品归还列表错误:", e);
        }
        return result;
    }

    /**
     * 获得所有库房信息
     * 
     * @return
     * @throws Exception
     * @author zhangzx
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<StockroomEntity> getStockroom(){
        List result = null;
        try {
            result = dao.queryForlist("sampleIn_sqlMap.selectStockroom", null);
        } catch (Exception e) {
            log.error("检索库房信息错误:", e);
        }
        return result;
    }

    /**
     * 根据条件查询归还单
     * @author zhangzx
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<SampleInListDto> getSampleInListByCondition(NewPage page,
            SampleInSearchDto sampleInSearchDto){
        List result = null;
        try {
            result = queryRecords(dao,
                    "selectSampleInListByCondition.selectSampleInListByCondition",
                    sampleInSearchDto, page);
        } catch (Exception e) {
            log.error("获取检索样品归还单一览错误:", e);
        }
        return result;
    }

    /**
     * 查询修改页面归还单明细
     * by zhangzx
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<SampleInProductInfoDto> getProductInfoModify(SampleInAssessDto assessDto){
        List result = null;

        try {
            result = dao.queryForlist("sampleIn_sqlMap.selectSampleInProductInfoModify", assessDto);
        } catch (Exception e) {
            log.error("查询产品信息失败", e);
        }
        return result;
    }

    /**
     * 修改页面保存
     * by zhangzx
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean modifySampleInSave(SampleInDto sampleInDto, List list){
        boolean isSuccess = false;
        try {
            dao.beginTransaction();
            /* 归还单 */
            int count1 = dao.update("sampleIn_sqlMap.updateSampleInSave",
                    sampleInDto);

            String sampleInId = sampleInDto.getId();
            /* 归还明细明细 */
            dao.delete("sampleIn_sqlMap.deleteSampleInDetail", sampleInId);
            for (int i = 0; i < list.size(); i++) {
                SampleInDetailDto sampleInDetailDto = (SampleInDetailDto) list.get(i);
                dao.insert("sampleIn_sqlMap.insertSampleInDetail", sampleInDetailDto);
            }

            if (count1 != 1) {
                return isSuccess;
            }
            dao.commitTransaction();
            isSuccess = true;
        } catch (Exception e) {
            log.error("样品归还修改保存失败", e);
            isSuccess = false;
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.debug("样品归还修改保存失败", e);
                isSuccess = false;
            }
        }
        return isSuccess;
    }

    /**
     * 修改样品借出提交(发待办事务)
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean modifySampleInSubmit(SampleInDto sampleInDto, List list,
            WorkEntity work){
        boolean isSuccess = false;
        try {
            dao.beginTransaction();
            /* 归还单 */
            int count1 = dao.update("sampleIn_sqlMap.updateSampleIn",
                    sampleInDto);

            String sampleInId = sampleInDto.getId();
            /* 归还明细明细 */
            dao.delete("sampleIn_sqlMap.deleteSampleInDetail", sampleInId);
            for (int i = 0; i < list.size(); i++) {
                SampleInDetailDto sampleInDetailDto = (SampleInDetailDto) list.get(i);
                dao.insert("sampleIn_sqlMap.insertSampleInDetail", sampleInDetailDto);
            }
            
            /* 待办事务 */
            boolean workSuc = NextAssessManWork(work);

            if (count1 != 1||workSuc ==false) {
                return isSuccess;
            }
            dao.commitTransaction();
            isSuccess = true;
        } catch (Exception e) {
            log.error("样品归还修改保存失败", e);
            isSuccess = false;
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.debug("样品归还修改保存失败", e);
                isSuccess = false;
            }
        }
        return isSuccess;
    }

    /**
     * 删除归还单(同时删除明细)
     * @param id
     * @return
     * @throws Exception
     * @author zhangzx
     */
    @Override
    public boolean deleteSampleIn(String sampleInId){
        try {
            dao.beginTransaction();
            dao.delete("sampleIn_sqlMap.deleteSampleIn", sampleInId);
            dao.delete("sampleIn_sqlMap.deleteSampleInDetail", sampleInId);
            dao.commitTransaction(); 
            return true;
        } catch (Exception e) {
            log.error("删除归还单错误",e); 
            return false;
        }finally{
            try {
                dao.endTransaction(); 
            } catch (Exception e2) {
                log.error("删除归还单错误",e2);
                return false;
            }
        }
    }

    /* (non-Javadoc)
     * @see cn.com.thtf.egov.cms.iservice.stock.ISampleInService#getSampleOutPrice(java.lang.String, java.lang.String)
     */
    @Override
    public BigDecimal getSampleOutPrice(SampleOutDetailEntity outDetailEntity)
            throws Exception {
        return (BigDecimal)dao.queryForObject("sampleIn_sqlMap.getSampleOutPrice", outDetailEntity);
         
    }
    
    

   
}
