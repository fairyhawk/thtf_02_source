/**
 * ClassName  DepotServiceImp
 *
 * History
 * Create User: sxf
 * Create Date: 2009年12月21日
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.serviceimpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.IDao;
import cn.com.thtf.egov.cms.application.ListRange;
import cn.com.thtf.egov.cms.application.Page;
import cn.com.thtf.egov.cms.dto.DepotInfoDto;
import cn.com.thtf.egov.cms.iservice.IDepotService;

/**
 * 库房业务类
 * 
 * @author sxf
 */
public class DepotServiceImp implements IDepotService {

    /** IDao */
    @SuppressWarnings("unchecked")
    private IDao dao;

    private static Logger log = LoggerFactory.getLogger(DepotServiceImp.class);

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.IDepotService#queryDepotAll(cn.com.thtf
     * .egov.cms.application.Page)
     */
    @SuppressWarnings("unchecked")
    public ListRange<DepotInfoDto> queryDepotAll(Page page) throws Exception {
        ListRange<DepotInfoDto> depotList = (ListRange<DepotInfoDto>) dao
                .queryRecordsByPage("dept.depotAll", null, page);
        return depotList;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.IDepotService#idExit(cn.com.thtf.egov.cms
     * .dto.DepotInfo)
     */
    @SuppressWarnings("unchecked")
    public Integer idExit(DepotInfoDto dinfo) throws Exception {
        return (Integer) dao.get("base_sqlMap.isDepotExit", dinfo);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.IDepotService#addDepot(cn.com.thtf.egov
     * .cms.dto.DepotInfo)
     */
    @SuppressWarnings("unchecked")
    public String addDepot(DepotInfoDto ainfo) {
        String resultStr = null;
        try {
            String productDeptName = (String) (dao.queryForlist1(
                    "base_sqlMap.selectProductDeptName", ainfo.getProductDeptId()).get(0));
            StringBuffer productDeptNameStr = new StringBuffer();
            productDeptNameStr.append(productDeptName);
            productDeptNameStr.append(ainfo.getName());
            ainfo.setShortName(ainfo.getName());
            ainfo.setName(productDeptNameStr.toString());
            dao.insert("base_sqlMap.addDepot", ainfo);
        } catch (Exception e) {
            resultStr = "添加失败！";
            log.error("添加库房错误", e);
        }
        return resultStr;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.IDepotService#deleteDepot(java.lang.Integer
     * )
     */
    @SuppressWarnings("unchecked")
    public String deleteDepot(String idStr) throws Exception {
        String result = "";
        String[] id = idStr.split(",");
        try {
            dao.getSqlMap().startTransaction();
            dao.getSqlMap().startBatch();
            for (int i = 0; i < id.length; i++) {
                dao.delete("base_sqlMap.deleteDepot", Integer.parseInt(id[i]));
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
     * cn.com.thtf.egov.cms.iservice.IDepotService#showUpdateDepot(java.lang
     * .Integer)
     */
    @SuppressWarnings("unchecked")
    public DepotInfoDto showUpdateDepot(Integer id) throws Exception {
        return (DepotInfoDto) dao.get("base_sqlMap.getDepotById", id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.IDepotService#saveUpdateDepot(cn.com.thtf
     * .egov.cms.dto.DepotInfo)
     */
    public String saveUpdateDepot(DepotInfoDto depotInfo) {
        String resultStr = null;
        try {
            String productDeptName = (String) (dao.queryForlist1(
                    "base_sqlMap.selectProductDeptName", depotInfo.getProductDeptId()).get(0));
            StringBuffer productDeptNameStr = new StringBuffer();
            productDeptNameStr.append(productDeptName);
            productDeptNameStr.append(depotInfo.getName());
            depotInfo.setShortName(depotInfo.getName());
            depotInfo.setName(productDeptNameStr.toString());
            dao.update1("base_sqlMap.saveUpdateDepot", depotInfo);
        } catch (Exception e) {
            resultStr = "保存修改！";
        }
        return resultStr;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.IDepotService#queryAddressListByDepotId
     * (java.lang.Integer)
     */
    @SuppressWarnings("unchecked")
    public List<DepotInfoDto> queryAddressListByDepotId(Integer depotId) throws Exception {
        return (List<DepotInfoDto>) dao.queryForlist(
                "base_sqlMap.queryAddressListByDepotId", depotId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.IDepotService#addDepotAddress(cn.com.thtf
     * .egov.cms.dto.DepotInfo)
     */
    public boolean addDepotAddress(DepotInfoDto info) {
        boolean resultStr = true;
        try {
            dao.insert1("base_sqlMap.addDepotAddress", info);
        } catch (Exception e) {
            resultStr = false;
        }
        return resultStr;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.IDepotService#queryAddressByAddressId(java
     * .lang.Integer)
     */
    @SuppressWarnings("unchecked")
    public DepotInfoDto queryAddressByAddressId(Integer addressId) throws Exception {
        return (DepotInfoDto) dao.get("base_sqlMap.queryDepotAddressByAddressId",
                addressId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.IDepotService#saveAddress(cn.com.thtf.egov
     * .cms.dto.DepotInfo)
     */
    public boolean saveAddress(DepotInfoDto info) throws Exception {
        Integer i = dao.update("base_sqlMap.saveDepotAddress", info);
        boolean result = false;
        if(i!=0){
            result = true;
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.IDepotService#deleteAddress(java.lang.Integer
     * )
     */
    @SuppressWarnings("unchecked")
    public void deleteAddress(Integer addressId) throws Exception {
        dao.delete("base_sqlMap.deleteDepotAddress", addressId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.IDepotService#idDepotUpdateExit(cn.com.
     * thtf.egov.cms.dto.DepotInfo)
     */
    @SuppressWarnings("unchecked")
    public Integer idDepotUpdateExit(DepotInfoDto dinfo) throws Exception {
        return (Integer) dao.get("base_sqlMap.isDepotUpdateExit", dinfo);
    }

    /**
     * @return the dao
     */
    @SuppressWarnings("unchecked")
    public IDao getDao() {
        return dao;
    }

    /**
     * @param dao
     *            the dao to set
     */
    public void setDao(IDao<?> dao) {
        this.dao = dao;
    }
}
