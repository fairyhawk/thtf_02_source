<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>客户发货地址选择</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
		<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="${ctx}/js/messages_cn.js"></script>
		<style type="text/css"> 
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
			function getOption(obj){
				var obj=obj.value;
				arrAddress = obj.split("#");
			};

			//检索供应商名称
			function searchCustomer(){
				var customerName = $("#customerName").val();
				document.forms[0].action = "getSampleOutCustomerAddressList.do?customerName="+encodeURI(customerName,"UTF-8");
				$("#frmCustomer").submit();
			}

			//单击选择按钮时，将选中的信息传到父页面
			function toParent(){
				if(arrAddress==null || arrAddress==""){
				    alert("请选客户收货地址");
					return;
				}

				var customerId          = arrAddress[0];
				var customerName        = arrAddress[1];
				var addressId           = arrAddress[2];
				var companyName           = arrAddress[3];
				var goodsAddress        = arrAddress[4];
				var postcode            = arrAddress[5];
				var linkman             = arrAddress[6];
				var tel                 = arrAddress[7];
				var mobile              = arrAddress[8];
 
				//发货地址编号赋值
				$("#addressId",window.opener.document).attr("value",addressId);
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
<form action="" method="post" id ="frmCustomer">
<input name="customerId" type="hidden" id="customerId" value="${customerId}" />
<table width="96%" border="0" cellpadding="0" cellspacing="0" align="center">
   <tr>
    <td align="center"><br />
    	<div class="mo_wp">
          <div style="display: ; " class="mo_con" >
            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
              <tr>
                <td class="td_03" width="50%">客户名称</td>
                <td class="td_04"><span class="td_02">
                  <input name="customerName" type="text" id="customerName" value="${customerName}" style="width:240px;"/>
                </span></td>
              </tr>
              <tr>
                <td colspan="4" align="center" style="height:30px;"><a href="#" onClick="searchCustomer();"/><img src="${ctx}/images/btn_JianSuo.gif" /></td>
              </tr>
            </table>
          </div>
          <div class="mo_title" onclick="fMainListToggle(this)">
              <div  style="text-align:center"><img id="imgShang" src="${ctx}/images/shang_sj.png" /></div>
          </div>
        </div>    </td>
  </tr>
  <tr>
    <td align="center">
    <br/>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" align="center" class="biao1" id="xxxlist">
      <tr>
        <th width="30">选择</th>
        <th>客户名称</th>
        <th>货物接收单位名称</th>
        <th>发货地址</th>
        <th>邮编</th>
        <th>联系人</th>
        <th>电话</th>
        <th>手机</th>
      </tr>
		<logic:present name="customerAddressList">
			<logic:iterate id="addressList" name="customerAddressList">
				<tr>
					<td>
						<input type="radio" name="customerRadio" id="radio"
							value="${addressList.customerId}#${addressList.customerName}#${addressList.addressId}#${addressList.addressName}#${addressList.address}#${addressList.postcode}#${addressList.linkman}#${addressList.tel}#${addressList.mobile}" onclick="getOption(this);" />
					</td>
					<td width="150px">
						${addressList.customerName}&nbsp;
					</td>
					<td width="150px">
						${addressList.addressName}&nbsp;
					</td>
					<td width="150px">
						${addressList.address}&nbsp;
					</td>
					<td width="150px">
						${addressList.postcode}&nbsp;
					</td>
					<td width="150px">
						${addressList.linkman}&nbsp;
					</td>
					<td width="150px">
						${addressList.tel}&nbsp;
					</td>
					<td width="150px">
						${addressList.mobile}&nbsp;
					</td>
				</tr>
			</logic:iterate>
		</logic:present>
    </table>
    <br />
   </td>
 </tr>
</table>
<table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table">
	<tr>
		<td align="right" ><%@ include file="/jsp/common/newPage.jsp"%>&nbsp;&nbsp;</td>
	</tr>
</table>
<table align="center">
	<tr>
	  <td height="45px" colspan="2" align="center" valign="bottom"><a href="javascript:toParent();"><img src="${ctx}/images/btnChoice.gif" width="65"  height="20" /></td>
	</tr>
</table>   
 </form>
</body>
</html>
