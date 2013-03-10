<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="x-ua-compatible" content="ie=7" />
		<title>库房收货地址添加</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/util.js"></script>
		<script type="text/javascript">
		var formId = 0;
			//控件名
			var checknNames = [ "depot.receiveName", "depot.supplierAddress", "depot.supplierLinkman","depot.supplierTel","depot.supplierMobile"];
			//提示语
			var descriptions = [ "接收单位名称", "发货地址",  "联系人","电话","手机" ];
			//是否非空验证,如果非空验证填写notnull,如果不需要非空验证传空参
			var checkNulls = [ "notnull", "notnull",  "notnull" ,"notnull", "null"];
			//判断类型验证，只填数字验证填"num",    只填字母验证“abc”,    email验证“email”，非零开头的数字和零 "0num",  邮箱验证 "zip" ,登录名验证  “loginname” ,电话 “phone”
			var checkTypes = [ "", "",  "","phone","num"];
			//判断长度验证 如果判断是否等于  参数前面加0, 否则正常  不需要长度验证则传空参
			var checkLengths = [ "80", "80",  "8" ,"20","12"];
		function check(){
		if(checkForm()==false){
			return ;
			}
	   document.forms[0].submit();
	   }
	   function init(){
			if ('${serverData}'=='true') {
		   		opener.document.location.reload();
		   		window.close();
			} else if('${serverData}'=='false') {
				alert('库房发货地址添加失败！');
			}
		};
	</script>
	</head>

	<body onload="javascript:init();">
		<br />
		<html:form action="/depot.do?method=addDepotAddress" method="post">
			<table width="96%" border="0" cellspacing="0" cellpadding="0"
				align="center" class="biao3">
				<tr>
					<td class="td_03" width="50%">
						<span style="color:#FF0000">*</span>&nbsp;货物接收单位名称
					</td>
					<td class="td_04">
						<input type="hidden" name="depot.id" id="depot.id"
							value="${depotId}" />
						<input type="text" name="depot.receiveName" id="depot.receiveName" maxlength="40"
							style="width:240px;" />
					</td>
				</tr>
				<tr>
					<td class="td_03">
						<span style="color:#FF0000">*</span>&nbsp;发货地址
					</td>
					<td class="td_04">
						<input type="text" name="depot.supplierAddress" maxlength="40"
							id="depot.supplierAddress" style="width:480px;" />
					</td>
				</tr>
				<tr>
					<td class="td_03">
						邮编
					</td>
					<td class="td_04">
						<input type="text" name="depot.supplierPostcode" maxlength="6"
							id="depot.supplierPostcode" style="width:120px;" />
					</td>
				</tr>
				<tr>
					<td class="td_03">
						<span style="color:#FF0000">*</span>&nbsp;联系人
					</td>
					<td class="td_04">
						<input type="text" name="depot.supplierLinkman" maxlength="4"
							id="depot.supplierLinkman" style="width:120px;" />
					</td>
				</tr>
				<tr>
					<td class="td_03">
						<span style="color:#FF0000">*</span>&nbsp;电话
					</td>
					<td class="td_04">
						<input type="text" name="depot.supplierTel" id="depot.supplierTel" maxlength="20"
							style="width:120px;" />
					</td>
				</tr>
				<tr>
					<td class="td_03">
						手机
					</td>
					<td class="td_04">
						<input type="text" name="depot.supplierMobile" maxlength="12"
							id="depot.supplierMobile" style="width:120px;" />
					</td>
				</tr>
			</table>
			<table width="400" border="0" cellspacing="0" cellpadding="0"
				align="center">
				<tr>
					<td align="center" height="32px" valign="bottom">
						<a href="#"><img src="${ctx}/images/btnAdd.gif" width="65" height="20"
							onclick="check();" /></a>
					</td>
				</tr>
			</table>
		</html:form>
	</body>
</html>
