<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>供货商修改</title>
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
				if($("#table1")){
					$("#table1 tr:even").addClass("treven");
					$("#table1 tr").each(function(i){
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
		<script type="text/javascript" src="${ctx}/js/ajax.js"></script>
		<script type="text/javascript" src="${ctx}/js/util.js"></script>
		<script type="text/javascript">
	var currentSort;
//下拉列表项改变时的操作
	function proChange(objVal){
	 createXMLHttpRequest();
	 document.getElementById("supplier.city").length=1;
	 XMLHttpReq.onreadystatechange=getAjaxStatus;
	 var url = "${ctx}/logistics.do?method=getCitysByProvinceId&provinceId="+encodeURI(objVal,"UTF-8");
	 XMLHttpReq.open("GET",url,true);
	 XMLHttpReq.send(null);
	}
//解析XML信息，添加城市
	function parseXML(xmlDoc){
	var cityName = xmlDoc.getElementsByTagName("cityName");
	var areaName = "";
	var citySel = document.getElementById("supplier.city");
	for(var i=0;i<cityName.length;i++){
	var opt = document.createElement("OPTION");
	opt.text = xmlDoc.getElementsByTagName("cityName")[i].firstChild.data;
	opt.value=xmlDoc.getElementsByTagName("cityName")[i].firstChild.data;
	citySel.add(opt);
	areaName = xmlDoc.getElementsByTagName("areaName")[0].firstChild.data;
	}
	document.getElementById("supplier.area").innerHTML=areaName;
	}
		
	var formId = 0;
	//控件名
	var checknNames = [ "supplier.name", "supplier.province", "supplier.city","supplier.address","supplier.invoiceType","supplier.invoiceBankName","supplier.taxNumber","supplier.invoiceBankAccount","supplier.invoiceBankTel","supplier.invoiceBankAddress","supplier.remitBankName","supplier.remitBankAccount"];
	//提示语
	var descriptions = [ "供货商名称", "省", "市", "通信地址","发票类型","开户行","税号","帐号","电话","地址","汇款银行名称","汇款银行帐号"];
	//是否非空验证,如果非空验证填写notnull,如果不需要非空验证传空参
	var checkNulls = [ "notnull", "notnull", "notnull", "notnull" ,"notnull","null","null","null","null","null","notnull","notnull"];
	//判断类型验证，只填数字验证填"num",    只填字母验证“abc”,    email验证“email”，非零开头的数字和零 "0num",  邮箱验证 "zip" ,登录名验证  “loginname” ,电话 “phone”
	var checkTypes = [ "", "", "", "","", "", "", "","phone","", "", ""];
	//判断长度验证 如果判断是否等于  参数前面加0, 否则正常  不需要长度验证则传空参
	var checkLengths = [ "80", "", "", "80" ,"", "40", "20", "40" ,"20","80", "40", "40"];
	function check(){
	var invoiceType= document.getElementById('supplier.invoiceType').value;
		if(checkForm()==false){
			return ;
			}
		else if(invoiceType==1){
			var taxRate= document.getElementById('supplier.taxRate').value;
			if(taxRate==''){
				alert('必填项不能为空！');
				return;
			}
		}
	   	document.forms[0].submit();
	}
	
	//进入新增联系人页面
	function addLinkman(){
	var supplierId = document.getElementById("supplier.id").value;
	var supplierName = document.getElementById("supplier.name").value;
	window.open('${ctx}/supplier.do?method=showLinkmanAdd&supplierId='+supplierId+'&suppliername='+encodeURI(supplierName,"UTF-8"),'newwindow', 'toolbar=no,scrollbars=yes,resizable=no,top=200,left=400, width=700,height=200');
	}
	//进入新增发货地址页面
	function addAddress(){
	var supplierId = document.getElementById("supplier.id").value;
	var supplierName = document.getElementById("supplier.name").value;
	window.open('${ctx}/supplier.do?method=showAddressAdd&supplierId='+supplierId+'&suppliername='+encodeURI(supplierName,"UTF-8"),'newwindow', 'toolbar=no,scrollbars=yes,resizable=no,top=200,left=400, width=700,height=200');
	}
	//根据选择的发票类型，显示增值税率的显示情况
	function selectTaxRate(objVal){
	   if(objVal==0){
	     document.getElementById("rate1").style.display="none";
	     document.getElementById("rate2").style.display="none";
	     document.getElementById("rate3").style.display="block";
	     document.getElementById("rate4").style.display="block";
	   }else if(objVal==1){
	      document.getElementById("rate1").style.display="block";
	      document.getElementById("rate2").style.display="block";
	      document.getElementById("rate3").style.display="none";
	      document.getElementById("rate4").style.display="none";
	   }
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
		//删除供货商联系人
		function deleteLinkman(){
		var supplierId = document.getElementById("supplier.id").value;
		 if(myArray.length==0){
				alert("请选择删除项！");
			}else if (confirm("是否确认删除？")) {
				window.location = '${ctx}/supplier.do?method=deleteLinkman&supplierId='+supplierId+'&sxf='+ myArray.join(',');
			}else{
			}
		};
		//删除供货商发货地址
		function deleteAddress(){
		
		var supplierId = document.getElementById("supplier.id").value;
		 if(myArray.length==0){
				alert("请选择删除项！");
			}else if (confirm("是否确认删除？")) {
				window.location = '${ctx}/supplier.do?method=deleteAddress&supplierId='+supplierId+'&sxf='+ myArray.join(',');
			}else{
			}
		};
		
		function checks(){
		var err = document.getElementById("err").value;
		if(err==null||err==""){

		} 
		else if(err!= null){
			alert("修改失败！");
			return false;
		}
	}
</script>
	</head>

	<body onload='checks();'>
		<input type="hidden" name="err" value="${err}" id="err"></input>
		<html:form action="/supplier.do?method=saveSupplier" method="post">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td class="ye_header_left">&nbsp;
						
					</td>
					<td class="ye_header_center">
						<img src="${ctx}/images/main_jt.jpg" />
						&nbsp;当前位置： 基础信息管理 &gt;&gt; 供货商信息管理  &gt;&gt; 供货商修改
					</td>
					<td class="ye_header_right">&nbsp;
						
					</td>
				</tr>
				<tr>
					<td>&nbsp;
						
					</td>
					<td>
						<br />
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="biao3">
							<tr>
								<td class="td_01" width="20%">
									<span style="color:#FF0000">*</span>
									<span class="ye_header_center"></span>供货商名称
								</td>
								<td class="td_02" width="30%">
									<input type="text" name="supplier.name" id="supplier.name"
										value="${supplierInfo.name }" style="width:240px;" maxlength="40"/>
									<input type="hidden" name="supplier.id" id="supplier.id"
										value="${supplierInfo.id }" style="width:240px;" />

								</td>
								<td class="td_01" width="20%">
									区域
								</td>
								<td class="td_02" width="30%" id="supplier.area">
									${supplierInfo.area }
								</td>
							</tr>
							<tr>
								<td class="td_01">
									<span style="color:#FF0000">*</span>&nbsp;省
								</td>
								<td class="td_02">
									<select name="supplier.province" id="supplier.province"
										onchange="proChange(this.value);" style=" width:126px">
										<option value="" selected="selected">
											--请选择--
										</option>

										<logic:present name="provinceList">

											<logic:iterate id="province" name="provinceList">
												<logic:equal value="${province.name}" name="supplierInfo"
													property="province">
													<option value="${province.name}" selected="selected">
														${province.name}
													</option>
												</logic:equal>
												<logic:notEqual value="${province.name}" name="supplierInfo"
													property="province">
													<option value="${province.name}">
														${province.name}
													</option>
												</logic:notEqual>
											</logic:iterate>
										</logic:present>

									</select>
								</td>
								<td class="td_01">
									<span style="color:#FF0000">*</span>&nbsp;市
								</td>
								<td class="td_02">
									<select name="supplier.city" id="supplier.city"
										style=" width:126px">
										<option value="" selected="selected">
											--请选择--
										</option>
										<logic:present name="cityList">
											<logic:iterate id="cityInfo" name="cityList">
												<logic:equal value="${cityInfo.name}" name="supplierInfo"
													property="city">
													<option value="${cityInfo.name}" selected="selected">
														${cityInfo.name}
													</option>
												</logic:equal>
												<logic:notEqual value="${cityInfo.name}" name="supplierInfo"
													property="city">
													<option value="${cityInfo.name}">
														${cityInfo.name}
													</option>
												</logic:notEqual>
											</logic:iterate>
										</logic:present>
									</select>
								</td>
							</tr>
							<tr>
								<td class="td_01">
									<span style="color:#FF0000">*</span>&nbsp;通信地址
								</td>
								<td class="td_02">
									<input type="text" name="supplier.address"
										id="supplier.address" style="width:240px;" maxlength="40"
										value="${supplierInfo.address }" />
								</td>
								<td class="td_01">
									邮编
								</td>
								<td class="td_02">
									<input type="text" name="supplier.postcode"
										id="supplier.postcode" style="width:120px;"  maxlength="6"
										value="${supplierInfo.postcode }" />
								</td>
							</tr>
						</table>
						<br />
						<div class="div_tiao">
							&nbsp;&nbsp;联系人信息
						</div>
						<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="xxxlist">
							<tr>
								<th nowrap="nowrap" width="41">
									选择
								</th>
								<th nowrap="nowrap">
									姓名
								</th>
								<th nowrap="nowrap">
									职务
								</th>
								<th nowrap="nowrap">
									电话
								</th>
								<th nowrap="nowrap">
									传真
								</th>
								<th nowrap="nowrap">
									手机
								</th>
								<th nowrap="nowrap">
									Email
								</th>
								<th nowrap="nowrap">
									MSN
								</th>
								<th nowrap="nowrap">
									QQ
								</th>
								<th nowrap="nowrap" width="40">
									操作
								</th>
							</tr>
							<logic:present name="linkManList">
								<logic:iterate id="linkMan" name="linkManList">
									<tr>
										<td nowrap="nowrap" >
										&nbsp;<input name="ss" type="checkbox" value="${linkMan.linkmanId }" onclick="javascript:removeBefore(this);" />
										</td>
										<td nowrap="nowrap">
											${linkMan.linkman }
										</td>
										<td nowrap="nowrap" width="120px">
											${linkMan.role }&nbsp;
										</td>
										<td nowrap="nowrap" width="120px">
											${linkMan.tel }&nbsp;
										</td>
										<td nowrap="nowrap" width="120px">
											${linkMan.fax }&nbsp;
										</td>
										<td nowrap="nowrap" width="72px">
											${linkMan.mobile }&nbsp;
										</td>
										<td nowrap="nowrap" width="180px">
											${linkMan.mail}&nbsp;
										</td>
										<td nowrap="nowrap" width="180px">
											${linkMan.msn}&nbsp;
										</td>
										<td nowrap="nowrap" width="72px">
											${linkMan.qq}&nbsp;
										</td>
										<td nowrap="nowrap">
											<a href="#"
												onclick="javascript:window.open('${ctx}/supplier.do?method=showLinkmanUpdate&linkmanId='+${linkMan.linkmanId},'newwindow', 'toolbar=no,scrollbars=yes,resizable=no,top=200,left=400, width=700,height=200');">修改</a>
										</td>
									</tr>
								</logic:iterate>
							</logic:present>
						</table>
						<br />
						<table border="0" cellpadding="0" cellspacing="0" width="100%"
							id="ec_table2">
							<tr>
								<td width="30px" align="left" >
									&nbsp;&nbsp;<input name="zong" type="checkbox" onclick="che(this);" />
								</td>
								<td width="50px">
									全选
								</td>
								<td width="100px">
									<a href="#"><img src="${ctx}/images/btnDelete.gif"
										onclick="deleteLinkman();" /></a>
								</td>
								<td width="100px">
									<a href="#" onclick="addLinkman();"> <img
											src="${ctx}/images/btnAdd.gif" /> </a>
								</td>
								<td align="right">&nbsp;
									
								</td>
							</tr>
						</table>
						<br />
						<div class="div_tiao">
							&nbsp;&nbsp;开票信息
						</div>
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="biao3">
							<tr>
								<td class="td_01" width="20%">
									<span style="color:#FF0000">*</span>&nbsp;发票类型
								</td>


								<td class="td_02" width="30%">
									<select name="supplier.invoiceType" id="supplier.invoiceType"
										style=" width:126px" onchange="selectTaxRate(this.value);">
										<logic:equal value="0" name="supplierInfo"
											property="invoiceType">
											<option value="0" selected="selected">
												普通
											</option>
											<option value="1">
												增值税
											</option>
										</logic:equal>
										<logic:equal value="1" name="supplierInfo"
											property="invoiceType">
											<option value="0">
												普通
											</option>
											<option value="1" selected="selected">
												增值税
											</option>
										</logic:equal>
									</select>
								</td>
								<logic:equal value="1" name="supplierInfo"
									property="invoiceType">
									<td class="td_01" id="rate1" style="display:block;" width="20%">
										<span style="color:#FF0000">*</span>&nbsp;增值税税率
									</td>
									<td class="td_02" id="rate2" style="display:block;" width="30%">
										<input type="text" name="supplier.taxRate"
											id="supplier.taxRate" style="width:120px;" maxlength="2"
											value="${supplierInfo.taxRate }" />&nbsp;%
									</td>
									<td class="td_01" id="rate3" style="display:none;">
										<span style="color:#FF0000"></span>
									</td>
									<td class="td_02" id="rate4" style="display:none;">
									</td>
								</logic:equal>
								<logic:equal value="0" name="supplierInfo"
									property="invoiceType">
									<td class="td_01" id="rate1" style="display:none;">
										<span style="color:#FF0000">*</span>&nbsp;增值税税率
									</td>
									<td class="td_02" id="rate2" style="display:none;">
										<input type="text" name="supplier.taxRate"
											id="supplier.taxRate" style="width:120px;"
											value="${supplierInfo.taxRate }" /><span style="color:#FF0000">%</span>
									</td>
									<td class="td_01" id="rate3" style="display:block;">
										<span style="color:#FF0000"></span>
									</td>
									<td class="td_02" id="rate4" style="display:block;">
									</td>
								</logic:equal>
							</tr>
							<tr>
								<td class="td_01">
									开户行
								</td>
								<td class="td_02">
									<input type="text" name="supplier.invoiceBankName"
										id="supplier.invoiceBankName" style="width:240px;" maxlength="20"
										value="${supplierInfo.invoiceBankName }" />
								</td>
								<td class="td_01">
									税号
								</td>
								<td class="td_02">
									<input type="text" name="supplier.taxNumber"
										id="supplier.taxNumber" style="width:120px;" maxlength="20"
										value="${supplierInfo.taxNumber }" />
								</td>

							</tr>
							<tr>
								<td class="td_01">
									帐号
								</td>
								<td class="td_02">
									<input type="text" name="supplier.invoiceBankAccount"
										id="supplier.invoiceBankAccount" style="width:120px;" maxlength="40"
										value="${supplierInfo.invoiceBankAccount }" />
								</td>
								<td class="td_01">
									电话
								</td>
								<td class="td_02">
									<input type="text" name="supplier.invoiceBankTel"
										id="supplier.invoiceBankTel" style="width:120px;" maxlength="20"
										value="${supplierInfo.invoiceBankTel }" />
								</td>
							</tr>
							<tr>
								<td class="td_01">
									地址
								</td>
								<td class="td_02">
									<input type="text" name="supplier.invoiceBankAddress"
										id="supplier.invoiceBankAddress" style="width:240px;" maxlength="40"
										value="${supplierInfo.invoiceBankAddress }" />
								</td>
								<td class="td_01">
									<span style="color:#FF0000"></span>&nbsp;
								</td>
								<td class="td_02">
								</td>
							</tr>
						</table>
						<br />
						<div class="div_tiao">
							&nbsp;&nbsp;汇款信息
						</div>
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="biao3">
							<tr>
								<td class="td_01" width="20%" >
								<span style="color:#FF0000">*</span>
									开户行
								</td>
								<td class="td_02" width="30%">
									<input type="text" name="supplier.remitBankName"
										id="supplier.remitBankName" style="width:240px;" maxlength="20"
										value="${supplierInfo.remitBankName }" />
								</td>
								<td class="td_01" width="20%">
								<span style="color:#FF0000">*</span>
									帐号
								</td>
								<td class="td_02" width="30%">
									<input type="text" name="supplier.remitBankAccount"
										id="supplier.remitBankAccount" style="width:120px;" maxlength="40"
										value="${supplierInfo.remitBankAccount }" />
								</td>
							</tr>
						</table>
						<br />
						<div class="div_tiao">
							&nbsp;&nbsp;发货信息
						</div>
						<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table1">
							<tr>
								<th nowrap="nowrap" width="41">
									选择
								</th>
								<th nowrap="nowrap">
									货物接收单位名称
								</th>
								<th nowrap="nowrap">
									发货地址
								</th>
								<th nowrap="nowrap">
									邮编
								</th>
								<th nowrap="nowrap">
									联系人
								</th>
								<th nowrap="nowrap">
									电话
								</th>
								<th nowrap="nowrap">
									手机
								</th>
								<th nowrap="nowrap" width="40">
									操作
								</th>
							</tr>
							<logic:present name="addressList">
								<logic:iterate id="address" name="addressList">
									<tr>
										<td nowrap="nowrap">
											&nbsp;<input name="ss1" type="checkbox"
												value="${address.receiveId }"
												onclick="javascript:removeBefore(this);" />
										</td>
										<td nowrap="nowrap" width="240px">
											${address.receiveName}&nbsp;
										</td>
										<td nowrap="nowrap">
											${address.supplierAddress }
										</td>
										<td nowrap="nowrap" width="36px">
											${address.supplierPostcode}&nbsp;
										</td>
										<td nowrap="nowrap" width="48px">
											${address.supplierLinkman }&nbsp;
										</td>
										<td nowrap="nowrap" width="120px">
											${address.supplierTel}&nbsp;
										</td>
										<td nowrap="nowrap" width="72px">
											${address.supplierMobile }&nbsp;
										</td>
										<td nowrap="nowrap">
											<a href="#"
												onclick="javascript:window.open('${ctx}/supplier.do?method=showAddressUpdate&addressId='+${address.receiveId },'newwindow', 'toolbar=no,scrollbars=yes,resizable=no,top=200,left=400, width=700,height=200');">修改</a>
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
									&nbsp;&nbsp;<input name="input2" type="checkbox" onclick="che1(this);" />
								</td>
								<td width="50px">
									全选
								</td>
								<td width="100px">
									<a href="#"><img src="${ctx}/images/btnDelete.gif"
										onclick="deleteAddress();" /></a>
								</td>
								<td width="100px">
									<a href="#" onclick="addAddress();"><img
											src="${ctx}/images/btnAdd.gif" /> </a>
								</td>
								<td align="right">&nbsp;
									
								</td>
							</tr>
						</table>
					</td>
					<td>&nbsp;
						
					</td>
				</tr>
				<tr>
					<td></td>
					<td height="20px" align="center" valign="bottom">
						<a href="#"><img src="${ctx}/images/btnUpdate.gif" width="65" height="20"
							onclick="check();" /></a>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="${ctx}/supplier.do?method=supplierAll"><img
								src="${ctx}/images/btnBack.gif" /> </a>
					</td>
					<td></td>
				</tr>
			</table>
			<br />
		</html:form>
	</body>
</html>
