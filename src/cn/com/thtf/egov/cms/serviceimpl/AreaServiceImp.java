/**
 * ClassName  AreaService
 *
 * History
 * Create User: Administrator
 * Create Date: 2009-12-16
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.serviceimpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.DaoImpl;
import cn.com.thtf.egov.cms.application.IDao;
import cn.com.thtf.egov.cms.application.ListRange;
import cn.com.thtf.egov.cms.application.Page;
import cn.com.thtf.egov.cms.dto.AreaInfoDto;
import cn.com.thtf.egov.cms.iservice.IAreaService;

/**
 * @author Administrator
 * 
 */

public class AreaServiceImp implements IAreaService {

	@SuppressWarnings("unchecked")
	private IDao dao;

	@SuppressWarnings("unchecked")
	public void setDao(IDao dao) {
		this.dao = dao;
	}

	@SuppressWarnings("unchecked")
	public void setDao(DaoImpl dao) {
		this.dao = dao;
	}

	private static final Logger log = LoggerFactory
			.getLogger(AreaServiceImp.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.thtf.cms.iservice.IAreaService#QueryArea()
	 */
	@SuppressWarnings("unchecked")
	public ListRange queryArea(Page page) throws Exception {
		ListRange listRange = (ListRange) dao.queryRecordsByPage(
				"area.areaAll", null, page);
		// TODO Auto-generated method stub
		return listRange;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.thtf.cms.iservice.IAreaService#findname(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public AreaInfoDto findName(Integer id) throws Exception {
		AreaInfoDto areainfo = (AreaInfoDto) dao.get(
				"base_sqlMap.findAreaName", id);
		// TODO Auto-generated method stub
		return areainfo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.thtf.cms.iservice.IAreaService#saveupdate(java.lang.Integer)
	 */

	public String saveUpdate(AreaInfoDto ainfo) {
		String result = null;
		try {
			dao.update("base_sqlMap.updateArea", ainfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result = "修改失败！";
			// e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.thtf.cms.iservice.IAreaService#addArea(com.thtf.cms.dto.AreaInfo)
	 */
	@SuppressWarnings("unchecked")
	public String addArea(AreaInfoDto ainfo) {
		String result = null;
		try {
			dao.insert("base_sqlMap.addArea", ainfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result = "添加失败！";
			// e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.thtf.cms.iservice.IAreaService#idExit(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public Integer idExit(String name) throws Exception {
		Integer same = (Integer) dao.get("base_sqlMap.isExit", name);
		// TODO Auto-generated method stub
		return same;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.thtf.cms.iservice.IAreaService#deleteArea(java.lang.Integer)
	 */

	@SuppressWarnings("unchecked")
	public void deleteArea(String areaid) throws Exception {
		String[] id = areaid.split(",");

		try {
			dao.getSqlMap().startTransaction();
			dao.getSqlMap().startBatch();
			// beginTransaction();
			for (int i = 0; i < id.length; i++) {
				dao.delete("base_sqlMap.deleteArea", Integer.parseInt(id[i]));
			}
			dao.getSqlMap().executeBatch();
			dao.getSqlMap().commitTransaction();
			// commitTransaction();
		} catch (Exception e) {
			log.error("delete  error", e);
		} finally {
			dao.getSqlMap().endTransaction();
			// endTransaction();
			// log.error("delete user db error", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.thtf.egov.cms.iservice.IAreaService#queryArea1()
	 */
	@SuppressWarnings("unchecked")
	public List queryArea1() throws Exception {
		List list = (List) dao.queryRecordsAll("base_sqlMap.areaAll1");
		// TODO Auto-generated method stub
		return list;
	}

}
