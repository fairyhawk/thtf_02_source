package cn.com.thtf.egov.cms.dto;

import java.util.Map;

import cn.com.thtf.egov.cms.iservice.sell.ISalesContractManagementService;

/**
 * 销售合同评审角色
 * 
 * @author 李乐伟
 */
public class ContractJudgeRole {

    private Map<Integer, SalesJudgeRoleDto> saleRoleMap; // 全部评审人

    @SuppressWarnings("unchecked")
    public ContractJudgeRole(boolean flg, String productTypeId, Integer roleId,Integer regionalId,
            ISalesContractManagementService service) {

        saleRoleMap = service.getJudgeRole(flg, productTypeId, roleId,regionalId);
    }

    /**
     * 根据角色类型查评审人
     * 
     * @param roleType
     * @return
     */
    public SalesJudgeRoleDto getJudgeRoleByRoleType(Integer roleType) {
        return saleRoleMap == null ? null : (SalesJudgeRoleDto) saleRoleMap.get(roleType);
    }

    /**
     * 根据角色类型得到评审人id
     * 
     * @param roleType
     * @return
     */
    public String getUserIdByRoleType(Integer roleType) {
        SalesJudgeRoleDto judge = getJudgeRoleByRoleType(roleType);
        return judge == null ? null : judge.getUserId();
    }

}
