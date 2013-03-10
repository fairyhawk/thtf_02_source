<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@taglib prefix="tf" uri="localhost" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>公司收货地址选择</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
		<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="${ctx}/js/messages_cn.js"></script>
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
				    alert("请选择公司收货地址");
					return;
				}

				var companyId  = arrAddress[0];
				var companyName       = arrAddress[1];
				var goodsAddress      = arrAddress[2];
				var postcode          = arrAddress[3];
				var linkman           = arrAddress[4];
				var tel               = arrAddress[5];
				var mobile            = arrAddress[6];
				var addressid         = arrAddress[7];

				$("#receiveId",window.opener.document).attr("value","");
				$("#receiveName",window.opener.document).html(companyName+"&nbsp;&nbsp;<span style='display:none' id='spanid'><a onclick='return delReceiveAddress(this,2,"+companyId+","+addressid+")'><img src='${ctx}/images/btnSHDZSC.gif'/></a></span>");
				$("#addressId",window.opener.document).attr("value",addressid);
				$("#goodsName",window.opener.document).html(companyName+"&nbsp;");
				$("#address",window.opener.document).html(goodsAddress+"&nbsp;");
				$("#postcode",window.opener.document).html(postcode+"&nbsp;");		
				$("#linkman",window.opener.document).html(linkman+"&nbsp;");	
				$("#tel",window.opener.document).html(tel+"&nbsp;");	
                $("#mobile",window.opener.document).html(mobile+"&nbsp;");	
                
                $("#typeOfAddress",window.opener.document).val("2");
                $("#idOfAddress",window.opener.document).val(addressid);
                $("#companyId",window.opener.document).val(companyId)
                //$("#companyTypeToTr",window.opener.document).text("2");
                //$("#companyIdToTr",window.opener.document).text(companyAddressId);
				window.close();
			}
		</script>

</head>

<body>
<br />
<table width="96%" border="0" cellpadding="0" cellspacing="0" align="center" class="biao1" id="xxxlist">
	<tr>
	  <th width="30">选择</th>
	  <th>货物接收单位名称</th>
	  <th>发货地址</th>
	  <th>邮编</th>
	  <th>联系人</th>
	  <th>电话</th>
	  <th>手机</th>
  </tr>
	<logic:present name="companyAddressList">
		<logic:iterate id="addressList" name="companyAddressList">
			<tr>
				<td>
					<input type="radio" name="stockroomRadio" id="radio"
						value="${addressList.companyId}@#&*%@${tf:replaceHTML(addressList.name)}@#&*%@${tf:replaceHTML(addressList.address)}@#&*%@${tf:replaceHTML(addressList.postcode)}@#&*%@${tf:replaceHTML(addressList.linkman)}@#&*%@${tf:replaceHTML(addressList.tel)}@#&*%@${tf:replaceHTML(addressList.mobile)}@#&*%@${addressList.id}" onclick="getOption(this);" />
				</td>
				<td width="150px">
					${addressList.name}&nbsp;
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
<table align="center" width="96%">
	<tr>
	  <td height="45px" colspan="2" align="center" valign="bottom"><a href="javascript:toParent();"><img src="${ctx}/images/btnChoice.gif" width="65"  height="20" /></td>
	</tr>
</table>
</body>
</html>
