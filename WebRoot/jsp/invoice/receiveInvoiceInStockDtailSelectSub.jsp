<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
        <script type="text/javascript" src="${ctx}/js/Calendar1.js"></script>
		<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
		<script type="text/javascript" src="${ctx}/js/common/contractUtil.js"></script>
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
	});

	//检索产品信息
	function searchProdInfo(){
		document.forms[0].action = "getInStockProdList.do?";
		$("#frmProd").submit();
	}

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
		var inStockId           = arrProdInfo[0];
		var prodContractCode    = arrProdInfo[1];
		var compContractCode    = arrProdInfo[2];
		var prodId              = arrProdInfo[3];
		var prodCode            = arrProdInfo[4];
		var prodName            = arrProdInfo[5];
		var prodType            = arrProdInfo[6];
		var prodUnit            = arrProdInfo[7];
		var buyUnitPrice        = arrProdInfo[8];
		var inStockCount        = arrProdInfo[9];
		var appointMoney        = arrProdInfo[10];
		var receiveInvoiceCount = arrProdInfo[11];
		var receiveInvoiceMoney = arrProdInfo[12];
		var backGoodsMoney      = arrProdInfo[13];
		var taxRate				= arrProdInfo[14];
		var money				= arrProdInfo[15];
		if(obj.checked==true) {
			myArrayProduct.push([inStockId,prodContractCode,compContractCode,prodId,prodCode,prodName,prodType,prodUnit,buyUnitPrice,inStockCount,appointMoney,receiveInvoiceCount,receiveInvoiceMoney,backGoodsMoney,taxRate,money]);
		}
	}
</script>

</head>

<body>
<form action="" method="post" id ="frmProd">
<table width="96%" border="0" cellpadding="0" cellspacing="0" align="center">
   <tr>
    <td align="center"><br />
    	<div class="mo_wp">
          <div style="display: ; " class="mo_con" >
		    <input type="hidden" id="prodTypeId" name="prodTypeId" value="${inStockForm.prodTypeId}"/>
			<input type="hidden" id="prodTypeName" name="prodTypeName" value="${inStockForm.prodTypeName}"/>
			<input type="hidden" id="supplierId" name="supplierId" value="${inStockForm.supplierId}"/>
            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
              <tr>
                <td class="td_01" width="20%">入库单号</td>
                <td class="td_02" width="30%"><input type="text" name="inStockId" id="inStockId" style="width:120px;"  value="${inStockForm.inStockId}"/></td>
                 <td class="td_01" width="20%">产品分类名称</td>
                 <td class="td_02" width="30%">${inStockForm.prodTypeName}</td>
              </tr>
               <tr>
               	<td class="td_01">产品合同号</td>
               	<td class="td_02"><input type="text" name="prodContractCode" id="prodContractCode" style="width:120px;" value="${inStockForm.prodContractCode}" /></td>
               	<td class="td_01">公司合同号</td>
               	<td class="td_02"><input type="text" name="compContractCode" id="compContractCode" style="width:120px;"
				value="${inStockForm.compContractCode}"/></td>
               	</tr>
               <tr>
               	<td class="td_01">产品编码</td>
               	<td class="td_02"><input type="text" name="prodCode" id="prodCode" style="width:120px;"
				value="${inStockForm.prodCode}"/></td>
               	<td class="td_01">产品名称</td>
               	<td class="td_02"><input type="text" name="prodName" id="prodName" style="width:120px;" value="${inStockForm.prodName}" /></td>
               	</tr>
               <tr>
                <td class="td_01" width="20%">规格型号</td>
                <td class="td_02" width="30%"><input type="text" name="prodType" id="prodType" style="width:240px;" 
				value="${inStockForm.prodType}"/></td>
                 <td class="td_01" width="20%">&nbsp;</td>
                 <td class="td_02" width="30%">&nbsp;</td>
               </tr>
              <tr>
                <td colspan="4" align="center" style="height:30px;"><img src="${ctx}/images/btn_JianSuo.gif" onClick="searchProdInfo();"/></td>
              </tr>
            </table>
          </div>
          <div class="mo_title" onclick="fMainListToggle(this)">
              <div  style="text-align:center"><img id="imgShang" src="${ctx}/images/shang_sj.png" /></div>
          </div>
        </div>    </td>
  </tr>
  <tr>
    <td align="center">
    <input type="hidden" id="delimiter" value="'${date}'"/>
    <div style="width:100%; text-align:right">单位：元</div>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table">
      <tr>
        <th nowrap="nowrap">选择</th>
        <th nowrap="nowrap">入库单号</th>
        <th nowrap="nowrap">产品合同号</th>
        <th nowrap="nowrap">公司合同号</th>
        <th nowrap="nowrap">产品编码</th>
        <th nowrap="nowrap">产品名称</th>
        <th nowrap="nowrap">规格型号</th>
        <th nowrap="nowrap">单位</th>
        <th nowrap="nowrap">采购单价</th>
        <th nowrap="nowrap">入库数</th>
        <th nowrap="nowrap">指定金额</th>
        <th width="84" nowrap="nowrap">收票数</th>
        <th width="84" nowrap="nowrap">收票金额</th>
        <th width="84" nowrap="nowrap">返厂金额</th>
        </tr>
       <c:forEach var="prodList" items="${prodList}">
        <tr>
			<td nowrap="nowrap" style="border-left:1px solid #c2c2c2">
				<input type="checkbox" name="checkProduct"
					value="${prodList.inStockId}'${date}'${prodList.prodContractCode}'${date}'${prodList.compContractCode}'${date}'${prodList.prodId}'${date}'${prodList.prodCode}'${date}'${prodList.prodName}'${date}'${prodList.prodType}'${date}'${prodList.prodUnit}'${date}'${prodList.buyUnitPrice}'${date}'${prodList.inStockCount}'${date}'${prodList.appointMoney}'${date}'${prodList.receivedInvoiceCount}'${date}'${prodList.receiveInvoiceMoney}'${date}'${prodList.backGoodsMoney}'${date}'${prodList.taxRate}'${date}'${prodList.money}"
					>
			</td>
          <td nowrap="nowrap">${prodList.inStockId}&nbsp;</td>
          <td nowrap="nowrap">${prodList.prodContractCode}&nbsp;</td>
          <td nowrap="nowrap">${prodList.compContractCode}&nbsp;</td>
          <td nowrap="nowrap">${prodList.prodCode}&nbsp;</td>
          <td nowrap="nowrap">${prodList.prodName}&nbsp;</td>
          <td nowrap="nowrap">${prodList.prodType}&nbsp;</td>
          <td nowrap="nowrap">${prodList.prodUnit}&nbsp;</td>
          <td nowrap="nowrap" style="text-align:right;"><fmt:formatNumber value="${prodList.buyUnitPrice}" type="number" pattern="#,##0.00000" />&nbsp;</td>
          <td nowrap="nowrap" style="text-align:right;">${prodList.inStockCount}&nbsp;</td>
          <td nowrap="nowrap" style="text-align:right;"><fmt:formatNumber value="${prodList.appointMoney}" type="number" pattern="#,##0.00000" />&nbsp;</td>
          <td nowrap="nowrap" style="text-align:right;">${prodList.receivedInvoiceCount}&nbsp;</td>
          <td nowrap="nowrap" style="text-align:right;"><fmt:formatNumber value="${prodList.receiveInvoiceMoney}" type="number" pattern="#,##0.00000" />&nbsp;</td>
          <td nowrap="nowrap" style="text-align:right;"><fmt:formatNumber value="${prodList.backGoodsMoney}" type="number" pattern="#,##0.00000" />&nbsp;</td>
         </tr>
	   </c:forEach>
    </table>
    <br />
		<table border="0" cellpadding="0" cellspacing="0" width="100%"
			id="ec_table">
			<tr>
				<td align="right">
					<br />
					<%@ include file="/jsp/common/newPage.jsp"%>
					&nbsp;&nbsp;
				</td>
			</tr>
		</table>
		<table align="center">
			<tr>
				<td height="45px" colspan="2" align="center" valign="bottom">
					<img
							src="${ctx}/images/btnChoice.gif" onClick="selectProductList();" width="65" height="20" />
					</a>
				</td>
			</tr>
		</table> 
    </td>
  </tr>
</table>
 </form>
</body>
</html>
