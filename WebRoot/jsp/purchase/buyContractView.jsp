<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="tf" uri="localhost" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>采购合同查看</title>
	<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
		<style type="text/css">
		<!--
		.treven {
			background-color: #f3fbff;
		}
		.over {
			background-color: #ECECEC;
		}
		-->
		</style>
		<script language="JavaScript"> 
		function prevew(){
			 	window.open("${ctx}/getBuyContractPreview.do?buyContractId=${buyContractSelectDto.id}&print="+$("#print").val()+"",'newwindow', "toolbar=no,scrollbars=yes,resizable=yes,top=0,left=170, width=750,height=680");		
			}
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
		$("#decide").click(function(){//生效
			if($.trim($("#companyContractCode").val())==""){
				$("#companyContrctCodeError").css("color","red").text("请输入公司合同号！");
				$("#companyContractCode").focus().attr("class","invalid");
				return;
			}else{
				if(confirm("警告：公司合同号填写后不可更改!请再次确认！")){
					$("#buyContractDecideForm").attr("action","getBuyContractDecideOfTransact.do?decideType=true");
					$("#buyContractDecideForm").submit();
				}
			}
		});
		$("#cancel").click(function(){//取消合同
				$("#buyContractDecideForm").attr("action","getBuyContractDecideOfTransact.do?decideType=false");
				$("#buyContractDecideForm").submit();
		});
					});
		</script>
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
		<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="${ctx}/js/messages_cn.js"></script>
		<style type="text/css">
		<!--
		.STYLE1 {color: #FF0000}
		-->
		</style>
</head>

<body>
<c:if test="${param.decideError}">
	<script>alert("确认失败！");</script>
</c:if>
<c:if test="${param.supplierLinkmanIdError}">
	<script>alert("请填写合同号");</script>
</c:if>
<c:if test="${param.codeExistError}">
	<script>alert("此合同号已经存在");</script>
</c:if>
<c:if test="${param.notExistError}">
	<script>alert("采购合同文件不存在!");</script>
</c:if>
<c:if test="${param.statusError}">
	<script>alert("执行失败，此状态不能操作");</script>
</c:if>
<c:if test="${param.downLoadError}">
	<script>alert("下载文件出错!");</script>
</c:if>
<c:choose>
	<c:when test="${param.decide}">
		<c:set var ="title" value="采购合同确认"></c:set>
	</c:when>
	<c:otherwise>
		<c:set var ="title" value="采购合同查看"></c:set>
	</c:otherwise>
</c:choose>
<html:form action="getBuyContractDecideOfTransact" method="post" styleId="buyContractDecideForm">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 采购管理 &gt;&gt; 采购合同管理 &gt;&gt; <c:out value="${title}"></c:out> </td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td><br />
    <div class="div_tiao"> &nbsp;&nbsp;合同信息 </div>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
          <tr>
		 	<td class="td_01" height="18px">产品合同号</td>
			<td class="td_02" >${buyContractSelectDto.productContractCode}&nbsp;</td>
			<td class="td_01">公司合同号</td>
			<td class="td_02" >
				<c:choose>
					<c:when test="${param.decide}">
						<html:text property="buyContractOfAddDto.companyContractCode" styleId="companyContractCode" maxlength="20"></html:text>
			            	<span id="companyContrctCodeError"></span>
					</c:when>
					<c:otherwise>
						${buyContractSelectDto.companyContractCode}&nbsp;
					</c:otherwise>
				</c:choose>
			</td>
		 </tr>
          <tr>
            <td class="td_01" width="20%" height="18px">合同类型</td>
            <td class="td_02" width="30%">
            	<font class="buy_contract_contractType">
            	<c:if test="${buyContractSelectDto.contractType==0}">
            	国内
            	</c:if>
            	<c:if test="${buyContractSelectDto.contractType==1}">
            	国外
            	</c:if>
            	</font>
			</td>
            <td class="td_01" width="20%" height="18px">模版类型</td>
            <td class="td_02" width="30%">
            <c:if test="${buyContractSelectDto.templateType==0}">
            	模板
            </c:if>
            	<c:if test="${buyContractSelectDto.templateType==1}">
            	非模板
            	</c:if>
			</td>
          </tr>
           <c:if test="${buyContractSelectDto.templateType==0}">
          <tr>
            <td class="td_01" height="18px">签订地点</td>
            <td class="td_02">${buyContractSelectDto.place}</td>
            <td class="td_01">质量标准</td>
            <td class="td_02">${buyContractSelectDto.quality}</td>
          </tr>
          <tr>
            <td class="td_01" height="18px">验收期限</td>
            <td class="td_02">${buyContractSelectDto.checkLimit}日</td>
            <td class="td_01">&nbsp;保质期</td>
            <td class="td_02">${buyContractSelectDto.protectLimit}</td>
          </tr>
          </c:if>
      </table>
      <br />
      <div class="div_tiao"> &nbsp;&nbsp;供货商信息 </div>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
          <tr>
            <td class="td_01" width="20%" height="18px">供货商名称</td>
            <td class="td_02" width="30%">${buyContractSelectDto.supplierName}</td>
            <td class="td_01" width="20%">&nbsp;联系人</td>
            <td class="td_02" width="30%">${buyContractSelectDto.linkmanName}</td>
          </tr>
          <tr>
            <td class="td_01" height="18px">省份</td>
            <td class="td_02">${buyContractSelectDto.province}</td>
            <td class="td_01">电话</td>
            <td class="td_02">${buyContractSelectDto.linkmanTel}</td>
          </tr>
          <tr>
            <td class="td_01" height="18px">城市</td>
            <td class="td_02">${buyContractSelectDto.city}</td>
            <td class="td_01">传真</td>
            <td class="td_02">${buyContractSelectDto.linkmanFax}&nbsp;</td>
          </tr>
           <tr>
            <td class="td_01" height="18px">开户行</td>
            <td class="td_02">${buyContractSelectDto.remitBankName}</td>
            <td class="td_01">帐号</td>
            <td class="td_02">${buyContractSelectDto.remitBankAccount}</td>
           </tr>
           <tr>
            <td class="td_01" height="18px">发票类型</td>
            <td class="td_02">
            <c:if test="${buyContractSelectDto.invoiceType==0}">
            	普通
            </c:if>
            <c:if test="${buyContractSelectDto.invoiceType!=0}">
            	增值税
            </c:if>
            </td>
            <td class="td_01">增值税税率</td>
            <td class="td_02"> ${buyContractSelectDto.taxRate}%</td>
          </tr>
      </table>
    <div class="div_tiao"> &nbsp;&nbsp;产品信息 </div>
    <input type="hidden" id="decideType" name="decideType"/>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
            <tr>
                <td class="td_01" height="18px" width="20%">产品分类名称</td>
                <td class="td_02" width="30%">${buyContractSelectDto.productTypeName}</td>
                <td class="td_01" width="20%">产品部门名称</td>
                <td class="td_02" width="30%">${buyContractSelectDto.productDeptName}</td>
            </tr>
            <tr>
						<td class="td_01" width="20%"><span style="color:#FF0000">*</span>&nbsp;发货方式</td>
						<td colspan="3" class="td_02">
						<c:if test="${buyContractSelectDto.deliveryTerms==0 }">允许分批交货</c:if>
						<c:if test="${buyContractSelectDto.deliveryTerms==1 }">不允许分批交货</c:if>
					</tr>
   	  </table>
      <div style="width:100%; text-align:right">单位：元</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table" style="border-left:1px solid #FFFFFF;">
        <tr>
          <th width="40" nowrap="nowrap" style="border-left:1px solid #c2c2c2;">序号</th>
          <th nowrap="nowrap">产品编码</th>
          <th nowrap="nowrap">产品名称</th>
          <th nowrap="nowrap">规格型号</th>
          <th nowrap="nowrap">单位</th>
          <th nowrap="nowrap">采购数</th>
          <th nowrap="nowrap">采购单价</th>
          <th nowrap="nowrap">采购金额</th>
          <th nowrap="nowrap">入库金额</th>
          <th nowrap="nowrap">指定金额 </th>
          <th nowrap="nowrap">收票金额</th>
          <th nowrap="nowrap">退货合同金额</th>
          <th nowrap="nowrap">返厂金额</th>
        </tr>
        <logic:present name="buyContractProducts">
        <bean:define id="total" value="0" type="java.lang.String"/>
        <logic:iterate id="buyContractProduct" name="buyContractProducts" indexId="buyContractProductindex">
        <bean:define id="total" value="${buyContractProduct.buyMoney+total}" type="java.lang.String"/>
        <tr>
          <td nowrap="nowrap" style="border-left:1px solid #c2c2c2;text-align:right;padding-right:5px" height="18px">${buyContractProductindex+1}</td>
          <td nowrap="nowrap" >${buyContractProduct.prodCode}</td>
          <td nowrap="nowrap" >${buyContractProduct.prodName}</td>
          <td nowrap="nowrap" >${buyContractProduct.prodType}</td>
          <td nowrap="nowrap" >${buyContractProduct.prodUnit}</td>
          <td nowrap="nowrap" style="text-align:right;padding-right:5px">${buyContractProduct.buyCount}</td>
          <td nowrap="nowrap" style="text-align:right;padding-right:5px">
          	<fmt:formatNumber value="${buyContractProduct.buyPrice}" pattern="#,##0.00000"/>
          </td>
          <td nowrap="nowrap" style=" text-align:right;">
          	<fmt:formatNumber value="${buyContractProduct.buyMoney}" pattern="#,##0.00000"/>&nbsp;
          </td>
	          <td nowrap="nowrap" style=" text-align:right;">
	          	<fmt:formatNumber value="${buyContractProduct.instockMoney}" pattern="#,##0.00000"/>&nbsp;
		  </td>
          <td nowrap="nowrap" style=" text-align:right;">
				<fmt:formatNumber value="${buyContractProduct.appointMoney}" pattern="#,##0.00000"/>&nbsp;
			</td>
          <td nowrap="nowrap"  style=" text-align:right;">
				<fmt:formatNumber value="${buyContractProduct.receiveInvoiceMoney}" pattern="#,##0.00000"/>&nbsp;
			</td>
          <td nowrap="nowrap" style=" text-align:right;">
				<fmt:formatNumber value="${buyContractProduct.backContractMoney}" pattern="#,##0.00000"/>&nbsp;
		</td>
          <td nowrap="nowrap" style=" text-align:right;">
			<fmt:formatNumber value="${buyContractProduct.returnMoney}" pattern="#,##0.00000"/>&nbsp;
		</td>
        </tr>
        </logic:iterate>
        </logic:present>
        <tr>
        <td nowrap="nowrap" style="border:1px solid #FFFFFF; background-color:#FFFFFF">&nbsp;</td>
        <td nowrap="nowrap" style="border:1px solid #FFFFFF; background-color:#FFFFFF">&nbsp;</td>
          <td colspan="5" nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF"> 采购金额合计 </td>
          <td nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF"><fmt:formatNumber value="${total}" pattern="#,##0.00000"/></td>
          <td colspan="12" nowrap="nowrap" style=" border:1px solid #FFFFFF;  background-color:#FFFFFF"><span style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF">元</span></td>
        </tr>
      </table>
      <div class="div_tiao"> &nbsp;&nbsp;付款信息 </div>
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
          <tr>
            <td class="td_01" height="18px">付款方式</td>
            <td class="td_02"  <c:if test="${buyContractSelectDto.paymentWay!=5}">colspan="3"</c:if> >
            <c:if test="${buyContractSelectDto.paymentWay==2}">
            	支票
            </c:if>
             <c:if test="${buyContractSelectDto.paymentWay==3}">
            	网银
            </c:if>
              <c:if test="${buyContractSelectDto.paymentWay==4}">
            	电汇
            </c:if>
              <c:if test="${buyContractSelectDto.paymentWay==5}">
            	银行承兑
            </c:if>
              <c:if test="${buyContractSelectDto.paymentWay==6}">
            	信用证
            </c:if>
            </td>
             <c:if test="${buyContractSelectDto.paymentWay==5}">
	            <td class="td_01">账期</td>
	            <td class="td_02" >${buyContractSelectDto.arterm}天</td>
            </c:if>
          </tr>
          <tr>
            <td class="td_01" height="18px">付款类型</td>
            <td colspan="3" class="td_02" >
            <c:if test="${buyContractSelectDto.paymentType==0}">
            	全部预付
            </c:if>
            <c:if test="${buyContractSelectDto.paymentType==1}">
            	部分预付
            </c:if>
            <c:if test="${buyContractSelectDto.paymentType==2}">
            	货到付款
            </c:if>
            </td>
        </tr>
        <c:if test="${buyContractSelectDto.paymentType==0}">
          <tr>
            <td class="td_01" height="18px">合同签订后</td>
            <td colspan="3" class="td_02" >${buyContractSelectDto.contractPaymentTime}个工作日内</td>
          </tr>
         </c:if>
           <c:if test="${buyContractSelectDto.paymentType==1}">
          <tr>
            <td class="td_01" width="20%" height="18px">合同签订后</td>
            <td class="td_02" width="30%"> ${buyContractSelectDto.contractPaymentTime} 个工作日内</td>
            <td class="td_01" width="20%">预付金额</td>
            <td class="td_02" width="30%"><fmt:formatNumber value="${buyContractSelectDto.prepayMoney}" pattern="#,##0.00000"/>元</td>
          </tr>
          <tr>
            <td class="td_01" height="18px">验收合格后</td>
            <td colspan="3" class="td_02" >${buyContractSelectDto.sendPaymentTime}个工作日内</td>
          </tr>
          </c:if>
           <c:if test="${buyContractSelectDto.paymentType==2}">
          <tr>
            <td class="td_01" height="18px">验收合格后</td>
            <td class="td_02" >${buyContractSelectDto.sendPaymentTime}个工作日内</td>
            <td class="td_01">&nbsp;</td>
            <td class="td_02" >&nbsp;</td>
          </tr>
          </c:if>
      </table>
      <br />
      <div class="div_tiao"> &nbsp;&nbsp;发货信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" width="20%" height="18px">要求供货日期</td>
          <td class="td_02" width="30%">${buyContractSelectDto.requestDate}</td>
          <td class="td_01" width="20%">货运方式</td>
          <td class="td_02" width="30%">
            <c:if test="${buyContractSelectDto.transportWay==1}">
            	自提
            </c:if>
        	<c:if test="${buyContractSelectDto.transportWay==2}">
        		快递
        	</c:if>
        	<c:if test="${buyContractSelectDto.transportWay==3}">
        		汽运
        	</c:if>
        	<c:if test="${buyContractSelectDto.transportWay==4}">
        		铁运
        	</c:if>
        	<c:if test="${buyContractSelectDto.transportWay==5}">
        		航空
        	</c:if>
        	<c:if test="${buyContractSelectDto.transportWay==6}">
        		海运
        	</c:if>
        	<c:if test="${buyContractSelectDto.transportWay==7}">
        		中铁
        	</c:if>
        	<c:if test="${buyContractSelectDto.transportWay==8}">
        		市内送货
        	</c:if>
        	<c:if test="${buyContractSelectDto.transportWay==9}">
        		市内快递
        	</c:if>
          </td>
        </tr>
      </table>
      <br />
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table1" >
        <tr>
          <th nowrap="nowrap"  >名称</th>
          <th nowrap="nowrap">货物接收单位名称</th>
          <th nowrap="nowrap">收货地址</th>
          <th nowrap="nowrap">邮编</th>
          <th nowrap="nowrap">联系人</th>
          <th nowrap="nowrap">电话</th>
          <th nowrap="nowrap" width="80">手机</th>
        </tr>
         <logic:present name="buyContractReceivings">
        <logic:iterate id="buyContractReceiving" name="buyContractReceivings" indexId="buyContractReceivingindex">
        
        <tr>
          <td nowrap="nowrap" height="18px">${buyContractReceiving.receiveName}&nbsp;</td>
          <td nowrap="nowrap" >${buyContractReceiving.goodsName}&nbsp;</td>
          <td nowrap="nowrap" >${buyContractReceiving.address}&nbsp;</td>
          <td nowrap="nowrap" >${buyContractReceiving.postcode}&nbsp;</td>
          <td nowrap="nowrap" >${buyContractReceiving.linkman}&nbsp;</td>
          <td nowrap="nowrap" >${buyContractReceiving.tel}&nbsp;</td>
          <td nowrap="nowrap" >${buyContractReceiving.mobile}&nbsp;</td>
        </tr>
         </logic:iterate>
        </logic:present>
      </table>
      <div style="padding-top:3px"><a href="${ctx}/getReceiceInfoModify.do?buyContractId=${buyContractSelectDto.id}" id="receiveGoods"><img src="${ctx }/images/btnSHDZCK.gif"></img></a></div>
      <br/>
    <div class="div_tiao"> &nbsp;&nbsp;特殊说明</div>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_03" width="20%">特殊说明</td>
            <td class="td_04" style="padding:5px 100px 5px 5px;">
              ${tf:replaceBr(buyContractSelectDto.text)}&nbsp;
          </td>
        </tr>
    </table>
    <br />
						<div class="div_tiao">
							&nbsp;&nbsp;评审意见
						</div>
						
						<table border="0" cellpadding="0" cellspacing="0" align="center" width="460">
						<c:if test="${buyContractSelectDto.templateType==1}">
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
											<c:if test="${buyContractSelectDto.legalIdea== 1}">checked="checked"</c:if> />
										符合 &nbsp;&nbsp;
										<input type="checkbox" name="salesContract.legalIdea"
											id="checkbox4" disabled="disabled"
											<c:if test="${buyContractSelectDto.legalIdea == 0}">checked="checked"</c:if> />
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
													${tf:replaceBr(buyContractSelectDto.legalText)}
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr class="legalIdeaDIV">
									<td height="20px">
										签字：${buyContractSelectDto.legalName}
									</td>
									<td>
										日期：${buyContractSelectDto.legalDate}
									</td>
								</tr>
							<tr>
								<td height="20px" colspan="2">
									&nbsp;
								</td>
							</tr>
						</c:if>
								<tr class="proMajIdeaDIV">
									<td height="20px" colspan="2">
										采购主管评审意见：
									</td>
								</tr>
								<tr class="proMajIdeaDIV">
									<td width="320" nowrap="nowrap">
										1.产品要求是否符合（数量、规格、技术、质量）
									</td>
									<td height="20px" width="140" nowrap="nowrap">
										<input type="checkbox" name="salesContract.proMajIdea1"
											id="checkbox" disabled="disabled"
											<c:if test="${buyContractSelectDto.buyManProductIdea == 1}">checked="checked"</c:if> />
										符合 &nbsp;&nbsp;
										<input type="checkbox" name="salesContract.proMajIdea1"
											id="checkbox" disabled="disabled"
											<c:if test="${buyContractSelectDto.buyManProductIdea == 0}">checked="checked"</c:if> />
										不符合
									</td>
								</tr>
								<tr class="proMajIdeaDIV">
									<td>
										2.付款方式是否符合
									</td>
									<td height="20px">
										<input type="checkbox" name="salesContract.proMajIdea2"
											id="checkbox2" disabled="disabled"
											<c:if test="${buyContractSelectDto.buyManPaymentTypeIdea == 1}">checked="checked"</c:if> />
										符合 &nbsp;&nbsp;
										<input type="checkbox" name="salesContract.proMajIdea2"
											id="checkbox2" disabled="disabled"
											<c:if test="${buyContractSelectDto.buyManPaymentTypeIdea == 0}">checked="checked"</c:if> />
										不符合
									</td>
								</tr>
								<tr class="proMajIdeaDIV">
									<td>
										3.售前服务是否符合
									</td>
									<td height="20px">
										<input type="checkbox" name="salesContract.proMajIdea3"
											id="checkbox3" disabled="disabled"
											<c:if test="${buyContractSelectDto.buyManBeforeserviceIdea == 1}">checked="checked"</c:if> />
										符合 &nbsp;&nbsp;
										<input type="checkbox" name="salesContract.proMajIdea3"
											id="checkbox3" disabled="disabled"
											<c:if test="${buyContractSelectDto.buyManBeforeserviceIdea == 0}">checked="checked"</c:if> />
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
													${tf:replaceBr(buyContractSelectDto.buyManText)}
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr class="proMajIdeaDIV">
									<td height="20px">
										签字：${buyContractSelectDto.buyManName}
									</td>
									<td>
										日期：${buyContractSelectDto.buyManDate}
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
										<c:if test="${buyContractSelectDto.opeMajIdea == 1}">checked="checked"</c:if> />
									同意 &nbsp;&nbsp;
									<input type="checkbox" name="salesContract.opeMajIdea"
										id="checkbox4" disabled="disabled"
										<c:if test="${buyContractSelectDto.opeMajIdea == 0}">checked="checked"</c:if> />
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
												${tf:replaceBr(buyContractSelectDto.opeMajText)}
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td height="20px">
									签字：${buyContractSelectDto.opeMajName}
								</td>
								<td>
									日期：${buyContractSelectDto.opeMajDate}
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
					<c:choose>
						<c:when test="${param.decide}">
							 <a href="#" id="decide"><img src="${ctx}/images/btnSX.gif" width="65" height="20" /></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="#" id="cancel"><img src="${ctx}/images/btnQXHT.gif" width="88" height="20" /></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</c:when>
						<c:when test="${param.downLoad}">
							<c:if test="${buyContractSelectDto.templateType==0}">
								<a href="javascript:prevew()"><img src="${ctx}/images/btnYuLan.gif" width="65" height="20" class="preview" /></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="hidden" id="print" value="true"/>
								
							</c:if>
							<c:if test="${buyContractSelectDto.templateType==1}">
								<a href="${ctx }/downloadBuyContractFile.do?fileName=${buyContractSelectDto.fileName}"><img src="${ctx}/images/btnXiaZai.gif" width="65" height="20" class="preview" /></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;	
							</c:if>
						</c:when>
						<c:otherwise>
							<a href="javascript:prevew()"><img src="${ctx}/images/btnYuLan.gif" width="65" height="20" class="preview" /></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</c:otherwise>
					</c:choose>
					<a href="${ctx}/buyContractList.do?init=true"><img src="${ctx}/images/btnBack.gif" /></a>
						<html:hidden property="buyContractOfAddDto.id" value="${requestScope.id}"></html:hidden>
					</td>
					<td></td>
				</tr>
			</table>
			<br />
			</html:form>
</body>
</html>
