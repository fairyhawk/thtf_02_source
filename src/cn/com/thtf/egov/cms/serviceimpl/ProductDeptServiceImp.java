/**
 * ClassName  ProductDeptServiceImp
 *
 * History
 * Create User: Administrator
 * Create Date: 2009-12-16
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
import cn.com.thtf.egov.cms.dto.ProductDeptInfoDto;
import cn.com.thtf.egov.cms.dto.UserDeptInfoDto;
import cn.com.thtf.egov.cms.entity.StockroomEntity;
import cn.com.thtf.egov.cms.iservice.IProductDeptService;

/**
 * ProductDeptServiceImp
 * 
 * @author Administrator
 * 
 */
public class ProductDeptServiceImp implements IProductDeptService {

    /** IDao */
    @SuppressWarnings("unchecked")
    private IDao dao;

    private static Logger log = LoggerFactory.getLogger(ProductDeptServiceImp.class);

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.IProductDeptService#queryProductDept(cn
     * .com.thtf.egov.cms.application.Page)
     */
    @SuppressWarnings("unchecked")
    public ListRange<ProductDeptInfoDto> queryProductDept(Page page) throws Exception {
        return (ListRange<ProductDeptInfoDto>) dao.queryRecordsByPage(
                "pdept.productDeptAll", null, page);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.IProductDeptService#addProductDept(cn.com
     * .thtf.egov.cms.dto.ProductDeptInfo)
     */
    @SuppressWarnings("unchecked")
    public String addProductDept(ProductDeptInfoDto pinfo) {
        String resultStr = null;
        try {
            dao.insert("base_sqlMap.addProductDept", pinfo);
        } catch (Exception e) {
            resultStr = "添加失败！";
        }
        return resultStr;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.IProductDeptService#showUpdateProductDEpt
     * (cn.com.thtf.egov.cms.dto.ProductDeptInfo)
     */
    @SuppressWarnings("unchecked")
    public ProductDeptInfoDto showUpdateProductDept(Integer id) throws Exception {
        return (ProductDeptInfoDto) dao.get("base_sqlMap.gaiProductDept", id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.IProductDeptService#saveProductDept(cn.
     * com.thtf.egov.cms.dto.ProductDeptInfo)
     */
    @SuppressWarnings("unchecked")
    public String saveProductDept(ProductDeptInfoDto pinfo) {
        String resultStr = null;
        try {
            dao.getSqlMap().startTransaction();
            dao.update("base_sqlMap.updateProductDept", pinfo);
            List<StockroomEntity> list = dao.queryForlist(
                    "base_sqlMap.productDeptIdselectStockroom", pinfo.getId());
            if (list.size() > 0) {
                for (StockroomEntity se : list) {
                    StringBuffer sb = new StringBuffer();
                    sb.append(pinfo.getName());
                    sb.append(se.getShortName());
                    log.debug("更新库房名称为：" + sb.toString());
                    se.setName(sb.toString());
                    dao.update("base_sqlMap.productSaveUpdateDepot", se);
                }
            }
            dao.getSqlMap().commitTransaction();
        } catch (Exception e) {
            resultStr = "修改失败！";
            log.error("update error", e);
        } finally {
            try {
                dao.getSqlMap().endTransaction();
            } catch (Exception e) {
                log.error("修改事物回滚失败！", e);
            }
        }
        return resultStr;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.IProductDeptService#deleteProductDept(java
     * .lang.Integer)
     */
    @SuppressWarnings("unchecked")
    public String deleteProductDept(String idStr) throws Exception {
        String result = "";
        String[] id = idStr.split(",");
        try {
            dao.getSqlMap().startTransaction();
            dao.getSqlMap().startBatch();
            for (int i = 0; i < id.length; i++) {
                dao.delete("base_sqlMap.deleteProductDept", Integer.parseInt(id[i]));
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
     * cn.com.thtf.egov.cms.iservice.IProductDeptService#isExit(java.lang.String
     * , java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public Integer isExit(ProductDeptInfoDto pinfo) throws Exception {
        return (Integer) dao.get("base_sqlMap.isProductExits", pinfo);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.IProductDeptService#queryProductDeptName()
     */
    @SuppressWarnings("unchecked")
    public List queryProductDeptName() throws Exception {
        List list = (List) dao.queryRecordsAll("base_sqlMap.productDeptName");
        return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.IProductDeptService#queryProductDept1()
     */
    @SuppressWarnings("unchecked")
    public List<UserDeptInfoDto> queryProductDept1() throws Exception {
        return (List<UserDeptInfoDto>) dao.queryRecordsAll("base_sqlMap.productDeptAll1");
    }

    /*
     * (non-Javadoc)
     * 
     * @seecn.com.thtf.egov.cms.iservice.IProductDeptService#
     * getProductDeptByProductTypeId(java.lang.Integer)
     */
    @SuppressWarnings("unchecked")
    public ProductDeptInfoDto getProductDeptByProductTypeId(Integer productTypeId)
            throws Exception {
        return (ProductDeptInfoDto) dao.get(
                "base_sqlMap.queryProductDeptByProductTypeId", productTypeId);
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
    @SuppressWarnings("unchecked")
    public void setDao(IDao dao) {
        this.dao = dao;
    }

}
