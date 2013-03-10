<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>库房管理</title>
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
		<script type="text/javascript" src="${ctx}/js/base.js"></script>
		<script type="text/javascript" src="${ctx}/js/util.js"></script>
		<script type="text/javascript">
		function document.onkeydown(){                 
		     if(event.keyCode==13){
		         event.keyCode=0;  
		         return false;                               
		    }
		} 
		var formId = 0;
		//控件名	
		var checknNames = ["depot.name","depot.productDeptId"];
		//提示语
		var descriptions = [ "库房名称","产品部门"];
		//是否非空验证,如果非空验证填写notnull,如果不需要非空验证传空参
		var checkNulls = [ "notnull","notnull"];
		//判断类型验证，只填数字验证填"num",    只填字母验证“abc”,    email验证“email”，非零开头的数字和零 "0num",  邮箱验证 "zip" ,登录名验证  “loginname” ,电话 “phone”
		var checkTypes = [ "",""];
		//判断长度验证 如果判断是否等于  参数前面加0, 否则正常  不需要长度验证则传空参
		var checkLengths = [ "40",""];
		function check(){
		if(checkForm()==false){
			return ;
			}
			document.forms[0].submit();
		};
		function checks(){
			var err = document.getElementById("err").value;
			if(err==null||err==""){
				
			} 
			else if(err!= null){
				alert(err);
				}
		}
	
	//进入新增发货地址页面
	function addAddress(){
	var depotId = document.getElementById("depot.id").value;
	window.open('${ctx}/depot.do?method=showAddressAdd&depotId='+depotId,'newwindow', 'toolbar=no,scrollbars=yes,resizable=no,top=200,left=400, width=700,height=200');
	}
	//删除
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
		//删除客户发货地址
		function deleteAddress(){
		 if(myArray.length==0){
				alert("请选择删除项！");
			}else if (confirm("是否确认删除？")) {
				document.forms[0].action='${ctx}/depot.do?method=deleteAddress&sxf='+ myArray.join(',');
				document.forms[0].submit();
			}
		};
	</script>
	</head>

	<body onload="checks();">
		<html:form action="/depot" method="post">
			<input type="hidden" name="method" value="saveDepot" />
			<input type="hidden" name="depot.type" value="${depotInfo.type}" />
			<input type="hidden" name="depot.id" value="${depotInfo.id}"
				id="depot.id" />
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
									<logic:equal value="1" name="depotInfo" property="type">
										正常库
										</logic:equal>
											<logic:equal value="2" name="depotInfo" property="type">
										索赔库
										</logic:equal>
								</td>
								<td class="td_01" width="20%">
									<span style="color:#FF0000">*</span>&nbsp;产品部门名称
								</td>
								<td class="td_02" width="30%">
									<select name="depot.productDeptId" id="productDeptId"
										style="width:126px">
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
							<tr>
							</tr>
							<tr>
								<td class="td_01">
									<span style="color:#FF0000">*</span>&nbsp;库房名称
								</td>
								<td class="td_02">
									<input type="text" name="depot.name" id="depot.name" maxlength="20"
										value="${depotInfo.shortName}" style="width:240px;" />
								</td>
								<td class="td_01">
								</td>
								<td class="td_02">
								</td>
							</tr>
						</table>
						<br />
						<div class="div_tiao">
							&nbsp;&nbsp;收货地址信息
						</div>
						<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="xxxlist">
							<tr>
								<th nowrap="nowrap" width="41px">
									选择
								</th>
								<th nowrap="nowrap" width="240px">
									货物接收单位名称
								</th>
								<th nowrap="nowrap">
									发货地址
								</th>
								<th nowrap="nowrap" width="36px">
									邮编
								</th>
								<th nowrap="nowrap" width="48px">
									联系人
								</th>
								<th nowrap="nowrap" width="120px">
									电话
								</th>
								<th nowrap="nowrap" width="72px">
									手机
								</th>
								<th nowrap="nowrap" width="40px">
									操作
								</th>
							</tr>
							<logic:present name="addressList">
								<logic:iterate id="address" name="addressList">
									<tr>
										<td style="width:30px">
											&nbsp;<input name="ss" type="checkbox"
												value="${address.receiveId }"
												onclick="javascript:removeBefore(this);" />
										</td>
										<td nowrap="nowrap">
											${address.receiveName}&nbsp;
										</td>
										<td nowrap="nowrap">
											${address.supplierAddress }
										</td>
										<td nowrap="nowrap">
											${address.supplierPostcode}&nbsp;
										</td>
										<td nowrap="nowrap">
											${address.supplierLinkman }&nbsp;
										</td>
										<td nowrap="nowrap">
											${address.supplierTel}&nbsp;
										</td>
										<td nowrap="nowrap">
											${address.supplierMobile }&nbsp;
										</td>
										<td nowrap="nowrap">
											<a href="#"
												onclick="javascript:window.open('${ctx}/depot.do?method=showAddressUpdate&addressId='+${address.receiveId },'newwindow', 'toolbar=no,scrollbars=yes,resizable=no,top=200,left=400, width=700,height=200');">修改</a>
										</td>
									</tr>
								</logic:iterate>
							</logic:present>
						</table>
						<br/>
						<table border="0" cellpadding="0" cellspacing="0" width="100%"
							id="ec_table">
							<tr>
								<td width="30px" align="left">&nbsp;&nbsp;<input name="input2" type="checkbox" onclick="che(this);" />
								</td>
								<td width="30px">
									全选
								</td>
								<td width="100px">
									<a href="#"><img src="${ctx}/images/btnDelete.gif"
										onclick="deleteAddress();" />
									</a>
								</td>
								<td width="100px">
									<a href="#" onclick="addAddress();"><img
											src="${ctx}/images/btnAdd.gif" /> </a>
								</td>
								<td align="right">
									&nbsp;&nbsp;
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
