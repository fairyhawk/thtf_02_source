package cn.com.thtf.egov.cms.iservice.sell;

import java.util.List;

import cn.com.thtf.egov.cms.application.NewPage;
import cn.com.thtf.egov.cms.dto.NewReturnsProductDto;
import cn.com.thtf.egov.cms.dto.ReturnGoodsListQueryDto;
import cn.com.thtf.egov.cms.dto.SaleReturnGoodsViewSelfDto;
import cn.com.thtf.egov.cms.entity.SellBackGoodsEntity;
import cn.com.thtf.egov.cms.entity.UserEntity;
/**
 * 销售退货
 * 
 * @author LuPing
 */
public interface ISaleReturnsService {

    /**
     * 销售退货列表查询
     * 
     * @author LuPing
     * @param returnGoodsListQueryDto
     * @return
     */
    @SuppressWarnings("unchecked")
    public List getReturnGoodsList(NewPage page,
            ReturnGoodsListQueryDto returnGoodsListQueryDto);

    /**
     * 新建退货单库房选择小页面
     * 
     * @param page
     * @return
     */
    @SuppressWarnings("unchecked")
    public List selectAddressSelect(NewPage page);

    /**
     * 查询产品信息 新建
     * 
     * @author lxs
     * @param rde
     * @return
     */
    public List<NewReturnsProductDto> newSaleReturnsInit(NewPage page,String sendid, String backGoodsId);

    /**
     * 查看销售退货单
     * 
     * @author LuPing
     * @return
     */
    public SaleReturnGoodsViewSelfDto getReturnGoodsById(String returnGoodsId);

    /**
     * 新建退货单产品分类名称和客户名称
     * 
     * @author LuPing
     * @return
     */
    public SaleReturnGoodsViewSelfDto getReturnGoodsProductAndCustomer(String id);

    /**
     * 新建退货单
     * 
     * @author lxs
     * @param sbge
     * @return
     */
    public Boolean addReturnsGoods(SellBackGoodsEntity sbge, Integer[] thcount,Integer[] productId);

    /**
     * @author LuPing
     * @param sellBackGoodsE
     * @param user
     * @return
     */
    public Boolean modifySaleReturnGoodsAudit(SellBackGoodsEntity sellBackGoodsE,
            UserEntity user);

    /**
     * 修改退货单
     * 
     * @author lxs
     * @param sbge
     * @param thcount
     * @return
     */
    public Boolean modifyReturnsGoods(SellBackGoodsEntity sbge, Integer[] thcount,Integer[] productId);

    /**
     * 删除退货单
     * 
     * @author Administrator
     * @param id
     * @return
     */
    public Boolean deleteReturnGoodsSale(String id);
    
    /**
     * 查询产品信息 修改、查看
     * 
     * @author lxs
     * @param rde
     * @return
     */
    public List<NewReturnsProductDto> modifySaleReturnsProduct(String sendid, String backGoodsId);

}
