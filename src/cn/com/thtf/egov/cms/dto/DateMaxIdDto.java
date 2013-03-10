/**
 * ClassName  DateMaxIdDto
 *
 * History
 * Create User: Administrator
 * Create Date: 2010-7-29
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

import java.util.Date;

/**
 * 生成编号时使用
 * 
 * @author ChenHuajiang
 */

public class DateMaxIdDto {

    private int maxId;
    private Date date;

    public int getMaxId() {
        return this.maxId;
    }

    public void setMaxId(int maxId) {
        this.maxId = maxId;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
