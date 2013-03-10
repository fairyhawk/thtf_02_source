<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>销售合同选择</title>
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
					});
				}

				$("#add").click(function(){   
					 var seleSellArr = new Array();

					 $('#sellTable input:checked').each(function(){  
							 seleSellArr.push($(this).val()); 
					 });  
					
					 if(seleSellArr.length>0){
						$("#sellTable :checkbox").attr("checked", false);
						opener.addSell(seleSellArr);
						window.close();
					 }else{ 
						alert("未选任务合同");
					 }
				 }); 

				 $("input:checkbox").click(function(){   
					if($(this).attr("checked")){ 
						if(opener.checkSell($(this).val())){
							alert("此合同已存在");
							$(this).attr("checked",false);
						} 
					}
				 });  

			});
		</script> 
	</head>
	<body> 
		<html:form method="post" action="getAppointSellContract" styleId="ascForm">
		<input type="hidden" name="mreturnPara.mreturnId" value="${para.mreturnId}"/>
		<table width="96%" border="0" cellpadding="0" cellspacing="0" align="center">
		<tr>
			<td align="center"><br />
			<div class="mo_wp">
			<div style="display: ; " class="mo_con" >
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
					<tr> 
						<td class="td_01" width="20%">产品合同号</td>
						<td class="td_02" width="30%">
							<input type="text" name="mreturnPara.productContractCode" value="${para.productContractCode}" style="width:120px;" />
						</td>
						<td class="td_01" width="20%">公司合同号</td>
						<td class="td_02" width="30%">
							<input type="text" name="mreturnPara.companyContractCode" value="${para.companyContractCode}" style="width:120px;" />
						</td>
					</tr> 
					<tr>
						<td colspan="4" align="center" style="height:30px;"><img src="${ctx}/images/btn_JianSuo.gif" onclick="javascript:$('#ascForm').submit();"/></td>
					</tr>
				</table>
			</div>
			<div class="mo_title">
				<div  style="text-align:center"><img src="${ctx}/images/shang_sj.png"/></div>
			</div>
			</div>
			</td>
		</tr>
		<tr>
			<td align="center"><br/>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table">
				<thead>
					<th nowrap="nowrap" width="30px">选择</th>
					<th nowrap="nowrap">产品分类名称</th>
					<th nowrap="nowrap">产品合同号</th>
					<th nowrap="nowrap">公司合同号</th>
					<th nowrap="nowrap">合同金额</th>
					<th nowrap="nowrap">发货金额</th>

					<th nowrap="nowrap">备货金额</th>
					<th nowrap="nowrap">指定金额</th>
					<th nowrap="nowrap">预收金额</th>
					<th nowrap="nowrap">开票金额</th>
					<th nowrap="nowrap">退货合同金额</th>

					<th nowrap="nowrap">退货金额</th>
					<th nowrap="nowrap">现结金额</th>
					<th nowrap="nowrap">人员名称</th>
				</thead>
				<tbody id="sellTable">
				<logic:iterate id="sellContract" name="sellList">
				<tr>
				  <td nowrap="nowrap" width="30px">
					<input type="checkbox" id="checkbox7" value="${sellContract.id}&${sellContract.productContractCode}&${sellContract.companyContractCode}&${sellContract.money}&${sellContract.fhMoney}&${sellContract.bhMoney}&${sellContract.sendGoodsMoney}&${sellContract.sellMoney-sellContract.appointMoney}&${sellContract.kpMoney}&${sellContract.sbMoney}&${sellContract.thMoney}&${sellContract.userName}"/>
				  </td>
				  <td nowrap="nowrap" width="77px">${sellContract.productTypeName}&nbsp;</td>
				  <td nowrap="nowrap" width="120px">${sellContract.productContractCode}&nbsp;</td>
				  <td nowrap="nowrap" width="120px">${sellContract.companyContractCode}&nbsp;</td>
				  <td nowrap="nowrap" width="84px" style=" text-align:right; padding-right:5px;"><fmt:formatNumber value="${sellContract.money}" pattern="#,##0.00#"/>&nbsp;</td>
				  <td nowrap="nowrap" width="84px" style=" text-align:right; padding-right:5px;"><fmt:formatNumber value="${sellContract.fhMoney}" pattern="#,##0.00#"/>&nbsp;</td>

				  <td nowrap="nowrap" width="84px" style=" text-align:right; padding-right:5px;"><fmt:formatNumber value="${sellContract.bhMoney}" pattern="#,##0.00#"/>&nbsp;</td>
				  <td nowrap="nowrap" width="84px" style=" text-align:right; padding-right:5px;"><fmt:formatNumber value="${sellContract.sendGoodsMoney}" pattern="#,##0.00#"/>&nbsp;</td>
				  <td nowrap="nowrap" width="84px" style=" text-align:right; padding-right:5px;"><fmt:formatNumber value="${sellContract.sellMoney}" pattern="#,##0.00#"/>&nbsp;</td>
				  <td nowrap="nowrap" width="84px" style=" text-align:right; padding-right:5px;"><fmt:formatNumber value="${sellContract.kpMoney}" pattern="#,##0.00#"/>&nbsp;</td>
				  <td nowrap="nowrap" width="84px" style=" text-align:right; padding-right:5px;"><fmt:formatNumber value="${sellContract.sbMoney}" pattern="#,##0.00#"/>&nbsp;</td>

				  <td nowrap="nowrap" width="84px" style=" text-align:right; padding-right:5px;"><fmt:formatNumber value="${sellContract.thMoney}" pattern="#,##0.00#"/>&nbsp;</td>
				  
				  <td nowrap="nowrap" width="84px" style=" text-align:right; padding-right:5px;"><fmt:formatNumber value="${sellContract.cashMoney}" pattern="#,##0.00#"/>&nbsp;</td>

				  <td nowrap="nowrap" width="84px">${sellContract.userName}&nbsp;</td>
				</tr> 
				</logic:iterate>
				</tbody>
			</table>
			<br />
			<table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table">
				<tr>
					<td width="50px" style=" text-align:left; padding-left:4px">&nbsp;</td>
					<td width="50px">&nbsp;</td>
					<td align="right"><%@ include file="/jsp/common/newPage.jsp"%></td>
				</tr>
			</table>
			<table align="center">
				<tr>
					<td height="45px" colspan="2" align="center" valign="bottom">
						<img src="${ctx}/images/btnChoice.gif" id="add" width="65" height="20" />
					</td>
				</tr>
			</table>
			</td>
		</tr>
		</table>
		</html:form> 
	</body>
</html>
