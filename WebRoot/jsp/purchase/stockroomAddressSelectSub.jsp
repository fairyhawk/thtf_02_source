<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@taglib prefix="tf" uri="localhost" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>库房收货地址选择</title>
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
			function getOption(obj){
				var obj=obj.value;
				arrAddress = obj.split("@#&*%@");
			};

			//单击选择按钮时，将选中的信息传到父页面
			function toParent(){
				if(arrAddress==null || arrAddress==""){
				    alert("请选择库房收货地址");
					return;
				}

				var stockRoomId         = arrAddress[0];
				var stockRoomName       = arrAddress[1];
				var addressId           = arrAddress[2];
				var goodsName           = arrAddress[3];
				var goodsAddress        = arrAddress[4];
				var postcode            = arrAddress[5];
				var linkman             = arrAddress[6];
				var tel                 = arrAddress[7];
				var mobile              = arrAddress[8];

				$("#receiveId",window.opener.document).attr("value",stockRoomId);
				$("#receiveName",window.opener.document).html(stockRoomName+"&nbsp;&nbsp;<span style='display:none' id='spanid'><a onclick='return delReceiveAddress(this,1,"+stockRoomId+","+addressId+")'><img src='${ctx}/images/btnSHDZSC.gif'/></a></span>");
				$("#addressId",window.opener.document).attr("value",addressId);
				$("#goodsName",window.opener.document).html(goodsName+"&nbsp;");
				$("#address",window.opener.document).html(goodsAddress+"&nbsp;");
				$("#postcode",window.opener.document).html(postcode+"&nbsp;");		
				$("#linkman",window.opener.document).html(linkman+"&nbsp;");	
				$("#tel",window.opener.document).html(tel+"&nbsp;");	
                $("#mobile",window.opener.document).html(mobile+"&nbsp;");	
                
                $("#typeOfAddress",window.opener.document).val("1");
                $("#idOfAddress",window.opener.document).val(addressId);
                $("#companyId",window.opener.document).val(stockRoomId);
                //$("#companyTypeToTr",window.opener.document).text("1");
                //$("#companyIdToTr",window.opener.document).text(stockRoomId);
				window.close();
			}
		</script>
</head>

<body>
<br />
<table width="96%" border="0" cellpadding="0" cellspacing="0" align="center" class="biao1" id="xxxlist">
	<tr>
	  <th width="30">选择</th>
	  <th>库房名称</th>
	  <th>货物接收单位名称</th>
	  <th>发货地址</th>
	  <th>邮编</th>
	  <th>联系人</th>
	  <th>电话</th>
	  <th>手机</th>
   </tr>
		<logic:present name="stockroomList">
			<logic:iterate id="stockroomList" name="stockroomList">
				<tr>
					<td>
						<input type="radio" name="stockroomRadio" id="radio"
							value="${stockroomList.stockRoomId}@#&*%@${tf:replaceHTML(stockroomList.stockRoomName)}@#&*%@${stockroomList.stockRoomAddressId}@#&*%@${tf:replaceHTML(stockroomList.goodsName)}@#&*%@${tf:replaceHTML(stockroomList.goodsAddress)}@#&*%@${tf:replaceHTML(stockroomList.postcode)}@#&*%@${tf:replaceHTML(stockroomList.linkman)}@#&*%@${tf:replaceHTML(stockroomList.tel)}@#&*%@${tf:replaceHTML(stockroomList.mobile)}" onclick="getOption(this);" />
					</td>
					<td width="150px">
						${stockroomList.stockRoomName}&nbsp;
					</td>
					<td width="150px">
						${stockroomList.goodsName}&nbsp;
					</td>
					<td width="150px">
						${stockroomList.goodsAddress}&nbsp;
					</td>
					<td width="150px">
						${stockroomList.postcode}&nbsp;
					</td>
					<td width="150px">
						${stockroomList.linkman}&nbsp;
					</td>
					<td width="150px">
						${stockroomList.tel}&nbsp;
					</td>
					<td width="150px">
						${stockroomList.mobile}&nbsp;
					</td>
				</tr>
			</logic:iterate>
		</logic:present>
</table>
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
</body>
</html>
