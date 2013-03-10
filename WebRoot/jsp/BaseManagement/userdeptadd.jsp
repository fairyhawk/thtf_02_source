<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>人员部门管理-添加</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
<script type="text/javascript">
		$(document).ready(function(){
		    var msg = "${msg}";  //获取服务端信息
            
			$("#userDept").validate({
				rules: {
					"userdept.name": "required"
				},
				messages: {
					"userdept.name": "请输入正确的人员部门名称"
				}
			
			});
			if(msg!=""){
			    alert(msg);
			}
		});

	function checks(){
		var err = document.getElementById("err").value;
		if(err==null||err==""){
			
		} 
		else if(err!= null){
			alert("添加失败！");
			return false;
			}
	}
	

	
	</script>
</head>
<body onload="checks();">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" /> &nbsp;当前位置： 基础信息管理 &gt;&gt; 人员部门信息管理 &gt;&gt; 人员部门添加 </td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td align="center"><br />
      <html:form styleId="userDept" action="/userdeptmanagement" method="post"
						>
        <input type="hidden" name="method" value="addUserDept" />
        <table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="biao3">
          <tr>
            <td nowrap="nowrap" class="td_03"><span style="color:#FF0000">*</span>&nbsp;人员部门名称 </td>
            <td class="td_04" width="50%"><input type="text" name="userdept.name" id="name"
										maxlength="8" style="width:120px;">
              </input>
              <input type="hidden" name="err" value="${err}" id="err">
              </input>
            </td>
          </tr>
        </table>
        <table border="0" cellpadding="0" cellspacing="0" width="300px"
							id="ec_table">
          <tr>
            <td height="50px" valign="bottom" align="center"><a href="#" onclick="$('#userDept').submit();"><img
											src="${ctx}/images/btnAdd.gif" /></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a
										href="javascript:window.location='${ctx}/userdeptmanagement.do?method=userDeptAll';"><img
											src="${ctx}/images/btnBack.gif" width="65" height="20" /> </a> </td>
          </tr>
        </table>
      </html:form>
    </td>
    <td>&nbsp;</td>
  </tr>
</table>
</body>
</html>
