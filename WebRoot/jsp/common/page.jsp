<%@ page language="java" pageEncoding="UTF-8"%>
<input type="hidden" name="pager.offset"/>
<input type="hidden" name="pageSize" />
<input type="hidden" name="ye" />
<pg:pager url="${ctx}/demo/demoManage.do" items="${Page.totalSize}"
	maxPageItems="${Page.pageSize}" maxIndexPages="10"
	export="offset,currentPageNumber=pageNumber">
	<pg:param name="method" value="list" />
	
	共有记录&nbsp;${Page.totalSize}&nbsp;条,共&nbsp;${Page.offset}&nbsp;页,当前第&nbsp;${Page.thisPage}&nbsp;页
	
	<pg:first export="firstItem">
		<a href="javascript:gotoPage(${firstItem - 1},${Page.pageSize});"><font color="#000000">首页</font></a>
	</pg:first>
	<logic:equal name="Page" property="thisPage" value="1"> 
		<font color="#000000">上一页</font>
	</logic:equal>
	<pg:prev export="firstItem">
		<a href="javascript:gotoPage(${firstItem - 1},${Page.pageSize});"><font color="#000000">上一页</font></a>
	</pg:prev> 
	<pg:next export="firstItem">
		<a href="javascript:gotoPage(${firstItem - 1},${Page.pageSize});"><font color="#000000">下一页</font></a>
	</pg:next>
	<logic:equal name="Page" property="thisPage" value="${Page.offset}"> 
		<font color="#000000">下一页</font>
	</logic:equal>
	<pg:last export="firstItem">
		<a href="javascript:gotoPage(${firstItem - 1},${Page.pageSize});"><font color="#000000">尾页</font></a>
	</pg:last>
</pg:pager>
	
	