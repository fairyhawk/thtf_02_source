<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
	<head>
		<META http-equiv=Content-Type content="text/html; charset=gb2312">
		<LINK href="images/css.css" type=text/css rel=stylesheet>
	</head>

	<script language="javascript"> 
var XMLHttpReq; 
var currentSort; 
//创建XMLHttpRequest对象       
    function createXMLHttpRequest() { 
if(window.XMLHttpRequest) { //Mozilla 浏览器 
XMLHttpReq = new XMLHttpRequest(); 
} 
else if (window.ActiveXObject) { // IE浏览器 
try { 
XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP"); 
} catch (e) { 
try { 
XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP"); 
} catch (e) {} 
} 
} 
} 
//发送请求函数 
function sendRequest(url) { 
createXMLHttpRequest(); 
XMLHttpReq.open("GET", url, true); 
XMLHttpReq.onreadystatechange = processResponse;//指定响应函数 
XMLHttpReq.send(null);  // 发送请求 
} 
// 处理返回信息函数 
    function processResponse() { 
    if (XMLHttpReq.readyState == 4) { // 判断对象状态 
        if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息 
updateMenu(); 
    } else { //页面不正常 
      alert("您所请求的页面有异常。"); 
    } 
        } 
    } 
//更新菜单函数 
function updateMenu() { 
    var res=XMLHttpReq.responseXML.getElementsByTagName("res") 
    var subMenu = ""; 
    for(var i = 0; i < res.length; i++) { 
        subMenu = subMenu + "  " + res[i].firstChild.data + ""; 
    } 
currentSort.innerHTML = subMenu; 
} 
// 创建级联菜单函数 
function showSubMenu(obj) { 
currentSort =document.getElementById(obj); 
currentSort.parentNode.style.display = ""; 
alert(obj); 
sendRequest("menu.jsp?sort=" + obj); 
} 

</script>
	<table style="BORDER-COLLAPSE: collapse" borderColor=#111111
		cellSpacing=0 cellPadding=0 width=200 bgColor=#f5efe7 border=1>

		<TR>
			<TD align="middle" bgColor=#dbc2b0 height=19>
				<B>笔记本品牌</B>
			</TD>
		</TR>
		<tr>
			<td height="20">
				<a onClick="showSubMenu('IBM')">IBM</a>
			</td>
		</tr>
		<tr style="display:none">
			<td height="20" id="IBM">
			</td>
		</tr>
		<tr>
			<td height="20">
				<a onClick="showSubMenu('SONY')">SONY</a>
			</td>
		</tr>
		<tr style="display:none ">
			<td id="SONY" height="20">
			</td>
		</tr>
	</table>
</html>
