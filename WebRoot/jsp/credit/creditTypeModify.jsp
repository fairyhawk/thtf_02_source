<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>信用类型修改</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../../js/tad_bs.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/messages_cn.js"></script>

		<script type="text/javascript">
		$(document).ready(function(){
		    var msg = "${msg}";  //获取服务端信息
            
			$("#frmCrdt").validate({
				rules: {
					"creditTypeName": "required"
				},
				messages: {
					"creditTypeName": "请输入信用类型名称"
				}
			
			});
			if(msg!=""){
			    alert(msg);
			}
			
        });		

		</script>
	</head>

	<body>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td class="ye_header_left">
					&nbsp;
				</td>
				<td class="ye_header_center">
					<img src="${ctx}/images/main_jt.jpg" />
					&nbsp;当前位置： 信用管理 &gt;&gt; 信用类型管理 &gt;&gt; 信用类型名称修改
				</td>
				<td class="ye_header_right">
					&nbsp;
				</td>
			</tr>
			<tr>
				<td>
					&nbsp;
				</td>
				<td align="center">
					<br />
					<html:form styleId="frmCrdt" action="updateCreditType.do"
						method="post">
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="biao3">
							<tr>
								<td nowrap="nowrap" class="td_03" width="50%">
									<span style="color:#FF0000">*</span>&nbsp;信用类型名称
								</td>
								<td class="td_04">
									<input type="text" name="creditTypeName" value="${cinfo.name}"
										style="width:120px;" maxlength="8" id="name" />
									<input type="hidden" name="creditTypeId" value="${cinfo.id}" />
								</td>
							</tr>

						</table>
						<br />
						<table border="0" cellpadding="0" cellspacing="0" width="300px"
							id="ec_table">
							<tr>
								<td width="100px" height="50px" align="center">
									<a href="#" onclick="$('#frmCrdt').submit();"><img
											src="${ctx}/images/btnUpdate.gif" width="65" height="20"/></a>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<a href="${ctx}/findAllCreditType.do?"><img
											src="${ctx}/images/btnBack.gif" width="65" height="20"/></a>
								</td>
							</tr>
						</table>
					</html:form>
				</td>
				<td>
					&nbsp;
				</td>
			</tr>
		</table>
	</body>
</html>
