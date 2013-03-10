<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>区域产品分类选择</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
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
		<!--
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
		//-->
		</script>
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
<script type="text/javascript">
	var myArray=new Array();
	var XMLHttpReq=false;
	var regional;
	var regionalName;
	var obj1;
	function toParentsBefore(obj) {
		var id =obj.value;
		var name =obj.parentNode.parentNode.childNodes[1].innerText;
		var span = obj.parentNode.parentNode.childNodes[1].childNodes[0];
		if(obj.checked==true) {
			obj1=obj;
			proChange(id,regional);
			//pause(3000);
			//(temp);
			myArray.push([id+","+regional,name,regionalName]);
		} else {
			obj.parentNode.parentNode.childNodes[2].innerText = " ";
			for(var i=0;i<myArray.length;i++){
				if(myArray[i][0]==id+","+regional){
					myArray.splice(i,1);
				}
			}
		}
	};
	function pause(millisecondi){
	    var now = new Date();
	    var exitTime = now.getTime() + millisecondi;
	    while(true){
	        now = new Date();
	        if(now.getTime() > exitTime) return;
	    }
	};
	function submit() {
		window.opener.newWindow2(myArray,regionalName);
		window.close();
	};
	function createXMLHttpRequest() {
	  if(window.XMLHttpRequest) { //Mozilla 浏览器
	      XMLHttpReq = new XMLHttpRequest();
	  }else if (window.ActiveXObject) { // IE浏览器
	     try {
	        XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
	     } catch (e) {
		     try {
		        XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
		     } catch (e) {
		     	window.alert("创建XMLHttpRequest对象出错"+e);
		     }
	     }
	  }
      if(!(XMLHttpReq)){
        window.alert("创建XMLHttpRequest对象异常！");
      }
	};
	function proChange(id,regional){
		createXMLHttpRequest();
		XMLHttpReq.onreadystatechange=cityList;
		var url = "${ctx}/searchUser.do?method=salesAssistantAddBeforeCheck&regional="+regional+"&product="+id;
		XMLHttpReq.open("GET",url,true);
		XMLHttpReq.send(null);
	};
	function cityList() {
		if (XMLHttpReq.readyState == 4) { // 判断对象状态
			if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
				obj1.parentNode.parentNode.childNodes[2].innerText = obj1.parentNode.parentNode.childNodes[2].innerText+XMLHttpReq.responseText;
			}
		}
	};
	function toRegional(Regional){
		regionalName=Regional.options[Regional.selectedIndex].innerHTML;
		regional=Regional.value;
				
		var products = document.getElementsByName("product");
		var tableqwef = document.getElementById("xxxlist");
		for(i=0;i<products.length;i++){
        	products[i].checked=false;
    	}
    	for(i=1;i<tableqwef.rows.length;i++){
    		var tr = tableqwef.rows[i];
    		tr.childNodes[2].innerHTML="&nbsp;";
    	}
		
		myArray=new Array();
	};
	function init(){
		var regionals = document.getElementsByName("regional1112")[0];
		regionalName=regionals.options[0].innerHTML;
		regional=regionals.options[0].value;
	};
</script>
</head>
<body onLoad="javascript:init();">
<br>
<form action="searchUser.do">
<input type="hidden" name="method" value="">
<table width="96%" border="0" cellpadding="0" cellspacing="0" align="center" class="biao3" >
	<tr>
    	<td class="td_03" width="50%">区域选择</td>
    	<td class="td_04" width="50%">
	    	<select name="regional1112" style=" width:126px" onChange="javascript:toRegional(this);">
	    		<logic:iterate id="regional" name="regional">
	       			<option value="${regional.id}">${regional.name}</option>
	       		</logic:iterate>
	        </select>
        </td>
    </tr>
</table>
<br>
<table width="96%" border="0" cellpadding= 	"0" cellspacing="0" align="center" class="biao1" id="xxxlist">
	<tr>
		<th width="41">选择</th>
		<th>产品分类名称</th>
		<th>是否允许添加</th>
	</tr>
	<logic:iterate id="product" name="product">
		<tr>
			<td>&nbsp;<input type="checkbox" name="product" value="${product.id}" onClick="javascript:toParentsBefore(this);"></td>
			<td>${product.name}</td>
			<td>&nbsp;</td>
		</tr>
	</logic:iterate>
</table>
<table align="center">
	<tr>
      <td height="34px" colspan="3" align="center" valign="bottom"><a href="javascript:submit();"><img src="${ctx}/images/btnChoice.gif" width="65" height="20" /></a></td>
    </tr>
</table>
<br />
</form>
</body>
</html>

