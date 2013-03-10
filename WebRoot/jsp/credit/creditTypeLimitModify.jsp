<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>产品分类信用类型额度修改</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../../js/tad_bs.js"></script>
		<script type="text/javascript" src="${ctx}/js/math.js"></script>
		<script type="text/javascript" src="${ctx}/js/util.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="${ctx}/js/messages_cn.js"></script>
        <script type="text/javascript">
		$(document).ready(function(){
		    var msg = "${msg}";  //获取服务端信息
            
			$("#formModify").validate({
				rules: {
					"creditlimit.climit": {required:true,number:true,max:$("#freeLimit").val(),min:$("#assignLimit").val()}
				},
				messages: {
				
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
					&nbsp;当前位置： 信用管理 &gt;&gt; 产品分类信用类型额度管理 &gt;&gt; 产品分类信用类型额度修改
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
					<html:form styleId="formModify" action="updateProdAssortCreditLimit" method="get">
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="biao3">
							<tr>
								<td nowrap="nowrap" class="td_01" width="20%" height="18px"> 
									产品分类名称
								</td>
								<td class="td_02" width="30%">
									<input type="hidden" name="productTypeId"
										value="${creditTypeLimit.prodId}">
									<input type="hidden" name="timeStamp"
										value="${creditTypeLimit.timeStamp}">
									<input type="hidden" name="freeLimit"
										value="${creditTypeLimit.freeLimit}" id="freeLimit">
									<input type="hidden" name="assignLimit"
										value="${creditTypeLimit.assignedLimit}" id="assignLimit">		
									${creditTypeLimit.prodName}
								</td>
								<td nowrap="nowrap" class="td_01" width="20%">
									信用类型
								</td>
								<td class="td_02" width="30%">
									<input type="hidden" name="creditTypeId"
										value="${creditTypeLimit.crdttpId}">
									${creditTypeLimit.crdttpName}
								</td>
							</tr>
							<tr>
								<td nowrap="nowrap" class="td_01" width="20%" height="18px">
									可分配额度
								</td>
								<td class="td_02" width="30%">
									<fmt:formatNumber value="${creditTypeLimit.freeLimit}" pattern="#,##0.00#"/>&nbsp;元
								</td>
								<td nowrap="nowrap" class="td_01" width="20%">
									<span style="color:#FF0000">*</span>&nbsp;信用额度
								</td>
								<td class="td_02" width="30%">
									<input type="text" name="creditlimit.climit"
										style="width:120px;" value="${creditTypeLimit.selfLimit}" maxlength="12" id="climit"/>&nbsp;元
								</td>
							</tr>
							<tr>
								<td nowrap="nowrap" class="td_01" width="20%" height="18px">
									已分配额度
								</td>
								<td class="td_02" width="30%">
									<fmt:formatNumber value="${creditTypeLimit.assignedLimit}" pattern="#,##0.00#"/>&nbsp;元
								</td>
								<td nowrap="nowrap" class="td_01" width="20%">
									未分配额度
								</td>
								<td class="td_02" width="30%">
									<fmt:formatNumber value="${creditTypeLimit.unassignedLimit}" pattern="#,##0.00#"/>&nbsp;元
								
								</td>
							</tr>
						</table>
						<br />
						<table border="0" cellpadding="0" cellspacing="0" width="300px"
							id="ec_table">
							<tr>
								<td width="100px" height="50" align="center">
									<a href="#" onclick="$('#formModify').submit();"> <img
											src="${ctx}/images/btnUpdate.gif" width="65" height="20" /></a>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<a href="creditTypeLimit.do"><img
											src="${ctx}/images/btnBack.gif" width="65" height="20" /> </a>
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
