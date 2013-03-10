<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>需求单</title>
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
    <td align="center"><br />
      <div class="mo_wp">
        <div style="display: ; " class="mo_con" >
          <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
            <tr>
              <td class="td_01" width="20%">产品编码</td>
              <td class="td_02" width="30%"><input type="text" name="prodCode" id="prodCode" style="width:120px;" value="${prodCode}"/></td>
              <td class="td_01" width="20%">产品名称</td>
              <td class="td_02" width="30%"><input type="text" name="prodName" id="prodName" style="width:120px;" value="${prodName}"/></td>
            </tr>
            <tr>
              <td class="td_01">规格型号</td>
              <td class="td_02"><input type="text" name="prodType" id="prodType" style="width:240px;" value="${prodType}"/></td>
              <td class="td_01" width="20%">&nbsp;</td>
              <td class="td_02" width="30%">&nbsp;</td>
            </tr>
            <tr>
              <td colspan="4" align="center" style="height:30px;"><img src="${ctx}/images/btn_JianSuo.gif" onClick="searchDemand();"/></td>
            </tr>
          </table>
        </div>
        <div class="mo_title" onclick="fMainListToggle(this)">
          <div  style="text-align:center"><img id="imgShang" src="${ctx}/images/shang_sj.png" /></div>
        </div>
      </div></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td align="center">
      <br />
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="xxxlist">
        <tr>
          <th nowrap="nowrap">产品编码</th>
          <th nowrap="nowrap">产品名称</th>
          <th nowrap="nowrap">规格型号</th>
          <th nowrap="nowrap">单位</th>
          <th nowrap="nowrap" >未确认需求数</th>
		  <c:if test="${showColm=='showNum' || showColm=='showAll'}">
          <th nowrap="nowrap" >库存总数</th>
          <th nowrap="nowrap" >采购合同数</th>
          <th nowrap="nowrap" >销售合同数</th>
		  </c:if>
		  <c:if test="${showColm=='showArea'}">
          <th nowrap="nowrap" >人员区域</th>
		  </c:if>
          <th nowrap="nowrap" width="40">&nbsp;操作&nbsp;</th>
        </tr>
       <c:forEach var="demandList" items="${demandList}">
        <tr>
          <td nowrap="nowrap" height="18px" width="90">${demandList.prodCode}&nbsp;</td>
          <td nowrap="nowrap">${demandList.prodName}&nbsp;</td>
          <td nowrap="nowrap">${demandList.prodType}&nbsp;</td>
          <td nowrap="nowrap" width="36px">${demandList.prodUnit}&nbsp;</td>
          <td nowrap="nowrap" width="84" style="text-align:right;">${demandList.unconfirmedCount}&nbsp;</td>
		  <c:if test="${showColm=='showNum' || showColm=='showAll'}">
          <td nowrap="nowrap" width="84" style="text-align:right;">${demandList.stockTotalCount}&nbsp;</td>
          <td nowrap="nowrap" width="84" style="text-align:right;">${demandList.buyCount}&nbsp;</td>
          <td nowrap="nowrap" width="84" style="text-align:right;">${demandList.sellCount}&nbsp;</td>
		  </c:if>
		  <c:if test="${showColm=='showArea'}">
          <td nowrap="nowrap">${demandList.userAreaName}&nbsp;</td>
		  </c:if>
          <td nowrap="nowrap" width="40">
              <a href="${ctx}/getDemandDetailList.do?prodId=${demandList.prodId}">明细</a>&nbsp;
		  </td>
         </tr>
	   </c:forEach>
      </table>
      <br />
      <table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table">
        <tr>
          <td align="right" height="25px"><%@ include file="/jsp/common/newPage.jsp"%></td>
        </tr>
      </table></td>
    <td >&nbsp;</td>
  </tr>
</table>
</form>
</body>
</html>
