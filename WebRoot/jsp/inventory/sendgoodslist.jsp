<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>发货管理</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
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
						 $(this).click( function(){
			              if( $(this).hasClass("rowselected") ){
			                $(this).removeClass("rowselected");
			              }else{
			                $(this).addClass("rowselected");
			              }
			            });
					});
				}

				if($('#orderType').val()!=""){   
					$.each($('#orderType').val().split(","),function(i,str){  
						if(str==1){
							$('#checkbox1').attr('checked',"true");
						}
						if(str==2){
							$('#checkbox2').attr('checked',"true");
						}
						if(str==3){
							$('#checkbox3').attr('checked',"true");
						}
						if(str==4){
							$('#checkbox4').attr('checked',"true");
						} 

					}); 
				}

				if("${errorMsg}" != ""){
					alert("${errorMsg}");
				}

			});

			//提交验证
			function checkSubmit(){
				trimText();
				var str = "";
				if($('#checkbox1').attr('checked')==true){
					str = "1";
				}
				if($('#checkbox2').attr('checked')==true){
					if(str == ""){
						str = "2";
					}else{
						str = str + ",2";
					}
					
				}
				if($('#checkbox3').attr('checked')==true){
					if(str == ""){
						str = "3";
					}else{
						str = str + ",3";
					}
				}
				if($('#checkbox4').attr('checked')==true){
					if(str == ""){
						str = "4";
					}else{
						str = str + ",4";
					}
				}
				$('#orderType').val(str);

				$('#frmConList').submit(); 
			}
		function trimText(){
	        var fom = document.forms[0];
	        for(var i=0;i<fom.elements.length;i++){
	            if(fom.elements[i].type == 'text'){
	               fom.elements[i].value = $.trim(fom.elements[i].value);
	            }
	        }
	    }
		</script> 
	</head>

<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left" width="16px" title=""><div class=ellipsis_div style="width:16px;">&nbsp;</div></td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 库存管理 &gt;&gt 发货管理</td>
    <td class="ye_header_right" width="16px" title=""><div class=ellipsis_div style="width:16px;">&nbsp;</div></td>
  </tr>
   <tr>
    <td >&nbsp;</td>
    <td align="center"><br />
    	<div class="mo_wp">
          <div style="display: ; " class="mo_con" >
		    <form action="stockOrder.do" id="frmConList"> 
			<input type="hidden" name="queryPara.orderType" id="orderType" value="${queryPara.orderType}"/>
            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
              <tr>
                    <td class="td_01">发货单号</td>
                    <td class="td_02" >
						<input type="text" name="queryPara.orderId" value="${queryPara.orderId}" style="width:120px;" />
					</td>
                    <td class="td_01 STYLE1">库房名称</td>
                    <td class="td_02" >
						<html:select property="queryPara.stockroomId" value="${queryPara.stockroomId}" style=" width:264px">
							<html:option value="">--请选择--</html:option> 
							<html:options collection="stockroomList" property="id" labelProperty="name"/>
						</html:select>
					</td>
                  </tr>
                  <tr>
                    <td class="td_01">产品分类名称 </td>
                    <td class="td_02">
						<html:select styleId="productTypeSelect" property="queryPara.productTypeId" value="${queryPara.productTypeId}" style=" width:126px">
							<html:option value="">--请选择--</html:option> 
							<html:options collection="productTypeList" property="id" labelProperty="name"/>
						</html:select>
					</td>
                    <td class="td_01">客户名称</td>
                    <td class="td_02">
						<input type="text" name="queryPara.customerName" value="${queryPara.customerName}" style="width:240px;" />
					</td>
                  </tr> 
                  <tr>
                  	<td class="td_01">货物接收单位名称</td>
                  	<td class="td_02">
						<input type="text" name="queryPara.customerAddressName" value="${queryPara.customerAddressName}" style="width:240px;" />
					</td>
					<td class="td_01">货运方式</td>
                  	<td class="td_02">
						<html:select property="queryPara.transportWay" value="${queryPara.transportWay}" style=" width:126px">
							<html:option value="">--请选择--</html:option> 
							<html:option value="0">无指定</html:option> 
							<html:option value="1">自提</html:option>
							<html:option value="2">快递</html:option>
							<html:option value="3">汽运</html:option> 
							<html:option value="4">铁运</html:option>
							<html:option value="5">航空</html:option>
							<html:option value="6">海运</html:option> 
							<html:option value="7">中铁</html:option>
							<html:option value="8">市内送货</html:option>
							<html:option value="9">市内快递</html:option>
						</html:select>
					</td>
                  </tr>
				  <tr>
					<td class="td_01">发货起始日期</td>
					<td class="td_02">
						<input type="text" name="queryPara.starttime" maxlength="12" style="width:120px;" onfocus="calendar()" readonly="readonly" value="${queryPara.starttime}"/>
					</td>
					<td class="td_01">发货终止日期</td>
					<td class="td_02">
						<input type="text" name="queryPara.endtime" maxlength="12" style="width:120px;" onfocus="calendar()" readonly="readonly" value="${queryPara.endtime}"/>
					</td>
				  </tr>
                  <tr> 
                  	<td class="td_01">人员名称</td>
                  	<td class="td_02">
						<input type="text" name="queryPara.userName" value="${queryPara.userName}" style="width:120px;" />
					</td>
					<td class="td_01">发货单状态</td>
                  	<td class="td_02">
						<html:select property="queryPara.changeStatus" value="${queryPara.changeStatus}" style=" width:126px">
							<html:option value="">--请选择--</html:option> 
							<html:option value="0">待发货</html:option> 
							<html:option value="1">发货中</html:option>
							<html:option value="2">发货异常</html:option>
							<html:option value="3">发货成功</html:option>
						</html:select>
					</td>
                  </tr> 
                  <tr>
                    <td class="td_01">单据类型</td>
                    <td colspan="3" class="td_02">
						<input type="checkbox" id="checkbox1" name="queryPara.changeStatusArr"/>
                    	发货单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    	<input type="checkbox" id="checkbox2" name="queryPara.changeStatusArr"/>
                    	返厂单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    	<input type="checkbox" id="checkbox3" name="queryPara.changeStatusArr"/>
                    	移库单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    	<input type="checkbox" id="checkbox4" name="queryPara.changeStatusArr"/>
                    	样品借出单
					</td>
                  </tr>
				  <tr>
					<td colspan="4" align="center" style="height:30px;">
						<a href="javascript:checkSubmit();"><img src="${ctx}/images/btn_JianSuo.gif" /></a>
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
    <br/>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table">
      <tr>
        <th nowrap="nowrap">&nbsp;&nbsp;单据类型&nbsp;&nbsp;</th>
        <th nowrap="nowrap">发货单号</th>
        <th nowrap="nowrap">库房名称</th>
        <th nowrap="nowrap">&nbsp;产品分类名称&nbsp;</th>
        <th nowrap="nowrap">客户名称</th>

        <th nowrap="nowrap">货物接收单位名称</th>
        <th nowrap="nowrap">发货地址</th>
        <th nowrap="nowrap">&nbsp;货运方式&nbsp;</th>
        <th nowrap="nowrap">&nbsp;&nbsp;申请日期&nbsp;&nbsp;</th>
        <th nowrap="nowrap">&nbsp;要求发货日期&nbsp;</th>

        <th nowrap="nowrap">&nbsp;&nbsp;发货日期&nbsp;&nbsp;</th>
        <th nowrap="nowrap">&nbsp;人员名称&nbsp;</th>
        <th nowrap="nowrap">&nbsp;发货单状态&nbsp;</th>
        <th nowrap="nowrap">&nbsp;操作&nbsp;</th>
      </tr>
	  <logic:iterate id="order" name="orderList">
	  <tr>
		<td nowrap="nowrap" width="72px" height="18px">
			<c:if test="${order.orderType==1}">发货单&nbsp;</c:if>
			<c:if test="${order.orderType==2}">返厂单&nbsp;</c:if>
			<c:if test="${order.orderType==3}">移库单&nbsp;</c:if>
			<c:if test="${order.orderType==4}">样品借出单&nbsp;</c:if>
		</td>
		<td nowrap="nowrap" width="120px">${order.orderId}&nbsp;</td>
		<td nowrap="nowrap" width="120px">${order.stockroomName}&nbsp;</td>
		<td nowrap="nowrap" width="78px">${order.productType}&nbsp;</td>
		<td nowrap="nowrap" width="120px" title="${order.customerName}"><div class="ellipsis_div" style="width:120px;">${order.customerName}&nbsp;</div></td>

		<td nowrap="nowrap" width="120px" title="${order.customerAddressName}"><div class="ellipsis_div" style="width:120px;">${order.customerAddressName}&nbsp;</div></td>
		<td nowrap="nowrap" title="${order.customerAddress}"><div class="ellipsis_div" style="width:120px;">${order.customerAddress}&nbsp;&nbsp;</td>
		<td nowrap="nowrap" width="60px">
			<c:if test="${order.transportWay==0}">无指定&nbsp;</c:if>
			<c:if test="${order.transportWay==1}">自提&nbsp;</c:if>
			<c:if test="${order.transportWay==2}">快递&nbsp;</c:if>
			<c:if test="${order.transportWay==3}">汽运&nbsp;</c:if>
			<c:if test="${order.transportWay==4}">铁运&nbsp;</c:if>
			<c:if test="${order.transportWay==5}">航空&nbsp;</c:if>
			<c:if test="${order.transportWay==6}">海运&nbsp;</c:if>
			<c:if test="${order.transportWay==7}">中铁&nbsp;</c:if>
			<c:if test="${order.transportWay==8}">市内送货&nbsp;</c:if>
			<c:if test="${order.transportWay==9}">市内快递&nbsp;</c:if> 
		</td>
		<td nowrap="nowrap" width="73px">${order.date}&nbsp;</td>
		<td nowrap="nowrap" width="77px">${order.requestDate}&nbsp;</td>

		<td nowrap="nowrap" width="73px">${order.sendDate}&nbsp;</td>
		<td nowrap="nowrap" width="56px">${order.userName}&nbsp;</td>
		<td nowrap="nowrap" width="66px">
			<c:if test="${order.changeStatus==0}">待发货&nbsp;</c:if>
			<c:if test="${order.changeStatus==1}">发货中&nbsp;</c:if>
			<c:if test="${order.changeStatus==2}">发货异常&nbsp;</c:if>
			<c:if test="${order.changeStatus==3}">发货成功&nbsp;</c:if> 
		</td>
		<td nowrap="nowrap" > 
			<c:if test="${order.orderType==1}"> 
				<a href="${ctx}/getStockSendGoodsView.do?id=${order.orderId}">查看</a>
				<c:if test="${roleId==12}">
					<c:choose>
						<c:when test="${order.changeStatus==0||order.changeStatus==1}"> 
							<a href="${ctx}/getStockSendGoodsView.do?id=${order.orderId}&audit=true">发货异常</a>
						</c:when>
						<c:otherwise>
							发货异常
						</c:otherwise>
					</c:choose> 
				</c:if>
			</c:if>
			<c:if test="${order.orderType==2}">
				<a href="${ctx}/getStockBuyBackGoodsView.do?id=${order.orderId}">查看</a>
				<c:if test="${roleId==12}">
					<c:choose>
						<c:when test="${order.changeStatus==0||order.changeStatus==1}"> 
							<a href="${ctx}/getStockBuyBackGoodsView.do?id=${order.orderId}&audit=true">发货异常</a>
						</c:when>
						<c:otherwise>
							发货异常
						</c:otherwise>
					</c:choose> 
				</c:if> 
			</c:if>
			<c:if test="${order.orderType==3}">
				<a href="${ctx}/getStockMoveView.do?id=${order.orderId}">查看</a>
				<c:if test="${roleId==12}">
					<c:choose>
						<c:when test="${order.changeStatus==0||order.changeStatus==1}"> 
							<a href="${ctx}/getStockMoveView.do?id=${order.orderId}&audit=true">发货异常</a>
						</c:when>
						<c:otherwise>
							发货异常
						</c:otherwise>
					</c:choose> 
				</c:if> 
			</c:if>
			<c:if test="${order.orderType==4}">
				<a href="${ctx}/getStockSampleView.do?id=${order.orderId}">查看</a>
				<c:if test="${roleId==12}">
					<c:choose>
						<c:when test="${order.changeStatus==0||order.changeStatus==1}"> 
							<a href="${ctx}/getStockSampleView.do?id=${order.orderId}&audit=true">发货异常</a>
						</c:when>
						<c:otherwise>
							发货异常
						</c:otherwise>
					</c:choose> 
				</c:if> 
			</c:if>
			&nbsp;
		</td>
	  </tr>
      </logic:iterate>
    </table><br />
        <table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table">
        	<tr>
           	  <td align="right" ><%@ include file="/jsp/common/newPage.jsp"%></td>
            </tr>
        </table>    </td>
    <td >&nbsp;</td>
  </tr>
</table>
 
</body>
</html>
