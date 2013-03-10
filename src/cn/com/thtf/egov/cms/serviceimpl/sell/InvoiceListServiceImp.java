package cn.com.thtf.egov.cms.serviceimpl.sell;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.BaseService;
import cn.com.thtf.egov.cms.application.NewIDao;
import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.constant.Constants;
import cn.com.thtf.egov.cms.dto.CustomerInfoDto;
import cn.com.thtf.egov.cms.dto.InvoiceDetailDto;
import cn.com.thtf.egov.cms.dto.InvoiceInfoDto;
import cn.com.thtf.egov.cms.dto.InvoiceListDto;
import cn.com.thtf.egov.cms.dto.MakeInvoiceAddDto;
import cn.com.thtf.egov.cms.dto.ProductTypeInfoDto;
import cn.com.thtf.egov.cms.dto.SellInvocleOfAddDto;
import cn.com.thtf.egov.cms.dto.SellInvoiceDetailDto;
import cn.com.thtf.egov.cms.dto.SellInvoiceDetailOfInvoiceDto;
import cn.com.thtf.egov.cms.dto.SellInvoiceDto;
import cn.com.thtf.egov.cms.dto.SendGoodsParticularListDto;
import cn.com.thtf.egov.cms.entity.BusiLogEntity;
import cn.com.thtf.egov.cms.entity.MailEntity;
import cn.com.thtf.egov.cms.entity.MakeInvoiceDetailEntity;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.entity.WorkEntity;
import cn.com.thtf.egov.cms.form.MakeInvoiceDetail;
import cn.com.thtf.egov.cms.iservice.common.ICommonService;
import cn.com.thtf.egov.cms.iservice.sell.IBackInvoiceService;
import cn.com.thtf.egov.cms.iservice.sell.IInvoiceListService;
import cn.com.thtf.egov.cms.iservice.work.ITodoWorkService;
import cn.com.thtf.egov.cms.util.Container;
import cn.com.thtf.egov.cms.util.MailSenderInfo;

/**
 * 开票实现层
 * 
 * @author hanrubing
 */
public class InvoiceListServiceImp extends BaseService implements IInvoiceListService {

    private NewIDao dao;

    private static Logger log = LoggerFactory.getLogger(InvoiceListServiceImp.class);
    private java.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public NewIDao getDao() {
        return dao;
    }

    public void setDao(NewIDao dao) {
        this.dao = dao;
    }

    @SuppressWarnings("unchecked")
    public List<ProductTypeInfoDto> getProductList(ProductTypeInfoDto productDto)
            throws Exception {
        return dao.queryForlist("invoice_sqlMap.selectAllProduct", productDto);
    }

    /**
     * 开票管理列表 实现
     * 
     * @param nPage
     *            分页
     * @param user
     *            UserEntity实体类
     * @return Map invoiceList 列表，roleName 权限名字
     * @throws Exception
     */

    @SuppressWarnings("unchecked")
    @Override
    public List<Object> getInvoiceList(NewPage nPage, UserEntity user) throws Exception {
        InvoiceListDto invoiceDto = new InvoiceListDto();// 开票dto

        switch (user.getRoleId()) {
        case Constants.ROLE_SALES_MANAGER: {// 销售经理
            invoiceDto.setType("sellManage");
            invoiceDto.setUserId(user.getId());// 用户名
            break;
        }
        case Constants.ROLE_SALES_DIRECTOR: {// 销售总监 5
            invoiceDto.setUserId(user.getId());// 用户名
            invoiceDto.setType("sellMajordomo");
            break;
        }
        case Constants.ROLE_DIRECTOR_OF_OPERATIONS: {// 运营总监 17
            invoiceDto.setUserId(user.getId());// 用户名
            invoiceDto.setType("runMajordomo");
            break;
        }
        case Constants.ROLE_SALES_ASSISTANT: {// 销售助理 3，9一样

            invoiceDto.setUserAreaProductList(dao.queryForlist(
                    "salesBackContract_sqlMap.getAreaIdAndProductTypeIdOfRoldIs3",
                    user.getId()));
            invoiceDto.setUserId(user.getId());// 用户名
            invoiceDto.setType("sellAssistant");
            break;
        }
        case Constants.ROLE_REGIONAL_DIRECTOR: {// 区域总监 9
            invoiceDto.setUserAreaProductList(dao.queryForlist(
                    "salesBackContract_sqlMap.getAreaIdAndProductTypeIdOfRoldIs9",
                    user.getId()));
            invoiceDto.setType("areaMajordomo");
            invoiceDto.setUserId(user.getId());// 用户名
            break;
        }
        case Constants.ROLE_AREA_MANAGER: {// 区域经理 大区经理
            invoiceDto.setType("areaManage");
            invoiceDto.setUserId(user.getId());// 用户名
            break;
        }
        case Constants.ROLE_BIGAREA_MANAGER: {// 区域经理 大区经理
            invoiceDto.setType("areaManage");
            invoiceDto.setUserId(user.getId());// 用户名
            break;
        }
        case Constants.ROLE_CREDIT_COMMISSIONER: {// 信用专员 采购主管
            // 法务专员、运营总监助理、信用主管、总经理一样参数
            invoiceDto.setType("productCreditOffice");
            invoiceDto.setUserId(user.getId());
            break;
        }
        case Constants.ROLE_PROCUREMENT_OFFICER: {// 采购主管 11区域总监信用专员
            invoiceDto.setType("productCreditOffice");
            invoiceDto.setUserId(user.getId());
            break;
        }
        case Constants.ROLE_GENERAL_MANAGER: {// 总经理 18
            invoiceDto.setType("productCreditOffice");
            invoiceDto.setUserId(user.getId());
            break;
        }
        case Constants.ROLE_CREDIT_CHARGE: {// 信用主管 7
            invoiceDto.setType("productCreditOffice");
            invoiceDto.setUserId(user.getId());
            break;
        }
        case Constants.ROLE_AST_DIRECTOR_OF_OPERATIONS: {// 运营总监助理 16
            invoiceDto.setType("productCreditOffice");
            invoiceDto.setUserId(user.getId());
            break;
        }
        case Constants.ROLE_COMPLIANCE_OFFICER: {// 法务专员 15
            invoiceDto.setType("productCreditOffice");
            invoiceDto.setUserId(user.getId());
            break;
        }

        default:
            ;
        }
        try {
            return queryRecords(dao, "selectInvoice.getDate", invoiceDto, nPage);
        } catch (Exception e) {
            log.error("获取开票管理列表错误", e);
            return null;
        }

    }

    /**
     * 开票管理检索 实现
     * 
     * @param nPage
     *            分页
     * @param user
     *            UserEntity实体类
     * @param invoiceDto
     *            开票的dto
     * @return List<Object> 列表
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Object> getInvoiceListByObj(NewPage nPage, UserEntity user,
            InvoiceListDto invoiceDto) {
        try {
            if ("sellAssistant".equals(invoiceDto.getType())) {
                invoiceDto.setUserAreaProductList(dao.queryForlist(
                        "salesBackContract_sqlMap.getAreaIdAndProductTypeIdOfRoldIs3",
                        user.getId()));
            }
            if ("areaMajordomo".equals(invoiceDto.getType())) {
                invoiceDto.setUserAreaProductList(dao.queryForlist(
                        "salesBackContract_sqlMap.getAreaIdAndProductTypeIdOfRoldIs9",
                        user.getId()));
            }
            return queryRecords(dao, "selectInvoiceByObj.getData", invoiceDto, nPage);
        } catch (Exception e) {
            log.error("获取开票管理检索错误", e);
            return null;
        }

    }

    /**
     * 获得产品分类列表
     * 
     * @return List<ProductTypeInfoDto>
     * @throws Exception
     */
    public List<ProductTypeInfoDto> getProductType() {
        List<ProductTypeInfoDto> ProductType = null;
        ProductTypeInfoDto ptid = new ProductTypeInfoDto();
        try {
            ProductType = this.getProductList(ptid);// 产品分类

        } catch (Exception e) {
            log.error("获取产品分类错误", e);
        }

        return ProductType;
    }

    @Override
    public List<Object> getCustomerListByName(NewPage nPage, CustomerInfoDto customerDto) {
        try {
            return queryRecords(dao, "selectCustomerByObj.getDate", customerDto, nPage);
        } catch (Exception e) {
            log.error("获取产品分类错误", e);
            return null;
        }
    }

    @Override
    public List<Object> getCustomerList(NewPage nPage, CustomerInfoDto customerDto) {
        try {
            return queryRecords(dao, "selectCustomer.getDate", customerDto, nPage);
        } catch (Exception e) {
            log.error("取客户信息失败", e);
            return null;
        }
    }

    @Override
    public boolean removeInvoice(String id) {
        boolean isSuccess = true;
        try {
            dao.beginTransaction();// 事物开始
            int intCnt = modifyInvoiceOfDetailTodel(id);// 删除明细
            int intCntDe = dao.delete("invoice_sqlMap.deleteInvoice", id);
            if (intCntDe == 1 && intCnt > 0) {
                dao.commitTransaction();
            } else {
                dao.endTransaction();
                isSuccess = false;
            }
        } catch (Exception e) {
            log.error("添加发票失败", e);
            try {
                dao.endTransaction();
            } catch (Exception e1) {
                log.error("事务提交失败", e1);
            }// 事物结束
            isSuccess = false;
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.error("添加发票失败！", e);
            }
        }
        return isSuccess;
    }

    @Override
    public String getCustomerInfoOfJSON(List<Object> customerList) {
        String customerInfo = "{resultCount:" + customerList.size() + ",rows:[";
        for (int i = 0; i < customerList.size(); i++) {

            // CustomerInfoDto customerDto = new CustomerInfoDto();
            CustomerInfoDto customerDto = (CustomerInfoDto) customerList.get(i);
            String name = customerDto.getName();
            name = replaceHTML(name);
            String invoiceBankTel = customerDto.getInvoiceBankTel();
            invoiceBankTel = replaceHTML(invoiceBankTel);
            String taxNumber = customerDto.getTaxNumber();
            taxNumber = replaceHTML(taxNumber);
            String bankAddress = customerDto.getInvoiceBankAddress();
            bankAddress = replaceHTML(bankAddress);
            String bankName = customerDto.getInvoiceBankName();
            bankName = replaceHTML(bankName);

            customerInfo += "{id:" + customerDto.getId() + ",";
            customerInfo += "name:\"" + name + "\",";
            customerInfo += "invoiceBankTel:\"" + invoiceBankTel + "\",";
            customerInfo += "taxNumber:\"" + taxNumber + "\",";
            customerInfo += "invoiceBankAccount:\"" + customerDto.getInvoiceBankAccount()
                    + "\",";
            customerInfo += "invoiceBankAddress:\"" + bankAddress + "\",";
            customerInfo += "invoiceType:\"" + customerDto.getInvoiceType() + "\",";
            if (customerDto.getInvoiceType() == 0) {
                customerInfo += "invoiceTypeName:\"普通\",";
            }
            if (customerDto.getInvoiceType() == 1) {
                customerInfo += "invoiceTypeName:\"增值税\",";
            }
            customerInfo += "invoiceBankName:\"" + bankName + "\"},";

        }
        if (customerList.size() != 0) {
            customerInfo = customerInfo.substring(0, customerInfo.lastIndexOf(","));
        }
        customerInfo += "]}";
        return customerInfo;
    }

    public List<Object> getSendGoodsList(NewPage nPage,
            SendGoodsParticularListDto sendGoodsParticularListDto) {
        try {
            return queryRecords(dao, "selectSendGoodsList.getDate",
                    sendGoodsParticularListDto, nPage);
        } catch (Exception e) {
            log.error("获取发票明细出错！", e);
            return null;
        }
    }

    public List<Object> getSendGoodsListByObj(NewPage nPage,
            SendGoodsParticularListDto SendGoodsDto) {
        try {
            return queryRecords(dao, "selectSendGoodsListByObj.getDate", SendGoodsDto,
                    nPage);
        } catch (Exception e) {
            log.error("获取失败！", e);
            return null;
        }
    }

    public int syndicInvoiceDecide(InvoiceListDto invoiceDto) throws Exception {
        return dao.update("invoice_sqlMap.syndicInvoice", invoiceDto);
    }

    public boolean addInvoice(MakeInvoiceAddDto makeInvoiceAddDto,
            String returnValueToServer) throws Exception {
        boolean isSuccess = true;
        try {
            dao.beginTransaction();// 事物开始
            org.json.JSONObject json = new JSONObject(returnValueToServer);
            int sum = json.getInt("resultSum");
            BigDecimal makeInvoiceMoney= new BigDecimal(0);
            Map<String,Object> invoiceDetail = new HashMap<String,Object>();
            for (int i = 0; i < sum; i++) {
                JSONObject rows = json.getJSONArray("rows").getJSONObject(i);
                String sendGoodsId = rows.getString("sendGoodsId");// 发货单号
                String productId = rows.getString("productId");// 产品编号
                String makeSum = rows.getString("makeSum");// 开票数
                String makeMoney = rows.getString("makeMoney");// 开票金额

                /** 12.17 数据后台验证 */
                Map<String,Object> map=new HashMap<String,Object>();
                map.put("sendGoodsId", sendGoodsId);
                map.put("productId", productId);
                BigDecimal price = new BigDecimal((String)dao.queryForObject("invoice_sqlMap.getSellContractPrice", map));
                BigDecimal sumMoney = new BigDecimal(makeSum).multiply(price);
                if(!makeMoney.equals(String.valueOf(sumMoney))){
                        log.debug("发货单号为{0}，产品为{1}计算金额不符，js:"+makeMoney+",BigDecimal:"+sumMoney+"", sendGoodsId, productId);
                        }
                MakeInvoiceDetail makeInvoiceDetail = new MakeInvoiceDetail();
                makeInvoiceDetail.setMakeInvoiceId(makeInvoiceAddDto.getId());
                makeInvoiceDetail.setSendGoodsId(sendGoodsId);
                makeInvoiceDetail.setProductId(productId);
                makeInvoiceDetail.setMoney(String.valueOf(sumMoney));
                makeInvoiceDetail.setCount(makeSum);
                invoiceDetail.put(sendGoodsId+productId, sumMoney);
                makeInvoiceMoney = makeInvoiceMoney.add(sumMoney);
            }
            if(!makeInvoiceAddDto.getMoney().equals(String.valueOf(makeInvoiceMoney))){
                log.debug("开票单号为{0}",makeInvoiceAddDto.getId());
                log.debug("开票合计计算不符，js合计:{0},BigDecimal合计{1}", makeInvoiceAddDto.getMoney(), makeInvoiceMoney);
            }
            makeInvoiceAddDto.setMoney(String.valueOf(makeInvoiceMoney));
            /** 添加开票 */
            dao.insert("invoice_sqlMap.insertInvoice", makeInvoiceAddDto);
            /** 添加开票明细 */
            dao.startBatch();
            for (int i = 0; i < sum; i++) {
                JSONObject rows = json.getJSONArray("rows").getJSONObject(i);
                String sendGoodsId = rows.getString("sendGoodsId");// 发货单号
                String productId = rows.getString("productId");// 产品编号
                String makeSum = rows.getString("makeSum");// 开票数
                //String makeMoney = rows.getString("makeMoney");// 开票金额

                /** 12.17 数据后台验证 */
                MakeInvoiceDetail makeInvoiceDetail = new MakeInvoiceDetail();
                makeInvoiceDetail.setMakeInvoiceId(makeInvoiceAddDto.getId());
                makeInvoiceDetail.setSendGoodsId(sendGoodsId);
                makeInvoiceDetail.setProductId(productId);
                makeInvoiceDetail.setMoney(String.valueOf(invoiceDetail.get(sendGoodsId+productId)));
                makeInvoiceDetail.setCount(makeSum);
                dao.insert("invoice_sqlMap.insertInvoiceDetail", makeInvoiceDetail);
            }
            dao.executeBatch();
            if(makeInvoiceAddDto.getSellAssId()!=null){
                this.addTodoWorks(1, makeInvoiceAddDto.getSellAssId(),
                        Constants.SELL6);}
            dao.commitTransaction();
        } catch (Exception e) {
            log.error("添加开票失败", e);
            try {
                dao.endTransaction();
            } catch (Exception e1) {
                log.error("事务提交失败", e1);
            }// 事物结束
            isSuccess = false;
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.error("添加发票失败！", e);
            }
        }
        return isSuccess;
    }

    public Object getInvoiceOfCustomer(String id) {
        try {
            return dao.queryForObject("invoice_sqlMap.selectInvoiceOfCustomerById", id);
        } catch (Exception e) {
            log.error("开票修改 获得开票客户信息失败！", e);
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<Object> getInvoiceOfDetail(String id) {
        try {
            return dao.queryForlist("invoice_sqlMap.selectInvoiceOfDetailById", id);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error("开票修改  获得开票明细失败！", e);
            return null;
        }
    }

    public int modifyInvoiceOfDetailTodel(String id) throws Exception {
        return dao.delete("invoice_sqlMap.deleteInvoiceOfDetail", id);
    }

    public boolean modifyInvoice(MakeInvoiceAddDto makeInvoiceAddDto, String productOfJson) {
        boolean isSuccess = true;
        try {
            dao.beginTransaction();// 事物开始
            org.json.JSONObject json = new JSONObject(productOfJson);
            int sum = json.getInt("resultSum");
            BigDecimal makeInvoiceMoney= new BigDecimal(0);
            Map<String,Object> invoiceDetail = new HashMap<String,Object>();
            for (int i = 0; i < sum; i++) {
                JSONObject rows = json.getJSONArray("rows").getJSONObject(i);
                String sendGoodsId = rows.getString("sendGoodsId");// 发货单号
                String productId = rows.getString("productId");// 产品编号
                String makeSum = rows.getString("makeSum");// 开票数
                String makeMoney = rows.getString("makeMoney");// 开票金额

                /** 12.17 数据后台验证 */
                Map<String,Object> map=new HashMap<String,Object>();
                map.put("sendGoodsId", sendGoodsId);
                map.put("productId", productId);
                BigDecimal price = new BigDecimal((String)dao.queryForObject("invoice_sqlMap.getSellContractPrice", map));
                BigDecimal sumMoney = new BigDecimal(makeSum).multiply(price);
                if(!makeMoney.equals(String.valueOf(sumMoney))){
                        log.debug("发货单号为{0}，产品为{1}计算金额不符，js:"+makeMoney+",BigDecimal:"+sumMoney+"", sendGoodsId, productId);
                        }
                MakeInvoiceDetail makeInvoiceDetail = new MakeInvoiceDetail();
                makeInvoiceDetail.setMakeInvoiceId(makeInvoiceAddDto.getId());
                makeInvoiceDetail.setSendGoodsId(sendGoodsId);
                makeInvoiceDetail.setProductId(productId);
                makeInvoiceDetail.setMoney(String.valueOf(sumMoney));
                makeInvoiceDetail.setCount(makeSum);
                invoiceDetail.put(sendGoodsId+productId, sumMoney);
                makeInvoiceMoney = makeInvoiceMoney.add(sumMoney);
            }
            if(!makeInvoiceAddDto.getMoney().equals(String.valueOf(makeInvoiceMoney))){
                log.debug("开票单号为{0}",makeInvoiceAddDto.getId());
                log.debug("开票合计计算不符，js合计:{0},BigDecimal合计{1}", makeInvoiceAddDto.getMoney(), makeInvoiceMoney);
            }
            makeInvoiceAddDto.setMoney(String.valueOf(makeInvoiceMoney));
            dao.update("invoice_sqlMap.modifyInvoice", makeInvoiceAddDto);// 修改开票
            this.modifyInvoiceOfDetailTodel(makeInvoiceAddDto.getId());// 删除开票明细
            /** 添加开票明细 */
            dao.startBatch();
            for (int i = 0; i < sum; i++) {
                JSONObject rows = json.getJSONArray("rows").getJSONObject(i);
                String sendGoodsId = rows.getString("sendGoodsId");// 发货单号
                String productId = rows.getString("productId");// 产品编号

                String makeSum = rows.getString("makeSum");// 开票数
                //String makeMoney = rows.getString("makeMoney");// 开票金额
                
                MakeInvoiceDetail makeInvoiceDetail = new MakeInvoiceDetail();
                makeInvoiceDetail.setMakeInvoiceId(makeInvoiceAddDto.getId());
                makeInvoiceDetail.setSendGoodsId(sendGoodsId);
                makeInvoiceDetail.setProductId(productId);
                makeInvoiceDetail.setMoney(String.valueOf(invoiceDetail.get(sendGoodsId+productId)));
                makeInvoiceDetail.setCount(makeSum);
                dao.insert("invoice_sqlMap.insertInvoiceDetail", makeInvoiceDetail);
            }
            dao.executeBatch();
            if(makeInvoiceAddDto.getSellAssId()!=null){
            this.addTodoWorks(1, makeInvoiceAddDto.getSellAssId(),
                    Constants.SELL6);}
            dao.commitTransaction();
        } catch (Exception e) {
            log.debug("修改开票明细失败", e);
            try {
                dao.endTransaction();
            } catch (Exception e1) {
                log.debug("修改开票明细失败", e1);
            }// 事物结束
            isSuccess = false;
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.error("删除开票失败！", e);
            }
        }

        return isSuccess;
    }

    @SuppressWarnings("unchecked")
    public List<Object> getSellInvoiceListById(String id) {
        try {
            return dao.queryForlist("invoice_sqlMap.getSellInvoiceList", id);
        } catch (Exception e) {
            log.error("查找发票 返回发票明细列表失败！", e);
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<Object> getInvoiceOfDetailToSellById(String id) {
        try {
            return dao.queryForlist("invoice_sqlMap.getInvoiceOfDetailToSellById", id);
        } catch (Exception e) {
            log.error("查找开票明细之未开发票信息失败！", e);
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public int addSellInvoice(SellInvocleOfAddDto sellInvocleOfAddDto,
            String invoiceDetail[], UserEntity user) {
        int isSuccess = 1;
        try {
            dao.beginTransaction();// 事物开始
            String id = this.getSellInvoiceMaxId();
            sellInvocleOfAddDto.setId(id);
            dao.insert("invoice_sqlMap.addSellInvoice", sellInvocleOfAddDto);// SendGoodsListInfoDto
            // SendGoodsListInfoDto sendGoodsListInfoDto=
            // List<SendGoodsParticularListDto>
            // SendGoodsParticularListDtoList=dao.queryForlist("invoice_sqlMap.getInvoiceOfDetailToSellById",
            // sellInvocleOfAddDto.getMakeInvoiceId());
            // HashSet makeInvoiceDetailId=new HashSet();
            // for(int j=0;j<SendGoodsParticularListDtoList.size();j++){
            // SendGoodsParticularListDto
            // sendGoodsParticularListDto=(SendGoodsParticularListDto)SendGoodsParticularListDtoList.get(j);
            // makeInvoiceDetailId.add(sendGoodsParticularListDto.getMakeInvoiceId());
            // }
            dao.startBatch();
            for (int i = 0; i < invoiceDetail.length; i++) {
                // boolean isExist=notIn(invoiceDetail[i],makeInvoiceDetailId);
                // if(!isExist){return 2;}//发货数-其他开票-退货<申请开票数

                SellInvoiceDetailOfInvoiceDto sellInvoiceDetailDto = new SellInvoiceDetailOfInvoiceDto();
                sellInvoiceDetailDto.setSellInvoiceId(id);
                sellInvoiceDetailDto.setId(invoiceDetail[i]);
                this.addSellInvoiceDetail(sellInvoiceDetailDto);
                /** 改变flag */
                MakeInvoiceDetailEntity makeInvoiceDetailEntity = new MakeInvoiceDetailEntity();
                makeInvoiceDetailEntity.setId(Integer.parseInt(invoiceDetail[i]));
                makeInvoiceDetailEntity.setFlag(2);
                dao.update("invoice_sqlMap.modifyFlagOfMakeInvoice",
                        makeInvoiceDetailEntity);
            }
            dao.executeBatch();
            dao.commitTransaction();
        } catch (Exception e) {
            log.error("添加发票失败", e);
            try {
                dao.endTransaction();
            } catch (Exception e1) {
                log.error("事务提交失败", e1);
            }// 事物结束
            return 0;
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.error("添加发票失败！", e);
            }
        }

        return isSuccess;
    }

    /**
     * 获取最大id
     * 
     * @param 开票id
     * @return
     * @throws Exception
     */
    private synchronized String getSellInvoiceMaxId() throws Exception {
        return (String) dao.queryForObject("invoice_sqlMap.getSellInvoiceMaxId", "");
    }

    /**
     * 获取发票明细总数
     * 
     * @param 发票id
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unused")
    private String getSellInvoiceCountById(String id) throws Exception {

        return (String) dao.queryForObject("invoice_sqlMap.getSellInvoiceCountById", id);
    }

    /**
     * 添加发票明细
     * 
     * @param SellInvoiceDetailOfInvoiceDto
     *            sellInvoiceDetailDto
     * @throws Exception
     */
    private void addSellInvoiceDetail(SellInvoiceDetailOfInvoiceDto sellInvoiceDetailDto)
            throws Exception {
        dao.insert("invoice_sqlMap.addSellInvoiceDetail", sellInvoiceDetailDto);

    }

    @SuppressWarnings("unchecked")
    public List<SellInvocleOfAddDto> getSellInvoiceNumber(String id) {
        try {
            return dao.queryForlist("invoice_sqlMap.getSellInvoiceNumber", id);
        } catch (Exception e) {
            log.error("根据id查询发票错误", e);
        }
        return null;
    }

    /**
     * 根据id查询开票信息 creator: lilewei
     */
    @Override
    public InvoiceInfoDto getInvoiceById(String invoiceId) {

        InvoiceInfoDto invoice = null;

        try {

            invoice = (InvoiceInfoDto) dao.queryForObject(
                    "invoice_sqlMap.selectInvoiceById", invoiceId);

        } catch (Exception e) {
            log.error("根据id查询开票信息错误", e);
        }

        return invoice;
    }

    /*
     * (non-Javadoc)
     * 
     * @seecn.com.thtf.egov.cms.iservice.sell.IInvoiceListService#
     * getInvoiceDetailListByInvoiceId(java.lang.String)
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<InvoiceDetailDto> getInvoiceDetailListByInvoiceId(String invoiceId) {

        List<InvoiceDetailDto> invoiceDetailList = null;

        try {

            invoiceDetailList = dao.queryForlist(
                    "invoice_sqlMap.selectInvoiceDetailByInvoiceId", invoiceId);

        } catch (Exception e) {
            log.error("根据开票id查询开票明细列表错误", e);
        }
        return invoiceDetailList;
    }

    /*
     * 开票通知更新开票 lilewei
     */
    @Override
    public boolean modifyInvoiceforNotify(InvoiceInfoDto invoice, boolean isTrue,
            MailEntity mailEntity, MailSenderInfo mailSender) {

        boolean isSuccess = true;
        try {
            dao.beginTransaction();// 事物开始
            int updateCount = dao
                    .update("invoice_sqlMap.updateInvoiceForNotify", invoice);// 修改开票表状态
            // 减事务
            ITodoWorkService todoWork = (ITodoWorkService) Container
                    .getBean("todoWrokServiceImpl");
            WorkEntity work = new WorkEntity();
            work.setCount(-1);
            work.setUserId(invoice.getUserId());
            work.setWorkId(6);
            todoWork.addTodoWorks(work);
            isSuccess = updateCount > 0;
            dao.commitTransaction();
        } catch (Exception e) {
            log.error("开票通知更新开票错误", e);
            try {
                dao.endTransaction();
            } catch (Exception e1) {
                log.error("开票通知更新开票错误", e1);
            }
            isSuccess = false;
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.error("开票通知更新开票错误", e);
            }
        }
        return isSuccess;

        /*
         * java.text.SimpleDateFormat sdf = new
         * SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); try {
         * dao.beginTransaction();// 事物开始 if (isTrue) {// 邮件发送成功
         * mailEntity.setDatetime(sdf.format(new Date()));
         * mailEntity.setFlag(1);
         * 
         * int updateCount = dao.update("invoice_sqlMap.updateInvoiceForNotify",
         * invoice);// 修改开票表状态 // 减事务 ITodoWorkService todoWork =
         * (ITodoWorkService) Container .getBean("todoWrokServiceImpl");
         * WorkEntity work = new WorkEntity(); work.setCount(-1);
         * work.setUserId(invoice.getUserId()); work.setWorkId(6);
         * todoWork.addTodoWorks(work); isSuccess = updateCount > 0;
         * 
         * } else {// 邮件发送失败 mailEntity.setDatetime(null);
         * mailEntity.setFlag(0); } MailEntity mail = (MailEntity)
         * dao.queryForObject( "invoice_sqlMap.getMailIsExist",
         * mailEntity.getSubject());
         *//** 没有邮件 */
        /*
         * if (mail == null) {
         *//** 添加邮件 */
        /*
         * dao.insert("invoice_sqlMap.addMailOfMakeInvoice", mailEntity);
         *//** 获取mail的id */
        /*
         * String mailId = dao.queryForObject("invoice_sqlMap.selectMailId",
         * mailEntity).toString();
         *//** 插入收件人 mail_addressee */
        /*
         * MailAddresseeEntity mailAddresseeEntity = new MailAddresseeEntity();
         * mailAddresseeEntity.setMail(mailSender.getToAddress().getAddress());
         * mailAddresseeEntity.setMailId(Integer.parseInt(mailId));
         * mailAddresseeEntity.setName(Constants.K3_TO_NAME);
         * dao.insert("invoice_sqlMap.addMailAddressOfMakeInvoice",
         * mailAddresseeEntity); } else if (mail != null && mail.getFlag() == 0)
         * {
         *//** 修改邮件 */
        /*
         * dao.insert("invoice_sqlMap.modifyMailOfMakeInvoice", mailEntity); }
         * 
         * dao.commitTransaction(); } catch (Exception e) {
         * log.error("开票通知更新开票错误", e); try { dao.endTransaction(); } catch
         * (Exception e1) { log.error("开票通知更新开票错误", e1); } isSuccess = false; }
         * finally { try { dao.endTransaction(); } catch (Exception e) {
         * log.error("开票通知更新开票错误", e); } }
         */

    }

    public boolean deleteSellInvoice(String sellInvoiceid, String sellInvoiceDetailId,
            String makeInvoiceId, UserEntity user) {
        boolean isSuccess = true;
        try {
            dao.beginTransaction();// 事物开始
            // String
            // countSum=this.getSellInvoiceCountById(sellInvoiceid);//取出发票明细总数
            // 判断是否删除发票
            // /**如果总数为1 删除发票和发票明细*/
            // if("1".equals(countSum)){
            /** 加busi_log */
            ICommonService commonService = (ICommonService) Container
                    .getBean("commonServiceImpl");
            /** 获取发票信息用于busi_log */
            IBackInvoiceService backInvoiceService = (IBackInvoiceService) Container
                    .getBean("backInvoiceServiceImpl");
            // 根据发票号获得发票表信息
            SellInvoiceDto sellInvoiceDto = backInvoiceService
                    .selectSellInvoiceInfo(sellInvoiceid);
            // 根据发票号获得发票明细
            List<SellInvoiceDetailDto> sellInvoiceDetailList = backInvoiceService
                    .selectSellInvoiceDetailList(sellInvoiceid);
            MakeInvoiceAddDto makeInvoiceAddDto = (MakeInvoiceAddDto) dao.queryForObject(
                    "invoice_sqlMap.selectInvoiceOfCustomerById", makeInvoiceId);
            BusiLogEntity logEntity = new BusiLogEntity();
            logEntity.setUserid(user.getId());
            logEntity.setUsername(user.getName());
            logEntity.setContent(getSellInvoiceContent(makeInvoiceAddDto, sellInvoiceDto,
                    sellInvoiceDetailList, user.getName()));
            logEntity.setBusiType(103);

            commonService.addBusiLog(logEntity);

            /** 找发票表里的开票id */
            SellInvocleOfAddDto sellInvocleOfAddDto = new SellInvocleOfAddDto();
            sellInvocleOfAddDto.setId(sellInvoiceid);
            sellInvocleOfAddDto.setMakeInvoiceId(makeInvoiceId);
            List<SellInvocleOfAddDto> makeInvoiceIdList = dao.queryForlist(
                    "invoice_sqlMap.findMakeInvoiceDetailId", sellInvocleOfAddDto);
            for (int i = 0; i < makeInvoiceIdList.size(); i++) {
                SellInvocleOfAddDto sellDto = makeInvoiceIdList.get(i);
                MakeInvoiceDetailEntity makeInvoiceDetailEntity = new MakeInvoiceDetailEntity();
                makeInvoiceDetailEntity.setFlag(0);
                makeInvoiceDetailEntity
                        .setId(Integer.parseInt(sellDto.getMakeInvoiceId()));
                dao.update("invoice_sqlMap.modifyFlagOfMakeInvoice",
                        makeInvoiceDetailEntity);
            }
            /** 删除发票明细 */
            dao.delete("invoice_sqlMap.deleteSellInvoiceDetail", sellInvoiceDetailId);
            /** 删除发票 */
            dao.delete("invoice_sqlMap.deleteSellInvoice", sellInvoiceid);
            // }else{
            // /** 删除发票明细*/
            // dao.delete("invoice_sqlMap.deleteSellInvoiceDetail",
            // sellInvoiceDetailId);
            // }
            dao.commitTransaction();
        } catch (Exception e) {
            log.error("删除发票失败", e);
            try {
                dao.endTransaction();
            } catch (Exception e1) {
                log.debug("删除发票失败", e1);
            }// 事物结束
            isSuccess = false;
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.error("删除发票失败", e);
            }
        }
        return isSuccess;
    }

    @SuppressWarnings("unused")
    private static String replaceHTML(String str) {
        if (str == null)
            return str;
        str = str.replaceAll("\"", "&quot;");
        str = str.replaceAll("<", "&lt;");
        str = str.replaceAll(">", "&gt;");
        str = str.replaceAll("\\\\", "\\\\\\\\");
        str = str.replaceAll("'", "&#39;");
        return str;
    }

    @SuppressWarnings("unchecked")
    private static boolean notIn(String str, HashSet arr) {
        for (Iterator i = arr.iterator(); i.hasNext();) {
            if (str.equals(i.next().toString())) {
                return true;
            }
        }

        return false;
    }

    private String getSellInvoiceContent(MakeInvoiceAddDto makeInvoiceAddDto,
            SellInvoiceDto sellInvoiceDto,
            List<SellInvoiceDetailDto> SellInvoiceDetailList, String userName) {
        StringBuffer sBuff = new StringBuffer();
        sBuff.append("开票申请单号：");
        sBuff.append(makeInvoiceAddDto.getId());
        sBuff.append("，");
        sBuff.append("客户名称：");
        sBuff.append(makeInvoiceAddDto.getCustomerName());
        sBuff.append("，");
        sBuff.append("申请日期：");
        sBuff.append(makeInvoiceAddDto.getDate());
        sBuff.append(";\r\n");
        sBuff.append("发票号：");
        sBuff.append(sellInvoiceDto.getNumber());
        sBuff.append("，");
        sBuff.append("发票金额：");
        sBuff.append(sellInvoiceDto.getMoney());
        sBuff.append("，");
        sBuff.append("开票日期：");
        sBuff.append(sellInvoiceDto.getDate());
        sBuff.append(";\r\n");

        sBuff.append("录入人：");
        sBuff.append(userName);
        sBuff.append("，");
        sBuff.append("删除日期：");
        sBuff.append(sdf.format(new Date()));
        sBuff.append(";\r\n");
        for (int i = 0; i < SellInvoiceDetailList.size(); i++) {
            SellInvoiceDetailDto sellInvoiceDetailDto = SellInvoiceDetailList.get(i);

            sBuff.append("发货单号：");
            sBuff.append(sellInvoiceDetailDto.getSendGoodsId());
            sBuff.append("，");
            sBuff.append("产品编码：");
            sBuff.append(sellInvoiceDetailDto.getProductCode());
            sBuff.append("，");
            sBuff.append("产品名称：");
            sBuff.append(sellInvoiceDetailDto.getProductName());
            sBuff.append("，");
            sBuff.append("规格型号：");
            sBuff.append(sellInvoiceDetailDto.getProductType());
            sBuff.append("，");
            sBuff.append("开票数：");
            sBuff.append(sellInvoiceDetailDto.getCount());
            sBuff.append("，");
            sBuff.append("开票金额：");
            sBuff.append(sellInvoiceDetailDto.getMakeInvoicePrice());
            sBuff.append(";\r\n");

        }
        sBuff.append("备注信息：");
        sBuff.append(sellInvoiceDto.getRemark());
        sBuff.append(";\r\n");
        return sBuff.toString();
    }

    /**
     * K3确认开票，修改状态为开票成功
     * 
     * @param invoiceDto
     * @return
     */
    @Override
    public boolean k3ConfirmInvoice(InvoiceInfoDto invoiceDto) {

        try {
            dao.beginTransaction();// 事物开始
            dao.update("invoice_sqlMap.updateInvoiceForConfirm", invoiceDto);// 修改开票表状态
            dao.commitTransaction();
            return true;
        } catch (Exception e) {
            log.error("K3开票确认错误", e);
            try {
                dao.endTransaction();
            } catch (Exception e1) {
                log.error("K3开票确认错误", e1);
            }
            return false;
        } finally {
            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.error("K3开票确认错误", e);
                return false;
            }
        }

    }
    private boolean addTodoWorks(Integer count, String userId, Integer workId) {
        ITodoWorkService todoWork = (ITodoWorkService) Container
                .getBean("todoWrokServiceImpl");
        WorkEntity work = null;
        work = new WorkEntity();
        work.setCount(count);
        work.setUserId(userId);
        work.setWorkId(workId);
        return todoWork.addTodoWorks(work);
    }
}