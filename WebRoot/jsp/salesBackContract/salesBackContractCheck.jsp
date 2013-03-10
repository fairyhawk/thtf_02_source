<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@taglib prefix="tf" uri="localhost" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>销售退货合同修改</title>
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
		<!--
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
			});
		//-->
		</script>
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>

<script type="text/javascript">

	$(function(){
	
		$("#tableAssessIdea :checkbox").attr("disabled", true);
		
		var msg = "${msg}"
		
		if (msg != "") {
			alert(msg);
		}
	
	});
	
	//销售合同预览
	function previewSaleBackContract() {
		window.open('${ctx}/salesBackContractPreview.do?backContractId=${salesBackContract.id}','newwindow', "toolbar=no,scrollbars=yes,resizable=yes,top=0,left=170, width=750,height=680");
	}
	
</script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">

<input type="hidden" name="salesBackContractId" id="salesBackContractId" value="${salesBackContract.id}"/>
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 销售管理 &gt;&gt 销售退货合同管理 &gt;&gt; 销售退货合同查看</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td><br />
      <div class="div_tiao"> &nbsp;&nbsp;销售合同信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
          <tr>
				<td class="td_01" width="20%" height="18px">签订日期</td>
          		<td class="td_02" width="30%">${salesBackContract.signDate}&nbsp;</td>
          		<td class="td_01" width="20%">产品分类名称</td>
          		<td class="td_02" width="30%">${salesBackContract.productTypeName}&nbsp;</td>
          </tr>
          <tr>
          	<td class="td_01" width="20%" height="18px">产品合同号</td>
          	<td class="td_02" width="30%">${salesBackContract.productContractCode}&nbsp;</td>
          	<td class="td_01" width="20%">公司合同号</td>
          	<td class="td_02" width="30%">${salesBackContract.companyContractCode}&nbsp;</td>
          	</tr>
          <tr>
				<td class="td_01" width="20%" height="18px">合同金额</td>
          		<td class="td_02" width="30%">${salesBackContract.contractMoney}&nbsp;元</td>
          		<td class="td_01" width="20%">预收金额</td>
          		<td class="td_02" width="30%">${salesBackContract.advanceMoney}&nbsp;元</td>
          </tr>
      </table>
	  <br />
      <div class="div_tiao"> &nbsp;&nbsp;客户信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
          <tr>
				<td class="td_01" width="20%" height="18px">客户名称</td>
          		<td class="td_02" width="30%">${salesBackContract.customerName}&nbsp;</td>
          		<td class="td_01" width="20%">联系人</td>
          		<td class="td_02" width="30%">${salesBackContract.linkman}&nbsp;</td>
          </tr>
          <tr>
          	<td class="td_01" width="20%" height="18px">省份</td>
          	<td class="td_02" width="30%">${salesBackContract.customerProvince}&nbsp;</td>
          	<td class="td_01" width="20%">电话</td>
          	<td class="td_02" width="30%">${salesBackContract.linkmanTel}&nbsp;</td>
          	</tr>
          <tr>
          	<td class="td_01" width="20%" height="18px">城市</td>
          	<td class="td_02" width="30%">${salesBackContract.customerCity}&nbsp;</td>
          	<td class="td_01" width="20%">传真</td>
          	<td class="td_02" width="30%">${salesBackContract.linkmanFax}&nbsp;</td>
          	</tr>
          <tr>
          	<td class="td_01" width="20%" height="18px">开户行</td>
          	<td class="td_02" width="30%"  >${salesBackContract.invoiceBankName}&nbsp;</td>
          	<td class="td_01" width="20%">帐号</td>
          	<td class="td_02" width="30%">${salesBackContract.invoiceBankAccount}&nbsp;</td>
          	</tr>
          <tr>
				<td class="td_01" width="20%"height="18px">税号</td>
          		<td class="td_02" width="30%">${salesBackContract.customerTaxNumber}&nbsp;</td>
          		<td class="td_01" width="20%">发票类型</td>
          		<td class="td_02" width="30%">${salesBackContract.invoiceType}&nbsp;</td>
          </tr>
      </table>
	  <br />
      <div class="div_tiao"> &nbsp;&nbsp;退货合同信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
          <tr>
				<td class="td_01" width="20%" height="18px">模版类型</td>
          		<td class="td_02" width="30%">
          		
          		<c:if test="${salesBackContract.templateType==0}">标准模板</c:if>
				<c:if test="${salesBackContract.templateType==1}">自定义模板</c:if>
				<c:if test="${salesBackContract.templateType==2}">非模板</c:if>&nbsp;</td>
          		 <td class="td_01" width="20%">
          		 <c:if test="${salesBackContract.templateType==2}">
          		 	文件
          		 </c:if>
          		 &nbsp;</td>
          		<td class="td_02" width="30%">
          		
          		<c:if test="${salesBackContract.templateType==2}">
          			<a href="${ctx}/downloadSalesBackContractFile.do?fileName=${salesBackContract.file}&salesBackContractId=${salesBackContract.id}">
          			<img src="${ctx}/images/btnXiaZai.gif" width="65" height="20" /></a>
          		</c:if>
          		&nbsp;</td>
          </tr>
          <tr>
          	<td class="td_01" width="20%" height="18px">预计退货时间</td>
          	<td class="td_02" width="30%">${salesBackContract.date}&nbsp;</td>
          	<td class="td_01" width="20%">签订地点</td>
          	<td class="td_02" width="30%">${salesBackContract.place}&nbsp;</td>
          </tr>
      </table>
   <br/>
	 <div class="div_tiao"> &nbsp;&nbsp;产品信息 </div>
      <div style="width:100%; text-align:right">单位：元</div>
   	  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table" style="border-left:1px solid #FFFFFF;">
			<tr>
				<th nowrap="nowrap" width="35" style="border-left:1px solid #c2c2c2;">序号</th>
				<th nowrap="nowrap">产品编码</th>
				<th nowrap="nowrap">产品名称</th>
				<th nowrap="nowrap">规格型号</th>
				<th nowrap="nowrap">单位</th>
				<th nowrap="nowrap">销售数</th>
				<th nowrap="nowrap">销售单价</th>
				<th nowrap="nowrap">销售金额</th>
				<th nowrap="nowrap">发货金额</th>
				<th nowrap="nowrap">备货金额</th>
				<th nowrap="nowrap">指定金额</th>
				<th nowrap="nowrap">开票金额</th>
				<th nowrap="nowrap">已退货合同数</th>
				<th nowrap="nowrap">退货数</th>			
				<th nowrap="nowrap">退货合同数</th>
				<th nowrap="nowrap">退货合同金额</th>
			</tr>
	<c:forEach var="saleBackContractDetail" items="${salesBackContractDetailList}" varStatus="amount">
			<tr>
				<td nowrap="nowrap" style="border-left:1px solid #c2c2c2;" height="18">${amount.count}</td>
				<td nowrap="nowrap" >${saleBackContractDetail.productCode}&nbsp;</td>
				<td nowrap="nowrap" >${saleBackContractDetail.productName}&nbsp;</td>
				<td nowrap="nowrap" >${saleBackContractDetail.productType}&nbsp;</td>
				<td nowrap="nowrap" >${saleBackContractDetail.productUnit}&nbsp;</td>
				<td nowrap="nowrap" >${saleBackContractDetail.sellCount}&nbsp;</td>
				<td nowrap="nowrap" >				
				<fmt:formatNumber value="${saleBackContractDetail.sellPrice}" type="number" pattern="#,##0.00" />
				&nbsp;</td>
				<td nowrap="nowrap" style=" text-align:right; ">
				<fmt:formatNumber value="${saleBackContractDetail.sellMoney}" type="number" pattern="#,##0.00" />&nbsp;</td>				
				<td nowrap="nowrap" style=" text-align:right; ">
				<fmt:formatNumber value="${saleBackContractDetail.sendGoodsMoney}" type="number" pattern="#,##0.00" />&nbsp;</td>
				<td nowrap="nowrap" style=" text-align:right; ">
					<fmt:formatNumber value="${saleBackContractDetail.preparedGoodsMoney}" type="number" pattern="#,##0.00" />&nbsp;</td>
				<td nowrap="nowrap" style=" text-align:right; ">
					<fmt:formatNumber value="${saleBackContractDetail.appointMoney}" type="number" pattern="#,##0.00" />&nbsp;</td>
				<td nowrap="nowrap" style=" text-align:right;">
					<fmt:formatNumber value="${saleBackContractDetail.makeInvoiceMoney}" type="number" pattern="#,##0.00" />&nbsp;</td>
				<td nowrap="nowrap" >
				${saleBackContractDetail.backedContractCount}&nbsp;</td>
				<td nowrap="nowrap" >
				${saleBackContractDetail.backCount}&nbsp;</td>
				<td nowrap="nowrap" width="62px">
				${saleBackContractDetail.backContractCount}&nbsp;</td>				
				<td nowrap="nowrap" style=" text-align:right;">
					<fmt:formatNumber value="${saleBackContractDetail.backContractMoney}" type="number" pattern="#,##0.00" />
				&nbsp;</td>
			
			</tr>
	</c:forEach>
			<tr>
				<td style=" border:1px solid #FFFFFF; background-color:#FFFFFF">&nbsp;</td>
				<td colspan="10" nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF">&nbsp;</td>
				<td  nowrap="nowrap" style=" border:1px solid #FFFFFF;  background-color:#FFFFFF; text-align:right">&nbsp;</td>
				<td  nowrap="nowrap" style=" border:1px solid #FFFFFF;  background-color:#FFFFFF; text-align:right">&nbsp;</td>
				<td  nowrap="nowrap" style=" border:1px solid #FFFFFF;  background-color:#FFFFFF; text-align:right">&nbsp;</td>
				<td  nowrap="nowrap" style=" border:1px solid #FFFFFF;  background-color:#FFFFFF; text-align:right">退货合同金额合计</td>
				<td  nowrap="nowrap" style=" border:1px solid #FFFFFF;  background-color:#FFFFFF; text-align:right">
				
				<fmt:formatNumber value="${salesBackContract.money}" type="number"
													pattern="#,##0.00" />元</td>
			</tr>
		</table>
      <div class="div_tiao"> &nbsp;&nbsp;退货退款信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
          <tr>
          	<td class="td_03" width="50%" height="18px">退货退款方式选择</td>
          	<td class="td_04">
          	<c:if test="${salesBackContract.backWay ==1}">先退货后退款 </c:if>
          	<c:if test="${salesBackContract.backWay ==2}">先退款后退货</c:if>
          	<c:if test="${salesBackContract.backWay ==3}">已供货未付款</c:if>
          	<c:if test="${salesBackContract.backWay ==4}">未供货</c:if>
          	&nbsp;</td>
          	</tr>
      </table>
	  <br/>
        <div class="div_tiao"> &nbsp;&nbsp;发货信息 </div>
       	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
          <tr>
          	<td class="td_01" width="20%" height="18px">名称</td>
          	<td colspan="3" class="td_02">${salesBackContract.stockRoomName}&nbsp;</td>
          	</tr>
          <tr>
            <td class="td_01" width="20%" height="18px">货物接收单位名称</td>
            <td colspan="3" class="td_02">${salesBackContract.receiverUnitName}&nbsp;</td>
        </tr>
          <tr>
            <td class="td_01" width="20%" height="18px">收货地址</td>
            <td colspan="3" class="td_02">${salesBackContract.receiverAddress}&nbsp;</td>
        </tr>
        <tr>
            <td class="td_01" width="20%" height="18px">联系人</td>
            <td class="td_02" width="30%" >${salesBackContract.receiverLinkman}&nbsp;</td>
            <td class="td_01" width="20%">邮编</td>
            <td class="td_02" width="30%" >${salesBackContract.receiverPostcode}&nbsp;</td>
        </tr>
          <tr>
            <td class="td_01" width="20%" height="18px">电话</td>
            <td class="td_02" width="30%" >${salesBackContract.receiverTel}&nbsp;</td>
            <td class="td_01" width="20%">手机</td>
            <td class="td_02" width="30%" >${salesBackContract.receiverMobile}&nbsp;</td>
          </tr>
    </table>
   	 <br/>
			<div class="div_tiao"> &nbsp;&nbsp;退货原因说明</div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
				<tr>
					<td class="td_03" width="30%">退货原因说明</td>
					<td class="td_04" style="padding:5px 100px 5px 5px">${tf:replaceBr(salesBackContract.text)}&nbsp;</td>
				</tr>
			</table>
			<br>
		<div class="div_tiao"> &nbsp;&nbsp;评审意见</div>
		
   <table  id="tableAssessIdea" border="0" cellpadding="0" cellspacing="0" align="center"	width="460">
								
				<c:if test="${salesBackContract.templateType != '0'}">		
						<tr	>
							<td height="20px" colspan="2">
								法务专员评审意见：
							</td>
						</tr>
						<tr 	>
							<td>
								法律法规是否符合
							</td>
							<td height="20px">
								<input type="checkbox"   id="radio4" value="1"
									<c:if test='${salesBackContract.legalIdea == "1"}'>checked="checked"</c:if> />
								符合 &nbsp;&nbsp;
								<input type="checkbox"   id="radio4" value="0"
									<c:if test='${salesBackContract.legalIdea == "0"}'>checked="checked"</c:if> />
								不符合
							</td>
						</tr>
						<tr>
							<td colspan="2" valign="top">
								<table width="98%" border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap>
											补充说明：
										</td>
										<td style="padding:5px 0 5px 0;"  width="330px">
											${tf:replaceBr(salesBackContract.legalText)}
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr >
							<td height="20px">
								签字：&nbsp; ${salesBackContract.legalName}
							</td>
							<td>
								日期：&nbsp; ${salesBackContract.legalDate}
							</td>
						</tr>
						<tr>
							<td height="20px" colspan="2">
								&nbsp;
							</td>
						</tr>
				</c:if>	
					<tr >
							<td height="20px" colspan="2">
								区域总监评审意见：
							</td>
						</tr>
						<tr >
							<td>
								1.退货数量是否符合
							</td>
							<td height="20px" width="150">
								<input type="checkbox"   id="radio" value="1"
									<c:if test='${salesBackContract.areaMajIdea1 == "1"}'>checked="checked"</c:if> />
								符合 &nbsp;&nbsp;
								<input type="checkbox"   id="radio" value="0"
									<c:if test='${salesBackContract.areaMajIdea1 == "0"}'>checked="checked"</c:if> />
								不符合
							</td>
						</tr>
						<tr >
							<td>
								2.退货退款方式是否符合
							</td>
							<td height="20px">
								<input type="checkbox"   id="radio2" value="1"
									<c:if test='${salesBackContract.areaMajIdea2 == "1"}'>checked="checked"</c:if> />
								符合 &nbsp;&nbsp;
								<input type="checkbox"   id="radio2" value="0"
									<c:if test='${salesBackContract.areaMajIdea2 == "0"}'>checked="checked"</c:if> />
								不符合
							</td>
						</tr>
						<tr >
							<td>
								3.退货地址是否符合
							</td>
							<td height="20px"> 
								<input type="checkbox"   id="radio5" value="1"
									<c:if test='${salesBackContract.areaMajIdea3 == "1"}'>checked="checked"</c:if> />
								符合 &nbsp;&nbsp;
								<input type="checkbox"   id="radio5" value="0"
									<c:if test='${salesBackContract.areaMajIdea3 == "0"}'>checked="checked"</c:if> />
								不符合
							</td>
						</tr>
						
						<tr>
							<td colspan="2" valign="top">
								<table width="98%" border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap>
											补充说明：
										</td>
										<td style="padding:5px 0 5px 0;" name="tdSellMaj" width="339px">
											${tf:replaceBr(salesBackContract.areaMajText)}
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td height="20px">
								签字：&nbsp; ${salesBackContract.areaMajName}
							</td>
							<td>
								日期：&nbsp; ${salesBackContract.areaMajDate}
							</td>
						</tr>
						<tr>
							<td height="20px" colspan="2">
								&nbsp;
							</td>
						</tr>
						<tr >
							<td height="20px" colspan="2">
								销售总监评审意见：
							</td>
						</tr>
						<tr >
							<td>
								1.退货数量是否符合
							</td>
							<td height="20px" width="150">
								<input type="checkbox"   id="radio" value="1"
									<c:if test='${salesBackContract.sellMajIdea1 == "1"}'>checked="checked"</c:if> />
								符合 &nbsp;&nbsp;
								<input type="checkbox"   id="radio" value="0"
									<c:if test='${salesBackContract.sellMajIdea1 == "0"}'>checked="checked"</c:if> />
								不符合
							</td>
						</tr>
						<tr >
							<td>
								2.退货退款方式是否符合
							</td>
							<td height="20px">
								<input type="checkbox"   id="radio2" value="1"
									<c:if test='${salesBackContract.sellMajIdea2 == "1"}'>checked="checked"</c:if> />
								符合 &nbsp;&nbsp;
								<input type="checkbox"   id="radio2" value="0"
									<c:if test='${salesBackContract.sellMajIdea2 == "0"}'>checked="checked"</c:if> />
								不符合
							</td>
						</tr>
						<tr >
							<td>
								3.退货地址是否符合
							</td>
							<td height="20px"> 
								<input type="checkbox"   id="radio5" value="1"
									<c:if test='${salesBackContract.sellMajIdea3 == "1"}'>checked="checked"</c:if> />
								符合 &nbsp;&nbsp;
								<input type="checkbox"   id="radio5" value="0"
									<c:if test='${salesBackContract.sellMajIdea3 == "0"}'>checked="checked"</c:if> />
								不符合
							</td>
						</tr>
						
						<tr>
							<td colspan="2" valign="top">
								<table width="98%" border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap>
											补充说明：
										</td>
										<td style="padding:5px 0 5px 0;" name="tdSellMaj" width="339px">
											${tf:replaceBr(salesBackContract.sellMajText)}
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td height="20px">
								签字：&nbsp; ${salesBackContract.sellMajName}
							</td>
							<td>
								日期：&nbsp; ${salesBackContract.sellMajDate}
							</td>
						</tr>
						<tr>
							<td height="20px" colspan="2">
								&nbsp;
							</td>
						</tr>
						<tr>
							<td height="20px" colspan="2">
								运营总监评审意见：
							</td>
						</tr>
						<tr name="trOpeMaj">
							<td>
								&nbsp;
							</td>
							<td height="20px">
								<input type="checkbox"   id="radio4" value="1"
									<c:if test='${salesBackContract.opeMajIdea == "1"}'>checked="checked"</c:if> />
								同意 &nbsp;&nbsp;
								<input type="checkbox"   id="radio4" value="0"
									<c:if test='${salesBackContract.opeMajIdea == "0"}'>checked="checked"</c:if> />
								不同意
							</td>
						</tr>
						<tr>
							<td colspan="2" valign="top">
								<table width="98%" border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap>
											补充说明：
										</td>
										<td style="padding:5px 0 5px 0;" name="tdOpeMaj" width="339px">
											${tf:replaceBr(salesBackContract.opeMajText)}
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td height="20px">
								签字：&nbsp; ${salesBackContract.opeMajName} 
							</td>
							<td>
								日期：&nbsp; ${salesBackContract.opeMajDate} 
							</td>
						</tr>
					</table>
				
    </td>
    <td >&nbsp;</td>
  </tr>
  <tr>
    <td></td>
    <td height="50px" align="center" valign="bottom">
    	
    	<c:if test="${salesBackContract.templateType != 2}">
    		<a href="#">
    			<img src="${ctx}/images/btnYuLan.gif" width="65" height="20" onclick="previewSaleBackContract()"/></a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    	</c:if>
    	
    	<a href="${ctx}/salesBackContractManage.do?init=true">
    		<img src="${ctx}/images/btnBack.gif" />
    	</a>
    	</td>
    <td></td>
  </tr>
</table>
<br/><br/>
</body>
</html>
