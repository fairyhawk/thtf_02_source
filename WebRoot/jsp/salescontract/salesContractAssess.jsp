<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"	contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@taglib prefix="tf" uri="localhost" %>
<c:if test="${param.isTokenValid}">
	<script>alert("不能重复提交！");
		location="${ctx}/getSalesContractsList.do?init=true";
	</script>
</c:if>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>销售合同评审</title>
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
<script type="text/javascript" >
	$(function() {
		
		//隔行换色
		if($("#detailTable")){
			$("#detailTable tr:even").addClass("treven");
			$("#detailTable tr").each(function(i){
				$(this).mouseover(function(){
					$(this).addClass("over");
				});
				$(this).mouseout(function(){
					$(this).removeClass("over");
				});
			});
		}
		
		$("#salesContractAssessPreview").hide();
		
		var assessTag = $("#assessTag").val();
		
		$(":checkbox").attr("disabled", "disabled");
		
		//产品总监
		if (assessTag == "productDirector" ) {
			//评审项数
			$("#assessItems").attr("value", 3);
			$("tr[name='trProMaj'] :checkbox").attr("disabled", false);
			$("td[name='tdProMaj']").empty();
			$("td[name='tdProMaj']").append("<textArea id='textContent' name='proMajText' cols='60' rows='4'></textArea>");	
		}
		//法务
		if (assessTag == "complianceOfficer" ) {
			$("#assessItems").attr("value", 1);
			$("tr[name='trLegal'] :checkbox").attr("disabled", false);
			$("td[name='tdLegal']").empty();
			$("td[name='tdLegal']").append("<textArea id='textContent' name='legalText' cols='60' rows='4'></textArea>");	
		}
		//区域总监
		if(assessTag=="areaDirector"){
			$("#assessItems").attr("value",4);
			$("tr[name='trAreaMaj'] :checkbox").attr("disabled",false);
			$("td[name='tdAreaMaj']").empty();
			$("td[name='tdAreaMaj']").append("<textArea id='textContent' name='areaMajText' cols='60' rows='4'></textArea>");
		}
		//销售总监
		if (assessTag == "salesDirector" ) {
			$("#assessItems").attr("value", 4);
			$("tr[name='trSellMaj'] :checkbox").attr("disabled", false);
			$("td[name='tdSellMaj']").empty();
			$("td[name='tdSellMaj']").append("<textArea id='textContent' name='sellMajText' cols='60' rows='4'></textArea>");	
		}
		//运营总监
		if (assessTag == "operationsDirector" ) {
			$("#assessItems").attr("value", 1);		
			$("tr[name='trOpeMaj'] :checkbox").attr("disabled", false);
			$("td[name='tdOpeMaj']").empty();
			$("td[name='tdOpeMaj']").append("<textArea id='textContent' name='opeMajText' cols='60' rows='4'></textArea>");			
			
		}
		
		$(":checkbox:enabled").each(function(i,assessItem){
			
			$(assessItem).bind("click", function() {
				
					if (assessItem.value == "1" ) {						
						$(":checkbox[name='" + assessItem.name + "'][value='0']").attr("checked", false);
					} else {						
						$(":checkbox[name='" + assessItem.name + "'][value='1']").attr("checked", false);
					}
						
			});//bind			
			
		});//each
		
	
		$("#previewBtn").bind("click",function(){
		
			window.open('${ctx}/salesContractPreview.do?salesContractId=${param.contractId}&command=preview','newwindow', 'toolbar=no,scrollbars=yes,resizable=no,top=200,left=370, width=700,height=400');
		});	
		
		//服务器端消息
		var msg = "${msg}";
		if (msg != "") {
			alert(msg);
		}
						
	 }//function
	);
	
	//提交前的检查
	function submitCheck(tag) {

		//要评审的项数
		var assessItems = $("#assessItems").val();
		//选中的项数
		var selectedItems = $(":checkbox:checked:enabled").length;
		
		if (assessItems > selectedItems) {
			alert("评审未完成 ");
			return false;
		}

		if(checkTextAreaLen(200) == false){
			return ;
		}

		//通过
		if (tag == 1) {
			var assessTag = $("#assessTag").val();
		//通过的项数
		var passedItems = $(":checkbox[value='1']:checked:enabled").length;
		
		if ( passedItems == assessItems ) {		
						
		} 
		else {
			alert("评审项必须全部符合才能通过");
			return false;
		}//else	
			$("#assessResult").attr("value", "pass");
			//产品总监评审
			if(assessTag =="productDirector") {
				
				var checkResult = true;
				
				$("#detailTable :input[type='text'][disabled=false]").each(function(){
					
					if (checkResult == true) {
						
						var buyPrice = this.value;	
						
						if (buyPrice == 0) {
							alert("预计采购价不能为零");
							checkResult = false;
							return;
						}			
					}
				});	
				if (checkResult == false) {
					return;
				}	
			}
		}else{
			var txt = $.trim($("#textContent").val());
			if(txt =="" || txt == null){
				alert("请填写补充说明");
				$("#textContent").focus();
				return false;
			}
		}	
		$("#assessForm").submit();
	}

	var flg = true;
	//计算销售合同的利率
	function calcuteContractInterestRate(detail){
		var buyPrice = detail.value;
		if (buyPrice == "") {
			alert("预计采购价不能为空");
			detail.focus();
			return;
		}

		if(/^[1-9](\d{1,7})?(\.\d{1,2})?$/.test(buyPrice) || /^0{1}\.\d{1,2}$/.test(buyPrice) ){
			if(flg){
			    calculateContractInterestRate();
			}
			flg = true;
		}else{
			alert("预计采购价必须是数值型且保留两位小数");
			flg = false;
			detail.focus();
			return;					
		}
	};
	
	//计算合同的总利率
	function calculateContractInterestRate() {
	 	
		var totalPrice = 0;
		var totalBuyPrice = 0;
		var detailInterestRate=0;
		var interestRate=0;
		var totalBuyPriceForSum =0;
		$("input[name=detailId]").each(function(){
			
			var price = $("#detailPrice"+this.value).attr("value");
			var buyPrice = $("#detailBuyPrice"+this.value).attr("value");
			var count = $("#detailCount"+this.value).attr("value");
			
			totalPrice = totalPrice + (price * count);
			totalBuyPrice = totalBuyPrice + (buyPrice * count);
			totalBuyPriceForSum = totalBuyPriceForSum +(buyPrice * count);
			if(parseFloat(price)==0){
				detailInterestRate=0;
			}else{
				detailInterestRate = ((price-buyPrice)/price)*100.00;
			}
			detailInterestRate = detailInterestRate.toFixed(2);
			$("#tdDetailInterestRate"+this.value).attr("innerHTML", detailInterestRate);
						
		});
		if(parseFloat(totalPrice)==0){
			interestRate=0.00;
		}else{
			interestRate = ((totalPrice-totalBuyPrice)/totalPrice)*100.00;
		}
		interestRate = interestRate.toFixed(2);
		$("#interestRate").attr("value", interestRate);	
		$("#tdInterestRate").attr("innerHTML", interestRate);
		totalBuyPriceForSum=totalBuyPriceForSum.toFixed(2);
		$("#tdBuyPriceSum").attr("innerHTML", totalBuyPriceForSum);
	}
	
	//销售合同预览
	function contractPreview(type) {
		if(type==1) {
			$("#salesContractAssess").hide();
			$("#salesContractAssessPreview").show();
		} else {
			$("#salesContractAssess").show();
			$("#salesContractAssessPreview").hide();
		}
	}


	
</script>
</head>
<body>
<div id="salesContractAssess">
<input type="hidden" id="assessTag" value="${assessTag}"/>
<input type="hidden" id="assessItems" value=""/>


<html:form  action="assessSalesContract" styleId="assessForm" method="post">
<input type="hidden" id="assessResult" name="assessResult" value=""/>
<input type="hidden" name="id" value="${contract.id}"/>

<input type="hidden" id="productTypeId" name="productTypeId" value="${contract.productTypeId}"/>

<c:if test="${assessTag == 'productDirector'}">
	<input type="hidden" name="interestRate" id="interestRate" value="${contract.interestRate}"/>
</c:if>
	
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp;当前位置： 销售合同评审管理 &gt;&gt; 销售合同评审</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td><br />
    <div class="div_tiao"> &nbsp;&nbsp;合同信息 </div>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
          <tr>
            <td class="td_01" width="20%" height="18px">部门名称</td>
            <td class="td_02" width="30%">${contract.productDeptName}&nbsp;</td>
            <td class="td_01 width="20%">客户名称</td>
            <td class="td_02" width="30%">${contract.customerName}&nbsp;</td>
          </tr>
          <tr>
            <td class="td_01" >盖章类型</td>
            <td class="td_02">
 				<c:if test="${contract.stampType == 0}">业务章</c:if>															
				<c:if test="${contract.stampType == 1}">合同章</c:if>									
								         
            &nbsp;</td>
            <td class="td_01">模版类型</td>
            <td class="td_02">
            		<c:if test="${contract.templateType == 0 }">
						标准模版				
					</c:if>	
					<c:if test="${contract.templateType == 1 }">
						自定义模版				
					</c:if>	
					<c:if test="${contract.templateType == 2 }">
						非模版				
					</c:if>	
            		&nbsp;
            </td>
          </tr>
          <tr>
            <td class="td_01" height="18px">产品分类名称</td>
            <td class="td_02">${contract.productTypeName}&nbsp;</td>
            <td class="td_01">产品序列号</td>
            <td class="td_02">${contract.productDeptNo}${contract.productTypeNo}&nbsp;</td>
          </tr>
           <tr>
             <td class="td_01" height="18px">发票类型</td>
             <td class="td_02">
             					<c:if test="${contract.customerInvoiceType == 0}">
									普通
								</c:if>
								<c:if test="${contract.customerInvoiceType == 1}">
									增值税
								</c:if>
             &nbsp;</td>
             <td class="td_01">要求发货日期</td>
             <td class="td_02">${contract.requestSendDate}&nbsp;</td>
           </tr>
           <tr>
             <td class="td_01" height="18px">人员名称</td>
             <td class="td_02">${contract.userName}&nbsp;</td>
             <td class="td_01">人员区域</td>
             <td class="td_02">${contract.userAreaName}&nbsp;</td>
           </tr>
           <tr>
             <td class="td_01" height="18px">付款方式</td>
             <td class="td_02">		
             				    <logic:equal value="0" property="paymentWay"
													name="contract">
            												全部现结
            											</logic:equal>
												<logic:equal value="1" property="paymentWay"
													name="contract">
            												全部信用-${contract.creditTypeName}（<c:if test="${tf:isEmptyOrNull(contract.creditProjectName)==false}">${contract.creditProjectName}，</c:if>账期${contract.arterm}天，额度<fmt:formatNumber value="${contract.climit}" pattern="#,##0.00"/>元）
            											</logic:equal>
												<logic:equal value="2" property="paymentWay"
													name="contract">
            												部分信用部分现结-${contract.creditTypeName}（账期${contract.arterm}天，额度<fmt:formatNumber value="${contract.climit}" pattern="#,##0.00"/>元）&nbsp;
            									</logic:equal>
 
</td>
								<td class="td_01" >&nbsp;项目名称</td>
            					<td class="td_02">${contract.contractProName}&nbsp;</td>
           </tr>
           <logic:notEqual value="1" property="paymentWay"
													name="contract">
										<tr>
											<td class="td_01" height="18px">
												现结金额
											</td>
											<td class="td_02" colspan="3">
												<fmt:formatNumber value="${contract.cashMoney}" pattern="#,##0.00"/>元
											</td>
										</tr>
										</logic:notEqual>
										<logic:notEqual value="0" property="paymentWay"
													name="contract">
										<tr>
											<td class="td_01" height="18px">
												分批方式
											</td>
											<td class="td_02" >
												<logic:equal value="0" property="batchWay"
													name="contract">
            													不分批
            											</logic:equal>
												<logic:equal value="1" property="batchWay"
													name="contract">
            													分批
            											</logic:equal>
											</td>
											<td class="td_01" height="18px">
												分批提货最大金额（元）
											</td>
											<td class="td_02">
												<c:if test="${contract.batchMaxMoney==null}">&nbsp;&nbsp;&nbsp;/</c:if>
												<fmt:formatNumber value="${contract.batchMaxMoney}" pattern="#,##0.00"/>
												
											</td>
										</tr>
										</logic:notEqual>
           
           <tr>
            <td class="td_01" height="18px">特殊说明</td>
            <td colspan="3" class="td_02">${tf:replaceBr(contract.text)}&nbsp;</td>
        </tr>
      </table>
      <br />
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="detailTable" style="border-left:1px solid #FFFFFF;">
        <tr>
          <th nowrap="nowrap" width="40" style="border-left:1px solid #c2c2c2;">序号</th>
          <th nowrap="nowrap">产品名称</th>
          <th nowrap="nowrap">规格型号</th>
          <th nowrap="nowrap">单位</th>
          <th nowrap="nowrap">销售数</th>
          <th nowrap="nowrap">销售单价</th>
          <th nowrap="nowrap">销售金额</th>
          <th nowrap="nowrap">预计采购单价</th>
          <th nowrap="nowrap">销售毛利率(%)</th>
          <th nowrap="nowrap">销售可用数</th>
          <th nowrap="nowrap">限价</th>
        </tr>
     <c:forEach var="detail" varStatus="detailAmount" items="${contractProductDetailList}">
        <tr>
          <td nowrap="nowrap" style="border-left:1px solid #c2c2c2;" height="18px">${detailAmount.count}&nbsp;</td>
          <td nowrap="nowrap" >${detail.productName}&nbsp;</td>
          <td nowrap="nowrap" >${detail.productType}&nbsp;</td>
          <td nowrap="nowrap" >${detail.productUnit}&nbsp;</td>
          <td nowrap="nowrap" style=" text-align:right; padding-right:5px;">${detail.detailCount}&nbsp;</td>
          <td nowrap="nowrap" width="84px" style=" text-align:right; padding-right:5px;">
          	<fmt:formatNumber value="${detail.detailPrice}" pattern="#,##0.00"/>
          </td>
          <td nowrap="nowrap" width="84px" style=" text-align:right; padding-right:5px;">
          	<fmt:formatNumber value="${detail.detailCount * detail.detailPrice}" pattern="#,##0.00"/>
          </td>
          <td nowrap="nowrap" width="84px" style=" text-align:right; padding-right:5px;">
          	
          	<c:if test="${assessTag == 'productDirector'}">
          			<c:if test="${detail.detailCount > detail.productUsableCount}">
          				<input type="hidden" name="proDetailId"  value="${detail.detailId}"/>
          			</c:if>
          		<!--隐藏域 销售数，销售价格，预计采购价-->	
          		<input  type="hidden" name="detailId"  value="${detail.detailId}"/>	
          		<input  type="hidden" name="detailCount${detail.detailId}"        id="detailCount${detail.detailId}"  value="${detail.detailCount}"/>
          		<input  type="hidden" name="detailPrice${detail.detailId}"        id="detailPrice${detail.detailId}"  value="${detail.detailPrice}"/>
          		<input  type="hidden" name="productUsableCount${detail.detailId}" id="productUsableCount${detail.detailId}"  value="${detail.productUsableCount}"/>
          		<input  type="text"   name="detailBuyPrice${detail.detailId}"     id="detailBuyPrice${detail.detailId}" style="width:86px; text-align:right" 
          			value="${detail.detailBuyPrice}" 
          				<c:if test="${detail.detailCount <= detail.productUsableCount}">
          					disabled="disabled"
          				</c:if>
          			onblur="calcuteContractInterestRate(this);"
          			/>
          	</c:if>
          	
          	<c:if test="${ assessTag != 'productDirector'}">
          		<fmt:formatNumber value="${detail.detailBuyPrice}" pattern="#,##0.00"/>
          	</c:if>
          			
          </td>
          <td id="tdDetailInterestRate${detail.detailId}" nowrap="nowrap" width="84px" style=" text-align:right; padding-right:5px;">
          	<c:set var="marginRate">
          		<c:if test="${detail.detailPrice <= 0}">
          			0
          		</c:if>
          		<c:if test="${detail.detailPrice > 0}">
          			${((detail.detailPrice*100 - detail.detailBuyPrice*100) /  detail.detailPrice*100)/100.00}
          		</c:if>
          	</c:set>
		
          	<fmt:formatNumber value="${marginRate}" pattern="#,##0.00"/>
          </td>
          <td nowrap="nowrap" width="84px" style=" text-align:right; padding-right:5px;">
          	${detail.productUsableCount}</td>
          <td nowrap="nowrap" style="text-align:right; padding-right:5px;">
				<fmt:formatNumber value="${detail.limitPrice}" pattern="#,##0.00"/>&nbsp;
		  </td>
        </tr>
     </c:forEach>   
        
        <tr>
          <td colspan="6" nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF"> 销售金额合计 </td>
          
          <td nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF">
          	<fmt:formatNumber value="${contract.money}" pattern="#,##0.00"/>       
          </td>
          
          <td nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF"><div style="float:left">元</div>总利率</td>
          
          <td id="tdInterestRate" nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF">
          	<fmt:formatNumber value="${contract.interestRate}" pattern="#,##0.00"/>         
          </td>
          
          <td nowrap="nowrap" style=" border:1px solid #FFFFFF;background-color:#FFFFFF">%&nbsp;&nbsp;采购金额合计</td>
           <td nowrap="nowrap" id="tdBuyPriceSum" style=" border:1px solid #FFFFFF;text-align:right;padding-right:10px;background-color:#FFFFFF " ><fmt:formatNumber value="${toatlBuyPriceSum}" pattern="0.00"/></td>
        </tr>
      </table>
      <br />
      <div class="div_tiao"> &nbsp;&nbsp;评审意见</div>
            			<table border="0" cellpadding="0" cellspacing="0" align="center"
						width="460">
				<c:if test="${not empty contract.proMajId}">		
						
						<tr>
							<td height="20px" colspan="2">
								产品总监评审意见：
							</td>
						</tr>
						<tr name="trProMaj">
							<td width="320" nowrap="nowrap">
								1.产品要求是否符合（数量、规格、技术、质量）
							</td>
							<td height="20px" width="140" nowrap="nowrap">
								<input type="checkbox"  name="proMajIdea1" id="radio" value="1"
									<c:if test='${contract.proMajIdea1 == "1"}'>checked="checked"</c:if> 
									/>
								符合 &nbsp;&nbsp;
								<input type="checkbox"  name="proMajIdea1" id="radio" value="0"
									<c:if test='${contract.proMajIdea1 == "0"}'>checked="checked"</c:if> 
									/>
								不符合
							</td>
						</tr>
						<tr	name="trProMaj">
							<td>
								2.交付日期是否符合
							</td>
							<td height="20px">
								<input type="checkbox"  name="proMajIdea2" id="radio2" value="1"
									<c:if test='${contract.proMajIdea2 == "1"}'>checked="checked"</c:if> />
								符合 &nbsp;&nbsp;
								<input type="checkbox"  name="proMajIdea2" id="radio2" value="0"
									<c:if test='${contract.proMajIdea2 == "0"}'>checked="checked"</c:if> />
								不符合
							</td>
						</tr>
						<tr name="trProMaj">
							<td>
								3.包装要求是否符合
							</td>
							<td height="20px">
								<input type="checkbox"  name="proMajIdea3" id="radio3" value="1"
									<c:if test='${contract.proMajIdea3 == "1"}'>checked="checked"</c:if> />
								符合 &nbsp;&nbsp;
								<input type="checkbox"  name="proMajIdea3" id="radio3" value="0"
									<c:if test='${contract.proMajIdea3 == "0"}'>checked="checked"</c:if> />
								不符合
							</td>
						</tr>
						<tr name="trProMaj">
							<td colspan="2" valign="top">
								<table width="98%" border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap>
											补充说明：
										</td>
										<td style="padding:5px 0 5px 0;" name="tdProMaj" width="330px">
											${tf:replaceBr(contract.proMajText)}
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td height="20px">
								签字：&nbsp; ${contract.proMajName}
							</td>
							<td>
								日期：&nbsp; ${contract.proMajDate}
							</td>
						</tr>
						<tr>
							<td height="20px" colspan="2">&nbsp;
								
							</td>
						</tr>
				</c:if>
				
				<c:if test="${not empty contract.legalId}">		
						<tr	>
							<td height="20px" colspan="2">
								法务专员评审意见：
							</td>
						</tr>
						<tr name="trLegal"	>
							<td>
								法律法规是否符合
							</td>
							<td height="20px">
								<input type="checkbox"  name="legalIdea" id="radio4" value="1"
									<c:if test='${contract.legalIdea == "1"}'>checked="checked"</c:if> />
								符合 &nbsp;&nbsp;
								<input type="checkbox"  name="legalIdea" id="radio4" value="0"
									<c:if test='${contract.legalIdea == "0"}'>checked="checked"</c:if> />
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
										<td style="padding:5px 0 5px 0;" name="tdLegal" width="330px">
											${tf:replaceBr(contract.legalText)}
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td height="20px">
								签字：&nbsp; ${contract.legalName}
							</td>
							<td>
								日期：&nbsp; ${contract.legalDate}
							</td>
						</tr>
						<tr>
							<td height="20px" colspan="2">&nbsp;
								
							</td>
						</tr>
				</c:if>	
				<tr name="trAreaMaj">
						<td height="20px" colspan="2">
								区域总监评审意见：
						</td>
					</tr>
						<tr name="trAreaMaj">
							<td>
								1.毛利率是否符合
							</td>
							<td height="20px" width="150">
								<input type="checkbox"  name="areaMajIdea1" id="radio" value="1"
									<c:if test='${contract.areaMajIdea1 == "1"}'>checked="checked"</c:if> />
								符合 &nbsp;&nbsp;
								<input type="checkbox"  name="areaMajIdea1" id="radio" value="0"
									<c:if test='${contract.areaMajIdea1 == "0"}'>checked="checked"</c:if> />
								不符合
							</td>
						</tr>
						<tr name="trAreaMaj">
							<td>
								2.付款方式是否符合
							</td>
							<td height="20px">
								<input type="checkbox"  name="areaMajIdea2" id="radio2" value="1"
									<c:if test='${contract.areaMajIdea2 == "1"}'>checked="checked"</c:if> />
								符合 &nbsp;&nbsp;
								<input type="checkbox"  name="areaMajIdea2" id="radio2" value="0"
									<c:if test='${contract.areaMajIdea2 == "0"}'>checked="checked"</c:if> />
								不符合
							</td>
						</tr>
						<tr name="trAreaMaj">
							<td>
								3.运输方式是否符合
							</td>
							<td height="20px"> 
								<input type="checkbox"  name="areaMajIdea3" id="radio5" value="1"
									<c:if test='${contract.areaMajIdea3 == "1"}'>checked="checked"</c:if> />
								符合 &nbsp;&nbsp;
								<input type="checkbox"  name="areaMajIdea3" id="radio5" value="0"
									<c:if test='${contract.areaMajIdea3 == "0"}'>checked="checked"</c:if> />
								不符合
							</td>
						</tr>
						<tr name="trAreaMaj">
							<td>
								4.售后服务是否符合（含售前、售中）
							</td>
							<td height="20px">
								<input type="checkbox"  name="areaMajIdea4" id="radio3" value="1"
									<c:if test='${contract.areaMajIdea4 == "1"}'>checked="checked"</c:if> />
								符合 &nbsp;&nbsp;
								<input type="checkbox"  name="areaMajIdea4" id="radio3" value="0"
									<c:if test='${contract.areaMajIdea4 == "0"}'>checked="checked"</c:if> />
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
										<td style="padding:5px 0 5px 0;" name="tdAreaMaj" width="330px">
											${tf:replaceBr(contract.areaMajText)}
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td height="20px">
								签字：&nbsp; ${contract.areaMajName}
							</td>
							<td>
								日期：&nbsp; ${contract.areaMajDate}
							</td>
						</tr>
						<tr>
							<td height="20px" colspan="2">&nbsp;
								
							</td>
						</tr>	
						<tr name="trSellMaj">
							<td height="20px" colspan="2">
								销售总监评审意见：
							</td>
						</tr>
						<tr name="trSellMaj">
							<td>
								1.毛利率是否符合
							</td>
							<td height="20px" width="150">
								<input type="checkbox"  name="sellMajIdea1" id="radio" value="1"
									<c:if test='${contract.sellMajIdea1 == "1"}'>checked="checked"</c:if> />
								符合 &nbsp;&nbsp;
								<input type="checkbox"  name="sellMajIdea1" id="radio" value="0"
									<c:if test='${contract.sellMajIdea1 == "0"}'>checked="checked"</c:if> />
								不符合
							</td>
						</tr>
						<tr name="trSellMaj">
							<td>
								2.付款方式是否符合
							</td>
							<td height="20px">
								<input type="checkbox"  name="sellMajIdea2" id="radio2" value="1"
									<c:if test='${contract.sellMajIdea2 == "1"}'>checked="checked"</c:if> />
								符合 &nbsp;&nbsp;
								<input type="checkbox"  name="sellMajIdea2" id="radio2" value="0"
									<c:if test='${contract.sellMajIdea2 == "0"}'>checked="checked"</c:if> />
								不符合
							</td>
						</tr>
						<tr name="trSellMaj">
							<td>
								3.运输方式是否符合
							</td>
							<td height="20px"> 
								<input type="checkbox"  name="sellMajIdea3" id="radio5" value="1"
									<c:if test='${contract.sellMajIdea3 == "1"}'>checked="checked"</c:if> />
								符合 &nbsp;&nbsp;
								<input type="checkbox"  name="sellMajIdea3" id="radio5" value="0"
									<c:if test='${contract.sellMajIdea3 == "0"}'>checked="checked"</c:if> />
								不符合
							</td>
						</tr>
						<tr name="trSellMaj">
							<td>
								4.售后服务是否符合（含售前、售中）
							</td>
							<td height="20px">
								<input type="checkbox"  name="sellMajIdea4" id="radio3" value="1"
									<c:if test='${contract.sellMajIdea4 == "1"}'>checked="checked"</c:if> />
								符合 &nbsp;&nbsp;
								<input type="checkbox"  name="sellMajIdea4" id="radio3" value="0"
									<c:if test='${contract.sellMajIdea4 == "0"}'>checked="checked"</c:if> />
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
										<td style="padding:5px 0 5px 0;" name="tdSellMaj" width="330px">
											${tf:replaceBr(contract.sellMajText)}
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td height="20px">
								签字：&nbsp; ${contract.sellMajName}
							</td>
							<td>
								日期：&nbsp; ${contract.sellMajDate}
							</td>
						</tr>
						<tr>
							<td height="20px" colspan="2">&nbsp;
								
							</td>
						</tr>
						<tr>
							<td height="20px" colspan="2">
								运营总监评审意见：
							</td>
						</tr>
						<tr name="trOpeMaj">
							<td>&nbsp;
								
							</td>
							<td height="20px">
								<input type="checkbox"  name="opeMajIdea" id="radio4" value="1"
									<c:if test='${contract.opeMajIdea == "1"}'>checked="checked"</c:if> />
								同意 &nbsp;&nbsp;
								<input type="checkbox"  name="opeMajIdea" id="radio4" value="0"
									<c:if test='${contract.opeMajIdea == "0"}'>checked="checked"</c:if> />
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
										<td style="padding:5px 0 5px 0;" name="tdOpeMaj" width="330px">
											${tf:replaceBr(contract.opeMajText)}
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td height="20px">
								签字： &nbsp; ${contract.opeMajName} 
							</td>
							<td>
								日期： &nbsp; ${contract.opeMajDate} 
							</td>
						</tr>
					</table>

    </td>
    <td >&nbsp;</td>
  </tr>
  <tr>
    <td></td>
    <td height="50px" align="center" valign="bottom">
    	<img src="${ctx}/images/btnTG.gif" width="65" height="20" onclick="submitCheck(1);" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    	<img src="${ctx}/images/btnWTG.gif" width="65" height="20" onclick="submitCheck(0);" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    	<a href="${ctx}/getSalesContractsList.do?init=true"><img src="${ctx}/images/btnBack.gif" /></a>
    </td>
    <td></td>
  </tr>
</table>
</html:form>
<br>

</div>

</body>
</html>
