/**
 * ClassName  CustomerCreditDto
 *
 * History
 * Create User: Lubo
 * Create Date: 2010-5-21
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 客户信用DTO
 * 
 * @author Lubo
 */

public class CustomerCreditDto {

    /** 客户信用ID */
    private Integer id;
    /** 客户信用明细ID */
    private Integer detileId;
    /** 客户信用 使用额度 */
    private BigDecimal money;
    /** 客户信用ID */
    private Timestamp timeStamp;

    /**
     * @return the money
     */
    public BigDecimal getMoney() {
        return money;
    }

    /**
     * @param money the money to set
     */
    public void setMoney(BigDecimal money) {
        this.money = money;
    }

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
     * @return the detileId
     */
    public Integer getDetileId() {
        return detileId;
    }

    /**
     * @param detileId
     *            the detileId to set
     */
    public void setDetileId(Integer detileId) {
        this.detileId = detileId;
    }

    /**
     * @return the timeStamp
     */
    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    /**
     * @param timeStamp
     *            the timeStamp to set
     */
    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }
}
