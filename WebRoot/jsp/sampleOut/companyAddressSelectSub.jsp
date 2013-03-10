<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>公司发货地址选择</title>
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
				arrAddress = obj.split("#");
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
						value="${addressList.companyId}#${addressList.name}#${addressList.address}#${addressList.postcode}#${addressList.linkman}#${addressList.tel}#${addressList.mobile}#${addressList.id}" onclick="getOption(this);" />
				</td>
				<td width="150px">
					${addressList.name}
				</td>
				<td width="150px">
					${addressList.address}
				</td>
				<td width="150px">
					${addressList.postcode}
				</td>
				<td width="150px">
					${addressList.linkman}
				</td>
				<td width="150px">
					${addressList.tel}
				</td>
				<td width="150px">
					${addressList.mobile}
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
