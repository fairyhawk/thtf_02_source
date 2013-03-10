package cn.com.thtf.egov.cms.iservice;

import cn.com.thtf.egov.cms.dto.UersSearchInfoDto;

public interface IUserRemoveService {
	/**
	 * 
	 * @param UersSearchInfoDto
	 * @param
	 * @return String
	 */
	public String userRemove(UersSearchInfoDto userSearchInfo) throws Exception;
}
