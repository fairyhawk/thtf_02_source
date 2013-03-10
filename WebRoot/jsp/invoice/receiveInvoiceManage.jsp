<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>采购发票管理</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
        <script type="text/javascript" src="${ctx}/js/Calendar1.js"></script>
		<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="${ctx}/js/messages_cn.js"></script>
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

		var msg = "${msg}";  //获取服务端信息	
		if (msg != "") 
		{
			alert(msg);
		}	
	});

	//删除
	function deleteInvoice(id){
		if(confirm("确认删除？")){
			window.location = 'removeRecordById.do?reInvoiceId='+id;
		}
	}

	//更新(退票)
	function updateInvoice(id){
		if(confirm("确认退票？")){
			window.location = 'modifyRecordById.do?reInvoiceId='+id;
		}
	}

	//检索
	function searchInvoice(){
		document.forms[0].action = "getReceiveInvoiceList.do";
		$("#frmInvoice").submit();
	}
</script>

</head>
<form action="" method="get" id ="frmInvoice">
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left" width="16px" title=""><div class=ellipsis_div style="width:16px;">&nbsp;</div></td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 采购管理 &gt;&gt; 采购发票管理</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
   <tr>
    <td >&nbsp;</td>
    <td align="center"><br />
    	<div class="mo_wp">
          <div style="display: ; " class="mo_con" >
            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
              <tr>
                <td class="td_01" width="20%">收票流水号</td>
                <td class="td_02" width="30%"><input type="text" name="receiveInvoiceId" id="receiveInvoiceId" value="${searchForm.receiveInvoiceId}"  style="width:120px;" /></td>
                <td class="td_01" width="20%">产品分类名称</td>
                <td class="td_02" width="30%">
				<html:select property="prodTypeId" value="${searchForm.prodTypeId}" style=" width:126px">
					<html:option value="">--请选择--</html:option> 
					<html:options collection="productTypeList" property="id" labelProperty="name"/>
				</html:select>
              </td>
              </tr>
              <tr>
                <td class="td_01">供货商名称</td>
                <td class="td_02"><input type="text" name="supplierName" id="supplierName"  value="${searchForm.supplierName}" style="width:240px;" /></td>
                <td class="td_01">发票状态</td>
                <td class="td_02">
					<html:select property="status" value="${searchForm.status}" style=" width:126px">
						<html:option value="">--请选择--</html:option>  
						<html:option value="1">已收票</html:option>
						<html:option value="2">已退票</html:option> 
					</html:select>
               </td>
              </tr>
              <tr>
                <td class="td_01">发票类型</td>
                <td class="td_02">
					<html:select property="invoiceType" value="${searchForm.invoiceType}" style=" width:126px">
						<html:option value="">--请选择--</html:option>  
						<html:option value="0">普通</html:option>
						<html:option value="1">增值税</html:option> 
					</html:select>				
                </td>
                <td class="td_01">发票号</td>
                <td class="td_02"><input type="text" name="invoiceNo" id="invoiceNo" value="${searchForm.invoiceNo}" style="width:120px;" /></td>
              </tr>
              <tr>
              	<td class="td_01">收票起始日期</td>
              	<td class="td_02">
                    <input type="text" name="receiveStartDate" id="receiveStartDate" style="width:120px;" onfocus="calendar()" readonly="readonly" value="${searchForm.receiveStartDate}"/>
				</td>				

              	<td class="td_01">收票终止日期</td>
              	<td class="td_02">
                    <input type="text" name="receiveEndDate" id="receiveEndDate" style="width:120px;" onfocus="calendar()" readonly="readonly" value="${searchForm.receiveEndDate}"/>				
                </td>
              	</tr>
              <tr>
              	<td class="td_01">退票起始日期</td>
              	<td class="td_02">
                    <input type="text" name="backStartDate" id="backStartDate" style="width:120px;" onfocus="calendar()" readonly="readonly" value="${searchForm.backStartDate}"/>					
                </td>
              	<td class="td_01">退票终止日期</td>
              	<td class="td_02">
                    <input type="text" name="backEndDate" id="backEndDate" style="width:120px;" onfocus="calendar()" readonly="readonly" value="${searchForm.backEndDate}"/>					
                </td>
              	</tr>
              <tr>
                <td class="td_01">人员名称</td>
                <td class="td_02"><input type="text" name="userName" id="userName" value="${searchForm.userName}" style="width:120px;" /></td>
                <td class="td_01">入库单号</td>
                <td class="td_02"><input type="text" name="inStockId" id="inStockId" value="${searchForm.inStockId}" style="width:120px;" /></td>
              </tr>
              <tr>
                <td colspan="4" align="center" style="height:30px;"><img src="${ctx}/images/btn_JianSuo.gif" onClick="searchInvoice();" /></td>
              </tr>
            </table>
          </div>
          <div class="mo_title" onclick="fMainListToggle(this)">
              <div  style="text-align:center"><img id="imgShang" src="${ctx}/images/shang_sj.png" /></div>
          </div>
        </div>
    </td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td align="center">
    <div style="width:100%; text-align:right">单位：元</div>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table">
      <tr>
        <th nowrap="nowrap">&nbsp;收票流水号&nbsp;</th>
        <th nowrap="nowrap">&nbsp;产品分类名称&nbsp;</th>
        <th nowrap="nowrap">供货商名称 </th>
        <th nowrap="nowrap">&nbsp;&nbsp;发票金额&nbsp;&nbsp;</th>
        <th nowrap="nowrap">&nbsp;发票状态&nbsp;</th>
        <th nowrap="nowrap" >&nbsp;发票类型&nbsp;</th>
        <th nowrap="nowrap" >&nbsp;&nbsp;发票号&nbsp;&nbsp;</th>
        <th nowrap="nowrap" >&nbsp;收票日期&nbsp;</th>
        <th nowrap="nowrap" >&nbsp;退票日期&nbsp;</th>
        <th nowrap="nowrap" >&nbsp;人员名称&nbsp;</th>
        <th nowrap="nowrap">&nbsp;操作&nbsp;</th>
      </tr>
       <c:forEach var="invoiceList" items="${invoiceList}">
        <tr>
          <td nowrap="nowrap" width="120px" height="18">${invoiceList.receiveInvoiceId}&nbsp;</td>
          <td nowrap="nowrap">${invoiceList.prodTypeName}&nbsp;</td>
          <td nowrap="nowrap" title="${invoiceList.supplierName}"><div class="ellipsis_div" style="width:120px;">${invoiceList.supplierName}&nbsp;</div></td>
          <td nowrap="nowrap" style=" text-align:right;"><fmt:formatNumber value="${invoiceList.invoiceMoney}" type="number" pattern="#,##0.00000" />&nbsp;</td>
		  <c:if test="${invoiceList.status==1}">
          <td nowrap="nowrap">已收票</td>
		  </c:if>
		  <c:if test="${invoiceList.status==2}">
          <td nowrap="nowrap">已退票</td>
		  </c:if>
		  <c:if test="${invoiceList.invoiceType==0}">
          <td nowrap="nowrap">普通</td>
		  </c:if>
		  <c:if test="${invoiceList.invoiceType==1}">
          <td nowrap="nowrap">增值税</td>
		  </c:if>
          <td nowrap="nowrap">${invoiceList.invoiceNo}&nbsp;</td>
          <td nowrap="nowrap">${invoiceList.receiveDate}&nbsp;</td>
          <td nowrap="nowrap">${invoiceList.backDate}&nbsp;</td>
          <td nowrap="nowrap">${invoiceList.userName}&nbsp;</td>
		  <td nowrap="nowrap">
		  	<a href="${ctx}/getInvoiceInfo.do?receiveInvoiceId=${invoiceList.receiveInvoiceId}&flag=view">查看</a>
			<c:if test="${userAction == 'all'}">
				<c:if test="${invoiceList.status==2}">
					删除
					指定
					退票
				</c:if>
				<c:if test="${invoiceList.status==1}">
					<c:if test="${invoiceList.invoiceCount<=0}">
					<a href="javascript:deleteInvoice('${invoiceList.receiveInvoiceId}');">删除</a>
					</c:if>
					<c:if test="${invoiceList.invoiceCount>0}">
						删除
					</c:if>
				    <a href="${ctx}/getInvoiceInfo.do?receiveInvoiceId=${invoiceList.receiveInvoiceId}&flag=appoint&supplierId=${invoiceList.supplierId}">指定</a>
					<c:if test="${invoiceList.invoiceCount>0}">
						<a href="javascript:updateInvoice('${invoiceList.receiveInvoiceId}');">退票</a>
					</c:if>
					<c:if test="${invoiceList.invoiceCount<=0}">
						退票
					</c:if>
				</c:if>
			</c:if>
		  </td>
         </tr>
	   </c:forEach>
    </table>
  <br />
      <table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table">
        <tr>
		<c:if test="${userAction == 'all'}">
		 <td width="120px"><a href="${ctx}/goReceiveInvoicePage.do?"><img src="${ctx}/images/btCGFPLR.gif" width="113" height="20"/></a>
		 </c:if>
          <td align="right" height="25px"><%@ include file="/jsp/common/newPage.jsp"%></td>
        </tr>
      </table>
    </td>
    <td >&nbsp;</td>
  </tr>
</table>
</form> 
</body>
</html>
