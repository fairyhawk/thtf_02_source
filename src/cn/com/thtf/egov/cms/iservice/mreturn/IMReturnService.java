/**
 * ClassName  IMReturnService
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-5-6
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.iservice.mreturn;

import java.math.BigDecimal;
import java.util.List;

import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.dto.AppointAddDto;
import cn.com.thtf.egov.cms.dto.AppointSellDetailDto;
import cn.com.thtf.egov.cms.dto.AppointSendGoodsDetailDto;
import cn.com.thtf.egov.cms.dto.MReturnInfoDto;
import cn.com.thtf.egov.cms.dto.MReturnListQueryDto;
import cn.com.thtf.egov.cms.dto.MreturnAppointDto;
import cn.com.thtf.egov.cms.dto.MreturnArriveInfoDto;
import cn.com.thtf.egov.cms.dto.MreturnDto;
import cn.com.thtf.egov.cms.entity.CustomerEntity;
import cn.com.thtf.egov.cms.entity.MreturnEntity;
import cn.com.thtf.egov.cms.entity.ProductTypeEntity;
import cn.com.thtf.egov.cms.entity.ReturnDetailEntity;
import cn.com.thtf.egov.cms.entity.UserEntity;

/**
 * 回款信息
 * 
 * @author Lubo
 */

public interface IMReturnService {

    /**
     * 获取所有回款信息
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    public List getMReturnList(NewPage page, MReturnListQueryDto mReturnListQueryDto,
            UserEntity user);

    /**
     * 获取回款信息
     * 
     * @param mreturnId
     * @return
     */
    public MreturnDto getMReturnById(String mreturnId);

    /**
     * 获取指定合同的回款信息
     * 
     * @param sellContractId
     * @return
     */
    public List<MreturnDto> getMReturn(String sellContractId);

    /**
     * 获取指定合同的回款明细 非在途
     * 
     * @param sellContractId
     * @return
     */
    public List<MreturnDto> getMReturnDetail(String sellContractId);

    /**
     * 获取指定合同的回款明细 在途
     * 
     * @param sellContractId
     * @return
     */
    public List<MreturnDto> getMreturnDetailInRransit(String sellContractId);

    /**
     * 指定销售合同小页面
     * 
     * @param para
     * @param page
     * @return
     */
    public List<AppointSellDetailDto> getSellContractDetail(MreturnAppointDto para,
            NewPage page);

    /**
     * 指定产品明细小页面
     * 
     * @param para
     * @param page
     * @return
     */
    public List<AppointSendGoodsDetailDto> getSendGoodsDetail(MreturnAppointDto para,
            NewPage page);

    /**
     * 获取所有产品分类集合
     * 
     * @return
     */
    public List<ProductTypeEntity> getAllProductType();

    /**
     * 获取销售助理负责产品
     * 
     * @return
     */
    public List<ProductTypeEntity> getAllProductTypeByUserId(String userId);

    /**
     * 到帐初始化
     * 
     * @param para
     * @param page
     * @return
     */
    public MReturnInfoDto getArriveInitialization(String mrid);

    /**
     * 回款表时间戳检索
     * 
     * @param para
     * @param page
     * @return
     */
    public String selectMreturnTimesTamp(String id);

    /**
     * 更新回款表
     * 
     * @param para
     * @param page
     * @return
     */
    public Boolean mewarriveMReturn(MreturnEntity mreturn, List<MreturnArriveInfoDto> maid);

    /**
     * 新增回款指定
     * 
     * @param para
     * @return
     */
    public boolean addMreturnAppoint(AppointAddDto para);

    /**
     * 删除回款
     * 
     * @param mrid
     * @param userInfo
     * @return
     */
    public Integer removeMReturn(String mrid, UserEntity userInfo);

    /**
     * 汇款录入 客户小页面
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    public List getMReturnCustomer(NewPage page, CustomerEntity customer);

    /**
     * 指定总额查询
     * 
     * @param para
     * @param page
     * @return
     */
    public BigDecimal sumMoney(ReturnDetailEntity rde);

    /**
     * 客户表时间戳检索
     * 
     * @param para
     * @param page
     * @return
     */
    public String selectCustomeerTimesTamp(String id);

    /**
     * 到帐更新所用信息
     * 
     * @param para
     * @param page
     * @return
     */
    public List<MreturnArriveInfoDto> selectarriveinfodto(String id);

    /**
     * 回款录入（创建）
     * 
     * @param mreturnEntity
     * @return
     */
    public Boolean createMReturn(MreturnEntity mreturnEntity);
}
