<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ taglib prefix="tf" uri="localhost" %>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>采购合同预览</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
		<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/messages_cn.js"></script>

	    <style type="text/css"> 
		<!--
		    body{font-family:"宋体"; background-image:url(${ctx}/images/shuiyin.jpg); background-repeat:
			repeat-y;background-position:top center;}
			.tad_1{border-left:1px solid #000000; border-bottom:1px solid #000000; }
			.tad_1 td{border-right:1px solid #000000;border-top:1px solid #000000;color:#000000;white-space:nowrap; padding:3px 0px 3px 0px;}
			.td_f{font-size:14px;}
			tr{height:20px;}
			.tad td{padding-bottom:5px;}
			.tad02{ border:1px solid #CCCCCC;}
			.td_001{border-bottom:1px solid #CCCCCC; border-right:1px solid #CCCCCC; padding-left:5px;padding-top:4px;}
			.td_002{border-bottom:1px solid #CCCCCC;padding-left:8px;padding-top:3px;}
		.STYLE1 {
			font-size: 24px;
			font-weight: bold;
		} 
  		@media   print   {   
  		.buttonNoPrint {display:none;}   
  		}   
		-->
		</style>
	</head>
<script> 
	$(document).ready(function(){
		$("#print").click(function(){
			wb.execwb(6,6);
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
		<th height="40" colspan="2" valign="top"><span class="STYLE1">${buyContractProducts[0].prodTypeName}采购合同</span></th>
	</tr>
	<tr> 
		<td width="50%">供方：${tf:replaceNull(buyContractPreviewDto.supplierName)}</td>
		<td>产品合同编号：${tf:replaceNull(buyContractPreviewDto.productContractCode)}</td>
	</tr>
	<tr> 
		<td>需方：${buyContractPreviewDto.companyName}</td>
		<td>公司合同编号：${tf:replaceNull(buyContractPreviewDto.companyContractCode)}</td>
	</tr>
	<tr>
		<td>签订日期：${tf:replaceNull(buyContractPreviewDto.buyContractDate)}</td>
		<td>签订地点：${tf:replaceNull(buyContractPreviewDto.place)}</td>
	</tr>
	<tr>
		<td colspan="2">
			<table cellpadding="0" cellspacing="0" width="100%">
						<td width="27px">1.</td>
				<td>本合同经供需双方协商，按《中华人民共和国合同法》签订并信守下列条款：</td>
			</table>		</td>
	</tr>
	<tr>
		<td colspan="2">
			<table width="100%" cellpadding="0" cellspacing="0" class="tad_1">
				<tr>
					<td align="center" width="35"><strong>序号</strong></td>
					<td align="center"><strong>产品名称</strong></td>
					<td align="center"><strong>品牌</strong></td>
					<td align="center"><strong>规格型号</strong></td>
					<td align="center" width="40"><strong>单位</strong></td>
					<td align="center" width="40"><strong>数量</strong></td>
					<td align="center" width="89"><strong>单价（含税）</strong></td>
					<td align="center" width="89"><strong>总价（含税）</strong></td>
				</tr>
				 <logic:present name="buyContractProducts">
        <bean:define id="total" value="0" type="java.lang.String"/>
        <logic:iterate id="buyContractProduct" name="buyContractProducts" indexId="buyContractProductindex">
        <bean:define id="total" value="${buyContractProduct.buyPrice*buyContractProduct.buyCount+total}" type="java.lang.String"/>
				<tr>
					<td align="right">${buyContractProductindex+1}&nbsp;</td>
					<td>&nbsp;${buyContractProduct.prodName}</td>
					<td>&nbsp;${buyContractProduct.prodTypeName}</td>
					<td>&nbsp;${buyContractProduct.prodType}</td>
					<td >${buyContractProduct.prodUnit}&nbsp;</td>
					<td align="right">${buyContractProduct.buyCount}&nbsp;</td>
					<td align="right">
						<fmt:formatNumber value="${buyContractProduct.buyPrice}" pattern="#,##0.00000"/>&nbsp;
					</td>
					<td align="right">
						<fmt:formatNumber value="${buyContractProduct.buyMoney}" pattern="#,##0.00000"/>&nbsp;
					</td>
				</tr>
				 </logic:iterate>
        </logic:present>		
			</table>		</td>
	</tr>
	<tr>
		<td colspan="2">&nbsp;&nbsp;总价：<u>  ￥<fmt:formatNumber value="${total}" pattern="#,##0.00000"/>      </u>元（大写：<u>  ${totalMoney}                 </u>）</td>
	</tr>
	<tr>
		<td colspan="2">
			<table cellpadding="0" cellspacing="0" width="100%">
					<td width="27px" valign="top">2.</td>
				<td style="line-height:22px">质量标准：所有产品均为全新原厂原装正品，原厂包装，外观无瑕疵，标识齐全。供方保证产品质量全部符合<u>    ${tf:replaceNull(buyContractPreviewDto.quality)}    </u>标准。</td>
			</table>		</td>
	</tr>
	<tr>
		<td colspan="2"><table cellpadding="0" cellspacing="0" width="100%">
			<tr>
				<td width="27px" valign="top">3.</td>
				<c:if test="${!showAdjunct}">
					<td valign="top" style="line-height:22px">单一交货地点：<u>    ${tf:replaceNull(buyContractReceivings.address)}       </u>   联系人及电话：<u>  ${buyContractReceivings.linkman}(${tf:replaceNull(buyContractReceivings.tel)})    </u></td>
				</c:if>
				<c:if test="${showAdjunct}">
					<td valign="top" style="line-height:22px">多个交货地址。 &nbsp;（详见合同附件）</td> 
					</c:if>
			</tr>
		</table></td>
	</tr>
	<tr>
		<td colspan="2"><table cellpadding="0" cellspacing="0" width="100%">
			<tr>
				<td width="27px" valign="top">4.</td>
				<td valign="top" style="line-height:22px">交货期限：<u> ${tf:replaceNull(buyContractPreviewDto.requestDate)}  </u>，
					<c:if test="${buyContractPreviewDto.deliveryTerms == 0}">允许分批交货。 </c:if>
					
					 <c:if test="${buyContractPreviewDto.deliveryTerms == 1}">不允许分批交货。</c:if>
					</td>
			</tr>
		</table></td>
	</tr>
	<tr>
		<td colspan="2"><table cellpadding="0" cellspacing="0" width="100%">
			<tr>
				<td width="27px" valign="top">5.</td>
				<td valign="top" style="line-height:22px">运     输：
										<c:if test="${buyContractPreviewDto.transportWay == 1}">自提 </c:if>
									
										<c:if test="${buyContractPreviewDto.transportWay == 2}">快递 </c:if> 
									
										<c:if test="${buyContractPreviewDto.transportWay == 3}">汽运</c:if> 
									
										<c:if test="${buyContractPreviewDto.transportWay == 4}">铁运 </c:if> 
									
										<c:if test="${buyContractPreviewDto.transportWay == 5}">航空</c:if>
									 
										<c:if test="${buyContractPreviewDto.transportWay == 6}">海运</c:if> 
									 
										<c:if test="${buyContractPreviewDto.transportWay == 7}">中铁</c:if> 
									 
										<c:if test="${buyContractPreviewDto.transportWay == 8}">市内送货</c:if> 
									 
										<c:if test="${buyContractPreviewDto.transportWay == 9}">市内快递</c:if>
									 
									，运费及保险由供方负担。</td>
			</tr>
		</table></td>
	</tr>
	<tr>
		<td colspan="2"><table cellpadding="0" cellspacing="0" width="100%">
			<tr>
				<td width="27px" valign="top">6.</td>
				<td valign="top" style="line-height:22px">验    收：到货后需方对货物的品种、型号、规格、数量进行验收，如发现与本合同规定的条件不符，供方须在到货后 <u>${tf:replaceNull(buyContractPreviewDto.checkLimit)}日</u>内无条件为需方换货。<br />
					验收通过不能排除供方对无法立即发现的由于设计、工艺或材料的缺陷而发生的任何不足或故障的责任。</td>
			</tr>
		</table></td>
	</tr>
	<tr>
		<td colspan="2"><table cellpadding="0" cellspacing="0" width="100%">
			<tr>
				<td width="27px" valign="top">7.</td>
				<td valign="top" style="line-height:22px">付     款：
					<c:if test="${buyContractPreviewDto.paymentType==0}" >
					本合同签订后<u> ${tf:replaceNull(buyContractPreviewDto.contractPaymentTime)} </u>个工作日内，需方向供方支付<u> <fmt:formatNumber value="${total}" pattern="#,##0.00000"/> </u>元。在支付货款前，需方应先收到供方提供的同等金额的增值税发票（税率17%）。<br />
					</c:if>
					<c:if test="${buyContractPreviewDto.paymentType==1}" >
					<br />1)	&nbsp;本合同签订后<u> ${tf:replaceNull(buyContractPreviewDto.contractPaymentTime)} </u>个工作日内，需方向供方支付<u> <fmt:formatNumber value="${buyContractPreviewDto.prepayMoney}" pattern="#,##0.00000"/> </u>元。在支付货款前，需方应先收到供方提供的同等金额的增值税发票（税率17%）。<br />
					2) &nbsp;货到需方指定地点后按合同第６款进行验收，验收合格后<u>  ${tf:replaceNull(buyContractPreviewDto.sendPaymentTime)} </u>个工作日内付清货款。在支付货款前，需方应先收到供方提供的同等金额的增值税发票（税率17%）。				</td>
					</c:if>
					<c:if test="${buyContractPreviewDto.paymentType==2}" >
					货到需方指定地点后按合同第６款进行验收，验收合格后<u>  ${tf:replaceNull(buyContractPreviewDto.sendPaymentTime)} </u>个工作日内付清货款。在支付货款前，需方应先收到供方提供的同等金额的增值税发票（税率17%）。				</td>
					</c:if>
			</tr>
		</table></td>
	</tr>
	<tr>
		<td colspan="2"><table cellpadding="0" cellspacing="0" width="100%">
			<tr>
				<td width="27px" valign="top">8.</td>
				<td style="line-height:22px">保    证：供方保证提供相应服务支持以及售后服务；质保期：<u> ${tf:replaceNull(buyContractPreviewDto.protectLimit)} </u>。<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;供方保证提供的产品不因所有权或知识产权瑕疵受到任何第三方追索。				</td>
			</tr>
		</table></td>
	</tr>
	<tr>
		<td colspan="2"><table cellpadding="0" cellspacing="0" width="100%">
			<tr>
				<td width="27px" valign="top">9.</td>
				<td style="line-height:22px">违约责任：1）供方每拖延付货一天扣除该批货款3‰的违约金，但违约金最高不超过该批货款的5%。若需方因所有权或知识产权瑕疵而受到追索，供方应负担需方因此而支付的一切费用，并且赔偿需方因此而遭受的一切损失；供方不提供相应服务支持以及售后服务的，视为违约，应负担需方因此而遭受的损失并支付相关费用。（以上费用包括但不限于律师费、交通费、资料费、食宿费等）。<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2）需方每拖延付款一天支付该延期货款3‰的违约金。但违约金最高不超过该延期货款的5%。</td>
			</tr>
		</table></td>
	</tr>
	<tr>
		<td colspan="2"><table cellpadding="0" cellspacing="0" width="100%">
			<tr>
				<td width="105px" valign="top">10.其它事宜：</td>
				<td style="line-height:22px">本合同一式四份，双方各执二份，在法律上具有同等效力。传真件有效。<br />本合同双方签字盖章后生效。<br />与本合同有关的一切争议，双方应友好协商解决，协商不成，任何一方可向需方所在地人民法院提起诉讼。
				</td>
			</tr>
		</table></td>
	</tr>
	<tr> 
		<td colspan="2">
			<table width="100%" cellpadding="0" cellspacing="0" class="tad02">
				<tr>
					<td width="50%" class="td_001">供方：${buyContractPreviewDto.supplierName}</td>
					<td class="td_002">需方：${buyContractPreviewDto.companyName}</td>
				</tr>
				<tr>
					<td class="td_001">签署人签字：${buyContractPreviewDto.linkmanName}</td>
					<td class="td_002">签署人签字：${buyContractPreviewDto.prodSuperintendentName}</td>
				</tr>
				<tr>
					<td class="td_001">开户行：${buyContractPreviewDto.remitBankName}</td>
					<td class="td_002">开户行：${buyContractPreviewDto.invoiceBankName}</td>
				</tr>
				<tr>
					<td class="td_001">账号：${buyContractPreviewDto.remitBankAccount}</td>
					<td class="td_002">地址：${tf:replaceNull(buyContractPreviewDto.invoiceBankAddress)}</td>
				</tr>
				<tr>
					<td class="td_001">&nbsp;</td>
					<td class="td_002">电话：${tf:replaceNull(buyContractPreviewDto.invoiceBankTel)}</td>
				</tr>
				<tr>
					<td class="td_001">&nbsp;</td>
					<td class="td_002">帐号：${buyContractPreviewDto.invoiceBankAccount}</td>
				</tr>
				<tr>
					<td class="td_001">&nbsp;</td>
					<td class="td_002">税号：${buyContractPreviewDto.taxNumber}</td>
				</tr>
			</table>		</td>
	</tr>
</table>
</body>
<c:if test="${requestScope.print}">
		<div style="text-align:center;">
		    <a href="#" id="print"><img src="/cms/images/btnPrint.gif" width="65" height="20" class="buttonNoPrint" /></a>&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<img src="${ctx}/images/btnPrintSZ.gif" width="88" height="20" class="buttonNoPrint" onClick="javascript:printSetup();"/>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<img src="${ctx}/images/btnPrintYL.gif" class="buttonNoPrint" onClick="javascript:printPreview();" />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<c:if test="${showAdjunct}">
				<a href="${ctx}/printBuyContractOfSendAddress.do?id=${buyContractPreviewDto.id}&&cc=${buyContractPreviewDto.companyContractCode}&pc=${buyContractPreviewDto.productContractCode}"><img src="${ctx}/images/btnFJYL.jpg" class="buttonNoPrint" /></a>
			</c:if>
			
		</div>
</c:if>
</html>
