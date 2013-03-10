/**
 * ClassName  IBuyContractService
 *
 * History
 * Create User: ChenHuajiang
 * Create Date: 2010-6-1
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.iservice.purchase;

import java.util.HashMap;
import java.util.List;

import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.dto.BuyContractOfAddDto;
import cn.com.thtf.egov.cms.dto.BuyContractPreviewDto;
import cn.com.thtf.egov.cms.dto.BuyContractProductInfoDto;
import cn.com.thtf.egov.cms.dto.BuyContractReceivingInfoDto;
import cn.com.thtf.egov.cms.dto.BuyContractReviewDto;
import cn.com.thtf.egov.cms.dto.BuyContractSelectDto;
import cn.com.thtf.egov.cms.entity.SupplierLinkmanEntity;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.form.BuyContractSearchForm;
import cn.com.thtf.egov.cms.form.ProductSearchForm;

/**
 * 采购合同
 * 
 * @author ChenHuajiang 2010-6-1
 */

public interface IBuyContractService {

    /**
     * 检索采购合同信息
     * 
     * @return list
     */
    @SuppressWarnings("unchecked")
    public List getBuyContracts(BuyContractSearchForm param, NewPage page);

    /**
     * 供应商信息
     * 
     * @param name
     *            供应商名称
     * @return list
     */
    @SuppressWarnings("unchecked")
    public List getSuppliers(String name, NewPage page);

    /**
     * 供应商-联系人
     * 
     * @param supplierId
     *            供应商ID
     * @return list
     */
    @SuppressWarnings("unchecked")
    public List getSupplierLinkmanBySupplierId(String supplierId);

    /**
     * 联系人信息
     * 
     * @param id
     *            联系人ID
     * @return SupplierLinkmanEntity
     */
    public SupplierLinkmanEntity getSupplierLinkmanById(String id);

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

    /**
     * 库房收货地址
     * 
     * @return List
     */
    @SuppressWarnings("unchecked")
    public List getStockroomAddressList(NewPage page);

    /**
     * 客户收获地址
     * 
     * @param customerName
     *            客户名称
     * @return List
     */
    @SuppressWarnings("unchecked")
    public List getCustomerAddressList(String customerName, NewPage page);

    /**
     * 公司收获地址
     * 
     * @return List
     */
    @SuppressWarnings("unchecked")
    public List getCompanyAddressList();

    /**
     * 收货地址添加
     * 
     * @param buyContractId
     *            采购合同ID
     * @return List
     */
    @SuppressWarnings("unchecked")
    public List getReceiveAddress(String buyContractId);

    /**
     * 查看采购合同信息
     * 
     * @author HanHaiyun
     * @param Id
     *            采购合同id
     * @return 采购合同信息
     */
    public BuyContractSelectDto getBuyContractById(String Id);

    /**
     * 查看采购合同收货信息
     * 
     * @author HanHaiyun
     * @param Id
     *            采购合同id
     * @return 采购合同收货信息
     */
    public List<BuyContractReceivingInfoDto> getBuyContractReceivingInfoById(String Id);

    /**
     * 查看采购合同产品信息
     * 
     * @author HanHaiyun
     * @param Id
     *            采购合同id
     * @return 采购合同产品信息
     */
    public List<BuyContractProductInfoDto> getBuyContractProductInfoById(String Id);

    /**
     * 采购合同预览
     * 
     * @author HanHaiyun
     * @param Id
     *            采购合同id
     * @return 预览信息
     */
    public BuyContractPreviewDto getBuyContractPreviewByContractId(String id);

    /**
     * 采购合同评审
     * 
     * @author HanHaiyun
     * @param 评审dto
     * @return 是否成功
     */
    public boolean updateReview(BuyContractReviewDto buyContractReviewDto,
            UserEntity userEntity, Integer status,UserEntity buySpecialist);
    /**
     * 得到采购专员
     * @param contractId 采购合同编号
     * @return
     */
    public UserEntity getBuySpecialist(String contractId);
    public UserEntity getUser(String id);

    /**
     * 添加采购合同
     * 
     * @param buyContractOfAddDto
     *            采购合同dto JsonDate 明细数据
     * @return int 0 1
     */
    public int addBuyContractOfTransact(BuyContractOfAddDto buyContractOfAddDto,
            String JsonDate);

    /**
     * 添加地址
     * 
     * @param jsonData
     *            数据
     * @return int 0 1
     */
    public int addReceiveOfBuyContract(String jsonData);

    /**
     * 删除收获地址
     * 
     * @param jsonData
     *            数据
     * @return int 0 1
     */
    public int deleteReceiveOfBuyContract(String jsonData);

    /**
     * 修改采购合同显示
     * 
     * @param id
     *            采购合同id
     * @return object
     */
    public Object modifyBuyContractOfShow(String id);

    /**
     * 修改采购合同 产品信息列表
     * 
     * @param id
     *            采购合同id
     * @return List
     */
    @SuppressWarnings("unchecked")
    public List getBuyContractProductInfoOfModify(String id);

    /**
     * 采购合同 收货信息修列表
     * 
     * @param id
     *            采购合同id
     * @return String jsonDate
     */
    public String getReceiceInfoModify(String id);

    /**
     * 修改采购合同
     * 
     * @param buyContractOfAddDto
     *            采购合同dto JsonDate 明细数据
     * @return int 0,1
     */
    public int modifyBuyContractOfTransact(BuyContractOfAddDto buyContractOfAddDto,
            String JsonDate);

    /**
     * 获取修改查看收货信息列表
     * 
     * @param id
     *            采购合同id
     * @return 列表
     */
    @SuppressWarnings("unchecked")
    public List getReceiceInfoOfShow(String id);

    /**
     * 判断公司合同号是否存
     * 
     * @param BuyContractOfAddDto
     * @return 个数
     */
    public String getBuyContractIsExist(BuyContractOfAddDto buyContractOfAddDto);

    /**
     * 修改采购合同 确认
     * 
     * @param BuyContractOfAddDto
     * @return int 0,1
     */
    public int updateBuyContractOfDecide(BuyContractOfAddDto buyContractOfAddDto);

    /**
     * 打印合同表
     * 
     * @param id
     *            采购合同id user
     * @return int 0,1
     */
    public int modifyCommentTableOfStatus(String id, UserEntity user);

    /**
     * 删除采购合同
     * 
     * @param id
     *            采购合同id
     * @return int 0,1
     */
    public int deleteBuyContract(String id);
    /**
     * 收货信息总数
     * 
     * @param productInfoList 采购合同产品列表
     * @return int 
     */
    public String getReceiceInfoCount(List<BuyContractProductInfoDto> productInfoList,String buyContractId);
    /**
     * 获取状态
     * 
     * @param Map 表名 id
     * @return int 状态
     */
    public int getStrutsOfAll(HashMap<String, String> map);
}
