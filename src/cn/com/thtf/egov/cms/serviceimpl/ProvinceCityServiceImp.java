package cn.com.thtf.egov.cms.serviceimpl;

import java.util.List;

import cn.com.thtf.egov.cms.application.IDao;
import cn.com.thtf.egov.cms.iservice.IProvinceCityService;

/**
 * 处理大区、省、市的业务类
 * 
 * @author sxf
 * 
 */
public class ProvinceCityServiceImp implements IProvinceCityService {
	@SuppressWarnings("unchecked")
	private IDao dao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.IProvinceCityService#queryAllProvince()
	 */
	@SuppressWarnings("unchecked")
	public List queryAllProvince() throws Exception {
		// TODO Auto-generated method stub
		List list = dao.queryRecordsAll("base_sqlMap.provinceAll");
		return list;
	}

	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public IDao getDao() {
		return dao;
	}

	@SuppressWarnings("unchecked")
	public void setDao(IDao dao) {
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.IProvinceCityService#queryAllCityByProvinceId
	 * (java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List queryAllCityByProvinceId(String provinceName) throws Exception {
		List list = dao.queryForlist1("base_sqlMap.getCitysByProvinceId",
				provinceName);
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.thtf.egov.cms.iservice.IProvinceCityService#queryAllArea()
	 */
	@SuppressWarnings("unchecked")
	public List queryAllArea() throws Exception {
		List list = dao.queryRecordsAll("base_sqlMap.queryAreaAll");
		return list;
	}
}
