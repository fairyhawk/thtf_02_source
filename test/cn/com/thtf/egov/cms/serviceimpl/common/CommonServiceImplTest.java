/**
 * ClassName  CommonServiceImplTest
 *
 * History
 * Create User: Administrator
 * Create Date: 2010-7-6
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.serviceimpl.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cn.com.thtf.egov.cms.constant.Constants;
import cn.com.thtf.egov.cms.dto.ContractIdDto;
import cn.com.thtf.egov.cms.entity.ProductTypeEntity;
import cn.com.thtf.egov.cms.iservice.common.ICommonService;
import cn.com.thtf.egov.cms.util.Container;

/**
 * 
 * @author Administrator
 */

public class CommonServiceImplTest {
    ICommonService service = null;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        service = (ICommonService) Container.getBean("commonServiceImpl");
    }

    /**
     * Test method for
     * {@link cn.com.thtf.egov.cms.serviceimpl.common.CommonServiceImpl#getAllProductTypes(java.lang.String, java.lang.String, java.lang.String)}
     * .
     */
    @Test
    public void testGetAllProductTypes() {

        List<ProductTypeEntity> list = service.getAllProductTypes(null, "luffyday3", "3");
        System.out.println(list.size());
    }

    @Test
    public void testGetProdAvePrice() {
        String p = service.getProdAvePrice("32", "1");
        System.out.println(p);
    }

    @Test
    public void testGetMaxIdDto() {
        Constants.CONTRACT_MAX_ID_DTO = new ContractIdDto();
        service.getMaxIdDto();
    }

    @Test
    public void testGetSerialNumber() throws ParseException {
        Constants.CONTRACT_MAX_ID_DTO = new ContractIdDto();
        service.getMaxIdDto();
        String sn = service.getSerialNumber("XS", "xxx");
        System.out.println("sn:" + sn);
        System.out
                .println(Constants.CONTRACT_MAX_ID_DTO.getSellContractIdDto().getDate());
        System.out.println(Constants.CONTRACT_MAX_ID_DTO.getSellContractIdDto()
                .getMaxId());

        System.out.println("======================");
        sn = service.getSerialNumber("XS", "xxx");
        System.out.println("sn:" + sn);
        System.out
                .println(Constants.CONTRACT_MAX_ID_DTO.getSellContractIdDto().getDate());
        System.out.println(Constants.CONTRACT_MAX_ID_DTO.getSellContractIdDto()
                .getMaxId());
        
        System.out.println("======================");
        Constants.CONTRACT_MAX_ID_DTO.getSellContractIdDto().setDate(
                new SimpleDateFormat("yyyy-MM-dd").parse("2010-07-20"));
        Constants.CONTRACT_MAX_ID_DTO.getSellContractIdDto().setMaxId(5);
        sn = service.getSerialNumber("XS", "xxx");
        System.out.println("sn:" + sn);
        System.out
                .println(Constants.CONTRACT_MAX_ID_DTO.getSellContractIdDto().getDate());
        System.out.println(Constants.CONTRACT_MAX_ID_DTO.getSellContractIdDto()
                .getMaxId());

    }
}
