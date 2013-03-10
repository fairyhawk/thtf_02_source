package cn.com.thtf.egov.cms.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.com.thtf.egov.cms.application.IDao;
import cn.com.thtf.egov.cms.iservice.IUserChangeBeforeService;

public class UserChangeBeforeServiceImpl implements IUserChangeBeforeService {
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

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.IUserChangeBeforeService#userChangeBefore
     * (java.lang.String, java.lang.Integer)
     */
    public Object[] userChangeBefore(String id, Integer viewid) {
        Object[] obj = new Object[6];
        try {
            if (viewid == 1) {
                obj[0] = dao.queryForlist1("userManagement.userView_1", id).get(0);
                obj[1] = dao.queryRecordsAll("userManagement.userDept");
                obj[2] = dao.queryRecordsAll("userManagement.userDuty");
            } else if (viewid == 2) {
                obj[0] = dao.queryForlist1("userManagement.userView_1", id).get(0);
                obj[1] = dao.queryRecordsAll("userManagement.userDept");
                obj[2] = dao.queryRecordsAll("userManagement.userDuty");
                obj[3] = getProductType(
                        dao.queryRecordsAll("userManagement.userChange_2"),
                        dao.queryForlist1("userManagement.userView_2", id));
            } else if (viewid == 3) {
                obj[0] = dao.queryForlist1("userManagement.userView_11", id).get(0);
                obj[1] = dao.queryRecordsAll("userManagement.userDept");
                obj[2] = dao.queryRecordsAll("userManagement.userDuty");
                obj[3] = getProductType(
                        dao.queryRecordsAll("userManagement.userChange_2"),
                        dao.queryForlist1("userManagement.userView_2", id));
                obj[4] = dao.queryRecordsAll("userManagement.userArea");
            } else if (viewid == 4) {
                obj[0] = dao.queryForlist1("userManagement.userView_11", id).get(0);
                obj[1] = dao.queryRecordsAll("userManagement.userDept");
                obj[2] = dao.queryRecordsAll("userManagement.userDuty");
                obj[3] = getProductType(
                        dao.queryRecordsAll("userManagement.userChange_2"),
                        dao.queryForlist1("userManagement.userView_2", id));
                obj[4] = dao.queryRecordsAll("userManagement.userArea");
                obj[5] = dao.queryForlist1("userManagement.sgawega", id);
            } else if (viewid == 5) {
                obj[0] = dao.queryForlist1("userManagement.userView_12", id).get(0);
                obj[1] = dao.queryRecordsAll("userManagement.userDept");
                obj[2] = dao.queryRecordsAll("userManagement.userDuty");
            } else if (viewid == 6) {
                obj[0] = dao.queryForlist1("userManagement.userView_1", id).get(0);
                obj[1] = dao
                        .queryForlist1("userManagement.salesAssistantAddBefore_4", id);
                obj[2] = dao.queryRecordsAll("userManagement.userDept");
            } else if (viewid == 7) {
                obj[0] = dao.queryForlist1("userManagement.userView_1", id).get(0);
                obj[1] = dao
                        .queryForlist1("userManagement.salesAssistantAddBefore_6", id);
                obj[2] = dao.queryRecordsAll("userManagement.userDept");
            } else if (viewid == 19 || viewid == 20) {
                obj[0] = dao.queryForlist1("userManagement.userView_39", id).get(0);
                obj[1] = dao.queryRecordsAll("userManagement.userDept");
                obj[2] = dao.queryRecordsAll("userManagement.userDuty");
                // userChange_39
                // obj[3] = getProductType(
                // dao.queryRecordsAll("userManagement.userChange_2"),
                // dao.queryForlist1("userManagement.userView_2", id));
                obj[3] = dao.queryForlist1("userManagement.userChange_39", id);
                //obj[4] = dao.queryRecordsAll("userManagement.userArea");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return obj;
    }

    @SuppressWarnings("unchecked")
    private List getProductType(List productAll, List productType) {
        List<Object> list = new ArrayList<Object>();
        StringBuffer sb = new StringBuffer();
        sb.append(",");
        for (Object object : productType) {
            Map map = (Map) object;
            sb.append(map.get("id")).append(",");
        }
        for (Object object2 : productAll) {
            Map map = (Map) object2;
            if (sb.toString().indexOf("," + map.get("id").toString() + ",") >= 0) {
                map.put("mark", 1);
            }
            list.add(map);
        }
        return list;
    }

}
