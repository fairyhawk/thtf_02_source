package cn.com.thtf.egov.cms.iservice.stock;

import java.util.List;

import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.dto.IncomeStockListDto;
import cn.com.thtf.egov.cms.dto.IncomeStoreRoomDto;
import cn.com.thtf.egov.cms.dto.saleReturnGoodsViewSelfOfIncomeDto;

public interface IIncomeStockService {
    /**
     * 入库管理列表
     * 
     * @return list
     */
	@SuppressWarnings("unchecked")
	public List getStockList(IncomeStockListDto incomeStockListDto, NewPage page);
	/**
     * 入库管理  库房名称列表
     * 
     * @return list
     */
	@SuppressWarnings("unchecked")
	public List getStoreRoomList();
	/**
     * 入库管理 评审 入库单
     * 
     * @return int 
     */
	public int modifyIncomeStoreroomOfIntock(IncomeStoreRoomDto incomeStoreRoomDto);
	/**
     * 入库管理 销售退货单 查看
     * 
     * @return saleReturnGoodsViewSelfOfIncomeDto 
     */
	public saleReturnGoodsViewSelfOfIncomeDto getReturnGoodsById(String returnGoodsId);
	/**
     * 入库管理 虚拟库
     * 
     * @return IncomeStoreRoomDto 
     */
	public int modifyIncomeStoreroomOfIn(IncomeStoreRoomDto incomeStoreRoomDto);
}
