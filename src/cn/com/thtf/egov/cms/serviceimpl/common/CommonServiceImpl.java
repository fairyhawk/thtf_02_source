/**
 * ClassName  SendGoodsCreditLogicServiceImp
 *
 * History
 * Create User: lubo
 * Create Date: 2009-12-16
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.serviceimpl.common;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.NewIDao;
import cn.com.thtf.egov.cms.constant.Constants;
import cn.com.thtf.egov.cms.dto.ContractIdDto;
import cn.com.thtf.egov.cms.dto.CreditLogicQueryDto;
import cn.com.thtf.egov.cms.dto.CustomerCreditCommonDto;
import cn.com.thtf.egov.cms.dto.CustomerFundsDto;
import cn.com.thtf.egov.cms.dto.DateMaxIdDto;
import cn.com.thtf.egov.cms.dto.WorkReceiverDto;
import cn.com.thtf.egov.cms.entity.BusiLogEntity;
import cn.com.thtf.egov.cms.entity.ProductTypeEntity;
import cn.com.thtf.egov.cms.entity.UserAreaProductEntity;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.entity.UserProductEntity;
import cn.com.thtf.egov.cms.entity.UserStockroomProductEntity;
import cn.com.thtf.egov.cms.iservice.common.ICommonService;
import cn.com.thtf.egov.cms.util.Util;

/**
 * ProductDeptServiceImp
 * 
 * @author lubo
 * 
 */
public class CommonServiceImpl implements ICommonService {

    /** log */
    private static Logger log = LoggerFactory.getLogger(CommonServiceImpl.class);
    /** NewIDao */
    private NewIDao dao;
    /** SN_FORMAT */
    private static DecimalFormat SN_FORMAT = new DecimalFormat("000");

    /**
     * 获取客户信用
     * 
     * @param customerId
     * @param prodTypeId
     * @return
     */
    public String getCustomerCreditId(Integer customerId, Integer productTypeId) {
        CreditLogicQueryDto param = new CreditLogicQueryDto();
        param.setProductTypeId(productTypeId);
        param.setCustomerId(customerId);
        
        String result = null;
        try {
            result = (String) dao.queryForObject(
                    "common_sqlMap.getCustomerCreditId", param);
        } catch (Exception e) {
            log.error("获取客户信用错误", e);
        }
        return result;
    }

    /**
     * 新增操作记录
     * 
     * @param para
     * @return
     */
    public boolean addBusiLog(BusiLogEntity para) {
        boolean result = false;
        try {
            dao.insert("common.insertBusiLog", para);
            result = true;
        } catch (Exception e) {
            log.error("新增操作记录错误！", e);
        }
        return result;
    }

    /**
     * 获取产品销售可用数
     * 
     * @param prodId
     *            产品Id
     * @return 销售可用数
     */
    @Override
    public Integer getAvailProdCnt(String prodId) {
        Integer intCnt = 0;
        try {
            intCnt = (Integer) dao.queryForObject("common.selectProdFreeCount", prodId);
        } catch (Exception e) {
            log.error("获取产品销售可用数", e);
        }
        return intCnt;
    }

    /**
     * 获取客户信用
     * 
     * @param customerId
     *            客户Id
     * @param prodTypeId
     *            产品类型Id
     * @param customerCrdId
     *            客户信用类型Id：查看客户一条信用时填写，否则为null
     * @return CustomerCreditCommonDto
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<CustomerCreditCommonDto> getCustomerCredit(String customerId,
            String prodTypeId, String customerCrdId) {

        List<CustomerCreditCommonDto> list = null;

        Map map = new HashMap();
        map.put("customerId", customerId);
        map.put("prodTypeId", prodTypeId);

        if (!StringUtils.isBlank(customerCrdId)) {
            map.put("customerCrdId", customerCrdId);
        }
        try {
            list = dao.queryForlist("common.selectCustomerCreditById", map);
        } catch (Exception e) {
            log.error("获取客户信用失败", e);
        }
        return list;
    }

    public BigDecimal getSellContractResultMoney(String sellContractId, String appointType) {
        CreditLogicQueryDto para = new CreditLogicQueryDto();
        para.setSellContractId(sellContractId);
        para.setAppointType(appointType);

        CustomerFundsDto result = null;
        try {
            if (StringUtils.equals(Constants.RETURN_DETAIL_TODETAIL, appointType)) {
                result = (CustomerFundsDto) dao.queryForObject(
                        "common_sqlMap.queryCustomerReturnMoneyDetail", para);
            } else {
                result = (CustomerFundsDto) dao.queryForObject(
                        "common_sqlMap.queryCustomerReturnMoney", para);
            }
            return result.getReturnMoney();
        } catch (Exception e) {
            log.error("信用判断,取得回款错误", e);
            return null;
        }
    }

    @Override
    public BigDecimal getSellContractInRransit(String sellContractId, String appointType) {
        CreditLogicQueryDto para = new CreditLogicQueryDto();
        para.setSellContractId(sellContractId);
        para.setAppointType(appointType);

        CustomerFundsDto result = null;
        try {
            if (StringUtils.equals(Constants.RETURN_DETAIL_TODETAIL, appointType)) {
                result = (CustomerFundsDto) dao.queryForObject(
                        "common_sqlMap.queryCustomerInRransitMoneyDetail", para);
            } else {
                result = (CustomerFundsDto) dao.queryForObject(
                        "common_sqlMap.queryCustomerInRransitMoney", para);
            }

            return result.getInRransitMoney();
        } catch (Exception e) {
            log.error("信用判断,取得在途款错误", e);
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<CustomerFundsDto> expiredLogic(Integer customerId) {
        CreditLogicQueryDto para = new CreditLogicQueryDto();
        para.setCustomerId(customerId);

        List<CustomerFundsDto> result = null;
        try {
            result = dao.queryForlist("common_sqlMap.queryCustomerExpired", para);
        } catch (Exception e) {
            log.error("信用判断,判断客户是否存在超期欠款错误", e);
        }

        return result;
    }

    @Override
    public BigDecimal getReceivedPayments() {
        return null;
    }

    public NewIDao getDao() {
        return dao;
    }

    public void setDao(NewIDao dao) {
        this.dao = dao;
    }

    /**
     * 生成编号
     * 
     * 
     * @param prefix
     *            编号前缀
     * 
     * @param tableName
     *            表名
     * 
     * @return newSN 生成的编号
     */
    @Override
    public synchronized String getSerialNumber(String prefix, String table) {

        if (StringUtils.isBlank(prefix) || StringUtils.isBlank(table)) {
            log.error("获取编号失败：getSerialNumber()参数为空");
            return null;
        }

        DateMaxIdDto dto = null;
        if (prefix.equals("XS")) {// 销售合同
            dto = Constants.CONTRACT_MAX_ID_DTO.getSellContractIdDto();
        } else if (prefix.equals("TXS")) {// 销售退货合同
            dto = Constants.CONTRACT_MAX_ID_DTO.getSellBackContractIdDto();
        } else if (prefix.equals("TFH")) {// 销售退货单
            dto = Constants.CONTRACT_MAX_ID_DTO.getSendBackGoodsIdDto();
        } else if (prefix.equals("CG")) {// 采购合同
            dto = Constants.CONTRACT_MAX_ID_DTO.getBuyContractIdDto();
        } else if (prefix.equals("TCG")) {// 采购退货
            dto = Constants.CONTRACT_MAX_ID_DTO.getBuyBackContractIdDto();
        } else if (prefix.equals("FH")) {// 发货单
            dto = Constants.CONTRACT_MAX_ID_DTO.getSendGoodsIdDto();
        } else if (prefix.equals("RK")) {// 入库单
            dto = Constants.CONTRACT_MAX_ID_DTO.getInStockIdDto();
        } else if (prefix.equals("ZX")) {// 装箱单
            dto = Constants.CONTRACT_MAX_ID_DTO.getBoxIdDto();
        } else if (prefix.equals("HK")) {// 回款单
            dto = Constants.CONTRACT_MAX_ID_DTO.getMreturnIdDto();
        } else if (prefix.equals("FK")) {// 付款单
            dto = Constants.CONTRACT_MAX_ID_DTO.getPaymentIdDto();
        } else if (prefix.equals("KP")) {// 开票单
            dto = Constants.CONTRACT_MAX_ID_DTO.getMakeInvoiceIdDto();
        } else if (prefix.equals("SP")) {// 收票单
            dto = Constants.CONTRACT_MAX_ID_DTO.getReceiveInvocieIdDto();
        } else if (prefix.equals("TRK")) {// 采购返厂
            dto = Constants.CONTRACT_MAX_ID_DTO.getBuyBackGoodsIdDto();
        } else if (prefix.equals("THK")) {// 销售退款
            dto = Constants.CONTRACT_MAX_ID_DTO.getSellBackIdDto();
        } else if (prefix.equals("TFK")) {// 采购退款
            dto = Constants.CONTRACT_MAX_ID_DTO.getBuyBackIdDto();
        } else if (prefix.equals("YK")) {// 移库单
            dto = Constants.CONTRACT_MAX_ID_DTO.getMoveIdDto();
        } else if (prefix.equals("YP")) {// 借出单
            dto = Constants.CONTRACT_MAX_ID_DTO.getSampleOutIdDto();
        } else if (prefix.equals("TYP")) {// 归还单
            dto = Constants.CONTRACT_MAX_ID_DTO.getSampleInIdDto();
        }

        StringBuilder sn = new StringBuilder();
        sn.append(prefix.toUpperCase());

        try {
            Date todayDate = new Date();
            String today = Util.formatShortDate(todayDate);
            sn.append(today);
            if (StringUtils.equals(today, Util.formatShortDate(dto.getDate()))) {
                sn.append(SN_FORMAT.format(dto.getMaxId() + 1));
                dto.setMaxId(dto.getMaxId() + 1);
            } else {
                sn.append("001");
                dto.setDate(todayDate);
                dto.setMaxId(1);
            }

        } catch (Throwable e) {
            log.error("获取编号失败", e);
        }
        return sn.toString();
    }

    /**
     * 产品合同号生成
     * 
     * @param prefix
     *            编号前缀
     * @param prodTypeId
     *            产品分类Id
     * @param table
     *            表名
     * @return 生成的编号
     */
    @SuppressWarnings("unchecked")
    @Override
    public synchronized String getProdContractSN(String prefix, String prodTypeId,
            String table) {
        if (StringUtils.isBlank(prefix) || StringUtils.isBlank(table)
                || StringUtils.isBlank(prodTypeId)) {
            log.error("获取产品合同编号失败：getProdContractSN()参数为空");
            return null;
        }

        // 产品分类位数判断
        if (prodTypeId.length() > 2) {
            log.error("获取产品合同编号失败：产品分类ID位数大于两位");
            return null;
        }

        String prodNo = null;
        try {
            prodNo = getProdNoById(prodTypeId);// 获取产品分类编号
        } catch (Exception e) {
            log.error("根据产品分类ID获取产品分类编号失败", e);
        }
        if (StringUtils.isBlank(prodNo)) {
            return null;
        }

        String newSN = null; // 新生成的产品合同编号
        Integer maxSN = null;// 数据库中的最大编号
        String newPrefix = prefix.toUpperCase() + prodNo
                + Util.formatShortDate(new Date()).substring(0, 2);

        Map map = new HashMap();
        map.put("prodNo", prodNo);
        map.put("table", table);

        try {
            maxSN = (Integer) dao.queryForObject("common.getProdContractId", map);
            if (maxSN == null) {
                newSN = newPrefix + "00001";
            } else {
                maxSN += 1;
                if (maxSN.toString().length() == 1) {
                    newSN = newPrefix + "0000";
                }
                if (maxSN.toString().length() == 2) {
                    newSN = newPrefix + "000";
                }
                if (maxSN.toString().length() == 3) {
                    newSN = newPrefix + "00";
                }
                if (maxSN.toString().length() == 4) {
                    newSN = newPrefix + "0";
                }
                if (maxSN.toString().length() == 5) {
                    newSN = newPrefix;
                }
                newSN += maxSN;
            }
        } catch (Exception e) {
            log.error("获取产品合同编号失败", e);
        }
        return newSN;
    }

    /**
     * 产品退货合同号生成
     * 
     * @param prodContSN
     *            产品合同号
     * @param table
     *            表名
     * @return 生成的编号
     */
    @SuppressWarnings("unchecked")
    public synchronized String getProdContractBackSN(String prodContSN, String table) {
        if (StringUtils.isBlank(prodContSN) || StringUtils.isBlank(table)) {
            log.error("获取产品退货合同编号失败：getProdContractBackSN()参数为空");
            return null;
        }

        Integer maxSN = null;// 数据库中的最大编号
        prodContSN = "T" + prodContSN;

        Map map = new HashMap();
        map.put("table", table);
        map.put("prodContSN", prodContSN);
        try {
            maxSN = (Integer) dao.queryForObject("common.getProdContractBackId", map);
            if (maxSN == null) {
                return prodContSN += "01";
            }
            maxSN += 1;
            if (maxSN < 10) {
                prodContSN += "0";
            }
            prodContSN += maxSN;
        } catch (Exception e) {
            log.error("获取产品退货合同编号失败", e);
        }
        return prodContSN;
    }

    /**
     * 产品库存平均价
     * 
     * @param prodId
     *            产品Id
     * @param flag
     *            求平均价和采购价的标识 0:平均价; 1:采购价
     * @return 平均价
     */
    @Override
    public String getProdAvePrice(String prodId, String flag) {
        if (StringUtils.isBlank(prodId)) {
            log.error("获取产品库存平均价、预计采购价失败：getProdAvePrice()第一个参数为空");
            return null;
        }
        String price = null;
        try {
            if ("0".equals(flag)) {// 产品平均价
                price = (String) dao.queryForObject("common.getProdAvePrice", prodId);
            } else if ("1".equals(flag)) {// 产品预计采购价
                price = (String) dao.queryForObject("common.getProdExpectBuyPrice",
                        prodId);
            } else {
                log.error("获取产品库存平均价、预计采购价失败：getProdAvePrice()第二个参数值只能为0和1");
            }
        } catch (Exception e) {
            log.error("获取产品库存平均价、预计采购价失败", e);
        }
        return price;
    }

    /**
     * 锁表,锁定选中的产品记录
     * 
     * @param map
     *            产品Id
     * @return true or false
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean lockProductRecord(Map map) {
        if (map.isEmpty()) {
            log.error("锁表失败：产品ID为空");
            return false;
        }
        boolean isSuccess = false;
        try {
            int intCnt = (Integer) dao.queryForObject("common.lockProductRecord", map);
            if (intCnt > 0) {
                isSuccess = true;
            }
        } catch (Exception e) {
            isSuccess = false;
            log.error("锁表失败", e);
        }
        return isSuccess;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.common.ICommonService#getUserIdByCondition
     * (java.lang.String, java.lang.String, java.lang.String,
     * java.lang.String[])
     */
    @SuppressWarnings("unchecked")
    @Override
    public WorkReceiverDto getUserIdByCondition(Integer productTypeId,
            Integer userAreaId, Integer stockroomId, Integer... roleIdArr) {
        WorkReceiverDto result = null;

        try {
            if (productTypeId != null && userAreaId != null && stockroomId == null
                    && roleIdArr.length == 0) {
                // 销售助理
                UserAreaProductEntity para = new UserAreaProductEntity();
                para.setProductTypeId(productTypeId);
                para.setUserAreaId(userAreaId);

                String userId = (String) dao.queryForObject(
                        "common_sqlMap.selectSalesAssistant", para);
                if (userId != null) {
                    result = new WorkReceiverDto();
                    result.setUserId(userId);
                }
            } else if (productTypeId != null && stockroomId != null && userAreaId == null
                    && roleIdArr.length == 0) {
                // 库房管理员
                UserStockroomProductEntity para = new UserStockroomProductEntity();
                para.setProductTypeId(productTypeId);
                para.setStockroomId(stockroomId);

                String userId = (String) dao.queryForObject(
                        "common_sqlMap.selectTreasuryManager", para);
                if (userId != null) {
                    result = new WorkReceiverDto();
                    result.setUserId(userId);
                }
            } else {
                /* 用户ID数组 */
                String roleId = getRoleIdArr(roleIdArr);
                Map<Integer, String> roleIdMap = new HashMap<Integer, String>();
                List<UserEntity> userList = null;

                if (productTypeId == null && userAreaId == null && stockroomId == null
                        && roleIdArr.length > 0) {
                    // 唯一权限的人
                    userList = (List<UserEntity>) dao.queryForlist(
                            "common_sqlMap.selectOnlyRole", roleId);
                } else if (productTypeId != null && userAreaId != null
                        && roleIdArr.length == 0
                        && roleIdArr[0] == Constants.ROLE_REGIONAL_DIRECTOR) {
                    // 区域总监
                    UserAreaProductEntity para = new UserAreaProductEntity();
                    para.setProductTypeId(productTypeId);
                    para.setUserAreaId(userAreaId);
                    userList = (List<UserEntity>) dao.queryForlist(
                            "common_sqlMap.selectJudgeRoleByRegionalId", para);
                } else if (productTypeId != null && userAreaId != null
                        && roleIdArr.length != 0 && stockroomId == null) {
                    // 销售经理

                    UserAreaProductEntity para = new UserAreaProductEntity();
                    para.setProductTypeId(productTypeId);
                    para.setUserAreaId(userAreaId);
                    para.setUserId(roleId);

                    userList = (List<UserEntity>) dao.queryForlist(
                            "common_sqlMap.selectProductAndArea", para);

                } else {
                    // 产品总监、销售经理、销售总监、采购专员、采购主管
                    UserProductEntity para = new UserProductEntity();
                    para.setUserId(roleId);
                    para.setProductTypeId(productTypeId);

                    userList = (List<UserEntity>) dao.queryForlist(
                            "common_sqlMap.selectRoleByCondition", para);
                }

                for (UserEntity userEntity : userList) {
                    roleIdMap.put(userEntity.getRoleId(), userEntity.getId());
                }
                result = new WorkReceiverDto();
                result.setRoleIdMap(roleIdMap);
            }
        } catch (Exception e) {
            log.error("根据条件检索 接收事物人员ID", e);
        }

        return result;
    }

    /**
     * 封装权限ID
     * 
     * @param str
     * @return
     */
    private String getRoleIdArr(Integer[] num) {
        StringBuffer result = new StringBuffer();
        for (Integer roleId : num) {
            result.append(roleId).append(",");
        }

        if (result.length() > 0) {
            return result.substring(0, result.length() - 1);
        } else {
            return result.toString();
        }

    }

    /**
     * 查看所有产品分类
     * 
     * @param id
     *            产品分类ID，可以为null
     * @param userId
     *            当前用户ID，可以为null
     * @param roleId
     *            当前用户角色ID，可以为null;userId与roleId必须同时为null或者同时有值
     * @return ProductTypeEntity
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<ProductTypeEntity> getAllProductTypes(String id, String userId,
            String roleId) {
        List<ProductTypeEntity> list = null;
        HashMap map = new HashMap();
        map.put("prodId", id);
        map.put("userId", userId);
        if (roleId != null) {
            int intRoleId = Integer.parseInt(roleId);
            // 不受区域、产品分类限制
            if (intRoleId == Constants.ROLE_CREDIT_CHARGE
                    || intRoleId == Constants.ROLE_COMPLIANCE_OFFICER
                    || intRoleId == Constants.ROLE_AST_DIRECTOR_OF_OPERATIONS
                    || intRoleId == Constants.ROLE_DIRECTOR_OF_OPERATIONS
                    || intRoleId == Constants.ROLE_GENERAL_MANAGER) {
                roleId = "9999";
            }
        }
        map.put("roleId", roleId);

        log.info("查看所有产品分类");
        try {
            list = dao.queryForlist("common.selectAllProductTypes", map);
        } catch (Exception e) {
            log.error("查看所有产品分类失败" + e);
        }
        return list;
    }

    /**
     * 根据产品分类获取产品编号
     * 
     * @param id
     *            产品分类ID
     * @return prodNo
     */
    @Override
    public String getProdNoById(String id) {
        String prodNo = null;
        try {
            prodNo = (String) dao.queryForObject("common.getProdNoById", id);
        } catch (Exception e) {
            log.error("根据产品分类获取产品编号失败", e);
        }
        return prodNo;
    }

    /**
     * 根据产品ID更新产品库存金额
     * 
     * @param prodId
     *            产品ID
     * @param money
     *            库存金额
     * @return true false
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean modifyProdMoneyById(String prodId, String money) {
        boolean isSuccess = false;
        int intCnt = 0;
        HashMap map = new HashMap();
        map.put("prodId", prodId);
        map.put("money", money);

        try {
            intCnt = dao.update("common.updateProdMoneyById", map);
            if (intCnt > 0) {
                isSuccess = true;
            }
        } catch (Exception e) {
            log.error("根据产品ID更新产品库存金额失败", e);
            isSuccess = false;
        }
        return isSuccess;
    }

    /**
     * @see cn.com.thtf.egov.cms.iservice.common.ICommonService#getMaxIdDto()
     */
    @Override
    public ContractIdDto getMaxIdDto() {
        ContractIdDto dto = Constants.CONTRACT_MAX_ID_DTO;
        try {
            // 销售合同
            DateMaxIdDto idDto = (DateMaxIdDto) dao.queryForObject("common.getMaxId",
                    "sell_contract");
            log.debug("销售合同: 日期:{} - MaxId:{}", idDto.getDate(), idDto.getMaxId());
            dto.setSellContractIdDto(idDto);

            // 采购合同
            idDto = (DateMaxIdDto) dao.queryForObject("common.getMaxId", "buy_contract");
            log.debug("采购合同: 日期:{} - MaxId:{}", idDto.getDate(), idDto.getMaxId());
            dto.setBuyContractIdDto(idDto);

            // 发货单
            idDto = (DateMaxIdDto) dao.queryForObject("common.getMaxId", "send_goods");
            log.debug("发货单: 日期:{} - MaxId:{}", idDto.getDate(), idDto.getMaxId());
            dto.setSendGoodsIdDto(idDto);

            // 入库单
            idDto = (DateMaxIdDto) dao.queryForObject("common.getMaxId", "in_stock");
            log.debug("入库单: 日期:{} - MaxId:{}", idDto.getDate(), idDto.getMaxId());
            dto.setInStockIdDto(idDto);

            // 装箱单
            idDto = (DateMaxIdDto) dao.queryForObject("common.getMaxId", "box");
            log.debug("装箱单: 日期:{} - MaxId:{}", idDto.getDate(), idDto.getMaxId());
            dto.setBoxIdDto(idDto);

            // 回款单
            idDto = (DateMaxIdDto) dao.queryForObject("common.getMaxId", "mreturn");
            log.debug("回款单: 日期:{} - MaxId:{}", idDto.getDate(), idDto.getMaxId());
            dto.setMreturnIdDto(idDto);

            // 付款单
            idDto = (DateMaxIdDto) dao.queryForObject("common.getMaxId", "payment");
            log.debug("付款单: 日期:{} - MaxId:{}", idDto.getDate(), idDto.getMaxId());
            dto.setPaymentIdDto(idDto);

            // 开票
            idDto = (DateMaxIdDto) dao.queryForObject("common.getMaxId", "make_invoice");
            log.debug("开票: 日期:{} - MaxId:{}", idDto.getDate(), idDto.getMaxId());
            dto.setMakeInvoiceIdDto(idDto);

            // 收票
            idDto = (DateMaxIdDto) dao.queryForObject("common.getMaxId",
                    "receive_invoice");
            log.debug("收票: 日期:{} - MaxId:{}", idDto.getDate(), idDto.getMaxId());
            dto.setReceiveInvocieIdDto(idDto);

            // 销售退货合同
            idDto = (DateMaxIdDto) dao.queryForObject("common.getMaxId",
                    "sell_back_contract");
            log.debug("销售退货合同: 日期:{} - MaxId:{}", idDto.getDate(), idDto.getMaxId());
            dto.setSellBackContractIdDto(idDto);

            // 采购退货合同
            idDto = (DateMaxIdDto) dao.queryForObject("common.getMaxId",
                    "buy_back_contract");
            log.debug("采购退货合同: 日期:{} - MaxId:{}", idDto.getDate(), idDto.getMaxId());
            dto.setBuyBackContractIdDto(idDto);

            // 销售退货单
            idDto = (DateMaxIdDto) dao.queryForObject("common.getMaxId",
                    "sell_back_goods");
            log.debug("销售退货单: 日期:{} - MaxId:{}", idDto.getDate(), idDto.getMaxId());
            dto.setSendBackGoodsIdDto(idDto);

            // 采购返厂单
            idDto = (DateMaxIdDto) dao
                    .queryForObject("common.getMaxId", "buy_back_goods");
            log.debug("采购返厂单: 日期:{} - MaxId:{}", idDto.getDate(), idDto.getMaxId());
            dto.setBuyBackGoodsIdDto(idDto);

            // 销售退款单
            idDto = (DateMaxIdDto) dao.queryForObject("common.getMaxId", "sell_back");
            log.debug("销售退款单: 日期:{} - MaxId:{}", idDto.getDate(), idDto.getMaxId());
            dto.setSellBackIdDto(idDto);

            // 采购退款单
            idDto = (DateMaxIdDto) dao.queryForObject("common.getMaxId", "buy_back");
            log.debug("采购退款单: 日期:{} - MaxId:{}", idDto.getDate(), idDto.getMaxId());
            dto.setBuyBackIdDto(idDto);

            // 移库单
            idDto = (DateMaxIdDto) dao.queryForObject("common.getMaxId", "move");
            log.debug("移库单: 日期:{} - MaxId:{}", idDto.getDate(), idDto.getMaxId());
            dto.setMoveIdDto(idDto);

            // 借出单
            idDto = (DateMaxIdDto) dao.queryForObject("common.getMaxId", "sample_out");
            log.debug("借出单: 日期:{} - MaxId:{}", idDto.getDate(), idDto.getMaxId());
            dto.setSampleOutIdDto(idDto);

            // 归还单
            idDto = (DateMaxIdDto) dao.queryForObject("common.getMaxId", "sample_in");
            log.debug("归还单: 日期:{} - MaxId:{}", idDto.getDate(), idDto.getMaxId());
            dto.setSampleInIdDto(idDto);
        } catch (Exception e) {
            log.error("获取合同ID最大值失败：", e);
        }
        return dto;
    }

    /**
     * 根据合同Id,获取合同状态
     * 
     * @param table
     *            表名
     * @param id
     *            合同ID
     * @return 合同状态 -99表示记录已被删除
     */
    @Override
    public String getStatusById(String table, String id) {
        if (StringUtils.isBlank(table) || StringUtils.isBlank(id)) {
            log.info("根据合同Id,获取合同状态失败：参数不能为空");
            return null;
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("table", table);
        map.put("id", id);
        String status = null;
        try {
            status = (String) dao.queryForObject("common.getConstractStatusById", map);
            if (status == null) {// 记录不存在
                status = "-99";
            }
        } catch (Exception e) {
            log.error("根据合同Id,获取合同状态失败：", e);
            status = null;
        }
        return status;
    }
}
