/**
 * ClassName  IWayBillService
 *
 * History
 * Create User: liuqg
 * Create Date: 2010-5-10
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.iservice.wayBill;

import java.util.List;

import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.dto.WayBillViewDto;
import cn.com.thtf.egov.cms.dto.WayBillSearchDto;
import cn.com.thtf.egov.cms.dto.WayBillAllDto;

/**
 * 
 * @author liuqg
 */

public interface IWayBillService {

	/**
	 * 获取运单一览
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<WayBillAllDto> selectWayBillAll(NewPage page,
			Integer logisticsId) throws Exception;

	/**
	 * 根据画面条件检索运单一览
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<WayBillAllDto> selectWayBillByCondition(NewPage page,
			WayBillSearchDto searchWayBillDto) throws Exception;

	/**
	 * 根据装箱单号运单号获取运单信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public WayBillViewDto selectWayBillForModify(String boxId) throws Exception;

	/**
	 * 修改运单信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean updateWayBill(WayBillViewDto billModifyDto) throws Exception;

}
