<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>销售总监修改</title>
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
				if($("#xxxlist")){
					$("#xxxlist tr:even").addClass("treven");
					$("#xxxlist tr").each(function(i){
						$(this).mouseover(function(){
							$(this).addClass("over");
						});
						$(this).mouseout(function(){
							$(this).removeClass("over");
						});
					});
				}
			});
		//-->
		</script>
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
<script type="text/javascript" src="${ctx}/js/util.js"></script>
<script type="text/javascript">
var myArray=new Array();
		var mark1;
		var mark;
	function che(a) {
		var all=document.getElementsByName("product");
		if(a.checked==true){
       			 for(i=0;i<all.length;i++){
            		all[i].checked=true;
            		myArray.push(all[i].value);
       		 }
   			 }else if(a.checked==false){
        		for(i=0;i<all.length;i++){
            		all[i].checked=false;
        		}
        		myArray=new Array();
   	 		 }
	};
	function removeBefore(a) {
			for(var i=0;i<myArray.length;i++){
				if(myArray[i]==a.value){
					mark=0;
					mark1=i;
				}
			}
			if(mark!=0){
				myArray.push(a.value);
			}
			if(mark==0){
				myArray.splice(mark1,1);
			}
			var mark1=0;
			var mark=0;
	};
	var formId = 0;
	//控件名
	var checknNames =  [ "id",			"mail", 	"name", 	"newpassword" ,	"tel",		"mobile",	"msn",		"qq"];
	//提示语
	var descriptions = [ "用户名",       "邮箱", 		"用户名", 	"密码",			"电话",		"手机",		"MSN",		"QQ"];
	//是否非空验证,如果非空验证填写notnull,如果只验证 开头和结尾的空格填写 notspace，如果不需要非空验证传空参
	var checkNulls =   [ "notnull",		"notnull",	"notnull", 	"notspace",		"notnull",	"notnull",	 "",		""];
	//判断类型验证，只填数字验证填"num",    只填字母验证“abc”,只能填字母和数字  “abcnum”，    email验证“email”，非零开头的数字和零 "0num",  邮箱验证 "zip" ,登录名验证  “loginname” ,电话 “phone”,
	var checkTypes =   [ "loginname", 	"email", 	"", 		"", 			"phone",	"num",		"email",	"num"];
	//判断长度验证 如果判断是否等于  参数前面加0, 否则正常  不需要长度验证则传空参
	var checkLengths = [ "20", 			"30", 		"8", 		"x6",			"20",		"12",		"30",		"12"];
	
	function clickSubmit() {
		if(checkForm()==false){
			return ;
		}
		document.getElementsByTagName('Form')[0].submit();
	}
</script>
</head>

<body>
<form action="searchUser.do">
<input type="hidden" name="products" value="">
<input type="hidden" name="method" value="changeUser">
<input type="hidden" name="changeid" value="2">
<input type="hidden" name="id" value="${view.id}">
<input type="hidden" name="roleid" value="${view.role_id}">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 基础信息管理 &gt;&gt; 用户信息管理 &gt;&gt; 用户修改</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
   <tr>
    <td >&nbsp;</td>
    <td align="center">
    <br />
    	<table width="100%" border="0" cellpadding="0" cellspacing="0"  class="biao3">
	       	<tr>
	           	<td nowrap="nowrap" class="td_01">登录名</td>
				<td class="td_02">
					${view.id}
				</td>
				<td class="td_01"><span style="color:#FF0000">*</span>&nbsp;Email</td>
				<td class="td_02">
					<input type="text" name="mail" style="width:240px;" value="${view.mail}" maxlength="30"/>
				</td>
	      	</tr>
			<tr>
			  	<td nowrap="nowrap" class="td_01"><span style="color:#FF0000">*</span>&nbsp;用户名</td>
				<td class="td_02">
					<input type="text" name="name" style="width:120px;" value="${view.name}" maxlength="4"/>
				</td>
				<td class="td_01">密码</td>
				<td class="td_02">
					<input type="text" name="newPassword" style="width:120px;" maxlength="40"/>&nbsp;<span style="color:#FF0000">*如填写将变更用户密码</span><input type="hidden" name="password" value="${view.password}" maxlength="40"/>
				</td>
			</tr>
            <tr>
            	<td nowrap="nowrap" class="td_01"><span style="color:#FF0000">*</span>&nbsp;电话</td>
                <td class="td_02"><input type="text" name="tel" style="width:120px;" value="${view.tel}" maxlength="20"/></td>
                <td nowrap="nowrap" class="td_01"><span style="color:#FF0000">*</span>&nbsp;手机</td>
                <td class="td_02"><input type="text" name="mobile" style="width:120px;" value="${view.mobile}" maxlength="12"/></td>
            </tr>
            <tr>
              <td class="td_01" width="20%">&nbsp;&nbsp;MSN</td>
              <td class="td_02" width="30%"><input type="text" name="msn" style="width:240px;" value="${view.msn}" maxlength="30"/></td>
              <td class="td_01" width="20%">&nbsp;&nbsp;QQ</td>
              <td class="td_02" width="30%"><input type="text" name="qq" style="width:120px;" value="${view.qq}" maxlength="12"/></td>
            </tr>
            <tr>
              <td class="td_01"><span style="color:#FF0000">*</span>&nbsp;使用状态</td>
              <td class="td_02">
	              <select name="enable" style=" width:126px">
	              	<c:if test="${view.enable==1}">
		              <option value="1" selected="selected">使用</option>
					</c:if>
					<c:if test="${view.enable!=1}">
		              <option value="1">使用</option>
					</c:if>
					<c:if test="${view.enable==0}">
		              <option value="0" selected="selected">停用</option>
		            </c:if>
		            <c:if test="${view.enable!=0}">
		              <option value="0">停用</option>
		            </c:if>
	              </select>
              </td>
              <td class="td_01"><span style="color:#FF0000">*</span>&nbsp;人员部门</td>
              <td class="td_02">
              <select name="user_dept_id" style="width:126px">
              				<logic:iterate id="li" name="userDept">
              					<c:if test="${view.user_dept_id==li.dept_id}">
	                				<option value="${li.dept_id}" selected="selected">${li.dept_name}</option>
	                			</c:if>
	                			<c:if test="${view.user_dept_id!=li.dept_id}">
	                				<option value="${li.dept_id}">${li.dept_name}</option>
	                			</c:if>
	                		</logic:iterate>
              </select></td>
            </tr>
            <tr>
              <td class="td_01" height="18">职务</td>
              <td class="td_02">
              ${view.role_name}
              </td>
              <td class="td_01">&nbsp;</td>
              <td class="td_02">&nbsp;</td>
          </tr>
        </table>
     </td>
    <td >&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td align="center">
    <br/><div class=" div_tiao">&nbsp;负责产品</div>
    	<table width="50%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="xxxlist">
        	<tr>
            	<th nowrap="nowrap" width="41">选择</th>
                <th nowrap="nowrap">产品分类名称</th>
            </tr>
            <logic:iterate id="product" name="product">
	            <tr>
	            	<td align="right">
	            		<c:if test="${product.mark==1}">
	            			&nbsp;<input name="product" type="checkbox" value="${product.id}" checked="checked">
	            		</c:if>
	            		<c:if test="${product.mark!=1}">
	            			&nbsp;<input name="product" type="checkbox" value="${product.id}">
	            		</c:if>
	            	</td>
	                <td align="right">&nbsp;${product.name}</td>
	            </tr>
            </logic:iterate>
        </table>
<br />
<table border="0" cellpadding="0" cellspacing="0" width="50%" id="ec_table">
  <tr>
    <td width="41px" align="left">&nbsp;&nbsp;<input type="checkbox" onclick="javascript:che(this);"></td>
    <td width="50px">全选</td>
    <td width="100px"></td>
    <td width="100px"></td>
    <td align="right">&nbsp;</td>
  </tr>
</table>
</td>
    <td >&nbsp;</td>
  </tr>
  <tr><td></td><td height="50px" align="center" valign="bottom"><a href="javascript:clickSubmit();"><img src="${ctx}/images/btnUpdate.gif" width="65" height="20" /></a>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <a href="javascript:window.location = '${ctx}/searchUser.do?method=searchUserBefore';"><img src="${ctx}/images/btnBack.gif" /></a></td>
    <td></td>
  </tr>
</table>
<br />
</form>
</body>
</html>
