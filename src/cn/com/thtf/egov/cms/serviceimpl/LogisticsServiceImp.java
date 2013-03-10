package cn.com.thtf.egov.cms.serviceimpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.BaseService;
import cn.com.thtf.egov.cms.application.IDao;
import cn.com.thtf.egov.cms.application.ListRange;
import cn.com.thtf.egov.cms.application.NewIDao;
import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.application.Page;
import cn.com.thtf.egov.cms.dto.LogisticsInfoDto;
import cn.com.thtf.egov.cms.entity.LogisticsEntity;
import cn.com.thtf.egov.cms.iservice.ILogisticsService;

/**
 * 处理物流公司业务类
 * 
 * @author sxf
 * 
 */
public class LogisticsServiceImp extends BaseService implements ILogisticsService {

    /** IDao */
    @SuppressWarnings("unchecked")
    private IDao dao;
    /** NewIDao */
    private NewIDao newDao;

    private static final Logger log = LoggerFactory.getLogger(AreaServiceImp.class);

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.ILogisticsService#queryAllLogistics(cn.
     * com.thtf.egov.cms.entity.Logistics,
     * cn.com.thtf.egov.cms.application.NewPage)
     */
    @SuppressWarnings("unchecked")
    public List<LogisticsEntity> queryAllLogistics(LogisticsEntity para, NewPage page) {
        List result = null;
        try {
            result = queryRecords(newDao, "queryAllLogistics.queryDate", para, page);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.ILogisticsService#idExit(java.lang.String)
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
     * cn.com.thtf.egov.cms.iservice.ILogisticsService#addLogistics(cn.com.thtf
     * .egov.cms.dto.LogisticsInfoDto)
     */
    @SuppressWarnings("unchecked")
    public String addLogistics(LogisticsInfoDto info) {
        String result = null;
        try {
            dao.insert("base_sqlMap.addLogistics", info);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            result = "添加失败！";
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.ILogisticsService#deleteLogistics(java.
     * lang.String)
     */
    @SuppressWarnings("unchecked")
    public String deleteLogistics(String idStr) throws Exception {
        String result = "";
        String[] id = idStr.split(",");
        try {
            dao.getSqlMap().startTransaction();
            dao.getSqlMap().startBatch();
            for (int i = 0; i < id.length; i++) {
                dao.delete("base_sqlMap.deleteLogistics", Integer.parseInt(id[i]));
            }
            dao.getSqlMap().executeBatch();
            dao.getSqlMap().commitTransaction();
            // commitTransaction();
        } catch (Exception e) {
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
     * cn.com.thtf.egov.cms.iservice.ILogisticsService#showUpdateLogistics(java
     * .lang.Integer)
     */
    @SuppressWarnings("unchecked")
    public LogisticsInfoDto showUpdateLogistics(Integer id) throws Exception {
        LogisticsInfoDto info = (LogisticsInfoDto) dao.get(
                "base_sqlMap.getLogisticsById", id);
        return info;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.ILogisticsService#saveLogistics(cn.com.
     * thtf.egov.cms.dto.LogisticsInfoDto)
     */
    public String saveLogistics(LogisticsInfoDto info) {
        String result = null;
        try {
            dao.update1("base_sqlMap.saveUpdateLogistics", info);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            result = "修改失败！";
        }
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.ILogisticsService#queryAllLogistics(cn.
     * com.thtf.egov.cms.application.Page,
     * cn.com.thtf.egov.cms.dto.LogisticsInfoDto)
     */
    @SuppressWarnings("unchecked")
    public ListRange queryAllLogistics(Page page, LogisticsInfoDto linfo)
            throws Exception {
        ListRange logisticsList = (ListRange) dao.queryRecordsByPage(
                "logisticsByName.checkLogistics", linfo, page);
        return logisticsList;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.ILogisticsService#checkLogisticsByName(
     * cn.com.thtf.egov.cms.dto.LogisticsInfoDto,
     * cn.com.thtf.egov.cms.application.Page)
     */
    @SuppressWarnings("unchecked")
    public ListRange checkLogisticsByName(LogisticsInfoDto info, Page page)
            throws Exception {
        ListRange logisticsList = (ListRange) dao.queryRecordsByPage(
                "logisticsByName.checkLogistics", info, page);
        return logisticsList;
    }

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

    /**
     * @return the newDao
     */
    public NewIDao getNewDao() {
        return newDao;
    }

    /**
     * @param newDao
     *            the newDao to set
     */
    public void setNewDao(NewIDao newDao) {
        this.newDao = newDao;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.com.thtf.egov.cms.iservice.ILogisticsService#getAllLogistics()
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<LogisticsEntity> getAllLogistics() {
        try {
            return dao.queryForlist("box_sqlMap.selectAllLogistics", null);
        } catch (Exception e) {
            log.error("检索所有物流公司错误", e);
            return null;
        }
    }

}
