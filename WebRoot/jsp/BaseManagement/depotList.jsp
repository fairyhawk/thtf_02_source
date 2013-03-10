<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>库房信息管理</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
		<style type="text/css"> 
		<!--
		.treven {
			background-color: #f3fbff;
		}
		.over {
			background-color: #ECECEC;
		}
		.rowselected {
		  	background-color: #868686;
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
				window.location ='${ctx}/depot.do?method=deleteDepot&id='+myArray.join(',');
			}else{
			}
		};
		function checks(){
			var err = document.getElementById("err").value;
			if(err==null||err==""){
				
			} 
			else if(err!= null){
				alert(err);
				}
		}
		</script>
	</head>

	<body onload="checks();">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td class="ye_header_left">&nbsp;
					
				</td>
				<td class="ye_header_center">
					<img src="${ctx}/images/main_jt.jpg" />
					&nbsp;当前位置： 基础信息管理 &gt;&gt; 库房信息管理
				</td>
				<td class="ye_header_right">&nbsp;
					
				</td>
			</tr>
			<tr>
				<td>&nbsp;
					
				</td>
				<td align="center">
					<br />
					<form action="depot.do?method=depotAll" method="post">
					<input type="hidden" name="err" value="${err}" id="err"></input>
						<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="xxxlist">
							<tr>
								<th nowrap="nowrap" width="41px">
									选择
								</th>
								<th nowrap="nowrap">
									库房名称
								</th>
								<th nowrap="nowrap">
									库房类型
								</th>
								<th nowrap="nowrap">
									部门名称
								</th>
								<th nowrap="nowrap" width="70px">
									操作
								</th>
							</tr>
							<logic:present name="depotList">
								<logic:iterate id="depot" name="depotList" property="records">
									<tr>
										<td nowrap="nowrap">
											&nbsp;<input type="checkbox" name="ss" value="${depot.id}" id="box"
												onclick="javascript:removeBefore(this);" />
										</td>
										<td nowrap="nowrap">
											${depot.name}
										</td>
										<td nowrap="nowrap" width="120px">
											<logic:equal value="0" name="depot" property="type">
										虚拟库
										</logic:equal>
											<logic:equal value="1" name="depot" property="type">
										正常库
										</logic:equal>
											<logic:equal value="2" name="depot" property="type">
										索赔库
										</logic:equal>
										</td>
										<td nowrap="nowrap" width="120px">
											${depot.productDeptName}
										</td>
										<td nowrap="nowrap">
											<a href="${ctx}/depot.do?method=depotUpdate&id=${depot.id}&mark=view">查看</a>
											<a href="${ctx}/depot.do?method=depotUpdate&id=${depot.id}">修改</a>
										</td>
									</tr>
								</logic:iterate>
							</logic:present>
						</table>
						<br />
						<table border="0" cellpadding="0" cellspacing="0" width="100%"
							id="ec_table">
							<tr>
								<td width="30px" align="left">
									&nbsp;&nbsp;<input type="checkbox" name="zong" onclick="che(this);" />
								</td>
								<td width="50px">
									全选
								</td>
								<td width="100px">
									<a href="javascript:shan();"> <img
											src="${ctx}/images/btnDelete.gif" /> </a>
								</td>
								<td width="100px">
									<a
										href="javascript:window.location ='${ctx}/depot.do?method=showAddDepot';">
										<img src="${ctx}/images/btnAdd.gif" /> </a>
								</td>
								<td align="right">
									<%@ include file="/jsp/common/page.jsp"%>
								</td>
							</tr>
						</table>
					</form>
				</td>
				<td>&nbsp;
					
				</td>
			</tr>
		</table>
	</body>
</html>
