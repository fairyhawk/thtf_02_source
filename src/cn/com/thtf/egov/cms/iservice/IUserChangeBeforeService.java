package cn.com.thtf.egov.cms.iservice;

public interface IUserChangeBeforeService {
	/**
	 * 
	 * @param id
	 * @param viewid
	 * @return Object[]
	 */
	public Object[] userChangeBefore(String id, Integer viewid);
}
