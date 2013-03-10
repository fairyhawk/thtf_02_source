<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>产品选择</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
		<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="${ctx}/js/messages_cn.js"></script>
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
	//将小页面被选中的产品信息带到大页面
	var myArrayProduct=new Array();
	function selectProductList() 
	{
		var productChecked =document.getElementsByName("checkProduct");
		// 定义是否有产品被选中
		var count = 0;
		// 把被选中的产品传入数组
		for(var i=0;i<productChecked.length;i++){
			if(productChecked[i].checked==true){
				toParentsValue(productChecked[i]);
				count = 1 ;
			}
		}

		// 没有产品被选择
		if(count==0){
			alert("请选择产品");
			return;
		}

		//将值传回父窗口
		opener.newWindow(myArrayProduct);
		window.close();
	}
    // 把选中产品的一条记录传到父页面
    function toParentsValue(obj) 
    {
    	var delimiter=$("#delimiter").val();
		var records = obj.value;
		var arrProdInfo = new Array();
		arrProdInfo = records.split(delimiter);
		var prodId				= arrProdInfo[0];
		var prodCode			= arrProdInfo[1];
		var prodName			= arrProdInfo[2];
		var prodType            = arrProdInfo[3];
		var prodUnit            = arrProdInfo[4];
		var buyCount            = arrProdInfo[5];
		var buyPrice            = arrProdInfo[6];
		var buyMoney            = arrProdInfo[7];
		var instockMoney        = arrProdInfo[8];
		var appointMoney        = arrProdInfo[9];
		var receiveInvoiceMoney = arrProdInfo[10];
		var backContractCount   = arrProdInfo[11];
		var returnMoney			= arrProdInfo[12];
		var stockTotalNum       = arrProdInfo[13];
		if(obj.checked==true) {	myArrayProduct.push([prodId,prodCode,prodName,prodType,prodUnit,buyCount,buyPrice,buyMoney,instockMoney,appointMoney,receiveInvoiceMoney,backContractCount,returnMoney,stockTotalNum]);
		}
	}
	</script>
	</head>
	<body>
	<input type="hidden" id="buyContractId" name="buyContractId" value="${buyContractId}"/>
	<input type="hidden" id="delimiter" value="'${date}'"/>
	<div style="width:96%; text-align:right; margin:0 auto">单位：元</div>
	<table width="96%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table" align="center">
	  <tr>
		<th nowrap="nowrap" width="29px">选择</th>
		<th nowrap="nowrap" >产品编码</th>
		<th nowrap="nowrap" >产品名称</th>
		<th nowrap="nowrap">规格型号</th>
		<th nowrap="nowrap">单位</th>
		<th nowrap="nowrap">采购数</th>
		<th nowrap="nowrap">采购单价</th>
		<th nowrap="nowrap">采购金额</th>
		<th nowrap="nowrap">入库金额</th>
		<th nowrap="nowrap">指定金额</th>
		<th nowrap="nowrap">收票金额</th>
		<th nowrap="nowrap">退货合同数</th>
		<th nowrap="nowrap">返厂金额</th>
	  </tr>
		<logic:present name="prodInfoList">
			<logic:iterate id="prodInfoList" name="prodInfoList">
				<tr id="tr${prodInfoList.prodId}">
					<td>
						<input type="checkbox" name="checkProduct"
							value="${prodInfoList.prodId}'${date}'${prodInfoList.prodCode}'${date}'${prodInfoList.prodName}'${date}'${prodInfoList.prodType}'${date}'${prodInfoList.prodUnit}'${date}'${prodInfoList.buyCount}'${date}'${prodInfoList.buyPrice}'${date}'${prodInfoList.buyMoney}'${date}'${prodInfoList.instockMoney}'${date}'${prodInfoList.appointMoney}'${date}'${prodInfoList.receiveInvoiceMoney}'${date}'${prodInfoList.backContractCount}'${date}'${prodInfoList.returnMoney}'${date}'${prodInfoList.stockTotalNum}"
							>
					</td>
					<td nowrap="nowrap" >
						${prodInfoList.prodCode}&nbsp;
					</td>
					<td nowrap="nowrap">
						${prodInfoList.prodName}&nbsp;
					</td>
					<td nowrap="nowrap">
						${prodInfoList.prodType}&nbsp;
					</td>
					<td nowrap="nowrap">
						${prodInfoList.prodUnit}&nbsp;
					</td>
					<td nowrap="nowrap" style="text-align:right;">
						${prodInfoList.buyCount}&nbsp;
					</td>
					<td nowrap="nowrap" style="text-align:right;">
						<fmt:formatNumber value="${prodInfoList.buyPrice}" pattern="#,##0.00000"/>&nbsp;
					</td>
					<td nowrap="nowrap" style="text-align:right;">
						<fmt:formatNumber value="${prodInfoList.buyMoney}" pattern="#,##0.00000"/>&nbsp;
					</td>
					<td nowrap="nowrap" style="text-align:right;">
						<fmt:formatNumber value="${prodInfoList.instockMoney}" pattern="#,##0.00000"/>&nbsp;
					</td>
					<td nowrap="nowrap" style="text-align:right;">
						<fmt:formatNumber value="${prodInfoList.appointMoney}" pattern="#,##0.00000"/>&nbsp;
					</td>
					<td nowrap="nowrap" style="text-align:right;">
						<fmt:formatNumber value="${prodInfoList.receiveInvoiceMoney}" pattern="#,##0.00000"/>&nbsp;
					</td>
					<td nowrap="nowrap" style="text-align:right;">
						${prodInfoList.backContractCount}&nbsp;
					</td>
					<td nowrap="nowrap" style="text-align:right;">
						<fmt:formatNumber value="${prodInfoList.returnMoney}" pattern="#,##0.00000"/>&nbsp;
					</td>
				</tr>
			</logic:iterate>
		</logic:present>
	</table>			
	<br />
	<table border="0" cellpadding="0" cellspacing="0" width="100%"
		id="ec_table">
		<tr>
			<td align="right">
				<%@ include file="/jsp/common/newPage.jsp"%>&nbsp;&nbsp;
			</td>
		</tr>
	</table>
	<table align="center">
		<tr>
			<td height="45px" colspan="2" align="center" valign="bottom">
				<a href="#" id="selectProduct"  onClick="selectProductList();"> <img
						src="${ctx}/images/btnChoice.gif" width="65" height="20" />
				</a>
			</td>
		</tr>
	</table>

	</body>
</html>
