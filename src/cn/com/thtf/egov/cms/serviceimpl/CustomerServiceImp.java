package cn.com.thtf.egov.cms.serviceimpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.IDao;
import cn.com.thtf.egov.cms.application.ListRange;
import cn.com.thtf.egov.cms.application.Page;
import cn.com.thtf.egov.cms.dto.CustomerInfoDto;
import cn.com.thtf.egov.cms.entity.UserEntity;
import cn.com.thtf.egov.cms.iservice.ICustomerService;

/**
 * 处理客户业务类
 * 
 * @author sxf
 * 
 */
public class CustomerServiceImp implements ICustomerService {
    @SuppressWarnings("unchecked")
    private IDao dao;

    /**
     * 
     * @return
     */
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

    private static Logger log = LoggerFactory.getLogger(CustomerServiceImp.class);

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.ICustomerService#idExit(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public Integer idExit(String name) throws Exception {
        Integer count = (Integer) dao.get("base_sqlMap.isLogisticsExit", name);
        return count;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.ICustomerService#queryAllCustomer(cn.com
     * .thtf.egov.cms.application.Page,
     * cn.com.thtf.egov.cms.dto.CustomerInfoDto)
     */
    @SuppressWarnings("unchecked")
    public ListRange queryAllCustomer(Page page, CustomerInfoDto linfo) throws Exception {
        ListRange supplierList = (ListRange) dao.queryRecordsByPage(
                "customer.customerAll", linfo, page);
        return supplierList;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.ICustomerService#queryCustomerAddressListById
     * (java.lang.Integer)
     */
    @SuppressWarnings("unchecked")
    public List queryCustomerAddressListById(Integer id) throws Exception {
        List addressList = dao.queryForlist("base_sqlMap.queryCustomerAddressListById",
                id);
        return addressList;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.ICustomerService#queryCustomerById(java
     * .lang.Integer)
     */
    @SuppressWarnings("unchecked")
    public CustomerInfoDto queryCustomerById(Integer id) throws Exception {
        CustomerInfoDto info = (CustomerInfoDto) dao.get("base_sqlMap.queryCustomerById",
                id);
        return info;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.ICustomerService#queryCustomerLinkmanListById
     * (java.lang.Integer)
     */
    @SuppressWarnings("unchecked")
    public List queryCustomerLinkmanListById(Integer id) throws Exception {
        List linkList = dao.queryForlist("base_sqlMap.queryCustomerLinkmanListById", id);
        return linkList;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.ICustomerService#addCustomer(cn.com.thtf
     * .egov.cms.dto.CustomerInfoDto)
     */
    public String addCustomer(CustomerInfoDto info) {
        String is = null;
        try {
            dao.insert1("base_sqlMap.addCustomer", info);
        } catch (Exception e) {
            is = "添加失败！";
        }
        return is;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.ICustomerService#deleteCustomer(java.lang
     * .String)
     */
    @SuppressWarnings("unchecked")
    public String deleteCustomer(String idStr) throws Exception {
        String[] id = idStr.split(",");
        String result = "";
        try {
            dao.getSqlMap().startTransaction();
            dao.getSqlMap().startBatch();
            for (int i = 0; i < id.length; i++) {
                dao.delete("base_sqlMap.deleteCustomer", Integer.parseInt(id[i]));
            }
            dao.getSqlMap().executeBatch();
            dao.getSqlMap().commitTransaction();
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
     * cn.com.thtf.egov.cms.iservice.ICustomerService#addCustomerLinkman(cn.
     * com.thtf.egov.cms.dto.CustomerInfoDto)
     */
    public String addCustomerLinkman(CustomerInfoDto info) {
        String is = null;
        try {
            dao.insert1("base_sqlMap.addCustomerLinkman", info);
        } catch (Exception e) {
            is = "添加失败！";
            log.error("添加联系人失败", e);
        }
        return is;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.ICustomerService#addCustomerAddress(cn.
     * com.thtf.egov.cms.dto.CustomerInfoDto)
     */
    public String addCustomerAddress(CustomerInfoDto info) {
        String is = null;
        try {
            dao.insert1("base_sqlMap.addCustomerAddress", info);
        } catch (Exception e) {
            is = "添加失败！";
        }
        return is;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.ICustomerService#saveAddress(cn.com.thtf
     * .egov.cms.dto.CustomerInfoDto)
     */
    public void saveAddress(CustomerInfoDto info) throws Exception {
        dao.update("base_sqlMap.saveCustomerAddress", info);

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.ICustomerService#saveLinkman(cn.com.thtf
     * .egov.cms.dto.CustomerInfoDto)
     */
    public void saveLinkman(CustomerInfoDto info) throws Exception {
        dao.update("base_sqlMap.saveCustomerLinkman", info);

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.ICustomerService#queryAddressByAddressId
     * (java.lang.Integer)
     */
    @SuppressWarnings("unchecked")
    public CustomerInfoDto queryAddressByAddressId(Integer addressId) throws Exception {
        CustomerInfoDto info = (CustomerInfoDto) dao.get(
                "base_sqlMap.queryCustomerAddressByAddressId", addressId);
        return info;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.ICustomerService#queryLinkmanByLinkmanId
     * (java.lang.Integer)
     */
    @SuppressWarnings("unchecked")
    public CustomerInfoDto queryLinkmanByLinkmanId(Integer linkmanId) throws Exception {
        CustomerInfoDto info = (CustomerInfoDto) dao.get(
                "base_sqlMap.queryCustomerLinkmanByLinkmanId", linkmanId);
        return info;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.ICustomerService#saveCustomer(cn.com.thtf
     * .egov.cms.dto.CustomerInfoDto)
     */
    public String saveCustomer(CustomerInfoDto info) {
        String is = null;
        try {
            dao.update("base_sqlMap.saveCustomer", info);
        } catch (Exception e) {
            is = "修改失败！";
        }
        return is;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.ICustomerService#deleteAddress(java.lang
     * .Integer)
     */
    @SuppressWarnings("unchecked")
    public void deleteAddress(Integer addressId) throws Exception {
        dao.delete("base_sqlMap.deleteCustomerAddress", addressId);

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.ICustomerService#deleteLinkman(java.lang
     * .Integer)
     */
    @SuppressWarnings("unchecked")
    public void deleteLinkman(Integer linkmanId) throws Exception {
        dao.delete("base_sqlMap.deleteCustomerLinkman", linkmanId);

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.ICustomerService#queryCustomerByUserId(
     * cn.com.thtf.egov.cms.application.Page, java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public ListRange queryCustomerByUserId(Page page, String userId, CustomerInfoDto linfo)
            throws Exception {
        linfo.setUserid(userId);
        // UserEntity para = new UserEntity();
        // para.setId(userId);
        ListRange customerU = (ListRange) dao.queryRecordsByPage(
                "getCustomerByUserId.result", linfo, page);
        return customerU;
    }

    /**
     * 
     * @param page
     * @param userId
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public ListRange queryCustomerByUserAreaId(Page page, String userId) throws Exception {
        UserEntity para = new UserEntity();
        para.setId(userId);
        ListRange customerU = (ListRange) dao.queryRecordsByPage(
                "getCustomerByUserId.result", para, page);
        return customerU;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.ICustomerService#queryCustomerByAreaManagerId
     * (cn.com.thtf.egov.cms.application.Page, java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public ListRange queryCustomerByAreaManagerId(Page page, String userId,
            CustomerInfoDto linfo) throws Exception {
        // UserEntity para = new UserEntity();
        // para.setId(userId);
        linfo.setUserid(userId);
        ListRange customerU = (ListRange) dao.queryRecordsByPage(
                "queryCustomerByAreaManagerId.result", linfo, page);
        return customerU;
    }

    /**
     * 查询
     * 
     * @param addressId
     * @throws Exception
     */
    public ListRange queryCustomerByUserAreaMapping(Page page, String userId,
            CustomerInfoDto linfo) throws Exception {
        linfo.setUserid(userId);
        ListRange customerU = (ListRange) dao.queryRecordsByPage(
                "queryCustomerByUserAreaMapping.result", linfo, page);
        return customerU;
    }
}
