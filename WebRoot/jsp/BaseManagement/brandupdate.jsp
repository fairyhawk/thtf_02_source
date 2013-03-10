<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
	<head>

		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>品牌管理-修改</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../..js/tad_bs.js"></script>
		<script type="text/javascript">
	function check(){
		var name = document.getElementById("name").value;
		if(name == ""){
			alert("不可以为空！");
			}else{
		document.forms[0].submit();
		}
	}
	function checks(){
		var err = document.getElementById("err").value;
		if(err==null||err==""){
			
		} 
		else if(err!= null){
			alert("修改失败！");
			}
	}
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
					&nbsp;当前位置： 基础信息管理 &gt;&gt; 人员区域信息管理 &gt;&gt; 人员区域修改
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
					<html:form action="/brand" method="post">
						<input type="hidden" name="method" value="saveBrandUpdate" />
						<table width="400px" border="0" cellpadding="0" cellspacing="0"
							class="biao2">
							<tr>
								<th colspan="2">
									品牌修改
								</th>
							</tr>
							<tr>
								<td nowrap="nowrap" class="biao2_01">
									<span style="color:#FF0000">*</span>&nbsp;品牌名称
								</td>
								<td>
									<input type="text" name="brand.name" value="${binfo.name}"
										id="name" maxlength="12"></input>
									<input type="hidden" name="brand.id" value="${binfo.id}" />
									<input type="hidden" name="err" value="${err}" id="err"></input>
								</td>
							</tr>

						</table>
						<br />
						<table border="0" cellpadding="0" cellspacing="0" width="300px"
							id="ec_table">
							<tr>
								<td width="100px" align="center">
									<a href="javascript:check();"><img
											src="${ctx}/images/btnUpdate.gif" width="65" height="20" />
									</a>
								</td>
								<td width="100px" align="center">
									<a
										href="javascript:window.location='${ctx}/brand.do?method=brandAll';"><img
											src="${ctx}/images/btnBack.gif" width="65" height="20" />
									</a>
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
