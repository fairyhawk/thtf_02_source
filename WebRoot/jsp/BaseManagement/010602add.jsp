<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>供货商添加</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../../js/tad_bs.js"></script>
		<script type="text/javascript" src="${ctx}/js/ajax.js"></script>
		<script type="text/javascript" src="${ctx}/js/util.js"></script>
		<script type="text/javascript">
	var currentSort;
//下拉列表项改变时的操作
	function proChange(objVal){
	 createXMLHttpRequest();
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
	citySel.options.length=1;
	for(var i=0;i<cityName.length;i++){
	var opt = document.createElement("OPTION");
	opt.text = xmlDoc.getElementsByTagName("cityName")[i].firstChild.data;
	opt.value=xmlDoc.getElementsByTagName("cityName")[i].firstChild.data;
	citySel.add(opt);
	areaName = xmlDoc.getElementsByTagName("areaName")[0].firstChild.data;
	}
	document.getElementById("supplier.area").innerHTML=areaName;
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
	
	function checks(){
		var err = document.getElementById("err").value;
		if(err==null||err==""){
			
		} 
		else if(err!= null){
			alert("添加失败！");
			return false;
		}
	}
</script>
	</head>
	<body onload='checks();'>
		<input type="hidden" name="err" value="${err}" id="err"></input>
		<html:form action="/supplier.do?method=addSupplier" method="post">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td class="ye_header_left">
						&nbsp;
					</td>
					<td class="ye_header_center">
						<img src="${ctx}/images/main_jt.jpg" />
						&nbsp;当前位置： 基础信息管理 &gt;&gt; 供货商信息管理 &gt;&gt; 供货商添加
					</td>
					<td class="ye_header_right">
						&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
					<td>
						<br />
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="biao3">
							<tr>
								<td class="td_01">
									<span style="color:#FF0000">*</span>&nbsp;供货商名称
								</td>
								<td class="td_02">
									<input type="text" name="supplier.name" id="supplier.name"
										style="width:240px;" maxlength="40"/>
								</td>
								<td class="td_01">
									区域
								</td>
								<td class="td_02" id="supplier.area">
								&nbsp;
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
												<option value="${province.name}">
													${province.name}
												</option>
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
									</select>
								</td>
							</tr>
							<tr>
								<td class="td_01">
									<span style="color:#FF0000">*</span>&nbsp;通信地址
								</td>
								<td class="td_02">
									<input type="text" name="supplier.address"
										id="supplier.address" style="width:240px;" maxlength="40"/>
								</td>
								<td class="td_01">
									邮编
								</td>
								<td class="td_02">
									<input type="text" name="supplier.postcode"
										id="supplier.postcode" style="width:120px;" maxlength="6"/>
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
								<td class="td_01">
									<span style="color:#FF0000">*</span>&nbsp;发票类型
								</td>
								<td class="td_02">
									<select name="supplier.invoiceType" id="supplier.invoiceType"
										style=" width:126px" onchange="selectTaxRate(this.value);">
										<option value="0" selected="selected">
											普通
										</option>
										<option value="1">
											增值税
										</option>
									</select>
								</td>
								<td class="td_01" id="rate1" style="display:none;">
									<span style="color:#FF0000">*</span>&nbsp;增值税税率
								</td>
								<td class="td_02" id="rate2" style="display:none;">
									<input type="text" name="supplier.taxRate"
										id="supplier.taxRate" style="width:120px;" maxlength="2"/>&nbsp;%
								</td>
								<td class="td_01" id="rate3" style="display:block;">
									<span style="color:#FF0000"></span>&nbsp;
								</td>
								<td class="td_02" id="rate4" style="display:block;">
								&nbsp;
								</td>
							</tr>
							<tr>
								<td class="td_01">
									开户行
								</td>
								<td class="td_02">
									<input type="text" name="supplier.invoiceBankName"
										id="supplier.invoiceBankName" style="width:240px;" maxlength="20"/>
								</td>
								<td class="td_01">
									税号
								</td>
								<td class="td_02">
									<input type="text" name="supplier.taxNumber"
										id="supplier.taxNumber" style="width:120px;" maxlength="20"/>
								</td>
							</tr>
							<tr>
								<td class="td_01">
									帐号
								</td>
								<td class="td_02">
									<input type="text" name="supplier.invoiceBankAccount"
										id="supplier.invoiceBankAccount" style="width:120px;" maxlength="40"/>
								</td>
								<td class="td_01">
									电话
								</td>
								<td class="td_02">
									<input type="text" name="supplier.invoiceBankTel"
										id="supplier.invoiceBankTel" style="width:120px;" maxlength="20"/>
								</td>
							</tr>
							<tr>
								<td class="td_01">
									地址
								</td>
								<td class="td_02">
									<input type="text" name="supplier.invoiceBankAddress"
										id="supplier.invoiceBankAddress" style="width:240px;" maxlength="40"/>
								</td>
								<td class="td_01">
									<span style="color:#FF0000"></span>&nbsp;
								</td>
								<td class="td_02">
								&nbsp;
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
								<td class="td_01">
									<span style="color:#FF0000">*</span>&nbsp;开户行
								</td>
								<td class="td_02">
									<input type="text" name="supplier.remitBankName"
										id="supplier.remitBankName" style="width:240px;" maxlength="20"/>
								</td>
								<td class="td_01">
									<span style="color:#FF0000">*</span>&nbsp;帐号
								</td>
								<td class="td_02">
									<input type="text" name="supplier.remitBankAccount"
										id="supplier.remitBankAccount" style="width:120px;" maxlength="40"/>
								</td>
							</tr>
						</table>
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td></td>
					<td height="50px" align="center" valign="bottom">
						<a href="#"><img src="${ctx}/images/btnAdd.gif" onclick="check();" /></a>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="${ctx}/supplier.do?method=supplierAll"><img
								src="${ctx}/images/btnBack.gif" /> </a>
					</td>
					<td></td>
				</tr>
			</table>
		</html:form>
	</body>
</html>
