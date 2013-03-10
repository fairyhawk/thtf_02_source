<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>销售发票管理</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" /> 
	
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script> 
<script type="text/javascript" src="${ctx}/js/messages_cn.js"></script>
<script type="text/javascript" src="${ctx}/js/Calendar1.js"></script> 
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
		<style type="text/css"> 
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
						//点击换色
						$(this).click( function(){
			              if( $(this).hasClass("rowselected") ){
			                $(this).removeClass("rowselected");
			              }else{
			                $(this).addClass("rowselected");
			              }
			            });
						//点击换色
		
					});
				}
			});
		//-->
		</script>
<script type="text/javascript">

	  $(document).ready(function(){
	      var msg = "${msg}";  //获取服务端信息
		if(msg!=""){
		alert(msg);
		}
	 });
	 	 
	 function confimSubmit(){
	  if(confirm('是否确认该销售退票？')==false)return false;
	  return true;  
	 }
	 
	function form0submit() {
	   $("#searchBackIncoice").submit();
	}
	
</script>

</head>

<body>
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 销售管理 &gt;&gt 销售发票管理</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
   <tr>
    <td >&nbsp;</td>
    <td align="center"><br />
    	<div class="mo_wp">
          <div style="display: ; " class="mo_con" >
          <form action="searchBackIncoice.do" id="searchBackIncoice" >
          	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
				<tr>
					<td class="td_01">发票号</td>
					<td class="td_02"><input type="text" name="searchBackIncoice.number" id="name2" style="width:120px;" value="${searchBackIncoiceDto.number}"/></td>
					<td class="td_01">开票申请单号</td>
					<td class="td_02">
					  <input type="text" name="searchBackIncoice.makeInvoiceId" id="name8" style="width:120px;" value="${searchBackIncoiceDto.makeInvoiceId}"/>
					</td>
				</tr>
				<tr>
					<td class="td_01">客户名称</td>
					<td class="td_02"><input type="text" name="searchBackIncoice.customerName" id="name4" style="width:240px;" value="${searchBackIncoiceDto.customerName}"/></td>
					<td class="td_01">产品分类名称</td>
					<td class="td_02"> 
					
						<html:select property="searchBackIncoice.productTypeId" value="${searchBackIncoiceDto.productTypeId}" style=" width:126px">
							<html:option value="">--请选择--</html:option>
							<logic:iterate id="productEntity" name="productEntitieList" >
							   <html:option value="${productEntity.id}"> ${productEntity.name}</html:option> 
							</logic:iterate>
						 
						</html:select>
					
					
					</td>
				</tr>
				<tr>
					<td class="td_01">发票类型</td>
					<td class="td_02">
					
						<html:select property="searchBackIncoice.invoiceType" style=" width:126px" value="${searchBackIncoiceDto.invoiceType}" >
							<html:option value="">--请选择--</html:option> 
							<html:option value="0">普通</html:option> 
							<html:option value="1">增值税</html:option>
						</html:select> 
					
					</td>
					<td class="td_01">申请人</td>
					<td class="td_02"><input type="text" name="searchBackIncoice.userName" id="name3" style="width:120px;" value="${searchBackIncoiceDto.userName}"/></td>
				</tr>
				<tr>
					<td class="td_01">确认人</td>
					<td class="td_02"><input type="text" name="searchBackIncoice.confirmName" id="name5" style="width:120px;" value="${searchBackIncoiceDto.confirmName}" /></td>
					<td class="td_01">发票状态</td>
					<td class="td_02">
					 
					<html:select property="searchBackIncoice.status" value="${searchBackIncoiceDto.status}" style=" width:126px">
							<html:option value="">--请选择--</html:option>
							<html:option value="1">开票</html:option>
							<html:option value="2">销售总监待评审</html:option>
							<html:option value="3">销售总监未通过</html:option>
							<html:option value="4">运营总监待评审</html:option> 
							<html:option value="5">运营总监未通过</html:option> 
							<html:option value="6">待确认</html:option> 
							<html:option value="7">退票成功</html:option>  
						</html:select>
					
					
					</td>
				</tr>
				<tr>
					<td class="td_01">开票起始日期</td>
					<td class="td_02"><input type="text" onfocus="calendar()" name="searchBackIncoice.dateStart" id="name12" style="width:120px;" value="${searchBackIncoiceDto.dateStart}" /></td>
					<td class="td_01">开票终止日期</td>
					<td class="td_02"><input type="text" onfocus="calendar()" name="searchBackIncoice.dateEnd" id="name9" style="width:120px;" value="${searchBackIncoiceDto.dateEnd}" /></td>
				</tr>
				<tr>
					<td class="td_01">申请起始日期</td>
					<td class="td_02"><input type="text" onfocus="calendar()" name="searchBackIncoice.requestDateStart" id="name7" style="width:120px;" value="${searchBackIncoiceDto.requestDateStart}" /></td>
					<td class="td_01">申请终止日期</td>
					<td class="td_02"><input type="text" onfocus="calendar()"  name="searchBackIncoice.requestDateEnd" id="name6" style="width:120px;" value="${searchBackIncoiceDto.requestDateEnd}" /></td>
				</tr>
				<tr>
					<td class="td_01">退票起始日期</td>
					<td class="td_02"><input type="text" onfocus="calendar()" name="searchBackIncoice.backDateStart" id="name11" style="width:120px;" value="${searchBackIncoiceDto.backDateStart}" /></td>
					<td class="td_01">退票终止日期</td>
					<td class="td_02"><input type="text" onfocus="calendar()" name="searchBackIncoice.backDateEnd" id="name10" style="width:120px;" value="${searchBackIncoiceDto.backDateEnd}" /></td>
				</tr>
				<tr>
					<td class="td_01">发货单号</td>
					<td class="td_02"><input type="text" name="searchBackIncoice.sendGoodsId" id="name12" style="width:120px;" value="${searchBackIncoiceDto.sendGoodsId}" /></td>
					<td class="td_01">&nbsp;</td>
					<td class="td_02">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="4" align="center" style="height:30px;"> 
					 <img src="${ctx}/images/btn_JianSuo.gif" onclick="form0submit()"/>
					
					</td>
				</tr>
			</table>
			</form>
          </div>
          <div class="mo_title" onclick="fMainListToggle(this)">
              <div  style="text-align:center"><img id="imgShang" src="${ctx}/images/shang_sj.png" /></div>
          </div>
        </div>
    </td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;&nbsp;</td>
    <td align="center">
    <div style="width:100%; text-align:right">单位：元</div>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table">
		<tr>
			<th nowrap="nowrap">&nbsp;开票日期&nbsp;</th>
			<th nowrap="nowrap">&nbsp;发票号&nbsp;</th>
			<th nowrap="nowrap">&nbsp;开票申请单号&nbsp;</th>
			<th nowrap="nowrap" >客户名称</th>
			<th nowrap="nowrap">&nbsp;产品分类名称&nbsp;</th>
			<th nowrap="nowrap">&nbsp;&nbsp;&nbsp;发票金额&nbsp;&nbsp;&nbsp;</th>
			<th nowrap="nowrap">&nbsp;发票类型&nbsp;</th>
			<th nowrap="nowrap">&nbsp;申请人&nbsp;</th>
			<th nowrap="nowrap">&nbsp;确认人&nbsp;</th>
			<th nowrap="nowrap">&nbsp;&nbsp;申请日期&nbsp;&nbsp;</th>
			<th nowrap="nowrap">&nbsp;&nbsp;退票日期&nbsp;&nbsp;</th>
			<th width="80px" nowrap="nowrap">发票状态</th>
			<th nowrap="nowrap" >&nbsp;操作&nbsp;</th>
		</tr>
		<logic:iterate id="backInvoiceDto" name="backInvoiceList" >
			<tr>
				<td nowrap="nowrap" width="73px" height="18">${backInvoiceDto.date}&nbsp;</td>
				<td width="120px" nowrap="nowrap">${backInvoiceDto.number}&nbsp;</td>
				<td width="80px" nowrap="nowrap">${backInvoiceDto.makeInvoiceId}&nbsp;</td>
				<td width="120px" nowrap="nowrap" title="${backInvoiceDto.customerName}"><div class="ellipsis_div" style="width:240px;">${backInvoiceDto.customerName}&nbsp;</div></td>
				<td width="72px" nowrap="nowrap" >${backInvoiceDto.productTypeName}&nbsp;</td>
				<td width="86px" nowrap="nowrap" style=" text-align:right;"><fmt:formatNumber value="${backInvoiceDto.money}" type="number" pattern="#,##0.00" />&nbsp;</td>
				<td width="60px" nowrap="nowrap" >
				  <logic:equal value="0" name="backInvoiceDto" property="invoiceType">普通</logic:equal> 
				  <logic:equal value="1" name="backInvoiceDto" property="invoiceType">增值税</logic:equal>
				  &nbsp;
				</td>
				<td width="72px" nowrap="nowrap">${backInvoiceDto.userName}&nbsp;</td>
				<td width="72px" nowrap="nowrap">${backInvoiceDto.confirmName}&nbsp;</td>
				<td width="73px" nowrap="nowrap">${backInvoiceDto.requestDate}&nbsp;</td>
				<td width="73px" nowrap="nowrap" >${backInvoiceDto.backDate}&nbsp;</td>
				<td width="95px" nowrap="nowrap" ><div class=ellipsis_div style="width:97px;">
				  <c:if test="${backInvoiceDto.status==1}">开票</c:if>
				  <c:if test="${backInvoiceDto.status==2}">销售总监待评审</c:if>
				  <c:if test="${backInvoiceDto.status==3}">销售总监未通过</c:if>
				  <c:if test="${backInvoiceDto.status==4}">运营总监待评审</c:if>
				  <c:if test="${backInvoiceDto.status==5}">运营总监未通过</c:if>
				  <c:if test="${backInvoiceDto.status==6}">待确认</c:if>
				  <c:if test="${backInvoiceDto.status==7}">退票成功</c:if></div>
				 </td> 
				
				<td nowrap="nowrap" >
					<a href="getSellInvoiceList.do?sellInvoiceId=${backInvoiceDto.id}&flag=1">查看</a>
					<!-- 销售经理  -->
					<c:if test="${USERLOGIN.roleId==4}"> 
					    <c:if test="${ (backInvoiceDto.status==3) || (backInvoiceDto.status==5)}">
						  <a href="getSellInvoiceList.do?sellInvoiceId=${backInvoiceDto.id}&flag=2">修改</a>
						</c:if>  
						<c:if test="${(backInvoiceDto.status==1) || (backInvoiceDto.status==2) || (backInvoiceDto.status==4) || (backInvoiceDto.status==6) || (backInvoiceDto.status==7)}">
						  修改
						</c:if> 
						<c:if test="${backInvoiceDto.status==1}">
						  <a href="getSellInvoiceList.do?sellInvoiceId=${backInvoiceDto.id}&flag=3">退票</a>
						</c:if>
						<c:if test="${backInvoiceDto.status!=1}">
						  退票
						</c:if>
					 </c:if> 
					 
					<!-- 销售总监  -->
					<c:if test="${USERLOGIN.roleId==5}">  
						<c:if test="${backInvoiceDto.status==2}">
						  <a href="showBackInvoiceJudge.do?sellInvoiceId=${backInvoiceDto.id}">评审</a>
						</c:if>
						<c:if test="${backInvoiceDto.status!=2}">
						  评审
						</c:if> 
					 </c:if> 
					<!-- 运营总监  -->
					<c:if test="${USERLOGIN.roleId==17}">  
					    <c:if test="${backInvoiceDto.status==4}">
						  <a href="showBackInvoiceJudge.do?sellInvoiceId=${backInvoiceDto.id}">评审</a>
						</c:if>
						<c:if test="${backInvoiceDto.status!=4}">
						    评审
						</c:if> 
					</c:if>				  
					<!-- 销售助理  -->
					<c:if test="${USERLOGIN.roleId==3}">
					    <c:if test="${backInvoiceDto.status==6}">
						  <a href="confirmBackIncoice.do?sellInvoiceId=${backInvoiceDto.id}&makeInvoiceId=${backInvoiceDto.makeInvoiceId}" onclick=" return confimSubmit()">确认</a>
						</c:if>
						<c:if test="${backInvoiceDto.status!=6}">
						    确认
						</c:if> 
					</c:if>	
					
					<!-- 其他权限只能查看  --> 
										
				</td>
			</tr>
		</logic:iterate>
	</table>
    <br />
        <table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table">
        	<tr>
            	<td align="right"><%@ include file="/jsp/common/newPage.jsp"%></td>
          </tr>
        </table>    </td>
    <td >&nbsp;</td>
  </tr>
</table>
 
</body>
</html>
