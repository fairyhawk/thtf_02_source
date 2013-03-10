<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ taglib prefix="tf" uri="localhost" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>销售退货合同预览</title>
<style type="text/css">
<!--
body{font-family:"宋体"; background-image:url(${ctx}/images/shuiyin.jpg); background-repeat:
repeat-y;background-position:top center;}

.tad_1 {
	border-left:1px solid #000000;
	border-bottom:1px solid #000000;
	font-size:14px
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
	font-weight:bold;
}
.td_02 {
	border-bottom:1px solid #000000;
}
.STYLE1 {
	font-size: 24px;
	font-weight: bold;
}
.td_011 {border-bottom:1px solid #CCCCCC; border-right:1px solid #CCCCCC; padding-left:5px;}
.td_021 {border-bottom:1px solid #CCCCCC;padding-left:8px;}
-->
</style>
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<script>
	$(document).ready(function(){
		$("#print").click(function(){
			$("#DivPrint").hide();
			wb.execwb(6,6);
			$("#DivPrint").show();
		});
		  
		 });
		 function printPreview(){ 
		 $("#DivPrint").hide();   
			// 打印页面预览    
			wb.execwb(7,1);
			$("#DivPrint").show();       
		}    
		function printSetup(){    
			// 打印页面设置    
			wb.execwb(8,1);    
		}
</script>
</head>
<body>
<br/>
<br/>
<br/>
<c:if test="${param.print}">
<OBJECT id="wb" height=0 width=0 classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 name="wb"></OBJECT>
</c:if>
<table border="0" cellpadding="0" cellspacing="0" align="center" width="650" class="tad">
  <tr>
    <th height="40" colspan="2" valign="top"><span class="STYLE1">${salesBackContract.productTypeName}销售退货合同</span></th>
  </tr>
  <tr>
    <td width="50%">供方：${company.name}</td>
    <td>退货产品合同编号：${tf:replaceNull(salesBackContract.productContractCodeOfBack)}</td>
  </tr>
  <tr>
    <td>需方：${salesBackContract.customerName}</td>
    <td>退货公司合同编号：${tf:replaceNull(salesBackContract.companyContractCodeOfBack)}</td>
  </tr>
  <tr>
    <td>签订日期：${tf:replaceNull(salesBackContract.date)}</td>
    <td>签订地点：${tf:replaceNull(salesBackContract.signPlace)}</td>
  </tr>
  <tr>
    <td colspan="2"><table cellpadding="0" cellspacing="0" width="100%">
        <td width="32px" valign="top">一、</td>
          <td>供需双方于<u>${tf:replaceNull(salesBackContract.signDate)}</u>签订了《<u>${salesBackContract.productTypeName}产品销售</u>》合同，产品合同编号：<u>${salesBackContract.productContractCode}</u>
          <BR />
          金额（元）：<u>                    <fmt:formatNumber value="${salesBackContract.contractMoney}" type="number"
													pattern="#,##0.00" />
         </u>。</td>
      </table></td>
  </tr>
  <tr>
    <td colspan="2">&nbsp;现双方协商一致对如下产品作退货处理。办理完相应退货手续后原合同金额相应变更。</td>
  </tr>
  <tr>
    <td colspan="2"><table width="100%" cellpadding="0" cellspacing="0" class="tad_1">
        <tr>
          <td align="center"><strong>产品名称</strong></td>
          <td align="center"><strong>规格型号</strong></td>
          
          <td align="center" width="59"><strong>计量单位</strong></td>
          <td align="center" width="40"><strong>数量</strong></td>
          <td align="center" width="89"><strong>单价（元）</strong></td>
          <td align="center" width="89"><strong>金额（元）</strong></td>
        </tr>
       <c:forEach var="saleBackContractDetail" items="${salesBackContractDetailList}" > 
        	<tr>
         	 <td nowrap="nowrap">&nbsp;${saleBackContractDetail.productName}</td>
          	 <td nowrap="nowrap">&nbsp;${saleBackContractDetail.productType}T</td>
          	
          	 <td align="left" nowrap="nowrap">&nbsp;${saleBackContractDetail.productUnit}</td>
          	 <td align="right" nowrap="nowrap">${saleBackContractDetail.backContractCount}&nbsp;</td>
          	 <td align="right" nowrap="nowrap"><fmt:formatNumber value="${saleBackContractDetail.sellPrice}" type="number"
													pattern="#,##0.00" />&nbsp;</td>
          	 <td align="right" nowrap="nowrap"><fmt:formatNumber value="${saleBackContractDetail.backContractMoney}" type="number"
													pattern="#,##0.00" />&nbsp;</td>
       	 	</tr>
        </c:forEach>
       
        <tr>
          <td colspan="4"><strong>合计人民币金额（大写）：${RMB}</strong></td>
          <td align="center" nowrap="nowrap"><strong>合 计：</strong></td>
          <td align="right" nowrap="nowrap">
          <fmt:formatNumber value="${salesBackContract.money}" type="number"
													pattern="#,##0.00" />&nbsp;</td>
        </tr>
      </table></td>
  </tr>
  <tr>
    <td colspan="2"><table cellpadding="0" cellspacing="0" width="100%">
        <td width="32px" valign="top"><div style="padding-top:4px">二、</div></td>
          <td style="line-height:22px">需方应保证所退货物为供方向需方销售货物，包装完好，未拆封，对于已开封或者非供方所销售货物，不允许退货。</td>
      </table></td>
  </tr>
  <tr>
    <td colspan="2"><table cellpadding="0" cellspacing="0" width="100%">
      <tr>
        <td width="32" rowspan="4" valign="top"><div style="padding-top:5px">三、</div></td>
        <td style="line-height:22px"><table cellpadding="0" cellspacing="0" width="100%">
            <tr>
              <td style="padding:0px 0px 0px 0px; width:112px">最晚退货时间：</td>
              <td style=""><u> &nbsp;${tf:replaceNull(salesBackContract.backDate)}&nbsp;</u></td>
              <td>&nbsp;</td>
            </tr>
        </table></td>
      </tr>
      <tr>
        <td style="line-height:22px"><table cellpadding="0" cellspacing="0" width="100%">
            <tr>
              <td style="padding:0px 0px 0px 0px; width:147px">货物接收单位名称：</td>
              <td ><u>&nbsp;${tf:replaceNull(salesBackContract.receiverUnitName)}&nbsp;</u></td>
            </tr>
        </table></td>
      </tr>
      <tr>
        <td style="line-height:22px"><table cellpadding="0" cellspacing="0" width="100%">
            <tr>
              <td style="padding:0px 0px 0px 0px; width:80px">发货地址：</td>
              <td><u>&nbsp;${tf:replaceNull(salesBackContract.receiverAddress)}&nbsp;</u></td>
              <td style="padding:0px 0px 0px 0px; width:50px">邮编：</td>
              <td > <u>&nbsp;${tf:replaceNull(salesBackContract.receiverPostcode)}&nbsp;</u></td>
            </tr>
        </table></td>
      </tr>
      <tr>
        <td style="line-height:22px"><table cellpadding="0" cellspacing="0" width="100%">
            <tr>
              <td style="padding:0px 0px 0px 0px; width:65px">联系人：</td>
              <td ><u>&nbsp;${tf:replaceNull(salesBackContract.receiverLinkman)}&nbsp;</u></td>
              <td style="padding:0px 0px 0px 0px; width:48px">电话：</td>
              <td ><u>&nbsp;${tf:replaceNull(salesBackContract.receiverTel)}&nbsp;</u></td>
              <td style="padding:0px 0px 0px 0px; width:50px">手机：</td>
              <td ><u>&nbsp;${tf:replaceNull(salesBackContract.receiverMobile)}</u></td>
            </tr>
        </table></td>
      </tr>
      <tr>
        <td></td>
        <td style="line-height:22px">退货方式：需方以快捷、安全、可靠的方式进行运输，并承担运费。</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td colspan="2"><table cellpadding="0" cellspacing="0" width="100%">
      <tr>
        <td width="32" valign="top"><div style="padding-top:3px">四、</div></td>
        <td width="616" style="line-height:22px">
        <table cellpadding="0" cellspacing="0" width="100%">
            <tr>
              <td style="padding:0px 0px 0px 0px; ">退货结算方式及期限：本合同按如下方式退货，如供方已向需方开具发票，需方应先协助供方办理相应退票手续。</td>
            </tr>

        </table>
        
          <table cellpadding="0" cellspacing="0" width="100%">
            <tr>
            	<c:choose>
            		<c:when test="${salesBackContract.backWay==1 }">
            			<td style="padding:0px 0px 0px 0px; ">本合同签订之日起5日内，需方应将货物发至供方指定地点，供方收到货物后5日内对货物进行检测，供方检测通过后5日内退还需方已付的相应货款。</td>
            		</c:when>
            		<c:when test="${salesBackContract.backWay==2 }">
            			<td style="padding:0px 0px 0px 0px; "><p align="left">需方已支付货款，本合同签订后供方向需方退还已付的相应货款，款到后5日内需方将货物发至供方指定地点。 </p></td>
            		</c:when>
            		<c:when test="${salesBackContract.backWay==3 }">
            			<td style="padding:0px 0px 0px 0px; ">需方尚未支付货款，本合同签订之日起5日内，需方应将货物发至供方指定地点。</td>
            		</c:when>
            		<c:when test="${salesBackContract.backWay==4 }">
            			<td style="padding:0px 0px 0px 0px; ">供方尚未供货，本合同签订后原合同金额相应变更，如需方已支付货款，供方应将相应货款退回需方账户。</td>
            		</c:when>
            	</c:choose>
            </tr>
          </table>
		</td>
      </tr>

    </table></td>
  </tr>
  <tr>
    <td colspan="2"><table cellpadding="0" cellspacing="0" width="100%">
      <tr>
        <td width="32px" valign="top"><div style="padding-top:3px">五、</div></td>
        <td style="line-height:22px">违约责任：如需方在约定之日未按合同约定将货物发至供方指定地点，供方有权按退货总额的1% /天收取违约金；本合同签订后15日内，需方还未将上述货物按合同约定退至供方指定地点，本合同作废，需方除应支付货款外，还应向供方支付退货总额20%的违约金。如供方未按合同约定退还货款的，需方有权按退货总额的1% /天收取违约金；</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td colspan="2"><table cellpadding="0" cellspacing="0" width="100%">
      <tr>
        <td width="32px" valign="top"><div style="padding-top:2px">六、</div></td>
        <td style="line-height:22px">争议解决方式：对于本合同执行过程中发生的纠纷，供需双方本着友好合作的态度协商解决；协商不成，任何一方有权向合同签订地的人民法院提起诉讼。</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td colspan="2"><table cellpadding="0" cellspacing="0" width="100%">
      <tr>
        <td width="32px" valign="top"><div style="padding-top:2px">七、</div></td>
        <td style="line-height:22px">本合同一式四份，双方各执二份，自双方签字盖章之日起生效，传真件有效。</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td colspan="2"><table width="100%" cellpadding="0" cellspacing="0" class="tad02">
      <tr>
        <td align="center" width="50%" class="td_011">供   方</td>
        <td align="center" class="td_021">需   方</td>
      </tr>
      <tr>
        <td class="td_011">卖方单位（章）：${company.name}</td>
        <td class="td_021">买方单位（章）：${salesBackContract.customerName}</td>
      </tr>
      <tr>
        <td class="td_011">委托代理人：${salesBackContract.userName}</td>
        <td class="td_021">委托代理人：${salesBackContract.linkman}</td>
      </tr>
      <tr>
        <td class="td_011">电话：${tf:replaceNull(salesBackContract.userTel)}</td>
        <td class="td_021">电话：${tf:replaceNull(salesBackContract.linkmanTel)}</td>
      </tr>
      <tr>
        <td class="td_011">传真：${tf:replaceNull(salesBackContract.productDeptFax)}</td>
        <td class="td_021">传真：${tf:replaceNull(salesBackContract.linkmanFax)}</td>
      </tr>
      <tr>
        <td class="td_011">开户行：${company.remit_bank_name}</td>
        <td class="td_021">开户行：${salesBackContract.invoiceBankName}</td>
      </tr>
      <tr>
        <td rowspan="2" valign="top" style="border-right:1px solid #CCCCCC">&nbsp;帐号：${salesBackContract.productDeptAccount}</td>
        <td  class="td_021">帐号：${salesBackContract.invoiceBankAccount}</td>
      </tr>
      <tr>
        <td >&nbsp;税号：${salesBackContract.customerTaxNumber}</td>
      </tr>
    </table></td>
  </tr>
</table>
<div style="text-align:center;">

    <c:if test="${requestScope.print}">   </c:if><!-- 打印 -->		
    	<div id="DivPrint">
			<a href="#" id="print" ><img style="border-width:0px" src="${ctx}/images/btnPrint.gif" width="65" height="20" /></a>&nbsp;&nbsp;
    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<img src="${ctx}/images/btnPrintSZ.gif" width="88" height="20" class="buttonNoPrint" onClick="javascript:printSetup();"/>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<img src="${ctx}/images/btnPrintYL.gif" class="buttonNoPrint" onClick="javascript:printPreview();" />
    	</div>
  
</body>
</html>
