/**
 * ClassName  NewPage
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-4-8
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.application;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import cn.com.thtf.egov.cms.constant.Constants;
import cn.com.thtf.egov.cms.util.Util;

/**
 * NewPage
 * 
 * @author Lubo
 * 
 */
public class NewPage implements Serializable {

    /** 自动生成序列化ID */
    private static final long serialVersionUID = 1L;

    /** url */
    private String url;
    /** query */
    private StringBuffer query = new StringBuffer("?");

    /** 共多少记录 */
    private int totalResultSize;
    /** 共多少页 */
    private int totalPageSize;
    /** 每页多少条 */
    private int pageSize;

    /** 当前页 */
    private int thisPage;

    /**
     * @param query
     *            the query to set
     */
    public void setQuery(String name, String value) {
        if (StringUtils.isNotBlank(value)) {
            query.append(name).append("=").append(Util.encodeUrl(value)).append("&");
        }
    }

    /**
     * @return the pageFirst
     */
    public String getPageFirst() {
        StringBuffer sbUrl = new StringBuffer();

        sbUrl.append(url);
        sbUrl.append(query);
        sbUrl.append(Constants.NEW_THISPAGE);
        sbUrl.append("=1");

        return sbUrl.toString();
    }

    /**
     * @return the pageLast
     */
    public String getPageLast() {
        StringBuffer sbUrl = new StringBuffer();

        sbUrl.append(url);
        sbUrl.append(query);
        sbUrl.append(Constants.NEW_THISPAGE);
        sbUrl.append("=");
        sbUrl.append(totalPageSize);

        return sbUrl.toString();
    }

    /**
     * @return the pageNext
     */
    public String getPageNext() {
        StringBuffer sbUrl = new StringBuffer();

        sbUrl.append(url);
        sbUrl.append(query);
        sbUrl.append(Constants.NEW_THISPAGE);
        sbUrl.append("=");
        sbUrl.append(thisPage + 1);

        return sbUrl.toString();
    }

    /**
     * @return the pageBefore
     */
    public String getPageBefore() {
        StringBuffer sbUrl = new StringBuffer();

        sbUrl.append(url);
        sbUrl.append(query);
        sbUrl.append(Constants.NEW_THISPAGE);
        sbUrl.append("=");
        sbUrl.append(thisPage - 1);

        return sbUrl.toString();
    }

    // /** offset */
    // private String pageFirst;
    // /** offset */
    // private String pageLast;
    // /** offset */
    // private String pageNext;
    // /** offset */
    // private String pageBefore;

    /**
     * @return the query
     */
    public StringBuffer getQuery() {
        return query;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @return the pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize
     *            the pageSize to set
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

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
     * @return the totalPageSize
     */
    public int getTotalPageSize() {
        return totalPageSize;
    }

    /**
     * @param totalPageSize
     *            the totalPageSize to set
     */
    public void setTotalPageSize(int totalPageSize) {
        this.totalPageSize = totalPageSize;
    }

    /**
     * @return the totalResultSize
     */
    public int getTotalResultSize() {
        return totalResultSize;
    }

    /**
     * @param totalResultSize
     *            the totalResultSize to set
     */
    public void setTotalResultSize(int totalResultSize) {
        this.totalResultSize = totalResultSize;
    }

    /**
     * @param url
     *            the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

}
