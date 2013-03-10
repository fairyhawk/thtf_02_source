<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script> 
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="x-ua-compatible" content="ie=7" />
		<title>客户联系人添加</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/util.js"></script>
		<script type="text/javascript">
		var formId = 0;
			//控件名
			var checknNames = [ "supplier.linkman", "supplier.role", "supplier.tel","supplier.fax","supplier.mobile","supplier.mail","supplier.qq","supplier.msn"];
			//提示语
			var descriptions = [ "联系人姓名", "职务", "电话", "传真","手机","email","qq","msn"];
			//是否非空验证,如果非空验证填写notnull,如果不需要非空验证传空参
			var checkNulls = [ "notnull", "notnull", "notnull", "null" ,"null","notnull","null","null"];
			//判断类型验证，只填数字验证填"num",    只填字母验证“abc”,    email验证“email”，非零开头的数字和零 "0num",  邮箱验证 "zip" ,登录名验证  “loginname” ,电话 “phone”
			var checkTypes = [ "", "", "phone", "phone","num","email","num","email"];
			//判断长度验证 如果判断是否等于  参数前面加0, 否则正常  不需要长度验证则传空参
			var checkLengths = [ "8", "20", "20", "20" ,"12","50","12","30"];
		
	function check(){
		if(checkForm()==false){
			return ;
		} 
	 
		var data = "method=addSupplierLinkman&id="+$("#id").val()+"&linkman="+$("#linkman").val()+"&role="+$("#role").val()+"&tel="+$("#tel").val()+"&fax="+$("#fax").val()+"&mobile="+$("#mobile").val()+"&mail="+$("#mail").val()+"&qq="+$("#qq").val()+"&msn="+$("#msn").val();

		$.ajax({
		  type: "POST",
		  async: false,
		  url: "${ctx}/customer.do",
		  data: data +"&date="+Date(),
			 success: function(msg){ 
				 if(msg==0){
					  alert("添加失败！");
					  return false;
				 }else{
					window.close();
					opener.document.location.reload();
				 } 
			}
		}); 
	    //document.forms[0].submit();
	    
	}
</script>
	</head>
	<body>
		<br />
		<html:form action="/customer.do?method=addSupplierLinkman"
			method="post">
			<table width="96%" border="0" cellspacing="0" cellpadding="0"
				align="center" class="biao3">
				<tr>
					<td class="td_03" style="height:18px">
						客户名称
					</td>
					<td colspan="3" class="td_04">
						${supplierName}&nbsp;
						<input type="hidden" name="supplier.name" id="supplier.name"
						  value="${supplierName}" maxlength="8"/>
						<input type="hidden" name="supplier.id" id="id"
							value="${supplierId }" />
					</td>
				</tr>
				<tr>
					<td class="td_01">
						<span style="color:#FF0000">*</span>&nbsp;联系人姓名
					</td>
					<td class="td_02">
						<input type="text" name="supplier.linkman" id="linkman"
							style="width:120px;"  maxlength="4"/>
					</td>
					<td class="td_01">
						<span style="color:#FF0000">*</span>&nbsp;职务
					</td>
					<td class="td_02">
						<input type="text" name="supplier.role" id="role"
							style="width:120px;" maxlength="10"/>
					</td>
				</tr>
				<tr>
					<td class="td_01">
						<span style="color:#FF0000">*</span>&nbsp;电话
					</td>
					<td class="td_02">
						<input type="text" name="supplier.tel" id="tel"
							style="width:120px;" maxlength="20"/>
					</td>
					<td class="td_01">
						传真
					</td>
					<td class="td_02">
						<input type="text" name="supplier.fax" id="fax"
							style="width:120px;" maxlength="20"/>&nbsp;
					</td>
				</tr>
				<tr>
					<td class="td_01">
						手机
					</td>
					<td class="td_02">
						<input type="text" name="supplier.mobile" id="mobile"
							style="width:120px;" maxlength="12"/>
					</td>
					<td class="td_01">
						<span style="color:#FF0000">*</span>&nbsp;Email
					</td>
					<td class="td_02">
						<input type="text" name="supplier.mail" id="mail"
							style="width:240px;" maxlength="50"/>
					</td>
				</tr>
				<tr>
					<td class="td_01">
						QQ
					</td>
					<td class="td_02">
						<input type="text" name="supplier.qq" id="qq"
							style="width:120px;" maxlength="12"/>
					</td>
					<td class="td_01">
						MSN
					</td>
					<td class="td_02">
						<input type="text" name="supplier.msn" id="msn"
							style="width:240px;" maxlength="30"/>
					</td>
				</tr>
			</table>
			<table width="400" border="0" cellspacing="0" cellpadding="0"
				align="center">
				<tr>
					<td align="center" height="50px" valign="bottom">
						<a href="#"><img src="${ctx}/images/btnAdd.gif" width="65" height="20"
							onclick="check();" /></a>
					</td>
				</tr>
			</table>
		</html:form>
	</body>
</html>
