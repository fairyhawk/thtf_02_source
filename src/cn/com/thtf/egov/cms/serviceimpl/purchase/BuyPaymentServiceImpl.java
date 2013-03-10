package cn.com.thtf.egov.cms.serviceimpl.purchase;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.BaseService;
import cn.com.thtf.egov.cms.application.NewIDao;
import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.dto.BuyPaymentAddDto;
import cn.com.thtf.egov.cms.constant.Constants;
import cn.com.thtf.egov.cms.dto.BuyContractDto;
import cn.com.thtf.egov.cms.dto.BuyPaymentBuyContractDto;
import cn.com.thtf.egov.cms.dto.BuyPaymentInfoDto;
import cn.com.thtf.egov.cms.dto.BuyPaymentListQueryDto;
import cn.com.thtf.egov.cms.entity.PaymentDetailEntity;
import cn.com.thtf.egov.cms.dto.WorkReceiverDto;
import cn.com.thtf.egov.cms.entity.InStockDetailEntity;
import cn.com.thtf.egov.cms.entity.PaymentEntity;
import cn.com.thtf.egov.cms.entity.ProductEntity;
import cn.com.thtf.egov.cms.entity.ProductTypeEntity;
import cn.com.thtf.egov.cms.entity.WorkEntity;
import cn.com.thtf.egov.cms.iservice.common.ICommonService;
import cn.com.thtf.egov.cms.iservice.purchase.IBuyPaymentService;
import cn.com.thtf.egov.cms.iservice.work.ITodoWorkService;
import cn.com.thtf.egov.cms.util.Container;

/**
 * 采购付款
 * 
 * @author lxs
 */
public class BuyPaymentServiceImpl extends BaseService implements IBuyPaymentService {

    /** log */
    private static Logger log = LoggerFactory.getLogger(BuyPaymentServiceImpl.class);
    /** NewIDao */
    private NewIDao dao;
    /** ITodoWorkService */
    private ITodoWorkService todoWorkService;
    /** ICommonService */
    private ICommonService commonService;

    public NewIDao getDao() {
        return dao;
    }

    public void setDao(NewIDao dao) {
        this.dao = dao;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.purchase.IBuyPaymentService#getBuyPaymentList
     * ()
     */
    @Override
    public List<Object> getBuyPaymentList(NewPage page,
            BuyPaymentListQueryDto buyPaymentListQueryDto) {
        List<Object> list = new ArrayList<Object>();
        try {
            list = queryRecords(dao, "getBuyPaymentList.search", buyPaymentListQueryDto,
                    page);
        } catch (Exception e) {
            log.error("select paymentList error!", e);
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.purchase.IBuyPaymentService#viewBuyPayment
     * (java.lang.String)
     */
    @Override
    public BuyPaymentInfoDto viewBuyPayment(String id) {
        log.debug("付款单查看页面信息!");
        BuyPaymentInfoDto bpid = new BuyPaymentInfoDto();
        try {
            bpid = (BuyPaymentInfoDto) dao.queryForObject(
                    "buyPayment_sqlMap.selectViewBuyPayment", id);
        } catch (Exception e) {
            log.error("buyPayment_sqlMap.selectViewBuyPayment err!", e);
        }
        return bpid;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.purchase.IBuyPaymentService#getAllProductType
     * ()
     */
    @Override
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
     * cn.com.thtf.egov.cms.iservice.purchase.IBuyPaymentService#getAllProductType
     * ()
     */
    @SuppressWarnings("unchecked")
    @Override
    public List getBuyPaymentAllSupplier(String supplierName, NewPage page) {
        List list = new ArrayList();
        Map<String, String> map = new HashMap<String, String>();
        map.put("supplierName", supplierName);
        try {
            list = queryRecords(dao, "getBuyPaymentAllSupplier.search", map, page);
        } catch (Exception e) {
            log.error("BuyPayment select Supplier error!", e);
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @seecn.com.thtf.egov.cms.iservice.purchase.IBuyPaymentService#
     * getBuyPaymentBuyContract(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<BuyPaymentBuyContractDto> getBuyPaymentBuyContract(String id) {
        List<BuyPaymentBuyContractDto> list = new ArrayList<BuyPaymentBuyContractDto>();
        try {
            list = dao.queryForlist("buyPayment_sqlMap.viewBuyPaymentBuyContract", id);
        } catch (Exception e) {
            log.error("select BuyPaymentView BuyContract error!", e);
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @seecn.com.thtf.egov.cms.iservice.purchase.IBuyPaymentService#
     * getSupplierLinkmanBySupplierId(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List getBuyPaymentSupplierLinkmanBySupplierId(String supplierId) {
        List list = new ArrayList();
        try {
            list = dao.queryForlist(
                    "buyPayment_sqlMap.getBuyPaymentSupplierLinkManBySupplierId",
                    supplierId);
        } catch (Exception e) {
            log.error("select SupplierLinkman By SupplierId error!", e);
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @seecn.com.thtf.egov.cms.iservice.purchase.IBuyPaymentService#
     * getBuyPaymentProductTypeByUserId(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List getBuyPaymentProductTypeByUserId(String userId) {
        List list = new ArrayList();
        try {
            list = dao.queryForlist("buyPayment_sqlMap.getBuyPaymentProductTypeByUserId",
                    userId);
        } catch (Exception e) {
            log.error("select ProductType By userId error!", e);
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @seecn.com.thtf.egov.cms.iservice.purchase.IBuyPaymentService#
     * getBuyPaymentSelectBuyContracts(java.util.Map)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List getBuyPaymentSelectBuyContracts(Map<String, String> map, NewPage page) {
        List list = new ArrayList();
        try {
            list = queryRecords(dao, "getBuyPaymentSelectBuyContracts.search", map, page);
        } catch (Exception e) {
            log.error("select BuyContracts By SupplierId and ProductTypeId error!", e);
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @seecn.com.thtf.egov.cms.iservice.purchase.IBuyPaymentService#
     * getBuyPaymentBuyProduct(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<BuyPaymentBuyContractDto> getBuyPaymentBuyProduct(String id) {
        List<BuyPaymentBuyContractDto> list = new ArrayList<BuyPaymentBuyContractDto>();
        try {
            list = dao.queryForlist("buyPayment_sqlMap.viewBuyPaymentBuyProduct", id);
        } catch (Exception e) {
            log.error("select BuyPayment Product err!", e);
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @seecn.com.thtf.egov.cms.iservice.purchase.IBuyPaymentService#
     * 
     * @seecn.com.thtf.egov.cms.iservice.purchase.IBuyPaymentService#
     * getBuyPaymentInStockDetail(java.util.Map,
     * cn.com.thtf.egov.cms.application.NewPage)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List getBuyPaymentInStockDetail(Map<String, String> map, NewPage page) {
        List list = new ArrayList();
        try {
            list = queryRecords(dao, "getBuyPaymentInStockDetail.search", map, page);
        } catch (Exception e) {
            log.error("select InStockDetail By SupplierId and ProductTypeId error!", e);
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.purchase.IBuyPaymentService#addBuyPayment
     * (cn.com.thtf.egov.cms.dto.BuyPaymentAddDto)
     */
    @Override
    public Boolean addBuyPayment(BuyPaymentAddDto buyP) {
        Boolean result = false;
        List<PaymentDetailEntity> list = new ArrayList<PaymentDetailEntity>();
        try {
            dao.beginTransaction();
            // 重新计算总金额
            BigDecimal totPrice = new BigDecimal(0);
            if (buyP.getAdvanceMoney() != null) {
                for (int i = 0; i < buyP.getAdvanceMoney().length; i++) {
                    totPrice = totPrice.add(new BigDecimal(buyP.getAdvanceMoney()[i]));
                }
            }
            if (buyP.getAppointMoney() != null) {
                for (int i = 0; i < buyP.getAppointMoney().length; i++) {
                    totPrice = totPrice.add(new BigDecimal(buyP.getAppointMoney()[i]));
                }
            }
            buyP.setMoney(totPrice.toString());

            // 插入付款单
            dao.insert("buyPayment_sqlMap.addBuyPayment", buyP);
            list = getPaymentDetailE(buyP);
            // 批量插入付款单明细
            for (PaymentDetailEntity p : list) {
                dao.insert("buyPayment_sqlMap.addBuyPaymentDetail", p);
            }
            // 新增待办事务
            if (StringUtils.equals(buyP.getBtnClick(), "1")) {
                todoWorkService = (ITodoWorkService) Container
                        .getBean("todoWrokServiceImpl");
                commonService = (ICommonService) Container.getBean("commonServiceImpl");
                WorkEntity work = new WorkEntity();
                Boolean pd = true;
                // 事物数量
                work.setCount(1);
                // 事务编号
                work.setWorkId(Constants.SELL16);
                WorkReceiverDto workReceiverDto = commonService.getUserIdByCondition(
                        Integer.parseInt(buyP.getProductTypeId()), null, null,
                        Constants.ROLE_PRODUCT_DIRECTOR);
                String userId = workReceiverDto
                        .getUserIdByRoleId(Constants.ROLE_PRODUCT_DIRECTOR);
                work.setUserId(userId);
                // 执行新增事务
                pd = todoWorkService.addTodoWorks(work);
                if (pd == false) {
                    throw new Exception();
                }
            }
            result = true;
            dao.commitTransaction();
        } catch (Exception e) {
            result = false;
            log.error("insert  BuyPayment error!", e);
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.error("Close Transaction error!", e);
            }
        }
        return result;
    }

    /**
     * 分离付款明细
     * 
     * @author LuPing
     * @return
     */
    public List<PaymentDetailEntity> getPaymentDetailE(BuyPaymentAddDto buyP)
            throws Exception {
        List<PaymentDetailEntity> list = new ArrayList<PaymentDetailEntity>();
        // 付款金额
        String paymentId = buyP.getPaymentId();
        // 指定合同--合同ID
        String[] buyContractId = buyP.getBuyContractId();
        // 指定合同--指定金额
        String[] advanceMoney = buyP.getAdvanceMoney();
        // 指定入库明细--入库单ID
        String[] inStockId = buyP.getInStockId();
        // 指定入库明细--产品ID
        String[] productId = buyP.getProductId();
        // 指定入库明细--指定金额
        String[] appointMoney = buyP.getAppointMoney();
        // 封装指定到合同数据
        if (buyContractId != null) {
            for (int i = 0; i < buyContractId.length; i++) {
                PaymentDetailEntity p = new PaymentDetailEntity();
                p.setPaymentId(paymentId);
                p.setAppointType(0);
                p.setBuyContractId(buyContractId[i]);
                try {
                    p.setMoney(new BigDecimal(advanceMoney[i]));
                } catch (Exception e) {
                    log.error("appointContract  Money error!", e);
                    throw new Exception("appointContract  Money error!");
                }
                list.add(p);
            }
        }
        // 封装指定到付款明细数据
        if (inStockId != null) {
            for (int i = 0; i < inStockId.length; i++) {
                PaymentDetailEntity p = new PaymentDetailEntity();
                p.setPaymentId(paymentId);
                p.setAppointType(1);
                p.setInStockId(inStockId[i]);
                try {
                    p.setProductId(Integer.parseInt(productId[i]));
                } catch (Exception e) {
                    log.error("appointInStock  productId  error!", e);
                    throw new Exception("appointInStock  productId  error!");
                }
                try {
                    p.setMoney(new BigDecimal(appointMoney[i]));
                } catch (Exception e) {
                    log.error("appointInStock  Money error!", e);
                    throw new Exception("appointInStock  Money error!");
                }
                list.add(p);
            }
        }
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @seecn.com.thtf.egov.cms.iservice.purchase.IBuyPaymentService#
     * validatePaymentMoney(cn.com.thtf.egov.cms.dto.BuyPaymentAddDto)
     */
    @SuppressWarnings("unchecked")
    public boolean validatePaymentMoney(BuyPaymentAddDto buyP) throws Exception {
        boolean result = false;
        String[] buyContractId = buyP.getBuyContractId();// 指定合同--合同ID
        String[] advanceMoney = buyP.getAdvanceMoney();// 指定合同--指定金额
        String[] appointMoney = buyP.getAppointMoney();// 指定入库明细--指定金额
        String[] inStockBuyBuyContractId = buyP.getInStockBuyBuyContractId();// 入库明细对应的合同号
        Map<String, BigDecimal> buyContractMap = new HashMap<String, BigDecimal>();// 定义容器---缓存指定到合同的信息
        Map<String, BigDecimal> inStockMap = new HashMap<String, BigDecimal>();// 定义容器---缓存指定到入库明细的信息
        String buyContractPar = "";// 采购合同查询条件
        Set<String> buyContractSet = new HashSet<String>();// 利用容器去重
        try {
            if (buyContractId != null) {// 合同预付金额
                for (int i = 0; i < buyContractId.length; i++) {// 缓存指定到合同的信息
                    buyContractMap.put(buyContractId[i], new BigDecimal(advanceMoney[i]));
                    buyContractSet.add(buyContractId[i]);
                }
            }
            if (inStockBuyBuyContractId != null) {// 合同指定金额
                for (int i = 0; i < inStockBuyBuyContractId.length; i++) { // 缓存指定到入库明细的信息
                    if (inStockMap.containsKey(inStockBuyBuyContractId[i])) {
                        inStockMap.put(
                                inStockBuyBuyContractId[i],
                                inStockMap.get(inStockBuyBuyContractId[i]).add(
                                        new BigDecimal(appointMoney[i])));
                    } else {
                        inStockMap.put(inStockBuyBuyContractId[i], new BigDecimal(
                                appointMoney[i]));
                    }
                    buyContractSet.add(inStockBuyBuyContractId[i]);
                }
            }
            buyContractPar = buyContractSet.toString();
            buyContractPar = StringUtils.remove(
                    buyContractPar.substring(1, buyContractPar.length() - 1), " ");
            buyContractPar = new StringBuffer("'")
                    .append(buyContractPar.replaceAll(",", "','")).append("'").toString();
            log.debug("采购合同查询条件：" + buyContractPar);
            List<BuyContractDto> buyContractDtoList = (List<BuyContractDto>) dao
                    .queryForlist(
                            "buyPayment_sqlMap.getBuyPaymentSelectBuyContractsMoney",
                            buyContractPar);
            for (BuyContractDto buyContractDto : buyContractDtoList) {
                buyContractDto.setPaymentId(buyP.getPaymentId());
                /* 根据付款编号和合同编号取出指定金额 */
                String mySelfBuyConMoney = (String) dao.queryForObject(
                        "buyPayment_sqlMap.buyPaymentMySelfBuyCon", buyContractDto);
                String mySelfInstockMoney = (String) dao.queryForObject(
                        "buyPayment_sqlMap.buyPaymentMySelfInstock", buyContractDto);
                BigDecimal mySelfMoney = new BigDecimal(mySelfBuyConMoney)
                        .add(new BigDecimal(mySelfInstockMoney));

                BigDecimal contractMoney = new BigDecimal(
                        buyContractDto.getContractMoney() + "");// 合同金额
                BigDecimal backContractMoney = new BigDecimal(buyContractDto// 退货金额
                        .getBackContractMoney());
                BigDecimal clickAdvanceMoney = new BigDecimal(0);// 预付金额
                if (buyContractMap.containsKey(buyContractDto.getId())) {
                    clickAdvanceMoney = buyContractMap.get(buyContractDto.getId());
                }
                BigDecimal alreadyAdvanceMoney = new BigDecimal(buyContractDto// 已预付金额
                        .getAdvanceMoney());
                BigDecimal clickAppointMoney = new BigDecimal(0);// 指定金额
                if (inStockMap.containsKey(buyContractDto.getId())) {
                    clickAppointMoney = inStockMap.get(buyContractDto.getId());
                }
                BigDecimal alreadyAppointMoney = new BigDecimal(buyContractDto// 已指定金额
                        .getAppointMoney());
                result = validatePaymentMoneyExpression(contractMoney, backContractMoney,
                        clickAdvanceMoney, alreadyAdvanceMoney, clickAppointMoney,
                        alreadyAppointMoney,mySelfMoney);
                if (!result) {
                    return result;
                }
            }
            result = true;
        } catch (Exception e) {
            log.error("金额验证失败！", e);
            result = false;
        }
        return result;
    }

    /**
     * 合同金额验证计算公式
     * 
     * @param contractMoney
     * @param backContractMoney
     * @param clickAdvanceMoney
     * @param alreadyAdvanceMoney
     * @param clickAppointMoney
     * @param alreadyAppointMoney
     * @return
     * @throws Exception
     *             （合同金额-退货金额）-（预付金额+已预付金额）-（已指定金额+指定金额）>=零
     */
    public boolean validatePaymentMoneyExpression(BigDecimal contractMoney,
            BigDecimal backContractMoney, BigDecimal clickAdvanceMoney,
            BigDecimal alreadyAdvanceMoney, BigDecimal clickAppointMoney,
            BigDecimal alreadyAppointMoney,BigDecimal mySelfMoney) throws Exception {
        boolean result = false;
        try {
            BigDecimal validateDate = contractMoney.subtract(backContractMoney)
                    .subtract(clickAdvanceMoney).subtract(alreadyAdvanceMoney)
                    .subtract(alreadyAppointMoney).subtract(clickAppointMoney).add(mySelfMoney);
            log.debug("合同金额验证！公式计算金额为：" + validateDate);
            if (validateDate.compareTo(new BigDecimal("0")) == 0
                    || validateDate.compareTo(new BigDecimal("0")) == 1) {
                result = true;
            }
        } catch (Exception e) {
            log.error("付款后台金额计算验证错误！", e);
            result = false;
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.purchase.IBuyPaymentService#buyPaymentBuyAudit
     * (cn.com.thtf.egov.cms.entity.Payment, java.lang.String)
     */
    @Override
    public Boolean buyPaymentBuyAudit(PaymentEntity p) {
        boolean result = true;
        try {
            dao.beginTransaction();
            Integer count = dao.update(
                    "buyPayment_sqlMap.updateBuyPaymentReviewComments", p);
            if (count == 0) {
                throw new Exception();
            }
            todoWorkService = (ITodoWorkService) Container.getBean("todoWrokServiceImpl");
            commonService = (ICommonService) Container.getBean("commonServiceImpl");
            WorkEntity work = new WorkEntity();
            Boolean pd = true;
            // 事物数量减一
            work.setCount(-1);
            // 事务编号
            work.setWorkId(Constants.SELL16);
            if (p.getStatus() == 3 || p.getStatus() == 4) {
                // 产品总监减事物
                log.debug("产品总监减事物");
                // 事物接收人
                log.debug("产品总监:" + p.getProMajId() + "事物-1");
                work.setUserId(p.getProMajId());
                // 执行事务更新
                pd = todoWorkService.addTodoWorks(work);
                if (!pd) {
                    throw new Exception();
                }
                if (p.getStatus() == 4) {
                    // 采购主管加事物
                    log.debug("采购主管加事物");
                    // 事物数量增一
                    work.setCount(1);
                    WorkReceiverDto workReceiverDto = commonService.getUserIdByCondition(
                            p.getProductTypeId(), null, null,
                            Constants.ROLE_PROCUREMENT_OFFICER);
                    String cgzgId = workReceiverDto
                            .getUserIdByRoleId(Constants.ROLE_PROCUREMENT_OFFICER);
                    // 事物接收人
                    log.debug("采购主管:" + cgzgId + "事物+1");
                    work.setUserId(cgzgId);
                    // 执行事务更新
                    pd = todoWorkService.addTodoWorks(work);
                }
            } else if (p.getStatus() == 5 || p.getStatus() == 6 || p.getStatus() == 8) {
                // 采购主管减事物
                log.debug("采购主管减事物");
                WorkReceiverDto workReceiverDto = commonService.getUserIdByCondition(
                        p.getProductTypeId(), null, null,
                        Constants.ROLE_PROCUREMENT_OFFICER);
                String cgzgId = workReceiverDto
                        .getUserIdByRoleId(Constants.ROLE_PROCUREMENT_OFFICER);
                // 事物接收人
                log.debug("采购主管:" + cgzgId + "事物-1");
                work.setUserId(cgzgId);
                // 执行事务更新
                pd = todoWorkService.addTodoWorks(work);
                if (!pd) {
                    throw new Exception();
                }
                if (p.getStatus() == 6) {
                    // 运营总监助理
                    log.debug("运营总监助理加事物");
                    // 事物数量增一
                    work.setCount(1);
                    workReceiverDto = commonService.getUserIdByCondition(null, null,
                            null, Constants.ROLE_AST_DIRECTOR_OF_OPERATIONS);
                    String yyzjzlId = workReceiverDto
                            .getUserIdByRoleId(Constants.ROLE_AST_DIRECTOR_OF_OPERATIONS);
                    // 事物接收人
                    log.debug("运营总监助理:" + yyzjzlId + "事物+1");
                    work.setUserId(yyzjzlId);
                    pd = todoWorkService.addTodoWorks(work);
                }
                if (p.getStatus() == 8) {
                    // 运营总监
                    log.debug("运营总监加事物");
                    // 事物数量增一
                    work.setCount(1);
                    workReceiverDto = commonService.getUserIdByCondition(null, null,
                            null, Constants.ROLE_DIRECTOR_OF_OPERATIONS);
                    String yyzjId = workReceiverDto
                            .getUserIdByRoleId(Constants.ROLE_DIRECTOR_OF_OPERATIONS);
                    // 事物接收人
                    log.debug("运营总监:" + yyzjId + "事物+1");
                    work.setUserId(yyzjId);
                    pd = todoWorkService.addTodoWorks(work);
                }
            }
            if (p.getStatus() == 7) {
                // 运营总监助理
                log.debug("运营总监助理减事物");
                WorkReceiverDto workReceiverDto = commonService.getUserIdByCondition(
                        null, null, null, Constants.ROLE_AST_DIRECTOR_OF_OPERATIONS);
                String yyzjzlId = workReceiverDto
                        .getUserIdByRoleId(Constants.ROLE_AST_DIRECTOR_OF_OPERATIONS);
                // 事物接收人
                log.debug("运营总监助理:" + yyzjzlId + "事物-1");
                work.setUserId(yyzjzlId);
                pd = todoWorkService.addTodoWorks(work);
            }
            if (p.getStatus() == 9) {
                // 运营总监
                log.debug("运营总监减事物");
                WorkReceiverDto workReceiverDto = commonService.getUserIdByCondition(
                        null, null, null, Constants.ROLE_DIRECTOR_OF_OPERATIONS);
                String yyzjId = workReceiverDto
                        .getUserIdByRoleId(Constants.ROLE_DIRECTOR_OF_OPERATIONS);
                // 事物接收人
                log.debug("运营总监:" + yyzjId + "事物-1");
                work.setUserId(yyzjId);
                pd = todoWorkService.addTodoWorks(work);
            }
            if (p.getStatus() == 10) {
                // 运营总监助理或运营总监事物减一
                log.debug("减事物用户ID" + p.getUserId());
                work.setUserId(p.getUserId());
                pd = todoWorkService.addTodoWorks(work);
                if (!pd) {
                    throw new Exception();
                }
                log.debug("采购专员加事物");
                // 事物数量增一
                work.setCount(1);
                // 事务编号
                work.setWorkId(Constants.SELL17);
                WorkReceiverDto workReceiverDto = commonService.getUserIdByCondition(
                        p.getProductTypeId(), null, null,
                        Constants.ROLE_PROCUREMENT_COMMISSIONER);
                String cgzyId = workReceiverDto
                        .getUserIdByRoleId(Constants.ROLE_PROCUREMENT_COMMISSIONER);
                log.debug("采购主专员:" + cgzyId + "事物+1");
                work.setUserId(cgzyId);
                pd = todoWorkService.addTodoWorks(work);

            }
            if (!pd) {
                throw new Exception();
            }
            dao.commitTransaction();
        } catch (Exception e) {
            result = false;
            log.error("付款评审失败!", e);
        } finally {
            try {
                dao.endTransaction();
                log.debug("付款评审成功");
            } catch (Exception e) {
                log.error("付款评审事物回滚失败!", e);
            }
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.purchase.IBuyPaymentService#buyPaymentPrint
     * (java.lang.String)
     */
    @Override
    public Boolean buyPaymentPrint(PaymentEntity pe) {
        boolean result = true;
        try {
            dao.beginTransaction();
            if (pe.getStatus() != 10) {
                throw new Exception();
            }
            log.debug(pe.getStatus() + "状态");
            pe.setStatus(12);

            Integer count = dao.update(
                    "buyPayment_sqlMap.updateBuyPaymentReviewComments", pe);
            if (count == 0) {
                throw new Exception();
            }
            todoWorkService = (ITodoWorkService) Container.getBean("todoWrokServiceImpl");
            commonService = (ICommonService) Container.getBean("commonServiceImpl");
            WorkEntity work = new WorkEntity();
            Boolean pdWork = true;
            // 事物数量减一
            work.setCount(-1);
            // 事务编号
            work.setWorkId(Constants.SELL17);
            // 事物接收人
            work.setUserId(pe.getUserId());
            // 执行事务更新
            pdWork = todoWorkService.addTodoWorks(work);
            if (!pdWork) {
                throw new Exception();
            }
            dao.commitTransaction();
        } catch (Exception e) {
            result = false;
            log.error("付款打印失败!", e);
        } finally {
            try {
                dao.endTransaction();
                log.debug("付款打印成功");
            } catch (Exception e) {
                log.error("付款打印事物回滚失败!", e);
            }
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.purchase.IBuyPaymentService#acceptBuyPayment
     * (java.util.Map)
     */
    @Override
    public Boolean acceptBuyPayment(PaymentEntity pe) {
        boolean result = true;
        try {
            dao.beginTransaction();
            if (pe.getStatus() == 11) {
                pe.setStatus(12);
                Integer pd = dao.update("buyPayment_sqlMap.updateConfirmBuyPayment", pe);
                if (pd == 0) {
                    throw new Exception();
                }
            } else {
                throw new Exception();
            }
            log.debug("付款单ID" + pe.getId());
            Integer remindFlag = (Integer) dao.queryForObject(
                    "buyPayment_sqlMap.selectBuyPaymentRemindFlag", pe.getId());
            log.debug("remindFlag:" + remindFlag);
            if (remindFlag == 1) {
                log.debug("减采购专员待办事务!");
                todoWorkService = (ITodoWorkService) Container
                        .getBean("todoWrokServiceImpl");
                commonService = (ICommonService) Container.getBean("commonServiceImpl");
                WorkEntity work = new WorkEntity();
                Boolean pdWork = true;
                // 事物数量减一
                work.setCount(-1);
                // 事务编号
                work.setWorkId(Constants.SELL18);
                // 事物接收人
                work.setUserId(pe.getUserId());
                // 执行事务更新
                pdWork = todoWorkService.addTodoWorks(work);
                if (!pdWork) {
                    throw new Exception();
                }
            }
            dao.commitTransaction();
        } catch (Exception e) {
            result = false;
            log.error("付款承兑失败!", e);
        } finally {
            try {
                dao.endTransaction();
                log.debug("付款承兑成功!");
            } catch (Exception e) {
                log.error("付款承兑事物回滚失败!", e);
            }
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.purchase.IBuyPaymentService#confirmBuyPayment
     * (cn.com.thtf.egov.cms.entity.PaymentEntity)
     */
    @Override
    public Boolean confirmBuyPayment(PaymentEntity pe, String[] inStockId,
            String[] productId, String[] appointMoney, String contractType) {
        boolean result = true;
        Integer pd = 0;
        try {
            dao.beginTransaction();
            log.debug("采购合同类型：" + contractType);
            if (Integer.parseInt(contractType) == 1) {
                for (int i = 0; i < productId.length; i++) {
                    // 查询公司税率
                    BigDecimal taxRate = (BigDecimal) dao.queryForObject(
                            "buyPayment_sqlMap.buyPaymentSelectTaxRate", inStockId[i]);
                    log.debug("税率:" + taxRate);
                    PaymentDetailEntity pde = new PaymentDetailEntity();
                    pde.setPaymentId(pe.getId());
                    pde.setProductId(Integer.parseInt(productId[i]));
                    pde.setMoney(new BigDecimal(appointMoney[i]));
                    pde.setInStockId(inStockId[i]);
                    log.debug("产品ID:" + productId[i] + "指定金额:" + appointMoney[i]
                            + "入库单ID:" + inStockId[i]);
                    // 更新付款明细表金额
                    pd = dao.update("buyPayment_sqlMap.updateConfirmBuyPaymentDetail",
                            pde);
                    if (pd == 0) {
                        throw new Exception();
                    }
                    // 查询该产品库存金额
                    BigDecimal productMoney = (BigDecimal) dao.queryForObject(
                            "buyPayment_sqlMap.pIdSelectProductMoney", productId[i]);
                    log.debug("原产品库存金额:" + productMoney);
                    // 查询该入库单下该产品入库数和入库单价
                    InStockDetailEntity isde = new InStockDetailEntity();
                    isde.setInStockId(inStockId[i]);
                    isde.setProductId(Integer.parseInt(productId[i]));
                    log.debug("产品ID:" + productId[i] + "入库单ID:" + inStockId[i]);
                    InStockDetailEntity insde = (InStockDetailEntity) dao.queryForObject(
                            "buyPayment_sqlMap.inStockDetailCountOrPrice", isde);
                    // 入库数*入库单价
                    BigDecimal instockCountMoney = (new BigDecimal(insde.getCount())
                            .multiply(insde.getPrice()));
                    // 除完税率的库存金额
                    instockCountMoney = instockCountMoney.divide(
                            taxRate.add(new BigDecimal(100)), 15,
                            BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
                    // 除完税率的付款金额
                    BigDecimal paymentMoney = new BigDecimal(appointMoney[i]).divide(
                            taxRate.add(new BigDecimal(100)), 15,
                            BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
                    log.debug("入库数*入库单价:" + instockCountMoney + "除完税率的库存金额:"
                            + instockCountMoney + "除完税率的付款金额:" + paymentMoney);

                    // 库存金额=原库存金额-入库单价*入库数/(1+税率)+付款金额/(1+税率)
                    productMoney = productMoney.subtract(instockCountMoney).add(
                            paymentMoney);
                    // productMoney = productMoney.subtract(
                    // insde.getPrice().multiply(new
                    // BigDecimal(insde.getCount()))).divide(taxRate.divide(
                    // new BigDecimal(100.00), 15,
                    // BigDecimal.ROUND_HALF_UP)).add(
                    // new BigDecimal(appointMoney[i]).divide(taxRate.divide(
                    // new BigDecimal(100.00), 15, BigDecimal.ROUND_HALF_UP),
                    // 15, BigDecimal.ROUND_HALF_UP));
                    log.debug("库存金额:" + productMoney);
                    // 更新产品库存金额
                    ProductEntity prodect = new ProductEntity();
                    prodect.setId(Integer.parseInt(productId[i]));
                    prodect.setMoney(productMoney);
                    pd = dao.update("buyPayment_sqlMap.idUpdateProductMoney", prodect);
                    if (pd == 0) {
                        throw new Exception();
                    }
                }
            }
            if (productId != null) {
                for (int i = 0; i < productId.length; i++) {
                    PaymentDetailEntity pde = new PaymentDetailEntity();
                    pde.setPaymentId(pe.getId());
                    pde.setMoney(new BigDecimal(appointMoney[i]));
                    pde.setInStockId(inStockId[i]);
                    pde.setProductId(Integer.parseInt(productId[i]));

                    pd = dao.update("buyPayment_sqlMap.updateConfirmBuyPaymentDetail",
                            pde);
                }
            }
            dao.delete("buyPayment_sqlMap.deleteBuyPaymentDetail", null);
            if (pe.getStatus() == 12) {
                pe.setStatus(13);
                pd = dao.update("buyPayment_sqlMap.updateConfirmBuyPayment", pe);
                if (pd == 0) {
                    throw new Exception();
                }
                // log.debug("付款单ID" + pe.getId());
                // Integer remindFlag = (Integer) dao.queryForObject(
                // "buyPayment_sqlMap.selectBuyPaymentRemindFlag", pe.getId());
                // log.debug("remindFlag:" + remindFlag);
                // if (remindFlag == 1) {
                // log.debug("减采购专员待办事务!");
                // todoWorkService = (ITodoWorkService) Container
                // .getBean("todoWrokServiceImpl");
                // commonService = (ICommonService) Container
                // .getBean("commonServiceImpl");
                // WorkEntity work = new WorkEntity();
                // Boolean pdWork = true;
                // // 事物数量减一
                // work.setCount(-1);
                // // 事务编号
                // work.setWorkId(Constants.SELL18);
                // // 事物接收人
                // work.setUserId(pe.getUserId());
                // // 执行事务更新
                // pdWork = todoWorkService.addTodoWorks(work);
                // if (!pdWork) {
                // throw new Exception();
                // }
                // }
            } else {
                throw new Exception();
            }
            dao.commitTransaction();
        } catch (Exception e) {
            result = false;
            log.error("付款确认失败!", e);
        } finally {
            try {
                dao.endTransaction();
                log.debug("付款确认成功!");
            } catch (Exception e) {
                log.error("付款确认事物回滚失败!", e);
            }
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.purchase.IBuyPaymentService#modifyBuyPayment
     * (cn.com.thtf.egov.cms.dto.BuyPaymentAddDto)
     */
    @Override
    public Boolean modifyBuyPayment(BuyPaymentAddDto buyP) {
        Boolean result = false;
        List<PaymentDetailEntity> list = new ArrayList<PaymentDetailEntity>();
        try {
            dao.beginTransaction();

            // 重新计算总金额
            BigDecimal totPrice = new BigDecimal(0);
            if (buyP.getAdvanceMoney() != null) {
                for (int i = 0; i < buyP.getAdvanceMoney().length; i++) {
                    totPrice = totPrice.add(new BigDecimal(buyP.getAdvanceMoney()[i]));
                }
            }
            if (buyP.getAppointMoney() != null) {
                for (int i = 0; i < buyP.getAppointMoney().length; i++) {
                    totPrice = totPrice.add(new BigDecimal(buyP.getAppointMoney()[i]));
                }
            }
            buyP.setMoney(totPrice.toString());

            // 更新付款单
            dao.update("buyPayment_sqlMap.modifyBuyPayment", buyP);
            list = getPaymentDetailE(buyP);
            // 批量更新明细
            // 删除付款明细
            dao.delete("buyPayment_sqlMap.deleltBuyPaymentDetail", buyP.getPaymentId());
            for (PaymentDetailEntity p : list) {
                // 插入付款单明细
                dao.insert("buyPayment_sqlMap.addBuyPaymentDetail", p);
            }
            // 新增待办事务
            if (StringUtils.equals(buyP.getBtnClick(), "1")) {
                todoWorkService = (ITodoWorkService) Container
                        .getBean("todoWrokServiceImpl");
                commonService = (ICommonService) Container.getBean("commonServiceImpl");
                WorkEntity work = new WorkEntity();
                Boolean pd = true;
                // 事物数量
                work.setCount(1);
                // 事务编号
                work.setWorkId(Constants.SELL16);
                WorkReceiverDto workReceiverDto = commonService.getUserIdByCondition(
                        Integer.parseInt(buyP.getProductTypeId()), null, null,
                        Constants.ROLE_PRODUCT_DIRECTOR);
                String userId = workReceiverDto
                        .getUserIdByRoleId(Constants.ROLE_PRODUCT_DIRECTOR);
                work.setUserId(userId);
                // 执行新增事务
                pd = todoWorkService.addTodoWorks(work);
                if (pd == false) {
                    throw new Exception();
                }
            }
            result = true;
            dao.commitTransaction();
        } catch (Exception e) {
            result = false;
            log.error("update  BuyPayment error!", e);
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.error("Close Transaction error!", e);
            }
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.purchase.IBuyPaymentService#appointBuyPayment
     * (cn.com.thtf.egov.cms.dto.BuyPaymentAddDto)
     */
    @Override
    public Boolean appointBuyPayment(BuyPaymentAddDto buyP) {
        boolean result = true;
        List<PaymentDetailEntity> list = new ArrayList<PaymentDetailEntity>();
        Integer pd = 0;
        try {
            dao.beginTransaction();
            // 更新付款单
            pd = dao.update("buyPayment_sqlMap.appointBuyPayment", buyP);
            if (pd == 0) {
                throw new Exception();
            }
            list = getPaymentDetailE(buyP);
            // 批量更新明细
            for (PaymentDetailEntity p : list) {
                // 插入付款单明细
                pd = dao.update("buyPayment_sqlMap.appointBuyPaymentDetail", p);
                if (pd == 0) {
                    throw new Exception();
                }
            }
            dao.commitTransaction();
        } catch (Exception e) {
            result = false;
            log.error("update  appointBuyPayment error!", e);
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.error("Close Transaction error!", e);
            }
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @seecn.com.thtf.egov.cms.iservice.purchase.IBuyPaymentService#
     * againAppointBuyPayment(cn.com.thtf.egov.cms.dto.BuyPaymentAddDto)
     */
    @SuppressWarnings("unchecked")
    @Override
    public Boolean againAppointBuyPayment(BuyPaymentAddDto buyP, String contractType) {
        Boolean result = true;
        Integer shu = 0;
        List<PaymentDetailEntity> list = new ArrayList<PaymentDetailEntity>();
        try {
            List<BuyPaymentBuyContractDto> listProduct = new ArrayList<BuyPaymentBuyContractDto>();
            listProduct = dao.queryForlist("buyPayment_sqlMap.viewBuyPaymentBuyProduct",
                    buyP.getPaymentId());
            dao.beginTransaction();
            // 更新付款单
            dao.update("buyPayment_sqlMap.againAppointBuyPayment", buyP);
            list = getPaymentDetailE(buyP);
            if (Integer.parseInt(contractType) == 1) {
                for (BuyPaymentBuyContractDto bpbcd : listProduct) {
                    // 查询公司税率
                    BigDecimal taxRate = (BigDecimal) dao.queryForObject(
                            "buyPayment_sqlMap.buyPaymentSelectTaxRate",
                            bpbcd.getInStockId());
                    log.debug("税率:" + taxRate);
                    // 查询该产品库存金额
                    BigDecimal productMoney = (BigDecimal) dao.queryForObject(
                            "buyPayment_sqlMap.pIdSelectProductMoney",
                            bpbcd.getProductId());
                    log.debug("原产品库存金额:" + productMoney);
                    // 查询该入库单下该产品入库数和入库单价
                    InStockDetailEntity isde = new InStockDetailEntity();
                    isde.setInStockId(bpbcd.getInStockId());
                    isde.setProductId(Integer.parseInt(bpbcd.getProductId()));
                    log.debug("产品ID:" + bpbcd.getProductId() + "入库单ID:"
                            + bpbcd.getInStockId());
                    InStockDetailEntity insde = (InStockDetailEntity) dao.queryForObject(
                            "buyPayment_sqlMap.inStockDetailCountOrPrice", isde);
                    // 入库数*入库单价
                    BigDecimal instockCountMoney = (new BigDecimal(insde.getCount())
                            .multiply(insde.getPrice()));
                    // 除完税率的库存金额
                    instockCountMoney = instockCountMoney.divide(
                            taxRate.add(new BigDecimal(100)), 15,
                            BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
                    // 除完税率的付款金额
                    BigDecimal paymentMoney = bpbcd
                            .getZdMoney()
                            .divide(taxRate.add(new BigDecimal(100)), 15,
                                    BigDecimal.ROUND_HALF_UP)
                            .multiply(new BigDecimal(100));
                    // 库存金额=原库存金额-入库单价*入库数/(1+税率)+付款金额/(1+税率)
                    productMoney = productMoney.add(instockCountMoney).subtract(
                            paymentMoney);
                    // // 库存金额计算
                    // productMoney = productMoney.add(
                    // insde.getPrice().multiply(new
                    // BigDecimal(insde.getCount())))
                    // .subtract(
                    // bpbcd.getZdMoney().divide(
                    // taxRate.divide(new BigDecimal(100.00), 15,
                    // BigDecimal.ROUND_HALF_UP), 15,
                    // BigDecimal.ROUND_HALF_UP));
                    // log.debug("库存金额:" + productMoney);
                    // 更新产品库存金额
                    ProductEntity prodect = new ProductEntity();
                    prodect.setId(Integer.parseInt(bpbcd.getProductId()));
                    prodect.setMoney(productMoney);
                    shu = dao.update("buyPayment_sqlMap.idUpdateProductMoney", prodect);
                    if (shu == 0) {
                        throw new Exception();
                    }
                }
                for (PaymentDetailEntity pde : list) {
                    // 查询公司税率
                    BigDecimal taxRate = (BigDecimal) dao.queryForObject(
                            "buyPayment_sqlMap.buyPaymentSelectTaxRate",
                            pde.getInStockId());
                    log.debug("税率:" + taxRate);
                    // 查询该产品库存金额
                    BigDecimal productMoney = (BigDecimal) dao.queryForObject(
                            "buyPayment_sqlMap.pIdSelectProductMoney", pde.getProductId()
                                    .toString());
                    log.debug("原产品库存金额:" + productMoney);
                    // 查询该入库单下该产品入库数和入库单价
                    InStockDetailEntity isde = new InStockDetailEntity();
                    isde.setInStockId(pde.getInStockId());
                    isde.setProductId(pde.getProductId());
                    log.debug("产品ID:" + pde.getProductId() + "入库单ID:"
                            + pde.getInStockId());
                    InStockDetailEntity insde = (InStockDetailEntity) dao.queryForObject(
                            "buyPayment_sqlMap.inStockDetailCountOrPrice", isde);
                    // 入库数*入库单价
                    BigDecimal instockCountMoney = (new BigDecimal(insde.getCount())
                            .multiply(insde.getPrice()));
                    // 除完税率的库存金额
                    instockCountMoney = instockCountMoney.divide(
                            taxRate.add(new BigDecimal(100)), 15,
                            BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
                    // 除完税率的付款金额
                    BigDecimal paymentMoney = pde
                            .getMoney()
                            .divide(taxRate.add(new BigDecimal(100)), 15,
                                    BigDecimal.ROUND_HALF_UP)
                            .multiply(new BigDecimal(100));
                    // 库存金额=原库存金额-入库单价*入库数/(1+税率)+付款金额/(1+税率)
                    productMoney = productMoney.subtract(instockCountMoney).add(
                            paymentMoney);
                    // productMoney = productMoney.subtract(
                    // insde.getPrice().multiply(new
                    // BigDecimal(insde.getCount()))).add(
                    // pde.getMoney().divide(
                    // taxRate.divide(new BigDecimal(100.00), 15,
                    // BigDecimal.ROUND_HALF_UP), 15,
                    // BigDecimal.ROUND_HALF_UP));
                    log.debug("库存金额:" + productMoney);
                    // 更新产品库存金额
                    ProductEntity prodect = new ProductEntity();
                    prodect.setId(pde.getProductId());
                    prodect.setMoney(productMoney);
                    shu = dao.update("buyPayment_sqlMap.idUpdateProductMoney", prodect);
                    if (shu == 0) {
                        throw new Exception();
                    }
                }
            }
            // 删除付款明细
            dao.delete("buyPayment_sqlMap.deleltBuyPaymentDetail", buyP.getPaymentId());
            for (PaymentDetailEntity p : list) {
                // 插入付款单明细
                dao.insert("buyPayment_sqlMap.addBuyPaymentDetail", p);
            }
            dao.commitTransaction();
        } catch (Exception e) {
            result = false;
            log.error("update  againAppointBuyPayment error!", e);
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.error("Close Transaction error!", e);
            }
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.purchase.IBuyPaymentService#deleteBuyPayment
     * (java.lang.String)
     */
    @Override
    public Boolean deleteBuyPayment(String id) {
        boolean result = true;
        try {
            dao.delete("buyPayment_sqlMap.deleteBuyPayment", id);
        } catch (Exception e) {
            result = false;
            log.error("删除付款单错误!", e);
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.purchase.IBuyPaymentService#buyPaymentzd
     * (java.lang.String)
     */
    public Integer buyPaymentzd(String id) {
        Integer result = 0;
        try {
            result = (Integer) dao.queryForObject("buyPayment_sqlMap.buyPaymentzhiding",
                    id);
        } catch (Exception e) {
            log.error("外单查询出错!", e);
        }
        return result;
    }

    public BigDecimal productTypeIdwzdMoney(java.util.Map<String, String> map) {
        BigDecimal result = new BigDecimal(0);
        try {
            result = (BigDecimal) dao.queryForObject(
                    "buyPayment_sqlMap.productTypeIdSelectwzdMoney", map);
        } catch (Exception e) {
            log.error("未指定金额查询出错!", e);
        }
        return result;
    }
}
