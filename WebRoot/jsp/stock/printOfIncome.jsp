<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<head>
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
@media   print   {   
  		.buttonNoPrint {display:none;}   
  		}		
		</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script>
	$(document).ready(function(){
		document.title=$("#title",window.opener.document).html()+"打印";
		$("#meno").html($("#printOfDiv",window.opener.document).html());
		$(":[id=showPrint]").css("display","");
		$("#showPrintNone").css("display","none");
	});
	function print(){
		wb.execwb(6,6);
	}
		function printPreview(){    
			// 打印页面预览    
			wb.execwb(7,1);       
		}    
		function printSetup(){    
			// 打印页面设置    
			wb.execwb(8,1);    
		}
</script>
<title id="title"></title>
	
</head>

<body>
<br/>
<div id="meno"></div>
<br/><br/>
<div style="text-align:center">
			<a href="#" onclick="javascript:print();">
					<img src="${ctx}/images/btnPrint.gif" width="65" height="20" class="buttonNoPrint" /></a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="#" onclick="javascript:printSetup();"><img src="${ctx}/images/btnPrintSZ.gif" width="88" height="20" class="buttonNoPrint" /></a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="#" onclick="javascript:printPreview();"><img src="${ctx}/images/btnPrintYL.gif" class="buttonNoPrint" /></a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</div>
</body>
<OBJECT id="wb" height=0 width=0 classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 name="wb"></OBJECT>
</html>