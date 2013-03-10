/**
 * ClassName  ISampleInService
 *
 * History
 * Create User: zhangzx
 * Create Date: 2010-7-12
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.iservice.stock;

import java.math.BigDecimal;
import java.util.List;


import cn.com.thtf.egov.cms.dto.SampleInAssessDto;


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

public interface ISampleInService {
	/**
	 * 查询样品归还评审
	 * 
	 * @author 蒋名星
	 * @param id
	 * @return SampleInAssessDto
	 * @throws Exception
	 */
	public SampleInAssessDto getSampleInById(String id) throws Exception;

	/**
	 * 查询产品信息
	 * 
	 * @author 蒋名星
	 * @param assessDto
	 * @return SampleInAssessDto
	 * @throws Exception
	 */
	public List<SampleInProductInfoDto> getProductInfo(
			SampleInAssessDto assessDto) throws Exception;
	
	/**
	 * 查询新建产品信息
	 * 
	 * @author 蒋名星
	 * @param assessDto
	 * @return SampleInAssessDto
	 * @throws Exception
	 */
	public List<SampleInProductInfoDto> getNewProductInfo(
			SampleOutAssessDto assessDto) throws Exception;
	
	/**
	 * 样品归还管理评审办事务自减
	 * 
	 * @author 蒋名星
	 * @param sampleInAssessDto
	 * @param work
	 * @param workself
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateSampleInAssessDto(SampleInAssessDto sampleInAssessDto,
			 WorkEntity workself) throws Exception;

	/**
	 * 样品归还管理评审不发待办事务
	 * 
	 * @author 蒋名星
	 * @param sampleInAssessDto
	 * @param work
	 * @param workself
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateAssessNoWork(SampleInAssessDto sampleInAssessDto,
			WorkEntity workself) throws Exception;
	
	/**
	 * 新建样品归还单保存
	 * 
	 * @author 蒋名星
	 * @param adddto
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public boolean addSampleInSave(SampleInAssessDto adddto,
			List<SampleInDetailDto> list) throws Exception;
	
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
	public boolean addSampleInSubmit(SampleInAssessDto adddto,
			WorkEntity work, List<SampleInDetailDto> sampleInDetailEntities
			) throws Exception;

	 /**
     * 样品归还列表初始化
     * 
     * @param page
     * @param user
     * @return
     * @throws Exception
     * @author zhangzx
     */
    public List<SampleInListDto> getSampleInList(NewPage page, UserEntity user)
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
     * 根据条件查询归还单
     * @return
     * @throws Exception
     * @author zhangzx
     */
    public List<SampleInListDto> getSampleInListByCondition(NewPage page,
            SampleInSearchDto sampleInSearchDto) throws Exception;
    
    /**
     * 查询修改页面归还单明细
     * 
     * @param assessDto
     * @return
     * @throws Exception
     * @author zhangzx
     */
    public List<SampleInProductInfoDto> getProductInfoModify(SampleInAssessDto assessDto)
            throws Exception;
   /**
    * 修改页面保存
    * @param sampleInEntity
    * @param list
    * @return
    * @throws Exception
    * @author zhangzx
    */
    @SuppressWarnings("unchecked")
    public boolean modifySampleInSave(SampleInDto sampleInDto, List list)
    throws Exception;
    
    /**
     * 修改样品借出提交(发待办事务)
     * @param sampleInDto
     * @param list
     * @param work
     * @return
     * @throws Exception
     * @author zhangzx
     */
    @SuppressWarnings("unchecked")
    public boolean modifySampleInSubmit(SampleInDto sampleInDto, List list, WorkEntity work)
    throws Exception;
    
    /**
     * 删除归还单(同时删除明细)
     * @param id
     * @return
     * @throws Exception
     * @author zhangzx
     */
    public boolean deleteSampleIn(String sampleInId)throws Exception;
    /**
     * 获得借出时的价格
     * @param SampleOutDetailEntity
     * @throws Exception
     * @author liuqg
     */ 
    public BigDecimal getSampleOutPrice(SampleOutDetailEntity outDetailEntity )throws Exception;
}
