<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="x-ua-compatible" content="ie=7" />
		<title>客户发货地址添加</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/util.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript">
			var token = true; 
			var formId = 0;
			//控件名
			var checknNames = [ "supplier.receiveName", "supplier.supplierAddress", "supplier.supplierLinkman","supplier.supplierTel","supplier.supplierMobile"];
			//提示语
			var descriptions = [ "接收单位名称", "发货地址",  "联系人","电话","手机"];
			//是否非空验证,如果非空验证填写notnull,如果不需要非空验证传空参
			var checkNulls = [ "notnull", "notnull", "notnull" ,"notnull", "null"];
			//判断类型验证，只填数字验证填"num",    只填字母验证“abc”,    email验证“email”，非零开头的数字和零 "0num",  邮箱验证 "zip" ,登录名验证  “loginname” ,电话 “phone”
			var checkTypes = [ "", "",  "","phone","num"];
			//判断长度验证 如果判断是否等于  参数前面加0, 否则正常  不需要长度验证则传空参
			var checkLengths = [ "80", "80",  "8" ,"20","12"];
		function check(){
		if(checkForm()==false){
			return ;
			}
		if(!token)return;
		token = false;
		$.ajax({
			url:"${ctx}/customer.do?method=addSupplierAddress",
			type:"post",
			data:"id="+$("#id").val()+"&receiveName="+$("#receiveName").val()+"&supplierAddress="+$("#supplierAddress").val()+"&supplierPostcode="+$("#supplierPostcode").val()+"&supplierLinkman="+$("#supplierLinkman").val()+"&supplierTel="+$("#supplierTel").val()+"&supplierMobile="+$("#supplierMobile").val(),
			dataType:"text",
			async:false,
			success:function(resultVlaue){
				if(resultVlaue=="false"){alert("添加失败");}
				else{
					window.close();
					opener.document.location.reload();
				}
				token = true;
			}
		});
	   
	   
	}
	</script>
	</head>

	<body>
		<br />
		<html:form action="/customer.do?method=addSupplierAddress" method="post">
			<table width="96%" border="0" cellspacing="0" cellpadding="0"
				align="center" class="biao3">
				<tr>
					<td class="td_03" width="50%">
						<span style="color:#FF0000">*</span>&nbsp;货物接收单位名称
					</td>
					<td class="td_04">
						<input type="hidden" name="supplier.id" id="id"
							value="${supplierId}" />
						<input type="text" name="supplier.receiveName"
							id="receiveName" style="width:240px;" maxlength="40"/>
					</td>
				</tr>
				<tr>
					<td class="td_03">
						<span style="color:#FF0000">*</span>&nbsp;发货地址
					</td>
					<td class="td_04">
						<input type="text" name="supplier.supplierAddress"
							id="supplierAddress" style="width:480px;" maxlength="40"/>
					</td>
				</tr>
				<tr>
					<td class="td_03">
						邮编
					</td>
					<td class="td_04">
						<input type="text" name="supplier.supplierPostcode"
							id="supplierPostcode" style="width:120px;" maxlength="6"/>
					</td>
				</tr>
				<tr>
					<td class="td_03">
						<span style="color:#FF0000">*</span>&nbsp;联系人
					</td>
					<td class="td_04">
						<input type="text" name="supplier.supplierLinkman"
							id="supplierLinkman" style="width:120px;" maxlength="4"/>
					</td>
				</tr>
				<tr>
					<td class="td_03">
						<span style="color:#FF0000">*</span>&nbsp;电话
					</td>
					<td class="td_04">
						<input type="text" name="supplier.supplierTel"
							id="supplierTel" style="width:120px;" maxlength="20"/>
					</td>
				</tr>
				<tr>
					<td class="td_03">
						手机
					</td>
					<td class="td_04">
						<input type="text" name="supplier.supplierMobile"
							id="supplierMobile" style="width:120px;" maxlength="12"/>
					</td>
				</tr>
			</table>
			<br />
			<table width="400" border="0" cellspacing="0" cellpadding="0"
				align="center">
				<tr>
					<td align="center" height="20px">
						<a href="#"><img src="${ctx}/images/btnAdd.gif" width="65" height="20"
							onclick="check();" /></a>
					</td>
				</tr>
			</table>
		</html:form>
	</body>
</html>
