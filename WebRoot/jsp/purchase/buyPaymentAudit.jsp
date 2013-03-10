<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib prefix="tf" uri="localhost" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>付款评审</title>
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
				$.ajaxSetup({ 
  					async: false 
  				}); 
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
		//通过
		$('#btnTG').click(function(){
			var result = submitCheck();
			if(result == false){
				return false;
			}
			$("#iderpd")[0].value=1;
			$.post("auditBuyPayment.do",$("#BuyPaymentAuditForm").serializeArray() ,waitResult,"html");
		});
		//未通过
		$('#btnWTG').click(function(){
			var result = submitCheck();
			if(result == false){
				return false;
			}
			if("${userRoleId}"==10){
				textArea = $.trim($("#proMajText").val().replace(/[\u3000]/g," "));
			}
			if("${userRoleId}"==11){
				textArea = $.trim($("#buyManText").val().replace(/[\u3000]/g," "));
			}
			if("${userRoleId}"==16||"${userRoleId}"==17){
				textArea = $.trim($("#opeMajText").val().replace(/[\u3000]/g," "));
			}
			if(textArea ==null|| textArea ==""){
				alert("补充说明不可为空！");
				return false;
			}
			$("#iderpd")[0].value=0;
			$.post("auditBuyPayment.do",$("#BuyPaymentAuditForm").serializeArray() ,waitResult,"html");
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
		//checkbox 单选
		$(":checkbox:enabled").each(function(i,assessItem){
			$(assessItem).bind("click", function() {
					if (assessItem.value == "1" ) {						
						$(":checkbox[name='" + assessItem.name + "'][value='0']").attr("checked", false);
					} else {						
						$(":checkbox[name='" + assessItem.name + "'][value='1']").attr("checked", false);
					}
			});//bind			
		});//each
		//判断checkbox是否可选产品总监
		if("${userRoleId}"==10){
			$('#buyManIdea1').attr("disabled",true);
			$('#buyManIdea0').attr("disabled",true);
			$('#opeMajIder1').attr("disabled",true);
			$('#opeMajIder0').attr("disabled",true);
			buyManIdeaCheck();
			opeMajIderCheck();
		}
		//判断checkbox是否可选 采购主管、运营总监助理、运营总监
		if("${userRoleId}"==11||"${userRoleId}"==16||"${userRoleId}"==17){
			$('#proMajIder0').attr("disabled",true);
			$('#proMajIder1').attr("disabled",true);
			$('#proMajIder2').attr("disabled",true);
			$('#proMajIder3').attr("disabled",true);
			$('#proMajIder4').attr("disabled",true);
			$('#proMajIder5').attr("disabled",true);
			$('#proMajIder6').attr("disabled",true);
			$('#proMajIder7').attr("disabled",true);
			$('#proMajIder8').attr("disabled",true);
			$('#proMajIder9').attr("disabled",true);
			proMajIderCheck();
			if("${userRoleId}"==11){
				//采购主管
				$('#opeMajIder1').attr("disabled",true);
				$('#opeMajIder0').attr("disabled",true);
				opeMajIderCheck();
			}else{ 
				//运营总监助理、运营总监
				$('#buyManIdea1').attr("disabled",true);
				$('#buyManIdea0').attr("disabled",true);
				buyManIdeaCheck();
			}
		}
		//金额计算
		 var appointMoney,moneyE=0,moneyE1=0,sumMoney = 0;
		$('#inStock tr').each(function(i){
			appointMoney = rmoney($(this).children("td:eq(14)").text())*100;
			moneyE = moneyE+appointMoney;
		}); 
		$('#appointMoneyE').text(formatMoney(moneyE/100,5));
		$('#buyContract tr').each(function(i){
			appointMoney = rmoney($(this).children("td:eq(10)").text())*100;
			moneyE1 = moneyE1+appointMoney;
		}); 
		$('#advanceMoneyE').text(formatMoney(moneyE1/100,5));
	});
		//产品总监评审意见
		function proMajIderCheck(){
	        if('${ProMajIder[0]}'=='1'){
	        	$('#proMajIder0').attr("checked",'true');
	        }
	        if('${ProMajIder[0]}'=='0'){
	        	$('#proMajIder1').attr("checked",'true');
	        }
	        if('${ProMajIder[1]}'=='1'){
	        	$('#proMajIder2').attr("checked",'true');
	        }
	        if('${ProMajIder[1]}'=='0'){
	        	$('#proMajIder3').attr("checked",'true');
	        }
	        if('${ProMajIder[2]}'=='1'){
	        	$('#proMajIder4').attr("checked",'true');
	        }
	        if('${ProMajIder[2]}'=='0'){
	        	$('#proMajIder5').attr("checked",'true');
	        }
	        if('${ProMajIder[3]}'=='1'){
	        	$('#proMajIder6').attr("checked",'true');
	        }
	        if('${ProMajIder[3]}'=='0'){
	        	$('#proMajIder7').attr("checked",'true');
	        }
	        if('${ProMajIder[4]}'=='1'){
	        	$('#proMajIder8').attr("checked",'true');
	        }
	        if('${ProMajIder[4]}'=='0'){
	        	$('#proMajIder9').attr("checked",'true');
	        }
		}
		//采购主管评审意见
		function buyManIdeaCheck(){
	        if('${bpid.buyManIdea}'=='1'){
	        	$('#buyManIdea1').attr("checked",'true');
	        }
	        if('${bpid.buyManIdea}'=='0'){
	        	$('#buyManIdea0').attr("checked",'true');
	        }
		}
		//运营总监评审意见
		function opeMajIderCheck(){
	        if('${bpid.opeMajIder}'=='1'){
	        	$('#opeMajIder1').attr("checked",'true');
	        }
	        if('${bpid.opeMajIder}'=='0'){
	        	$('#opeMajIder0').attr("checked",'true');
	        }
		}
		//提交后获取后台新建结果
		function waitResult(err){
		  if(err=='1'){
		  	document.location.href='buyPayment.do';
		  }else if(err == '2'){
		    alert("评审失败！");
		  }else if(err == '3'){
		  	alert("评审意见必须全为符合！");
		  }
		}
		function noiderChecked(){
			var checkbox = $(":checkbox[value='1']:checked:enabled").length;
			var nocheckbox = $(":checkbox[value='0']:checked:enabled").length;
			var textArea = $.trim($("#textarea").val().replace(/[\u3000]/g," "));
			$("#textarea").val(textArea);
			var textAreaLen = textArea.length; 
			if(textAreaLen>200){
				alert("补充说明不能大于200个字符！");
				return;
			}
			$("#iderpd")[0].value=0;
			submitChecked(checkbox,nocheckbox);
		}
		function submitCheck(){
			var result = true;
			var textArea ;
			var CheckCount=0; 
			if("${userRoleId}"==10){
				textArea = $.trim($("#proMajText").val().replace(/[\u3000]/g," "));
				$("#proMajText").val(textArea);
				$(":checkbox:enabled").each(function(){
					if($(this).attr("checked")){
					CheckCount++; 
					} 
				});
				if(CheckCount != 5){
					alert("评审意见不可为空！");
					result = false;
				}
			}
			if("${userRoleId}"==11){
				textArea = $.trim($("#buyManText").val().replace(/[\u3000]/g," "));
				$("#buyManText").val(textArea);
				$(":checkbox:enabled").each(function(){
					if($(this).attr("checked")){
					CheckCount++; 
					} 
				});
				if(CheckCount != 1){
					alert("评审意见不可为空！");
					result = false;
				}
			}
			if("${userRoleId}"==16||"${userRoleId}"==17){
				textArea = $.trim($("#opeMajText").val().replace(/[\u3000]/g," "));
				$("#opeMajText").val(textArea);
				$(":checkbox:enabled").each(function(){
					if($(this).attr("checked")){
					CheckCount++; 
					} 
				});
				if(CheckCount != 1){
					alert("评审意见不可为空！");
					result = false;
				}
			}
			var textAreaLen = textArea.length;
			if(textAreaLen>200&&result == true){
				alert("补充说明不能大于200个字符！");
				result = false;
			}
			return result ;
		}
		//还原金额   
		function rmoney(s){ 
			return parseFloat(s.replace(/[^\d\.-]/g,""));  
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
		
</script>
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" /> &nbsp;当前位置： 采购管理 &gt;&gt; 付款管理 &gt;&gt; 付款评审</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
  
  <td >&nbsp;</td>
  <td>
  <br />
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
      <td class="td_02" id = "fkMoney"><fmt:formatNumber value="${bpid.money}" pattern="#,##0.00000#"/>元</td>
      <td class="td_01">产品分类名称</td>
      <td class="td_02" >${bpid.productTypeName}&nbsp;</td>
    </tr>
    <tr>
      <td class="td_01" height="18px">未指定金额</td>
      <td class="td_02" ><fmt:formatNumber value="${wzdMoney}" pattern="#,##0.00000#"/>元</td>
      <td class="td_01">&nbsp;</td>
      <td class="td_02" >&nbsp;</td>
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
            <td nowrap="nowrap" style=" text-align:right;"><fmt:formatNumber value="${BuyContract.spMoney}" pattern="#,##0.00000#"/>&nbsp;</td>
            <td nowrap="nowrap" style=" text-align:right;"><fmt:formatNumber value="${BuyContract.backContractMoney}" pattern="#,##0.00000#"/></td>
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
        <td nowrap="nowrap"  style=" border:1px solid #FFFFFF;  background-color:#FFFFFF; text-align:right"><span id="advanceMoneyE">0.00</span>元</td>
      </tr>
    </tfoot>
  </table>
  <br/>
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
      <logic:present name="productList">
        <logic:iterate id="product" name="productList" indexId="index">
          <tr>
            <td width="40" nowrap="nowrap" style="border-left:1px solid #c2c2c2;" height="18">${index+1}&nbsp;</td>
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
            <td nowrap="nowrap" style=" text-align:right;" width="91"><fmt:formatNumber value="${product.zdMoney}" pattern="#,##0.00000#"/>&nbsp;&nbsp;</td>
          </tr>
        </logic:iterate>
      </logic:present>
    </tbody>
    <tfoot>
      <tr>
        <td colspan="5" nowrap="nowrap" style="border:1px solid #FFFFFF; background-color:#FFFFFF">&nbsp;</td>
        <td colspan="9" nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF"> 指定金额合计 </td>
        <td nowrap="nowrap"  style=" border:1px solid #FFFFFF;  background-color:#FFFFFF; text-align:right"><span id="appointMoneyE">0.00</span>元</td>
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
  <form action ="auditBuyPayment" method="post" id="BuyPaymentAuditForm" name="BuyPaymentAuditForm">
  
  <input type="hidden" name="id" value="${id}"/>
  <input type="hidden" id="iderpd" name="iderpd" value="1" />
  <input type="hidden" id="fkMoney" name="fkMoney" value="${bpid.money}"/>
  <input type="hidden" id="productTypeId" name="productTypeId" value="${bpid.productTypeId}"/>
  <div class="div_tiao"> &nbsp;&nbsp;评审意见</div>
  <table border="0"  cellpadding="0" cellspacing="0" align="center" width="460">
    <tr>
      <td height="20px" colspan="2" >产品总监评审意见：</td>
    </tr>
    <tr>
      <td nowrap="nowrap">1.付款时间是否符合</td>
      <td height="20px" nowrap="nowrap"><input type="checkbox" name="proMajIder0" id="proMajIder0" value="1" />
        符合&nbsp;&nbsp;
        <input type="checkbox" name="proMajIder0" id="proMajIder1" value="0" />
        不符合 </td>
    </tr>
    <tr>
      <td nowrap="nowrap">2.付款金额是否符合</td>
      <td height="20px" nowrap="nowrap"><input type="checkbox" name="proMajIder1" id="proMajIder2" value="1" />
        符合&nbsp;&nbsp;
        <input type="checkbox" name="proMajIder1" id="proMajIder3" value="0" />
        不符合 </td>
    </tr>
    <tr>
      <td nowrap="nowrap">3.付款类型是否符合</td>
      <td height="20px" nowrap="nowrap"><input type="checkbox" name="proMajIder2" id="proMajIder4" value="1" />
        符合&nbsp;&nbsp;
        <input type="checkbox" name="proMajIder2" id="proMajIder5" value="0" />
        不符合 </td>
    </tr>
    <tr>
      <td nowrap="nowrap">4.付款方式是否符合</td>
      <td height="20px" nowrap="nowrap"><input type="checkbox" name="proMajIder3" id="proMajIder6" value="1" />
        符合&nbsp;&nbsp;
        <input type="checkbox" name="proMajIder3" id="proMajIder7" value="0" />
        不符合 </td>
    </tr>
    <tr>
      <td width="320" nowrap="nowrap">5.供货商付款信息是否符合</td>
      <td height="20px" width="150" nowrap="nowrap"><input type="checkbox" name="proMajIder4" id="proMajIder8" value="1" />
        符合&nbsp;&nbsp;
        <input type="checkbox" name="proMajIder4" id="proMajIder9" value="0" />
        不符合 </td>
    </tr>
    <c:if test="${userRoleId == 10}">
      <tr>
        <td colspan="2" valign="top"><table width="98%" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap="nowrap">补充说明：</td>
              <td style="padding:5px 0 5px 0;"><textarea name="proMajText" id="proMajText" cols="60" rows="3"></textarea></td>
            </tr>
          </table></td>
      </tr>
      <tr>
        <td height="20px">签字：</td>
        <td>日期：</td>
      </tr>
    </c:if>
    <c:if test="${userRoleId != 10}">
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
    </c:if>
    <tr>
      <td height="20px" colspan="2">&nbsp;</td>
    </tr>
    <tr>
      <td height="20px" colspan="2">采购主管评审意见：</td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td height="20px"><input type="checkbox" name="buyManIdea" id="buyManIdea1" value="1" />
        符合&nbsp;&nbsp;
        <input type="checkbox" name="buyManIdea" id="buyManIdea0" value="0" />
        不符合 </td>
    </tr>
    <c:if test="${userRoleId == 11}">
      <tr>
        <td colspan="2" valign="top"><table width="98%" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap="nowrap">补充说明：</td>
              <td style="padding:5px 0 5px 0;"><textarea name="buyManText" id="buyManText" cols="60" rows="3"></textarea>
                &nbsp;</td>
            </tr>
          </table></td>
      </tr>
      <tr>
        <td height="20px">签字：</td>
        <td>日期：</td>
      </tr>
    </c:if>
    <c:if test="${userRoleId != 11}">
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
    </c:if>
    <tr>
      <td height="20px" colspan="2" >&nbsp;</td>
    </tr>
    <tr>
      <td height="20px" colspan="2">运营总监评审意见：</td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td height="20px"><input type="checkbox" name="opeMajIder" id="opeMajIder1" value="1" />
        同意&nbsp;&nbsp;
        <input type="checkbox" name="opeMajIder" id="opeMajIder0" value="0" />
        不同意 </td>
    </tr>
    <c:if test="${userRoleId == 16||userRoleId == 17}">
      <tr>
        <td colspan="2" valign="top"><table width="98%" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap="nowrap">补充说明：</td>
              <td style="padding:5px 0 5px 0;" ><textarea name="opeMajText" id="opeMajText" cols="60" rows="3"></textarea>
                &nbsp;</td>
            </tr>
          </table></td>
      </tr>
      <tr>
        <td height="20px">签字：</td>
        <td>日期：</td>
      </tr>
    </c:if>
    <c:if test="${userRoleId != 16&&userRoleId != 17}">
      <tr>
        <td colspan="2" valign="top"><table width="98%" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap="nowrap">补充说明：</td>
              <td style="padding:5px 0 5px 0;" >${tf:replaceBr(bpid.opeMajText)}&nbsp;</td>
            </tr>
          </table></td>
      </tr>
      <tr>
        <td height="20px">签字：${bpid.opeMajName}</td>
        <td>日期：${bpid.opeMajDate}</td>
      </tr>
    </c:if>
  </table>
  </form>
  </td>
  <td >&nbsp;</td>
  </tr>
  <tr>
    <td></td>
    <td height="50px" align="center" valign="bottom">
    <img id="btnTG" src="${ctx}/images/btnTG.gif" width="65" height="20" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
    <img id="btnWTG" src="${ctx}/images/btnWTG.gif" width="65" height="20" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
    <img id="backBtn" src="${ctx}/images/btnBack.gif" /></td>
    <td></td>
  </tr>
</table>
<br/>
</body>
</html>
