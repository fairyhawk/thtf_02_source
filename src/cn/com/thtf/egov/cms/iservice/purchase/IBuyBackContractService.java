/**
 * ClassName  IBuyBackContractService
 *
 * History
 * Create User: ChenHuajiang
 * Create Date: 2010-6-12
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.iservice.purchase;

import java.util.HashMap;
import java.util.List;

import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.dto.BuyBackContractDto;
import cn.com.thtf.egov.cms.dto.BuyBackContractInfoDto;
import cn.com.thtf.egov.cms.dto.BuyBackContractPreviewDto;
import cn.com.thtf.egov.cms.dto.BuyBackContractPreviewProdDto;
import cn.com.thtf.egov.cms.dto.BuyBackContractReviewDto;
import cn.com.thtf.egov.cms.dto.BuyContractProductInfoDto;
import cn.com.thtf.egov.cms.dto.BuyContractReviewDto;
import cn.com.thtf.egov.cms.dto.SupplierAddressDto;
import cn.com.thtf.egov.cms.entity.BuyBackContractDetailEntity;
import cn.com.thtf.egov.cms.entity.BuyBackContractEntity;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.entity.WorkEntity;
import cn.com.thtf.egov.cms.form.BuyContractSearchForm;

/**
 * 采购退货合同
 * 
 * @author ChenHuajiang
 */

public interface IBuyBackContractService {
    /**
     * 检索采购退货合同信息
     * 
     * @return list
     */
    @SuppressWarnings("unchecked")
    public List getBuyBackContracts(BuyContractSearchForm param, NewPage page);

    /**
     * 采购合同信息
     * 
     * @param id
     *            采购合同ID
     * @return list
     */
    public BuyBackContractDto getBuyContractInfoById(String id);

    /**
     * 产品选择
     * 
     * @param map
     *            采购合同ID,UserId,RoleId
     * @return List
     */
    @SuppressWarnings("unchecked")
    public List getProdInfoListById(HashMap map, NewPage page);

    /**
     * @author HanHaiyun 供应商信息
     * @param name
     * @param page
     * @return
     */
    @SuppressWarnings("unchecked")
    public List getSupplierAddress(String name, String buyContractId, NewPage page);

    /**
     * 单个供应商信息
     * 
     * @author HanHaiyun
     * @return
     */
    public SupplierAddressDto getSupplierAddressById(String supplierId);

    /**
     * 添加采购退货合同
     * 
     * @author HanHaiyun
     * @param buyBackContractEntity
     *            采购退货合同信息
     * @param buyBackContractDetailEntitys
     *            采购退货合同明细
     * @param work
     *            需要添加的事务
     */
    public boolean insertBuyBackContract(BuyBackContractEntity buyBackContractEntity,
            List<BuyBackContractDetailEntity> buyBackContractDetailEntitys,
            WorkEntity work);

    /**
     * 采购退货合同所有产品信息
     * 
     * @author HanHaiyun
     * @param buyBackContractId
     *            采购退货合同编号
     * @param buyContractId
     *            采购合同编号
     * @return 产品信息
     */
    public List<BuyContractProductInfoDto> getBuyBackContractProducts(
            String buyBackContractId, String buyContractId);

    /**
     * 采购退货合同信息
     * 
     * @author HanHaiyun
     * @param buyBackContractId
     *            采购退货合同编号
     * @return 采购退货合同信息
     */
    public BuyBackContractInfoDto getBuyBackContract(String buyBackContractId);

    /**
     * 删除采购退货明细
     * 
     * @author HanHaiyun
     * @param contructBackContractId
     *            采购退货id
     * @return 是否删除成功
     */
    public boolean delBackContractDetailByContructBackContractId(
            String contructBackContractId);

    /**
     * 删除采购退货合同
     * 
     * @author HanHaiyun
     * @param id
     *            采购退货合同id
     * @return 是否删除成功
     */
    public boolean delBackContractById(String id);

    /**
     * 修改采购退货合同
     * 
     * @author HanHaiyun
     * @param buyBackContractEntity
     *            采购退货合同信息
     * @param buyBackContractDetailEntitys
     *            采购退货合同明细
     * @param work
     *            需要添加的事务
     */
    public boolean updBuyBackContract(BuyBackContractEntity buyBackContractEntity,
            List<BuyBackContractDetailEntity> buyBackContractDetailEntitys,
            WorkEntity work);

    /**
     * 采购退货合同预览产品信息
     * 
     * @author HanHaiyun
     * @param buyBackContractId
     *            产品退货合同编号
     * @return 所有关联产品信息
     */
    public List<BuyBackContractPreviewProdDto> getBackContractProviewProds(
            String buyBackContractId);

    /**
     * 采购退货合同评审查看
     * 
     * @author HanHaiyun
     * @param buyBackContractId
     *            产品退货合同编号
     * @return 所有关联产品信息
     */
    public List<BuyBackContractPreviewProdDto> getBackContractReviewProds(
            String buyBackContractId);

    /**
     * 采购退货合同预览基本信息
     * 
     * @author HanHaiyun
     * @param buyBackContractId
     *            产品退货合同编号
     * @return 基础信息
     */
    public BuyBackContractPreviewDto getBackContractProview(String buyBackContractId);

    /**
     * 采购退货合同评审
     * 
     * @author HanHaiyun
     * @param 评审内容
     * @return 是否成功
     */
    public boolean buyBackContractReview(BuyContractReviewDto buyContractReviewDto,
            UserEntity userEntity, Integer status,UserEntity buySpecialist);
    /**
     * 得到采购专员
     * @param contractId 采购合同编号
     * @return
     */
    public UserEntity getBuySpecialist(String contractId);

    /**
     * 查看评审表
     * 
     * @param buyContractId
     *            采购退货合同编号
     * @return 评审信息
     */
    public BuyBackContractReviewDto getBackContractReview(String buyBackContractId);

    /**
     * 更新采购退货合同状态
     * 
     * @param status
     *            状态
     * @param buyContractId
     *            采购退货合同编号
     * @return 是否更新成功
     */
    public boolean upBuyBackContractStatus(Integer status, String buyBackContractId,
            UserEntity userEntity);

    /**
     * 采购退货合同确认
     * 
     * @param status
     *            状态
     * @param companyContractCode
     *            公司合同号
     * @return 是否确认成功
     */
    public boolean buyBackContractConfirm(Integer status, String companyContractCode,
            String buyBackContractId);

    /**
     * 判断公司合同号是否存在
     * 
     * @param companyContractCode
     *            公司合同号
     * @return
     */
    public boolean isExistsCompanyContractCode(String companyContractCode);
}
