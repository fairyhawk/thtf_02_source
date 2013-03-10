/**
 * ClassName  MReturnServiceImp
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-5-6
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.serviceimpl.mreturn;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.BaseService;
import cn.com.thtf.egov.cms.application.NewIDao;
import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.dto.AppointAddDto;
import cn.com.thtf.egov.cms.dto.AppointSellDetailDto;
import cn.com.thtf.egov.cms.dto.AppointSendGoodsDetailDto;
import cn.com.thtf.egov.cms.dto.CustomerCreditDto;
import cn.com.thtf.egov.cms.dto.MReturnInfoDto;
import cn.com.thtf.egov.cms.dto.MReturnListDto;
import cn.com.thtf.egov.cms.dto.MReturnListQueryDto;
import cn.com.thtf.egov.cms.dto.MreturnAppointDto;
import cn.com.thtf.egov.cms.dto.MreturnArriveInfoDto;
import cn.com.thtf.egov.cms.dto.MreturnDto;
import cn.com.thtf.egov.cms.dto.ReturnDetailDto;
import cn.com.thtf.egov.cms.entity.BusiLogEntity;
import cn.com.thtf.egov.cms.entity.CustomerCreditDetailEntity;
import cn.com.thtf.egov.cms.entity.CustomerCreditEntity;
import cn.com.thtf.egov.cms.entity.CustomerEntity;
import cn.com.thtf.egov.cms.entity.MreturnEntity;
import cn.com.thtf.egov.cms.entity.ProductTypeEntity;
import cn.com.thtf.egov.cms.entity.ReturnDetailEntity;
import cn.com.thtf.egov.cms.entity.SendGoodsEntity;
import cn.com.thtf.egov.cms.entity.UserAreaProductEntity;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.iservice.common.ICommonService;
import cn.com.thtf.egov.cms.iservice.mail.IMailService;
import cn.com.thtf.egov.cms.iservice.mreturn.IMReturnService;
import cn.com.thtf.egov.cms.util.Container;
import cn.com.thtf.egov.cms.util.Util;

/**
 * MReturnServiceImp
 * 
 * @author Lubo
 */
public class MReturnServiceImp extends BaseService implements IMReturnService {

    /** log */
    private static Logger log = LoggerFactory.getLogger(MReturnServiceImp.class);
    /** ICommonService */
    private ICommonService commonService;
    /** NewIDao */
    private NewIDao dao;

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.mreturn.IMReturnService#getMReturnList()
     */
    @SuppressWarnings("unchecked")
    @Override
    public List getMReturnList(NewPage page, MReturnListQueryDto mReturnListQueryDto,
            UserEntity user) {

        List list = new ArrayList<MReturnListDto>();
        try {
            list = queryRecords(dao, "selectMreturnList.search", mReturnListQueryDto,
                    page);
        } catch (Exception e) {
            log.error("检索回款列表错误", e);
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.mreturn.IMReturnService#getMReturnById(
     * java.lang.String)
     */
    @Override
    public MreturnDto getMReturnById(String mreturnId) {
        try {
            return (MreturnDto) dao.queryForObject("mreturn_sqlMap.selectMreturnById",
                    mreturnId);
        } catch (Exception e) {
            log.error("检索回款信息错误", e);
            return null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.mreturn.IMReturnService#getMReturn(java
     * .lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<MreturnDto> getMReturn(String sellContractId) {
        List<MreturnDto> result = null;
        try {
            result = dao.queryForlist("mreturn_sqlMap.selectMreturn", sellContractId);
        } catch (Exception e) {
            log.error("获取指定合同的回款信息错误:", e);
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.mreturn.IMReturnService#getMReturnDetail
     * (java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<MreturnDto> getMReturnDetail(String sellContractId) {
        List<MreturnDto> result = null;
        try {
            result = dao.queryForlist("mreturn_sqlMap.selectMreturnDetail",
                    sellContractId);
        } catch (Exception e) {
            log.error("获取指定合同的回款明细 非在途:", e);
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @seecn.com.thtf.egov.cms.iservice.mreturn.IMReturnService#
     * getMreturnDetailInRransit(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<MreturnDto> getMreturnDetailInRransit(String sellContractId) {
        List<MreturnDto> result = null;
        try {
            result = dao.queryForlist("mreturn_sqlMap.selectMreturnDetailInRransit",
                    sellContractId);
        } catch (Exception e) {
            log.error("获取指定合同的回款明细 在途:", e);
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.mreturn.IMReturnService#getSellContractDetail
     * ()
     */
    @SuppressWarnings("unchecked")
    public List<AppointSellDetailDto> getSellContractDetail(MreturnAppointDto para,
            NewPage page) {
        List result = null;
        try {

            MreturnDto mreturnInfo = (MreturnDto) dao.queryForObject(
                    "mreturn_sqlMap.selectMreturnById", para.getMreturnId());

            if (page != null) {
                UserEntity userEntity = new UserEntity();
                userEntity.setId(mreturnInfo.getUserId());
                /* 销售助理 */
                List<UserAreaProductEntity> userAreaProductList = (List<UserAreaProductEntity>) dao
                        .getSqlMap().queryForList(
                                "salesContract_sqlMap.getUserAreaProduct", userEntity);

                /* 封装查询参数 */
                // setUserAreaProduct(para, userAreaProductList);
                para.setUserAreaProductList(userAreaProductList);
            }
            para.setProductTypeId(mreturnInfo.getProductTypeId());
            para.setCustomerId(mreturnInfo.getCustomerId());

            if (page != null) {
                result = queryRecords(dao, "selectSellContractDetail.date", para, page);
            } else {
                para.setAppointMoney("true");
                result = dao.queryForlist("selectSellContractDetail.date", para);
            }
        } catch (Exception e) {
            log.error("获取指定销售合同小页面:", e);
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.mreturn.IMReturnService#getSendGoodsDetail
     * (cn.com.thtf.egov.cms.dto.MreturnAppointDto,
     * cn.com.thtf.egov.cms.application.NewPage)
     */
    @SuppressWarnings("unchecked")
    public List<AppointSendGoodsDetailDto> getSendGoodsDetail(MreturnAppointDto para,
            NewPage page) {
        List result = null;
        try {

            MreturnDto mreturnInfo = (MreturnDto) dao.queryForObject(
                    "mreturn_sqlMap.selectMreturnById", para.getMreturnId());

            if (page != null) {
                UserEntity userEntity = new UserEntity();
                userEntity.setId(mreturnInfo.getUserId());
                /* 销售助理 */
                List<UserAreaProductEntity> userAreaProductList = (List<UserAreaProductEntity>) dao
                        .getSqlMap().queryForList(
                                "salesContract_sqlMap.getUserAreaProduct", userEntity);

                /* 封装查询参数 */
                // setUserAreaProduct(para, userAreaProductList);
                para.setUserAreaProductList(userAreaProductList);
            }
            para.setProductTypeId(mreturnInfo.getProductTypeId());
            para.setCustomerId(mreturnInfo.getCustomerId());

            if (page != null) {
                result = queryRecords(dao, "selectSendGoodsDetail.date", para, page);
            } else {
                para.setAppointMoney("true");
                para.setIsFreeze("true");
                result = dao.queryForlist("selectSendGoodsDetail.date", para);
            }

        } catch (Exception e) {
            log.error("指定产品明细小页面:", e);
        }
        return result;
    }

    // /**
    // * 封装 查询参数 区域产品
    // *
    // * @param para
    // * @param userAreaProductList
    // */
    // private void setUserAreaProduct(MreturnAppointDto para,
    // List<UserAreaProductEntity> userAreaProductList) {
    // StringBuffer area = new StringBuffer();
    // StringBuffer product = new StringBuffer();
    //
    // for (UserAreaProductEntity userAreaProduct : userAreaProductList) {
    // area.append(userAreaProduct.getUserAreaId()).append(",");
    // product.append(userAreaProduct.getProductTypeId()).append(",");
    // }
    //
    // if (area.length() > 0) {
    // para.setUserArea(area.substring(0, area.length() - 1));
    // }
    // if (product.length() > 0) {
    // para.setUserProductType(product.substring(0, product.length() - 1));
    // }
    // }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.mreturn.IMReturnService#addMreturnAppoint
     * (cn.com.thtf.egov.cms.dto.AppointAddDto)
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean addMreturnAppoint(AppointAddDto para) {
        try {
            dao.beginTransaction();

            commonService = (ICommonService) Container.getBean("commonServiceImpl");

            /* 获取时间戳 */
            MreturnDto mreturnDto = getMReturnById(para.getMreturnId());

            /* 获取此回款的所有指定到明细的Map */
            Map<Integer, ReturnDetailDto> sendGoodsMap = new HashMap<Integer, ReturnDetailDto>();

            ReturnDetailEntity returnDetailPara = new ReturnDetailEntity();
            returnDetailPara.setReturnId(para.getMreturnId());
            returnDetailPara.setAppointType(4);
            List<ReturnDetailDto> sendGoodsList = dao.queryForlist(
                    "mreturn_sqlMap.selectMreturnAndCustomerCredit", returnDetailPara);
            for (ReturnDetailDto returnDetailDto : sendGoodsList) {
                /* 信用信息 */
                if (returnDetailDto.getCreditTypeId() != 5) {
                    String id = commonService.getCustomerCreditId(
                            returnDetailDto.getCustomerId(),
                            returnDetailDto.getProductTypeId());
                    if (id == null) {
                        log.error("获取信用信息错误(非项目信用)");
                        return false;
                    } else {
                        returnDetailDto.setCustomerCreditId(Integer.parseInt(id));
                    }
                }

                sendGoodsMap.put(returnDetailDto.getId(), returnDetailDto);
            }

            /* 获取此回款的所有指定到合同的Map */
            Map<Integer, ReturnDetailDto> sellMap = new HashMap<Integer, ReturnDetailDto>();

            returnDetailPara.setAppointType(2);
            List<ReturnDetailDto> sellList = dao.queryForlist(
                    "mreturn_sqlMap.selectMreturnDetailByAppointType", returnDetailPara);
            for (ReturnDetailDto returnDetailDto : sellList) {
                sellMap.put(returnDetailDto.getId(), returnDetailDto);
            }

            Map<Integer, String> newSendGoodsMap = new HashMap<Integer, String>();
            /* begin 指定到明细 */
            if (para.getSendGoodsIdArr() != null) {
                for (int i = 0; i < para.getSendGoodsIdArr().length; i++) {
                    String thisMrDetileId = para.getSendGoodsMrDetileIdArr()[i];
                    BigDecimal newMoney = new BigDecimal(para.getSendGoodsPriceArr()[i]);

                    /* 新增 */
                    if (StringUtils.equals(thisMrDetileId, "-1")) {
                        /* 新增回款明细 */
                        MreturnDto insertPara = new MreturnDto();
                        insertPara.setId(para.getMreturnId());
                        insertPara.setAppointType("4");
                        insertPara.setSendGoodsId(para.getSendGoodsIdArr()[i]);
                        insertPara.setProductId(para.getProductIdArr()[i]);
                        insertPara.setMoney(newMoney);
                        dao.insert("mreturn_sqlMap.insertReturnDetail", insertPara);

                        /* 判断是否是在途款 */
                        if (!StringUtils.equals(mreturnDto.getReturnType(), "1")) {
                            /* 客户信用时间戳 */
                            CustomerCreditDetailEntity creditDetail = new CustomerCreditDetailEntity();
                            creditDetail.setSendGoodsId(para.getSendGoodsIdArr()[i]);
                            creditDetail.setProductId(Integer.parseInt(para
                                    .getProductIdArr()[i]));

                            CustomerCreditDto creditResult = (CustomerCreditDto) dao
                                    .queryForObject(
                                            "credit_sqlMap.getCreditDetailByCondition",
                                            creditDetail);

                            /* 获取使用的信用 =发货金额-已指定金额 */
                            // BigDecimal sendGoodsMoney = new BigDecimal(para
                            // .getSendGoodsMoneyArr()[i]);

                            // BigDecimal appointMoney = new BigDecimal(para
                            // .getAppointMoneyArr()[i]);

                            // BigDecimal useCredit =
                            // sendGoodsMoney.subtract(appointMoney);

                            /* 判断指定的回款 是否等于使用的信用 */
                            // if (newMoney.compareTo(useCredit) == 0) {

                            /* 信用是否为0 */
                            if (creditResult.getMoney().compareTo(newMoney) > 0) {

                                /* 更新客户信用明细 */
                                creditDetail.setId(creditResult.getDetileId());
                                creditDetail.setMoney(newMoney.negate());

                                int updateResult = dao.update(
                                        "credit_sqlMap.updateCreditDetileMoney",
                                        creditDetail);
                                if (updateResult != 1) {
                                    log.error("更新使用额度错误");
                                    return false;
                                }
                            } else {
                                /* 删除客户信用明细 */
                                dao.delete("credit_sqlMap.deleteCreditDetileById",
                                        creditResult.getDetileId());
                            }

                            /* 更新客户信用表时间戳 */
                            CustomerCreditEntity creditUpdatePara = new CustomerCreditEntity();
                            creditUpdatePara.setId(creditResult.getId());
                            creditUpdatePara.setTimeStamp(creditResult.getTimeStamp());
                            int result = dao.update(
                                    "credit_sqlMap.updateCustomerCreditTimeStamp",
                                    creditUpdatePara);

                            if (result != 1) {
                                log.error("信用已变更!");
                                return false;
                            }
                        }

                        // 设置发货单 结算状态为 未结清 未结清:0
                        SendGoodsEntity sendGoodsEntity = new SendGoodsEntity();
                        sendGoodsEntity.setId(para.getSendGoodsIdArr()[i]);
                        sendGoodsEntity.setCloseFlag("0");
                        dao.update("sendGoods_sqlMap.updateSendGoodsCloseFlag",
                                sendGoodsEntity);

                    } else {
                        /* 修改 */
                        /* 当指定金额变更时 */
                        BigDecimal oldDetile = sendGoodsMap.get(
                                Integer.parseInt(thisMrDetileId)).getMoney();
                        if (oldDetile.compareTo(newMoney) != 0) {
                            /* 当指定金额为0时 */
                            if (newMoney.compareTo(new BigDecimal(0)) == 0) {
                                // 删除回款明细
                                MreturnDto deletePara = new MreturnDto();
                                deletePara.setDetailId(thisMrDetileId);
                                dao.delete("mreturn_sqlMap.deleteReturnDetail",
                                        deletePara);
                            } else {
                                // 更新回款明细
                                MreturnDto updatePara = new MreturnDto();
                                updatePara.setDetailId(thisMrDetileId);
                                updatePara.setMoney(newMoney);

                                dao.update("mreturn_sqlMap.updateReturnDetailMoney",
                                        updatePara);
                            }

                            /* 判断是否是在途款 */
                            if (!StringUtils.equals(mreturnDto.getReturnType(), "1")) {
                                /* 客户信用时间戳 */
                                CustomerCreditDetailEntity creditDetail = new CustomerCreditDetailEntity();
                                creditDetail.setSendGoodsId(para.getSendGoodsIdArr()[i]);
                                creditDetail.setProductId(Integer.parseInt(para
                                        .getProductIdArr()[i]));
                                creditDetail.setCustomerCreditId(sendGoodsMap.get(
                                        Integer.parseInt(thisMrDetileId))
                                        .getCustomerCreditId());

                                CustomerCreditDto creditResult = (CustomerCreditDto) dao
                                        .queryForObject(
                                                "credit_sqlMap.getCreditAndDetailByCondition",
                                                creditDetail);

                                /* 新信用额度 = 原信用额度+原回款明细金额-新回款明细金额 */
                                BigDecimal newCredit = oldDetile.subtract(newMoney);
                                creditDetail.setMoney(newCredit);

                                /* 判断是否有信用记录 */
                                if (creditResult.getDetileId() == null) {
                                    dao.insert(
                                            "credit_sqlMap.insertCustomerCreditDetail",
                                            creditDetail);
                                } else {
                                    newCredit = newCredit.add(creditResult.getMoney());

                                    /* 更新客户信用明细 */
                                    creditDetail.setId(creditResult.getDetileId());
                                    int updateResult = dao.update(
                                            "credit_sqlMap.updateCreditDetileMoney",
                                            creditDetail);
                                    if (updateResult != 1) {
                                        log.error("更新使用额度错误");
                                        return false;
                                    }

                                    // 判断是否全部指定
                                    if (newCredit.compareTo(new BigDecimal(0)) == 0) {
                                        /* 删除客户信用明细 */
                                        dao.delete(
                                                "credit_sqlMap.deleteCreditDetileById",
                                                creditResult.getDetileId());
                                    }

                                }

                                /* 更新客户信用表时间戳 */
                                CustomerCreditEntity creditUpdatePara = new CustomerCreditEntity();
                                creditUpdatePara.setId(creditResult.getId());
                                creditUpdatePara
                                        .setTimeStamp(creditResult.getTimeStamp());
                                int result = dao.update(
                                        "credit_sqlMap.updateCustomerCreditTimeStamp",
                                        creditUpdatePara);

                                if (result != 1) {
                                    log.error("信用已变更!");
                                    return false;
                                }
                            }

                            // 设置发货单 结算状态为 未结清 未结清:0
                            SendGoodsEntity sendGoodsEntity = new SendGoodsEntity();
                            sendGoodsEntity.setId(para.getSendGoodsIdArr()[i]);
                            sendGoodsEntity.setCloseFlag("0");
                            dao.update("sendGoods_sqlMap.updateSendGoodsCloseFlag",
                                    sendGoodsEntity);

                        }
                    }

                    newSendGoodsMap
                            .put(Integer.parseInt(para.getSendGoodsMrDetileIdArr()[i]),
                                    "true");

                }

            }

            /* 删除 */
            for (ReturnDetailDto returnDetailDto : sendGoodsList) {
                if (newSendGoodsMap.get(returnDetailDto.getId()) == null) {
                    // 删除回款明细
                    MreturnDto deletePara = new MreturnDto();
                    deletePara.setDetailId(returnDetailDto.getId().toString());
                    dao.delete("mreturn_sqlMap.deleteReturnDetail", deletePara);

                    /* 判断是否是在途款 */
                    if (!StringUtils.equals(mreturnDto.getReturnType(), "1")) {
                        /* 客户信用时间戳 */
                        CustomerCreditDetailEntity creditDetail = new CustomerCreditDetailEntity();
                        creditDetail.setSendGoodsId(returnDetailDto.getSendGoodsId());
                        creditDetail.setProductId(returnDetailDto.getProductId());
                        creditDetail.setCustomerCreditId(returnDetailDto
                                .getCustomerCreditId());

                        CustomerCreditDto creditResult = (CustomerCreditDto) dao
                                .queryForObject(
                                        "credit_sqlMap.getCreditAndDetailByCondition",
                                        creditDetail);

                        /* 判断是否有信用记录 */
                        if (creditResult.getDetileId() == null) {
                            creditDetail.setMoney(returnDetailDto.getMoney());

                            dao.insert("credit_sqlMap.insertCustomerCreditDetail",
                                    creditDetail);
                        } else {

                            /* 更新客户信用明细 */
                            creditDetail.setId(creditResult.getDetileId());
                            creditDetail.setMoney(returnDetailDto.getMoney());

                            int updateResult = dao
                                    .update("credit_sqlMap.updateCreditDetileMoney",
                                            creditDetail);
                            if (updateResult != 1) {
                                log.error("更新使用额度错误");
                                return false;
                            }

                        }

                        /* 更新客户信用表时间戳 */
                        CustomerCreditEntity creditUpdatePara = new CustomerCreditEntity();
                        creditUpdatePara.setId(creditResult.getId());
                        creditUpdatePara.setTimeStamp(creditResult.getTimeStamp());
                        int result = dao.update(
                                "credit_sqlMap.updateCustomerCreditTimeStamp",
                                creditUpdatePara);

                        if (result != 1) {
                            log.error("信用已变更!");
                            return false;
                        }

                    }

                    // 设置发货单 结算状态为 未结清 未结清:0
                    SendGoodsEntity sendGoodsEntity = new SendGoodsEntity();
                    sendGoodsEntity.setId(returnDetailDto.getSendGoodsId());
                    sendGoodsEntity.setCloseFlag("0");
                    dao.update("sendGoods_sqlMap.updateSendGoodsCloseFlag",
                            sendGoodsEntity);
                }
            }

            Map<Integer, String> newSellMap = new HashMap<Integer, String>();
            if (para.getSellContractIdArr() != null) {
                /* begin 指定到合同 */
                for (int j = 0; j < para.getSellContractIdArr().length; j++) {
                    String thisMrDetileId = para.getSellMrDetileIdArr()[j];
                    BigDecimal newMoney = new BigDecimal(para.getSellPriceArr()[j]);

                    /* 新增 */
                    if (StringUtils.equals(thisMrDetileId, "-1")) {
                        /* 新增回款明细 */
                        MreturnDto insertPara = new MreturnDto();
                        insertPara.setId(para.getMreturnId());
                        insertPara.setAppointType("2");
                        insertPara.setSellContractId(para.getSellContractIdArr()[j]);

                        insertPara.setMoney(newMoney);
                        dao.insert("mreturn_sqlMap.insertReturnDetail", insertPara);
                    } else {
                        /* 修改 */
                        /* 当指定金额变更时 */
                        if (sellMap.get(Integer.parseInt(thisMrDetileId)).getMoney()
                                .compareTo(newMoney) != 0) {
                            /* 当指定金额为0时 */
                            if (newMoney.compareTo(new BigDecimal(0)) == 0) {
                                // 删除回款明细
                                MreturnDto deletePara = new MreturnDto();
                                deletePara.setDetailId(thisMrDetileId);
                                dao.delete("mreturn_sqlMap.deleteReturnDetail",
                                        deletePara);
                            } else {
                                // 更新回款明细
                                MreturnDto updatePara = new MreturnDto();
                                updatePara.setDetailId(thisMrDetileId);
                                updatePara.setMoney(newMoney);

                                dao.update("mreturn_sqlMap.updateReturnDetailMoney",
                                        updatePara);
                            }

                        }
                    }

                    /* 插入 */
                    newSellMap.put(Integer.parseInt(para.getSellMrDetileIdArr()[j]),
                            "true");
                }

            }

            /* 删除 */
            for (ReturnDetailDto returnDetailDto : sellList) {
                if (newSellMap.get(returnDetailDto.getId()) == null) {
                    // 删除回款明细
                    MreturnDto deletePara = new MreturnDto();
                    deletePara.setDetailId(returnDetailDto.getId().toString());
                    dao.delete("mreturn_sqlMap.deleteReturnDetail", deletePara);
                }
            }

            /* 更新回款表时间戳 */
            MreturnDto mreturnPara = new MreturnDto();
            mreturnPara.setId(mreturnDto.getId());
            mreturnPara.setTimeStamp(mreturnDto.getTimeStamp());

            int updateResult = 0;
            updateResult = dao.update("mreturn_sqlMap.updateMReturnTimestamp",
                    mreturnPara);
            if (updateResult != 1) {
                log.error("回款已变更!");
                return false;
            }

            dao.commitTransaction();
            return true;
        } catch (Exception e) {
            log.error("新增回款指定发生错误", e);
            return false;
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e1) {
                log.error("新增回款指定回滚,发生错误:", e1);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.sell.ISendGoodsService#sdQueryAllProductType
     * ()
     */
    @SuppressWarnings("unchecked")
    public List<ProductTypeEntity> getAllProductType() {
        List<ProductTypeEntity> list = new ArrayList<ProductTypeEntity>();
        try {
            list = dao.queryForlist("sendGoods_sqlMap.sdQueryAllProductType", null);
        } catch (Exception e) {
            log.error("select producttype error!", e);
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.mreturn.IMReturnService#getArriveInitialization
     * (java.lang.String)
     */
    public MReturnInfoDto getArriveInitialization(String mrid) {
        log.debug("到帐页面初始化查询！");
        MReturnInfoDto mrifd = new MReturnInfoDto();
        try {

            mrifd = (MReturnInfoDto) dao.queryForObject(
                    "mreturn_sqlMap.selectArriveInitialization", mrid);
        } catch (Exception e) {
            log.error("到帐页面初始化查询出错！", e);
        }
        return mrifd;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.mreturn.IMReturnService#select(java.lang
     * .String)
     */
    public String selectMreturnTimesTamp(String id) {
        log.debug("回款表时间戳查询");
        String result = null;
        try {
            result = (String) dao.queryForObject("mreturn_sqlMap.selectmreturntimestamp",
                    id);
        } catch (Exception e) {
            log.error("回款表时间戳查询失败！", e);
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.mreturn.IMReturnService#mewarriveMReturn
     * (cn.com.thtf.egov.cms.entity.MreturnEntity)
     */
    public Boolean mewarriveMReturn(MreturnEntity mreturn, List<MreturnArriveInfoDto> list) {
        boolean result = true;
        BigDecimal bd = new BigDecimal(0);
        Map<String, String> map = new HashMap<String, String>();
        Integer pd = 0;
        boolean customercredit = false;
        try {
            dao.beginTransaction();
            pd = dao.update("mreturn_sqlMap.updateMewarriveMReturn", mreturn);
            if (pd == 0) {
                throw new Exception();
            }
            for (MreturnArriveInfoDto maid : list) {
                if (StringUtils.isNotEmpty(maid.getSendgoodsid())) {
                    if (maid.getCustomerdetailmoney().compareTo(bd) != 0) {
                        if (maid.getReturndetailmoney().compareTo(bd) != 0) {
                            pd = dao.update("mreturn_sqlMap.updatecustomercreditdetail",
                                    maid);
                            customercredit = true;
                            map.put("sendgoodsid", maid.getSendgoodsid());
                            map.put("productid", maid.getProductid().toString());
                            map.put("customertimestamp", maid.getCustomertimestamp());
                        }
                        if (pd == 0) {
                            log.error("修改客户信用明细失败！");
                            throw new Exception();
                        }
                    } else {
                        dao.delete("mreturn_sqlMap.deletecustomercreditdetail",
                                maid.getCustomerdetailid());
                    }
                    if (maid.getCustomerdetailmoney().compareTo(
                            maid.getReturndetailmoney()) == 0) {
                        dao.delete("mreturn_sqlMap.deletecustomercreditdetail",
                                maid.getCustomerdetailid());
                    }
                }
            }
            if (customercredit == true) {
                dao.update("mreturn_sqlMap.updatecustomercredittimestamp", map);
            }
            // dao.delete("mreturn_sqlMap.deletemreturnarrive",
            // maid.getReturndetailid());

            // 设置发货单 结算状态为 未结清 未结清:0
            // SendGoodsEntity sendGoodsEntity = new SendGoodsEntity();
            // sendGoodsEntity.setId(mreturn.getId());
            // sendGoodsEntity.setCloseFlag("0");
            // dao.update("sendGoods_sqlMap.updateSendGoodsCloseFlagM",
            // sendGoodsEntity);

            /* 到账时发送邮件给资金管理员发邮件 */
            IMailService mailService = (IMailService) Container.getBean("mailServiceImp");
            boolean mailResult = mailService.mreturn(mreturn);
            if (!mailResult) {
                return false;
            }

            dao.commitTransaction();
        } catch (Exception e) {
            result = false;
            log.error("到帐更新失败！", e);
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.error("到帐更新失败-->事务回滚失败", e);
                result = false;
            }
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.mreturn.IMReturnService#removeMReturn(java
     * .lang.String, cn.com.thtf.egov.cms.entity.UserEntity)
     */
    public Integer removeMReturn(String mrid, UserEntity userInfo) {
        log.debug("回款删除！");
        Integer result = 0;
        Integer pd = 0;

        try {
            dao.beginTransaction();

            pd = (Integer) dao.queryForObject("mreturn_sqlMap.mreturnSelectDetail", mrid);
            if (pd == 0) {
                MReturnListDto mreturn = (MReturnListDto) dao.queryForObject(
                        "mreturn_sqlMap.selectMreturnListDetail", mrid);

                ICommonService commonService = (ICommonService) Container
                        .getBean("commonServiceImpl");
                BusiLogEntity logPara = new BusiLogEntity();
                logPara.setUserid(userInfo.getId());
                logPara.setUsername(userInfo.getName());
                logPara.setContent(getMReturnContent(mreturn));
                logPara.setBusiType(101);

                boolean addResult = commonService.addBusiLog(logPara);
                if (addResult) {
                    dao.delete("mreturn_sqlMap.deleteMReturn", mrid);
                    result = 1;
                }
            } else {
                result = -1;
                log.info("存在明细的回款不可删除");
            }
            dao.commitTransaction();
        } catch (Exception e) {
            log.error("回款删除失败！", e);
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e1) {
                log.error("回款删除事物回滚,发生错误:", e1);
            }
        }
        return result;
    }

    /**
     * 封装回款信息
     * 
     * @param mreturn
     * @return
     */
    private String getMReturnContent(MReturnListDto mreturn) {
        StringBuffer sb = new StringBuffer();
        sb.append("回款流水号:").append(mreturn.getId()).append(",");
        sb.append("回款编号:").append(mreturn.getNo()).append(",");
        sb.append("产品分类:").append(mreturn.getProductTyepName()).append(",");
        sb.append("客户名称:").append(mreturn.getCustomerName()).append(",");
        sb.append("回款总额:").append(mreturn.getMoney()).append(",");

        sb.append("指定金额:").append(mreturn.getPointMoney()).append(",");
        sb.append("合同预收金额:").append(mreturn.getPointContractMoney()).append(",");
        sb.append("产品分类预收金额:").append(mreturn.getPointProductTypeMoney()).append(",");
        sb.append("退款金额:").append(mreturn.getBackMoney()).append(",");
        if (StringUtils.equals(mreturn.getRetrunType(), "0")) {
            sb.append("回款类型:").append("回款").append(",");
        } else if (StringUtils.equals(mreturn.getRetrunType(), "1")) {
            sb.append("回款类型:").append("在途款").append(",");
        } else {
            sb.append("回款类型:").append("到账").append(",");
        }

        if (StringUtils.equals(mreturn.getReturnWay(), "1")) {
            sb.append("回款方式:").append("现金").append(",");
        } else if (StringUtils.equals(mreturn.getReturnWay(), "2")) {
            sb.append("回款方式:").append("支票").append(",");
        } else if (StringUtils.equals(mreturn.getReturnWay(), "3")) {
            sb.append("回款方式:").append("网银").append(",");
        } else if (StringUtils.equals(mreturn.getReturnWay(), "4")) {
            sb.append("回款方式:").append("电汇").append(",");
        } else if (StringUtils.equals(mreturn.getReturnWay(), "5")) {
            sb.append("回款方式:").append("银行承兑").append(",");
        } else if (StringUtils.equals(mreturn.getReturnWay(), "6")) {
            sb.append("回款方式:").append("承诺函").append(",");
        } else {
            sb.append("回款方式:").append("其它").append(",");
        }

        sb.append("录入日期:").append(mreturn.getDate()).append(",");
        sb.append("回款日期:").append(mreturn.getReturnDate()).append(",");
        sb.append("凭证号:").append(mreturn.getNumber()).append(",");
        sb.append("录入人:").append(mreturn.getUserName()).append(",");

        sb.append("删除日期:").append(Util.getDateTime()).append(";");
        return sb.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @seecn.com.thtf.egov.cms.iservice.mreturn.IMReturnService#
     * getAllProductTypeByUserId()
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<ProductTypeEntity> getAllProductTypeByUserId(String userId) {
        List<ProductTypeEntity> list = new ArrayList<ProductTypeEntity>();
        try {
            list = dao.queryForlist("mreturn_sqlMap.mRetrunQueryAllProductTypeByUserId",
                    userId);
        } catch (Exception e) {
            log.error("select producttype error!", e);
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.mreturn.IMReturnService#getMReturnCustomer
     * (java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List getMReturnCustomer(NewPage page, CustomerEntity customer) {
        List list = new ArrayList();
        try {
            list = queryRecords(dao, "getMReturnAllCustomer.search", customer, page);
        } catch (Exception e) {
            log.error("检索客户错误!", e);
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.mreturn.IMReturnService#sumMoney(cn.com
     * .thtf.egov.cms.entity.ReturnDetailEntity)
     */
    public BigDecimal sumMoney(ReturnDetailEntity rde) {
        BigDecimal result = new BigDecimal(0);
        try {
            result = (BigDecimal) dao
                    .queryForObject("mreturn_sqlMap.selectSumMoney", rde);
        } catch (Exception e) {
            log.error("总额查询失败！", e);
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @seecn.com.thtf.egov.cms.iservice.mreturn.IMReturnService#
     * selectCustomeerTimesTamp(java.lang.String)
     */
    public String selectCustomeerTimesTamp(String id) {
        log.debug("客户表时间戳查询");
        String result = null;
        try {
            result = (String) dao.queryForObject(
                    "mreturn_sqlMap.selectcustomertimestamp", id);
        } catch (Exception e) {
            log.error("客户表时间戳查询失败！", e);
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.mreturn.IMReturnService#createMReturn(cn
     * .com.thtf.egov.cms.entity.MreturnEntity)
     */
    @Override
    public Boolean createMReturn(MreturnEntity mreturnEntity) {
        Boolean result = false;
        try {

            dao.beginTransaction();
            dao.insert("mreturn_sqlMap.createMReturn", mreturnEntity);

            if (mreturnEntity.getReturnType() == 0) {
                /* 新建回款发送邮件给资金管理员发邮件 */
                IMailService mailService = (IMailService) Container
                        .getBean("mailServiceImp");
                boolean mailResult = mailService.mreturn(mreturnEntity);
                if (!mailResult) {
                    return false;
                }
            }

            dao.commitTransaction();
            result = true;
        } catch (Exception e) {
            result = false;
            log.error("insert MReturn error!", e);
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e1) {
                log.error("新建回款事物回滚,发生错误:", e1);
            }
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.mreturn.IMReturnService#selectarriveinfodto
     * (java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public List<MreturnArriveInfoDto> selectarriveinfodto(String id) {
        List<MreturnArriveInfoDto> maid = new ArrayList<MreturnArriveInfoDto>();
        try {
            maid = (List<MreturnArriveInfoDto>) dao.queryForlist(
                    "mreturn_sqlMap.selectarriveinfodto", id);
        } catch (Exception e) {
            log.error("到帐页面初始化查询出错！", e);
        }
        return maid;
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

}
