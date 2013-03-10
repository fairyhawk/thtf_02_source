<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="tf" uri="localhost" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>付款确认</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/Calendar1.js"></script>
<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
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
$(function(){
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
//-->
	//获取服务器返回结果
	function waitResult(result){
		if(result == 'true'){
			document.location.href = 'buyPayment.do';
			
		}else{
			alert("付款确认失败！");
		}
	}
	$(function(){
		$.ajaxSetup({ 
  			async: false 
  		}); 
		//返回列表页
		$('#backBtn').click(function(){
			document.location.href='buyPayment.do';
		});
		//确认
		$('#confirmBtn').click(function(){
			var result = submitChick();
			if(result == true){
				if(confirm("付款金额提交后不可更改，请再次确认金额是否正确！")){
					$.post('confirmBuyPaymentModify.do',$('#confirmForm').serializeArray() ,waitResult,'html');
				}
			}
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
		//产品总监评审意见
        if('${ProMajIder[0]}'=='1'){
        	$('#checkbox1').attr("checked",'true');
        }
        if('${ProMajIder[0]}'=='0'){
        	$('#checkbox2').attr("checked",'true');
        }
        if('${ProMajIder[1]}'=='1'){
        	$('#checkbox3').attr("checked",'true');
        }
        if('${ProMajIder[1]}'=='0'){
        	$('#checkbox4').attr("checked",'true');
        }
        if('${ProMajIder[2]}'=='1'){
        	$('#checkbox5').attr("checked",'true');
        }
        if('${ProMajIder[2]}'=='0'){
        	$('#checkbox6').attr("checked",'true');
        }
        if('${ProMajIder[3]}'=='1'){
        	$('#checkbox7').attr("checked",'true');
        }
        if('${ProMajIder[3]}'=='0'){
        	$('#checkbox8').attr("checked",'true');
        }
        if('${ProMajIder[4]}'=='1'){
        	$('#checkbox9').attr("checked",'true');
        }
        if('${ProMajIder[4]}'=='0'){
        	$('#checkbox10').attr("checked",'true');
        }
        //采购主管评审意见
        if('${bpid.buyManIdea}'=='1'){
        	$('#buyManIdea1').attr("checked",'true');
        }
        if('${bpid.buyManIdea}'=='0'){
        	$('#buyManIdea0').attr("checked",'true');
        }
        //运营总监评审意见
        if('${bpid.opeMajIder}'=='1'){
        	$('#opeMajIder1').attr("checked",'true');
        }
        if('${bpid.opeMajIder}'=='0'){
        	$('#opeMajIder0').attr("checked",'true');
        }
        //金额计算（干什么用的?）
        var appointMoney,moneyE=0,moneyE1=0,sumMoney = 0;
		$('#inStock tr').each(function(i){
			appointMoney = $(this).children("td:eq(14)").find('input').val();
			moneyE = FloatAdd(moneyE,appointMoney);
		}); 
		$('#appointMoneyE').text(formatMoney(moneyE,5));
		$('#buyContract tr').each(function(i){
			appointMoney = rmoney($(this).children("td:eq(10)").text());
			moneyE1 = FloatAdd(moneyE1,appointMoney);
		}); 
		$('#advanceMoneyE').text(formatMoney(moneyE1,5));
	});
	function changeMoney(){
		var appointMoney,moneyE=0;
		$('#inStock tr').each(function(i){
			appointMoney = $(this).children("td:eq(14)").find('input').val();
			moneyE = FloatAdd(moneyE,appointMoney);
		}); 
		$('#appointMoneyE').text(formatMoney(moneyE,5));
		//如果是外单，付款金额可变，并且只有可能等于指定金额合计！
		if(${bpid.contractType}=='1'){
			$('#sumMoney').text(''.concat(formatMoney(moneyE,5)).concat('元'));
			$('#money').val(moneyE);
		}
	}	
	function submitChick(){
		var paymentDate = $("#paymentDate")[0].value;
		var number = $("#number")[0].value;
		if(paymentDate == ""||paymentDate == null){
			alert("付款日期不可为空！");
			return false;
		}else{
			var date = paymentDate.split("-");  
			if(date[0] < new Date().getFullYear()){
				alert("付款日期必须大于等于今天");
				return false;
			} 
			if(date[0] == new Date().getFullYear() 
				&& date[1] < new Date().getMonth()+1 
				){
				alert("付款日期必须大于等于今天");
				return false;
			}
			if(date[0] == new Date().getFullYear() 
				&& date[1] == new Date().getMonth()+1 
				&& date[2] < new Date().getDate()){ 
				alert("付款日期必须大于等于今天");
				return false;
			}
		}
		if(number ==""||number==null){
			alert("凭证号不可为空！");
			return false;
		}
		var appointMoney,moneyE=0,moneyE1=0,summoney = 0;
		var pd = true;
		$('#inStock tr').each(function(i){
			appointMoney = $(this).children("td:eq(14)").find('input').val();
			if(appointMoney == 0){
				pd = false;
			}
			moneyE = FloatAdd(moneyE,appointMoney);
		}); 
		$('#appointMoneyE').text(formatMoney(moneyE,5));
		$('#buyContract tr').each(function(i){
			appointMoney = rmoney($(this).children("td:eq(10)").text());
			moneyE1 = FloatAdd(moneyE1,appointMoney);
		}); 
		$('#advanceMoneyE').text(formatMoney(moneyE1,5));
		summoney = FloatAdd(moneyE,moneyE1); 

		var sumMoney = rmoney($('#sumMoney').text());
		if(pd == false){
			alert("指定金额不可以为零！");
			return false;
		}
		var contractType = "${bpid.contractType}";
		if(contractType == 0){ 
			if(summoney > sumMoney){
				alert("预付金额合计加上指定金额合计大于付款金额！");
				return false;
			}
			if(summoney != sumMoney){
				alert("付款金额必须全部指定！");
				return false;
			}
		}
		return pd;
	}	
	
	//金额的格式化s为要格式化的参数（浮点型），n为小数点后保留的位数	
		function formatMoney(s,n){
			n = n>0 && n<=20 ? n : 2;
			s = parseFloat((s+"").replace(/[^\d\.-]/g,"")).toFixed(n)+"";
			var l = s.split(".")[0].split("").reverse(), 
			r = s.split(".")[1]; 
			t = "";
			for(i = 0;i<l.length;i++){
				t+=l[i]+((i+1)%3==0 && (i+1) != l.length ? "," : ""); 
			}
			return t.split("").reverse().join("")+"."+r;
		}
		//还原金额   
		function rmoney(s){  
			return parseFloat(s.replace(/[^\d\.-]/g,""));  
		} 
</script>
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
</head>
<body>
<form action="confirmBuyPaymentModify.do" method="post" id="confirmForm">
<input type="hidden" name="id" value="${id}"/>
<input type="hidden" name="status" value="${bpid.status}"/>
<input type="hidden" id="money" name="money" value="${bpid.money}">
<input type="hidden" id="contractType" name="contractType" value="${bpid.contractType}" />
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" /> &nbsp;当前位置： 采购管理 &gt;&gt; 付款管理 &gt;&gt; 付款确认</td>
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
          <td id="sumMoney" class="td_02" ><fmt:formatNumber value="${bpid.money}" pattern="#,##0.00000#"/>&nbsp;元</td>
          <td class="td_01">产品分类名称</td>
          <td class="td_02" >${bpid.productTypeName}&nbsp;</td>
        </tr>
        <tr>
	        <td class="td_01" width="20%"><span style="color:#FF0000">*</span>&nbsp;付款日期</td>
	        <td class="td_02" width="30%"><input type="text" name="paymentDate" id="paymentDate" maxlength="12"
										style="width:120px;" onfocus="calendar()"
										readonly="readonly" value="" /></td>
			<td class="td_01" width="20%"><span style="color:#FF0000">*</span>&nbsp;凭证号</td>
	        <td class="td_02"><input type="text" name="number" id="number" style="width:240px;" maxlength="20"/></td>
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
		            <td nowrap="nowrap" >${BuyContract.productContractCode}&nbsp;</td>
		            <td nowrap="nowrap" >${BuyContract.companyContractCode}&nbsp;</td>
		            <td nowrap="nowrap" style=" text-align:right;"><fmt:formatNumber value="${BuyContract.contractMomey}" pattern="#,##0.00000#"/>&nbsp;</td>
		            <td nowrap="nowrap" style=" text-align:right;"><fmt:formatNumber value="${BuyContract.inStockMoney}" pattern="#,##0.00000#"/>&nbsp;</td>
		            <td nowrap="nowrap" style=" text-align:right;"><fmt:formatNumber value="${BuyContract.zdMoney}" pattern="#,##0.00000#"/>&nbsp;</td>
		            <td nowrap="nowrap" style=" text-align:right;"><fmt:formatNumber value="${BuyContract.yysMoney}" pattern="#,##0.00000#"/>&nbsp;</td>
		            <td nowrap="nowrap" style=" text-align:right;" ><fmt:formatNumber value="${BuyContract.spMoney}" pattern="#,##0.00000#"/>&nbsp;</td>
		            <td nowrap="nowrap" style=" text-align:right;"><fmt:formatNumber value="${BuyContract.backContractMoney}" pattern="#,##0.00000#"/>&nbsp;</td>
		            <td nowrap="nowrap" style=" text-align:right;"><fmt:formatNumber value="${BuyContract.fcMoney}" pattern="#,##0.00000#"/>&nbsp;</td>
		            <td nowrap="nowrap" style=" text-align:right; width:91px"><fmt:formatNumber value="${BuyContract.yfMoney}" pattern="#,##0.00000#"/>&nbsp;&nbsp;</td>
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
            <th nowrap="nowrap">要求付款日期</th>
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
					<input type="hidden" name="inStockId" id="inStockId" value="${product.inStockId}"  />
					<input type="hidden" name="productId" id="productId" value="${product.productId}" /></td>
		            <td nowrap="nowrap" >${product.inStockId}&nbsp;</td>
		            <td nowrap="nowrap" >${product.requestAccountDate}&nbsp;</td>
		            <td nowrap="nowrap" >${product.productContractCode}&nbsp;</td>
		            <td nowrap="nowrap" >${product.companyContractCode}&nbsp;</td>
		            <td nowrap="nowrap" >${product.productCode}&nbsp;</td>
		            <td nowrap="nowrap" >${product.productName}&nbsp;</td>
		            <td nowrap="nowrap" >${product.productType}&nbsp;</td>
		            <td nowrap="nowrap" >${product.productUnit}&nbsp;</td>
		            <td nowrap="nowrap" ><fmt:formatNumber value="${product.buyContractPrice}" pattern="#,##0.00000#"/>&nbsp;</td>
		            <td nowrap="nowrap" style=" text-align:right;"><fmt:formatNumber value="${product.inStockMoney}" pattern="#,##0.00000#"/>&nbsp;</td>
		            <td nowrap="nowrap" style=" text-align:right;"><fmt:formatNumber value="${product.yzdMoney}" pattern="#,##0.00000#"/>&nbsp;</td>
		            <td nowrap="nowrap" style=" text-align:right;"><fmt:formatNumber value="${product.spMoney}" pattern="#,##0.00000#"/>&nbsp;</td>
		            <td nowrap="nowrap" style=" text-align:right;"><fmt:formatNumber value="${product.fcMoney}" pattern="#,##0.00000#"/>&nbsp;</td>
		             <td style="text-align:right"><input type="text" name="appointMoney" id="appointMoney" style="width:90px; text-align:right" onchange="javascript:changeMoney();" value="${product.zdMoney}" />&nbsp;&nbsp;</td>
		          </tr>
		      </logic:iterate>
        </tbody>
          <tr>
            <td colspan="5" nowrap="nowrap" style="border:1px solid #FFFFFF; background-color:#FFFFFF">&nbsp;</td>
            <td colspan="9" nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF"> 指定金额合计 </td>
            <td nowrap="nowrap"  style=" border:1px solid #FFFFFF;  background-color:#FFFFFF; text-align:right" ><span id="appointMoneyE"></span>元</td>
          </tr>
        </tfoot>
      </table>
      <br />
      <div class="div_tiao"> &nbsp;&nbsp;特殊说明</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_03" width="20%">特殊说明</td>
          <td class="td_04" style="padding:5px 100px 5px 5px">${tf:replaceBr(bpid.text)}&nbsp;</td>
        </tr>
      </table>
      <br />
      <div class="div_tiao"> &nbsp;&nbsp;评审意见</div>
      <table border="0"  cellpadding="0" cellspacing="0" align="center" width="460">
        <tr>
          <td height="20px" colspan="2" >产品总监评审意见：</td>
        </tr>
        <tr>
          <td nowrap="nowrap">1.付款时间是否符合</td>
          <td height="20px" nowrap="nowrap">
          	 <c:if test = "${strIder[0]==0}">
          	 
          	 </c:if>
          	<input type="checkbox" name="checkbox1" id="checkbox1" disabled="disabled"/>符合&nbsp;&nbsp;
            <input type="checkbox" name="checkbox2" id="checkbox2" disabled="disabled"/>不符合 
          </td>
        </tr>
        <tr>
          <td nowrap="nowrap">2.付款金额是否符合</td>
          <td height="20px" nowrap="nowrap">
            <input type="checkbox" name="checkbox3" id="checkbox3" disabled="disabled"/>符合&nbsp;&nbsp;
            <input type="checkbox" name="checkbox4" id="checkbox4" disabled="disabled"/>不符合 
          </td>
        </tr>
        <tr>
          <td nowrap="nowrap">3.付款类型是否符合</td>
          <td height="20px" nowrap="nowrap">
          	<input type="checkbox" name="checkbox5" id="checkbox5" disabled="disabled"/>符合&nbsp;&nbsp;
            <input type="checkbox" name="checkbox6" id="checkbox6" disabled="disabled"/>不符合 
          </td>
        </tr>
        <tr>
          <td nowrap="nowrap">4.付款方式是否符合</td>
          <td height="20px" nowrap="nowrap">
	          	<input type="checkbox" name="checkbox7" id="checkbox7" disabled="disabled"/>符合&nbsp;&nbsp;
	            <input type="checkbox" name="checkbox8" id="checkbox8" disabled="disabled"/>不符合
		  </td>
        </tr>
        <tr>
          <td width="320" nowrap="nowrap">5.供货商付款信息是否符合</td>
          <td height="20px" width="150" nowrap="nowrap">
            <input type="checkbox" name="checkbox9" id="checkbox9" disabled="disabled"/>符合&nbsp;&nbsp;
            <input type="checkbox" name="checkbox10" id="checkbox10" disabled="disabled"/>不符合 
          </td>
        </tr>
        <tr>
          <td colspan="2" valign="top"><table width="98%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap="nowrap">补充说明：</td>
                <td style="padding:5px 0 5px 0;">${tf:replaceBr(bpid.proMajText)}&nbsp;</td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td height="20px">签字：${bpid.proMajName}</td>
          <td>日期：${bpid.proMajDate}</td>
        </tr>
        <tr>
          <td height="20px" colspan="2">&nbsp;</td>
        </tr>
        <tr>
          <td height="20px" colspan="2">采购主管评审意见：</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
		  <td height="20px">
	      	 <input type="checkbox" name="buyManIdea1" id="buyManIdea1" disabled="disabled">符合&nbsp;&nbsp;
	         <input type="checkbox" name="buyManIdea0" id="buyManIdea0" disabled="disabled"/>不符合
	      </td>            
        </tr>
        <tr>
          <td colspan="2" valign="top"><table width="98%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap="nowrap">补充说明：</td>
                <td style="padding:5px 0 5px 0;">${tf:replaceBr(bpid.buyManText)}&nbsp;</td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td height="20px">签字：${bpid.buyManName}</td>
          <td>日期：${bpid.buyManDate}</td>
        </tr>
        <tr>
          <td height="20px" colspan="2" >&nbsp;</td>
        </tr>
        <tr>
          <td height="20px" colspan="2">运营总监评审意见：</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
	      <td height="20px">
	      	 <input type="checkbox" name="opeMajIder1" id="opeMajIder1" disabled="disabled" />符合&nbsp;&nbsp;
		     <input type="checkbox" name="opeMajIder0" id="opeMajIder0" disabled="disabled" />不符合
	      </td>         	
        </tr>
        <tr>
          <td colspan="2" valign="top"><table width="98%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap="nowrap">补充说明：</td>
                <td style="padding:5px 0 5px 0;" >${tf:replaceBr(bpid.opeMajText)}</td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td height="20px">签字：${bpid.opeMajName}</td>
          <td>日期：${bpid.opeMajDate}</td>
        </tr>
      </table></td>
    <td >&nbsp;</td>
  </tr>
  <tr>
    <td></td>
    <td height="50px" align="center" valign="bottom">
    	<img id="confirmBtn" src="${ctx}/images/btnConfirm.gif" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    	<img id="backBtn" src="${ctx}/images/btnBack.gif" />
    </td>
    <td></td>
  </tr>
</table>
<br />
</form>
</body>
</html>
