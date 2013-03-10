<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>区域查看</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
<script type="text/javascript" src="${ctx}/js/util.js"></script>
<style type="text/css"> 
	.treven {
		background-color: #f3fbff;
	}
	.over {
		background-color: #ECECEC;
	}
</style>
<script language="JavaScript"> 
	$(document).ready(function(){

		if($("#areaListTable")){
			$("#areaListTable tr:even").addClass("treven");
			$("#areaListTable tr").each(function(i){
				$(this).mouseover(function(){
					$(this).addClass("over");
				});
				$(this).mouseout(function(){
					$(this).removeClass("over");
				});
			});
		} 

	}); 
 
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
<input type="hidden" name="changeid" value="39">
<input type="hidden" name="id" value="${view.id}">
<input type="hidden" name="roleid" value="${view.role_id}">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center" style="font-size:12px;"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 基础信息管理 &gt;&gt; 用户信息管理 &gt;&gt; 用户查看</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
   <tr>
    <td >&nbsp;</td>
    <td align="center">
    <br />
    	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        	<tr>
            	<td class="td_01" width="20%" height="18px">登录名</td>
                <td class="td_02" width="30%">${view.id}&nbsp;</td>
                <td class="td_01" width="20%">Email</td>
                <td class="td_02" width="30%">${view.mail}&nbsp;</td>
       	  </tr>
            <tr>
            	<td class="td_01" height="18px">用户名</td>
                <td class="td_02">${view.name}&nbsp;</td>
              <td class="td_01">密码</td>
                <td class="td_02">&nbsp;</td>
          </tr>
            <tr>
            	<td class="td_01" height="18px">电话</td>
                <td class="td_02">${view.tel}&nbsp;</td>
              <td class="td_01">手机</td>
                <td class="td_02">${view.mobile}&nbsp;</td>
          </tr>
            <tr>
            	<td class="td_01" height="18px">MSN</td>
                <td class="td_02">${view.msn}&nbsp;</td>
              <td class="td_01">QQ</td>
                <td class="td_02">${view.qq}&nbsp;</td>
          </tr>
            <tr>
           	  <td class="td_01" height="18px">使用状态</td>
                <td class="td_02"><c:if test="${view.enable==1}">使用</c:if><c:if test="${view.enable==0}">停用</c:if></td>
              <td class="td_01">人员部门</td>
                <td class="td_02">${view.dept_name}&nbsp;</td>
          </tr>
            <tr>
           	  <td class="td_01" height="18px">职务</td>
                <td class="td_02">${view.role_name}&nbsp;</td>
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
    <br/><div class=" div_tiao" style="font-size:12px;">&nbsp;负责区域</div>
    	<table width="50%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="areaListTable">
        	<tr> 
				<th nowrap="nowrap">区域名称</th> 
            </tr>
            <logic:iterate id="area" name="userAreaMapp"> 
				<c:if test="${area.en!=-1}">
				 <tr>
					<td height="18px" align="right">&nbsp; 
						${area.area_name}
					</td> 
				</tr>
				</c:if> 
            </logic:iterate>
        </table>
<br />
<table border="0" cellpadding="0" cellspacing="0" width="50%" id="ec_table">
  <tr>
    <td width="41px" align="left">&nbsp;&nbsp;</td>
    <td width="50px" style="font-size:12px;">&nbsp;</td>
    <td width="100px"></td>
    <td width="100px"></td>
    <td align="right">&nbsp;</td>
  </tr>
</table></td>
    <td >&nbsp;</td>
  </tr>
  <tr><td></td><td height="50px" align="center" valign="bottom">
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <a href="javascript:window.location = '${ctx}/searchUser.do?method=searchUserBefore';"><img src="${ctx}/images/btnBack.gif" /></a></td>
    <td></td>
  </tr>
</table>
<br />
</form>
</body>
</html>
