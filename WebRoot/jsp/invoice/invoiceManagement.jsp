<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@taglib prefix="tf" uri="localhost" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>开票管理</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
<script type="text/javascript" src="${ctx}/js/Calendar1.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<style>
#div1 a {text-decoration: underline}
<!--
.treven {
	background-color: #f3fbff;
}
.over {
	background-color: #ECECEC;
}
.rowselected {
	background-color: #868686;
}
-->
</style>
</head>
<body>

<script type="text/javascript">
	function querySubmit() {document.forms[0].submit();}
	function delInvoice(){
		if(confirm('确认删除该条信息与明细？')){return true;}else{return false;}
	}
	$(document).ready(function(){

	if($("#table1")){
			$("#table1 tr:even").addClass("treven");
			$("#table1 tr").each(function(i){
				$(this).mouseover(function(){
					$(this).addClass("over");
				});
				$(this).mouseout(function(){
					$(this).removeClass("over");
				});
				$(this).click( function(){
				  if( $(this).hasClass("rowselected") ){
					$(this).removeClass("rowselected");
				  }else{
					$(this).addClass("rowselected");
				  }
				});
			});
		}	
		});
</script>
<html:form action="findInvoiceList.do" method="get">

<table width="100%" height="75%" border="0" cellpadding="0"
	cellspacing="0">
	<tr>
		<td class="ye_header_left">&nbsp;</td>
		<td class="ye_header_center" style="font-size:12px"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 销售管理 &gt;&gt; 开票管理</td>
		<td class="ye_header_right">&nbsp;</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td align="center">
		<div class="mo_wp">
		<div style="display: " class="mo_con"><br /> 	
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="biao3">
			<tr>
				<td class="td_01">开票申请单号</td>
				<td class="td_02">
					<html:text property="invoiceListDto.id" style="width: 120px;"></html:text>
				<td class="td_01">产品分类名称</td>
				<td class="td_02">
				<html:select property="invoiceListDto.productTypeName" value="${invoiceDto.productTypeName}" style="width:126px">
					<html:option value="">--请选择--</html:option>
					<html:options collection="productType" property="id"  labelProperty="name" />
				</html:select></td>
			</tr>
			<tr>
				<td class="td_01">客户名称</td>
				<td class="td_02">
				<html:text property="invoiceListDto.customerName" style="width: 120px;"></html:text>
				<td class="td_01">发票类型</td>
				<td class="td_02">
					<html:select property="invoiceListDto.invoiceType" value="${invoiceDto.invoiceType}" style="width:126px">
						<html:option value="">--请选择--</html:option> 
						<html:option value="0">普通</html:option>
						<html:option value="1">增值税</html:option>
					</html:select>
					 
				</td>
			</tr>
			<tr>
				<td class="td_01">申请起始日期</td>
				<td class="td_02">
					<html:text property="invoiceListDto.date" style="width: 120px;" onfocus="calendar()" readonly="readonly"></html:text>
				</td>
				<td class="td_01">申请终止日期</td>
				<td class="td_02">
					<html:text property="invoiceListDto.applyEndTime" style="width: 120px;" onfocus="calendar()" readonly="readonly"></html:text>
				</td>
			</tr>
			<tr>
				<td class="td_01">通知起始日期</td>
				<td class="td_02">
					<html:text property="invoiceListDto.notifyDate" style="width: 120px;" onfocus="calendar()" readonly="readonly"></html:text>
				</td>
				<td class="td_01">通知终止日期</td>
				<td class="td_02">
					<html:text property="invoiceListDto.notifyEndTime"  style="width: 120px;" onfocus="calendar()" readonly="readonly"></html:text>
				</td>
			</tr>
			<tr>
				<td class="td_01">申请人</td>
				<td class="td_02">
					<html:text property="invoiceListDto.userName" style="width: 120px;"></html:text>
				</td>
				<td class="td_01">通知人</td>
				<td class="td_02">
					<html:text property="invoiceListDto.notifyName" style="width: 120px;"></html:text>
				</td>
			</tr>
			<tr>
				<td class="td_01">开票状态</td>
				<td class="td_02">
					<html:select property="invoiceListDto.status" value="${invoiceDto.status}" style="width:126px">
						<html:option value="">--请选择--</html:option> 
						<html:option value="1">未提交</html:option>
						<html:option value="6">待通知</html:option>
						<html:option value="2">待确认</html:option>
						<html:option value="7">申请成功</html:option>
					</html:select>
				</td>
				<td class="td_01">发货单号</td>
				<td class="td_02"><html:text property="invoiceListDto.sendGoodsId" style="width: 120px;"></html:text></td>
			</tr>
			<tr>
				<td colspan="4" align="center" style="height: 30px;">
					<a href="javascript:querySubmit();">
						<img  src="${ctx}/images/btn_JianSuo.gif" />
					</a>
				</td>
			</tr>
		</table>
		</div>
		<div class="mo_title" onclick="fMainListToggle(this)">
		<div style="text-align: center"><img id="imgShang" src="${ctx}/images/shang_sj.png" /></div>
		</div>
		</div>
		</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td align="center">
		<font color="red" style="font-size:14px">
		<c:if test="${param.error}">删除失败，状态已被更改</c:if>
		<c:if test="${param.passError}">无此权限操作</c:if>
		</font>
		<div style="width: 100%;font-size:12px; text-align: right">单位：元</div>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table1">
			<tr>
				<th nowrap="nowrap">&nbsp;开票申请单号&nbsp;</th>
				<th nowrap="nowrap">&nbsp;产品分类名称&nbsp;</th>
				<th nowrap="nowrap">客户名称</th>
				<th nowrap="nowrap">&nbsp;&nbsp;&nbsp;申请金额&nbsp;&nbsp;&nbsp;</th>
				<th nowrap="nowrap">&nbsp;&nbsp;&nbsp;发票金额&nbsp;&nbsp;&nbsp;</th>
				<th nowrap="nowrap">&nbsp;&nbsp;&nbsp;退票金额&nbsp;&nbsp;&nbsp;</th>
				<th nowrap="nowrap">发票类型</th>
				<th nowrap="nowrap">&nbsp;&nbsp;申请日期&nbsp;&nbsp;</th>
				<th nowrap="nowrap">&nbsp;&nbsp;通知日期&nbsp;&nbsp;</th>
				<th nowrap="nowrap">&nbsp;&nbsp;确认日期&nbsp;&nbsp;</th>
				<th nowrap="nowrap">&nbsp;申请人&nbsp;</th>
				<th nowrap="nowrap">&nbsp;通知人&nbsp;</th>
				<th nowrap="nowrap">&nbsp;开票状态&nbsp;</th>
				<th nowrap="nowrap">&nbsp;操作&nbsp;</th>
			</tr>
			<logic:iterate id="invoiceList" name="invoiceList">
				<tr>
				<td nowrap="nowrap" height="18px" width="72px">${invoiceList.id}</td>
				<td nowrap="nowrap" >${invoiceList.productTypeName}&nbsp;</td>
				<td nowrap="nowrap" width="180px"><div class="ellipsis_div" style="width:180px;">${tf:replaceHTML(invoiceList.customerName)}&nbsp;</div></td>
				<td nowrap="nowrap" width="90px" style="text-align: right;">
				<fmt:formatNumber value="${invoiceList.money}" type="number" pattern="#,##0.00" />&nbsp;
				</td>
				<td nowrap="nowrap" width="90px" style="text-align: right;">
					<logic:empty name="invoiceList" property="invoiceMoney">0.00&nbsp;</logic:empty>
					<logic:notEmpty name="invoiceList" property="invoiceMoney"><fmt:formatNumber value="${invoiceList.invoiceMoney}" type="number" pattern="#,##0.00" />&nbsp;</logic:notEmpty>
				</td>
				<td nowrap="nowrap" width="90px" style="text-align: right">
					<logic:empty name="invoiceList" property="returnMoney">0.00&nbsp;</logic:empty>
					<logic:notEmpty name="invoiceList" property="returnMoney"><fmt:formatNumber value="${invoiceList.returnMoney}" type="number" pattern="#,##0.00" />&nbsp;</logic:notEmpty>
				</td>
				<td nowrap="nowrap" width="60px">
					<logic:equal value="0" name="invoiceList" property="invoiceType">普通</logic:equal>
					<logic:equal value="1" name="invoiceList" property="invoiceType">增值税</logic:equal>
				</td>
				<td nowrap="nowrap" width="73px">${invoiceList.date}&nbsp;</td>
				<td nowrap="nowrap" width="73px">${invoiceList.notifyDate}&nbsp;</td>
				<td nowrap="nowrap" width="73px">${invoiceList.confirmDate}&nbsp;</td>
				<!-- <td nowrap="nowrap" width="126px"><div class="ellipsis_div" style="width:126px;">${invoiceList.notifyDate}&nbsp;</div></td>
				<td nowrap="nowrap" width="126px"><div class="ellipsis_div" style="width:126px;">${invoiceList.confirmDate}&nbsp;</div></td>
				 -->
				<td nowrap="nowrap" width="70px">${invoiceList.userName}&nbsp;</td>
				<td nowrap="nowrap" width="70px">${invoiceList.notifyName}&nbsp;</td>
				<td nowrap="nowrap" width="70px">
					<logic:equal value="1" name="invoiceList" property="status">未提交</logic:equal>
					<logic:equal value="6" name="invoiceList" property="status">待通知</logic:equal>
					<logic:equal value="2" name="invoiceList" property="status">待确认</logic:equal>
					<logic:equal value="7" name="invoiceList" property="status">申请成功</logic:equal>
				</td>
				<td nowrap="nowrap">
				<html:link title="查看" action="lookInvoice" paramId="id" paramName="invoiceList" paramProperty="id" >查看</html:link>
				<c:choose>	
					<c:when test="${requestScope.role==4}">
						<c:choose>
						<c:when test="${invoiceList.status==1}">
							<html:link title="修改" action="modifyInvoiceOfShow" paramId="id" paramName="invoiceList" paramProperty="id" >修改</html:link>
							<html:link title="删除" action="delInvoice" paramId="id" paramName="invoiceList" paramProperty="id" onclick="return delInvoice()">删除</html:link>
						</c:when>
						<c:otherwise> 
						修改 删除
						</c:otherwise>
						</c:choose>
					</c:when>
					<c:when test="${requestScope.role==3}">
						<c:choose>
							<c:when test="${invoiceList.status==6}">
								<a href="${ctx}/showNotifiedInvoice.do?invoiceId=${invoiceList.id}">开票通知</a>
							</c:when>
							<c:otherwise>开票通知</c:otherwise>
						</c:choose>
						
						<c:choose>
							<c:when test="${invoiceList.status==7}">
								<html:link title="确认" action="decideInvoice" paramId="id" paramName="invoiceList" paramProperty="id" >确认</html:link>
							</c:when>
							<c:otherwise>确认</c:otherwise>
						</c:choose>
					</c:when>
					
					<c:when test="${requestScope.role==21}">
						<c:choose>
							<c:when test="${invoiceList.status==2}">
								<a href="${ctx}/showConfirmInvoice.do?invoiceId=${invoiceList.id}">确认</a>
							</c:when>
							<c:otherwise>确认</c:otherwise>
						</c:choose> 
					</c:when>
					
				</c:choose>

					</td>
			</tr>
			
			</logic:iterate>
			<html:hidden  property="invoiceListDto.userId" value="${requestScope.userid}"/>
			<html:hidden  property="invoiceListDto.type" value="${requestScope.role}"/>
		</table>
		<br />
		<table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table">
			<tr>
				<td width="100px" align="left"><c:if test="${requestScope.role==4}"><a href="${ctx}/addInvoiceOfShow.do"><img src="${ctx}/images/btnKPSQ.gif" width="88" height="20" /></a></c:if></td>
				<td align="right" height="20px">
					<div style="text-align:right;font-size:12px" id="div1">
						<%@ include file="/jsp/common/newPage.jsp"%>
					</div>
				</td>
			</tr>
		</table>
		
		</td>
		<td>&nbsp;</td>
	</tr>
	
</table>

</html:form>
</body>
</html>