<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>库存明细</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<style type="text/css">
<!--
.treven {
	background-color: #f3fbff;
}
.over { 	
	background-color: #ECECEC;
}
-->
</style>
<script language="JavaScript"> 
		<!--
			$(document).ready(function(){
				if($("#table")){
					$("#table tr:even").addClass("treven");
					$("#table tr").each(function(i){
						$(this).mouseover(function(){
							$(this).addClass("over");
						});
						$(this).mouseout(function(){
							$(this).removeClass("over");
						});
					});
				}
			});
		//-->
		</script> 
</head><br />
<table width="96%" border="0" cellpadding="0" cellspacing="0" class="biao1" align="center" id="table">
	<tr>
		<th nowrap="nowrap">产品分类名称</th>
        <th nowrap="nowrap">产品系列名称</th>
        <th nowrap="nowrap">产品编码</th>
        <th nowrap="nowrap">产品名称</th>
        <th nowrap="nowrap">规格型号</th>
		
        <th nowrap="nowrap">单位</th>
		<c:if test="${roleId==5||roleId==8||roleId==10||roleId==11||roleId==16||roleId==17||roleId==18}"> 
			 <th nowrap="nowrap">库存单价</th>
		</c:if>
        <th nowrap="nowrap">库房名称</th> 
        <th nowrap="nowrap">库存总数</th> 
        <th nowrap="nowrap">发货冻结数</th>

        <th nowrap="nowrap">备货数</th>
        <th nowrap="nowrap">发货可用数</th> 
	</tr>
	<logic:iterate id="stock" name="stockList">
	<tr>
		<td nowrap="nowrap" width="81px" height="18">${stock.productTypeName}&nbsp;</td>
        <td nowrap="nowrap" width="87px">${stock.productSerieName}&nbsp;</td>
        <td nowrap="nowrap" width="96px">${stock.productCode}&nbsp;</td>
        <td nowrap="nowrap" width="120px">${stock.productName}&nbsp;</td>
        <td nowrap="nowrap" width="80px" >${stock.productType}&nbsp;</td>

        <td nowrap="nowrap" width="60px" >${stock.productUnit}&nbsp;</td> 
		<c:if test="${roleId==5||roleId==8||roleId==10||roleId==11||roleId==16||roleId==17||roleId==18}"> 
			<td nowrap="nowrap" style=" text-align:right; padding-right:5px;">${stock.stockPrice}&nbsp;</td>
		</c:if>
		<td nowrap="nowrap" width="72px">${stock.stockroomName}&nbsp;</td>
		<td nowrap="nowrap" width="84px" style="text-align:right;">${stock.stockNum}&nbsp;</td> 
        <td nowrap="nowrap" width="64px" style="text-align:right;">${stock.stockSendLock}&nbsp;</td>
        <td nowrap="nowrap" width="64px" style="text-align:right;">${stock.stockPrepared}&nbsp;</td>

        <td nowrap="nowrap" width="80px" style="text-align:right;">${stock.sendGoodsUseCount}&nbsp;</td>
        
	</tr> 
	</logic:iterate>
</table><br/>
<table border="0" cellpadding="0" cellspacing="0" width="96%" id="ec_table" align="center">
	<tr>
		<td align="right" ><%@ include file="/jsp/common/newPage.jsp"%></td>
	</tr>
</table>
</body>
</html>
