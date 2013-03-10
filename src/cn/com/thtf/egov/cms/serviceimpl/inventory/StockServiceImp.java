/**
 * ClassName  StockServiceImp
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-6-3
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.serviceimpl.inventory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.BaseService;
import cn.com.thtf.egov.cms.application.NewIDao;
import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.dto.CustomerCreditCommonDto;
import cn.com.thtf.egov.cms.dto.MreturnDto;
import cn.com.thtf.egov.cms.dto.SendGoodsDto;
import cn.com.thtf.egov.cms.dto.StockProductDto;
import cn.com.thtf.egov.cms.dto.StockQueryDto;
import cn.com.thtf.egov.cms.dto.StockSendGoodsDto;
import cn.com.thtf.egov.cms.entity.BuyBackGoodsEntity;
import cn.com.thtf.egov.cms.entity.CustomerCreditEntity;
import cn.com.thtf.egov.cms.entity.MoveEntity;
import cn.com.thtf.egov.cms.entity.SampleOutEntity;
import cn.com.thtf.egov.cms.entity.SellContractEntity;
import cn.com.thtf.egov.cms.entity.SendGoodsEntity;
import cn.com.thtf.egov.cms.entity.UserAreaProductEntity;
import cn.com.thtf.egov.cms.entity.UserProductEntity;
import cn.com.thtf.egov.cms.entity.UserStockroomProductEntity;
import cn.com.thtf.egov.cms.iservice.common.ICommonService;
import cn.com.thtf.egov.cms.iservice.credit.ICreditService;
import cn.com.thtf.egov.cms.iservice.inventory.IStockService;
import cn.com.thtf.egov.cms.iservice.mail.IMailService;
import cn.com.thtf.egov.cms.util.Container;

/**
 * StockServiceImp
 * 
 * @author Lubo
 */
public class StockServiceImp extends BaseService implements IStockService {

    /** log */
    private static Logger log = LoggerFactory.getLogger(StockServiceImp.class);
    /** ICommonService */
    private ICommonService commonService;
    /** ITodoWorkService */
    // private ITodoWorkService todoWorkService;
    /** NewIDao */
    private NewIDao dao;

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.inventory.IStockService#auditBuyBackGoods
     * (cn.com.thtf.egov.cms.entity.BuyBackGoodsEntity)
     */
    @Override
    public boolean auditBuyBackGoods(BuyBackGoodsEntity auditPara) {
        try {
            // 更新 状态和意见 其它单共通 10
            dao.update("inventtory_sqlMap.updateBuyBackGoodsComplete", auditPara);

            // check失败的发货单是否在配货单中，如果有，删除。其它单共通
            dao.delete("box_sqlMap.deleteBoxDetail", auditPara.getId());

            // 插入事务
            // todoWorkService = (ITodoWorkService)
            // Container.getApplicationContext()
            // .getBean("todoWrokServiceImpl");
            // WorkEntity work = new WorkEntity();
            // boolean workResult = false;
            // work.setCount(1);
            // work.setWorkId(Constants.SELL3);
            // work.setUserId(auditPara.getUserId());
            //
            // workResult = todoWorkService.addTodoWorks(work);
            // if (!workResult) {
            // log.error("添加代办事物错误!");
            // return false;
            // }

            return true;
        } catch (Exception e) {
            log.error("评审返场单失败！", e);
            return false;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.inventory.IStockService#auditMove(cn.com
     * .thtf.egov.cms.entity.MoveEntity)
     */
    @Override
    public boolean auditMove(MoveEntity auditPara) {
        try {
            // 更新 状态和意见 其它单共通 6
            dao.update("inventtory_sqlMap.updateMoveComplete", auditPara);

            // check失败的发货单是否在配货单中，如果有，删除。其它单共通
            dao.delete("box_sqlMap.deleteBoxDetail", auditPara.getId());

            // 更新库存表,减少库存冻结数
            dao.update("inventtory_sqlMap.updateSendLockByMoveId", auditPara.getId());

            // 插入事务
            // todoWorkService = (ITodoWorkService)
            // Container.getApplicationContext()
            // .getBean("todoWrokServiceImpl");
            // WorkEntity work = new WorkEntity();
            // boolean workResult = false;
            // work.setCount(1);
            // work.setWorkId(Constants.SELL3);
            // work.setUserId(auditPara.getUserId());
            //
            // workResult = todoWorkService.addTodoWorks(work);
            // if (!workResult) {
            // log.error("添加代办事物错误!");
            // return false;
            // }

            return true;
        } catch (Exception e) {
            log.error("评审移库单失败！", e);
            return false;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.inventory.IStockService#auditSampleOut(
     * cn.com.thtf.egov.cms.entity.SampleOutEntity)
     */
    @Override
    public boolean auditSampleOut(SampleOutEntity auditPara) {
        try {
            // 更新 状态和意见 其它单共通 8
            dao.update("inventtory_sqlMap.updateSampleOutComplete", auditPara);

            // check失败的发货单是否在配货单中，如果有，删除。其它单共通
            dao.delete("box_sqlMap.deleteBoxDetail", auditPara.getId());

            // 更新库存表,减少库存冻结数
            dao.update("inventtory_sqlMap.updateSendLockBySampleOutId", auditPara.getId());

            // 插入事务
            // todoWorkService = (ITodoWorkService)
            // Container.getApplicationContext()
            // .getBean("todoWrokServiceImpl");
            // WorkEntity work = new WorkEntity();
            // boolean workResult = false;
            // work.setCount(1);
            // work.setWorkId(Constants.SELL3);
            // work.setUserId(auditPara.getUserId());
            //
            // workResult = todoWorkService.addTodoWorks(work);
            // if (!workResult) {
            // log.error("添加代办事物错误!");
            // return false;
            // }

            return true;
        } catch (Exception e) {
            log.error("评审借出单失败！", e);
            return false;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.inventory.IStockService#auditSendGoods(
     * cn.com.thtf.egov.cms.entity.SendGoodsEntity)
     */
    @SuppressWarnings("unchecked")
    public boolean auditSendGoods(SendGoodsDto auditPara) {
        try {
            dao.beginTransaction();

            // 更新 状态和意见 其它单共通
            SendGoodsEntity sendGoodsPara = new SendGoodsEntity();
            sendGoodsPara.setId(auditPara.getId());
            sendGoodsPara.setStatus(auditPara.getStatus());
            sendGoodsPara.setStkAdmIdea(auditPara.getStkAdmIdea());
            sendGoodsPara.setStkAdmText(auditPara.getStkAdmText());

            sendGoodsPara.setStkAdmId(auditPara.getStkAdmId());
            sendGoodsPara.setStkAdmName(auditPara.getStkAdmName());
            sendGoodsPara.setStkAdmDate(auditPara.getStkAdmDate());
            dao.update("sendGoods_sqlMap.updateSendGoodsComplete", sendGoodsPara);

            /* 获取合同信息 */
            SellContractEntity sellContract = (SellContractEntity) dao.queryForObject(
                    "sendGoods_sqlMap.selectSellContractBySendgoodId", auditPara.getId());

            /* 检索用户姓名和邮件地址 */
            auditPara.setToUserName(sellContract.getUserName());
            auditPara.setToUserMail(sellContract.getText());
            
            commonService = (ICommonService) Container.getBean("commonServiceImpl");
            String creditId = sellContract.getCustomerCreditId().toString();
            /* 信用信息 */
            if (sellContract.getCreditTypeId() != 5) {
                String id = commonService.getCustomerCreditId(sellContract.getCustomerId(),
                        sellContract.getProductTypeId());
                if (id == null) {
                    log.error("合同:{},获取信用信息错误(非项目信用)", sellContract.getId());
                } else {
                    creditId = id;
                }
            }

            // 取客户信用时间戳
            commonService = (ICommonService) Container.getBean("commonServiceImpl");
            List<CustomerCreditCommonDto> creditResult = commonService.getCustomerCredit(
                    sellContract.getCustomerId().toString(), sellContract
                            .getProductTypeId().toString(), creditId);

            if (creditResult == null || creditResult.size() == 0) {
                log.error("获取信用信息错误");
                return false;
            }

            // 删除此发货明细对应的信用记录
            dao.delete("credit_sqlMap.deleteCreditDetileBySendgoodsId", auditPara.getId());

            // 更新客户信用时间戳
            CustomerCreditEntity creditUpdatePara = new CustomerCreditEntity();
            creditUpdatePara.setId(Integer.parseInt(creditResult.get(0)
                    .getCustomerCrdId()));
            creditUpdatePara.setTimeStamp(creditResult.get(0).getTimeStamp());

            ICreditService creditService = (ICreditService) Container
                    .getBean("creditTypeServiceImp");
            boolean result = creditService
                    .modifyCustomerCreditTimeStamp(creditUpdatePara);

            if (!result) {
                log.error("信用已变更!");
                return false;
            }

            // 获取回款时间戳
            List<MreturnDto> mreturn = dao.queryForlist(
                    "mreturn_sqlMap.selectMreturnDetailBySendgoodsId", auditPara.getId());

            for (MreturnDto mreturnDto : mreturn) {
                // 检索此回款 指定金额
                MreturnDto moneyResult = (MreturnDto) dao.queryForObject(
                        "mreturn_sqlMap.selectBySendgoodsIdAndMreturnId", mreturnDto);
                mreturnDto.setMoney(new BigDecimal(moneyResult.getAppointMoney()));

                // 检索是否存在指定到合同的回款
                mreturnDto.setSellContractId(sellContract.getId());
                int count = dao.getCount("mreturn_sqlMap.selectBySendgoodsIdAndSellId",
                        mreturnDto);

                // 指定到合同,存在则更新 不存在则新增
                if (count == 0) {
                    mreturnDto.setAppointType("2");
                    dao.insert("mreturn_sqlMap.insertReturnDetail", mreturnDto);
                } else {
                    dao.update("mreturn_sqlMap.updateMoneyBySellIdAndReturnId",
                            mreturnDto);
                }

                // 删除此发货明细对应的回款记录
                dao.delete("mreturn_sqlMap.deleteReturnDetailBySendgoodsId", mreturnDto);

                // 更新回款时间戳
                int updateResult = 0;
                updateResult = dao.update("mreturn_sqlMap.updateMReturnTimestamp",
                        mreturnDto);
                if (updateResult != 1) {
                    log.error("回款已变更!");
                    return false;
                }
            }

            // 更新库存表,减少库存冻结数
            dao.update("inventtory_sqlMap.updateSendLockBySendgoodsId", auditPara.getId());

            // check失败的发货单是否在配货单中，如果有，删除。其它单共通
            dao.delete("box_sqlMap.deleteBoxDetail", auditPara.getId());

            // 插入事务
            // todoWorkService = (ITodoWorkService)
            // Container.getApplicationContext()
            // .getBean("todoWrokServiceImpl");
            // WorkEntity work = new WorkEntity();
            // boolean workResult = false;
            // work.setCount(1);
            // work.setWorkId(Constants.SELL3);
            // work.setUserId(sellContract.getUserId());
            //
            // workResult = todoWorkService.addTodoWorks(work);
            // if (!workResult) {
            // log.error("添加代办事物错误!");
            // return false;
            // }

            /* 给销售经理发邮件 */
            IMailService mailService = (IMailService) Container.getBean("mailServiceImp");
            boolean mailResult = mailService.sendGoodsException(auditPara);

            if (!mailResult) {
                log.error("发送邮件失败！");
                return false;
            }

            dao.commitTransaction();
            return true;
        } catch (Exception e) {
            log.error("评审发货单失败！", e);
            return false;
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e1) {
                log.error("发货异常事物回滚,发生错误:", e1);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.inventory.IStockService#getOrderList(cn
     * .com.thtf.egov.cms.dto.StockSendGoodsDto,
     * cn.com.thtf.egov.cms.application.NewPage)
     */
    @SuppressWarnings("unchecked")
    public List<StockSendGoodsDto> getOrderList(StockSendGoodsDto para, NewPage page) {
        List result = null;

        try {

            int roleId = para.getUser().getRoleId();

            if (roleId == 12) {
                List<UserStockroomProductEntity> userStockroomList = (List<UserStockroomProductEntity>) dao
                        .getSqlMap().queryForList(
                                "inventtory_sqlMap.selectTreasuryManagerById",
                                para.getUser().getId());

                /* 封装查询参数 */
                // setUserStockroom(para, userStockroomList);

                if (userStockroomList.size() > 0) {
                    /* 封装查询参数 */
                    // setUserStockroom(para, userStockroomList);
                    para.setUserStockroomList(userStockroomList);
                    result = queryRecords(dao, "selectStockSendGoods.data", para, page);
                } else {
                    result = new ArrayList();
                }
            } else {
                result = queryRecords(dao, "selectStockSendGoods.data", para, page);
            }

        } catch (Exception e) {
            log.error("检索单据列表错误！", e);
        }

        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.inventory.IStockService#getStockDetileList
     * (cn.com.thtf.egov.cms.dto.StockQueryDto,
     * cn.com.thtf.egov.cms.application.NewPage)
     */
    @SuppressWarnings("unchecked")
    public List<StockProductDto> getStockDetileList(StockQueryDto para, NewPage page) {
        List result = null;
        try {
            result = queryRecords(dao, "selectStockProductDetail.data", para, page);
        } catch (Exception e) {
            log.error("检索库存明细错误！", e);
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.inventory.IStockService#getStockList(cn
     * .com.thtf.egov.cms.dto.StockQueryDto,
     * cn.com.thtf.egov.cms.application.NewPage)
     */
    @SuppressWarnings("unchecked")
    public List<StockProductDto> getStockList(StockQueryDto para, NewPage page) {
        List result = null;

        try {
            int roleId = para.getUser().getRoleId();
            /* 销售经理、销售总监、信用专员、采购专员、区域总监、产品总监、采购主管 */
            if (roleId == 4 || roleId == 5 || roleId == 6 || roleId == 8 || roleId == 9
                    || roleId == 10 || roleId == 11) {
                List<UserProductEntity> userProductList = (List<UserProductEntity>) dao
                        .getSqlMap().queryForList("salesContract_sqlMap.getUserProduct",
                                para.getUser());
                if (userProductList.size() > 0) {
                    /* 封装查询参数 */
                    setUserProduct(para, userProductList);
                    result = queryRecords(dao, "selectStockProduct.data", para, page);
                } else {
                    result = new ArrayList();
                }
            } else if (roleId == 3) {
                /* 销售助理 */
                List<UserAreaProductEntity> userAreaProductList = (List<UserAreaProductEntity>) dao
                        .getSqlMap()
                        .queryForList("salesContract_sqlMap.getUserAreaProduct",
                                para.getUser());
                if (userAreaProductList.size() > 0) {
                    /* 封装查询参数 */
                    setUserAreaProduct(para, userAreaProductList);
                    result = queryRecords(dao, "selectStockProduct.data", para, page);
                } else {
                    result = new ArrayList();
                }
            } else if (roleId == 12) {
                List<UserStockroomProductEntity> userStockroomList = (List<UserStockroomProductEntity>) dao
                        .getSqlMap().queryForList(
                                "inventtory_sqlMap.selectTreasuryManagerById",
                                para.getUser().getId());
                if (userStockroomList.size() > 0) {
                    /* 封装查询参数 */
                    setUserStockroom(para, userStockroomList);
                    // para.setUserStockroomList(userStockroomList);
                    result = queryRecords(dao, "selectStockProduct.data", para, page);
                } else {
                    result = new ArrayList();
                }
            } else {
                result = queryRecords(dao, "selectStockProduct.data", para, page);
            }
            // result = queryRecords(dao, "selectStockProduct.data", para,
            // page);
        } catch (Exception e) {
            log.error("检索库存列表错误！", e);
        }

        return result;
    }

    /**
     * 封装 查询参数 库房产品
     * 
     * @param salesDto
     * @param userAreaProductList
     */
    private void setUserStockroom(StockQueryDto para,
            List<UserStockroomProductEntity> userStockroomList) {
        // StringBuffer stockroom = new StringBuffer();
        StringBuffer product = new StringBuffer();

        for (UserStockroomProductEntity userStockroom : userStockroomList) {
            // stockroom.append(userStockroom.getStockroomId()).append(",");
            product.append(userStockroom.getProductTypeId()).append(",");
        }
        // if (stockroom.length() > 0) {
        // para.setStockroomIdArr(stockroom.substring(0, stockroom.length() -
        // 1));
        // }
        if (product.length() > 0) {
            para.setProductTypeIdArr(product.substring(0, product.length() - 1));
        }
    }

    // /**
    // * 封装 查询参数 区域产品
    // *
    // * @param salesDto
    // * @param userAreaProductList
    // */
    // private void setUserStockroom(StockQueryDto para,
    // List<UserStockroomProductEntity> userStockroomList) {
    // StringBuffer stockroom = new StringBuffer();
    // StringBuffer product = new StringBuffer();
    //
    // for (UserStockroomProductEntity userStockroom : userStockroomList) {
    // stockroom.append(userStockroom.getStockroomId()).append(",");
    // product.append(userStockroom.getProductTypeId()).append(",");
    // }
    // if (stockroom.length() > 0) {
    // para.setStockroomIdArr(stockroom.substring(0, stockroom.length() - 1));
    // }
    // if (product.length() > 0) {
    // para.setProductTypeIdArr(product.substring(0, product.length() - 1));
    // }
    // }
    //
    // /**
    // * 封装 查询参数 区域产品
    // *
    // * @param salesDto
    // * @param userAreaProductList
    // */
    // private void setUserStockroom(StockSendGoodsDto para,
    // List<UserStockroomProductEntity> userStockroomList) {
    // StringBuffer stockroom = new StringBuffer();
    // StringBuffer product = new StringBuffer();
    //
    // for (UserStockroomProductEntity userStockroom : userStockroomList) {
    // stockroom.append(userStockroom.getStockroomId()).append(",");
    // product.append(userStockroom.getProductTypeId()).append(",");
    // }
    // if (stockroom.length() > 0) {
    // para.setStockroomIdArr(stockroom.substring(0, stockroom.length() - 1));
    // }
    // if (product.length() > 0) {
    // para.setProductTypeIdArr(product.substring(0, product.length() - 1));
    // }
    // }

    /**
     * 封装 查询参数 区域产品
     * 
     * @param salesDto
     * @param userAreaProductList
     */
    private void setUserAreaProduct(StockQueryDto para,
            List<UserAreaProductEntity> userAreaProductList) {
        StringBuffer product = new StringBuffer();

        for (UserAreaProductEntity userAreaProduct : userAreaProductList) {
            product.append(userAreaProduct.getProductTypeId()).append(",");
        }

        if (product.length() > 0) {
            para.setProductTypeIdArr(product.substring(0, product.length() - 1));
        }
    }

    /**
     * 封装 查询参数 区域产品
     * 
     * @param salesDto
     * @param userAreaProductList
     */
    private void setUserProduct(StockQueryDto para,
            List<UserProductEntity> userProductList) {
        StringBuffer product = new StringBuffer();

        for (UserProductEntity userProduct : userProductList) {
            product.append(userProduct.getProductTypeId()).append(",");
        }

        if (product.length() > 0) {
            para.setProductTypeIdArr(product.substring(0, product.length() - 1));
        }
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
