<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"	contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@taglib prefix="tf" uri="localhost" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>开票确认</title>
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

 
</head>
<body>
<html:form action="K3ConfirmInvoice.do?invoiceId=${invoice.id}">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 销售管理 &gt;&gt 开票管理 &gt;&gt; 开票确认</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td><br />
      <div class="div_tiao"> &nbsp;&nbsp;开票申请信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
          <tr>
				<td class="td_01" width="20%" height="18px">申请单号</td>
          		<td class="td_02" width="30%">${invoice.id}&nbsp;</td>
          		<td class="td_01" width="20%">发票类型</td>
          		<td class="td_02" width="30%">
          				<c:if test="${invoice.invoiceType == 0}">
							普通
						</c:if>
						
						<c:if test="${invoice.invoiceType == 1}">
							增值税发票
						</c:if>&nbsp;
          		</td>
          </tr>
          <tr>
				<td class="td_01" height="18px">客户名称</td>
          		<td class="td_02">${invoice.customerName}&nbsp;</td>
          		<td class="td_01">税号</td>
          		<td class="td_02">${invoice.taxNumber}&nbsp;</td>
          </tr>
          <tr>
				<td class="td_01" height="18px">地址</td>
          		<td class="td_02">${invoice.invoiceBankAddress}&nbsp;</td>
          		<td class="td_01">电话</td>
          		<td class="td_02">${invoice.invoiceBankTel}&nbsp;</td>
          </tr>
          <tr>
				<td class="td_01" height="18px">开户行</td>
          		<td class="td_02">${invoice.invoiceBankName}&nbsp;</td>
          		<td class="td_01">帐号</td>
          		<td class="td_02">${invoice.invoiceBankAccount}&nbsp;</td>
          		
          </tr>
          <tr>
				<td class="td_01" height="18px">申请人</td>
          		<td class="td_02">${invoice.userName}&nbsp;</td>
          		<td class="td_01">申请日期</td>
          		<td class="td_02">${invoice.date}&nbsp;</td>
          </tr>
          
          <tr>
				<td class="td_01" height="18px">通知人</td>
          		<td class="td_02">${invoice.notifyName}&nbsp;</td>
          		<td class="td_01">通知时间</td>
          		<td class="td_02">${invoice.notifyDate}&nbsp;</td>
          </tr>
          
      </table>
   <br/>
	 <div class="div_tiao"> &nbsp;&nbsp;产品信息 </div>
   	  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
            <tr>
              <td height="20" class="td_03" width="50%">产品分类名称</td>
                <td class="td_04">${invoice.productTypeName}&nbsp;
                <c:choose>
				 <c:when test="${invoice.productTypeId<10}">0${invoice.productTypeId}</c:when>  
                  <c:otherwise> ${invoice.productTypeId}</c:otherwise>
                 </c:choose>
               </td>
            </tr>
   	  </table>
<input type="hidden" id="invoiceId" value="${invoice.id}" name="invoiceId"/>
      <div style="width:100%; text-align:right">单位：元</div>
   	  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table" style="border-left:1px solid #FFFFFF;">
			<tr>
				<th nowrap="nowrap" width="35" style="border-left:1px solid #c2c2c2;" >序号</th>
				<th nowrap="nowrap">发货单号</th>
				<th nowrap="nowrap">产品合同号</th>
				<th nowrap="nowrap">产品编码</th>
				<th nowrap="nowrap">产品名称</th> 
				<th nowrap="nowrap">规格型号</th>
				<th nowrap="nowrap">单位</th>
				<th nowrap="nowrap">数量</th>
				<th nowrap="nowrap">销售单价</th>
				<th nowrap="nowrap">开票金额</th>
			</tr>
		<c:forEach var="invoiceDetail" items="${invoiceDetailList}" varStatus="detailCount">	
			<tr>
				<td nowrap="nowrap" style="border-left:1px solid #c2c2c2; text-align:right;padding-right:5px" height="18px">${detailCount.count}</td>
				<td nowrap="nowrap" >${invoiceDetail.sendGoodsId}&nbsp;</td>
				<td nowrap="nowrap" >${invoiceDetail.productContractCode}&nbsp;</td>
				<td nowrap="nowrap" >${invoiceDetail.productCode}&nbsp;</td>
				<td nowrap="nowrap" >${invoiceDetail.productName}&nbsp;</td>
				<td nowrap="nowrap" >${invoiceDetail.productType}&nbsp;</td>
				<td nowrap="nowrap" >${invoiceDetail.productUnit}&nbsp;</td>
				<td nowrap="nowrap" width="96px;" style="text-align:right;">${invoiceDetail.count}&nbsp;</td>
				<td nowrap="nowrap" style=" text-align:right; width:93px;">
					<fmt:formatNumber value="${invoiceDetail.price}" pattern="#,##0.0#"/>&nbsp;
				</td>
				<td nowrap="nowrap" style=" text-align:right; padding-right:12px; width:90px;">
					<fmt:formatNumber value="${invoiceDetail.money}" pattern="#,##0.0#"/>
				</td>
			</tr>
		</c:forEach>
			<tr>
				<td style=" border:1px solid #FFFFFF; background-color:#FFFFFF">&nbsp;</td>
				<td style=" border:1px solid #FFFFFF; background-color:#FFFFFF">&nbsp;</td>
				<td style=" border:1px solid #FFFFFF; background-color:#FFFFFF">&nbsp;</td>
				<td style=" border:1px solid #FFFFFF; background-color:#FFFFFF">&nbsp;</td>
				<td colspan="4" nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF">&nbsp;</td>
				<td nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF">开票金额合计</td>
				<td  nowrap="nowrap" style=" border:1px solid #FFFFFF;  background-color:#FFFFFF; text-align:right">
					<fmt:formatNumber value="${invoice.money}" pattern="#,##0.0#"/>元
				</td>
			</tr>
		</table>
   	 <br/>
			<div class="div_tiao"> &nbsp;&nbsp;特殊说明</div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
				<tr>
					<td class="td_03" width="20%">特殊说明</td>
					<td class="td_04" >${tf:replaceBr(invoice.text)}&nbsp;
					</td>
				</tr>
			</table>
			<div class="div_tiao"> &nbsp;&nbsp;补充说明</div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
				<tr>
					<td class="td_03" width="20%">开票专员补充说明</td>
					<td class="td_04" >
						<textarea rows="3" cols="100" id="text_k3" name="text_k3"></textarea>
					</td>
				</tr>
			</table>
    </td>
    <td >&nbsp;</td>
  </tr>
  <tr>
    <td></td>
    <td height="50px" align="center" valign="bottom">
    <a  href="#" onclick="submit()">
    <img src="${ctx}/images/btnConfirm2.gif" width="65" height="20" /></a>
 
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
    <a href="${ctx}/getInvoiceList.do">
    	<img src="${ctx}/images/btnBack.gif" />
    </a>
    </td>
    <td></td>
  </tr>
</table>
<br><br>
</html:form>
</body>

</html>
