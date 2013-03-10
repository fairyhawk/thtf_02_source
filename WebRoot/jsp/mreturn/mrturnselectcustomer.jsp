<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>客户选择</title>
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
				//注入检索click事件
				$("#jiansuoBtn").click(
					function(){
						document.forms[0].submit();
					}
				);
				//注入提交click事件
				$("#btnChoice").click(
					function(){
						var result = false;
						$('input[name=radio]').each(function(i){
						 	if ($(this).attr("checked") == true) {
						          result = true;
						          opener.addCustomer($(this).val()); 
								  //选择完毕关闭小页面
								  window.close();
						    }
						});
						if(!result){
						alert('请选择客户！');
						return false;
					}
					}
				);
				
			});
		//-->
		</script>
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
</head>
<body>
<br />
<div class="mo_wp">
  <div style="display: ; " class="mo_con" >
  	<form action="getMReturnCustomerSelect.do" name="MReturnCustomerForm" > 
    <table width="96%" border="0" cellpadding="0" cellspacing="0" class="biao3" align="center">
      <tr>
        <td class="td_03" width="50%">客户名称</td>
        <td class="td_04"><input type="text" name="customerE.name" id="customerE.name" style="width:240px;" value="${mReturnListForm.customerE.name}" />
        </td>
      <tr>
        <td colspan="2" align="center" height="30px"><img id="jiansuoBtn" src="${ctx}/images/btn_JianSuo.gif" /></td>
      </tr>
    </table>
  </div>
  <div class="mo_title" onclick="fMainListToggle(this)">
    <div  style="text-align:center"><img src="${ctx}/images/shang_sj.png" /></div>
  </div>
</div>
<table border="0" cellpadding="0" cellspacing="0" width="96%" align="center" class="biao1" id="table">
  <tr>
    <th width="30px">选择</th>
    <th>客户名称</th>
  </tr>
  <logic:present name="list">
	<logic:iterate id="c" name="list" >
	  <tr>
	    <td>
	    <input type="radio" name="radio" id="radio" value="${c.id},${c.name}" />
	    </td>
	    <td>${c.name}&nbsp;</td>
	  </tr>
	</logic:iterate>
  </logic:present>
</table>
<table cellpadding="0" cellspacing="0" width="96%" border="0" align="center">
  <tr>
    <td align="right" height="25"><%@ include file="/jsp/common/newPage.jsp"%></td>
  </tr>
  <tr>
    <td height="50px" align="center" valign="bottom"><img id="btnChoice" src="${ctx}/images/btnChoice.gif" /></td>
  </tr>
</table>
</body>
</html>
