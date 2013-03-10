<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>供货商查看</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
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
				if($("#table")){
					$("#table tr:even").addClass("treven");
					$("#table tr").each(function(i){
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
		<script type="text/javascript">
</script>
	</head>

	<body>
		<form action="supplier.do?method=supplierAll" method="post">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td class="ye_header_left">
						&nbsp;
					</td>
					<td class="ye_header_center">
						<img src="${ctx}/images/main_jt.jpg" />
						&nbsp;当前位置： 基础信息管理 &gt;&gt; 供货商信息管理  &gt;&gt; 供货商查看
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
								<td class="td_01" width="20%" height="18px">
									<span class="ye_header_center"></span>供货商名称
								</td>
								<td class="td_02" width="30%">
									${supplier.name}
								</td>
								<td class="td_01" width="20%">
									区域
								</td>
								<td class="td_02" width="30%">
									${supplier.area}
								</td>
							</tr>
							<tr>
								<td class="td_01" height="18px">
									省
								</td>
								<td class="td_02">
									${supplier.province}
								</td>
								<td class="td_01">
									市
								</td>
								<td class="td_02">
									${supplier.city}
								</td>
							</tr>
							<tr>
								<td class="td_01" height="18px">
									通信地址
								</td>
								<td class="td_02">
									${supplier.address}
								</td>
								<td class="td_01">
									邮编
								</td>
								<td class="td_02">
									${supplier.postcode}
								</td>
							</tr>
						</table>
						<br />
						<div class="div_tiao">
							&nbsp;&nbsp;联系人信息
						</div>
						<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="xxxlist">
							<tr>
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
							</tr>
							<logic:present name="linkList">
								<logic:iterate id="link" name="linkList">
									<tr>
										<td nowrap="nowrap" height="18px">
											${link.linkman}
										</td>
										<td nowrap="nowrap" width="120px">
											${link.role }&nbsp;
										</td>
										<td nowrap="nowrap" width="120px">
											${link.tel}&nbsp;
										</td>
										<td nowrap="nowrap" width="120px">
											${link.fax }&nbsp;
										</td>
										<td nowrap="nowrap" width="72px">
											${link.mobile}&nbsp;
										</td>
										<td nowrap="nowrap" width="180px">
											${link.mail }&nbsp;
										</td>
										<td nowrap="nowrap" width="180px">
											${link.msn}&nbsp;
										</td>
										<td nowrap="nowrap" width="72px">
											${link.qq}&nbsp;
										</td>
									</tr>
								</logic:iterate>
							</logic:present>
						</table>
						<br />
						<br />
						<div class="div_tiao">
							&nbsp;&nbsp;开票信息
						</div>
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="biao3">
							<tr>
								<td class="td_01" width="20%" height="18px">
									发票类型
								</td>
								<td class="td_02" width="30%">
									<logic:equal value="0" name="supplier" property="invoiceType">
									普通
									</logic:equal>
									<logic:equal value="1" name="supplier" property="invoiceType">
									增值税
									</logic:equal>
								</td>

								<logic:equal value="0" name="supplier" property="invoiceType">
									<td class="td_01" width="20%">
									</td>
									<td class="td_02" width="30%">
									</td>
								</logic:equal>
								<logic:equal value="1" name="supplier" property="invoiceType">
									<td class="td_01">
										增值税税率
									</td>
									<td class="td_02">
										${supplier.taxRate}&nbsp;%
									</td>
								</logic:equal>
							</tr>
							<tr>
								<td class="td_01" height="18px">
									开户行
								</td>
								<td class="td_02">
									${supplier.invoiceBankName}
								</td>
								<td class="td_01">
									税号
								</td>
								<td class="td_02">
									${supplier.taxNumber}
								</td>
							</tr>
							<tr>
								<td class="td_01" height="18px">
									帐号
								</td>
								<td class="td_02">
									${supplier.invoiceBankAccount}
								</td>
								<td class="td_01">
									电话
								</td>
								<td class="td_02">
									${supplier.invoiceBankTel}
								</td>
							</tr>
							<tr>
								<td class="td_01" height="18px">
									地址
								</td>
								<td class="td_02">
									${supplier.invoiceBankAddress }
								</td>
								<td class="td_01">
									&nbsp;
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
								<td class="td_01" height="18px" width="20%">
									开户行
								</td>
								<td class="td_02" width="30%">
									${supplier.remitBankName}
								</td>
								<td class="td_01" width="20%">
									帐号
								</td>
								<td class="td_02" width="30%">
									${supplier.remitBankAccount}
								</td>
							</tr>
						</table>
						<br />
						<div class="div_tiao">
							&nbsp;&nbsp;发货信息
						</div>
						<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table">
							<tr>
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
							</tr>
							<logic:present name="addressList">
								<logic:iterate id="address" name="addressList">
									<tr>
										<td nowrap="nowrap" width="240px"height="18px">
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
									</tr>
								</logic:iterate>
							</logic:present>
						</table>
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td></td>
					<td height="50px" align="center" valign="bottom">
						<a href="${ctx}/supplier.do?method=supplierAll"><img src="${ctx}/images/btnBack.gif" width="65" height="20" /> </a>
					</td>
					<td></td>
				</tr>
			</table>
			<br />
		</form>
	</body>
</html>
