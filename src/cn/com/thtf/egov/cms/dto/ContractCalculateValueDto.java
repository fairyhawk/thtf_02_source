package cn.com.thtf.egov.cms.dto;

import java.util.ArrayList;
import java.util.List;

public  class ContractCalculateValueDto {

    private String totalMoney; // 合同总金额

    private String totalRate; // 合同总利率

    private List<SalesContractProductDetailDto> lackProductList = new ArrayList<SalesContractProductDetailDto>();

    public List<SalesContractProductDetailDto> getLackProductList() {
        return lackProductList;
    }

    public void addLackProduct(SalesContractProductDetailDto lackProduct) {
        lackProductList.add(lackProduct);
    }

    public boolean isLackProcuct() {
        return lackProductList.size() > 0;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getTotalRate() {
        return totalRate;
    }

    public void setTotalRate(String totalRate) {
        this.totalRate = totalRate;
    }

}

