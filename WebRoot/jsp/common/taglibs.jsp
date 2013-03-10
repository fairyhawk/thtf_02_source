<%@ taglib prefix="html" uri="/WEB-INF/tlds/struts-html.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/tlds/struts-bean.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/tlds/struts-logic.tld"%>
<%@ taglib prefix="nested" uri="/WEB-INF/tlds/struts-nested.tld"%>
<%@ taglib prefix="tiles" uri="/WEB-INF/tlds/struts-tiles.tld"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="pg" uri="/WEB-INF/pager-taglib.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tlds/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tlds/fn.tld"%>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<c:set var="ctx">${pageContext.request.contextPath}</c:set>
<script>
var jsCtx = '${ctx}';
</script>
