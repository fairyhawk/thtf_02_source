<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>物流公司查看</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../../js/tad_bs2.js"></script>
	</head>
	<body>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td class="ye_header_left">
					&nbsp;
				</td>
				<td class="ye_header_center">
					<img src="${ctx}/images/main_jt.jpg" />
					&nbsp;当前位置： 基础信息管理 &gt;&gt; 物流公司信息管理 &gt;&gt; 物流公司查看
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
								物流公司名称
							</td>
							<td class="td_02" width="30%">
								${logisticsInfo.name}
								<input type="hidden" name="ids" value="${logisticsInfo.id}">
							</td>
							<td class="td_01" height="18px">
								区域
							</td>
							<td class="td_02">
								${logisticsInfo.area}&nbsp;
							</td>
						</tr>
						<tr>
							<td class="td_01" height="18px">
								省
							</td>
							<td class="td_02">
								${logisticsInfo.province}
							</td>
							<td class="td_01">
								市
							</td>
							<td class="td_02">
								${logisticsInfo.city}
							</td>
						</tr>
						<tr>
							<td class="td_01" height="18px">
								通信地址
							</td>
							<td class="td_02">
								${logisticsInfo.address}
							</td>
							<td class="td_01">
								邮编
							</td>
							<td class="td_02">
								${logisticsInfo.postcode}
							</td>
						</tr>
						<tr>
							<td class="td_01" width="20%" height="18px">
								合作开始时间
							</td>
							<td class="td_02" width="30%">
								${logisticsInfo.date}
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
						&nbsp;&nbsp;联系人信息
					</div>
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="biao3">
						<tr>
							<td class="td_01" width="20%" height="18px">
								联系人姓名
							</td>
							<td class="td_02" width="30%">
								${logisticsInfo.linkman}
							</td>
							<td class="td_01" width="20%">
								职务
							</td>
							<td class="td_02" width="30%">
								${logisticsInfo.role}&nbsp;
							</td>
						</tr>
						<tr>
							<td class="td_01" height="18px">
								电话
							</td>
							<td class="td_02">
								${logisticsInfo.tel}
							</td>
							<td class="td_01">
								传真
							</td>
							<td class="td_02">
								${logisticsInfo.fax}&nbsp;
							</td>
						</tr>
						<tr>
							<td class="td_01" height="18px">
								手机
							</td>
							<td class="td_02">
								${logisticsInfo.mobile}&nbsp;
							</td>
							<td class="td_01">
								Email
							</td>
							<td class="td_02">
								${logisticsInfo.mail}&nbsp;
							</td>
						</tr>
						<tr>
							<td class="td_01" height="18px">
								QQ
							</td>
							<td class="td_02">
								${logisticsInfo.qq}&nbsp;
							</td>
							<td class="td_01">
								MSN
							</td>
							<td class="td_02">
								${logisticsInfo.msn}&nbsp;
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
							<td class="td_01" width="20%" height="18px">
								开户行
							</td>
							<td class="td_02" width="30%"> 
								${logisticsInfo.remitBankName}
							</td>
							<td class="td_01" width="20%">
								帐号
							</td>
							<td class="td_02" width="30%">
								${logisticsInfo.remitBankAccount}
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
				<td align="center" valign="bottom" height="50">
					<a href="${ctx}/logistics.do?method=logisticsAll"><img src="${ctx}/images/btnBack.gif" /> </a>
				</td>
				<td></td>
			</tr>
		</table>
	</body>
</html>
