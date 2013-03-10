<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
<head>
<title>发货地址选择</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
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
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
<script type="text/javascript" src="${ctx}/js/base.js"></script>
<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
<script type="text/javascript" src="${ctx}/js/util.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript">
		
		var addressid;
		var reveivename;
		var addressname;
		var reveivelinkman;
		var postcode;
		var receivetel;
		var receivemobile;
		
		// 用于选择客户地址后将值传回添加页面 
		function toParentsAddress() {
			//getOption($(":radio:checked").get(0));
			var isChecked=0;
			var cusAddress = document.getElementsByName("cusAddessRadio");
			for(i=0;i<cusAddress.length;i++){
				if(cusAddress[i].checked){
					isChecked=1;
					break;
				}
			}
			if(isChecked==1){
			
		     // 判断是否有客户的收获地址被选中

				window.opener.document.getElementById("customerAddressId").value = addressid; 
				window.opener.document.getElementById("reveivename").innerHTML = reveivename;
				window.opener.document.getElementById("addressname").innerHTML = addressname;
				window.opener.document.getElementById("reveivelinkman").innerHTML = reveivelinkman;
				window.opener.document.getElementById("postcode").innerHTML = postcode;
				window.opener.document.getElementById("receivetel").innerHTML = receivetel;
				window.opener.document.getElementById("receivemobile").innerHTML = receivemobile;

				$("#addreveivename",window.opener.document).attr("value",reveivename);
				$("#addaddressname",window.opener.document).attr("value",addressname);
				$("#addreveivelinkman",window.opener.document).attr("value",reveivelinkman);
				$("#addpostcode",window.opener.document).attr("value",postcode);
				$("#addreceivetel",window.opener.document).attr("value",receivetel);
				$("#addreceivemobile",window.opener.document).attr("value",receivemobile);
 

			    window.close();
		    
			}else{
				alert("请选择发货地址！");				
			}
	  
	};
	function getOption(obj){ 
		addressid = $(obj).val(); 
        var tr = $(obj).parent().parent(); 

		reveivename = tr.children("td:eq(1)").text(); 
		addressname = tr.children("td:eq(2)").text();
		postcode = tr.children("td:eq(3)").text();
		reveivelinkman = tr.children("td:eq(4)").text();
		receivetel = tr.children("td:eq(5)").text();
		receivemobile = tr.children("td:eq(6)").text();
	};
	$(document).ready(function(){
		if($("#table")){
			$("#table tr:odd").addClass("treven");
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
</head>
<body >
<br />
<form action="sell.do?method=addressByCustomerId" method="post">
  <table width="96%" border="0" cellpadding="0" cellspacing="0" align="center" class="biao1" id="table"> 
    <tr>
		<th width="30"> 选择 </th>
		<th> 货物接收单位名称 </th>
		<th> 发货地址 </th>
		<th> 邮编 </th>
		<th> 联系人 </th>
		<th> 电话 </th>
		<th> 手机 </th> 
	</tr>
    <logic:present name="sendAddresslist">
		<logic:iterate id="sendAddresslist" name="sendAddresslist">
		<tr>
		  <td><input type="radio" name="cusAddessRadio" id="radio" value="${sendAddresslist.id}" onclick="getOption(this);" /></td>
		  <td width="150px" title="${sendAddresslist.name}">${sendAddresslist.name}</td>
		  <td title="${sendAddresslist.address}">${sendAddresslist.address}</td>
		  <td width="40">${sendAddresslist.postcode}</td>
		  <td width="48px">${sendAddresslist.linkman}</td>
		  <td width="120px">${sendAddresslist.tel}</td>
		  <td width="72">${sendAddresslist.mobile}</td>
		</tr>
		</logic:iterate> 
    </logic:present> 
  </table>
  <br />
  <table align="center" width="96%" cellpadding="0" cellspacing="0">
    <tr>
      <td align="right" style="font-size:12px"><%@ include file="/jsp/common/newPage.jsp"%></td>
    </tr>
    <tr>
      <td height="45px" align="center" valign="bottom"><a href="#" onclick="toParentsAddress();"><img src="${ctx}/images/btnChoice.gif" width="65" height="20" /> </a></td>
    </tr>
  </table>
</form>
</body>
</html>
