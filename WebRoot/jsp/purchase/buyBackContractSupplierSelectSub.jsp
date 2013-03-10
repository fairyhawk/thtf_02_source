<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>供货商选择</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
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
		-->
		</style>
		<script language="JavaScript"> 
		    $(document).ready(function(){
				if($("#xxxlist")){
					$("#xxxlist tr:even").addClass("treven");
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

			var arrAddress = new Array();
			//获取选中记录的信息
			function getOption(obj,date){
				var obj=obj.value;
				arrAddress = obj.split(date);
			};

			//检索供应商名称
			function searchSupplier(){
				var supplierName = $("#supplierName").val();
				if(supplierName==null || supplierName==""){
					alert("请输入供应商名称");
					return;
				}

				document.forms[0].action = "getSupplierAddress.do?supplierName="+encodeURI(supplierName,"UTF-8");
				$("#frmSupplier").submit();
			}

			//单击选择按钮时，将选中的信息传到父页面
			function toParent(){
				if(arrAddress==null || arrAddress==""){
				    alert("请选择货物接收单位");
					return;
				}
				
				var id       		= arrAddress[0];//编号
				var supplierId     	= arrAddress[1];//所属供货商编号
				var name         	= arrAddress[2];//货物接收单位名称
				var address         = arrAddress[3];//发货地址
				var postcode    	= arrAddress[4];//邮编
				var linkman 		= arrAddress[5];//联系人
				var tel      		= arrAddress[6];//电话
				var mobile          = arrAddress[7];//手机
				$("#supplierAddressId",window.opener.document).val(id);
				$("#supplierId",window.opener.document).val(supplierId);
				$("#name",window.opener.document).html(name);
				$("#address",window.opener.document).html(address);
				$("#postcode",window.opener.document).html(postcode);
				$("#linkman",window.opener.document).html(linkman);
				$("#tel",window.opener.document).html(tel);		
				$("#mobile",window.opener.document).html(mobile);	
				window.close();
			}
		</script>
</head>

<body>
<form action="" method="post" id ="frmSupplier">
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
        <logic:present name="supplierAddressList">
			<logic:iterate id="supplierAddressList" name="supplierAddressList">
      <tr>
        <td nowrap="nowrap">
        	<input type="radio" name="supplierRadio" id="radio"  value="${supplierAddressList.id}'${date}'${supplierAddressList.supplierId}'${date}'${supplierAddressList.name}'${date}'${supplierAddressList.address}'${date}'${supplierAddressList.postcode}'${date}'${supplierAddressList.linkman}'${date}'${supplierAddressList.tel}'${date}'${supplierAddressList.mobile}" onclick="getOption(this,'\'${date}\'');" />
        </td>
        <td nowrap="nowrap">${supplierAddressList.name}</td>
        <td nowrap="nowrap">${supplierAddressList.address}</td>
        <td nowrap="nowrap">${supplierAddressList.postcode}</td>
        <td nowrap="nowrap">${supplierAddressList.linkman}</td>
        <td nowrap="nowrap">${supplierAddressList.tel}</td>
        <td nowrap="nowrap">${supplierAddressList.mobile}</td>
      </tr>
      		</logic:iterate>
		</logic:present>
    </table>

<table width="96%" border="0" cellpadding="0" cellspacing="0" align="center">
  <tr>
    <td align="center">
    <br/>
    <br />
	<table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table">
		<tr>
			<td align="right" ><%@ include file="/jsp/common/newPage.jsp"%></td>
		</tr>
	</table>
	<table align="center">
		<tr>
		  <td height="45px" colspan="2" align="center" valign="bottom"><a href="javascript:toParent();"><img src="${ctx}/images/btnChoice.gif" width="65"  height="20" /></td>
		</tr>
	</table>    
    </td>
  </tr>
</table>
</form>
</body>
</html>
