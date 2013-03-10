<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>物流管理员修改</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/util.js"></script>
<script type="text/javascript">
<!--
function MM_jumpMenu(targ,selObj,restore){ //v3.0
  eval(targ+".location='"+selObj.options[selObj.selectedIndex].value+"'");
  if (restore) selObj.selectedIndex=0;
}
//-->

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
  <input type="hidden" name="method" value="changeUser">
  <input type="hidden" name="changeid" value="5">
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
    <td><br />
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" width="20%">登录名</td>
          <td class="td_02" width="30%">${view.id}</td>
          <td class="td_01" width="20%"><span style="color:#FF0000">*</span>&nbsp;Email</td>
          <td class="td_02" width="30%"><input type="text" name="mail" value="${view.mail}" style="width:240px;" maxlength="30"/></td>
        </tr>
        <tr>
          <td class="td_01"><span style="color:#FF0000">*</span>&nbsp;用户名</td>
          <td class="td_02"><input type="text" name="name" value="${view.name}" style="width:120px;" maxlength="4"/></td>
          <td class="td_01">密码</td>
          <td class="td_02"><input type="text" name="newPassword" style="width:120px;" maxlength="40"/>
            &nbsp;<span style="color:#FF0000">*如填写将变更用户密码</span>
            <input type="hidden" name="password" value="${view.password}"/>
          </td>
        </tr>
        <tr>
          <td class="td_01"><span style="color:#FF0000">*</span>&nbsp;电话</td>
          <td class="td_02"><input type="text" name="tel" value="${view.tel}" style="width:120px;" maxlength="20"/></td>
          <td class="td_01"><span style="color:#FF0000">*</span>&nbsp;手机</td>
          <td class="td_02"><input type="text" name="mobile" value="${view.mobile}" style="width:120px;" maxlength="12"/></td>
        </tr>
        <tr>
          <td class="td_01">MSN</td>
          <td class="td_02"><input type="text" name="msn" value="${view.msn}" style="width:240px;" maxlength="30"/></td>
          <td class="td_01">QQ</td>
          <td class="td_02"><input type="text" name="qq" value="${view.qq}" style="width:120px;" maxlength="12"/></td>
        </tr>
        <tr>
          <td class="td_01"><span style="color:#FF0000">*</span>&nbsp;使用状态</td>
          <td class="td_02"><select name="enable" style=" width:126px">
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
          <td class="td_01" height="18px">&nbsp;</td>
          <td class="td_02">&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18">职务</td>
          <td class="td_02"> ${view.role_name} </td>
          <td class="td_01" height="18px">&nbsp;</td>
          <td class="td_02">&nbsp;</td>
        </tr>
        </tr>
        
        <tr>
          <td class="td_01">所属物流公司</td>
          <td class="td_02">${view.logistics_name}</td>
          <td class="td_01" height="18px">&nbsp;</td>
          <td class="td_02">&nbsp;</td>
        </tr>
      </table>
      <table border="0" cellpadding="0" cellspacing="0" width="300px" id="ec_table" align="center">
        <tr>
          <td height="50px" valign="bottom" align="center"><a href="javascript:clickSubmit();"><img src="${ctx}/images/btnUpdate.gif" width="65" height="20" /></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="javascript:window.location = '${ctx}/searchUser.do?method=searchUserBefore';"><img src="${ctx}/images/btnBack.gif" width="65" height="20" /></a></td>
        </tr>
      </table></td>
    <td >&nbsp;</td>
    </tr>
  </table>
</form>
</body>
</html>
