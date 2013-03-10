<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>销售退货管理</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
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
			$(function(){ 
				//服务器端消息
				var err = "${err}";		
				if (err != "") {
					alert(err);
				}
			});
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
				
				$("#btnJianSuo").click(function(){
					trimText();
					$('#init').val('false');
					$("#returnGoodsListForm").submit();
				});
			});
		function deleteBackGoods(backGoodsId){
			if(confirm("是否确认删除！")){
				window.location = '${ctx}/deleteReturnGoods.do?returnGoodsId='+backGoodsId;
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
</script>
</head>
<body>
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 销售管理 &gt;&gt 销售退货管理</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td align="center"><br />
      <div class="mo_wp">
        <div style="display: ; " class="mo_con" >
        <form action="saleReturns.do" id = "returnGoodsListForm" name="returnGoodsListForm" > 
        	<input type="hidden" name="returnGoodsQuery.init" id="init" value="${returnGoodsListForm.returnGoodsQuery.init}" />
          <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
            <tr>
              <td class="td_01" width="20%" >退货单号</td>
              <td class="td_02" width="30%"><input type="text" name="returnGoodsQuery.id" value="${returnGoodsListForm.returnGoodsQuery.id}" style="width:120px;" /></td>
              <td class="td_01" width="20%">发货单号</td>
              <td class="td_02" width="30%"><input type="text" name="returnGoodsQuery.sendGoodsId" id="name6" style="width:120px;" value="${returnGoodsListForm.returnGoodsQuery.sendGoodsId}"  /></td>
            </tr>
            <tr>
              <td class="td_01" width="20%">库房名称</td>
              <td class="td_02" width="30%">
              	<html:select property="returnGoodsQuery.stockRoomId" value="${returnGoodsListForm.returnGoodsQuery.stockRoomId}" style=" width:126px">
					<html:option value="">--请选择--</html:option> 
					<html:options collection="stockList" property="id" labelProperty="name"/>
				</html:select>
              <td class="td_01" width="20%">产品合同号</td>
              <td class="td_02" width="30%"><input type="text" name="returnGoodsQuery.productContractCode" id="name8" style="width:120px;" value="${returnGoodsListForm.returnGoodsQuery.productContractCode}" /></td>
            </tr>
            <tr>
              <td class="td_01" width="20%">公司合同号</td>
              <td class="td_02" width="30%"><input type="text" name="returnGoodsQuery.companyContarctCode" id="name4" style="width:120px;" value="${returnGoodsListForm.returnGoodsQuery.companyContarctCode}" /></td>
              <td class="td_01" width="20%">产品分类名称</td>
              <td class="td_02" width="30%">
              	<html:select property="returnGoodsQuery.productTypeId" value="${returnGoodsListForm.returnGoodsQuery.productTypeId}" style=" width:126px">
					<html:option value="">--请选择--</html:option> 
					<html:options collection="productList" property="id" labelProperty="name"/>
				</html:select>
            </tr>
            <tr>
              <td class="td_01" width="20%">客户名称</td>
              <td class="td_02" width="30%"><input type="text" name="returnGoodsQuery.customerName" id="name9" style="width:120px;" value="${returnGoodsListForm.returnGoodsQuery.customerName}" /></td>
              <td class="td_01" width="20%">人员名称</td>
              <td class="td_02" width="30%"><input type="text" name="returnGoodsQuery.userName" id="name3" style="width:120px;" value="${returnGoodsListForm.returnGoodsQuery.userName}" /></td>
            </tr>
            <tr>
              <td class="td_01" width="20%">申请起始日期</td>
              <td class="td_02" width="30%">
              	<input type="text" name="returnGoodsQuery.startDate" id="returnGoodsQuery.startDate" style="width:120px;" onfocus="calendar()"
									readonly="readonly" value="${returnGoodsListForm.returnGoodsQuery.startDate}" />
			  </td>
              <td class="td_01" width="20%">申请终止日期</td>
              <td class="td_02" width="30%">
				<input type="text" name="returnGoodsQuery.endDate" id="returnGoodsQuery.endDate" style="width:120px;" onfocus="calendar()"
									readonly="readonly" value="${returnGoodsListForm.returnGoodsQuery.endDate}" />
			  </td>
            </tr>
            <tr>
              <td class="td_01" width="20%">退货起始日期</td>
              <td class="td_02" width="30%">
				<input type="text" name="returnGoodsQuery.startSBCDate" id="returnGoodsQuery.startSBCDate" style="width:120px;" onfocus="calendar()"
									readonly="readonly" value="${returnGoodsListForm.returnGoodsQuery.startSBCDate}" />
			  </td>
              <td class="td_01" width="20%">退货终止日期</td>
              <td class="td_02" width="30%">
				<input type="text" name="returnGoodsQuery.endSBCDate" id="returnGoodsQuery.endSBCDate" style="width:120px;" onfocus="calendar()"
									readonly="readonly" value="${returnGoodsListForm.returnGoodsQuery.endSBCDate}" />
			  </td>
            </tr>
            <tr>
              <td class="td_01" width="20%">退货单状态</td>
              <td class="td_02" width="30%">
              	<html:select property="returnGoodsQuery.status" value="${returnGoodsListForm.returnGoodsQuery.status}" style="width:126px">
				  <html:option value="">--请选择--</html:option> 
				  <html:option value="1">未提交</html:option>
                  <html:option value="2">销售总监待评审</html:option>
                  <html:option value="3">销售总监未通过</html:option>
                  <html:option value="4">运营总监待评审</html:option>
                  <html:option value="5">运营总监未通过</html:option>
                  <html:option value="6">待核对</html:option>
                  <html:option value="7">核对未通过</html:option>
                  <html:option value="8">入库成功</html:option>
				</html:select>
              <td class="td_01" width="20%">&nbsp;</td>
              <td class="td_02" width="30%">&nbsp;</td>
            </tr>
            <tr>
              <td colspan="4" align="center" style="height:30px;">
              	<img id="btnJianSuo" src="${ctx}/images/btn_JianSuo.gif" />
              </td>
            </tr>
          </table>
          </form>
        </div>
        <div class="mo_title" onclick="fMainListToggle(this)">
          <div  style="text-align:center"><img id="imgShang" src="${ctx}/images/shang_sj.png" /></div>
        </div>
      </div></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td align="center">
      <div style="width:100%; text-align:right">单位：元</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table">
        <tr>
          <th nowrap="nowrap">退货单号</th>
          <th nowrap="nowrap">发货单号</th>
          <th nowrap="nowrap">库房名称</th>
          <th nowrap="nowrap">产品合同号</th>
          <th nowrap="nowrap">公司合同号</th>
          <th nowrap="nowrap">产品分类名称</th>
          <th nowrap="nowrap" >客户名称</th>
          <th nowrap="nowrap">退货金额</th>
          <th nowrap="nowrap">人员名称</th>
          <th nowrap="nowrap">申请日期</th>
          <th nowrap="nowrap">退货日期</th>
          <th nowrap="nowrap">退货单状态</th>
          <th nowrap="nowrap">&nbsp;操作&nbsp;</th>
        </tr>
        <logic:present name="list">
			<logic:iterate id="returnGoods" name="list" >
        <tr>
          <td nowrap="nowrap" width="120px" height="18px">${returnGoods.id}&nbsp;</td>
          <td nowrap="nowrap" width="120px">${returnGoods.sendGoodsId}&nbsp;</td>
          <td nowrap="nowrap" title="${returnGoods.stockRoomName}"><div class=ellipsis_div style="width:90px;">${returnGoods.stockRoomName}&nbsp;</div></td>
          <td nowrap="nowrap" title="${returnGoods.productContractCode}"><div class=ellipsis_div style="width:73px;">${returnGoods.productContractCode}&nbsp;</div></td>
          <td nowrap="nowrap" title="${returnGoods.companyContarctCode}"><div class=ellipsis_div style="width:96px;">${returnGoods.companyContarctCode}&nbsp;</div></td>
          <td width="72px" nowrap="nowrap">${returnGoods.productTypeName}&nbsp;</td>
          <td width="120px" nowrap="nowrap" title="${returnGoods.customerName}">
         	 <div class="ellipsis_div" style="width:120px;">${returnGoods.customerName}&nbsp;</div>
          </td>
          <td width="86px" nowrap="nowrap" style=" text-align:right;" title="<fmt:formatNumber value="${returnGoods.money}" pattern="#,##0.00#"/>"><div class=ellipsis_div style="width:86px;"><fmt:formatNumber value="${returnGoods.money}" pattern="#,##0.00#"/>&nbsp;</div></td>
          <td width="65px" nowrap="nowrap" >${returnGoods.userName}&nbsp;</td>
          <td width="73px" nowrap="nowrap" >${returnGoods.date}&nbsp;</td>
          <td width="73px" nowrap="nowrap">${returnGoods.sbcDate}&nbsp;</td>
          <td width="95px" nowrap="nowrap" ><div class=ellipsis_div style="width:96px;">
          	<c:if test="${returnGoods.status==1}">
          	未提交
          	</c:if>
          	<c:if test="${returnGoods.status==2}">
          	销售总监待评审
          	</c:if>
          	<c:if test="${returnGoods.status==3}">
          	销售总监未通过
          	</c:if>
          	<c:if test="${returnGoods.status==4}">
          	运营总监待评审
          	</c:if>
          	<c:if test="${returnGoods.status==5}">
          	运营总监未通过
          	</c:if>
          	<c:if test="${returnGoods.status==6}">
          	待核对
          	</c:if>
          	<c:if test="${returnGoods.status==7}">
          	核对未通过
          	</c:if>
          	<c:if test="${returnGoods.status==8}">
          	入库成功
          	</c:if>
          	&nbsp;</div>
          </td>
          <td nowrap="nowrap">
          <a href="${ctx}/viewReturnGoodsSale.do?returnGoodsId=${returnGoods.id}&sendGoodsId=${returnGoods.sendGoodsId}">查看</a> 
          <c:if test="${roleId==4}">
	          <c:if test="${returnGoods.status==1||returnGoods.status==3||returnGoods.status==5||returnGoods.status==7}">
	          	<a href="${ctx}/modifyReturnGoodsSaleInit.do?returnGoodsId=${returnGoods.id}&sendGoodsId=${returnGoods.sendGoodsId}">修改</a> 
	          </c:if>
	          <c:if test="${!(returnGoods.status==1||returnGoods.status==3||returnGoods.status==5||returnGoods.status==7)}">
	          	修改
	          </c:if>
          </c:if>
      	  <c:if test="${roleId==4}">
      	  	<c:if test="${returnGoods.status==1||returnGoods.status==3||returnGoods.status==5||returnGoods.status==7}">
          		<a href="javascript:deleteBackGoods('${returnGoods.id}');">删除</a>
          	</c:if>
          	<c:if test="${!(returnGoods.status==1||returnGoods.status==3||returnGoods.status==5||returnGoods.status==7)}">
	          	删除
            </c:if>
          </c:if>
          	<c:if test="${roleId==5||roleId==17}">
	          	<c:if test="${(roleId==5&&returnGoods.status==2)||(roleId==17&&returnGoods.status==4)}">
	          		<a href="${ctx}/auditReturnGoodsSaleInit.do?returnGoodsId=${returnGoods.id}&sendGoodsId=${returnGoods.sendGoodsId}">评审</a>
	  		  	</c:if>
	  		  	<c:if test="${!(roleId==5&&returnGoods.status==2)&&!(roleId==17&&returnGoods.status==4)}">
	          		评审
	  		  	</c:if>
  		  	</c:if>
  		  </td>
        </tr>
        	</logic:iterate>
        </logic:present>
			
      </table>
      <br />
      <table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table">
        <tr>
          <td align="right"><%@ include file="/jsp/common/newPage.jsp"%></td>
        </tr>
      </table></td>
    <td >&nbsp;</td>
  </tr>
</table>
</body>
</html>
