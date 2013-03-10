<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ taglib prefix="tf" uri="localhost" %>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>订货确认单</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
		<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/messages_cn.js"></script>

	    <style type="text/css"> 
		<!--
		
		body {
	font-family:"宋体";
font-size:14px
}
.tad_1 {
	border-left:1px solid #000000;
	border-bottom:1px solid #000000;
}
.tad_1 td {
	border-right:1px solid #000000;
	border-top:1px solid #000000;
	color:#000000;
	white-space:nowrap;
	padding:3px 0px 3px 0px;
}
.tad td {
	padding-bottom:5px;
}
.tad02 {
	border:1px solid #CCCCCC;
}
.td_01 {
	border-bottom:1px solid #CCCCCC;
	border-right:1px solid #CCCCCC;
	padding-left:5px;
}
.td_02 {
	border-bottom:1px solid #CCCCCC;
	padding-left:8px;
}
.STYLE1 {
	font-size: 24px;
	font-weight: bold;
}  
		-->
		</style>
	</head>
		<script type="text/javascript">	
		function printPreview(){   
			// 打印页面预览    
				$(".buttonNoPrint").hide();
			wb.execwb(7,1);
				$(".buttonNoPrint").show();
			      
		}    
		function printSetup(){    
			// 打印页面设置    
			wb.execwb(8,1);    
		}

		function  printIt(){ 
			$(".buttonNoPrint").hide();
			wb.execwb(6,6); 
			$(".buttonNoPrint").show();
		} 
		
		function closeWindow(){
			wb.execwb(45,1); 
		}
	    </script>


<body><br/><br/><br/>

<OBJECT id="wb" height=0 width=0 classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 name="wb"></OBJECT>

<table border="0" cellpadding="0" cellspacing="0" align="center" width="650" class="tad">
	<tr>
		<th height="60px" colspan="4" valign="top"><span class="STYLE1">订货确认单</span></th>
	</tr>
	<tr height="40px">
		<td width="50%" colspan="2"  ></td>
		<td colspan="2">签订日期:
		${tf:replaceNull(salesContract.date)}</td>
	</tr>
	<tr height="25px">
		<td width="16%" colspan="2">订货单位:${salesContract.customerName}</td>
		<td width="14%">协议编号：<c:if test="${salesContract.creditTypeId==4}">${salesContract.agreementCode}</c:if></td>
		<td ></td>
	</tr>
	<tr height="25px">
		<td  style="padding-top:4px" colspan="2">产品合同号：${tf:replaceNull(salesContract.productContractCode)}</td>
		<td colspan="2">公司合同号：${tf:replaceNull(salesContract.companyContractCode)}</td>
	</tr>
	<tr height="25px">
		<td width="50%" style="padding-top:4px" colspan="2">买方订货人：${salesContract.customerLinkmanName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;电话：${tf:replaceNull(salesContract.customerLinkmanTel)}</td>
		<td colspan="2">传真：
		${tf:replaceNull(salesContract.customerLinkmanFax)}</td>
	</tr>
	<tr height="25px">
		<td  style="padding-top:4px" colspan="4">卖方销售人员：${salesContract.userName}</td>
	</tr>
	<tr height="25px">
		<td  colspan="4">交货地点：${tf:replaceNull(salesContract.customerAddressAddress)}</td>
	</tr>
	<tr height="25px">
		<td  style="padding-top:4px" colspan="2">交货时间：${tf:replaceNull(salesContract.requestSendDate)}</td>
		<td colspan="2">联系人：${tf:replaceNull(salesContract.customerAddressLinkman)}</td>
	</tr>
	<tr>
		<td colspan="4" style="padding-top:20px;"><table width="98%" cellpadding="0" cellspacing="0" class="tad_1">
				<tr>
					<td align="center"><strong>序号</strong></td>
					<td align="center"><strong>产品名称</strong></td>
					<td align="center"><strong>规格</strong></td>
					<td align="center" width="67"><strong>单位</strong></td>
					<td align="center" width="40"><strong>数量</strong></td>
					<td align="center" width="60"><strong>单价（元）</strong></td>
					<td align="center" width="96"><strong>总价（元）</strong></td>
				</tr>
				<logic:present name="salesConProductList">
								<logic:iterate id="productlist" name="salesConProductList"
									indexId="indexId">
									<tr>
									<td nowrap="nowrap"> &nbsp;${indexId+1}</td>
										<td nowrap="nowrap">
											&nbsp;${productlist.productName}
										</td>
										<td nowrap="nowrap">
											&nbsp;${productlist.productType}
										</td>
										<td nowrap="nowrap">
											&nbsp;${productlist.productUnit}
										</td>
										<td nowrap="nowrap" align="right">
											${productlist.salesCount}&nbsp;
										</td>
										<td nowrap="nowrap" align="right">
											<fmt:formatNumber value="${productlist.salesPrice}" pattern="#,##0.00"/>&nbsp;
										</td>

										<td nowrap="nowrap" align="right">
											<fmt:formatNumber value="${productlist.salesMoney}" pattern="#,##0.00"/>&nbsp;
										</td>
									</tr>
								</logic:iterate>
							</logic:present>
				<tr>
					<td colspan="6" align="center"><strong>合计:</td>
					<td nowrap="nowrap" align="right">￥<fmt:formatNumber value="${salesContract.money}" pattern="#,##0.00"/>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="7" align="left"><strong>合计人民币金额（大写）:${capitalMoney}</strong></td>
				</tr>
			</table></td>
	</tr>
	
	
	<tr>
		<td colspan="3" align="right" style="padding-top:40px;">
			买方授权人签字：</td>
			<td style="padding-top:40px;">&nbsp;<hr></td>
	</tr>
	<tr>
		<td colspan="3" align="right">
			日期：</td>
			<td style="padding-top:5px;">&nbsp;<hr></td>
	</tr>
	<tr>
		<td colspan="4" style="font-size:13px;padding-top:10px;">
			说明：<br/><br/>
		1、授权人应是订货单位与同方股份有限公司签署的销售协议中所指的授权人。<br/><br/>
		2、本"订货确认单"经订货单位授权人签字或盖章后给予生效。<br/><br/>
		3、订货单位变更订单需以书面形式通知同方股份有限公司。<br/><br/>
		4、本确认单传真件有效。<br/>
		</td>
	</tr>
	<input id="print" type="hidden" value="${param.print}">
				<tr>
					<td colspan="4" align="center" height="50px">
					<a href=#><img src="${ctx}/images/btnPrint.gif" width="65" height="20" class="buttonNoPrint" onClick="javascript:printIt();"/></a>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<a href=#><img src="${ctx}/images/btnPrintSZ.gif" width="88" height="20" class="buttonNoPrint" onClick="javascript:printSetup();"/></a>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				    <a href=#><img src="${ctx}/images/btnPrintYL.gif" class="buttonNoPrint" onClick="javascript:printPreview();" /></a>
					</td>
				</tr>
</table>
</body>
</html>
