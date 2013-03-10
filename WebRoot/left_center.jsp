<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%> 
<html>
<head>
<title>无标题文档</title>
<style type="text/css">
<!--
	html,body{
     margin:0px;
	 height:100%;
}
.container{
     width:100%;
	 height:100%;
	 margin:0px;
	 background-position:center;
}
.main{
     position:absolute;
     width:6px;
	 height:60px;
     top:50%;    
     margin-top:-30px;     
     background-image:url(images/fclose.jpg);
	 
}
.left_center_foot{background-image: url(images/main_bottom1.jpg);background-repeat: repeat-x;background-attachment:fixed;background-position:bottom; width:8px; height:28px;}
-->
</style>
<script language=javascript type=text/javascript>
function changeLeft(){    frmElem=window.parent.document.getElementById("fmain");
if (frmElem.cols != "0, 6, *") {	frmElem.cols = "0, 6, *";
Elem=document.getElementById('menuSwitch')
Elem.innerHTML="<img src=\"images/fopen.jpg\">";
} else {	frmElem.cols = "175, 6, *";
Elem=document.getElementById('menuSwitch')
Elem.innerHTML="<img src=\"images/fclose.jpg\">";
}}
</script>
</head>
<body id="midbar" class="left_center_foot" style="background-color:#e6f3fc;">
<div class="container">
    <div id="menuSwitch" onClick="changeLeft()" class="main">
    
  </div>
</div>
</body>
</html>