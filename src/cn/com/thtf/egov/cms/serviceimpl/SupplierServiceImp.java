package cn.com.thtf.egov.cms.serviceimpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.IDao;
import cn.com.thtf.egov.cms.application.ListRange;
import cn.com.thtf.egov.cms.application.Page;
import cn.com.thtf.egov.cms.dto.SupplierInfoDto;
import cn.com.thtf.egov.cms.iservice.ISupplierService;

/**
 * 处理供货商业务类
 * 
 * @author sxf
 * 
 */
public class SupplierServiceImp implements ISupplierService {
	@SuppressWarnings("unchecked")
	private IDao dao;

	@SuppressWarnings("unchecked")
	public IDao getDao() {
		return dao;
	}

	/**
	 * 
	 * @param dao
	 */
	@SuppressWarnings("unchecked")
	public void setDao(IDao dao) {
		this.dao = dao;
	}

	private static final Logger log = LoggerFactory
			.getLogger(SupplierServiceImp.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.ISupplierService#idExit(java.lang.String)
	 */
	@SuppressWarnings( { "unchecked" })
	public Integer idExit(String name) throws Exception {
		Integer count = (Integer) dao.get("base_sqlMap.isLogisticsExit", name);
		return count;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.ISupplierService#queryAllSupplier(cn.com
	 * .thtf.egov.cms.application.Page,
	 * cn.com.thtf.egov.cms.dto.SupplierInfoDto)
	 */
	@SuppressWarnings("unchecked")
	public ListRange queryAllSupplier(Page page, SupplierInfoDto linfo)
			throws Exception {
		ListRange supplierList = (ListRange) dao.queryRecordsByPage(
				"supplier.supplierAll", linfo, page);
		return supplierList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.ISupplierService#querySupplierAddressListById
	 * (java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public List querySupplierAddressListById(Integer id) throws Exception {
		List addressList = dao.queryForlist("base_sqlMap.queryAddressListById",
				id);
		return addressList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.ISupplierService#querySupplierById(java
	 * .lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public SupplierInfoDto querySupplierById(Integer id) throws Exception {
		SupplierInfoDto info = (SupplierInfoDto) dao.get(
				"base_sqlMap.querySupplierById", id);
		return info;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.ISupplierService#querySupplierLinkmanListById
	 * (java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public List querySupplierLinkmanListById(Integer id) throws Exception {
		List linkList = dao
				.queryForlist("base_sqlMap.queryLinkmanListById", id);
		return linkList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.ISupplierService#addSupplier(cn.com.thtf
	 * .egov.cms.dto.SupplierInfoDto)
	 */
	public String addSupplier(SupplierInfoDto info) {
		String is = null;
		try {
			dao.insert1("base_sqlMap.addSupplier", info);
		} catch (Exception e) {
			is = "添加失败";
		}
		return is;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.ISupplierService#deleteSupplier(java.lang
	 * .String)
	 */
	@SuppressWarnings("unchecked")
	public String deleteSupplier(String idStr) throws Exception {
		String result = "";
		String[] id = idStr.split(",");
		try {
			dao.getSqlMap().startTransaction();
			dao.getSqlMap().startBatch();
			for (int i = 0; i < id.length; i++) {
				dao.delete("base_sqlMap.deleteSupplier", Integer
						.parseInt(id[i]));
			}
			dao.getSqlMap().executeBatch();
			dao.getSqlMap().commitTransaction();
			// commitTransaction();
		} catch (Exception e) {
			result = "删除失败！";
			log.error("delete error", e);
		} finally {
			dao.getSqlMap().endTransaction();
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.ISupplierService#addSupplierLinkman(cn.
	 * com.thtf.egov.cms.dto.SupplierInfoDto)
	 */
	public String addSupplierLinkman(SupplierInfoDto info) {
		String is = null;
		try {
			dao.insert1("base_sqlMap.addSupplierLinkman", info);
		} catch (Exception e) {
			is = "添加失败";
		}
		return is;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.ISupplierService#addSupplierAddress(cn.
	 * com.thtf.egov.cms.dto.SupplierInfoDto)
	 */
	public String addSupplierAddress(SupplierInfoDto info) {
		String is = null;
		try {
			dao.insert1("base_sqlMap.addSupplierAddress", info);
		} catch (Exception e) {
			is = "添加失败";
		}
		return is;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.ISupplierService#saveAddress(cn.com.thtf
	 * .egov.cms.dto.SupplierInfoDto)
	 */
	public void saveAddress(SupplierInfoDto info) throws Exception {
		dao.update("base_sqlMap.saveSupplierAddress", info);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.ISupplierService#saveLinkman(cn.com.thtf
	 * .egov.cms.dto.SupplierInfoDto)
	 */
	public void saveLinkman(SupplierInfoDto info) throws Exception {
		dao.update("base_sqlMap.saveSupplierLinkman", info);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.ISupplierService#queryAddressByAddressId
	 * (java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public SupplierInfoDto queryAddressByAddressId(Integer addressId)
			throws Exception {
		SupplierInfoDto info = (SupplierInfoDto) dao.get(
				"base_sqlMap.queryAddressByAddressId", addressId);
		return info;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.ISupplierService#queryLinkmanByLinkmanId
	 * (java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public SupplierInfoDto queryLinkmanByLinkmanId(Integer linkmanId)
			throws Exception {
		SupplierInfoDto info = (SupplierInfoDto) dao.get(
				"base_sqlMap.queryLinkmanByLinkmanId", linkmanId);
		return info;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.ISupplierService#saveSupplier(cn.com.thtf
	 * .egov.cms.dto.SupplierInfoDto)
	 */
	public String saveSupplier(SupplierInfoDto info) {
		String is = null;
		try {
			dao.update("base_sqlMap.saveSupplier", info);
		} catch (Exception e) {
			is = "修改失败";
		}
		return is;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.ISupplierService#deleteAddress(java.lang
	 * .Integer)
	 */
	@SuppressWarnings("unchecked")
	public void deleteAddress(Integer addressId) throws Exception {
		dao.delete("base_sqlMap.deleteSupplierAddress", addressId);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.ISupplierService#deleteLinkman(java.lang
	 * .Integer)
	 */
	@SuppressWarnings("unchecked")
	public void deleteLinkman(Integer linkmanId) throws Exception {
		dao.delete("base_sqlMap.deleteSupplierLinkman", linkmanId);

	}
}
