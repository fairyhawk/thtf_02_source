/**
 * ClassName  DemandServiceImpl
 *
 * History
 * Create User: ChenHuajiang
 * Create Date: 2010-6-8
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.serviceimpl.demand;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.BaseService;
import cn.com.thtf.egov.cms.application.NewIDao;
import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.constant.Constants;
import cn.com.thtf.egov.cms.dto.DemandProductDto;
import cn.com.thtf.egov.cms.iservice.demand.IDemandService;

/**
 * 需求管理
 * 
 * @author ChenHuajiang
 */

public class DemandServiceImpl extends BaseService implements IDemandService {
    /** log */
    private static Logger log = LoggerFactory.getLogger(DemandServiceImpl.class);
    /** NewIDao */
    private NewIDao dao;

    public NewIDao getDao() {
        return dao;
    }

    public void setDao(NewIDao dao) {
        this.dao = dao;
    }

    /*
     * 需求管理列表
     */
    @SuppressWarnings("unchecked")
    @Override
    public List getDemandProducts(HashMap map, NewPage page) {
        log.info("获取需求管理列表信息");
        List list = null;
        try {
            if (map.get("roleId").equals(Constants.ROLE_REGIONAL_DIRECTOR)) {
                DemandProductDto dto = new DemandProductDto();
                dto.setUserAreaProductList(dao.queryForlist(
                        "salesBackContract_sqlMap.getAreaIdAndProductTypeIdOfRoldIs9",
                        map.get("userId")));
                map.put("arrList", dto.getUserAreaProductList());
            }

            // 分页处理
            int pageNum = page.getThisPage();
            int pageSize = page.getPageSize();
            if (pageNum == 1) {
                map.put("fromLimit", 0);
            } else {
                map.put("fromLimit", pageSize * (pageNum - 1));
            }
            map.put("toLimit", pageSize);

            list = queryRecords(dao, "demand.selectAllProducts", map, page);
        } catch (Exception e) {
            log.error("获取需求管理列表信息失败" + e);
        }
        return list;
    }

    /*
     * 需求管理列表--明细
     */
    @SuppressWarnings("unchecked")
    @Override
    public List getDemandProductsDetail(HashMap map, NewPage page) {
        log.info("获取需求管理列表--明细信息");
        List list = null;
        try {
            if (map.get("roleId").equals(Constants.ROLE_REGIONAL_DIRECTOR)) {
                DemandProductDto dto = new DemandProductDto();
                dto.setUserAreaProductList(dao.queryForlist(
                        "salesBackContract_sqlMap.getAreaIdAndProductTypeIdOfRoldIs9",
                        map.get("userId")));
                map.put("arrList", dto.getUserAreaProductList());
            }
            list = queryRecords(dao, "demandDetail.selectAllProductsDetail", map, page);
        } catch (Exception e) {
            log.error("获取需求管理列表--明细失败" + e);
        }
        return list;
    }

    /*
     * 需求管理列表--确认
     */
    @SuppressWarnings("unchecked")
    @Override
    public Integer modifyDemandById(HashMap map) {
        log.info("修改需求单状态");
        int intCnt = 0;
        try {
            intCnt = dao.update("demandUpdate.update", map);
        } catch (Exception e) {
            log.error("修改需求单状态失败" + e);
        }
        return intCnt;
    }
}
