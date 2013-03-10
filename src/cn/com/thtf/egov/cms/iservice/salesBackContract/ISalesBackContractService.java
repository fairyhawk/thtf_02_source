package cn.com.thtf.egov.cms.iservice.salesBackContract;

import java.util.List;

import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.dto.SaleBackContractOfAddDto;
import cn.com.thtf.egov.cms.dto.SalesBackContractDetailDto;
import cn.com.thtf.egov.cms.dto.SalesBackContractDto;
import cn.com.thtf.egov.cms.dto.SalesBackQueryDto;
import cn.com.thtf.egov.cms.entity.UserEntity;

/**
 * 销售退货合同 Service
 * 
 * @author lilewei
 */
public interface ISalesBackContractService {

    /**
     * 根据条件查询 所有的销售退货合同
     * 
     * @param queryCondition
     * @return
     */
    public List<SalesBackContractDto> getAllSalesBackContractByCondition(
            SalesBackQueryDto queryCondition, NewPage page);
    /**
     * 根据销售合同id查找合同信息 hrb
     * 
     * @param String id 销售合同id
     * @return
     */
    public Object getSalesContractOfBack(String id);
    /**
     * 根据销售合同id查找产品信息 hrb
     * 
     * @param String id 销售合同id
     * @return
     */
	public List<Object> getGoodsInfoById(NewPage nPage,String id);
    /**
     * 库房信息 hrb
     *  
     * @return
     */
	public List<Object> getStockRoomList();
    /**
     * 收获地址信息 hrb
     *  
     * @return
     */
	public List<Object> getReceiveInfo(String id);
    /**
     * 添加销售退货合同 hrb
     * @param SaleBackContractOfAddDto saleBackContractOfAddDto,String JsonDate json数据
     * @return int 0 1
     */
	public int addSaleBackContract(SaleBackContractOfAddDto saleBackContractOfAddDto,String JsonDate);

    /**
     * 根据id查询销售退货合同
     * 
     * @param salesBackContractId
     * @return
     */
    public SalesBackContractDto getSalesBackContractById(String salesBackContractId);

    /**
     * 查询销售退货合同的全部明细
     * 
     * @author lilewei
     * @param salesBackContractId
     * @return
     */
    public List<SalesBackContractDetailDto> getAllDetailsOfSalesBackContract(
            String salesBackContractId);
    /**
     * 销售退货合同修改 显示 
     * @author hrb
     * @param String id 销售合同id
     * @return 销售退货合同信息
     */
    public Object getModifySalesContractOfBack(String id);
    /**
     * 根据销售退货合同id查找产品信息 显示 hrb
     * @author hrb
     * @param String id 销售合同id
     * @return List产品信息数组
     */
	public List<Object> getGoodsInfoByIdOfShow(String id);
    /**
     * 修改销售退货合同
     * @author hrb
     * @param SaleBackContractOfAddDto saleBackContractOfAddDto,String JsonDate json数据
     * @return int 0 1
     */
	public int modifySaleBackContract(SaleBackContractOfAddDto saleBackContractOfAddDto,String JsonDate);
    /**
     * 评审销售合同
     * @author hrb
     * @param SaleBackContractOfAddDto saleBackContractOfAddDto
     * @param UserEntity user
     * @return int 0 1
     */
	public int modifySaleBackContractOfComment(SaleBackContractOfAddDto saleBackContractOfAddDto,UserEntity user);	
    /**
     * 删除销售合同
     * @author hrb
     * @param 销售退货合同 id
     * @param UserEntity user
     * @return int 0 1
     */
	/**
	 * 获得销售退货合同所在的区域编号
	 * @param backContractId
	 * @return
	 */
    public int getAreaIdBySalesBackContractId(String backContractId);
	public int deleteSalesBackContract(String id);	
    /**
     * 查看评审表
     * @author hrb
     * @param 销售退货合同 id
     * @param UserEntity user
     * @return int 0 1
     */
	public Object getCommentTable(String id);
    /**
     * 打印评审表 修改状态
     * @author hrb
     * @param 销售退货合同 id UserEntity user
     * @return int 0 1
     */
	public int modifyCommentTableOfStatus(String id,UserEntity user);
    /**
     * 确认销售合同
     * @author hrb
     * @param SaleBackContractOfAddDto saleBackContractOfAddDto
     * @return int 0 1
     */
	public int modifySaleBackContractOfDecide(SaleBackContractOfAddDto saleBackContractOfAddDto);	
    /**
     * 判断合同号是否存在
     * @author hrb
     * @param SaleBackContractOfAddDto saleBackContractOfAddDto
     * @return int 0 1
     */
	public List<Object> getSalesBackContractIsExist(SaleBackContractOfAddDto saleBackContractOfAddDto);	
	
}
