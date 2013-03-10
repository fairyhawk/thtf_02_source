<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib prefix="tf" uri="localhost" %>
<head>
<title>付款预览</title>
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
	@media   print   {   
  		.buttonNoPrint {display:none;}   
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
			});
function MM_jumpMenu(targ,selObj,restore){ //v3.0
  eval(targ+".location='"+selObj.options[selObj.selectedIndex].value+"'");
  if (restore) selObj.selectedIndex=0;
}
//-->
	$(function(){
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
		//金额计算
        var appointMoney,moneyE=0,moneyE1=0,sumMoney = 0;
		$('#inStock tr').each(function(i){
			appointMoney = rmoney($(this).children("td:eq(13)").text())*100;
			moneyE = moneyE+appointMoney;
		}); 
		$('#appointMoneyE').text(formatMoney(moneyE/100,5));
		$('#buyContract tr').each(function(i){
			appointMoney = rmoney($(this).children("td:eq(10)").text())*100;
			moneyE1 = moneyE1+appointMoney;
		}); 
		$('#advanceMoneyE').text(formatMoney(moneyE1/100,5));
	});
	function printPreview(){   
		// 打印页面预览    
		wb.execwb(7,1);
	}    
	function printSetup(){    
		// 打印页面设置    
		wb.execwb(8,1);    
	}
	function  printIt(img){ 
		//打印后按钮变灰 不可用
		img.style.filter=" Alpha(opacity=50);-moz-opacity:.1;opacity:0.1";
			
  		img.disabled="true";
		
		wb.execwb(6,6);
		var id = "${id}";
		var status = "${bpid.status}";
		var paymentWay = "${bpid.paymentWay}";
		$.post("buyPaymentPrint.do?id="+id+"&status="+status+"&paymentWay="+paymentWay); 
		
	} 
	//提交后获取后台新建结果
		function waitResult(err){
		  if(err){
		  	//打印处理
		  	alert("打印成功！");
		  }else{
		    alert("评审失败！");
		  }
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
<OBJECT id="wb" height=0 width=0 classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 name="wb"></OBJECT>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td >&nbsp;</td>
    <td><br />
      <div class="div_tiao"> &nbsp;&nbsp;供货商信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" height="18px" width="20%">供货商名称${bpid.id}</td>
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
             <td class="td_01" width="20%"></td>
         	 <td class="td_02" width="30%"></td>
          </c:if>
        </tr>
        <tr>
          <td class="td_01" height="18px">付款金额</td>
          <td class="td_02" ><fmt:formatNumber value="${bpid.money}" pattern="#,##0.00000#"/>&nbsp;元</td>
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
      <br/>
      <div class="div_tiao"> &nbsp;&nbsp;产品信息 </div>
      <div style="text-align:right; width:100%">单位：元</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table" style="border-left:1px solid #FFFFFF;">
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
		<logic:present name="productList">
			<logic:iterate id="product" name="productList" indexId="index">
				<tr>
		            <td width="40" nowrap="nowrap" style="border-left:1px solid #c2c2c2;" height="18">${index+1}&nbsp;</td>
		            <td nowrap="nowrap" >${product.inStockId}&nbsp;</td>
		            <td nowrap="nowrap" >${product.productContractCode}&nbsp;</td>
		            <td nowrap="nowrap" >${product.companyContractCode}&nbsp;</td>
		            <td nowrap="nowrap" >${product.productCode}&nbsp;</td>
		            <td nowrap="nowrap" >${product.productName}&nbsp;</td>
		            <td nowrap="nowrap" >${product.productType}&nbsp;</td>
		            <td nowrap="nowrap">${product.productUnit}&nbsp;</td>
		            <td nowrap="nowrap"  ><fmt:formatNumber value="${product.buyContractPrice}" pattern="#,##0.00000#"/>&nbsp;</td>
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
            <td colspan="8" nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF"> 指定金额合计 </td>
            <td nowrap="nowrap"  style=" border:1px solid #FFFFFF;  background-color:#FFFFFF; text-align:right"><span id="appointMoneyE"></span>元</td>
          </tr>
        </tfoot>
      </table>
      <br />
      <div class="div_tiao"> &nbsp;&nbsp;特殊说明</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_03" width="20%">特殊说明</td>
          <td class="td_04" style="padding:5px 100px 5px 5px">&nbsp;${bpid.text}</td>
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
          	<input type="checkbox" name="checkbox1" id="checkbox1" disabled="disabled" <c:if test="${ProMajIder[0]==1}">checked="checked"</c:if>/>符合&nbsp;&nbsp;
            <input type="checkbox" name="checkbox2" id="checkbox2" disabled="disabled" <c:if test="${ProMajIder[0]==0}">checked="checked"</c:if>/>不符合 
          </td>
        </tr>
        <tr>
          <td nowrap="nowrap">2.付款金额是否符合</td>
          <td height="20px" nowrap="nowrap">
            <input type="checkbox" name="checkbox3" id="checkbox3" disabled="disabled" <c:if test="${ProMajIder[1]==1}">checked="checked"</c:if>/>符合&nbsp;&nbsp;
            <input type="checkbox" name="checkbox4" id="checkbox4" disabled="disabled" <c:if test="${ProMajIder[1]==0}">checked="checked"</c:if>/>不符合 
          </td>
        </tr>
        <tr>
          <td nowrap="nowrap">3.付款类型是否符合</td>
          <td height="20px" nowrap="nowrap">
          	<input type="checkbox" name="checkbox5" id="checkbox5" disabled="disabled" <c:if test="${ProMajIder[2]==1}">checked="checked"</c:if>/>符合&nbsp;&nbsp;
            <input type="checkbox" name="checkbox6" id="checkbox6" disabled="disabled" <c:if test="${ProMajIder[2]==0}">checked="checked"</c:if>/>不符合 
          </td>
        </tr>
        <tr>
          <td nowrap="nowrap">4.付款方式是否符合</td>
          <td height="20px" nowrap="nowrap">
	          	<input type="checkbox" name="checkbox7" id="checkbox7" disabled="disabled" <c:if test="${ProMajIder[3]==1}">checked="checked"</c:if>/>符合&nbsp;&nbsp;
	            <input type="checkbox" name="checkbox8" id="checkbox8" disabled="disabled" <c:if test="${ProMajIder[3]==0}">checked="checked"</c:if>/>不符合
		  </td>
        </tr>
        <tr>
          <td width="320" nowrap="nowrap">5.供货商付款信息是否符合</td>
          <td height="20px" width="150" nowrap="nowrap">
            <input type="checkbox" name="checkbox9" id="checkbox9" disabled="disabled" <c:if test="${ProMajIder[4]==1}">checked="checked"</c:if>/>符合&nbsp;&nbsp;
            <input type="checkbox" name="checkbox10" id="checkbox10" disabled="disabled" <c:if test="${ProMajIder[4]==0}">checked="checked"</c:if>/>不符合 
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
	      	 <input type="checkbox" name="buyManIdea1" id="buyManIdea1" disabled="disabled" <c:if test="${bpid.buyManIdea==1}">checked="checked"</c:if>>符合&nbsp;&nbsp;
	         <input type="checkbox" name="buyManIdea0" id="buyManIdea0" disabled="disabled" <c:if test="${bpid.buyManIdea==0}">checked="checked"</c:if>/>不符合
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
	      	 <input type="checkbox" name="opeMajIder1" id="opeMajIder1" disabled="disabled" <c:if test="${bpid.opeMajIder==1}">checked="checked"</c:if>/>符合&nbsp;&nbsp;
		     <input type="checkbox" name="opeMajIder0" id="opeMajIder0" disabled="disabled" <c:if test="${bpid.opeMajIder==0}">checked="checked"</c:if>/>不符合
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
   		<img id="printBtn" src="/cms/images/btnPrint.gif" width="65" height="20" class="buttonNoPrint" onClick="javascript:printIt(this);"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<img src="/cms/images/btnPrintSZ.gif" width="88" height="20" class="buttonNoPrint" onClick="javascript:printSetup();"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<img src="/cms/images/btnPrintYL.gif" class="buttonNoPrint" onClick="javascript:printPreview();" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    </td>
    <td></td>
  </tr>
</table>
</body>
</html>
