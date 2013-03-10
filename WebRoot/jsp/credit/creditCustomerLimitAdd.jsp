<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>客户信用添加</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../../js/tad_bs.js"></script>
		<script type="text/javascript" src="${ctx}/js/util.js"></script>

		<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="${ctx}/js/messages_cn.js"></script>
		<script type="text/javascript">
	
		//提交前的检查
		$(function(){
		
			var creditTypeId = $("#creditTypeId option:selected").val();
			
			show_hide_tdProjectName(creditTypeId);
			
			$("#cstmrCrdtForm").validate({
						rules: {
								"customerId": "required",					
								"creditTypeId": "required",
								"productTypeId": "required",
								//"projectName": "required",	
								"creditLimit": {required:true,number:true,min:0},			
								//"creditLimit": {required:true,number:true,range:[0,$("#distributeCredit").val()]},
								"arterm": {required:true,number:true,min:0}					
							}
			});	
			
			
		    var msg = "${msg}";  //获取服务端信息	

			
			if (msg != "") 
			{
                $("#projectName").attr("value","${projectName}");
				alert(msg);
			}		
			
		});		
	

	
		function show_hide_tdProjectName(creditTypeId) {
			
			if (creditTypeId == "5" )
			{
				$("td [name=tdProjectName]").show();
				$("#projectName").attr("value", "");
				$("td [name=tdProjectNameFill]").hide();
				$("td [name=tdagreementCode]").hide();	
			}else if(creditTypeId == "4"){
				$("td [name=tdagreementCode]").show();//.text("协议合同编号") tdagreementCode">agreementCode
				$("#agreementCode").attr("value", "");
				$("td [name=tdProjectNameFill]").hide();
				$("td [name=tdProjectName]").hide();
			}else
			{
				$("td [name=tdProjectName]").hide();
				$("td [name=tdProjectNameFill]").show();
				$("#projectName").attr("value", "null");
				$("td [name=tdagreementCode]").hide();
				$("td [name=tdProjectName]").hide();
				$("#projectName").attr("value", "null");
			} 
			
	
		}
		
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
		
		//填充选择的客户
		function fillSelectedCusmoer(customer) 
		{		
			$("#tdCustomerName").empty();
			$("#tdCustomerName").append("<input type='hidden' name='customerId' id='customerId' />" + customer.name);		
			$("#customerId").attr("value", customer.id);
			$("#customerName").attr("value", customer.name);
		
		}
		
		function getCanDistributeLimit() 
		{
				var productTypeId = $("#productTypeId option:selected").val();
				var creditTypeId = $("#creditTypeId option:selected").val();
				
				show_hide_tdProjectName(creditTypeId);
				
				if (creditTypeId != "" && productTypeId != "") 
				{					
					$.ajax({
						type:"POST",
						url:"${ctx}/getCanDistributeLimit.do",
						data:"productTypeId=" + productTypeId + "&creditTypeId=" + creditTypeId ,
						success:function(money){
						    $("#canDistributeLimit").empty();
							$("#canDistributeLimit").prepend( formatCurrency(money) + " 元");
							$("#distributeCredit").attr("value", Number(money));										
						
						}//success
						});//ajax
					}//if			
					
		};//function
	
	function checkSubmit(){
        var creditTypeId = $("#creditTypeId option:selected").val();
		var text=$("#txsm").val();
		if (creditTypeId == "5" ){
		    var pname = $("#projectName").val();
			if(pname=="" || pname==null){
			    $("#projectName").addClass("invalid");
				alert("请填写项目名称");
				return;
			}else{
				$("#projectName").removeClass("invalid");
			}
		}
		if(text.length>500){
			alert("特殊说明不能超过500字,目前已有"+text.length+"字。");
			return;
		}
	    $('#cstmrCrdtForm').submit();
	}
		</script>
	</head>

	<body >
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td class="ye_header_left">&nbsp;</td>
				<td class="ye_header_center">
					<img src="${ctx}/images/main_jt.jpg" />
					&nbsp;当前位置： 信用管理 &gt;&gt; 客户信用管理 &gt;&gt; 客户信用添加
				</td>
				<td class="ye_header_right">&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;
					 
				</td>
				<td align="center">
					<br />

					<html:form action="/addCustomerCredit.do" method="post" styleId="cstmrCrdtForm">						
						<input type="hidden" name="distributeCredit" id="distributeCredit" value="${customerCredit.distributeCredit}"/>
						<input type="hidden" name="customerName" id="customerName" value="${customerCredit.customerName}"/>
						<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
							<tr>
								<td nowrap="nowrap" class="td_01" width="20%">
									<a href="#" onclick="javascript:window.open('${ctx}/selectCustomer.do','newwindow', 'toolbar=no,scrollbars=yes,resizable=yes,top=200,left=400, width=600,height520');">
										<img src="${ctx}/images/btnChoice.gif" width="65" height="20" /></a>								</td>
								<td class="td_02" width="30%" id="tdCustomerName">									
									<input type="hidden" name="customerId" id="customerId" value="${customerCredit.customerId}"/>
									${customerCredit.customerName}								</td>
								<td nowrap="nowrap" class="td_01" width="20%">&nbsp;								</td>
								<td class="td_02" width="30%">&nbsp;								</td>
							</tr>
							<tr>
								<td nowrap="nowrap" class="td_01" width="20%" height="18px">
									<span style="color:#FF0000">*</span>&nbsp;产品分类名称								</td>
								<td class="td_02" width="30%">
									<select name="productTypeId" style=" width:126px"
										id="productTypeId" onchange="getCanDistributeLimit();">
										
											<option value="" >
											--请选择--											</option>
								
										
										<c:forEach var="productType" items="${productTypeList}">
											<option value="${productType.id}"
												<c:if test="${productType.id == customerCredit.productTypeId}">
													selected = "selected"
												</c:if>
											>
												${productType.name}
											</option>
										</c:forEach>	
									</select>								</td>
								<td nowrap="nowrap" class="td_01" width="20%"  height="18px">
									<span style="color:#FF0000">*</span>&nbsp;信用类型								</td>
								<td class="td_02" width="30%">
									<select name="creditTypeId" style=" width:126px" id="creditTypeId" onchange="getCanDistributeLimit();">
										
									
											<option value="" >
											--请选择--											</option>
										
										
										<c:forEach var="creditType" items="${creditTypeList}">
											<option value="${creditType.id}"
												<c:if test="${creditType.id == customerCredit.creditTypeId}">
													selected = "selected"
												</c:if>
											>
												${creditType.name}
											</option>												
										</c:forEach>
									</select>								</td>
							</tr>
							<tr>
								<td nowrap="nowrap" class="td_01" width="20%"  height="18px">
									可分配额度								</td>
								<td class="td_02" width="30%" height="18" id="canDistributeLimit">
									<c:if test="${not empty customerCredit}">
										<fmt:formatNumber value="${customerCredit.distributeCredit}" pattern="#,##0.00#"/>&nbsp;元									</c:if>&nbsp;								</td>
								<td nowrap="nowrap" class="td_01" width="20%" name="tdProjectName">	
									<span style="color:#FF0000">*</span>&nbsp;项目名称								</td>
								<td class="td_02" width="30%"  name="tdProjectName">
									<input type="text" name="projectName"  id="projectName" style="width:120px;" maxlength="20"
											value="${customerCredit.projectName}"/>								</td>
								<td nowrap="nowrap" class="td_01" width="20%" name="tdagreementCode">	
									&nbsp;协议合同编号								</td>
								<td class="td_02" width="30%"  name="tdagreementCode">
									<input type="text" name="agreementCode"  id="agreementCode" style="width:120px;" maxlength="30"
											value="${customerCredit.agreementCode}"/>								</td>
								<td class="td_01"  name="tdProjectNameFill">
									<span style="color:#FF0000"></span>&nbsp;								</td>
								<td class="td_02"  name="tdProjectNameFill">&nbsp;								</td>
							</tr>
							<tr>
								<td nowrap="nowrap" class="td_01" width="20%"  height="18px">
									<span style="color:#FF0000">*</span>&nbsp;信用额度								</td>
								<td class="td_02" width="30%">
									<input type="text" name="creditLimit" style="width:120px;"
										maxlength="11" id ="creditLimit" value="${customerCredit.creditLimit}"/>
									元								</td>
								<td nowrap="nowrap" class="td_01" width="20%">
									<span style="color:#FF0000">*</span>&nbsp;账期								</td>
								<td class="td_02" width="30%">
									<input type="text" name="arterm" style="width:120px;"
										maxlength="3" value="${customerCredit.arterm}" />
									天								</td>
							</tr>
							<tr>
          <td nowrap="nowrap" class="td_01" width="20%">特殊说明 </td>
          <td class="td_02" colspan="3" width="30%"><textarea name="text" cols="106" rows="5" id="txsm"></textarea>(最多500字)</td>
							</tr>
						</table>
						<br />
						<table border="0" cellpadding="0" cellspacing="0" width="300px" height="50" id="ec_table">
							<tr>
								<td align="center">
									<img src="${ctx}/images/btnAdd.gif" width="65" height="20" onclick="checkSubmit();" />
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<a href="${ctx}/customerCreditManage.do"><img src="${ctx}/images/btnBack.gif" width="65" height="20" /> 
									</a>
								</td>
							</tr>
						</table>
					</html:form>
				</td>
				<td>&nbsp;
					
				</td>
			</tr>
		</table>
	</body>
</html>
<br>