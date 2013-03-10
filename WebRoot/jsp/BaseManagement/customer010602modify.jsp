<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>客户修改</title>
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
	//var len = xmlDoc.getElementsByTagName("cityId");
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
	var descriptions = [ "客户名称", "省", "市", "通信地址","发票类型","开户行","税号","帐号","电话","地址","汇款银行名称","汇款银行帐号"];
	//是否非空验证,如果非空验证填写notnull,如果不需要非空验证传空参
	var checkNulls = [ "notnull", "notnull", "notnull", "notnull" ,"notnull","null","null","null","null","null","null","null"];
	//判断类型验证，只填数字验证填"num",    只填字母验证“abc”,    email验证“email”，非零开头的数字和零 "0num",  邮箱验证 "zip" ,登录名验证  “loginname” ,电话 “phone”
	var checkTypes = [ "", "", "", "","", "", "", "","phone","", "", ""];
	//判断长度验证 如果判断是否等于  参数前面加0, 否则正常  不需要长度验证则传空参
	var checkLengths = [ "80", "", "", "80" ,"", "40", "20", "40" ,"20","80", "40", "40"];
	
	function check(){
	if(checkForm()==false){
			return ;
			}
	   document.forms[0].submit();
	}
	
	//进入新增联系人页面
	function addLinkman(){
	var supplierId = document.getElementById("supplier.id").value;
	var supplierName = document.getElementById("supplier.name").value;
	window.open('${ctx}/customer.do?method=showLinkmanAdd&supplierId='+supplierId+'&suppliername='+encodeURI(supplierName,"UTF-8"),'newwindow', 'toolbar=no,scrollbars=yes,resizable=no,top=200,left=400, width=700,height=200');
	}
	//进入新增发货地址页面
	function addAddress(){
	var supplierId = document.getElementById("supplier.id").value;
	var supplierName = document.getElementById("supplier.name").value;
	window.open('${ctx}/customer.do?method=showAddressAdd&supplierId='+supplierId+'&suppliername='+encodeURI(supplierName,"UTF-8"),'newwindow', 'toolbar=no,scrollbars=yes,resizable=no,top=200,left=400, width=700,height=200');
	}
	//根据选择的发票类型，显示增值税率的显示情况
	function selectTaxRate(objVal){
		if(objVal==0){
	      document.getElementById("rate1").style.display="block";
	      document.getElementById("rate2").style.display="none";
	      document.getElementById("rate3").style.display="block";
	      document.getElementById("rate4").style.display="none";
	      document.getElementById("rate5").style.display="block";
	      document.getElementById("rate6").style.display="none";
	      document.getElementById("rate7").style.display="block";
	      document.getElementById("rate8").style.display="none";
	      document.getElementById("rate9").style.display="block";
	      document.getElementById("rate10").style.display="none";
	      
	      checkNulls = [ "notnull", "notnull", "notnull", "notnull" ,"notnull","null","null","null","null","null","null","null"];
	   }else if(objVal==1){
	      document.getElementById("rate1").style.display="none";
	      document.getElementById("rate2").style.display="block";
	      document.getElementById("rate3").style.display="none";
	      document.getElementById("rate4").style.display="block";
	      document.getElementById("rate5").style.display="none";
	      document.getElementById("rate6").style.display="block";
	      document.getElementById("rate7").style.display="none";
	      document.getElementById("rate8").style.display="block";
	      document.getElementById("rate9").style.display="none";
	      document.getElementById("rate10").style.display="block";
	      
	      checkNulls = [ "notnull", "notnull", "notnull", "notnull" ,"notnull","notnull","notnull","notnull","notnull","notnull","null","null"];
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
		//删除客户联系人
		function deleteLinkman(){
		var supplierId = document.getElementById("supplier.id").value;
		 if(myArray.length==0){
				alert("请选择删除项！");
			}else if (confirm("是否确认删除？")) {
				window.location = '${ctx}/customer.do?method=deleteLinkman&supplierId='+supplierId+'&sxf='+ myArray.join(',');
			}else{
			}
		};
		//删除客户发货地址
		function deleteAddress(){
		
		var supplierId = document.getElementById("supplier.id").value;
		 if(myArray.length==0){
				alert("请选择删除项！");
			}else if (confirm("是否确认删除？")) {
				window.location = '${ctx}/customer.do?method=deleteAddress&supplierId='+supplierId+'&sxf='+ myArray.join(',');
			}else{
			}
		};
		function checks(){
			var err = document.getElementById("err").value;
			if(err==null||err==""){
				
			} else if(err!= null){
				alert(err);
			}
		}
</script>
	</head>

	<body onload="checks();">
		<html:form action="/customer.do?method=saveSupplier" method="post">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td class="ye_header_left">&nbsp;
						
					</td>
					<td class="ye_header_center">
						<img src="${ctx}/images/main_jt.jpg" />
						&nbsp;当前位置： 基础信息管理 &gt;&gt; 客户信息管理  &gt;&gt; 客户修改
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
									<span style="color:#FF0000">*</span>&nbsp;
									<span class="ye_header_center"></span>客户名称
								</td>
								<td class="td_02" width="30%">
									<input type="text" name="supplier.name" id="supplier.name"
										value="${supplierInfo.name }" style="width:240px;" maxlength="40"/>
									<input type="hidden" name="supplier.id" id="supplier.id"
										value="${supplierInfo.id }" style="width:240px;" />
									<input type="hidden" name="err" value="${err}" id="err"></input>
								</td>
								<td class="td_01" width="20%">
									区域
								</td>
								<td class="td_02" width="30%" id="supplier.area">
									${supplierInfo.area}
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
										id="supplier.address" style="width:240px;"
										value="${supplierInfo.address }" maxlength="40"/>
								</td>
								<td class="td_01">
									邮编
								</td>
								<td class="td_02">
									<input type="text" name="supplier.postcode"
										id="supplier.postcode" style="width:120px;"
										value="${supplierInfo.postcode }" maxlength="6"/>
								</td>
							</tr>
						</table>
						<br />
						<div class="div_tiao">
							&nbsp;&nbsp;联系人信息
						</div>
						<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table1">
							<tr>
								<th nowrap="nowrap" width="41">
									选择
								</th>
								<th nowrap="nowrap">
									姓名
								</th>
								<th nowrap="nowrap" width="120">
									职务
								</th>
								<th nowrap="nowrap" width="120">
									电话
								</th>
								<th nowrap="nowrap" width="120">
									传真
								</th>
								<th nowrap="nowrap" width="72">
									手机
								</th>
								<th nowrap="nowrap" width="180">
									Email
								</th>
								<th nowrap="nowrap" width="180">
									MSN
								</th>
								<th nowrap="nowrap" width="72">
									QQ
								</th>
								<th nowrap="nowrap" width="40">
									操作
								</th>
							</tr>
							<logic:present name="linkManList">
								<logic:iterate id="linkMan" name="linkManList">
									<tr>
										<td nowrap="nowrap">
											&nbsp;<input name="ss" type="checkbox" value="${linkMan.linkmanId }" onclick="javascript:removeBefore(this);" />
										</td>
										<td nowrap="nowrap">
											${linkMan.linkman }
										</td>
										<td nowrap="nowrap">
											${linkMan.role }&nbsp;
										</td>
										<td nowrap="nowrap">
											${linkMan.tel }&nbsp;
										</td>
										<td nowrap="nowrap">
											${linkMan.fax }&nbsp;
										</td>
										<td nowrap="nowrap">
											${linkMan.mobile }&nbsp;
										</td>
										<td nowrap="nowrap">
											${linkMan.mail}&nbsp;
										</td>
										<td nowrap="nowrap">
											${linkMan.msn}&nbsp;
										</td>
										<td nowrap="nowrap">
											${linkMan.qq}&nbsp;
										</td>
										<td nowrap="nowrap">
											<a href="#" onclick="javascript:window.open('${ctx}/customer.do?method=showLinkmanUpdate&linkmanId='+${linkMan.linkmanId},'newwindow', 'toolbar=no,scrollbars=yes,resizable=no,top=200,left=400, width=700,height=200');">修改</a>
										</td>
									</tr>
								</logic:iterate>
							</logic:present>
						</table>
						<br />
						<table border="0" cellpadding="0" cellspacing="0" width="100%"
							id="ec_table2">
							<tr>
								<td width="41px" align="left">
									&nbsp;
									<input name="zong" type="checkbox" onclick="che(this);" />
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
								<logic:equal value="0" name="supplierInfo"
									property="invoiceType">
									<td class="td_01" id="rate1" style="display:block;">
										税号
									</td>
									<td class="td_01" id="rate2" style="display:none;">
										<span style="color:#FF0000">*</span>&nbsp;税号
									</td>
								</logic:equal>
								<logic:equal value="1" name="supplierInfo"
									property="invoiceType">
									<td class="td_01" id="rate1" style="display:none;">
										税号
									</td>
									<td class="td_01" id="rate2" style="display:block;">
										<span style="color:#FF0000">*</span>&nbsp;税号
									</td>
								</logic:equal>
								<td class="td_02">
									<input type="text" name="supplier.taxNumber"
										id="supplier.taxNumber" style="width:120px;" value="${supplierInfo.taxNumber}" maxlength="20"/>
								</td>
							</tr>
							<tr>
								<logic:equal value="0" name="supplierInfo"
									property="invoiceType">
									<td class="td_01" id="rate3" style="display:block;">
										开户行
									</td>
									<td class="td_01" id="rate4" style="display:none;">
										<span style="color:#FF0000">*</span>&nbsp;开户行
									</td>
								</logic:equal>
								<logic:equal value="1" name="supplierInfo"
									property="invoiceType">
									<td class="td_01" id="rate3" style="display:none;">
										开户行
									</td>
									<td class="td_01" id="rate4" style="display:block;">
										<span style="color:#FF0000">*</span>&nbsp;开户行
									</td>
								</logic:equal>
								<td class="td_02">
									<input type="text" name="supplier.invoiceBankName"
										id="supplier.invoiceBankName" style="width:240px;"
										value="${supplierInfo.invoiceBankName }" maxlength="20"/>
								</td>
									<logic:equal value="0" name="supplierInfo"
									property="invoiceType">
									<td class="td_01" id="rate5" style="display:block;">
										帐号
									</td>
									<td class="td_01" id="rate6" style="display:none;">
										<span style="color:#FF0000">*</span>&nbsp;帐号
									</td>
								</logic:equal>
								<logic:equal value="1" name="supplierInfo"
									property="invoiceType">
									<td class="td_01" id="rate5" style="display:none;">
										帐号
									</td>
									<td class="td_01" id="rate6" style="display:block;">
										<span style="color:#FF0000">*</span>&nbsp;帐号
									</td>
								</logic:equal>
								<td class="td_02">
									<input type="text" name="supplier.invoiceBankAccount"
										id="supplier.invoiceBankAccount" style="width:120px;"
										value="${supplierInfo.invoiceBankAccount }" maxlength="40"/>
								</td>
							</tr>
							<tr>
								<logic:equal value="0" name="supplierInfo"
									property="invoiceType">
									<td class="td_01" id="rate7" style="display:block;">
										地址
									</td>
									<td class="td_01" id="rate8" style="display:none;">
										<span style="color:#FF0000">*</span>&nbsp;地址
									</td>
								</logic:equal>
								<logic:equal value="1" name="supplierInfo"
									property="invoiceType">
									<td class="td_01" id="rate7" style="display:none;">
										地址
									</td>
									<td class="td_01" id="rate8" style="display:block;">
										<span style="color:#FF0000">*</span>&nbsp;地址
									</td>
								</logic:equal>
								<td class="td_02">
									<input type="text" name="supplier.invoiceBankAddress"
										id="supplier.invoiceBankAddress" style="width:240px;"
										value="${supplierInfo.invoiceBankAddress }" maxlength="40"/>
								</td>
								<logic:equal value="0" name="supplierInfo"
									property="invoiceType">
									<td class="td_01" id="rate9" style="display:block;">
										电话
									</td>
									<td class="td_01" id="rate10" style="display:none;">
										<span style="color:#FF0000">*</span>&nbsp;电话
									</td>
								</logic:equal>
								<logic:equal value="1" name="supplierInfo"
									property="invoiceType">
									<td class="td_01" id="rate9" style="display:none;">
										电话
									</td>
									<td class="td_01" id="rate10" style="display:block;">
										<span style="color:#FF0000">*</span>&nbsp;电话
									</td>
								</logic:equal>
								<td class="td_02">
									<input type="text" name="supplier.invoiceBankTel"
										id="supplier.invoiceBankTel" style="width:120px;"
										value="${supplierInfo.invoiceBankTel }" maxlength="20"/>
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
								<td class="td_01" width="20%">
									开户行
								</td>
								<td class="td_02" width="30%">
									<input type="text" name="supplier.remitBankName"
										id="supplier.remitBankName" style="width:240px;"
										value="${supplierInfo.remitBankName }" maxlength="20"/>
								</td>
								<td class="td_01" width="20%">
									帐号
								</td>
								<td class="td_02" width="30%">
									<input type="text" name="supplier.remitBankAccount"
										id="supplier.remitBankAccount" style="width:120px;"
										value="${supplierInfo.remitBankAccount }" maxlength="40"/>
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
								<th nowrap="nowrap" width="240">
									货物接收单位名称
								</th>
								<th nowrap="nowrap">
									发货地址
								</th>
								<th nowrap="nowrap" width="36">
									邮编
								</th>
								<th nowrap="nowrap" width="48">
									联系人
								</th>
								<th nowrap="nowrap" width="120">
									电话
								</th>
								<th nowrap="nowrap" width="72">
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
										<td nowrap="nowrap">
											${address.receiveName}&nbsp;
										</td>
										<td nowrap="nowrap">
											${address.supplierAddress}
										</td>
										<td nowrap="nowrap">
											${address.supplierPostcode}&nbsp;
										</td>
										<td nowrap="nowrap" >
											${address.supplierLinkman}&nbsp;
										</td>
										<td nowrap="nowrap" >
											${address.supplierTel}&nbsp;
										</td>
										<td nowrap="nowrap">
											${address.supplierMobile}&nbsp;
										</td>
										<td nowrap="nowrap">
											<a href="#"
												onclick="javascript:window.open('${ctx}/customer.do?method=showAddressUpdate&addressId='+${address.receiveId },'newwindow', 'toolbar=no,scrollbars=yes,resizable=no,top=200,left=400, width=700,height=200');">修改</a>
										</td>
									</tr>
								</logic:iterate>
							</logic:present>
						</table>
						<br />
						<table border="0" cellpadding="0" cellspacing="0" width="100%"
							id="ec_table">
							<tr>
								<td width="41px" align="left">&nbsp;&nbsp;<input name="input2" type="checkbox" onclick="che1(this);" />
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
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td></td>
					<td height="50px" align="center" valign="bottom">
						<a href="#"><img src="${ctx}/images/btnUpdate.gif" width="65" height="20"
							onclick="check();" /></a>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="${ctx}/customer.do?method=supplierAll"><img
								src="${ctx}/images/btnBack.gif" /> </a>
					</td>
					<td></td>
				</tr>
			</table>
			<br />
		</html:form>
	</body>
</html>
