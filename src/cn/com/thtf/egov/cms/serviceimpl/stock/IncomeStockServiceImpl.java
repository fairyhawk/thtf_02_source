package cn.com.thtf.egov.cms.serviceimpl.stock;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.BaseService;
import cn.com.thtf.egov.cms.application.NewIDao;
import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.constant.Constants;
import cn.com.thtf.egov.cms.dto.BuyContractDto;
import cn.com.thtf.egov.cms.dto.CompanyInfoDto;
import cn.com.thtf.egov.cms.dto.InStockProductDto;
import cn.com.thtf.egov.cms.dto.IncomeOfListDto;
import cn.com.thtf.egov.cms.dto.IncomeOfSendEmailDto;
import cn.com.thtf.egov.cms.dto.IncomeStockListDto;
import cn.com.thtf.egov.cms.dto.IncomeStoreRoomDto;
import cn.com.thtf.egov.cms.dto.ReceiveGoodsDetailDto;
import cn.com.thtf.egov.cms.dto.StockOfIncomeDto;
import cn.com.thtf.egov.cms.dto.saleReturnGoodsViewSelfOfIncomeDto;
import cn.com.thtf.egov.cms.entity.CustomerCreditDetailEntity;
import cn.com.thtf.egov.cms.entity.CustomerCreditEntity;
import cn.com.thtf.egov.cms.entity.InStockEntity;
import cn.com.thtf.egov.cms.entity.MailAddresseeEntity;
import cn.com.thtf.egov.cms.entity.MailEntity;
import cn.com.thtf.egov.cms.entity.ProductEntity;
import cn.com.thtf.egov.cms.entity.ReceiveGoodsDetailEntity;
import cn.com.thtf.egov.cms.entity.StockEntity;
import cn.com.thtf.egov.cms.iservice.purchase.IInStockService;
import cn.com.thtf.egov.cms.iservice.stock.IIncomeStockService;
import cn.com.thtf.egov.cms.util.Container;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class IncomeStockServiceImpl extends BaseService implements IIncomeStockService{
	private NewIDao dao;

    private static Logger log = LoggerFactory.getLogger(IncomeStockServiceImpl.class);
    private java.text.SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List getStockList(IncomeStockListDto incomeStockListDto, NewPage page) {
		List incomeStockList=null;
		try {
			/** 获取此库房管理员的区域及库房id */
			List<IncomeOfListDto> stockList=dao.queryForlist("stock_sqlMap.getStockIdAndProductTypeId", incomeStockListDto.getUserId());
			incomeStockListDto.setStockList(stockList);
			incomeStockList= queryRecords(dao, "getIncomeStockList.getData", incomeStockListDto, page);
		} catch (Exception e) {
			log.error("入库管理列表错误",e);
		}
		return incomeStockList;
	}
	@SuppressWarnings("rawtypes")
    public List getStoreRoomList(){
		List storeRoomList=null;
		try {
			storeRoomList= dao.queryForlist("stock_sqlMap.getStoreRoom", "");
		} catch (Exception e) {
			log.error("入库管理 库房名称 错误",e);
		}
		return storeRoomList;
	}
	@SuppressWarnings("unchecked")
    public int modifyIncomeStoreroomOfIntock(IncomeStoreRoomDto incomeStoreRoomDto){
		int isSuccess=0;
		try {
			/** 判断是否通过 0否 1是*/
			int pass=incomeStoreRoomDto.getIsSuccess();
			dao.beginTransaction();
			/** 更改状态和评审意见*/
			isSuccess= dao.update("stock_sqlMap.modifyIncomeStoreroomOfIntock", incomeStoreRoomDto);
			/** 通过*/
			if(pass==1){
				
		        /** 发送email */
				/** 发送email */
				List<IncomeOfSendEmailDto> incomeOfSendEmailList=null;
				MailEntity mailEntity = new MailEntity();
				String subject=null;
				if("1".equals(incomeStoreRoomDto.getBillType())){
					incomeOfSendEmailList=dao.queryForlist("stock_sqlMap.getIncomeOfSendEmailByInStoke", incomeStoreRoomDto.getId());
					mailEntity.setType(2);
					subject = new StringBuffer("入库_").append(incomeStoreRoomDto.getId()).toString();
				}
				if("2".equals(incomeStoreRoomDto.getBillType())){
					incomeOfSendEmailList=dao.queryForlist("stock_sqlMap.getIncomeOfSendEmailBySellReturn", incomeStoreRoomDto.getId());	
					mailEntity.setType(4);
					 subject = new StringBuffer("退货_").append(incomeStoreRoomDto.getId()).toString();
				}
				if("3".equals(incomeStoreRoomDto.getBillType())){
				    /* 公司税 */
		            CompanyInfoDto companyInfo = (CompanyInfoDto) dao.queryForObject(
		                    "base_sqlMap.companyInfo", null);
		            BigDecimal taxRate = new BigDecimal(companyInfo.getTax_rate()).add(
		                    new BigDecimal("100")).divide(new BigDecimal("100"), 15,
		                    BigDecimal.ROUND_HALF_UP);
				    incomeOfSendEmailList=dao.queryForlist("stock_sqlMap.getIncomeOfSendEmailByMove", incomeStoreRoomDto.getId());
					for (IncomeOfSendEmailDto incomeOfSendEmailDto : incomeOfSendEmailList) {
					    incomeOfSendEmailDto.setPrice(incomeOfSendEmailDto.getPrice().multiply(taxRate)
	                            .setScale(2, BigDecimal.ROUND_HALF_UP));
					    incomeOfSendEmailDto.setSumValue(incomeOfSendEmailDto.getPrice().multiply(
	                            new BigDecimal(incomeOfSendEmailDto.getCount())));
                    }
				    
				    mailEntity.setType(6);
					 subject = new StringBuffer("移库入库_").append(incomeStoreRoomDto.getId()).toString();
				}
				if("4".equals(incomeStoreRoomDto.getBillType())){
					/* 公司税 */
		            CompanyInfoDto companyInfo = (CompanyInfoDto) dao.queryForObject(
		                    "base_sqlMap.companyInfo", null);
		            BigDecimal taxRate = new BigDecimal(companyInfo.getTax_rate()).add(
		                    new BigDecimal("100")).divide(new BigDecimal("100"), 15,
		                    BigDecimal.ROUND_HALF_UP);
				    incomeOfSendEmailList=dao.queryForlist("stock_sqlMap.getIncomeOfSendEmailBySample", incomeStoreRoomDto.getId());
					for (IncomeOfSendEmailDto incomeOfSendEmailDto : incomeOfSendEmailList) {
					    incomeOfSendEmailDto.setPrice(incomeOfSendEmailDto.getPrice().multiply(taxRate)
	                            .setScale(2, BigDecimal.ROUND_HALF_UP));
					    incomeOfSendEmailDto.setSumValue(incomeOfSendEmailDto.getPrice().multiply(
	                            new BigDecimal(incomeOfSendEmailDto.getCount())));
                    }
					mailEntity.setType(8);
					 subject = new StringBuffer("样品归还_").append(incomeStoreRoomDto.getId()).toString();
				}
				
				 Map<String, List<IncomeOfSendEmailDto>> ftlPara = new HashMap<String, List<IncomeOfSendEmailDto>>();
		            ftlPara.put("incomeList", incomeOfSendEmailList);

		            Configuration config = Container.getTemplateConfig();
		            Template template = config.getTemplate(Constants.K3_INCOMESTOCK_TEMPLATE);
		            StringWriter outWriter = new StringWriter();
		            template.process(ftlPara, outWriter);
		            
		            mailEntity.setUserName(incomeOfSendEmailList.get(0).getUserName());
		            mailEntity.setSubject(subject);
		            mailEntity.setText(outWriter.toString());
		            mailEntity.setFlag(0);
		            
		            String mailId = dao.insert("mail_sqlMap.insertMail", mailEntity);
		            MailAddresseeEntity addresseeEntity = new MailAddresseeEntity();
		            addresseeEntity.setMailId(Integer.parseInt(mailId));
		            addresseeEntity.setMail(Constants.K3_TO_ADDRESS);
		            addresseeEntity.setName(Constants.K3_TO_NAME);

		            dao.insert("mail_sqlMap.insertMailAddressee", addresseeEntity);
		            /** 发送email 结束*/ 
		            
		            
				//System.out.println(incomeStoreRoomDto.getJsonData());
				String newJsonDate="{resultSum:"+incomeOfSendEmailList.size()+",rows:[";
				for(int i=0;i<incomeOfSendEmailList.size();i++){
					IncomeOfSendEmailDto incomeOfSendEmailDto=(IncomeOfSendEmailDto)incomeOfSendEmailList.get(i);
					if("2".equals(incomeStoreRoomDto.getBillType())){
						newJsonDate+="{productId:"+incomeOfSendEmailDto.getProductId()+",incomeSum:"+incomeOfSendEmailDto.getCount()+",incomeMoney:"+incomeOfSendEmailDto.getSellPrice()+",sellMoney:"+incomeOfSendEmailDto.getPrice()+"},";
					}else if("3".equals(incomeStoreRoomDto.getBillType()) || "4".equals(incomeStoreRoomDto.getBillType())){//未加税
						newJsonDate+="{productId:"+incomeOfSendEmailDto.getProductId()+",incomeSum:"+incomeOfSendEmailDto.getCount()+",incomeMoney:"+incomeOfSendEmailDto.getSellPrice()+"},";
					}else{
						newJsonDate+="{productId:"+incomeOfSendEmailDto.getProductId()+",incomeSum:"+incomeOfSendEmailDto.getCount()+",incomeMoney:"+incomeOfSendEmailDto.getPrice()+"},";
					}
					
				}
				newJsonDate=newJsonDate.substring(0,newJsonDate.lastIndexOf(","));
				newJsonDate+="]}";
				incomeStoreRoomDto.setJsonData(newJsonDate);
				//System.out.println("NEW:"+newJsonDate);
				org.json.JSONObject json = new JSONObject(incomeStoreRoomDto.getJsonData());
				String customerCreditId="0";
				String sendGoodsId="0";
				String oldTimesTamp=null;
				//dao.startBatch();
				/** 销售退货单专用 客户信用 */
				if("2".equals(incomeStoreRoomDto.getBillType())){
					/** 客户信用id */
					customerCreditId=incomeStoreRoomDto.getCustomerCreditId();
					/** 销售单 发货单号 */
					sendGoodsId=incomeStoreRoomDto.getSendGoodsId();
					/** 客户信用 时间戳 */
					oldTimesTamp=(String)dao.queryForObject("stock_sqlMap.getCustomerTimesTamp", customerCreditId);
					
				}
				
				/** 循环更新产品和库存*/
				for(int i=0;i<json.getInt("resultSum");i++){
					JSONObject rows = json.getJSONArray("rows").getJSONObject(i);
					/** 产品id*/
					int productId = rows.getInt("productId");
					/** 入库数量*/
					int incomeSum = rows.getInt("incomeSum");
					/** 入库单价*/
					double incomeMoney = rows.getDouble("incomeMoney");
					/** 取库存时间戳 */
					StockOfIncomeDto stockOfIncomeDto=new StockOfIncomeDto();
					stockOfIncomeDto.setStockroomId(Integer.parseInt(incomeStoreRoomDto.getStockroomId()));
					stockOfIncomeDto.setProductId(productId);
					StockEntity stockEntity=(StockEntity)dao.queryForObject("stock_sqlMap.getTimestamp", stockOfIncomeDto);
					
					ProductEntity productEntity=new ProductEntity();
					productEntity.setId(productId);
					productEntity.setMoney(new BigDecimal(incomeMoney*incomeSum));
					/** 入库单专用 增值税 */
					if("1".equals(incomeStoreRoomDto.getInvoiceType()) && "1".equals(incomeStoreRoomDto.getBillType())){
						BigDecimal  moneyOfDouble=new BigDecimal(incomeMoney);//入库单价
						double tax=Double.parseDouble(incomeStoreRoomDto.getTaxRate())/100+1;//税率
						moneyOfDouble=moneyOfDouble.divide(new BigDecimal(tax),15,BigDecimal.ROUND_HALF_UP);//入库单价
						
						//double moneyOfDouble=incomeMoney/(Double.parseDouble(incomeStoreRoomDto.getTaxRate())/100+1);
						BigDecimal  moneyDecimal=moneyOfDouble.multiply(new BigDecimal(incomeSum));//入库总价
						productEntity.setMoney(moneyDecimal);//.divide(new BigDecimal(1), BigDecimal.ROUND_HALF_UP)
					}
					/** 销售退货单专用 客户信用 */
					if("2".equals(incomeStoreRoomDto.getBillType())){
						double sellMoney = rows.getDouble("sellMoney");
						CustomerCreditDetailEntity customerCreditDetailEntity=new CustomerCreditDetailEntity();
						customerCreditDetailEntity.setCustomerCreditId(Integer.parseInt(customerCreditId));
						customerCreditDetailEntity.setSendGoodsId(sendGoodsId);
						customerCreditDetailEntity.setProductId(productId);
						customerCreditDetailEntity.setMoney(new BigDecimal(sellMoney*incomeSum));
						/** 更改 客户信用 */
						dao.update("stock_sqlMap.modifyCustomerCreditDetailOfIncome", customerCreditDetailEntity);
					}
					/** 存入库存金额 */
					dao.update("stock_sqlMap.modifyStockMoneyOfIncome", productEntity);
					/** 存入库存数量 */
					if(stockEntity==null){
						/** 插入库存数量 */
						stockOfIncomeDto.setNum(incomeSum);
						dao.update("stock_sqlMap.addStockCountOfIncome", stockOfIncomeDto);
					}else{
						String timestamp=stockEntity.getTimeStamp().toString();
						int id=stockEntity.getId();
						stockOfIncomeDto.setOldTimeStamp(timestamp);
						stockOfIncomeDto.setTimeStamp(sdf.format(new Date()));
						stockOfIncomeDto.setNum(stockEntity.getNum()+incomeSum);
						stockOfIncomeDto.setId(id);
						/** 存入库存数量 */
						dao.update("stock_sqlMap.modifyStockCountOfIncome", stockOfIncomeDto);
					}
				}
				//dao.executeBatch();
				/** 销售退货单专用 客户信用 */
				if("2".equals(incomeStoreRoomDto.getBillType())){
					CustomerCreditEntity customerCreditEntity=new CustomerCreditEntity();
					customerCreditEntity.setId(Integer.parseInt(customerCreditId));
					customerCreditEntity.setTimeStamp(new Date());
					customerCreditEntity.setDatetime(oldTimesTamp);
					/** 销售退货单专用 更改时间戳 */
					dao.update("stock_sqlMap.modifyCustomerCreditOfIncome", customerCreditEntity);
				}
			
			}
			dao.commitTransaction();
		} catch (Exception e) {
			log.error("评审 入库单 错误",e);
		}finally {
            try {
                dao.endTransaction();
            } catch (Exception e) {
                log.error("评审 入库单 错误" + e);
            }
        }
		return isSuccess;
	}
	@SuppressWarnings("unchecked")
	public int modifyIncomeStoreroomOfIn(IncomeStoreRoomDto incomeStoreRoomDto){

		int isSuccess=0;
		try {
			String inStoreId=incomeStoreRoomDto.getId();
			IInStockService inStockService=(IInStockService)Container.getBean("inStockServiceImp");
			InStockEntity inStockInfo = inStockService.getInStock(inStoreId);
			int stockroomId=inStockInfo.getStockroomId();
			/** 更改状态和评审意见*/
			isSuccess= dao.update("stock_sqlMap.modifyIncomeStoreroomOfIntock", incomeStoreRoomDto);
	        /** 收获地址信息 */
	        ReceiveGoodsDetailEntity receiveGoodsPara = new ReceiveGoodsDetailEntity();
	        receiveGoodsPara.setId(inStockInfo.getReceiveGoodsDetailId());

	        List<ReceiveGoodsDetailDto> receiveGoodsResult = inStockService
	                .getReceiveGoodsDetail(receiveGoodsPara);
	        /** 产品信息 */
	        ReceiveGoodsDetailDto productPara = new ReceiveGoodsDetailDto();
	        productPara.setBuyContractId(inStockInfo.getBuyContractId());
	        productPara.setCompanyType(receiveGoodsResult.get(0).getCompanyType());
	        productPara.setAddressId(receiveGoodsResult.get(0).getAddressId());
	        productPara.setInStockId(inStoreId);

	        List<InStockProductDto> productList = inStockService
	                .getProductListView(productPara);
	        BuyContractDto buyContract = inStockService.getBuyContract(inStockInfo.getBuyContractId());
	        /** 发票类型 税率 */
	        String taxRate=buyContract.getTaxRate();
	        String invoiceType=buyContract.getInvoiceType();
	        
	        
	        for(int i=0;i<productList.size();i++){
	        	InStockProductDto inStockProductDto=productList.get(i);	 
	        	int productId=Integer.parseInt(inStockProductDto.getProductId());
	        	int incomeSum=inStockProductDto.getCount();
	        	BigDecimal incomeMoney=inStockProductDto.getBuyContractDetailPrice();
	        	
				/** 取库存时间戳 */
				StockOfIncomeDto stockOfIncomeDto=new StockOfIncomeDto();
				stockOfIncomeDto.setStockroomId(stockroomId);
				stockOfIncomeDto.setProductId(productId);
				StockEntity stockEntity=(StockEntity)dao.queryForObject("stock_sqlMap.getTimestamp", stockOfIncomeDto);
				if(stockEntity==null){
					log.debug("stock 表没有找到数据！");
					//isSuccess=0;
				}
				ProductEntity productEntity=new ProductEntity();
				productEntity.setId(productId);
				productEntity.setMoney(incomeMoney.multiply(new BigDecimal(incomeSum)));
				
				/** 入库单专用 增值税 */
				if("1".equals(invoiceType) && !"0".equals(taxRate)){
					BigDecimal moneyOfDouble=incomeMoney.divide(new BigDecimal(Double.parseDouble(taxRate)/100+1),15,BigDecimal.ROUND_HALF_UP);
					BigDecimal  moneyDecimal=moneyOfDouble.multiply(new BigDecimal(incomeSum));
					//productEntity.setMoney(moneyDecimal.divide(new BigDecimal(1), BigDecimal.ROUND_HALF_UP));
					productEntity.setMoney(moneyDecimal);
				}
				/** 存入库存金额 */
				dao.update("stock_sqlMap.modifyStockMoneyOfIncome", productEntity);
				/** 存入库存数量 */
				if(stockEntity==null){
					/** 插入库存数量 */
					stockOfIncomeDto.setNum(incomeSum);
					dao.update("stock_sqlMap.addStockCountOfIncome", stockOfIncomeDto);
				}else{
					String timestamp=stockEntity.getTimeStamp().toString();
					int id=stockEntity.getId();
					stockOfIncomeDto.setOldTimeStamp(timestamp);
					stockOfIncomeDto.setTimeStamp(sdf.format(new Date()));
					stockOfIncomeDto.setNum(stockEntity.getNum()+incomeSum);
					stockOfIncomeDto.setId(id);
					/** 存入库存数量 */
					dao.update("stock_sqlMap.modifyStockCountOfIncome", stockOfIncomeDto);
				}
	        }
	        /** 发送email */
			List<IncomeOfSendEmailDto> incomeOfSendEmailList=null;
			MailEntity mailEntity = new MailEntity();
			String subject=null;
				incomeOfSendEmailList=dao.queryForlist("stock_sqlMap.getIncomeOfSendEmailByInStoke", incomeStoreRoomDto.getId());
				mailEntity.setType(2);
				subject = new StringBuffer("入库_").append(incomeStoreRoomDto.getId()).toString();
				Map<String, List<IncomeOfSendEmailDto>> ftlPara = new HashMap<String, List<IncomeOfSendEmailDto>>();
	            ftlPara.put("incomeList", incomeOfSendEmailList);

	            Configuration config = Container.getTemplateConfig();
	            Template template = config.getTemplate(Constants.K3_INCOMESTOCK_TEMPLATE);
	            StringWriter outWriter = new StringWriter();
	            template.process(ftlPara, outWriter);
	            
	            mailEntity.setUserName(incomeOfSendEmailList.get(0).getUserName());
	            mailEntity.setSubject(subject);
	            mailEntity.setText(outWriter.toString());
	            mailEntity.setFlag(0);
	            
	            String mailId = dao.insert("mail_sqlMap.insertMail", mailEntity);
	            MailAddresseeEntity addresseeEntity = new MailAddresseeEntity();
	            addresseeEntity.setMailId(Integer.parseInt(mailId));
	            addresseeEntity.setMail(Constants.K3_TO_ADDRESS);
	            addresseeEntity.setName(Constants.K3_TO_NAME);

	            dao.insert("mail_sqlMap.insertMailAddressee", addresseeEntity);
	            /** 发送email 结束*/ 
	        isSuccess=1;
		} catch (Throwable e) {
			isSuccess=0;
			log.error("评审 入库单 错误",e);
		}
		return isSuccess;
	
	}
	public NewIDao getDao() {
		return dao;
	}
	public void setDao(NewIDao dao) {
		this.dao = dao;
	}
    public saleReturnGoodsViewSelfOfIncomeDto getReturnGoodsById(String returnGoodsId) {
    	saleReturnGoodsViewSelfOfIncomeDto saleReturnGoodsViewSelfDto = new saleReturnGoodsViewSelfOfIncomeDto();
        try {
            saleReturnGoodsViewSelfDto = (saleReturnGoodsViewSelfOfIncomeDto) dao.queryForObject(
                    "stock_sqlMap.getReturnGoodsByIdOfIncome", returnGoodsId);
        } catch (Exception e) {
            log.error("获取信用出错 ", e);
        }
        return saleReturnGoodsViewSelfDto;
    }
}
