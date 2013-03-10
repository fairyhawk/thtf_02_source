<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>付款管理</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
<script type="text/javascript" src="${ctx}/js/math.js"></script>
<script type="text/javascript" src="${ctx}/js/Calendar1.js"></script>
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
	var paymentId ,money;
		<!--
			$(document).ready(function(){
				//隔行换色
					if($("#table")){
						$("#table tr:even").addClass("treven");
						$("#table tr").each(function(i){
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
					//alert($('#init').val());
				//检索提交
					$('#btnJianSuo').click(function(){
						trimText();
						$('#init').val('false');
						$('#buyPaymentListForm').submit();
					});	
				//服务器端消息
				var err = "${err}";		
				if (err != "") {
					alert(err);
				}
			});
		//-->
		//确认
		function bearingConfirmation(id,arterm,status){
			if(status == 11){
				window.open('${ctx}/jsp/purchase/buyPaymentAcceptAppoint.jsp?id='+id+'&arterm='+arterm+'&status='+status,'newwindow','toolbar=no,scrollbars=yes,resizable=no,top=300,left=450,width=600,height=250');
			}
			if(status == 12){
				window.location ='${ctx}/confirmBuyPaymentModifyInfo.do?id='+id;
				//window.open('${ctx}/jsp/purchase/buyPaymentAppoint.jsp?id='+id+'&arterm='+arterm+'&status='+status,'newwindow','toolbar=no,scrollbars=yes,resizable=no,top=300,left=450,width=600,height=250');
			}
		}
		//删除
		function deleteBuyPayment(id){
			if(confirm("是否确认删除？")){
				window.location ='${ctx}/deleteBuyPayment.do?id='+id;
			}
		}
		function refreshDate(){
			window.location.href="${ctx}/buyPayment.do";
			//$.post("${ctx}/buyPayment.do");
		}
		function trimText(){
	        var fom = document.forms[0];
	        for(var i=0;i<fom.elements.length;i++){
	            if(fom.elements[i].type == 'text'){
	               fom.elements[i].value = $.trim(fom.elements[i].value);
	            }
	        }
	    }
	    function zd(id,backMoney){
	    	paymentId = id;
	    	money = backMoney;
	    	$.post("${ctx}/buyPaymentzd.do?id="+id,waitResult1,"html");
	    }
	    function waitResult1(result){
	    	if(result*1 == 1){
	    		alert("外单不充许指定！");
	    	}else{
	    		window.location.href="${ctx}/againAppointBuyPaymentInit.do?id="+paymentId+"&backMoney="+money;	
	    	}
	    }
		</script>
</head>

<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left" width="16px" ><div class=ellipsis_div style="width:16px;">&nbsp;</div></td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 采购管理 &gt;&gt; 付款管理</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
   <tr>
    <td >&nbsp;</td>
    <td align="center"><br />
    	<div class="mo_wp">
          <div style="display: ; " class="mo_con" >
          <form action="buyPayment.do" name="buyPaymentListForm" id="buyPaymentListForm"> 
          	<input type="hidden" id="init" name="buyPaymentListQueryDto.init" value="${buyPaymentListForm.buyPaymentListQueryDto.init}"  />
          	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
				<tr>
					<td class="td_01" width="20%">付款申请单号</td>
					<td class="td_02" width="30%"><input type="text" name="buyPaymentListQueryDto.id" id="name" style="width:120px;" value="${buyPaymentListForm.buyPaymentListQueryDto.id}" /></td>
					<td class="td_01" width="20%">产品分类名称</td>
					<td class="td_02" width="30%">
						<html:select property="buyPaymentListQueryDto.productTypeId" value="${buyPaymentListForm.buyPaymentListQueryDto.productTypeId}" style=" width:126px">
							<html:option value="">--请选择--</html:option> 
							<html:options collection="producttypelist" property="id" labelProperty="name"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<td class="td_01" width="20%">入库单号</td>
					<td class="td_02" width="30%"><input type="text" name="buyPaymentListQueryDto.inStockId" id="name" style="width:120px;" value="${buyPaymentListForm.buyPaymentListQueryDto.inStockId}" /></td>
					<td class="td_01" width="20%">产品合同号</td>
					<td class="td_02" width="30%"><input type="text" name="buyPaymentListQueryDto.productContractCode" id="name" style="width:120px;" value="${buyPaymentListForm.buyPaymentListQueryDto.productContractCode}" /></td>
				</tr>
				<tr>
					<td class="td_01">供货商名称</td>
					<td class="td_02"><input type="text" name="buyPaymentListQueryDto.supplierName" id="name4" style="width:120px;" value="${buyPaymentListForm.buyPaymentListQueryDto.supplierName}"  /></td>
					<td class="td_01">付款金额</td>
					<td class="td_02"><input type="text" name="buyPaymentListQueryDto.money" id="name7" style="width:120px;" value="${buyPaymentListForm.buyPaymentListQueryDto.money}"  />
					元</td>
				</tr>
				<tr>
					<td class="td_01">付款方式</td>
		  			<td class="td_02">
		  				<html:select property="buyPaymentListQueryDto.paymentWay" value="${buyPaymentListForm.buyPaymentListQueryDto.paymentWay}" style="width:126px">
							<html:option value="">--请选择--</html:option> 	 
							<html:option value="2">支票</html:option>
		                    <html:option value="3">网银</html:option>
		                    <html:option value="4">电汇</html:option>
		                    <html:option value="5">银行承兑</html:option>
		                    <html:option value="6">信用证</html:option>
		                    <html:option value="7">其他</html:option>
						</html:select>
					</td>
					<td class="td_01">付款单状态</td>
					<td class="td_02">
						<html:select property="buyPaymentListQueryDto.status" value="${buyPaymentListForm.buyPaymentListQueryDto.status}" style="width:126px">
							<html:option value="">--请选择--</html:option> 
							<html:option value="1">未提交</html:option>
							<html:option value="2">产品总监待评审</html:option>
							<html:option value="3">产品总监未通过</html:option>
							<html:option value="4">采购主管待评审</html:option>
							<html:option value="5">采购主管未通过</html:option>
							<html:option value="6">运营助理待评审</html:option>
							<html:option value="7">运营助理未通过</html:option>
							<html:option value="8">运营总监待评审</html:option>
							<html:option value="9">运营总监未通过</html:option>
							<html:option value="10">待打印</html:option>
							<html:option value="11">承兑待确认</html:option>
							<html:option value="12">付款待确认</html:option>
							<html:option value="13">付款完成</html:option>
						</html:select>
					</td>
			  </tr>
				<tr>
					<td class="td_01">申请起始日期</td>
					<td class="td_02">
						<input type="text" name="buyPaymentListQueryDto.startDate" id="buyPaymentListQueryDto.startDate" style="width:120px;" onfocus="calendar()"
									readonly="readonly" value="${buyPaymentListQueryForm.buyPaymentListQueryDto.startDate}" />
					</td>
					<td class="td_01">申请终止日期</td>
					<td class="td_02">
						<input type="text" name="buyPaymentListQueryDto.endDate" id="buyPaymentListQueryDto.endDate" style="width:120px;" onfocus="calendar()"
									readonly="readonly" value="${buyPaymentListQueryForm.buyPaymentListQueryDto.endDate}" />
					</td>
				</tr>
				<tr>
					<td class="td_01">承兑开具起始日期</td>
					<td class="td_02">
						<input type="text" name="buyPaymentListQueryDto.startAcceptDate" id="buyPaymentListQueryDto.startAcceptDate" style="width:120px;" onfocus="calendar()"
									readonly="readonly" value="${buyPaymentListQueryForm.buyPaymentListQueryDto.startAcceptDate}" />
					</td>
					<td class="td_01">承兑开具终止日期</td>
					<td class="td_02">
						<input type="text" name="buyPaymentListQueryDto.endAcceptDate" id="buyPaymentListQueryDto.endAcceptDate" style="width:120px;" onfocus="calendar()"
									readonly="readonly" value="${buyPaymentListQueryForm.buyPaymentListQueryDto.endAcceptDate}" />
					</td>
				</tr>
				<tr>
					<td class="td_01">付款起始日期</td>
					<td class="td_02">
						<input type="text" name="buyPaymentListQueryDto.startPaymentDate" id="buyPaymentListQueryDto.startPaymentDate" style="width:120px;" onfocus="calendar()"
									readonly="readonly" value="${buyPaymentListQueryForm.buyPaymentListQueryDto.startPaymentDate}" />
					</td>
					<td class="td_01">付款终止日期</td>
					<td class="td_02">
						<input type="text" name="buyPaymentListQueryDto.endPaymentDate" id="buyPaymentListQueryDto.endPaymentDate" style="width:120px;" onfocus="calendar()"
									readonly="readonly" value="${buyPaymentListQueryForm.buyPaymentListQueryDto.endPaymentDate}" />
					</td>
				</tr>
				<tr>
					<td class="td_01">申请人</td>
					<td class="td_02"><input type="text" name="buyPaymentListQueryDto.userName" id="name6" style="width:120px;" value="${buyPaymentListForm.buyPaymentListQueryDto.userName}"  /></td>
					<td class="td_01">&nbsp;</td>
					<td class="td_02">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="4" align="center" style="height:30px;"><img id="btnJianSuo" src="${ctx}/images/btn_JianSuo.gif" /></td>
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
			<th nowrap="nowrap">付款单号</th>
		  	<th nowrap="nowrap">&nbsp;&nbsp;&nbsp;产品分类&nbsp;&nbsp;&nbsp;</th>
			<th nowrap="nowrap">供货商名称</th>
			<th nowrap="nowrap">&nbsp;&nbsp;付款金额&nbsp;&nbsp;</th>
			<th nowrap="nowrap">&nbsp;&nbsp;指定金额&nbsp;&nbsp;</th>
			<th nowrap="nowrap">&nbsp;&nbsp;合同预付金额&nbsp;&nbsp;</th>
			<th nowrap="nowrap">&nbsp;&nbsp;产品分类预付金额&nbsp;&nbsp;</th>
			<th nowrap="nowrap" >&nbsp;&nbsp;退款金额&nbsp;&nbsp;</th>
		    <th nowrap="nowrap" >&nbsp;申请日期&nbsp;</th>
	      	<th nowrap="nowrap" >&nbsp;付款方式&nbsp;</th>
			<th nowrap="nowrap" >承兑开具日期</th>
			<th nowrap="nowrap" >帐期(天)</th>
			<th nowrap="nowrap" >&nbsp;付款日期&nbsp;</th>
			<th nowrap="nowrap" >凭证号码</th>
			<th nowrap="nowrap">&nbsp;&nbsp;申请人&nbsp;&nbsp;</th>
			<th nowrap="nowrap" >&nbsp;&nbsp;付款单状态&nbsp;&nbsp;</th>
			<th nowrap="nowrap">&nbsp;操作&nbsp;</th>
		</tr>
		<logic:iterate id="payment" name="list">
			<tr>
				<td nowrap="nowrap" width="120px" height="18px">${payment.id}&nbsp;</td>
				<td nowrap="nowrap" width="120px">${payment.productTypeName}&nbsp;</td>
				<td nowrap="nowrap" width="120px" title="${payment.supplierName}"><div class="ellipsis_div" style="width:120px;">${payment.supplierName}</div></td>
				<td nowrap="nowrap" width="90px" style="text-align:right;" title="<fmt:formatNumber value="${payment.money}" pattern="#,##0.00000#"/>"><fmt:formatNumber value="${payment.money}" pattern="#,##0.00000#"/>&nbsp;</td>
				<td nowrap="nowrap" width="90px" style="text-align:right;" title="<fmt:formatNumber value="${payment.appointMoney1}" pattern="#,##0.00000#"/>"><fmt:formatNumber value="${payment.appointMoney1}" pattern="#,##0.00000#"/>&nbsp;</td>
				<td nowrap="nowrap" width="90px" style="text-align:right;" title="<fmt:formatNumber value="${payment.appointMoney0}" pattern="#,##0.00000#"/>"><fmt:formatNumber value="${payment.appointMoney0}" pattern="#,##0.00000#"/>&nbsp;</td>
				
				<td nowrap="nowrap" width="90px" style="text-align:right;" title="<fmt:formatNumber value="${payment.productMoney}" pattern="#,##0.00000#"/>"><fmt:formatNumber value="${payment.productMoney}" pattern="#,##0.00000#"/>&nbsp;</td>
				
				<td nowrap="nowrap" width="90px" style="text-align:right;" title="<fmt:formatNumber value="${payment.backMoney}" pattern="#,##0.00000#"/>"><fmt:formatNumber value="${payment.backMoney}" pattern="#,##0.00000#"/>&nbsp;</td>
				<td nowrap="nowrap" width="73px" >${payment.date}&nbsp;</td>
				<td nowrap="nowrap"  >
				<c:if test="${payment.paymentWay==2}">
					支票
				</c:if>
				<c:if test="${payment.paymentWay==3}">
					网银
				</c:if>
				<c:if test="${payment.paymentWay==4}">
					电汇
				</c:if>
				<c:if test="${payment.paymentWay==5}">
			    	银行承兑
				</c:if>
				<c:if test="${payment.paymentWay==6}">
			    	信用证
				</c:if>
				<c:if test="${payment.paymentWay==7}">
			    	其它
				</c:if>
				&nbsp;
				</td>
				<td nowrap="nowrap" width="77px" >${payment.acceptDate}&nbsp;</td>
				<td nowrap="nowrap" width="51px" >${payment.arterm}&nbsp;</td>
				<td nowrap="nowrap" width="77px" >${payment.paymentDate}&nbsp;</td>
				<td nowrap="nowrap" width="120px" ><div class="ellipsis_div" style="width:120px;">${payment.number}&nbsp;</div></td>
				<td nowrap="nowrap">${payment.userName}&nbsp;</td>
				<td nowrap="nowrap">
				<c:if test="${payment.status==1}">
					未提交
				</c:if>
				<c:if test="${payment.status==2}">
					产品总监待评审
				</c:if>
				<c:if test="${payment.status==3}">
					产品总监未通过
				</c:if>
				<c:if test="${payment.status==4}">
			    	采购主管待评审
				</c:if>
				<c:if test="${payment.status==5}">
					采购主管未通过
				</c:if>
				<c:if test="${payment.status==6}">
					运营助理待评审
				</c:if>
				<c:if test="${payment.status==7}">
					运营助理未通过
				</c:if>
				<c:if test="${payment.status==8}">
			    	运营总监待评审
				</c:if>
				<c:if test="${payment.status==9}">
					运营总监未通过
				</c:if>
				<c:if test="${payment.status==10}">
					待打印
				</c:if>
				<c:if test="${payment.status==11}">
					承兑待确认
				</c:if>
				<c:if test="${payment.status==12}">
			    	付款待确认
				</c:if>
				<c:if test="${payment.status==13}">
			    	付款完成
				</c:if></td>
				<td nowrap="nowrap">
				  <a href="javascript:window.location ='${ctx}/viewBuyPayment.do?id=${payment.id}'">查看</a>
				  <c:if test="${userRoleId==8}">
				  	  <c:if test="${payment.status==1||payment.status==3||payment.status==5||payment.status==7||payment.status==9}">
					  	<a href="javascript:window.location ='${ctx}/modifyBuyPaymentInit.do?id=${payment.id}'">修改</a>
					  	<a href="javascript:deleteBuyPayment('${payment.id}');">删除</a>
					  </c:if> 
					  <c:if test="${!(payment.status==1||payment.status==3||payment.status==5||payment.status==7||payment.status==9)}">
					  	修改
					  	删除
					  </c:if> 
					  <c:if test="${payment.status==11||payment.status==12}">
					  	<a href="javascript:bearingConfirmation('${payment.id}','${payment.arterm}','${payment.status}');">确认</a>
					  </c:if>
					  <c:if test="${payment.status!=11&&payment.status!=12}">
					  	确认
					  </c:if>
					  <c:if test="${payment.status==13}">
					  	<% //<a href="javascript:window.location ='${ctx}/appointBuyPaymentInit.do?id=${payment.id}'">指定</a> %>
					  	<a href="javaScript:zd('${payment.id}','${payment.backMoney}');">指定</a>
					  	<a href="${ctx}/getPaymentInfoForAdd.do?id=${payment.id}">退款</a>
					  </c:if>
					  <c:if test="${payment.status!=13}">
					  	<% //指定 %>
					  	指定
					  	退款
					  </c:if>
					  
					  
				  </c:if>
				  <c:if test="${userRoleId ==10||userRoleId ==11||userRoleId==16||userRoleId==17}">
				  	  <c:if test="${payment.status==2&&userRoleId ==10}">
				  	  	<a href="javascript:window.location ='${ctx}/auditBuyPaymentInit.do?id=${payment.id}'">评审</a>
				  	  </c:if>
				  	  <c:if test="${payment.status==4&&userRoleId ==11}">
				  	  	<a href="javascript:window.location ='${ctx}/auditBuyPaymentInit.do?id=${payment.id}'">评审</a>
				  	  </c:if>
				  	  <c:if test="${payment.status==6&&userRoleId==16}">
				  	  	<a href="javascript:window.location ='${ctx}/auditBuyPaymentInit.do?id=${payment.id}'">评审</a>
				  	  </c:if>
				  	  <c:if test="${payment.status==8&&userRoleId==17}">
				  	  	<a href="javascript:window.location ='${ctx}/auditBuyPaymentInit.do?id=${payment.id}'">评审</a>
				  	  </c:if>
				  	  <c:if test="${(payment.status==2&&userRoleId !=10)||(payment.status==4&&userRoleId !=11)||(payment.status==6&&userRoleId!=16)||(payment.status==8&&userRoleId!=17)||(payment.status!=2&&payment.status!=4&&payment.status!=6&&payment.status!=8)}">
				  	  	评审
				  	  </c:if>
				  </c:if>
				</td>
			</tr>
		</logic:iterate>
	</table>
<br />
        <table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table">

        	<tr>
        	  <td align="left">
        	  <c:if test="${userRoleId ==8}"><a href="${ctx}/buyPaymentAddInit.do"><img src="${ctx}/images/btnFKSQ.gif" width="88" height="20" /></a></c:if>
        	  </td>
           	  <td align="right" ><%@ include file="/jsp/common/newPage.jsp"%></td>
       	  </tr>
        </table>
    </td>
    <td >&nbsp;</td>
  </tr>
</table>
 
</body>
</html>
