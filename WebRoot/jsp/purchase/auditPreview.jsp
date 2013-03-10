<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@taglib prefix="tf" uri="localhost" %>

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

function printfalse(){
wb.execwb(6,6);
}
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
		
		var html=$.ajax({
			url:'printBuyContractCommentTable.do?id='+$("#id").val()+'',
			success:function(html){
				window.opener.location.href='buyContractList.do?init=true';  
				wb.execwb(6,6); 
			}
		});
		} 	
		function closeWindow(){
			wb.execwb(45,1); 
		}
		
	
	</script>
	</head>
<body><br/>
<c:if test="${param.modifyError}">
	<script>alert("打印失败！");</script>
</c:if>
<OBJECT id="wb" height=0 width=0 classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 name="wb"></OBJECT>
<html:form action="printCommentTable" method="post" styleId="commentForm">

<table border="0" cellpadding="0" cellspacing="0" align="center" width="98%" class="tad"><tr><td colspan="2" align="center" height="30"><table border="0" cellpadding="0" cellspacing="0" align="center" width="98%" class="tad">
	<tr>
		<td height="40" colspan="2" valign="top"><table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td align="center" class="STYLE1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;同方股份有限公司</td>
				<td width="100">JL-CP-001</td>
			</tr>
			<tr>
				<td colspan="2" align="center" class="STYLE2" height="25px">采购合同评审表</td>
			</tr>
		</table></td>
	</tr>
	<tr>
		<td colspan="2"><table width="100%" cellpadding="0" cellspacing="0" class="tad_1">
			<tr>
				<td width="11%" align="center">部门名称</td>
				<td width="38%" align="left">${buyContractOfAddDto.productDeptName}&nbsp;</td>
				<td width="13%" align="center">产品合同号</td>
				<td width="38%" align="left">${buyContractOfAddDto.productContractCode}&nbsp;</td>
			</tr>
			<tr>
				<td align="center">供货商名称</td>
				<td align="left">${buyContractOfAddDto.supplierName}&nbsp;</td>
				<td align="center">公司合同号</td>
				<td align="left">${buyContractOfAddDto.companyContractCode}&nbsp;</td>
			</tr>
			<tr>
				<td align="center">合同类型</td>
				<td align="left">
				<logic:equal value="0" property="contractType"
										name="buyContractOfAddDto">
            		国内
            	</logic:equal>
									<logic:equal value="1" property="contractType"
										name="buyContractOfAddDto">
            		国外
            	</logic:equal>				
				</td>
				<td align="center">模板类型</td>
				<td align="left">
				<logic:equal value="0" property="templateType"
										name="buyContractOfAddDto">
            		标准模板
            	</logic:equal>

				<logic:equal value="1" property="templateType"
										name="buyContractOfAddDto">
            		非模板&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            	</logic:equal>
				
				</td>
			</tr>
			<tr>
				<td align="center">产品分类</td>
				<td align="left">${buyContractOfAddDto.productTypeName}</td>
				<td align="center">产品序列号</td>
				<td align="left">${buyContractOfAddDto.productDeptNo}${buyContractOfAddDto.productTypeNo}</td>
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
				<td align="center" width="96">采购单价</td>
				<td align="center" width="96">采购总价</td>
				<td align="center" width="80">库存单价</td>
				<td align="center" width="75">增长率(%)</td>
				<td align="center" width="70"><strong>库存总数</strong></td>
				<td align="center" width="70"><strong>缺货总数</strong></td>
			</tr>			
			<logic:present name="prodInfoList">
								<logic:iterate id="prodInfoList" name="prodInfoList"
									indexId="indexId">
									<tr>
										<td nowrap="nowrap" style="text-align:right;padding-right:5px">
											${indexId+1}
										</td>										
										<td nowrap="nowrap" style="text-align:left;padding-left:5px">
											${prodInfoList.prodName}
										</td>
										<td nowrap="nowrap" style="text-align:left;padding-left:5px">
											${prodInfoList.prodType}
										</td>
										<td nowrap="nowrap" style="text-align:left;padding-left:5px">
											${prodInfoList.prodUnit}
										</td>
										<td nowrap="nowrap" style="text-align:right;padding-right:5px">
											${prodInfoList.buyCount}
										</td>
										<td nowrap="nowrap" style="text-align:right;padding-right:5px">
											${prodInfoList.buyPrice}
										</td>
										<td nowrap="nowrap" style="text-align:right;padding-right:5px">
											<fmt:formatNumber value="${prodInfoList.buyCount*prodInfoList.buyPrice}" pattern="#,##0.00000"/>
										</td>
										<td nowrap="nowrap" style="text-align:right;padding-right:5px">
											<fmt:formatNumber value="${prodInfoList.stockUnitPrice}" pattern="#,##0.00"/>
										</td>	
										<td nowrap="nowrap" style="text-align:right;padding-right:5px">
											<c:choose>
												<c:when test="${prodInfoList.stockUnitPrice*1==0}">
													0
												</c:when>
												<c:when test="${buyContractOfAddDto.invoiceType==0}"><fmt:formatNumber value="${(prodInfoList.buyPrice-prodInfoList.noTaxOfPrice)*100/prodInfoList.noTaxOfPrice}" type="number" pattern="0.00"/></c:when>
												<c:otherwise>
													<fmt:formatNumber value="${(prodInfoList.buyPrice/(1+buyContractOfAddDto.taxRate/100)-prodInfoList.noTaxOfPrice)*100/prodInfoList.noTaxOfPrice}" type="number" pattern="0.00"/>
												</c:otherwise>
											</c:choose>
										</td>
										<td nowrap="nowrap" style="text-align:right;padding-right:5px">
											${prodInfoList.stockTotalCount}
										</td>
										<td nowrap="nowrap" style="text-align:right;padding-right:5px" >
											${prodInfoList.demandCount}
										</td>									
									</tr>
								</logic:iterate>
							</logic:present>
			<tr>
				<td colspan="11" align="right" style="padding-right:205px">总计&nbsp;￥<fmt:formatNumber value="${buyContractOfAddDto.money}" pattern="#,##0.00000"/>&nbsp;元</td>

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
				<td style="line-height:22px" align="left" colspan="3">1.发票类型：<logic:equal value="0" property="invoiceType" name="buyContractOfAddDto">普通</logic:equal><logic:equal value="1" property="invoiceType" name="buyContractOfAddDto">增值税</logic:equal></td>
			</tr>
			<tr>
				<td  style="line-height:22px" align="left" colspan="3">2.付款方式：
				<c:if test="${buyContractOfAddDto.paymentWay==2}">
	            	支票
	            </c:if>
	             <c:if test="${buyContractOfAddDto.paymentWay==3}">
	            	网银
	            </c:if>
	              <c:if test="${buyContractOfAddDto.paymentWay==4}">
	            	电汇
	            </c:if>
	              <c:if test="${buyContractOfAddDto.paymentWay==5}">
	            	银行承兑
	            </c:if>
	              <c:if test="${buyContractOfAddDto.paymentWay==6}">
	            	信用证
	            </c:if>
				</td>
			</tr>
			<tr>
				<td  style="line-height:22px" align="left" colspan="3">3.付款类型：
					<logic:equal value="0" property="paymentType" name="buyContractOfAddDto">全部预付&nbsp;&nbsp;合同签订后${tf:replaceNull(buyContractOfAddDto.contractPaymentTime)}个工作日内</logic:equal>
					<logic:equal value="1" property="paymentType" name="buyContractOfAddDto">部分预付&nbsp;&nbsp;合同签订后${tf:replaceNull(buyContractOfAddDto.contractPaymentTime)} 个工作日内&nbsp;&nbsp;
																							预付金额<c:if test="${buyContractOfAddDto.prepayMoney!=null}"><fmt:formatNumber value="${buyContractOfAddDto.prepayMoney}" pattern="#,##0.00000"/></c:if><c:if test="${buyContractOfAddDto.prepayMoney==null}">/</c:if>元&nbsp;&nbsp;
																							验收合格后${tf:replaceNull(buyContractOfAddDto.sendPaymentTime)}个工作日内
																							</logic:equal>
					<logic:equal value="2" property="paymentType" name="buyContractOfAddDto">货到付款&nbsp;&nbsp;验收合格后${tf:replaceNull(buyContractOfAddDto.sendPaymentTime)}个工作日内</logic:equal>
				</td>
			</tr>
			<tr>
				<td  style="line-height:22px" align="left" colspan="3">4.要求供货日期：
					${buyContractOfAddDto.requestDate}
				</td>
			</tr>
			<tr>
				<td style="line-height:22px" colspan="3" colspan="3"><table cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td  valign="top" align="left" width="120px" nowrap>重要事项说明：</td><td align="left">${tf:replaceBr(buyContractOfAddDto.text)}</td>
					</tr>
				</table></td>
			</tr>
			<tr>
				<td style="line-height:22px" width="50%">&nbsp;</td>
				<td style="line-height:22px" width="25%" align="left">送审人签字：${buyContractOfAddDto.userName}</td>
				<td style="line-height:22px" width="25%" align="left">日期：${buyContractOfAddDto.dateTime}</td>
			</tr>
		</table></td>
	</tr>
	<c:if test="${buyContractOfAddDto.templateType=='1'}">
	<tr>
		<td colspan="2"><table cellpadding="0" cellspacing="0" width="100%" class="tad_2">
			<tr>
				<td colspan="3" style="line-height:22px" align="left">法务专员评审意见</td>
			</tr>
			<tr>
				<td style="line-height:22px" align="left">法律法规是否符合</td>
				<td height="20px" colspan="2" align="left">
					<input type="checkbox" name="salesBackContractOfShowDto.legalIdea"
						id="checkbox4" onclick="return false"
						<c:if test="${buyContractOfAddDto.legalIdea == 1}">checked="checked"</c:if> />
					符合 &nbsp;&nbsp;
					<input type="checkbox" name="salesBackContractOfShowDto.legalIdea"
						id="checkbox4" onclick="return false"
						<c:if test="${buyContractOfAddDto.legalIdea == 0}">checked="checked"</c:if> />
					不符合
				</td>
			</tr>
			<tr>
				<td colspan="3" style="line-height:22px" align="left"><table cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td width="70" valign="top" align="left">补充说明：</td>
						<td align="left">${tf:replaceBr(buyContractOfAddDto.legalText)}</td>
					</tr>
				</table></td>
			</tr>
			<tr>
				<td style="line-height:22px" width="50%">&nbsp;</td>
				<td style="line-height:22px" width="25%" align="left">签字：${buyContractOfAddDto.legalName}</td>
				<td style="line-height:22px" width="25%" align="left">日期：${buyContractOfAddDto.legalDate}</td>
			</tr>
		</table></td>
	</tr>
	
	</c:if>
  	  			<c:if test="${buyContractOfAddDto.buyManIdea!=null && fn:substring(buyContractOfAddDto.buyManIdea,0,1)==0}">
					<c:set value="checked" var="sellCountUnpass" scope="page"></c:set>
				</c:if>
				<c:if test="${buyContractOfAddDto.buyManIdea!=null && fn:substring(buyContractOfAddDto.buyManIdea,0,1)==1}">
					<c:set value="checked" var="sellCountPass" scope="page"></c:set>
				</c:if> 

				<c:if test="${buyContractOfAddDto.buyManIdea!=null && fn:substring(buyContractOfAddDto.buyManIdea,1,2)==0}">
					<c:set value="checked" var="sellQuomodoUnpass" scope="page"></c:set>
				</c:if>
				<c:if test="${buyContractOfAddDto.buyManIdea!=null && fn:substring(buyContractOfAddDto.buyManIdea,1,2)==1}">
					<c:set value="checked" var="sellQuomodoPass" scope="page"></c:set>
				</c:if> 
				
				<c:if test="${buyContractOfAddDto.buyManIdea!=null && fn:substring(buyContractOfAddDto.buyManIdea,2,3)==0}">
					<c:set value="checked" var="sellAddressUnpass" scope="page"></c:set>
				</c:if>
				<c:if test="${buyContractOfAddDto.buyManIdea!=null && fn:substring(buyContractOfAddDto.buyManIdea,2,3)==1}">
					<c:set value="checked" var="sellAddressPass" scope="page"></c:set>
				</c:if> 
	<tr>
		<td colspan="2"><table cellpadding="0" cellspacing="0" width="100%" class="tad_2">
			<tr>
				<td colspan="3" style="line-height:22px" align="left">采购主管评审意见</td>
			</tr>
			<tr>
				<td style="line-height:22px" align="left">1.产品要求是否符合（数量、规格、技术、质量） &nbsp;</td>
				<td height="20px" colspan="2" align="left">
					<input type="checkbox" name="checkbox5" id="checkbox5" onclick="return false" <c:out value="${sellCountPass}"></c:out>/>符合
				  				&nbsp;&nbsp;
					<input type="checkbox" name="checkbox5" id="checkbox5" onclick="return false" <c:out value="${sellCountUnpass}"></c:out>/>不符合
				</td>
			</tr>
			<tr>
				<td style="line-height:22px" align="left">2.付款方式是否符合 &nbsp;</td>
				<td height="20px" colspan="2" align="left">
	  				<input type="checkbox" name="checkbox5" id="checkbox5" onclick="return false" <c:out value="${sellQuomodoPass}"></c:out>/>符合
		  				&nbsp;&nbsp;
					<input type="checkbox" name="checkbox5" id="checkbox5" onclick="return false" <c:out value="${sellQuomodoUnpass}"></c:out>/>不符合
				</td>
			</tr>
			<tr>
				<td style="line-height:22px" align="left">3.售后服务是否符合（含售前、售中） &nbsp;</td>
				<td height="20px" colspan="2" align="left">
	  				<input type="checkbox" name="checkbox5" id="checkbox5" onclick="return false" <c:out value="${sellAddressPass}"></c:out>/>符合
		  				&nbsp;&nbsp;
					<input type="checkbox" name="checkbox5" id="checkbox5" onclick="return false" <c:out value="${sellAddressUnpass}"></c:out>/>不符合
				</td>
			</tr>
			<tr>
				<td colspan="3" style="line-height:22px"><table cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td width="70" valign="top" >补充说明：</td>
						<td align="left">${tf:replaceBr(buyContractOfAddDto.buyManText)}</td>
					</tr>
				</table></td>
			</tr>	
			<tr>
				<td style="line-height:22px" width="50%">&nbsp;</td>
				<td style="line-height:22px" width="25%" align="left">签字：${buyContractOfAddDto.buyManName}</td>
				<td style="line-height:22px" width="25%" align="left">日期：${buyContractOfAddDto.buyManDate}</td>
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
									<input type="checkbox" name="salesBackContractOfShowDto.opeMajIdea"
										id="checkbox4" onclick="return false"
										<c:if test="${buyContractOfAddDto.opeMajIdea == 1}">checked="checked"</c:if> />
									同意 &nbsp;&nbsp;
									<input type="checkbox" name="salesBackContractOfShowDto.opeMajIdea"
										id="checkbox4" onclick="return false"
										<c:if test="${buyContractOfAddDto.opeMajIdea == 0}">checked="checked"</c:if> />
									不同意
				</td>
			</tr>
			<tr>
				<td colspan="3" style="line-height:22px"><table cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td width="70" valign="top">补充说明：</td>
						<td align="left">${tf:replaceBr(buyContractOfAddDto.opeMajText)}</td>
					</tr>
				</table></td>
			</tr>
			<tr>
				<td style="line-height:22px" width="50%">&nbsp;</td>
				<td style="line-height:22px" width="25%" align="left">签字：${buyContractOfAddDto.opeMajName}</td>
				<td style="line-height:22px" width="25%" align="left">日期：${buyContractOfAddDto.opeMajDate}</td>
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
				<td style="line-height:22px" width="50%">&nbsp;</td>
				<td style="line-height:22px" width="25%" align="left">签字：</td>
				<td style="line-height:22px" width="25%" align="left">日期：</td>
			</tr>
			
		</table></td>
	</tr>
	<tr>
		<td colspan="2" align="center" height="50px">
			<c:choose>
				<c:when test="${param.print && buyContractOfAddDto.status==10}">
				<!-- <a href="#" onclick="javascript:return printIt();">
					<img src="${ctx}/images/btnPrint.gif" width="65" height="20" class="buttonNoPrint" /></a> -->
					<img src="${ctx}/images/btnPrint.gif" width="65" height="20" class="buttonNoPrint" onClick="printIt(this)"/>
				</c:when>
				<c:otherwise>
				<a href="#" onclick="printfalse()">
					<img src="${ctx}/images/btnPrint.gif" width="65" height="20" class="buttonNoPrint" /></a>
				</c:otherwise>
			</c:choose>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="#" onclick="javascript:printSetup();"><img src="${ctx}/images/btnPrintSZ.gif" width="88" height="20" class="buttonNoPrint" /></a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="#" onclick="javascript:printPreview();"><img src="${ctx}/images/btnPrintYL.gif" class="buttonNoPrint" /></a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	
		</td>
	</tr>
	
</table>
<input  type="hidden" value="${buyContractOfAddDto.id	}" name="id" id="id"/>
</html:form>
</body>
</html>
