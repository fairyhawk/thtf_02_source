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
import cn.com.thtf.egov.cms.dto.ProductSerieInfoDto;
import cn.com.thtf.egov.cms.iservice.IProductSerieService;

/**
 * @author Administrator
 * 
 */
public class ProductSerieServiceImp implements IProductSerieService {
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
	 * @see
	 * cn.com.thtf.egov.cms.iservice.IProductSerieService#queryProductSerieAll()
	 */
	@SuppressWarnings("unchecked")
	public ListRange queryProductSerieAll(ProductSerieInfoDto pinfo, Page page)
			throws Exception {
		ListRange list = (ListRange) dao.queryRecordsByPage(
				"pseriec.productSerieCheck", pinfo, page);
		// TODO Auto-generated method stub
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.thtf.egov.cms.iservice.IProductSerieService#showUpdate()
	 */
	@SuppressWarnings("unchecked")
	public ProductSerieInfoDto showUpdate(Integer id) throws Exception {
		ProductSerieInfoDto pinfo = (ProductSerieInfoDto) dao.get(
				"base_sqlMap.productSerieEdit", id);
		// TODO Auto-generated method stub
		return pinfo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.thtf.egov.cms.iservice.IProductSerieService#maxProductSerie()
	 */
	@SuppressWarnings("unchecked")
	public String maxProductSerie(String productTypeId) throws Exception {
		String no = (String) dao.get("base_sqlMap.maxProductSerieNo",
				productTypeId);
		// TODO Auto-generated method stub
		return no;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.IProductSerieService#checkProductSerie(
	 * cn.com.thtf.egov.cms.dto.ProductSerieInfo)
	 */
	@SuppressWarnings("unchecked")
	public ListRange checkProductSerie(ProductSerieInfoDto pinfo, Page page)
			throws Exception {
		ListRange list = (ListRange) dao.queryRecordsByPage(
				"pseriec.productSerieCheck", pinfo, page);
		// TODO Auto-generated method stub
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.IProductSerieService#addProductSerie(cn
	 * .com.thtf.egov.cms.dto.ProductSerieInfo)
	 */
	@SuppressWarnings("unchecked")
	public void addProductSerie(ProductSerieInfoDto pinfo) throws Exception {
		dao.insert("base_sqlMap.addProductSerie", pinfo);
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.IProductSerieService#isExit(cn.com.thtf
	 * .egov.cms.dto.ProductSerieInfo)
	 */
	@SuppressWarnings("unchecked")
	public Integer isExit(ProductSerieInfoDto pinfo) throws Exception {
		Integer same = (Integer) dao.get("base_sqlMap.isSerieExit", pinfo);
		// TODO Auto-generated method stub
		return same;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.IProductSerieService#updateProductSerie
	 * (cn.com.thtf.egov.cms.dto.ProductSerieInfo)
	 */
	public void updateProductSerie(ProductSerieInfoDto pinfo) throws Exception {
		dao.update("base_sqlMap.updateProductSerie", pinfo);
		// TODO Auto-generated method stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.IProductSerieService#deleteProductSerie
	 * (cn.com.thtf.egov.cms.dto.ProductSerieInfo)
	 */
	@SuppressWarnings("unchecked")
	public String deleteProductSerie(String idStr) {
		String result = null;
		String[] id = idStr.split(",");
		try {
			dao.getSqlMap().startTransaction();
			dao.getSqlMap().startBatch();
			for (int i = 0; i < id.length; i++) {
				dao.delete("base_sqlMap.deleteProductSerie", Integer
						.parseInt(id[i]));
			}
			dao.getSqlMap().executeBatch();
			dao.getSqlMap().commitTransaction();
		} catch (Exception e) {
			result = "删除失败！";
			log.error("delete error", e);
		} finally {
			try {
				dao.getSqlMap().endTransaction();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.IProductSerieService#productSerieNameAll()
	 */
	@SuppressWarnings("unchecked")
	public List productSerieNameAll(Integer typeId) throws Exception {
		// List list = dao.queryRecordsAll("base_sqlMap.productSerieName");
		List list = dao.queryForlist1("base_sqlMap.productSerieName", typeId);
		// TODO Auto-generated method stub
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.thtf.egov.cms.iservice.IProductSerieService#productSerieAll()
	 */
	@SuppressWarnings("unchecked")
	public List productSerieAll() throws Exception {
		List list = dao.queryForlist("base_sqlMap.productSerieAll", null);
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.IProductSerieService#isExit1(cn.com.thtf
	 * .egov.cms.dto.ProductSerieInfo)
	 */
	@SuppressWarnings("unchecked")
	public Integer isExit1(ProductSerieInfoDto pinfo) throws Exception {
		Integer idResult = (Integer) dao.get("base_sqlMap.isSerieExit1", pinfo);
		// TODO Auto-generated method stub
		return idResult;
	}

}
