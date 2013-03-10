<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="tf" uri="localhost" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运单查看</title>

 	<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" /> 
	<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.bgiframe.pack.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.jmesa.js"></script>
	<script type="text/javascript" src="${ctx}/js/jmesa.js"></script> 
	<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>

<script type="text/javascript">
<!--
function MM_jumpMenu(targ,selObj,restore){ //v3.0
  eval(targ+".location='"+selObj.options[selObj.selectedIndex].value+"'");
  if (restore) selObj.selectedIndex=0;
}
//-->
</script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td class="ye_header_left">&nbsp;</td>
		<td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 物流管理 &gt;&gt; 运单管理 &gt;&gt; 运单查看</td>
		<td class="ye_header_right">&nbsp;</td>
	</tr>
	<tr>
		<td >&nbsp;</td>
		<td><br />
			<div class="div_tiao"> &nbsp;&nbsp;发货信息 </div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
				<tr>
					<td class="td_01" width="20%" height="18px">装箱单号</td>
					<td class="td_02" width="30%">${wayBillViewDto.box_id}&nbsp;</td>
					<td class="td_01" width="20%">发货日期</td>
					<td class="td_02" width="30%">${wayBillViewDto.box_date}&nbsp;</td>
				</tr>
				<tr>
					<td class="td_01" height="18px">客户名称</td>
					<td class="td_02" >${wayBillViewDto.customer_name}&nbsp;</td>
					<td class="td_01">货物接收单位名称</td>
					<td class="td_02" >${wayBillViewDto.name}&nbsp;</td>
				</tr>
				<tr>
					<td class="td_01" height="18px">发货地址</td>
					<td colspan="3" class="td_02" >${wayBillViewDto.customer_address}&nbsp;</td>
				</tr>
				<tr>
					<td class="td_01" height="18px">联系人</td>
					<td class="td_02" >${wayBillViewDto.linkman}&nbsp;</td>
					<td class="td_01">邮编</td>
					<td class="td_02" >${wayBillViewDto.customer_postcode}&nbsp;</td>
				</tr>
				<tr>
					<td class="td_01" height="18px">电话</td>
					<td class="td_02" >${wayBillViewDto.customer_tel}&nbsp;</td>
					<td class="td_01">手机</td>
					<td class="td_02" >${wayBillViewDto.customer_mobile}&nbsp;</td>
				</tr>
				<tr>
					<td class="td_01" width="20%" height="18px">装箱件数</td>
					<td class="td_02" width="30%">${wayBillViewDto.box_count}&nbsp;件</td> 
					<td class="td_01" width="20%">要求货运方式</td>
					<td class="td_02" width="30%"> 
					     <c:if test="${wayBillViewDto.request_way==0}"> 无指定 </c:if>
      		             <c:if test="${wayBillViewDto.request_way==1}"> 自提 </c:if>
		                 <c:if test="${wayBillViewDto.request_way==2}"> 快递 </c:if>
		                 <c:if test="${wayBillViewDto.request_way==3}"> 汽运 </c:if>
		                 <c:if test="${wayBillViewDto.request_way==4}"> 铁运 </c:if>
		                 <c:if test="${wayBillViewDto.request_way==5}"> 航空 </c:if>
		                 <c:if test="${wayBillViewDto.request_way==6}"> 海运 </c:if>
		                 <c:if test="${wayBillViewDto.request_way==7}"> 中铁 </c:if>
		                 <c:if test="${wayBillViewDto.request_way==8}"> 市内送货 </c:if>
		                 <c:if test="${wayBillViewDto.request_way==9}"> 市内快递 </c:if>  
      	                 &nbsp;</td>
					
				</tr>
			</table>
			<br />
			<div class="div_tiao"> &nbsp;&nbsp;运单信息 </div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
				<tr>
					<td class="td_01" width="20%" height="18px">运单号</td>
					<td class="td_02" width="30%">${wayBillViewDto.no}&nbsp;</td>
					<td class="td_01" width="20%">实际货运方式</td>
					<td class="td_02" width="30%">
      		             <c:if test="${wayBillViewDto.reality_way==1}"> 自提 </c:if>
		                 <c:if test="${wayBillViewDto.reality_way==2}"> 快递 </c:if>
		                 <c:if test="${wayBillViewDto.reality_way==3}"> 汽运 </c:if>
		                 <c:if test="${wayBillViewDto.reality_way==4}"> 铁运 </c:if>
		                 <c:if test="${wayBillViewDto.reality_way==5}"> 航空 </c:if>
		                 <c:if test="${wayBillViewDto.reality_way==6}"> 海运 </c:if>
		                 <c:if test="${wayBillViewDto.reality_way==7}"> 中铁 </c:if>
		                 <c:if test="${wayBillViewDto.reality_way==8}"> 市内送货 </c:if>
		                 <c:if test="${wayBillViewDto.reality_way==9}"> 市内快递 </c:if>  
      	                 &nbsp;</td>
				</tr>
				<tr>
					<td class="td_01" height="18px">预计到货日期</td>
					<td class="td_02" >${wayBillViewDto.estimate_date}&nbsp;</td>
					<td class="td_01">联系电话</td>
					<td class="td_02" >${wayBillViewDto.box_tel}&nbsp;</td>
				
				</tr>
				<tr>
					<td class="td_01" height="18px">在途信息</td>
					
					<td colspan="3" class="td_02">
					${tf:replaceBr(wayBillViewDto.box_info)}
					&nbsp;</td>
				</tr>
				<tr>
					<td class="td_01" height="18px">到货日期</td>
					<td class="td_02" >${wayBillViewDto.arrival_date}&nbsp;</td>
					<td class="td_01">签收日期</td>
					<td class="td_02" >${wayBillViewDto.signoff_date}&nbsp;</td>
				</tr>
				<tr>
					<td class="td_01" height="18px">签收人</td>
					<td class="td_02" >${wayBillViewDto.signoff_name}&nbsp;</td>
					<td class="td_01">&nbsp;</td>
					<td class="td_02">&nbsp;</td>
				</tr>
			</table></td>
	</tr>
	<tr>
		<td></td>
		<td height="50px" align="center" valign="bottom">
		 <a href=#><img src="${ctx}/images/btnBack.gif"   
		<c:if test="${back == true}">onclick="javascript:window.location='${ctx}/getSendGoodsList.do'"</c:if>  
        <c:if test="${back != true}">onclick="javascript:window.location='${ctx}/wayBillAll.do'"</c:if> 
		
		/></a>
		
        
		</td>
		<td></td>
	</tr>
</table>
</body>
</html>