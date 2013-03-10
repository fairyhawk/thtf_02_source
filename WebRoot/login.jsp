<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%> 
<html>
<head>
<title>用户登录</title>
<style type="text/css">
<!--
html,body{
     margin:0px;
	 height:100%;
	 font-size:12px;
	 font-family:"宋体";
}
#main{
     position:absolute;
     width:546px;
	 height:245px;
	 left:50%;
     top:50%; 
	 margin-left:-273px;   
     margin-top:-122px;
	 
}
.nav {
	border:1px solid #83ace7;
}
.userText {
	font-size: 12px;
	font-weight: 600;
	color: #2A7FFF;
}
.titleStyle {
	font-family: "楷体_GB2312", "宋体", Arial, Helvetica, sans-serif;
	font-size: 20px;
	font-style: normal;
	font-weight: bold;
	color: #FFFFFF;
	height: 40px;
	background-repeat: repeat-x;
	background-color: #007FFF;
}
input.data {
   background-color: #FFFFFF;
   height: 21px;
   width: 180px;
   border: 1px solid #83ace7;
   font-family: "Arial";
   font-size: 12px;
   padding-top: 2px;
   padding-left: 3px;
}
.button {
	font-size: 12px;
	font-weight: bold;
	padding-right: 0px;
	padding-bottom: 3px;
	padding-left: 0px;
	margin: 1px;
	background-image:url(images/DL.gif);
	width:65px;
	height:24px;
	color: #ffffff;
	border:0px;
}
img{border:0px;}
-->
</style>
<script type="text/javascript">
	function tips(){
		var tips = document.getElementById("tips").value
		if(tips == null||tips == ""){
			
		}else{
			alert(tips);
		}
	}
	function changeimg(){
		//var codelink = document.getElementById("codelink");
		var codeimg = document.getElementById("codeimg");
		codeimg.src = "${ctx}/images/confirmimg?date=" + new Date();
	}
	
	function loginCheck(){
		var username = document.getElementById("username").value;
		var password = document.getElementById("password").value;
		var code = document.getElementById("code").value;
		if(username == null||username == ""){
			alert("登录名不能为空！");
			return false;
		}
		if(password == null||password == ""){
			alert("密码不能为空！");
			return false;
		}else{
			if(password.length<6){
				alert("密码长度至少6位！");
				return false;
			}
		}
		if(code == null||code == ""){
			alert("验证码不能为空！");
			return false;
		}
		
	}

</script>
</head>

<body onload="tips()">
<form action="login.do?method=login" method="post" name="loginForm" target="_top">
    <div id="main">
    <table cellpadding="0" cellspacing="0" class="nav" width="100%" height="100%">
    	<tr>
        	<td height="40px" align="center" class="titleStyle">同方物联网应用产业本部</td>
        </tr>
        <tr>
        	<td class="loginBG" style=" background-image:url(images/loginBackground.jpg); background-repeat:no-repeat;" height="180">
            	<table cellpadding="0" cellspacing="0" width="250px"  align="center">
                    <tr>
                        <td height="38px" class="userText">登录名：</td><td><input type="text"  id="username" name="username" class="data" style="width:180px" /></td>
                    </tr>
                     <tr>
                        <td height="38px" class="userText">密&nbsp;&nbsp;码：</td><td><input type="password" id="password" name="password" class="data" style="width:180px" /></td>
                    </tr>
					 <tr>
                        <td height="38px" class="userText">验证码：</td><td><input type="text" id="code" name="code" class="data" style="width:100px" />&nbsp;&nbsp;<a id="codelink" href="#" onclick="changeimg();" title="看不清?点击图片换一张!"><img id="codeimg" src="${ctx}/images/confirmimg" align="absmiddle"/></a></td>
                    </tr>
                     <tr>
                        <td height="42px" colspan="2" align="center" valign="bottom">
                        	<input type="submit" name="login" value="登&nbsp;&nbsp;录" class="button"  onclick="return loginCheck();">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                          <input type="reset" name="cancel" value="清&nbsp;&nbsp;空" class="button">&nbsp;
                        </td>
                    </tr>
                </table>
          </td>
        </tr>
        <tr>
        	<td height="22px" align="center" bgcolor="#83ace7" style="font-size:12px">版权所有&nbsp;&copy;&nbsp;2009-2010&nbsp;同方股份有限公司
		      &nbsp;&nbsp;建议在IE8&nbsp;1024*768分辨率下使用</td>
      </tr>
	</table>
  </div>
</form>
<input type="hidden" id="tips" value="${LOGIN_TIPS}">
</body>
</html>
