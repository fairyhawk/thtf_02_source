package cn.com.thtf.egov.cms.serviceimpl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.IDao;
import cn.com.thtf.egov.cms.iservice.IUserChangeService;

public class UserChangeServiceImpl implements IUserChangeService {

    /** log */
    private static Logger log = LoggerFactory.getLogger(UserChangeServiceImpl.class);

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
     * cn.com.thtf.egov.cms.iservice.IUserChangeService#userChange(java.lang
     * .Integer, java.util.Map)
     */
    public Boolean userChange(Integer changeid, Map<String, Object> map) {
        // map.put("role_id", changeid.toString());
        boolean result = true;
        if (StringUtils.equals((String) map.get("enable"), "1")) {
            result = userUnique(map);
            if (result == false) {
                return result;
            }
        }
        /** 用户修改 **/
        try {
            if (changeid == 1) {
                dao.update1("userManagement.userChange_1", map);
            } else if (changeid == 2) {
                dao.update1("userManagement.userChange_1", map);
                dao.delete1("userManagement.userChange_3", map);
                Object[] temp = (Object[]) map.get("product");
                if (!(temp == null || temp.length == 0)) {
                    for (Object obj : temp) {
                        map.put("product_type_id", obj);
                        dao.insert1("userManagement.userChange_4", map);
                    }
                }
            } else if (changeid == 3) {
                dao.update1("userManagement.userChange_5", map);
                dao.delete1("userManagement.userChange_3", map);
                Object[] temp = (Object[]) map.get("product");
                if (!(temp == null || temp.length == 0)) {
                    for (Object obj : temp) {
                        map.put("product_type_id", obj);
                        dao.insert1("userManagement.userChange_4", map);
                    }
                }
            } else if (changeid == 4) {
                dao.update1("userManagement.userChange_5", map);
                dao.delete1("userManagement.userChange_3", map);
                Object[] temp = (Object[]) map.get("product");
                if (!(temp == null || temp.length == 0)) {
                    for (Object obj : temp) {
                        map.put("product_type_id", obj);
                        dao.insert1("userManagement.userChange_4", map);
                    }
                }
                Object[] temp1 = (Object[]) map.get("customer");
                if (!(temp1 == null || temp1.length == 0)) {
                    dao.delete1("userManagement.userChange_8", map);
                    for (Object obj : temp1) {
                        map.put("customer_id", obj);
                        dao.insert1("userManagement.userChange_9", map);
                    }
                } else {
                    dao.delete1("userManagement.userChange_8", map);
                }
            } else if (changeid == 5) {
                dao.update1("userManagement.userChange_10", map);
            } else if (changeid == 6) {
                dao.update1("userManagement.userChange_1", map);
                dao.delete1("userManagement.salesAssistantAddBefore_5", map);
                String[] temp = (String[]) map.get("regionalProduct");
                if (!(temp == null || temp.length == 0)) {
                    for (String string : temp) {
                        String[] strings = string.split(",");
                        map.put("chen1", strings[0]);
                        map.put("chen2", strings[1]);
                        dao.insert1("userManagement.salesAssistantAddBefore_10", map);
                    }
                }
            } else if (changeid == 7) {
                dao.update1("userManagement.userChange_1", map);
                dao.delete1("userManagement.salesAssistantAddBefore_9", map);
                String[] temp = (String[]) map.get("treasuryProduct");
                if (!(temp == null || temp.length == 0)) {

                    for (String string : temp) {
                        String[] strings = string.split(",");
                        map.put("chen1", strings[0]);
                        map.put("chen2", strings[1]);
                        dao.insert1("userManagement.salesAssistantAddBefore_11", map);
                    }
                }
            }else if (changeid == 39) {
                dao.update1("userManagement.userUserChange_39", map);
                dao.delete1("userManagement.userDelete_39", map);
                /** 判断用户是否有负责区域 **/
                String[] areaList = (String[]) map.get("areaList");
                if (areaList != null && areaList.length != 0) {
                    for (String string : areaList) {
                        map.put("areaId", string);
                        dao.insert1("userManagement.addUserareaMapp", map);
                    }
                } 
            }
        } catch (Exception e) {
            result = false;
            log.error("修改用户信息错误！", e);
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public boolean userUnique(Map map) {
        Integer role_id = Integer.parseInt((String) map.get("role_id"));
        Map<String, String> upemap = new HashMap<String, String>();
        Integer pd = 0;

        /** 区域总监 区域 */
        if (role_id == 9) {
            try {
                upemap.put("role_id", role_id.toString());
                upemap.put("userAreaId", (String) map.get("user_area_id"));
                upemap.put("userId", (String) map.get("id"));
                // Integer result = (Integer) dao.queryForlist1(
                // "userManagement.onlyuserarea", upemap).get(0);

                upemap.put("role_id", role_id.toString());
                String[] product_type_id = (String[]) map.get("product");
                if (!(product_type_id == null || product_type_id.length == 0)) {
                    for (String string : product_type_id) {
                        upemap.put("productTypeId", string);
                        // map.put("productTypeId", string);
                        // pd = (Integer) dao.queryForlist1(
                        // "userManagement.onlyproducttype", upemap).get(0);
                        // if (result > 0 && pd > 0) {
                        // log.info("同一区域同一产品分类存在相同的区域总监");
                        // return false;
                        // }
                        pd = (Integer) dao.queryForlist1(
                                "userManagement.onlyuserareaExtUp", upemap).get(0);
                        if (pd > 0) {
                            log.info("同一区域同一产品分类存在相同的区域总监");
                            return false;
                        }
                    }
                }
            } catch (Exception e) {
                log.error("区域总监对区域唯一性检测错误！", e);
            }
        }

        /** 采购专员、采购主管、产品总监、销售总监对产品分类唯一性检测 **/
        if (role_id == 8 || role_id == 10 || role_id == 11 || role_id == 5) {
            try {
                // UserProductEntity upe = new UserProductEntity();
                // upe.setId(Integer.parseInt((String)map.get("id")));
                upemap.put("role_id", role_id.toString());
                upemap.put("userId", map.get("id").toString());
                // upe.setUserId((String) map.get("id"));
                // String[] product_type_id = (String[]) map
                // .get("product_type_id");
                String[] product_type_id = (String[]) map.get("product");
                if (!(product_type_id == null || product_type_id.length == 0)) {
                    for (String string : product_type_id) {
                        // upe.setProductTypeId(Integer.parseInt(string));
                        upemap.put("productTypeId", string);
                        pd = (Integer) dao.queryForlist1(
                                "userManagement.onlyproducttype", upemap).get(0);
                        if (pd > 0) {
                            log.info("同一产品分类存在相同的角色" + role_id);
                            return false;
                        }
                    }
                }
            } catch (Exception e) {
                log.error("采购专员、采购主管、产品总监、销售总监对产品分类唯一性检测错误！", e);
            }
        }
        /** 法务专员、运营总监助理、运营总监唯一性检测 **/
        if (role_id == 15 || role_id == 16 || role_id == 17) {
            try {
                upemap.put("role_id", role_id.toString());
                upemap.put("userId", map.get("id").toString());
                pd = (Integer) dao.queryForlist1("userManagement.onlyrole", upemap)
                        .get(0);
                if (pd > 0) {
                    log.info("存在相同的角色" + role_id);
                    return false;
                }
            } catch (Exception e) {
                log.error("法务专员、运营总监助理、运营总监唯一性检测错误！", e);
            }
        }
        /** 库房管理员同一库房对产品分类唯一性检测 **/
        if (role_id == 12) {
            try {
                // UserStockroomProductEntity uspe = new
                // UserStockroomProductEntity();
                // uspe.setId(Integer.parseInt((String) map.get("id")));
                upemap.put("userId", map.get("id").toString());
                String[] treasuryProduct = (String[]) map.get("treasuryProduct");
                if (!(treasuryProduct == null || treasuryProduct.length == 0)) {
                    for (String string : treasuryProduct) {
                        String[] strings = string.split(",");
                        // uspe.setProductTypeId(Integer.parseInt(strings[0]));
                        // uspe.setStockroomId(Integer.parseInt(strings[1]));
                        upemap.put("productTypeId", strings[0]);
                        upemap.put("stockroomId", strings[1]);
                        pd = (Integer) dao.queryForlist1(
                                "userManagement.onlytreasurymanager", upemap).get(0);
                        if (pd > 0) {
                            log.info("同一库房同一产品分类存在相同的库房管理员");
                            return false;
                        }
                    }
                }
            } catch (Exception e) {
                log.error("库房管理员唯一性检测错误！", e);
            }
        }
        /** 销售助理同一区域下对产品分类唯一性检测 **/
        if (role_id == 3) {
            try {
                // UserAreaProductEntity uape = new UserAreaProductEntity();
                // uape.setId(Integer.parseInt((String) map.get("id")));
                upemap.put("userId", map.get("id").toString());
                String[] regionalProduct = (String[]) map.get("regionalProduct");
                if (!(regionalProduct == null || regionalProduct.length == 0)) {
                    for (String string : regionalProduct) {
                        String[] strings = string.split(",");
                        // uape.setUserAreaId(Integer.parseInt(strings[1]));
                        // uape.setProductTypeId(Integer.parseInt(strings[0]));
                        upemap.put("userAreaId", strings[1]);
                        upemap.put("productTypeId", strings[0]);
                        pd = (Integer) dao.queryForlist1(
                                "userManagement.onlysalesassistant", upemap).get(0);
                        if (pd > 0) {
                            log.info("同一区域同一产品分类存在相同的销售助理");
                            return false;
                        }
                    }
                }
            } catch (Exception e) {
                log.error("销售助理唯一性检测错误！", e);
            }
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.IUserChangeService#workCount(java.lang.
     * String)
     */
    public boolean workCount(String userId) {
        boolean result = true;
        Integer workCount;
        try {
            workCount = (Integer) dao.queryForlist1(
                    "userManagement.userIdSelectUserWork", userId).get(0);
            if (workCount != 0) {
                result = false;
            }
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
        }
        return result;
    }
}
