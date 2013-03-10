package cn.com.thtf.egov.cms.serviceimpl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.IDao;
import cn.com.thtf.egov.cms.iservice.IUserAdd1Service;

@SuppressWarnings("unchecked")
public class UserAdd1ServiceImpl implements IUserAdd1Service {

    /** log */
    private static Logger log = LoggerFactory.getLogger(UserAdd1ServiceImpl.class);

    private IDao dao;

    /**
     * 
     * @return
     */
    public IDao getDao() {
        return dao;
    }

    /**
     * 
     * @param dao
     */
    public void setDao(IDao dao) {
        this.dao = dao;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.com.thtf.egov.cms.iservice.IUserAdd1Service#userAdd(java.util.Map)
     */
    public Boolean userAdd(Map map) {
        Boolean result = true;
        int roleId = Integer.parseInt((String) map.get("role_id"));
        try {
            String[] product_type_id = (String[]) map.get("product_type_id");
            if (StringUtils.equals((String) map.get("enable"), "1")) {
                boolean userpd = userUnique(map);
                if (userpd == false) {
                    return false;
                }
            }
            /** 添加用户信息 **/
            dao.insert1("userManagement.userAdd_2", map);
            /** 判断用户是否有负责产品 **/
            if (!(product_type_id == null || product_type_id.length == 0)) {
                if (roleId == 4 || roleId == 5 || roleId == 6 || roleId == 8
                        || roleId == 9 || roleId == 10 || roleId == 10) {
                    for (String string : product_type_id) {
                        map.put("product", string);
                        dao.insert1("userManagement.userAdd_3", map);
                    }
                }
            }
            /** 判断用户是否有客户 **/
            String[] customer = (String[]) map.get("customer");
            if (!(customer == null || customer.length == 0)) {
                if (roleId == 40) {
                    for (String string : customer) {
                        map.put("customer", string);
                        dao.insert1("userManagement.userAdd_4", map);
                    }
                }
            }
            /** 判断用户是否有区域产品 **/
            String[] regionalProduct = (String[]) map.get("regionalProduct");
            if (!(regionalProduct == null || regionalProduct.length == 0)) {
                if (roleId == 3) {
                    for (String string : regionalProduct) {
                        String[] strings = string.split(",");
                        map.put("chen1", strings[0]);
                        map.put("chen2", strings[1]);
                        dao.insert1("userManagement.userAdd_6", map);
                    }
                }
            }
            /** 判断用户是否有负责库房产品 **/
            String[] treasuryProduct = (String[]) map.get("treasuryProduct");
            if (!(treasuryProduct == null || treasuryProduct.length == 0)) {
                if (roleId == 12) {
                    for (String string : treasuryProduct) {
                        String[] strings = string.split(",");
                        map.put("chen1", strings[0]);
                        map.put("chen2", strings[1]);
                        dao.insert1("userManagement.salesAssistantAddBefore_11", map);
                    }
                }
            }
            /** 判断用户是否有负责区域 **/
            String[] areaList = (String[]) map.get("areaList");
            if (areaList != null && areaList.length != 0) {
                if (roleId == 19 || roleId == 20) {
                    for (String string : areaList) {
                        map.put("areaId", string);
                        dao.insert1("userManagement.addUserareaMapp", map);
                    }
                }
            }
        } catch (Exception e) {
            result = false;
            log.error("新增用户失败", e);
        }
        return result;
    }

    // TODO
    public boolean userUnique(Map map) {
        Integer role_id = Integer.parseInt((String) map.get("role_id"));
        Map<String, String> upemap = new HashMap<String, String>();
        Integer pd = 0;

        /** 区域总监 区域 */
        if (role_id == 9) {
            try {
                upemap.put("role_id", role_id.toString());
                upemap.put("userAreaId", (String) map.get("user_area_id"));
                // Integer result = (Integer) dao.queryForlist1(
                // "userManagement.onlyuserarea", upemap).get(0);

                upemap.put("role_id", role_id.toString());
                String[] product_type_id = (String[]) map.get("product_type_id");
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
                upemap.put("role_id", role_id.toString());
                String[] product_type_id = (String[]) map.get("product_type_id");
                if (!(product_type_id == null || product_type_id.length == 0)) {
                    for (String string : product_type_id) {
                        upemap.put("productTypeId", string);
                        // map.put("productTypeId", string);
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

}
