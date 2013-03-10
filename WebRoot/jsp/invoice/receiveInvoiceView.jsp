<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@taglib prefix="tf" uri="localhost" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>采购发票查看</title>
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
        <script type="text/javascript" src="${ctx}/js/Calendar1.js"></script>
		<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
		<script type="text/javascript" src="${ctx}/js/common/contractUtil.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="${ctx}/js/messages_cn.js"></script>
<style type="text/css">
<!--
.STYLE1 {color: #FF0000}
-->
</style>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 采购管理 &gt;&gt; 采购发票管理 &gt;&gt; 采购发票查看</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td><br />
      <div class="div_tiao"> &nbsp;&nbsp;采购发票信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
			<tr>
				<td class="td_01" width="20%" height="18">产品分类名称</td>
			  <td class="td_02" width="30%">${dto.prodTypeName}&nbsp;</td>
				<td class="td_01" width="20%">供货商名称</td>
			  <td class="td_02" width="30%">${dto.supplierName}&nbsp;</td>
			</tr>
			<tr>
				<td class="td_01" height="18">收票日期</td>
				<td class="td_02">${dto.receiveDate}&nbsp;</td>
				<td class="td_01">发票金额</td>
			  <td class="td_02">${dto.invoiceMoney}&nbsp;元</td>
			</tr>
			<tr>
				<td class="td_01" height="18">发票类型</td>
				  <c:if test="${dto.invoiceType==0}">
				  <td class="td_02">普通&nbsp;</td>
				  </c:if>
				  <c:if test="${dto.invoiceType==1}">
				  <td class="td_02">增值税&nbsp;</td>
				  </c:if>
				<td class="td_01">发票号</td>
			  <td class="td_02">${dto.invoiceNo}&nbsp;</td>
			</tr>
			 <c:if test="${dto.invoiceType==1}">
			<tr>
			  <td class="td_01" height="18">税率</td>
			  <td class="td_02">${dto.taxRate}%</td>
			  <td class="td_01">&nbsp;</td>
			  <td class="td_02">&nbsp;</td>
	    </tr>
	      </c:if>
			<tr>
				<td class="td_01" height="18">发票状态</td>
				  <c:if test="${dto.status==1}">
				  <td class="td_02">已收票&nbsp;</td>
				  </c:if>
				  <c:if test="${dto.status==2}">
				  <td class="td_02">已退票&nbsp;</td>
				  </c:if>
				<td class="td_01">退票日期</td>
				<td class="td_02">${dto.backDate}&nbsp;</td>
			</tr>
			<tr>
				<td class="td_01" height="18">特殊说明</td>
				<td colspan="3" class="td_02">${tf:replaceBr(dto.text)}&nbsp;</td>
			</tr>
		</table>
      <br/>
	 <div class="div_tiao"> &nbsp;&nbsp;产品信息 </div>
      <div style="width:100%; text-align:right">单位：元</div>
   	  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table" style="border-left:1px solid #FFFFFF;">
			<tr>
				<th nowrap="nowrap" style="border-left:1px solid #c2c2c2;">入库单号</th>
				<th nowrap="nowrap">产品合同号</th>
				<th nowrap="nowrap">公司合同号</th>
				<th nowrap="nowrap">产品编码</th>
				<th nowrap="nowrap">产品名称</th>
				<th nowrap="nowrap">规格型号</th>
				<th nowrap="nowrap">单位</th>
				<th nowrap="nowrap">采购单价 </th>
				<th nowrap="nowrap">入库数</th>
				<th nowrap="nowrap">指定金额</th>
				<th nowrap="nowrap">已收票数</th>
				<th nowrap="nowrap">收票数</th>
				<th nowrap="nowrap">收票金额</th>
				<th nowrap="nowrap">返厂金额</th>
			</tr>

        <bean:define id="total" value="0" type="java.lang.String"/>
        <logic:iterate id="list" name="list">
        <bean:define id="total" value="${list.receiveInvoiceMoney+total}" type="java.lang.String"/>
			  <tr>
				<td nowrap="nowrap" style="border-left:1px solid #c2c2c2;" height="18">${list.inStockId}&nbsp;</td>
				<td nowrap="nowrap" >${list.prodContractCode}&nbsp;</td>
				<td nowrap="nowrap" >${list.compContractCode}&nbsp;</td>
				<td nowrap="nowrap" >${list.prodCode}&nbsp;</td>
				<td nowrap="nowrap" >${list.prodName}&nbsp;</td>
				<td nowrap="nowrap" >${list.prodType}&nbsp;</td>
				<td nowrap="nowrap" >${list.prodUnit}&nbsp;</td>
				<td nowrap="nowrap" style="text-align:right;"><fmt:formatNumber value="${list.buyUnitPrice}" pattern="#,##0.00000"/>&nbsp;</td>
				<td nowrap="nowrap" style="text-align:right;">${list.inStockCount}&nbsp;</td>
				<td nowrap="nowrap" style=" text-align:right; "><fmt:formatNumber value="${list.appointMoney}" pattern="#,##0.00000"/>&nbsp;</td>
				<td nowrap="nowrap" style="text-align:right;">${list.receivedInvoiceCount}&nbsp;</td>
				<td nowrap="nowrap" style="text-align:right;" >${list.receiveInvoiceCount}&nbsp;</td>
				<td nowrap="nowrap" style=" text-align:right; "><fmt:formatNumber value="${list.receiveInvoiceMoney}" pattern="#,##0.00000"/>&nbsp;</td>
				<td nowrap="nowrap" style=" text-align:right; "><fmt:formatNumber value="${list.backGoodsMoney}" pattern="#,##0.00000"/>&nbsp;</td>
			  </tr>
		  </logic:iterate>
			<tr>
				<td style=" border:1px solid #FFFFFF; background-color:#FFFFFF">&nbsp;</td>
				<td colspan="10" nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF">&nbsp;</td>
				<td  nowrap="nowrap" style=" border:1px solid #FFFFFF;  background-color:#FFFFFF; text-align:right">收票金额合计</td>
				<td  nowrap="nowrap" style=" border:1px solid #FFFFFF;  background-color:#FFFFFF; text-align:right"><fmt:formatNumber value="${total}" pattern="#,##0.00000"/>&nbsp;</td>
				<td  nowrap="nowrap" style=" border:1px solid #FFFFFF;  background-color:#FFFFFF; text-align:left">元</td>
			</tr>
		</table>
   	
    </td>
    <td >&nbsp;</td>
  </tr>
  <tr>
    <td></td>
    <td height="50px" align="center" valign="bottom"><a href="${ctx}/getReceiveInvoiceList.do"><img src="${ctx}/images/btnBack.gif" /></a></td>
    <td></td>
  </tr>
</table>
<br />
</body>
</html>
