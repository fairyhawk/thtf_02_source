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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.thtf.egov.cms.application.DaoImpl;
import cn.com.thtf.egov.cms.application.IDao;
import cn.com.thtf.egov.cms.application.ListRange;
import cn.com.thtf.egov.cms.application.Page;
import cn.com.thtf.egov.cms.dto.ProductInfoDto;
import cn.com.thtf.egov.cms.iservice.IProductService;

/**
 * @author Administrator
 * 
 */
public class ProductServiceImp implements IProductService {

	@SuppressWarnings("unchecked")
	private IDao dao;

	/**
	 * 
	 * @param dao
	 */
	@SuppressWarnings("unchecked")
	public void setDao(IDao dao) {
		this.dao = dao;
	}

	/**
	 * 
	 * @param dao
	 */
	@SuppressWarnings("unchecked")
	public void setDao(DaoImpl dao) {
		this.dao = dao;
	}

	private static Logger log = LoggerFactory
			.getLogger(ProductServiceImp.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.IProductService#queryProductAll(cn.com.
	 * thtf.egov.cms.application.Page, cn.com.thtf.egov.cms.dto.ProductInfo)
	 */
	@SuppressWarnings("unchecked")
	public ListRange queryProductAll(Page page, ProductInfoDto pinfo)
			throws Exception {
		ListRange list = (ListRange) dao.queryRecordsByPage(
				"product.productAll", pinfo, page);
		// TODO Auto-generated method stub
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.thtf.egov.cms.iservice.IProductService#autoProductNO()
	 */
	@SuppressWarnings("unchecked")
	public String autoProductNO(ProductInfoDto info) throws Exception {
		String result = (String) dao.get("base_sqlMap.autoProductNo", info);
		// TODO Auto-generated method stub
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.IProductService#addProduct(cn.com.thtf.
	 * egov.cms.dto.ProductInfo)
	 */
	@SuppressWarnings("unchecked")
	public String addProduct(ProductInfoDto pinfo) {
		String result = null;
		try {
			dao.insert("base_sqlMap.addProduct", pinfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result = "添加失败！";
		}
		// TODO Auto-generated method stub
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.IProductService#showUpdate(java.lang.Integer
	 * )
	 */
	@SuppressWarnings("unchecked")
	public ProductInfoDto showUpdate(Integer id) throws Exception {
		ProductInfoDto pinfo = (ProductInfoDto) dao.get(
				"base_sqlMap.showProductUpdate", id);
		// TODO Auto-generated method stub
		return pinfo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.IProductService#updateProduct(cn.com.thtf
	 * .egov.cms.dto.ProductInfo)
	 */
	public String updateProduct(ProductInfoDto pinfo) {
		String result = null;
		try {
			dao.update("base_sqlMap.saveUpdateProduct", pinfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result = "修改失败！";
		}
		// TODO Auto-generated method stub
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.IProductService#checkProduct(cn.com.thtf
	 * .egov.cms.dto.ProductInfo)
	 */
	@SuppressWarnings("unchecked")
	public ListRange checkProduct(ProductInfoDto pinfo, Page page)
			throws Exception {
		ListRange list = (ListRange) dao.queryRecordsByPage(
				"product.productAll", pinfo, page);
		// TODO Auto-generated method stub
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.thtf.egov.cms.iservice.IProductService#deleteProduct()
	 */
	@SuppressWarnings("unchecked")
	public String deleteProduct(String idStr) throws Exception {
		String result = "";
		String[] id = idStr.split(",");
		try {
			dao.getSqlMap().startTransaction();
			dao.getSqlMap().startBatch();
			for (int i = 0; i < id.length; i++) {
				dao
						.delete("base_sqlMap.deleteProduct", Integer
								.parseInt(id[i]));
			}
			dao.getSqlMap().executeBatch();
			dao.getSqlMap().commitTransaction();
		} catch (Exception e) {
			result = "删除失败！";
			log.error("delete error", e);
		} finally {
			dao.getSqlMap().endTransaction();
		}
		// TODO Auto-generated method stub
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.IProductService#isExit(cn.com.thtf.egov
	 * .cms.dto.ProductInfo)
	 */
	@SuppressWarnings("unchecked")
	public Integer isExit(ProductInfoDto pinfo) throws Exception {
		Integer same = (Integer) dao.get("base_sqlMap.isProductExit", pinfo);
		// TODO Auto-generated method stub
		return same;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.IProductService#isExit1(cn.com.thtf.egov
	 * .cms.dto.ProductInfo)
	 */
	@SuppressWarnings("unchecked")
	public Integer isExit1(ProductInfoDto pinfo) throws Exception {
		Integer same = (Integer) dao.get("base_sqlMap.isProductExit1", pinfo);
		// TODO Auto-generated method stub
		return same;
	}

	@SuppressWarnings("unchecked")
	public ProductInfoDto getProductById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return (ProductInfoDto) dao.get("base_sqlMap.queryProductById", id);
	}
}
