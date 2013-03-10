package cn.com.thtf.egov.cms.iservice;

import java.util.List;

import cn.com.thtf.egov.cms.application.ListRange;
import cn.com.thtf.egov.cms.application.Page;
import cn.com.thtf.egov.cms.dto.SupplierInfoDto;

/**
 * 处理供货商业务接口
 * 
 * @author sxf
 * 
 */
public interface ISupplierService {
	/**
	 * 查询所有供货商列表
	 * 
	 * @return
	 * @throws Exception
	 */

	@SuppressWarnings("unchecked")
	public ListRange queryAllSupplier(Page page, SupplierInfoDto linfo)
			throws Exception;

	/**
	 * 根据供货商Id查询供货商信息
	 * 
	 * @param page
	 * @param linfo
	 * @return
	 * @throws Exception
	 */
	public SupplierInfoDto querySupplierById(Integer id) throws Exception;

	/**
	 * 根据供货商Id查询所有联系人列表
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List querySupplierLinkmanListById(Integer id) throws Exception;

	/**
	 * 根据供货商Id查询所有发货地址
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List querySupplierAddressListById(Integer id) throws Exception;

	/**
	 * 去除重复
	 * 
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public Integer idExit(String name) throws Exception;

	/**
	 * 新增供货商
	 * 
	 * @param info
	 * @throws Exception
	 */
	public String addSupplier(SupplierInfoDto info);

	/**
	 * 保存修改后的供货商
	 * 
	 * @param info
	 * @return
	 */
	public String saveSupplier(SupplierInfoDto info);

	/**
	 * 删除供货商
	 * 
	 * @param ainfo
	 * @throws Exception
	 */
	public String deleteSupplier(String idStr) throws Exception;

	/**
	 * 新增供货商联系人
	 * 
	 * @param info
	 * @return
	 */
	public String addSupplierLinkman(SupplierInfoDto info);

	/**
	 * 新增供货商发货地址
	 * 
	 * @param info
	 * @return
	 */
	public String addSupplierAddress(SupplierInfoDto info);

	/**
	 * 根据供货商联系人Id查询联系人
	 * 
	 * @return
	 * @throws Exception
	 */
	public SupplierInfoDto queryLinkmanByLinkmanId(Integer linkmanId)
			throws Exception;

	/**
	 * 根据供货商发货地址Id查询发货地址
	 * 
	 * @return
	 * @throws Exception
	 */
	public SupplierInfoDto queryAddressByAddressId(Integer addressId)
			throws Exception;

	/**
	 * 保存修改之后的供货商联系人
	 * 
	 * @param info
	 * @throws Exception
	 */
	public void saveLinkman(SupplierInfoDto info) throws Exception;

	/**
	 * 保存修改之后的供货商发货地址
	 * 
	 * @param info
	 * @throws Exception
	 */
	public void saveAddress(SupplierInfoDto info) throws Exception;

	/**
	 * 删除供货商联系人
	 * 
	 * @param addressId
	 * @throws Exception
	 */
	public void deleteLinkman(Integer linkmanId) throws Exception;

	/**
	 * 删除供货商发货地址
	 * 
	 * @param addressId
	 * @throws Exception
	 */
	public void deleteAddress(Integer addressId) throws Exception;

}
