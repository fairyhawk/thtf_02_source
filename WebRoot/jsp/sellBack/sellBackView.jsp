<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="tf" uri="localhost" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>退款查看</title>
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
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td class="ye_header_left">&nbsp;</td>
		<td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 销售管理 &gt;&gt 销售退款管理 &gt;&gt; 退款查看</td>
		<td class="ye_header_right">&nbsp;</td>
	</tr>
	<tr>
		<td >&nbsp;</td>
		<td><br />
			<div class="div_tiao"> &nbsp;&nbsp;客户信息 </div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
				<tr>
					<td class="td_01" width="20%" height="18px">客户名称</td>
					<td class="td_02" width="30%">${assessDto.customerName}&nbsp;</td>
					<td class="td_01" width="20%">联系人</td>
					<td class="td_02" width="30%">${assessDto.name}&nbsp;</td>
				</tr>
				<tr>
					<td class="td_01" height="18px">省份</td>
					<td class="td_02">${assessDto.province}&nbsp;</td>
					<td class="td_01">电话</td>
					<td class="td_02">${assessDto.tel}&nbsp;</td>
				</tr>
				<tr>
					<td class="td_01" height="18px">城市</td>
					<td class="td_02">${assessDto.city}&nbsp;</td>
					<td class="td_01">传真</td>
					<td class="td_02">${assessDto.fax}&nbsp;</td>
				</tr>
				<tr>
					<td class="td_01" height="18px">汇款银行名称</td>
					<td class="td_02">${assessDto.remitBankName}&nbsp;</td>
					<td class="td_01">汇款银行帐号</td>
					<td class="td_02">${assessDto.remitBankAccount}&nbsp;</td>
				</tr>
			</table>
			<br />
			<div class="div_tiao"> &nbsp;&nbsp;回款信息 </div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
				<tr>
					<td class="td_01" width="20%" height="18px">回款金额</td>
					<td class="td_02" width="30%"><fmt:formatNumber value="${sellBackDto.mreturnMoney}" pattern="#,##0.00"/>&nbsp;元</td>
					<td class="td_01" width="20%">指定金额</td>
					<td class="td_02" width="30%"><fmt:formatNumber value="${sellBackDto.detailMoney}" pattern="#,##0.00"/>&nbsp;元</td>
				</tr>
				<tr>
					<td class="td_01" height="18px">合同预收金额</td>
					<td class="td_02"><fmt:formatNumber value="${sellBackDto.contMoney}" pattern="#,##0.00"/>&nbsp;元</td>
					<td class="td_01">产品分类预收金额</td>
					<td class="td_02"><fmt:formatNumber value="${sellBackDto.prodMoney}" pattern="#,##0.00"/>&nbsp;元</td>
				</tr>
				<tr>
					<td class="td_01" height="18px">已退款金额</td>
					<td class="td_02"><fmt:formatNumber value="${sellBackDto.backSuccessmoney}" pattern="#,##0.00"/>&nbsp;元</td>
					<td class="td_01">回款可退款金额</td>
					<td class="td_02"><fmt:formatNumber value="${sellBackDto.mreturnCanBackMoney}" pattern="#,##0.00"/>&nbsp;元</td>
				</tr>
			</table>
			<br />
			<div class="div_tiao"> &nbsp;&nbsp;产品分类信用信息 </div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
				<tr>
					<td class="td_01" width="20%" height="18px">产品分类预收金额合计</td>
					<td class="td_02" width="30%"><fmt:formatNumber value="${sellBackDto.prodMoneySum}" pattern="#,##0.00"/>&nbsp;元</td>
					<td class="td_01" width="20%">产品分类可退款金额</td>
					<td class="td_02" width="30%"><fmt:formatNumber value="${sellBackDto.proCanBackMoney}" pattern="#,##0.00"/>&nbsp;元</td>
				</tr>
			</table>
			<div style="width:100%; text-align:right">单位：元</div>
	  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table1">
				<tr>
					<th nowrap="nowrap" >产品分类名称</th>
					<th nowrap="nowrap" >信用类型名称</th>
					<th nowrap="nowrap">项目名称</th>
					<th nowrap="nowrap">帐期</th>
					<th nowrap="nowrap">信用额度</th>
					<th nowrap="nowrap">已用金额</th>
				</tr>
				  <logic:iterate id="customerProInfoListDto" name="customerProInfoListDtos" >
				<tr>
					<td nowrap="nowrap" height="18px">${customerProInfoListDto.productName}&nbsp;</td>
					<td nowrap="nowrap" >${customerProInfoListDto.creditTypeName}&nbsp;</td>
					<td nowrap="nowrap" >${customerProInfoListDto.projectName}&nbsp;</td>
					<td nowrap="nowrap" style="text-align:right;">${customerProInfoListDto.arterm}&nbsp;</td>
					<td nowrap="nowrap" style="text-align:right;"><fmt:formatNumber value="${customerProInfoListDto.climit}" pattern="#,##0.00"/>&nbsp;</td>
					<td nowrap="nowrap" style="text-align:right;"><fmt:formatNumber value="${customerProInfoListDto.canUseLimit}" pattern="#,##0.00"/>&nbsp;</td>
				</tr>
				</logic:iterate>
			</table>
			<br />
			<div class="div_tiao"> &nbsp;&nbsp;退款信息 </div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
				<tr>
					<td class="td_01" width="20%" height="18px">退款方式</td>
					<td class="td_02" width="30%">
					<logic:equal value="7" property="backWay" name="assessDto">
            		            其他
            	    </logic:equal>
				    <logic:equal value="2" property="backWay" name="assessDto">
            		            支票
            	    </logic:equal>
            	    <logic:equal value="3" property="backWay" name="assessDto">
            		           网银
            	    </logic:equal>
            	    <logic:equal value="4" property="backWay" name="assessDto">
            		           电汇
            	    </logic:equal>
            	    <logic:equal value="5" property="backWay" name="assessDto">
            		           银行承兑
            	    </logic:equal>
					&nbsp;</td>
					<td class="td_01" width="20%">退款金额</td>
					<td class="td_02" width="30%"><fmt:formatNumber value="${assessDto.money}" pattern="#,##0.00"/>&nbsp;元</td>
				</tr>
			</table>
			<br/>
			<div class="div_tiao"> &nbsp;&nbsp;特殊说明</div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
				<tr>
					<td class="td_03" width="20%">特殊说明</td>
					<td class="td_04" style="padding:5px 100px 5px 5px">
					${tf:replaceBr(assessDto.text)}
					&nbsp;</td>
				</tr>
			</table>
			<br />
			<div class="div_tiao"> &nbsp;&nbsp;评审意见</div>
			<table border="0"  cellpadding="0" cellspacing="0" align="center" width="460">
				<tr>
					<td height="20px" colspan="2" >销售总监评审意见：</td>
				</tr>
				<tr>
					<td width="320">&nbsp;</td>
					<td width="150" height="18px"><input type="checkbox" name="assessDto.sellMajIdea" id="checkbox" disabled="disabled" 
                         <c:if test='${assessDto.sellMajIdea == "1"}'>checked="checked"</c:if>  
                      />符合
						&nbsp;&nbsp;
						<input type="checkbox" name="assessDto.sellMajIdea" id="checkbox" disabled="disabled"
                          <c:if test='${assessDto.sellMajIdea == "0"}'>checked="checked"</c:if>  
                       />不符合
						</td>
				</tr>
				<tr>
					<td colspan="2" valign="top"><table width="98%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap>补充说明：</td>
								<td style="padding:5px 0 5px 0;">
								${tf:replaceBr(assessDto.sellMajText)}
								</td>
							</tr>
						</table></td>
				</tr>
				<tr>
					<td height="20px">签字：${assessDto.sellMajName}</td>
					<td>日期：${assessDto.sellMajDate}</td>
				</tr>
				<tr>
					<td height="20px" colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td height="20px" colspan="2">运营总监评审意见：</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td height="20px"><input type="checkbox" name="assessDto.opeMajIdea " id="checkbox" disabled="disabled"
                           <c:if test='${assessDto.opeMajIdea == "1"}'>checked="checked"</c:if>  
                      />符合
						&nbsp;&nbsp;
						<input type="checkbox" name="assessDto.opeMajIdea" id="checkbox" disabled="disabled" 
                           <c:if test='${assessDto.opeMajIdea == "0"}'>checked="checked"</c:if> 
                      />不符合
                      </td>
				</tr>
				<tr>
					<td colspan="2" valign="top"><table width="98%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap>补充说明：</td>
								<td style="padding:5px 0 5px 0;">
								${tf:replaceBr(assessDto.opeMajText)}
							    </td>
							</tr>
						</table></td>
				</tr>
				<tr>
					<td height="20px">签字：${assessDto.opeMajName}</td>
					<td>日期：${assessDto.opeMajDate}</td>
				</tr>
		</table></td>
		<td >&nbsp;</td>
	</tr>
	<tr>
		<td></td>
		<td height="50px" align="center" valign="bottom">
		<a href="${ctx}/getSellBackList.do">
		<img src="${ctx}/images/btnBack.gif" />
		</a></td>
		<td></td>
	</tr>
</table>
<br />
</body>
</html>
