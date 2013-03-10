<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>客户信用修改</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../../js/tad_bs.js"></script>
		<script type="text/javascript" src="${ctx}/js/util.js"></script>
			
		<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="${ctx}/js/messages_cn.js"></script>
		<script type="text/javascript">
	
		 function show_hide_tdProjectName(creditTypeId) {
			
			if (creditTypeId == "5" )
			{
				$("td [name=tdProjectName]").show();
				$("td [name=tdProjectNameFill]").hide();
				$("td [name=tdagreementCode]").hide();	
				$("#cstmrCrdtForm").validate({
					rules: {
						//"customerId": "required",					
						"creditTypeId": "required",
						"projectTypeId": "required",
						"projectName": "required",	
						"creditLimit":  {required:true,number:true,min:0},
						"arterm": {required:true,number:true,min:0}					
					}
				});
			}else if(creditTypeId == "4"){
				$("td [name=tdagreementCode]").show();
				$("td [name=tdProjectName]").hide();
				$("td [name=tdProjectNameFill]").hide();
				$("#projectName").attr("value", "null");
			} 
			else
			{
				$("td [name=tdProjectName]").hide();
				$("td [name=tdProjectNameFill]").show();
				$("#projectName").attr("value", "null");
				$("td [name=tdagreementCode]").hide();
			} 
		}

		//提交前的检查
		$(function(){
		
		    var msg = "${msg}";  //获取服务端信息
		    
		    var creditTypeId = $("#creditTypeId option:selected").val();
			if (creditTypeId==null || creditTypeId=="") {
				creditTypeId = $("#creditTypeId").val();
			}

			show_hide_tdProjectName(creditTypeId);		

			$("#cstmrCrdtForm").validate({
				rules: {					
					"creditTypeId": "required",
					"projectName": "required",	
					"creditLimit":  {required:true,number:true,min:0},
					"arterm": {required:true,number:true,min:0}					
				}
			});


			if (msg != "") 
			{
				$("#creditLimit").attr("value",formatCurrency(${customerCredit.creditLimit}));
				alert(msg);
			}
		});

		//ajax取可分配额度值
		function getCanDistributeLimit() 
		{
					var productTypeId = $("#productTypeId").val();
					var creditTypeId = $("#creditTypeId option:selected").val();
					
					show_hide_tdProjectName(creditTypeId);
					
					if (creditTypeId != "" || creditTypeId != null) 
					{
						$.ajax({
							type:"POST",
							url:"${ctx}/getCanDistributeLimit.do",
							data:"productTypeId=" + productTypeId + "&creditTypeId=" + creditTypeId ,
							success:function(text){
							    $("#canDistributeLimit").empty();
								if(text=="0" || text=="0.0"){
								    $("#canDistributeLimit").prepend("0.00 元");
								}else{
								    $("#canDistributeLimit").prepend( formatCurrency(text) + " 元");
								}
								var old = $("#oldCreditLimit").val()-0;
								var max = Number(text);
							
								total = old + max;
								
								$("#distributeCredit").attr("value", text);							
								$("#resultLimit").attr("value", total);
							
								$("#cstmrCrdtForm").validate({
									rules: {					
										"creditTypeId": "required",
										 "projectName": "required",	
										"creditLimit": {required:true,number:true,min:0},
										"arterm": {required:true,number:true,min:0}					
									},
													
									messages: {
										//"selProductTypeId": {required:"请选择产品分类"}
										//"selCreditTypeId": {required:"请选择信用类型"}
										//"creditlimit.climit": {required:"请输入信用额度",number:"请输入合法的数字"}
									}
								});
							}
						});
					}				
				
		};
		
		
		function getCreditType() {
			
			var pid = $("#productTypeId option:selected").val();
			
			if (pid != "")
			{
				$.getJSON("${ctx}/getAllCreditTypeOfProduct.do?productTypeId=" + pid, function(creditTypeList){
					$("#creditTypeId").empty();
					
					var  options = "<option value=''>请选择</option>";
					
					$.each(creditTypeList,function(i,entry){
						options = options + "<option value='"+ entry['id'] + "'>" + entry['name'] + "</option>";
											
					});
					
					$("#creditTypeId").append(options);
				});
			}
			else
			{	$("#creditTypeId").empty();
				$("#creditTypeId").append("<option value=''>请选择</option>");
			}
		}
		function setFlag(){
			var pName = $.trim($("#projectName").val());
			var pNameOld = $.trim($("#projectNameOld").val());
			var text=$("#txsm").val();
			if( pName!="" && pName!=null){
				 if(pName==pNameOld){
					 $("#flag").attr("value",1);
				 }
			}
			var flg = $("#enable  option:selected").val();
		  var free = parseFloat($("#distributeCredit").val());
		   var limit = parseFloat($("#creditLimit").val());
		   var usedLock =parseFloat("${customerCredit.lockCredit + customerCredit.usedCredit}");
			if(flg==0){
		    if(limit>free){
			        alert("信用额度必须小于等于可分配额度");
					return ;
			   }
			}else{
			    if(limit<=free && limit>=usedLock){
				}else{
				    alert("信用额度必须大于等于已用冻结额度并且小于等于可分配额度");
					return;
				}    
			}
			if(text.length>500){
			alert("特殊说明不能超过500字,目前已有"+text.length+"字。");
			return;
		}
            $("#cstmrCrdtForm").submit();
		}		
	</script>
	</head>

	<body >
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td class="ye_header_left">
					&nbsp;
				</td>
				<td class="ye_header_center">
					<img src="${ctx}/images/main_jt.jpg" />
					&nbsp;当前位置： 信用管理 &gt;&gt; 客户信用管理 &gt;&gt; 客户信用修改
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

					<html:form action="/modifyCustomerCredit" method="get" styleId="cstmrCrdtForm" >
						<input type="hidden" name="id" value="${customerCredit.id}" />
						<input type="hidden" name="timeStamp" value="${customerCredit.timeStamp}" />
						<input type="hidden" name="productTypeId" id="productTypeId" value="${customerCredit.productTypeId}"/>
						<input type="hidden" name="distributeCredit" id="distributeCredit" value="${customerCredit.distributeCredit}"/>
						<input type="hidden" name="oldCreditLimit" id="oldCreditLimit" value="${customerCredit.creditLimit}"/>
						<input type="hidden" name="resultLimit" id="resultLimit" value="${customerCredit.creditLimit + customerCredit.distributeCredit}"/>
						<input type="hidden" name="customerName" id="customerName" value="${customerCredit.customerName}"/>
						<input type="hidden" name="productTypeName" id="productTypeName" value="${customerCredit.productTypeName}"/>
						<input type="hidden"  name="timeStamp" id="timeStamp" value="${customerCredit.timeStamp}"/>
						<input type="hidden" name="lockCredit" id="lockCredit" value="${customerCredit.lockCredit + customerCredit.usedCredit}"/>
						<input type="hidden" name="customerId" id="customerId" value="${customerId}"/>
						<input type="hidden" name="flag" id="flag" value="0"/>
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="biao3">
							<tr>
								<td nowrap="nowrap" class="td_01" width="20%" height="18px">
									客户名称
								</td>
								<td class="td_02" width="30%">
									${customerCredit.customerName}									
								</td>
								<td nowrap="nowrap" class="td_01" width="20%">
									&nbsp;
								</td>
								<td class="td_02" width="30%">
									&nbsp;
								</td>
							</tr>
							<tr>
								<td nowrap="nowrap" class="td_01" width="20%">
									产品分类名称
								</td>
								<td class="td_02" width="30%">								   								
									${customerCredit.productTypeName}
								</td>
								<td nowrap="nowrap" class="td_01" width="20%" height="18px">
									信用类型
								</td>
								<td class="td_02" width="30%">									
									
									<c:if test="${customerCredit.creditTypeId == 5}">
										项目
						            <input type="hidden" name="creditTypeId" id="creditTypeId" value="${customerCredit.creditTypeId}"/>	
									</c:if>
									
									<c:if test="${customerCredit.creditTypeId != 5}">
										<select name="creditTypeId" style=" width:126px" id="creditTypeId" onchange="getCanDistributeLimit();">
											<c:forEach var="creditType" items="${creditTypeList}">
												<c:if test="${creditType.id!=5}">
													<option value="${creditType.id}" 
													<c:if test="${creditType.id == customerCredit.creditTypeId}">
													selected="selected"
													</c:if>>
													${creditType.name}
													</option>
												</c:if>
											</c:forEach>
										</select>
									</c:if>
								</td>
							</tr>
							<tr>
								<td nowrap="nowrap" class="td_01" width="20%">
									可分配额度
								</td>
								<td class="td_02" width="30%" height="18" id="canDistributeLimit">
										<fmt:formatNumber value="${customerCredit.distributeCredit}" pattern="#,##0.00#"/>&nbsp;元
								</td>
								<td nowrap="nowrap" class="td_01" width="20%" name="tdProjectName">
									<span style="color:#FF0000">*</span>&nbsp;项目名称
								</td>
								<td class="td_02" width="30%" name="tdProjectName">
									<input type="text" id="projectName" name="projectName" style="width:120px;" maxlength="20"
										value="${customerCredit.projectName}" />
									<input type="hidden" id="projectNameOld" name="projectNameOld" style="width:120px;"
										value="${customerCredit.projectName}" />
								</td>
								<td nowrap="nowrap" class="td_01" width="20%" name="tdagreementCode">	
									&nbsp;协议合同编号
								</td>
								<td class="td_02" width="30%"  name="tdagreementCode">
									<input type="text" name="agreementCode"  id="agreementCode" style="width:120px;" maxlength="30"
											value="${customerCredit.agreementCode}"/>
								</td>
								<td nowrap="nowrap" class="td_01" width="20%" name="tdProjectNameFill">
									<span style="color:#FF0000"></span>&nbsp;
								</td>
								<td class="td_02" width="30%" name="tdProjectNameFill">
									&nbsp;
								</td>
							</tr>
							<tr>
								<td nowrap="nowrap" class="td_01" width="20%">
									<span style="color:#FF0000">*</span>&nbsp;信用额度
								</td>
								<td class="td_02" width="30%">
									<input type="text" id = "creditLimit" name="creditLimit" style="width:120px;"
										value="${customerCredit.creditLimit}" maxlength="11" />
									<input type="hidden" value="${customerCredit.creditLimit}" id="oldLimit" />									
									元
								</td>
								</td>
									<td nowrap="nowrap" class="td_01" width="20%">
									<span style="color:#FF0000">*</span>&nbsp;账期
								</td>
								<td class="td_02" width="30%">
									<input type="text" name="arterm" style="width:120px;"
										value="${customerCredit.arterm}" maxlength="3" />
									天
								</td>
							</tr>
							<tr>
								<td nowrap="nowrap" class="td_01" width="20%">
									已用冻结额度
								</td>
								<td class="td_02" width="30%">
									<fmt:formatNumber value="${customerCredit.lockCredit + customerCredit.usedCredit}" pattern="#,##0.00#"/>&nbsp;元
								</td>
								<td nowrap="nowrap" class="td_01" width="20%">
									状态
								</td>
								<td class="td_02" width="30%">
									<select id="enable" name="enable" style=" width:126px">
										<option
											<logic:equal value="1" name="customerCredit" property="enable"> selected="selected"</logic:equal>
											value="1">
											启用
										</option>
										<option
											<logic:notEqual value="1" name="customerCredit" property="enable"> selected="selected"</logic:notEqual>
											value="0">
											停用
										</option>
									</select>
								</td>
							</tr>
							<tr>
          <td nowrap="nowrap" class="td_01" width="20%">特殊说明 </td>
          <td class="td_02" colspan="3" width="30%"><textarea cols="106" rows="5" name="text" id="txsm">${customerCredit.text}</textarea>(最多500字)</td>
							</tr>
				
						</table>
						<br />
						<table border="0" cellpadding="0" cellspacing="0" width="300px"
							id="ec_table">
							<tr>
								<td width="300px" height="50" align="center">
									<a href="#" onclick="setFlag();" ><img
											src="${ctx}/images/btnUpdate.gif" width="65" height="20" /></a>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<a href="${ctx}/customerCreditManage.do"><img
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
