<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>邮件查看</title>
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
		var tag = true;
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

			if("${errorMsg}" != ""){
				alert("${errorMsg}");
				tag = true;
				 
			}

			$("#subBtn").click(function(){   
				 if(tag){
					tag = false;
					document.location.assign("${ctx}/sendMail.do?mailPara.id=${mail.id}");   
				 }else{
					alert("请不要重复发送！");
				 }
			});
			
		});  
	</script> 
</head>

<body>
<body>
 
<input type="hidden" name="addPara.mreturnId" value="${mreturn.id}" />
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 邮件管理 &gt;&gt; 邮件管理 &gt;&gt; 邮件查看</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td><br />
      <div class="div_tiao"> &nbsp;&nbsp;邮件信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" width="20%" height="18px">发件时间</td>
          <td class="td_02" width="30%" >${mail.datetime}&nbsp;</td>
          <td class="td_01" width="20%">邮件类型</td>
          <td class="td_02" width="30%" >
				<c:if test="${mail.type==1}">出库</c:if> 
				<c:if test="${mail.type==2}">入库</c:if>
				<c:if test="${mail.type==3}">返厂</c:if>
				<c:if test="${mail.type==4}">退货</c:if>

				<c:if test="${mail.type==5}">移库出库</c:if> 
				<c:if test="${mail.type==6}">移库入库</c:if> 
				<c:if test="${mail.type==7}">样品借出</c:if>
				<c:if test="${mail.type==8}">样品归还</c:if>
				<c:if test="${mail.type==9}">开票通知</c:if> 

				<c:if test="${mail.type==10}">回款确认</c:if>
				<c:if test="${mail.type==11}">付款提醒</c:if>
				<c:if test="${mail.type==12}">发货异常</c:if> 
		  </td>
        </tr>
        <tr>
          <td class="td_01" width="20%" height="18px">发件人</td>
          <td class="td_02" width="30%">${mail.userName}&nbsp;</td> 
          <td class="td_01" width="20%" height="18px">收件人</td>
          <td class="td_02" width="30%">${mail.name}&nbsp;</td> 
        </tr>
        <tr>
          <td class="td_01" width="20%" height="18px">主题</td>
          <td class="td_02" width="30%" colspan="3">${mail.subject}&nbsp;</td> 
        </tr>
		<tr>
          <td colspan="4">${mail.text}</td> 
        </tr>
      </table></td> 
    <td >&nbsp;</td>
  </tr>
  <tr>
    <td></td>
    <td height="50px" align="center" valign="bottom">  
		<a href="javascript:void(0);" id="subBtn"><img src="${ctx}/images/btnFYJjpg.jpg" /></a>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
		<a href="${ctx}/mailList.do"><img src="${ctx}/images/btnBack.gif" /></a>
	</td>
    <td></td>
  </tr>
</table>
 
</body>
</html>
