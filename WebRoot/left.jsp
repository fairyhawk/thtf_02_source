<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="${ctx}/css/themes/base/jquery.ui.all.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
<!--
	body{ margin:0px; padding:0px; font-size:12px; font-family:"宋体"; background-color:#FFFFFF;}
	.left_bgcol{background-image:url(images/left/left_bg.jpg); background-repeat:repeat-x;}
	.left_foot{background-image: url(images/left/left_bottom.jpg);background-repeat: no-repeat;background-attachment:fixed;background-position: left bottom; width:175px; height:50px;}
	.left_center{ border-left:1px solid #b6c0c9; border-right:1px solid #b6c0c9; }
	img{border:0;}
	a:link {font-size:12px; text-decoration:none; color:#42687f; }
	a:visited{font-size:12px; text-decoration:none; color:#03515d;}
	a:hover{font-size:12px; text-decoration:underline;}
	.navbar{margin:0px;padding:0px;}
	.navbar ul{margin:0px;padding:0px;list-style:none;}
	.navbar li{line-height:22px;}
-->
</style>
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/ui/jquery-ui-1.8.custom.min.js"></script>
<script language="JavaScript">
<!--
    $(document).ready(function() {
        $("#accordion > div").addClass("navbar");
        $("#accordion").accordion({
            active:'-1',
            autoHeight: false,
            collapsible: true
        });
    });

//-->
</script>
</head>

<body class="left_foot">
<table width="175px" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF">
  <tr>
  	<td width="6px" rowspan="2" bgcolor="#e7f4fc"></td>
    <td style="height:29px;" bgcolor="#e1e6e8"><img src="images/left/left_header.jpg" width="169" height="29"></td>
  </tr>
  <tr>
    <td class="" >
	    <div class="" style="margin: 0px">
            <div id="accordion">
                ${tree}
            </div>
	    </div>
    </td>
  </tr>
</table>
</body>
</html>

