<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>入库管理</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
		<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="${ctx}/js/messages_cn.js"></script>
        <script type="text/javascript" src="${ctx}/js/Calendar1.js"></script>
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
					});
				}
				$("#find").click(function(){
					$("#incomeStockListForm").submit();
				});
				
			var billtype='${requestScope.billType}';
		var billtypes=new Array();
				billtype=billtype.substring(0,billtype.lastIndexOf("@"));
				if(billtype!=null){
					billtypes=billtype.split("@");
					for(var i=0;i<billtypes.length;i++){
						switch(billtypes[i]){
							case '1':$("#billType1").attr("checked","true");break;
							case '2':$("#billType2").attr("checked","true");break;
							case '3':$("#billType3").attr("checked","true");break;
							case '4':$("#billType4").attr("checked","true");break;
						}
					}
				}				
			});
		//-->
		

		</script>
</head>

<body>
<c:if test="${param.statusError}">
	<script>alert("执行失败，此状态不能操作");</script>
</c:if>
<html:form action="getIncomeStockList" method="get" styleId="incomeStockListForm">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left" width="16px" title=""><div class=ellipsis_div style="width:16px;">&nbsp;</div></td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 库存管理 &gt;&gt; 入库管理</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
   <tr>
    <td >&nbsp;</td>
    <td align="center"><br />
    	<div class="mo_wp">
          <div style="display: ; " class="mo_con" >
            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
              <tr>
                <td class="td_01" width="20%">入库单号</td>
                <td class="td_02" width="30%">
                	<html:text property="incomeStockListDto.incomeStockId" style=" width:126px"></html:text>
                </td>
                <td class="td_01" width="20%">库房名称</td>
                <td class="td_02" width="30%">
                	<html:select property="incomeStockListDto.storeroomId">
                		<html:option value="">---请选择---</html:option>
                		<html:options collection="storeRoomList" property="id" labelProperty="name"/>
                	</html:select>
                </td>
              </tr>
              <tr>
                <td class="td_01">产品分类名称</td>
                <td class="td_02">
                	<html:select property="incomeStockListDto.productTypeId" style=" width:126px">
                		<html:option value="">---请选择---</html:option>
                		<html:options collection="productTypeList" property="id" labelProperty="name"/>
                	</html:select>
                </td>
				<td class="td_01">供货商名称</td>
                <td class="td_02"><html:text property="incomeStockListDto.supplierName" style=" width:240px"></html:text></td>
              </tr>
              <tr>
                <td class="td_01" width="20%">入库起始日期</td>
                <td class="td_02" width="30%">
                	<html:text property="incomeStockListDto.incomeDate" style=" width:126px"  onfocus="calendar()" readonly="readonly"></html:text>
                </td>
                <td class="td_01" width="20%">入库终止日期</td>
                <td class="td_02" width="30%">
                	<html:text property="incomeStockListDto.endIncomeDate" style=" width:126px"  onfocus="calendar()" readonly="readonly"></html:text>
                </td>
              </tr>
              <tr>
                <td class="td_01">人员名称</td>
                <td class="td_02"><html:text property="incomeStockListDto.userName" style=" width:126px"></html:text></td>
                <td class="td_01">入库单状态</td>
                <td class="td_02">
                	<html:select property="incomeStockListDto.status" style=" width:126px">
                		<html:option value="">---请选择---</html:option>
                		<html:option value="4">待核对</html:option>
                		<html:option value="5">核对未通过</html:option>
                		<html:option value="6">入库成功</html:option>
                	</html:select></td>
              </tr>
              <tr>
                <td class="td_01">单据类型</td>
                <td colspan="3" class="td_02">
				 <html:checkbox property="incomeStockListDto.billType" value="1" styleId="billType1"></html:checkbox>
				入库单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<html:checkbox property="incomeStockListDto.billType" value="2" styleId="billType2"></html:checkbox>
				销售退货单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<html:checkbox property="incomeStockListDto.billType" value="3" styleId="billType3"></html:checkbox>
				移库单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<html:checkbox property="incomeStockListDto.billType" value="4" styleId="billType4"></html:checkbox>
				样品归还单</td>
                </tr>
              <tr>
                <td colspan="4" align="center" style="height:30px;"><a href="#" id="find"><img src="${ctx}/images/btn_JianSuo.gif" /></a></td>
              </tr>
            </table>
          </div>
          <div class="mo_title" onclick="fMainListToggle(this)">
              <div  style="text-align:center">
              	<img id="imgShang" src="${ctx}/images/shang_sj.png" />
              </div>
          </div>
        </div>
    </td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;&nbsp;</td>
    <td align="center">
    <div style="width:100%; text-align:right">&nbsp;</div>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table">
      <tr>
        <th nowrap="nowrap">单据类型</th>
        <th nowrap="nowrap">入库单号</th>
        <th nowrap="nowrap">库房名称</th>
        <th nowrap="nowrap">产品分类</th>
        <th nowrap="nowrap">供货商名称</th>
        <th nowrap="nowrap" >申请日期</th>
        <th nowrap="nowrap" >入库日期</th>
        <th nowrap="nowrap" >人员名称</th>
        <th nowrap="nowrap" >入库单状态</th>
        <th nowrap="nowrap" width="66">&nbsp;操作&nbsp;</th>
      </tr>
      <logic:iterate id="incomeStockList" name="incomeStockList">
	      <tr>
	        <td nowrap="nowrap" width="80" height="18">
	        	<c:if test="${incomeStockList.billType==1 }">入库单</c:if>
	        	<c:if test="${incomeStockList.billType==2 }">销售退货单</c:if>
	        	<c:if test="${incomeStockList.billType==3 }">移库单</c:if>
	        	<c:if test="${incomeStockList.billType==4 }">样品归还单</c:if>
	        </td>
	        <td nowrap="nowrap" width="120">${incomeStockList.incomeStockId }&nbsp;</td>
	        <td nowrap="nowrap" width="120">${incomeStockList.storeroom }&nbsp;</td>
	        <td nowrap="nowrap" width="120">${incomeStockList.productTypeName }&nbsp;</td>
	        <td nowrap="nowrap" width="240">${incomeStockList.supplierName }&nbsp;</td>
	        <td nowrap="nowrap" width="120" >${incomeStockList.date }&nbsp;</td>
	        <td nowrap="nowrap" width="120" >${incomeStockList.incomeDate }&nbsp;</td>
	        <td nowrap="nowrap" width="96" >${incomeStockList.userName }&nbsp;</td>
	        <td nowrap="nowrap" width="80" >
	        	<c:if test="${incomeStockList.status==1 }">未提交	</c:if>
	        	<c:if test="${incomeStockList.status==2 }">采购主管待评审</c:if>
	        	<c:if test="${incomeStockList.status==3 }">采购主管未通过</c:if>
	        	<c:if test="${incomeStockList.status==4 }">待核对</c:if>
	        	<c:if test="${incomeStockList.status==5 }">核对未通过</c:if>
	        	<c:if test="${incomeStockList.status==6 }">入库成功</c:if>&nbsp;
	        </td>
	        <td nowrap="nowrap">
	        	<a href="${ctx}/incomeStoreRoomOfView.do?billType=${incomeStockList.billType}&id=${incomeStockList.incomeStockId }&sendGoodId=${incomeStockList.sendGoodsId}">查看</a>
	        	<c:choose>
	        		<c:when test="${incomeStockList.status==4 && requestScope.roleId==12}">
	        			<a href="${ctx}/incomeStoreRoomOfComment.do?billType=${incomeStockList.billType}&id=${incomeStockList.incomeStockId }&sendGoodId=${incomeStockList.sendGoodsId}">核对</a>
	        		</c:when>
	        		<c:otherwise>
	        			<c:if test="${requestScope.roleId==12}">核对</c:if>
	        		</c:otherwise>
	        	</c:choose>
	        	
	       </td>
	      </tr>
      </logic:iterate>
    </table>
<br />
        <table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table">
        	<tr>
           	  <td align="right"><%@ include file="/jsp/common/newPage.jsp"%></td>
          	</tr>
        </table>
    </td>
    <td >&nbsp;</td>
  </tr>
</table>
<html:hidden property="incomeStockListDto.type" value="true"/>
 </html:form>
</body>
</html>
