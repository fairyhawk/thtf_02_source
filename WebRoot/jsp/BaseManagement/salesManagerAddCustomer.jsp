<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>客户选择</title>
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
<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
<script type="text/javascript">
	var myArray=new Array();
	var XMLHttpReq;
	function gotoPage(pageOffset, pageSize) {
		if (pageOffset < 0) {
			return;
		}
		var form = document.getElementsByTagName("Form")[1];
		form.elements["pager.offset"].value = pageOffset;
		form.elements["pageSize"].value = pageSize;
		form.submit();
	};
	function form0submit() {
		var form = document.getElementsByTagName("Form")[0];
		form.submit();
	};
	function toParents() {
		window.opener.newWindow1(myArray);
	};
	function toParentsBefore(obj) {
		var id =obj.value;
		var name =obj.parentNode.parentNode.childNodes[1].innerHTML;
		if(obj.checked==true) {
			myArray.push([id,name]);
		} else {
			for(var i=0;i<myArray.length;i++){
				myArray[i]==id;
				myArray.splice(i,1);
			}
		}
	};
</script>
</head>
<body>
<form action="searchUser.do">
<input type="hidden" name="method" value="salesManagerAddCustomer">
<br />
<div class="mo_wp">
          <div style="display: ; " class="mo_con" >
            <table width="96%" border="0" cellpadding="0" cellspacing="0" class="biao3" align="center">
              <tr>
                <td class="td_03" width="50%">客户名称</td>
                <td class="td_04">
                  <input type="text" name="name" value="${name}" style="width:240px;">
                </td>
              <tr>
                <td colspan="2" align="center" height="30px"><a href="javascript:form0submit();"><img src="${ctx}/images/btn_JianSuo.gif" /></a></td>
              </tr>
            </table>
          </div>
          <div class="mo_title" onClick="fMainListToggle(this)">
              <div  style="text-align:center"><img id="imgShang" src="${ctx}/images/shang_sj.png" /></div>
          </div>
        </div>
</form>
<br />
<form action="searchUser.do">
<input type="hidden" name="method" value="salesManagerAddCustomer">
<input type="hidden" name="name" value="${name}">
<input type="hidden" name="id" value="${id}">
<table border="0" cellpadding="0" cellspacing="0" width="96%" align="center" class="biao1" id="xxxlist">
	<tr>
    	<th width="41px">选择</th>
        <th>客户名称</th>
    </tr>
    <logic:iterate id="listRange" name="listRange" property="records">
	    <tr>
	    	<td>&nbsp;<input type="checkbox" name="chen123" value="${listRange.id}" onClick="javascript:toParentsBefore(this);"></td>
	        <td>${listRange.name}</td>
	    </tr>
    </logic:iterate>
</table>
<table cellpadding="0" cellspacing="0" width="96%" border="0" align="center">
	<tr>
    	<td style="text-align:right;font-size:12px" ><%@ include file="/jsp/common/page.jsp"%></td>
    </tr>
	<tr>
    	<td height="34px" align="center" valign="bottom"><a href="javascript:toParents();"><img src="${ctx}/images/btnChoice.gif" /></a></td>
    </tr>
</table>
</form>
</body>
</html>
