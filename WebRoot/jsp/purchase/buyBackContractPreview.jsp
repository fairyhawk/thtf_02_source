<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ taglib prefix="tf" uri="localhost" %>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>采购退货合同预览</title>
		<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
		<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/messages_cn.js"></script>

	    <style type="text/css"> 
		<!--
body{font-family:"宋体"; background-image:url(${ctx}/images/shuiyin.jpg); background-repeat:
repeat-y;background-position:top center;}		

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

<body><br/><br/><br/>
<c:if test="${requestScope.print}">
<OBJECT id="wb" height=0 width=0 classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 name="wb"></OBJECT>
</c:if>
<table border="0" cellpadding="0" cellspacing="0" align="center" width="650" class="tad">
	<tr>
		<th height="40" colspan="2" valign="top"><span class="STYLE1">${backContractPreviewDto.prodTypeName}采购退货合同</span></th>
	</tr>
	<tr>
		<td width="50%">供方：${backContractPreviewDto.supplierName}</td>
		<td>退货产品合同编号：${tf:replaceNull(backContractPreviewDto.productContractCode)}</td>
	</tr>
	<tr>
		<td>需方：${backContractPreviewDto.compName}</td>
		<td>退货公司合同编号：${tf:replaceNull(backContractPreviewDto.companyContractCode)}</td>
	</tr>
	<tr>
		<td>签订日期：${tf:replaceNull(backContractPreviewDto.buyBackContractDate)}</td>
		<td>签订地点：${tf:replaceNull(backContractPreviewDto.buyBackContractPlace)}</td>
	</tr>
	<tr>
		<td colspan="2"><table cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td width="32px">一、</td>
					<td>供需双方于<u>    ${tf:replaceNull(backContractPreviewDto.buyContractDate)}     </u>签订了《<u>${backContractPreviewDto.buyContractName}合同</u>》，合同编号<u>：  ${backContractPreviewDto.productContractCodeOfBuy}    </u>金额：<u><fmt:formatNumber value="${backContractPreviewDto.buyContractMoney}" pattern="#,##0.00000"/></u>。</td>
				</tr>
				<tr>
					<td width="32px">&nbsp;</td>
					<td>现双方协商一致对如下产品作退货处理。办理完相应退货手续后原合同金额相应变更。</td>
				</tr>
			</table></td>
	</tr>
	<tr>
		<td colspan="2"><table width="100%" cellpadding="0" cellspacing="0" class="tad_1">
				<tr>
					<td align="center"><strong>产品名称</strong></td>
					<td align="center"><strong>规格型号</strong></td>
					<td align="center" width="67"><strong>计量单位</strong></td>
					<td align="center" width="40"><strong>数量</strong></td>
					<td align="center" width="96"><strong>单价（元）</strong></td>
					<td align="center" width="96"><strong>金额（元）</strong></td>
				</tr>
				<logic:present name="prods">
      			    <bean:define id="total" value="0" type="java.lang.String"/>
        			<logic:iterate id="prod" name="prods" indexId="prodindex">
        			<bean:define id="total" value="${prod.money+total}" type="java.lang.String"/>
				<tr>
					<td >&nbsp;${prod.prodName}</td>
					<td>&nbsp;${prod.prodType}</td>
					<td>&nbsp;${prod.prodUnit}</td>
					<td align="right">${prod.backContractProdCount}&nbsp;</td>
					<td align="right"><fmt:formatNumber value="${prod.buyContractPrice}" pattern="#,##0.00000"/>&nbsp;</td>
					<td align="right">
						<fmt:formatNumber value="${prod.money}" pattern="#,##0.00000"/>&nbsp;
					</td>
				</tr>
				
				 </logic:iterate>
        </logic:present>
				<tr>
					<td colspan="5" align="left"><strong>合计人民币金额（大写）：${totalMoney}</strong></td>
					<td align="center" ><strong>合计</strong>：<fmt:formatNumber value="${total}" pattern="#,##0.00000"/></td>
				</tr>
			</table></td>
	</tr>
	<tr>
		<td colspan="2"><table cellpadding="0" cellspacing="0" width="100%">
				<td width="32px" valign="top">二、</td>
					<td valign="top" style="line-height:22px">
						退货时间、地点：<u>   ${tf:replaceNull(backContractPreviewDto.requestSendDate)}         ${tf:replaceNull(backContractPreviewDto.supAddress)}                 </u><br />
						退货方式：需方以快捷、安全、可靠的方式进行运输，并承担运费。
					</td>
			</table></td>
	</tr>
	<tr>
		<td colspan="2" valign="top"><table cellpadding="0" cellspacing="0" width="100%">
			<tr>
				<td width="32px" valign="top">三、</td>
				<td valign="top" style="line-height:22px">
				退货结算方式及期限：本合同按如下方式退货，如供方已向需方开具发票，需方应协助供方办理相应退票手续。<br />	
				<c:choose>
					<c:when test="${backContractPreviewDto.backWay==1 }">本合同签订后，需方应将货物发至供方指定地点，供方收到货物后5日内退还需方已付的货款。</c:when>
					<c:when test="${backContractPreviewDto.backWay==2 }">本合同签订后5日内，供方将相应货款退回需方账户，需方收到货款后将货物退至供方指定地点。</c:when>
					<c:when test="${backContractPreviewDto.backWay==3 }">需方尚未支付货款，本合同签订后，需方应将货物发至供方指定地点。</c:when>
					<c:when test="${backContractPreviewDto.backWay==4 }">需方已支付货款，供方尚未供货，本合同签订后原合同金额相应变更，供方应在本合同签订后5日内将相应货款退回需方账户。</c:when>
					<c:when test="${backContractPreviewDto.backWay==5 }">供方未供货，需方未支付货款，本合同签订后原合同金额相应变更，其他未尽条款不变。</c:when>
				</c:choose>
				<br />
			</tr>
		</table></td>
	</tr>
	<tr>
		<td colspan="2"><table cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td width="32px" valign="top">四、</td>
					<td valign="top" style="line-height:22px">
					违约责任：如需方在约定之日未按合同约定将货物发至供方指定地点，供方有权按退货总额的1% /天收取违约金；<br />
					如供方未按合同约定退还货款的，需方有权按退货总额的1% /天收取违约金；
					</td>
				</tr>
			</table></td>
	</tr>
	<tr>
		<td colspan="2"><table cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td width="32px" valign="top">五、</td>
					<td valign="top" style="line-height:22px">争议解决方式：对于本合同执行过程中发生的纠纷，供需双方本着友好合作的态度协商解决；协商不成，任何一方有权向合同签订地的人民法院提起诉讼；</td>
				</tr>
			</table></td>
	</tr>
	<tr>
		<td colspan="2"><table cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td width="32px">六、</td>
					<td style="line-height:22px">本合同一式四份，双方各执二份，自双方签字盖章之日起生效，传真件有效。</td>
				</tr>
			</table></td>
	</tr>
	<tr>
		<td colspan="2"><table width="100%" cellpadding="0" cellspacing="0" class="tad02">
				<tr>
					<td align="center" width="50%" class="td_01">供   方</td>
					<td align="center" class="td_02">需   方</td>
				</tr>
				<tr>
					<td class="td_01">卖方单位（章）：${backContractPreviewDto.supplierName}</td>
					<td class="td_02">买方单位（章）：${backContractPreviewDto.compName}</td>
				</tr>
				<tr>
					<td class="td_01">委托代理人：${tf:replaceNull(backContractPreviewDto.supLinkmanName)}</td>
					<td class="td_02">委托代理人：${tf:replaceNull(backContractPreviewDto.prodSuperintendentName)}</td>
				</tr>
				<tr>
					<td class="td_01">电话：${tf:replaceNull(backContractPreviewDto.supLinkmanTel)}</td>
					<td class="td_02">电话：${tf:replaceNull(backContractPreviewDto.prodSuperintendentTel)}</td>
				</tr>
				<tr>
					<td class="td_01">传真：${tf:replaceNull(backContractPreviewDto.supLinkmanFax)}</td>
					<td class="td_02">传真：${tf:replaceNull(backContractPreviewDto.prodDeptFax)}</td>
				</tr>
				<tr>
					<td class="td_01">开户行：${tf:replaceNull(backContractPreviewDto.supInvoiceBankName)}</td>
					<td class="td_02">开户行：${tf:replaceNull(backContractPreviewDto.comRemitBankName)}</td>
				</tr>
				<tr>
					<td  valign="top" style="border-right:1px solid #CCCCCC">&nbsp;帐号：${tf:replaceNull(backContractPreviewDto.supInvoiceBankAccount)}</td>
					<td  class="td_02">帐号：${tf:replaceNull(backContractPreviewDto.compRemitBankAccount)}</td>
				</tr>				
			</table></td>
	</tr>
</table>
<c:if test="${requestScope.print}">
		<div style="text-align:center;" id="DivPrint">
		    <a href="#" id="print"><img src="/cms/images/btnPrint.gif" width="65" height="20" border="0" /></a>&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<img src="${ctx}/images/btnPrintSZ.gif" width="88" height="20" class="buttonNoPrint" onClick="javascript:printSetup();"/>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<img src="${ctx}/images/btnPrintYL.gif" class="buttonNoPrint" onClick="javascript:printPreview();" />
		</div>
</c:if>
</body>
</html>
