<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@taglib prefix="tf" uri="localhost" %>
<html>
<head>
<c:if test="${param.procurementOfficerError}">
	<script>alert("采购主管不存在！");</script>
</c:if>
<c:if test="${param.directorOfOperationsError}">
	<script>alert("运营总监不存在！");</script>
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
<c:if test="${!(buyBackContract.status==2&&USERLOGIN.roleId==15)&&!(buyBackContract.status==4&&USERLOGIN.roleId==11)&&!(buyBackContract.status==6&&USERLOGIN.roleId==17)}">
	<script>
	location="${ctx}/buyBackContractList.do";
	</script>
</c:if>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>采购退货合同评审</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/common/contractUtil.js"></script>
		<script type="text/javascript" src="${ctx}/js/math.js"></script>
		<script type="text/javascript" src="${ctx}/js/Calendar1.js"></script>
		<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
		<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="${ctx}/js/messages_cn.js"></script>
        <script type="text/javascript" src="${ctx}/js/util.js"></script>
<style type="text/css">
<!--
.STYLE1 {
	color: #FF0000
}
.treven {
	background-color: #f3fbff;
}
.over {
	background-color: #ECECEC;
}
-->
</style>
<script type="text/javascript">
		$(document).ready(function(){
				if($("#table")){
					$("#table tr:odd").addClass("treven");
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
			
			var userRole= document.getElementById('userRole').value;
			if(isPass=='1'){
				var isPassitems = $(":checkbox[value='1']:checked:enabled").length;
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
			var forms=document.getElementById("buyBackContractForm");
			forms.submit();
		}
</script>
</head>
<body>
<c:if test="${param.notExistError}">
	<script>alert("采购合同文件不存在!");</script>
</c:if>
<c:if test="${param.downLoadError}">
	<script>alert("下载文件出错!");</script>
</c:if>
<input type="hidden" id="tableLength" value="0"/>
<input type="hidden" id="buyState" name="bbce.status"/>
<input type="hidden" id="userRole" name="userRole" value="${USERLOGIN.roleId}"/>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 采购管理 &gt;&gt; 采购退货合同管理 &gt;&gt; 采购退货合同评审</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td><br />
      <div class="div_tiao"> &nbsp;&nbsp;采购合同信息 </div>
	  <input type="hidden" id="buyContractId" name="bbce.buyContractId" value="${buyBackContract.buyContractId}"/>
	  <input type="hidden" id="prodTypeId" name="bbce.productTypeId" value="${contractDto.prodTypeId}"/>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
                <tr>
          <td class="td_01" height="18px">产品合同号</td>
          <td class="td_02">${contractDto.prodContractCode}&nbsp;</td>
          <td class="td_01">公司合同号</td>
          <td class="td_02">${contractDto.compContractCode}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" width="20%" height="18px">签订日期</td>
          <td class="td_02" width="30%">${contractDto.signDate}&nbsp;</td>
          <td class="td_01" width="20%">产品分类名称</td>
          <td class="td_02" width="30%">${contractDto.prodTypeName}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18px">合同金额（元）</td>
          <td class="td_02"><fmt:formatNumber value="${contractDto.contractMoney}" pattern="#,##0.00000"/>&nbsp;</td>
          <td class="td_01">预付金额（元）</td>
          <td class="td_02"><fmt:formatNumber value="${contractDto.prepayMoney}" pattern="#,##0.00000"/>&nbsp;</td>
        </tr>
      </table>
      <br />
      <div class="div_tiao"> &nbsp;&nbsp;供货商信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" width="20%" height="18px">供货商名称</td>
          <td class="td_02" width="30%">${contractDto.supplierName}&nbsp;</td>
          <td class="td_01" width="20%">联系人</td>
          <td class="td_02" width="30%">${contractDto.linkman}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18px">省份</td>
          <td class="td_02">${contractDto.province}&nbsp;</td>
          <td class="td_01">电话</td>
          <td class="td_02">${contractDto.tel}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18px">城市</td>
          <td class="td_02">${contractDto.city}&nbsp;</td>
          <td class="td_01">传真</td>
          <td class="td_02">${contractDto.fax}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18px">开户行</td>
          <td class="td_02">${contractDto.remitBankName}&nbsp;</td>
          <td class="td_01">帐号</td>
          <td class="td_02">${contractDto.remitBankAccount}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18px">发票类型</td>
		  <td>&nbsp;
			<c:if test="${contractDto.invoiceType==0}">普通</c:if>
			<c:if test="${contractDto.invoiceType==1}">增值税</c:if>
          </td>
          <td class="td_01">增值税税率</td>
          <td class="td_02">${contractDto.taxRate} %</td>
        </tr>
      </table>
      <br />
      <div class="div_tiao"> &nbsp;&nbsp;退货合同信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" width="20%" height="18px">合同类型</td>
          <td class="td_02" width="30%">
          	<c:if test="${contractDto.contractType==0}">
          		国内
          	</c:if>
          	<c:if test="${contractDto.contractType==1}">
          		国外
          	</c:if>
          </td>
          <td class="td_01" width="20%">模版类型</td>
           <td class="td_02" width="30%" >
			<c:if test="${buyBackContract.templateType==0}">
            模版
             </c:if> 
			 <c:if test="${buyBackContract.templateType==1}">
            非模版
			</c:if>
			</td>
        </tr>
        
		<c:if test="${buyBackContract.templateType==0}">
        <tr id="tr_MT">
          <td class="td_01" height="18px">签订地点</td>
          <td colspan="3" class="td_02" >${contractDto.place}
			<input type="hidden" name="bbce.place" value="${contractDto.place}"/>
		  </td>
        </tr>
		</c:if>
      </table>
      <br/>
      <div class="div_tiao"> &nbsp;&nbsp;产品信息 </div>
      <div style="width:100%; text-align:right">单位：元</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table" style="border-left:1px solid #FFFFFF;">
      <thead>
        <tr>
          <th nowrap="nowrap" style="border-left:1px solid #c2c2c2;">序号</th>
          <th nowrap="nowrap">产品编码</th>
          <th nowrap="nowrap">产品名称</th>
          <th nowrap="nowrap">规格型号</th>
          <th nowrap="nowrap">单位</th>
          <th nowrap="nowrap">采购数</th>
          <th nowrap="nowrap">采购单价</th>
          <th nowrap="nowrap">采购金额</th>
          <th nowrap="nowrap">入库金额</th>
          <th nowrap="nowrap">指定金额</th>
          <th nowrap="nowrap">收票金额</th>
          <th nowrap="nowrap">已退货合同数</th>
          <th nowrap="nowrap" width="90">返厂金额</th>
          <th nowrap="nowrap" width="90">退货合同数</th>
          <th nowrap="nowrap">退货合同金额</th>
          <th nowrap="nowrap">库存总数</th>
        </tr>
        </thead>
        <tbody id="prodtbody">
        <tr>
        </tr>
		<bean:define id="total" value="0" type="java.lang.String" />
		<logic:iterate id="prod" name="prods" indexId="prodindex">
		<bean:define id="total" value="${total+(prod.buyPrice*prod.buyBackContractCount)}" type="java.lang.String" />
			<tr>
				<td style="border-left:1px solid #c2c2c2;" height="18px"><input type="hidden" name="prodid" id="productids" value="${prod.prodId}"><input type="hidden" name="inStockId" value="${prod.prodId}">${prodindex+1}</td>
				<td>${prod.prodCode}&nbsp;</td>
				<td>${prod.prodName}&nbsp;</td>
				<td>${prod.prodType}&nbsp;</td>
				<td>${prod.prodUnit}&nbsp;</td>
				<td style="text-align:right;">${prod.buyCount}&nbsp;</td>
				<td style="text-align:right;"><fmt:formatNumber value="${prod.buyPrice}" pattern="#,##0.00000"/>&nbsp;</td>
				<td style="text-align:right;"><fmt:formatNumber value="${prod.buyMoney}" pattern="#,##0.00000"/>&nbsp;</td>
				<td style="text-align:right;"><fmt:formatNumber value="${prod.instockMoney}" pattern="#,##0.00000"/>&nbsp;</td>
				<td style="text-align:right;"><fmt:formatNumber value="${prod.appointMoney}" pattern="#,##0.00000"/>&nbsp;</td>
				<td style="text-align:right;"><fmt:formatNumber value="${prod.receiveInvoiceMoney}" pattern="#,##0.00000"/>&nbsp;</td>
				<td style="text-align:right;">${prod.backContractCount}&nbsp;</td>
				<td style="text-align:right;"><fmt:formatNumber value="${prod.returnMoney}" pattern="#,##0.00000"/>&nbsp;</td>
				<td style="text-align:right;">${prod.buyBackContractCount}&nbsp;</td>
				<td style="text-align:right;"><fmt:formatNumber value="${prod.buyPrice*prod.buyBackContractCount}" pattern="#,##0.00000"/>&nbsp;</td>
				<td style="text-align:right;">${prod.stockTotalNum}&nbsp;</td>
			</tr>
		</logic:iterate>
        </tbody>
        <tfoot>
        <tr>
          <td colspan="14" nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF">退货合同金额合计</td>
          <td nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF" id="receiveTotalMoeny">
				<fmt:formatNumber value="${total}" pattern="#,##0.00000"/>
		  </td>
          <td nowrap="nowrap" style=" border:1px solid #FFFFFF; background-color:#FFFFFF"><input type="hidden" id="receiveTotalMoenyHidden" value="${total}" name="bbce.money"/> 元</td>
        </tr>
        </tfoot>
      </table>
      <div class="div_tiao"> &nbsp;&nbsp;退货退款信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td width="50%"  class="td_03" height="18px"> 退货退款方式</td>
          <td class="td_04" >
			<c:if test="${buyBackContract.backWay==1}">先退货后退款</c:if> 
            <c:if test="${buyBackContract.backWay==2}">先退款后退货</c:if> 
			<c:if test="${buyBackContract.backWay==3}">已供货未付款</c:if>
		    <c:if test="${buyBackContract.backWay==4}">未供货已付款</c:if>
			<c:if test="${buyBackContract.backWay==5}">未供货未付款</c:if>
            </td>
        </tr>
      </table>
      <br />
      <div class="div_tiao"> &nbsp;&nbsp;发货信息</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td width="62%"></td>
          <td align="left">要求发货日期&nbsp;&nbsp;&nbsp;
            ${buyBackContract.requestSendDate}</td>
        </tr>
      </table>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" width="20%" height="18">货物接收单位名称</td>
          <td colspan="3" class="td_02" ><input type="hidden" id="supplierAddressId" name="bbce.supplierAddressId" value="${supplierAddress.id}"/><input type="hidden" id="supplierId" value="${supplierAddress.supplierId}"/>
          	<label id="name">${supplierAddress.name}</label></td>
        </tr>
        <tr>
          <td class="td_01" height="18">发货地址</td>
          <td colspan="3" class="td_02" ><label id="address">${supplierAddress.address}</label></td>
        </tr>
        <tr>
          <td class="td_01" width="20%" height="18">联系人</td>
          <td class="td_02" width="30%"><label id="linkman">${supplierAddress.linkman}</label></td>
          <td class="td_01" width="20%">邮编</td>
          <td class="td_02" width="30%"><label id="postcode">${supplierAddress.postcode}</label></td>
        </tr>
        <tr>
          <td class="td_01" height="18">电话</td>
          <td class="td_02" ><label id="tel">${supplierAddress.tel}</label></td>
          <td class="td_01">手机</td>
          <td class="td_02" ><label id="mobile">${supplierAddress.mobile}</label></td>
        </tr>
      </table>
      <br />
      <div class="div_tiao"> &nbsp;&nbsp;退货原因说明</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_03" width="30%"> 退货原因说明</td>
          <td class="td_04" style="padding:5px 100px 5px 5px;">${tf:replaceBr(buyBackContract.text)}
          </td>
        </tr>
      </table>
      						<div class="div_tiao">
							&nbsp;&nbsp;评审意见
						</div>
						<html:form  action="getBackBuyContractsReview" styleId="buyBackContractForm" method="post">
						<table border="0" cellpadding="0" cellspacing="0" align="center" width="460">
						<c:if test="${buyBackContract.templateType==1}">
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
											<c:if test="${buyBackContract.legalIdea== 1}">checked="checked"</c:if> />
										符合 &nbsp;&nbsp;
										<input type="checkbox" name="buyContractReviewDto.legalIdea"
											id="checkbox4" value="0" 
											<c:if test="${buyBackContract.legalIdea == 0}">checked="checked"</c:if> />
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

												 <textarea name="buyContractReviewDto.legalText"  id="textArea" cols=60" rows="3" >${buyBackContract.legalText}</textarea>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr class="legalIdeaDIV">
									<td height="20px">
										签字：${USERLOGIN.name}
										<input type="hidden" name="buyContractReviewDto.legalName" value="${USERLOGIN.name}"/>
									</td>
									<td>
										日期：${buyBackContract.dateTime}
										<input type="hidden" name="buyContractReviewDto.legalDate" value="${buyBackContract.dateTime}"/>
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
											<c:if test="${buyBackContract.legalIdea== 1}">checked="checked"</c:if> />
										符合 &nbsp;&nbsp;
										<input type="checkbox" name="salesContract.legalIdea"
											id="checkbox4" disabled="disabled"
											<c:if test="${bbuyBackContract.legalIdea == 0}">checked="checked"</c:if> />
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
													${tf:replaceBr(buyBackContract.legalText)}
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr class="legalIdeaDIV">
									<td height="20px">
										签字：${buyBackContract.legalName}
									</td>
									<td>
										日期：${buyBackContract.legalDate}
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
										1. 退货合同数量是否符合
									</td>
									<td height="20px" width="140" nowrap="nowrap">
										<input type="checkbox" name="buyManidea1"
											id="checkbox" value="1" 
											<c:if test="${buyBackContract.buyManProductIdea == 1}">checked="checked"</c:if> />
										符合 &nbsp;&nbsp;
										<input type="checkbox" name="buyManidea1"
											id="checkbox" value="0" 
											<c:if test="${buyBackContract.buyManProductIdea == 0}">checked="checked"</c:if> />
										不符合
									</td>
								</tr>
								<tr class="proMajIdeaDIV">
									<td>
										2.退货退款方式是否符合
									</td>
									<td height="20px">
										<input type="checkbox" name="buyManidea2"
											id="checkbox2" value="1"  
											<c:if test="${buyBackContract.buyManPaymentTypeIdea == 1}">checked="checked"</c:if> />
										符合 &nbsp;&nbsp;
										<input type="checkbox" name="buyManidea2"
											id="checkbox2" value="0" 
											<c:if test="${buyBackContract.buyManPaymentTypeIdea == 0}">checked="checked"</c:if> />
										不符合
									</td>
								</tr>
								<tr class="proMajIdeaDIV">
									<td>
										3.退货地址是否符合
									</td>
									<td height="20px">
										<input type="checkbox" name="buyManidea3"
											id="checkbox3" value="1" 
											<c:if test="${buyBackContract.buyManBeforeserviceIdea == 1}">checked="checked"</c:if> />
										符合 &nbsp;&nbsp;
										<input type="checkbox" name="buyManidea3"
											id="checkbox3" value="0" 
											<c:if test="${buyBackContract.buyManBeforeserviceIdea == 0}">checked="checked"</c:if> />
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

												<td style="padding:5px 0 5px 0;">
												<textarea name="buyContractReviewDto.buyManText"   id="textArea" cols="60" rows="3" >${buyBackContract.buyManText}</textarea>
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
										日期：${buyBackContract.dateTime}
										<input type="hidden" name="buyContractReviewDto.buyManDate" value="${buyBackContract.dateTime}"/>
									</td>
								</tr>
									</logic:equal>
									<logic:notEqual name="USERLOGIN" property="roleId" value="11" scope="session">
								<tr class="proMajIdeaDIV">
									<td width="320" nowrap="nowrap">
										1.退货合同数量是否符合
									</td>
									<td height="20px" width="140" nowrap="nowrap">
										<input type="checkbox" name="salesContract.proMajIdea1"
											id="checkbox" disabled="disabled"
											<c:if test="${buyBackContract.buyManProductIdea == 1}">checked="checked"</c:if> />
										符合 &nbsp;&nbsp;
										<input type="checkbox" name="salesContract.proMajIdea1"
											id="checkbox" disabled="disabled"
											<c:if test="${buyBackContract.buyManProductIdea == 0}">checked="checked"</c:if> />
										不符合
									</td>
								</tr>
								<tr class="proMajIdeaDIV">
									<td>
										2.退货退款方式是否符合
									</td>
									<td height="20px">
										<input type="checkbox" name="salesContract.proMajIdea2"
											id="checkbox2" disabled="disabled"
											<c:if test="${buyBackContract.buyManPaymentTypeIdea == 1}">checked="checked"</c:if> />
										符合 &nbsp;&nbsp;
										<input type="checkbox" name="salesContract.proMajIdea2"
											id="checkbox2" disabled="disabled"
											<c:if test="${buyBackContract.buyManPaymentTypeIdea == 0}">checked="checked"</c:if> />
										不符合
									</td>
								</tr>
								<tr class="proMajIdeaDIV">
									<td>
										3.退货地址是否符合
									</td>
									<td height="20px">
										<input type="checkbox" name="salesContract.proMajIdea3"
											id="checkbox3" disabled="disabled"
											<c:if test="${buyBackContract.buyManBeforeserviceIdea == 1}">checked="checked"</c:if> />
										符合 &nbsp;&nbsp;
										<input type="checkbox" name="salesContract.proMajIdea3"
											id="checkbox3" disabled="disabled"
											<c:if test="${buyBackContract.buyManBeforeserviceIdea == 0}">checked="checked"</c:if> />
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
													${tf:replaceBr(buyBackContract.buyManText)}
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr class="proMajIdeaDIV">
									<td height="20px">
										签字：${buyBackContract.buyManName}
									</td>
									<td>
										日期：${buyBackContract.buyManDate}
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
							<c:if test="${USERLOGIN.roleId==17}">
							<tr>
								<td>&nbsp;
									
								</td>
								<td height="20px">
									<input type="checkbox" name="buyContractReviewDto.opeMajIdea"
										id="checkbox4" value="1" 
										<c:if test="${buyBackContract.opeMajIdea == 1}">checked="checked"</c:if> />
									同意 &nbsp;&nbsp;
									<input type="checkbox" name="buyContractReviewDto.opeMajIdea"
										id="checkbox4" value="0" 
										<c:if test="${buyBackContract.opeMajIdea == 0}">checked="checked"</c:if> />
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

												<textarea name="buyContractReviewDto.opeMajText"   id="textArea"  cols="60" rows="3" >${buyBackContract.opeMajText}</textarea>

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
									日期：${buyBackContract.dateTime}
									<input type="hidden" name="buyContractReviewDto.opeMajDate" value="${buyBackContract.dateTime}"/>
								</td>
							</tr>
							</c:if>
							<c:if test="${USERLOGIN.roleId!=17}">
							<tr>
								<td>&nbsp;
									
								</td>
								<td height="20px">
									<input type="checkbox" name="salesContract.opeMajIdea"
										id="checkbox4" disabled="disabled"
										<c:if test="${buyBackContract.opeMajIdea == 1}">checked="checked"</c:if> />
									同意 &nbsp;&nbsp;
									<input type="checkbox" name="salesContract.opeMajIdea"
										id="checkbox4" disabled="disabled"
										<c:if test="${buyBackContract.opeMajIdea == 0}">checked="checked"</c:if> />
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
												${tf:replaceBr(buyBackContract.opeMajText)}
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td height="20px">
								签字：${buyBackContract.opeMajName}
								</td>
								<td>
									日期：${buyBackContract.opeMajDate}
								</td>
							</tr>
						</c:if>
				<tr>
					
					<td colspan="2" style="color:red" height="50px" align="center" valign="bottom">
						<a href="javascript:submitReview('1')"><img src="${ctx}/images/btnTG.gif" width="65" height="20" /></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    					<a href="javascript:submitReview('0')"><img src="${ctx}/images/btnWTG.gif" width="65" height="20"/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<img src="${ctx}/images/btnBack.gif" onClick="javascript:history.go(-1);" />
					</td>
					<td></td>
				</tr>
			</table>
			<input type="hidden" name="buyContractReviewDto.status" id="buyContractReviewDto.status"/>
			<input type="hidden" name="buyContractReviewDto.buyManIdea" id="buyContractReviewDto.buyManIdea" />
			<input type="hidden" name="buyContractReviewDto.id" value="${buyBackContract.buyBackContractId}"/>
			<input type="hidden" name="buyContractReviewDto.legalId" value="${buyBackContract.legalId}"/>
			<input type="hidden" name="buyContractReviewDto.buyManId" value="${buyBackContract.buyManId}"/>
			<input type="hidden" name="buyContractReviewDto.opeMajId" value="${buyBackContract.opeMajId}"/>
			<input type="hidden" name="buyContractReviewDto.prodTotalMoney" value="${total}"/>
			<input type="hidden" name="buyContractReviewDto.increaserate" value="${increaserate}"/>
			<input type="hidden" name="buyContractReviewDto.prodTypeId" value="${buyBackContract.productTypeId}"/>
			<input type="hidden" name="buyContractReviewDto.prodContractCode" value="${contractDto.prodContractCode}"/>
			</html:form>
      
      </td>
    <td >&nbsp;</td>
  </tr>
</table>
    <br />

</body>

</html>
