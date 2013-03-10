/**
 * ClassName  LimitSqlExecutor
 *
 * History
 * Create User: Shiy
 * Create Date: 2010-8-24
 * Update User:
 * Update Date:
 */
package cn.com.thtf.egov.cms.ibatis.ext;

import java.sql.Connection;
import java.sql.SQLException;

import com.ibatis.sqlmap.engine.execution.SqlExecutor;
import com.ibatis.sqlmap.engine.mapping.statement.RowHandlerCallback;
import com.ibatis.sqlmap.engine.scope.RequestScope;

/**
 * iBatis实现物理翻页的扩展
 * 
 * @author Shiy
 */

public class LimitSqlExecutor extends SqlExecutor {

    private Dialect dialect;

    private boolean enableLimit = true;

    public Dialect getDialect() {
        return dialect;
    }

    public void setDialect(Dialect dialect) {
        this.dialect = dialect;
    }

    public boolean isEnableLimit() {
        return enableLimit;
    }

    public void setEnableLimit(boolean enableLimit) {
        this.enableLimit = enableLimit;
    }

    public boolean supportsLimit() {
        if (enableLimit && dialect != null) {
            return dialect.supportsLimit();
        }
        return false;
    }

    @Override
    public void executeQuery(RequestScope request, Connection conn, String sql,
            Object[] parameters, int skipResults, int maxResults,
            RowHandlerCallback callback) throws SQLException {
        if ((skipResults != NO_SKIPPED_RESULTS || maxResults != NO_MAXIMUM_RESULTS)
                && supportsLimit()) {
            sql = dialect.getLimitString(sql, skipResults, maxResults);
            skipResults = NO_SKIPPED_RESULTS;
            maxResults = NO_MAXIMUM_RESULTS;
        }
        super.executeQuery(request, conn, sql, parameters, skipResults, maxResults,
                callback);
    }
}
