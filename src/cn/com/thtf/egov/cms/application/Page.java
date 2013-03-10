/**
 * ClassName  Page
 *
 * History
 * Create User: Lubo
 * Create Date: 2009-6-30
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.application;

import java.io.Serializable;

/**
 * Page
 * 
 * @author Lubo
 * 
 */
public class Page implements Serializable {

	/** 自动生成序列化ID */
	private static final long serialVersionUID = 1L;

	/** offset */
	private int offset;
	/** pageSize */
	private int pageSize;
	/** totalSize */
	private int totalSize;
	/** totalSize */
	private int thisPage;

	/**
	 * @return the thisPage
	 */
	public int getThisPage() {
		return thisPage;
	}

	/**
	 * @param thisPage
	 *            the thisPage to set
	 */
	public void setThisPage(int thisPage) {
		this.thisPage = thisPage;
	}

	/**
	 * Page
	 */
	public Page() {
	}

	/**
	 * Page
	 * 
	 * @param offset
	 * @param pageSize
	 */
	public Page(int offset, int pageSize) {
		this.offset = offset;
		this.pageSize = pageSize;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}
}
