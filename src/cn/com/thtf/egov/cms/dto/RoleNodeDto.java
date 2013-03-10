/**
 * ClassName  RoleNodeDto
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-4-7
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

/**
 * RoleNodeDto
 * 
 * @author Lubo
 */

public class RoleNodeDto {

	/** id */
	private Integer id;
	/** role_id */
	private Integer roleId;
	/** role_id */
	private String nodeId;
	/** name */
	private String name;
	/** parent_node_id */
	private String parentNodeId;

	/** url */
	private String url;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the roleId
	 */
	public Integer getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId
	 *            the roleId to set
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the nodeId
	 */
	public String getNodeId() {
		return nodeId;
	}

	/**
	 * @param nodeId
	 *            the nodeId to set
	 */
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the parentNodeId
	 */
	public String getParentNodeId() {
		return parentNodeId;
	}

	/**
	 * @param parentNodeId
	 *            the parentNodeId to set
	 */
	public void setParentNodeId(String parentNodeId) {
		this.parentNodeId = parentNodeId;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
}
