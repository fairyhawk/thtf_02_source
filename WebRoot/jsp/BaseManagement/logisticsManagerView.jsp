<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
</head>

<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置：  基础管理信息 &gt;&gt; 用户信息管理 &gt;&gt; 用户查看</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td align="center">
    <br/>
    	<table width="100%" border="0" cellpadding="0" cellspacing="0"  class="biao3">
        	<tr>
            	<td nowrap="nowrap" class="td_01" width="20%" height="18px">&nbsp;登录名</td>
                <td class="td_02" width="30%">${view.id}&nbsp;</td>
                <td class="td_01" width="20%">Email</td>
                <td class="td_02" width="30%">${view.mail}&nbsp;</td>
        	</tr>
          <tr>
            	<td nowrap="nowrap" class="td_01" height="18px">&nbsp;用户名</td>
                <td class="td_02">${view.name}&nbsp;</td>
                <td class="td_01">&nbsp;密码</td>
                <td class="td_02"></td>
          </tr>
            <tr>
            	<td nowrap="nowrap" class="td_01" height="18px">&nbsp;电话</td>
                <td class="td_02">${view.tel}</td>
                <td nowrap="nowrap" class="td_01">&nbsp;手机</td>
                <td class="td_02">${view.mobile}</td>
            </tr>
            <tr>
              <td class="td_01" height="18px">&nbsp;MSN</td>
              <td class="td_02">${view.msn}&nbsp;</td>
              <td class="td_01">&nbsp;QQ</td>
              <td class="td_02">${view.qq}&nbsp;</td>
            </tr>
            <tr>
              <td class="td_01" height="18px">&nbsp;使用状态</td>
              <td class="td_02"><c:if test="${view.enable==1}">使用</c:if><c:if test="${view.enable==0}">停用</c:if></td>
              <td class="td_01" height="18px">&nbsp;</td>
              <td class="td_02">&nbsp;</td>
            </tr>
            <tr>
              <td class="td_01">&nbsp;职务</td>
              <td class="td_02">${view.role_name}&nbsp;</td>
              <td class="td_01" height="18px">&nbsp;</td>
              <td class="td_02">&nbsp;</td>
            </tr>
            <tr>
              <td class="td_01" height="18px">&nbsp;所属物流公司</td>
              <td class="td_02">${view.logistics_name}&nbsp;</td>
              <td class="td_01" height="18px">&nbsp;</td>
              <td class="td_02">&nbsp;</td>
          </tr>
        </table>
        <table border="0" cellpadding="0" cellspacing="0" width="300px" id="ec_table">
        	<tr>
                <td  align="center" height="50px" valign="bottom"><a href="javascript:window.location = '${ctx}/searchUser.do?method=searchUserBefore';"><img src="${ctx}/images/btnBack.gif" width="65" height="20" /></a></td>
            </tr>
        </table>
    </td>
    <td >&nbsp;</td>
  </tr>
</table>
</body>
</html>
