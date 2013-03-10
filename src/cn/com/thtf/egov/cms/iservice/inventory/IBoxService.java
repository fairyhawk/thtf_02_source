/**
 * ClassName  IBoxService
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-6-2
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.iservice.inventory;

import java.util.List;

import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.dto.BoxDto;
import cn.com.thtf.egov.cms.dto.BoxQueryDto;
import cn.com.thtf.egov.cms.dto.SendGoodsAddDto;
import cn.com.thtf.egov.cms.dto.StockSendGoodsDetailDto;
import cn.com.thtf.egov.cms.dto.StockSendGoodsDto;

/**
 * 装箱单
 * 
 * @author Lubo
 */
public interface IBoxService {
    
    /**
     * 装箱单确认已付款
     * 
     * @param para
     * @return
     */
    public boolean boxTbcPay(BoxDto para);

    /**
     * 装箱单确认
     * 
     * @param para
     * @return
     */
    public boolean boxTbc(BoxDto para);

    /**
     * 发货单特殊说明
     * 
     * @param para
     * @return
     */
    public List<StockSendGoodsDto> getOrderText(StockSendGoodsDto para);

    /**
     * 装箱单删除
     * 
     * @param para
     * @param page
     * @return
     */
    public boolean removeBox(String id);

    /**
     * 装箱单回执确认
     * 
     * @param para
     * @param page
     * @return
     */
    public boolean boxConfirm(BoxDto para);

    /**
     * 装箱单列表
     * 
     * @param para
     * @param page
     * @return
     */
    public List<BoxDto> getBoxList(BoxQueryDto para, NewPage page);

    /**
     * 装箱单查看
     * 
     * @param id
     * @return
     */
    public BoxDto getBox(String id);

    /**
     * 装箱单连纸打印
     * 
     * @param id
     * @return
     */
    public BoxDto getBoxPrint(String id);

    /**
     * 新增装箱单
     * 
     * @param para
     * @return
     */
    public boolean addBox(BoxDto para);

    /**
     * 发货虚出
     * 
     * @param para
     * @return
     */
    public boolean completeSendGoodsFalse(SendGoodsAddDto para);

    /**
     * 选择发货单小页面
     * 
     * @param para
     * @return
     */
    public List<StockSendGoodsDto> getOrderList(StockSendGoodsDto para);

    /**
     * 发货单明细
     * 
     * @param para
     * @return
     */
    public List<StockSendGoodsDetailDto> getOrderDetail(StockSendGoodsDto para);

}
