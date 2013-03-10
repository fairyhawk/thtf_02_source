<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="tf" uri="localhost" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<c:if test="${param.procurementOfficerError}">
	<script>alert("采购主管不存在！");</script>
</c:if>
<c:if test="${param.directorOfOperationsError}">
	<script>alert("运营总监不存在！");</script>
</c:if>
<c:if test="${param.astDirectorOfOperationsError}">
	<script>alert("运营总监助理不存在！");</script>
</c:if>
<c:if test="${param.buySpecialistError}">
	<script>alert("采购专员不存在！");</script>
</c:if>
<c:if test="${param.reviewError}">
	<script> alert("评审失败");</script>
</c:if>
<c:if test="${param.isTokenValid}">
	<script>alert("不能重复提交！");</script>
</c:if>
<c:if test="${!(buyContractSelectDto.status==2&&USERLOGIN.roleId==15)&&!(buyContractSelectDto.status==4&&USERLOGIN.roleId==11)&&!(buyContractSelectDto.status==8&&USERLOGIN.roleId==17)&&!(buyContractSelectDto.status==6&&USERLOGIN.roleId==16)}">
	<script>
	location="${ctx}/buyContractList.do";
	</script>
</c:if>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>采购合同评审</title>
	<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
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
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/util.js"></script>
        <script type="text/javascript">	
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
			});
		$(function() {
		$(":checkbox:enabled").each(function(i,item){
			$(item).bind("click", function() {
				if (item.value == "1" ) {				
					$(":checkbox[name='" + item.name + "'][value='0']").attr("checked", false);
				} else {					
					$(":checkbox[name='" + item.name + "'][value='1']").attr("checked", false);
				}
						
			});			
			
		});
		});
			function submitReview(isPass){
			var userRole= document.getElementById('userRole').value;//得到当前用户的角色
			if(isPass=='1'){
				var isPassitems = $(":checkbox[value='1']:checked:enabled").length;//得到选择符合的复选框个数
				var selectedItems = $(":checkbox:checked:enabled").length;//得到所有选中的复选框个数
				var isTrueSelItems=$(":checkbox[value='1']:enabled").length;//得到所有符合的复选框个数
				if(isPassitems==isTrueSelItems){//如果所有符合的复选框都已选中
					if(userRole=="11"){//采购主管
						document.getElementById("buyContractReviewDto.buyManIdea").value="111";
					}
					document.getElementById("buyContractReviewDto.status").value=1;
				}else{
					alert("评审项必须全部符合才能通过");
					return;
				}
			}else if(isPass=='0'){
				var isFPassitems = $(":checkbox[value='0']:checked:enabled").length;//得到不符合的个数
				var isTPassitems = $(":checkbox[value='1']:checked:enabled").length;//得到符合的个数
				var selectedItems = $(":checkbox[value='1']:enabled").length;//得到所有符合复选框的个数
				var text=$.trim($("#textArea").val());
				if(text==""){
					alert("请填写补充说明");
					$("#textArea").focus();
					return ;
				}
				if((isFPassitems+isTPassitems)==selectedItems){//如果所有的项都已选择
					 if(userRole=="11"){//采购主管
						var s="";
						$(":checkbox:checked:enabled").each(function(i,item){
							s+=item.value
						});	
					document.getElementById("buyContractReviewDto.buyManIdea").value=s;
					}
					document.getElementById("buyContractReviewDto.status").value=0;
				}else{
					alert("有未选择的项");
					return ;
				}
			}
			//用于判读补充说明是否输入过长
			if(checkTextAreaLen(200) == false){
				return ;
			}
			var forms=document.getElementById("byContractReviewForm");
			forms.submit();
		}
        </script>
<style type="text/css">
<!--
.STYLE1 {color: #FF0000}
-->
</style></head>

<body>
<input type="hidden" id="userRole" name="userRole" value="${USERLOGIN.roleId}"/>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp;当前位置： 采购管理 &gt;&gt; 采购合同管理 &gt;&gt; 采购合同评审</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td><br />
    <div class="div_tiao"> &nbsp;&nbsp;合同信息 </div>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
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
            <td class="td_01" width="20%">模版类型</td>
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
            <td class="td_01" width="20%" height="18px">签订地点</td>
            <td class="td_02" width="30%">${buyContractSelectDto.place}&nbsp;</td>
            <td class="td_01" width="20%">质量标准</td>
            <td class="td_02" width="30%">${buyContractSelectDto.quality}&nbsp;</td>
          </tr>
          <tr>
            <td class="td_01" height="18px">验收期限</td>
            <td class="td_02">${buyContractSelectDto.checkLimit}日</td>
            <td class="td_01">&nbsp;保质期</td>
            <td class="td_02">${buyContractSelectDto.protectLimit}&nbsp;</td>
          </tr>
           </c:if>
      </table>
      <br />
      <div class="div_tiao"> &nbsp;&nbsp;供货商信息 </div>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
          <tr>
            <td class="td_01" width="20%" height="18px">供货商名称</td>
            <td class="td_02" width="30%">${buyContractSelectDto.supplierName}&nbsp;</td>
            <td class="td_01" width="20%">联系人</td>
            <td class="td_02" width="30%">${buyContractSelectDto.linkmanName}&nbsp;</td>
          </tr>
          <tr>
            <td class="td_01" height="18px">省份</td>
            <td class="td_02">${buyContractSelectDto.province}&nbsp;</td>
            <td class="td_01">电话</td>
            <td class="td_02">${buyContractSelectDto.linkmanTel}&nbsp;</td>
          </tr>
          <tr>
            <td class="td_01" height="18px">城市</td>
            <td class="td_02">${buyContractSelectDto.city}&nbsp;</td>
            <td class="td_01">传真</td>
            <td class="td_02">${buyContractSelectDto.linkmanFax}&nbsp;</td>
          </tr>
           <tr>
            <td class="td_01" height="18px">开户行</td>
            <td class="td_02">${buyContractSelectDto.remitBankName}&nbsp;</td>
            <td class="td_01">帐号</td>
            <td class="td_02">${buyContractSelectDto.remitBankAccount}&nbsp;</td>
           </tr>
           <tr>
            <td class="td_01">发票类型</td>
            <td class="td_02">
           <c:if test="${buyContractSelectDto.invoiceType==0}">
            	普通
            </c:if>
            <c:if test="${buyContractSelectDto.invoiceType!=0}">
            	增值税
            </c:if>
            </td>
            <td class="td_01" height="18px">增值税税率</td>
            <td class="td_02"> ${buyContractSelectDto.taxRate}%</td>
          </tr>
      </table>
    <br />
    <div class="div_tiao"> &nbsp;&nbsp;产品信息 </div>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
            <tr>
                <td class="td_01" height="18px" width="20%">产品分类名称</td>
                <td class="td_02" width="30%">${buyContractSelectDto.productTypeName}&nbsp;</td>
                <td class="td_01" width="20%">产品部门名称</td>
                <td class="td_02" width="30%">${buyContractSelectDto.productDeptName}&nbsp;</td>
            </tr>
             <tr>
						<td class="td_01" width="20%"><span style="color:#FF0000">*</span>&nbsp;发货方式</td>
						<td colspan="3" class="td_02">
						<c:if test="${buyContractSelectDto.deliveryTerms==0 }">允许分批交货</c:if>&nbsp;
					</tr>
   	  </table>
   	  <br />
      <div style="width:100%; text-align:right">单位：元&nbsp;&nbsp;&nbsp;</div>
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
          <th nowrap="nowrap">库存总数</th>
          <th nowrap="nowrap">需求数</th>
          <th nowrap="nowrap">库存单价</th>
          <th nowrap="nowrap">增长率(%)</th>
        </tr>
        <logic:present name="buyContractProducts">
        <bean:define id="total" value="0" type="java.lang.String"/>
        <bean:define id="increaserate" value="0.0" type="java.lang.String"/>
        <logic:iterate id="buyContractProduct" name="buyContractProducts" indexId="buyContractProductindex">
        <bean:define id="total" value="${buyContractProduct.buyPrice*buyContractProduct.buyCount+total}" type="java.lang.String"/>
        <c:choose>
				<c:when test="${buyContractProduct.stockUnitPrice*1==0}">
					<c:if test="${'100'>increaserate}">
			       		<bean:define id="increaserate" value="100" type="java.lang.String"/>
			       	</c:if>
				</c:when>
				<c:when test="${buyContractSelectDto.invoiceType==0}">
					<c:if test="${(buyContractProduct.buyPrice-buyContractProduct.noTaxOfPrice)*100/buyContractProduct.noTaxOfPrice>increaserate}">
			       		<bean:define id="increaserate" value="${(buyContractProduct.buyPrice-buyContractProduct.noTaxOfPrice)*100/buyContractProduct.noTaxOfPrice}" type="java.lang.String"/>
			       	</c:if>
				</c:when>
				<c:otherwise>
					<c:if test="${(buyContractProduct.buyPrice/(1+buyContractSelectDto.taxRate/100)-buyContractProduct.noTaxOfPrice)*100/buyContractProduct.noTaxOfPrice>increaserate}">
			       		<bean:define id="increaserate" value="${(buyContractProduct.buyPrice/(1+buyContractSelectDto.taxRate/100)-buyContractProduct.noTaxOfPrice)*100/buyContractProduct.noTaxOfPrice}" type="java.lang.String"/>
			       	</c:if>
				</c:otherwise>
			</c:choose>

        <tr>
          <td nowrap="nowrap" style="border-left:1px solid #c2c2c2;text-align:right;" height="18px">${buyContractProductindex+1}&nbsp;</td>
          <td nowrap="nowrap" >${buyContractProduct.prodCode}&nbsp;</td>
          <td nowrap="nowrap" >${buyContractProduct.prodName}&nbsp;</td>
          <td nowrap="nowrap" >${buyContractProduct.prodType}&nbsp;</td>
          <td nowrap="nowrap" >${buyContractProduct.prodUnit}&nbsp;</td>
          <td nowrap="nowrap" style=" text-align:right; ">${buyContractProduct.buyCount}&nbsp;</td>
          <td nowrap="nowrap" style=" text-align:right; ">
          	<fmt:formatNumber value="${buyContractProduct.buyPrice}" pattern="#,##0.00000"/>&nbsp;
          </td>
          <td nowrap="nowrap" style=" text-align:right; ">
          	<fmt:formatNumber value="${buyContractProduct.buyMoney}" pattern="#,##0.00000"/>&nbsp;
          </td>
          <td nowrap="nowrap" style=" text-align:right; ">${buyContractProduct.stockTotalNum}&nbsp;</td>
          <td nowrap="nowrap" style=" text-align:right; ">${buyContractProduct.demandCount}&nbsp;</td>
          <td nowrap="nowrap" style=" text-align:right; ">
          		<fmt:formatNumber value="${buyContractProduct.stockUnitPrice}" pattern="#,##0.00000"/>
          </td>
          <td nowrap="nowrap" style=" text-align:right; ">
          <c:choose>
				<c:when test="${buyContractProduct.stockUnitPrice*1==0}">
					100
				</c:when>
				<c:when test="${buyContractSelectDto.invoiceType==0}"><fmt:formatNumber value="${(buyContractProduct.buyPrice-buyContractProduct.noTaxOfPrice)*100/buyContractProduct.noTaxOfPrice}" type="number" pattern="0.00"/></c:when>
				<c:otherwise>
					<fmt:formatNumber value="${(buyContractProduct.buyPrice/(1+buyContractSelectDto.taxRate/100)-buyContractProduct.noTaxOfPrice)*100/buyContractProduct.noTaxOfPrice}" type="number" pattern="0.00"/>
				</c:otherwise>
			</c:choose>&nbsp;  
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
      <br />
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
            <td class="td_02" width="30%">${buyContractSelectDto.contractPaymentTime} 个工作日内</td>
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
            <td class="td_02" colspan="3">${buyContractSelectDto.sendPaymentTime}个工作日内</td>
          </tr>
           </c:if>
      </table>
<br/>
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
          <th nowrap="nowrap" >名称</th>
          <th nowrap="nowrap">货物接收单位名称</th>
          <th nowrap="nowrap">收获地址</th>
          <th nowrap="nowrap">邮编</th>
          <th nowrap="nowrap">联系人</th>
          <th nowrap="nowrap">电话</th>
          <th nowrap="nowrap" width="80" >手机</th>
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
      <div style="padding-top:3px">
      	<a href="${ctx}/getReceiceInfoModify.do?buyContractId=${buyContractSelectDto.id}&typeOflook=syndic" id="receiveGoods"><img src="${ctx }/images/btnSHDZCK.gif"/></a>
      </div>
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
						<html:form  action="getBuyContractsReview" styleId="byContractReviewForm" method="post">
						<table border="0" cellpadding="0" cellspacing="0" align="center" width="460">
						<c:if test="${buyContractSelectDto.templateType==1}">
							<tr class="legalIdeaDIV">
									<td height="20px" colspan="2">
										法务专员评审意见：
									</td>
								</tr>
								<!--如果当前用户为法务专员-->
							<c:if test="${USERLOGIN.roleId==15}">
							<tr class="legalIdeaDIV">
									<td>
										法律法规是否符合
									</td>
									<td height="20px">
										<input type="checkbox" name="buyContractReviewDto.legalIdea"
											id="checkbox4" value="1" 
											<c:if test="${buyContractSelectDto.legalIdea== 1}">checked="checked"</c:if> />
										符合 &nbsp;&nbsp;
										<input type="checkbox" name="buyContractReviewDto.legalIdea"
											id="checkbox4" value="0" 
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

												 <textarea name="buyContractReviewDto.legalText"  id="textArea"   cols="60" rows="3" >${buyContractSelectDto.legalText}</textarea>

												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr class="legalIdeaDIV">
									<td height="20px">
										签字：${USERLOGIN.name}
										<input type="hidden" name="buyContractReviewDto.legalName" value="${buyContractSelectDto.legalName}"/>
									</td>
									<td>
										日期：${buyContractSelectDto.dateTime}
										<input type="hidden" name="buyContractReviewDto.legalDate" value="${buyContractSelectDto.dateTime}"/>
									</td>
								</tr>
							</c:if>
								<c:if test="${USERLOGIN.roleId!=15}">
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
								</c:if>
								
							<tr>
								<td height="20px" colspan="2">&nbsp;
									
								</td>
							</tr>
							
						</c:if>
								<tr class="proMajIdeaDIV">
									<td height="20px" colspan="2">
										采购主管评审意见：
									</td>
								</tr>
									<logic:equal name="USERLOGIN" property="roleId" value="11" scope="session">
									<tr class="proMajIdeaDIV">
									<td width="320" nowrap="nowrap">
										1.产品要求是否符合（数量、规格、技术、质量）
									</td>
									<td height="20px" width="140" nowrap="nowrap">
										<input type="checkbox" name="buyManidea1"
											id="checkbox" value="1" 
											<c:if test="${buyContractSelectDto.buyManProductIdea == 1}">checked="checked"</c:if> />
										符合 &nbsp;&nbsp;
										<input type="checkbox" name="buyManidea1"
											id="checkbox" value="0" 
											<c:if test="${buyContractSelectDto.buyManProductIdea == 0}">checked="checked"</c:if> />
										不符合
									</td>
								</tr>
								<tr class="proMajIdeaDIV">
									<td>
										2.付款方式是否符合
									</td>
									<td height="20px">
										<input type="checkbox" name="buyManidea2"
											id="checkbox2" value="1"  
											<c:if test="${buyContractSelectDto.buyManPaymentTypeIdea == 1}">checked="checked"</c:if> />
										符合 &nbsp;&nbsp;
										<input type="checkbox" name="buyManidea2"
											id="checkbox2" value="0" 
											<c:if test="${buyContractSelectDto.buyManPaymentTypeIdea == 0}">checked="checked"</c:if> />
										不符合
									</td>
								</tr>
								<tr class="proMajIdeaDIV">
									<td>
										3.售前服务是否符合
									</td>
									<td height="20px">
										<input type="checkbox" name="buyManidea3"
											id="checkbox3" value="1" 
											<c:if test="${buyContractSelectDto.buyManBeforeserviceIdea == 1}">checked="checked"</c:if> />
										符合 &nbsp;&nbsp;
										<input type="checkbox" name="buyManidea3"
											id="checkbox3" value="0" 
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

												<textarea name="buyContractReviewDto.buyManText"   id="textArea" cols="60" rows="3" >${buyContractSelectDto.buyManText}</textarea>
													
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr class="proMajIdeaDIV">
									<td height="20px">
										签字：${USERLOGIN.name}
										<input type="hidden" name="buyContractReviewDto.buyManName" value="${USERLOGIN.name}"/>
									</td>
									<td>
										日期：${buyContractSelectDto.dateTime}
										<input type="hidden" name="buyContractReviewDto.buyManDate" value="${buyContractSelectDto.dateTime}"/>
									</td>
								</tr>
									</logic:equal>
									<logic:notEqual name="USERLOGIN" property="roleId" value="11" scope="session">
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
								</logic:notEqual>
								
							<tr>
								<td height="20px" colspan="2">&nbsp;
									
								</td>
							</tr>
							<tr>
								<td height="20px" colspan="2">
									运营总监评审意见：
								</td>
							</tr>
							<c:if test="${USERLOGIN.roleId==17||USERLOGIN.roleId==16}">
							<tr>
								<td>&nbsp;
									
								</td>
								<td height="20px">
									<input type="checkbox" name="buyContractReviewDto.opeMajIdea"
										id="checkbox4" value="1" 
										<c:if test="${buyContractSelectDto.opeMajIdea == 1}">checked="checked"</c:if> />
									同意 &nbsp;&nbsp;
									<input type="checkbox" name="buyContractReviewDto.opeMajIdea"
										id="checkbox4" value="0" 
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
												<textarea name="buyContractReviewDto.opeMajText"   id="textArea" cols="60" rows="3" >${buyContractSelectDto.opeMajText}</textarea>

											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td height="20px">
								签字：${USERLOGIN.name}
									<input type="hidden" name="buyContractReviewDto.opeMajName" value="${USERLOGIN.name}"/>
								</td>
								<td>
									日期：${buyContractSelectDto.dateTime}
									<input type="hidden" name="buyContractReviewDto.opeMajDate" value="${buyContractSelectDto.dateTime}"/>
								</td>
							</tr>
							</c:if>
							<c:if test="${USERLOGIN.roleId!=17&&USERLOGIN.roleId!=16}">
							<tr>
								<td>&nbsp;
									
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
						</c:if>
							
						</table>
					</td>
					<td>&nbsp;
						
					</td>
				</tr>
				<tr>
					<td></td>
					<td height="50px" align="center" valign="bottom">
						<a href="javascript:submitReview('1')"><img src="${ctx}/images/btnTG.gif" width="65" height="20" /></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    					<a href="javascript:submitReview('0')"><img src="${ctx}/images/btnWTG.gif" width="65" height="20"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="${ctx }/buyContractList.do?init=true"><img src="${ctx}/images/btnBack.gif" /></a>
					</td>
					<td></td>
				</tr>
			</table>
			<input type="hidden" name="buyContractReviewDto.status" id="buyContractReviewDto.status"/>
			<input type="hidden" name="buyContractReviewDto.buyManIdea" id="buyContractReviewDto.buyManIdea" />
			<input type="hidden" name="buyContractReviewDto.id" value="${buyContractSelectDto.id}"/>
			<input type="hidden" name="buyContractReviewDto.legalId" value="${buyContractSelectDto.legalId}"/>
			<input type="hidden" name="buyContractReviewDto.buyManId" value="${buyContractSelectDto.buyManId}"/>
			<input type="hidden" name="buyContractReviewDto.opeMajId" value="${buyContractSelectDto.opeMajId}"/>
			<input type="hidden" name="buyContractReviewDto.prodTotalMoney" value="${total}"/>
			<input type="hidden" name="buyContractReviewDto.increaserate" value="${increaserate}"/>
			<input type="hidden" name="buyContractReviewDto.prodTypeId" value="${buyContractSelectDto.productTypeId}"/>
			</html:form>
		</div>
    </td>
    <td >&nbsp;</td>
  </tr>
  <tr>
    <td></td>
    <td></td>
  </tr>
</table>
</body>
</html>
