<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>客户选择</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
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
				if($("#xxxlist")){
					$("#xxxlist tr:odd").addClass("treven");
					$("#xxxlist tr").each(function(i){
						$(this).mouseover(function(){
							$(this).addClass("over");
						});
						$(this).mouseout(function(){
							$(this).removeClass("over");
						});
					});
				}
			});
		//-->
		</script>
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript">
		
		//初始化原先选中的客户
		$(function(){
			
			var customerId = window.opener.document.getElementById("customerId").value;
			
			$("radoCustomerId").each(function(index){
				if (this.value == customerId)
					this.checked = true;					
			});
		});
		
		function selectCustomer() {
			
			var customer = {};
			
			if ($("input:checked").length > 0 ) {
				customer.id = $("input:checked").get(0).value;
				customer.name = $("#customerName" + customer.id).val();				
			}
			else {
				customer.id="";
				customer.name="";
			}
			
			window.close();
			window.opener.fillSelectedCusmoer(customer);
			
		};
		
		function submitCheck() {
		
			var customerName = $("#customerName").val();
		
			if (customerName != null) {	
				document.forms[0].submit();
			}
		}

		</script>
</head>
<body>
<br/>
<div class="mo_wp">
  <div style="display: ; " class="mo_con">
    <html:form action="selectCustomer" method="post">
      <table width="96%" border="0" cellpadding="0" cellspacing="0"
						class="biao3" align="center">
        <tr>
          <td class="td_03" width="50%"> 客户名称 </td>
          <td class="td_04"><input type="text" name="customerName" id="customerName"
									style="width:240px;" value="${customerName}">
          </td>
        <tr>
          <td colspan="2" align="center" height="30px">
          <a href="javascript:submitCheck();"><img id="" src="${ctx}/images/btn_JianSuo.gif" /></a></td>
        </tr>
      </table>
    </html:form>
  </div>
  <div class="mo_title" onclick="fMainListToggle(this)">
    <div style="text-align:center"><img id="imgShang" src="${ctx}/images/shang_sj.png" /></div>
  </div>
</div>
<br/>
<table border="0" cellpadding="0" cellspacing="0" width="96%" align="center" class="biao1" id="xxxlist">
  <tr>
    <th width="41px"> 选择 </th>
    <th> 客户名称 </th>
  </tr>
  <c:forEach var="customer" items="${customerList}">
    <tr>
      <td>&nbsp;<input type="radio" name="radoCustomerId" value="${customer.id}"/>
      </td>
      <td><input type="hidden"  id="customerName${customer.id}" value="${customer.name}"/>
        ${customer.name} </td>
    </tr>
  </c:forEach>
</table><br />
<table cellpadding="0" cellspacing="0" width="96%" border="0" align="center">
  <tr>
    <td style="text-align:right" class="td_04" ><%@ include file="/jsp/common/newPage.jsp"%>
    </td>
  </tr>
  <tr>
    <td height="34px" align="center" valign="bottom"><a href="javascript:selectCustomer();"><img src="${ctx}/images/btnChoice.gif" /> </a> </td>
  </tr>
</table>
<br />
</body>
</html>
