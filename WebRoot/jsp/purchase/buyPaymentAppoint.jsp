<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>付款确认</title>
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
		//确定
		$('#confirmBtn').click(function(){
			var paymentDate = $("#paymentDate")[0].value;
			var number = $("#number")[0].value;
			var result = check(paymentDate,number);
			if(result == true){
				$.post("${ctx}/confirmBuyPayment.do",$("#confirmBuyPaymentForm").serializeArray(),waitResult,"html");
			}
		});
	});
	function check(paymentDate,number){
		if(paymentDate == ""||paymentDate == null){
			alert("承兑开具日期不可为空！");
			return false;
		}else{
			var date = paymentDate.split("-");  
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
		if(number ==""||number==null){
			alert("凭证号不可为空！");
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
	    alert("确认失败！");
	  }
	}
</script>
</head>

<body>
<form action="confirmBuyPayment" method="post" id="confirmBuyPaymentForm" name="confirmBuyPaymentForm">
<input type="hidden" name="id" value="${param.id}"/>
<input type="hidden" name="status" value="${param.status}"/>
<table width="96%" border="0" cellpadding="0" cellspacing="0" align="center">
   <tr>
    <td align="center"><br />
            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
              <tr>
                <td class="td_01" width="20%">付款日期</td>
                <td class="td_02" width="30%"><input type="text" name="paymentDate" id="paymentDate" maxlength="12"
											style="width:120px;" onfocus="calendar()"
											readonly="readonly" value="" /></td>
              </tr>
               <tr>
                <td class="td_01" width="20%">凭证号</td>
                <td class="td_02"><input type="text" name="number" id="number" style="width:240px;" maxlength="20"/></td>
                </tr>
            </table></td>
  </tr>
  <tr>
    <td height="40" align="center" valign="bottom">
    	<a><img id="confirmBtn" src="${ctx}/images/btnConfirm.gif" /></a>
    </td>
  </tr>
</table>
</form>
</body>
</html>
