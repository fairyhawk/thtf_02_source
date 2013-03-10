<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><!--不要的页面 -->预付款指定</title>
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<style type="text/css">
<!--
.STYLE1 {
	color: #FF0000
}
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
				if($("#table1")){
					$("#table1 tr:even").addClass("treven");
					$("#table1 tr").each(function(i){
						$(this).mouseover(function(){
							$(this).addClass("over");
						});
						$(this).mouseout(function(){
							$(this).removeClass("over");
						});
					});
				}
			});
function MM_jumpMenu(targ,selObj,restore){ //v3.0
  eval(targ+".location='"+selObj.options[selObj.selectedIndex].value+"'");
  if (restore) selObj.selectedIndex=0;
}
//-->
	$(function(){
		//返回列表页
		$('#backBtn').click(function(){
			document.location.href='buyPayment.do';
		});
		//提交
		$('#submitBtn').click(function(){
			var pd = check();
			if(pd == false){
				return false;
			}
			$.post("appointBuyPayment.do",$("#appointBuyPaymentInitForm").serializeArray() ,waitResult,"html");
		});
		//发票类型
		if('${bpid.supplierInvoiceType}'=='0'){
			$('#supplierInvoiceType').text("普通");
		}
		if('${bpid.supplierInvoiceType}'=='1'){
			$('#supplierInvoiceType').text("增值税");
		}
		//付款方式
		if('${bpid.paymentWay}'=='2'){
			$('#paymentWay').text("支票");
		}
		if('${bpid.paymentWay}'=='3'){
			$('#paymentWay').text("网银");
		}
		if('${bpid.paymentWay}'=='4'){
			$('#paymentWay').text("电汇");
		}
		if('${bpid.paymentWay}'=='5'){
			$('#paymentWay').text("银行承兑");
		}
		if('${bpid.paymentWay}'=='6'){
			$('#paymentWay').text("信用证");
		}
		if('${bpid.paymentWay}'=='7'){
			$('#paymentWay').text("其它");
		}
		
		//初始化金额
		changeCount('inStockDelete');
		changeCount('buyContractDelete');
		//计算备货金额
	});
	//提交后获取后台新建结果
	function waitResult(err){
	  	if(err){
	  		document.location.href='buyPayment.do';
	 	}else{
	   		alert("指定失败！");
		}
	}
	//计算备货金额
	function changeCount(mart){
		var appointMoney,moneyE=0,sumMoney = 0;
		if(mart=='appointMoney'||mart=='inStockDelete'){
			$('#inStock tr').each(function(i){
				appointMoney = $(this).children("td:eq(13)").find('input').val()*100;
				moneyE = moneyE+appointMoney;
			}); 
			$('#appointMoneyE').text(moneyE/100);
		}else if(mart=='advanceMoney'||mart=='buyContractDelete'){
			$('#buyContract tr').each(function(i){
				appointMoney = $(this).children("td:eq(10)").text()*100;
				moneyE = moneyE+appointMoney;
			}); 
			$('#advanceMoneyE').text(moneyE/100);
		}
		//付款金额计算
		sumMoney = ($('#advanceMoneyE').text()*100)/100+($('#appointMoneyE').text()*100)/100;
		if(!(mart=='inStockDelete'||mart=='buyContractDelete')){
			$('#sumMoney').text(''.concat(sumMoney).concat('元'));
		}
		$('#money').val(sumMoney);
	}
		function check(){
			var buyContractId,inStockBuyContractId,money1=0,sumMoney=0,money2=0;
			$('#buyContract tr').each(function(i){
				buyContractId = $(this).children("td:eq(1)").find('input').val();
				$('#inStock tr').each(function(i){
					inStockBuyContractId = $(this).children("td:eq(1)").find('input').val();
					if(inStockBuyContractId == buyContractId){
						money1 = $(this).children("td:eq(13)").find('input').val()*100;
						sumMoney = sumMoney+money1/100;
					}
				});
				money2 = $(this).children("td:eq(10)").text()*100;
				if(money1>money2){
					alert("本合同的预付金额必须大于该合同入库明细指定金额合计！");
					return false;
				}
			}); 
			return true;
		}
</script>
</head>
<body>
<form action ="appointBuyPaymentInit" method="post" id="appointBuyPaymentInitForm" name="appointBuyPaymentInitForm">
<input type="hidden" id="paymentId" name="paymentId" value="${id}" />
<input type="hidden" id="money" name="money" value="${bpid.money}">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" /> &nbsp;当前位置： 采购管理 &gt;&gt; 付款管理 &gt;&gt; 预付款指定</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td><br />
      <div class="div_tiao"> &nbsp;&nbsp;供货商信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" height="18px" width="20%">供货商名称</td>
          <td class="td_02" width="30%">${bpid.supplierName}&nbsp;</td>
          <td class="td_01" width="20%">联系人</td>
          <td class="td_02" width="30%">${bpid.linkmanName}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18px">省份</td>
          <td class="td_02">${bpid.supplierProvince}&nbsp;</td>
          <td class="td_01">电话</td>
          <td class="td_02">${bpid.linkmanTel}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18px">城市</td>
          <td class="td_02">${bpid.supplierCity}&nbsp;</td>
          <td class="td_01">传真</td>
          <td class="td_02">${bpid.linkmanFax}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18px">汇款银行名称</td>
          <td class="td_02">${bpid.supplierRemitBankName}&nbsp;</td>
          <td class="td_01">汇款银行帐号</td>
          <td class="td_02">${bpid.supplierRemitBankAccount}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18px">发票类型</td>
          <td id="supplierInvoiceType" class="td_02">&nbsp;</td>
          <td class="td_01">增值税税率</td>
          <td class="td_02">${bpid.supplierTaxRate}%</td>
        </tr>
      </table>
      <br />
      <div class="div_tiao"> &nbsp;&nbsp;付款信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" width="20%" height="18">付款方式</td>
          <td id="paymentWay" class="td_02" width="30%">&nbsp;</td>
          <c:if test="${bpid.paymentWay==5}">
             <td class="td_01" width="20%">帐期</td>
         	 <td class="td_02" width="30%">${bpid.arterm}&nbsp;天</td>
          </c:if>
          <c:if test="${bpid.paymentWay!=5}">
             <td class="td_01" width="20%">&nbsp;</td>
         	 <td class="td_02" width="30%">&nbsp;</td>
          </c:if>
        </tr>
        <tr>
          <td class="td_01" height="18px">付款金额</td>
          <td id="sumMoney" class="td_02" >${bpid.money}&nbsp;元</td>
          <td class="td_01">产品分类名称</td>
          <td class="td_02" >${bpid.productTypeName}&nbsp;</td>
        </tr>
      </table>
      <br />
      <div class="div_tiao"> &nbsp;&nbsp;采购合同信息 </div>
      <div style="width:100%; text-align:right">单位：元</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table" style="border-left:1px solid #FFFFFF;">
        <thead>
          <tr>
            <th nowrap="nowrap" style="border-left:1px solid #c2c2c2;">序号</th>
            <th nowrap="nowrap">产品合同号</th>
            <th nowrap="nowrap">公司合同号</th>
            <th nowrap="nowrap">合同金额</th>
            <th nowrap="nowrap">入库金额</th>
            <th nowrap="nowrap">指定金额</th>
            <th nowrap="nowrap">已预付金额</th>
            <th width="84" nowrap="nowrap">收票金额</th>
            <th width="84" nowrap="nowrap">退货合同金额</th>
            <th width="84" nowrap="nowrap">返厂金额</th>
            <th nowrap="nowrap">预付金额</th>
          </tr>
        </thead>
        <tbody id="buyContract">
			<logic:present name="BuyContractList">
				<logic:iterate id="BuyContract" name="BuyContractList" indexId="index">
		          <tr>
		            <td width="40" nowrap="nowrap" style="border-left:1px solid #c2c2c2;" height="18">${index+1}&nbsp;</td>
		            <td nowrap="nowrap" >${BuyContract.productContractCode}&nbsp;<input type="hidden" id="buyContractId1" name="buyContractId1" value="${BuyContract.buyContractId}"></td>
		            <td nowrap="nowrap" >${BuyContract.companyContractCode}&nbsp;</td>
		            <td nowrap="nowrap" style=" text-align:right;">${BuyContract.contractMomey}&nbsp;</td>
		            <td nowrap="nowrap" style=" text-align:right;">${BuyContract.inStockMoney}&nbsp;</td>
		            <td nowrap="nowrap" style=" text-align:right;">${BuyContract.zdMoney}&nbsp;</td>
		            <td nowrap="nowrap" style=" text-align:right;">${BuyContract.yysMoney}&nbsp;</td>
		            <td nowrap="nowrap" style=" text-align:right;" >${BuyContract.spMoney}&nbsp;</td>
		            <td nowrap="nowrap" style=" text-align:right;">${BuyContract.backContractMoney}&nbsp;</td>
		            <td nowrap="nowrap" style=" text-align:right;">${BuyContract.fcMoney}&nbsp;</td>
		            <td nowrap="nowrap" style=" text-align:right; width:91px">${BuyContract.yfMoney}&nbsp;&nbsp;</td>
		          </tr>
		        </logic:iterate>
	   		 </logic:present>
        </tbody>
        <tfoot>
          <tr>
            <td colspan="4" nowrap="nowrap" style="border:1px solid #FFFFFF; background-color:#FFFFFF">&nbsp;</td>
            <td colspan="6" nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF"> 预付金额合计 </td>
            <td nowrap="nowrap"  style=" border:1px solid #FFFFFF;  background-color:#FFFFFF; text-align:right"><span id="advanceMoneyE"></span>元</td>
          </tr>
        </tfoot>
      </table>
      <div class="div_tiao"> &nbsp;&nbsp;产品信息 </div>
      <div style="text-align:right; width:100%">单位：元</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table1" style="border-left:1px solid #FFFFFF;">
        <thead>
          <tr>
            <th nowrap="nowrap" style="border-left:1px solid #c2c2c2;">序号</th>
            <th nowrap="nowrap">入库单号</th>
            <th nowrap="nowrap">产品合同号</th>
            <th nowrap="nowrap">公司合同号</th>
            <th nowrap="nowrap">产品编码</th>
            <th nowrap="nowrap">产品名称</th>
            <th nowrap="nowrap">规格型号</th>
            <th nowrap="nowrap">单位</th>
            <th nowrap="nowrap">采购单价</th>
            <th width="84" nowrap="nowrap">入库金额</th>
            <th width="84" nowrap="nowrap">已指定金额</th>
            <th width="86" nowrap="nowrap">收票金额</th>
            <th width="86" nowrap="nowrap">返厂金额</th>
            <th nowrap="nowrap">指定金额</th>
          </tr>
        </thead>
     <tbody id="inStock">
        	<logic:iterate id="product" name="productList" indexId="index">
				<tr>
					<td style="border-left:1px solid #c2c2c2;">${index+1}&nbsp;
						<input type="hidden" name="inStockIdAndProductId" id="inStockIdAndProductId" value="${product.inStockId}#${product.productId}" />
						<input type="hidden" name="inStockId" id="inStockId" value="${product.inStockId}"  /><input type="hidden" name="productId" id="productId" value="${product.productId}" />
					</td>
		            <td nowrap="nowrap" >${product.inStockId}&nbsp;<input type="hidden" id="productBuyContractId" name="productBuyContractId" value="${product.buyContractId}"></td>
		            <td nowrap="nowrap" >${product.productContractCode}&nbsp;</td>
		            <td nowrap="nowrap" >${product.companyContractCode}&nbsp;</td>
		            <td nowrap="nowrap" >${product.productCode}&nbsp;</td>
		            <td nowrap="nowrap" >${product.productName}&nbsp;</td>
		            <td nowrap="nowrap" >${product.productType}&nbsp;</td>
		            <td nowrap="nowrap">${product.productUnit}&nbsp;</td>
		            <td nowrap="nowrap"  >${product.buyContractPrice}&nbsp;</td>
		            <td nowrap="nowrap" style=" text-align:right;">${product.inStockMoney}&nbsp;</td>
		            <td nowrap="nowrap" style=" text-align:right;">${product.yzdMoney}&nbsp;</td>
		            <td nowrap="nowrap" style=" text-align:right;">${product.spMoney}&nbsp;</td>
		            <td nowrap="nowrap" style=" text-align:right;">${product.fcMoney}&nbsp;</td>
		            <td style="text-align:right"><input type="text" name="appointMoney" id="appointMoney" style="width:86px; text-align:right" onchange="javascript:changeCount(this.id);" value="${product.zdMoney}" />&nbsp;&nbsp;</td>
		          </tr>
		      </logic:iterate>
        </tbody>
          <tr>
            <td colspan="5" nowrap="nowrap" style="border:1px solid #FFFFFF; background-color:#FFFFFF">&nbsp;</td>
            <td colspan="8" nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF"> 指定金额合计 </td>
            <td nowrap="nowrap"  style=" border:1px solid #FFFFFF;  background-color:#FFFFFF; text-align:right" ><span id="appointMoneyE"></span>元</td>
          </tr>
        </tfoot>
      </table>
      <br />
      <div class="div_tiao"> &nbsp;&nbsp;特殊说明</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_03" width="20%">特殊说明</td>
          <td class="td_04" ><textarea name="text" id="text" cols="100" rows="4">${bpid.text}</textarea></td>
        </tr>
    </table>
    </td>
    <td >&nbsp;</td>
  </tr>
  <tr>
    <td></td>
    <td height="50px" align="center" valign="bottom">
	    <a href="#"><img id="submitBtn" src="${ctx}/images/btnSubmit.gif" width="65" height="20" /></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
	    <a href="#"><img id="backBtn" src="${ctx}/images/btnBack.gif" /></a>
    </td>
    <td></td>
  </tr>
</table>
</form>
</body>
</html>
