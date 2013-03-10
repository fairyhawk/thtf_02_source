/**
 * ClassName  IWayBillViewService
 *
 * History
 * Create User: jiangmingxing
 * Create Date: 2010-5-13
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.iservice.wayBill;

import cn.com.thtf.egov.cms.dto.WayBillViewDto;

/**
 * 
 * @author jiangmingxing
 */

public interface IWayBillViewService {
	/**
	 * 获取运单查看ID
	 * 
	 * @return
	 * @throws Exception
	 */
	public WayBillViewDto getWayBillById(String box_id);

}
