package cn.com.thtf.egov.cms.iservice;

import java.util.List;

/**
 * 查询大区、省、市的业务接口
 * 
 * @author sxf
 * 
 */
public interface IProvinceCityService {
	/**
	 * 查询所有省
	 * 
	 * @return
	 * @throws Exception
	 */

	@SuppressWarnings("unchecked")
	public List queryAllProvince() throws Exception;

	/**
	 * 查询所有区域
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List queryAllArea() throws Exception;

	/**
	 * 根据省Name查询该省所有市
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List queryAllCityByProvinceId(String provinceName) throws Exception;

}
