/**
 * ClassName  BuyContractServiceImp
 *
 * History
 * Create User: ChenHuajiang
 * Create Date: 2010-6-1
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.serviceimpl.purchase;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.BaseService;
import cn.com.thtf.egov.cms.application.NewIDao;
import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.constant.Constants;
import cn.com.thtf.egov.cms.dto.BuyContractDetailDto;
import cn.com.thtf.egov.cms.dto.BuyContractOfAddDto;
import cn.com.thtf.egov.cms.dto.BuyContractOfReceiveInfoDto;
import cn.com.thtf.egov.cms.dto.BuyContractPreviewDto;
import cn.com.thtf.egov.cms.dto.BuyContractProductInfoDto;
import cn.com.thtf.egov.cms.dto.BuyContractReceivingInfoDto;
import cn.com.thtf.egov.cms.dto.BuyContractReviewDto;
import cn.com.thtf.egov.cms.dto.BuyContractSelectDto;
import cn.com.thtf.egov.cms.dto.ReceiveDetailDto;
import cn.com.thtf.egov.cms.entity.SupplierLinkmanEntity;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.entity.WorkEntity;
import cn.com.thtf.egov.cms.form.BuyContractSearchForm;
import cn.com.thtf.egov.cms.form.ProductSearchForm;
import cn.com.thtf.egov.cms.iservice.purchase.IBuyContractService;
import cn.com.thtf.egov.cms.iservice.work.ITodoWorkService;
import cn.com.thtf.egov.cms.util.Container;

/**
 * 采购合同
 * 
 * @author ChenHuajiang
 */

public class BuyContractServiceImpl extends BaseService implements IBuyContractService {
    /** log */
    private static Logger log = LoggerFactory.getLogger(BuyContractServiceImpl.class);
    /** NewIDao */
    private NewIDao dao;

    public NewIDao getDao() {
        return dao;
    }

    public void setDao(NewIDao dao) {
        this.dao = dao;
    }

    /* 采购合同列表 */
    @SuppressWarnings("unchecked")
    @Override
    public List getBuyContracts(BuyContractSearchForm param, NewPage page) {
        log.info("获取采购合同列表数据");
        List list = null;
        try {
            list =  dao.queryForlist("buyContractLists.selectBuyContracts",param);
            //list = queryRecords(dao, "buyContractLists.selectBuyContracts", param, page);
            int count = dao.getCount("buyContractLists.recordCount",param);
            page.setTotalResultSize(count);
            int totalPageSize = (page.getTotalResultSize() - 1)
                    / page.getPageSize() + 1;
            page.setTotalPageSize(totalPageSize);
        } catch (Exception e) {
            log.error("获取采购合同列表数据失败" + e);
        }
        return list;
    }

    /* 供应商信息 */
    @SuppressWarnings("unchecked")
    @Override
    public List getSuppliers(String name, NewPage page) {
        log.info("获取供应商信息");
        HashMap map = new HashMap();
        map.put("supplierName", name);
        List list = null;
        try {
            list = queryRecords(dao, "suppliers.selectAllSuppliers", map, page);
        } catch (Exception e) {
            log.error("获取供应商信息失败" + e);
        }
        return list;
    }

    /* 供应商-联系人 */
    @SuppressWarnings("unchecked")
    @Override
    public List getSupplierLinkmanBySupplierId(String supplierId) {
        log.info("获取供应商联系人");
        HashMap map = new HashMap();
        map.put("supplierId", supplierId);
        List list = null;
        try {
            list = dao.queryForlist("linkman.selectLinkmansBySupplierId", map);
        } catch (Exception e) {
            log.error("获取供应商联系人失败" + e);
        }
        return list;
    }

    /* 联系人信息 */
    @SuppressWarnings("unchecked")
    @Override
    public SupplierLinkmanEntity getSupplierLinkmanById(String id) {
        log.info("获取联系人信息");
        HashMap map = new HashMap();
        map.put("id", id);
        SupplierLinkmanEntity entity = null;
        try {
            entity = (SupplierLinkmanEntity) dao.queryForObject(
                    "linkman.selectLinkmanById", map);
        } catch (Exception e) {
            log.error("获取联系人信息失败" + e);
        }
        return entity;
    }

    /* 产品分类及部门 */
    @SuppressWarnings("unchecked")
    @Override
    public List getProdTypeDeptList(String userId) {
        log.info("获取产品分类及部门");
        HashMap map = new HashMap();
        map.put("userId", userId);
        List list = null;
        try {
            list = dao.queryForlist("productType.selectProductTypeByUserId", map);
        } catch (Exception e) {
            log.error("获取产品分类及部门失败" + e);
        }
        return list;
    }

    /* 根据条件检索产品信息 */
    @SuppressWarnings("unchecked")
    @Override
    public List getProdInfoByCondition(ProductSearchForm param, NewPage page) {
        log.info("根据条件检索产品信息");
        List list = null;
        try {
            list = queryRecords(dao, "productSelected.selectProductByCondition", param,
                    page);
        } catch (Exception e) {
            log.error("根据条件检索产品信息失败" + e);
        }
        return list;
    }

    /* 根据产品分类ID检索产品系列信息 */
    @SuppressWarnings("unchecked")
    @Override
    public List getProdSeriesByProdTypeId(String prodTypeId) {
        log.info("根据产品分类ID检索产品系列信息");
        HashMap map = new HashMap();
        map.put("prodTypeId", prodTypeId);
        List list = null;
        try {
            list = dao.queryForlist("prodSerie.selectProdSeriesByProdTypeId", map);
        } catch (Exception e) {
            log.error("根据产品分类ID检索产品系列信息失败" + e);
        }
        return list;
    }

    /* 库房收货地址 */
    @SuppressWarnings("unchecked")
    @Override
    public List getStockroomAddressList(NewPage page) {
        log.info("获取库房收货地址信息");
        List list = null;
        try {
            list = queryRecords(dao, "stock.selectAllStockrooms", null, page);
        } catch (Exception e) {
            log.error("获取库房收货地址信息失败" + e);
        }
        return list;
    }

    /* 客户收获地址 */
    @SuppressWarnings("unchecked")
    @Override
    public List getCustomerAddressList(String customerName, NewPage page) {
        log.info("获取客户收获地址信息");
        HashMap map = new HashMap();
        map.put("customerName", customerName);
        List list = null;
        try {
            list = queryRecords(dao, "customerAddress.selectAllCustomers", map, page);
        } catch (Exception e) {
            log.error("获取客户收获地址信息失败" + e);
        }
        return list;
    }

    /* 公司收获地址 */
    @SuppressWarnings("unchecked")
    @Override
    public List getCompanyAddressList() {
        log.info("获取公司收获地址");
        List list = null;
        try {
            list = dao.queryForlist("companyAddress.selectAllCompanies", null);
        } catch (Exception e) {
            log.error("获取公司收获地址失败" + e);
        }
        return list;
    }

    /* 收货地址添加 */
    @SuppressWarnings("unchecked")
    @Override
    public List getReceiveAddress(String buyContractId) {
        log.info("获取收货地址信息");
        HashMap map = new HashMap();
        map.put("buyContractId", buyContractId);
        List list = null;
        try {
            list = dao.queryForlist("receive.selectRerceiveAddress", map);
        } catch (Exception e) {
            log.error("获取收货地址信息失败" + e);
        }
        return list;
    }

    /**
     * 查看采购合同信息
     * 
     * @author HanHaiyun
     * @param Id
     *            采购合同id
     * @return 采购合同信息
     */
    @Override
    public BuyContractSelectDto getBuyContractById(String Id) {
        log.info("查看采购合同信息【开始】-service");
        BuyContractSelectDto buyContractSelectDto = null;
        try {
            buyContractSelectDto = (BuyContractSelectDto) dao.queryForObject(
                    "customerAddress.selectBuyContractDetail", Id);
            log.info("查看采购合同信息【结束】-service");
        } catch (Exception e) {
            log.error("查看采购合同信息【失败】-service" + e);
        }
        return buyContractSelectDto;
    }

    /**
     * 查看采购合同收货信息
     * 
     * @author HanHaiyun
     * @param Id
     *            采购合同id
     * @return 采购合同收货信息
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<BuyContractReceivingInfoDto> getBuyContractReceivingInfoById(String Id) {
        log.info("查看采购合同收货信息【开始】-service");
        List<BuyContractReceivingInfoDto> bcridList = null;
        try {
            bcridList = dao.queryForlist(
                    "customerAddress.selectBuyContractReceivingInfo", Id);
            log.info("查看采购合同收货信息【结束】-service");
        } catch (Exception e) {
            log.error("查看采购合同收货信息【失败】-service" + e);
        }
        return bcridList;
    }

    /**
     * 查看采购合同产品信息
     * 
     * @author HanHaiyun
     * @param Id
     *            采购合同id
     * @return 采购合同产品信息
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<BuyContractProductInfoDto> getBuyContractProductInfoById(String Id) {
        log.info("查看采购合同产品信息【开始】-service");
        List<BuyContractProductInfoDto> bcpidList = null;
        try {
            bcpidList = dao.queryForlist("customerAddress.selectBuyContractProducts", Id);
            log.info("查看采购合同产品信息【结束】-service");
        } catch (Exception e) {
            log.error("查看采购合同产品信息失败【失败】-service" + e);
        }
        return bcpidList;
    }

    /**
     * 采购合同预览
     * 
     * @author HanHaiyun
     * @param Id
     *            采购合同id
     * @return 预览信息
     */
    @Override
    public BuyContractPreviewDto getBuyContractPreviewByContractId(String Id) {
        log.info("采购合同预览查询【开始】-service");
        BuyContractPreviewDto buyContractPreviewDto = null;
        try {
            buyContractPreviewDto = (BuyContractPreviewDto) dao.queryForObject(
                    "customerAddress.selectBuyContractPreview", Id);
            log.info("采购合同预览查询【结束】-service");
        } catch (Exception e) {
            log.error("采购合同预览查询【失败】-service" + e);
        }
        return buyContractPreviewDto;
    }

    /**
     * 采购合同评审
     * 
     * @author HanHaiyun
     * @param buyContractReviewDto
     *            合同内容 DTO userEntity 当前用户 status 状态(此状态只有0[通过]和1[不通过]两种结果，
     *            而并非合同的状态)
     * @return 是否评审成功
     */
    @Override
    public boolean updateReview(BuyContractReviewDto buyContractReviewDto,
            UserEntity userEntity, Integer status, UserEntity buySpecialist) {
        log.info("采购合同评审【开始】-service");
        try {
            dao.beginTransaction();// 开始事务
            if (userEntity.getRoleId() == Constants.ROLE_COMPLIANCE_OFFICER) {// 用户身份法务专员
                buyContractReviewDto.setStatus(status == 1 ? 4 : 3);// 状态等于1表示通过
                // 得到当前用户信息
                buyContractReviewDto.setLegalId(userEntity.getId());
                buyContractReviewDto.setLegalName(userEntity.getName());
                // 事务修改
                if (status == 1) {
                    this.addTodoWorks(1, buyContractReviewDto.getBuyManId(),
                            Constants.SELL13);
                }
                // 将自己的事物减少一个
                this
                        .addTodoWorks(-1, buyContractReviewDto.getLegalId(),
                                Constants.SELL13);
            } else if (userEntity.getRoleId() == Constants.ROLE_PROCUREMENT_OFFICER) {// 用户身份为为采购主管
                // 如果状态为1(通过)，并且为运营总监助理 那么将合同状态修改为6,否则为8，如果不通过则为5
                buyContractReviewDto
                        .setStatus(status == 1 ? (buyContractReviewDto.getOpeMajRoleId() == Constants.ROLE_AST_DIRECTOR_OF_OPERATIONS ? 6
                                : 8)
                                : 5);
                buyContractReviewDto.setBuyManId(userEntity.getId());
                buyContractReviewDto.setBuyManName(userEntity.getName());
                // 事务修改
                // 判断采购主管是否为通过 通过则为运营总监添加事物
                if (status == 1) {
                    this.addTodoWorks(1, buyContractReviewDto.getOpeMajId(),
                            Constants.SELL13);
                }
                // 将自己的事物减少一个
                this.addTodoWorks(-1, buyContractReviewDto.getBuyManId(),
                        Constants.SELL13);
            } else if (userEntity.getRoleId() == Constants.ROLE_DIRECTOR_OF_OPERATIONS
                    || userEntity.getRoleId() == Constants.ROLE_AST_DIRECTOR_OF_OPERATIONS) {// 用户身份为运营总监或运营总监助理
                buyContractReviewDto
                        .setStatus(status == 1 ? 10
                                : (userEntity.getRoleId() == Constants.ROLE_AST_DIRECTOR_OF_OPERATIONS ? 7
                                        : 9));
                buyContractReviewDto.setOpeMajId(userEntity.getId());
                buyContractReviewDto.setOpeMajName(userEntity.getName());
                // 事务修改
                if (status == 1) {

                    this.addTodoWorks(1, buySpecialist.getId(), Constants.SELL14);
                }
                // 将自己的事物减少一个
                this.addTodoWorks(-1, buyContractReviewDto.getOpeMajId(),
                        Constants.SELL13);
            }
            dao.update("buyContract_sqlMap.review", buyContractReviewDto);
            dao.commitTransaction();// 提交事务
            log.info("采购合同评审【成功】-service");
        } catch (Exception e) {
            log.error("采购合同评审【失败】-service  " + e);
            return false;
        } finally {
            try {
                dao.endTransaction();
                log.info("采购合同评审【结束】-service");
            } catch (Exception e) {
                log.error("采购合同评审关闭事务【失败】-service  " + e);
            }
        }
        return true;
    }

    /**
     * 根据用户Id得到该用户的信息
     * 
     * @author HanHaiyun
     * @param id
     *            用户Id
     */
    @Override
    public UserEntity getUser(String id) {
        UserEntity userEntity = null;
        try {
            userEntity = (UserEntity) dao.queryForObject(
                    "buyContract_sqlMap.selectUserRole", id);
        } catch (Exception e) {
            log.error("得到用户失败");
        }
        return userEntity;
    }

    @Override
    public UserEntity getBuySpecialist(String contractId) {
        UserEntity buySpecialist = null;
        try {
            buySpecialist = (UserEntity) dao.queryForObject(
                    "buyContract_sqlMap.selectBuyContractUserProduct", contractId);
        } catch (Exception e) {
            log.error("采购合同评审获取用户【失败】-service",e);
        }
        return buySpecialist;
    }

    /**
     * 添加事务
     * 
     * @author HanHaiyun
     * @param count
     *            事务个数
     * @param userId
     *            用户Id
     * @param workId
     *            事务Id
     * @return 是否添加成功
     */
    public boolean addTodoWorks(Integer count, String userId, Integer workId) {
        ITodoWorkService todoWork = (ITodoWorkService) Container
                .getBean("todoWrokServiceImpl");
        WorkEntity work = null;
        work = new WorkEntity();
        work.setCount(count);
        work.setUserId(userId);
        work.setWorkId(workId);
        return todoWork.addTodoWorks(work);
    }

    public int addBuyContractOfTransact(BuyContractOfAddDto buyContractOfAddDto,
            String JsonDate) {
        int issuccess = 0;
        try {
            /** 事物开始 */
            dao.beginTransaction();
            org.json.JSONObject json = new JSONObject(JsonDate);
            int sum = json.getInt("resultSum");
            BigDecimal buySum=new BigDecimal(0);
            for (int i = 0; i < sum; i++) {
                JSONObject rows = json.getJSONArray("rows").getJSONObject(i);
                String buyCount = rows.getString("buyCount");
                String buyPrice = rows.getString("buyMoney");
                /** 计算总和 */
                buySum = buySum.add(new BigDecimal(buyPrice).multiply(new BigDecimal(buyCount)));
            }
            if(buyContractOfAddDto.getMoney()!=null && !buyContractOfAddDto.getMoney().equals(String.valueOf(buySum))){
                log.debug("采购合同号为{0}，js:"+buyContractOfAddDto.getMoney()+",BigDecimal:"+buySum+"", buyContractOfAddDto.getId());
                }
            buyContractOfAddDto.setMoney(String.valueOf(buySum));
            /** 插入采购合同 */
            dao.insert("buyContract_sqlMap.addBuyContract", buyContractOfAddDto);
            dao.startBatch();
            /** 插入采购合同明细 */
            for (int i = 0; i < sum; i++) {
                JSONObject rows = json.getJSONArray("rows").getJSONObject(i);
                String buyCount = rows.getString("buyCount");
                String buyPrice = rows.getString("buyMoney");
                String productId = rows.getString("productId");// 产品编号
                /** 计算总和 */
                BuyContractDetailDto buyContractDetail = new BuyContractDetailDto();
                buyContractDetail.setCount(buyCount);
                buyContractDetail.setProductId(productId);
                buyContractDetail.setBuyContractId(buyContractOfAddDto.getId());
                buyContractDetail.setPrice(buyPrice);
                dao.insert("buyContract_sqlMap.addBuyContractDetail", buyContractDetail);
            }
            dao.executeBatch();
            dao.commitTransaction();
            issuccess = 1;
        } catch (Exception e) {
            issuccess = 0;
            try {
                dao.endTransaction();
            } catch (Exception e1) {
                log.error("添加采购合同失败！", e1);
            }
            log.error("添加采购合同失败！", e);
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.error("添加采购合同失败！", e);
            }
        }
        return issuccess;
    }

    public int addReceiveOfBuyContract(String jsonData) {
        int issuccess = 0;
        try {
            /** 事物开始 */
            dao.beginTransaction();
            dao.startBatch();
            /** 插入地址明细 */
            org.json.JSONObject json = new JSONObject(jsonData);

            int sum = json.getInt("resultSum");
            String buyContractId = json.getString("buyContractId");
            String companyType = json.getString("companyType");
            String companyId = json.getString("companyId");

            String addressId = json.getString("addressId");
            ReceiveDetailDto receiveDetailDto = new ReceiveDetailDto();
            receiveDetailDto.setBuyContractId(buyContractId);
            receiveDetailDto.setCompanyId(companyId);
            receiveDetailDto.setCompanyType(companyType);
            receiveDetailDto.setAddressId(addressId);
            Object selectReceiveOfBuyContractCount = dao.queryForObject(
                    "buyContract_sqlMap.selectReceiveOfBuyContract", receiveDetailDto);
            if (!"0".equals(selectReceiveOfBuyContractCount.toString())) {
                log.error("插入地址明细失败！存在此用户");
                return 0;
            }
            for (int i = 0; i < sum; i++) {
                JSONObject rows = json.getJSONArray("rows").getJSONObject(i);
                int selectCount = rows.getInt("selectCount");
                String productId = rows.getString("productId");
                receiveDetailDto.setBuyCount(selectCount);
                receiveDetailDto.setProdId(productId);
                dao
                        .insert("buyContract_sqlMap.addReceiveOfBuyContract",
                                receiveDetailDto);
            }
            dao.executeBatch();
            dao.commitTransaction();
            issuccess = 1;
        } catch (Exception e) {
            issuccess = 0;
            try {
                dao.endTransaction();
            } catch (Exception e1) {
                log.error("插入地址明细失败！", e1);
            }
            log.error("插入地址明细失败！", e);
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.error("插入地址明细失败！", e);
            }
        }
        return issuccess;
    }

    public int deleteReceiveOfBuyContract(String jsonData) {
        int issuccess = 0;
        try {
            /** 删除收获地址 */
            org.json.JSONObject json = new JSONObject(jsonData);
            String buyContractId = json.getString("buyContractId");
            String companyType = json.getString("companyType");
            String addressId = json.getString("addressId");
            String companyId = json.getString("companyId");
            ReceiveDetailDto receiveDetailDto = new ReceiveDetailDto();
            receiveDetailDto.setBuyContractId(buyContractId);
            receiveDetailDto.setAddressId(addressId);
            receiveDetailDto.setCompanyType(companyType);
            receiveDetailDto.setCompanyId(companyId);
            Object delCount = dao.delete("buyContract_sqlMap.deleteReceiveOfBuyContract",
                    receiveDetailDto);

            if ("0".equals(delCount.toString())) {
                return 0;
            }
            issuccess = 1;
        } catch (Exception e) {
            issuccess = 0;
            log.error("删除收获地址失败！", e);
        }
        return issuccess;
    }

    @Override
    public Object modifyBuyContractOfShow(String id) {
        Object buyContract = null;
        try {
            buyContract = dao.queryForObject(
                    "buyContract_sqlMap.modifyBuyContractOfShow", id);
        } catch (Exception e) {
            log.error("修改采购合同 显示失败！", e);
        }
        return buyContract;
    }

    @SuppressWarnings("unchecked")
    public List<BuyContractProductInfoDto> getBuyContractProductInfoOfModify(String id) {
        List<BuyContractProductInfoDto> productInfoList = null;
        try {
            productInfoList = dao.queryForlist(
                    "buyContract_sqlMap.getBuyContractProductInfoOfModify", id);
        } catch (Exception e) {
            log.error("修改采购合同 产品信息列表失败！", e);
        }
        return productInfoList;
    }

    @SuppressWarnings("unchecked")
    public String getReceiceInfoCount(List<BuyContractProductInfoDto> productInfoList,
            String id) {
        String returnValue = null;
        try {
            List<BuyContractDetailDto> BuyContractDetailDtoList = dao.queryForlist(
                    "buyContract_sqlMap.getBuyContractOfReceiveProductCount", id);
            returnValue = "{resultSum:" + BuyContractDetailDtoList.size() + ",rows:[";
            for (int i = 0; i < BuyContractDetailDtoList.size(); i++) {
                BuyContractDetailDto buyContractDetailDto = (BuyContractDetailDto) BuyContractDetailDtoList
                        .get(i);
                returnValue += "{productId:" + buyContractDetailDto.getProductId()
                        + ",sum:" + buyContractDetailDto.getCount() + "},";
            }
            // {resultSum:4,rows:[{productId:14,sum:23},{productId:15,sum:32},{productId:17,sum:33},{productId:20,sum:44}]}
            if (BuyContractDetailDtoList.size() != 0) {
                returnValue = returnValue.substring(0, returnValue.lastIndexOf(","));
            }
            returnValue += "]}";
        } catch (Exception e) {
            log.error("采购合同 获取产品总数失败！", e);
        }
        return returnValue;
    }

    @SuppressWarnings( { "unchecked", "static-access" })
    public String getReceiceInfoModify(String id) {
        String returnValue = null;
        try {
            List<BuyContractOfReceiveInfoDto> receiveInfoList = dao.queryForlist(
                    "buyContract_sqlMap.getBuyContractOfReceiveInfo", id);
            returnValue = "{resultSum:" + receiveInfoList.size() + ",rows:[";
            for (int i = 0; i < receiveInfoList.size(); i++) {
                BuyContractOfReceiveInfoDto buyContractOfReceiveInfoDto = receiveInfoList
                        .get(i);
                if (!StringUtils.isBlank(buyContractOfReceiveInfoDto.getAddressId())) {
                    List<ReceiveDetailDto> receiveDetailList = dao.queryForlist(
                            "buyContract_sqlMap.getBuyContractOfReceiveProduct",
                            buyContractOfReceiveInfoDto);// 获取采购合同 收货信息物品列表
                    String receiveName = buyContractOfReceiveInfoDto.getReceiveName();
                    receiveName = this.replaceHTML(receiveName);
                    String goodsName = buyContractOfReceiveInfoDto.getGoodsName();
                    goodsName = this.replaceHTML(goodsName);
                    String address = buyContractOfReceiveInfoDto.getAddress();
                    address = this.replaceHTML(address);
                    String linkman = buyContractOfReceiveInfoDto.getLinkman();
                    linkman = this.replaceHTML(linkman);
                    String tel = buyContractOfReceiveInfoDto.getTel();
                    tel = this.replaceHTML(tel);
                    String post = buyContractOfReceiveInfoDto.getPostcode();
                    post = this.replaceHTML(post);
                    String mobil = buyContractOfReceiveInfoDto.getMobile();
                    mobil = this.replaceHTML(mobil);
                    returnValue += "{companyType:"
                            + buyContractOfReceiveInfoDto.getCompanyType()
                            + ",receiveId:" + buyContractOfReceiveInfoDto.getReceiveId()
                            + ",companyId:" + buyContractOfReceiveInfoDto.getCompanyId()
                            + ",addressId:" + buyContractOfReceiveInfoDto.getAddressId()
                            + ",receiveName:\"" + receiveName + "\",goodsName:\""
                            + goodsName + "\",address:\"" + address + "\",linkman:\""
                            + linkman + "\",tel:\"" + tel + "\",postcode:\"" + post
                            + "\",mobile:\"" + mobil + "\",resultSum:"
                            + receiveDetailList.size() + ",rows:[";

                    for (int j = 0; j < receiveDetailList.size(); j++) {
                        ReceiveDetailDto receiveDetailDto = receiveDetailList.get(j);
                        String prodName = receiveDetailDto.getProdName();
                        prodName = this.replaceHTML(prodName);
                        String prodType = receiveDetailDto.getProdType();
                        prodType = this.replaceHTML(prodType);
                        String prodUnit = receiveDetailDto.getProdUnit();
                        prodUnit = this.replaceHTML(prodUnit);
                        returnValue += "{prodId:" + receiveDetailDto.getProdId()
                                + ",prodCode:\"" + receiveDetailDto.getProdCode()
                                + "\",prodName:\"" + prodName + "\",prodType:\""
                                + prodType + "\",prodUnit:\"" + prodUnit
                                + "\",buyCount:\"" + receiveDetailDto.getBuyCount()
                                + "\",receiveCount:" + receiveDetailDto.getReceiveCount()
                                + "},";
                    }
                    returnValue = returnValue.substring(0, returnValue.lastIndexOf(","));
                    returnValue += "]},";
                }
            }
            if (receiveInfoList.size() != 0) {
                returnValue = returnValue.substring(0, returnValue.lastIndexOf(","));
            }
            returnValue += "]}";
            returnValue = returnValue.replaceAll("null", "");
        } catch (Exception e) {
            log.error("采购合同 收货信息修列表失败！", e);
        }
        return returnValue;
    }

    public int modifyBuyContractOfTransact(BuyContractOfAddDto buyContractOfAddDto,
            String JsonDate) {
        int issuccess = 0;
        try {
            /** 事物开始 */
            dao.beginTransaction();
            org.json.JSONObject json = new JSONObject(JsonDate);
            int sum = json.getInt("resultSum");
            BigDecimal buySum=new BigDecimal(0);
            for (int i = 0; i < sum; i++) {
                JSONObject rows = json.getJSONArray("rows").getJSONObject(i);
                String buyCount = rows.getString("buyCount");
                String buyPrice = rows.getString("buyMoney");
                /** 计算总和 */
                buySum = buySum.add(new BigDecimal(buyPrice).multiply(new BigDecimal(buyCount)));
            }
            if(buyContractOfAddDto.getMoney()!=null && !buyContractOfAddDto.getMoney().equals(String.valueOf(buySum))){
                log.debug("修改采购合同号为{0}，js:"+buyContractOfAddDto.getMoney()+",BigDecimal:"+buySum+"", buyContractOfAddDto.getId());
                }
            buyContractOfAddDto.setMoney(String.valueOf(buySum));
            /** 修改采购合同 */
            dao.insert("buyContract_sqlMap.modifyBuyContract", buyContractOfAddDto);
            dao.startBatch();
            /** 插入采购合同明细 */
            ITodoWorkService todoWork = (ITodoWorkService) Container
                    .getBean("todoWrokServiceImpl");
            /** 插入事务 法务专员 */
            if ("2".equals(buyContractOfAddDto.getStatus())
                    && "1".equals(buyContractOfAddDto.getTemplateType())) {
                WorkEntity work = new WorkEntity();
                work.setCount(1);
                work.setUserId(buyContractOfAddDto.getLegalId());
                work.setWorkId(13);
                todoWork.addTodoWorks(work);
            }
            /** 插入事务 采购主管待评审 */
            if ("4".equals(buyContractOfAddDto.getStatus())
                    && "0".equals(buyContractOfAddDto.getTemplateType())) {
                WorkEntity work = new WorkEntity();
                work.setCount(1);
                work.setUserId(buyContractOfAddDto.getBuyManId());
                work.setWorkId(13);
                todoWork.addTodoWorks(work);
            }
            /** 删除采购合同明细 */
            dao.delete("buyContract_sqlMap.deleteBuyContractDetail", buyContractOfAddDto
                    .getId());
            for (int i = 0; i < sum; i++) {
                JSONObject rows = json.getJSONArray("rows").getJSONObject(i);
                String buyCount = rows.getString("buyCount");
                String buyPrice = rows.getString("buyMoney");
                String productId = rows.getString("productId");// 产品编号
                BuyContractDetailDto buyContractDetail = new BuyContractDetailDto();
                buyContractDetail.setCount(buyCount);
                buyContractDetail.setProductId(productId);
                buyContractDetail.setBuyContractId(buyContractOfAddDto.getId());
                buyContractDetail.setPrice(buyPrice);
                dao.insert("buyContract_sqlMap.addBuyContractDetail", buyContractDetail);
            }
            dao.executeBatch();
            dao.commitTransaction();
            issuccess = 1;
        } catch (Exception e) {
            issuccess = 0;
            try {
                dao.endTransaction();
            } catch (Exception e1) {
                log.error("修改采购合同失败！", e1);
            }
            log.error("修改采购合同失败！", e);
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.error("修改采购合同失败！", e);
            }
        }
        return issuccess;
    }

    @SuppressWarnings("unchecked")
    public List getReceiceInfoOfShow(String id) {
        List<BuyContractOfReceiveInfoDto> receiveInfoList = null;
        try {
            receiveInfoList = dao.queryForlist(
                    "buyContract_sqlMap.getBuyContractOfReceiveInfo", id);
        } catch (Exception e) {
            log.error("获取收货信息失败！", e);
        }

        return receiveInfoList;

    }

    public String getBuyContractIsExist(BuyContractOfAddDto buyContractOfAddDto) {
        String isExist = null;
        try {
            isExist = (String) dao.queryForObject(
                    "buyContract_sqlMap.getBuyContractIsExist", buyContractOfAddDto);
        } catch (Exception e) {
            log.error("判断公司合同号是否存在失败！", e);
        }

        return isExist;

    }

    public int updateBuyContractOfDecide(BuyContractOfAddDto buyContractOfAddDto) {
        int isSuccess = 0;
        try {
            isSuccess = dao.update("buyContract_sqlMap.updateBuyContractOfDecide",
                    buyContractOfAddDto);
        } catch (Exception e) {
            log.error("修改采购合同 确认失败！", e);
        }

        return isSuccess;
    }

    public int modifyCommentTableOfStatus(String id, UserEntity user) {
        int isSuccess = 0;
        try {
            /** 改状态 */
            isSuccess = dao.update("buyContract_sqlMap.modifyCommentTableOfStatus", id);
            /** 减事物 */
            if (isSuccess != 0) {
                ITodoWorkService todoWork = (ITodoWorkService) Container
                        .getBean("todoWrokServiceImpl");
                WorkEntity work = new WorkEntity();
                work.setCount(-1);
                work.setUserId(user.getId());
                work.setWorkId(14);
                todoWork.addTodoWorks(work);
            }
            return isSuccess;
        } catch (Exception e) {
            log.error("改状态失败", e);
        }
        return isSuccess;
    }

    public int getStrutsOfAll(HashMap<String, String> map) {
        int isSuccess = 0;
        try {
            isSuccess = (Integer) dao.queryForObject("buyContract_sqlMap.getStrutsOfAll",
                    map);
        } catch (Exception e) {
            log.error("获取状态失败！", e);
        }
        return isSuccess;
    }

    public int deleteBuyContract(String id) {
        int issuccess = 0;
        try {
            /** 事物开始 */
            dao.beginTransaction();
            /** 删除采购合同地址 */
            dao.delete("buyContract_sqlMap.deleteBuyContractAddress", id);
            /** 删除采购合同明细 */
            dao.delete("buyContract_sqlMap.deleteBuyContractDetail", id);
            /** 删除采购合同 */
            dao.delete("buyContract_sqlMap.deleteBuyContract", id);
            dao.commitTransaction();
            issuccess = 1;
        } catch (Exception e) {
            issuccess = 0;
            try {
                dao.endTransaction();
            } catch (Exception e1) {
                log.error("删除采购合同失败！", e1);
            }
            log.error("删除采购合同失败！", e);
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.error("删除采购合同失败！", e);
            }
        }
        return issuccess;
    }

    private static String replaceHTML(String str) {
        if (str == null)
            return str;
        str = str.replaceAll("\"", "&quot;");
        str = str.replaceAll("<", "&lt;");
        str = str.replaceAll(">", "&gt;");
        str = str.replaceAll("\\\\", "\\\\\\\\");
        return str;
    }
}
