package cn.com.thtf.egov.cms.iservice;

import java.util.List;

import cn.com.thtf.egov.cms.application.ListRange;
import cn.com.thtf.egov.cms.application.Page;
import cn.com.thtf.egov.cms.dto.CustomerInfoDto;

/**
 * 处理客户业务接口
 * 
 * @author sxf
 * 
 */
@SuppressWarnings("unchecked")
public interface ICustomerService {
	/**
	 * 查询所有客户列表
	 * 
	 * @return
	 * @throws Exception
	 */

	public ListRange queryAllCustomer(Page page, CustomerInfoDto linfo)
			throws Exception;

	/**
	 * 根据客户Id查询客户信息
	 * 
	 * @param page
	 * @param linfo
	 * @return
	 * @throws Exception
	 */
	public CustomerInfoDto queryCustomerById(Integer id) throws Exception;

	/**
	 * 根据客户Id查询所有联系人列表
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List queryCustomerLinkmanListById(Integer id) throws Exception;

	/**
	 * 根据客户Id查询所有发货地址
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List queryCustomerAddressListById(Integer id) throws Exception;

	/**
	 * 去除重复
	 * 
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public Integer idExit(String name) throws Exception;

	/**
	 * 新增客户
	 * 
	 * @param info
	 * @throws Exception
	 */
	public String addCustomer(CustomerInfoDto info);

	/**
	 * 保存修改后的客户
	 * 
	 * @param info
	 * @return
	 */
	public String saveCustomer(CustomerInfoDto info);

	/**
	 * 删除客户
	 * 
	 * @param ainfo
	 * @throws Exception
	 */
	public String deleteCustomer(String idStr) throws Exception;

	/**
	 * 新增客户联系人
	 * 
	 * @param info
	 * @return
	 */
	public String addCustomerLinkman(CustomerInfoDto info);

	/**
	 * 新增客户发货地址
	 * 
	 * @param info
	 * @return
	 */
	public String addCustomerAddress(CustomerInfoDto info);

	/**
	 * 根据客户联系人Id查询联系人
	 * 
	 * @return
	 * @throws Exception
	 */
	public CustomerInfoDto queryLinkmanByLinkmanId(Integer linkmanId)
			throws Exception;

	/**
	 * 根据客户发货地址Id查询发货地址
	 * 
	 * @return
	 * @throws Exception
	 */
	public CustomerInfoDto queryAddressByAddressId(Integer addressId)
			throws Exception;

	/**
	 * 保存修改之后的客户联系人
	 * 
	 * @param info
	 * @throws Exception
	 */
	public void saveLinkman(CustomerInfoDto info) throws Exception;

	/**
	 * 保存修改之后的客户发货地址
	 * 
	 * @param info
	 * @throws Exception
	 */
	public void saveAddress(CustomerInfoDto info) throws Exception;

	/**
	 * 删除客户联系人
	 * 
	 * @param addressId
	 * @throws Exception
	 */
	public void deleteLinkman(Integer linkmanId) throws Exception;

	/**
	 * 删除客户发货地址
	 * 
	 * @param addressId
	 * @throws Exception
	 */
	public void deleteAddress(Integer addressId) throws Exception;

	/**
	 * 根据销售经理查询所有的客户
	 * 
	 * @param addressId
	 * @throws Exception
	 */
	public ListRange queryCustomerByUserId(Page page, String userId, CustomerInfoDto linfo)
			throws Exception;

	/**
	 * 查询区域总监下所有销售经理所负责的所有客户
	 * 
	 * @param addressId
	 * @throws Exception
	 */
	public ListRange queryCustomerByAreaManagerId(Page page, String userId, CustomerInfoDto linfo)
			throws Exception;
	
    /**
     * 查询
     * 
     * @param addressId
     * @throws Exception
     */
    public ListRange queryCustomerByUserAreaMapping(Page page, String userId, CustomerInfoDto linfo)
            throws Exception;
}
