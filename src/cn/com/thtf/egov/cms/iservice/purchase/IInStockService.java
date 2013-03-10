/**
 * ClassName  IInStockService
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-5-27
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.iservice.purchase;

import java.util.List;

import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.dto.AddInStockDto;
import cn.com.thtf.egov.cms.dto.BuyContractDto;
import cn.com.thtf.egov.cms.dto.InStockProductDto;
import cn.com.thtf.egov.cms.dto.InStockQueryDto;
import cn.com.thtf.egov.cms.dto.InStockResultDto;
import cn.com.thtf.egov.cms.dto.ReceiveGoodsDetailDto;
import cn.com.thtf.egov.cms.entity.InStockEntity;
import cn.com.thtf.egov.cms.entity.ReceiveGoodsDetailEntity;

/**
 * 入库单
 * 
 * @author Lubo
 */
public interface IInStockService {

    /**
     * 入库单评审
     * 
     * @param auditPara
     * @return
     */
    public boolean auditInstock(InStockEntity auditPara);

    /**
     * 删除入库单
     * 
     * @param instockId
     * @return
     */
    public boolean removeInstock(String instockId);

    /**
     * 检索入库单列表
     * 
     * @param para
     * @param page
     * @return
     */
    public List<InStockResultDto> getInStockList(InStockQueryDto para, NewPage page);

    /**
     * 检索入库单信息
     * 
     * @param id
     * @return
     */
    public InStockEntity getInStock(String id);

    /**
     * 新增入库单
     * 
     * @param para
     * @return
     */
    public boolean addInStock(AddInStockDto para);

    /**
     * 检索采购合同信息
     * 
     * @param buyContractId
     * @return
     */
    public BuyContractDto getBuyContract(String buyContractId);

    /**
     * 选择收获地址
     * 
     * @param para
     * @return
     */
    public List<ReceiveGoodsDetailDto> getReceiveGoodsDetail(ReceiveGoodsDetailEntity para);

    /**
     * 选择产品
     * 
     * @param buyContractId
     * @return
     */
    public List<InStockProductDto> getProductList(ReceiveGoodsDetailDto para);

    /**
     * 选择产品
     * 
     * @param buyContractId
     * @return
     */
    public List<InStockProductDto> getProductListView(ReceiveGoodsDetailDto para);

}
