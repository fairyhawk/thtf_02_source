<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>产品分类信用类型额度添加</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../../js/tad_bs.js"></script>
		<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
		<script type="text/javascript" src="${ctx}/js/util.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="${ctx}/js/messages_cn.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
		    	var msg = "${msg}";  //获取服务端信息

				$("#frmInsert").validate({
				rules: {
					"productTypeId": "required",
					"creditTypeId": "required",
					"creditlimit.climit": {required:true,number:true}
				},
				messages: {
					//"selProductTypeId": {required:"请选择产品分类"}
					//"selCreditTypeId": {required:"请选择信用类型"}
					//"creditlimit.climit": {required:"请输入信用额度",number:"请输入合法的数字"}
				}
			});
			if(msg!=""){
			    	alert(msg);
				}
				
       		});	
       		
       		//下拉列表项改变时的操作
			function proChange(){
				var selVAL = $("#selProductTypeId option:selected").val();

				$("#canClimit").replaceWith("<td class='td_02' width='30%' id='canClimit'>&nbsp;元</td>");
				$.ajax({
					type:"POST",
					url:"findCanAssignClimit.do?method=findCanAssignClimit",
					data:"productTypeId="+selVAL,
					success:function(text){
						$("#canClimit").prepend(formatCurrency(text));
					}
				});
			};
       		//返回错误时设置原提交的值
       		$(window).load(function(){
				var pid = $("#productTypeIdHid").val();
				var cid = $("#creditTypeIdHid").val();
				var climit = $("#climitHid").val();
				if(pid!=""){
					$("#selProductTypeId").attr("value",pid);
					$("#selProductTypeId").trigger("change");
					$("#selCreditTypeId").attr("value",cid);
					$("#climits").val(climit);
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
					&nbsp;当前位置： 信用管理 &gt;&gt; 产品分类信用类型额度管理 &gt;&gt; 产品分类信用类型额度添加
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
					<html:form action="insertNewProdAssortCreditTypeLimit" method="post" styleId="frmInsert">
					    <input type="hidden" value="${productTypeId}" id="productTypeIdHid"></input>
						<input type="hidden" value="${creditTypeId}" id="creditTypeIdHid"/>
						<input type="hidden" value="${climit}" id="climitHid"/>
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="biao3">
							<tr>
								<td nowrap="nowrap" class="td_01" width="20%">
									<span style="color:#FF0000">*</span>&nbsp;产品分类名称
								</td>
								<td class="td_02" width="30%">
									<select name="productTypeId" style=" width:126px"
										id="selProductTypeId" onchange="proChange()">
										<option value="" selected="selected">
											--请选择--
										</option>
										<logic:present name="prodTypeList">
											<logic:iterate id="pnameLists" name="prodTypeList">
												<option value="${pnameLists.id}">
													${pnameLists.name}
												</option>
											</logic:iterate>
										</logic:present>
									</select>
								</td>
								<td nowrap="nowrap" class="td_01" width="20%">
									<span style="color:#FF0000">*</span>&nbsp;信用类型
								</td>
								<td class="td_02" width="30%">
									<select name="creditTypeId" style=" width:126px"
										id="selCreditTypeId">
										<option value="" selected="selected">
											--请选择--
										</option>
										<logic:present name="creditTypeList">
											<logic:iterate id="cnamelists" name="creditTypeList">
												<option value="${cnamelists.id}">
													${cnamelists.name}
												</option>
											</logic:iterate>
										</logic:present>
									</select>
								</td>
							</tr>
							<tr>
								<td nowrap="nowrap" class="td_01" width="20%">
									可分配额度

								</td>
								<td class="td_02" width="30%" id="canClimit">
								</td>
								<td nowrap="nowrap" class="td_01" width="20%">
									<span style="color:#FF0000">*</span>&nbsp;信用额度
								</td>
								<td class="td_02" width="30%">
									<input type="text" name="creditlimit.climit"
										style="width:120px;" maxlength="12"  id = "climits"/>&nbsp;元
								</td>
							</tr>
						</table>
						<br />
						<table border="0" cellpadding="0" cellspacing="0" width="300px"
							id="ec_table">
							<tr>
								<td width="100px" height="50" align="center">
									<a href="#" onclick="$('#frmInsert').submit();" > <img
											src="${ctx}/images/btnAdd.gif" class="submit"/></a>
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
