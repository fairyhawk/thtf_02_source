<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>
<%@taglib prefix="tf" uri="localhost" %>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>评审表查看</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
		<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/messages_cn.js"></script>		
	<style type="text/css">
	<!--
		body{font-family:"宋体"; font-size:13px; font-weight:bold;}
		.tad_1{border-left:1px solid #000000; border-bottom:1px solid #000000; }
		.tad_1 td{border-right:1px solid #000000;border-top:1px solid #000000;color:#000000;white-space:nowrap; padding:3px 0px 3px 0px;}
		.tad td{padding-bottom:5px;}
		.tad_2{ border-bottom:1px solid #000000; }
	.STYLE1 {
		font-size: 24px;
		font-weight: bold;
	}
	@media   print   {   
  		.buttonNoPrint {display:none;}   
  		}
	.STYLE2 {font-size: 14px}
	-->
	</style>
	
	<script type="text/javascript">
	$(document).ready(function(){
		 	var print = $("#print").val();
			if(print == "true"){
				$(".buttonNoPrint").show();
				document.title = "评审表打印";
				
			}else{
				//$(".buttonNoPrint").hide();
			}
		 
		 });	
	
	$.ajaxSetup({ 
 	 async: false 
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
			var roleId = "${roleId}";
			if(roleId==3){//销售助理
				//window.location.href='modifyContractStatus.do?saleContractId=${salesContract.id}';
				
				$.post("modifyContractStatus.do?saleContractId=${salesContract.id}"); 
				
				window.opener.location.href='getSalesContractsList.do?init=true'; 
			}
		} 	
		function closeWindow(){
			wb.execwb(45,1); 
		}
		
	
	</script>
	</head>
<body><br/>
<OBJECT id="wb" height="0" width="0" classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" name="wb"></OBJECT>
<table border="0" cellpadding="0" cellspacing="0" align="center" width="98%" class="tad"><tr><td colspan="2" align="center" height="30"><table border="0" cellpadding="0" cellspacing="0" align="center" width="98%" class="tad">
	<tr>
		<td height="40" colspan="2" valign="top"><table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td align="center" class="STYLE1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;同方股份有限公司</td>
				<td width="100">JL-CP-001</td>
			</tr>
			<tr>
				<td colspan="2" align="center" class="STYLE2" height="25px">销售合同评审表</td>
			</tr>
		</table></td>
	</tr>
	<tr>
		<td colspan="2"><table width="100%" cellpadding="0" cellspacing="0" class="tad_1">
			<tr>
				<td width="11%" align="center">部门名称</td>
				<td width="38%" align="left">${salesContract.productDeptName}&nbsp;</td>
				<td width="13%" align="center">产品合同号</td>
				<td width="38%" align="left">${salesContract.productContractCode}&nbsp;</td>
			</tr>
			<tr>
				<td align="center">客户名称</td>
				<td align="left">${salesContract.customerName}&nbsp;</td>
				<td align="center">公司合同号</td>
				<td align="left">${salesContract.companyContractCode}&nbsp;</td>
			</tr>
			<tr>
				<td align="center">盖章类型</td>
				<td align="left">
				<logic:equal value="0" property="stampType"
										name="salesContract">
            		业务章
            	</logic:equal>
									<logic:equal value="1" property="stampType"
										name="salesContract">
            		合同章
            	</logic:equal>				
				</td>
				<td align="center">模板类型</td>
				<td align="left">
				<logic:equal value="0" property="templateType"
										name="salesContract">
            		标准模板
            	</logic:equal>
									<logic:equal value="1" property="templateType"
										name="salesContract">
            		自定义模板
            	</logic:equal>
									<logic:equal value="2" property="templateType"
										name="salesContract">
            		非模板&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            	</logic:equal>
				
				</td>
			</tr>
			<tr>
				<td align="center">产品分类</td>
				<td align="left">${salesContract.productTypeName}&nbsp;</td>
				<td align="center">产品序列号</td>
				<td align="left">${salesContract.productDeptNo}${salesContract.productTypeNo}&nbsp;</td>
			</tr>
			<tr>
				<td align="center"> 项目名称 </td>
				<td align="left" COLSPAN=3>${salesContract.contractProName}&nbsp;</td>
			</tr>
		</table></td>
	</tr>
	<tr>
		<td width="50%">&nbsp;</td>
		<td align="right">单位（元）</td>
	</tr>
	<tr>
		<td colspan="2"><table width="100%" cellpadding="0" cellspacing="0" class="tad_1">
			<tr>
				<td align="center"><strong>序号</strong></td>
				<td align="center">产品名称</td>				
				<td align="center"><strong>规格型号</strong></td>
				<td align="center" width="40"><strong>单位</strong></td>
				<td align="center" width="40"><strong>数量</strong></td>
				<td align="center" width="96">销售单价</td>
				<td align="center" width="96">销售总价</td>
				<td align="center" width="96"><strong>预计采购单价</strong></td>
				<td align="center" width="96"><strong>销售毛利率(%)</strong></td>
				<td align="center" width="96"><strong>限价</strong></td>
			</tr>			
			<logic:present name="salesConProductList">
								<logic:iterate id="productlist" name="salesConProductList"
									indexId="indexId">
									<tr>
										<td nowrap="nowrap">
											${indexId+1}
										</td>										
										<td nowrap="nowrap">
											${productlist.productName}
										</td>
										<td nowrap="nowrap">
											${productlist.productType}
										</td>
										<td nowrap="nowrap">
											${productlist.productUnit}
										</td>
										<td nowrap="nowrap">
											${productlist.salesCount}
										</td>
										<td nowrap="nowrap" align="right">
											<fmt:formatNumber value="${productlist.salesPrice}" pattern="#,##0.00#"/>
										</td>
										<td nowrap="nowrap" align="right">
										<fmt:formatNumber value="${productlist.salesMoney}" pattern="#,##0.00#"/>
										</td>
										<td nowrap="nowrap" align="right">
										<fmt:formatNumber value="${productlist.detailBuyPrice}" pattern="#,##0.00#"/>
										</td>
										<td nowrap="nowrap" align="right">
											${productlist.grossRateRound}
										</td>	
										 <td nowrap="nowrap" style="text-align:right; padding-right:5px;">
											<fmt:formatNumber value="${productlist.limitPrice}" pattern="#,##0.00"/>&nbsp;
		 								 </td>							
									</tr>
								</logic:iterate>
							</logic:present>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>				
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td align="right">&nbsp;</td>
				<td align="right">总计</td>
				<td align="right">￥<fmt:formatNumber value="${salesContract.money}" pattern="#,##0.00#"/></td>
				<td align="right">总利率</td>
				<td align="right">${salesContract.interestRateRound}</td>
				<td align="right">&nbsp;</td>
			</tr>
		</table></td>
	</tr>
	<tr>
		<td colspan="2">
		<table cellpadding="0" cellspacing="0" width="100%" class="tad_2">
			<tr>
				<td style="line-height:22px" align="left" colspan="3">部门送审人送审说明</td>
			</tr>
			<tr>
				<td style="line-height:22px" align="left" colspan="3">1.发票类型：<logic:equal value="0" property="customerInvoiceType" name="salesContract">普通</logic:equal><logic:equal value="1" property="customerInvoiceType" name="salesContract">增值税</logic:equal></td>
			</tr>
			<tr>
				<td  style="line-height:22px" align="left" colspan="3">
				2.付款方式：
				
				<logic:equal value="0" property="paymentWay"
													name="salesContract">
            												全部现结 &nbsp;&nbsp;&nbsp;现结金额：
            												<fmt:formatNumber value="${salesContract.cashMoney}" pattern="#,##0.00"/>元
            											</logic:equal>
												<logic:equal value="1" property="paymentWay"
													name="salesContract">
            												全部信用-${salesContract.creditTypeName}（<c:if test="${tf:isEmptyOrNull(salesContract.creditProjectName)==false}">${salesContract.creditProjectName}，</c:if>账期${salesContract.arterm}天，额度<fmt:formatNumber value="${salesContract.climit}" pattern="#,##0.00"/>元）
            											</logic:equal>
												<logic:equal value="2" property="paymentWay"
													name="salesContract">
            												部分信用部分现结-${salesContract.creditTypeName}（账期${salesContract.arterm}天，额度<fmt:formatNumber value="${salesContract.climit}" pattern="#,##0.00"/>元）
            												&nbsp;&nbsp;&nbsp;现结金额:
            												<fmt:formatNumber value="${salesContract.cashMoney}" pattern="#,##0.00"/>元
            											</logic:equal>
</td>
			</tr>
			<tr>
				<td  style="line-height:22px" align="left" colspan="3">3.要求发货日期：${salesContract.requestSendDate}</td>
			</tr>
			<tr>
				<td style="line-height:22px" colspan="3" colspan="3"><table cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td  valign="top" align="left" width="120px" nowrap>4.重要事项说明：</td><td align="left">${tf:replaceBr(salesContract.text)}</td>
					</tr>
				</table></td>
			</tr>
			<tr>
				<td style="line-height:22px" width="55%">&nbsp;</td>
				<td style="line-height:22px" width="20%" align="left">送审人签字：${salesContract.userName}</td>
				<td style="line-height:22px" width="25%" align="left">日期：${salesContract.dateTime}</td>
			</tr>
		</table></td>
	</tr>
	<tr>
		<td colspan="2">
		<table cellpadding="0" cellspacing="0" width="100%" class="tad_2">
			<tr>
				<td colspan="3" style="line-height:22px" align="left">产品总监评审意见</td>
			</tr>
			<tr class="proMajIdeaDIV">
				<td width="320" nowrap="nowrap" align="left">
					1.产品要求是否符合（数量、规格、技术、质量）
				</td>
				<td height="20px" nowrap="nowrap" colspan="2" align="left">
					<input type="checkbox" name="salesContract.proMajIdea1"
						id="checkbox" onclick="return false"
						<c:if test="${salesContract.proMajIdea1 == 1}">checked="checked"</c:if> />
					符合 &nbsp;&nbsp;
					<input type="checkbox" name="salesContract.proMajIdea1"
						id="checkbox"  onclick="return false"
						<c:if test="${salesContract.proMajIdea1 == 0}">checked="checked"</c:if> />
					不符合
				</td>
			</tr>
			<tr>
				<td style="line-height:22px" align="left">2.交付日期是否符合</td>
				<td height="20px" colspan="2" align="left">
										<input type="checkbox" name="salesContract.proMajIdea2"
											id="checkbox2" onclick="return false"
											<c:if test="${salesContract.proMajIdea2 == 1}">checked="checked"</c:if> />
										符合 &nbsp;&nbsp;
										<input type="checkbox" name="salesContract.proMajIdea2"
											id="checkbox2" onclick="return false"
											<c:if test="${salesContract.proMajIdea2 == 0}">checked="checked"</c:if> />
										不符合
				</td>
			</tr>
			<tr>
				<td style="line-height:22px" align="left">3.包装要求是否符合</td>
				<td height="20px" colspan="2" align="left">
										<input type="checkbox" name="salesContract.proMajIdea3"
											id="checkbox3" onclick="return false"
											<c:if test="${salesContract.proMajIdea3 == 1}">checked="checked"</c:if> />
										符合 &nbsp;&nbsp;
										<input type="checkbox" name="salesContract.proMajIdea3"
											id="checkbox3" onclick="return false"
											<c:if test="${salesContract.proMajIdea3 == 0}">checked="checked"</c:if> />
										不符合
				</td>
			</tr>
			<tr>
				<td colspan="3" style="line-height:22px" align="left"><table cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td width="70" valign="top">补充说明：</td>
						<td align="left">${tf:replaceBr(salesContract.proMajText)}</td>
					</tr>
				</table></td>
			</tr>
			<tr>
				<td style="line-height:22px" width="55%">&nbsp;</td>
				<td style="line-height:22px" width="20%" align="left">签字：${salesContract.proMajName}</td>
				<td style="line-height:22px" width="25%" align="left">日期：${salesContract.proMajDate2}</td>
			</tr>
		</table></td>
	</tr>
	<logic:notEqual value="0" property="templateType"
										name="salesContract">
	<tr>
		<td colspan="2"><table cellpadding="0" cellspacing="0" width="100%" class="tad_2">
			<tr>
				<td colspan="3" style="line-height:22px" align="left">法务专员评审意见</td>
			</tr>
			<tr>
				<td style="line-height:22px" align="left">法律法规是否符合</td>
				<td height="20px" colspan="2" align="left">
					<input type="checkbox" name="salesContract.legalIdea"
						id="checkbox4" onclick="return false"
						<c:if test="${salesContract.legalIdea == 1}">checked="checked"</c:if> />
					符合 &nbsp;&nbsp;
					<input type="checkbox" name="salesContract.legalIdea"
						id="checkbox4" onclick="return false"
						<c:if test="${salesContract.legalIdea == 0}">checked="checked"</c:if> />
					不符合
				</td>
			</tr>
			<tr>
				<td colspan="3" style="line-height:22px" align="left"><table cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td width="70" valign="top" align="left">补充说明：</td>
						<td align="left">${tf:replaceBr(salesContract.legalText)}</td>
					</tr>
				</table></td>
			</tr>
			<tr>
				<td style="line-height:22px" width="55%">&nbsp;</td>
				<td style="line-height:22px" width="20%" align="left">签字：${salesContract.legalName}</td>
				<td style="line-height:22px" width="25%" align="left">日期：${salesContract.legalDate2}</td>
			</tr>
		</table></td>
	</tr>
	</logic:notEqual>
	<tr>
		<td colspan="2"><table cellpadding="0" cellspacing="0" width="100%" class="tad_2">
			<tr>
				<td colspan="3" style="line-height:22px" align="left">区域总监评审意见</td>
			</tr>
			<tr>
				<tr>
								<td style="line-height:22px" align="left">
									1.毛利率是否符合
								</td>
								<td style="line-height:22px" align="left">
									<input type="checkbox" name="salesContract.areaMajIdea1"
										id="checkbox" onclick="return false"
										<c:if test="${salesContract.areaMajIdea1 == 1}">checked="checked"</c:if> />
									符合 &nbsp;&nbsp;
									<input type="checkbox" name="salesContract.areaMajIdea1"
										id="checkbox" onclick="return false"
										<c:if test="${salesContract.areaMajIdea1 == 0}">checked="checked"</c:if> />
									不符合
								</td>
							</tr>
							<tr>
								<td style="line-height:22px" align="left">
									2.付款方式是否符合
								</td>
								<td height="20px" colspan="2" align="left">
									<input type="checkbox" name="salesContract.areaMajIdea2"
										id="checkbox2" onclick="return false"
										<c:if test="${salesContract.areaMajIdea2 == 1}">checked="checked"</c:if> />
									符合 &nbsp;&nbsp;
									<input type="checkbox" name="salesContract.areaMajIdea2"
										id="checkbox2" onclick="return false"
										<c:if test="${salesContract.areaMajIdea2 == 0}">checked="checked"</c:if> />
									不符合
								</td>
							</tr>
							<tr>
								<td style="line-height:22px" align="left">
									3.运输方式是否符合
								</td>
								<td style="line-height:22px" align="left">
									<input type="checkbox" name="salesContract.areaMajIdea3"
										id="checkbox5" onclick="return false"
										<c:if test="${salesContract.areaMajIdea3 == 1}">checked="checked"</c:if> />
									符合 &nbsp;&nbsp;
									<input type="checkbox" name="salesContract.areaMajIdea3"
										id="checkbox5" onclick="return false"
										<c:if test="${salesContract.areaMajIdea3 == 0}">checked="checked"</c:if> />
									不符合
								</td>
							</tr>
							<tr>
								<td style="line-height:22px" align="left">
									4.售后服务是否符合（含售前、售中）
								</td>
								<td style="line-height:22px" align="left">
									<input type="checkbox" name="salesContract.areaMajIdea4"
										id="checkbox3" onclick="return false"
										<c:if test="${salesContract.areaMajIdea4 == 1}">checked="checked"</c:if> />
									符合 &nbsp;&nbsp;
									<input type="checkbox" name="salesContract.areaMajIdea4"
										id="checkbox3" onclick="return false"
										<c:if test="${salesContract.areaMajIdea4 == 0}">checked="checked"</c:if> />
									不符合
								</td>
							</tr>
			<tr>
				<td colspan="3" style="line-height:22px" align="left"><table cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td width="70" valign="top" align="left">补充说明：</td>
						<td align="left">${tf:replaceBr(salesContract.areaMajText)}</td>
					</tr>
				</table></td>
			</tr>
			<tr>
				<td style="line-height:22px" width="55%">&nbsp;</td>
				<td style="line-height:22px" width="20%" align="left">签字：${salesContract.areaMajName}</td>
				<td style="line-height:22px" width="25%" align="left">日期：${salesContract.areaMajDate}</td>
			</tr>
		</table></td>
	</tr>
	
	<tr>
		<td colspan="2"><table cellpadding="0" cellspacing="0" width="100%" class="tad_2">
			<tr>
				<td colspan="3" style="line-height:22px" align="left">销售总监评审意见</td>
			</tr>
			<tr>
				<td style="line-height:22px" align="left">1.毛利率是否符合</td>
				<td height="20px" colspan="2" align="left">
									<input type="checkbox" name="salesContract.sellMajIdea1"
										id="checkbox" onclick="return false"
										<c:if test="${salesContract.sellMajIdea1 == 1}">checked="checked"</c:if> />
									符合 &nbsp;&nbsp;
									<input type="checkbox" name="salesContract.sellMajIdea1"
										id="checkbox" onclick="return false"
										<c:if test="${salesContract.sellMajIdea1 == 0}">checked="checked"</c:if> />
									不符合
				</td>
			</tr>
			<tr>
				<td style="line-height:22px" align="left">2.付款方式是否符合</td>
				<td height="20px" colspan="2" align="left">
									<input type="checkbox" name="salesContract.sellMajIdea2"
										id="checkbox2" onclick="return false"
										<c:if test="${salesContract.sellMajIdea2 == 1}">checked="checked"</c:if> />
									符合 &nbsp;&nbsp;
									<input type="checkbox" name="salesContract.sellMajIdea2"
										id="checkbox2" onclick="return false"
										<c:if test="${salesContract.sellMajIdea2 == 0}">checked="checked"</c:if> />
									不符合
				</td>
			</tr>
			<tr>
				<td style="line-height:22px" align="left">3.运输方式是否符合</td>
				<td height="20px" colspan="2" align="left">
									<input type="checkbox" name="salesContract.sellMajIdea3"
										id="checkbox5" onclick="return false"
										<c:if test="${salesContract.sellMajIdea3 == 1}">checked="checked"</c:if> />
									符合 &nbsp;&nbsp;
									<input type="checkbox" name="salesContract.sellMajIdea3"
										id="checkbox5" onclick="return false"
										<c:if test="${salesContract.sellMajIdea3 == 0}">checked="checked"</c:if> />
									不符合
				</td>
			</tr>
			<tr>
				<td style="line-height:22px" align="left">4.售后服务是否符合（含售前、售中）</td>
				<td height="20px" colspan="2" align="left">
									<input type="checkbox" name="salesContract.sellMajIdea4"
										id="checkbox3" onclick="return false"
										<c:if test="${salesContract.sellMajIdea4 == 1}">checked="checked"</c:if> />
									符合 &nbsp;&nbsp;
									<input type="checkbox" name="salesContract.sellMajIdea4"
										id="checkbox3" onclick="return false"
										<c:if test="${salesContract.sellMajIdea4 == 0}">checked="checked"</c:if> />
									不符合
				</td>
			</tr>
			<tr>
				<td colspan="3" style="line-height:22px"><table cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td width="70" valign="top" >补充说明：</td>
						<td align="left">${tf:replaceBr(salesContract.sellMajText)}</td>
					</tr>
				</table></td>
			</tr>
			<tr>
				<td style="line-height:22px" width="55%">&nbsp;</td>
				<td style="line-height:22px" width="20%" align="left">签字：${salesContract.sellMajName}</td>
				<td style="line-height:22px" width="25%" align="left">日期：${salesContract.sellMajDate2}</td>
			</tr>
		</table></td>
	</tr>
	<tr>
		<td colspan="2"><table cellpadding="0" cellspacing="0" width="100%" class="tad_2">
			<tr>
				<td colspan="3" style="line-height:22px" align="left">运营总监评审意见</td>
			</tr>
			<tr>
				<td style="line-height:22px">&nbsp;</td>
				<td height="20px" colspan="2" align="left">
									<input type="checkbox" name="salesContract.opeMajIdea"
										id="checkbox4" onclick="return false"
										<c:if test="${salesContract.opeMajIdea == 1}">checked="checked"</c:if> />
									同意 &nbsp;&nbsp;
									<input type="checkbox" name="salesContract.opeMajIdea"
										id="checkbox4" onclick="return false"
										<c:if test="${salesContract.opeMajIdea == 0}">checked="checked"</c:if> />
									不同意
				</td>
			</tr>
			<tr>
				<td colspan="3" style="line-height:22px"><table cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td width="70" valign="top">补充说明：</td>
						<td align="left">${tf:replaceBr(salesContract.opeMajText)}</td>
					</tr>
				</table></td>
			</tr>
			<tr>
				<td style="line-height:22px" width="55%">&nbsp;</td>
				<td style="line-height:22px" width="20%" align="left">签字：${salesContract.opeMajName}</td>
				<td style="line-height:22px" width="25%" align="left">日期：${salesContract.opeMajDate2}</td>
			</tr>
		</table></td>
	</tr>
	<tr>
		<td colspan="2"><table cellpadding="0" cellspacing="0" width="100%" class="tad_2">
			<tr>
				<td colspan="3" style="line-height:22px" align="left">公司授权领导最终审批意见</td>
			</tr>
			<tr>
				<td style="line-height:22px">&nbsp;</td>
				<td colspan="2" align="left"><input type="checkbox" name="checkbox9" id="checkbox10" onclick="return false"/>
					同意
					&nbsp;&nbsp;
											<input type="checkbox" name="checkbox9" id="checkbox10" onclick="return false"/>
					不同意</td>
			</tr>
			<tr>
				<td colspan="3" style="line-height:22px"><table cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td width="70" valign="top">&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
				</table></td>
			</tr>
			<tr>
				<td style="line-height:22px" width="55%">&nbsp;</td>
				<td style="line-height:22px" width="20%" align="left">签字：</td>
				<td style="line-height:22px" width="25%" align="left">日期：</td>
			</tr>
			
		</table></td>
	</tr>
	
	<tr>
		<td colspan="2" align="center" height="50px">
			<input id="print" type="hidden" value="${param.printAudit}">
			<img src="${ctx}/images/btnPrint.gif" width="65" height="20" class="buttonNoPrint" onClick="javascript:return printIt(this);"/>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<img src="${ctx}/images/btnPrintSZ.gif" width="88" height="20" class="buttonNoPrint" onClick="javascript:printSetup();"/>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<img src="${ctx}/images/btnPrintYL.gif" class="buttonNoPrint" onClick="javascript:printPreview();" />
			
		</td>
	</tr>
</table>

</body>
</html>
