<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>供货商发货地址选择</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
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
	 
		</script>
<script language="JavaScript"> 

		var supplierId;
		var supplierName;
		var supplierAddress;
		var supplierPostcode;
		var supplierLinkman;
		var supplierTel;
		var supplierMobile;

		
	function getOption(obj){
		supplierId=obj.value;
		window.opener.document.getElementById("supplierAddressId").value = supplierId;
		supplierName=obj.parentNode.parentNode.childNodes[1].innerHTML; 
		supplierAddress=obj.parentNode.parentNode.childNodes[2].innerHTML;
		supplierPostcode=obj.parentNode.parentNode.childNodes[3].innerHTML;
		supplierLinkman=obj.parentNode.parentNode.childNodes[4].innerHTML;
		supplierTel=obj.parentNode.parentNode.childNodes[5].innerHTML;
		supplierMobile=obj.parentNode.parentNode.childNodes[6].innerHTML;
	};
	//选择
	function subAddress(){
	
			
		
		     // 判断是否有发货地址被选中
			var isChecked=false;
			var cusAddress = document.getElementsByName("addressAadio");
			for(i=0;i<cusAddress.length;i++){
				if(cusAddress[i].checked){
					isChecked=true;
					break;
				}
			} 
			if(isChecked==true){ 
				//变量赋值
				getOption($(":radio:checked").get(0));
				//给父页面的td赋值
				window.opener.document.getElementById("supplierAddressId").value = supplierId; 
				window.opener.document.getElementById("supplierName").innerHTML = supplierName;
				window.opener.document.getElementById("supplierAddress").innerHTML = supplierAddress;
				window.opener.document.getElementById("supplierLinkman").innerHTML = supplierLinkman;
				window.opener.document.getElementById("supplierPostcode").innerHTML = supplierPostcode;
				window.opener.document.getElementById("supplierTel").innerHTML = supplierTel;
				window.opener.document.getElementById("supplierMobile").innerHTML = supplierMobile;
			    window.close();
		    
			}else{
				alert("请选择发货地址！");			
			}
	
	}
	
</script>
</head>
<body>
<table width="96%" border="0" cellpadding="0" cellspacing="0" align="center">
  <tr>
    <td align="center">
    <br/>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table">
      <tr>
        <th nowrap="nowrap">选择</th>
        <th nowrap="nowrap">货物接收单位名称</th>
        <th nowrap="nowrap">发货地址</th>
        <th nowrap="nowrap">邮编</th>
        <th nowrap="nowrap">联系人</th>
        <th nowrap="nowrap">电话</th>
        <th nowrap="nowrap">手机</th>
        </tr>
        
      <logic:iterate name="addressEntities" id="addressEntity"> 
      <tr>
        <td  width="50px" nowrap="nowrap"><input type="radio" name="addressAadio" id="radio3" value="${addressEntity.id}" /></td>
        <td nowrap="nowrap">${addressEntity.name}&nbsp;</td>
        <td nowrap="nowrap">${addressEntity.address}&nbsp;</td>
        <td nowrap="nowrap" width="42">${addressEntity.postcode}&nbsp;</td>
        <td nowrap="nowrap" width="53">${addressEntity.linkman}&nbsp;</td>
        <td nowrap="nowrap" width="120px">${addressEntity.tel}&nbsp;</td>
        <td nowrap="nowrap" width="70px">${addressEntity.mobile}&nbsp;</td>
      </tr> 
        </logic:iterate>
    </table>
    <br />
        <table border="0" cellpadding="0" cellspacing="0" width="100%" >
        	<tr>
           	  	<td align="right"><%@ include file="/jsp/common/newPage.jsp"%></td>
          </tr>
        </table>
        <table align="center">
            <tr>
              <td height="45px" colspan="2" align="center" valign="bottom"><img src="${ctx}/images/btnChoice.gif" width="65" height="20" onclick="subAddress()"/></td>
            </tr>
        </table>    
    </td>
  </tr>
</table>
 
</body>
</html>
