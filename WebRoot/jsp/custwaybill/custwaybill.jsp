<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运单查询</title>
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
.rowselected {
	background-color: #868686;
}
-->
</style>
<script language="JavaScript"> 
 
	$(document).ready(function(){
	 
		if($("#table")){
			$("#table tr:even").addClass("treven");
			$("#table tr").each(function(i){
				$(this).mouseover(function(){
					$(this).addClass("over");
				});
				$(this).mouseout(function(){
					$(this).removeClass("over");
				});
				$(this).click( function(){
	              if( $(this).hasClass("rowselected") ){
	                $(this).removeClass("rowselected");
	              }else{
	                $(this).addClass("rowselected");
	              }
	            });
			});
		}

		$('#JianSuo').click(function(){ 
			trimText(); 
			
			if($('#productContractCode').val() == "" && $('#companyContractCode').val() == ""){
				alert("至少输入一个条件！");
				return false;
			}

			if($('#code').val() == ""){
				alert("验证码不能为空！");
				return false;
			}

			$('#frmConList').submit();
		});

		if("${errorMsg}" != ""){
			alert("${errorMsg}");
		}
 
	}); 

	function trimText(){
		var fom = document.forms[0];
		for(var i=0;i<fom.elements.length;i++){
			if(fom.elements[i].type == 'text'){
			   fom.elements[i].value = $.trim(fom.elements[i].value);
			}
		}
	}

	function changeimg(){
		//var codelink = document.getElementById("codelink");
		var codeimg = document.getElementById("codeimg");
		codeimg.src = "${ctx}/images/confirmimg?date=" + new Date();
	}

	function queryDetail(boxId){
		if(boxId==""){
			alert("暂无运单！");
			return false;
		}else{
			return true;
		}

	}
</script> 
</head>
<body> 
<table width="100%" border="0" cellpadding="0" cellspacing="0"> 
	<tr>
		<td>&nbsp;</td>
		<td align="center"><br />
			<div class="mo_wp">
			<div style="display: ; " class="mo_con" >
			<html:form method="post" action="queryWaybill" styleId="frmConList">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
				<tr>
					<td class="td_01" width="20%">
						产品合同号
					</td>
					<td class="td_02" width="30%">
						<input type="text" name="queryPara.productContractCode" id="productContractCode" style="width:120px;" maxlength="20" value="${queryPara.productContractCode}"/>
					</td>
					<td class="td_01" width="20%">
						公司合同号
					</td>
					<td class="td_02" width="30%">
						<input type="text" name="queryPara.companyContractCode" id="companyContractCode" style="width:120px;" maxlength="20" value="${queryPara.companyContractCode}"/>
					</td>
				</tr>
				<tr>
					<td class="td_01" width="20%">
						验证码
					</td>
					<td class="td_02" width="30%">
						<input type="text" id="code" name="code" class="data" style="width:100px" />&nbsp;&nbsp;<a id="codelink" href="#" onclick="changeimg();" title="看不清?点击图片换一张!"><img id="codeimg" src="${ctx}/images/confirmimg" align="absmiddle"/></a>
					</td>
					<td class="td_01" width="20%">
						&nbsp;
					</td>
					<td class="td_02" width="30%">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td colspan="4" align="center" style="height:30px;">
                		<img id="JianSuo" src="${ctx}/images/btn_JianSuo.gif"/>
					</td>
				</tr>
			</table>
			</html:form>
			</div> 
		</td>
		<td>&nbsp;</td>
	</tr>
	<logic:present name="waybillList">
	<tr>
		<td>&nbsp;&nbsp;</td>
		<td align="center">
			<br/>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table">
			<tr>
				<th nowrap="nowrap">发货单号</th>
				<th nowrap="nowrap">产品分类名称</th>
				<th nowrap="nowrap">产品合同号</th>
				<th nowrap="nowrap">公司合同号</th>
				<th nowrap="nowrap">客户名称</th>

				<th nowrap="nowrap">发货日期</th>
				<th nowrap="nowrap">要求到帐日期</th>
				<th nowrap="nowrap">人员名称</th> 

				<th nowrap="nowrap">&nbsp;操作&nbsp;</th>
			</tr>
     
			<logic:iterate id="waybill" name="waybillList" >
			<tr>
				<td nowrap="nowrap" width="100px" height="18">${waybill.id}&nbsp;</td>
				<td nowrap="nowrap" width="100px" height="18">${waybill.productTypeName}&nbsp;</td>
				<td nowrap="nowrap" width="100px" height="18">${waybill.productContractCode}&nbsp;</td>
				<td nowrap="nowrap" width="100px" height="18">${waybill.companyContractCode}&nbsp;</td>
				<td nowrap="nowrap" width="100px" height="18">${waybill.customerName}&nbsp;</td>

				<td nowrap="nowrap" width="100px" height="18">${waybill.sendDate}&nbsp;</td>
				<td nowrap="nowrap" width="100px" height="18">${waybill.date}&nbsp;</td>
				<td nowrap="nowrap" width="100px" height="18">${waybill.userName}&nbsp;</td> 
				 
				<td nowrap="nowrap" width="70px">
					<a href="${ctx}/queryWaybillDetail.do?queryPara.boxId=${waybill.boxId}" onclick="return queryDetail('${waybill.boxId}');">查看运单</a> 
				</td>
			</tr>
			</logic:iterate>
			</table>
		</td>
		<td>&nbsp;</td>
	</tr>
	</logic:present>
</table> 

</body>
</html>
