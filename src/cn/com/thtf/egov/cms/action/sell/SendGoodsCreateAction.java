/**
 * ClassName  SendGoodsCreateAction
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-4-19
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.action.sell;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.NewBaseAction;
import cn.com.thtf.egov.cms.constant.Constants;
import cn.com.thtf.egov.cms.dto.CreditLogicQueryDto;
import cn.com.thtf.egov.cms.dto.CreditLogicResultDto;
import cn.com.thtf.egov.cms.dto.CustomerCreditCommonDto;
import cn.com.thtf.egov.cms.dto.CustomerFundsDto;
import cn.com.thtf.egov.cms.dto.MreturnDto;
import cn.com.thtf.egov.cms.dto.SalesContractInfoDto;
import cn.com.thtf.egov.cms.dto.SendGoodInfoDto;
import cn.com.thtf.egov.cms.dto.SendGoodsAddDto;
import cn.com.thtf.egov.cms.dto.SendgoodSauditPrepareDto;
import cn.com.thtf.egov.cms.dto.SendgoodViewSelectProductDto;
import cn.com.thtf.egov.cms.dto.SendgoodsProductSelectDto;
import cn.com.thtf.egov.cms.entity.SendGoodsDetailEntity;
import cn.com.thtf.egov.cms.entity.SendGoodsEntity;
import cn.com.thtf.egov.cms.entity.StockEntity;
import cn.com.thtf.egov.cms.entity.StockroomEntity;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.form.CreateReserveGoodsForm;
import cn.com.thtf.egov.cms.form.CreateSendGoodsForm;
import cn.com.thtf.egov.cms.iservice.common.ICommonService;
import cn.com.thtf.egov.cms.iservice.inventory.IStockroomService;
import cn.com.thtf.egov.cms.iservice.mreturn.IMReturnService;
import cn.com.thtf.egov.cms.iservice.sell.ISalesContractManagementService;
import cn.com.thtf.egov.cms.iservice.sell.ISendGoodsService;
import cn.com.thtf.egov.cms.util.Container;
import cn.com.thtf.egov.cms.util.Util;

/**
 * 新建发货、备货单
 * 
 * @author Lubo
 */

public class SendGoodsCreateAction extends NewBaseAction {

    /** log */
    private static Logger log = LoggerFactory.getLogger(SendGoodsCreateAction.class);
    /** ISendGoodsService */
    private ISendGoodsService sendGoodsService;
    /** ICommonService */
    private ICommonService commonService;
    /** ISalesContractManagementService */
    private ISalesContractManagementService salesContractService;
    /** IMReturnService */
    private IMReturnService mReturnService;
    /** IStockroomService */
    private IStockroomService stockroomService;

    /**
     * 新建发货单 --初始化!
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addCreateSendGoodsInit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("新建发货单 --初始化!");

        /* 根据合同ID 获取合同信息 和 信用信息 */
        String sellContractId = request.getParameter("sellContractId");
        if (StringUtils.isBlank(sellContractId)) {
            log.error("sellContractId为空");
        }

        /* 获取合同信息和信用信息 */
        SalesContractInfoDto salesContract = getSellContractCreditData(sellContractId);
        if (salesContract != null) {
            /* 检索所有库房 */
            IStockroomService stockroomService = (IStockroomService) getBean("stockroomServiceImp");
            List<StockroomEntity> stockroomList = stockroomService
                    .getTypicallyAndVirtual(salesContract.getProductTypeId());
            StringBuffer sb = new StringBuffer();
            for (StockroomEntity stockroomEntity : stockroomList) {
                sb.append(stockroomEntity.getId()).append(":")
                        .append(stockroomEntity.getType()).append(",");
            }
            request.setAttribute("stockroomTypeList", sb);

            /* 防止重复提交 */
            this.saveToken(request);

            request.setAttribute("sellContractId", sellContractId);
            request.setAttribute("stockroomList", stockroomList);
            request.setAttribute("salesContract", salesContract);
            return mapping.findForward("createSendGoods");
        } else {
            request.setAttribute("errorMsg", "新建发货单失败,获取信用错误！");
            return mapping.findForward("createSendGoodsFail");
        }

    }

    /**
     * 获取合同信息和信用信息
     * 
     * @param sellContractId
     * @return
     */
    private SalesContractInfoDto getSellContractCreditData(String sellContractId) {
        salesContractService = (ISalesContractManagementService) getBean("salesContractManagementServiceImp");

        /* 根据销售合同流水号查询对应合同的相关数据 */
        SalesContractInfoDto salesContract = salesContractService
                .getSalesContractInfoById(sellContractId);
        /* 获取帐期、回款预收金额、在途预收金额 */
        commonService = (ICommonService) getBean("commonServiceImpl");

        /* 回款 指定到合同 */
        BigDecimal prepayMoney = commonService.getSellContractResultMoney(sellContractId,
                Constants.RETURN_DETAIL_TOCONTRACT);
        /* 在途 指定到合同 */
        BigDecimal transitMoney = commonService.getSellContractInRransit(sellContractId,
                Constants.RETURN_DETAIL_TOCONTRACT);

        if (prepayMoney != null) {
            salesContract.setPrepayMoney(prepayMoney.toString());
        }
        if (transitMoney != null) {
            salesContract.setTransitMoney(transitMoney.toString());
        }

        String creditId = salesContract.getCustomerCreditId().toString();
        /* 信用信息 */
        if (salesContract.getCreditTypeId() != 5) {
            String id = commonService.getCustomerCreditId(salesContract.getCustomerId(),
                    salesContract.getProductTypeId());
            if (id == null) {
                log.error("合同:{},获取信用信息错误(非项目信用)", sellContractId);
            } else {
                creditId = id;
            }
        }

        List<CustomerCreditCommonDto> creditResult = commonService.getCustomerCredit(
                salesContract.getCustomerId().toString(), salesContract
                        .getProductTypeId().toString(), creditId);

        if (creditResult.size() == 0) {
            log.error("合同:{},获取信用信息错误", sellContractId);
            salesContract = null;
        } else {
            salesContract.setArterm(creditResult.get(0).getPaymentTerm());
            salesContract.setCreditTypeName(creditResult.get(0).getCreditTypeName());
            salesContract.setProjectName(creditResult.get(0).getProjectName());
            salesContract.setClimit(creditResult.get(0).getAssignedLimit());
            salesContract.setFreeLimit(creditResult.get(0).getFreeLimit());
            salesContract.setCustomerCreditId(Integer.parseInt(creditId));
        }

        return salesContract;
    }

    /**
     * 封装新增参数
     * 
     * @param addForm
     * @return
     */
    private SendGoodsAddDto getAddPara(CreateSendGoodsForm addForm) {
        SendGoodsAddDto addPara = addForm.getAddPara();

        /* 获取合同信息和信用信息 */
        SalesContractInfoDto salesContract = getSellContractCreditData(addPara
                .getSellContractId());
        /* 从合同取 */
        addPara.setPaymentWay(salesContract.getPaymentWay());
        addPara.setCustomerCreditId(salesContract.getCustomerCreditId());
        addPara.setProductTypeId(salesContract.getProductTypeId());
        addPara.setCustomerId(salesContract.getCustomerId());
        addPara.setCustomerName(salesContract.getCustomerName());
        if (salesContract.getCashMoney() == null) {
            salesContract.setCashMoney(new BigDecimal("0"));
        }
        addPara.setCashMoney(salesContract.getCashMoney().toString());
        /* 从信用取 */
        addPara.setProjectName(salesContract.getProjectName());

        if (salesContract.getCreditTypeId() != null) {
            addPara.setCreditTypeId(salesContract.getCreditTypeId().toString());
        }
        if (salesContract.getArterm() != null) {
            addPara.setArterm(salesContract.getArterm().toString());
        }
        if (salesContract.getClimit() != null) {
            addPara.setClimit(salesContract.getClimit().toString());
        }
        if (salesContract.getFreeLimit() != null) {
            addPara.setFree(salesContract.getFreeLimit().toString());
        }

        addPara.setPrepayMoney(salesContract.getPrepayMoney());
        addPara.setTransitMoney(salesContract.getTransitMoney());

        // 从页面取
        addPara.setId(addForm.getAddPara().getId());
        addPara.setStockroomId(addForm.getAddPara().getStockroomId());
        addPara.setProductIdArr(addForm.getAddPara().getProductIdArr());
        addPara.setCountArr(addForm.getAddPara().getCountArr());
        addPara.setMoneyArr(addForm.getAddPara().getMoneyArr());

        addPara.setCustomerAddressId(addForm.getAddPara().getCustomerAddressId());
        addPara.setTakeNumber(addForm.getAddPara().getTakeNumber());
        addPara.setTakeName(addForm.getAddPara().getTakeName());

        addPara.setRequestDate(addForm.getAddPara().getRequestDate());

        addPara.setText(addForm.getAddPara().getText());

        // 原信用判断成功后

        /* 获取发货单编号 */
        if (StringUtils.isBlank(addPara.getId())) {
            String id = commonService.getSerialNumber("FH", "SEND_GOODS");
            addPara.setId(id);
        } else {
            addPara.setPageType(true);
        }

        /* 如果点击的是保存 那么状态是1 date不更新 */
        if (StringUtils.equals(addPara.getSubmitType(), "1")) {
            addPara.setStatus("1");
        } else {
            addPara.setStatus("2");
            addPara.setDate(Util.getDate());
        }

        addPara.setSendGoodsType("1");

        return addPara;
    }

    /**
     * 新建发货单
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addCreateSendGoods(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        // log.debug("新建发货单");

        /* 防止重复提交 */
        if (isTokenValid(request)) {
            resetToken(request);
        } else {
            return mapping.findForward("submitRepeat");
        }

        /* 获取新增参数 */
        CreateSendGoodsForm addForm = (CreateSendGoodsForm) form;
        SendGoodsAddDto addPara = getAddPara(addForm);
        UserEntity userInfo = getLoginUserInfo(request);
        if (addPara.isPageType()) {
            log.info("User:{},修改发货单:{}", userInfo.getId(), addPara.getId());
        } else {
            log.info("User:{},新建发货单:{}", userInfo.getId(), addPara.getId());
        }
        log.info("发货单:{},合同ID:{}", addPara.getId(), addPara.getSellContractId());

        sendGoodsService = (ISendGoodsService) getBean("sendGoodsServiceImpl");
        mReturnService = (IMReturnService) Container.getBean("mReturnServiceImp");

        /* forward */
        String forward = "createFail";
        /* 错误信息 */
        StringBuffer resultMsg = new StringBuffer("新增发货单失败:\\n");

        /* 用户信息 */
        addPara.setUserId(userInfo.getId());
        addPara.setUserAreaId(userInfo.getUserAreaId().toString());
        addPara.setUserName(userInfo.getName());
        addPara.setDatetime(Util.getDate());
        try {
            /* 保存发货单 */
            boolean result = sendGoodsService.seveSendgoods(addPara);
            if (result) {

                /* 判断是提交还是保存 */
                if (StringUtils.equals("2", addPara.getSubmitType())) {

                    /* 检索在回款记录,包括时间戳 */
                    List<MreturnDto> returnDetailList = mReturnService
                            .getMReturnDetail(addPara.getSellContractId());
                    List<MreturnDto> returnDetailInList = mReturnService
                            .getMreturnDetailInRransit(addPara.getSellContractId());

                    /* 检索在库存记录,包括时间戳 */
                    stockroomService = (IStockroomService) Container
                            .getBean("stockroomServiceImp");
                    List<StockEntity> stockList = stockroomService
                            .getStockByStockroomId(addPara.getStockroomId());

                    /* 检索库存数 */
                    boolean checkStockNumresult = sendGoodsService.checkStockNum(
                            addPara.getStockroomId(), addPara.getSellContractId(),
                            addPara.getProductIdArr(), addPara.getCountArr());
                    if (checkStockNumresult) {
                        // if (checkStockNumresult) {
                        /* 信用判断 */
                        CreditLogicQueryDto ceditPara = new CreditLogicQueryDto();
                        ceditPara.setCustomerId(addPara.getCustomerId());
                        ceditPara.setProductTypeId(addPara.getProductTypeId());
                        ceditPara.setPaymentWay(addPara.getPaymentWay());
                        ceditPara.setSellContractId(addPara.getSellContractId());
                        ceditPara.setSendGoodsMoney(addPara.getSendGoodsMoney());
                        ceditPara.setCustomerCreditId(addPara.getCustomerCreditId());
                        ceditPara.setCashMoney(addPara.getCashMoney());

                        CreditLogicResultDto ceditresult = sendGoodsService
                                .creditLogic(ceditPara);

                        if (ceditresult.isAllow()) {
                            // addPara.setCreditLogicResultDto(ceditresult);
                            addPara.setCustomerCredit(ceditresult
                                    .getCustomerCreditCommonDto());

                            // addPara.setMreturnList(mReturnList);
                            addPara.setReturnDetailList(returnDetailList);
                            addPara.setReturnDetailInList(returnDetailInList);
                            addPara.setStockList(stockList);

                            /* 更新DB */
                            boolean addResult = sendGoodsService.addSendgoods(addPara);

                            if (addResult) {
                                forward = "createSuccess";
                            } else {
                                // log.error("新增发货单失败");
                                resultMsg.append("录入DB错误");
                            }
                        } else {
                            if (ceditresult.getProductList() != null
                                    && ceditresult.getProductList().size() != 0) {
                                resultMsg.append("存在超期欠款:\\n");
                                for (CustomerFundsDto productEntity : ceditresult
                                        .getProductList()) {
                                    resultMsg.append("产品分类名称:");
                                    resultMsg.append(productEntity.getProductTypeName());
                                    resultMsg.append("\\n");
                                }
                                // log.error("新增发货单失败");
                            } else {
                                // log.error("新增发货单失败");
                                resultMsg.append("信用错误:\\n");
                                resultMsg.append(ceditresult.getErrorMsg());
                            }

                        }
                    } else {
                        resultMsg.append("库存不足");
                        // log.error("库存不足");
                    }
                } else {
                    forward = "createSuccess";
                }
            } else {
                resultMsg.append("保存发货单失败");
            }
        } catch (Exception e) {
            log.error("新建发货单错误:", e);
            resultMsg.append("录入DB错误");
        }

        if (StringUtils.equals("createFail", forward)) {
            // 如果是提交,那么需要把状态变回未提交
            if (StringUtils.equals(addPara.getSubmitType(), "2")) {
                addPara.setStatus("1");
                sendGoodsService.modifySendgoodStatus(addPara);
            }

            request.setAttribute("errorMsg", resultMsg);
            StringBuffer failUrl = new StringBuffer();
            failUrl.append("/modifySendGoods.do?id=");
            failUrl.append(addPara.getId());

            if (addPara.isPageType()) {
                log.info("修改发货单:{},失败:{}", addPara.getId(), resultMsg);
            } else {
                log.info("新建发货单:{},失败:{}", addPara.getId(), resultMsg);
            }

            return new ActionForward(failUrl.toString());
        } else {

            if (addPara.isPageType()) {
                log.info("User:{},修改发货单,成功:{}", userInfo.getId(), addPara.getId());
            } else {
                log.info("User:{},新建发货单,成功:{}", userInfo.getId(), addPara.getId());
            }

            return mapping.findForward(forward);
        }

    }

    /**
     * 新建备货单--初始
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addCreateReserveGoodsInit(ActionMapping mapping,
            ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        log.debug("新建备货单--初始化!");
        /* 根据合同ID 获取合同信息 和 信用信息 */
        String sellContractId = request.getParameter("sellContractId");
        if (StringUtils.isBlank(sellContractId)) {
            log.error("sellContractId为空");
        }
        /* 根据销售合同流水号查询对应合同的相关数据 */
        SalesContractInfoDto salesContract = getSellContractCreditData(sellContractId);
        /* 检索所有库房 */
        IStockroomService stockroomService = (IStockroomService) getBean("stockroomServiceImp");
        List<StockroomEntity> stockroomList = stockroomService.getNotPrepareStockroom(
                salesContract.getId(), salesContract.getProductTypeId());

        /* 防止重复提交 */
        this.saveToken(request);

        request.setAttribute("sellContractId", sellContractId);
        request.setAttribute("stockroomList", stockroomList);
        request.setAttribute("salesContract", salesContract);
        return mapping.findForward("createReserveGoods");
    }

    /**
     * 新建备货单 修改备货单
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public ActionForward addCreateReserveGoods(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("新建备货单");
        UserEntity userInfo = getLoginUserInfo(request);
        String forward = Constants.SUCCESS;

        // /* 防止重复提交 */
        // this.saveToken(request);
        /* 防止重复提交 */
        if (isTokenValid(request)) {
            resetToken(request);
        } else {
            return mapping.findForward(Constants.SUCCESS);
        }

        Map<String, String> saveMap = saveCreateReserveGoods(mapping, form, request,
                response);
        forward = saveMap.get("forward");
        if (!StringUtils.equals(forward, Constants.SUCCESS)) {
            return mapping.findForward(forward);
        }
        /* 失败跳转 */
        String fail = "updatefail";
        /* 检索form */
        String attributeVal = "创建失败！";
        CreateReserveGoodsForm reserveGoodsForm = (CreateReserveGoodsForm) form;
        /* 如果是保存直接跳转 */
        if (StringUtils.equals(reserveGoodsForm.getSubmitType(), "1")) {
            return mapping.findForward(forward);
        }
        String[] productIds = reserveGoodsForm.getProductId();
        String[] reserveGoodsnum = reserveGoodsForm.getReserveGoodsNum();
        String[] prices = reserveGoodsForm.getPrice();
        /*
         * 增改开关 ---修改：true ---新增：false
         */
        Boolean mark = true;
        /* 获取发货单编号 */
        String id = reserveGoodsForm.getSendGoodId();
        if (StringUtils.isEmpty(id)) {
            mark = false;
            fail = "addfail";
            attributeVal = "创建失败！";
            id = saveMap.get("id");
        }
        /* 新建失败之后修改Action所需要的id */
        request.setAttribute("sendId", id);
        /* 根据合同ID 获取合同信息 和 信用信息 */
        String sellContractId = reserveGoodsForm.getSellContractId();
        SalesContractInfoDto salesContract = getSellContractCreditData(sellContractId);
        UserEntity userE = getLoginUserInfo(request);
        /* 判断库存是否满足备货 */
        Boolean checkStockNumresult = false;
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Timestamp> timeMap = new HashMap<String, Timestamp>();
        sendGoodsService = (ISendGoodsService) this.getBean("sendGoodsServiceImpl");
        if (reserveGoodsForm.getStockroomSelect() != null) {
            map = sendGoodsService.checkStockNumReserve(reserveGoodsForm
                    .getStockroomSelect().toString(), reserveGoodsForm
                    .getSellContractId(), productIds, reserveGoodsnum);
            checkStockNumresult = (Boolean) map.get("checkResult");
            timeMap = (Map<String, Timestamp>) map.get("timeMap");
        } else {
            log.error("库房ID为空！");
            request.setAttribute("sellContractId", sellContractId);
            request.setAttribute("err", attributeVal);
            mapping.findForward(fail);
        }
        /*
         * 如果满足库存 创建备货单
         */
        if (checkStockNumresult) {
            SendGoodsEntity sendGoodE = this.setSendGoodEntity(salesContract);
            log.debug("生成发货单ID：" + id);
            sendGoodE.setId(id);
            sendGoodE.setSellContractId(sellContractId);
            sendGoodE.setSendGoodsType(0);
            sendGoodE.setRequestDate(reserveGoodsForm.getWillSendGoodsDate());
            sendGoodE.setMoney(reserveGoodsForm.getReserveGoodsMoneyE());
            sendGoodE.setStockroomId(reserveGoodsForm.getStockroomSelect());
            sendGoodE.setUserId(userE.getId());
            sendGoodE.setUserName(userE.getName());
            sendGoodE.setUserAreaId(userE.getUserAreaId());
            sendGoodE.setText(reserveGoodsForm.getReserveText());
            sendGoodE.setDatetime(Util.getDate());
            // lxs添加区域
            sendGoodE.setUserAreaId(userInfo.getUserAreaId());
            log.debug("备货单添加所属区域:" + sendGoodE.getUserAreaId());
            /* 如果点击的是保存 那么状态是1 date不更新 */
            if (StringUtils.equals(reserveGoodsForm.getSubmitType(), "1")) {
                sendGoodE.setStatus(1);
                log.debug("保存发货单！");
            } else {
                sendGoodE.setStatus(6);
                sendGoodE.setDate(Util.getDate());
                log.debug("提交发货单！");
            }
            /* 创建单明细数据集 */
            List<SendGoodsDetailEntity> sendGoodsDetailList = new ArrayList<SendGoodsDetailEntity>();
            BigDecimal sumMoney = new BigDecimal(0);
            for (int i = 0; i < productIds.length; i++) {
                SendGoodsDetailEntity sendGoodDetailE = new SendGoodsDetailEntity();
                sendGoodDetailE.setSendGoodsId(id);
                if (StringUtils.isNotEmpty(prices[i])) {
                    sendGoodDetailE.setPrice(new BigDecimal(prices[i]));
                }
                sendGoodDetailE.setProductId(Integer.parseInt(productIds[i]));
                sendGoodDetailE.setCount(Integer.parseInt(reserveGoodsnum[i]));
                sendGoodsDetailList.add(sendGoodDetailE);

                sumMoney = sumMoney.add(new BigDecimal(prices[i])
                        .multiply(new BigDecimal(Integer.parseInt(reserveGoodsnum[i]))));
                log.debug("备货金额：" + sumMoney);
            }
            if (sumMoney.compareTo(sendGoodE.getMoney()) == 0) {
                log.debug("备货金额相等！");
                sendGoodE.setMoney(sumMoney);
                Boolean result = false;
                // 执行修改
                if (mark) {
                    attributeVal = "修改失败！";
                }
                result = sendGoodsService.modifyReservegoods(sendGoodE,
                        sendGoodsDetailList, timeMap);
                // 执行新增
                // else {
                // result = sendGoodsService.addReservegoods(sendGoodE,
                // sendGoodsDetailList,
                // timeMap);
                // }
                // 监控SQL执行结果
                if (!result) {
                    request.setAttribute("err", attributeVal);
                    if (mark) {
                        // request.setAttribute("id", id);
                    } else {
                        request.setAttribute("sellContractId", sellContractId);
                    }
                    forward = fail;
                } else {
                    request.setAttribute("err", attributeVal);
                    if (!mark) {
                        request.setAttribute("sellContractId", sellContractId);
                    }
                }
            } else {
                log.error("备货金额计算不相等！");
                request.setAttribute("err", attributeVal);
                request.setAttribute("sellContractId", sellContractId);
                forward = fail;
            }
        } else {
            log.error("库存数不足！");
            request.setAttribute("err", attributeVal);
            request.setAttribute("sellContractId", sellContractId);
            forward = fail;
        }
        return mapping.findForward(forward);
    }

    /**
     * 选择产品小页面
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getProductByStockroomId(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("根据产品分类和库房ID");
        String stockroomId = request.getParameter("stockroomId");
        String sellContractId = request.getParameter("sellContractId");

        log.debug("库房编号:" + stockroomId);
        log.debug("销售合同号:" + sellContractId);

        List<SendgoodsProductSelectDto> productList = null;
        if (StringUtils.isNotBlank(stockroomId) && StringUtils.isNotBlank(sellContractId)) {
            sendGoodsService = (ISendGoodsService) getBean("sendGoodsServiceImpl");
            productList = sendGoodsService.getProductListBySrIdAndScId(stockroomId,
                    sellContractId);
        } else {
            request.setAttribute("errorMsg", "传入参数错误");
        }

        request.setAttribute("productList", productList);
        return mapping.findForward("productListView");
    }

    /**
     * 选择产品小页面（备货）
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getReserveProductByStockroomId(ActionMapping mapping,
            ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        log.debug("根据产品分类和库房ID");
        String stockroomId = request.getParameter("stockroomId");
        String sellContractId = request.getParameter("sellContractId");

        log.debug("库房编号:" + stockroomId);
        log.debug("销售合同号:" + sellContractId);

        List<SendgoodsProductSelectDto> productList = null;
        if (StringUtils.isNotBlank(stockroomId) && StringUtils.isNotBlank(sellContractId)) {
            sendGoodsService = (ISendGoodsService) getBean("sendGoodsServiceImpl");
            productList = sendGoodsService.getReserveProductListBySrIdAndScId(
                    stockroomId, sellContractId);
        } else {
            request.setAttribute("errorMsg", "传入参数错误");
        }

        request.setAttribute("productList", productList);
        return mapping.findForward("productListView");
    }

    /**
     * 创建备货单部分数据（销售合同数据）
     * 
     * @param salesContract
     * @return
     */
    public SendGoodsEntity setSendGoodEntity(SalesContractInfoDto salesContract) {
        SendGoodsEntity sendGoodsE = new SendGoodsEntity();
        sendGoodsE.setProductTypeId(salesContract.getProductTypeId());
        sendGoodsE.setCustomerId(salesContract.getCustomerId());
        sendGoodsE.setCustomerName(salesContract.getCustomerName());
        sendGoodsE.setCreditTypeId(salesContract.getCreditTypeId());
        sendGoodsE.setProjectName(salesContract.getProjectName());
        sendGoodsE.setArterm(salesContract.getArterm());
        sendGoodsE.setClimit(salesContract.getClimit());
        sendGoodsE.setFree(salesContract.getFreeLimit());

        if (StringUtils.isEmpty(salesContract.getPrepayMoney())) {
            sendGoodsE.setPrepayMoney(new BigDecimal(0));
        } else {
            sendGoodsE.setPrepayMoney(new BigDecimal(salesContract.getPrepayMoney()));
        }
        if (StringUtils.isEmpty(salesContract.getTransitMoney())) {
            sendGoodsE.setTransitMoney(new BigDecimal(0));
        } else {
            sendGoodsE.setTransitMoney(new BigDecimal(salesContract.getTransitMoney()));
        }
        return sendGoodsE;
    }

    /**
     * 备货单修改
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward modifyReserveGoods(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("备货单修改初始化！");
        String sendid = request.getParameter("id");
        if (StringUtils.isEmpty(sendid)) {
            sendid = (String) request.getAttribute("sendId");
        }
        ISendGoodsService sendGoodsService = (ISendGoodsService) this
                .getBean("sendGoodsServiceImpl");
        SendGoodInfoDto sge = sendGoodsService.getReservegoodsView(sendid);
        /* 根据销售合同流水号查询对应合同的相关数据 */
        SalesContractInfoDto salesContract = getSellContractCreditData(sge.getSellid());
        sge.setPaymentWay(salesContract.getPaymentWay());
        sge.setCashMoney(salesContract.getCashMoney());
        sge.setBatchWay(salesContract.getBatchWay());
        sge.setBatchMaxMoney(salesContract.getBatchMaxMoney());
        sge.setCreditname(salesContract.getCreditTypeName());
        sge.setSendProjectName(salesContract.getProjectName());
        sge.setArterm(salesContract.getArterm());
        sge.setClimit(salesContract.getClimit());
        sge.setFree(salesContract.getFreeLimit());
        if (StringUtils.isEmpty(salesContract.getPrepayMoney())) {
            salesContract.setPrepayMoney("0");
        }
        ;
        if (StringUtils.isEmpty(salesContract.getTransitMoney())) {
            salesContract.setTransitMoney("0");
        }
        sge.setSendPrepayMoney(new BigDecimal(salesContract.getPrepayMoney()));
        sge.setSendTransitMoney(new BigDecimal(salesContract.getTransitMoney()));
        List<SendgoodSauditPrepareDto> list = sendGoodsService
                .selectSauditPrepareView(sge);
        BigDecimal bhzmoney = new BigDecimal(0);
        try {
            for (SendgoodSauditPrepareDto sspd : list) {
                if (sspd.getBfmoney() == null) {
                    sspd.setBfmoney(new BigDecimal(0));
                }
                bhzmoney = bhzmoney.add(sspd.getBfmoney());
            }
        } catch (Exception e) {
            log.error("备货总额出错!", e);
        }
        Util util = new Util();
        Integer[] str = null;
        if (StringUtils.isEmpty(sge.getSendProMajIdea())) {
            log.debug("评审意见为空！");
        } else {
            str = util.splitIdea(sge.getSendProMajIdea());
        }
        request.setAttribute("ProMajIder", str);
        Integer[] str1 = null;
        if (StringUtils.isEmpty(sge.getSendAreaMajIdea())) {
            log.debug("评审意见为空！");
        } else {
            str1 = util.splitIdea(sge.getSendAreaMajIdea());
        }
        request.setAttribute("AreaMajId", str1);

        /* 防止重复提交 */
        this.saveToken(request);

        request.setAttribute("bhzmoney", bhzmoney);
        request.setAttribute("sge", sge);
        request.setAttribute("list", list);
        return mapping.findForward(Constants.SUCCESS);
    }

    /**
     * 发货单修改
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward modifySendGoods(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        // log.debug("发货单修改初始化！");

        /* 根据发货单ID */
        String sendGoodsId = request.getParameter("id");
        // String errorMsg = request.getParameter("errorMsg");

        sendGoodsService = (ISendGoodsService) this.getBean("sendGoodsServiceImpl");
        SendGoodInfoDto sendGoodInfo = sendGoodsService.getSendGoodsView(sendGoodsId);

        List<SendgoodViewSelectProductDto> productList = sendGoodsService
                .sendGoodsViewProduct(sendGoodInfo);

        /* 检索所有库房 */
        IStockroomService stockroomService = (IStockroomService) getBean("stockroomServiceImp");
        List<StockroomEntity> stockroomList = stockroomService
                .getTypicallyAndVirtual(sendGoodInfo.getSendProductTypeId());
        StringBuffer sb = new StringBuffer();
        for (StockroomEntity stockroomEntity : stockroomList) {
            sb.append(stockroomEntity.getId()).append(":")
                    .append(stockroomEntity.getType()).append(",");
        }
        request.setAttribute("stockroomTypeList", sb);

        /* 获取帐期、回款预收金额、在途预收金额 */
        commonService = (ICommonService) getBean("commonServiceImpl");

        /* 回款 指定到合同 */
        BigDecimal prepayMoney = commonService.getSellContractResultMoney(
                sendGoodInfo.getSendSellContractId(), Constants.RETURN_DETAIL_TOCONTRACT);
        /* 在途 指定到合同 */
        BigDecimal transitMoney = commonService.getSellContractInRransit(
                sendGoodInfo.getSendSellContractId(), Constants.RETURN_DETAIL_TOCONTRACT);

        if (prepayMoney != null) {
            sendGoodInfo.setSendPrepayMoney(prepayMoney);
        }
        if (transitMoney != null) {
            sendGoodInfo.setSendTransitMoney(transitMoney);
        }

        String creditId = sendGoodInfo.getCustomerCreditId().toString();
        /* 信用信息 */
        if (sendGoodInfo.getSendCreditTypeId() != 5) {
            String id = commonService.getCustomerCreditId(sendGoodInfo.getCustomerId(),
                    sendGoodInfo.getProductTypeId());
            if (id == null) {
                log.error("发货单:{},获取信用信息错误(非项目信用)", sendGoodsId);
            } else {
                creditId = id;
            }
        }

        /* 信用信息 */
        List<CustomerCreditCommonDto> creditResult = commonService.getCustomerCredit(
                sendGoodInfo.getCustomerId().toString(), sendGoodInfo.getProductTypeId()
                        .toString(), creditId);

        if (creditResult.size() == 0) {
            log.error("发货单:{},获取信用信息错误", sendGoodsId);
            sendGoodInfo = null;
        } else {
            sendGoodInfo.setCreditname(creditResult.get(0).getCreditTypeName());
            sendGoodInfo.setSendProjectName(creditResult.get(0).getProjectName());
            sendGoodInfo.setArterm(creditResult.get(0).getPaymentTerm());
            sendGoodInfo.setClimit(creditResult.get(0).getAssignedLimit());
            sendGoodInfo.setFree(creditResult.get(0).getFreeLimit());
        }

        if (sendGoodInfo != null) {

            /* 防止重复提交 */
            this.saveToken(request);

            // request.setAttribute("errorMsg", errorMsg);

            request.setAttribute("isUpdate", "true");
            request.setAttribute("sendGoodInfo", sendGoodInfo);
            request.setAttribute("productList", productList);
            request.setAttribute("productListSize", productList.size());
            request.setAttribute("stockroomList", stockroomList);
            return mapping.findForward(Constants.SUCCESS);

        } else {
            request.setAttribute("errorMsg", "修改发货单失败,获取信用错误！");
            return mapping.findForward("fail");
        }
    }

    /**
     * 备货单保存
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public Map<String, String> saveCreateReserveGoods(ActionMapping mapping,
            ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        log.debug("新建备货单");
        UserEntity userInfo = getLoginUserInfo(request);
        log.info("User:{},新建备货单", userInfo.getId());
        String forward = Constants.SUCCESS;
        /* 失败跳转 */
        String fail = "updatesavefail";
        /* 检索form */
        String attributeVal = "创建失败！";
        CreateReserveGoodsForm reserveGoodsForm = (CreateReserveGoodsForm) form;
        String[] productIds = reserveGoodsForm.getProductId();
        String[] reserveGoodsnum = reserveGoodsForm.getReserveGoodsNum();
        String[] prices = reserveGoodsForm.getPrice();
        Map<String, String> returnMap = new HashMap<String, String>();
        /*
         * 增改开关 ---修改：true ---新增：false
         */
        Boolean mark = true;
        /* 获取发货单编号 */
        String id = reserveGoodsForm.getSendGoodId();
        if (StringUtils.isEmpty(id)) {
            mark = false;
            fail = "addsavefail";

            attributeVal = "创建失败！";
            commonService = (ICommonService) getBean("commonServiceImpl");
            id = commonService.getSerialNumber("FH", "SEND_GOODS");
        }
        /* 根据合同ID 获取合同信息 和 信用信息 */
        String sellContractId = reserveGoodsForm.getSellContractId();
        SalesContractInfoDto salesContract = getSellContractCreditData(sellContractId);
        UserEntity userE = getLoginUserInfo(request);
        /* 判断库存是否满足备货 */
        Boolean checkStockNumresult = false;
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Timestamp> timeMap = new HashMap<String, Timestamp>();
        sendGoodsService = (ISendGoodsService) this.getBean("sendGoodsServiceImpl");
        if (reserveGoodsForm.getStockroomSelect() != null) {
            map = sendGoodsService.checkStockNumReserve(reserveGoodsForm
                    .getStockroomSelect().toString(), reserveGoodsForm
                    .getSellContractId(), productIds, reserveGoodsnum);
            checkStockNumresult = (Boolean) map.get("checkResult");
            timeMap = (Map<String, Timestamp>) map.get("timeMap");
        } else {
            log.error("库房ID为空！");
            request.setAttribute("sellContractId", sellContractId);
            request.setAttribute("err", attributeVal);
            log.info("User:{},新建备货单 失败", userInfo.getId());
            forward = fail;
            returnMap.put("forward", forward);
            returnMap.put("id", id);
            return returnMap;
        }
        /*
         * 如果满足库存 创建备货单
         */
        if (checkStockNumresult) {
            SendGoodsEntity sendGoodE = this.setSendGoodEntity(salesContract);
            log.debug("生成发货单ID：" + id);
            sendGoodE.setId(id);
            sendGoodE.setSellContractId(sellContractId);
            sendGoodE.setSendGoodsType(0);
            sendGoodE.setRequestDate(reserveGoodsForm.getWillSendGoodsDate());
            sendGoodE.setMoney(reserveGoodsForm.getReserveGoodsMoneyE());
            sendGoodE.setStockroomId(reserveGoodsForm.getStockroomSelect());
            sendGoodE.setUserId(userE.getId());
            sendGoodE.setUserName(userE.getName());
            sendGoodE.setUserAreaId(userE.getUserAreaId());
            sendGoodE.setText(reserveGoodsForm.getReserveText());
            sendGoodE.setDatetime(Util.getDate());
            sendGoodE.setExtendExceedDays(Constants.EXTEND_EXCEED_DAYS);
            sendGoodE.setStatus(1);
            // lxs添加区域
            sendGoodE.setUserAreaId(userInfo.getUserAreaId());
            log.debug("备货单修改所属区域" + sendGoodE.getUserAreaId());
            /* 创建单明细数据集 */
            List<SendGoodsDetailEntity> sendGoodsDetailList = new ArrayList<SendGoodsDetailEntity>();
            for (int i = 0; i < productIds.length; i++) {
                SendGoodsDetailEntity sendGoodDetailE = new SendGoodsDetailEntity();
                sendGoodDetailE.setSendGoodsId(id);
                if (StringUtils.isNotEmpty(prices[0])) {
                    sendGoodDetailE.setPrice(new BigDecimal(prices[0]));
                }
                sendGoodDetailE.setProductId(Integer.parseInt(productIds[i]));
                sendGoodDetailE.setCount(Integer.parseInt(reserveGoodsnum[i]));
                sendGoodsDetailList.add(sendGoodDetailE);
                // log.debug(productIds[i] + "__and__" + reserveGoodsnum[i]
                // + "__and__" + prices[0]);
            }
            Boolean result = false;
            // 执行修改保存
            if (mark) {
                attributeVal = "修改失败！";
                result = sendGoodsService.modifyReservegoods(sendGoodE,
                        sendGoodsDetailList, timeMap);
                if (result == false) {
                    log.info("User:{},修改备货单失败", userInfo.getId());
                }
            }
            // 执行新增保存
            else {
                result = sendGoodsService.addReservegoods(sendGoodE, sendGoodsDetailList,
                        timeMap);
            }
            // 监控SQL执行结果
            if (!result) {
                request.setAttribute("err", attributeVal);
                log.info("User:{},新建备货单 失败", userInfo.getId());
                if (mark) {
                    // request.setAttribute("id", id);
                } else {
                    request.setAttribute("sellContractId", sellContractId);
                }
                forward = fail;
                returnMap.put("forward", forward);
                returnMap.put("id", id);
                return returnMap;
            }
        } else {
            log.error("库存数不足！");
            request.setAttribute("err", attributeVal);
            log.info("User:{},新建备货单 失败", userInfo.getId());
            request.setAttribute("sellContractId", sellContractId);
            forward = fail;
            returnMap.put("forward", forward);
            returnMap.put("id", id);
            return returnMap;
        }

        returnMap.put("forward", forward);
        returnMap.put("id", id);
        log.info("User:{},新建备货单 成功", userInfo.getId());
        return returnMap;
    }
}
