<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>承兑确认</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/Calendar1.js"></script>
<script type="text/javascript">
<!--
function MM_jumpMenu(targ,selObj,restore){ //v3.0
  eval(targ+".location='"+selObj.options[selObj.selectedIndex].value+"'");
  if (restore) selObj.selectedIndex=0;
}
//-->
	$(document).ready(function(){
		$.ajaxSetup({ 
  			async: false 
  		}); 
	});	
	$(function(){
		//付款单号
		var id = "${param.id}";
		//付款单状态
		var id = "${param.status}";
		//帐期
		$("#arterm")[0].value = "${param.arterm}";
		//确定
		$('#confirmBtn').click(function(){
			var acceptDate = $("#acceptDate")[0].value;
			var arterm = $("#arterm")[0].value;
			var acceptNumber = $("#acceptNumber")[0].value;
			var result = check(acceptDate,arterm,acceptNumber);
			if(result == true){
				$.post("${ctx}/acceptBuyPayment.do",$("#acceptBuyPaymentForm").serializeArray(),waitResult,"html");
			}
		});
	});
	function check(acceptDate,arterm,acceptNumber){
		if(acceptDate == ""||acceptDate == null){
			alert("承兑开具日期不可为空！");
			return false;
		}else{
			var date = acceptDate.split("-");  
			if(date[0] < new Date().getFullYear()){
				alert("要求发货日期必须大于等于今天");
				return false;
			} 
			if(date[0] == new Date().getFullYear() 
				&& date[1] < new Date().getMonth()+1 
				){
				alert("要求发货日期必须大于等于今天");
				return false;
			}
			if(date[0] == new Date().getFullYear() 
				&& date[1] == new Date().getMonth()+1 
				&& date[2] < new Date().getDate()){ 
				alert("要求发货日期必须大于等于今天");
				return false;
			} else {
			} 
		}
		if(arterm ==""||arterm==null){
			alert("帐期不可为空！");
			return false;
		}else{
			var regExp = /^[0-9]\d*/;
			if (regExp.test(arterm) == false) {
				alert('帐期只能输入数字！');
				return false;
			}
		}
		if(acceptNumber==""||acceptNumber==null){
			alert("承兑票号不可为空！");
			return false;
		}
		return true;
	}
	//提交后获取后台新建结果
	function waitResult(result){
	  if(result=='true'){
	  	opener.refreshDate();
	  	window.close();
	  }else{
	    alert("承兑失败！");
	  }
	}
</script>
</head>

<body>
<form action="acceptBuyPayment" method="post" id="acceptBuyPaymentForm" name="acceptBuyPaymentForm">
<input type="hidden" name="id" value="${param.id}"/>
<input type="hidden" name="status" value="${param.status}"/>
<table width="96%" border="0" cellpadding="0" cellspacing="0" align="center">
	<tr>
		<td align="center"><br />
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
	              <tr>
		              <td class="td_01" width="20%">承兑开具日期</td>
		              <td class="td_02" width="30%"><input type="text" name="acceptDate" id="acceptDate" maxlength="12"
											style="width:120px;" onfocus="calendar()"
											readonly="readonly" value="" /></td>
		              <td class="td_01" width="20%">帐期</td>
		              <td class="td_02" width="30%"><input type="text" name="arterm" id="arterm" value="" style="width:120px;" maxlength="3"/>天</td>
		              </tr>
		              <tr>
		              <td class="td_01" width="20%">承兑票号</td>
		              <td colspan="3" class="td_02"><input type="text" name="acceptNumber" id="acceptNumber" style="width:240px;" maxlength="20"/></td>
	               </tr>
	            </table>
			         
		</td>
	</tr>
	<tr>
		<td height="40" align="center" valign="bottom">
			<a href="#"><img id="confirmBtn" src="${ctx}/images/btnConfirm.gif" /></a>
		</td>
	</tr>
</table>
</form> 
</body>
</html>
