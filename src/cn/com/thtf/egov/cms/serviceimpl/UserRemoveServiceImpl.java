package cn.com.thtf.egov.cms.serviceimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.IDao;
import cn.com.thtf.egov.cms.dto.UersSearchInfoDto;
import cn.com.thtf.egov.cms.iservice.IUserRemoveService;

public class UserRemoveServiceImpl implements IUserRemoveService {

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

	private static final Logger log = LoggerFactory
			.getLogger(AreaServiceImp.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.IUserRemoveService#userRemove(cn.com.thtf
	 * .egov.cms.dto.UersSearchInfoDto)
	 */
	@SuppressWarnings("unchecked")
	public String userRemove(UersSearchInfoDto userSearchInfo) throws Exception {
		String result = "";
		try {
			dao.getSqlMap().startTransaction();
			dao.getSqlMap().startBatch();
			for (String id : userSearchInfo.getId().toString().split(",")) {
				dao.delete("userManagement.userRemove", id);
				dao.delete("userManagement.userRemove1", id);
				dao.delete("userManagement.userRemove2", id);
			}
			dao.getSqlMap().executeBatch();
			dao.getSqlMap().commitTransaction();
			// commitTransaction();
		} catch (Exception e) {
			result = "删除失败！";
			log.error("delete error", e);
		} finally {
			dao.getSqlMap().endTransaction();
		}
		return result;
	}
}
