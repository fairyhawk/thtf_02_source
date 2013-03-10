<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@taglib prefix="tf" uri="localhost" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>开票确认</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
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
				if($("#table3")){
					$("#table3 tr:even").addClass("treven");
					$("#table3 tr").each(function(i){
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
<script type="text/javascript">
function bodyLoad(){
		if($("#table3 tr").length==2){$("#sumMoney").text("0")}
		else{
		var makeInvoiceSum=0;
			$("#table3 tr").each(function(i){
				if(i!=0 && i!=$("#table3 tr").length-1){
					makeInvoiceSum=FloatAdd(makeInvoiceSum,$("#"+$(this).attr("id")+" td:nth-child(17)").text());
				}
			});
			$("#sumMoney").text(formatMoney(makeInvoiceSum.toFixed(2)));
		}
}
function delSellInvoiceClick(){

//if(confirm('确认删除该发票及该发票关联的所有明细数据？')){return true;}else{return false;}
if(confirm('确认删除该发票及该发票关联的所有明细数据？')){return true;}else{return false;}
}
function formatMoney(s, n)
{
   n = n > 0 && n <= 20 ? n : 2;
   s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
   var l = s.split(".")[0].split("").reverse(),
   r = s.split(".")[1];
   t = "";
   for(i = 0; i < l.length; i ++ )
   {
      t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
   }
   return t.split("").reverse().join("") + "." + r;
}
</script>
<body id="thisBody" onLoad="bodyLoad()">
<form action="" method="post" id="decidrForm">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center" style="font-size:12px"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 销售管理 &gt;&gt 开票管理 &gt;&gt; 开票确认</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td><br />
      <div class="div_tiao" style="font-size:13px"> &nbsp;&nbsp;开票申请信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3" >
          <tr>
            <td class="td_01" width="20%" height="18px">申请单号</td>
            <td class="td_02" width="30%">${customerInfo.id }</td>
            <td class="td_01" width="20%">发票类型</td>
            <td class="td_02" id="invoiceTypeName" width="30%">
            	<c:if test="${customerInfo.invoiceType==0 }">普通</c:if>
            	<c:if test="${customerInfo.invoiceType==1 }">增值税</c:if>
            &nbsp;</td>
          </tr>
          <tr>
            <td class="td_01" height="18px">客户名称</td>
            <td class="td_02" >${customerInfo.customerName }&nbsp;</td>
            <td class="td_01">税号</td>
            <td class="td_02" id="taxNumber">${customerInfo.taxNumber }&nbsp;</td>
          </tr>
          <tr> 
            <td class="td_01">开户行</td>
            <td class="td_02" id="invoiceBankName">${customerInfo.invoiceBankName }&nbsp;</td>
            <td class="td_01">帐号</td>
            <td class="td_02" id="invoiceBankAccount">${customerInfo.invoiceBankAccount }&nbsp;</td>
          </tr>
          <tr>
            <td class="td_01" height="18px">地址</td>
            <td class="td_02" id="invoiceBankAddress">${customerInfo.invoiceBankAddress}&nbsp;</td>
            <td class="td_01">电话</td>
            <td class="td_02" id="invoiceBankTel">${customerInfo.invoiceBankTel }&nbsp;</td>
          </tr>
	          <tr>
	            <td class="td_01" height="18px">申请人</td>
	            <td class="td_02" >${customerInfo.userName }&nbsp;</td>
	            <td class="td_01">申请日期</td>
	            <td class="td_02">${customerInfo.date}</td>
	          </tr>
	        <tr>
	            <td class="td_01" height="18px">申请金额</td>
	            <td class="td_02" ><fmt:formatNumber value="${customerInfo.money }" type="number" pattern="#,##0.00" />&nbsp;</td>
	            <td class="td_01">&nbsp;</td>
	            <td class="td_02">&nbsp;</td>
	          </tr>
      </table>
   <br/>
	 <div class="div_tiao" style="font-size:13px"> &nbsp;&nbsp;产品信息 </div>
   	  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3" >
            <tr>
              <td height="18" class="td_03" width="50%">产品分类名称</td>
                <td class="td_04">${customerInfo.productName }</td>
            </tr>
   	  </table>
   	  <c:if test="${param.passError}"><FONT color="red" style="font-size:14px">操作有误！</font></c:if>  
   	  <c:if test="${param.delError}"><FONT color="red" style="font-size:14px">删除失败！</font></c:if> 
   	      
   	  <div style="width:100%; text-align:right;font-size:12px">单位：元</div>
   	    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table3" style="border-left:1px solid #FFFFFF;">
			<tr>
				<th nowrap="nowrap" width="35" style="border-left:1px solid #c2c2c2;">序号</th>
				<th nowrap="nowrap">发票号</th>
				<th nowrap="nowrap">发货单号</th>
				<th nowrap="nowrap">产品合同号</th>
				<th nowrap="nowrap">公司合同号</th>
				<th nowrap="nowrap">产品编码</th>
				<th nowrap="nowrap">产品名称</th>
				<th nowrap="nowrap">规格型号</th>
				<th nowrap="nowrap">单位</th>
				<th nowrap="nowrap">销售单价</th>
				<th nowrap="nowrap">发货数</th>
				<th nowrap="nowrap">指定金额</th>
				<th nowrap="nowrap">开票数</th>
				<th nowrap="nowrap">开票金额</th>
				<th nowrap="nowrap">退货金额</th>
				<th nowrap="nowrap" width="114">&nbsp;操作&nbsp;</th>
			</tr>
			<logic:iterate id="sellInvoiceList" indexId="index" name="sellInvoiceList">	
			<tr id="tr${index+1}"> 
				<td nowrap="nowrap" style="border-left:1px solid #c2c2c2;padding-right:5px;text-align:right" height="18px">${index+1}</td>
				<td nowrap="nowrap" >${sellInvoiceList.number}</td>
				<td nowrap="nowrap" >${sellInvoiceList.sendGoodsId }&nbsp;</td>
				<td nowrap="nowrap" >${sellInvoiceList.productContractNum }&nbsp;</td>
				<td nowrap="nowrap" >${sellInvoiceList.companyContractNum }&nbsp;</td>
				<td nowrap="nowrap" >${sellInvoiceList.productCode }&nbsp;</td>
				<td nowrap="nowrap" >${sellInvoiceList.productName }&nbsp;</td>
				<td nowrap="nowrap" >${sellInvoiceList.specModel }&nbsp;</td>
				<td nowrap="nowrap" >${sellInvoiceList.unit }&nbsp;</td>
				<td nowrap="nowrap" style="padding-right:5px;text-align:right"><fmt:formatNumber value="${sellInvoiceList.sellPrice} " type="number" pattern="#,##0.00" /></td>
				<td nowrap="nowrap" style="padding-right:5px;text-align:right">${sellInvoiceList.sendCount }</td>
				<td nowrap="nowrap" style=" text-align:right; "><fmt:formatNumber value="${sellInvoiceList.money} " type="number" pattern="#,##0.00" />&nbsp;</td>
				<td nowrap="nowrap" style="padding-right:5px;text-align:right">${sellInvoiceList.invoiceCount }</td>
				<td nowrap="nowrap" style=" text-align:right; "><fmt:formatNumber value="${sellInvoiceList.invoiceMoney} " type="number" pattern="#,##0.00" />&nbsp;</td>
				<td nowrap="nowrap" style=" text-align:right;"><fmt:formatNumber value="${sellInvoiceList.returnMoney }" type="number" pattern="#,##0.00" />&nbsp;</td>
				<td nowrap="nowrap" width="114">
					<a href="getSellInvoiceList.do?sellInvoiceId=${sellInvoiceList.id }&flag=1&back=true">查看发票</a>
					<c:choose>
						<c:when test="${sellInvoiceList.status==1 || sellInvoiceList.status==3 || sellInvoiceList.status==5}">
							<html:link styleId="delSellInvoice" onclick="return delSellInvoiceClick()" title="删除发票" action="delSellInvoiceOfInvoice.do?mid=${customerInfo.id  }&SIId=${sellInvoiceList.id }&SIDId=${sellInvoiceList.sellInvoiceDetailId }" >删除发票</html:link>
						</c:when>
						<c:otherwise>删除发票</c:otherwise>
					</c:choose>
					<c:if test=""></c:if>
					
				</td>
				<td nowrap="nowrap" style="display:none">${sellInvoiceList.invoiceMoney}</td>
			</tr>
			</logic:iterate>
			<tr>
				<td colspan="2" style=" border:1px solid #FFFFFF; background-color:#FFFFFF;padding-left:0px"><a href="${ctx }/getSellInvoice.do?id=${customerInfo.id }" ><img src="${ctx}/images/btnAddFP.gif" width="88" height="20" /></a>
				</td>
				<td colspan="12" nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF"> 发票金额合计 </td>
				<td nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF" id="sumMoney"></td>
				<td  nowrap="nowrap" style=" text-align:left; border:1px solid #FFFFFF; background-color:#FFFFFF">元</td>
			</tr>
		</table>
   	  <br/>
			<div class="div_tiao" style="font-size:13px"> &nbsp;&nbsp;特殊说明</div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
				<tr>
					<td class="td_03" width="20%">特殊说明</td>
					<td class="td_04" style="padding:5px 100px 5px 5px">${tf:replaceBr(customerInfo.text)}&nbsp;
					</td>
				</tr>
			</table>
            
    </td>
    <td >&nbsp;</td>
  </tr>
  <tr>
    <td></td>
    <td height="50px" align="center" valign="bottom">
    <a href="${ctx }/getInvoiceList.do"><img src="${ctx}/images/btnBack.gif" /></a></td>
    <td></td>
  </tr>
</table>
<br />
</form>
</body>
</html>
