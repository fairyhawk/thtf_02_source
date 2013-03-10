<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%> 
<html>
<head>
<title>无标题文档</title>
<link href="${ctx}/css/themes/base/jquery.ui.all.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/ui/jquery-ui-1.8.custom.min.js"></script>
<style type="text/css">
<!--
	body{ margin:0px; padding:0px; background-color:#FFFFFF; font-size:12px; font-family:"宋体"; }
-->
</style>
<link href="css/css.css" rel="stylesheet" type="text/css" />
<script language="JavaScript">
<!--
    $(document).ready(function() {
        $("#dialog").dialog({ 
            resizable: false,
            position: [20,50],
            width:220
        });
        $(".ui-dialog-titlebar-close").remove();
        updateToList();
    });

    function updateToList(){
        var time = new Date();
        $.getJSON("${ctx}/todolist.do?"+time, function(json){
            if( json != null ){
                $.each(json, function(i,item){
                    $('#lastLogin').before('<tr><td class="dbsx_td"><a href="'+item.url+'">'+item.work_name+'</a></td><td align="right" class="dbsx_td">'+item.count+'件</td></tr>');
                });
                $('#lastLogin').before('<tr><td colspan="2" height="30px">&nbsp;</td></tr>');
            }
        });
    }

//-->
</script>
</head>
<body>
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="images/main_jt.jpg" />&nbsp; 当前位置： 工作流 &gt;&gt; 待办事项</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
</table>
<div id="dialog" title="待办事项">
    <table id="todolist" border="0" cellpadding="0" cellspacing="0" class="dbsx">
        <tr id="lastLogin"><td colspan="2">上次登录时间:</td></tr>
        <tr><td colspan="2">${USERLOGIN.datetime}</td></tr>
    </table>
</div>
</body>
</html>
