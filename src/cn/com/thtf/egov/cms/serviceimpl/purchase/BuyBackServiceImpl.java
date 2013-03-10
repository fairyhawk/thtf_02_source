/**
 * ClassName  BuyBackServiceImpl
 *
 * History
 * Create User: liuqg
 * Create Date: 2010-6-9
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.serviceimpl.purchase;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.BaseService;
import cn.com.thtf.egov.cms.application.NewIDao;
import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.dto.AddBuyBackListDto;
import cn.com.thtf.egov.cms.dto.BuyBackListDto;
import cn.com.thtf.egov.cms.dto.BuyBackSearchDto;
import cn.com.thtf.egov.cms.dto.PaymentInfoForBuyBackDto;
import cn.com.thtf.egov.cms.entity.BusiLogEntity;
import cn.com.thtf.egov.cms.entity.ProductTypeEntity;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.iservice.common.ICommonService;
import cn.com.thtf.egov.cms.iservice.purchase.IBuyBackService;
import cn.com.thtf.egov.cms.util.Container;
import cn.com.thtf.egov.cms.util.Util;

/**
 * 采购退款
 * 
 * @author liuqg
 */

public class BuyBackServiceImpl extends BaseService implements IBuyBackService {
	/** log */
	private static Logger log = LoggerFactory
			.getLogger(BuyBackServiceImpl.class);
	/** NewIDao */
	private NewIDao dao;

	/**
	 * @return the log
	 */
	public static Logger getLog() {
		return log;
	}

	/**
	 * @param log
	 *            the log to set
	 */
	public static void setLog(Logger log) {
		BuyBackServiceImpl.log = log;
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

	/**
	 * 采购退款添加
	 * 
	 * @author liuqg
	 * @param backListDto
	 * @return
	 * @throws Exception
	 */
	public boolean addBuyBack(AddBuyBackListDto backListDto) throws Exception {
		try {
			dao.beginTransaction();
			// 插入到采购退款表中
			dao.insert("buyBack_sqlMap.insertBuyBack", backListDto);
			dao.commitTransaction();
			return true;
		} catch (Exception e) {
			log.error("采购退款录入失败", e);
			return false;
		} finally {
			try {
				dao.endTransaction();
			} catch (Exception e) {
				log.debug("采购退款录入失败", e);
				return false;
			}
		}
	}

	/**
	 *查看采购退款
	 * 
	 * @author jiangmx
	 * @param buyBackId
	 * @return BuyBackListDto
	 */
	@Override
	public BuyBackListDto selectBuyBackById(String buyBackId) throws Exception {

		BuyBackListDto result = null;

		try {
			result = (BuyBackListDto) dao.queryForObject(
					"buyBack_sqlMap.selectBuyBackInfo", buyBackId);
		} catch (Exception e) {
			log.error("获取采购退款查看信息错误", e);
		}

		return result;
	}

	/**
	 * 删除采购退款
	 * 
	 * @author 蒋名星
	 */
	@Override
	public boolean removeBuyBack(String buyBackId,UserEntity user) throws Exception {

		boolean isSuccess = false;

		try {
			dao.beginTransaction();
			BuyBackListDto backListDto = (BuyBackListDto)dao.queryForObject("buyBack_sqlMap.selectBuyBackLogInfo", buyBackId);
			BusiLogEntity logEntity = new BusiLogEntity();
			logEntity.setUserid(user.getId());
			logEntity.setUsername(user.getName());
			logEntity.setContent(getBuyBackContent(backListDto));
			logEntity.setBusiType(102);
			ICommonService commonService = (ICommonService) Container
            .getBean("commonServiceImpl");
			boolean addResult = commonService.addBusiLog(logEntity);
            if (addResult) {
            	int countInfo = dao.delete("buyBack_sqlMap.deleteBuyBackInfo",
    					buyBackId);
    			if (countInfo < 1) {
    				dao.endTransaction();
    				return false;
    			}
    			
            } 
			
			dao.commitTransaction();

			isSuccess = true;

		} catch (Exception e) {
			log.debug("删除采购退款失败", e);
			isSuccess = false;
		} finally {

			try {
				dao.endTransaction();
			} catch (Exception e) {
				log.debug("删除采购退款失败", e);
				isSuccess = false;
			}

		}

		return isSuccess;
	}

	/**
	 *查询付款单信息(录入查看时用)
	 * 
	 * @author liuqg
	 * @param id
	 * @return PaymentInfoForBuyBackDto
	 * @throws Exception
	 */
	public PaymentInfoForBuyBackDto selectPaymentById(String id)
			throws Exception {
		PaymentInfoForBuyBackDto payment = null;
		try {
			payment = (PaymentInfoForBuyBackDto) dao.queryForObject(
					"buyBack_sqlMap.selectPaymentById", id);
		} catch (Exception e) {
			log.error("查询付款单信息错误");
		}

		return payment;
	}

	/**
	 * 查询采购退款列表 by zhangzx
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BuyBackListDto> getBuyBackList(NewPage page, UserEntity user)
			throws Exception {
		List result = null;
		try {
			result = queryRecords(dao, "selectBuyBackList.selectBuyBackList",
					user, page);
		} catch (Exception e) {
			log.error("获取采购退款一览错误:", e);
		}
		return result;
	}

	/**
	 * 获得产品分类 by zhangzx
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ProductTypeEntity> getProductType() throws Exception {
		List result = null;
		try {
			result = dao.queryForlist("buyBack_sqlMap.selectProductTypeList",
					null);
		} catch (Exception e) {
			log.error("检索产品分类错误:", e);
		}
		return result;
	}

	/**
	 * 根据检索条件查找记录by zzx
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BuyBackListDto> getBuyBackListByCondition(NewPage page,
			BuyBackSearchDto buyBackSearchDto) throws Exception {
		List result = null;
		try {
			result = queryRecords(dao,
					"selectBuyBackByCondition.selectBuyBackByCondition",
					buyBackSearchDto, page);
		} catch (Exception e) {
			log.error("检索采购退款一览错误:", e);
		}
		return result;
	}

	/**
	 * 采购退款修改
	 * 
	 * @author liuqg
	 * @param backListDto
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean modifyBuyBack(AddBuyBackListDto backListDto)
			throws Exception {
		try {
			dao.beginTransaction();
			// 更新到采购退款表中
			dao.update("buyBack_sqlMap.modifyBuyBack", backListDto);
			dao.commitTransaction();
			return true;
		} catch (Exception e) {
			log.error("修改采购退款失败", e);
			return false;
		} finally {
			try {
				dao.endTransaction();
			} catch (Exception e) {
				log.debug("修改采购退款失败", e);
				return false;
			}
		}
	}
	
	public String getBuyBackContent(BuyBackListDto backListDto) {
		StringBuffer re= new StringBuffer();
		re.append("退款单号:").append(backListDto.getId()).append(",");
		re.append("付款单号:").append(backListDto.getPaymentId()).append(",");
		re.append("退款编号:").append(backListDto.getNo()).append(",");
		re.append("产品分类编号:").append(backListDto.getProductTypeId()).append(",");
		re.append("产品分类名称:").append(backListDto.getProductTypeName()).append(",");
		re.append("供货商编号:").append(backListDto.getSupplierId()).append(",");
		re.append("供货商名称:").append(backListDto.getSupplierName()).append(",");
		re.append("录入日期:").append(backListDto.getDate()).append(",");
		re.append("退款日期:").append(backListDto.getBackDate()).append(",");
		String backway=""; 
		switch (backListDto.getBackWay()) { 
		case 1:
			backway="现金";
			break;
		case 2:
			backway="支票";
			break;
		case 3:
			backway="网银";
			break;
		case 4:
			backway="电汇";
			break;
		case 5:
			backway="银行承兑";
			break;
		case 7:
			backway="其它";
			break;
			
		default:
			backway="";
		}
		
		re.append("退款方式:").append(backway).append(",");
		re.append("凭证号:").append(backListDto.getNumber()).append(",");
		re.append("退款金额:").append(backListDto.getMoney()).append(",");
		re.append("登录名:").append(backListDto.getUserId()).append(",");
		re.append("人员名称:").append(backListDto.getUserName()).append(",");
		re.append("特殊说明:").append(backListDto.getText()).append(",");
		re.append("删除日期:").append(Util.getDateTime());

		return re.toString();
	}
	public static void main(String[] args) {
		System.out.println(Util.getDateTime());
	}
}
