/**
 * ClassName  ISalesContractManagementService
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-4-12
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.iservice.sell;

import java.util.List;
import java.util.Map;

import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.dto.AssessManDto;
import cn.com.thtf.egov.cms.dto.CompanyInfoDto;
import cn.com.thtf.egov.cms.dto.SalesConProductDto;
import cn.com.thtf.egov.cms.dto.SalesContractInfoDto;
import cn.com.thtf.egov.cms.dto.SalesContractProductDetailDto;
import cn.com.thtf.egov.cms.dto.SalesContractQueryDto;
import cn.com.thtf.egov.cms.dto.SalesContractResultDto;
import cn.com.thtf.egov.cms.entity.CustomerLinkmanEntity;
import cn.com.thtf.egov.cms.entity.DemandEntity;
import cn.com.thtf.egov.cms.entity.SellContractDetailEntity;
import cn.com.thtf.egov.cms.entity.WorkEntity;
import cn.com.thtf.egov.cms.form.ProductSearchForm;

/**
 * 销售合同管理
 * 
 * @author Lubo
 */

public interface ISalesContractManagementService {

    /**
     * 检索公司合同号
     * 
     * @param code
     * @return
     */
    public String getCompanycontractcode(String code);

    /**
     * 销售合同检索详细
     * 
     * @param param
     */
    public void getSalesContractListDetail(SalesContractResultDto param);

    /**
     * 销售合同检索
     * 
     * @param salesDto
     * @param page
     * @return
     */
    public List<SalesContractResultDto> getSalesContractList(
            SalesContractQueryDto salesDto, NewPage page);

    /**
     * 根据销售合同流水号查询对应合同的信息 销售合同查看
     * 
     * @param salesContractId
     * @return
     */
    public SalesContractInfoDto getSalesContractInfoById(String salesContractId);

    /**
     * 新建销售合同 根据用户查看负责的客户信息
     * 
     * @param userId
     * @return
     * @author shensi
     */
    @SuppressWarnings("unchecked")
    public List getCustomerByUserId(String userId);

    /**
     * 新建销售合同 根据客户Id查看联系人
     * 
     * @param customerId
     * @return
     * @author shensi
     */
    @SuppressWarnings("unchecked")
    public List getLinkManByCustomerId(String customerId);

    /**
     * 新建销售合同 根据联系人Id查看联系人相关信息
     * 
     * @param linkManId
     * @return
     * @author shensi
     */
    public CustomerLinkmanEntity getLinkMsgByLinkManId(String linkManId);

    /**
     * 新建销售合同 根据用户Id查看所属区域
     * 
     * @param userId
     * @return
     * @author shensi
     */
    public String getAreaNameByUserId(String userId);

    /**
     * 产品选择小页面
     * 
     * @author shensi
     */
    @SuppressWarnings("unchecked")
    public List getProduct(SalesConProductDto salesConProductDto, NewPage page);

    /**
     * 新建销售合同 根据用户Id查看负责的产品分类
     * 
     * @param userId
     * @return
     * @author shensi
     */
    @SuppressWarnings("unchecked")
    public List getProTypeByUserId(String userId);

    /**
     * 新建销售合同 根据产品分类查看系列
     * 
     * @param proTypeId
     * @return
     * @author shensi
     */
    @SuppressWarnings("unchecked")
    public List getProSerieByProTypeId(String proTypeId);

    /**
     * 新建销售合同 付款方式选择
     * 
     * @return
     * @author shensi
     */
    @SuppressWarnings("unchecked")
    public List getPaymentWay(Map map, NewPage page);

    /**
     * 新建销售合同 发货地址选择
     * 
     * @return
     * @author shensi
     */
    @SuppressWarnings("unchecked")
    public List getSendGoodsAddress(NewPage page, String customerId);

    /**
     * 新建销售合同 查看用户的相关信息
     * 
     * @param userId
     * @return
     * @author shensi
     */
    @SuppressWarnings("unchecked")
    public List getUserMagByUserId(String userId);

    /**
     * 新建销售合同
     * 
     * @return
     * @author shensi
     */
    @SuppressWarnings("unchecked")
    public boolean addSalesContarct(SalesContractInfoDto salesContractInfoDto,

    List salesConDetialList, List demandList, WorkEntity work, String sign);

    /**
     * 销售合同明细
     * 
     * @param sellContractDetail
     * @return
     * @author shensi
     */
    public boolean addSalesContarctDetial(SellContractDetailEntity sellContractDetail);

    /**
     * 插入需求单表
     * 
     * @param demandEntity
     * @return
     * @author shensi
     */
    public boolean addDemand(DemandEntity demandEntity);

    /**
     * 判断 查询评审人是否存在
     * 
     * @param proTypeId
     *            产品分类ID
     * @param flg
     *            评审标志： flg=true 新建、修改；flg=false 评审
     * @param roleId
     *            下一评审人角色ID
     * @param regionalId
     *            区域id
     * @return
     * 
     * @author shensi
     */
    @SuppressWarnings("unchecked")
    public Map getJudgeRole(boolean flg, String proTypeId, Integer roleId,
            Integer regionalId);

    /**
     * 根据id 查询销售合同
     * 
     * @author 李乐伟
     * @param contractId
     * @return
     */
    public SalesContractInfoDto getSalesContractById(String contractId);

    /**
     * 更新销售合同
     * 
     * @param contractData
     * @return
     * @author 李乐伟
     */
    @SuppressWarnings("unchecked")
    public boolean modifySalesContract(List contractData);

    /**
     * 根据销售合同id查询对应的合同明细
     * 
     * @author 李乐伟
     * @param contractId
     * @return
     */
    @SuppressWarnings("unchecked")
    public List getSalesContractDetailListByContractId(String contractId);

    /**
     * 根据销售合同id查询产品明细
     * 
     * @author 张子旭
     * @param contractId
     * @return
     */
    @SuppressWarnings("unchecked")
    public List selectSalesContractProductDetailList(String contractId);

    /**
     * 销售合同评审更新
     * 
     * @param contractData
     * @return
     * @author 李乐伟
     */
    @SuppressWarnings("unchecked")
    public boolean modifySalesContractForAssess(List updateData);

    /**
     * 根据事务id,用户id查询下一个评审人的对应事务是否存在
     * 
     * @param contractData
     * @return
     * @author 李乐伟
     */
    public boolean isNextAssessManWorkExist(AssessManDto nextAssessMan);

    /**
     * 查询与销售合同对应的销售助理id
     * 
     * @author 李乐伟
     * @param contractId
     * @return
     */
    public String getSalesAssistantIdByContractId(String contractId);

    /**
     * 销售合同修改删除明细产品
     * 
     * @param salesContractInfoDto
     * @return
     */
    public boolean removeSalesConDetail(SellContractDetailEntity sellContractDetailEntity);

    /**
     * 销售合同修改时删除需求单的的产品
     * 
     * @param demandEntity
     * @return shensi
     */
    public boolean removeDemand(DemandEntity demandEntity);

    /**
     * 删除销售合同的全部合同明细
     * 
     * @author 李乐伟
     * @param contractId
     *            合同id
     */
    public boolean removeAllContractDetailByContractId(String contractId);

    /**
     * 增加销售合同的全部合同明细
     * 
     * @param contractDetailList
     * @return
     */
    public boolean addSalesContractDetil(
            List<SalesContractProductDetailDto> contractDetailList);

    /**
     * 删除销售合同的全部需求单
     * 
     * @param contractId
     *            合同id
     * @return
     * @author 李乐伟
     */
    public boolean removeAllDemandOfContract(String contractId);

    /**
     * 销售合同删除
     * 
     * @param contractId
     * @return
     * @author 张子旭
     */
    public boolean removeSalesContract(String contractId);

    /**
     * 查找公司信息
     * 
     * @return
     * @author 张子旭
     */
    public CompanyInfoDto getCompanyInfo();

    /**
     * 更新合同状态信息（待打印->待确认）
     * 
     * @return
     * @author 张子旭
     */
    public boolean modifyContractStatus(String contractId, WorkEntity work);

    /**
     * 更新合同状态信息（待确认->合同生效）
     * 
     * @return
     * @author 张子旭
     */
    @SuppressWarnings("unchecked")
    public boolean modifyContractStatusEffect(Map map);

    /**
     * 合同取消
     * 
     * @return
     * @author 张子旭
     */
    public boolean modifySalesContractCancel(String contractId);

    /**
     * 新增下一个评审人的待办事务
     * 
     * @author 李乐伟
     * @param allDetail
     * @return
     */
    public boolean insertNextAssessManWork(AssessManDto nextAssessMan);

    /**
     * 更新下一个评审人的待办事务
     * 
     * @author 李乐伟
     * @param allDetail
     * @return
     */
    public boolean updateNextAssessManWork(AssessManDto nextAssessMan);

    /**
     * 产品分类及部门
     * 
     * @param userId
     *            当前用户ID
     * @return List
     */
    @SuppressWarnings("unchecked")
    public List getProdTypeDeptList(String userId);

    /**
     * 根据条件检索产品信息
     * 
     * @param param
     *            检索条件
     * @return List
     */
    @SuppressWarnings("unchecked")
    public List getProdInfoByCondition(ProductSearchForm param, NewPage page);

    /**
     * 根据产品分类ID检索产品系列信息
     * 
     * @param prodTypeId
     *            产品分类ID
     * @return List
     */
    @SuppressWarnings("unchecked")
    public List getProdSeriesByProdTypeId(String prodTypeId);
}
