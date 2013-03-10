<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>库房修改</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
		<script type="text/javascript" src="${ctx}/js/base.js"></script>
		<script type="text/javascript">
		function document.onkeydown(){                 
		     if(event.keyCode==13){
		         event.keyCode=0;  
		         return false;                               
		    }
		} 
		function checks(){
			var err = document.getElementById("err").value;
			if(err==null||err==""){
				
			} 
			else if(err!= null){
				alert(err);
				}
		}
		
		function check(){
	   document.forms[0].submit();
	}
		function selectDept(name)
        { 
         var deptName=name.options[name.selectedIndex].text; 
         document.getElementById("deptAndName").innerHTML=deptName+"虚拟库";
        }
		
	</script>
	</head>

	<body onload="checks();">
		<html:form action="/depot" method="post">
			<input type="hidden" name="method" value="saveDepot" />
			<input type="hidden" name="depot.type" value="${depotInfo.type}" />
			<input type="hidden" name="depot.id" value="${depotInfo.id}"
				id="depot.id" />
			<input type="hidden" name="depot.name" value="${depotInfo.name}" />
			<input type="hidden" name="err" value="${err}" id="err"></input>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td class="ye_header_left">&nbsp;
						
					</td>
					<td class="ye_header_center">
						<img src="${ctx}/images/main_jt.jpg" />
						&nbsp;当前位置： 基础信息管理 &gt;&gt; 库房信息管理 &gt;&gt; 库房修改
					</td>
					<td class="ye_header_right">&nbsp;
						
					</td>
				</tr>
				<tr>
					<td>&nbsp;
						
					</td>
					<td align="center">
						<br />
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="biao3">
							<tr>
								<td class="td_01" width="20%">
									库房类型
								</td>
								<td class="td_02" width="30%">
									虚拟库
								</td>
								<td class="td_01" width="20%">
									<span style="color:#FF0000">*</span>&nbsp;产品部门选择
								</td>
								<td class="td_02" width="30%">
									<select name="depot.productDeptId" id="productDeptId"
										style="width:126px" onchange="selectDept(this);">
										<logic:present name="productDeptList">
											<logic:iterate id="productDept" name="productDeptList">
												<logic:equal value="${productDept.id}" name="depotInfo"
													property="productDeptId">
													<option value="${productDept.id}" selected="selected">
														${productDept.name}
													</option>
												</logic:equal>
												<logic:notEqual value="${productDept.id}" name="depotInfo"
													property="productDeptId">
													<option value="${productDept.id}">
														${productDept.name}
													</option>
												</logic:notEqual>
											</logic:iterate>
										</logic:present>
									</select>
								</td>
							</tr>
						</table>
						<table align="center">
							<tr>
								<td height="50px" valign="bottom">
									<a href="#"><img src="${ctx}/images/btnUpdate.gif" width="65" height="20"
										onclick="check();" /></a>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<a href="${ctx}/depot.do?method=depotAll"><img
											src="${ctx}/images/btnBack.gif" /> </a>
								</td>
							</tr>
						</table>
					</td>
					<td>&nbsp;
						
					</td>
				</tr>
			</table>
		</html:form>
	</body>
</html>
