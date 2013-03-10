<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@taglib prefix="tf" uri="localhost" %>
<html>
<head>
<title></title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
<script type="text/javascript" src="${ctx}/js/Calendar1.js"></script>
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
<script type="text/javascript">
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
$(document).ready(function(){
				if($("#table2")){
					$("#table2 tr:even").addClass("treven");
					$("#table2 tr").each(function(i){
						$(this).mouseover(function(){
							$(this).addClass("over");
						});
						$(this).mouseout(function(){
							$(this).removeClass("over");
						});
					});
				}
			});
function CheckAll()//判断全选
  {
     for (var i=0;i<document.forms[0].elements.length;i++)
     {
       document.forms[0].elements[i].checked=document.forms[0].checkAll.checked;
     }
     if(document.forms[0].checkAll.checked){
     	invoiceDetailClick();
     }else{
     	$("#makeInvoiceSum").text("0.00元");//重新取和
		$("#money").val("0");
     }
  }
 function invoiceDetailClick(){
  		var length=$("#table2 tr").length;//总行数
  		var makeInvoiceSum=0;
  		$("#table2 input:checked[name='invoiceDetail']").each(function(i){//input[name='invoiceDetail'][checked]
  			if(i!=length-1){
  				var trId=$(this).parent().parent().attr("id");
  				makeInvoiceSum=FloatAdd(makeInvoiceSum,$("#"+trId+" td:nth-child(12)").text());////重新取和
  			}
  		});
  		$("#makeInvoiceSum").text(formatMoney(makeInvoiceSum.toFixed(2),2)+"元");//重新取和
		$("#money").val(makeInvoiceSum.toFixed(2));
  	}
 $(document).ready(function(){
  	$("#addSellInvoiceSubmit").click(function(){
  	$("#makeInvoiceError").css("display","none");$("#date").attr("class","");
  	$("#dateError").css("display","none");$("#makeMoney").attr("class","");
  	$("#invoiceIdError").css("display","none");$("#invoiceId").attr("class","");
  		if($("#table2 input:checked[name='invoiceDetail']").length==0){//input[name='invoiceDetail'][checked]
  			alert("至少选择一张票据");
  			return;
  		}
  		if(parseInt($("#money").val())!=parseInt($("#makeMoney").val())){//判断金额是否相等
  		$("#makeMoney").focus().attr("class","invalid");
  			$("#makeInvoiceError").html("<font style='font-size:12px' color='red'>发票金额填写有误!</font>").css("display","");
  			return;
  		}
  		
  		if($.trim($("#invoiceId").val())==""){
  			$("#invoiceId").focus().attr("class","invalid");
  			$("#invoiceIdError").html("<font style='font-size:12px' color='red'>请填写发票号!</font>").css("display","");
  			return;
  			}
  		if($("#date").val()==""){
  			$("#date").focus().attr("class","invalid");
  			$("#dateError").html("<font style='font-size:12px' color='red'>请选择开票日期!</font>").css("display","");
  			return;
  		}
  		
  		if($("#remark").val().length>200){
  			alert("备注信息不能多于200个字符");
  			$("#remark").focus();
  			return;
  		}
  		document.getElementById("addSellInvoice").action=jsCtx+"/addSellInvoice.do";
	    document.forms[0].submit();
  		
  	});
  	 // $("#makeMoney").blur(function(){
  	//		if($("#makeMoney").val()==""){
  	//			$("#makeMoney").focus().attr("class","invalid");
  	//			$("#makeInvoiceError").html("<font style='font-size:12px' color='red'>请填写发票金额!</font>");
  	//		}
  	//	});
  });
	
</script>
</head>
<c:if test="${param.messageAddCountError}">
	<script>alert("无法添加发票！此发货单号的产品已经申请退货");</script>
</c:if>
<body onload="invoiceDetailClick()">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center" style="font-size:12px"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 销售管理 &gt;&gt 开票管理 &gt;&gt; 开票确认 &gt;&gt; 添加发票</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td>
 <html:form action="addSellInvoice" method="post" styleId="addSellInvoice">
 <br/>
 <div class="div_tiao" style="font-size:13px"> &nbsp;&nbsp;发票信息</div>
<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center">
   <tr>
    <td align="center"></td>
   </tr>
  <tr>
    <td align="center"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
		<tr>
			<td class="td_01" width="20%">开票日期</td>
			<td class="td_02" width="30%"><input type="text" name="date" id="date" style="width:120px;" readonly="readonly" onfocus="calendar()"/><div id="dateError"></div></td>
			<td class="td_01" width="20%">发票号</td>
			<td class="td_02" width="30%"><input type="text" name="invoiceId" id="invoiceId" style="width:120px;" /><div id="invoiceIdError"></div></td>
			</tr>
		<tr>
			<td class="td_01">发票金额</td>
			<td class="td_02"><input type="text" name="makeMoney" id="makeMoney" style="width:120px;" />
				元 <div id="makeInvoiceError"></div>
				</td>
            <td class="td_01">&nbsp;</td>
			<td class="td_02">&nbsp;</td>
			</tr>
	</table>
	<br />
		<c:if test="${param.messageError}"><div><font color="red" style="font-size:14px">您填写有误！</font></div></c:if>
		<c:if test="${param.messageAddError}"><div><font color="red" style="font-size:14px">发票添加失败！</font></div></c:if>
		<c:if test="${param.messageOfNumberError}"><div><font color="red" style="font-size:14px">此发票号已经存在！</font></div></c:if>
		
	 <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table2" style="border-left:1px solid #FFFFFF;">
			<tr>
				<th nowrap="nowrap" style="border-left:1px solid #c2c2c2;">选择</th>
				<th nowrap="nowrap">发货单号</th>
				<th nowrap="nowrap">产品合同号</th>
				<th nowrap="nowrap">公司合同号</th>
				<th nowrap="nowrap">产品编码</th>
				<th nowrap="nowrap">产品名称</th>
				<th nowrap="nowrap">规格型号</th>
				<th nowrap="nowrap">单位</th>
				<th nowrap="nowrap">销售单价</th>
				<th nowrap="nowrap">申请开票数</th>
				<th nowrap="nowrap">申请开票金额</th>
			</tr>
			<logic:iterate id="sendGoodsList" name="sendGoodsList" indexId="index">	
			<tr id="tr${sendGoodsList.makeInvoiceId }">
				<td nowrap="nowrap" style="border-left:1px solid #c2c2c2;">
					<input type="checkbox" name="invoiceDetail" id="invoiceDetail" checked="checked" onclick="invoiceDetailClick()" value="${sendGoodsList.makeInvoiceId }">
				</td>
				<td nowrap="nowrap" >${sendGoodsList.id }</td>
				<td nowrap="nowrap" >${sendGoodsList.productContractNum }&nbsp;</td>
				<td nowrap="nowrap" >${sendGoodsList.companyContractNum }&nbsp;</td>
				<td nowrap="nowrap" >${sendGoodsList.productCode }&nbsp;</td>
				<td nowrap="nowrap" >${sendGoodsList.productName }&nbsp;</td>
				<td nowrap="nowrap" >${sendGoodsList.specModel }&nbsp;</td>
				<td nowrap="nowrap" >${sendGoodsList.unit }&nbsp;</td>
				<td nowrap="nowrap" style="text-align:right"><fmt:formatNumber value="${sendGoodsList.sellPrice }" type="number" pattern="#,##0.00" />&nbsp;</td>
				<td nowrap="nowrap" style=" text-align:right;">${sendGoodsList.appointCount }&nbsp;</td>
				<td nowrap="nowrap" style=" text-align:right; "><fmt:formatNumber value="${sendGoodsList.money }" type="number" pattern="#,##0.00" />&nbsp;&nbsp;</td>
				<td nowrap="nowrap" style=" text-align:right;display:none ">${sendGoodsList.money }</td>
			</tr>
			</logic:iterate>
			<tr id="lastTr">
				<td style=" border:1px solid #FFFFFF; background-color:#FFFFFF"><input type="checkbox" name="checkAll" id="checkAll" onclick="CheckAll()"/>				</td>
				<td style=" border:1px solid #FFFFFF; background-color:#FFFFFF">全选</td>
				<td colspan="8" nowrap="nowrap" style="text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF"> 申请开票金额合计 </td>
				<td nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; background-color:#FFFFFF" id="makeInvoiceSum"></td>
			</tr>
		</table>
		<br/>
		<div class="div_tiao" style="font-size:13px"> &nbsp;&nbsp;备注信息 </div>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
				<tr>
					<td class="td_03" width="20%">备注信息</td>
					<td class="td_04" style="padding:5px 100px 5px 5px">
						<textarea rows="3" cols="100" id="remark" name="remark"></textarea>&nbsp;
					</td>
				</tr>
			</table>
		<input type="hidden" name="money" id="money" value="${customerInfo.money}"/>
		<input type="hidden" name="makeInvoiceId" value="${customerInfo.id}"/>
		<input type="hidden" name="customerName" value="${tf:replaceHTML(customerInfo.customerName)}"/>
		<input type="hidden" name="customerId" value="${customerInfo.customerId}"/>
		<input type="hidden" name="productTypeId" value="${customerInfo.productTypeId}"/>
		<input type="hidden" name="invoiceType" value="${customerInfo.invoiceType}"/>
		<input type="hidden" name="userId" value="${customerInfo.userId}"/>
		<input type="hidden" name="userName" value="${customerInfo.userName}"/>
		<div style="padding-top:20px">
			<a href="#" id="addSellInvoiceSubmit"><img src="${ctx}/images/btnAdd.gif" width="65" height="20" /></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="#" onclick="javascript:history.back()"><img src="${ctx}/images/btnBack.gif" width="65" height="20" /></a>
		</div>
		
		</html:form>
		</td>
		<td>&nbsp;</td>
		</tr>
</table>
</body>
</html>