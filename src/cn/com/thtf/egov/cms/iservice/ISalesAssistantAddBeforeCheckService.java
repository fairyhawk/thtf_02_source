package cn.com.thtf.egov.cms.iservice;

public interface ISalesAssistantAddBeforeCheckService {
	/**
	 * 
	 * @param regional
	 * @param product
	 * @return Object[]
	 */
	public Object[] checkRegionalProduct(String regional, String product);
}
