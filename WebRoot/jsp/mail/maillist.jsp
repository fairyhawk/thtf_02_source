<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>邮件管理</title>
	<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
	<script type="text/javascript" src="${ctx}/js/Calendar1.js"></script>
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
			if($("#table")){
				$("#table tr:even").addClass("treven");
				$("#table tr").each(function(i){
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
	</script> 
</head>

<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 邮件管理 &gt;&gt; 邮件管理</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
   <tr>
    <td >&nbsp;</td>
    <td align="center"><br />
    	<div class="mo_wp">
          <div style="display: ; " class="mo_con" >
		  <form action="mailList.do" id="mailList">
            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
              <tr>
				<td class="td_01">发件起始日期</td>
				<td class="td_02">
					<input type="text" name="mailPara.starttime" maxlength="12" style="width:120px;" onfocus="calendar()" readonly="readonly" value="${mailPara.starttime}"/>
				</td>
				<td class="td_01">发件终止日期</td>
				<td class="td_02">
					<input type="text" name="mailPara.endtime" maxlength="12" style="width:120px;" onfocus="calendar()" readonly="readonly" value="${mailPara.endtime}"/>
				</td>
			  </tr>
			  <tr> 
				<td class="td_01">邮件类型</td>
				<td class="td_02">
					<html:select property="mailPara.type" value="${mailPara.type}" style=" width:126px">
						<html:option value="">--请选择--</html:option> 
						<html:option value="1">出库</html:option> 
						<html:option value="2">入库</html:option>
						<html:option value="3">返厂</html:option>
						<html:option value="4">退货</html:option>

						<html:option value="5">移库出库</html:option> 
						<html:option value="6">移库入库</html:option> 
						<html:option value="7">样品借出</html:option>
						<html:option value="8">样品归还</html:option>
						<html:option value="9">开票通知</html:option> 

						<html:option value="10">回款确认</html:option>
						<html:option value="11">付款提醒</html:option>
						<html:option value="12">发货异常</html:option>
						<html:option value="13">入库汇总</html:option>
						<html:option value="14">出库汇总</html:option>
					</html:select>
				</td>
				<td class="td_01">主题</td>
				<td class="td_02">
					<input type="text" name="mailPara.subject" id="name8" style="width:240px;" value="${mailPara.subject}"/>
				</td>
			 </tr>
			 <tr>
				<td class="td_01" width="20%">发件人</td>
				<td class="td_02" width="30%">
					<input type="text" name="mailPara.userName" value="${mailPara.userName}" style="width:120px;" />
				</td>
				<td class="td_01" width="20%">收件人</td>
				<td class="td_02" width="30%">
					<input type="text" name="mailPara.name" value="${mailPara.name}" style="width:120px;" />
				</td> 
			 </tr>
			 <tr> 
				<td class="td_01">状态</td>
				<td class="td_02">
					<html:select property="mailPara.flag" value="${mailPara.flag}" style=" width:126px">
						<html:option value="">--请选择--</html:option> 
						<html:option value="1">已发送</html:option> 
						<html:option value="0">未发送</html:option> 
					</html:select>
				</td>
				<td class="td_01">&nbsp;</td>
				<td class="td_02">&nbsp;</td>
			 </tr>
             <tr>
                <td colspan="4" align="center" style="height:30px;">
					<a href="javascript:$('#mailList').submit();"><img src="${ctx}/images/btn_JianSuo.gif" /></a>
				</td>
              </tr>
            </table>
			</form>
          </div>
          <div class="mo_title" onclick="fMainListToggle(this)">
              <div  style="text-align:center"><img id="imgShang" src="${ctx}/images/shang_sj.png" /></div>
          </div>
        </div>
    </td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;&nbsp;</td>
    <td align="center">
    <br/>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table">
      <tr>
        <th nowrap="nowrap" width="120px">发件时间</th>
        <th nowrap="nowrap" width="120px">邮件类型</th>
        <th nowrap="nowrap">主题</th>
        <th nowrap="nowrap" width="120px">发件人</th>
        <th nowrap="nowrap" width="120px">收件人</th>
        <th nowrap="nowrap" width="120px">状态</th> 
        <th nowrap="nowrap" width="40px">操作</th>
      </tr>
	  <logic:iterate id="mail" name="mailList">
		<tr>
			<td height="18px">${mail.datetime}&nbsp;</td>
			<td>
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
			<td>${mail.subject}</td>
			<td>${mail.userName}</td>
			<td>${mail.name}</td>

			<td> 
				<c:if test="${mail.flag==0}">未发送</c:if>
				<c:if test="${mail.flag==1}">已发送</c:if>
			</td>
			<td><a href="${ctx}/mailView.do?mailPara.id=${mail.id}">查看</a></td>
		</tr>
	  </logic:iterate>
    </table><br />
	<table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table">
        	<tr>
           	  <td align="right" ><%@ include file="/jsp/common/newPage.jsp"%></td>
        </tr>
    </table>    </td>
    <td >&nbsp;</td>
  </tr>
</table>
 
</body>
</html>
