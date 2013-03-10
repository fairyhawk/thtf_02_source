<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>产品分类信用总额修改</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/tad_bs1.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/messages_cn.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
		    var msg = "${msg}";  //获取服务端信息
            
			$("#frmCrdt").validate({
				rules: {
					"ptClimit": {required:true,number:true}
				},
				messages: {
					// "ptClimit": {required:"请输入信用额度",number:"信用额度必须是数字"}
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
					&nbsp;当前位置： 信用管理 &gt;&gt; 产品分类信用总额管理 &gt;&gt; 产品分类信用总额修改
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
					<html:form action="updateProdCreditLimit" method="post" styleId="frmCrdt">
					<br />
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="biao3">
							<tr>
								<td nowrap="nowrap" class="td_01" width="20%">
									产品分类名称
								</td>
								<td class="td_02" width="30%">
									${pcli.prodName}
								</td>
								<td nowrap="nowrap" class="td_01" width="20%">
									<span style="color:#FF0000">*</span>&nbsp;信用额度
								</td>
								<td class="td_02" width="30%">
									<input type="text" name="ptClimit" style="width:120px;"
										value="${pcli.totalLimit}" />&nbsp;元
									<input name="prodId" value="${pcli.prodId}" type="hidden" />
									
								</td>
							</tr>
							<tr>
								<td nowrap="nowrap" class="td_01" width="20%" height="18">
									已分配额度
								</td>
								<td class="td_02" width="30%">
									<fmt:formatNumber value="${pcli.assignedLimit} " pattern="#,##0.00#"/>&nbsp;元
								</td>
								<td nowrap="nowrap" class="td_01" width="20%">
									未分配额度
								</td>
								<td class="td_02" width="30%">
								<fmt:formatNumber value="${pcli.unassignedLimit}" pattern="#,##0.00#"/>&nbsp;元
								</td>
							</tr>
						</table>
					</html:form>
					<br />
					<table border="0" cellpadding="0" cellspacing="0" width="300px"
						id="ec_table">
						<tr>
							<td width="100px" height="50" align="center">
								<a href="#" onclick="$('#frmCrdt').submit();"> <img
										src="${ctx}/images/btnUpdate.gif" width="65" height="20" /></a>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="${ctx}/prodCreditLimit.do?"><img
										src="${ctx}/images/btnBack.gif" width="65" height="20" /> </a>
							</td>
						</tr>
					</table>
				</td>
				<td>
					&nbsp;
				</td>
			</tr>
		</table>
	</body>
</html>
