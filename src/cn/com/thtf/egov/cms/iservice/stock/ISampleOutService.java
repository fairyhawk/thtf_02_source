/**
 * ClassName  ISampleOutService
 *
 * History
 * Create User: liuqg
 * Create Date: 2010-7-5
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.iservice.stock;

import java.util.List;
import java.util.Map;

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

/**
 * 
 * @author liuqg
 */

public interface ISampleOutService {
    /**
     * 样品借出列表初始化
     * 
     * @param page
     * @param user
     * @return
     * @throws Exception
     * @author zhangzx
     */
    public List<SampleOutListDto> getSampleOutList(NewPage page, UserEntity user)
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
     * 根据条件检索移库单
     * @param page
     * @param sampleOutSearchDto
     * @return
     * @throws Exception
     * @author zhangzx
     */
    public List<SampleOutListDto> getSampleOutListByCondition(NewPage page,
            SampleOutSearchDto sampleOutSearchDto) throws Exception;

    /**
     * 查询修改页面借出单明细
     * 
     * @param assessDto
     * @return
     * @throws Exception
     * @author zhangzx
     */
    public List<SampleOutProductInfoDto> getProductInfoModify(SampleOutAssessDto assessDto)
            throws Exception;

     /**
     * 修改时计算库存单价
     * @param productId
     * @return
     */
    @SuppressWarnings("unchecked")
    public List getProductPriceModify(String[] productIds);

    /**
     * 修改样品借出保存
     * 
     * @param modifySampleOutDto
     * @param list
     * @return
     * @throws Exception
     * @author zhangzx
     */
    @SuppressWarnings("unchecked")
    public boolean modifySampleOutSave(SampleOutEntity modifySampleOutDto, List list)
            throws Exception;

    /**
     * 修改样品借出提交(发待办事务)
     * 
     * @param modifyMoveStockDto
     * @param list
     * @param workEntity
     * @param stockEntities
     * @return
     * @author zhangzx
     */
    @SuppressWarnings("unchecked")
    public boolean modifySampleOutSubmitWork(SampleOutEntity modifySampleOutDto,
            List list, WorkEntity workEntity, List<StockEntity> stockEntities)
            throws Exception;
    /**
     * 获得收货地址
     * @param addressId
     * @return
     * @throws Exception
     * @author zhangzx
     */
    @SuppressWarnings("unchecked")
    public CompanyCustomerSupplierAddressDto getAddressInfo(Map map) throws Exception;
    
  /**
	 * 查询样品借出评审
	 * 
	 * @author 蒋名星
	 * @param outId
	 * @return SampleOutAssessDto
	 * @throws Exception
	 */
	public SampleOutAssessDto getSampleOutById(String outId)
			throws Exception;
	
	/**
	 *查询产品信息
	 * 
	 * @author 蒋名星
	 * @param assessDto
	 * @return SampleOutAssessDto
	 * @throws Exception
	 */
	public List<SampleOutProductInfoDto> getProductInfo(SampleOutAssessDto assessDto)
			throws Exception;

	/**
	 * 样品借出管理评审办事务自减
	 * 
	 * @author 蒋名星
	 * @param sampleOutAssessDto
	 * @param work
	 * @param workself
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateSampleOutAssess(SampleOutAssessDto sampleOutAssessDto,
			 WorkEntity workself) throws Exception;

	/**
	 * 样品借出管理评审不发待办事务
	 * 
	 * @author 蒋名星
	 * @param sampleOutAssessDto
	 * @param work
	 * @param workself
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateAssessNoWork(SampleOutAssessDto sampleOutAssessDto,
			WorkEntity workself,List<StockEntity> stockEntities) throws Exception;
	
	/**
	 * 新建样品借出单保存
	 * 
	 * @author 蒋名星
	 * @param adddto
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public boolean addSampleOutSave(SampleOutAssessDto adddto,
			List<SampleOutDetailEntity> list) throws Exception;
	
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
	 * @throws Exception
	 */
	public boolean addSampleOutSubmit(SampleOutAssessDto adddto,
			WorkEntity work, List<SampleOutDetailEntity> sampleOutDetailEntities,
			List<StockEntity> stockEntities) throws Exception;
	
	/**
	 * 查询用户权限
	 * 
	 * @author 蒋名星
	 * @param userId
	 * @return roleId
	 * @throws Exception
	 */
	public String selectUserRoleId(String userId) throws Exception;
	
	/**
	 * 客户列表
	 * 
	 * @author liuqg
	 * @return
	 */
	public List<CustomerEntity> selectCustomer(NewPage newPage,UserEntity user) throws Exception;

	/**
	 * 检索客户列表
	 * 
	 * @author liuqg
	 * @return
	 */
	public List<CustomerEntity> selectCustomerByName(
			CustomerEntity customerEntity, NewPage newPage) throws Exception;

	/**
	 * 删除借出单(同时删除明细)
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean deleteSampleOut(String id)throws Exception;

	/**
	 * 获得公司名称
	 * 
	 * @author liuqg
	 * @return
	 */
	public CompanyEntity selectcompany();
	
    /* 客户收获地址 */
    
    @SuppressWarnings("unchecked")
	public List getCustomerAddressList(Map map, NewPage page);
    
}
