<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>装箱单管理</title>
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
<!--
	$(document).ready(function(){
		$('#JianSuo').click(function(){
			trimText();
			$('#frmConList').submit();
		});	
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

		if("${errorMsg}" != ""){
			alert("${errorMsg}");
		}
	});

	function deleteBox(id){
		if(confirm("是否确认删除？")){
			window.location = 'boxDel.do?addPara.id='+id;
		}
	}

	function confirmBox(id){
		if(confirm("是否确认提交？")){
			window.location = 'boxConfirm.do?addPara.id='+id;
		}
	}
	function boxTbcPay(id){
		if(confirm("是否确认提交？")){
			window.location = 'boxTbcPay.do?addPara.id='+id;
		}
	}
	
	function trimText(){
	        var fom = document.forms[0];
	        for(var i=0;i<fom.elements.length;i++){
	            if(fom.elements[i].type == 'text'){
	               fom.elements[i].value = $.trim(fom.elements[i].value);
	            }
	        }
	    }
//-->
</script> 
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left" width="16px" title=""><div class=ellipsis_div style="width:16px;">&nbsp;</div></td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 库存管理 &gt;&gt; 装箱单管理</td>
    <td class="ye_header_right" width="16px" title=""><div class=ellipsis_div style="width:16px;">&nbsp;</div></td>
  </tr>
   <tr>
    <td >&nbsp;</td>
    <td align="center"><br />
    	<div class="mo_wp">
          <div style="display: ; " class="mo_con" >
		    <form action="boxList.do" id="frmConList"> 
            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
				  <tr>
                    <td class="td_01" width="20%">装箱单号</td>
                    <td class="td_02" width="30%"><input type="text" name="queryPara.id" value="${queryPara.id}" style="width:120px;" /></td>
                    <td class="td_01" width="20%">运单号</td>
                    <td class="td_02" width="30%"><input type="text" name="queryPara.no" value="${queryPara.no}" style="width:120px;" /></td>
                  </tr>
				  <tr> 
                  	<td class="td_01">库房名称</td>
                  	<td class="td_02">
						<html:select property="queryPara.stockroomId" value="${queryPara.stockroomId}" style=" width:264px">
							<html:option value="">--请选择--</html:option> 
							<html:options collection="stockroomList" property="id" labelProperty="name"/>
						</html:select>
					</td>
					<td class="td_01">产品分类名称</td>
                  	<td class="td_02">
						<html:select styleId="productTypeSelect" property="queryPara.productTypeId" value="${queryPara.productTypeId}" style=" width:126px">
							<html:option value="">--请选择--</html:option> 
							<html:options collection="productTypeList" property="id" labelProperty="name"/>
						</html:select>
					</td>
                  </tr> 
                  <tr>
                    <td class="td_01">物流公司名称 </td>
                    <td class="td_02"><input type="text" name="queryPara.logisticsName" value="${queryPara.logisticsName}" style="width:240px;" /></td>
                    <td class="td_01">客户名称</td>
                    <td class="td_02"><input type="text" name="queryPara.customerName" value="${queryPara.customerName}" style="width:240px;" /></td>
                  </tr> 
                  <tr>
                  	<td class="td_01">发货起始日期</td>
                  	<td class="td_02"><input type="text" name="queryPara.sendDateStarttime" maxlength="12" style="width:120px;" onfocus="calendar()" readonly="readonly" value="${queryPara.sendDateStarttime}"/></td>
                  	<td class="td_01">发货终止日期</td>
                  	<td class="td_02"><input type="text" name="queryPara.sendDateEndtime" maxlength="12" style="width:120px;" onfocus="calendar()" readonly="readonly" value="${queryPara.sendDateEndtime}"/></td>
                  	</tr>
                  <tr>
                  	<td class="td_01">预计到货起始日期</td>
                  	<td class="td_02"><input type="text" name="queryPara.estimateDateStarttime" maxlength="12" style="width:120px;" onfocus="calendar()" readonly="readonly" value="${queryPara.estimateDateStarttime}"/></td>
                  	<td class="td_01">预计到货终止日期 </td>
                  	<td class="td_02"><input type="text" name="queryPara.estimateDateEndtime" maxlength="12" style="width:120px;" onfocus="calendar()" readonly="readonly" value="${queryPara.estimateDateEndtime}"/></td>
                  	</tr>
                  <tr>
                  	<td class="td_01">到货起始日期</td>
                  	<td class="td_02"><input type="text" name="queryPara.arrivalDateStarttime" maxlength="12" style="width:120px;" onfocus="calendar()" readonly="readonly" value="${queryPara.arrivalDateStarttime}"/></td>
                  	<td class="td_01">到货终止日期</td>
                  	<td class="td_02"><input type="text" name="queryPara.arrivalDateEndtime" maxlength="12" style="width:120px;" onfocus="calendar()" readonly="readonly" value="${queryPara.arrivalDateEndtime}"/></td>
                  	</tr>
                  <tr>
                  	<td class="td_01">签收起始日期</td>

                  	<td class="td_02"><input type="text" name="queryPara.signoffDateStarttime" maxlength="12" style="width:120px;" onfocus="calendar()" readonly="readonly" value="${queryPara.signoffDateStarttime}"/></td>
                  	<td class="td_01">签收终止日期</td>
                  	<td class="td_02"><input type="text" name="queryPara.signoffDateEndtime" maxlength="12" style="width:120px;" onfocus="calendar()" readonly="readonly" value="${queryPara.signoffDateEndtime}"/></td>
                  	</tr>
                  <tr>
                    <td class="td_01">回执确认起始日期</td>
                    <td class="td_02"><input type="text" name="queryPara.confirmDateStarttime" maxlength="12" style="width:120px;" onfocus="calendar()" readonly="readonly" value="${queryPara.confirmDateStarttime}"/></td>
                    <td class="td_01">回执确认终止日期</td>
                    <td class="td_02"><input type="text" name="queryPara.confirmDateEndtime" maxlength="12" style="width:120px;" onfocus="calendar()" readonly="readonly" value="${queryPara.confirmDateEndtime}"/></td>
                  </tr>

				   <tr>
                  	<td class="td_01">人员名称</td>
                  	<td class="td_02"><input type="text" name="queryPara.userName"  value="${queryPara.userName}" style="width:120px;" /></td>
                  	<td class="td_01">装箱单状态</td>
                  	<td class="td_02">
						<html:select property="queryPara.status" value="${queryPara.status}" style=" width:126px">
							<html:option value="">--请选择--</html:option> 
							<html:option value="1">装箱中</html:option>
							<html:option value="2">在途</html:option>
							<html:option value="3">到达</html:option>
							<html:option value="4">签收</html:option> 
							<html:option value="5">回执确认</html:option>
							<html:option value="6">已付款</html:option> 
						</html:select>
					</td>
                  	</tr>

					<tr>
                  	<td class="td_01">发货单号</td>
                  	<td class="td_02"><input type="text" name="queryPara.orderId"  value="${queryPara.orderId}" style="width:120px;" />&nbsp;</td>
                  	<td class="td_01">&nbsp;</td>
                  	<td class="td_02">&nbsp;</td>
                  	</tr>
              <tr>
                <td colspan="4" align="center" style="height:30px;">
                	<img id="JianSuo" src="${ctx}/images/btn_JianSuo.gif"/>
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
        <th nowrap="nowrap">装箱单号</th>
        <th nowrap="nowrap" >&nbsp;&nbsp;&nbsp;运单号&nbsp;&nbsp;&nbsp;</th>
        <th nowrap="nowrap">库房名称</th>
        <th nowrap="nowrap">&nbsp;产品分类名称&nbsp;</th>
        <th nowrap="nowrap">物流公司名称</th>

        <th nowrap="nowrap">客户名称</th>
        <th nowrap="nowrap">货物接收单位名称</th>
        <th nowrap="nowrap">发货地址</th>
        <th nowrap="nowrap">&nbsp;装箱件数&nbsp;</th>
        <th nowrap="nowrap">&nbsp;要求货运方式&nbsp;</th>

        <th nowrap="nowrap">&nbsp;实际货运方式&nbsp;</th>
        <th nowrap="nowrap">&nbsp;&nbsp;发货日期&nbsp;&nbsp;</th>
        <th nowrap="nowrap">&nbsp;预计到货日期&nbsp;</th>
        <th nowrap="nowrap">&nbsp;&nbsp;到货日期&nbsp;&nbsp;</th>
        <th nowrap="nowrap">&nbsp;&nbsp;签收日期&nbsp;&nbsp;</th>

        <th nowrap="nowrap">&nbsp;回执确认日期&nbsp;</th>
		<th nowrap="nowrap">&nbsp;人员名称&nbsp;</th>
		<th nowrap="nowrap">&nbsp;装箱单状态&nbsp;</th>

		<th nowrap="nowrap">&nbsp;是否确认&nbsp;</th>
		<th nowrap="nowrap">&nbsp;确认时间&nbsp;</th>

        <th nowrap="nowrap">&nbsp;操作&nbsp;</th>
      </tr>
	  <logic:iterate id="box" name="boxList">
		  <tr>
			<td nowrap="nowrap" width="100px" height="18">${box.id}&nbsp;</td>
			<td nowrap="nowrap" width="96px">${box.no}&nbsp;</td>
			<td nowrap="nowrap" width="120px">${box.stockroomName}&nbsp;</td>
			<td nowrap="nowrap" width="80px">${box.productTypeName}&nbsp;</td>
			<td nowrap="nowrap" width="96px" title="${box.logisticsName}"><div class="ellipsis_div" style="width:96px;">${box.logisticsName}&nbsp;</div></td>

			<td nowrap="nowrap" width="96px" title="${box.customerName}"><div class="ellipsis_div" style="width:96px;">${box.customerName}&nbsp;</div></td>
			<td nowrap="nowrap" width="120px" title="${box.customerAddressName}"><div class="ellipsis_div" style="width:120px;">${box.customerAddressName}&nbsp;</div></td>
			<td nowrap="nowrap" width="120px" title="${box.customerAddress}"><div class="ellipsis_div" style="width:120px;">${box.customerAddress}&nbsp;</div></td>
			<td nowrap="nowrap" width="60px" style="text-align:right;">${box.count}&nbsp;</td>
			<td nowrap="nowrap" width="60px">
				<c:if test="${box.customerTransportWay==0}">无指定</c:if>
				<c:if test="${box.customerTransportWay==1}">自提</c:if>
				<c:if test="${box.customerTransportWay==2}">快递</c:if>
				<c:if test="${box.customerTransportWay==3}">汽运</c:if>
				<c:if test="${box.customerTransportWay==4}">铁运</c:if>

				<c:if test="${box.customerTransportWay==5}">航空</c:if>
				<c:if test="${box.customerTransportWay==6}">海运</c:if>
				<c:if test="${box.customerTransportWay==7}">中铁</c:if>
				 
				<c:if test="${box.customerTransportWay==8}">市内送货</c:if>
				<c:if test="${box.customerTransportWay==9}">市内快递</c:if>&nbsp;
			</td>

			<td nowrap="nowrap" width="60px">
				<c:if test="${box.transportWay==0}">无指定</c:if>
				<c:if test="${box.transportWay==1}">自提</c:if>
				<c:if test="${box.transportWay==2}">快递</c:if>
				<c:if test="${box.transportWay==3}">汽运</c:if>
				<c:if test="${box.transportWay==4}">铁运</c:if>

				<c:if test="${box.transportWay==5}">航空</c:if>
				<c:if test="${box.transportWay==6}">海运</c:if>
				<c:if test="${box.transportWay==7}">中铁</c:if>
				 
				<c:if test="${box.transportWay==8}">市内送货</c:if>
				<c:if test="${box.transportWay==9}">市内快递</c:if>&nbsp;
			</td>
			<td nowrap="nowrap" width="73px">${box.date}&nbsp;</td>
			<td nowrap="nowrap" width="77px">${box.estimateDate}&nbsp;</td>
			<td nowrap="nowrap" width="73px">${box.arrivalDate}&nbsp;</td>
			<td nowrap="nowrap" width="73px">${box.signoffDate}&nbsp;</td>

			<td nowrap="nowrap" width="77px">${box.confirmDate}&nbsp;</td>
			<td nowrap="nowrap" width="64px">${box.userName}&nbsp;</td>
			<td nowrap="nowrap" width="64px">
				<c:if test="${box.status==1}">装箱中</c:if>
				<c:if test="${box.status==2}">在途</c:if>
				<c:if test="${box.status==3}">到达</c:if>
				<c:if test="${box.status==4}">签收</c:if> 
				<c:if test="${box.status==5}">回执确认</c:if>
				<c:if test="${box.status==6}">已付款</c:if>
			</td>
			
			<td nowrap="nowrap" width="64px">
				<c:if test="${box.tbcFlag==1}">是</c:if>
				<c:if test="${box.tbcFlag==0}">否</c:if>
			</td>
			<td nowrap="nowrap" width="77px">${box.tbcdate}&nbsp;</td>
			

			<td nowrap="nowrap"> 
				<a href="${ctx}/getBoxView.do?addPara.id=${box.id}">查看</a> 
				<c:if test="${roleId==12}">
					<c:choose>
						<c:when test="${box.status==1}"> 
							<a href="${ctx}/addBoxInit.do?addPara.id=${box.id}">修改</a>
							<a href="javascript:deleteBox('${box.id}');">删除</a>
						</c:when>
						<c:otherwise>
							修改
							删除
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${(box.status==2||box.status==3||box.status==4)&&box.tbcFlag==0}"> 
							<a href="${ctx}/boxTbcInit.do?addPara.id=${box.id}">确认</a>
						</c:when>
						<c:otherwise>
							确认
						</c:otherwise>
					</c:choose> 
					<c:choose>
						<c:when test="${box.status==4&&box.tbcFlag==1}"> 
							<a href="${ctx}/boxConfirmInit.do?addPara.id=${box.id}">回执确认</a>
						</c:when>
						<c:otherwise>
							回执确认
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${box.status==5}"> 
							<a href="javascript:boxTbcPay('${box.id}');">已付款</a>
						</c:when>
						<c:otherwise>
							已付款
						</c:otherwise>
					</c:choose>
				</c:if>&nbsp;
			</td>
		  </tr>
	  </logic:iterate> 
    </table>
    <br />
        <table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table">
        	<tr>
				<td align="left" width="150px">
				<c:if test="${roleId==12}">
					<a href="${ctx}/addBoxInit.do"><img src="${ctx}/images/btnXJZXD.gif" width="102" height="20" /></a>
				</c:if>
				</td>
           	  <td align="right"><%@ include file="/jsp/common/newPage.jsp"%></td>
            </tr>
        </table>   
	 </td>
    <td >&nbsp;</td>
  </tr>
</table>
 
</body>
</html>
