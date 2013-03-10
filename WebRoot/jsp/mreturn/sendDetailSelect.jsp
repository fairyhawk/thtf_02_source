<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>发货明细选择</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/math.js"></script>
		<script type="text/javascript" src="${ctx}/js/Calendar1.js"></script>
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
					 var sendGoodsArr = new Array();

					 $('#sendGoodsTable input:checked').each(function(){  
							 sendGoodsArr.push($(this).val()); 
					 });  
					
					 if(sendGoodsArr.length>0){
						$("#sendGoodsTable :checkbox").attr("checked", false);
						opener.addSendGoods(sendGoodsArr);
						window.close(); 
					 }else{ 
						alert("未选任务发货明细");
					 }
				 }); 

				 $("input:checkbox").click(function(){   
					if($(this).attr("checked")){ 
						if(opener.checkSendGoods($(this).val())){
							alert("此发货明细已存在");
							$(this).attr("checked",false);
						} 
					}
				 });  

			});
		</script> 
	</head>
	<body> 
	<html:form method="post" action="getAppointSendGoods" styleId="ascForm">
	<input type="hidden" name="mreturnPara.mreturnId" value="${para.mreturnId}"/> 
	<table width="96%" border="0" cellpadding="0" cellspacing="0" align="center">
	<tr>
	<td align="center"><br />
		<div class="mo_wp">
		<div style="display: ; " class="mo_con" >
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
			<tr>
				<td class="td_01" width="20%">发货单号</td>
				<td class="td_02" width="30%">
					<input type="text" name="mreturnPara.sendGoodsId" value="${para.sendGoodsId}" style="width:120px;" />
				</td>
				<td class="td_01" width="20%">产品合同号</td>
				<td class="td_02" width="30%">
					<input type="text" name="mreturnPara.productContractCode" value="${para.productContractCode}" style="width:120px;" />
				</td>
			</tr>
			<tr>
				<td class="td_01" height="18px">公司合同号</td>
				<td class="td_02">
					<input type="text" name="mreturnPara.companyContractCode" value="${para.companyContractCode}" style="width:120px;" />
				</td>
				<td class="td_01">产品编码</td>
				<td class="td_02">
					<input type="text" name="mreturnPara.productCode" value="${para.productCode}" style="width:120px;" />
				</td>
				</tr>
				<tr>
					<td class="td_01" height="18px">产品名称</td>
					<td class="td_02">
						<input type="text" name="mreturnPara.productName" value="${para.productName}" style="width:120px;" />
				</td>
				<td class="td_01">规格型号</td>
				<td class="td_02">
					<input type="text" name="mreturnPara.productType" value="${para.productType}" style="width:120px;" />
				</td>
			</tr>
			<tr>
				<td class="td_01" height="18px">要求到帐起始日期</td>
				<td class="td_02">
					<input type="text" name="mreturnPara.startSgrqartermdate" id="mreturnPara.startSgrqartermdate" style="width:120px;" onfocus="calendar()"
									readonly="readonly" value="${para.startSgrqartermdate}" /></td>
				<td class="td_01">要求到帐终止日期</td>
				<td class="td_02">
					<input type="text" name="mreturnPara.endSgrqartermdate" id="mreturnPara.endSgrqartermdate" style="width:120px;" onfocus="calendar()"
									readonly="readonly" value="${para.endSgrqartermdate}" /></td>
				</tr>
			<tr>
				<td class="td_01" width="20%">人员名称</td>
				<td class="td_02" width="30%">
					<input type="text" name="mreturnPara.userName" value="${para.userName}" style="width:120px;" />
				</td>
				<td class="td_01" width="20%">&nbsp;</td>
				<td class="td_02" width="30%">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="4" align="center" style="height:30px;"><img src="${ctx}/images/btn_JianSuo.gif" onclick="javascript:$('#ascForm').submit();"/></td>
			</tr>
		</table>
		</div>
		<div class="mo_title">
			<div  style="text-align:center"><img src="${ctx}/images/shang_sj.png" /></div>
			</div>
		</div>
	</td>
	</tr>
	<tr>
	<td align="center"><br/>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table">
			<thead>
				<th nowrap="nowrap" width="30px">选择</th>
				<th nowrap="nowrap">发货单号</th>
				<th nowrap="nowrap">要求到帐日期</th>
				<th nowrap="nowrap">产品合同号</th>
				<th nowrap="nowrap">公司合同号</th>
				<th nowrap="nowrap">产品编码</th>
				<th nowrap="nowrap">产品名称</th>

				<th nowrap="nowrap">规格型号</th>
				<th nowrap="nowrap">单位</th>
				<th nowrap="nowrap">销售单价</th>

				<th nowrap="nowrap">发货金额</th>
				<th nowrap="nowrap">指定金额</th>
				<th nowrap="nowrap">开票金额</th>
				<th nowrap="nowrap">退货金额</th>

				<th nowrap="nowrap">人员名称</th>
			</thead>
				<tbody id="sendGoodsTable">
				<logic:iterate id="sendgoods" name="sendGoodsList" >
				<tr>
				  <td nowrap="nowrap" width="30px">
					<input type="checkbox" id="checkbox7" value="${sendgoods.productId}&${sendgoods.sendGoodsId}&${sendgoods.productContractCode}&${sendgoods.companyContractCode}&${sendgoods.productCode}&${sendgoods.productName}&${sendgoods.productType}&${sendgoods.productUnit}&${sendgoods.sellContractDetailPrice}&${sendgoods.fhMoney}&${sendgoods.sendGoodsMoney}&${sendgoods.kpMoney}&${sendgoods.thMoney}&${sendgoods.userName}"/>
				  </td>
				  <td nowrap="nowrap" width="77px">${sendgoods.sendGoodsId}&nbsp;</td>
				  <td nowrap="nowrap" width="77px">${sendgoods.sgrqartermdate}&nbsp;</td>
				  <td nowrap="nowrap" width="120px">${sendgoods.productContractCode}&nbsp;</td>
				  <td nowrap="nowrap" width="120px">${sendgoods.companyContractCode}&nbsp;</td>
				  <td nowrap="nowrap" width="84px" >${sendgoods.productCode}&nbsp;</td>
				  <td nowrap="nowrap" width="84px" >${sendgoods.productName}&nbsp;</td>

				  <td nowrap="nowrap" width="84px" >${sendgoods.productType}&nbsp;</td>
				  <td nowrap="nowrap" width="84px" >${sendgoods.productUnit}&nbsp;</td>
				  <td nowrap="nowrap" width="84px" style=" text-align:right; padding-right:5px;"><fmt:formatNumber value="${sendgoods.sellContractDetailPrice}" pattern="#,##0.00#"/>&nbsp;</td>

				  <td nowrap="nowrap" width="84px" style=" text-align:right; padding-right:5px;"><fmt:formatNumber value="${sendgoods.fhMoney}" pattern="#,##0.00#"/>&nbsp;</td>
				  <td nowrap="nowrap" width="84px" style=" text-align:right; padding-right:5px;"><fmt:formatNumber value="${sendgoods.sendGoodsMoney-sendgoods.appointMoney}" pattern="#,##0.00#"/>&nbsp;</td> 
				  <td nowrap="nowrap" width="84px" style=" text-align:right; padding-right:5px;"><fmt:formatNumber value="${sendgoods.kpMoney}" pattern="#,##0.00#"/>&nbsp;</td>  
				  <td nowrap="nowrap" width="84px" style=" text-align:right; padding-right:5px;"><fmt:formatNumber value="${sendgoods.thMoney}" pattern="#,##0.00#"/>&nbsp;</td>

				  <td nowrap="nowrap" width="84px" >${sendgoods.userName}&nbsp;</td>
				</tr> 
				</logic:iterate>
				</tbody>
		  </table>
		  <br />
		  <table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table">
			<tr>
			  <td align="right"><%@ include file="/jsp/common/newPage.jsp"%></td>
			</tr>
		  </table>
		  <table align="center">
			<tr>
			  <td height="45px" colspan="2" align="center" valign="bottom">
				<img src="${ctx}/images/btnChoice.gif" id="add" width="65" height="20" />
			  </td>
			</tr>
		  </table></td>
	  </tr>
	</table> 
	</html:form>
	</body>
</html>