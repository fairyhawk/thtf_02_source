<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>需求单明细</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
		<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="${ctx}/js/messages_cn.js"></script>
<style type="text/css">
<!--
.treven {
	background-color: #f3fbff;
}
.over {
	background-color: #ECECEC;
}
.rowselected {
	background-color: #868686;
}
-->
</style>
<script language="JavaScript"> 
    
	$(document).ready(function(){
		if($("#xxxlist")){
			$("#xxxlist tr:even").addClass("treven");
			$("#xxxlist tr").each(function(i){
				$(this).mouseover(function(){
					$(this).addClass("over");
				});
				$(this).mouseout(function(){
					$(this).removeClass("over");
				});
				$(this).click( function(){
				  if( $(this).hasClass("rowselected") ){
					$(this).removeClass("rowselected");
				  }else{
					$(this).addClass("rowselected");
				  }
				});
			});
		}

		var msg = "${msg}";  //获取服务端信息	
		if (msg != "") 
		{
			alert(msg);
		}	
	});

	//检索
	function searchDemand(){
		var prodCode = $("#prodCode").val();
		var prodName = $("#prodName").val();
		var prodType = $("#prodType").val();
		document.forms[0].action = "getDemandList.do?prodCode="+$.trim(prodCode)+'&prodName='+encodeURI($.trim(prodName),"UTF-8")+'&prodType='+encodeURI($.trim(prodType),"UTF-8");
		$("#frmDemand").submit();
    }
</script>

</head>
<body>
<form action="" method="get" id ="frmDemand">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" /> &nbsp;当前位置： 采购管理 &gt;&gt; 需求管理</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td align="center"><div style="width:100%; text-align:right">&nbsp;</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="xxxlist">
        <tr>
          <th nowrap="nowrap">产品合同号</th>
          <th nowrap="nowrap">公司合同号</th>
          <th nowrap="nowrap">产品编码</th>
          <th nowrap="nowrap">产品名称</th>
          <th nowrap="nowrap">规格型号</th>
          <th nowrap="nowrap">单位</th>
          <th nowrap="nowrap">需求数</th>
          <th nowrap="nowrap" >要求发货日期</th>
          <th nowrap="nowrap" >客户名称</th>
          <th nowrap="nowrap" >人员名称</th>
          <th nowrap="nowrap" >人员区域</th>
          <th nowrap="nowrap" >确认日期</th>
          <th nowrap="nowrap" >需求单状态</th>
		  <c:if test="${showColm=='showNum'}">
          <th nowrap="nowrap" >&nbsp;操作&nbsp;</th>
		  </c:if>
        </tr>
       <c:forEach var="demandDetailList" items="${demandDetailList}">
        <tr>
          <td nowrap="nowrap" height="18px">${demandDetailList.prodCntrtCode}&nbsp;</td>
          <td nowrap="nowrap">${demandDetailList.compCntrtCode}&nbsp;</td>
          <td nowrap="nowrap" width="90">${demandDetailList.prodCode}&nbsp;</td>
          <td nowrap="nowrap">${demandDetailList.prodName}&nbsp;</td>
          <td nowrap="nowrap">${demandDetailList.prodType}&nbsp;</td>
          <td nowrap="nowrap" width="36">${demandDetailList.prodUnit}&nbsp;</td>
          <td nowrap="nowrap" width="50" style="text-align:right;">${demandDetailList.demandCount}&nbsp;</td>
          <td nowrap="nowrap" width="84">${demandDetailList.sendDate}&nbsp;</td>
          <td nowrap="nowrap" title="${demandDetailList.customerName}"><div class=ellipsis_div style="width:120px;">${demandDetailList.customerName}&nbsp;</td>
          <td nowrap="nowrap">${demandDetailList.userName}&nbsp;</td>
          <td nowrap="nowrap">${demandDetailList.userAreaName}&nbsp;</td>
          <td nowrap="nowrap" width="84">${demandDetailList.confirmDate}&nbsp;</td>
		  <c:if test="${demandDetailList.demandStatus==0}">
			<td nowrap="nowrap">未确认</td>
		  </c:if>
		  <c:if test="${demandDetailList.demandStatus==1}">
			<td nowrap="nowrap">已确认</td>
		  </c:if>
		  <c:if test="${showColm=='showNum'}">
          <td nowrap="nowrap" width="40px" style="text-align:center">
			  <c:if test="${demandDetailList.demandStatus==0}">
			    <a href="${ctx}/modifyDemandById.do?demandId=${demandDetailList.demandId}&prodId=${demandDetailList.prodId}">确认</a></c:if>
			  <c:if test="${demandDetailList.demandStatus==1}">确认 </c:if>
		  </td>
		  </c:if>
	   </c:forEach>
      </table>
      <br />
      <table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table">
        <tr>
          <td align="right" height="25px"><%@ include file="/jsp/common/newPage.jsp"%></td>
        </tr>
        <tr>
          <td height="35px" align="center" valign="bottom"><a href="${ctx}/getDemandList.do?"><img src="${ctx}/images/btnBack.gif" width="65" height="20" /></a></td>
		</tr>
      </table></td>
    <td >&nbsp;</td>
  </tr>
</table>
</form>
</body>
</html>
