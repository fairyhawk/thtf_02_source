/**
 * ClassName  ICustwaybillService
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-8-17
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.iservice.custwaybill;

import java.util.List;

import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.dto.CustwaybillDto;
import cn.com.thtf.egov.cms.dto.WayBillViewDto;

/**
 * ICustwaybillService
 * 
 * @author Lubo
 */
public interface ICustwaybillService {

	/**
	 * 查询运单
	 * 
	 * @param para
	 * @param page
	 * @return
	 */
	public List<CustwaybillDto> getWaybill(CustwaybillDto para, NewPage page);

	/**
	 * 查询运单详情
	 * 
	 * @param id
	 * @return
	 */
	public WayBillViewDto getWayBillDetail(String id);
}
