<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>库房管理员查看</title>
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
		</style>
		<script language="JavaScript"> 
		<!--
			$(document).ready(function(){
				if($("#xxxlist")){
					$("#xxxlist tr:even").addClass("treven");
					$("#xxxlist tr").each(function(i){
						$(this).mouseover(function(){
							$(this).addClass("over");
						});
						$(this).mouseout(function(){
							$(this).removeClass("over");
						});
					});
				}
			});
		//-->
		</script>
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
<script type="text/javascript">
<!--
function MM_jumpMenu(targ,selObj,restore){ //v3.0
  eval(targ+".location='"+selObj.options[selObj.selectedIndex].value+"'");
  if (restore) selObj.selectedIndex=0;
}
//-->
</script>
</head>

<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center" style="font-size:12px;"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 基础信息管理 &gt;&gt; 用户信息管理 &gt;&gt; 用户查看</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
   <tr>
    <td >&nbsp;</td>
    <td align="center">
    <br />
    	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        	<tr>
            	<td class="td_01" height="18px">登录名</td>
                <td class="td_02" >${view.id}&nbsp;</td>
                <td class="td_01">Email</td>
                <td class="td_02" >${view.mail}&nbsp;</td>
        	</tr>
            <tr>
            	<td class="td_01" height="18px">用户名</td>
                <td class="td_02">${view.name}&nbsp;</td>
                <td class="td_01">密码</td>
                <td class="td_02">&nbsp;</td>
            </tr>
            <tr>
            	<td class="td_01" height="18px">电话</td>
                <td class="td_02">${view.tel}&nbsp;</td>
                <td class="td_01">手机</td>
                <td class="td_02">${view.mobile}&nbsp;</td>
            </tr>
            <tr>
            	<td class="td_01" height="18px">MSN</td>
              <td class="td_02">${view.msn}&nbsp;</td>
                <td class="td_01">QQ</td>
                <td class="td_02">${view.qq}&nbsp;</td>
            </tr>
            <tr>
           	  <td class="td_01" height="18px">使用状态</td>
              <td class="td_02"><c:if test="${view.enable==1}">使用</c:if><c:if test="${view.enable==0}">停用</c:if>&nbsp;</td>
              <td class="td_01">人员部门</td>
              <td class="td_02">${view.dept_name}&nbsp;</td>
            </tr>
            <tr>
           	  <td class="td_01" height="18px">职务</td>
              <td class="td_02">${view.role_name}&nbsp;</td>
              <td class="td_01">&nbsp;</td>
              <td class="td_02">&nbsp;</td>
            </tr>
            
        </table>
     </td>
    <td >&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td align="center">
    <br/>
    <div class=" div_tiao" style="font-size:12px;">&nbsp;负责库房产品</div>
    	<table width="50%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="xxxlist">
        	<tr>
                <th nowrap="nowrap">库房名称</th>
                <th nowrap="nowrap">产品分类名称</th>
            </tr>
            <logic:iterate id="asdga" name="asdga">
	          <tr>
	            <td align="right" height="18px" width="50%">&nbsp;${asdga.area_name}</td>
	            <td align="right">&nbsp;${asdga.type_name}</td>
	          </tr>
          </logic:iterate>
        </table>
<table border="0" cellpadding="0" cellspacing="0" width="50%" id="ec_table">
</table>
    </td>
    <td >&nbsp;</td>
  </tr>
  <tr><td></td><td height="50px" align="center" valign="bottom">
  <a href="javascript:window.location = '${ctx}/searchUser.do?method=searchUserBefore';"><img src="${ctx}/images/btnBack.gif" /></a></td>
    <td></td>
  </tr>
</table>
<br />

</body>
</html>