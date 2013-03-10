<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>
<%@taglib prefix="tf" uri="localhost" %>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>销售合同确认</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
		<style type="text/css"> 
		<!--
		.STYLE1 {color: #FF0000;}
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
				if($("#creditTable")){
					$("#creditTable tr:even").addClass("treven");
					$("#creditTable tr").each(function(i){
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
		</script>
		<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
		<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/messages_cn.js"></script>
		<script type="text/javascript">
		
		
        
        $(window).load(function(){
        	
			//判断是有隐藏产品总监或法务专员的评审意见
			if($("#proMajIdeaaHidden").val() == -1){
				$(".proMajIdeaDIV").hide();
			}
			if($("#legalIdeaHidden").val() == -1){
				$(".legalIdeaDIV").hide();
			}
			//根据模板类型来显示不同的合同信息
			var templateTypeVal = $("#salesContractTemplateType").val();
			if(templateTypeVal == 0){
				$("#placeAndProtectLimit").show();
				$("#checkLimitAndBreach").show();
				$("#salesContractLawsuit").show();
				$("#salesContractClause").hide();
			}else if(templateTypeVal == 1){
				$("#placeAndProtectLimit").show();
				$("#checkLimitAndBreach").show();
				$("#salesContractLawsuit").show();
				$("#salesContractClause").show();
				$(".preview").hide();
			}else{
				$("#placeAndProtectLimit").hide();
				$("#checkLimitAndBreach").hide();
				$("#salesContractLawsuit").hide();
				$("#salesContractClause").hide();
			}
			
			var msg = "${msg}";  //获取服务端信息
			
			 if(msg != ""){
     			alert(msg);
    		 }      	
        });	
        
		function subSales(){	
			if(${salesContract.stampType} ==1){
		
				 if($("#salesConForm").validate().form()==false){
				  return false;
				 }
			}
 
			$.ajax({
				  type: "POST",
				  async: false,
				  url: "${ctx}/checkCompanycontractcode.do",
				  data: "checkCode=" + $("#companyContractCode").val() +"&date="+Date(),
				     success: function(msg){ 
					     if(msg==0){
							alert("公司合同号已存在,请重新输入！");
							  return false;
						 }else{
							if(confirm("警告：公司合同号填写后不可更改!请再次确认！")){
								salesConForm.submit();
							}
						 }
					     
						 
					}
				}); 


			
		}
       
		$(document).ready(function(){
			if(${salesContract.stampType} ==0){
    			
    			$("#companyContractCode").hide();
    		}
	
	
			$("#salesConForm").validate({
			
				rules:{
					"companyContractCode": "required"
				},
				messages: {
					"companyContractCode": {required:"必填字段"}
				}

			});	
		
	
		  
       });	
	
    function cancelEffect(){
	    if(confirm("确认取消合同？")){
		   location.href = "${ctx}/salesContractCancel.do?salesContractId=${salesContract.id}";
		}
	}
   
	</script>
	</head>

	<body>
		<form action="${ctx}/modifyContractStatusEffect.do" method="post" id="salesConForm">
			<input type="hidden" name="salesContractId" value="${salesContract.id}">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td class="ye_header_left">
						&nbsp;
					</td>
					<td class="ye_header_center">
						<img src="${ctx}/images/main_jt.jpg" />
						&nbsp;当前位置： 销售管理 &gt;&gt; 销售合同管理 &gt;&gt; 销售合同确认
					</td>
					<td class="ye_header_right">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
					<td>
						<br />
						<div class="div_tiao">
							&nbsp;&nbsp;合同信息
						</div>
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="biao3">
							<tr>
           					 	<td class="td_01" height="18px">产品合同号</td>
            					<td class="td_02" >${salesContract.productContractCode}&nbsp;</td>
            					<td class="td_01">公司合同号</td>
            					<td class="td_02" ><input type="text" id="companyContractCode" name="companyContractCode" value="${salesContract.companyContractCode}" maxlength="20"></td>
        					</tr>
							<tr>
								<td class="td_01" width="20%" height="18px">
									盖章类型
								</td>
								<td class="td_02" width="30%">
									<logic:equal value="0" property="stampType"
										name="salesContract">
            		业务章
            	</logic:equal>
									<logic:equal value="1" property="stampType"
										name="salesContract">
            		合同章
            	</logic:equal>
								</td>
								<td class="td_01" width="20%">
									模版类型
								</td>
								<input type="hidden" id="salesContractTemplateType" value="${salesContract.templateType}">
								<td class="td_02" width="30%">
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
            		非模板&nbsp;
            	</logic:equal>
								</td>
							</tr>
							<tr id="placeAndProtectLimit">
								<td class="td_01" height="18px">
									签订地点
								</td>
								<td class="td_02">
									${salesContract.place}&nbsp;
								</td>
								<td class="td_01">
									保价期限
								</td>
								<td class="td_02">
									${salesContract.protectLimit}日&nbsp;
								</td>
							</tr>
							<tr id="checkLimitAndBreach">
								<td class="td_01" height="18px">
									验收期限
								</td>
								<td class="td_02">
									${salesContract.checkLimit}日&nbsp;
								</td>
								<td class="td_01">
									违约金标准
								</td>
								<td class="td_02">
									${salesContract.breach}&nbsp;
								</td>
							</tr>
							<tr id="salesContractLawsuit">
								<td class="td_01" height="18px">
									诉讼地
								</td>
								<td class="td_02">
									${salesContract.lawsuit}&nbsp;
								</td>
								<td class="td_01">
									&nbsp;
								</td>
								<td class="td_02">
									&nbsp;
								</td>
							</tr>
							<tr id="salesContractClause">
								<td class="td_01" height="18px">
									合同附加条款
								</td>
								<td class="td_02" colspan="3">
									${tf:replaceBr(salesContract.clause)}&nbsp;
								</td>
							</tr>
						</table>
						<br />
						<div class="div_tiao">
							&nbsp;&nbsp;客户信息
						</div>
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="biao3">
							<tr>
								<td class="td_01" width="20%" height="18px">
									客户名称
								</td>
								<td class="td_02" width="30%">
									${salesContract.customerName}&nbsp;
								</td>
								<td class="td_01" width="20%">
									联系人
								</td>
								<td class="td_02" width="30%">
									${salesContract.customerLinkmanName}&nbsp;
								</td>
							</tr>
							<tr>
								<td class="td_01" height="18px">
									省份
								</td>
								<td class="td_02">
									${salesContract.customerProvince}&nbsp;
								</td>
								<td class="td_01">
									电话
								</td>
								<td class="td_02">
									${salesContract.customerLinkmanTel}&nbsp;
								</td>
							</tr>
							<tr>
								<td class="td_01" height="18px">
									城市
								</td>
								<td class="td_02">
									${salesContract.customerCity}&nbsp;
								</td>
								<td class="td_01">
									传真
								</td>
								<td class="td_02">
									${salesContract.customerLinkmanFax}&nbsp;
								</td>
							</tr>
							<tr>
								<td class="td_01" height="18px">
									开户行
								</td>
								<td class="td_02">
									${salesContract.customerInvoiceBankName}&nbsp;
								</td>
								<td class="td_01">
									帐号
								</td>
								<td class="td_02">
									${salesContract.customerInvoiceBankAccount}&nbsp;
								</td>
							</tr>
							<tr>
								<td class="td_01" height="18px">
									税号
								</td>
								<td class="td_02">
									${salesContract.customerTaxNumber}&nbsp;
								</td>
								<td class="td_01">
									发票类型
								</td>
								<td class="td_02">
									<logic:equal value="0" property="customerInvoiceType"
										name="salesContract">
            		普通&nbsp;
            	</logic:equal>
									<logic:equal value="1" property="customerInvoiceType"
										name="salesContract">
            		增值税&nbsp;
            	</logic:equal>
								</td>
							</tr>
						</table>
						<br />
						<div class="div_tiao">
							&nbsp;&nbsp;产品信息
						</div>
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="biao3">
							<tr>
								<td class="td_01" height="18px" width="20%">
									产品分类名称
								</td>
								<td class="td_02" width="30%">
									${salesContract.productTypeName}&nbsp;
								</td>
								<td class="td_01" width="20%">
									产品部门名称
								</td>
								<td class="td_02" width="30%">
									${salesContract.productDeptName}&nbsp;
								</td>
							</tr>
						</table>
						<div style="width:100%; text-align:right">
							单位：元
						</div>
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="biao1" id="table" style="border-left:1px solid #FFFFFF;">
							<tr>
								<th nowrap="nowrap" width="40"
									style="border-left:1px solid #c2c2c2;">
									序号
								</th>
								<th nowrap="nowrap">
									产品编码
								</th>
								<th nowrap="nowrap">
									产品名称
								</th>
								<th nowrap="nowrap">
									规格型号
								</th>
								<th nowrap="nowrap">
									单位
								</th>
								<th nowrap="nowrap">
									销售数
								</th>
								<th nowrap="nowrap">
									销售单价
								</th>
								<th nowrap="nowrap">
									销售金额
								</th>
								<th nowrap="nowrap">
									发货金额
								</th>
								<th nowrap="nowrap">
									备货金额
								</th>
								<th nowrap="nowrap">
									指定金额
								</th>
								<th nowrap="nowrap">
									开票金额
								</th>
								<th nowrap="nowrap">
									退货合同金额
								</th>
								<th nowrap="nowrap">
									退货金额
								</th>
							</tr>
							<logic:present name="salesConProductList">
								<logic:iterate id="productlist" name="salesConProductList"
									indexId="indexId">
									<tr>
										<td nowrap="nowrap" style="border-left:1px solid #c2c2c2;" height="18px">
											${indexId+1}&nbsp;
										</td>
										<td nowrap="nowrap">
											${productlist.productCode}&nbsp;
										</td>
										<td nowrap="nowrap">
											${productlist.productName}&nbsp;
										</td>
										<td nowrap="nowrap">
											${productlist.productType}&nbsp;
										</td>
										<td nowrap="nowrap">
											${productlist.productUnit}&nbsp;
										</td>
										<td nowrap="nowrap" style="text-align:right; padding-right:5px">
											${productlist.salesCount}&nbsp;
										</td>
										<td nowrap="nowrap" style="text-align:right; padding-right:5px">
										    <fmt:formatNumber value="${productlist.salesPrice}" pattern="#,##0.00"/>&nbsp;
										</td>
										<td nowrap="nowrap" style="text-align:right; padding-right:5px">
										    <fmt:formatNumber value="${productlist.salesMoney}" pattern="#,##0.00"/>&nbsp;
										</td>
										<td nowrap="nowrap" style="text-align:right; padding-right:5px">
										    <fmt:formatNumber value="${productlist.sendGoodsMoney}" pattern="#,##0.00"/>&nbsp;
										</td>
										<td nowrap="nowrap" style="text-align:right; padding-right:5px">
										    <fmt:formatNumber value="${productlist.readyGoodsMoney}" pattern="#,##0.00"/>&nbsp;
										</td>
										<td nowrap="nowrap" style="text-align:right; padding-right:5px">
										    <fmt:formatNumber value="${productlist.appointMoney}" pattern="#,##0.00"/>&nbsp;
										</td>
										<td nowrap="nowrap" style="text-align:right; padding-right:5px">
										    <fmt:formatNumber value="${productlist.invoiceMoney}" pattern="#,##0.00"/>&nbsp;
										</td>
										<td nowrap="nowrap" style="text-align:right; padding-right:5px">
										    <fmt:formatNumber value="${productlist.backSellMoney}" pattern="#,##0.00"/>&nbsp;
										</td>
										<td nowrap="nowrap" style="text-align:right; padding-right:5px">
										    <fmt:formatNumber value="${productlist.backGoodsMoney}" pattern="#,##0.00"/>&nbsp;
										</td>
										
									</tr>
								</logic:iterate>
							</logic:present>
						
							<tr>
								<td colspan="7" nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF">
									销售金额合计
								</td>
								<td nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF">
								    <fmt:formatNumber value="${salesContract.money}" pattern="#,##0.00"/>&nbsp;
								</td>
								<td colspan="5" nowrap="nowrap" style=" border:1px solid #FFFFFF;  background-color:#FFFFFF">
									<span style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF">元</span>
								</td>
							</tr>
						</table>
						<br />
						<div class="div_tiao">
							&nbsp;&nbsp;付款信息
						</div>
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="biao3">
							<tr>
								<td class="td_01" width="20%" height="18px">
									付款方式
								</td>
								<td class="td_02" width="30%">
									<logic:equal value="0" property="paymentWay"
										name="salesContract">
            		全部现结
            	</logic:equal>
									<logic:equal value="1" property="paymentWay"
										name="salesContract">
            		全部信用
            	</logic:equal>
									<logic:equal value="2" property="paymentWay"
										name="salesContract">
            		部分现结部分信用
            	</logic:equal>
								</td>
								<td class="td_01" width="20%">
									现结金额(元)
								</td>
								<td class="td_02" width="30%">
								    <fmt:formatNumber value="${salesContract.cashMoney}" pattern="#,##0.00"/>&nbsp;
								</td>
							</tr>
							<tr id="td_fenpi" <c:if test="${salesContract.paymentWay == 0}">  style="display:none"</c:if>>
								<td class="td_01" height="18px">
									分批方式
								</td>
								<td class="td_02">
									<logic:equal value="0" property="batchWay" name="salesContract">
            		不分批
            	</logic:equal>
									<logic:equal value="1" property="batchWay" name="salesContract">
            		分批
            	</logic:equal>
								</td>
								<td class="td_01">
									单批最大金额(元)
								</td>
								<td class="td_02">
								    <fmt:formatNumber value="${salesContract.batchMaxMoney}" pattern="#,##0.00"/>&nbsp;
								</td>
							</tr>
						</table>
						<br />
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="biao1" id="creditTable">
							<tr>
								<th nowrap="nowrap">
									信用类型
								</th>
								<th nowrap="nowrap">
									项目名称
								</th>
								<th nowrap="nowrap">
									帐期(天)
								</th>
								<th nowrap="nowrap">
									信用额度(元)
								</th>
								<th nowrap="nowrap">
									可用额度(元)
								</th>
							</tr>
							<tr>
								<td nowrap="nowrap" id="td0" height="18px">
									${salesContract.creditTypeName}&nbsp;
								</td>
								<td nowrap="nowrap" id="td1">
									${salesContract.projectName}&nbsp;
								</td>
								<td nowrap="nowrap" id="td2" style="text-align:right;">
									${salesContract.arterm}&nbsp;
								</td>
								<td nowrap="nowrap" id="td3" style="text-align:right;">
								    <fmt:formatNumber value="${salesContract.climit}" pattern="#,##0.00"/>&nbsp;
								</td>
								<td nowrap="nowrap" id="td4" style="text-align:right;">
								   <fmt:formatNumber value="${salesContract.freeLimit}" pattern="#,##0.00"/>&nbsp;
								</td>
							</tr>
						</table>
						<br />
						<div class="div_tiao">
							&nbsp;&nbsp;发货信息
						</div>
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="biao3">
							<tr>
								<td class="td_01" height="18px">
									货物接收单位名称
								</td>
								<td class="td_02">
									${salesContract.customerAddressName}&nbsp;
								</td>
								<td class="td_01">
									要求发货日期
								</td>
								<td class="td_02">
									${salesContract.requestSendDate}&nbsp;
								</td>
							</tr>
							<tr>
								<td class="td_01" height="18px">
									发货地址
								</td>
								<td colspan="3" class="td_02">
									${salesContract.customerAddressAddress}&nbsp;
								</td>
							</tr>
							<tr>
								<td class="td_01" width="20%" height="18px">
									联系人
								</td>
								<td class="td_02" width="30%">
									${salesContract.customerAddressLinkman}&nbsp;
								</td>
								<td class="td_01" width="20%">
									邮编
								</td>
								<td class="td_02" width="30%">
									${salesContract.customerAddressPostcode}&nbsp;
								</td>
							</tr>
							<tr>
								<td class="td_01" height="18px">
									电话
								</td>
								<td class="td_02">
									${salesContract.customerAddressTel}&nbsp;
								</td>
								<td class="td_01">
									手机
								</td>
								<td class="td_02">
									${salesContract.customerAddressMobile}&nbsp;
								</td>
							</tr>
						</table>
						<br />
						<div class="div_tiao">
							&nbsp;&nbsp;供方信息
						</div>
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="biao3">
							<tr>
								<td class="td_01" width="20%" height="18px">
									人员名称
								</td>
								<td class="td_02" width="30%">
									${salesContract.userName}&nbsp;
								</td>
								<td class="td_01" width="20%">
									人员区域
								</td>
								<td class="td_02" width="30%">
									${salesContract.userAreaName}&nbsp;
								</td>
							</tr>
							<tr>
								<td class="td_01" height="18px">
									电话
								</td>
								<td class="td_02">
									${salesContract.userTel}&nbsp;
								</td>
								<td class="td_01">
									传真
								</td>
								<td class="td_02">
									${salesContract.productDeptFax}&nbsp;
								</td>
							</tr>
							<tr>
								<td class="td_01" height="18px">
									帐号
								</td>
								<td class="td_02">
									${salesContract.productDeptAccount}&nbsp;
								</td>
								<td class="td_01">
									&nbsp;
								</td>
								<td class="td_02">
									&nbsp;
								</td>
							</tr>
						</table>
						<br />
						<div class="div_tiao">
							&nbsp;&nbsp;特殊说明
						</div>
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="biao3">
							<tr>
								<td class="td_03" width="20%">
									特殊说明
								</td>
								<td class="td_04">
									${tf:replaceBr(salesContract.text)}&nbsp;
								</td>
							</tr>
						</table>
						<br />
						<div class="div_tiao">
							&nbsp;&nbsp;评审意见
						</div>
						<table border="0" cellpadding="0" cellspacing="0" align="center"
							width="460">
								<input type="hidden" name="proMajIdeaHidden" id="proMajIdeaaHidden" value="${salesContract.proMajId}"	>
								<tr class="proMajIdeaDIV">
									<td height="20px" colspan="2">
										产品总监评审意见：
									</td>
								</tr>
								<tr class="proMajIdeaDIV">
									<td width="320" nowrap="nowrap">
										1.产品要求是否符合（数量、规格、技术、质量）
									</td>
									<td height="20px" width="140" nowrap="nowrap">
										<input type="checkbox" name="salesContract.proMajIdea1"
											id="checkbox" disabled="disabled"
											<c:if test="${salesContract.proMajIdea1 == 1}">checked="checked"</c:if> />
										符合 &nbsp;&nbsp;
										<input type="checkbox" name="salesContract.proMajIdea1"
											id="checkbox" disabled="disabled"
											<c:if test="${salesContract.proMajIdea1 == 0}">checked="checked"</c:if> />
										不符合
									</td>
								</tr>
								<tr class="proMajIdeaDIV">
									<td>
										2.交付日期是否符合
									</td>
									<td height="20px">
										<input type="checkbox" name="salesContract.proMajIdea2"
											id="checkbox2" disabled="disabled"
											<c:if test="${salesContract.proMajIdea2 == 1}">checked="checked"</c:if> />
										符合 &nbsp;&nbsp;
										<input type="checkbox" name="salesContract.proMajIdea2"
											id="checkbox2" disabled="disabled"
											<c:if test="${salesContract.proMajIdea2 == 0}">checked="checked"</c:if> />
										不符合
									</td>
								</tr>
								<tr class="proMajIdeaDIV">
									<td>
										3.包装要求是否符合
									</td>
									<td height="20px">
										<input type="checkbox" name="salesContract.proMajIdea3"
											id="checkbox3" disabled="disabled"
											<c:if test="${salesContract.proMajIdea3 == 1}">checked="checked"</c:if> />
										符合 &nbsp;&nbsp;
										<input type="checkbox" name="salesContract.proMajIdea3"
											id="checkbox3" disabled="disabled"
											<c:if test="${salesContract.proMajIdea3 == 0}">checked="checked"</c:if> />
										不符合
									</td>
								</tr>
								<tr class="proMajIdeaDIV">
									<td colspan="2" valign="top">
										<table width="98%" border="0" cellpadding="0" cellspacing="0">
											<tr>
												<td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap>
													补充说明：
												</td>
												<td style="padding:5px 0 5px 0;" width="330px">
													${tf:replaceBr(salesContract.proMajText)}
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr class="proMajIdeaDIV">
									<td height="20px">
										签字：${salesContract.proMajName}
									</td>
									<td>
										日期：${salesContract.proMajDate2}
									</td>
								</tr>
							<tr>
								<td height="20px" colspan="2">
									&nbsp;
								</td>
							</tr>
								<input type="hidden" name="legalIdeaHidden" id="legalIdeaHidden" value="${salesContract.legalId}"	>
								<tr class="legalIdeaDIV">
									<td height="20px" colspan="2">
										法务专员评审意见：
									</td>
								</tr>
								<tr class="legalIdeaDIV">
									<td>
										法律法规是否符合
									</td>
									<td height="20px">
										<input type="checkbox" name="salesContract.legalIdea"
											id="checkbox4" disabled="disabled"
											<c:if test="${salesContract.legalIdea == 1}">checked="checked"</c:if> />
										符合 &nbsp;&nbsp;
										<input type="checkbox" name="salesContract.legalIdea"
											id="checkbox4" disabled="disabled"
											<c:if test="${salesContract.legalIdea == 0}">checked="checked"</c:if> />
										不符合
									</td>
								</tr>
								<tr class="legalIdeaDIV">
									<td colspan="2" valign="top">
										<table width="98%" border="0" cellpadding="0" cellspacing="0">
											<tr>
												<td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap>
													补充说明：
												</td>
												<td style="padding:5px 0 5px 0;" width="330px">
													${tf:replaceBr(salesContract.legalText)}
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr class="legalIdeaDIV">
									<td height="20px">
										签字：${salesContract.legalName}
									</td>
									<td>
										日期：${salesContract.legalDate2}
									</td>
								</tr>
							<tr>
								<td height="20px" colspan="2">
									&nbsp;
								</td>
							</tr>
							<tr>
								<td height="20px" colspan="2">
									销售总监评审意见：
								</td>
							</tr>
							<tr>
								<td>
									1.毛利率是否符合
								</td>
								<td height="20px" width="150">
									<input type="checkbox" name="salesContract.sellMajIdea1"
										id="checkbox" disabled="disabled"
										<c:if test="${salesContract.sellMajIdea1 == 1}">checked="checked"</c:if> />
									符合 &nbsp;&nbsp;
									<input type="checkbox" name="salesContract.sellMajIdea1"
										id="checkbox" disabled="disabled"
										<c:if test="${salesContract.sellMajIdea1 == 0}">checked="checked"</c:if> />
									不符合
								</td>
							</tr>
							<tr>
								<td>
									2.付款方式是否符合
								</td>
								<td height="20px">
									<input type="checkbox" name="salesContract.sellMajIdea2"
										id="checkbox2" disabled="disabled"
										<c:if test="${salesContract.sellMajIdea2 == 1}">checked="checked"</c:if> />
									符合 &nbsp;&nbsp;
									<input type="checkbox" name="salesContract.sellMajIdea2"
										id="checkbox2" disabled="disabled"
										<c:if test="${salesContract.sellMajIdea2 == 0}">checked="checked"</c:if> />
									不符合
								</td>
							</tr>
							<tr>
								<td>
									3.运输方式是否符合
								</td>
								<td height="20px">
									<input type="checkbox" name="salesContract.sellMajIdea3"
										id="checkbox5" disabled="disabled"
										<c:if test="${salesContract.sellMajIdea3 == 1}">checked="checked"</c:if> />
									符合 &nbsp;&nbsp;
									<input type="checkbox" name="salesContract.sellMajIdea3"
										id="checkbox5" disabled="disabled"
										<c:if test="${salesContract.sellMajIdea3 == 0}">checked="checked"</c:if> />
									不符合
								</td>
							</tr>
							<tr>
								<td>
									4.售后服务是否符合（含售前、售中）
								</td>
								<td height="20px">
									<input type="checkbox" name="salesContract.sellMajIdea4"
										id="checkbox3" disabled="disabled"
										<c:if test="${salesContract.sellMajIdea4 == 1}">checked="checked"</c:if> />
									符合 &nbsp;&nbsp;
									<input type="checkbox" name="salesContract.sellMajIdea4"
										id="checkbox3" disabled="disabled"
										<c:if test="${salesContract.sellMajIdea4 == 0}">checked="checked"</c:if> />
									不符合
								</td>
							</tr>
							<tr>
								<td colspan="2" valign="top">
									<table width="98%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap>
												补充说明：
											</td>
											<td style="padding:5px 0 5px 0;" width="330px">
												${tf:replaceBr(salesContract.sellMajText)}
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td height="20px">
									签字：${salesContract.sellMajName}
								</td>
								<td>
									日期：${salesContract.sellMajDate2}
								</td>
							</tr>
							<tr>
								<td height="20px" colspan="2">
									&nbsp;
								</td>
							</tr>
							<tr>
								<td height="20px" colspan="2">
									运营总监评审意见：
								</td>
							</tr>
							<tr>
								<td>
									&nbsp;
								</td>
								<td height="20px">
									<input type="checkbox" name="salesContract.opeMajIdea"
										id="checkbox4" disabled="disabled"
										<c:if test="${salesContract.opeMajIdea == 1}">checked="checked"</c:if> />
									同意 &nbsp;&nbsp;
									<input type="checkbox" name="salesContract.opeMajIdea"
										id="checkbox4" disabled="disabled"
										<c:if test="${salesContract.opeMajIdea == 0}">checked="checked"</c:if> />
									不同意
								</td>
							</tr>
							<tr>
								<td colspan="2" valign="top">
									<table width="98%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap>
												补充说明：
											</td>
											<td style="padding:5px 0 5px 0;" width="330px">
												${tf:replaceBr(salesContract.opeMajText)}
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td height="20px">
									签字：${salesContract.opeMajName}
								</td>
								<td>
									日期：${salesContract.opeMajDate2}
								</td>
							</tr>
						</table>
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td></td>
					<td height="50px" align="center" valign="bottom">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="#" onclick="subSales()"><img src="${ctx}/images/btnSX.gif" width="65" height="20"/></a>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="#" onclick="cancelEffect()"><img src="${ctx}/images/btnQXHT.gif" width="88" height="20"
								id="cancelContract" /></a>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<img src="${ctx}/images/btnBack.gif" onClick="javascript:history.go(-1);" />
					</td>
					<td></td>
				</tr>
			</table>
			<br />
	<form>
	</body>
</html>
