/**
 * ClassName  IAreaService
 *
 * History
 * Create User: Administrator
 * Create Date: 2009-12-16
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.iservice;

import java.util.List;

import cn.com.thtf.egov.cms.application.ListRange;
import cn.com.thtf.egov.cms.application.Page;
import cn.com.thtf.egov.cms.dto.ProductSerieInfoDto;

/**
 * @author Administrator 产品系列
 * 
 */
public interface IProductSerieService {

    /**
     * 产品系列查询所有
     * 
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public ListRange queryProductSerieAll(ProductSerieInfoDto pinfo, Page page)
            throws Exception;

    /**
     * 显示修改
     * 
     * @return
     * @throws Exception
     */
    public ProductSerieInfoDto showUpdate(Integer id) throws Exception;

    /**
     * 自动生成产品编号
     * 
     * @return
     * @throws Exception
     */
    public String maxProductSerie(String productTypeId) throws Exception;

    /**
     * 检索产品系列
     * 
     * @param pinfo
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public ListRange checkProductSerie(ProductSerieInfoDto pinfo, Page page)
            throws Exception;

    /**
     * 添加产品系列
     * 
     * @param pinfo
     * @throws Exception
     */
    public void addProductSerie(ProductSerieInfoDto pinfo) throws Exception;

    /**
     * 去重复
     * 
     * @param pinfo
     * @return
     * @throws Exception
     */
    public Integer isExit(ProductSerieInfoDto pinfo) throws Exception;

    public Integer isExit1(ProductSerieInfoDto pinfo) throws Exception;

    /**
     * 修改
     * 
     * @param pinfo
     * @throws Exception
     */
    public void updateProductSerie(ProductSerieInfoDto pinfo) throws Exception;

    /**
     * 删除
     * 
     * @param pinfo
     * @throws Exception
     */
    public String deleteProductSerie(String idStr);

    /**
     * 根据分类Id所有产品系列
     * 
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List productSerieNameAll(Integer typeId) throws Exception;

    /**
     * 查询所有产品系列列表
     * 
     * @param typeId
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List productSerieAll() throws Exception;

}
