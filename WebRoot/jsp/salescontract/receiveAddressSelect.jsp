<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>收货地址选择</title>
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
<script type="text/javascript" src="${ctx}/js/messages_cn.js"></script>		
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
		$(function(){ 
				$("#btnChoice").click(
					function(){
						var result = false;
						$('input[name=radio]').each(function(i){
						 	if ($(this).attr("checked") == true) {
						          result = true;
						          opener.addAdderss($(this).val()); 
								  window.close();
						    }
						});
						if(!result){
							alert("请选择客户！");
							return false;
						}
					}
				);
		});
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
			});
		//-->
		</script>
</head>

<body>
<table width="96%" border="0" cellpadding="0" cellspacing="0" align="center">
   <tr>
    <td align="center"><br />
	    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
	      <tr>
	        <td class="td_03" width="50%" height="18">库房名称</td>
	        <td class="td_04">${stockRoomName}&nbsp;</td>
	      </tr>
	    </table>
   </td>
  </tr>
  <tr>
    <td align="center">
    <BR />
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table" style="border-left:1px solid #FFFFFF;">
		<tr>
			<th nowrap="nowrap" width="40" style="border-left:1px solid #c2c2c2;">选择</th>
			<th nowrap="nowrap">货物接收单位名称</th>
			<th nowrap="nowrap">收货地址</th>
			<th nowrap="nowrap">邮编</th>
			<th nowrap="nowrap">联系人</th>
			<th nowrap="nowrap">电话</th>
			<th nowrap="nowrap">手机</th>
			</tr>
			<logic:present name="stockRoomList">
					<logic:iterate id="address" name="stockRoomList" >
						<tr>
							<td nowrap="nowrap" style="border-left:1px solid #c2c2c2;">
								<input type="radio" name="radio" id="radio" value="${address.goodsName}&${address.goodsAddress}&${address.linkman}&${address.postcode}&${address.tel}&${address.mobile}&${address.stockRoomAddressId}" />
							</td>
							<td nowrap="nowrap" >${address.goodsName}&nbsp;</td>
							<td nowrap="nowrap" >${address.goodsAddress}&nbsp;</td>
							<td nowrap="nowrap" width="40">${address.postcode}&nbsp;</td>
							<td nowrap="nowrap" width="70px">${address.linkman}&nbsp;</td>
							<td nowrap="nowrap" width="120px">${address.tel}&nbsp;</td>
							<td nowrap="nowrap" width="70">${address.mobile}&nbsp;</td>
						</tr>
					</logic:iterate>
				</logic:present>
	</table>
    <table align="center" width="100%" cellpadding="0" cellspacing="0">
            <tr>
              <td height="45px"  align="center" valign="bottom">
              	<img id="btnChoice" src="${ctx}/images/btnChoice.gif" width="65" height="20" />
              </td>
            </tr>
        </table>    
    </td>
  </tr>
</table>
 
</body>
</html>
