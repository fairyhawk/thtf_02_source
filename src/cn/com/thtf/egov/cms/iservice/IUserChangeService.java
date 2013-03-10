package cn.com.thtf.egov.cms.iservice;

import java.util.Map;

public interface IUserChangeService {
	/**
	 * 
	 * @param changeid
	 * @param map
	 * @return
	 */
	public Boolean userChange(Integer changeid, Map<String, Object> map);
	/**
	 * 
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
    public boolean userUnique(Map map);
	
	/**
     * 查询用户是否有待办事物
     * 
     * @param userId
     * @return
     */
    public boolean workCount(String userId);
}
