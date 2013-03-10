<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>修改个人信息</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../../js/tad_bs2.js"></script>
		<script type="text/javascript" src="${ctx}/js/util.js"></script>
		<script type="text/javascript">
			var formId = 0;
			//控件名
			var checknNames = [ "userInfo.password", "userInfo.tel", "newpassword","userInfo.mail","comformpassword","userInfo.mobile","userInfo.msn","userInfo.qq"];
			//提示语
			var descriptions = [ "原密码", "电话", "新密码", "邮箱","新密码确认","手机","msn","qq"];
			//是否非空验证,如果非空验证填写notnull,如果不需要非空验证传空参
			var checkNulls = [ "notspace", "notnull", "notspace", "notnull" ,"notspace", "notnull","null","null"];
			//判断类型验证，只填数字验证填"num",    只填字母验证“abc”,    email验证“email”，非零开头的数字和零 "0num",  邮箱验证 "zip" ,登录名验证  “loginname” ,电话 “phone”
			var checkTypes = [ "", "phone", "", "email","","num","email","num"];
			//判断长度验证 如果判断是否等于  参数前面加0, 否则正常  不需要长度验证则传空参
			var checkLengths = [ "x6", "", "x6", "30","x6","12","30","12"];
			
		function check(){
			if(checkForm()==false){
				return ;
				}
			var newp = document.getElementById("newp").value;
			var again = document.getElementById("again").value;
			var pass = document.getElementById("pass").value;
			if(pass == "" && newp != ""){
				alert("原密码为空，不可以更改新密码！");
			}
			else if(newp != again){
				alert("两次密码不一致！");
			}else{
				document.forms[0].submit();
				//alert("修改成功！");
				}
		};
		function checks(){
			var err = document.getElementById("err").value;
			if(err==null||err==""){
			} 
			else if(err!= null){
				alert("密码错误！");
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
					&nbsp;当前位置： 基础信息管理 &gt;&gt; 修改个人信息
				</td>
				<td class="ye_header_right">&nbsp;
					
				</td>
			</tr>
			<tr>
				<td>&nbsp;
					
				</td>
				<td align="center">
					<br />
					<html:form action="/user" method="post">
						<input type="hidden" name="method" value="updateUser" />
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="biao3">
							<tr>
								<td nowrap="nowrap" class="td_01" width="20%">
									登录名
								</td>
								<td class="td_02" width="30%" height="18px">
									<label>
										${uinfo.id}
									</label>
								</td>
								<td class="td_01" width="20%">
									用户名
								</td>
								<td class="td_02" width="30%">
									${uinfo.name}
								</td>
							</tr>
							<tr>
								<td nowrap="nowrap" class="td_01" width="20%">
									原密码
								</td>
								<td class="td_02" width="30%">
									<input type="password" name="userInfo.password" style="width:120px;" id = "pass"/>
								</td>
								<td class="td_01">
									<span style="color:#FF0000">*</span>&nbsp;电话
								</td>
								<td class="td_02" width="30%">
									<input type="text" name="userInfo.tel" style="width:120px;"
										value="${uinfo.tel}"  id = "tel" maxlength="20"/>
										<input type="hidden" name="err" 
										value="${err}"  id="err"/>
								</td>
							</tr>
							<tr>
								<td nowrap="nowrap" class="td_01" width="20%">
									新密码
								</td>
								<td class="td_02" width="30%">
									<input type="password" name="newpassword" style="width:120px;" id = "newp"/>
								</td>
								<td nowrap="nowrap" class="td_01" width="20%">
									<span style="color:#FF0000">*</span>&nbsp;Email
								</td>
								<td class="td_02" width="30%">
									<input type="text" name="userInfo.mail" style="width:240px;"
										value="${uinfo.mail}" id = "mail" maxlength="30"/>
								</td>
							</tr>
							<tr>
								<td class="td_01" width="20%">
									新密码确认
								</td>
								<td class="td_02" width="30%">
									<input type="password" name="comformpassword"
										style="width:120px;" id = "again"/>
								</td>
								<td class="td_01" width="20%">
									<span style="color:#FF0000">*</span>&nbsp;手机
								</td>
								<td class="td_02" width="30%">
									<input type="text" name="userInfo.mobile" style="width:120px;"
										value="${uinfo.mobile}" id = "mobile" maxlength="12"/>
								</td>
							</tr>
							<tr>
								<td class="td_01" width="20%">
									MSN
								</td>
								<td class="td_02" width="30%">
									<input type="text" name="userInfo.msn" style="width:240px;"
										value="${uinfo.msn}" maxlength="30"/>
								</td>
								<td class="td_01" width="20%">
									QQ
								</td>
								<td class="td_02" width="30%">
									<input type="text" name="userInfo.qq" style="width:120px;"
										value="${uinfo.qq}" maxlength="12"/>
								</td>
							</tr>
						</table>
						<table border="0" cellpadding="0" cellspacing="0" width="300px"
							id="ec_table">
							<tr>
								<td height="50px" valign="bottom" align="center">
									<a href="javascript:check();"> <img src="${ctx}/images/btnUpdate.gif"
											width="65" height="20" />
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
