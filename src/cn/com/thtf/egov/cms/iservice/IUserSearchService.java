package cn.com.thtf.egov.cms.iservice;

import cn.com.thtf.egov.cms.application.Page;
import cn.com.thtf.egov.cms.dto.UersSearchInfoDto;

public interface IUserSearchService {
	/**
	 * 
	 * @param UersSearchInfoDto
	 * @param Page
	 * @return Object[]
	 */
	public Object[] userSearch(UersSearchInfoDto userSearchInfo, Page page);
}
