<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>公司信息管理</title>
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
		.rowselected {
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
		<script type="text/javascript" src="${ctx}/js/util.js"></script>
		<script type="text/javascript">
		var formId = 0;
		//控件名	
		var checknNames = [ "company.name", "company.invoice_bank_name", "company.invoice_bank_account","company.invoice_bank_address","company.invoice_bank_tel","company.tax_number","company.tax_rate"];
		//提示语
		var descriptions = [ "公司名称", "开户行", "帐号", "地址","电话","税号","增值税税率"];
		//是否非空验证,如果非空验证填写notnull,如果不需要非空验证传空参
		var checkNulls = [ "notnull", "notnull", "notnull", "notnull" ,"notnull","notnull","notnull" ,"null","null"];
		//判断类型验证，只填数字验证填"num",    只填字母验证“abc”,    email验证“email”，非零开头的数字和零 "0num",  邮箱验证 "zip" ,登录名验证  “loginname” ,电话 “phone”
		var checkTypes = [ "", "", "", "","phone","","num","",""];
		//判断长度验证 如果判断是否等于  参数前面加0, 否则正常  不需要长度验证则传空参
		var checkLengths = [ "80", "40", "20", "80" ,"20","20","2","40","20"];
		function check(){
		if(checkForm()==false){
			return ;
			}
				document.forms[0].submit();
		};
		
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
				window.location = '${ctx}/companyadress.do?method=companyDelete&id='+ myArray.join(',');
			}else{
			}
		};

	</script>
	</head>
	<body>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td class="ye_header_left">&nbsp;
					
				</td>
				<td class="ye_header_center" style="font-size:12px;">
					<img src="${ctx}/images/main_jt.jpg" />
					&nbsp;当前位置： 基础信息管理 &gt;&gt; 公司信息管理
				</td>
				<td class="ye_header_right">&nbsp;
					
				</td>
			</tr>
			<tr>
				<td>&nbsp;
					
				</td>
				<td>
					<br />
					<div class="div_tiao">
						&nbsp;&nbsp;公司信息
					</div>
					<html:form action="/company" method="post">
						<input type="hidden" name="method" value="companyAdd" />
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="biao3">
							<tr>
								<td class="td_03" width="20%">
									<span style="color:#FF0000">*</span>&nbsp;公司名称
								</td>
								<td class="td_04">
									<input type="text" name="company.name" id="name"
										style="width:240px;" maxlength="40" value="${cinfos.name}" />
									<input type="hidden" name="company.id" maxlength="20"
										value="${cinfos.id}" />
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
									<span style="color:#FF0000">*</span>&nbsp;开户行
								</td>
								<td class="td_02" width="30%">
									<input type="text" name="company.invoice_bank_name"
										id="invoice_bank_name" style="width:240px;" maxlength="20"
										value="${cinfos.invoice_bank_name}" />
								</td>
								<td class="td_01" width="20%">
									<span style="color:#FF0000">*</span>&nbsp;帐号
								</td>
								<td class="td_02" width="30%">
									<input type="text" name="company.invoice_bank_account"
										id="invoice_bank_account" style="width:120px;" maxlength="20"
										value="${cinfos.invoice_bank_account}" />
								</td>
							</tr>
							<tr>
								<td class="td_01">
									<span style="color:#FF0000">*</span>&nbsp;地址
								</td>
								<td class="td_02">
									<input type="text" name="company.invoice_bank_address"
										id="invoice_bank_address" style="width:240px;" maxlength="40"
										value="${cinfos.invoice_bank_address}" />
								</td>
								<td class="td_01">
									<span style="color:#FF0000">*</span>&nbsp;电话
								</td>
								<td class="td_02">
									<input type="text" name="company.invoice_bank_tel"
										id="invoice_bank_tel" style="width:120px;" maxlength="20"
										value="${cinfos.invoice_bank_tel}" />
								</td>
							</tr>
							<tr>
								<td class="td_01">
									<span style="color:#FF0000">*</span>&nbsp;税号
								</td>
								<td class="td_02">
									<input type="text" name="company.tax_number" id="tax_number"
										style="width:120px;" maxlength="20"
										value="${cinfos.tax_number}" />
								</td>
								<td class="td_01">
									<span style="color:#FF0000">*</span>&nbsp;增值税税率
								</td>
								<td class="td_02">
									<input type="text" name="company.tax_rate" id="tax_rate"
										style="width:120px;" value="${cinfos.tax_rate}" maxlength="2"/>&nbsp;%
								</td>
							</tr>
						</table>
						<br />
					</html:form>
					<div class="div_tiao" style="font-size:12px;">
						&nbsp;&nbsp;收货地址信息
					</div>
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="xxxlist">
						<tr>
							<!--
							<th nowrap="nowrap" width="41">
								选择
							</th>
							-->
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
						<tr>
							<logic:present name="cadresslist">
								<logic:iterate id="cadressall" name="cadresslist">
									<tr>
										<!--
										<td nowrap="nowrap">
											&nbsp;<input type="checkbox" name="ss" value="${cadressall.id}"
												id="box" onclick="javascript:removeBefore(this);">
										</td>
										-->
										<td nowrap="nowrap" width="240px">
											${cadressall.name}&nbsp;
										</td>
										<td nowrap="nowrap"  width="480px">
											${cadressall.address}&nbsp;
										</td>
										<td nowrap="nowrap" width="40">
											${cadressall.postcode}&nbsp;
										</td>
										<td nowrap="nowrap" width="40">
											${cadressall.linkman}&nbsp;
										</td>
										<td nowrap="nowrap" width="40">
											${cadressall.tel}&nbsp;
										</td>
										<td nowrap="nowrap" width="40">
											${cadressall.mobile} &nbsp;
										</td>
										<td nowrap="nowrap" width="30px">
											<a href="#"
												onclick="javascript:window.open('${ctx}/companyadress.do?method=showCompanyAddressUpdate&id=${cadressall.id}','newwindow', 'toolbar=no,scrollbars=yes,resizable=no,top=200,left=400, width=700,height=200');">修改</a>
										</td>
									</tr>
								</logic:iterate>
							</logic:present>
						</tr>
					</table>
					<br />
					<table border="0" cellpadding="0" cellspacing="0" width="100%"
						id="ec_table">
						<tr>
							<!--
							<td width="41px" align="left">
								&nbsp;&nbsp;<input type="checkbox" name="zong" onclick="che(this);">
							</td>
							<td width="50px" style="font-size:12px;">
								全选
							</td>
							<td width="100px">
								<a href="javascript:shan();"> <img
										src="${ctx}/images/btnDelete.gif" /> </a>
							</td>
							-->
							<td width="100px">
								<a href="#"
									onclick="javascript:window.open('${ctx}/companyadress.do?method=showCompanyAddressAdd','newwindow', 'toolbar=no,scrollbars=yes,resizable=no,top=200,left=400, width=700,height=200');"><img
										src="${ctx}/images/btnAdd.gif" /> </a>
							</td>
							<td align="right">
								&nbsp;&nbsp;
							</td>
						</tr>
					</table>
				</td>
				<td>&nbsp;
				</td>
			</tr>
			<tr>
				<td></td>
				<td height="34px" align="center" valign="bottom">
					<a href="javascript:check();"> <img
							src="${ctx}/images/btnSave.gif" width="65" height="20" /> </a>
				</td>
				<td></td>
			</tr>
		</table>
		<br />
	</body>
</html>
