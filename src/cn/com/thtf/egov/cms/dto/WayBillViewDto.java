/**
 * ClassName  WayBillViewDto
 *
 * History
 * Create User: jiangmingxing
 * Create Date: 2010-5-13
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

/**
 * 运单查看信息
 * @author jiangmingxing
 *
 */
public class WayBillViewDto {
    private String box_id;   //装箱单号
    private String box_date;  //发货日期
    private String customer_name;  //客户名称
    private String name;      //货物接收单位名称
    private String customer_address;  //发货地址 
    private String linkman;        //联系人
    private String customer_postcode;   //客户邮编
    private String customer_tel;      //客户电话
    private String customer_mobile;   //客户手机
    private Integer box_count;      //装箱件数
    private Integer request_way;    //要求货运方式
    private String no;          //运单号
    private Integer transport_way;   //货运方式
    private String estimate_date;    //预计到货日期
    private String box_tel;          //物流公司联系电话
    private String box_info;         //在途信息
    private String arrival_date;     //到货日期
    private String signoff_date;     //签收日期
    private String signoff_name;     //签收人
    private Integer status; // 运单状态
    private String log_adm_name;     //签收人
    private Integer reality_way;//实际货运方式
    
	/**
	 * @return the reality_way
	 */
	public Integer getReality_way() {
		return reality_way;
	}
	/**
	 * @param realityWay the reality_way to set
	 */
	public void setReality_way(Integer realityWay) {
		reality_way = realityWay;
	}
	/**
	 * @return the log_adm_name
	 */
	public String getLog_adm_name() {
		return log_adm_name;
	}
	/**
	 * @param logAdmName the log_adm_name to set
	 */
	public void setLog_adm_name(String logAdmName) {
		log_adm_name = logAdmName;
	}
	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * @return the box_id
	 */
	public String getBox_id() {
		return box_id;
	}
	/**
	 * @param boxId the box_id to set
	 */
	public void setBox_id(String boxId) {
		box_id = boxId;
	}
	
	/**
	 * @return the box_date
	 */
	public String getBox_date() {
		return box_date;
	}
	/**
	 * @param boxDate the box_date to set
	 */
	public void setBox_date(String boxDate) {
		box_date = boxDate;
	}
	/**
	 * @return the customer_name
	 */
	public String getCustomer_name() {
		return customer_name;
	}
	/**
	 * @param customerName the customer_name to set
	 */
	public void setCustomer_name(String customerName) {
		customer_name = customerName;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the customer_address
	 */
	public String getCustomer_address() {
		return customer_address;
	}
	/**
	 * @param customerAddress the customer_address to set
	 */
	public void setCustomer_address(String customerAddress) {
		customer_address = customerAddress;
	}
	/**
	 * @return the linkman
	 */
	public String getLinkman() {
		return linkman;
	}
	/**
	 * @param linkman the linkman to set
	 */
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	/**
	 * @return the customer_postcode
	 */
	public String getCustomer_postcode() {
		return customer_postcode;
	}
	/**
	 * @param customerPostcode the customer_postcode to set
	 */
	public void setCustomer_postcode(String customerPostcode) {
		customer_postcode = customerPostcode;
	}
	/**
	 * @return the customer_tel
	 */
	public String getCustomer_tel() {
		return customer_tel;
	}
	/**
	 * @param customerTel the customer_tel to set
	 */
	public void setCustomer_tel(String customerTel) {
		customer_tel = customerTel;
	}
	/**
	 * @return the customer_mobile
	 */
	public String getCustomer_mobile() {
		return customer_mobile;
	}
	/**
	 * @param customerMobile the customer_mobile to set
	 */
	public void setCustomer_mobile(String customerMobile) {
		customer_mobile = customerMobile;
	}
	/**
	 * @return the box_count
	 */
	public Integer getBox_count() {
		return box_count;
	}
	/**
	 * @param boxCount the box_count to set
	 */
	public void setBox_count(Integer boxCount) {
		box_count = boxCount;
	}
	/**
	 * @return the request_way
	 */
	public Integer getRequest_way() {
		return request_way;
	}
	/**
	 * @param requestWay the request_way to set
	 */
	public void setRequest_way(Integer requestWay) {
		request_way = requestWay;
	}
	/**
	 * @return the no
	 */
	public String getNo() {
		return no;
	}
	/**
	 * @param no the no to set
	 */
	public void setNo(String no) {
		this.no = no;
	}
	/**
	 * @return the transport_way
	 */
	public Integer getTransport_way() {
		return transport_way;
	}
	/**
	 * @param transportWay the transport_way to set
	 */
	public void setTransport_way(Integer transportWay) {
		transport_way = transportWay;
	}
	/**
	 * @return the estimate_date
	 */
	public String getEstimate_date() {
		return estimate_date;
	}
	/**
	 * @param estimateDate the estimate_date to set
	 */
	public void setEstimate_date(String estimateDate) {
		estimate_date = estimateDate;
	}
	/**
	 * @return the box_tel
	 */
	public String getBox_tel() {
		return box_tel;
	}
	/**
	 * @param boxTel the box_tel to set
	 */
	public void setBox_tel(String boxTel) {
		box_tel = boxTel;
	}
	/**
	 * @return the box_info
	 */
	public String getBox_info() {
		return box_info;
	}
	/**
	 * @param boxInfo the box_info to set
	 */
	public void setBox_info(String boxInfo) {
		box_info = boxInfo;
	}
	/**
	 * @return the arrival_date
	 */
	public String getArrival_date() {
		return arrival_date;
	}
	/**
	 * @param arrivalDate the arrival_date to set
	 */
	public void setArrival_date(String arrivalDate) {
		arrival_date = arrivalDate;
	}
	/**
	 * @return the signoff_date
	 */
	public String getSignoff_date() {
		return signoff_date;
	}
	/**
	 * @param signoffDate the signoff_date to set
	 */
	public void setSignoff_date(String signoffDate) {
		signoff_date = signoffDate;
	}
	/**
	 * @return the signoff_name
	 */
	public String getSignoff_name() {
		return signoff_name;
	}
	/**
	 * @param signoffName the signoff_name to set
	 */
	public void setSignoff_name(String signoffName) {
		signoff_name = signoffName;
	}
    
    
  
    

}
