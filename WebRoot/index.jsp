<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%> 
<html>
<head>
<title>清华同方信用管理系统</title>
<script language="JavaScript" type="text/JavaScript">
	
</script>
</head>
<frameset rows="73,*" cols="*" frameborder="no" border="0" framespacing="0">
	<frame src="header.jsp" name="header" frameborder="NO" scrolling="NO" noresize marginwidth="0" marginheight="0" id="header" >
	<frameset id=fmain style="corsor: auto" border=0 name="fmain" frameSpacing=0 frameBorder=no cols="175, 6, *">
		<!--页左-->
		<frame src="left.jsp" name=navigation scrolling="NO" noResize marginWidth=0 marginHeight=0 id="Navigation Area">
		<frame name=f5 marginWidth=0 marginHeight=0 src="left_center.jsp" frameBorder=no noResize scrolling=no>
		
		<frameset rows="*,29" frameborder="NO" border="0" framespacing="0" name="youmain">
			<frame id=body name=body marginWidth=0 marginHeight=0 src="main.jsp" noResize>
			<frame src="footer.jsp" name="footer" scrolling="NO" noresize id="footer">
		</frameset>
	</frameset>
</frameset><noframes></noframes>
<body><br>
</body>
</html>
