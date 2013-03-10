package cn.com.thtf.egov.cms.iservice;

import java.util.List;

import cn.com.thtf.egov.cms.application.ListRange;
import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.application.Page;
import cn.com.thtf.egov.cms.dto.LogisticsInfoDto;
import cn.com.thtf.egov.cms.entity.LogisticsEntity;

/**
 * 处理物流公司的业务接口
 * 
 * @author sxf
 * 
 */
public interface ILogisticsService {

    /**
     * 获取所有物流公司
     * 
     * @return
     */
    public List<LogisticsEntity> getAllLogistics();

    /**
     * 查询所有物流公司
     * 
     * @param para
     * @param page
     * @return
     */
    public List<LogisticsEntity> queryAllLogistics(LogisticsEntity para, NewPage page);

    /**
     * 查询所有物流公司列表
     * 
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public ListRange queryAllLogistics(Page page, LogisticsInfoDto linfo)
            throws Exception;

    /**
     * 去除重复
     * 
     * @param name
     * @return
     * @throws Exception
     */
    public Integer idExit(String name) throws Exception;

    /**
     * 新增物流公司
     * 
     * @param info
     * @throws Exception
     */
    public String addLogistics(LogisticsInfoDto info);

    /**
     * 删除物流公司
     * 
     * @param ainfo
     * @throws Exception
     */
    public String deleteLogistics(String idStr) throws Exception;

    /**
     * 根据物流公司Id查询物流公司信息
     * 
     * @param id
     * @throws Exception
     */
    public LogisticsInfoDto showUpdateLogistics(Integer id) throws Exception;

    /**
     * 保存修改之后的物流公司信息
     * 
     * @param info
     * @throws Exception
     */
    public String saveLogistics(LogisticsInfoDto info) throws Exception;

    /**
     * 根据物流公司的Name检索物流公司
     * 
     * @param pinfo
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public ListRange checkLogisticsByName(LogisticsInfoDto info, Page page)
            throws Exception;
}
