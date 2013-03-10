<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>错误</title>
<style type="text/css">
<!--
html,body{
    margin:0px;
	height:100%
	font-family:"宋体", Basemic, "Arial Narrow";
	font-size:12px;
	background-color:#ffffff; 
}
.ffnn-corner{
	font-size:12px;
	background-color:#ffffff; 
	position:absolute;
    width:401px;
	height:180px;
	left:50%;
    top:50%; 
	margin-left:-200px;   
    margin-top:-90px;}
-->
</style>
 

</head>
<body>
	<div class="ffnn-corner">
		<div style="background-image:url(${ctx}/images/wrong_tou.jpg); background-repeat:no-repeat; width:401px; height:32px"></div>
		<div style="border:1px solid #cccccc;height:100px"><br/>
			<div style="float:left;padding-left:20px"><img src="${ctx}/images/wrong_1.jpg" width="37" height="37" /></div>
			<div style="float:left;padding:12px 0px 0px 20px;font-weight:bold;">系统错误，请联系管理员！</div>
		</div>
		<div style="background-color:#f9f9f9;border-bottom:1px solid #cccccc;border-left:1px solid #cccccc;border-right:1px solid #cccccc;line-height:30px;text-align:center">同方股份有限公司</div>
	</div>
</body>
</html>
