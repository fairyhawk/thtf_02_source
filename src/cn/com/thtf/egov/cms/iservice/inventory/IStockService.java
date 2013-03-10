/**
 * ClassName  IStockService
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-6-3
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.iservice.inventory;

import java.util.List;

import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.dto.SendGoodsDto;
import cn.com.thtf.egov.cms.dto.StockProductDto;
import cn.com.thtf.egov.cms.dto.StockQueryDto;
import cn.com.thtf.egov.cms.dto.StockSendGoodsDto;
import cn.com.thtf.egov.cms.entity.BuyBackGoodsEntity;
import cn.com.thtf.egov.cms.entity.MoveEntity;
import cn.com.thtf.egov.cms.entity.SampleOutEntity;

/**
 * IStockService
 * 
 * @author Lubo
 */
public interface IStockService {

    /**
     * 借出单评审异常
     * 
     * @param auditPara
     * @return
     */
    public boolean auditSampleOut(SampleOutEntity auditPara);

    /**
     * 移库单评审异常
     * 
     * @param auditPara
     * @return
     */
    public boolean auditMove(MoveEntity auditPara);

    /**
     * 返场单评审异常
     * 
     * @param auditPara
     * @return
     */
    public boolean auditBuyBackGoods(BuyBackGoodsEntity auditPara);

    /**
     * 发货单评审异常
     * 
     * @param auditPara
     * @return
     */
    public boolean auditSendGoods(SendGoodsDto auditPara);

    /**
     * 检索单据列表
     * 
     * @param para
     * @param page
     * @return
     */
    public List<StockSendGoodsDto> getOrderList(StockSendGoodsDto para, NewPage page);

    /**
     * 检索库存列表
     * 
     * @param para
     * @param page
     * @return
     */
    public List<StockProductDto> getStockList(StockQueryDto para, NewPage page);

    /**
     * 检索库存明细
     * 
     * @param para
     * @param page
     * @return
     */
    public List<StockProductDto> getStockDetileList(StockQueryDto para, NewPage page);
}
