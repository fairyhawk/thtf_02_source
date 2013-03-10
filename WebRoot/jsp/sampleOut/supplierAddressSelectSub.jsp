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
 
		var arrAddress = new Array();
			//获取选中记录的信息
			function getOption(obj){
				var obj=obj.value;
				arrAddress = obj.split("#");
			};

			//单击选择按钮时，将选中的信息传到父页面
			function toParent(){
				if(arrAddress==null || arrAddress==""){
				    alert("请选择发货地址");
					return;
				}

				var addressid  = arrAddress[0];//
				var companyName       = arrAddress[1];//货物接收单位名称
				var goodsAddress      = arrAddress[2];//发货地址
				var postcode          = arrAddress[3];
				var linkman           = arrAddress[4];
				var tel               = arrAddress[5];
				var mobile            = arrAddress[6]; 
				//发货地址编号赋值
				$("#addressId",window.opener.document).attr("value",addressid);
				//货物接受单位名称
				$("#receiveName",window.opener.document).html(companyName+'&nbsp;');
				//发货地址
				$("#address",window.opener.document).html(goodsAddress+'&nbsp;');
				//联系人
				$("#linkman",window.opener.document).html(linkman+'&nbsp;');	
				//邮编
				$("#postcode",window.opener.document).html(postcode+'&nbsp;');		
				//电话
				$("#tel",window.opener.document).html(tel+'&nbsp;');	
				//手机
                $("#mobile",window.opener.document).html(mobile+'&nbsp;');	
                arrAddress = null;
				window.close();
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
        <th nowrap="nowrap">收货地址</th>
        <th nowrap="nowrap">邮编</th>
        <th nowrap="nowrap">联系人</th>
        <th nowrap="nowrap">电话</th>
        <th nowrap="nowrap">手机</th>
        </tr>
        
      <logic:iterate name="addressEntities" id="addressEntity"> 
      <tr>
        <td  width="30px" nowrap="nowrap"><input type="radio" name="addressAadio" id="radio3" 
	        value="${addressEntity.id}#${addressEntity.name}#${addressEntity.address}#${addressEntity.postcode}#${addressEntity.linkman}#${addressEntity.tel}#${addressEntity.mobile}"  
	        onclick="getOption(this);"/>
        </td>
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
              <td height="45px" colspan="2" align="center" valign="bottom"><img src="${ctx}/images/btnChoice.gif" width="65" height="20" onclick="toParent()"/></td>
            </tr>
        </table>    
    </td>
  </tr>
</table>
 
</body>
</html>
