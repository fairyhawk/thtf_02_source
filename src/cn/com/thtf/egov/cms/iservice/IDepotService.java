/**
 * ClassName  IDepotService
 *
 * History
 * Create User: sxf
 * Create Date: 2009年12月21日
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.iservice;

import java.util.List;

import cn.com.thtf.egov.cms.application.ListRange;
import cn.com.thtf.egov.cms.application.Page;
import cn.com.thtf.egov.cms.dto.DepotInfoDto;

/**
 * IDepotService
 * 
 * @author sxf
 * 
 */
public interface IDepotService {

	/**
	 * 查询所有库房列表
	 * 
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public ListRange<DepotInfoDto> queryDepotAll(Page page) throws Exception;

	/**
	 * 去除库房添加重复
	 * 
	 * @param dinfo
	 * @return
	 * @throws Exception
	 */
	public Integer idExit(DepotInfoDto dinfo) throws Exception;

	/**
	 * 去除库房修改重复
	 * 
	 * @param dinfo
	 * @return
	 * @throws Exception
	 */
	public Integer idDepotUpdateExit(DepotInfoDto dinfo) throws Exception;

	/**
	 * 添加库房
	 * 
	 * @param ainfo
	 * @return
	 */
	public String addDepot(DepotInfoDto ainfo);

	/**
	 * 删除库房
	 * 
	 * @param id
	 * @throws Exception
	 */
	public String deleteDepot(String idStr) throws Exception;

	/**
	 * 根据库房id查询库房信息
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public DepotInfoDto showUpdateDepot(Integer id) throws Exception;

	/**
	 * 修改库房
	 * 
	 * @param depotInfo
	 * @return
	 */
	public String saveUpdateDepot(DepotInfoDto depotInfo);

	/**
	 * 根据库房id查询该库房下所有发货地址列表
	 * 
	 * @param depotId
	 * @return
	 * @throws Exception
	 */
	public List<DepotInfoDto> queryAddressListByDepotId(Integer depotId)
			throws Exception;

	/**
	 * 新增库房发货地址
	 * 
	 * @param info
	 * @return
	 */
	public boolean addDepotAddress(DepotInfoDto info);

	/**
	 * 根据库房发货地址Id查询发货地址
	 * 
	 * @param addressId
	 * @return
	 * @throws Exception
	 */
	public DepotInfoDto queryAddressByAddressId(Integer addressId)
			throws Exception;

	/**
	 * 保存修改之后的库房发货地址
	 * 
	 * @param info
	 * @return TODO
	 * @throws Exception
	 */
	public boolean saveAddress(DepotInfoDto info) throws Exception;

	/**
	 * 删除库房发货地址
	 * 
	 * @param addressId
	 * @throws Exception
	 */
	public void deleteAddress(Integer addressId) throws Exception;

}
