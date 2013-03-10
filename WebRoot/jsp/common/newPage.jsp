<%@ page language="java" pageEncoding="UTF-8"%> 

<logic:notEqual name="NewPage" property="totalResultSize" value="0">

共有记录&nbsp;${NewPage.totalResultSize}&nbsp;条,共&nbsp;${NewPage.totalPageSize}&nbsp;页,当前第&nbsp;${NewPage.thisPage}&nbsp;页

<logic:greaterEqual name="NewPage" property="thisPage" value="1">
	<a href="${NewPage.pageFirst}">首页</a>
</logic:greaterEqual>
<logic:lessThan name="NewPage" property="thisPage" value="1">
	首页
</logic:lessThan> 
 
<logic:lessThan name="NewPage" property="thisPage" value="2">
	上一页
</logic:lessThan>
<logic:greaterEqual name="NewPage" property="thisPage" value="2">
	<a href="${NewPage.pageBefore}">上一页</a>
</logic:greaterEqual> 

<logic:greaterEqual name="NewPage" property="totalPageSize" value="${NewPage.thisPage+1}">
	<a href="${NewPage.pageNext}">下一页</a>
</logic:greaterEqual> 
<logic:lessThan name="NewPage" property="totalPageSize" value="${NewPage.thisPage+1}">
	下一页
</logic:lessThan>

<logic:greaterThan name="NewPage" property="totalPageSize" value="${NewPage.thisPage}">
	<a href="${NewPage.pageLast}">尾页</a>
</logic:greaterThan>
<logic:lessEqual name="NewPage" property="totalPageSize" value="${NewPage.thisPage}">
	尾页
</logic:lessEqual>

</logic:notEqual>