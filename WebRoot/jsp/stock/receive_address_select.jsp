<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>发货地址选择</title>
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

		var stockroomAddressId;
		var stockroomAddressName;
		var stockroomAddressAddress;
		var stockroomAddressPostcode;
		var stockroomAddressLinkman;
		var stockroomAddressTel;
		var stockroomAddressMobile;

		
	function getOption(obj){
		stockroomAddressId=obj.value;
		window.opener.document.getElementById("stockroomAddressAddressId").value = stockroomAddressId;
		stockroomAddressName=obj.parentNode.parentNode.childNodes[1].innerHTML; 
		stockroomAddressAddress=obj.parentNode.parentNode.childNodes[2].innerHTML;
		stockroomAddressPostcode=obj.parentNode.parentNode.childNodes[3].innerHTML;
		stockroomAddressLinkman=obj.parentNode.parentNode.childNodes[4].innerHTML;
		stockroomAddressTel=obj.parentNode.parentNode.childNodes[5].innerHTML;
		stockroomAddressMobile=obj.parentNode.parentNode.childNodes[6].innerHTML;
	};
	//选择
	function subAddress(){		
		     // 判断是否有发货地址被选中
			var isChecked=false;
			var cusAddress = document.getElementsByName("addressRadio");
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
				window.opener.document.getElementById("stockroomAddressAddressId").value = stockroomAddressId; 
				window.opener.document.getElementById("stockroomAddressName").innerHTML = stockroomAddressName;
				window.opener.document.getElementById("stockroomAddressAddress").innerHTML = stockroomAddressAddress;
				window.opener.document.getElementById("stockroomAddressLinkman").innerHTML = stockroomAddressLinkman;
				window.opener.document.getElementById("stockroomAddressPostcode").innerHTML = stockroomAddressPostcode;
				window.opener.document.getElementById("stockroomAddressTel").innerHTML = stockroomAddressTel;
				window.opener.document.getElementById("stockroomAddressMobile").innerHTML = stockroomAddressMobile;
			    window.close();
		    
			}else{
				alert("请选择收货地址！");			
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
        
      <logic:iterate name="stockroomAddressEntities" id="stockroomAddressEntity"> 
      <tr>
        <td width="30px" nowrap="nowrap"><input type="radio" name="addressRadio" id="radio3" value="${stockroomAddressEntity.id}"/></td>
        <td nowrap="nowrap">&nbsp;${stockroomAddressEntity.name}</td>        
        <td nowrap="nowrap">&nbsp;${stockroomAddressEntity.address}</td>
        <td nowrap="nowrap" width="42">&nbsp;${stockroomAddressEntity.postcode}</td>
        <td nowrap="nowrap" width="53">&nbsp;${stockroomAddressEntity.linkman}</td>
        <td nowrap="nowrap" width="120px">&nbsp;${stockroomAddressEntity.tel}</td>
        <td nowrap="nowrap" width="70px">&nbsp;${stockroomAddressEntity.mobile}</td>
        </tr> 
        </logic:iterate> 
        
    </table>
    <br />
        <table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table">
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
