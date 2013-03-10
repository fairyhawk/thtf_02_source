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
import cn.com.thtf.egov.cms.dto.ProductTypeInfoDto;
import cn.com.thtf.egov.cms.iservice.IProductTypeService;

/**
 * @author Administrator
 * 
 */
public class ProductTypeServiceImp implements IProductTypeService {

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

	private static Logger log = LoggerFactory
			.getLogger(ProductTypeServiceImp.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.thtf.egov.cms.iservice.IProductTypeService#queryProductType()
	 */
	@SuppressWarnings("unchecked")
	public ListRange queryProductType(Page page) throws Exception {
		ListRange list = (ListRange) dao.queryRecordsByPage(
				"ptype.productTypeAll", null, page);
		// TODO Auto-generated method stub
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.IProductTypeService#addProductType(cn.com
	 * .thtf.egov.cms.dto.ProductTypeInfo)
	 */
	@SuppressWarnings("unchecked")
	public String addProductType(ProductTypeInfoDto ptinfo) {
		String ss = null;
		try {
			dao.insert("base_sqlMap.addProductType", ptinfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ss = "添加失败！";
		}
		// TODO Auto-generated method stub
		return ss;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.thtf.egov.cms.iservice.IProductTypeService#findTypeByID()
	 */
	@SuppressWarnings("unchecked")
	public ProductTypeInfoDto findTypeByID(Integer id) throws Exception {
		ProductTypeInfoDto pinfo = (ProductTypeInfoDto) dao.get(
				"base_sqlMap.productDeptMsg", id);
		// TODO Auto-generated method stub
		return pinfo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.IProductTypeService#updateProductType(cn
	 * .com.thtf.egov.cms.dto.ProductTypeInfo)
	 */
	public String updateProductType(ProductTypeInfoDto pinfo) {
		String ss = null;
		try {
			dao.update("base_sqlMap.updateProductType", pinfo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ss = "修改失败！";
		}
		// TODO Auto-generated method stub
		return ss;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.IProductTypeService#isExit(cn.com.thtf.
	 * egov.cms.dto.ProductTypeInfo)
	 */
	@SuppressWarnings("unchecked")
	public Integer isExit(ProductTypeInfoDto pinfo) throws Exception {
		Integer same = (Integer) dao.get("base_sqlMap.isProductTypeExits",
				pinfo);
		// TODO Auto-generated method stub
		return same;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.IProductTypeService#deleteProductType(java
	 * .lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public String deleteProductType(String idStr) {
		String result = null;
		String[] id = idStr.split(",");

		try {
			dao.getSqlMap().startTransaction();
			dao.getSqlMap().startBatch();
			for (int i = 0; i < id.length; i++) {
				dao.delete("base_sqlMap.deleteProductType", Integer
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
				e.printStackTrace();
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.IProductTypeService#fingProductTypeAll()
	 */
	@SuppressWarnings("unchecked")
	public List fingProductTypeAll() throws Exception {
		List list = (List) dao.queryRecordsAll("base_sqlMap.productTypeName");
		// TODO Auto-generated method stub
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.IProductDeptService#findNoById(java.lang
	 * .String)
	 */
	@SuppressWarnings("unchecked")
	public String findNoById(String id) throws Exception {
		String no = (String) dao.get("base_sqlMap.productDeptNoById", id);
		// TODO Auto-generated method stub
		return no;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.com.thtf.egov.cms.iservice.credit.ICreditTypeLimitService#productTypeAll
	 * ()
	 */
	@SuppressWarnings("unchecked")
	public List productTypeAll() throws Exception {
		List list = (List) dao.queryRecordsAll("base_sqlMap.productNameAll");
		// TODO Auto-generated method stub
		return list;
	}

	@SuppressWarnings("unchecked")
	public List fingProductTypeAll1(String userid) throws Exception {
		List list = (List) dao.queryForlist("base_sqlMap.productTypeName1",
				userid);
		// TODO Auto-generated method stub
		return list;
	}

	@SuppressWarnings("unchecked")
	public List fingProductTypeAll12(ProductTypeInfoDto pt) throws Exception {
		List list = (List) dao
				.queryForlist("base_sqlMap.productTypeName12", pt);
		return list;
	}

	@SuppressWarnings("unchecked")
	public String maxProductTypeNo() throws Exception {
		String max = (String) dao.get("base_sqlMap.maxProductTypeNo", null);
		// TODO Auto-generated method stub
		return max;
	}

}
