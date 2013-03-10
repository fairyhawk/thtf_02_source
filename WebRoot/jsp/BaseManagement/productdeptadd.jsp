<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>产品部门添加</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
		<script type="text/javascript" src="${ctx}/js/util.js"></script>
		<script type="text/javascript">
		
		var formId = 0;
		//控件名
		var checknNames = [ "productdept.no", "productdept.name", "productdept.account","productdept.fax"];
		//提示语
		var descriptions = [ "部门编号", "部门名称", "部门账号", "部门传真号"];
		//是否非空验证,如果非空验证填写notnull,如果不需要非空验证传空参
		var checkNulls = [ "notnull", "notnull", "notnull", "notnull" ,"notnull"];
		//判断类型验证，只填数字验证填"num",    只填字母验证“abc”,    email验证“email”，非零开头的数字和零 "0num",  邮箱验证 "zip" ,登录名验证  “loginname” ,电话 “phone”
		var checkTypes = [ "", "", "", "phone"];
		//判断长度验证 如果判断是否等于  参数前面加0, 否则正常  不需要长度验证则传空参
		var checkLengths = [ "4", "16", "20", "20"];
		function sub(){
			if(checkForm()==false){
			return ;
			}
				 document.forms[0].submit();
			
		};
	
		function checks(){
			var err = document.getElementById("err").value;
			if(err==null||err==""){
				
			} 
			else if(err!= null){
				alert("添加失败！");
				}
		}
		</script>
	</head>
	<body onload="checks();">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td class="ye_header_left">&nbsp;
					
				</td>
				<td class="ye_header_center">
					<img src="${ctx}/images/main_jt.jpg" />
					&nbsp;当前位置： 基础信息管理 &gt;&gt; 产品部门信息管理 &gt;&gt; 产品部门添加
				</td>
				<td class="ye_header_right">&nbsp;
					
				</td>
			</tr>
			<tr>
				<td>&nbsp;
					
				</td>
				<td align="center">
					<br />
					<html:form action="/productdeptmanagement" method="post">
						<input type="hidden" name="method" value="saveProductDept" />
						<table width="100%" border="0" cellpadding="0" cellspacing="0"  class="biao3">
							<tr>
								<td nowrap="nowrap" class="td_01"><span style="color:#FF0000">*</span>&nbsp;产品部门编号</td>
								<td class="td_02">
									<input type="text" name="productdept.no" id="id" maxlength="4" style="width:120px;"/>
									<input type="hidden" name="err" id="err" value="${err}"/>
								</td>
								 <td class="td_01"><span style="color:#FF0000">*</span>&nbsp;产品部门名称</td>
								<td class="td_02">
									<input type="text" name="productdept.name" id="name" maxlength="8" style="width:120px;"/>
								</td>
							</tr>
							<tr>
								<td nowrap="nowrap" class="td_01"><span style="color:#FF0000">*</span>&nbsp;帐号</td>
								<td class="td_02">
									<input type="text" name="productdept.account" id="account" maxlength="20" style="width:120px;"/>
								</td>
								<td class="td_01"><span style="color:#FF0000">*</span>&nbsp;传真</td>
								<td class="td_02">
									<input type="text" name="productdept.fax" id="fax" maxlength="20" style="width:120px;"/>
								</td>
							</tr>
						</table>
						<table border="0" cellpadding="0" cellspacing="0" width="400px"
							id="ec_table">
							<tr>
								<td align="center" height="50px" valign="bottom">
									<a href="javascript:sub();"><img src="${ctx}/images/btnAdd.gif" /></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<a href="javascript:window.location='${ctx}/productdeptmanagement.do?method=productDeptAll';"><img
											src="${ctx}/images/btnBack.gif" width="65" height="20" />
									</a>
								</td>
							</tr>
						</table>
					</html:form>
				</td>
				<td>&nbsp;
					
				</td>
			</tr>
		</table>
	</body>
</html>
