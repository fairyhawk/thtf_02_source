<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>收货地址选择</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
		<style type="text/css"> 
			.treven {
				background-color: #f3fbff;
			}
			.over {
				background-color: #ECECEC;
			}
			 
			.STYLE1 {
				color: #FF0000
			} 
		</style>
		<script language="JavaScript">  
			$(document).ready(function(){
				if($("#receiveTable")){
					$("#receiveTable tr:even").addClass("treven");
					$("#receiveTable tr").each(function(i){
						$(this).mouseover(function(){
							$(this).addClass("over");
						});
						$(this).mouseout(function(){
							$(this).removeClass("over");
						});
					});
				}


				$("#add").click(function(){ 
					if($('#receiveTable input:checked').length==0){
						alert("请选择一个地址！");
					}else{
						var str = $('#receiveTable :radio:checked').val().split("&");
						if(str[1]!=1 && str[2]==""){
							alert("错误:此地址没有虚拟库！");
						}else{
							$("#receiveId",window.opener.document).attr("value",str[0]);
							$("#receiveCompanyType",window.opener.document).attr("value",str[1]);
							$("#receiveAddressId",window.opener.document).attr("value",str[10]);
							$("#stockroomId",window.opener.document).attr("value",str[2]); 

							$("#receiveName",window.opener.document).text(str[4]);
							$("#receiveAddress",window.opener.document).text(str[5]);
							$("#receiveLinkman",window.opener.document).text(str[7]);

							$("#receivePostcode",window.opener.document).text(str[6]);
							$("#receiveTel",window.opener.document).text(str[8]);
							$("#receiveMobile",window.opener.document).text(str[9]);
							$("#receiveStockroomName",window.opener.document).text(str[3]); 

							window.close(); 

						} 
					}  
				 }); 

				 if("${receiveId}"!=""){
					 $('#receiveTable :radio').each(function(){  
						 var str = $(this).val().split("&");
						 if(str[0]=="${receiveId}"){
							$(this).attr("checked","true"); 
						 }
					 }); 
				 }

			}); 
		</script> 
	</head>

	<body>
	<html:form method="post" action="getReceiveGoodsDetail" styleId="ascForm">
	<table width="96%" border="0" cellpadding="0" cellspacing="0" align="center">
	  <tr>
		<td align="center">
		<br/>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="receiveTable">
		  <tr>
			<th nowrap="nowrap" width="30px">选择</th>
			<th nowrap="nowrap">名称</th>
			<th nowrap="nowrap">货物接收单位名称</th>
			<th nowrap="nowrap">收货地址</th>
			<th nowrap="nowrap">邮编</th>
			<th nowrap="nowrap">联系人</th>
			<th nowrap="nowrap">电话</th>
			<th nowrap="nowrap">手机</th>
		  </tr>

		  <logic:iterate id="receive" name="resultList">
			  <tr>
				<td nowrap="nowrap">
					<input type="radio" name="checkedRadio"value="${receive.id}&${receive.companyType}&${receive.stockroomId}&${receive.stockroomName}&${receive.name}&${receive.address}&${receive.postcode}&${receive.linkman}&${receive.tel}&${receive.mobile}&${receive.addressId}" />
				</td>
				<td nowrap="nowrap">${receive.companyName}&nbsp;</td>
				<td nowrap="nowrap">${receive.name}&nbsp;</td>
				<td nowrap="nowrap">${receive.address}&nbsp;</td>
				<td nowrap="nowrap">${receive.postcode}&nbsp;</td>
				<td nowrap="nowrap">${receive.linkman}&nbsp;</td>
				<td nowrap="nowrap">${receive.tel}&nbsp;</td>
				<td nowrap="nowrap">${receive.mobile}&nbsp;</td>
			  </tr>
		  </logic:iterate>

		</table>
		<br /> 
			<table align="center">
				<tr>
				  <td height="45px" colspan="2" align="center" valign="bottom"><img src="${ctx}/images/btnChoice.gif" width="65" height="20" id="add"/></td>
				</tr>
			</table>    
		</td>
	  </tr>
	</table>
	</html:form>

	</body>
</html>
