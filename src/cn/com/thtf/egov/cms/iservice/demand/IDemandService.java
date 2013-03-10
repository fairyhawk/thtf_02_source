/**
 * ClassName  IDemandService
 *
 * History
 * Create User: ChenHuajiang
 * Create Date: 2010-6-8
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.iservice.demand;

import java.util.HashMap;
import java.util.List;

import cn.com.thtf.egov.cms.application.NewPage;

/**
 * 需求管理
 * 
 * @author ChenHuajiang
 */

public interface IDemandService {

    /**
     * 需求管理列表
     * 
     * @param map
     *            检索条件
     * @return list
     */
    @SuppressWarnings("unchecked")
    public List getDemandProducts(HashMap map, NewPage page);

    /**
     * 需求管理列表--明细
     * 
     * @param map
     *            当前用户ID,角色ID
     * @return list
     */
    @SuppressWarnings("unchecked")
    public List getDemandProductsDetail(HashMap map, NewPage page);

    /**
     * 需求管理列表--确认
     * 
     * @param map
     *            当期用户ID,用户名称,需求表ID
     * @return Integer
     */
    @SuppressWarnings("unchecked")
    public Integer modifyDemandById(HashMap map);
}
