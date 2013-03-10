<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>产品部门信息管理</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<style type="text/css">
<!--
.treven {
	background-color: #f3fbff;
}
.over {
	background-color: #ECECEC;
}.rowselected {
  	background-color: #868686;
}
-->
</style>
<script language="JavaScript"> 
		<!--
			$(document).ready(function(){
				if($("#xxxlist")){
					$("#xxxlist tr:odd").addClass("treven");
					$("#xxxlist tr").each(function(i){
						$(this).mouseover(function(){
							$(this).addClass("over");
						});
						$(this).mouseout(function(){
							$(this).removeClass("over");
						});
						$(this).click( function(){
				            if( $(this).hasClass("rowselected") ){
				                $(this).removeClass("rowselected");
				            }else{
				                $(this).addClass("rowselected");
				            }
			            });
					});
				}
			});
		//-->
		</script>
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
<script type="text/javascript" src="${ctx}/js/base.js"></script>
<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
<script type="text/javascript">
			var myArray=new Array();
		var mark1;
		var mark;
		function removeBefore(a) {
			for(var i=0;i<myArray.length;i++){
				if(myArray[i]==a.value){
					mark=0;
					mark1=i;
				}
			}
			if(mark!=0){
				myArray.push(a.value);
			}
			if(mark==0){
				myArray.splice(mark1,1);
			}
			var mark1=0;
			var mark=0;
		};
		function shan(){
		 if(myArray.length==0){
				alert("请选择删除项！");
				
			}else if (confirm("是否确认删除？")) {
				window.location = '${ctx}/productdeptmanagement.do?method=deleteProductDept&id='+ myArray.join(',');
			}else{
			}
		}
		
		function checks(){
		var err = document.getElementById("err").value;
		if(err==null||err==""){
			
		} 
		else if(err!= null){
			alert("删除失败！");
			return false;
			}
		}
		</script>
</head>
<body onload="checks();">
<input type="hidden" name="err" value="${err}" id="err">
</input>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" /> &nbsp;当前位置： 基础信息管理 &gt;&gt; 产品部门信息管理 </td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
  
  <td>&nbsp;</td>
  <td align="center"><br />
    <form action="productdeptmanagement.do?method=productDeptAll"
						method="post">
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="xxxlist">
        <tr>
          <th nowrap="nowrap" width="41px"> 选择 </th>
          <th nowrap="nowrap" width="96px"> 产品部门编号 </th>
          <th nowrap="nowrap"> 产品部门名称 </th>
          <th nowrap="nowrap" width="120px"> 帐号 </th>
          <th nowrap="nowrap" width="120px"> 传真号 </th>
          <th nowrap="nowrap" width="40px"> 操作 </th>
        </tr>
        <tr>
          <logic:present name="productdeptlist">
          <logic:iterate id="productdeptall" name="productdeptlist"
										property="records">
        <tr>
          <td>&nbsp;<input type="checkbox" name="ss"
													value="${productdeptall.id}" id="box"
													onclick="javascript:removeBefore(this);" />
          </td>
          <td> ${productdeptall.no} </td>
          <td> ${productdeptall.name} </td>
          <td> ${productdeptall.account}&nbsp;</td>
          <td> ${productdeptall.fax}&nbsp;</td>
          <td><a
													href="${ctx}/productdeptmanagement.do?method=showProductDeptUpdate&id=${productdeptall.id}">修改</a> </td>
        </tr>
        </logic:iterate>
        
        </logic:present>
        
        </tr>
        
      </table>
      <br />
      <table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table">
        <tr>
          <td width="30px" align="left">&nbsp;&nbsp;<input type="checkbox" name="zong" onclick="che(this);" /></td>
          <td width="50px"> 全选 </td>
          <td width="100px"><a href="javascript:shan();"> <img src="${ctx}/images/btnDelete.gif" /> </a> </td>
          <td width="100px"><a href="javascript:window.location ='${ctx}/productdeptmanagement.do?method=showProductDept';"> <img src="${ctx}/images/btnAdd.gif" /> </a> </td>
          <td align="right"><%@ include file="/jsp/common/page.jsp"%>
          </td>
        </tr>
      </table>
    </form></td>
  <td>&nbsp;</td>
  </tr>
</table>
</body>
</html>
