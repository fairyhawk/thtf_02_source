<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ taglib prefix="tf" uri="localhost" %>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>销售合同预览</title>
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
			.tad{font-size:14px;}
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

		<script type="text/javascript">
		 $(document).ready(function(){
		 	var print = $("#print").val();
			var roleId = "${roleId}";
			if(print == "true" || roleId=="3"|| roleId=="4"){//销售助理和销售经理可以随时打印
				$(".buttonNoPrint").show();
				document.title = "销售合同打印";
			}else{
				$(".buttonNoPrint").hide();
			}
	
		 });	
		
		function printPreview(){   
			// 打印页面预览    
			wb.execwb(7,1);
			      
		}    
		function printSetup(){    
			// 打印页面设置    
			wb.execwb(8,1);    
		}

		function  printIt(){ 
			wb.execwb(6,6); 
		} 
		
		function closeWindow(){
			wb.execwb(45,1); 
		}
	    </script>
	</head>
	<body>
		<OBJECT id="wb" height=0 width=0 classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 name="wb"></OBJECT>
		<div id="previewPage">
			<br />
			<br />
			<br />
			<table border="0" cellpadding="0" cellspacing="0" align="center"
				width="650" class="tad">
				<tr>
					<th height="40" colspan="2" valign="top">
						<span class="STYLE1">${salesContract.productTypeName}销售合同</span>
					</th>
				</tr>
				<tr>
					<td width="50%">
						供方：同方股份有限公司
					</td>
					<td>
						产品合同编号：${tf:replaceNull(salesContract.productContractCode)}
					</td>
				</tr>
				<tr>
					<td id="xufang">
						需方：${salesContract.customerName}
					</td>
					<td>
						公司合同编号：${tf:replaceNull(salesContract.companyContractCode)}
					</td>
				</tr>
				<tr>
					<td>
						签订日期：${tf:replaceNull(salesContract.date)}
					</td>
					<td id="qiandingdidian">
						签订地点：${tf:replaceNull(salesContract.place)}
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<table cellpadding="0" cellspacing="0" width="100%">
							<td width="32px">
								一、
							</td>
							<td>
								经双方同意，按《中华人民共和国合同法》签订，供方向需方提供如下产品：
							</td>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
					<td align="right">
						单位（元）
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<table width="100%" cellpadding="0" cellspacing="0" class="tad_1"
							id="looktable">
							<tr>
								<td align="center">
									<strong>产品名称</strong>
								</td>
								<td align="center">
									<strong>规格型号</strong>
								</td>
								<td align="center" width="40">
									<strong>单位</strong>
								</td>
								<td align="center" width="40">
									<strong>数量</strong>
								</td>
								<td align="center" width="96">
									<strong>单价</strong>
								</td>
								<td align="center" width="96">
									<strong>金额</strong>
								</td>
							</tr>
							<logic:present name="salesConProductList">
								<logic:iterate id="productlist" name="salesConProductList"
									indexId="indexId">
									<tr>
										<td nowrap="nowrap">
											${productlist.productName}
										</td>
										<td nowrap="nowrap">
											${productlist.productType}
										</td>
										<td nowrap="nowrap">
											${productlist.productUnit}
										</td>
										<td nowrap="nowrap" align="right">
											${productlist.salesCount}
										</td>
										<td nowrap="nowrap" align="right">
											<fmt:formatNumber value="${productlist.salesPrice}" pattern="#,##0.00"/>
										</td>

										<td nowrap="nowrap" align="right">
											<fmt:formatNumber value="${productlist.salesMoney}" pattern="#,##0.00"/>
										</td>
									</tr>
								</logic:iterate>
							</logic:present>
							

							<tr>
								<td align="center">
									<strong>合计</strong>
								</td>
								<td colspan="4">${capitalMoney}</td>
								<td align="right">
									￥<fmt:formatNumber value="${salesContract.money}" pattern="#,##0.00"/>
								
								</td>
							</tr>
								<logic:equal value="1" property="templateType"
										name="salesContract">
            					<td align="center">
									<strong>备注</strong>
								</td>
								<td colspan="5">${tf:replaceBr(salesContract.clause)}&nbsp;</td>
            				 </logic:equal>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<table cellpadding="0" cellspacing="0" width="100%">
							<td width="32px" valign="top">
								<div style="padding-top:2px">
									二、
								</div>
							</td>
							<td style="line-height:22px">
								质量要求、技术标准、供方对质量负责的条件和期限：需方收到货物后
								<u>${salesContract.checkLimit}</u>
								日内应根据厂家标准验收，若货物不符合厂家验收标准，应以书面形式通知供方；供方交货后
								<u>${salesContract.checkLimit}</u>
								日内未收到需方书面异议，视为验收合格。7日内供方对质量不合格产品予以更换，并按照厂家规定提供相关服务。
							</td>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<table cellpadding="0" cellspacing="0" width="100%">
							<tr>
								<td width="32" rowspan="4" valign="top">
									<div style="padding-top:7px">
										三、
									</div>
								</td>
								<td style="line-height:22px">
									<table cellpadding="0" cellspacing="0" width="100%">
										<tr>
											<td style="padding:0px 0px 0px 0px; width:128px">
												交（提）货时间：
											</td>
											<td 
												id="tijiaoTime">
												<u>&nbsp;<logic:equal value="1" property="paymentWay" name="salesContract">
												${tf:replaceNull(salesContract.requestSendDate)}&nbsp;</logic:equal>
													<logic:notEqual value="1" property="paymentWay" name="salesContract">无&nbsp;</logic:notEqual>
												</u>
											</td>
											<td></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td style="line-height:22px">
									<table cellpadding="0" cellspacing="0" width="100%">
										<tr>
											<td style="padding:0px 0px 0px 0px; width:147px">
												货物接收单位名称：
											</td>
											<td 
												id="reveiveCompangName">
												<u>&nbsp;${tf:replaceNull(salesContract.customerAddressName)}&nbsp;</u>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td style="line-height:22px">
									<table cellpadding="0" cellspacing="0" width="100%">
										<tr>
											<td style="padding:0px 0px 0px 0px; width:80px">
												发货地址：
											</td>
											<td 
												id="sendaddress11">
												<u>&nbsp;${tf:replaceNull(salesContract.customerAddressAddress)}&nbsp;</u>
												
											</td>
											<td style="padding:0px 0px 0px 0px; width:50px">
												邮编：
											</td>
											<td  id="postcode1">
												<u>&nbsp;${tf:replaceNull(salesContract.customerAddressPostcode)}&nbsp;</u>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td style="line-height:22px">
									<table cellpadding="0" cellspacing="0" width="100%">
										<tr>
											<td style="padding:0px 0px 0px 0px; width:65px">
												联系人：
											</td>
											<td >
												<u>&nbsp;${tf:replaceNull(salesContract.customerAddressLinkman)}&nbsp;</u>
											</td>
											<td style="padding:0px 0px 0px 0px; width:48px">
												电话：
											</td>
											<td >
												<u>&nbsp;${tf:replaceNull(salesContract.customerAddressTel)}&nbsp;</u>
											</td>
											<td style="padding:0px 0px 0px 0px; width:50px">
												手机：
											</td>
											<td >
												<u>&nbsp;${tf:replaceNull(salesContract.customerAddressMobile)}&nbsp;</u>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td></td>
								<td style="line-height:22px">
									交货方式：供方以快捷、安全、可靠的方式进行运输，并承担运费。
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<table cellpadding="0" cellspacing="0" width="100%">
							<tr>
								<td width="32" valign="top"  style="padding-top:3px;>
									<div style="padding-top:7px">
										四、
									</div>
								</td>
								<td colspan="2" style="line-height:22px">
									<table cellpadding="0" cellspacing="0" width="100%">
										<tr>
											<td style="padding:0px 0px 0px 0px; width:80px" valign="top">
												付款方式：
											</td>
											<td colspan="3">
												<logic:equal value="0" property="paymentWay"
													name="salesContract">
            												现款现结，款到发货。
            											</logic:equal>
												<logic:equal value="1" property="paymentWay"
													name="salesContract">
														 需方自收到每批货物后的<u>&nbsp;${(salesContract.arterm-7)<0?0:(salesContract.arterm-7)}</u>日内结清该批货款；累计欠款额（包括但不仅限于本合同）不得超过<u>&nbsp;<fmt:formatNumber value="${salesContract.climit}" pattern="#,##0.00"/></u>元。
            											</logic:equal>
												<logic:equal value="2" property="paymentWay"
													name="salesContract">
            												预付款<u>&nbsp;<fmt:formatNumber value="${salesContract.cashMoney}" pattern="#,##0.00"/></u>元，款到后按需方要求发货，余款每批货到后<u>&nbsp;${(salesContract.arterm-7)<0?0:(salesContract.arterm-7)}</u>日内结清；累计欠款额（包括但不仅限于本合同）不得超过<u>&nbsp;<fmt:formatNumber value="${salesContract.climit}" pattern="#,##0.00"/></u>元。
            												
            											</logic:equal>
            											
											</td>
										</tr>
										
									</table>
								</td>
							</tr>
							<tr>
								<td valign="top">
									&nbsp;
								</td>
								<td width="80" valign="top"
									style="line-height:22px; width:80px;">
									保价约定：
								</td>
								<td width="536" style="line-height:22px">
									本合同价格有效期为自合同签订之日起
									<u>${tf:replaceNull(salesContract.protectLimit)}</u>个自然日，超出上述期限的未提货物价格由双方协商，另行签订合同。
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<table cellpadding="0" cellspacing="0" width="100%">
							<tr>
								<td width="32px" valign="top">
									<div style="padding-top:4px">
										五、
									</div>
								</td>
								<td style="line-height:22px">
									违约责任：如需方在约定之日未结清全部货款，供方有权按
									<u>${tf:replaceNull(salesContract.breach)}</u>收取违约金；在未付清全部货款前，所列设备所有权均归供方所有，供方有权以任何方式收回已交付的所有设备，由此给供方造成的一切损失由需方承担。
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<table cellpadding="0" cellspacing="0" width="100%">
							<tr>
								<td width="32px" valign="top">
									<div style="padding-top:3px">
										六、
									</div>
								</td>
								<td style="line-height:22px">
									争议解决方式：对于本合同执行过程中发生的纠纷，供需双方本着友好合作的态度协商解决；协商不成，任何一方有权向${tf:replaceNull(salesContract.lawsuit)}。
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<table cellpadding="0" cellspacing="0" width="100%">
							<tr>
								<td width="32px" valign="top">
									<div style="padding-top:3px">
										七、
									</div>
								</td>
								<td style="line-height:22px">
									其他：本合同双方签字盖章后生效，传真件有效。变更本合同内容时由双方签署书面文件后有效，任何一方不得擅自毁约或转让本合同。
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<table cellpadding="0" cellspacing="0" width="100%">
							<tr>
								<td width="32px" valign="top">
									<div style="padding-top:3px">
										八、
									</div>
								</td>
								<td style="line-height:22px">
									其他未尽事宜，双方可签订附件，附件与本合同具有同等法律效力。
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<table width="100%" cellpadding="0" cellspacing="0" class="tad02">
							<tr>
								<td align="center" width="50%" class="td_001">
									供 方
								</td>
								<td align="center" class="td_002">
									需 方
								</td>
							</tr>
							<tr>
								<td class="td_001">
									卖方单位（章）：${CompanyInfo.name}
								</td>
								<td class="td_002">
									买方单位（章）：${salesContract.customerName}
								</td>
							</tr>
							<tr>
								<td class="td_001" id="delegation1">
									委托代理人：${salesContract.userName}
								</td>
								<td class="td_002" id="delegation2">
									委托代理人：${salesContract.customerLinkmanName}
								</td>
							</tr>
							<tr>
								<td class="td_001" id="previsePhone1">
									电话：${tf:replaceNull(salesContract.userTel)}
								</td>
								<td class="td_002" id="previsePhone2">
									电话：${tf:replaceNull(salesContract.customerLinkmanTel)}
								</td>
							</tr>
							<tr>
								<td class="td_001" id="previseFax1">
									传真：${tf:replaceNull(salesContract.productDeptFax)}
								</td>
								<td class="td_002" id="previseFax2">
									传真：${tf:replaceNull(salesContract.customerLinkmanFax)}
								</td>
							</tr>
							<tr>
								<td class="td_001">
									开户行：${CompanyInfo.invoice_bank_name}
								</td>
								<td class="td_002">
									开户行：${salesContract.customerInvoiceBankName}
								</td>
							</tr>
							<tr>
								<td rowspan="2" valign="top" style="border-right:1px solid #CCCCCC;padding-top:4px;">
									&nbsp;帐号：${salesContract.productDeptAccount}
								</td>
								<td class="td_002">
									帐号：${salesContract.customerInvoiceBankAccount}
								</td>
							</tr>
							<tr>
								<td style="padding-top:4px;">
									&nbsp;税号：${salesContract.customerTaxNumber}
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<input id="print" type="hidden" value="${param.print}">
				<tr>
					<td colspan="2" align="center" height="50px">
					<a href=#><img src="${ctx}/images/btnPrint.gif" width="65" height="20" class="buttonNoPrint" onClick="javascript:printIt();"/></a>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<a href=#><img src="${ctx}/images/btnPrintSZ.gif" width="88" height="20" class="buttonNoPrint" onClick="javascript:printSetup();"/></a>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				    <a href=#><img src="${ctx}/images/btnPrintYL.gif" class="buttonNoPrint" onClick="javascript:printPreview();" /></a>
				    
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>
