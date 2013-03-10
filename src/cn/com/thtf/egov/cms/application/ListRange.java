/**
 * ClassName  ListRange
 *
 * History
 * Create User: Lubo
 * Create Date: 2009-6-30
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * ListRange
 * 
 * @author Lubo
 * 
 */
public class ListRange<T> implements Serializable {

	/** 自动生成序列化ID */
	private static final long serialVersionUID = 1L;
	/** success */
	private boolean success;
	/** records */
	private List<T> records;
	/** query */
	private Serializable query;
	/** Page */
	private Page page;

	public ListRange() {
		this.records = new ArrayList<T>();
	}

	public ListRange(Page page) {
		this();
		this.page = page;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}

	public List<T> getRecords() {
		return records;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Page getPage() {
		return page;
	}

	public Serializable getQuery() {
		return query;
	}

	public void setQuery(Serializable query) {
		this.query = query;
	}

	public void clear() {
		this.query = null;
	}
}